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

package com.liferay.commerce.order.web.internal.frontend;

import com.liferay.frontend.taglib.clay.data.set.ClayDataSetDisplayView;
import com.liferay.frontend.taglib.clay.data.set.view.table.BaseTableClayDataSetDisplayView;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchema;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaBuilder;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaBuilderFactory;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaField;
import com.liferay.petra.string.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, immediate = true,
	property = "clay.data.set.display.name=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_ORDER_ITEMS,
	service = ClayDataSetDisplayView.class
)
public class CommerceOrderItemClayTableDataSetDisplayView
	extends BaseTableClayDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.create();

		ClayTableSchemaField imageField =
			clayTableSchemaBuilder.addClayTableSchemaField(
				"image", StringPool.BLANK);

		imageField.setContentRenderer("image");

		ClayTableSchemaField skuField =
			clayTableSchemaBuilder.addClayTableSchemaField("sku", "sku");

		skuField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addClayTableSchemaField("name", "name");

		clayTableSchemaBuilder.addClayTableSchemaField("options", "options");

		clayTableSchemaBuilder.addClayTableSchemaField(
			"requestedDeliveryDate", "delivery-date");

		clayTableSchemaBuilder.addClayTableSchemaField(
			"deliveryGroup", "delivery-group");

		ClayTableSchemaField priceField =
			clayTableSchemaBuilder.addClayTableSchemaField("price", "price");

		priceField.setContentRenderer("commerceTableCellSubscription");

		clayTableSchemaBuilder.addClayTableSchemaField("discount", "discount");

		clayTableSchemaBuilder.addClayTableSchemaField("quantity", "quantity");

		clayTableSchemaBuilder.addClayTableSchemaField("total", "total");

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}