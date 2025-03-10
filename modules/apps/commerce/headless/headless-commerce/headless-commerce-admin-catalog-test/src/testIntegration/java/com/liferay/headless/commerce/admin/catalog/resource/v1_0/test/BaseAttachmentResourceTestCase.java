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

import com.liferay.headless.commerce.admin.catalog.client.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.client.http.HttpInvoker;
import com.liferay.headless.commerce.admin.catalog.client.pagination.Page;
import com.liferay.headless.commerce.admin.catalog.client.pagination.Pagination;
import com.liferay.headless.commerce.admin.catalog.client.resource.v1_0.AttachmentResource;
import com.liferay.headless.commerce.admin.catalog.client.serdes.v1_0.AttachmentSerDes;
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
import org.apache.commons.lang.time.DateUtils;

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
public abstract class BaseAttachmentResourceTestCase {

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

		_attachmentResource.setContextCompany(testCompany);

		AttachmentResource.Builder builder = AttachmentResource.builder();

		attachmentResource = builder.authentication(
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

		Attachment attachment1 = randomAttachment();

		String json = objectMapper.writeValueAsString(attachment1);

		Attachment attachment2 = AttachmentSerDes.toDTO(json);

		Assert.assertTrue(equals(attachment1, attachment2));
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

		Attachment attachment = randomAttachment();

		String json1 = objectMapper.writeValueAsString(attachment);
		String json2 = AttachmentSerDes.toJSON(attachment);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Attachment attachment = randomAttachment();

		attachment.setAttachment(regex);
		attachment.setExternalReferenceCode(regex);
		attachment.setSrc(regex);

		String json = AttachmentSerDes.toJSON(attachment);

		Assert.assertFalse(json.contains(regex));

		attachment = AttachmentSerDes.toDTO(json);

		Assert.assertEquals(regex, attachment.getAttachment());
		Assert.assertEquals(regex, attachment.getExternalReferenceCode());
		Assert.assertEquals(regex, attachment.getSrc());
	}

	@Test
	public void testGetProductByExternalReferenceCodeAttachmentsPage()
		throws Exception {

		Page<Attachment> page =
			attachmentResource.getProductByExternalReferenceCodeAttachmentsPage(
				testGetProductByExternalReferenceCodeAttachmentsPage_getExternalReferenceCode(),
				Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		String externalReferenceCode =
			testGetProductByExternalReferenceCodeAttachmentsPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetProductByExternalReferenceCodeAttachmentsPage_getIrrelevantExternalReferenceCode();

		if ((irrelevantExternalReferenceCode != null)) {
			Attachment irrelevantAttachment =
				testGetProductByExternalReferenceCodeAttachmentsPage_addAttachment(
					irrelevantExternalReferenceCode,
					randomIrrelevantAttachment());

			page =
				attachmentResource.
					getProductByExternalReferenceCodeAttachmentsPage(
						irrelevantExternalReferenceCode, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantAttachment),
				(List<Attachment>)page.getItems());
			assertValid(page);
		}

		Attachment attachment1 =
			testGetProductByExternalReferenceCodeAttachmentsPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Attachment attachment2 =
			testGetProductByExternalReferenceCodeAttachmentsPage_addAttachment(
				externalReferenceCode, randomAttachment());

		page =
			attachmentResource.getProductByExternalReferenceCodeAttachmentsPage(
				externalReferenceCode, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2),
			(List<Attachment>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductByExternalReferenceCodeAttachmentsPageWithPagination()
		throws Exception {

		String externalReferenceCode =
			testGetProductByExternalReferenceCodeAttachmentsPage_getExternalReferenceCode();

		Attachment attachment1 =
			testGetProductByExternalReferenceCodeAttachmentsPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Attachment attachment2 =
			testGetProductByExternalReferenceCodeAttachmentsPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Attachment attachment3 =
			testGetProductByExternalReferenceCodeAttachmentsPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Page<Attachment> page1 =
			attachmentResource.getProductByExternalReferenceCodeAttachmentsPage(
				externalReferenceCode, Pagination.of(1, 2));

		List<Attachment> attachments1 = (List<Attachment>)page1.getItems();

		Assert.assertEquals(attachments1.toString(), 2, attachments1.size());

		Page<Attachment> page2 =
			attachmentResource.getProductByExternalReferenceCodeAttachmentsPage(
				externalReferenceCode, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Attachment> attachments2 = (List<Attachment>)page2.getItems();

		Assert.assertEquals(attachments2.toString(), 1, attachments2.size());

		Page<Attachment> page3 =
			attachmentResource.getProductByExternalReferenceCodeAttachmentsPage(
				externalReferenceCode, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2, attachment3),
			(List<Attachment>)page3.getItems());
	}

	protected Attachment
			testGetProductByExternalReferenceCodeAttachmentsPage_addAttachment(
				String externalReferenceCode, Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalReferenceCodeAttachmentsPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalReferenceCodeAttachmentsPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductByExternalReferenceCodeAttachment()
		throws Exception {

		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductByExternalReferenceCodeAttachment_addAttachment(
				randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment
			testPostProductByExternalReferenceCodeAttachment_addAttachment(
				Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductByExternalReferenceCodeAttachmentByBase64()
		throws Exception {

		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductByExternalReferenceCodeAttachmentByBase64_addAttachment(
				randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment
			testPostProductByExternalReferenceCodeAttachmentByBase64_addAttachment(
				Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductByExternalReferenceCodeAttachmentByUrl()
		throws Exception {

		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductByExternalReferenceCodeAttachmentByUrl_addAttachment(
				randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment
			testPostProductByExternalReferenceCodeAttachmentByUrl_addAttachment(
				Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetProductByExternalReferenceCodeImagesPage()
		throws Exception {

		Page<Attachment> page =
			attachmentResource.getProductByExternalReferenceCodeImagesPage(
				testGetProductByExternalReferenceCodeImagesPage_getExternalReferenceCode(),
				Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		String externalReferenceCode =
			testGetProductByExternalReferenceCodeImagesPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetProductByExternalReferenceCodeImagesPage_getIrrelevantExternalReferenceCode();

		if ((irrelevantExternalReferenceCode != null)) {
			Attachment irrelevantAttachment =
				testGetProductByExternalReferenceCodeImagesPage_addAttachment(
					irrelevantExternalReferenceCode,
					randomIrrelevantAttachment());

			page =
				attachmentResource.getProductByExternalReferenceCodeImagesPage(
					irrelevantExternalReferenceCode, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantAttachment),
				(List<Attachment>)page.getItems());
			assertValid(page);
		}

		Attachment attachment1 =
			testGetProductByExternalReferenceCodeImagesPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Attachment attachment2 =
			testGetProductByExternalReferenceCodeImagesPage_addAttachment(
				externalReferenceCode, randomAttachment());

		page = attachmentResource.getProductByExternalReferenceCodeImagesPage(
			externalReferenceCode, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2),
			(List<Attachment>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductByExternalReferenceCodeImagesPageWithPagination()
		throws Exception {

		String externalReferenceCode =
			testGetProductByExternalReferenceCodeImagesPage_getExternalReferenceCode();

		Attachment attachment1 =
			testGetProductByExternalReferenceCodeImagesPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Attachment attachment2 =
			testGetProductByExternalReferenceCodeImagesPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Attachment attachment3 =
			testGetProductByExternalReferenceCodeImagesPage_addAttachment(
				externalReferenceCode, randomAttachment());

		Page<Attachment> page1 =
			attachmentResource.getProductByExternalReferenceCodeImagesPage(
				externalReferenceCode, Pagination.of(1, 2));

		List<Attachment> attachments1 = (List<Attachment>)page1.getItems();

		Assert.assertEquals(attachments1.toString(), 2, attachments1.size());

		Page<Attachment> page2 =
			attachmentResource.getProductByExternalReferenceCodeImagesPage(
				externalReferenceCode, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Attachment> attachments2 = (List<Attachment>)page2.getItems();

		Assert.assertEquals(attachments2.toString(), 1, attachments2.size());

		Page<Attachment> page3 =
			attachmentResource.getProductByExternalReferenceCodeImagesPage(
				externalReferenceCode, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2, attachment3),
			(List<Attachment>)page3.getItems());
	}

	protected Attachment
			testGetProductByExternalReferenceCodeImagesPage_addAttachment(
				String externalReferenceCode, Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalReferenceCodeImagesPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalReferenceCodeImagesPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductByExternalReferenceCodeImage() throws Exception {
		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductByExternalReferenceCodeImage_addAttachment(
				randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment
			testPostProductByExternalReferenceCodeImage_addAttachment(
				Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductByExternalReferenceCodeImageByBase64()
		throws Exception {

		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductByExternalReferenceCodeImageByBase64_addAttachment(
				randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment
			testPostProductByExternalReferenceCodeImageByBase64_addAttachment(
				Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductByExternalReferenceCodeImageByUrl()
		throws Exception {

		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductByExternalReferenceCodeImageByUrl_addAttachment(
				randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment
			testPostProductByExternalReferenceCodeImageByUrl_addAttachment(
				Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetProductIdAttachmentsPage() throws Exception {
		Page<Attachment> page = attachmentResource.getProductIdAttachmentsPage(
			testGetProductIdAttachmentsPage_getId(), Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		Long id = testGetProductIdAttachmentsPage_getId();
		Long irrelevantId = testGetProductIdAttachmentsPage_getIrrelevantId();

		if ((irrelevantId != null)) {
			Attachment irrelevantAttachment =
				testGetProductIdAttachmentsPage_addAttachment(
					irrelevantId, randomIrrelevantAttachment());

			page = attachmentResource.getProductIdAttachmentsPage(
				irrelevantId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantAttachment),
				(List<Attachment>)page.getItems());
			assertValid(page);
		}

		Attachment attachment1 = testGetProductIdAttachmentsPage_addAttachment(
			id, randomAttachment());

		Attachment attachment2 = testGetProductIdAttachmentsPage_addAttachment(
			id, randomAttachment());

		page = attachmentResource.getProductIdAttachmentsPage(
			id, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2),
			(List<Attachment>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductIdAttachmentsPageWithPagination()
		throws Exception {

		Long id = testGetProductIdAttachmentsPage_getId();

		Attachment attachment1 = testGetProductIdAttachmentsPage_addAttachment(
			id, randomAttachment());

		Attachment attachment2 = testGetProductIdAttachmentsPage_addAttachment(
			id, randomAttachment());

		Attachment attachment3 = testGetProductIdAttachmentsPage_addAttachment(
			id, randomAttachment());

		Page<Attachment> page1 = attachmentResource.getProductIdAttachmentsPage(
			id, Pagination.of(1, 2));

		List<Attachment> attachments1 = (List<Attachment>)page1.getItems();

		Assert.assertEquals(attachments1.toString(), 2, attachments1.size());

		Page<Attachment> page2 = attachmentResource.getProductIdAttachmentsPage(
			id, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Attachment> attachments2 = (List<Attachment>)page2.getItems();

		Assert.assertEquals(attachments2.toString(), 1, attachments2.size());

		Page<Attachment> page3 = attachmentResource.getProductIdAttachmentsPage(
			id, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2, attachment3),
			(List<Attachment>)page3.getItems());
	}

	protected Attachment testGetProductIdAttachmentsPage_addAttachment(
			Long id, Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetProductIdAttachmentsPage_getId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetProductIdAttachmentsPage_getIrrelevantId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductIdAttachment() throws Exception {
		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment = testPostProductIdAttachment_addAttachment(
			randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment testPostProductIdAttachment_addAttachment(
			Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductIdAttachmentByBase64() throws Exception {
		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductIdAttachmentByBase64_addAttachment(randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment testPostProductIdAttachmentByBase64_addAttachment(
			Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductIdAttachmentByUrl() throws Exception {
		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductIdAttachmentByUrl_addAttachment(randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment testPostProductIdAttachmentByUrl_addAttachment(
			Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetProductIdImagesPage() throws Exception {
		Page<Attachment> page = attachmentResource.getProductIdImagesPage(
			testGetProductIdImagesPage_getId(), Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		Long id = testGetProductIdImagesPage_getId();
		Long irrelevantId = testGetProductIdImagesPage_getIrrelevantId();

		if ((irrelevantId != null)) {
			Attachment irrelevantAttachment =
				testGetProductIdImagesPage_addAttachment(
					irrelevantId, randomIrrelevantAttachment());

			page = attachmentResource.getProductIdImagesPage(
				irrelevantId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantAttachment),
				(List<Attachment>)page.getItems());
			assertValid(page);
		}

		Attachment attachment1 = testGetProductIdImagesPage_addAttachment(
			id, randomAttachment());

		Attachment attachment2 = testGetProductIdImagesPage_addAttachment(
			id, randomAttachment());

		page = attachmentResource.getProductIdImagesPage(
			id, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2),
			(List<Attachment>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductIdImagesPageWithPagination() throws Exception {
		Long id = testGetProductIdImagesPage_getId();

		Attachment attachment1 = testGetProductIdImagesPage_addAttachment(
			id, randomAttachment());

		Attachment attachment2 = testGetProductIdImagesPage_addAttachment(
			id, randomAttachment());

		Attachment attachment3 = testGetProductIdImagesPage_addAttachment(
			id, randomAttachment());

		Page<Attachment> page1 = attachmentResource.getProductIdImagesPage(
			id, Pagination.of(1, 2));

		List<Attachment> attachments1 = (List<Attachment>)page1.getItems();

		Assert.assertEquals(attachments1.toString(), 2, attachments1.size());

		Page<Attachment> page2 = attachmentResource.getProductIdImagesPage(
			id, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Attachment> attachments2 = (List<Attachment>)page2.getItems();

		Assert.assertEquals(attachments2.toString(), 1, attachments2.size());

		Page<Attachment> page3 = attachmentResource.getProductIdImagesPage(
			id, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2, attachment3),
			(List<Attachment>)page3.getItems());
	}

	protected Attachment testGetProductIdImagesPage_addAttachment(
			Long id, Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetProductIdImagesPage_getId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetProductIdImagesPage_getIrrelevantId()
		throws Exception {

		return null;
	}

	@Test
	public void testPostProductIdImage() throws Exception {
		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment = testPostProductIdImage_addAttachment(
			randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment testPostProductIdImage_addAttachment(
			Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductIdImageByBase64() throws Exception {
		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment =
			testPostProductIdImageByBase64_addAttachment(randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment testPostProductIdImageByBase64_addAttachment(
			Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostProductIdImageByUrl() throws Exception {
		Attachment randomAttachment = randomAttachment();

		Attachment postAttachment = testPostProductIdImageByUrl_addAttachment(
			randomAttachment);

		assertEquals(randomAttachment, postAttachment);
		assertValid(postAttachment);
	}

	protected Attachment testPostProductIdImageByUrl_addAttachment(
			Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Attachment testGraphQLAttachment_addAttachment()
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
		Attachment attachment1, Attachment attachment2) {

		Assert.assertTrue(
			attachment1 + " does not equal " + attachment2,
			equals(attachment1, attachment2));
	}

	protected void assertEquals(
		List<Attachment> attachments1, List<Attachment> attachments2) {

		Assert.assertEquals(attachments1.size(), attachments2.size());

		for (int i = 0; i < attachments1.size(); i++) {
			Attachment attachment1 = attachments1.get(i);
			Attachment attachment2 = attachments2.get(i);

			assertEquals(attachment1, attachment2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Attachment> attachments1, List<Attachment> attachments2) {

		Assert.assertEquals(attachments1.size(), attachments2.size());

		for (Attachment attachment1 : attachments1) {
			boolean contains = false;

			for (Attachment attachment2 : attachments2) {
				if (equals(attachment1, attachment2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				attachments2 + " does not contain " + attachment1, contains);
		}
	}

	protected void assertValid(Attachment attachment) throws Exception {
		boolean valid = true;

		if (attachment.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("attachment", additionalAssertFieldName)) {
				if (attachment.getAttachment() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("displayDate", additionalAssertFieldName)) {
				if (attachment.getDisplayDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (attachment.getExpirationDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (attachment.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("neverExpire", additionalAssertFieldName)) {
				if (attachment.getNeverExpire() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("options", additionalAssertFieldName)) {
				if (attachment.getOptions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (attachment.getPriority() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("src", additionalAssertFieldName)) {
				if (attachment.getSrc() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (attachment.getTitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (attachment.getType() == null) {
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

	protected void assertValid(Page<Attachment> page) {
		boolean valid = false;

		java.util.Collection<Attachment> attachments = page.getItems();

		int size = attachments.size();

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
						Attachment.class)) {

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

	protected boolean equals(Attachment attachment1, Attachment attachment2) {
		if (attachment1 == attachment2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("attachment", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getAttachment(),
						attachment2.getAttachment())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("displayDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getDisplayDate(),
						attachment2.getDisplayDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getExpirationDate(),
						attachment2.getExpirationDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						attachment1.getExternalReferenceCode(),
						attachment2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getId(), attachment2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("neverExpire", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getNeverExpire(),
						attachment2.getNeverExpire())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("options", additionalAssertFieldName)) {
				if (!equals(
						(Map)attachment1.getOptions(),
						(Map)attachment2.getOptions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getPriority(), attachment2.getPriority())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("src", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getSrc(), attachment2.getSrc())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (!equals(
						(Map)attachment1.getTitle(),
						(Map)attachment2.getTitle())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getType(), attachment2.getType())) {

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

		if (!(_attachmentResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_attachmentResource;

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
		EntityField entityField, String operator, Attachment attachment) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("attachment")) {
			sb.append("'");
			sb.append(String.valueOf(attachment.getAttachment()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("displayDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(attachment.getDisplayDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(attachment.getDisplayDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(attachment.getDisplayDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("expirationDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							attachment.getExpirationDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							attachment.getExpirationDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(attachment.getExpirationDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("externalReferenceCode")) {
			sb.append("'");
			sb.append(String.valueOf(attachment.getExternalReferenceCode()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("neverExpire")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("options")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("priority")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("src")) {
			sb.append("'");
			sb.append(String.valueOf(attachment.getSrc()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("title")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("type")) {
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

	protected Attachment randomAttachment() throws Exception {
		return new Attachment() {
			{
				attachment = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				displayDate = RandomTestUtil.nextDate();
				expirationDate = RandomTestUtil.nextDate();
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				neverExpire = RandomTestUtil.randomBoolean();
				priority = RandomTestUtil.randomDouble();
				src = StringUtil.toLowerCase(RandomTestUtil.randomString());
				type = RandomTestUtil.randomInt();
			}
		};
	}

	protected Attachment randomIrrelevantAttachment() throws Exception {
		Attachment randomIrrelevantAttachment = randomAttachment();

		return randomIrrelevantAttachment;
	}

	protected Attachment randomPatchAttachment() throws Exception {
		return randomAttachment();
	}

	protected AttachmentResource attachmentResource;
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
		BaseAttachmentResourceTestCase.class);

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
	private
		com.liferay.headless.commerce.admin.catalog.resource.v1_0.
			AttachmentResource _attachmentResource;

}