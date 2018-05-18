<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceEntry commercePriceEntry = (CommercePriceEntry)request.getAttribute(CommercePriceListWebKeys.COMMERCE_PRICE_ENTRY);
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="custom-fields"
/>

<aui:model-context bean="<%= commercePriceEntry %>" model="<%= CommercePriceEntry.class %>" />

<liferay-expando:custom-attribute-list
	className="<%= CommercePriceEntry.class.getName() %>"
	classPK="<%= (commercePriceEntry != null) ? commercePriceEntry.getCommercePriceEntryId() : 0 %>"
	editable="<%= true %>"
	label="<%= true %>"
/>