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
CPDefinitionOptionRelDisplayContext cpDefinitionOptionRelDisplayContext = (CPDefinitionOptionRelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionOptionRelDisplayContext.getCPDefinition();

long cpDefinitionId = cpDefinitionOptionRelDisplayContext.getCPDefinitionId();

SearchContainer<CPDefinitionOptionRel> cpDefinitionOptionRelSearchContainer = cpDefinitionOptionRelDisplayContext.getSearchContainer();

PortletURL portletURL = cpDefinitionOptionRelDisplayContext.getPortletURL();

String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-product-definition-options");

portletURL.setParameter("toolbarItem", toolbarItem);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);

renderResponse.setTitle(cpDefinition.getTitle(languageId));

request.setAttribute("view.jsp-cpDefinition", cpDefinition);
request.setAttribute("view.jsp-cpType", cpDefinitionOptionRelDisplayContext.getCPType());
request.setAttribute("view.jsp-portletURL", portletURL);
request.setAttribute("view.jsp-showSearch", true);
request.setAttribute("view.jsp-toolbarItem", toolbarItem);
%>

<liferay-util:include page="/definition_navbar.jsp" servletContext="<%= application %>" />

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="cpDefinitionOptionRels"
>
	<liferay-frontend:management-bar-buttons>
		<c:if test="<%= cpDefinitionOptionRelDisplayContext.isShowInfoPanel() %>">
			<liferay-frontend:management-bar-sidenav-toggler-button
				icon="info-circle"
				label="info"
			/>
		</c:if>

		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="<%= cpDefinitionOptionRelDisplayContext.getDisplayStyle() %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= cpDefinitionOptionRelDisplayContext.getOrderByCol() %>"
			orderByType="<%= cpDefinitionOptionRelDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"priority", "create-date", "title"} %>'
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<c:if test="<%= cpDefinitionOptionRelDisplayContext.isShowInfoPanel() %>">
			<liferay-frontend:management-bar-sidenav-toggler-button
				icon="info-circle"
				label="info"
			/>
		</c:if>

		<liferay-frontend:management-bar-button href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCPDefinitionOptionRels();" %>' icon="trash" label="delete" />
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<div id="<portlet:namespace />productDefinitionOptionRelsContainer">
	<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
		<c:if test="<%= cpDefinitionOptionRelDisplayContext.isShowInfoPanel() %>">
			<liferay-portlet:resourceURL
				copyCurrentRenderParameters="<%= false %>"
				id="cpDefinitionOptionRelInfoPanel"
				var="sidebarPanelURL"
			/>

			<liferay-frontend:sidebar-panel
				resourceURL="<%= sidebarPanelURL %>"
				searchContainerId="cpDefinitionOptionRels"
			>
				<liferay-util:include page="/definition_option_rel_info_panel.jsp" servletContext="<%= application %>" />
			</liferay-frontend:sidebar-panel>
		</c:if>

		<div class="sidenav-content">
			<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="deleteCPDefinitionOptionRelIds" type="hidden" />

				<div class="product-definition-option-rels-container" id="<portlet:namespace />entriesContainer">
					<liferay-ui:search-container
						id="cpDefinitionOptionRels"
						iteratorURL="<%= portletURL %>"
						searchContainer="<%= cpDefinitionOptionRelSearchContainer %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.commerce.product.model.CPDefinitionOptionRel"
							cssClass="entry-display-style"
							keyProperty="CPDefinitionOptionRelId"
							modelVar="cpDefinitionOptionRel"
						>

							<%
							PortletURL rowURL = renderResponse.createRenderURL();

							rowURL.setParameter("mvcRenderCommandName", "editProductDefinitionOptionRel");
							rowURL.setParameter("redirect", currentURL);
							rowURL.setParameter("cpDefinitionOptionRelId", String.valueOf(cpDefinitionOptionRel.getCPDefinitionOptionRelId()));
							%>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								href="<%= rowURL %>"
								name="title"
								value="<%= HtmlUtil.escape(cpDefinitionOptionRel.getTitle(languageId)) %>"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="author"
								property="userName"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								property="facetable"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								property="required"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="sku-contributor"
								property="skuContributor"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								property="priority"
							/>

							<liferay-ui:search-container-column-date
								cssClass="table-cell-content"
								name="create-date"
								property="createDate"
							/>

							<liferay-ui:search-container-column-date
								cssClass="table-cell-content"
								name="modified-date"
								property="modifiedDate"
							/>

							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="/definition_option_rel_action.jsp"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator markupView="lexicon" searchContainer="<%= cpDefinitionOptionRelSearchContainer %>" />
					</liferay-ui:search-container>
				</div>
			</aui:form>
		</div>
	</div>
</div>

<portlet:actionURL name="editProductDefinitionOptionRel" var="addCPDefinitionOptionRelURL" />

<aui:form action="<%= addCPDefinitionOptionRelURL %>" cssClass="hide" name="addCPDefinitionOptionRelFm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinitionId %>" />
	<aui:input name="cpOptionIds" type="hidden" value="" />
</aui:form>

<liferay-frontend:add-menu>
	<liferay-frontend:add-menu-item id="addCommerceProductOption" title='<%= LanguageUtil.get(request, "add-option") %>' url="javascript:;" />
</liferay-frontend:add-menu>

<aui:script>
	function <portlet:namespace />deleteCPDefinitionOptionRels() {
		if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-options") %>')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCPDefinitionOptionRelIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editProductDefinitionOptionRel" />');
		}
	}
</aui:script>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />addCommerceProductOption').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'productOptionsSelectItem',
					on: {
						selectedItemChange: function(event) {
							var selectedItems = event.newVal;

							if (selectedItems) {

								$('#<portlet:namespace />cpOptionIds').val(selectedItems);

								var addCPDefinitionOptionRelFm = $('#<portlet:namespace />addCPDefinitionOptionRelFm');

								submitForm(addCPDefinitionOptionRelFm);
							}
						}
					},
					title: '<liferay-ui:message arguments="<%= cpDefinition.getTitle(languageId) %>" key="add-new-option-to-x" />',
					url: '<%= cpDefinitionOptionRelDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>