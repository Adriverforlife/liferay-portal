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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPInstancePricingInfoDisplayContext
	extends CPInstanceDisplayContext {

	public CPInstancePricingInfoDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CommercePriceFormatter commercePriceFormatter,
			CPDefinitionOptionRelService cpDefinitionOptionRelService,
			CPInstanceService cpInstanceService,
			CPInstanceHelper cpInstanceHelper,
			CommerceCurrencyService commerceCurrencyService)
		throws PortalException {

		super(
			actionHelper, httpServletRequest, commercePriceFormatter,
			cpDefinitionOptionRelService, cpInstanceService, cpInstanceHelper);

		_commerceCurrencyService = commerceCurrencyService;
	}

	public String format(BigDecimal price) {
		return commercePriceFormatter.format(price);
	}

	public String getCommerceCurrencyCode() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.fetchPrimaryCommerceCurrency(
				themeDisplay.getScopeGroupId());

		if (commerceCurrency != null) {
			return commerceCurrency.getCode();
		}

		return StringPool.BLANK;
	}

	private final CommerceCurrencyService _commerceCurrencyService;

}