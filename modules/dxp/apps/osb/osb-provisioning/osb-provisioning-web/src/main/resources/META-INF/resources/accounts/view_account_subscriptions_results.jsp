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
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

String state = ParamUtil.getString(request, "state");

PortletURL searchURL = viewAccountDisplayContext.getPortletURL();
%>

<aui:form action="<%= searchURL.toString() %>" method="get" name="searchFm">
	<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />

	<aui:input label="" name="keywords" placeholder="search" />
</aui:form>

<liferay-ui:search-container
	searchContainer="<%= viewAccountDisplayContext.getProductPurchaseViewsSearchContainer(state) %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseViewDisplay"
		escapedModel="<%= true %>"
		modelVar="productPurchaseViewDisplay"
	>
		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcRenderCommandName" value="/accounts/view_subscription" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountKey" value="<%= productPurchaseViewDisplay.getAccountKey() %>" />
			<portlet:param name="productKey" value="<%= productPurchaseViewDisplay.getProductKey() %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="product"
		>
			<%= productPurchaseViewDisplay.getName() %>

			<div class="secondary-information">
				<%= productPurchaseViewDisplay.getSizingWithLabel() %>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="support-life"
		>
			<%= productPurchaseViewDisplay.getSupportLife() %>

			<c:if test="<%= productPurchaseViewDisplay.isFuture() && Validator.isNotNull(productPurchaseViewDisplay.getNextTermStartDate()) %>">
				<div class="secondary-information">
					<liferay-ui:message key="next-term-starts" />: <%= productPurchaseViewDisplay.getNextTermStartDate() %>
				</div>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="provisioned"
			value="<%= productPurchaseViewDisplay.getProvisionedCount() %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="purchased"
			value="<%= productPurchaseViewDisplay.getPurchasedCount() %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="status"
		>
			<span class="label <%= productPurchaseViewDisplay.getStatusStyle() %>"><%= productPurchaseViewDisplay.getStatus() %></span>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>