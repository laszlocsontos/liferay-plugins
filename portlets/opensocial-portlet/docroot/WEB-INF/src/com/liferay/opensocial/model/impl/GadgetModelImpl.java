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

package com.liferay.opensocial.model.impl;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.model.GadgetModel;
import com.liferay.opensocial.model.GadgetSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Gadget service. Represents a row in the &quot;OpenSocial_Gadget&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.opensocial.model.GadgetModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GadgetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GadgetImpl
 * @see com.liferay.opensocial.model.Gadget
 * @see com.liferay.opensocial.model.GadgetModel
 * @generated
 */
@JSON(strict = true)
public class GadgetModelImpl extends BaseModelImpl<Gadget>
	implements GadgetModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a gadget model instance should use the {@link com.liferay.opensocial.model.Gadget} interface instead.
	 */
	public static final String TABLE_NAME = "OpenSocial_Gadget";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "gadgetId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "url", Types.VARCHAR },
			{ "portletCategoryNames", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table OpenSocial_Gadget (uuid_ VARCHAR(75) null,gadgetId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,url STRING null,portletCategoryNames STRING null)";
	public static final String TABLE_SQL_DROP = "drop table OpenSocial_Gadget";
	public static final String ORDER_BY_JPQL = " ORDER BY gadget.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY OpenSocial_Gadget.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.opensocial.model.Gadget"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.opensocial.model.Gadget"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.opensocial.model.Gadget"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static long URL_COLUMN_BITMASK = 2L;
	public static long UUID_COLUMN_BITMASK = 4L;
	public static long NAME_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Gadget toModel(GadgetSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Gadget model = new GadgetImpl();

		model.setUuid(soapModel.getUuid());
		model.setGadgetId(soapModel.getGadgetId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setUrl(soapModel.getUrl());
		model.setPortletCategoryNames(soapModel.getPortletCategoryNames());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Gadget> toModels(GadgetSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Gadget> models = new ArrayList<Gadget>(soapModels.length);

		for (GadgetSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.opensocial.model.Gadget"));

	public GadgetModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _gadgetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setGadgetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gadgetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Gadget.class;
	}

	@Override
	public String getModelClassName() {
		return Gadget.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("gadgetId", getGadgetId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("portletCategoryNames", getPortletCategoryNames());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long gadgetId = (Long)attributes.get("gadgetId");

		if (gadgetId != null) {
			setGadgetId(gadgetId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String portletCategoryNames = (String)attributes.get(
				"portletCategoryNames");

		if (portletCategoryNames != null) {
			setPortletCategoryNames(portletCategoryNames);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getGadgetId() {
		return _gadgetId;
	}

	@Override
	public void setGadgetId(long gadgetId) {
		_gadgetId = gadgetId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		_columnBitmask |= URL_COLUMN_BITMASK;

		if (_originalUrl == null) {
			_originalUrl = _url;
		}

		_url = url;
	}

	public String getOriginalUrl() {
		return GetterUtil.getString(_originalUrl);
	}

	@JSON
	@Override
	public String getPortletCategoryNames() {
		if (_portletCategoryNames == null) {
			return StringPool.BLANK;
		}
		else {
			return _portletCategoryNames;
		}
	}

	@Override
	public void setPortletCategoryNames(String portletCategoryNames) {
		_portletCategoryNames = portletCategoryNames;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Gadget.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Gadget.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Gadget toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Gadget)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		GadgetImpl gadgetImpl = new GadgetImpl();

		gadgetImpl.setUuid(getUuid());
		gadgetImpl.setGadgetId(getGadgetId());
		gadgetImpl.setCompanyId(getCompanyId());
		gadgetImpl.setCreateDate(getCreateDate());
		gadgetImpl.setModifiedDate(getModifiedDate());
		gadgetImpl.setName(getName());
		gadgetImpl.setUrl(getUrl());
		gadgetImpl.setPortletCategoryNames(getPortletCategoryNames());

		gadgetImpl.resetOriginalValues();

		return gadgetImpl;
	}

	@Override
	public int compareTo(Gadget gadget) {
		int value = 0;

		value = getName().compareTo(gadget.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Gadget)) {
			return false;
		}

		Gadget gadget = (Gadget)obj;

		long primaryKey = gadget.getPrimaryKey();

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
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		GadgetModelImpl gadgetModelImpl = this;

		gadgetModelImpl._originalUuid = gadgetModelImpl._uuid;

		gadgetModelImpl._originalCompanyId = gadgetModelImpl._companyId;

		gadgetModelImpl._setOriginalCompanyId = false;

		gadgetModelImpl._originalUrl = gadgetModelImpl._url;

		gadgetModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Gadget> toCacheModel() {
		GadgetCacheModel gadgetCacheModel = new GadgetCacheModel();

		gadgetCacheModel.uuid = getUuid();

		String uuid = gadgetCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			gadgetCacheModel.uuid = null;
		}

		gadgetCacheModel.gadgetId = getGadgetId();

		gadgetCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			gadgetCacheModel.createDate = createDate.getTime();
		}
		else {
			gadgetCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			gadgetCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			gadgetCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		gadgetCacheModel.name = getName();

		String name = gadgetCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			gadgetCacheModel.name = null;
		}

		gadgetCacheModel.url = getUrl();

		String url = gadgetCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			gadgetCacheModel.url = null;
		}

		gadgetCacheModel.portletCategoryNames = getPortletCategoryNames();

		String portletCategoryNames = gadgetCacheModel.portletCategoryNames;

		if ((portletCategoryNames != null) &&
				(portletCategoryNames.length() == 0)) {
			gadgetCacheModel.portletCategoryNames = null;
		}

		return gadgetCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", gadgetId=");
		sb.append(getGadgetId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", portletCategoryNames=");
		sb.append(getPortletCategoryNames());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.opensocial.model.Gadget");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gadgetId</column-name><column-value><![CDATA[");
		sb.append(getGadgetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletCategoryNames</column-name><column-value><![CDATA[");
		sb.append(getPortletCategoryNames());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Gadget.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] { Gadget.class };
	private String _uuid;
	private String _originalUuid;
	private long _gadgetId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _originalUrl;
	private String _portletCategoryNames;
	private long _columnBitmask;
	private Gadget _escapedModel;
}