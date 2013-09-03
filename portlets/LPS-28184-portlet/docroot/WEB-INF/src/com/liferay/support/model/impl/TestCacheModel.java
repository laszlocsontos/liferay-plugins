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

package com.liferay.support.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.support.model.Test;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Test in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Test
 * @generated
 */
public class TestCacheModel implements CacheModel<Test>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{testId=");
		sb.append(testId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", test=");
		sb.append(test);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Test toEntityModel() {
		TestImpl testImpl = new TestImpl();

		testImpl.setTestId(testId);
		testImpl.setCompanyId(companyId);
		testImpl.setUserId(userId);

		if (test == null) {
			testImpl.setTest(StringPool.BLANK);
		}
		else {
			testImpl.setTest(test);
		}

		testImpl.resetOriginalValues();

		return testImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		test = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (test == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(test);
		}
	}

	public long testId;
	public long companyId;
	public long userId;
	public String test;
}