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

package com.liferay.commerce.product.definitions.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.commerce.product.constants.CommerceProductPortletKeys;
import com.liferay.commerce.product.exception.NoSuchProductDefinitionException;
import com.liferay.commerce.product.model.CommerceProductDefinition;
import com.liferay.commerce.product.service.CommerceProductDefinitionService;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceProductPortletKeys.COMMERCE_PRODUCT_DEFINITIONS,
		"mvc.command.name=editProductDefinition"
	},
	service = MVCActionCommand.class
)
public class EditCommerceProductDefinitionMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceProductDefinition(ActionRequest actionRequest)
		throws Exception {

		long commerceProductDefinitionId = ParamUtil.getLong(
			actionRequest, "commerceProductDefinitionId");

		_commerceProductDefinitionService.deleteCommerceProductDefinition(
			commerceProductDefinitionId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		CommerceProductDefinition commerceProductDefinition = null;

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		int workflowAction = ParamUtil.getInteger(
			actionRequest, "workflowAction",
			WorkflowConstants.ACTION_SAVE_DRAFT);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommerceProductDefinition(actionRequest);
			}
			else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.UPDATE)) {

				commerceProductDefinition = updateCommerceProductDefinition(
					actionRequest);
			}

			if ((commerceProductDefinition != null) &&
				(workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT)) {

				redirect = getSaveAndContinueRedirect(
					actionRequest, commerceProductDefinition, redirect);

				sendRedirect(actionRequest, actionResponse, redirect);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchProductDefinitionException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (e instanceof AssetCategoryException ||
					 e instanceof AssetTagException) {

				SessionErrors.add(actionRequest, e.getClass(), e);
			}
			else {
				throw e;
			}
		}
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest,
			CommerceProductDefinition commerceProductDefinition,
			String redirect)
		throws Exception {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			actionRequest, themeDisplay.getScopeGroup(),
			CommerceProductDefinition.class.getName(),
			PortletProvider.Action.EDIT);

		portletURL.setParameter(
			"mvcRenderCommandName", "editProductDefinition");
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter(
			"commerceProductDefinitionId",
			String.valueOf(commerceProductDefinition.
				getCommerceProductDefinitionId()));

		return portletURL.toString();
	}

	protected CommerceProductDefinition updateCommerceProductDefinition(
			ActionRequest actionRequest)
		throws Exception {

		long commerceProductDefinitionId = ParamUtil.getLong(
			actionRequest, "commerceProductDefinitionId");

		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "titleMapAsXML");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(
				actionRequest, "descriptionMapAsXML");
		String productTypeName = ParamUtil.getString(
			actionRequest, "productTypeName");
		String baseSKU = ParamUtil.getString(actionRequest, "baseSKU");

		int displayDateMonth = ParamUtil.getInteger(
			actionRequest, "displayDateMonth");
		int displayDateDay = ParamUtil.getInteger(
			actionRequest, "displayDateDay");
		int displayDateYear = ParamUtil.getInteger(
			actionRequest, "displayDateYear");
		int displayDateHour = ParamUtil.getInteger(
			actionRequest, "displayDateHour");
		int displayDateMinute = ParamUtil.getInteger(
			actionRequest, "displayDateMinute");
		int displayDateAmPm = ParamUtil.getInteger(
			actionRequest, "displayDateAmPm");

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		int expirationDateHour = ParamUtil.getInteger(
			actionRequest, "expirationDateHour");
		int expirationDateMinute = ParamUtil.getInteger(
			actionRequest, "expirationDateMinute");
		int expirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "expirationDateAmPm");

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		boolean neverExpire = ParamUtil.getBoolean(
			actionRequest, "neverExpire");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceProductDefinition.class.getName(), actionRequest);

		CommerceProductDefinition commerceProductDefinition = null;

		if (commerceProductDefinitionId <= 0) {

			// Add commerce product definition

			commerceProductDefinition =
				_commerceProductDefinitionService.addCommerceProductDefinition(
					baseSKU, titleMap, descriptionMap, productTypeName, null,
					displayDateMonth, displayDateDay, displayDateYear,
					displayDateHour, displayDateMinute, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, serviceContext);
		}
		else {

			// Update commerce product definition

			commerceProductDefinition =
				_commerceProductDefinitionService.
					updateCommerceProductDefinition(
						commerceProductDefinitionId, baseSKU, titleMap,
						descriptionMap, productTypeName, null, displayDateMonth,
						displayDateDay, displayDateYear, displayDateHour,
						displayDateMinute, expirationDateMonth,
						expirationDateDay, expirationDateYear,
						expirationDateHour, expirationDateMinute, neverExpire,
						serviceContext);
		}

		return commerceProductDefinition;
	}

	@Reference
	private CommerceProductDefinitionService _commerceProductDefinitionService;

}