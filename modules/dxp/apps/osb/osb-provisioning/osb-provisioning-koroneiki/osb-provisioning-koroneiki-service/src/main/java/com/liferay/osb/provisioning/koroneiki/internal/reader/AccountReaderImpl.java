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

package com.liferay.osb.provisioning.koroneiki.internal.reader;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(immediate = true, service = AccountReader.class)
public class AccountReaderImpl implements AccountReader {

	public List<Account> getAncestorAccounts(Account account) throws Exception {
		List<Account> ancestorAccounts = new ArrayList<>();

		if (Validator.isNotNull(account.getParentAccountKey())) {
			Account parentAccount = _accountWebService.fetchAccount(
				account.getParentAccountKey());

			if (parentAccount != null) {
				ancestorAccounts.add(parentAccount);

				ancestorAccounts.addAll(getAncestorAccounts(parentAccount));
			}
		}

		return ancestorAccounts;
	}

	public int getDeveloperCount(Account account) {
		Contact[] contacts = account.getCustomerContacts();

		if (contacts == null) {
			return 0;
		}

		int developerCount = 0;

		for (Contact contact : contacts) {
			ContactRole[] contactRoles = contact.getContactRoles();

			if (contactRoles == null) {
				continue;
			}

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(ContactRoleConstants.NAME_SUPPORT_DEVELOPER)) {
					developerCount++;

					break;
				}
			}
		}

		return developerCount;
	}

	public Team getFirstLineSupportTeam(Account account) throws Exception {
		return _getTeam(account, TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);
	}

	public int getMaxDeveloperCount(Account account) {
		if (ArrayUtil.isEmpty(account.getProductPurchases())) {
			return 0;
		}

		ProductPurchase slaProductPurchase = getSLAProductPurchase(account);

		if (slaProductPurchase == null) {
			return 0;
		}

		int developerAddons = 0;
		int productionInstances = 0;

		for (ProductPurchase productPurchase : account.getProductPurchases()) {
			Product product = productPurchase.getProduct();

			String name = product.getName();

			if (name.equals(ProductConstants.NAME_DESIGNATED_CONTACT_ADD_ON)) {
				developerAddons += productPurchase.getQuantity();
			}
			else if (name.equals(
						ProductConstants.
							NAME_DXP_CLOUD_SUBSCRIPTION_HA_PRODUCTION)) {

				productionInstances += 2 * productPurchase.getQuantity();
			}
			else if (name.equals(ProductConstants.NAME_DXP_PRODUCTION) ||
					 name.equals(
						 ProductConstants.
							 NAME_DXP_CLOUD_SUBSCRIPTION_STD_PRODUCTION) ||
					 name.equals(
						 ProductConstants.NAME_DXP_CLOUD_INSTANCE_PRODUCTION)) {

				productionInstances += productPurchase.getQuantity();
			}
		}

		if (productionInstances <= 0) {
			return 0;
		}

		int maxDeveloperCount = 0;

		Product product = slaProductPurchase.getProduct();

		String name = product.getName();

		if (name.equals(ProductConstants.NAME_GOLD)) {
			if (productionInstances <= 4) {
				maxDeveloperCount = 2;
			}
			else if (productionInstances <= 8) {
				maxDeveloperCount = 4;
			}
			else if (productionInstances <= 12) {
				maxDeveloperCount = 6;
			}
			else if (productionInstances <= 16) {
				maxDeveloperCount = 8;
			}
			else if (productionInstances <= 20) {
				maxDeveloperCount = 10;
			}
			else {
				maxDeveloperCount = 12;
			}
		}
		else if (name.equals(ProductConstants.NAME_PLATINUM)) {
			if (productionInstances <= 4) {
				maxDeveloperCount = 3;
			}
			else if (productionInstances <= 8) {
				maxDeveloperCount = 6;
			}
			else if (productionInstances <= 12) {
				maxDeveloperCount = 9;
			}
			else if (productionInstances <= 16) {
				maxDeveloperCount = 12;
			}
			else if (productionInstances <= 20) {
				maxDeveloperCount = 15;
			}
			else {
				maxDeveloperCount = 18;
			}
		}

		maxDeveloperCount += developerAddons;

		return maxDeveloperCount;
	}

	public Team getPartnerTeam(Account account) throws Exception {
		return _getTeam(account, TeamRoleConstants.NAME_PARTNER);
	}

	public ProductPurchase getSLAProductPurchase(Account account) {
		if (ArrayUtil.isEmpty(account.getProductPurchases())) {
			return null;
		}

		ProductPurchase slaProductPurchase = null;

		for (ProductPurchase productPurchase : account.getProductPurchases()) {
			Product product = productPurchase.getProduct();

			if (!ArrayUtil.contains(
					ProductConstants.SUBSCRIPTION_NAMES, product.getName())) {

				continue;
			}

			if (_isHigherSLA(slaProductPurchase, productPurchase)) {
				slaProductPurchase = productPurchase;
			}
		}

		return slaProductPurchase;
	}

	private int _getSLARank(Product product) {
		String name = product.getName();

		if (name.equals(ProductConstants.NAME_GOLD)) {
			return 3;
		}
		else if (name.equals(ProductConstants.NAME_LIMITED)) {
			return 1;
		}
		else if (name.equals(ProductConstants.NAME_PLATINUM)) {
			return 4;
		}
		else if (name.equals(ProductConstants.NAME_SILVER)) {
			return 2;
		}

		return 0;
	}

	private Team _getTeam(Account account, String teamRoleName)
		throws Exception {

		Team[] teams = account.getAssignedTeams();

		if (teams != null) {
			for (Team team : teams) {
				TeamRole[] teamRoles = team.getTeamRoles();

				if (teamRoles == null) {
					List<TeamRole> teamRolesList =
						_teamRoleWebService.getTeamRoles(
							account.getKey(), team.getKey(), 1, 1000);

					teamRoles = teamRolesList.toArray(new TeamRole[0]);
				}

				for (TeamRole teamRole : teamRoles) {
					if (teamRoleName.equals(teamRole.getName())) {
						return team;
					}
				}
			}
		}

		return null;
	}

	private boolean _isHigherSLA(
		ProductPurchase curProductPurchase, ProductPurchase productPurchase) {

		if (curProductPurchase == null) {
			return true;
		}

		int curSLARank = _getSLARank(curProductPurchase.getProduct());
		int slaRank = _getSLARank(productPurchase.getProduct());

		if (slaRank > curSLARank) {
			return true;
		}

		if (slaRank < curSLARank) {
			return false;
		}

		if (productPurchase.getPerpetual() &&
			!curProductPurchase.getPerpetual()) {

			return true;
		}

		if (!productPurchase.getPerpetual() &&
			curProductPurchase.getPerpetual()) {

			return false;
		}

		Date curEndDate = curProductPurchase.getEndDate();
		Date endDate = productPurchase.getEndDate();

		if (endDate.after(curEndDate)) {
			return true;
		}

		return false;
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

}