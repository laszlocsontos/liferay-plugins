<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.support.test.TestPortletConstants" %>

<portlet:defineObjects />

This is the <b>Test Input Textarea</b> portlet.

<liferay-portlet:actionURL name="submitText" var="submitTextURL" />

<aui:form action="<%= submitTextURL %>" method="post" name="fm">
	<liferay-ui:input-textarea param="<%= TestPortletConstants.PARAM_NAME %>" />

	<div class="message">
		<%= ParamUtil.get(request, TestPortletConstants.PARAM_NAME, StringPool.BLANK) %>
	</div>

	<aui:button-row>
		<input type="submit" value="<liferay-ui:message key="send" />" />
	</aui:button-row>
</aui:form>