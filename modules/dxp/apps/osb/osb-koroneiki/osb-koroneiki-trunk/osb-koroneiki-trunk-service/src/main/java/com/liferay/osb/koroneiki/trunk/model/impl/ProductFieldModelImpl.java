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

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductFieldModel;
import com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the ProductField service. Represents a row in the &quot;Koroneiki_ProductField&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ProductFieldModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductFieldImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductFieldImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ProductFieldModelImpl
	extends BaseModelImpl<ProductField> implements ProductFieldModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a product field model instance should use the <code>ProductField</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ProductField";

	public static final Object[][] TABLE_COLUMNS = {
		{"productFieldId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"productPurchaseId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"value", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("productFieldId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("productPurchaseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ProductField (productFieldId LONG not null primary key,companyId LONG,userId LONG,productPurchaseId LONG,name VARCHAR(75) null,value VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ProductField";

	public static final String ORDER_BY_JPQL =
		" ORDER BY productField.productFieldId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ProductField.productFieldId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long PRODUCTPURCHASEID_COLUMN_BITMASK = 1L;

	public static final long PRODUCTFIELDID_COLUMN_BITMASK = 2L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ProductField toModel(ProductFieldSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ProductField model = new ProductFieldImpl();

		model.setProductFieldId(soapModel.getProductFieldId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setProductPurchaseId(soapModel.getProductPurchaseId());
		model.setName(soapModel.getName());
		model.setValue(soapModel.getValue());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ProductField> toModels(ProductFieldSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ProductField> models = new ArrayList<ProductField>(
			soapModels.length);

		for (ProductFieldSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ProductFieldModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _productFieldId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProductFieldId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _productFieldId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ProductField.class;
	}

	@Override
	public String getModelClassName() {
		return ProductField.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ProductField, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ProductField, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductField, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ProductField)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ProductField, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ProductField, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ProductField)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ProductField, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ProductField, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ProductField, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ProductField, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ProductField, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ProductField, Object>>();
		Map<String, BiConsumer<ProductField, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ProductField, ?>>();

		attributeGetterFunctions.put(
			"productFieldId", ProductField::getProductFieldId);
		attributeSetterBiConsumers.put(
			"productFieldId",
			(BiConsumer<ProductField, Long>)ProductField::setProductFieldId);
		attributeGetterFunctions.put("companyId", ProductField::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ProductField, Long>)ProductField::setCompanyId);
		attributeGetterFunctions.put("userId", ProductField::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<ProductField, Long>)ProductField::setUserId);
		attributeGetterFunctions.put(
			"productPurchaseId", ProductField::getProductPurchaseId);
		attributeSetterBiConsumers.put(
			"productPurchaseId",
			(BiConsumer<ProductField, Long>)ProductField::setProductPurchaseId);
		attributeGetterFunctions.put("name", ProductField::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<ProductField, String>)ProductField::setName);
		attributeGetterFunctions.put("value", ProductField::getValue);
		attributeSetterBiConsumers.put(
			"value", (BiConsumer<ProductField, String>)ProductField::setValue);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getProductFieldId() {
		return _productFieldId;
	}

	@Override
	public void setProductFieldId(long productFieldId) {
		_productFieldId = productFieldId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public long getProductPurchaseId() {
		return _productPurchaseId;
	}

	@Override
	public void setProductPurchaseId(long productPurchaseId) {
		_columnBitmask |= PRODUCTPURCHASEID_COLUMN_BITMASK;

		if (!_setOriginalProductPurchaseId) {
			_setOriginalProductPurchaseId = true;

			_originalProductPurchaseId = _productPurchaseId;
		}

		_productPurchaseId = productPurchaseId;
	}

	public long getOriginalProductPurchaseId() {
		return _originalProductPurchaseId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getValue() {
		if (_value == null) {
			return "";
		}
		else {
			return _value;
		}
	}

	@Override
	public void setValue(String value) {
		_value = value;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ProductField.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ProductField toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ProductField)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ProductFieldImpl productFieldImpl = new ProductFieldImpl();

		productFieldImpl.setProductFieldId(getProductFieldId());
		productFieldImpl.setCompanyId(getCompanyId());
		productFieldImpl.setUserId(getUserId());
		productFieldImpl.setProductPurchaseId(getProductPurchaseId());
		productFieldImpl.setName(getName());
		productFieldImpl.setValue(getValue());

		productFieldImpl.resetOriginalValues();

		return productFieldImpl;
	}

	@Override
	public int compareTo(ProductField productField) {
		long primaryKey = productField.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProductField)) {
			return false;
		}

		ProductField productField = (ProductField)obj;

		long primaryKey = productField.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		ProductFieldModelImpl productFieldModelImpl = this;

		productFieldModelImpl._originalProductPurchaseId =
			productFieldModelImpl._productPurchaseId;

		productFieldModelImpl._setOriginalProductPurchaseId = false;

		productFieldModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ProductField> toCacheModel() {
		ProductFieldCacheModel productFieldCacheModel =
			new ProductFieldCacheModel();

		productFieldCacheModel.productFieldId = getProductFieldId();

		productFieldCacheModel.companyId = getCompanyId();

		productFieldCacheModel.userId = getUserId();

		productFieldCacheModel.productPurchaseId = getProductPurchaseId();

		productFieldCacheModel.name = getName();

		String name = productFieldCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			productFieldCacheModel.name = null;
		}

		productFieldCacheModel.value = getValue();

		String value = productFieldCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			productFieldCacheModel.value = null;
		}

		return productFieldCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ProductField, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ProductField, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductField, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ProductField)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<ProductField, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ProductField, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductField, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ProductField)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		ProductField.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		ProductField.class, ModelWrapper.class
	};
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _productFieldId;
	private long _companyId;
	private long _userId;
	private long _productPurchaseId;
	private long _originalProductPurchaseId;
	private boolean _setOriginalProductPurchaseId;
	private String _name;
	private String _value;
	private long _columnBitmask;
	private ProductField _escapedModel;

}