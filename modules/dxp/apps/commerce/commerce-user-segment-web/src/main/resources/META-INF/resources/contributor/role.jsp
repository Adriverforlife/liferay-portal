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
CommerceUserSegmentCriterionTypeRoleDisplayContext commerceUserSegmentCriterionTypeRoleDisplayContext = (CommerceUserSegmentCriterionTypeRoleDisplayContext)request.getAttribute("role.jsp-portletDisplayContext");

List<Role> roles = commerceUserSegmentCriterionTypeRoleDisplayContext.getRoles();
%>

<liferay-util:buffer
	var="removeCommerceUserSegmentCriterionRoleIcon"
>
	<liferay-ui:icon
		icon="times"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	cssClass="lfr-search-container-user-segment-criterion-roles"
	curParam="commerceUserSegmentCriterionTypeRoleCur"
	headerNames="null,null"
	id="commerceUserSegmentCriterionRoleSearchContainer"
	iteratorURL="<%= currentURLObj %>"
	total="<%= roles.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= roles.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Role"
		keyProperty="roleId"
		modelVar="role"
	>
		<liferay-ui:search-container-column-text
			cssClass="table-cell-content"
			value="<%= HtmlUtil.escape(role.getName()) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a class="float-right modify-link" data-rowId="<%= role.getRoleId() %>" href="javascript:;"><%= removeCommerceUserSegmentCriterionRoleIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<aui:button name="selectCommerceUserSegmentCriterionTypeRole" value="select" />

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />selectCommerceUserSegmentCriterionTypeRole').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'rolesSelectItem',
					on: {
						selectedItemChange: function(event) {
							var selectedItems = event.newVal;

							if (selectedItems) {
								var A = AUI();

								selectedItems = selectedItems.split(",");

								A.Array.each(
									selectedItems,
									function(item, index, selectedItems) {
										<portlet:namespace />addCommerceUserSegmentCriterionTypeRole(item);
									}
								);
							}
						}
					},
					title: '<liferay-ui:message arguments="role" key="select-x" />',
					url: '<%= commerceUserSegmentCriterionTypeRoleDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>

<aui:script>
	var <portlet:namespace />addCommerceUserSegmentCriterionTypeRoleIds = [];
	var <portlet:namespace />deleteCommerceUserSegmentCriterionTypeRoleIds = [];

	function <portlet:namespace />addCommerceUserSegmentCriterionTypeRole(item) {
		var A = AUI();

		var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />commerceUserSegmentCriterionRoleSearchContainer');

		var rowColumns = [];

		rowColumns.push(item);
		rowColumns.push('<a class="float-right modify-link" data-rowId="' + item + '" href="javascript:;"><%= UnicodeFormatter.toString(removeCommerceUserSegmentCriterionRoleIcon) %></a>');

		A.Array.removeItem(<portlet:namespace />deleteCommerceUserSegmentCriterionTypeRoleIds, item);

		<portlet:namespace />addCommerceUserSegmentCriterionTypeRoleIds.push(item);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceUserSegmentCriterionTypeRoleIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceUserSegmentCriterionTypeRoleIds.join(',');

		searchContainer.addRow(rowColumns, item);

		searchContainer.updateDataStore();
	}

	function <portlet:namespace />deleteCommerceUserSegmentCriterionTypeRole(roleId) {
		var A = AUI();

		A.Array.removeItem(<portlet:namespace />addCommerceUserSegmentCriterionTypeRoleIds, roleId);

		<portlet:namespace />deleteCommerceUserSegmentCriterionTypeRoleIds.push(roleId);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceUserSegmentCriterionTypeRoleIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceUserSegmentCriterionTypeRoleIds.join(',');
	}
</aui:script>

<aui:script use="liferay-search-container">
	var Util = Liferay.Util;

	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />commerceUserSegmentCriterionRoleSearchContainer');

	var searchContainerContentBox = searchContainer.get('contentBox');

	searchContainerContentBox.delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var rowId = link.attr('data-rowId');

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));

			<portlet:namespace />deleteCommerceUserSegmentCriterionTypeRole(rowId);
		},
		'.modify-link'
	);
</aui:script>