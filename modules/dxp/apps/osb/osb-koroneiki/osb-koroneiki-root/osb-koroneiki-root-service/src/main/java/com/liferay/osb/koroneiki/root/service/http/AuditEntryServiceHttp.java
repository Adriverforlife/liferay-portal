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

package com.liferay.osb.koroneiki.root.service.http;

import com.liferay.osb.koroneiki.root.service.AuditEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AuditEntryServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryServiceSoap
 * @generated
 */
public class AuditEntryServiceHttp {

	public static java.util.List
		<com.liferay.osb.koroneiki.root.model.AuditEntry> getAuditEntries(
				HttpPrincipal httpPrincipal, long classNameId, long classPK,
				int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntries",
				_getAuditEntriesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.root.model.AuditEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.root.model.AuditEntry> getAuditEntries(
				HttpPrincipal httpPrincipal, long classNameId, long classPK,
				long fieldClassNameId, long fieldClassPK, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntries",
				_getAuditEntriesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, fieldClassNameId, fieldClassPK,
				start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.root.model.AuditEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAuditEntriesCount(
			HttpPrincipal httpPrincipal, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntriesCount",
				_getAuditEntriesCountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAuditEntriesCount(
			HttpPrincipal httpPrincipal, long classNameId, long classPK,
			long fieldClassNameId, long fieldClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntriesCount",
				_getAuditEntriesCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, fieldClassNameId,
				fieldClassPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.root.model.AuditEntry getAuditEntry(
			HttpPrincipal httpPrincipal, long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntry",
				_getAuditEntryParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, auditEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.root.model.AuditEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.root.model.AuditEntry getAuditEntry(
			HttpPrincipal httpPrincipal, String auditEntryKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntry",
				_getAuditEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, auditEntryKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.root.model.AuditEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AuditEntryServiceHttp.class);

	private static final Class<?>[] _getAuditEntriesParameterTypes0 =
		new Class[] {long.class, long.class, int.class, int.class};
	private static final Class<?>[] _getAuditEntriesParameterTypes1 =
		new Class[] {
			long.class, long.class, long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getAuditEntriesCountParameterTypes2 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getAuditEntriesCountParameterTypes3 =
		new Class[] {long.class, long.class, long.class, long.class};
	private static final Class<?>[] _getAuditEntryParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _getAuditEntryParameterTypes5 =
		new Class[] {String.class};

}