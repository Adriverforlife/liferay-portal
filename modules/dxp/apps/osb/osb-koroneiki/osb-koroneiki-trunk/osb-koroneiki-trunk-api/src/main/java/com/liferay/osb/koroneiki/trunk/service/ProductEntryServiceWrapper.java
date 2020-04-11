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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryService
 * @generated
 */
public class ProductEntryServiceWrapper
	implements ProductEntryService, ServiceWrapper<ProductEntryService> {

	public ProductEntryServiceWrapper(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry addProductEntry(
			String name,
			java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.addProductEntry(name, productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			deleteProductEntry(long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.deleteProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			deleteProductEntry(String productEntryKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.deleteProductEntry(productEntryKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductEntry>
			getAccountProductEntries(
				long accountId, String search, String state, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getAccountProductEntries(
			accountId, search, state, start, end);
	}

	@Override
	public int getAccountProductEntriesCount(
			long accountId, String search, String state)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getAccountProductEntriesCount(
			accountId, search, state);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductEntry>
			getProductEntries(int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getProductEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductEntry>
			getProductEntries(
				String domain, String entityName, String entityId, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getProductEntries(
			domain, entityName, entityId, start, end);
	}

	@Override
	public int getProductEntriesCount()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getProductEntriesCount();
	}

	@Override
	public int getProductEntriesCount(
			String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getProductEntriesCount(
			domain, entityName, entityId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry getProductEntry(
			long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry getProductEntry(
			String productEntryKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getProductEntry(productEntryKey);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			getProductEntryByName(String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.getProductEntryByName(name);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			updateProductEntry(
				long productEntryId, String name,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.updateProductEntry(
			productEntryId, name, productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			updateProductEntry(
				String productEntryKey, String name,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryService.updateProductEntry(
			productEntryKey, name, productFields);
	}

	@Override
	public ProductEntryService getWrappedService() {
		return _productEntryService;
	}

	@Override
	public void setWrappedService(ProductEntryService productEntryService) {
		_productEntryService = productEntryService;
	}

	private ProductEntryService _productEntryService;

}