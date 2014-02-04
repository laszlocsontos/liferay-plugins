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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.support.util.GroupTestUtil;

import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

/**
 * @author lcsontos
 */
public class DataGenerator implements Runnable {

	public DataGenerator(CountDownLatch finishedLatch, User user) {
		_finishedLatch = finishedLatch;

		_user = user;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void run() {
		try {
			Group group = GroupTestUtil.addGroup();

			long userId = _user.getUserId();

			UserLocalServiceUtil.addGroupUser(group.getGroupId(), userId);

			long classPK = group.getGroupId();

			String type = "General";

			int displayDateDay, expirationDateDay;
			int displayDateMonth, expirationDateMonth;
			int displayDateYear, expirationDateYear;

			displayDateDay = expirationDateDay = _NOW.get(
				Calendar.DAY_OF_MONTH);
			displayDateMonth = expirationDateMonth = _NOW.get(Calendar.MONTH);
			displayDateYear = _NOW.get(Calendar.YEAR);

			expirationDateYear = displayDateYear + 1;

			int displayDateHour, expirationDateHour;
			int displayDateMinute, expirationDateMinute;

			displayDateHour = expirationDateHour = _NOW.get(Calendar.HOUR);
			displayDateMinute = expirationDateMinute = _NOW.get(
				Calendar.MINUTE);

			int priority = 1;
			boolean alert = false;

			for (int i = 0; i < _NUM_ANNOUNCEMENTS; i++) {
				String content = StringUtil.randomString();
				String title = StringUtil.randomString();

				AnnouncementsEntryLocalServiceUtil.addEntry(
					userId, _CLASSNAME_ID, classPK, title, content, null, type,
					displayDateMonth, displayDateDay, displayDateYear,
					displayDateHour, displayDateMinute, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, priority, alert);
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
		finally {
			_finishedLatch.countDown();
		}
	}

	static {
		_CLASSNAME_ID = ClassNameLocalServiceUtil.getClassNameId(Group.class);

		_NOW = Calendar.getInstance();
	}

	private static final long _CLASSNAME_ID;

	private static final Calendar _NOW;

	private static final int _NUM_ANNOUNCEMENTS = 10;

	private static Log _log = LogFactoryUtil.getLog(DataGenerator.class);

	private CountDownLatch _finishedLatch;
	private User _user;

}