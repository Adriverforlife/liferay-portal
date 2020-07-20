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

package com.liferay.layout.page.template.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LayoutPageTemplateCollection service. Represents a row in the &quot;LayoutPageTemplateCollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateCollectionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateCollectionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateCollection
 * @generated
 */
@ProviderType
public interface LayoutPageTemplateCollectionModel
	extends BaseModel<LayoutPageTemplateCollection>,
			CTModel<LayoutPageTemplateCollection>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout page template collection model instance should use the {@link LayoutPageTemplateCollection} interface instead.
	 */

	/**
	 * Returns the primary key of this layout page template collection.
	 *
	 * @return the primary key of this layout page template collection
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout page template collection.
	 *
	 * @param primaryKey the primary key of this layout page template collection
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this layout page template collection.
	 *
	 * @return the mvcc version of this layout page template collection
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this layout page template collection.
	 *
	 * @param mvccVersion the mvcc version of this layout page template collection
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this layout page template collection.
	 *
	 * @return the ct collection ID of this layout page template collection
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this layout page template collection.
	 *
	 * @param ctCollectionId the ct collection ID of this layout page template collection
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this layout page template collection.
	 *
	 * @return the uuid of this layout page template collection
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this layout page template collection.
	 *
	 * @param uuid the uuid of this layout page template collection
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the layout page template collection ID of this layout page template collection.
	 *
	 * @return the layout page template collection ID of this layout page template collection
	 */
	public long getLayoutPageTemplateCollectionId();

	/**
	 * Sets the layout page template collection ID of this layout page template collection.
	 *
	 * @param layoutPageTemplateCollectionId the layout page template collection ID of this layout page template collection
	 */
	public void setLayoutPageTemplateCollectionId(
		long layoutPageTemplateCollectionId);

	/**
	 * Returns the group ID of this layout page template collection.
	 *
	 * @return the group ID of this layout page template collection
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this layout page template collection.
	 *
	 * @param groupId the group ID of this layout page template collection
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout page template collection.
	 *
	 * @return the company ID of this layout page template collection
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout page template collection.
	 *
	 * @param companyId the company ID of this layout page template collection
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this layout page template collection.
	 *
	 * @return the user ID of this layout page template collection
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this layout page template collection.
	 *
	 * @param userId the user ID of this layout page template collection
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this layout page template collection.
	 *
	 * @return the user uuid of this layout page template collection
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this layout page template collection.
	 *
	 * @param userUuid the user uuid of this layout page template collection
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this layout page template collection.
	 *
	 * @return the user name of this layout page template collection
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this layout page template collection.
	 *
	 * @param userName the user name of this layout page template collection
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this layout page template collection.
	 *
	 * @return the create date of this layout page template collection
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout page template collection.
	 *
	 * @param createDate the create date of this layout page template collection
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout page template collection.
	 *
	 * @return the modified date of this layout page template collection
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout page template collection.
	 *
	 * @param modifiedDate the modified date of this layout page template collection
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the layout page template collection key of this layout page template collection.
	 *
	 * @return the layout page template collection key of this layout page template collection
	 */
	@AutoEscape
	public String getLayoutPageTemplateCollectionKey();

	/**
	 * Sets the layout page template collection key of this layout page template collection.
	 *
	 * @param layoutPageTemplateCollectionKey the layout page template collection key of this layout page template collection
	 */
	public void setLayoutPageTemplateCollectionKey(
		String layoutPageTemplateCollectionKey);

	/**
	 * Returns the name of this layout page template collection.
	 *
	 * @return the name of this layout page template collection
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this layout page template collection.
	 *
	 * @param name the name of this layout page template collection
	 */
	public void setName(String name);

	/**
	 * Returns the description of this layout page template collection.
	 *
	 * @return the description of this layout page template collection
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this layout page template collection.
	 *
	 * @param description the description of this layout page template collection
	 */
	public void setDescription(String description);

	/**
	 * Returns the last publish date of this layout page template collection.
	 *
	 * @return the last publish date of this layout page template collection
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this layout page template collection.
	 *
	 * @param lastPublishDate the last publish date of this layout page template collection
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

}