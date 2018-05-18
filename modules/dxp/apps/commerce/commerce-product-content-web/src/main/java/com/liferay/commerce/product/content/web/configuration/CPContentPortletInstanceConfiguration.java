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

package com.liferay.commerce.product.content.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Marco Leo
 */
@ExtendedObjectClassDefinition(
	category = "commerce",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.commerce.product.content.web.configuration.CPContentPortletInstanceConfiguration",
	localization = "content/Language",
	name = "commerce-product-content-portlet-instance-configuration-name"
)
public interface CPContentPortletInstanceConfiguration {

	@Meta.AD(deflt = "", name = "display-style", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "", name = "cp-type", required = false)
	public String cpType();

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
	public String displayStyleGroupId();

}