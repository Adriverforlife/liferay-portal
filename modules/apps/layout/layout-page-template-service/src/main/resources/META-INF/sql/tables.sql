create table LayoutPageTemplateCollection (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	layoutPageTemplateCollectionId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	lptCollectionKey VARCHAR(75) null,
	name VARCHAR(75) null,
	description STRING null,
	lastPublishDate DATE null,
	primary key (layoutPageTemplateCollectionId, ctCollectionId)
);

create table LayoutPageTemplateEntry (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	layoutPageTemplateEntryId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	layoutPageTemplateCollectionId LONG,
	layoutPageTemplateEntryKey VARCHAR(75) null,
	classNameId LONG,
	classTypeId LONG,
	name VARCHAR(75) null,
	type_ INTEGER,
	previewFileEntryId LONG,
	defaultTemplate BOOLEAN,
	layoutPrototypeId LONG,
	plid LONG,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	primary key (layoutPageTemplateEntryId, ctCollectionId)
);

create table LayoutPageTemplateStructure (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	layoutPageTemplateStructureId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	primary key (layoutPageTemplateStructureId, ctCollectionId)
);

create table LayoutPageTemplateStructureRel (
	mvccVersion LONG default 0 not null,
	ctCollectionId LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	lPageTemplateStructureRelId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	layoutPageTemplateStructureId LONG,
	segmentsExperienceId LONG,
	data_ TEXT null,
	primary key (lPageTemplateStructureRelId, ctCollectionId)
);