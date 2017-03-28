create table CommerceProductDefinition (
	uuid_ VARCHAR(75) null,
	commerceProductDefinitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	urlTitle STRING null,
	description STRING null,
	productTypeName VARCHAR(75) null,
	availableIndividually BOOLEAN,
	DDMStructureKey VARCHAR(75) null,
	baseSKU VARCHAR(75) null,
	displayDate DATE null,
	expirationDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CommerceProductInstance (
	uuid_ VARCHAR(75) null,
	commerceProductInstanceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceProductDefinitionId LONG,
	sku VARCHAR(75) null,
	DDMContent VARCHAR(75) null,
	displayDate DATE null,
	expirationDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);