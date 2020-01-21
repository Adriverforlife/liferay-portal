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
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.RecentLayoutBranch;
import com.liferay.portal.kernel.model.RecentLayoutBranchModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the RecentLayoutBranch service. Represents a row in the &quot;RecentLayoutBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RecentLayoutBranchModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RecentLayoutBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutBranchImpl
 * @generated
 */
public class RecentLayoutBranchModelImpl
	extends BaseModelImpl<RecentLayoutBranch>
	implements RecentLayoutBranchModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a recent layout branch model instance should use the <code>RecentLayoutBranch</code> interface instead.
	 */
	public static final String TABLE_NAME = "RecentLayoutBranch";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"recentLayoutBranchId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"layoutBranchId", Types.BIGINT},
		{"layoutSetBranchId", Types.BIGINT}, {"plid", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recentLayoutBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutSetBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table RecentLayoutBranch (mvccVersion LONG default 0 not null,recentLayoutBranchId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,layoutBranchId LONG,layoutSetBranchId LONG,plid LONG)";

	public static final String TABLE_SQL_DROP = "drop table RecentLayoutBranch";

	public static final String ORDER_BY_JPQL =
		" ORDER BY recentLayoutBranch.recentLayoutBranchId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY RecentLayoutBranch.recentLayoutBranchId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.RecentLayoutBranch"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.RecentLayoutBranch"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.RecentLayoutBranch"),
		true);

	public static final long GROUPID_COLUMN_BITMASK = 1L;

	public static final long LAYOUTBRANCHID_COLUMN_BITMASK = 2L;

	public static final long LAYOUTSETBRANCHID_COLUMN_BITMASK = 4L;

	public static final long PLID_COLUMN_BITMASK = 8L;

	public static final long USERID_COLUMN_BITMASK = 16L;

	public static final long RECENTLAYOUTBRANCHID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.RecentLayoutBranch"));

	public RecentLayoutBranchModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _recentLayoutBranchId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRecentLayoutBranchId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _recentLayoutBranchId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RecentLayoutBranch.class;
	}

	@Override
	public String getModelClassName() {
		return RecentLayoutBranch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RecentLayoutBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RecentLayoutBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecentLayoutBranch, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((RecentLayoutBranch)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RecentLayoutBranch, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RecentLayoutBranch, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RecentLayoutBranch)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RecentLayoutBranch, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RecentLayoutBranch, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, RecentLayoutBranch>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			RecentLayoutBranch.class.getClassLoader(), RecentLayoutBranch.class,
			ModelWrapper.class);

		try {
			Constructor<RecentLayoutBranch> constructor =
				(Constructor<RecentLayoutBranch>)proxyClass.getConstructor(
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

	private static final Map<String, Function<RecentLayoutBranch, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<RecentLayoutBranch, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<RecentLayoutBranch, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<RecentLayoutBranch, Object>>();
		Map<String, BiConsumer<RecentLayoutBranch, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<RecentLayoutBranch, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", RecentLayoutBranch::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<RecentLayoutBranch, Long>)
				RecentLayoutBranch::setMvccVersion);
		attributeGetterFunctions.put(
			"recentLayoutBranchId",
			RecentLayoutBranch::getRecentLayoutBranchId);
		attributeSetterBiConsumers.put(
			"recentLayoutBranchId",
			(BiConsumer<RecentLayoutBranch, Long>)
				RecentLayoutBranch::setRecentLayoutBranchId);
		attributeGetterFunctions.put("groupId", RecentLayoutBranch::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<RecentLayoutBranch, Long>)
				RecentLayoutBranch::setGroupId);
		attributeGetterFunctions.put(
			"companyId", RecentLayoutBranch::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<RecentLayoutBranch, Long>)
				RecentLayoutBranch::setCompanyId);
		attributeGetterFunctions.put("userId", RecentLayoutBranch::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<RecentLayoutBranch, Long>)
				RecentLayoutBranch::setUserId);
		attributeGetterFunctions.put(
			"layoutBranchId", RecentLayoutBranch::getLayoutBranchId);
		attributeSetterBiConsumers.put(
			"layoutBranchId",
			(BiConsumer<RecentLayoutBranch, Long>)
				RecentLayoutBranch::setLayoutBranchId);
		attributeGetterFunctions.put(
			"layoutSetBranchId", RecentLayoutBranch::getLayoutSetBranchId);
		attributeSetterBiConsumers.put(
			"layoutSetBranchId",
			(BiConsumer<RecentLayoutBranch, Long>)
				RecentLayoutBranch::setLayoutSetBranchId);
		attributeGetterFunctions.put("plid", RecentLayoutBranch::getPlid);
		attributeSetterBiConsumers.put(
			"plid",
			(BiConsumer<RecentLayoutBranch, Long>)RecentLayoutBranch::setPlid);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getRecentLayoutBranchId() {
		return _recentLayoutBranchId;
	}

	@Override
	public void setRecentLayoutBranchId(long recentLayoutBranchId) {
		_recentLayoutBranchId = recentLayoutBranchId;
	}

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

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

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

	@Override
	public long getLayoutBranchId() {
		return _layoutBranchId;
	}

	@Override
	public void setLayoutBranchId(long layoutBranchId) {
		_columnBitmask |= LAYOUTBRANCHID_COLUMN_BITMASK;

		if (!_setOriginalLayoutBranchId) {
			_setOriginalLayoutBranchId = true;

			_originalLayoutBranchId = _layoutBranchId;
		}

		_layoutBranchId = layoutBranchId;
	}

	public long getOriginalLayoutBranchId() {
		return _originalLayoutBranchId;
	}

	@Override
	public long getLayoutSetBranchId() {
		return _layoutSetBranchId;
	}

	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_columnBitmask |= LAYOUTSETBRANCHID_COLUMN_BITMASK;

		if (!_setOriginalLayoutSetBranchId) {
			_setOriginalLayoutSetBranchId = true;

			_originalLayoutSetBranchId = _layoutSetBranchId;
		}

		_layoutSetBranchId = layoutSetBranchId;
	}

	public long getOriginalLayoutSetBranchId() {
		return _originalLayoutSetBranchId;
	}

	@Override
	public long getPlid() {
		return _plid;
	}

	@Override
	public void setPlid(long plid) {
		_columnBitmask |= PLID_COLUMN_BITMASK;

		if (!_setOriginalPlid) {
			_setOriginalPlid = true;

			_originalPlid = _plid;
		}

		_plid = plid;
	}

	public long getOriginalPlid() {
		return _originalPlid;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), RecentLayoutBranch.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RecentLayoutBranch toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, RecentLayoutBranch>
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
		RecentLayoutBranchImpl recentLayoutBranchImpl =
			new RecentLayoutBranchImpl();

		recentLayoutBranchImpl.setMvccVersion(getMvccVersion());
		recentLayoutBranchImpl.setRecentLayoutBranchId(
			getRecentLayoutBranchId());
		recentLayoutBranchImpl.setGroupId(getGroupId());
		recentLayoutBranchImpl.setCompanyId(getCompanyId());
		recentLayoutBranchImpl.setUserId(getUserId());
		recentLayoutBranchImpl.setLayoutBranchId(getLayoutBranchId());
		recentLayoutBranchImpl.setLayoutSetBranchId(getLayoutSetBranchId());
		recentLayoutBranchImpl.setPlid(getPlid());

		recentLayoutBranchImpl.resetOriginalValues();

		return recentLayoutBranchImpl;
	}

	@Override
	public int compareTo(RecentLayoutBranch recentLayoutBranch) {
		long primaryKey = recentLayoutBranch.getPrimaryKey();

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

		if (!(obj instanceof RecentLayoutBranch)) {
			return false;
		}

		RecentLayoutBranch recentLayoutBranch = (RecentLayoutBranch)obj;

		long primaryKey = recentLayoutBranch.getPrimaryKey();

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
		RecentLayoutBranchModelImpl recentLayoutBranchModelImpl = this;

		recentLayoutBranchModelImpl._originalGroupId =
			recentLayoutBranchModelImpl._groupId;

		recentLayoutBranchModelImpl._setOriginalGroupId = false;

		recentLayoutBranchModelImpl._originalUserId =
			recentLayoutBranchModelImpl._userId;

		recentLayoutBranchModelImpl._setOriginalUserId = false;

		recentLayoutBranchModelImpl._originalLayoutBranchId =
			recentLayoutBranchModelImpl._layoutBranchId;

		recentLayoutBranchModelImpl._setOriginalLayoutBranchId = false;

		recentLayoutBranchModelImpl._originalLayoutSetBranchId =
			recentLayoutBranchModelImpl._layoutSetBranchId;

		recentLayoutBranchModelImpl._setOriginalLayoutSetBranchId = false;

		recentLayoutBranchModelImpl._originalPlid =
			recentLayoutBranchModelImpl._plid;

		recentLayoutBranchModelImpl._setOriginalPlid = false;

		recentLayoutBranchModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<RecentLayoutBranch> toCacheModel() {
		RecentLayoutBranchCacheModel recentLayoutBranchCacheModel =
			new RecentLayoutBranchCacheModel();

		recentLayoutBranchCacheModel.mvccVersion = getMvccVersion();

		recentLayoutBranchCacheModel.recentLayoutBranchId =
			getRecentLayoutBranchId();

		recentLayoutBranchCacheModel.groupId = getGroupId();

		recentLayoutBranchCacheModel.companyId = getCompanyId();

		recentLayoutBranchCacheModel.userId = getUserId();

		recentLayoutBranchCacheModel.layoutBranchId = getLayoutBranchId();

		recentLayoutBranchCacheModel.layoutSetBranchId = getLayoutSetBranchId();

		recentLayoutBranchCacheModel.plid = getPlid();

		return recentLayoutBranchCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RecentLayoutBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RecentLayoutBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecentLayoutBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((RecentLayoutBranch)this));
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
		Map<String, Function<RecentLayoutBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<RecentLayoutBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecentLayoutBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((RecentLayoutBranch)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, RecentLayoutBranch>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _recentLayoutBranchId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _layoutBranchId;
	private long _originalLayoutBranchId;
	private boolean _setOriginalLayoutBranchId;
	private long _layoutSetBranchId;
	private long _originalLayoutSetBranchId;
	private boolean _setOriginalLayoutSetBranchId;
	private long _plid;
	private long _originalPlid;
	private boolean _setOriginalPlid;
	private long _columnBitmask;
	private RecentLayoutBranch _escapedModel;

}