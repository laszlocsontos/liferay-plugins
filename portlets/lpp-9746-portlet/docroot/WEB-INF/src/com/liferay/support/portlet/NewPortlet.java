package com.liferay.support.portlet;

/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.support.util.PortalInstances;
import com.liferay.support.util.TestPropsValues;
import com.liferay.support.util.UserTestUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author lcsontos
 */
public class NewPortlet extends MVCPortlet {

	@Override
	public void destroy() {
		super.destroy();

		List<Runnable> pendingTasks = _executorService.shutdownNow();

		if ((_finishLatch.getCount() > 0) && _log.isInfoEnabled()) {
			_log.info(pendingTasks.size() + " tasks will never be executed.");
		}
	}

	@Override
	public void init() throws PortletException {
		super.init();

		try {
			_initExecutor();

			_initUser();
		}
		catch (Exception e) {
			_user = null;

			_log.error(e);
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		if (!_processingFlag.compareAndSet(false, true)) {
			return;
		}

		new ProgressChecker().start();

		for (int i = 0; i < _NUM_SITES; i++) {
			Runnable dataGenerator = new DataGenerator(_finishLatch, _user);

			_executorService.submit(dataGenerator);
		}

		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(_processingFlag.get());

		jsonArray.put(_NUM_SITES - _finishLatch.getCount());
		jsonArray.put(_NUM_SITES);

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	private void _initExecutor() {
		int numProc = Runtime.getRuntime().availableProcessors();

		_executorService = Executors.newFixedThreadPool(numProc);

		_finishLatch = new CountDownLatch(_NUM_SITES);

		_processingFlag = new AtomicBoolean(false);
	}

	private void _initUser() throws Exception {
		Company company = CompanyLocalServiceUtil.getCompanyByWebId(
			"liferay.com");

		PortalInstances.defaultCompanyId = company.getCompanyId();
		TestPropsValues.companyId = company.getCompanyId();

		TestPropsValues.userId = UserLocalServiceUtil.getDefaultUserId(
			company.getCompanyId());

		_user = UserTestUtil.addUser();
	}

	private static final int _NUM_SITES = 500;

	private static Log _log = LogFactoryUtil.getLog(NewPortlet.class);

	private ExecutorService _executorService;
	private CountDownLatch _finishLatch;
	private AtomicBoolean _processingFlag;
	private User _user;

	private class ProgressChecker extends Thread {

		@Override
		public void run() {
			try {
				for (; !_finishLatch.await(100, TimeUnit.MILLISECONDS););

				_processingFlag.set(false);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

	}

}