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

package com.liferay.portal.workflow.kaleo.forms.service.base;

import com.liferay.dynamic.data.lists.service.persistence.DDLRecordPersistence;
import com.liferay.dynamic.data.lists.service.persistence.DDLRecordSetPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.WorkflowDefinitionLinkPersistence;
import com.liferay.portal.kernel.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessService;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessFinder;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessLinkPersistence;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the kaleo process remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessServiceImpl}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessServiceImpl
 * @generated
 */
public abstract class KaleoProcessServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, KaleoProcessService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>KaleoProcessService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessServiceUtil</code>.
	 */

	/**
	 * Returns the kaleo process local service.
	 *
	 * @return the kaleo process local service
	 */
	public
		com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalService
			getKaleoProcessLocalService() {

		return kaleoProcessLocalService;
	}

	/**
	 * Sets the kaleo process local service.
	 *
	 * @param kaleoProcessLocalService the kaleo process local service
	 */
	public void setKaleoProcessLocalService(
		com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalService
			kaleoProcessLocalService) {

		this.kaleoProcessLocalService = kaleoProcessLocalService;
	}

	/**
	 * Returns the kaleo process remote service.
	 *
	 * @return the kaleo process remote service
	 */
	public KaleoProcessService getKaleoProcessService() {
		return kaleoProcessService;
	}

	/**
	 * Sets the kaleo process remote service.
	 *
	 * @param kaleoProcessService the kaleo process remote service
	 */
	public void setKaleoProcessService(
		KaleoProcessService kaleoProcessService) {

		this.kaleoProcessService = kaleoProcessService;
	}

	/**
	 * Returns the kaleo process persistence.
	 *
	 * @return the kaleo process persistence
	 */
	public KaleoProcessPersistence getKaleoProcessPersistence() {
		return kaleoProcessPersistence;
	}

	/**
	 * Sets the kaleo process persistence.
	 *
	 * @param kaleoProcessPersistence the kaleo process persistence
	 */
	public void setKaleoProcessPersistence(
		KaleoProcessPersistence kaleoProcessPersistence) {

		this.kaleoProcessPersistence = kaleoProcessPersistence;
	}

	/**
	 * Returns the kaleo process finder.
	 *
	 * @return the kaleo process finder
	 */
	public KaleoProcessFinder getKaleoProcessFinder() {
		return kaleoProcessFinder;
	}

	/**
	 * Sets the kaleo process finder.
	 *
	 * @param kaleoProcessFinder the kaleo process finder
	 */
	public void setKaleoProcessFinder(KaleoProcessFinder kaleoProcessFinder) {
		this.kaleoProcessFinder = kaleoProcessFinder;
	}

	/**
	 * Returns the kaleo process link local service.
	 *
	 * @return the kaleo process link local service
	 */
	public
		com.liferay.portal.workflow.kaleo.forms.service.
			KaleoProcessLinkLocalService getKaleoProcessLinkLocalService() {

		return kaleoProcessLinkLocalService;
	}

	/**
	 * Sets the kaleo process link local service.
	 *
	 * @param kaleoProcessLinkLocalService the kaleo process link local service
	 */
	public void setKaleoProcessLinkLocalService(
		com.liferay.portal.workflow.kaleo.forms.service.
			KaleoProcessLinkLocalService kaleoProcessLinkLocalService) {

		this.kaleoProcessLinkLocalService = kaleoProcessLinkLocalService;
	}

	/**
	 * Returns the kaleo process link persistence.
	 *
	 * @return the kaleo process link persistence
	 */
	public KaleoProcessLinkPersistence getKaleoProcessLinkPersistence() {
		return kaleoProcessLinkPersistence;
	}

	/**
	 * Sets the kaleo process link persistence.
	 *
	 * @param kaleoProcessLinkPersistence the kaleo process link persistence
	 */
	public void setKaleoProcessLinkPersistence(
		KaleoProcessLinkPersistence kaleoProcessLinkPersistence) {

		this.kaleoProcessLinkPersistence = kaleoProcessLinkPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the ddl record local service.
	 *
	 * @return the ddl record local service
	 */
	public com.liferay.dynamic.data.lists.service.DDLRecordLocalService
		getDDLRecordLocalService() {

		return ddlRecordLocalService;
	}

	/**
	 * Sets the ddl record local service.
	 *
	 * @param ddlRecordLocalService the ddl record local service
	 */
	public void setDDLRecordLocalService(
		com.liferay.dynamic.data.lists.service.DDLRecordLocalService
			ddlRecordLocalService) {

		this.ddlRecordLocalService = ddlRecordLocalService;
	}

	/**
	 * Returns the ddl record remote service.
	 *
	 * @return the ddl record remote service
	 */
	public com.liferay.dynamic.data.lists.service.DDLRecordService
		getDDLRecordService() {

		return ddlRecordService;
	}

	/**
	 * Sets the ddl record remote service.
	 *
	 * @param ddlRecordService the ddl record remote service
	 */
	public void setDDLRecordService(
		com.liferay.dynamic.data.lists.service.DDLRecordService
			ddlRecordService) {

		this.ddlRecordService = ddlRecordService;
	}

	/**
	 * Returns the ddl record persistence.
	 *
	 * @return the ddl record persistence
	 */
	public DDLRecordPersistence getDDLRecordPersistence() {
		return ddlRecordPersistence;
	}

	/**
	 * Sets the ddl record persistence.
	 *
	 * @param ddlRecordPersistence the ddl record persistence
	 */
	public void setDDLRecordPersistence(
		DDLRecordPersistence ddlRecordPersistence) {

		this.ddlRecordPersistence = ddlRecordPersistence;
	}

	/**
	 * Returns the ddl record set local service.
	 *
	 * @return the ddl record set local service
	 */
	public com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService
		getDDLRecordSetLocalService() {

		return ddlRecordSetLocalService;
	}

	/**
	 * Sets the ddl record set local service.
	 *
	 * @param ddlRecordSetLocalService the ddl record set local service
	 */
	public void setDDLRecordSetLocalService(
		com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService
			ddlRecordSetLocalService) {

		this.ddlRecordSetLocalService = ddlRecordSetLocalService;
	}

	/**
	 * Returns the ddl record set remote service.
	 *
	 * @return the ddl record set remote service
	 */
	public com.liferay.dynamic.data.lists.service.DDLRecordSetService
		getDDLRecordSetService() {

		return ddlRecordSetService;
	}

	/**
	 * Sets the ddl record set remote service.
	 *
	 * @param ddlRecordSetService the ddl record set remote service
	 */
	public void setDDLRecordSetService(
		com.liferay.dynamic.data.lists.service.DDLRecordSetService
			ddlRecordSetService) {

		this.ddlRecordSetService = ddlRecordSetService;
	}

	/**
	 * Returns the ddl record set persistence.
	 *
	 * @return the ddl record set persistence
	 */
	public DDLRecordSetPersistence getDDLRecordSetPersistence() {
		return ddlRecordSetPersistence;
	}

	/**
	 * Sets the ddl record set persistence.
	 *
	 * @param ddlRecordSetPersistence the ddl record set persistence
	 */
	public void setDDLRecordSetPersistence(
		DDLRecordSetPersistence ddlRecordSetPersistence) {

		this.ddlRecordSetPersistence = ddlRecordSetPersistence;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the workflow definition link local service.
	 *
	 * @return the workflow definition link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService
		getWorkflowDefinitionLinkLocalService() {

		return workflowDefinitionLinkLocalService;
	}

	/**
	 * Sets the workflow definition link local service.
	 *
	 * @param workflowDefinitionLinkLocalService the workflow definition link local service
	 */
	public void setWorkflowDefinitionLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService
			workflowDefinitionLinkLocalService) {

		this.workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	/**
	 * Returns the workflow definition link persistence.
	 *
	 * @return the workflow definition link persistence
	 */
	public WorkflowDefinitionLinkPersistence
		getWorkflowDefinitionLinkPersistence() {

		return workflowDefinitionLinkPersistence;
	}

	/**
	 * Sets the workflow definition link persistence.
	 *
	 * @param workflowDefinitionLinkPersistence the workflow definition link persistence
	 */
	public void setWorkflowDefinitionLinkPersistence(
		WorkflowDefinitionLinkPersistence workflowDefinitionLinkPersistence) {

		this.workflowDefinitionLinkPersistence =
			workflowDefinitionLinkPersistence;
	}

	/**
	 * Returns the workflow instance link local service.
	 *
	 * @return the workflow instance link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		getWorkflowInstanceLinkLocalService() {

		return workflowInstanceLinkLocalService;
	}

	/**
	 * Sets the workflow instance link local service.
	 *
	 * @param workflowInstanceLinkLocalService the workflow instance link local service
	 */
	public void setWorkflowInstanceLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
			workflowInstanceLinkLocalService) {

		this.workflowInstanceLinkLocalService =
			workflowInstanceLinkLocalService;
	}

	/**
	 * Returns the workflow instance link persistence.
	 *
	 * @return the workflow instance link persistence
	 */
	public WorkflowInstanceLinkPersistence
		getWorkflowInstanceLinkPersistence() {

		return workflowInstanceLinkPersistence;
	}

	/**
	 * Sets the workflow instance link persistence.
	 *
	 * @param workflowInstanceLinkPersistence the workflow instance link persistence
	 */
	public void setWorkflowInstanceLinkPersistence(
		WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence) {

		this.workflowInstanceLinkPersistence = workflowInstanceLinkPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return KaleoProcessService.class.getName();
	}

	protected Class<?> getModelClass() {
		return KaleoProcess.class;
	}

	protected String getModelClassName() {
		return KaleoProcess.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = kaleoProcessPersistence.getDataSource();

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

	@BeanReference(
		type = com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalService.class
	)
	protected
		com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalService
			kaleoProcessLocalService;

	@BeanReference(type = KaleoProcessService.class)
	protected KaleoProcessService kaleoProcessService;

	@BeanReference(type = KaleoProcessPersistence.class)
	protected KaleoProcessPersistence kaleoProcessPersistence;

	@BeanReference(type = KaleoProcessFinder.class)
	protected KaleoProcessFinder kaleoProcessFinder;

	@BeanReference(
		type = com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalService.class
	)
	protected
		com.liferay.portal.workflow.kaleo.forms.service.
			KaleoProcessLinkLocalService kaleoProcessLinkLocalService;

	@BeanReference(type = KaleoProcessLinkPersistence.class)
	protected KaleoProcessLinkPersistence kaleoProcessLinkPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.dynamic.data.lists.service.DDLRecordLocalService.class
	)
	protected com.liferay.dynamic.data.lists.service.DDLRecordLocalService
		ddlRecordLocalService;

	@ServiceReference(
		type = com.liferay.dynamic.data.lists.service.DDLRecordService.class
	)
	protected com.liferay.dynamic.data.lists.service.DDLRecordService
		ddlRecordService;

	@ServiceReference(type = DDLRecordPersistence.class)
	protected DDLRecordPersistence ddlRecordPersistence;

	@ServiceReference(
		type = com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService.class
	)
	protected com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService
		ddlRecordSetLocalService;

	@ServiceReference(
		type = com.liferay.dynamic.data.lists.service.DDLRecordSetService.class
	)
	protected com.liferay.dynamic.data.lists.service.DDLRecordSetService
		ddlRecordSetService;

	@ServiceReference(type = DDLRecordSetPersistence.class)
	protected DDLRecordSetPersistence ddlRecordSetPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService.class
	)
	protected
		com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService
			workflowDefinitionLinkLocalService;

	@ServiceReference(type = WorkflowDefinitionLinkPersistence.class)
	protected WorkflowDefinitionLinkPersistence
		workflowDefinitionLinkPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService.class
	)
	protected com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		workflowInstanceLinkLocalService;

	@ServiceReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;

}