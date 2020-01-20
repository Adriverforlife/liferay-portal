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

package com.liferay.organizations.internal.configuration.persistence.listener;

import com.liferay.organizations.internal.configuration.OrganizationTypeConfiguration;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListenerException;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Dictionary;
import java.util.ResourceBundle;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.organizations.internal.configuration.OrganizationTypeConfiguration",
	service = ConfigurationModelListener.class
)
public class OrganizationTypeConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onBeforeSave(String pid, Dictionary<String, Object> properties)
		throws ConfigurationModelListenerException {

		try {
			String name = (String)properties.get("name");

			_validateNameExists(name);

			_validateUniqueConfiguration(pid, name);
		}
		catch (Exception exception) {
			throw new ConfigurationModelListenerException(
				exception.getMessage(), OrganizationTypeConfiguration.class,
				getClass(), properties);
		}
	}

	private ResourceBundle _getResourceBundle() {
		return ResourceBundleUtil.getBundle(
			"content.Language", LocaleThreadLocal.getThemeDisplayLocale(),
			getClass());
	}

	private void _validateNameExists(String name) throws Exception {
		if (Validator.isNotNull(name)) {
			return;
		}

		ResourceBundle resourceBundle = _getResourceBundle();

		String message = ResourceBundleUtil.getString(
			resourceBundle, "an-organization-type-must-have-a-valid-name");

		throw new Exception(message);
	}

	private void _validateUniqueConfiguration(String pid, String name)
		throws Exception {

		String filterString = String.format(
			"(&(service.factoryPid=%s)(name=%s))",
			OrganizationTypeConfiguration.class.getName(), name);

		Configuration[] configurations = _configurationAdmin.listConfigurations(
			filterString);

		if (configurations == null) {
			return;
		}

		Configuration configuration = configurations[0];

		if (pid.equals(configuration.getPid())) {
			return;
		}

		ResourceBundle resourceBundle = _getResourceBundle();

		String message = ResourceBundleUtil.getString(
			resourceBundle,
			"there-is-already-an-organization-type-with-the-name-x", name);

		throw new Exception(message);
	}

	@Reference
	private ConfigurationAdmin _configurationAdmin;

}