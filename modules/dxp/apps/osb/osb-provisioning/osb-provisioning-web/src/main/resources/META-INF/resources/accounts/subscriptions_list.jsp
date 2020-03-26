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

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();

String state = ParamUtil.getString(request, "state");
%>

<portlet:renderURL var="subscriptionSearchURL">
	<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="accountKey" value="<%= accountDisplay.getAccountKey() %>" />
</portlet:renderURL>

<aui:form action="<%= subscriptionSearchURL.toString() %>" name="searchFm">
	<aui:input label="" name="search" placeholder="search" />
</aui:form>

<liferay-ui:search-container
	searchContainer="<%= viewAccountDisplayContext.getProductPurchaseViewSearchContainer(state) %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.provisioning.web.internal.display.context.ProductSubscriptionDisplay"
		escapedModel="<%= true %>"
		modelVar="productSubscriptionDisplay"
	>
		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcRenderCommandName" value="/accounts/view_product_subscription" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountKey" value="<%= productSubscriptionDisplay.getAccountKey() %>" />
			<portlet:param name="productKey" value="<%= productSubscriptionDisplay.getProductKey() %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="product"
		>
			<%= productSubscriptionDisplay.getName() %>

			<div class="secondary-information">
				<%= productSubscriptionDisplay.getSizing() %>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="support-life"
		>
			<%= productSubscriptionDisplay.getSupportLife() %>

			<c:if test="<%= productSubscriptionDisplay.isInactive() %>">
				<div class="secondary-information">
					<liferay-ui:message key="next-term-starts" />: <%= productSubscriptionDisplay.getNextTermStartDate() %>
				</div>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="provisioned"
			value="<%= productSubscriptionDisplay.getProvisionedCount() %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="purchased"
			value="<%= productSubscriptionDisplay.getPurchasedCount() %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="status"
		>
			<span class="label <%= productSubscriptionDisplay.getStatusStyle() %>"><%= productSubscriptionDisplay.getStatus() %></span>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>