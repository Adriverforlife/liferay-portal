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
String redirect = ParamUtil.getString(request, "redirect");

Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);
AccountNote accountNote = (AccountNote)request.getAttribute(TaprootWebKeys.ACCOUNT_NOTE);

long accountNoteId = BeanParamUtil.getLong(accountNote, request, "accountNoteId");

renderResponse.setTitle(koroneikiAccount.getName());
%>

<portlet:actionURL name="/accounts_admin/edit_account_note" var="editAccountNoteURL" />

<aui:form action="<%= editAccountNoteURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="accountNoteId" type="hidden" value="<%= String.valueOf(accountNoteId) %>" />
	<aui:input name="accountId" type="hidden" value="<%= String.valueOf(koroneikiAccount.getAccountId()) %>" />

	<aui:model-context bean="<%= accountNote %>" model="<%= AccountNote.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:choose>
				<c:when test="<%= accountNote != null %>">
					<h5><liferay-ui:message key="type" /></h5>

					<p>
						<%= accountNote.getType() %>
					</p>
				</c:when>
				<c:otherwise>
					<aui:select name="type">

						<%
						for (Note.Type curType : Note.Type.values()) {
						%>

							<aui:option label="<%= curType %>" value="<%= curType %>" />

						<%
						}
						%>

					</aui:select>
				</c:otherwise>
			</c:choose>

			<aui:select name="priority">

				<%
				for (int i = 1; i <= 10; i++) {
				%>

					<aui:option label="<%= i %>" value="<%= i %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input name="content" />

			<aui:select name="format">

				<%
				for (Note.Format curFormat : Note.Format.values()) {
				%>

					<aui:option label="<%= curFormat %>" value="<%= curFormat %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select name="status">

				<%
				for (Note.Status curStatus : Note.Status.values()) {
				%>

					<aui:option label="<%= curStatus %>" value="<%= curStatus %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button primary="<%= true %>" type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>