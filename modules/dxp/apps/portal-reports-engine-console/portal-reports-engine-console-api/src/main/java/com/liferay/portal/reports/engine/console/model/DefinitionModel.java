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

package com.liferay.portal.reports.engine.console.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the Definition service. Represents a row in the &quot;Reports_Definition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.reports.engine.console.model.impl.DefinitionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.reports.engine.console.model.impl.DefinitionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Definition
 * @see com.liferay.portal.reports.engine.console.model.impl.DefinitionImpl
 * @see com.liferay.portal.reports.engine.console.model.impl.DefinitionModelImpl
 * @generated
 */
@ProviderType
public interface DefinitionModel extends BaseModel<Definition>, LocalizedModel,
	ShardedModel, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a definition model instance should use the {@link Definition} interface instead.
	 */

	/**
	 * Returns the primary key of this definition.
	 *
	 * @return the primary key of this definition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this definition.
	 *
	 * @param primaryKey the primary key of this definition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this definition.
	 *
	 * @return the uuid of this definition
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this definition.
	 *
	 * @param uuid the uuid of this definition
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the definition ID of this definition.
	 *
	 * @return the definition ID of this definition
	 */
	public long getDefinitionId();

	/**
	 * Sets the definition ID of this definition.
	 *
	 * @param definitionId the definition ID of this definition
	 */
	public void setDefinitionId(long definitionId);

	/**
	 * Returns the group ID of this definition.
	 *
	 * @return the group ID of this definition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this definition.
	 *
	 * @param groupId the group ID of this definition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this definition.
	 *
	 * @return the company ID of this definition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this definition.
	 *
	 * @param companyId the company ID of this definition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this definition.
	 *
	 * @return the user ID of this definition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this definition.
	 *
	 * @param userId the user ID of this definition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this definition.
	 *
	 * @return the user uuid of this definition
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this definition.
	 *
	 * @param userUuid the user uuid of this definition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this definition.
	 *
	 * @return the user name of this definition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this definition.
	 *
	 * @param userName the user name of this definition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this definition.
	 *
	 * @return the create date of this definition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this definition.
	 *
	 * @param createDate the create date of this definition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this definition.
	 *
	 * @return the modified date of this definition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this definition.
	 *
	 * @param modifiedDate the modified date of this definition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this definition.
	 *
	 * @return the name of this definition
	 */
	public String getName();

	/**
	 * Returns the localized name of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this definition
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this definition
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this definition
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this definition.
	 *
	 * @return the locales and localized names of this definition
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this definition.
	 *
	 * @param name the name of this definition
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this definition in the language.
	 *
	 * @param name the localized name of this definition
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this definition in the language, and sets the default locale.
	 *
	 * @param name the localized name of this definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this definition from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this definition
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this definition from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this definition
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this definition.
	 *
	 * @return the description of this definition
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this definition
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this definition
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this definition
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this definition.
	 *
	 * @return the locales and localized descriptions of this definition
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this definition.
	 *
	 * @param description the description of this definition
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this definition in the language.
	 *
	 * @param description the localized description of this definition
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this definition in the language, and sets the default locale.
	 *
	 * @param description the localized description of this definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this definition from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this definition
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this definition from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this definition
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the source ID of this definition.
	 *
	 * @return the source ID of this definition
	 */
	public long getSourceId();

	/**
	 * Sets the source ID of this definition.
	 *
	 * @param sourceId the source ID of this definition
	 */
	public void setSourceId(long sourceId);

	/**
	 * Returns the report name of this definition.
	 *
	 * @return the report name of this definition
	 */
	@AutoEscape
	public String getReportName();

	/**
	 * Sets the report name of this definition.
	 *
	 * @param reportName the report name of this definition
	 */
	public void setReportName(String reportName);

	/**
	 * Returns the report parameters of this definition.
	 *
	 * @return the report parameters of this definition
	 */
	@AutoEscape
	public String getReportParameters();

	/**
	 * Sets the report parameters of this definition.
	 *
	 * @param reportParameters the report parameters of this definition
	 */
	public void setReportParameters(String reportParameters);

	/**
	 * Returns the last publish date of this definition.
	 *
	 * @return the last publish date of this definition
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this definition.
	 *
	 * @param lastPublishDate the last publish date of this definition
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;
}