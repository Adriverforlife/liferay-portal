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

package com.liferay.headless.commerce.admin.catalog.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.commerce.admin.catalog.client.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.admin.catalog.client.http.HttpInvoker;
import com.liferay.headless.commerce.admin.catalog.client.pagination.Page;
import com.liferay.headless.commerce.admin.catalog.client.pagination.Pagination;
import com.liferay.headless.commerce.admin.catalog.client.resource.v1_0.ProductSpecificationResource;
import com.liferay.headless.commerce.admin.catalog.client.serdes.v1_0.ProductSpecificationSerDes;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public abstract class BaseProductSpecificationResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_productSpecificationResource.setContextCompany(testCompany);

		ProductSpecificationResource.Builder builder =
			ProductSpecificationResource.builder();

		productSpecificationResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProductSpecification productSpecification1 =
			randomProductSpecification();

		String json = objectMapper.writeValueAsString(productSpecification1);

		ProductSpecification productSpecification2 =
			ProductSpecificationSerDes.toDTO(json);

		Assert.assertTrue(equals(productSpecification1, productSpecification2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProductSpecification productSpecification =
			randomProductSpecification();

		String json1 = objectMapper.writeValueAsString(productSpecification);
		String json2 = ProductSpecificationSerDes.toJSON(productSpecification);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ProductSpecification productSpecification =
			randomProductSpecification();

		productSpecification.setSpecificationKey(regex);

		String json = ProductSpecificationSerDes.toJSON(productSpecification);

		Assert.assertFalse(json.contains(regex));

		productSpecification = ProductSpecificationSerDes.toDTO(json);

		Assert.assertEquals(regex, productSpecification.getSpecificationKey());
	}

	@Test
	public void testGetProductIdProductSpecificationsPage() throws Exception {
		Page<ProductSpecification> page =
			productSpecificationResource.getProductIdProductSpecificationsPage(
				testGetProductIdProductSpecificationsPage_getId(),
				Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		Long id = testGetProductIdProductSpecificationsPage_getId();
		Long irrelevantId =
			testGetProductIdProductSpecificationsPage_getIrrelevantId();

		if ((irrelevantId != null)) {
			ProductSpecification irrelevantProductSpecification =
				testGetProductIdProductSpecificationsPage_addProductSpecification(
					irrelevantId, randomIrrelevantProductSpecification());

			page =
				productSpecificationResource.
					getProductIdProductSpecificationsPage(
						irrelevantId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductSpecification),
				(List<ProductSpecification>)page.getItems());
			assertValid(page);
		}

		ProductSpecification productSpecification1 =
			testGetProductIdProductSpecificationsPage_addProductSpecification(
				id, randomProductSpecification());

		ProductSpecification productSpecification2 =
			testGetProductIdProductSpecificationsPage_addProductSpecification(
				id, randomProductSpecification());

		page =
			productSpecificationResource.getProductIdProductSpecificationsPage(
				id, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productSpecification1, productSpecification2),
			(List<ProductSpecification>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductIdProductSpecificationsPageWithPagination()
		throws Exception {

		Long id = testGetProductIdProductSpecificationsPage_getId();

		ProductSpecification productSpecification1 =
			testGetProductIdProductSpecificationsPage_addProductSpecification(
				id, randomProductSpecification());

		ProductSpecification productSpecification2 =
			testGetProductIdProductSpecificationsPage_addProductSpecification(
				id, randomProductSpecification());

		ProductSpecification productSpecification3 =
			testGetProductIdProductSpecificationsPage_addProductSpecification(
				id, randomProductSpecification());

		Page<ProductSpecification> page1 =
			productSpecificationResource.getProductIdProductSpecificationsPage(
				id, Pagination.of(1, 2));

		List<ProductSpecification> productSpecifications1 =
			(List<ProductSpecification>)page1.getItems();

		Assert.assertEquals(
			productSpecifications1.toString(), 2,
			productSpecifications1.size());

		Page<ProductSpecification> page2 =
			productSpecificationResource.getProductIdProductSpecificationsPage(
				id, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductSpecification> productSpecifications2 =
			(List<ProductSpecification>)page2.getItems();

		Assert.assertEquals(
			productSpecifications2.toString(), 1,
			productSpecifications2.size());

		Page<ProductSpecification> page3 =
			productSpecificationResource.getProductIdProductSpecificationsPage(
				id, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				productSpecification1, productSpecification2,
				productSpecification3),
			(List<ProductSpecification>)page3.getItems());
	}

	protected ProductSpecification
			testGetProductIdProductSpecificationsPage_addProductSpecification(
				Long id, ProductSpecification productSpecification)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetProductIdProductSpecificationsPage_getId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetProductIdProductSpecificationsPage_getIrrelevantId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductIdProductSpecification() throws Exception {
		ProductSpecification randomProductSpecification =
			randomProductSpecification();

		ProductSpecification postProductSpecification =
			testPostProductIdProductSpecification_addProductSpecification(
				randomProductSpecification);

		assertEquals(randomProductSpecification, postProductSpecification);
		assertValid(postProductSpecification);
	}

	protected ProductSpecification
			testPostProductIdProductSpecification_addProductSpecification(
				ProductSpecification productSpecification)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected ProductSpecification
			testGraphQLProductSpecification_addProductSpecification()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ProductSpecification productSpecification1,
		ProductSpecification productSpecification2) {

		Assert.assertTrue(
			productSpecification1 + " does not equal " + productSpecification2,
			equals(productSpecification1, productSpecification2));
	}

	protected void assertEquals(
		List<ProductSpecification> productSpecifications1,
		List<ProductSpecification> productSpecifications2) {

		Assert.assertEquals(
			productSpecifications1.size(), productSpecifications2.size());

		for (int i = 0; i < productSpecifications1.size(); i++) {
			ProductSpecification productSpecification1 =
				productSpecifications1.get(i);
			ProductSpecification productSpecification2 =
				productSpecifications2.get(i);

			assertEquals(productSpecification1, productSpecification2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ProductSpecification> productSpecifications1,
		List<ProductSpecification> productSpecifications2) {

		Assert.assertEquals(
			productSpecifications1.size(), productSpecifications2.size());

		for (ProductSpecification productSpecification1 :
				productSpecifications1) {

			boolean contains = false;

			for (ProductSpecification productSpecification2 :
					productSpecifications2) {

				if (equals(productSpecification1, productSpecification2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				productSpecifications2 + " does not contain " +
					productSpecification1,
				contains);
		}
	}

	protected void assertValid(ProductSpecification productSpecification)
		throws Exception {

		boolean valid = true;

		if (productSpecification.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("optionCategoryId", additionalAssertFieldName)) {
				if (productSpecification.getOptionCategoryId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (productSpecification.getPriority() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (productSpecification.getProductId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("specificationId", additionalAssertFieldName)) {
				if (productSpecification.getSpecificationId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("specificationKey", additionalAssertFieldName)) {
				if (productSpecification.getSpecificationKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("value", additionalAssertFieldName)) {
				if (productSpecification.getValue() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<ProductSpecification> page) {
		boolean valid = false;

		java.util.Collection<ProductSpecification> productSpecifications =
			page.getItems();

		int size = productSpecifications.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (Field field :
				ReflectionUtil.getDeclaredFields(
					com.liferay.headless.commerce.admin.catalog.dto.v1_0.
						ProductSpecification.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					ReflectionUtil.getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		ProductSpecification productSpecification1,
		ProductSpecification productSpecification2) {

		if (productSpecification1 == productSpecification2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productSpecification1.getId(),
						productSpecification2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("optionCategoryId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productSpecification1.getOptionCategoryId(),
						productSpecification2.getOptionCategoryId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productSpecification1.getPriority(),
						productSpecification2.getPriority())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productSpecification1.getProductId(),
						productSpecification2.getProductId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("specificationId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productSpecification1.getSpecificationId(),
						productSpecification2.getSpecificationId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("specificationKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productSpecification1.getSpecificationKey(),
						productSpecification2.getSpecificationKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("value", additionalAssertFieldName)) {
				if (!equals(
						(Map)productSpecification1.getValue(),
						(Map)productSpecification2.getValue())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_productSpecificationResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_productSpecificationResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator,
		ProductSpecification productSpecification) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("optionCategoryId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("priority")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("specificationId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("specificationKey")) {
			sb.append("'");
			sb.append(
				String.valueOf(productSpecification.getSpecificationKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("value")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected ProductSpecification randomProductSpecification()
		throws Exception {

		return new ProductSpecification() {
			{
				id = RandomTestUtil.randomLong();
				optionCategoryId = RandomTestUtil.randomLong();
				priority = RandomTestUtil.randomDouble();
				productId = RandomTestUtil.randomLong();
				specificationId = RandomTestUtil.randomLong();
				specificationKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected ProductSpecification randomIrrelevantProductSpecification()
		throws Exception {

		ProductSpecification randomIrrelevantProductSpecification =
			randomProductSpecification();

		return randomIrrelevantProductSpecification;
	}

	protected ProductSpecification randomPatchProductSpecification()
		throws Exception {

		return randomProductSpecification();
	}

	protected ProductSpecificationResource productSpecificationResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

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

				sb.setLength(sb.length() - 1);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseProductSpecificationResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.commerce.admin.catalog.resource.v1_0.
		ProductSpecificationResource _productSpecificationResource;

}