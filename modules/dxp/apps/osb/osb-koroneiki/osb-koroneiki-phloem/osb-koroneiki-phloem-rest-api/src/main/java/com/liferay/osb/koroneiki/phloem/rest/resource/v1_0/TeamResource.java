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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamPermission;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/koroneiki-rest/v1.0
 *
 * @author Amos Fong
 * @generated
 */
@Generated("")
@ProviderType
public interface TeamResource {

	public Page<Team> getAccountAccountKeyAssignedTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public Page<Team> getAccountAccountKeyTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public Team postAccountAccountKeyTeam(
			String agentName, String agentUID, String accountKey, Team team)
		throws Exception;

	public Page<Team> getTeamsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Page<Team> getTeamByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception;

	public void deleteTeam(String agentName, String agentUID, String teamKey)
		throws Exception;

	public Team getTeam(String teamKey) throws Exception;

	public Team putTeam(
			String agentName, String agentUID, String teamKey, Team team)
		throws Exception;

	public void deleteTeamContactByOkta(
			String agentName, String agentUID, String teamKey, String[] oktaIds)
		throws Exception;

	public void putTeamContactByOkta(
			String agentName, String agentUID, String teamKey, String[] oktaIds)
		throws Exception;

	public void deleteTeamContactByOktaRole(
			String agentName, String agentUID, String teamKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception;

	public void putTeamContactByOktaRole(
			String agentName, String agentUID, String teamKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception;

	public void deleteTeamContactByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public void putTeamContactByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public void deleteTeamContactByUuidContactUuidRole(
			String agentName, String agentUID, String teamKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void putTeamContactByUuidContactUuidRole(
			String agentName, String agentUID, String teamKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void deleteTeamTeamPermission(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception;

	public void putTeamTeamPermission(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(User contextUser);

}