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

package com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Node;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.NodeResource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Rafael Praxedes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/node.properties",
	scope = ServiceScope.PROTOTYPE, service = NodeResource.class
)
public class NodeResourceImpl extends BaseNodeResourceImpl {

	@Override
	public Page<Node> getProcessNodesPage(Long processId) throws Exception {
		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setIndexNames("workflow-metrics-nodes");
		searchSearchRequest.setQuery(
			new BooleanQueryImpl() {
				{
					setPreBooleanFilter(
						new BooleanFilter() {
							{
								addRequiredTerm(
									"companyId", contextCompany.getCompanyId());
								addRequiredTerm("deleted", false);
								addRequiredTerm("processId", processId);
							}
						});
				}
			});
		searchSearchRequest.setSize(10000);

		SearchSearchResponse searchSearchResponse =
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest);

		SearchHits searchHits = searchSearchResponse.getSearchHits();

		return Page.of(
			Stream.of(
				searchHits.getSearchHits()
			).flatMap(
				List::stream
			).map(
				SearchHit::getDocument
			).map(
				this::_toNode
			).collect(
				Collectors.toList()
			));
	}

	private Node _toNode(Document document) {
		return new Node() {
			{
				id = document.getLong("nodeId");
				initial = GetterUtil.getBoolean(document.getValue("initial"));
				name = document.getString("name");
				terminal = GetterUtil.getBoolean(document.getValue("terminal"));
				type = document.getString("type");
			}
		};
	}

	@Reference
	private SearchRequestExecutor _searchRequestExecutor;

}