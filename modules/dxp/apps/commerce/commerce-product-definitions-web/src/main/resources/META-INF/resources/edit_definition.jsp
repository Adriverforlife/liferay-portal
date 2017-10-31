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
CPDefinitionsDisplayContext cpDefinitionsDisplayContext = (CPDefinitionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionsDisplayContext.getCPDefinition();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "editProductDefinition");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(catalogURL);

String title = LanguageUtil.get(request, "add-product");

if (cpDefinition != null) {
	title = cpDefinition.getTitle(languageId);
}

renderResponse.setTitle(title);

request.setAttribute("view.jsp-cpDefinition", cpDefinition);
request.setAttribute("view.jsp-cpType", cpDefinitionsDisplayContext.getCPType());
request.setAttribute("view.jsp-portletURL", portletURL);
request.setAttribute("view.jsp-showSearch", false);
%>

<div class="container-fluid-1280">
	<h1><%= title %></h1>
</div>

<liferay-frontend:screen-navigation
	key="<%= CPDefinitionScreenNavigationConstants.SCREEN_NAVIGATION_KEY_CP_DEFINITION_GENERAL %>"
	modelBean="<%= cpDefinition %>"
	portletURL="<%= currentURLObj %>"
/>