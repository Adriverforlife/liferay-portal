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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.DisplayTerms" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.DateUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinition" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowException" %><%@
page import="com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.web.internal.constants.KaleoDesignerActionKeys" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.web.internal.constants.KaleoDesignerWebKeys" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.web.internal.dao.search.KaleoDefinitionVersionResultRowSplitter" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.web.internal.permission.KaleoDefinitionVersionPermission" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.web.internal.permission.KaleoDesignerPermission" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.web.internal.portlet.display.context.KaleoDesignerDisplayContext" %><%@
page import="com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion" %><%@
page import="com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalServiceUtil" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.text.Format" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
KaleoDesignerDisplayContext kaleoDesignerDisplayContext = (KaleoDesignerDisplayContext)renderRequest.getAttribute(KaleoDesignerWebKeys.KALEO_DESIGNER_DISPLAY_CONTEXT);

Format dateFormatTime = null;

if (!DateUtil.isFormatAmPm(locale)) {
	dateFormatTime = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM d, yyyy, HH:mm", locale, timeZone);
}
else {
	dateFormatTime = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM d, yyyy, hh:mm a", locale, timeZone);
}
%>