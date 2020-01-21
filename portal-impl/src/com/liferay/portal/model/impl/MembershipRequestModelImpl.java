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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MembershipRequest;
import com.liferay.portal.kernel.model.MembershipRequestModel;
import com.liferay.portal.kernel.model.MembershipRequestSoap;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

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

/**
 * The base model implementation for the MembershipRequest service. Represents a row in the &quot;MembershipRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MembershipRequestModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MembershipRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequestImpl
 * @generated
 */
@JSON(strict = true)
public class MembershipRequestModelImpl
	extends BaseModelImpl<MembershipRequest> implements MembershipRequestModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a membership request model instance should use the <code>MembershipRequest</code> interface instead.
	 */
	public static final String TABLE_NAME = "MembershipRequest";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"membershipRequestId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"comments", Types.VARCHAR}, {"replyComments", Types.VARCHAR},
		{"replyDate", Types.TIMESTAMP}, {"replierUserId", Types.BIGINT},
		{"statusId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("membershipRequestId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("comments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("replyComments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("replyDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("replierUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MembershipRequest (mvccVersion LONG default 0 not null,membershipRequestId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,comments STRING null,replyComments STRING null,replyDate DATE null,replierUserId LONG,statusId LONG)";

	public static final String TABLE_SQL_DROP = "drop table MembershipRequest";

	public static final String ORDER_BY_JPQL =
		" ORDER BY membershipRequest.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MembershipRequest.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.MembershipRequest"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.MembershipRequest"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.MembershipRequest"),
		true);

	public static final long GROUPID_COLUMN_BITMASK = 1L;

	public static final long STATUSID_COLUMN_BITMASK = 2L;

	public static final long USERID_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MembershipRequest toModel(MembershipRequestSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		MembershipRequest model = new MembershipRequestImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setMembershipRequestId(soapModel.getMembershipRequestId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setComments(soapModel.getComments());
		model.setReplyComments(soapModel.getReplyComments());
		model.setReplyDate(soapModel.getReplyDate());
		model.setReplierUserId(soapModel.getReplierUserId());
		model.setStatusId(soapModel.getStatusId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MembershipRequest> toModels(
		MembershipRequestSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<MembershipRequest> models = new ArrayList<MembershipRequest>(
			soapModels.length);

		for (MembershipRequestSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.MembershipRequest"));

	public MembershipRequestModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _membershipRequestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMembershipRequestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _membershipRequestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MembershipRequest.class;
	}

	@Override
	public String getModelClassName() {
		return MembershipRequest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MembershipRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MembershipRequest, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MembershipRequest)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MembershipRequest, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MembershipRequest, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MembershipRequest)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MembershipRequest, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MembershipRequest, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MembershipRequest>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MembershipRequest.class.getClassLoader(), MembershipRequest.class,
			ModelWrapper.class);

		try {
			Constructor<MembershipRequest> constructor =
				(Constructor<MembershipRequest>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<MembershipRequest, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MembershipRequest, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<MembershipRequest, Object>>();
		Map<String, BiConsumer<MembershipRequest, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<MembershipRequest, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", MembershipRequest::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setMvccVersion);
		attributeGetterFunctions.put(
			"membershipRequestId", MembershipRequest::getMembershipRequestId);
		attributeSetterBiConsumers.put(
			"membershipRequestId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setMembershipRequestId);
		attributeGetterFunctions.put("groupId", MembershipRequest::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<MembershipRequest, Long>)MembershipRequest::setGroupId);
		attributeGetterFunctions.put(
			"companyId", MembershipRequest::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setCompanyId);
		attributeGetterFunctions.put("userId", MembershipRequest::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<MembershipRequest, Long>)MembershipRequest::setUserId);
		attributeGetterFunctions.put(
			"createDate", MembershipRequest::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<MembershipRequest, Date>)
				MembershipRequest::setCreateDate);
		attributeGetterFunctions.put(
			"comments", MembershipRequest::getComments);
		attributeSetterBiConsumers.put(
			"comments",
			(BiConsumer<MembershipRequest, String>)
				MembershipRequest::setComments);
		attributeGetterFunctions.put(
			"replyComments", MembershipRequest::getReplyComments);
		attributeSetterBiConsumers.put(
			"replyComments",
			(BiConsumer<MembershipRequest, String>)
				MembershipRequest::setReplyComments);
		attributeGetterFunctions.put(
			"replyDate", MembershipRequest::getReplyDate);
		attributeSetterBiConsumers.put(
			"replyDate",
			(BiConsumer<MembershipRequest, Date>)
				MembershipRequest::setReplyDate);
		attributeGetterFunctions.put(
			"replierUserId", MembershipRequest::getReplierUserId);
		attributeSetterBiConsumers.put(
			"replierUserId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setReplierUserId);
		attributeGetterFunctions.put(
			"statusId", MembershipRequest::getStatusId);
		attributeSetterBiConsumers.put(
			"statusId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setStatusId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getMembershipRequestId() {
		return _membershipRequestId;
	}

	@Override
	public void setMembershipRequestId(long membershipRequestId) {
		_membershipRequestId = membershipRequestId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
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
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
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
	public String getComments() {
		if (_comments == null) {
			return "";
		}
		else {
			return _comments;
		}
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;
	}

	@JSON
	@Override
	public String getReplyComments() {
		if (_replyComments == null) {
			return "";
		}
		else {
			return _replyComments;
		}
	}

	@Override
	public void setReplyComments(String replyComments) {
		_replyComments = replyComments;
	}

	@JSON
	@Override
	public Date getReplyDate() {
		return _replyDate;
	}

	@Override
	public void setReplyDate(Date replyDate) {
		_replyDate = replyDate;
	}

	@JSON
	@Override
	public long getReplierUserId() {
		return _replierUserId;
	}

	@Override
	public void setReplierUserId(long replierUserId) {
		_replierUserId = replierUserId;
	}

	@Override
	public String getReplierUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getReplierUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setReplierUserUuid(String replierUserUuid) {
	}

	@JSON
	@Override
	public long getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(long statusId) {
		_columnBitmask |= STATUSID_COLUMN_BITMASK;

		if (!_setOriginalStatusId) {
			_setOriginalStatusId = true;

			_originalStatusId = _statusId;
		}

		_statusId = statusId;
	}

	public long getOriginalStatusId() {
		return _originalStatusId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), MembershipRequest.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MembershipRequest toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MembershipRequest>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MembershipRequestImpl membershipRequestImpl =
			new MembershipRequestImpl();

		membershipRequestImpl.setMvccVersion(getMvccVersion());
		membershipRequestImpl.setMembershipRequestId(getMembershipRequestId());
		membershipRequestImpl.setGroupId(getGroupId());
		membershipRequestImpl.setCompanyId(getCompanyId());
		membershipRequestImpl.setUserId(getUserId());
		membershipRequestImpl.setCreateDate(getCreateDate());
		membershipRequestImpl.setComments(getComments());
		membershipRequestImpl.setReplyComments(getReplyComments());
		membershipRequestImpl.setReplyDate(getReplyDate());
		membershipRequestImpl.setReplierUserId(getReplierUserId());
		membershipRequestImpl.setStatusId(getStatusId());

		membershipRequestImpl.resetOriginalValues();

		return membershipRequestImpl;
	}

	@Override
	public int compareTo(MembershipRequest membershipRequest) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), membershipRequest.getCreateDate());

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

		if (!(obj instanceof MembershipRequest)) {
			return false;
		}

		MembershipRequest membershipRequest = (MembershipRequest)obj;

		long primaryKey = membershipRequest.getPrimaryKey();

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
		MembershipRequestModelImpl membershipRequestModelImpl = this;

		membershipRequestModelImpl._originalGroupId =
			membershipRequestModelImpl._groupId;

		membershipRequestModelImpl._setOriginalGroupId = false;

		membershipRequestModelImpl._originalUserId =
			membershipRequestModelImpl._userId;

		membershipRequestModelImpl._setOriginalUserId = false;

		membershipRequestModelImpl._originalStatusId =
			membershipRequestModelImpl._statusId;

		membershipRequestModelImpl._setOriginalStatusId = false;

		membershipRequestModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MembershipRequest> toCacheModel() {
		MembershipRequestCacheModel membershipRequestCacheModel =
			new MembershipRequestCacheModel();

		membershipRequestCacheModel.mvccVersion = getMvccVersion();

		membershipRequestCacheModel.membershipRequestId =
			getMembershipRequestId();

		membershipRequestCacheModel.groupId = getGroupId();

		membershipRequestCacheModel.companyId = getCompanyId();

		membershipRequestCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			membershipRequestCacheModel.createDate = createDate.getTime();
		}
		else {
			membershipRequestCacheModel.createDate = Long.MIN_VALUE;
		}

		membershipRequestCacheModel.comments = getComments();

		String comments = membershipRequestCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			membershipRequestCacheModel.comments = null;
		}

		membershipRequestCacheModel.replyComments = getReplyComments();

		String replyComments = membershipRequestCacheModel.replyComments;

		if ((replyComments != null) && (replyComments.length() == 0)) {
			membershipRequestCacheModel.replyComments = null;
		}

		Date replyDate = getReplyDate();

		if (replyDate != null) {
			membershipRequestCacheModel.replyDate = replyDate.getTime();
		}
		else {
			membershipRequestCacheModel.replyDate = Long.MIN_VALUE;
		}

		membershipRequestCacheModel.replierUserId = getReplierUserId();

		membershipRequestCacheModel.statusId = getStatusId();

		return membershipRequestCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MembershipRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MembershipRequest, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((MembershipRequest)this));
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
		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MembershipRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MembershipRequest, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((MembershipRequest)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, MembershipRequest>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _membershipRequestId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private Date _createDate;
	private String _comments;
	private String _replyComments;
	private Date _replyDate;
	private long _replierUserId;
	private long _statusId;
	private long _originalStatusId;
	private boolean _setOriginalStatusId;
	private long _columnBitmask;
	private MembershipRequest _escapedModel;

}