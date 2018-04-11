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

package com.liferay.commerce.tax.engine.fixed.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate;
import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateModel;
import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the CommerceTaxFixedRate service. Represents a row in the &quot;CommerceTaxFixedRate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceTaxFixedRateModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceTaxFixedRateImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTaxFixedRateImpl
 * @see CommerceTaxFixedRate
 * @see CommerceTaxFixedRateModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceTaxFixedRateModelImpl extends BaseModelImpl<CommerceTaxFixedRate>
	implements CommerceTaxFixedRateModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce tax fixed rate model instance should use the {@link CommerceTaxFixedRate} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceTaxFixedRate";
	public static final Object[][] TABLE_COLUMNS = {
			{ "commerceTaxFixedRateId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "CPTaxCategoryId", Types.BIGINT },
			{ "commerceTaxMethodId", Types.BIGINT },
			{ "rate", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceTaxFixedRateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPTaxCategoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceTaxMethodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("rate", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceTaxFixedRate (commerceTaxFixedRateId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPTaxCategoryId LONG,commerceTaxMethodId LONG,rate DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table CommerceTaxFixedRate";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceTaxFixedRate.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceTaxFixedRate.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.tax.engine.fixed.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.tax.engine.fixed.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.tax.engine.fixed.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate"),
			true);
	public static final long CPTAXCATEGORYID_COLUMN_BITMASK = 1L;
	public static final long COMMERCETAXMETHODID_COLUMN_BITMASK = 2L;
	public static final long CREATEDATE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceTaxFixedRate toModel(
		CommerceTaxFixedRateSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceTaxFixedRate model = new CommerceTaxFixedRateImpl();

		model.setCommerceTaxFixedRateId(soapModel.getCommerceTaxFixedRateId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCPTaxCategoryId(soapModel.getCPTaxCategoryId());
		model.setCommerceTaxMethodId(soapModel.getCommerceTaxMethodId());
		model.setRate(soapModel.getRate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceTaxFixedRate> toModels(
		CommerceTaxFixedRateSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceTaxFixedRate> models = new ArrayList<CommerceTaxFixedRate>(soapModels.length);

		for (CommerceTaxFixedRateSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.tax.engine.fixed.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate"));

	public CommerceTaxFixedRateModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceTaxFixedRateId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceTaxFixedRateId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceTaxFixedRateId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceTaxFixedRate.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceTaxFixedRate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceTaxFixedRateId", getCommerceTaxFixedRateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPTaxCategoryId", getCPTaxCategoryId());
		attributes.put("commerceTaxMethodId", getCommerceTaxMethodId());
		attributes.put("rate", getRate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceTaxFixedRateId = (Long)attributes.get(
				"commerceTaxFixedRateId");

		if (commerceTaxFixedRateId != null) {
			setCommerceTaxFixedRateId(commerceTaxFixedRateId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long CPTaxCategoryId = (Long)attributes.get("CPTaxCategoryId");

		if (CPTaxCategoryId != null) {
			setCPTaxCategoryId(CPTaxCategoryId);
		}

		Long commerceTaxMethodId = (Long)attributes.get("commerceTaxMethodId");

		if (commerceTaxMethodId != null) {
			setCommerceTaxMethodId(commerceTaxMethodId);
		}

		Double rate = (Double)attributes.get("rate");

		if (rate != null) {
			setRate(rate);
		}
	}

	@JSON
	@Override
	public long getCommerceTaxFixedRateId() {
		return _commerceTaxFixedRateId;
	}

	@Override
	public void setCommerceTaxFixedRateId(long commerceTaxFixedRateId) {
		_commerceTaxFixedRateId = commerceTaxFixedRateId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
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
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCPTaxCategoryId() {
		return _CPTaxCategoryId;
	}

	@Override
	public void setCPTaxCategoryId(long CPTaxCategoryId) {
		_columnBitmask |= CPTAXCATEGORYID_COLUMN_BITMASK;

		if (!_setOriginalCPTaxCategoryId) {
			_setOriginalCPTaxCategoryId = true;

			_originalCPTaxCategoryId = _CPTaxCategoryId;
		}

		_CPTaxCategoryId = CPTaxCategoryId;
	}

	public long getOriginalCPTaxCategoryId() {
		return _originalCPTaxCategoryId;
	}

	@JSON
	@Override
	public long getCommerceTaxMethodId() {
		return _commerceTaxMethodId;
	}

	@Override
	public void setCommerceTaxMethodId(long commerceTaxMethodId) {
		_columnBitmask |= COMMERCETAXMETHODID_COLUMN_BITMASK;

		if (!_setOriginalCommerceTaxMethodId) {
			_setOriginalCommerceTaxMethodId = true;

			_originalCommerceTaxMethodId = _commerceTaxMethodId;
		}

		_commerceTaxMethodId = commerceTaxMethodId;
	}

	public long getOriginalCommerceTaxMethodId() {
		return _originalCommerceTaxMethodId;
	}

	@JSON
	@Override
	public double getRate() {
		return _rate;
	}

	@Override
	public void setRate(double rate) {
		_rate = rate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceTaxFixedRate.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceTaxFixedRate toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceTaxFixedRate)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceTaxFixedRateImpl commerceTaxFixedRateImpl = new CommerceTaxFixedRateImpl();

		commerceTaxFixedRateImpl.setCommerceTaxFixedRateId(getCommerceTaxFixedRateId());
		commerceTaxFixedRateImpl.setGroupId(getGroupId());
		commerceTaxFixedRateImpl.setCompanyId(getCompanyId());
		commerceTaxFixedRateImpl.setUserId(getUserId());
		commerceTaxFixedRateImpl.setUserName(getUserName());
		commerceTaxFixedRateImpl.setCreateDate(getCreateDate());
		commerceTaxFixedRateImpl.setModifiedDate(getModifiedDate());
		commerceTaxFixedRateImpl.setCPTaxCategoryId(getCPTaxCategoryId());
		commerceTaxFixedRateImpl.setCommerceTaxMethodId(getCommerceTaxMethodId());
		commerceTaxFixedRateImpl.setRate(getRate());

		commerceTaxFixedRateImpl.resetOriginalValues();

		return commerceTaxFixedRateImpl;
	}

	@Override
	public int compareTo(CommerceTaxFixedRate commerceTaxFixedRate) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				commerceTaxFixedRate.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceTaxFixedRate)) {
			return false;
		}

		CommerceTaxFixedRate commerceTaxFixedRate = (CommerceTaxFixedRate)obj;

		long primaryKey = commerceTaxFixedRate.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceTaxFixedRateModelImpl commerceTaxFixedRateModelImpl = this;

		commerceTaxFixedRateModelImpl._setModifiedDate = false;

		commerceTaxFixedRateModelImpl._originalCPTaxCategoryId = commerceTaxFixedRateModelImpl._CPTaxCategoryId;

		commerceTaxFixedRateModelImpl._setOriginalCPTaxCategoryId = false;

		commerceTaxFixedRateModelImpl._originalCommerceTaxMethodId = commerceTaxFixedRateModelImpl._commerceTaxMethodId;

		commerceTaxFixedRateModelImpl._setOriginalCommerceTaxMethodId = false;

		commerceTaxFixedRateModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceTaxFixedRate> toCacheModel() {
		CommerceTaxFixedRateCacheModel commerceTaxFixedRateCacheModel = new CommerceTaxFixedRateCacheModel();

		commerceTaxFixedRateCacheModel.commerceTaxFixedRateId = getCommerceTaxFixedRateId();

		commerceTaxFixedRateCacheModel.groupId = getGroupId();

		commerceTaxFixedRateCacheModel.companyId = getCompanyId();

		commerceTaxFixedRateCacheModel.userId = getUserId();

		commerceTaxFixedRateCacheModel.userName = getUserName();

		String userName = commerceTaxFixedRateCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceTaxFixedRateCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceTaxFixedRateCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceTaxFixedRateCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceTaxFixedRateCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceTaxFixedRateCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceTaxFixedRateCacheModel.CPTaxCategoryId = getCPTaxCategoryId();

		commerceTaxFixedRateCacheModel.commerceTaxMethodId = getCommerceTaxMethodId();

		commerceTaxFixedRateCacheModel.rate = getRate();

		return commerceTaxFixedRateCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{commerceTaxFixedRateId=");
		sb.append(getCommerceTaxFixedRateId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", CPTaxCategoryId=");
		sb.append(getCPTaxCategoryId());
		sb.append(", commerceTaxMethodId=");
		sb.append(getCommerceTaxMethodId());
		sb.append(", rate=");
		sb.append(getRate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceTaxFixedRateId</column-name><column-value><![CDATA[");
		sb.append(getCommerceTaxFixedRateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>CPTaxCategoryId</column-name><column-value><![CDATA[");
		sb.append(getCPTaxCategoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceTaxMethodId</column-name><column-value><![CDATA[");
		sb.append(getCommerceTaxMethodId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rate</column-name><column-value><![CDATA[");
		sb.append(getRate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceTaxFixedRate.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceTaxFixedRate.class
		};
	private long _commerceTaxFixedRateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPTaxCategoryId;
	private long _originalCPTaxCategoryId;
	private boolean _setOriginalCPTaxCategoryId;
	private long _commerceTaxMethodId;
	private long _originalCommerceTaxMethodId;
	private boolean _setOriginalCommerceTaxMethodId;
	private double _rate;
	private long _columnBitmask;
	private CommerceTaxFixedRate _escapedModel;
}