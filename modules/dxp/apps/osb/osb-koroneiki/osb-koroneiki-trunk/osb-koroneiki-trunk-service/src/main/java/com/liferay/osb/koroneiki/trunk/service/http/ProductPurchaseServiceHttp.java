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

package com.liferay.osb.koroneiki.trunk.service.http;

import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the HTTP utility for the
 * <code>ProductPurchaseServiceUtil</code> service
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
 * @see ProductPurchaseServiceSoap
 * @generated
 */
@ProviderType
public class ProductPurchaseServiceHttp {

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				HttpPrincipal httpPrincipal, long accountId, long projectId,
				long productEntryId, java.util.Date startDate,
				java.util.Date endDate, int quantity,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "addProductPurchase",
				_addProductPurchaseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, projectId, productEntryId, startDate,
				endDate, quantity, productFields);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(
				HttpPrincipal httpPrincipal, long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "deleteProductPurchase",
				_deleteProductPurchaseParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getAccountProductPurchases(
					HttpPrincipal httpPrincipal, long accountId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getAccountProductPurchases",
				_getAccountProductPurchasesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getAccountProductPurchasesCount(
			HttpPrincipal httpPrincipal, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class,
				"getAccountProductPurchasesCount",
				_getAccountProductPurchasesCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(
				HttpPrincipal httpPrincipal, long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getProductPurchase",
				_getProductPurchaseParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getProjectProductPurchases(
					HttpPrincipal httpPrincipal, long projectId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getProjectProductPurchases",
				_getProjectProductPurchasesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, projectId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getProjectProductPurchasesCount(
			HttpPrincipal httpPrincipal, long projectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class,
				"getProjectProductPurchasesCount",
				_getProjectProductPurchasesCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, projectId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			updateProductPurchase(
				HttpPrincipal httpPrincipal, long productPurchaseId,
				java.util.Date startDate, java.util.Date endDate, int quantity,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "updateProductPurchase",
				_updateProductPurchaseParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseId, startDate, endDate, quantity,
				productFields);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProductPurchaseServiceHttp.class);

	private static final Class<?>[] _addProductPurchaseParameterTypes0 =
		new Class[] {
			long.class, long.class, long.class, java.util.Date.class,
			java.util.Date.class, int.class, java.util.List.class
		};
	private static final Class<?>[] _deleteProductPurchaseParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getAccountProductPurchasesParameterTypes2 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[]
		_getAccountProductPurchasesCountParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getProductPurchaseParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _getProjectProductPurchasesParameterTypes5 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[]
		_getProjectProductPurchasesCountParameterTypes6 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateProductPurchaseParameterTypes7 =
		new Class[] {
			long.class, java.util.Date.class, java.util.Date.class, int.class,
			java.util.List.class
		};

}