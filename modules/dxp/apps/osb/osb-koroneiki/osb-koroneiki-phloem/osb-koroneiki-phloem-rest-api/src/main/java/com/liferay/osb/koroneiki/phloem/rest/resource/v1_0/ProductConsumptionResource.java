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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

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
public interface ProductConsumptionResource {

	public Page<ProductConsumption> getAccountProductConsumptionsPage(
			Long accountId, Pagination pagination)
		throws Exception;

	public ProductConsumption postAccountProductConsumption(
			Long accountId, ProductConsumption productConsumption)
		throws Exception;

	public void deleteProductConsumption(Long productConsumptionId)
		throws Exception;

	public ProductConsumption getProductConsumption(Long productConsumptionId)
		throws Exception;

	public Page<ProductConsumption> getProjectProductConsumptionsPage(
			Long projectId, Pagination pagination)
		throws Exception;

	public ProductConsumption postProjectProductConsumption(
			Long projectId, ProductConsumption productConsumption)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(Company contextCompany);

}