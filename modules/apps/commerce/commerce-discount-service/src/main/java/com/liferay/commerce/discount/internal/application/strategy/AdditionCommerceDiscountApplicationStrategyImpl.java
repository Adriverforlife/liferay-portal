/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.discount.internal.application.strategy;

import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.portal.kernel.exception.PortalException;

import java.math.BigDecimal;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "commerce.discount.application.strategy.key=" + CommercePricingConstants.DISCOUNT_ADDITION_METHOD,
	service = CommerceDiscountApplicationStrategy.class
)
public class AdditionCommerceDiscountApplicationStrategyImpl
	implements CommerceDiscountApplicationStrategy {

	@Override
	public BigDecimal applyCommerceDiscounts(
			BigDecimal commercePrice, BigDecimal[] commerceDiscountLevels)
		throws PortalException {

		BigDecimal discountedAmount = commercePrice;
		BigDecimal totalDiscount = BigDecimal.ZERO;

		for (BigDecimal commerceDiscountLevel : commerceDiscountLevels) {
			if ((commerceDiscountLevel == null) ||
				(commerceDiscountLevel.compareTo(BigDecimal.ZERO) == 0)) {

				continue;
			}

			totalDiscount = totalDiscount.add(commerceDiscountLevel);

			BigDecimal currentDiscountAmount = commercePrice.multiply(
				totalDiscount);

			currentDiscountAmount = currentDiscountAmount.divide(_ONE_HUNDRED);

			discountedAmount = commercePrice.subtract(currentDiscountAmount);
		}

		return discountedAmount;
	}

	private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);

}