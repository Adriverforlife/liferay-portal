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

package com.liferay.portal.remote.soap.extender.test;

import com.liferay.petra.string.StringBundler;

import javax.xml.ws.Endpoint;
import javax.xml.ws.spi.Provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 * @author Carlos Sierra Andrés
 */
public class JaxWsApiBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		_configurationAdminBundleActivator =
			new ConfigurationAdminBundleActivator();

		_configurationAdminBundleActivator.start(bundleContext);

		String filterString = StringBundler.concat(
			"(&(objectClass=", Provider.class.getName(), ")(",
			HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_PATH,
			"=/soap-test))");

		WaiterUtil.waitForFilter(bundleContext, filterString, 10_000);

		try {
			_endpoint = Endpoint.publish("/greeterApi", new GreeterImpl());
		}
		catch (Exception exception) {
			cleanUp(bundleContext);

			throw exception;
		}
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		cleanUp(bundleContext);
	}

	protected void cleanUp(BundleContext bundleContext) throws Exception {
		try {
			_endpoint.stop();
		}
		catch (Exception exception) {
		}

		_configurationAdminBundleActivator.stop(bundleContext);
	}

	private ConfigurationAdminBundleActivator
		_configurationAdminBundleActivator;
	private Endpoint _endpoint;

}