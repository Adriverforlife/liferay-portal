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

package com.liferay.portal.search.solr7.internal.facet;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.internal.facet.FacetImpl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.solr.common.util.NamedList;

import org.junit.Test;

/**
 * @author Bryan Engler
 */
public class FacetCollectorTest {

	@Test
	public void testSolrFacetFieldCollectorCountType() {
		NamedList<Object> namedList1 = new NamedList();

		namedList1.add("count", Long.valueOf(3));
		namedList1.add("val", "alpha");

		NamedList<Object> namedList2 = new NamedList();

		namedList2.add("count", Integer.valueOf(7));
		namedList2.add("val", "bravo");

		List<NamedList<Object>> bucketNamedList = new ArrayList<>();

		bucketNamedList.add(namedList1);
		bucketNamedList.add(namedList2);

		NamedList<Object> fieldNamedList = new NamedList();

		fieldNamedList.add("buckets", bucketNamedList);

		NamedList<Object> namedList3 = new NamedList();

		namedList3.add("field", fieldNamedList);

		Facet facet = new FacetImpl("field", new SearchContext());

		SolrFacetFieldCollector solrFacetFieldCollector =
			new SolrFacetFieldCollector(facet, namedList3);

		TermCollector termCollector = solrFacetFieldCollector.getTermCollector(
			"alpha");

		Assert.assertEquals(3, termCollector.getFrequency());

		termCollector = solrFacetFieldCollector.getTermCollector("bravo");

		Assert.assertEquals(7, termCollector.getFrequency());
	}

	@Test
	public void testSolrFacetQueryCollector() {
		NamedList<Object> namedList1 = new NamedList();

		String bucket1 = "field_alpha";

		NamedList<Object> namedList2 = new NamedList();

		namedList2.add("count", Long.valueOf(3));

		namedList1.add(bucket1, namedList2);

		String bucket2 = "field_bravo";

		NamedList<Object> namedList3 = new NamedList();

		namedList3.add("count", Integer.valueOf(7));

		namedList1.add(bucket2, namedList3);

		Facet facet = new FacetImpl("field", new SearchContext());

		SolrFacetQueryCollector solrFacetFieldCollector =
			new SolrFacetQueryCollector(facet, namedList1);

		TermCollector termCollector = solrFacetFieldCollector.getTermCollector(
			"alpha");

		Assert.assertEquals(3, termCollector.getFrequency());

		termCollector = solrFacetFieldCollector.getTermCollector("bravo");

		Assert.assertEquals(7, termCollector.getFrequency());
	}

}