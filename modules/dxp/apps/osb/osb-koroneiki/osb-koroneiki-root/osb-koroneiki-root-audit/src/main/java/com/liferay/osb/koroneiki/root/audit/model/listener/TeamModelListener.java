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
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class TeamModelListener extends BaseAuditModelListener<Team> {

	@Override
	public void onAfterCreate(Team team) throws ModelListenerException {
		super.onAfterCreate(team);

		try {
			ServiceContext serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Team.class),
				team.getTeamId());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				team.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				team.getTeamId(), AuditEntry.Action.ADD.toString(), "name",
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				team.getName(), getDescription(team), serviceContext);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(Team team) throws ModelListenerException {
		super.onBeforeRemove(team);

		try {
			ServiceContext serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Team.class),
				team.getTeamId());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				team.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				team.getTeamId(), AuditEntry.Action.DELETE.toString(), "name",
				StringPool.BLANK, team.getName(), StringPool.BLANK,
				StringPool.BLANK, getDescription(team), serviceContext);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeUpdate(Team team) throws ModelListenerException {
		super.onBeforeUpdate(team);

		try {
			ServiceContext serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Team.class),
				team.getTeamId());

			Team oldTeam = getModel(team.getTeamId());

			Map<String, Object> oldAttributes = oldTeam.getModelAttributes();

			Map<String, Object> attributes = team.getModelAttributes();

			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				String field = entry.getKey();

				if (isIgnoredField(field)) {
					continue;
				}

				Object oldValue = oldAttributes.get(field);

				Object value = entry.getValue();

				if (isSkipFieldUpdate(field, oldValue, value)) {
					continue;
				}

				auditEntryLocalService.addAuditEntry(
					getUserId(),
					classNameLocalService.getClassNameId(Account.class),
					team.getAccountId(),
					classNameLocalService.getClassNameId(Team.class),
					team.getTeamId(), AuditEntry.Action.UPDATE.toString(),
					field, StringPool.BLANK, String.valueOf(oldValue),
					StringPool.BLANK, String.valueOf(value),
					getDescription(team), serviceContext);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	protected String getDescription(Team team) throws PortalException {
		return team.getName();
	}

	@Override
	protected Team getModel(long classPK) throws PortalException {
		return _teamLocalService.getTeam(classPK);
	}

	@Reference
	private TeamLocalService _teamLocalService;

}