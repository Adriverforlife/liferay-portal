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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 */
public class ServiceContextUtil {

	public static ServiceContext getServiceContext() {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		return serviceContext;
	}

	public static void setAgentName(String agentName) {
		if (Validator.isNull(agentName)) {
			return;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.popServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		serviceContext.setAttribute("agentName", agentName);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);
	}

}