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

package com.liferay.commerce.machine.learning.internal.forecast.data.integration.process.type;

import com.liferay.commerce.data.integration.process.type.ProcessType;
import com.liferay.commerce.machine.learning.internal.data.integration.BaseCommerceMLProcessType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.data.integration.process.type.key=" + AssetCategoryCommerceMLForecastProcessType.KEY,
		"commerce.data.integration.process.type.order=100"
	},
	service = ProcessType.class
)
public class AssetCategoryCommerceMLForecastProcessType
	extends BaseCommerceMLProcessType {

	public static final String KEY = "asset-category-commerce-ml-forecast";

	@Override
	public String getKey() {
		return KEY;
	}

}