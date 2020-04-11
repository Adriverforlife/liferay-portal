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

package com.liferay.osb.provisioning.koroneiki.constants;

/**
 * @author Amos Fong
 */
public class ContactRoleConstants {

	public static final String NAME_MEMBER = "Member";

	public static final String NAME_PARTNER_MANAGER = "Partner Manager";

	public static final String NAME_PARTNER_MEMBER = "Partner Member";

	public static final String NAME_PREFIX_LIFERAY = "Liferay";

	public static final String NAME_SUPPORT_CLOSED_WATCHER =
		"Support Closed Watcher";

	public static final String NAME_SUPPORT_DEVELOPER = "Support Developer";

	public static final String NAME_SUPPORT_WATCHER = "Support Watcher";

	public static final String[] PARTNER_CONTACT_ROLES = {
		NAME_PARTNER_MANAGER, NAME_PARTNER_MEMBER
	};

	public static final String[] SUPPORT_CONTACT_ROLES = {
		NAME_SUPPORT_DEVELOPER, NAME_SUPPORT_WATCHER,
		NAME_SUPPORT_CLOSED_WATCHER
	};

}