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

package com.liferay.commerce.tax.engine.fixed.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceTaxFixedRate service. Represents a row in the &quot;CommerceTaxFixedRate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.tax.engine.fixed.model.impl.CommerceTaxFixedRateModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.tax.engine.fixed.model.impl.CommerceTaxFixedRateImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTaxFixedRate
 * @see com.liferay.commerce.tax.engine.fixed.model.impl.CommerceTaxFixedRateImpl
 * @see com.liferay.commerce.tax.engine.fixed.model.impl.CommerceTaxFixedRateModelImpl
 * @generated
 */
@ProviderType
public interface CommerceTaxFixedRateModel extends BaseModel<CommerceTaxFixedRate>,
	GroupedModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce tax fixed rate model instance should use the {@link CommerceTaxFixedRate} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce tax fixed rate.
	 *
	 * @return the primary key of this commerce tax fixed rate
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce tax fixed rate.
	 *
	 * @param primaryKey the primary key of this commerce tax fixed rate
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce tax fixed rate ID of this commerce tax fixed rate.
	 *
	 * @return the commerce tax fixed rate ID of this commerce tax fixed rate
	 */
	public long getCommerceTaxFixedRateId();

	/**
	 * Sets the commerce tax fixed rate ID of this commerce tax fixed rate.
	 *
	 * @param commerceTaxFixedRateId the commerce tax fixed rate ID of this commerce tax fixed rate
	 */
	public void setCommerceTaxFixedRateId(long commerceTaxFixedRateId);

	/**
	 * Returns the group ID of this commerce tax fixed rate.
	 *
	 * @return the group ID of this commerce tax fixed rate
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce tax fixed rate.
	 *
	 * @param groupId the group ID of this commerce tax fixed rate
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce tax fixed rate.
	 *
	 * @return the company ID of this commerce tax fixed rate
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce tax fixed rate.
	 *
	 * @param companyId the company ID of this commerce tax fixed rate
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce tax fixed rate.
	 *
	 * @return the user ID of this commerce tax fixed rate
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce tax fixed rate.
	 *
	 * @param userId the user ID of this commerce tax fixed rate
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce tax fixed rate.
	 *
	 * @return the user uuid of this commerce tax fixed rate
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce tax fixed rate.
	 *
	 * @param userUuid the user uuid of this commerce tax fixed rate
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce tax fixed rate.
	 *
	 * @return the user name of this commerce tax fixed rate
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce tax fixed rate.
	 *
	 * @param userName the user name of this commerce tax fixed rate
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce tax fixed rate.
	 *
	 * @return the create date of this commerce tax fixed rate
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce tax fixed rate.
	 *
	 * @param createDate the create date of this commerce tax fixed rate
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce tax fixed rate.
	 *
	 * @return the modified date of this commerce tax fixed rate
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce tax fixed rate.
	 *
	 * @param modifiedDate the modified date of this commerce tax fixed rate
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp tax category ID of this commerce tax fixed rate.
	 *
	 * @return the cp tax category ID of this commerce tax fixed rate
	 */
	public long getCPTaxCategoryId();

	/**
	 * Sets the cp tax category ID of this commerce tax fixed rate.
	 *
	 * @param CPTaxCategoryId the cp tax category ID of this commerce tax fixed rate
	 */
	public void setCPTaxCategoryId(long CPTaxCategoryId);

	/**
	 * Returns the commerce tax method ID of this commerce tax fixed rate.
	 *
	 * @return the commerce tax method ID of this commerce tax fixed rate
	 */
	public long getCommerceTaxMethodId();

	/**
	 * Sets the commerce tax method ID of this commerce tax fixed rate.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID of this commerce tax fixed rate
	 */
	public void setCommerceTaxMethodId(long commerceTaxMethodId);

	/**
	 * Returns the rate of this commerce tax fixed rate.
	 *
	 * @return the rate of this commerce tax fixed rate
	 */
	public double getRate();

	/**
	 * Sets the rate of this commerce tax fixed rate.
	 *
	 * @param rate the rate of this commerce tax fixed rate
	 */
	public void setRate(double rate);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(CommerceTaxFixedRate commerceTaxFixedRate);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceTaxFixedRate> toCacheModel();

	@Override
	public CommerceTaxFixedRate toEscapedModel();

	@Override
	public CommerceTaxFixedRate toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}