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
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "verifyPassword");
%>

<aui:form cssClass="modal-body" name="fm">
	<div class="sheet-text"><liferay-ui:message key="changes-to-the-email-address-or-screen-name-require-password-verification" /></div>

	<div class="hide" id="<portlet:namespace />verificationAlert">
		<clay:alert
			displayType="danger"
			message="incorrect-password-please-try-again"
		/>
	</div>

	<aui:field-wrapper cssClass="form-group">
		<aui:input name="userId" type="hidden" value="<%= themeDisplay.getUserId() %>" />
		<aui:input autocomplete="off" label="your-password" name="password" required="<%= true %>" size="30" type="password" />

		<div class="form-text">
			<liferay-ui:message key="enter-your-current-password-to-confirm-changes" />
		</div>
	</aui:field-wrapper>

	<aui:button-row cssClass="position-fixed">
		<aui:button onClick='<%= liferayPortletResponse.getNamespace() + "verifyPassword();" %>' primary="<%= true %>" value="confirm" />

		<aui:button type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />verifyPassword() {
		var form = document.<portlet:namespace />fm;

		Liferay.Util.fetch(
			'<liferay-portlet:resourceURL id="/users_admin/authenticate_user" />',
			{
				body: new FormData(form),
				method: 'POST',
			}
		)
			.then(function (response) {
				if (!response.ok) {
					throw new Error();
				}

				return response.text();
			})
			.then(function (response) {
				var openingLiferay = Liferay.Util.getOpener().Liferay;

				openingLiferay.fire('<%= HtmlUtil.escapeJS(eventName) %>');
				openingLiferay.fire('closeModal');
			})
			.catch(function (error) {
				var verificationAlert = document.getElementById(
					'<portlet:namespace />verificationAlert'
				);

				verificationAlert.classList.remove('hide');

				document.getElementById('<portlet:namespace/>password').focus();
			});
	}
</aui:script>