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

package com.liferay.sampleservicebuilder;

import com.liferay.portlet.expando.model.BaseCustomAttributesDisplay;
import com.liferay.portlet.expando.model.CustomAttributesDisplay;
import com.liferay.sampleservicebuilder.constants.SampleServiceBuilderPortletKeys;
import com.liferay.sampleservicebuilder.model.Foo;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 */
@Component(
	property = {
		"javax.portlet.name=" +
			SampleServiceBuilderPortletKeys.SAMPLE_SERVICE_BUILDER_PORTLET
	},
	service = CustomAttributesDisplay.class
)
public class FooCustomAttributesDisplay extends BaseCustomAttributesDisplay {

	@Override
	public String getClassName() {
		return Foo.class.getName();
	}

}