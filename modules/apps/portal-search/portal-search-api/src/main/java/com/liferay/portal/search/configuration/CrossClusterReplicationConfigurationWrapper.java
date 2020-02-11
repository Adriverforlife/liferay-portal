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

package com.liferay.portal.search.configuration;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Bryan Engler
 */
@ProviderType
public interface CrossClusterReplicationConfigurationWrapper {

	public String getCertificateFormat();

	public String getClusterName();

	public String getPassword();

	public String[] getSslCertificateAuthoritiesPaths();

	public String getSslCertificatePath();

	public String getSslKeyPath();

	public String getSslKeystorePassword();

	public String getSslKeystorePath();

	public String getSslTruststorePassword();

	public String getSslTruststorePath();

	public String[] getTransportAddresses();

	public String getTransportSSLVerificationMode();

	public String getUsername();

	public boolean isAuthenticationEnabled();

	public boolean isCCREnabled();

	public boolean isClientTransportIgnoreClusterName();

	public boolean isTransportSSLEnabled();

}