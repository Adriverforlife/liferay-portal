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
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LayoutPageTemplateStructureRel service. Represents a row in the &quot;LayoutPageTemplateStructureRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateStructureRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateStructureRelImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateStructureRel
 * @generated
 */
@ProviderType
public interface LayoutPageTemplateStructureRelModel
	extends BaseModel<LayoutPageTemplateStructureRel>,
			CTModel<LayoutPageTemplateStructureRel>, GroupedModel, MVCCModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout page template structure rel model instance should use the {@link LayoutPageTemplateStructureRel} interface instead.
	 */

	/**
	 * Returns the primary key of this layout page template structure rel.
	 *
	 * @return the primary key of this layout page template structure rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout page template structure rel.
	 *
	 * @param primaryKey the primary key of this layout page template structure rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this layout page template structure rel.
	 *
	 * @return the mvcc version of this layout page template structure rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this layout page template structure rel.
	 *
	 * @param mvccVersion the mvcc version of this layout page template structure rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this layout page template structure rel.
	 *
	 * @return the ct collection ID of this layout page template structure rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this layout page template structure rel.
	 *
	 * @param ctCollectionId the ct collection ID of this layout page template structure rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this layout page template structure rel.
	 *
	 * @return the uuid of this layout page template structure rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this layout page template structure rel.
	 *
	 * @param uuid the uuid of this layout page template structure rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the layout page template structure rel ID of this layout page template structure rel.
	 *
	 * @return the layout page template structure rel ID of this layout page template structure rel
	 */
	public long getLayoutPageTemplateStructureRelId();

	/**
	 * Sets the layout page template structure rel ID of this layout page template structure rel.
	 *
	 * @param layoutPageTemplateStructureRelId the layout page template structure rel ID of this layout page template structure rel
	 */
	public void setLayoutPageTemplateStructureRelId(
		long layoutPageTemplateStructureRelId);

	/**
	 * Returns the group ID of this layout page template structure rel.
	 *
	 * @return the group ID of this layout page template structure rel
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this layout page template structure rel.
	 *
	 * @param groupId the group ID of this layout page template structure rel
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout page template structure rel.
	 *
	 * @return the company ID of this layout page template structure rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout page template structure rel.
	 *
	 * @param companyId the company ID of this layout page template structure rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this layout page template structure rel.
	 *
	 * @return the user ID of this layout page template structure rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this layout page template structure rel.
	 *
	 * @param userId the user ID of this layout page template structure rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this layout page template structure rel.
	 *
	 * @return the user uuid of this layout page template structure rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this layout page template structure rel.
	 *
	 * @param userUuid the user uuid of this layout page template structure rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this layout page template structure rel.
	 *
	 * @return the user name of this layout page template structure rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this layout page template structure rel.
	 *
	 * @param userName the user name of this layout page template structure rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this layout page template structure rel.
	 *
	 * @return the create date of this layout page template structure rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout page template structure rel.
	 *
	 * @param createDate the create date of this layout page template structure rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout page template structure rel.
	 *
	 * @return the modified date of this layout page template structure rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout page template structure rel.
	 *
	 * @param modifiedDate the modified date of this layout page template structure rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the layout page template structure ID of this layout page template structure rel.
	 *
	 * @return the layout page template structure ID of this layout page template structure rel
	 */
	public long getLayoutPageTemplateStructureId();

	/**
	 * Sets the layout page template structure ID of this layout page template structure rel.
	 *
	 * @param layoutPageTemplateStructureId the layout page template structure ID of this layout page template structure rel
	 */
	public void setLayoutPageTemplateStructureId(
		long layoutPageTemplateStructureId);

	/**
	 * Returns the segments experience ID of this layout page template structure rel.
	 *
	 * @return the segments experience ID of this layout page template structure rel
	 */
	public long getSegmentsExperienceId();

	/**
	 * Sets the segments experience ID of this layout page template structure rel.
	 *
	 * @param segmentsExperienceId the segments experience ID of this layout page template structure rel
	 */
	public void setSegmentsExperienceId(long segmentsExperienceId);

	/**
	 * Returns the data of this layout page template structure rel.
	 *
	 * @return the data of this layout page template structure rel
	 */
	@AutoEscape
	public String getData();

	/**
	 * Sets the data of this layout page template structure rel.
	 *
	 * @param data the data of this layout page template structure rel
	 */
	public void setData(String data);

}