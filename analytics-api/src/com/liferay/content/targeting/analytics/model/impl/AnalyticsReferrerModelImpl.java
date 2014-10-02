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

package com.liferay.content.targeting.analytics.model.impl;

import com.liferay.content.targeting.analytics.model.AnalyticsReferrer;
import com.liferay.content.targeting.analytics.model.AnalyticsReferrerModel;
import com.liferay.content.targeting.analytics.model.AnalyticsReferrerSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the AnalyticsReferrer service. Represents a row in the &quot;CT_Analytics_AnalyticsReferrer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.content.targeting.analytics.model.AnalyticsReferrerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnalyticsReferrerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsReferrerImpl
 * @see com.liferay.content.targeting.analytics.model.AnalyticsReferrer
 * @see com.liferay.content.targeting.analytics.model.AnalyticsReferrerModel
 * @generated
 */
@JSON(strict = true)
public class AnalyticsReferrerModelImpl extends BaseModelImpl<AnalyticsReferrer>
	implements AnalyticsReferrerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a analytics referrer model instance should use the {@link com.liferay.content.targeting.analytics.model.AnalyticsReferrer} interface instead.
	 */
	public static final String TABLE_NAME = "CT_Analytics_AnalyticsReferrer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "analyticsReferrerId", Types.BIGINT },
			{ "analyticsEventId", Types.BIGINT },
			{ "referrerClassName", Types.VARCHAR },
			{ "referrerClassPK", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table CT_Analytics_AnalyticsReferrer (analyticsReferrerId LONG not null primary key,analyticsEventId LONG,referrerClassName VARCHAR(75) null,referrerClassPK LONG)";
	public static final String TABLE_SQL_DROP = "drop table CT_Analytics_AnalyticsReferrer";
	public static final String ORDER_BY_JPQL = " ORDER BY analyticsReferrer.analyticsReferrerId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CT_Analytics_AnalyticsReferrer.analyticsReferrerId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.content.targeting.analytics.model.AnalyticsReferrer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.content.targeting.analytics.model.AnalyticsReferrer"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.content.targeting.analytics.model.AnalyticsReferrer"),
			true);
	public static long ANALYTICSEVENTID_COLUMN_BITMASK = 1L;
	public static long REFERRERCLASSNAME_COLUMN_BITMASK = 2L;
	public static long REFERRERCLASSPK_COLUMN_BITMASK = 4L;
	public static long ANALYTICSREFERRERID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static AnalyticsReferrer toModel(AnalyticsReferrerSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AnalyticsReferrer model = new AnalyticsReferrerImpl();

		model.setAnalyticsReferrerId(soapModel.getAnalyticsReferrerId());
		model.setAnalyticsEventId(soapModel.getAnalyticsEventId());
		model.setReferrerClassName(soapModel.getReferrerClassName());
		model.setReferrerClassPK(soapModel.getReferrerClassPK());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AnalyticsReferrer> toModels(
		AnalyticsReferrerSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AnalyticsReferrer> models = new ArrayList<AnalyticsReferrer>(soapModels.length);

		for (AnalyticsReferrerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.content.targeting.analytics.model.AnalyticsReferrer"));

	public AnalyticsReferrerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _analyticsReferrerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAnalyticsReferrerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _analyticsReferrerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AnalyticsReferrer.class;
	}

	@Override
	public String getModelClassName() {
		return AnalyticsReferrer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("analyticsReferrerId", getAnalyticsReferrerId());
		attributes.put("analyticsEventId", getAnalyticsEventId());
		attributes.put("referrerClassName", getReferrerClassName());
		attributes.put("referrerClassPK", getReferrerClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long analyticsReferrerId = (Long)attributes.get("analyticsReferrerId");

		if (analyticsReferrerId != null) {
			setAnalyticsReferrerId(analyticsReferrerId);
		}

		Long analyticsEventId = (Long)attributes.get("analyticsEventId");

		if (analyticsEventId != null) {
			setAnalyticsEventId(analyticsEventId);
		}

		String referrerClassName = (String)attributes.get("referrerClassName");

		if (referrerClassName != null) {
			setReferrerClassName(referrerClassName);
		}

		Long referrerClassPK = (Long)attributes.get("referrerClassPK");

		if (referrerClassPK != null) {
			setReferrerClassPK(referrerClassPK);
		}
	}

	@JSON
	@Override
	public long getAnalyticsReferrerId() {
		return _analyticsReferrerId;
	}

	@Override
	public void setAnalyticsReferrerId(long analyticsReferrerId) {
		_analyticsReferrerId = analyticsReferrerId;
	}

	@JSON
	@Override
	public long getAnalyticsEventId() {
		return _analyticsEventId;
	}

	@Override
	public void setAnalyticsEventId(long analyticsEventId) {
		_columnBitmask |= ANALYTICSEVENTID_COLUMN_BITMASK;

		if (!_setOriginalAnalyticsEventId) {
			_setOriginalAnalyticsEventId = true;

			_originalAnalyticsEventId = _analyticsEventId;
		}

		_analyticsEventId = analyticsEventId;
	}

	public long getOriginalAnalyticsEventId() {
		return _originalAnalyticsEventId;
	}

	@JSON
	@Override
	public String getReferrerClassName() {
		if (_referrerClassName == null) {
			return StringPool.BLANK;
		}
		else {
			return _referrerClassName;
		}
	}

	@Override
	public void setReferrerClassName(String referrerClassName) {
		_columnBitmask |= REFERRERCLASSNAME_COLUMN_BITMASK;

		if (_originalReferrerClassName == null) {
			_originalReferrerClassName = _referrerClassName;
		}

		_referrerClassName = referrerClassName;
	}

	public String getOriginalReferrerClassName() {
		return GetterUtil.getString(_originalReferrerClassName);
	}

	@JSON
	@Override
	public long getReferrerClassPK() {
		return _referrerClassPK;
	}

	@Override
	public void setReferrerClassPK(long referrerClassPK) {
		_columnBitmask |= REFERRERCLASSPK_COLUMN_BITMASK;

		if (!_setOriginalReferrerClassPK) {
			_setOriginalReferrerClassPK = true;

			_originalReferrerClassPK = _referrerClassPK;
		}

		_referrerClassPK = referrerClassPK;
	}

	public long getOriginalReferrerClassPK() {
		return _originalReferrerClassPK;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			AnalyticsReferrer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AnalyticsReferrer toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AnalyticsReferrer)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AnalyticsReferrerImpl analyticsReferrerImpl = new AnalyticsReferrerImpl();

		analyticsReferrerImpl.setAnalyticsReferrerId(getAnalyticsReferrerId());
		analyticsReferrerImpl.setAnalyticsEventId(getAnalyticsEventId());
		analyticsReferrerImpl.setReferrerClassName(getReferrerClassName());
		analyticsReferrerImpl.setReferrerClassPK(getReferrerClassPK());

		analyticsReferrerImpl.resetOriginalValues();

		return analyticsReferrerImpl;
	}

	@Override
	public int compareTo(AnalyticsReferrer analyticsReferrer) {
		long primaryKey = analyticsReferrer.getPrimaryKey();

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

		if (!(obj instanceof AnalyticsReferrer)) {
			return false;
		}

		AnalyticsReferrer analyticsReferrer = (AnalyticsReferrer)obj;

		long primaryKey = analyticsReferrer.getPrimaryKey();

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
	public void resetOriginalValues() {
		AnalyticsReferrerModelImpl analyticsReferrerModelImpl = this;

		analyticsReferrerModelImpl._originalAnalyticsEventId = analyticsReferrerModelImpl._analyticsEventId;

		analyticsReferrerModelImpl._setOriginalAnalyticsEventId = false;

		analyticsReferrerModelImpl._originalReferrerClassName = analyticsReferrerModelImpl._referrerClassName;

		analyticsReferrerModelImpl._originalReferrerClassPK = analyticsReferrerModelImpl._referrerClassPK;

		analyticsReferrerModelImpl._setOriginalReferrerClassPK = false;

		analyticsReferrerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AnalyticsReferrer> toCacheModel() {
		AnalyticsReferrerCacheModel analyticsReferrerCacheModel = new AnalyticsReferrerCacheModel();

		analyticsReferrerCacheModel.analyticsReferrerId = getAnalyticsReferrerId();

		analyticsReferrerCacheModel.analyticsEventId = getAnalyticsEventId();

		analyticsReferrerCacheModel.referrerClassName = getReferrerClassName();

		String referrerClassName = analyticsReferrerCacheModel.referrerClassName;

		if ((referrerClassName != null) && (referrerClassName.length() == 0)) {
			analyticsReferrerCacheModel.referrerClassName = null;
		}

		analyticsReferrerCacheModel.referrerClassPK = getReferrerClassPK();

		return analyticsReferrerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{analyticsReferrerId=");
		sb.append(getAnalyticsReferrerId());
		sb.append(", analyticsEventId=");
		sb.append(getAnalyticsEventId());
		sb.append(", referrerClassName=");
		sb.append(getReferrerClassName());
		sb.append(", referrerClassPK=");
		sb.append(getReferrerClassPK());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.content.targeting.analytics.model.AnalyticsReferrer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>analyticsReferrerId</column-name><column-value><![CDATA[");
		sb.append(getAnalyticsReferrerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>analyticsEventId</column-name><column-value><![CDATA[");
		sb.append(getAnalyticsEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referrerClassName</column-name><column-value><![CDATA[");
		sb.append(getReferrerClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referrerClassPK</column-name><column-value><![CDATA[");
		sb.append(getReferrerClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = AnalyticsReferrer.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			AnalyticsReferrer.class
		};
	private long _analyticsReferrerId;
	private long _analyticsEventId;
	private long _originalAnalyticsEventId;
	private boolean _setOriginalAnalyticsEventId;
	private String _referrerClassName;
	private String _originalReferrerClassName;
	private long _referrerClassPK;
	private long _originalReferrerClassPK;
	private boolean _setOriginalReferrerClassPK;
	private long _columnBitmask;
	private AnalyticsReferrer _escapedModel;
}