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

package com.liferay.batch.engine;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.odata.entity.EntityModel;

import java.io.Serializable;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

/**
 * @author Ivica Cardic
 */
public abstract class BaseBatchEngineTaskItemDelegate<T>
	implements BatchEngineTaskItemDelegate<T> {

	@Override
	public void create(
			Collection<T> items, Map<String, Serializable> queryParameters,
			User user)
		throws Exception {

		for (T item : items) {
			createItem(item, queryParameters, user);
		}
	}

	public void createItem(
			T item, Map<String, Serializable> queryParameters, User user)
		throws Exception {
	}

	@Override
	public void delete(
			Collection<T> items, Map<String, Serializable> queryParameters,
			User user)
		throws Exception {

		for (T item : items) {
			deleteItem(item, queryParameters, user);
		}
	}

	public void deleteItem(
			T item, Map<String, Serializable> queryParameters, User user)
		throws Exception {
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap<?, ?> multivaluedMap)
		throws Exception {

		return null;
	}

	@Override
	public void update(
			Collection<T> items, Map<String, Serializable> queryParameters,
			User user)
		throws Exception {

		for (T item : items) {
			updateItem(item, queryParameters, user);
		}
	}

	public void updateItem(
			T item, Map<String, Serializable> queryParameters, User user)
		throws Exception {
	}

}