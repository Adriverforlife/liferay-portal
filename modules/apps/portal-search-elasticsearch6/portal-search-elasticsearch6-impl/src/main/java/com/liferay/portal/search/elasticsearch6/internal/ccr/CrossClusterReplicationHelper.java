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

package com.liferay.portal.search.elasticsearch6.internal.ccr;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnectionManager;

import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.KeyStore;

import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.client.CcrClient;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ccr.PauseFollowRequest;
import org.elasticsearch.client.ccr.PutFollowRequest;
import org.elasticsearch.client.ccr.UnfollowRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Bryan Engler
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration",
	immediate = true, service = CrossClusterReplicationHelper.class
)
public class CrossClusterReplicationHelper {

	public void follow(String indexName) {
		if (!elasticsearchConnectionManager.
				isCrossClusterReplicationEnabled()) {

			return;
		}

		try {
			_putFollow(indexName);
		}
		catch (Exception exception) {
			_log.error(
				StringBundler.concat(
					"Unable to follow the index ", indexName, " in the ",
					_REMOTE_CLUSTER_ALIAS, " cluster"),
				exception);
		}
	}

	public void unfollow(String indexName) {
		if (!elasticsearchConnectionManager.
				isCrossClusterReplicationEnabled()) {

			return;
		}

		try {
			_pauseFollow(indexName);

			_closeIndex(indexName);

			_unfollow(indexName);

			_deleteIndex(indexName);
		}
		catch (Exception exception) {
			_log.error("Unable to unfollow the index " + indexName, exception);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	@Reference
	protected ElasticsearchConnectionManager elasticsearchConnectionManager;

	private void _closeIndex(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		IndicesClient indices = restHighLevelClient.indices();

		CloseIndexRequest closeIndexRequest = new CloseIndexRequest(indexName);

		indices.close(closeIndexRequest, RequestOptions.DEFAULT);
	}

	private void _configureSecurity(RestClientBuilder restClientBuilder) {
		restClientBuilder.setHttpClientConfigCallback(
			httpClientBuilder -> {
				httpClientBuilder.setDefaultCredentialsProvider(
					_createCredentialsProvider());

				if (crossClusterReplicationConfigurationWrapper.
						isTransportSSLEnabled()) {

					httpClientBuilder.setSSLContext(_createSSLContext());
				}

				return httpClientBuilder;
			});
	}

	private CredentialsProvider _createCredentialsProvider() {
		CredentialsProvider credentialsProvider =
			new BasicCredentialsProvider();

		credentialsProvider.setCredentials(
			AuthScope.ANY,
			new UsernamePasswordCredentials(
				crossClusterReplicationConfigurationWrapper.getUsername(),
				crossClusterReplicationConfigurationWrapper.getPassword()));

		return credentialsProvider;
	}

	private RestHighLevelClient _createRestHighLevelClient() {
		RestClientBuilder restClientBuilder = RestClient.builder(
			HttpHost.create(_elasticsearchConfiguration.networkHost()));

		if (crossClusterReplicationConfigurationWrapper.
				isAuthenticationEnabled()) {

			_configureSecurity(restClientBuilder);
		}

		return new RestHighLevelClient(restClientBuilder);
	}

	private SSLContext _createSSLContext() {
		try {
			Path path = Paths.get(
				crossClusterReplicationConfigurationWrapper.
					getSslTruststorePath());

			InputStream is = Files.newInputStream(path);

			KeyStore keyStore = KeyStore.getInstance(
				crossClusterReplicationConfigurationWrapper.
					getCertificateFormat());
			String truststorePassword =
				crossClusterReplicationConfigurationWrapper.
					getSslTruststorePassword();

			keyStore.load(is, truststorePassword.toCharArray());

			SSLContextBuilder sslContextBuilder = SSLContexts.custom();

			sslContextBuilder.loadKeyMaterial(
				keyStore, truststorePassword.toCharArray());
			sslContextBuilder.loadTrustMaterial(keyStore, null);

			return sslContextBuilder.build();
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private void _deleteIndex(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		IndicesClient indices = restHighLevelClient.indices();

		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			indexName);

		indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
	}

	private void _pauseFollow(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		CcrClient ccrClient = restHighLevelClient.ccr();

		PauseFollowRequest pauseFollowRequest = new PauseFollowRequest(
			indexName);

		ccrClient.pauseFollow(pauseFollowRequest, RequestOptions.DEFAULT);
	}

	private void _putFollow(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		CcrClient ccrClient = restHighLevelClient.ccr();

		PutFollowRequest putFollowRequest = new PutFollowRequest(
			_REMOTE_CLUSTER_ALIAS, indexName, indexName,
			ActiveShardCount.from(1));

		ccrClient.putFollow(putFollowRequest, RequestOptions.DEFAULT);
	}

	private void _unfollow(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		CcrClient ccrClient = restHighLevelClient.ccr();

		UnfollowRequest unfollowRequest = new UnfollowRequest(indexName);

		ccrClient.unfollow(unfollowRequest, RequestOptions.DEFAULT);
	}

	private static final String _REMOTE_CLUSTER_ALIAS = "leader";

	private static final Log _log = LogFactoryUtil.getLog(
		CrossClusterReplicationHelper.class);

	private volatile ElasticsearchConfiguration _elasticsearchConfiguration;

}