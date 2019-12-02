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

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class EntitlementSerDes {

	public static Entitlement toDTO(String json) {
		EntitlementJSONParser entitlementJSONParser =
			new EntitlementJSONParser();

		return entitlementJSONParser.parseToDTO(json);
	}

	public static Entitlement[] toDTOs(String json) {
		EntitlementJSONParser entitlementJSONParser =
			new EntitlementJSONParser();

		return entitlementJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Entitlement entitlement) {
		if (entitlement == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (entitlement.getEntitlementDefinitionKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"entitlementDefinitionKey\": ");

			sb.append("\"");

			sb.append(_escape(entitlement.getEntitlementDefinitionKey()));

			sb.append("\"");
		}

		if (entitlement.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(entitlement.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		EntitlementJSONParser entitlementJSONParser =
			new EntitlementJSONParser();

		return entitlementJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Entitlement entitlement) {
		if (entitlement == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (entitlement.getEntitlementDefinitionKey() == null) {
			map.put("entitlementDefinitionKey", null);
		}
		else {
			map.put(
				"entitlementDefinitionKey",
				String.valueOf(entitlement.getEntitlementDefinitionKey()));
		}

		if (entitlement.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(entitlement.getName()));
		}

		return map;
	}

	public static class EntitlementJSONParser
		extends BaseJSONParser<Entitlement> {

		@Override
		protected Entitlement createDTO() {
			return new Entitlement();
		}

		@Override
		protected Entitlement[] createDTOArray(int size) {
			return new Entitlement[size];
		}

		@Override
		protected void setField(
			Entitlement entitlement, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName, "entitlementDefinitionKey")) {

				if (jsonParserFieldValue != null) {
					entitlement.setEntitlementDefinitionKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					entitlement.setName((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}