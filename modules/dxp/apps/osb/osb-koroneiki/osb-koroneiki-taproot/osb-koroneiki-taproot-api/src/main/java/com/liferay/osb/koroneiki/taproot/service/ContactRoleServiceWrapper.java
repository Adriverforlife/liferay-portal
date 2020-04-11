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

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRoleService
 * @generated
 */
public class ContactRoleServiceWrapper
	implements ContactRoleService, ServiceWrapper<ContactRoleService> {

	public ContactRoleServiceWrapper(ContactRoleService contactRoleService) {
		_contactRoleService = contactRoleService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactRole addContactRole(
			String name, String description, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.addContactRole(name, description, type);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactRole
			deleteContactRole(long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.deleteContactRole(contactRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactRole
			deleteContactRole(String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.deleteContactRole(contactRoleKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.ContactRole>
			getContactAccountContactRoles(
				long accountId, long contactId, String[] types, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactAccountContactRoles(
			accountId, contactId, types, start, end);
	}

	@Override
	public int getContactAccountContactRolesCount(
			long accountId, long contactId, String[] types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactAccountContactRolesCount(
			accountId, contactId, types);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.ContactRole>
			getContactContactRoles(long contactId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactContactRoles(
			contactId, start, end);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactRole getContactRole(
			long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactRole(contactRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactRole getContactRole(
			String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactRole(contactRoleKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactRole getContactRole(
			String name, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactRole(name, type);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.ContactRole>
			getContactTeamContactRoles(
				long teamId, long contactId, String[] types, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactTeamContactRoles(
			teamId, contactId, types, start, end);
	}

	@Override
	public int getContactTeamContactRolesCount(
			long teamId, long contactId, String[] types)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.getContactTeamContactRolesCount(
			teamId, contactId, types);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactRoleService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactRole
			updateContactRole(
				long contactRoleId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactRoleService.updateContactRole(
			contactRoleId, name, description);
	}

	@Override
	public ContactRoleService getWrappedService() {
		return _contactRoleService;
	}

	@Override
	public void setWrappedService(ContactRoleService contactRoleService) {
		_contactRoleService = contactRoleService;
	}

	private ContactRoleService _contactRoleService;

}