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

package com.liferay.support.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TestSoap implements Serializable {
	public static TestSoap toSoapModel(Test model) {
		TestSoap soapModel = new TestSoap();

		soapModel.setTestId(model.getTestId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setTest(model.getTest());

		return soapModel;
	}

	public static TestSoap[] toSoapModels(Test[] models) {
		TestSoap[] soapModels = new TestSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestSoap[][] toSoapModels(Test[][] models) {
		TestSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TestSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestSoap[] toSoapModels(List<Test> models) {
		List<TestSoap> soapModels = new ArrayList<TestSoap>(models.size());

		for (Test model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestSoap[soapModels.size()]);
	}

	public TestSoap() {
	}

	public long getPrimaryKey() {
		return _testId;
	}

	public void setPrimaryKey(long pk) {
		setTestId(pk);
	}

	public long getTestId() {
		return _testId;
	}

	public void setTestId(long testId) {
		_testId = testId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getTest() {
		return _test;
	}

	public void setTest(String test) {
		_test = test;
	}

	private long _testId;
	private long _companyId;
	private long _userId;
	private String _test;
}