<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CommerceProductOption commerceProductOption = (CommerceProductOption)request.getAttribute(CommerceProductWebKeys.COMMERCE_PRODUCT_OPTION);
List<DDMFormFieldType> ddmFormFieldTypes = commerceProductOptionsDisplayContext.getDDMFormFieldTypes();
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="product-option-base-information" />

<aui:model-context bean="<%= commerceProductOption %>" model="<%= CommerceProductOption.class %>" />

<aui:fieldset>
	<aui:input label="name" name="name" />
	<aui:input label="description" name="description" />

	<aui:select
		label="product-option-field-type"
		name="ddmFormFieldTypeName"
		showEmptyOption="<%= true %>"
	>

		<%
		for (DDMFormFieldType ddmFormFieldType : ddmFormFieldTypes) {
		%>

			<aui:option
				label="<%= ddmFormFieldType.getName() %>"
				value="<%= ddmFormFieldType.getName() %>"
				selected="<%= (commerceProductOption != null) && commerceProductOption.getDDMFormFieldTypeName().equals(ddmFormFieldType.getName()) %>"
			/>

		<%
		}
		%>

	</aui:select>
</aui:fieldset>