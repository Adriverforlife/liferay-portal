/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.organization.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.organization.service.CommerceOrganizationServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CommerceOrganizationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceOrganizationServiceHttp
 * @see CommerceOrganizationServiceUtil
 * @generated
 */
@ProviderType
public class CommerceOrganizationServiceSoap {
	public static com.liferay.portal.kernel.model.Organization addOrganization(
		long parentOrganizationId, String name, String type,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Organization returnValue = CommerceOrganizationServiceUtil.addOrganization(parentOrganizationId,
					name, type, serviceContext);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addOrganizationUsers(long organizationId,
		String[] emailAddresses,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			CommerceOrganizationServiceUtil.addOrganizationUsers(organizationId,
				emailAddresses, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.Organization fetchOrganization(
		long organizationId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Organization returnValue = CommerceOrganizationServiceUtil.fetchOrganization(organizationId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.Organization getOrganization(
		long organizationId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Organization returnValue = CommerceOrganizationServiceUtil.getOrganization(organizationId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteOrganization(long organizationId)
		throws RemoteException {
		try {
			CommerceOrganizationServiceUtil.deleteOrganization(organizationId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.Address getOrganizationPrimaryAddress(
		long organizationId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Address returnValue = CommerceOrganizationServiceUtil.getOrganizationPrimaryAddress(organizationId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.EmailAddress getOrganizationPrimaryEmailAddress(
		long organizationId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.EmailAddress returnValue = CommerceOrganizationServiceUtil.getOrganizationPrimaryEmailAddress(organizationId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsetOrganizationUsers(long organizationId,
		long[] userIds) throws RemoteException {
		try {
			CommerceOrganizationServiceUtil.unsetOrganizationUsers(organizationId,
				userIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.Organization updateOrganization(
		long organizationId, String name, long emailAddressId, String address,
		long addressId, String street1, String street2, String street3,
		String city, String zip, long regionId, long countryId, boolean logo,
		byte[] logoBytes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Organization returnValue = CommerceOrganizationServiceUtil.updateOrganization(organizationId,
					name, emailAddressId, address, addressId, street1, street2,
					street3, city, zip, regionId, countryId, logo, logoBytes,
					serviceContext);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceOrganizationServiceSoap.class);
}