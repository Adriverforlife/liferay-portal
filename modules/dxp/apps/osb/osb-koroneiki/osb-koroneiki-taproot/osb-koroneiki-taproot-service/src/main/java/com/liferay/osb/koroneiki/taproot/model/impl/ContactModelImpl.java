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
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactModel;
import com.liferay.osb.koroneiki.taproot.model.ContactSoap;
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
 * The base model implementation for the Contact service. Represents a row in the &quot;Koroneiki_Contact&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ContactModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ContactImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactImpl
 * @generated
 */
@JSON(strict = true)
public class ContactModelImpl
	extends BaseModelImpl<Contact> implements ContactModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a contact model instance should use the <code>Contact</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_Contact";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"contactId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"contactKey", Types.VARCHAR},
		{"oktaId", Types.VARCHAR}, {"firstName", Types.VARCHAR},
		{"middleName", Types.VARCHAR}, {"lastName", Types.VARCHAR},
		{"emailAddress", Types.VARCHAR}, {"languageId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contactId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("contactKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("oktaId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("firstName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("middleName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_Contact (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,contactId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,contactKey VARCHAR(75) null,oktaId VARCHAR(75) null,firstName VARCHAR(75) null,middleName VARCHAR(75) null,lastName VARCHAR(75) null,emailAddress VARCHAR(75) null,languageId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Koroneiki_Contact";

	public static final String ORDER_BY_JPQL =
		" ORDER BY contact.contactId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_Contact.contactId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long CONTACTKEY_COLUMN_BITMASK = 2L;

	public static final long EMAILADDRESS_COLUMN_BITMASK = 4L;

	public static final long OKTAID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long CONTACTID_COLUMN_BITMASK = 32L;

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
	public static Contact toModel(ContactSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Contact model = new ContactImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setContactId(soapModel.getContactId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setContactKey(soapModel.getContactKey());
		model.setOktaId(soapModel.getOktaId());
		model.setFirstName(soapModel.getFirstName());
		model.setMiddleName(soapModel.getMiddleName());
		model.setLastName(soapModel.getLastName());
		model.setEmailAddress(soapModel.getEmailAddress());
		model.setLanguageId(soapModel.getLanguageId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Contact> toModels(ContactSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Contact> models = new ArrayList<Contact>(soapModels.length);

		for (ContactSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ContactModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _contactId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContactId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contactId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Contact.class;
	}

	@Override
	public String getModelClassName() {
		return Contact.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Contact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Contact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Contact, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Contact)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Contact, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Contact, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Contact)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Contact, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Contact, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Contact>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Contact.class.getClassLoader(), Contact.class, ModelWrapper.class);

		try {
			Constructor<Contact> constructor =
				(Constructor<Contact>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Contact, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Contact, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Contact, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Contact, Object>>();
		Map<String, BiConsumer<Contact, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Contact, ?>>();

		attributeGetterFunctions.put("mvccVersion", Contact::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion", (BiConsumer<Contact, Long>)Contact::setMvccVersion);
		attributeGetterFunctions.put("uuid", Contact::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Contact, String>)Contact::setUuid);
		attributeGetterFunctions.put("contactId", Contact::getContactId);
		attributeSetterBiConsumers.put(
			"contactId", (BiConsumer<Contact, Long>)Contact::setContactId);
		attributeGetterFunctions.put("companyId", Contact::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Contact, Long>)Contact::setCompanyId);
		attributeGetterFunctions.put("userId", Contact::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Contact, Long>)Contact::setUserId);
		attributeGetterFunctions.put("createDate", Contact::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Contact, Date>)Contact::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Contact::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Contact, Date>)Contact::setModifiedDate);
		attributeGetterFunctions.put("contactKey", Contact::getContactKey);
		attributeSetterBiConsumers.put(
			"contactKey", (BiConsumer<Contact, String>)Contact::setContactKey);
		attributeGetterFunctions.put("oktaId", Contact::getOktaId);
		attributeSetterBiConsumers.put(
			"oktaId", (BiConsumer<Contact, String>)Contact::setOktaId);
		attributeGetterFunctions.put("firstName", Contact::getFirstName);
		attributeSetterBiConsumers.put(
			"firstName", (BiConsumer<Contact, String>)Contact::setFirstName);
		attributeGetterFunctions.put("middleName", Contact::getMiddleName);
		attributeSetterBiConsumers.put(
			"middleName", (BiConsumer<Contact, String>)Contact::setMiddleName);
		attributeGetterFunctions.put("lastName", Contact::getLastName);
		attributeSetterBiConsumers.put(
			"lastName", (BiConsumer<Contact, String>)Contact::setLastName);
		attributeGetterFunctions.put("emailAddress", Contact::getEmailAddress);
		attributeSetterBiConsumers.put(
			"emailAddress",
			(BiConsumer<Contact, String>)Contact::setEmailAddress);
		attributeGetterFunctions.put("languageId", Contact::getLanguageId);
		attributeSetterBiConsumers.put(
			"languageId", (BiConsumer<Contact, String>)Contact::setLanguageId);

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
	public long getContactId() {
		return _contactId;
	}

	@Override
	public void setContactId(long contactId) {
		_contactId = contactId;
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
		catch (PortalException portalException) {
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
	public String getContactKey() {
		if (_contactKey == null) {
			return "";
		}
		else {
			return _contactKey;
		}
	}

	@Override
	public void setContactKey(String contactKey) {
		_columnBitmask |= CONTACTKEY_COLUMN_BITMASK;

		if (_originalContactKey == null) {
			_originalContactKey = _contactKey;
		}

		_contactKey = contactKey;
	}

	public String getOriginalContactKey() {
		return GetterUtil.getString(_originalContactKey);
	}

	@JSON
	@Override
	public String getOktaId() {
		if (_oktaId == null) {
			return "";
		}
		else {
			return _oktaId;
		}
	}

	@Override
	public void setOktaId(String oktaId) {
		_columnBitmask |= OKTAID_COLUMN_BITMASK;

		if (_originalOktaId == null) {
			_originalOktaId = _oktaId;
		}

		_oktaId = oktaId;
	}

	public String getOriginalOktaId() {
		return GetterUtil.getString(_originalOktaId);
	}

	@JSON
	@Override
	public String getFirstName() {
		if (_firstName == null) {
			return "";
		}
		else {
			return _firstName;
		}
	}

	@Override
	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	@JSON
	@Override
	public String getMiddleName() {
		if (_middleName == null) {
			return "";
		}
		else {
			return _middleName;
		}
	}

	@Override
	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	@JSON
	@Override
	public String getLastName() {
		if (_lastName == null) {
			return "";
		}
		else {
			return _lastName;
		}
	}

	@Override
	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	@JSON
	@Override
	public String getEmailAddress() {
		if (_emailAddress == null) {
			return "";
		}
		else {
			return _emailAddress;
		}
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		_columnBitmask |= EMAILADDRESS_COLUMN_BITMASK;

		if (_originalEmailAddress == null) {
			_originalEmailAddress = _emailAddress;
		}

		_emailAddress = emailAddress;
	}

	public String getOriginalEmailAddress() {
		return GetterUtil.getString(_originalEmailAddress);
	}

	@JSON
	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Contact.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Contact.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Contact toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Contact>
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
		ContactImpl contactImpl = new ContactImpl();

		contactImpl.setMvccVersion(getMvccVersion());
		contactImpl.setUuid(getUuid());
		contactImpl.setContactId(getContactId());
		contactImpl.setCompanyId(getCompanyId());
		contactImpl.setUserId(getUserId());
		contactImpl.setCreateDate(getCreateDate());
		contactImpl.setModifiedDate(getModifiedDate());
		contactImpl.setContactKey(getContactKey());
		contactImpl.setOktaId(getOktaId());
		contactImpl.setFirstName(getFirstName());
		contactImpl.setMiddleName(getMiddleName());
		contactImpl.setLastName(getLastName());
		contactImpl.setEmailAddress(getEmailAddress());
		contactImpl.setLanguageId(getLanguageId());

		contactImpl.resetOriginalValues();

		return contactImpl;
	}

	@Override
	public int compareTo(Contact contact) {
		long primaryKey = contact.getPrimaryKey();

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

		if (!(obj instanceof Contact)) {
			return false;
		}

		Contact contact = (Contact)obj;

		long primaryKey = contact.getPrimaryKey();

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
		ContactModelImpl contactModelImpl = this;

		contactModelImpl._originalUuid = contactModelImpl._uuid;

		contactModelImpl._originalCompanyId = contactModelImpl._companyId;

		contactModelImpl._setOriginalCompanyId = false;

		contactModelImpl._setModifiedDate = false;

		contactModelImpl._originalContactKey = contactModelImpl._contactKey;

		contactModelImpl._originalOktaId = contactModelImpl._oktaId;

		contactModelImpl._originalEmailAddress = contactModelImpl._emailAddress;

		contactModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Contact> toCacheModel() {
		ContactCacheModel contactCacheModel = new ContactCacheModel();

		contactCacheModel.mvccVersion = getMvccVersion();

		contactCacheModel.uuid = getUuid();

		String uuid = contactCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			contactCacheModel.uuid = null;
		}

		contactCacheModel.contactId = getContactId();

		contactCacheModel.companyId = getCompanyId();

		contactCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			contactCacheModel.createDate = createDate.getTime();
		}
		else {
			contactCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			contactCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			contactCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		contactCacheModel.contactKey = getContactKey();

		String contactKey = contactCacheModel.contactKey;

		if ((contactKey != null) && (contactKey.length() == 0)) {
			contactCacheModel.contactKey = null;
		}

		contactCacheModel.oktaId = getOktaId();

		String oktaId = contactCacheModel.oktaId;

		if ((oktaId != null) && (oktaId.length() == 0)) {
			contactCacheModel.oktaId = null;
		}

		contactCacheModel.firstName = getFirstName();

		String firstName = contactCacheModel.firstName;

		if ((firstName != null) && (firstName.length() == 0)) {
			contactCacheModel.firstName = null;
		}

		contactCacheModel.middleName = getMiddleName();

		String middleName = contactCacheModel.middleName;

		if ((middleName != null) && (middleName.length() == 0)) {
			contactCacheModel.middleName = null;
		}

		contactCacheModel.lastName = getLastName();

		String lastName = contactCacheModel.lastName;

		if ((lastName != null) && (lastName.length() == 0)) {
			contactCacheModel.lastName = null;
		}

		contactCacheModel.emailAddress = getEmailAddress();

		String emailAddress = contactCacheModel.emailAddress;

		if ((emailAddress != null) && (emailAddress.length() == 0)) {
			contactCacheModel.emailAddress = null;
		}

		contactCacheModel.languageId = getLanguageId();

		String languageId = contactCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			contactCacheModel.languageId = null;
		}

		return contactCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Contact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Contact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Contact, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Contact)this));
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
		Map<String, Function<Contact, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Contact, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Contact, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Contact)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Contact>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _contactId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _contactKey;
	private String _originalContactKey;
	private String _oktaId;
	private String _originalOktaId;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _emailAddress;
	private String _originalEmailAddress;
	private String _languageId;
	private long _columnBitmask;
	private Contact _escapedModel;

}