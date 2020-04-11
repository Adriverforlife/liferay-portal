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

package com.liferay.osb.koroneiki.phytohormone.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewEntitlementDefinitionsManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewEntitlementDefinitionsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest,
		SearchContainer searchContainer) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer);
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	@Override
	public CreationMenu getCreationMenu() {
		String tabs1 = ParamUtil.getString(
			liferayPortletRequest, "tabs1", "account");

		return new CreationMenu() {
			{
				if (tabs1.equals("account")) {
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								liferayPortletResponse.createRenderURL(),
								"mvcRenderCommandName",
								"/entitlement_definitions_admin/edit_" +
									"entitlement_definition",
								"redirect", currentURLObj.toString(),
								"classNameId",
								PortalUtil.getClassNameId(Account.class));
							dropdownItem.setLabel(
								LanguageUtil.get(
									request, "new-entitlement-definition"));
						});
				}
				else {
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								liferayPortletResponse.createRenderURL(),
								"mvcRenderCommandName",
								"/entitlement_definitions_admin/edit_" +
									"entitlement_definition",
								"redirect", currentURLObj.toString(),
								"classNameId",
								PortalUtil.getClassNameId(Contact.class));
							dropdownItem.setLabel(
								LanguageUtil.get(
									request, "new-entitlement-definition"));
						});
				}
			}
		};
	}

	@Override
	public List<DropdownItem> getFilterDropdownItems() {
		return null;
	}

	public List<NavigationItem> getNavigationItems() {
		String tabs1 = ParamUtil.getString(
			liferayPortletRequest, "tabs1", "account");

		return NavigationItemList.of(
			() -> {
				NavigationItem navigationItem = new NavigationItem();

				navigationItem.setActive(tabs1.equals("account"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"account");
				navigationItem.setLabel(LanguageUtil.get(request, "account"));

				return navigationItem;
			},
			() -> {
				NavigationItem navigationItem = new NavigationItem();

				navigationItem.setActive(tabs1.equals("contact"));
				navigationItem.setHref(
					liferayPortletResponse.createRenderURL(), "tabs1",
					"contact");
				navigationItem.setLabel(LanguageUtil.get(request, "contact"));

				return navigationItem;
			});
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "entitlementDefinitionSearch";
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

}