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

package com.liferay.osb.koroneiki.phloem.rest.internal.graphql.mutation.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Project;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProjectResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import javax.annotation.Generated;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setContactResourceComponentServiceObjects(
		ComponentServiceObjects<ContactResource>
			contactResourceComponentServiceObjects) {

		_contactResourceComponentServiceObjects =
			contactResourceComponentServiceObjects;
	}

	public static void setContactRoleResourceComponentServiceObjects(
		ComponentServiceObjects<ContactRoleResource>
			contactRoleResourceComponentServiceObjects) {

		_contactRoleResourceComponentServiceObjects =
			contactRoleResourceComponentServiceObjects;
	}

	public static void setExternalLinkResourceComponentServiceObjects(
		ComponentServiceObjects<ExternalLinkResource>
			externalLinkResourceComponentServiceObjects) {

		_externalLinkResourceComponentServiceObjects =
			externalLinkResourceComponentServiceObjects;
	}

	public static void setProductResourceComponentServiceObjects(
		ComponentServiceObjects<ProductResource>
			productResourceComponentServiceObjects) {

		_productResourceComponentServiceObjects =
			productResourceComponentServiceObjects;
	}

	public static void setProductConsumptionResourceComponentServiceObjects(
		ComponentServiceObjects<ProductConsumptionResource>
			productConsumptionResourceComponentServiceObjects) {

		_productConsumptionResourceComponentServiceObjects =
			productConsumptionResourceComponentServiceObjects;
	}

	public static void setProductPurchaseResourceComponentServiceObjects(
		ComponentServiceObjects<ProductPurchaseResource>
			productPurchaseResourceComponentServiceObjects) {

		_productPurchaseResourceComponentServiceObjects =
			productPurchaseResourceComponentServiceObjects;
	}

	public static void setProjectResourceComponentServiceObjects(
		ComponentServiceObjects<ProjectResource>
			projectResourceComponentServiceObjects) {

		_projectResourceComponentServiceObjects =
			projectResourceComponentServiceObjects;
	}

	public static void setTeamResourceComponentServiceObjects(
		ComponentServiceObjects<TeamResource>
			teamResourceComponentServiceObjects) {

		_teamResourceComponentServiceObjects =
			teamResourceComponentServiceObjects;
	}

	public static void setTeamRoleResourceComponentServiceObjects(
		ComponentServiceObjects<TeamRoleResource>
			teamRoleResourceComponentServiceObjects) {

		_teamRoleResourceComponentServiceObjects =
			teamRoleResourceComponentServiceObjects;
	}

	@GraphQLField
	public Account postAccount(@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccount(account));
	}

	public void deleteAccount(@GraphQLName("accountId") Long accountId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccount(accountId));
	}

	public Account putAccount(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccount(accountId, account));
	}

	public void deleteAccountContact(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("contactIds") Long[] contactIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountContact(
				accountId, contactIds));
	}

	public void putAccountContact(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("contactIds") Long[] contactIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountContact(
				accountId, contactIds));
	}

	public void deleteAccountContactRole(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("contactId") Long contactId,
			@GraphQLName("contactRoleIds") Long[] contactRoleIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountContactRole(
				accountId, contactId, contactRoleIds));
	}

	public void putAccountContactRole(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("contactId") Long contactId,
			@GraphQLName("contactRoleIds") Long[] contactRoleIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountContactRole(
				accountId, contactId, contactRoleIds));
	}

	@GraphQLField
	public Contact postContact(@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.postContact(contact));
	}

	public void deleteContact(@GraphQLName("contactId") Long contactId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.deleteContact(contactId));
	}

	@GraphQLField
	public ContactRole postContactRole(
			@GraphQLName("contactRole") ContactRole contactRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.postContactRole(
				contactRole));
	}

	public void deleteContactRole(
			@GraphQLName("contactRoleId") Long contactRoleId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.deleteContactRole(
				contactRoleId));
	}

	public ContactRole putContactRole(
			@GraphQLName("contactRoleId") Long contactRoleId,
			@GraphQLName("contactRole") ContactRole contactRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.putContactRole(
				contactRoleId, contactRole));
	}

	@GraphQLField
	public ExternalLink postAccountExternalLink(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postAccountExternalLink(
					accountId, externalLink));
	}

	@GraphQLField
	public ExternalLink postContactExternalLink(
			@GraphQLName("contactId") Long contactId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postContactExternalLink(
					contactId, externalLink));
	}

	public void deleteExternalLink(
			@GraphQLName("externalLinkId") Long externalLinkId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> externalLinkResource.deleteExternalLink(
				externalLinkId));
	}

	@GraphQLField
	public ExternalLink postProductConsumptionExternalLink(
			@GraphQLName("productConsumptionId") Long productConsumptionId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postProductConsumptionExternalLink(
					productConsumptionId, externalLink));
	}

	@GraphQLField
	public ExternalLink postProductPurchaseExternalLink(
			@GraphQLName("productPurchaseId") Long productPurchaseId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postProductPurchaseExternalLink(
					productPurchaseId, externalLink));
	}

	@GraphQLField
	public ExternalLink postProductExternalLink(
			@GraphQLName("productId") Long productId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postProductExternalLink(
					productId, externalLink));
	}

	@GraphQLField
	public ExternalLink postProjectExternalLink(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postProjectExternalLink(
					projectId, externalLink));
	}

	@GraphQLField
	public ExternalLink postTeamExternalLink(
			@GraphQLName("teamId") Long teamId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> externalLinkResource.postTeamExternalLink(
				teamId, externalLink));
	}

	@GraphQLField
	public Product postProduct(@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.postProduct(product));
	}

	public void deleteProduct(@GraphQLName("productId") Long productId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.deleteProduct(productId));
	}

	public Product putProduct(
			@GraphQLName("productId") Long productId,
			@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.putProduct(productId, product));
	}

	@GraphQLField
	public ProductConsumption postAccountProductConsumption(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("productConsumption") ProductConsumption
				productConsumption)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.postAccountProductConsumption(
					accountId, productConsumption));
	}

	public void deleteProductConsumption(
			@GraphQLName("productConsumptionId") Long productConsumptionId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.deleteProductConsumption(
					productConsumptionId));
	}

	@GraphQLField
	public ProductConsumption postProjectProductConsumption(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("productConsumption") ProductConsumption
				productConsumption)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.postProjectProductConsumption(
					projectId, productConsumption));
	}

	@GraphQLField
	public ProductPurchase postAccountProductPurchase(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("productPurchase") ProductPurchase productPurchase)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.postAccountProductPurchase(
					accountId, productPurchase));
	}

	public void deleteProductPurchase(
			@GraphQLName("productPurchaseId") Long productPurchaseId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.deleteProductPurchase(
					productPurchaseId));
	}

	public ProductPurchase putProductPurchase(
			@GraphQLName("productPurchaseId") Long productPurchaseId,
			@GraphQLName("productPurchase") ProductPurchase productPurchase)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.putProductPurchase(
					productPurchaseId, productPurchase));
	}

	@GraphQLField
	public ProductPurchase postProjectProductPurchase(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("productPurchase") ProductPurchase productPurchase)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.postProjectProductPurchase(
					projectId, productPurchase));
	}

	@GraphQLField
	public Project postAccountProject(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("project") Project project)
		throws Exception {

		return _applyComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.postAccountProject(
				accountId, project));
	}

	public void deleteProject(@GraphQLName("projectId") Long projectId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.deleteProject(projectId));
	}

	public Project putProject(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("project") Project project)
		throws Exception {

		return _applyComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.putProject(projectId, project));
	}

	public void deleteProjectContact(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("contactIds") Long[] contactIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.deleteProjectContact(
				projectId, contactIds));
	}

	public void putProjectContact(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("contactIds") Long[] contactIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.putProjectContact(
				projectId, contactIds));
	}

	public void deleteProjectContactRole(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("contactId") Long contactId,
			@GraphQLName("contactRoleIds") Long[] contactRoleIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.deleteProjectContactRole(
				projectId, contactId, contactRoleIds));
	}

	public void putProjectContactRole(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("contactId") Long contactId,
			@GraphQLName("contactRoleIds") Long[] contactRoleIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.putProjectContactRole(
				projectId, contactId, contactRoleIds));
	}

	public void deleteProjectTeamRole(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("teamId") Long teamId,
			@GraphQLName("teamRoleIds") Long[] teamRoleIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.deleteProjectTeamRole(
				projectId, teamId, teamRoleIds));
	}

	public void putProjectTeamRole(
			@GraphQLName("projectId") Long projectId,
			@GraphQLName("teamId") Long teamId,
			@GraphQLName("teamRoleIds") Long[] teamRoleIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_projectResourceComponentServiceObjects,
			this::_populateResourceContext,
			projectResource -> projectResource.putProjectTeamRole(
				projectId, teamId, teamRoleIds));
	}

	@GraphQLField
	public Team postAccountTeam(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("team") Team team)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.postAccountTeam(accountId, team));
	}

	public void deleteTeam(@GraphQLName("teamId") Long teamId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeam(teamId));
	}

	public Team putTeam(
			@GraphQLName("teamId") Long teamId, @GraphQLName("team") Team team)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeam(teamId, team));
	}

	@GraphQLField
	public TeamRole postTeamRole(@GraphQLName("teamRole") TeamRole teamRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.postTeamRole(teamRole));
	}

	public void deleteTeamRole(@GraphQLName("teamRoleId") Long teamRoleId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.deleteTeamRole(teamRoleId));
	}

	public TeamRole putTeamRole(
			@GraphQLName("teamRoleId") Long teamRoleId,
			@GraphQLName("teamRole") TeamRole teamRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.putTeamRole(
				teamRoleId, teamRole));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextAcceptLanguage(_acceptLanguage);
		accountResource.setContextCompany(_company);
	}

	private void _populateResourceContext(ContactResource contactResource)
		throws Exception {

		contactResource.setContextAcceptLanguage(_acceptLanguage);
		contactResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ContactRoleResource contactRoleResource)
		throws Exception {

		contactRoleResource.setContextAcceptLanguage(_acceptLanguage);
		contactRoleResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ExternalLinkResource externalLinkResource)
		throws Exception {

		externalLinkResource.setContextAcceptLanguage(_acceptLanguage);
		externalLinkResource.setContextCompany(_company);
	}

	private void _populateResourceContext(ProductResource productResource)
		throws Exception {

		productResource.setContextAcceptLanguage(_acceptLanguage);
		productResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ProductConsumptionResource productConsumptionResource)
		throws Exception {

		productConsumptionResource.setContextAcceptLanguage(_acceptLanguage);
		productConsumptionResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ProductPurchaseResource productPurchaseResource)
		throws Exception {

		productPurchaseResource.setContextAcceptLanguage(_acceptLanguage);
		productPurchaseResource.setContextCompany(_company);
	}

	private void _populateResourceContext(ProjectResource projectResource)
		throws Exception {

		projectResource.setContextAcceptLanguage(_acceptLanguage);
		projectResource.setContextCompany(_company);
	}

	private void _populateResourceContext(TeamResource teamResource)
		throws Exception {

		teamResource.setContextAcceptLanguage(_acceptLanguage);
		teamResource.setContextCompany(_company);
	}

	private void _populateResourceContext(TeamRoleResource teamRoleResource)
		throws Exception {

		teamRoleResource.setContextAcceptLanguage(_acceptLanguage);
		teamRoleResource.setContextCompany(_company);
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactResource>
		_contactResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;
	private static ComponentServiceObjects<ExternalLinkResource>
		_externalLinkResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductConsumptionResource>
		_productConsumptionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductPurchaseResource>
		_productPurchaseResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProjectResource>
		_projectResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamResource>
		_teamResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamRoleResource>
		_teamRoleResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private Company _company;

}