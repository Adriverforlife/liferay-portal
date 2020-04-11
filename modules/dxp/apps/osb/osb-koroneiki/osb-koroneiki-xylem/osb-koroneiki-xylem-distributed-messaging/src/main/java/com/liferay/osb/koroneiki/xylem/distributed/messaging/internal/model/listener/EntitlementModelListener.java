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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.model.listener.PublishingTasksThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.entitlement.create",
		"remove.topic=koroneiki.entitlement.delete",
		"update.topic=koroneiki.entitlement.update"
	},
	service = ModelListener.class
)
public class EntitlementModelListener
	extends BaseXylemModelListener<Entitlement> {

	@Override
	public void onAfterCreate(Entitlement entitlement)
		throws ModelListenerException {

		super.onAfterCreate(entitlement);

		try {
			PublishingTasksThreadLocal.addPublishingTask(
				_getKey(entitlement), _getParentTopic(entitlement),
				_getParentCallable(entitlement));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onAfterRemove(Entitlement entitlement)
		throws ModelListenerException {

		super.onAfterRemove(entitlement);

		try {
			PublishingTasksThreadLocal.addPublishingTask(
				_getKey(entitlement), _getParentTopic(entitlement),
				_getParentCallable(entitlement));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	protected Callable<Message> getCallable(Entitlement entitlement)
		throws PortalException {

		if (entitlement.getClassNameId() ==
				_classNameLocalService.getClassNameId(Account.class)) {

			Account account = _accountLocalService.getAccount(
				entitlement.getClassPK());

			return () -> messageFactory.create(entitlement, account);
		}
		else if (entitlement.getClassNameId() ==
					_classNameLocalService.getClassNameId(Contact.class)) {

			Contact contact = _contactLocalService.getContact(
				entitlement.getClassPK());

			return () -> messageFactory.create(entitlement, contact);
		}

		return null;
	}

	private String _getKey(Entitlement entitlement) {
		StringBundler sb = new StringBundler(3);

		sb.append(_getParentTopic(entitlement));
		sb.append(StringPool.POUND);
		sb.append(entitlement.getClassPK());

		return sb.toString();
	}

	private Callable<Message> _getParentCallable(Entitlement entitlement)
		throws PortalException {

		if (entitlement.getClassNameId() ==
				_classNameLocalService.getClassNameId(Account.class)) {

			Account account = _accountLocalService.getAccount(
				entitlement.getClassPK());

			return () -> messageFactory.create(account);
		}
		else if (entitlement.getClassNameId() ==
					_classNameLocalService.getClassNameId(Contact.class)) {

			Contact contact = _contactLocalService.getContact(
				entitlement.getClassPK());

			return () -> messageFactory.create(contact);
		}

		return null;
	}

	private String _getParentTopic(Entitlement entitlement) {
		if (entitlement.getClassNameId() ==
				_classNameLocalService.getClassNameId(Account.class)) {

			return "koroneiki.account.update";
		}
		else if (entitlement.getClassNameId() ==
					_classNameLocalService.getClassNameId(Contact.class)) {

			return "koroneiki.contact.update";
		}

		return null;
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

}