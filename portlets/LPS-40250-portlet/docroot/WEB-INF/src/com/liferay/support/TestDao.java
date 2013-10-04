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

package com.liferay.support;

import org.apache.log4j.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lcsontos
 */
public class TestDao extends JdbcTemplate {

	@Override
	public void afterPropertiesSet() {
		int dummyCount = queryForInt("SELECT COUNT(*) FROM dual");

		_log.debug("*** ROWCOUNT: " + dummyCount);
	}

	private static Logger _log = Logger.getLogger(TestDao.class);

}