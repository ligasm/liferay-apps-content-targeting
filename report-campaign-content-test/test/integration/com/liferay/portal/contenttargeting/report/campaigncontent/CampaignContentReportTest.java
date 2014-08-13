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

package com.liferay.portal.contenttargeting.report.campaigncontent;

import com.liferay.osgi.util.service.ServiceTrackerUtil;
import com.liferay.portal.contenttargeting.analytics.service.AnalyticsEventLocalService;
import com.liferay.portal.contenttargeting.api.model.Report;
import com.liferay.portal.contenttargeting.api.model.ReportsRegistry;
import com.liferay.portal.contenttargeting.model.Campaign;
import com.liferay.portal.contenttargeting.report.campaigncontent.service.CampaignContentLocalService;
import com.liferay.portal.contenttargeting.report.campaigncontent.test.util.TestUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.journal.model.JournalArticle;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
public class CampaignContentReportTest {

	@Before
	public void setUp() {
		try {
			_bundle.start();
		}
		catch (BundleException e) {
			e.printStackTrace();
		}

		_analyticsEventLocalService = ServiceTrackerUtil.getService(
			AnalyticsEventLocalService.class, _bundle.getBundleContext());
		_campaignContentLocalService = ServiceTrackerUtil.getService(
			CampaignContentLocalService.class, _bundle.getBundleContext());
		_reportsRegistry = ServiceTrackerUtil.getService(
			ReportsRegistry.class, _bundle.getBundleContext());
	}

	@Test
	public void testCampaignContentReport() throws Exception {
		ServiceContext serviceContext = TestUtil.getServiceContext();

		long campaignId = 5;

		int initialCampaignContentCount =
			_campaignContentLocalService.getCampaignContentsCount(campaignId);

		// Obtain report from registry

		Report report = _reportsRegistry.getReport("CampaignContentReport");

		// Test update report without analytics

		report.updateReport(campaignId);

		Assert.assertEquals(
			initialCampaignContentCount,
			_campaignContentLocalService.getCampaignContentsCount(campaignId));

		// Add analytics

		_analyticsEventLocalService.addAnalyticsEvent(
			TestUtil.getUserId(), 1, JournalArticle.class.getName(), 2,
			Campaign.class.getName(), campaignId, null, "view", "127.0.0.1",
			"ES", "User Agent", "http://localhost", null, serviceContext);

		// Test update report with analytics

		report.updateReport(campaignId);

		Assert.assertEquals(
			initialCampaignContentCount + 1,
			_campaignContentLocalService.getCampaignContentsCount(campaignId));
	}

	private AnalyticsEventLocalService _analyticsEventLocalService;

	@ArquillianResource
	private Bundle _bundle;

	private CampaignContentLocalService
		_campaignContentLocalService;
	private ReportsRegistry _reportsRegistry;

}