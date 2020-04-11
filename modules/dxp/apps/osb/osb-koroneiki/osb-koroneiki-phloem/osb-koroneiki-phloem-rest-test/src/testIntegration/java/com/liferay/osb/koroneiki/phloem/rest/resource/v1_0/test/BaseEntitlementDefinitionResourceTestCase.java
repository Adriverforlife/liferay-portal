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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.EntitlementDefinitionSerDes;
import com.liferay.petra.string.StringBundler;
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
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BaseEntitlementDefinitionResourceTestCase {

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

		_entitlementDefinitionResource.setContextCompany(testCompany);

		EntitlementDefinitionResource.Builder builder =
			EntitlementDefinitionResource.builder();

		entitlementDefinitionResource = builder.locale(
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

		EntitlementDefinition entitlementDefinition1 =
			randomEntitlementDefinition();

		String json = objectMapper.writeValueAsString(entitlementDefinition1);

		EntitlementDefinition entitlementDefinition2 =
			EntitlementDefinitionSerDes.toDTO(json);

		Assert.assertTrue(
			equals(entitlementDefinition1, entitlementDefinition2));
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

		EntitlementDefinition entitlementDefinition =
			randomEntitlementDefinition();

		String json1 = objectMapper.writeValueAsString(entitlementDefinition);
		String json2 = EntitlementDefinitionSerDes.toJSON(
			entitlementDefinition);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		EntitlementDefinition entitlementDefinition =
			randomEntitlementDefinition();

		entitlementDefinition.setDefinition(regex);
		entitlementDefinition.setDescription(regex);
		entitlementDefinition.setKey(regex);
		entitlementDefinition.setName(regex);

		String json = EntitlementDefinitionSerDes.toJSON(entitlementDefinition);

		Assert.assertFalse(json.contains(regex));

		entitlementDefinition = EntitlementDefinitionSerDes.toDTO(json);

		Assert.assertEquals(regex, entitlementDefinition.getDefinition());
		Assert.assertEquals(regex, entitlementDefinition.getDescription());
		Assert.assertEquals(regex, entitlementDefinition.getKey());
		Assert.assertEquals(regex, entitlementDefinition.getName());
	}

	@Test
	public void testGetAccountEntitlementDefinitionsPage() throws Exception {
		Page<EntitlementDefinition> page =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				RandomTestUtil.randomString(), Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		EntitlementDefinition entitlementDefinition1 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		page =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(entitlementDefinition1, entitlementDefinition2),
			(List<EntitlementDefinition>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountEntitlementDefinitionsPageWithPagination()
		throws Exception {

		EntitlementDefinition entitlementDefinition1 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition3 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		Page<EntitlementDefinition> page1 =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(1, 2));

		List<EntitlementDefinition> entitlementDefinitions1 =
			(List<EntitlementDefinition>)page1.getItems();

		Assert.assertEquals(
			entitlementDefinitions1.toString(), 2,
			entitlementDefinitions1.size());

		Page<EntitlementDefinition> page2 =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<EntitlementDefinition> entitlementDefinitions2 =
			(List<EntitlementDefinition>)page2.getItems();

		Assert.assertEquals(
			entitlementDefinitions2.toString(), 1,
			entitlementDefinitions2.size());

		Page<EntitlementDefinition> page3 =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				entitlementDefinition1, entitlementDefinition2,
				entitlementDefinition3),
			(List<EntitlementDefinition>)page3.getItems());
	}

	protected EntitlementDefinition
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				EntitlementDefinition entitlementDefinition)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactEntitlementDefinitionsPage() throws Exception {
		Page<EntitlementDefinition> page =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				RandomTestUtil.randomString(), Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		EntitlementDefinition entitlementDefinition1 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		page =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(entitlementDefinition1, entitlementDefinition2),
			(List<EntitlementDefinition>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactEntitlementDefinitionsPageWithPagination()
		throws Exception {

		EntitlementDefinition entitlementDefinition1 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition3 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		Page<EntitlementDefinition> page1 =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(1, 2));

		List<EntitlementDefinition> entitlementDefinitions1 =
			(List<EntitlementDefinition>)page1.getItems();

		Assert.assertEquals(
			entitlementDefinitions1.toString(), 2,
			entitlementDefinitions1.size());

		Page<EntitlementDefinition> page2 =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<EntitlementDefinition> entitlementDefinitions2 =
			(List<EntitlementDefinition>)page2.getItems();

		Assert.assertEquals(
			entitlementDefinitions2.toString(), 1,
			entitlementDefinitions2.size());

		Page<EntitlementDefinition> page3 =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				entitlementDefinition1, entitlementDefinition2,
				entitlementDefinition3),
			(List<EntitlementDefinition>)page3.getItems());
	}

	protected EntitlementDefinition
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				EntitlementDefinition entitlementDefinition)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetEntitlementDefinition() throws Exception {
		Assert.assertTrue(false);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		EntitlementDefinition entitlementDefinition1,
		EntitlementDefinition entitlementDefinition2) {

		Assert.assertTrue(
			entitlementDefinition1 + " does not equal " +
				entitlementDefinition2,
			equals(entitlementDefinition1, entitlementDefinition2));
	}

	protected void assertEquals(
		List<EntitlementDefinition> entitlementDefinitions1,
		List<EntitlementDefinition> entitlementDefinitions2) {

		Assert.assertEquals(
			entitlementDefinitions1.size(), entitlementDefinitions2.size());

		for (int i = 0; i < entitlementDefinitions1.size(); i++) {
			EntitlementDefinition entitlementDefinition1 =
				entitlementDefinitions1.get(i);
			EntitlementDefinition entitlementDefinition2 =
				entitlementDefinitions2.get(i);

			assertEquals(entitlementDefinition1, entitlementDefinition2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<EntitlementDefinition> entitlementDefinitions1,
		List<EntitlementDefinition> entitlementDefinitions2) {

		Assert.assertEquals(
			entitlementDefinitions1.size(), entitlementDefinitions2.size());

		for (EntitlementDefinition entitlementDefinition1 :
				entitlementDefinitions1) {

			boolean contains = false;

			for (EntitlementDefinition entitlementDefinition2 :
					entitlementDefinitions2) {

				if (equals(entitlementDefinition1, entitlementDefinition2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				entitlementDefinitions2 + " does not contain " +
					entitlementDefinition1,
				contains);
		}
	}

	protected void assertValid(EntitlementDefinition entitlementDefinition) {
		boolean valid = true;

		if (entitlementDefinition.getDateCreated() == null) {
			valid = false;
		}

		if (entitlementDefinition.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("definition", additionalAssertFieldName)) {
				if (entitlementDefinition.getDefinition() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (entitlementDefinition.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (entitlementDefinition.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (entitlementDefinition.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (entitlementDefinition.getStatus() == null) {
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

	protected void assertValid(Page<EntitlementDefinition> page) {
		boolean valid = false;

		java.util.Collection<EntitlementDefinition> entitlementDefinitions =
			page.getItems();

		int size = entitlementDefinitions.size();

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

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		EntitlementDefinition entitlementDefinition1,
		EntitlementDefinition entitlementDefinition2) {

		if (entitlementDefinition1 == entitlementDefinition2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDateCreated(),
						entitlementDefinition2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDateModified(),
						entitlementDefinition2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("definition", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDefinition(),
						entitlementDefinition2.getDefinition())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDescription(),
						entitlementDefinition2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getKey(),
						entitlementDefinition2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getName(),
						entitlementDefinition2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getStatus(),
						entitlementDefinition2.getStatus())) {

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

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_entitlementDefinitionResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_entitlementDefinitionResource;

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
		EntitlementDefinition entitlementDefinition) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							entitlementDefinition.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							entitlementDefinition.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(entitlementDefinition.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							entitlementDefinition.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							entitlementDefinition.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(
						entitlementDefinition.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("definition")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getDefinition()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("status")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected EntitlementDefinition randomEntitlementDefinition()
		throws Exception {

		return new EntitlementDefinition() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				definition = RandomTestUtil.randomString();
				description = RandomTestUtil.randomString();
				key = RandomTestUtil.randomString();
				name = RandomTestUtil.randomString();
			}
		};
	}

	protected EntitlementDefinition randomIrrelevantEntitlementDefinition()
		throws Exception {

		EntitlementDefinition randomIrrelevantEntitlementDefinition =
			randomEntitlementDefinition();

		return randomIrrelevantEntitlementDefinition;
	}

	protected EntitlementDefinition randomPatchEntitlementDefinition()
		throws Exception {

		return randomEntitlementDefinition();
	}

	protected EntitlementDefinitionResource entitlementDefinitionResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseEntitlementDefinitionResourceTestCase.class);

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
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.
		EntitlementDefinitionResource _entitlementDefinitionResource;

}