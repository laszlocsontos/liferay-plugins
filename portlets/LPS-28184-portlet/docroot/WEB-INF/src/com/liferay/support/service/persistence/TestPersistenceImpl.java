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

package com.liferay.support.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.support.NoSuchTestException;
import com.liferay.support.model.Test;
import com.liferay.support.model.impl.TestImpl;
import com.liferay.support.model.impl.TestModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the test service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestPersistence
 * @see TestUtil
 * @generated
 */
public class TestPersistenceImpl extends BasePersistenceImpl<Test>
	implements TestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TestUtil} to access the test persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestModelImpl.FINDER_CACHE_ENABLED, TestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestModelImpl.FINDER_CACHE_ENABLED, TestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_U = new FinderPath(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestModelImpl.FINDER_CACHE_ENABLED, TestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U = new FinderPath(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestModelImpl.FINDER_CACHE_ENABLED, TestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			TestModelImpl.COMPANYID_COLUMN_BITMASK |
			TestModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_U = new FinderPath(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the tests where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the matching tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Test> findByC_U(long companyId, long userId)
		throws SystemException {
		return findByC_U(companyId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tests where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.support.model.impl.TestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tests
	 * @param end the upper bound of the range of tests (not inclusive)
	 * @return the range of matching tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Test> findByC_U(long companyId, long userId, int start, int end)
		throws SystemException {
		return findByC_U(companyId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tests where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.support.model.impl.TestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tests
	 * @param end the upper bound of the range of tests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Test> findByC_U(long companyId, long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U;
			finderArgs = new Object[] { companyId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_U;
			finderArgs = new Object[] {
					companyId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Test> list = (List<Test>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Test test : list) {
				if ((companyId != test.getCompanyId()) ||
						(userId != test.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEST_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Test>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Test>(list);
				}
				else {
					list = (List<Test>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first test in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching test
	 * @throws com.liferay.support.NoSuchTestException if a matching test could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test findByC_U_First(long companyId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTestException, SystemException {
		Test test = fetchByC_U_First(companyId, userId, orderByComparator);

		if (test != null) {
			return test;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTestException(msg.toString());
	}

	/**
	 * Returns the first test in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching test, or <code>null</code> if a matching test could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test fetchByC_U_First(long companyId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Test> list = findByC_U(companyId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last test in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching test
	 * @throws com.liferay.support.NoSuchTestException if a matching test could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test findByC_U_Last(long companyId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTestException, SystemException {
		Test test = fetchByC_U_Last(companyId, userId, orderByComparator);

		if (test != null) {
			return test;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTestException(msg.toString());
	}

	/**
	 * Returns the last test in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching test, or <code>null</code> if a matching test could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test fetchByC_U_Last(long companyId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_U(companyId, userId);

		if (count == 0) {
			return null;
		}

		List<Test> list = findByC_U(companyId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tests before and after the current test in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param testId the primary key of the current test
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next test
	 * @throws com.liferay.support.NoSuchTestException if a test with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test[] findByC_U_PrevAndNext(long testId, long companyId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTestException, SystemException {
		Test test = findByPrimaryKey(testId);

		Session session = null;

		try {
			session = openSession();

			Test[] array = new TestImpl[3];

			array[0] = getByC_U_PrevAndNext(session, test, companyId, userId,
					orderByComparator, true);

			array[1] = test;

			array[2] = getByC_U_PrevAndNext(session, test, companyId, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Test getByC_U_PrevAndNext(Session session, Test test,
		long companyId, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEST_WHERE);

		query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_U_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(test);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Test> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tests where companyId = &#63; and userId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_U(long companyId, long userId)
		throws SystemException {
		for (Test test : findByC_U(companyId, userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(test);
		}
	}

	/**
	 * Returns the number of tests where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the number of matching tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_U(long companyId, long userId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_U;

		Object[] finderArgs = new Object[] { companyId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEST_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 = "test.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_U_USERID_2 = "test.userId = ?";

	public TestPersistenceImpl() {
		setModelClass(Test.class);
	}

	/**
	 * Caches the test in the entity cache if it is enabled.
	 *
	 * @param test the test
	 */
	@Override
	public void cacheResult(Test test) {
		EntityCacheUtil.putResult(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestImpl.class, test.getPrimaryKey(), test);

		test.resetOriginalValues();
	}

	/**
	 * Caches the tests in the entity cache if it is enabled.
	 *
	 * @param tests the tests
	 */
	@Override
	public void cacheResult(List<Test> tests) {
		for (Test test : tests) {
			if (EntityCacheUtil.getResult(TestModelImpl.ENTITY_CACHE_ENABLED,
						TestImpl.class, test.getPrimaryKey()) == null) {
				cacheResult(test);
			}
			else {
				test.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all tests.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TestImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TestImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the test.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Test test) {
		EntityCacheUtil.removeResult(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestImpl.class, test.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Test> tests) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Test test : tests) {
			EntityCacheUtil.removeResult(TestModelImpl.ENTITY_CACHE_ENABLED,
				TestImpl.class, test.getPrimaryKey());
		}
	}

	/**
	 * Creates a new test with the primary key. Does not add the test to the database.
	 *
	 * @param testId the primary key for the new test
	 * @return the new test
	 */
	@Override
	public Test create(long testId) {
		Test test = new TestImpl();

		test.setNew(true);
		test.setPrimaryKey(testId);

		return test;
	}

	/**
	 * Removes the test with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testId the primary key of the test
	 * @return the test that was removed
	 * @throws com.liferay.support.NoSuchTestException if a test with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test remove(long testId) throws NoSuchTestException, SystemException {
		return remove((Serializable)testId);
	}

	/**
	 * Removes the test with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the test
	 * @return the test that was removed
	 * @throws com.liferay.support.NoSuchTestException if a test with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test remove(Serializable primaryKey)
		throws NoSuchTestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Test test = (Test)session.get(TestImpl.class, primaryKey);

			if (test == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(test);
		}
		catch (NoSuchTestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Test removeImpl(Test test) throws SystemException {
		test = toUnwrappedModel(test);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(test)) {
				test = (Test)session.get(TestImpl.class, test.getPrimaryKeyObj());
			}

			if (test != null) {
				session.delete(test);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (test != null) {
			clearCache(test);
		}

		return test;
	}

	@Override
	public Test updateImpl(com.liferay.support.model.Test test)
		throws SystemException {
		test = toUnwrappedModel(test);

		boolean isNew = test.isNew();

		TestModelImpl testModelImpl = (TestModelImpl)test;

		Session session = null;

		try {
			session = openSession();

			if (test.isNew()) {
				session.save(test);

				test.setNew(false);
			}
			else {
				session.merge(test);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TestModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((testModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						testModelImpl.getOriginalCompanyId(),
						testModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U,
					args);

				args = new Object[] {
						testModelImpl.getCompanyId(), testModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U,
					args);
			}
		}

		EntityCacheUtil.putResult(TestModelImpl.ENTITY_CACHE_ENABLED,
			TestImpl.class, test.getPrimaryKey(), test);

		return test;
	}

	protected Test toUnwrappedModel(Test test) {
		if (test instanceof TestImpl) {
			return test;
		}

		TestImpl testImpl = new TestImpl();

		testImpl.setNew(test.isNew());
		testImpl.setPrimaryKey(test.getPrimaryKey());

		testImpl.setTestId(test.getTestId());
		testImpl.setCompanyId(test.getCompanyId());
		testImpl.setUserId(test.getUserId());
		testImpl.setTest(test.getTest());

		return testImpl;
	}

	/**
	 * Returns the test with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the test
	 * @return the test
	 * @throws com.liferay.support.NoSuchTestException if a test with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestException, SystemException {
		Test test = fetchByPrimaryKey(primaryKey);

		if (test == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return test;
	}

	/**
	 * Returns the test with the primary key or throws a {@link com.liferay.support.NoSuchTestException} if it could not be found.
	 *
	 * @param testId the primary key of the test
	 * @return the test
	 * @throws com.liferay.support.NoSuchTestException if a test with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test findByPrimaryKey(long testId)
		throws NoSuchTestException, SystemException {
		return findByPrimaryKey((Serializable)testId);
	}

	/**
	 * Returns the test with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the test
	 * @return the test, or <code>null</code> if a test with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Test test = (Test)EntityCacheUtil.getResult(TestModelImpl.ENTITY_CACHE_ENABLED,
				TestImpl.class, primaryKey);

		if (test == _nullTest) {
			return null;
		}

		if (test == null) {
			Session session = null;

			try {
				session = openSession();

				test = (Test)session.get(TestImpl.class, primaryKey);

				if (test != null) {
					cacheResult(test);
				}
				else {
					EntityCacheUtil.putResult(TestModelImpl.ENTITY_CACHE_ENABLED,
						TestImpl.class, primaryKey, _nullTest);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(TestModelImpl.ENTITY_CACHE_ENABLED,
					TestImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return test;
	}

	/**
	 * Returns the test with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testId the primary key of the test
	 * @return the test, or <code>null</code> if a test with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Test fetchByPrimaryKey(long testId) throws SystemException {
		return fetchByPrimaryKey((Serializable)testId);
	}

	/**
	 * Returns all the tests.
	 *
	 * @return the tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Test> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.support.model.impl.TestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of tests
	 * @param end the upper bound of the range of tests (not inclusive)
	 * @return the range of tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Test> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the tests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.support.model.impl.TestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of tests
	 * @param end the upper bound of the range of tests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Test> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Test> list = (List<Test>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEST;

				if (pagination) {
					sql = sql.concat(TestModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Test>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Test>(list);
				}
				else {
					list = (List<Test>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Test test : findAll()) {
			remove(test);
		}
	}

	/**
	 * Returns the number of tests.
	 *
	 * @return the number of tests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TEST);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the test persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.support.model.Test")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Test>> listenersList = new ArrayList<ModelListener<Test>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Test>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TestImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_TEST = "SELECT test FROM Test test";
	private static final String _SQL_SELECT_TEST_WHERE = "SELECT test FROM Test test WHERE ";
	private static final String _SQL_COUNT_TEST = "SELECT COUNT(test) FROM Test test";
	private static final String _SQL_COUNT_TEST_WHERE = "SELECT COUNT(test) FROM Test test WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "test.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Test exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Test exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TestPersistenceImpl.class);
	private static Test _nullTest = new TestImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Test> toCacheModel() {
				return _nullTestCacheModel;
			}
		};

	private static CacheModel<Test> _nullTestCacheModel = new CacheModel<Test>() {
			@Override
			public Test toEntityModel() {
				return _nullTest;
			}
		};
}