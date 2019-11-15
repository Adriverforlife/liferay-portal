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

package com.liferay.osb.koroneiki.trunk.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for ProductPurchase. This utility wraps
 * <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseService
 * @generated
 */
public class ProductPurchaseServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductPurchaseServiceUtil} to access the product purchase remote service. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				long accountId, long productEntryId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addProductPurchase(
			accountId, productEntryId, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				String accountKey, String productEntryKey,
				java.util.Date startDate, java.util.Date endDate,
				java.util.Date originalEndDate, int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addProductPurchase(
			accountKey, productEntryKey, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteProductPurchase(productPurchaseId);
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteProductPurchase(productPurchaseKey);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getAccountProductPurchases(long accountId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountProductPurchases(accountId, start, end);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getAccountProductPurchases(
					String accountKey, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountProductPurchases(accountKey, start, end);
	}

	public static int getAccountProductPurchasesCount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountProductPurchasesCount(accountId);
	}

	public static int getAccountProductPurchasesCount(String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountProductPurchasesCount(accountKey);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getContactProductPurchases(long contactId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContactProductPurchases(contactId, start, end);
	}

	public static int getContactProductPurchasesCount(long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContactProductPurchasesCount(contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getProductPurchase(productPurchaseId);
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getProductPurchase(productPurchaseKey);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getProductPurchases(
					String domain, String entityName, String entityId,
					int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getProductPurchases(
			domain, entityName, entityId, start, end);
	}

	public static int getProductPurchasesCount(
			String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getProductPurchasesCount(
			domain, entityName, entityId);
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			updateProductPurchase(
				long productPurchaseId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateProductPurchase(
			productPurchaseId, startDate, endDate, originalEndDate, quantity,
			status, productFields);
	}

	public static ProductPurchaseService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ProductPurchaseService, ProductPurchaseService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProductPurchaseService.class);

		ServiceTracker<ProductPurchaseService, ProductPurchaseService>
			serviceTracker =
				new ServiceTracker
					<ProductPurchaseService, ProductPurchaseService>(
						bundle.getBundleContext(), ProductPurchaseService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}