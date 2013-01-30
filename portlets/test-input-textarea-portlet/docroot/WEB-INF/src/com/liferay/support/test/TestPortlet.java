/**
 *
 */
package com.liferay.support.test;

import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author lcsontos
 *
 */
public class TestPortlet extends MVCPortlet {

	public void submitText(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		String paramName = actionRequest.getParameter(
			TestPortletConstants.PARAM_NAME);

		actionResponse.setRenderParameter(TestPortletConstants.PARAM_NAME, paramName);
	}

}