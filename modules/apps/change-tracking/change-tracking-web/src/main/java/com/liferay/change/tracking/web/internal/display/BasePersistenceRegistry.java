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

package com.liferay.change.tracking.web.internal.display;

import com.liferay.change.tracking.reference.TableReferenceDefinition;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.spring.transaction.TransactionAttributeAdapter;
import com.liferay.portal.spring.transaction.TransactionAttributeBuilder;
import com.liferay.portal.spring.transaction.TransactionExecutor;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Preston Crary
 */
@Component(service = BasePersistenceRegistry.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BasePersistenceRegistry {

	public <T extends BaseModel<T>> Collection<T> fetchBaseModels(
		long classNameId, List<Long> primaryKeys) {

		TableReferenceDefinition<?> tableReferenceDefinition =
			_tableReferenceDefinitionServiceTrackerMap.getService(classNameId);

		BasePersistence<T> basePersistence =
			(BasePersistence<T>)tableReferenceDefinition.getBasePersistence();

		TransactionExecutor transactionExecutor = _portalTransactionExecutor;

		Bundle bundle = FrameworkUtil.getBundle(basePersistence.getClass());

		if (bundle != null) {
			transactionExecutor = _transactionExecutorMap.get(
				bundle.getBundleId());

			if (transactionExecutor == null) {
				throw new IllegalStateException(
					"No TransactionExecutor for " + tableReferenceDefinition);
			}
		}

		try {
			return transactionExecutor.execute(
				_transactionAttributeAdapter,
				() -> {
					Map<Serializable, T> map =
						basePersistence.fetchByPrimaryKeys(
							new HashSet<>(primaryKeys));

					return map.values();
				});
		}
		catch (Throwable throwable) {
			return ReflectionUtil.throwException(throwable);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_tableReferenceDefinitionServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, TableReferenceDefinition.class, null,
				(serviceReference, emitter) -> {
					TableReferenceDefinition<?> tableReferenceDefinition =
						bundleContext.getService(serviceReference);

					BasePersistence<?> basePersistence =
						tableReferenceDefinition.getBasePersistence();

					emitter.emit(
						_classNameLocalService.getClassNameId(
							basePersistence.getModelClass()));
				});

		_transactionExecutorServiceTracker = new ServiceTracker<>(
			bundleContext, TransactionExecutor.class,
			new TransactionExecutorServiceTrackerCustomizer(bundleContext));

		_transactionExecutorServiceTracker.open(true);
	}

	@Deactivate
	protected void deactivate() {
		_tableReferenceDefinitionServiceTrackerMap.close();

		_transactionExecutorServiceTracker.close();
	}

	private static final TransactionExecutor _portalTransactionExecutor =
		(TransactionExecutor)PortalBeanLocatorUtil.locate(
			"transactionExecutor");
	private static final TransactionAttributeAdapter
		_transactionAttributeAdapter = new TransactionAttributeAdapter(
			TransactionAttributeBuilder.build(
				BasePersistenceRegistry.class.getAnnotation(
					Transactional.class)));

	@Reference
	private ClassNameLocalService _classNameLocalService;

	private ServiceTrackerMap<Long, TableReferenceDefinition>
		_tableReferenceDefinitionServiceTrackerMap;
	private final Map<Object, TransactionExecutor> _transactionExecutorMap =
		new ConcurrentHashMap<>();
	private ServiceTracker<TransactionExecutor, ?>
		_transactionExecutorServiceTracker;

	private class TransactionExecutorServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<TransactionExecutor, Object> {

		@Override
		public Object addingService(
			ServiceReference<TransactionExecutor> serviceReference) {

			Object bundleId = serviceReference.getProperty(
				Constants.SERVICE_BUNDLEID);

			TransactionExecutor transactionExecutor = _bundleContext.getService(
				serviceReference);

			_transactionExecutorMap.put(bundleId, transactionExecutor);

			return bundleId;
		}

		@Override
		public void modifiedService(
			ServiceReference<TransactionExecutor> serviceReference,
			Object bundleId) {
		}

		@Override
		public void removedService(
			ServiceReference<TransactionExecutor> serviceReference,
			Object bundleId) {

			_transactionExecutorMap.remove(bundleId);

			_bundleContext.ungetService(serviceReference);
		}

		private TransactionExecutorServiceTrackerCustomizer(
			BundleContext bundleContext) {

			_bundleContext = bundleContext;
		}

		private final BundleContext _bundleContext;

	}

}