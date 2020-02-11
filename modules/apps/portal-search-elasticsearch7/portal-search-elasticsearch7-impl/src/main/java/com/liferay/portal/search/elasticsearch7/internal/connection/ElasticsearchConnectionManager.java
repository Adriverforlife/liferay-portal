/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch7.internal.index.IndexFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration",
	immediate = true,
	service = {
		ElasticsearchClientResolver.class, ElasticsearchConnectionManager.class
	}
)
public class ElasticsearchConnectionManager
	implements ElasticsearchClientResolver {

	public void activate(OperationMode operationMode) {
		validate(operationMode);

		_operationMode = operationMode;
	}

	public void connect() {
		ElasticsearchConnection elasticsearchConnection =
			getElasticsearchConnection(false);

		elasticsearchConnection.connect();
	}

	public AdminClient getAdminClient() {
		Client client = getClient();

		return client.admin();
	}

	@Override
	public Client getClient() {
		return getClient(false);
	}

	@Override
	public Client getClient(boolean preferLocalCluster) {
		ElasticsearchConnection elasticsearchConnection =
			getElasticsearchConnection(preferLocalCluster);

		if (elasticsearchConnection == null) {
			throw new ElasticsearchConnectionNotInitializedException();
		}

		return elasticsearchConnection.getClient();
	}

	public ElasticsearchConnection getElasticsearchConnection() {
		return getElasticsearchConnection(false);
	}

	public ElasticsearchConnection getElasticsearchConnection(
		boolean preferLocalCluster) {

		if (_operationMode == null) {
			return null;
		}

		if (_operationMode == OperationMode.EMBEDDED) {
			return _elasticsearchConnections.get(
				EmbeddedElasticsearchConnection.CONNECTION_ID);
		}

		if (preferLocalCluster && isCrossClusterReplicationEnabled()) {
			return _elasticsearchConnections.get(
				CCRElasticsearchConnection.CONNECTION_ID);
		}

		return _elasticsearchConnections.get(
			RemoteElasticsearchConnection.CONNECTION_ID);
	}

	public synchronized void registerCompanyId(long companyId) {
		_companyIds.put(companyId, companyId);
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		target = "(operation.mode=CCR)", unbind = "unsetElasticsearchConnection"
	)
	public void setCCRElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnections.put(
			CCRElasticsearchConnection.CONNECTION_ID, elasticsearchConnection);
	}

	@Reference(
		cardinality = ReferenceCardinality.MANDATORY,
		target = "(operation.mode=EMBEDDED)",
		unbind = "unsetElasticsearchConnection"
	)
	public void setEmbeddedElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnections.put(
			EmbeddedElasticsearchConnection.CONNECTION_ID,
			elasticsearchConnection);
	}

	@Reference(
		cardinality = ReferenceCardinality.MANDATORY,
		target = "(operation.mode=REMOTE)",
		unbind = "unsetElasticsearchConnection"
	)
	public void setRemoteElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnections.put(
			RemoteElasticsearchConnection.CONNECTION_ID,
			elasticsearchConnection);
	}

	public synchronized void unregisterCompanyId(long companyId) {
		_companyIds.remove(companyId);
	}

	public void unsetElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnections.remove(
			elasticsearchConnection.getConnectionId());

		elasticsearchConnection.close();
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);

		activate(translate(_elasticsearchConfiguration.operationMode()));

		setCCRElasticsearchConfiguration();
	}

	protected boolean isCrossClusterReplicationEnabled() {
		if (crossClusterReplicationConfigurationWrapper == null) {
			return false;
		}

		return crossClusterReplicationConfigurationWrapper.isCCREnabled();
	}

	@Modified
	protected synchronized void modified(Map<String, Object> properties) {
		_elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);

		modify(translate(_elasticsearchConfiguration.operationMode()));

		setCCRElasticsearchConfiguration();
	}

	protected synchronized void modify(OperationMode operationMode) {
		if (Objects.equals(operationMode, _operationMode)) {
			return;
		}

		validate(operationMode);

		ElasticsearchConnection newElasticsearchConnection =
			_elasticsearchConnections.get(operationMode.toString());

		newElasticsearchConnection.connect();

		if (_operationMode != null) {
			ElasticsearchConnection oldElasticsearchConnection =
				_elasticsearchConnections.get(_operationMode.toString());

			try {
				oldElasticsearchConnection.close();
			}
			catch (Exception exception) {
				_log.error(
					"Unable to close " + oldElasticsearchConnection, exception);
			}
		}

		_operationMode = operationMode;

		for (Long companyId : _companyIds.values()) {
			try {
				indexFactory.createIndices(getAdminClient(), companyId);
			}
			catch (Exception exception) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to reinitialize index for company " + companyId,
						exception);
				}
			}
		}
	}

	protected void setCCRElasticsearchConfiguration() {
		CCRElasticsearchConnection ccrElasticsearchConnection =
			(CCRElasticsearchConnection)_elasticsearchConnections.get(
				CCRElasticsearchConnection.CONNECTION_ID);

		if (ccrElasticsearchConnection != null) {
			ccrElasticsearchConnection.setElasticsearchConfiguration(
				_elasticsearchConfiguration);
		}
	}

	protected OperationMode translate(
		com.liferay.portal.search.elasticsearch7.configuration.OperationMode
			operationMode) {

		return OperationMode.valueOf(operationMode.name());
	}

	protected void validate(OperationMode operationMode) {
		if (!_elasticsearchConnections.containsKey(operationMode.toString())) {
			throw new MissingOperationModeException(operationMode);
		}
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	@Reference(unbind = "-")
	protected IndexFactory indexFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchConnectionManager.class);

	private final Map<Long, Long> _companyIds = new HashMap<>();
	private volatile ElasticsearchConfiguration _elasticsearchConfiguration;
	private final Map<String, ElasticsearchConnection>
		_elasticsearchConnections = new HashMap<>();
	private OperationMode _operationMode;

}