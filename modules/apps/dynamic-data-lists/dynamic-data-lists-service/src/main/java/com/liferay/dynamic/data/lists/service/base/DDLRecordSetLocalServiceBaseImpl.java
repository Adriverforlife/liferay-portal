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

package com.liferay.dynamic.data.lists.service.base;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.lists.service.persistence.DDLRecordSetFinder;
import com.liferay.dynamic.data.lists.service.persistence.DDLRecordSetPersistence;
import com.liferay.dynamic.data.lists.service.persistence.DDLRecordSetVersionPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the ddl record set local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.lists.service.impl.DDLRecordSetLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.lists.service.impl.DDLRecordSetLocalServiceImpl
 * @generated
 */
public abstract class DDLRecordSetLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, DDLRecordSetLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DDLRecordSetLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil</code>.
	 */

	/**
	 * Adds the ddl record set to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddlRecordSet the ddl record set
	 * @return the ddl record set that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDLRecordSet addDDLRecordSet(DDLRecordSet ddlRecordSet) {
		ddlRecordSet.setNew(true);

		return ddlRecordSetPersistence.update(ddlRecordSet);
	}

	/**
	 * Creates a new ddl record set with the primary key. Does not add the ddl record set to the database.
	 *
	 * @param recordSetId the primary key for the new ddl record set
	 * @return the new ddl record set
	 */
	@Override
	@Transactional(enabled = false)
	public DDLRecordSet createDDLRecordSet(long recordSetId) {
		return ddlRecordSetPersistence.create(recordSetId);
	}

	/**
	 * Deletes the ddl record set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recordSetId the primary key of the ddl record set
	 * @return the ddl record set that was removed
	 * @throws PortalException if a ddl record set with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDLRecordSet deleteDDLRecordSet(long recordSetId)
		throws PortalException {

		return ddlRecordSetPersistence.remove(recordSetId);
	}

	/**
	 * Deletes the ddl record set from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddlRecordSet the ddl record set
	 * @return the ddl record set that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDLRecordSet deleteDDLRecordSet(DDLRecordSet ddlRecordSet) {
		return ddlRecordSetPersistence.remove(ddlRecordSet);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			DDLRecordSet.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ddlRecordSetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordSetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return ddlRecordSetPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordSetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return ddlRecordSetPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return ddlRecordSetPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return ddlRecordSetPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DDLRecordSet fetchDDLRecordSet(long recordSetId) {
		return ddlRecordSetPersistence.fetchByPrimaryKey(recordSetId);
	}

	/**
	 * Returns the ddl record set matching the UUID and group.
	 *
	 * @param uuid the ddl record set's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddl record set, or <code>null</code> if a matching ddl record set could not be found
	 */
	@Override
	public DDLRecordSet fetchDDLRecordSetByUuidAndGroupId(
		String uuid, long groupId) {

		return ddlRecordSetPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the ddl record set with the primary key.
	 *
	 * @param recordSetId the primary key of the ddl record set
	 * @return the ddl record set
	 * @throws PortalException if a ddl record set with the primary key could not be found
	 */
	@Override
	public DDLRecordSet getDDLRecordSet(long recordSetId)
		throws PortalException {

		return ddlRecordSetPersistence.findByPrimaryKey(recordSetId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ddlRecordSetLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDLRecordSet.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("recordSetId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ddlRecordSetLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DDLRecordSet.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"recordSetId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ddlRecordSetLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDLRecordSet.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("recordSetId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DDLRecordSet>() {

				@Override
				public void performAction(DDLRecordSet ddlRecordSet)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, ddlRecordSet);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(DDLRecordSet.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return ddlRecordSetLocalService.deleteDDLRecordSet(
			(DDLRecordSet)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ddlRecordSetPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the ddl record sets matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddl record sets
	 * @param companyId the primary key of the company
	 * @return the matching ddl record sets, or an empty list if no matches were found
	 */
	@Override
	public List<DDLRecordSet> getDDLRecordSetsByUuidAndCompanyId(
		String uuid, long companyId) {

		return ddlRecordSetPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of ddl record sets matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddl record sets
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of ddl record sets
	 * @param end the upper bound of the range of ddl record sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching ddl record sets, or an empty list if no matches were found
	 */
	@Override
	public List<DDLRecordSet> getDDLRecordSetsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDLRecordSet> orderByComparator) {

		return ddlRecordSetPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the ddl record set matching the UUID and group.
	 *
	 * @param uuid the ddl record set's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddl record set
	 * @throws PortalException if a matching ddl record set could not be found
	 */
	@Override
	public DDLRecordSet getDDLRecordSetByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return ddlRecordSetPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the ddl record sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordSetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl record sets
	 * @param end the upper bound of the range of ddl record sets (not inclusive)
	 * @return the range of ddl record sets
	 */
	@Override
	public List<DDLRecordSet> getDDLRecordSets(int start, int end) {
		return ddlRecordSetPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ddl record sets.
	 *
	 * @return the number of ddl record sets
	 */
	@Override
	public int getDDLRecordSetsCount() {
		return ddlRecordSetPersistence.countAll();
	}

	/**
	 * Updates the ddl record set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddlRecordSet the ddl record set
	 * @return the ddl record set that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDLRecordSet updateDDLRecordSet(DDLRecordSet ddlRecordSet) {
		return ddlRecordSetPersistence.update(ddlRecordSet);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			DDLRecordSetLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		ddlRecordSetLocalService = (DDLRecordSetLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDLRecordSetLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDLRecordSet.class;
	}

	protected String getModelClassName() {
		return DDLRecordSet.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddlRecordSetPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected DDLRecordSetLocalService ddlRecordSetLocalService;

	@Reference
	protected DDLRecordSetPersistence ddlRecordSetPersistence;

	@Reference
	protected DDLRecordSetFinder ddlRecordSetFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected
		com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService
			workflowDefinitionLinkLocalService;

	@Reference
	protected DDLRecordSetVersionPersistence ddlRecordSetVersionPersistence;

}