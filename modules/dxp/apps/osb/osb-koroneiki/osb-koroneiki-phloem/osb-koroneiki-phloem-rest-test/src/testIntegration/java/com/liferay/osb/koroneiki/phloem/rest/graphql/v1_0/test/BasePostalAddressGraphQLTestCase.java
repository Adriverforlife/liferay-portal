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

package com.liferay.osb.koroneiki.phloem.rest.graphql.v1_0.test;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BasePostalAddressGraphQLTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testGetPostalAddress() throws Exception {
		PostalAddress postPostalAddress =
			testGetPostalAddress_addPostalAddress();

		List<GraphQLField> graphQLFields = new ArrayList<>();

		graphQLFields.add(new GraphQLField("id"));

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"getPostalAddress",
				new HashMap<String, Object>() {
					{
						put("postalAddressId", postPostalAddress.getId());
					}
				},
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
			_invoke(graphQLField.toString()));

		JSONObject dataJSONObject = responseJSONObject.getJSONObject("data");

		Assert.assertTrue(
			equals(
				postPostalAddress,
				dataJSONObject.getJSONObject("getPostalAddress")));
	}

	protected PostalAddress testGetPostalAddress_addPostalAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected boolean equals(
		PostalAddress postalAddress, JSONObject jsonObject) {

		List<String> fieldNames = new ArrayList(
			Arrays.asList(getAdditionalAssertFieldNames()));

		fieldNames.add("id");

		for (String fieldName : fieldNames) {
			if (Objects.equals("addressCountry", fieldName)) {
				if (!Objects.equals(
						postalAddress.getAddressCountry(),
						(String)jsonObject.getString("addressCountry"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("addressLocality", fieldName)) {
				if (!Objects.equals(
						postalAddress.getAddressLocality(),
						(String)jsonObject.getString("addressLocality"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("addressRegion", fieldName)) {
				if (!Objects.equals(
						postalAddress.getAddressRegion(),
						(String)jsonObject.getString("addressRegion"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("addressType", fieldName)) {
				if (!Objects.equals(
						postalAddress.getAddressType(),
						(String)jsonObject.getString("addressType"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", fieldName)) {
				if (!Objects.equals(
						postalAddress.getId(),
						(Long)jsonObject.getLong("id"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("mailing", fieldName)) {
				if (!Objects.equals(
						postalAddress.getMailing(),
						(Boolean)jsonObject.getBoolean("mailing"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("postalCode", fieldName)) {
				if (!Objects.equals(
						postalAddress.getPostalCode(),
						(String)jsonObject.getString("postalCode"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("primary", fieldName)) {
				if (!Objects.equals(
						postalAddress.getPrimary(),
						(Boolean)jsonObject.getBoolean("primary"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("streetAddressLine1", fieldName)) {
				if (!Objects.equals(
						postalAddress.getStreetAddressLine1(),
						(String)jsonObject.getString("streetAddressLine1"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("streetAddressLine2", fieldName)) {
				if (!Objects.equals(
						postalAddress.getStreetAddressLine2(),
						(String)jsonObject.getString("streetAddressLine2"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("streetAddressLine3", fieldName)) {
				if (!Objects.equals(
						postalAddress.getStreetAddressLine3(),
						(String)jsonObject.getString("streetAddressLine3"))) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected PostalAddress randomPostalAddress() throws Exception {
		return new PostalAddress() {
			{
				addressCountry = RandomTestUtil.randomString();
				addressLocality = RandomTestUtil.randomString();
				addressRegion = RandomTestUtil.randomString();
				addressType = RandomTestUtil.randomString();
				id = RandomTestUtil.randomLong();
				mailing = RandomTestUtil.randomBoolean();
				postalCode = RandomTestUtil.randomString();
				primary = RandomTestUtil.randomBoolean();
				streetAddressLine1 = RandomTestUtil.randomString();
				streetAddressLine2 = RandomTestUtil.randomString();
				streetAddressLine3 = RandomTestUtil.randomString();
			}
		};
	}

	protected Company testCompany;
	protected Group testGroup;

	private String _invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		JSONObject jsonObject = JSONUtil.put("query", query);

		httpInvoker.body(jsonObject.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	private class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

}