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

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class TeamAccountRoleModelListener
	extends BaseAuditModelListener<TeamAccountRole> {

	@Override
	public void onAfterCreate(TeamAccountRole teamAccountRole)
		throws ModelListenerException {

		try {
			Account account = teamAccountRole.getAccount();
			Team team = teamAccountRole.getTeam();
			TeamRole teamRole = teamAccountRole.getTeamRole();

			ServiceContext serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Account.class),
				teamAccountRole.getAccountId());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				teamAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				AuditEntry.Action.ASSIGN.toString(), "Team Role",
				StringPool.BLANK, StringPool.BLANK, teamRole.getName(),
				String.valueOf(teamAccountRole.getTeamRoleId()), team.getName(),
				serviceContext);

			serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId());

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				classNameLocalService.getClassNameId(TeamRole.class),
				teamAccountRole.getTeamRoleId(),
				AuditEntry.Action.ASSIGN.toString(), "Account",
				StringPool.BLANK, StringPool.BLANK, account.getName(),
				String.valueOf(account.getAccountId()), teamRole.getName(),
				serviceContext);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(TeamAccountRole teamAccountRole)
		throws ModelListenerException {

		try {
			Account account = teamAccountRole.getAccount();
			Team team = teamAccountRole.getTeam();
			TeamRole teamRole = teamAccountRole.getTeamRole();

			ServiceContext serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Account.class),
				teamAccountRole.getAccountId());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				teamAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				AuditEntry.Action.UNASSIGN.toString(), "Team Role",
				teamRole.getName(),
				String.valueOf(teamAccountRole.getTeamRoleId()),
				StringPool.BLANK, StringPool.BLANK, team.getName(),
				serviceContext);

			serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId());

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				classNameLocalService.getClassNameId(TeamRole.class),
				teamAccountRole.getTeamRoleId(),
				AuditEntry.Action.UNASSIGN.toString(), "Account",
				account.getName(), String.valueOf(account.getAccountId()),
				StringPool.BLANK, StringPool.BLANK, teamRole.getName(),
				serviceContext);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

}