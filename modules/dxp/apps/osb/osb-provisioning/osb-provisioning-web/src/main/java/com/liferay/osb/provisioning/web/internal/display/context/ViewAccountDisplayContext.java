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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.text.Format;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewAccountDisplayContext {

	public ViewAccountDisplayContext() {
	}

	public void addPortletBreadcrumbEntries() throws Exception {
		PortletURL accountsPortletURL = renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest,
			LanguageUtil.get(httpServletRequest, "accounts"),
			accountsPortletURL.toString());

		List<Account> ancestorAccounts = accountReader.getAncestorAccounts(
			account);

		for (int i = ancestorAccounts.size() - 1; i >= 0; i--) {
			Account ancestorAccount = ancestorAccounts.get(i);

			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
			portletURL.setParameter("accountKey", ancestorAccount.getKey());

			PortalUtil.addPortletBreadcrumbEntry(
				httpServletRequest, ancestorAccount.getName(),
				portletURL.toString());
		}

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", account.getKey());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, account.getName(), portletURL.toString());
	}

	public String getAccountAttachmentURL(long accountAttachmentId)
		throws Exception {

		return accountEntryWebService.getAccountAttachmentURL(
			accountAttachmentId);
	}

	public AccountDisplay getAccountDisplay() {
		return accountDisplay;
	}

	public AccountEntry getAccountEntry() throws Exception {
		return accountEntryWebService.fetchAccountEntry(account.getKey());
	}

	public String getAddExternalLinkURL() {
		PortletURL addExternalLinkURL = renderResponse.createActionURL();

		addExternalLinkURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_external_link");
		addExternalLinkURL.setParameter("accountKey", account.getKey());

		return addExternalLinkURL.toString();
	}

	public String getAddPostalAddressURL() {
		PortletURL addPostalAddressURL = renderResponse.createActionURL();

		addPostalAddressURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_postal_address");
		addPostalAddressURL.setParameter("accountKey", account.getKey());

		return addPostalAddressURL.toString();
	}

	public String getAssignAccountContactRolesURL() {
		PortletURL assignAccountContactRolesURL =
			renderResponse.createActionURL();

		assignAccountContactRolesURL.setParameter(
			ActionRequest.ACTION_NAME, "/assign_account_contact_roles");
		assignAccountContactRolesURL.setParameter(
			"accountKey", account.getKey());

		return assignAccountContactRolesURL.toString();
	}

	public List<AuditEntryDisplay> getAuditEntryDisplays() throws Exception {
		return TransformUtil.transform(
			auditEntryWebService.getAccountAuditEntries(
				account.getKey(), 1, 1000),
			auditEntry -> new AuditEntryDisplay(
				httpServletRequest, auditEntry));
	}

	public String getClearResultsURL() {
		PortletURL portletURL = getPortletURL();

		return portletURL.toString();
	}

	public String getCurrentURL() {
		return currentURLObj.toString();
	}

	public String getDeleteExternalLinkURL(String externalLinkKey) {
		PortletURL deleteExternalLinkURL = renderResponse.createActionURL();

		deleteExternalLinkURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_external_link");
		deleteExternalLinkURL.setParameter(Constants.CMD, Constants.DELETE);
		deleteExternalLinkURL.setParameter("externalLinkKey", externalLinkKey);

		return deleteExternalLinkURL.toString();
	}

	public String getDeletePostalAddressURL(long postalAddressId) {
		PortletURL deletePostalAddressURL = renderResponse.createActionURL();

		deletePostalAddressURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_postal_address");
		deletePostalAddressURL.setParameter(Constants.CMD, Constants.DELETE);
		deletePostalAddressURL.setParameter(
			"postalAddressId", String.valueOf(postalAddressId));

		return deletePostalAddressURL.toString();
	}

	public String getDeleteTeamURL(String teamKey) {
		PortletURL deleteTeamURL = renderResponse.createActionURL();

		deleteTeamURL.setParameter(ActionRequest.ACTION_NAME, "/edit_team");
		deleteTeamURL.setParameter(Constants.CMD, Constants.DELETE);
		deleteTeamURL.setParameter("teamKey", teamKey);

		return deleteTeamURL.toString();
	}

	public String getEditAccountURL() {
		PortletURL editAccountURL = renderResponse.createActionURL();

		editAccountURL.setParameter(ActionRequest.ACTION_NAME, "/edit_account");
		editAccountURL.setParameter("accountKey", account.getKey());

		return editAccountURL.toString();
	}

	public String getEditExternalLinkURL(String externalLinkKey) {
		PortletURL editExternalLinkURL = renderResponse.createActionURL();

		editExternalLinkURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_external_link");
		editExternalLinkURL.setParameter("externalLinkKey", externalLinkKey);

		return editExternalLinkURL.toString();
	}

	public String getEditPostalAddressURL(long postalAddressId) {
		PortletURL editPostalAddressURL = renderResponse.createActionURL();

		editPostalAddressURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_postal_address");
		editPostalAddressURL.setParameter(
			"postalAddressId", String.valueOf(postalAddressId));

		return editPostalAddressURL.toString();
	}

	public String getEditTeamURL(String teamKey) {
		PortletURL editTeamURL = renderResponse.createActionURL();

		editTeamURL.setParameter(ActionRequest.ACTION_NAME, "/edit_account");
		editTeamURL.setParameter("teamKey", teamKey);

		return editTeamURL.toString();
	}

	public Map<String, Object> getPanelData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL addNoteURL = renderResponse.createActionURL();

		addNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		addNoteURL.setParameter("accountKey", account.getKey());

		data.put("addNoteURL", addNoteURL.toString());

		data.put(
			"externalLinks",
			TransformUtil.transform(
				externalLinkWebService.getExternalLinks(
					account.getKey(), 1, 1000),
				externalLink -> new ExternalLinkDisplay(
					httpServletRequest, externalLink)));

		data.put(
			"notes",
			TransformUtil.transform(
				noteWebService.getNotes(
					account.getKey(), StringPool.BLANK, StringPool.BLANK, 1,
					1000),
				note -> new NoteDisplay(
					httpServletRequest, note, _getUpdateNoteURL(note.getKey()),
					_getDeleteNoteURL(note.getKey()))));

		return data;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL;
	}

	public SearchContainer getProductPurchaseViewsSearchContainer(String state)
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-subscriptions-were-found");

		String keywords = ParamUtil.getString(renderRequest, "keywords");
		String[] productKeys = ParamUtil.getStringValues(
			renderRequest, "productKeys");
		String[] statuses = ParamUtil.getStringValues(
			renderRequest, "statuses");
		int startDateMonth = ParamUtil.getInteger(
			renderRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(renderRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			renderRequest, "startDateYear");
		int endDateMonth = ParamUtil.getInteger(renderRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(renderRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(renderRequest, "endDateYear");

		StringBundler sb = new StringBundler();

		sb.append("(accountKey eq '");
		sb.append(account.getKey());
		sb.append("')");

		if (state.equals("active")) {
			sb.append(" and (state eq 'active')");
		}
		else if (state.equals("inactive")) {
			sb.append(" and ((state eq 'inactive') or (status eq '600'))");
		}

		if (statuses.length > 0) {
			StringBundler statusSB = new StringBundler();

			statusSB.append(" and (");

			for (int i = 0; i < statuses.length; i++) {
				String status = statuses[i];

				if (status.equals("cancelled")) {
					if (!state.equals("inactive")) {
						statusSB.append("(status eq '600')");
					}
				}
				else {
					String sbString = sb.toString();

					if (sbString.contains(status)) {
						continue;
					}

					statusSB.append("(state eq '");
					statusSB.append(status);
					statusSB.append("')");
				}

				if (i < (statuses.length - 1)) {
					String nextStatus = statuses[i + 1];

					if (nextStatus.equals("cancelled") &&
						state.equals("inactive")) {

						continue;
					}

					statusSB.append(" or ");
				}
			}

			statusSB.append(")");

			if (statusSB.length() > 7) {
				sb.append(statusSB.toString());
			}
		}

		if (productKeys.length > 0) {
			for (int i = 0; i < productKeys.length; i++) {
				if (i == 0) {
					sb.append(" and (");
				}

				sb.append("(productKey eq '");
				sb.append(productKeys[i]);
				sb.append("')");

				if (i < (productKeys.length - 1)) {
					sb.append(" or ");
				}
			}

			sb.append(")");
		}

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, null);

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, null);

		if ((startDate != null) && (endDate != null)) {
			sb.append(" and ((supportLifeStartDate ge ");
			sb.append(dateFormat.format(startDate));
			sb.append(") and (supportLifeEndDate le ");
			sb.append(dateFormat.format(endDate));
			sb.append("))");
		}

		List<ProductPurchaseView> productPurchaseViews =
			productPurchaseViewWebService.getProductPurchaseViews(
				keywords, sb.toString(), searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart(), null);

		searchContainer.setResults(
			TransformUtil.transform(
				productPurchaseViews,
				productPurchaseView -> new ProductPurchaseViewDisplay(
					httpServletRequest, account, productPurchaseView)));

		int count =
			(int)productPurchaseViewWebService.getProductPurchaseViewsCount(
				keywords, sb.toString(), searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart(), null);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public SearchContainer getTeamsSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-teams-were-found");

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		String filterString = "accountKey eq '" + account.getKey() + "'";

		List<Team> teams = teamWebService.search(
			keywords, filterString, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				teams, team -> new TeamDisplay(httpServletRequest, team)));

		int count = (int)teamWebService.searchCount(keywords, filterString);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public String getUnassignAccountCustomerContactURL(String emailAddress) {
		PortletURL unassignAccountCustomerContactURL =
			renderResponse.createActionURL();

		unassignAccountCustomerContactURL.setParameter(
			ActionRequest.ACTION_NAME, "/unassign_account_customer_contact");
		unassignAccountCustomerContactURL.setParameter(
			"accountKey", account.getKey());
		unassignAccountCustomerContactURL.setParameter(
			"emailAddress", emailAddress);

		return unassignAccountCustomerContactURL.toString();
	}

	public String getUpdateAccountAttachmentURL() throws Exception {
		return accountEntryWebService.getUpdateAccountAttachmentURL();
	}

	public String getUpdateInstructionsURL() {
		PortletURL updateInstructionsURL = renderResponse.createActionURL();

		updateInstructionsURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_account_entry");
		updateInstructionsURL.setParameter(
			Constants.CMD, ProvisioningActionKeys.UPDATE_INSTRUCTIONS);
		updateInstructionsURL.setParameter("accountKey", account.getKey());

		return updateInstructionsURL.toString();
	}

	public String getUpdateLanguageIdURL() {
		PortletURL updateLanguageIdURL = renderResponse.createActionURL();

		updateLanguageIdURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_account_entry");
		updateLanguageIdURL.setParameter(
			Constants.CMD, ProvisioningActionKeys.UPDATE_LANGUAGE_ID);
		updateLanguageIdURL.setParameter("accountKey", account.getKey());

		return updateLanguageIdURL.toString();
	}

	public void init(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			AccountEntryWebService accountEntryWebService,
			AccountWebService accountWebService,
			AuditEntryWebService auditEntryWebService,
			ContactRoleWebService contactRoleWebService,
			ContactWebService contactWebService,
			ExternalLinkWebService externalLinkWebService,
			NoteWebService noteWebService,
			ProductPurchaseViewWebService productPurchaseViewWebService,
			TeamWebService teamWebService)
		throws Exception {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		this.httpServletRequest = httpServletRequest;
		this.accountReader = accountReader;
		this.accountEntryWebService = accountEntryWebService;
		this.accountWebService = accountWebService;
		this.auditEntryWebService = auditEntryWebService;
		this.contactRoleWebService = contactRoleWebService;
		this.contactWebService = contactWebService;
		this.externalLinkWebService = externalLinkWebService;
		this.noteWebService = noteWebService;
		this.productPurchaseViewWebService = productPurchaseViewWebService;
		this.teamWebService = teamWebService;

		doInit();
	}

	protected void doInit() throws Exception {
		account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);

		accountDisplay = new AccountDisplay(
			httpServletRequest, accountReader, account);

		currentURLObj = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);
	}

	protected Account account;
	protected AccountDisplay accountDisplay;
	protected AccountEntryWebService accountEntryWebService;
	protected AccountReader accountReader;
	protected AccountWebService accountWebService;
	protected AuditEntryWebService auditEntryWebService;
	protected ContactRoleWebService contactRoleWebService;
	protected ContactWebService contactWebService;
	protected PortletURL currentURLObj;
	protected final Format dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	protected ExternalLinkWebService externalLinkWebService;
	protected HttpServletRequest httpServletRequest;
	protected NoteWebService noteWebService;
	protected ProductPurchaseViewWebService productPurchaseViewWebService;
	protected RenderRequest renderRequest;
	protected RenderResponse renderResponse;
	protected TeamWebService teamWebService;

	private String _getDeleteNoteURL(String noteKey) {
		PortletURL deleteNoteURL = renderResponse.createActionURL();

		deleteNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		deleteNoteURL.setParameter(Constants.CMD, Constants.DELETE);
		deleteNoteURL.setParameter("noteKey", noteKey);

		return deleteNoteURL.toString();
	}

	private String _getUpdateNoteURL(String noteKey) {
		PortletURL updateNoteURL = renderResponse.createActionURL();

		updateNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		updateNoteURL.setParameter("noteKey", noteKey);

		return updateNoteURL.toString();
	}

}