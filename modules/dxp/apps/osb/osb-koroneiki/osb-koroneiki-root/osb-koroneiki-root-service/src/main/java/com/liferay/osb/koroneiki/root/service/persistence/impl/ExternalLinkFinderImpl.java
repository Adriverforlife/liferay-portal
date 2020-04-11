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

package com.liferay.osb.koroneiki.root.service.persistence.impl;

import com.liferay.osb.koroneiki.root.service.persistence.ExternalLinkFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(service = ExternalLinkFinder.class)
public class ExternalLinkFinderImpl
	extends ExternalLinkFinderBaseImpl implements ExternalLinkFinder {

	public static final String FIND_BY_DOMAIN =
		ExternalLinkFinder.class.getName() + ".findByDomain";

	public static final String FIND_BY_D_EN =
		ExternalLinkFinder.class.getName() + ".findByD_EN";

	@Override
	public List<String> findByDomain(String domain, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_DOMAIN);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("domain", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(domain);

			return (List<String>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<String> findByD_EN(
		String domain, String entityName, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_D_EN);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("entityName", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(domain);
			qPos.add(entityName);

			return (List<String>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Reference
	private CustomSQL _customSQL;

}