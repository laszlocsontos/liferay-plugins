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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Test}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Test
 * @generated
 */
public class TestWrapper implements Test, ModelWrapper<Test> {
	public TestWrapper(Test test) {
		_test = test;
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

	/**
	* Returns the primary key of this test.
	*
	* @return the primary key of this test
	*/
	@Override
	public long getPrimaryKey() {
		return _test.getPrimaryKey();
	}

	/**
	* Sets the primary key of this test.
	*
	* @param primaryKey the primary key of this test
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_test.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the test ID of this test.
	*
	* @return the test ID of this test
	*/
	@Override
	public long getTestId() {
		return _test.getTestId();
	}

	/**
	* Sets the test ID of this test.
	*
	* @param testId the test ID of this test
	*/
	@Override
	public void setTestId(long testId) {
		_test.setTestId(testId);
	}

	/**
	* Returns the company ID of this test.
	*
	* @return the company ID of this test
	*/
	@Override
	public long getCompanyId() {
		return _test.getCompanyId();
	}

	/**
	* Sets the company ID of this test.
	*
	* @param companyId the company ID of this test
	*/
	@Override
	public void setCompanyId(long companyId) {
		_test.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this test.
	*
	* @return the user ID of this test
	*/
	@Override
	public long getUserId() {
		return _test.getUserId();
	}

	/**
	* Sets the user ID of this test.
	*
	* @param userId the user ID of this test
	*/
	@Override
	public void setUserId(long userId) {
		_test.setUserId(userId);
	}

	/**
	* Returns the user uuid of this test.
	*
	* @return the user uuid of this test
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _test.getUserUuid();
	}

	/**
	* Sets the user uuid of this test.
	*
	* @param userUuid the user uuid of this test
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_test.setUserUuid(userUuid);
	}

	/**
	* Returns the test of this test.
	*
	* @return the test of this test
	*/
	@Override
	public java.lang.String getTest() {
		return _test.getTest();
	}

	/**
	* Sets the test of this test.
	*
	* @param test the test of this test
	*/
	@Override
	public void setTest(java.lang.String test) {
		_test.setTest(test);
	}

	@Override
	public boolean isNew() {
		return _test.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_test.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _test.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_test.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _test.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _test.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_test.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _test.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_test.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_test.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_test.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TestWrapper((Test)_test.clone());
	}

	@Override
	public int compareTo(Test test) {
		return _test.compareTo(test);
	}

	@Override
	public int hashCode() {
		return _test.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<Test> toCacheModel() {
		return _test.toCacheModel();
	}

	@Override
	public Test toEscapedModel() {
		return new TestWrapper(_test.toEscapedModel());
	}

	@Override
	public Test toUnescapedModel() {
		return new TestWrapper(_test.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _test.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _test.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_test.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestWrapper)) {
			return false;
		}

		TestWrapper testWrapper = (TestWrapper)obj;

		if (Validator.equals(_test, testWrapper._test)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Test getWrappedTest() {
		return _test;
	}

	@Override
	public Test getWrappedModel() {
		return _test;
	}

	@Override
	public void resetOriginalValues() {
		_test.resetOriginalValues();
	}

	private Test _test;
}