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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.taproot.model.Project;
import com.liferay.osb.koroneiki.taproot.model.ProjectModel;
import com.liferay.osb.koroneiki.taproot.model.ProjectSoap;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the Project service. Represents a row in the &quot;Koroneiki_Project&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ProjectModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProjectImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ProjectModelImpl
	extends BaseModelImpl<Project> implements ProjectModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a project model instance should use the <code>Project</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_Project";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"projectId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"accountId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"code_", Types.VARCHAR}, {"industry", Types.VARCHAR},
		{"tier", Types.VARCHAR}, {"notes", Types.VARCHAR},
		{"soldBy", Types.VARCHAR}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}, {"statusMessage", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("projectId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("code_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("industry", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tier", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("notes", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("soldBy", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("statusMessage", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_Project (uuid_ VARCHAR(75) null,projectId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,accountId LONG,name VARCHAR(75) null,code_ VARCHAR(75) null,industry VARCHAR(75) null,tier VARCHAR(75) null,notes STRING null,soldBy VARCHAR(75) null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,statusMessage VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Koroneiki_Project";

	public static final String ORDER_BY_JPQL =
		" ORDER BY project.projectId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_Project.projectId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACCOUNTID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long PROJECTID_COLUMN_BITMASK = 8L;

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
	public static Project toModel(ProjectSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Project model = new ProjectImpl();

		model.setUuid(soapModel.getUuid());
		model.setProjectId(soapModel.getProjectId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAccountId(soapModel.getAccountId());
		model.setName(soapModel.getName());
		model.setCode(soapModel.getCode());
		model.setIndustry(soapModel.getIndustry());
		model.setTier(soapModel.getTier());
		model.setNotes(soapModel.getNotes());
		model.setSoldBy(soapModel.getSoldBy());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setStatusMessage(soapModel.getStatusMessage());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Project> toModels(ProjectSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Project> models = new ArrayList<Project>(soapModels.length);

		for (ProjectSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ProjectModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _projectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Project.class;
	}

	@Override
	public String getModelClassName() {
		return Project.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Project, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Project, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Project, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Project)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Project, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Project, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Project)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Project, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Project, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Project>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Project.class.getClassLoader(), Project.class, ModelWrapper.class);

		try {
			Constructor<Project> constructor =
				(Constructor<Project>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Project, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Project, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Project, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Project, Object>>();
		Map<String, BiConsumer<Project, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Project, ?>>();

		attributeGetterFunctions.put("uuid", Project::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Project, String>)Project::setUuid);
		attributeGetterFunctions.put("projectId", Project::getProjectId);
		attributeSetterBiConsumers.put(
			"projectId", (BiConsumer<Project, Long>)Project::setProjectId);
		attributeGetterFunctions.put("companyId", Project::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Project, Long>)Project::setCompanyId);
		attributeGetterFunctions.put("userId", Project::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Project, Long>)Project::setUserId);
		attributeGetterFunctions.put("createDate", Project::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Project, Date>)Project::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Project::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Project, Date>)Project::setModifiedDate);
		attributeGetterFunctions.put("accountId", Project::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId", (BiConsumer<Project, Long>)Project::setAccountId);
		attributeGetterFunctions.put("name", Project::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Project, String>)Project::setName);
		attributeGetterFunctions.put("code", Project::getCode);
		attributeSetterBiConsumers.put(
			"code", (BiConsumer<Project, String>)Project::setCode);
		attributeGetterFunctions.put("industry", Project::getIndustry);
		attributeSetterBiConsumers.put(
			"industry", (BiConsumer<Project, String>)Project::setIndustry);
		attributeGetterFunctions.put("tier", Project::getTier);
		attributeSetterBiConsumers.put(
			"tier", (BiConsumer<Project, String>)Project::setTier);
		attributeGetterFunctions.put("notes", Project::getNotes);
		attributeSetterBiConsumers.put(
			"notes", (BiConsumer<Project, String>)Project::setNotes);
		attributeGetterFunctions.put("soldBy", Project::getSoldBy);
		attributeSetterBiConsumers.put(
			"soldBy", (BiConsumer<Project, String>)Project::setSoldBy);
		attributeGetterFunctions.put("status", Project::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<Project, Integer>)Project::setStatus);
		attributeGetterFunctions.put(
			"statusByUserId", Project::getStatusByUserId);
		attributeSetterBiConsumers.put(
			"statusByUserId",
			(BiConsumer<Project, Long>)Project::setStatusByUserId);
		attributeGetterFunctions.put(
			"statusByUserName", Project::getStatusByUserName);
		attributeSetterBiConsumers.put(
			"statusByUserName",
			(BiConsumer<Project, String>)Project::setStatusByUserName);
		attributeGetterFunctions.put("statusDate", Project::getStatusDate);
		attributeSetterBiConsumers.put(
			"statusDate", (BiConsumer<Project, Date>)Project::setStatusDate);
		attributeGetterFunctions.put(
			"statusMessage", Project::getStatusMessage);
		attributeSetterBiConsumers.put(
			"statusMessage",
			(BiConsumer<Project, String>)Project::setStatusMessage);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
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
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_columnBitmask |= ACCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalAccountId) {
			_setOriginalAccountId = true;

			_originalAccountId = _accountId;
		}

		_accountId = accountId;
	}

	public long getOriginalAccountId() {
		return _originalAccountId;
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
	public String getCode() {
		if (_code == null) {
			return "";
		}
		else {
			return _code;
		}
	}

	@Override
	public void setCode(String code) {
		_code = code;
	}

	@JSON
	@Override
	public String getIndustry() {
		if (_industry == null) {
			return "";
		}
		else {
			return _industry;
		}
	}

	@Override
	public void setIndustry(String industry) {
		_industry = industry;
	}

	@JSON
	@Override
	public String getTier() {
		if (_tier == null) {
			return "";
		}
		else {
			return _tier;
		}
	}

	@Override
	public void setTier(String tier) {
		_tier = tier;
	}

	@JSON
	@Override
	public String getNotes() {
		if (_notes == null) {
			return "";
		}
		else {
			return _notes;
		}
	}

	@Override
	public void setNotes(String notes) {
		_notes = notes;
	}

	@JSON
	@Override
	public String getSoldBy() {
		if (_soldBy == null) {
			return "";
		}
		else {
			return _soldBy;
		}
	}

	@Override
	public void setSoldBy(String soldBy) {
		_soldBy = soldBy;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@JSON
	@Override
	public String getStatusMessage() {
		if (_statusMessage == null) {
			return "";
		}
		else {
			return _statusMessage;
		}
	}

	@Override
	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Project.class.getName()));
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Project.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Project toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ProjectImpl projectImpl = new ProjectImpl();

		projectImpl.setUuid(getUuid());
		projectImpl.setProjectId(getProjectId());
		projectImpl.setCompanyId(getCompanyId());
		projectImpl.setUserId(getUserId());
		projectImpl.setCreateDate(getCreateDate());
		projectImpl.setModifiedDate(getModifiedDate());
		projectImpl.setAccountId(getAccountId());
		projectImpl.setName(getName());
		projectImpl.setCode(getCode());
		projectImpl.setIndustry(getIndustry());
		projectImpl.setTier(getTier());
		projectImpl.setNotes(getNotes());
		projectImpl.setSoldBy(getSoldBy());
		projectImpl.setStatus(getStatus());
		projectImpl.setStatusByUserId(getStatusByUserId());
		projectImpl.setStatusByUserName(getStatusByUserName());
		projectImpl.setStatusDate(getStatusDate());
		projectImpl.setStatusMessage(getStatusMessage());

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	@Override
	public int compareTo(Project project) {
		long primaryKey = project.getPrimaryKey();

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

		if (!(obj instanceof Project)) {
			return false;
		}

		Project project = (Project)obj;

		long primaryKey = project.getPrimaryKey();

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
		ProjectModelImpl projectModelImpl = this;

		projectModelImpl._originalUuid = projectModelImpl._uuid;

		projectModelImpl._originalCompanyId = projectModelImpl._companyId;

		projectModelImpl._setOriginalCompanyId = false;

		projectModelImpl._setModifiedDate = false;

		projectModelImpl._originalAccountId = projectModelImpl._accountId;

		projectModelImpl._setOriginalAccountId = false;

		projectModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Project> toCacheModel() {
		ProjectCacheModel projectCacheModel = new ProjectCacheModel();

		projectCacheModel.uuid = getUuid();

		String uuid = projectCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			projectCacheModel.uuid = null;
		}

		projectCacheModel.projectId = getProjectId();

		projectCacheModel.companyId = getCompanyId();

		projectCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			projectCacheModel.createDate = createDate.getTime();
		}
		else {
			projectCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			projectCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			projectCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		projectCacheModel.accountId = getAccountId();

		projectCacheModel.name = getName();

		String name = projectCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			projectCacheModel.name = null;
		}

		projectCacheModel.code = getCode();

		String code = projectCacheModel.code;

		if ((code != null) && (code.length() == 0)) {
			projectCacheModel.code = null;
		}

		projectCacheModel.industry = getIndustry();

		String industry = projectCacheModel.industry;

		if ((industry != null) && (industry.length() == 0)) {
			projectCacheModel.industry = null;
		}

		projectCacheModel.tier = getTier();

		String tier = projectCacheModel.tier;

		if ((tier != null) && (tier.length() == 0)) {
			projectCacheModel.tier = null;
		}

		projectCacheModel.notes = getNotes();

		String notes = projectCacheModel.notes;

		if ((notes != null) && (notes.length() == 0)) {
			projectCacheModel.notes = null;
		}

		projectCacheModel.soldBy = getSoldBy();

		String soldBy = projectCacheModel.soldBy;

		if ((soldBy != null) && (soldBy.length() == 0)) {
			projectCacheModel.soldBy = null;
		}

		projectCacheModel.status = getStatus();

		projectCacheModel.statusByUserId = getStatusByUserId();

		projectCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = projectCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			projectCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			projectCacheModel.statusDate = statusDate.getTime();
		}
		else {
			projectCacheModel.statusDate = Long.MIN_VALUE;
		}

		projectCacheModel.statusMessage = getStatusMessage();

		String statusMessage = projectCacheModel.statusMessage;

		if ((statusMessage != null) && (statusMessage.length() == 0)) {
			projectCacheModel.statusMessage = null;
		}

		return projectCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Project, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Project, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Project, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Project)this));
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
		Map<String, Function<Project, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Project, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Project, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Project)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Project>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _projectId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _accountId;
	private long _originalAccountId;
	private boolean _setOriginalAccountId;
	private String _name;
	private String _code;
	private String _industry;
	private String _tier;
	private String _notes;
	private String _soldBy;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
	private long _columnBitmask;
	private Project _escapedModel;

}