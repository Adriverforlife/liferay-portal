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

package com.liferay.portal.workflow.kaleo.designer.web.internal.portlet;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.RolePermissionUtil;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.kernel.servlet.MultiSessionMessages;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.RoleNameComparator;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.designer.web.constants.KaleoDesignerPortletKeys;
import com.liferay.portal.workflow.kaleo.designer.web.internal.constants.KaleoDesignerWebKeys;
import com.liferay.portal.workflow.kaleo.designer.web.internal.portlet.display.context.KaleoDesignerDisplayContext;
import com.liferay.portal.workflow.kaleo.exception.DuplicateKaleoDefinitionNameException;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo Lundgren
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.autopropagated-parameters=availableFields",
		"com.liferay.portlet.autopropagated-parameters=availablePropertyModels",
		"com.liferay.portlet.autopropagated-parameters=kaleoProcessId",
		"com.liferay.portlet.autopropagated-parameters=openerWindowName",
		"com.liferay.portlet.autopropagated-parameters=portletResourceNamespace",
		"com.liferay.portlet.autopropagated-parameters=propertiesSaveCallback",
		"com.liferay.portlet.autopropagated-parameters=refreshOpenerOnClose",
		"com.liferay.portlet.autopropagated-parameters=saveCallback",
		"com.liferay.portlet.autopropagated-parameters=uiScope",
		"com.liferay.portlet.css-class-wrapper=kaleo-designer-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/designer/css/main.css",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Kaleo Designer Web",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/META-INF/resources/designer/",
		"javax.portlet.init-param.view-template=/designer/view.jsp",
		"javax.portlet.name=" + KaleoDesignerPortletKeys.KALEO_DESIGNER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,power-user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class KaleoDesignerPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (!SessionErrors.contains(
				renderRequest, DuplicateKaleoDefinitionNameException.class)) {

			try {
				setKaleoDefinitionVersionRenderRequestAttribute(
					renderRequest, renderResponse);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		boolean clearSessionMessage = ParamUtil.getBoolean(
			renderRequest, "clearSessionMessage");

		if (clearSessionMessage) {
			SessionMessages.clear(renderRequest);
		}

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("kaleoDefinitionVersions")) {
				serveKaleoDefinitionVersions(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("roles")) {
				serveRoles(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("scriptLanguages")) {
				serveScriptLanguages(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("users")) {
				serveUsers(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		if (!addProcessActionSuccessMessage) {
			return;
		}

		String redirect = actionRequest.getParameter("redirect");

		String portletId = _http.getParameter(redirect, "p_p_id", false);

		if (isRedirectToAnotherPortlet(portletId)) {
			String successMessage = ParamUtil.getString(
				actionRequest, "successMessage");

			MultiSessionMessages.add(
				actionRequest, portletId + "requestProcessed", successMessage);
		}
		else {
			super.addSuccessMessage(actionRequest, actionResponse);
		}
	}

	protected Integer[] getRoleTypesObj(int type) {
		if ((type == RoleConstants.TYPE_ORGANIZATION) ||
			(type == RoleConstants.TYPE_REGULAR) ||
			(type == RoleConstants.TYPE_SITE)) {

			return new Integer[] {type};
		}

		return new Integer[0];
	}

	@Override
	protected boolean isAlwaysSendRedirect() {
		return true;
	}

	protected boolean isRedirectToAnotherPortlet(String portletId) {
		if (Validator.isNull(portletId)) {
			return false;
		}

		if (portletId.contains(KaleoDesignerPortletKeys.KALEO_DESIGNER)) {
			return false;
		}

		return true;
	}

	protected void serveKaleoDefinitionVersions(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String name = ParamUtil.getString(resourceRequest, "name");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(name)) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			String position = ParamUtil.getString(resourceRequest, "position");

			KaleoDefinitionVersion kaleoDefinitionVersion = null;

			if (position.equals("latest")) {
				kaleoDefinitionVersion =
					_kaleoDefinitionVersionLocalService.
						getLatestKaleoDefinitionVersion(
							themeDisplay.getCompanyId(), name);
			}
			else {
				String draftVersion = ParamUtil.getString(
					resourceRequest, "draftVersion");

				KaleoDefinitionVersion[] kaleoDefinitionVersions =
					_kaleoDefinitionVersionLocalService.
						getKaleoDefinitionVersionsPrevAndNext(
							themeDisplay.getCompanyId(), name, draftVersion);

				if (position.equals("prev")) {
					kaleoDefinitionVersion = kaleoDefinitionVersions[0];
				}
				else if (position.equals("next")) {
					kaleoDefinitionVersion = kaleoDefinitionVersions[2];
				}

				if (kaleoDefinitionVersion == null) {
					kaleoDefinitionVersion = kaleoDefinitionVersions[1];
				}
			}

			jsonObject.put("content", kaleoDefinitionVersion.getContent());
			jsonObject.put("draftVersion", kaleoDefinitionVersion.getVersion());
			jsonObject.put("name", kaleoDefinitionVersion.getName());
			jsonObject.put(
				"title",
				LocalizationUtil.getLocalizationMap(
					kaleoDefinitionVersion.getTitle()));
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveRoles(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<Role> roles = new ArrayList<>();

		long[] roleIds = ParamUtil.getLongValues(resourceRequest, "roleIds");

		if (ArrayUtil.isNotEmpty(roleIds)) {
			roles = _roleLocalService.getRoles(roleIds);
		}
		else {
			String keywords = ParamUtil.getString(resourceRequest, "keywords");
			int type = ParamUtil.getInteger(resourceRequest, "type");

			roles = _roleLocalService.search(
				themeDisplay.getCompanyId(), keywords, getRoleTypesObj(type), 0,
				SearchContainer.DEFAULT_DELTA, new RoleNameComparator());
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Role role : roles) {
			if (!RolePermissionUtil.contains(
					themeDisplay.getPermissionChecker(), role.getRoleId(),
					ActionKeys.VIEW)) {

				continue;
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", role.getName());
			jsonObject.put("roleId", role.getRoleId());

			jsonArray.put(jsonObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	protected void serveScriptLanguages(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Set<String> supportedScriptLanguages =
			ScriptingUtil.getSupportedLanguages();

		Stream<String> supportedScriptLanguagesStream =
			supportedScriptLanguages.stream();

		// Skip JavaParser, will be fixed soon

		List<Object> sortedSupportedScriptLanguages =
			supportedScriptLanguagesStream.sorted(
			).collect(
				Collectors.toList()
			);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Object supportedScriptLanguage : sortedSupportedScriptLanguages) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String scriptLanguage = supportedScriptLanguage.toString();

			jsonObject.put(
				"scriptLanguage", StringUtil.toLowerCase(scriptLanguage));

			jsonArray.put(jsonObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	protected void serveUsers(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<User> users = new ArrayList<>();

		long[] userIds = ParamUtil.getLongValues(resourceRequest, "userIds");

		if (ArrayUtil.isNotEmpty(userIds)) {
			for (long userId : userIds) {
				User user = _userLocalService.fetchUser(userId);

				if (user != null) {
					users.add(user);
				}
			}
		}
		else {
			String keywords = ParamUtil.getString(resourceRequest, "keywords");

			users = _userLocalService.search(
				themeDisplay.getCompanyId(), keywords,
				WorkflowConstants.STATUS_APPROVED,
				new LinkedHashMap<String, Object>(), 0,
				SearchContainer.DEFAULT_DELTA, new UserFirstNameComparator());
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (User user : users) {
			if (!UserPermissionUtil.contains(
					themeDisplay.getPermissionChecker(), user.getUserId(),
					ActionKeys.VIEW)) {

				continue;
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("emailAddress", user.getEmailAddress());
			jsonObject.put("fullName", user.getFullName());
			jsonObject.put("screenName", user.getScreenName());
			jsonObject.put("userId", user.getUserId());

			jsonArray.put(jsonObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	@Reference(unbind = "-")
	protected void setKaleoDefinitionVersionLocalService(
		KaleoDefinitionVersionLocalService kaleoDefinitionVersionLocalService) {

		_kaleoDefinitionVersionLocalService =
			kaleoDefinitionVersionLocalService;
	}

	protected void setKaleoDefinitionVersionRenderRequestAttribute(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		KaleoDesignerDisplayContext kaleoDesignerDisplayContext =
			new KaleoDesignerDisplayContext(
				renderRequest, _kaleoDefinitionVersionLocalService,
				ResourceBundleLoaderUtil.
					getResourceBundleLoaderByBundleSymbolicName(
						"com.liferay.portal.workflow.kaleo.designer.web"),
				_userLocalService);

		renderRequest.setAttribute(
			KaleoDesignerWebKeys.KALEO_DESIGNER_DISPLAY_CONTEXT,
			kaleoDesignerDisplayContext);

		String name = ParamUtil.getString(renderRequest, "name");

		if (Validator.isNull(name)) {
			return;
		}

		KaleoDefinitionVersion kaleoDefinitionVersion = null;

		String draftVersion = ParamUtil.getString(
			renderRequest, "draftVersion");

		if (Validator.isNull(draftVersion)) {
			kaleoDefinitionVersion =
				_kaleoDefinitionVersionLocalService.
					fetchLatestKaleoDefinitionVersion(
						themeDisplay.getCompanyId(), name, null);
		}
		else {
			kaleoDefinitionVersion =
				_kaleoDefinitionVersionLocalService.getKaleoDefinitionVersion(
					themeDisplay.getCompanyId(), name, draftVersion);
		}

		renderRequest.setAttribute(
			KaleoDesignerWebKeys.KALEO_DRAFT_DEFINITION,
			kaleoDefinitionVersion);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoDesignerPortlet.class);

	@Reference
	private Http _http;

	@Reference
	private KaleoDefinitionVersionLocalService
		_kaleoDefinitionVersionLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}