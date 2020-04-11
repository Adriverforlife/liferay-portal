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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.AccountUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.AccountEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.PostalAddressResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactService;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountResource.class
)
public class AccountResourceImpl
	extends BaseAccountResourceImpl implements EntityModelResource {

	@Override
	public void deleteAccount(String agentName, String accountKey)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_accountService.deleteAccount(accountKey);
	}

	@Override
	public void deleteAccountAccountPermission(
			String agentName, String accountKey,
			AccountPermission accountPermission)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_updateAccountPermission(accountKey, "delete", accountPermission);
	}

	@Override
	public void deleteAccountAssignedTeamTeamKeyRole(
			String agentName, String accountKey, String teamKey,
			String[] teamRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String teamRoleKey : teamRoleKeys) {
			_teamAccountRoleService.deleteTeamAccountRole(
				teamKey, accountKey, teamRoleKey);
		}
	}

	public void deleteAccountContactByEmailAddresContactEmailAddressRole(
			String agentName, String accountKey, String contactEmailAddress,
			String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_deleteAccountContactRole(
			accountKey,
			_oktaContactIdentityProvider.getContactByEmailAddress(
				contactEmailAddress),
			contactRoleKeys);
	}

	@Override
	public void deleteAccountContactByOktaRole(
			String agentName, String accountKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_deleteAccountContactRole(
			accountKey,
			_oktaContactIdentityProvider.getContactByProviderId(oktaId),
			contactRoleKeys);
	}

	@Override
	public void deleteAccountContactByUuidContactUuidRole(
			String agentName, String accountKey, String contactUuid,
			String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_deleteAccountContactRole(
			accountKey,
			_webContactIdentityProvider.getContactByProviderId(contactUuid),
			contactRoleKeys);
	}

	public void deleteAccountCustomerContactByEmailAddres(
			String agentName, String accountKey, String[] contactEmailAddresses)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String contactEmailAddress : contactEmailAddresses) {
			_deleteAccountContact(
				accountKey,
				_oktaContactIdentityProvider.getContactByEmailAddress(
					contactEmailAddress),
				ContactRole.Type.ACCOUNT_CUSTOMER.toString());
		}
	}

	@Override
	public void deleteAccountCustomerContactByOkta(
			String agentName, String accountKey, String[] oktaIds)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String oktaId : oktaIds) {
			_deleteAccountContact(
				accountKey,
				_oktaContactIdentityProvider.getContactByProviderId(oktaId),
				ContactRole.Type.ACCOUNT_CUSTOMER.toString());
		}
	}

	@Override
	public void deleteAccountCustomerContactByUuid(
			String agentName, String accountKey, String[] contactUuids)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String contactUuid : contactUuids) {
			_deleteAccountContact(
				accountKey,
				_webContactIdentityProvider.getContactByProviderId(contactUuid),
				ContactRole.Type.ACCOUNT_CUSTOMER.toString());
		}
	}

	public void deleteAccountWorkerContactByEmailAddres(
			String agentName, String accountKey, String[] contactEmailAddresses)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String contactEmailAddress : contactEmailAddresses) {
			_deleteAccountContact(
				accountKey,
				_oktaContactIdentityProvider.getContactByEmailAddress(
					contactEmailAddress),
				ContactRole.Type.ACCOUNT_WORKER.toString());
		}
	}

	@Override
	public void deleteAccountWorkerContactByOkta(
			String agentName, String accountKey, String[] oktaIds)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String oktaId : oktaIds) {
			_deleteAccountContact(
				accountKey,
				_oktaContactIdentityProvider.getContactByProviderId(oktaId),
				ContactRole.Type.ACCOUNT_WORKER.toString());
		}
	}

	@Override
	public void deleteAccountWorkerContactByUuid(
			String agentName, String accountKey, String[] contactUuids)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String contactUuid : contactUuids) {
			_deleteAccountContact(
				accountKey,
				_webContactIdentityProvider.getContactByProviderId(contactUuid),
				ContactRole.Type.ACCOUNT_WORKER.toString());
		}
	}

	@Override
	public Account getAccount(String accountKey) throws Exception {
		return AccountUtil.toAccount(_accountService.getAccount(accountKey));
	}

	@Override
	public Page<Account> getAccountByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_accountService.getAccounts(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(account)),
			pagination,
			_accountService.getAccountsCount(domain, entityName, entityId));
	}

	@Override
	public Page<Account> getAccountChildAccountsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Account curAccount =
			_accountLocalService.getAccount(accountKey);

		return Page.of(
			transform(
				_accountService.getAccounts(
					curAccount.getAccountId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(account)),
			pagination,
			_accountService.getAccountsCount(curAccount.getAccountId()));
	}

	@Override
	public Page<Account> getAccountsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.Account.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> AccountUtil.toAccount(
				_accountLocalService.getAccount(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Page<Account> getContactByOktaAccountsPage(
			String oktaId, Pagination pagination)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByOktaId(oktaId);

		return Page.of(
			transform(
				_accountService.getContactAccounts(
					contact.getContactId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(account)),
			pagination,
			_accountService.getContactAccountsCount(contact.getContactId()));
	}

	@Override
	public Page<Account> getContactByUuidContactUuidAccountsPage(
			String contactUuid, Pagination pagination)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByUuid(contactUuid);

		return Page.of(
			transform(
				_accountService.getContactAccounts(
					contact.getContactId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(account)),
			pagination,
			_accountService.getContactAccountsCount(contact.getContactId()));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Page<Account> getTeamTeamKeyAssignedAccountsPage(
			String teamKey, Pagination pagination)
		throws Exception {

		Team team = _teamLocalService.getTeam(teamKey);

		return Page.of(
			transform(
				_accountService.getTeamAccounts(
					team.getTeamId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(account)),
			pagination, _accountService.getTeamAccountsCount(team.getTeamId()));
	}

	@Override
	public Account postAccount(String agentName, Account account)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		return _postAccount(agentName, null, account);
	}

	@Override
	public Account postAccountChildAccount(
			String agentName, String accountKey, Account account)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		return _postAccount(agentName, accountKey, account);
	}

	@Override
	public Account putAccount(
			String agentName, String accountKey, Account account)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		com.liferay.osb.koroneiki.taproot.model.Account curAccount =
			_accountLocalService.getAccount(accountKey);

		String code = GetterUtil.getString(
			account.getCode(), curAccount.getCode());
		String description = GetterUtil.getString(
			account.getDescription(), curAccount.getDescription());
		String notes = GetterUtil.getString(
			account.getNotes(), curAccount.getNotes());
		long logoId = GetterUtil.getLong(
			account.getLogoId(), curAccount.getLogoId());
		String contactEmailAddress = GetterUtil.getString(
			account.getContactEmailAddress(),
			curAccount.getContactEmailAddress());
		String profileEmailAddress = GetterUtil.getString(
			account.getProfileEmailAddress(),
			curAccount.getProfileEmailAddress());
		String phoneNumber = GetterUtil.getString(
			account.getPhoneNumber(), curAccount.getPhoneNumber());
		String faxNumber = GetterUtil.getString(
			account.getFaxNumber(), curAccount.getFaxNumber());
		String website = GetterUtil.getString(
			account.getWebsite(), curAccount.getWebsite());

		String tier = curAccount.getTier();

		Account.Tier accountTier = account.getTier();

		if (accountTier != null) {
			tier = accountTier.toString();
		}

		String soldBy = GetterUtil.getString(
			account.getSoldBy(), curAccount.getSoldBy());
		boolean internal = GetterUtil.getBoolean(
			account.getInternal(), curAccount.getInternal());

		int status = curAccount.getStatus();

		Account.Status accountStatus = account.getStatus();

		if (accountStatus != null) {
			status = WorkflowConstants.getLabelStatus(accountStatus.toString());
		}

		return AccountUtil.toAccount(
			_accountService.updateAccount(
				accountKey, curAccount.getParentAccountId(), account.getName(),
				code, description, notes, logoId, contactEmailAddress,
				profileEmailAddress, phoneNumber, faxNumber, website, tier,
				soldBy, internal, status));
	}

	@Override
	public void putAccountAccountPermission(
			String agentName, String accountKey,
			AccountPermission accountPermission)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_updateAccountPermission(accountKey, "add", accountPermission);
	}

	@Override
	public void putAccountAssignedTeamTeamKeyRole(
			String agentName, String accountKey, String teamKey,
			String[] teamRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		for (String teamRoleKey : teamRoleKeys) {
			_teamAccountRoleService.addTeamAccountRole(
				teamKey, accountKey, teamRoleKey);
		}
	}

	@Override
	public void putAccountContactByEmailAddresContactEmailAddressRole(
			String agentName, String accountKey, String contactEmailAddress,
			String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_putAccountContactRole(
			accountKey,
			_oktaContactIdentityProvider.getContactByEmailAddress(
				contactEmailAddress),
			contactRoleKeys);
	}

	@Override
	public void putAccountContactByOktaRole(
			String agentName, String accountKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_putAccountContactRole(
			accountKey,
			_oktaContactIdentityProvider.getContactByProviderId(oktaId),
			contactRoleKeys);
	}

	@Override
	public void putAccountContactByUuidContactUuidRole(
			String agentName, String accountKey, String contactUuid,
			String[] contactRoleKeys)
		throws Exception {

		ServiceContextUtil.setAgentName(agentName);

		_putAccountContactRole(
			accountKey,
			_webContactIdentityProvider.getContactByProviderId(contactUuid),
			contactRoleKeys);
	}

	private void _deleteAccountContact(
			String accountKey,
			com.liferay.osb.koroneiki.taproot.model.Contact contact,
			String contactRoleType)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		_contactAccountRoleService.deleteContactAccountRoles(
			contact.getContactId(), account.getAccountId(), contactRoleType);
	}

	private void _deleteAccountContactRole(
			String accountKey,
			com.liferay.osb.koroneiki.taproot.model.Contact contact,
			String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		for (String contactRoleKey : contactRoleKeys) {
			com.liferay.osb.koroneiki.taproot.model.ContactRole contactRole =
				_contactRoleLocalService.getContactRole(contactRoleKey);

			_contactAccountRoleService.deleteContactAccountRole(
				contact.getContactId(), account.getAccountId(),
				contactRole.getContactRoleId());
		}
	}

	private String _getContactRoleKey(String agentName, ContactRole contactRole)
		throws Exception {

		if (Validator.isNotNull(contactRole.getKey())) {
			return contactRole.getKey();
		}

		ContactRole.Type contactRoleType = contactRole.getType();

		com.liferay.osb.koroneiki.taproot.model.ContactRole curContactRole =
			_contactRoleLocalService.fetchContactRole(
				contactRole.getName(), contactRoleType.toString());

		if (curContactRole != null) {
			return curContactRole.getContactRoleKey();
		}

		contactRole = _contactRoleResource.postContactRole(
			agentName, contactRole);

		return contactRole.getKey();
	}

	private Account _postAccount(
			String agentName, String parentAccountKey, Account account)
		throws Exception {

		long parentAccountId = 0;

		if (Validator.isNotNull(parentAccountKey)) {
			com.liferay.osb.koroneiki.taproot.model.Account parentAccount =
				_accountLocalService.getAccount(parentAccountKey);

			parentAccountId = parentAccount.getAccountId();
		}

		String tier = StringPool.BLANK;

		Account.Tier accountTier = account.getTier();

		if (accountTier != null) {
			tier = accountTier.toString();
		}

		int status = WorkflowConstants.STATUS_APPROVED;

		Account.Status accountStatus = account.getStatus();

		if (accountStatus != null) {
			status = WorkflowConstants.getLabelStatus(accountStatus.toString());
		}

		Account curAccount = AccountUtil.toAccount(
			_accountService.addAccount(
				parentAccountId, account.getName(), account.getCode(),
				account.getDescription(), account.getNotes(),
				GetterUtil.getLong(account.getLogoId()),
				account.getContactEmailAddress(),
				account.getProfileEmailAddress(), account.getPhoneNumber(),
				account.getFaxNumber(), account.getWebsite(), tier,
				account.getSoldBy(),
				GetterUtil.getBoolean(account.getInternal()), status));

		if (!ArrayUtil.isEmpty(account.getContacts())) {
			for (Contact contact : account.getContacts()) {
				String[] contactRoleKeys = new String[0];

				if (!ArrayUtil.isEmpty(contact.getContactRoles())) {
					for (ContactRole contactRole : contact.getContactRoles()) {
						contactRoleKeys = ArrayUtil.append(
							contactRoleKeys,
							_getContactRoleKey(agentName, contactRole));
					}
				}

				if (Validator.isNotNull(contact.getOktaId())) {
					putAccountContactByOktaRole(
						agentName, curAccount.getKey(), contact.getOktaId(),
						contactRoleKeys);
				}
				else if (Validator.isNotNull(contact.getUuid())) {
					putAccountContactByUuidContactUuidRole(
						agentName, curAccount.getKey(), contact.getUuid(),
						contactRoleKeys);
				}
				else {
					_postContact(agentName, contact);

					putAccountContactByEmailAddresContactEmailAddressRole(
						agentName, curAccount.getKey(),
						contact.getEmailAddress(), contactRoleKeys);
				}
			}
		}

		if (!ArrayUtil.isEmpty(account.getExternalLinks())) {
			for (ExternalLink externalLink : account.getExternalLinks()) {
				_externalLinkResource.postAccountAccountKeyExternalLink(
					agentName, curAccount.getKey(), externalLink);
			}
		}

		if (!ArrayUtil.isEmpty(account.getPostalAddresses())) {
			for (PostalAddress postalAddress : account.getPostalAddresses()) {
				_postalAddressResource.postAccountAccountKeyPostalAddress(
					agentName, curAccount.getKey(), postalAddress);
			}
		}

		if (!ArrayUtil.isEmpty(account.getProductPurchases())) {
			for (ProductPurchase productPurchase :
					account.getProductPurchases()) {

				_productPurchaseResource.postAccountAccountKeyProductPurchase(
					agentName, curAccount.getKey(), productPurchase);
			}
		}

		return curAccount;
	}

	private void _postContact(String agentName, Contact contact)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_oktaContactIdentityProvider.fetchContactByEmailAddress(
				contact.getEmailAddress());

		if (curContact == null) {
			_contactResource.postContact(agentName, contact);
		}
	}

	private void _putAccountContactRole(
			String accountKey,
			com.liferay.osb.koroneiki.taproot.model.Contact contact,
			String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		for (String contactRoleKey : contactRoleKeys) {
			com.liferay.osb.koroneiki.taproot.model.ContactRole contactRole =
				_contactRoleLocalService.getContactRole(contactRoleKey);

			_contactAccountRoleService.addContactAccountRole(
				contact.getContactId(), account.getAccountId(),
				contactRole.getContactRoleId());
		}
	}

	private void _updateAccountPermission(
			String accountKey, String operation,
			AccountPermission accountPermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			PermissionThreadLocal.getPermissionChecker(), account,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(accountPermission.getAssignContact())) {
			actionIds.add(TaprootActionKeys.ASSIGN_CONTACT);
		}

		if (GetterUtil.getBoolean(accountPermission.getAssignTeam())) {
			actionIds.add(TaprootActionKeys.ASSIGN_TEAM);
		}

		if (GetterUtil.getBoolean(accountPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(accountPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(accountPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(accountPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.taproot.model.Account.class.getName(),
			account.getAccountId(), accountPermission.getRoleNames(),
			actionIds);
	}

	private static final EntityModel _entityModel = new AccountEntityModel();

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.AccountPermission
		_accountPermission;

	@Reference
	private AccountService _accountService;

	@Reference
	private ContactAccountRoleService _contactAccountRoleService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactResource _contactResource;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactRoleResource _contactRoleResource;

	@Reference
	private ContactService _contactService;

	@Reference
	private ExternalLinkResource _externalLinkResource;

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _oktaContactIdentityProvider;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private PostalAddressResource _postalAddressResource;

	@Reference
	private ProductPurchaseResource _productPurchaseResource;

	@Reference
	private TeamAccountRoleService _teamAccountRoleService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference(target = "(provider=web)")
	private ContactIdentityProvider _webContactIdentityProvider;

}