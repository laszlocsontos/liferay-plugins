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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.support.service.ClpSerializer;
import com.liferay.support.service.TestLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TestClp extends BaseModelImpl<Test> implements Test {
	public TestClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Test.class;
	}

	@Override
	public String getModelClassName() {
		return Test.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _testId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testId", getTestId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("test", getTest());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testId = (Long)attributes.get("testId");

		if (testId != null) {
			setTestId(testId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String test = (String)attributes.get("test");

		if (test != null) {
			setTest(test);
		}
	}

	@Override
	public long getTestId() {
		return _testId;
	}

	@Override
	public void setTestId(long testId) {
		_testId = testId;

		if (_testRemoteModel != null) {
			try {
				Class<?> clazz = _testRemoteModel.getClass();

				Method method = clazz.getMethod("setTestId", long.class);

				method.invoke(_testRemoteModel, testId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_testRemoteModel != null) {
			try {
				Class<?> clazz = _testRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_testRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_testRemoteModel != null) {
			try {
				Class<?> clazz = _testRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_testRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getTest() {
		return _test;
	}

	@Override
	public void setTest(String test) {
		_test = test;

		if (_testRemoteModel != null) {
			try {
				Class<?> clazz = _testRemoteModel.getClass();

				Method method = clazz.getMethod("setTest", String.class);

				method.invoke(_testRemoteModel, test);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTestRemoteModel() {
		return _testRemoteModel;
	}

	public void setTestRemoteModel(BaseModel<?> testRemoteModel) {
		_testRemoteModel = testRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _testRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_testRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			TestLocalServiceUtil.addTest(this);
		}
		else {
			TestLocalServiceUtil.updateTest(this);
		}
	}

	@Override
	public Test toEscapedModel() {
		return (Test)ProxyUtil.newProxyInstance(Test.class.getClassLoader(),
			new Class[] { Test.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TestClp clone = new TestClp();

		clone.setTestId(getTestId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setTest(getTest());

		return clone;
	}

	@Override
	public int compareTo(Test test) {
		long primaryKey = test.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestClp)) {
			return false;
		}

		TestClp test = (TestClp)obj;

		long primaryKey = test.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{testId=");
		sb.append(getTestId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", test=");
		sb.append(getTest());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.support.model.Test");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>testId</column-name><column-value><![CDATA[");
		sb.append(getTestId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>test</column-name><column-value><![CDATA[");
		sb.append(getTest());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _testId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _test;
	private BaseModel<?> _testRemoteModel;
}