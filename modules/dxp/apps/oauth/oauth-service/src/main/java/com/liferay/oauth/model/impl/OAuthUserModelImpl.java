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

package com.liferay.oauth.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.model.OAuthUserModel;
import com.liferay.oauth.model.OAuthUserSoap;

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
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

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
 * The base model implementation for the OAuthUser service. Represents a row in the &quot;OAuth_OAuthUser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link OAuthUserModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuthUserImpl}.
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthUserImpl
 * @see OAuthUser
 * @see OAuthUserModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class OAuthUserModelImpl extends BaseModelImpl<OAuthUser>
	implements OAuthUserModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth user model instance should use the {@link OAuthUser} interface instead.
	 */
	public static final String TABLE_NAME = "OAuth_OAuthUser";
	public static final Object[][] TABLE_COLUMNS = {
			{ "oAuthUserId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "oAuthApplicationId", Types.BIGINT },
			{ "accessToken", Types.VARCHAR },
			{ "accessSecret", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("oAuthUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("oAuthApplicationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accessToken", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accessSecret", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table OAuth_OAuthUser (oAuthUserId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,oAuthApplicationId LONG,accessToken VARCHAR(75) null,accessSecret VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table OAuth_OAuthUser";
	public static final String ORDER_BY_JPQL = " ORDER BY oAuthUser.oAuthUserId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY OAuth_OAuthUser.oAuthUserId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.oauth.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.oauth.model.OAuthUser"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.oauth.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.oauth.model.OAuthUser"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.oauth.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.oauth.model.OAuthUser"),
			true);
	public static final long ACCESSTOKEN_COLUMN_BITMASK = 1L;
	public static final long OAUTHAPPLICATIONID_COLUMN_BITMASK = 2L;
	public static final long USERID_COLUMN_BITMASK = 4L;
	public static final long OAUTHUSERID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static OAuthUser toModel(OAuthUserSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		OAuthUser model = new OAuthUserImpl();

		model.setOAuthUserId(soapModel.getOAuthUserId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setOAuthApplicationId(soapModel.getOAuthApplicationId());
		model.setAccessToken(soapModel.getAccessToken());
		model.setAccessSecret(soapModel.getAccessSecret());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<OAuthUser> toModels(OAuthUserSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<OAuthUser> models = new ArrayList<OAuthUser>(soapModels.length);

		for (OAuthUserSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.oauth.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.oauth.model.OAuthUser"));

	public OAuthUserModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthUserId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthUserId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthUser.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OAuthUser, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<OAuthUser, Object> attributeGetterFunction = entry.getValue();

			attributes.put(attributeName,
				attributeGetterFunction.apply((OAuthUser)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OAuthUser, Object>> attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OAuthUser, Object> attributeSetterBiConsumer = attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((OAuthUser)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<OAuthUser, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OAuthUser, Object>> getAttributeSetterBiConsumers() {
		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<OAuthUser, Object>> _attributeGetterFunctions;
	private static final Map<String, BiConsumer<OAuthUser, Object>> _attributeSetterBiConsumers;

	static {
		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions = new LinkedHashMap<String, Function<OAuthUser, Object>>();
		Map<String, BiConsumer<OAuthUser, ?>> attributeSetterBiConsumers = new LinkedHashMap<String, BiConsumer<OAuthUser, ?>>();


		attributeGetterFunctions.put("oAuthUserId", OAuthUser::getOAuthUserId);
		attributeSetterBiConsumers.put("oAuthUserId", (BiConsumer<OAuthUser, Long>)OAuthUser::setOAuthUserId);
		attributeGetterFunctions.put("companyId", OAuthUser::getCompanyId);
		attributeSetterBiConsumers.put("companyId", (BiConsumer<OAuthUser, Long>)OAuthUser::setCompanyId);
		attributeGetterFunctions.put("userId", OAuthUser::getUserId);
		attributeSetterBiConsumers.put("userId", (BiConsumer<OAuthUser, Long>)OAuthUser::setUserId);
		attributeGetterFunctions.put("userName", OAuthUser::getUserName);
		attributeSetterBiConsumers.put("userName", (BiConsumer<OAuthUser, String>)OAuthUser::setUserName);
		attributeGetterFunctions.put("createDate", OAuthUser::getCreateDate);
		attributeSetterBiConsumers.put("createDate", (BiConsumer<OAuthUser, Date>)OAuthUser::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", OAuthUser::getModifiedDate);
		attributeSetterBiConsumers.put("modifiedDate", (BiConsumer<OAuthUser, Date>)OAuthUser::setModifiedDate);
		attributeGetterFunctions.put("oAuthApplicationId", OAuthUser::getOAuthApplicationId);
		attributeSetterBiConsumers.put("oAuthApplicationId", (BiConsumer<OAuthUser, Long>)OAuthUser::setOAuthApplicationId);
		attributeGetterFunctions.put("accessToken", OAuthUser::getAccessToken);
		attributeSetterBiConsumers.put("accessToken", (BiConsumer<OAuthUser, String>)OAuthUser::setAccessToken);
		attributeGetterFunctions.put("accessSecret", OAuthUser::getAccessSecret);
		attributeSetterBiConsumers.put("accessSecret", (BiConsumer<OAuthUser, String>)OAuthUser::setAccessSecret);


		_attributeGetterFunctions = Collections.unmodifiableMap(attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap((Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getOAuthUserId() {
		return _oAuthUserId;
	}

	@Override
	public void setOAuthUserId(long oAuthUserId) {
		_oAuthUserId = oAuthUserId;
	}

	@Override
	public String getOAuthUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getOAuthUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setOAuthUserUuid(String oAuthUserUuid) {
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
		catch (PortalException pe) {
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
	public long getOAuthApplicationId() {
		return _oAuthApplicationId;
	}

	@Override
	public void setOAuthApplicationId(long oAuthApplicationId) {
		_columnBitmask |= OAUTHAPPLICATIONID_COLUMN_BITMASK;

		if (!_setOriginalOAuthApplicationId) {
			_setOriginalOAuthApplicationId = true;

			_originalOAuthApplicationId = _oAuthApplicationId;
		}

		_oAuthApplicationId = oAuthApplicationId;
	}

	public long getOriginalOAuthApplicationId() {
		return _originalOAuthApplicationId;
	}

	@JSON
	@Override
	public String getAccessToken() {
		if (_accessToken == null) {
			return "";
		}
		else {
			return _accessToken;
		}
	}

	@Override
	public void setAccessToken(String accessToken) {
		_columnBitmask |= ACCESSTOKEN_COLUMN_BITMASK;

		if (_originalAccessToken == null) {
			_originalAccessToken = _accessToken;
		}

		_accessToken = accessToken;
	}

	public String getOriginalAccessToken() {
		return GetterUtil.getString(_originalAccessToken);
	}

	@JSON
	@Override
	public String getAccessSecret() {
		if (_accessSecret == null) {
			return "";
		}
		else {
			return _accessSecret;
		}
	}

	@Override
	public void setAccessSecret(String accessSecret) {
		_accessSecret = accessSecret;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			OAuthUser.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OAuthUser toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (OAuthUser)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		OAuthUserImpl oAuthUserImpl = new OAuthUserImpl();

		oAuthUserImpl.setOAuthUserId(getOAuthUserId());
		oAuthUserImpl.setCompanyId(getCompanyId());
		oAuthUserImpl.setUserId(getUserId());
		oAuthUserImpl.setUserName(getUserName());
		oAuthUserImpl.setCreateDate(getCreateDate());
		oAuthUserImpl.setModifiedDate(getModifiedDate());
		oAuthUserImpl.setOAuthApplicationId(getOAuthApplicationId());
		oAuthUserImpl.setAccessToken(getAccessToken());
		oAuthUserImpl.setAccessSecret(getAccessSecret());

		oAuthUserImpl.resetOriginalValues();

		return oAuthUserImpl;
	}

	@Override
	public int compareTo(OAuthUser oAuthUser) {
		long primaryKey = oAuthUser.getPrimaryKey();

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

		if (!(obj instanceof OAuthUser)) {
			return false;
		}

		OAuthUser oAuthUser = (OAuthUser)obj;

		long primaryKey = oAuthUser.getPrimaryKey();

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
		OAuthUserModelImpl oAuthUserModelImpl = this;

		oAuthUserModelImpl._originalUserId = oAuthUserModelImpl._userId;

		oAuthUserModelImpl._setOriginalUserId = false;

		oAuthUserModelImpl._setModifiedDate = false;

		oAuthUserModelImpl._originalOAuthApplicationId = oAuthUserModelImpl._oAuthApplicationId;

		oAuthUserModelImpl._setOriginalOAuthApplicationId = false;

		oAuthUserModelImpl._originalAccessToken = oAuthUserModelImpl._accessToken;

		oAuthUserModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<OAuthUser> toCacheModel() {
		OAuthUserCacheModel oAuthUserCacheModel = new OAuthUserCacheModel();

		oAuthUserCacheModel.oAuthUserId = getOAuthUserId();

		oAuthUserCacheModel.companyId = getCompanyId();

		oAuthUserCacheModel.userId = getUserId();

		oAuthUserCacheModel.userName = getUserName();

		String userName = oAuthUserCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			oAuthUserCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			oAuthUserCacheModel.createDate = createDate.getTime();
		}
		else {
			oAuthUserCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			oAuthUserCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			oAuthUserCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		oAuthUserCacheModel.oAuthApplicationId = getOAuthApplicationId();

		oAuthUserCacheModel.accessToken = getAccessToken();

		String accessToken = oAuthUserCacheModel.accessToken;

		if ((accessToken != null) && (accessToken.length() == 0)) {
			oAuthUserCacheModel.accessToken = null;
		}

		oAuthUserCacheModel.accessSecret = getAccessSecret();

		String accessSecret = oAuthUserCacheModel.accessSecret;

		if ((accessSecret != null) && (accessSecret.length() == 0)) {
			oAuthUserCacheModel.accessSecret = null;
		}

		return oAuthUserCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((4 * attributeGetterFunctions.size()) +
				2);

		sb.append("{");

		for (Map.Entry<String, Function<OAuthUser, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<OAuthUser, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((OAuthUser)this));
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
		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((5 * attributeGetterFunctions.size()) +
				4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OAuthUser, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<OAuthUser, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OAuthUser)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = OAuthUser.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			OAuthUser.class, ModelWrapper.class
		};
	private long _oAuthUserId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _oAuthApplicationId;
	private long _originalOAuthApplicationId;
	private boolean _setOriginalOAuthApplicationId;
	private String _accessToken;
	private String _originalAccessToken;
	private String _accessSecret;
	private long _columnBitmask;
	private OAuthUser _escapedModel;
}