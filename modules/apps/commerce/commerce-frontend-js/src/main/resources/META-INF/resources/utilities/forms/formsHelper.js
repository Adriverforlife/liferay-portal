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

const FIELD_BLACKLIST = ['formDate', 'p_auth'];

export function isFormElement(form) {
	return form instanceof HTMLFormElement;
}

function isBlacklistedField(field) {
	return FIELD_BLACKLIST.includes(field);
}

export function toJSON(formData) {
	const json = {};

	// eslint-disable-next-line no-for-of-loops/no-for-of-loops, no-unused-vars
	for (const [key, value] of formData.entries()) {
		if (!isBlacklistedField(key)) {
			if (!(key in json)) {
				json[key] = value;
				continue;
			}

			if (!Array.isArray(json[key])) {
				json[key] = [json[key]];
			}

			json[key].push(value);
		}
	}

	return json;
}
