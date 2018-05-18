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

package com.liferay.commerce.product.catalog.rule.web.internal.display.context.util;

import com.liferay.portal.kernel.display.context.util.BaseRequestHelper;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CPCatalogRuleRequestHelper extends BaseRequestHelper {

	public CPCatalogRuleRequestHelper(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

	public HttpServletResponse getResponse() {
		return PortalUtil.getHttpServletResponse(getLiferayPortletResponse());
	}

}