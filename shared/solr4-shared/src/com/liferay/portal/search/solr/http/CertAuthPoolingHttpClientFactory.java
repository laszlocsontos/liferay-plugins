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

package com.liferay.portal.search.solr.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.security.KeyStore;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * @author László Csontos
 */
public class CertAuthPoolingHttpClientFactory
	extends BasePoolingHttpClientFactory {

	@Override
	public void afterPropertiesSet() throws Exception {
		_keyStore = initKeyStore(
			_keyStoreType, _keyStoreLocation, _keyStorePassword);

		_trustStore = initKeyStore(
			_trustStoreType, _trustStoreLocation, _trustStorePassword);

		initSSLSocketFactory();

		super.afterPropertiesSet();
	}

	public void setHostnameVerifier(X509HostnameVerifier hostnameVerifier) {
		_hostnameVerifier = hostnameVerifier;
	}

	public void setKeyStoreLocation(String keyStoreLocation) {
		_keyStoreLocation = keyStoreLocation;
	}

	public void setKeyStorePassword(char[] keyStorePassword) {
		_keyStorePassword = keyStorePassword;
	}

	public void setKeyStoreType(String keyStoreType) {
		_keyStoreType = keyStoreType;
	}

	public void setTrustStoreLocation(String trustStoreLocation) {
		_trustStoreLocation = trustStoreLocation;
	}

	public void setTrustStorePassword(char[] trustStorePassword) {
		_trustStorePassword = trustStorePassword;
	}

	public void setTrustStoreType(String trustStoreType) {
		_trustStoreType = trustStoreType;
	}

	@Override
	protected SchemeRegistry getSchemeRegistry() {
		Scheme scheme = new Scheme(
			_DEFAULT_SCHEME_NAME, _DEFAULT_SCHEME_PORT, _socketFactory);

		SchemeRegistry schemeRegistry = new SchemeRegistry();

		schemeRegistry.register(scheme);

		return schemeRegistry;
	}

	protected KeyStore initKeyStore(
			String keyStoreType, String keyStoreLocation,
			char[] keyStorePassword)
		throws Exception {

		if (keyStoreLocation == null) {
			return null;
		}

		KeyStore keyStore = KeyStore.getInstance(keyStoreType);

		InputStream inputStream = loadFile(keyStoreLocation);

		try {
			keyStore.load(inputStream, keyStorePassword);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}

		return keyStore;
	}

	protected void initSSLSocketFactory() {
		_socketFactory = SSLSocketFactory.getSystemSocketFactory();

		if (_keyStore == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Custom keyStore has not been initialized, falling back " +
						"to system's defaults.");
			}

			return;
		}

		if (_trustStore == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Custom trustStore has not been initialized, falling " +
						"back to system's defaults.");
			}

			return;
		}

		try {
			_socketFactory = new SSLSocketFactory(
				_DEFAULT_ALGORITHM, _keyStore,
				String.valueOf(_keyStorePassword), _trustStore, null, null,
				_hostnameVerifier);
		}
		catch (Exception e) {
			_log.error(
				"Custom SSLSocketFactory initialization has failed; falling " +
					"back to system's default.", e);
		}
	}

	protected InputStream loadFile(String fileName)
		throws FileNotFoundException {

		InputStream inputStream = null;

		if (fileName.startsWith("classpath:")) {
			fileName = fileName.substring(10);

			Class<?> clazz = getClass();

			inputStream = clazz.getResourceAsStream(fileName);
		}

		if (inputStream != null) {
			return inputStream;
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				fileName + " hasn't been found on the classpath; trying to " +
					"load from the filesystem.");
		}

		return new FileInputStream(fileName);
	}

	private static final String _DEFAULT_ALGORITHM = SSLSocketFactory.TLS;

	private static final X509HostnameVerifier _DEFAULT_HOSTNAME_VERIFIER =
		SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

	private static final String _DEFAULT_KEYSTORE_TYPE =
		KeyStore.getDefaultType();

	private static final String _DEFAULT_SCHEME_NAME = "https";

	private static final int _DEFAULT_SCHEME_PORT = 443;

	private static Log _log = LogFactoryUtil.getLog(
		CertAuthPoolingHttpClientFactory.class);

	private X509HostnameVerifier _hostnameVerifier = _DEFAULT_HOSTNAME_VERIFIER;
	private KeyStore _keyStore;
	private String _keyStoreLocation;
	private char[] _keyStorePassword;
	private String _keyStoreType = _DEFAULT_KEYSTORE_TYPE;
	private SSLSocketFactory _socketFactory;
	private KeyStore _trustStore;
	private String _trustStoreLocation;
	private char[] _trustStorePassword;
	private String _trustStoreType = _DEFAULT_KEYSTORE_TYPE;

}