/**
 * Copyright (c) 2011-2016, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.engdream.base.service;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.engdream.base.entity.BaseEntity;
import org.engdream.base.util.SqlUtil;
import org.engdream.common.util.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

	private static final Log logger = LogFactory.getLog(BaseService.class);
	private static final String DEFAULT_CACHE_NAME = "default_cache";

	@Autowired
	protected BaseMapper<T> baseMapper;

	protected Class<T> currentModleClass() {
		return ReflectUtils.findParameterizedType(getClass(), 0);
	}

	/**
	 * <p>
	 * 批量操作 SqlSession
	 * </p>
	 */
	protected SqlSession sqlSessionBatch() {
		return SqlHelper.sqlSessionBatch(currentModleClass());
	}

	/**
	 * 获取SqlStatement
	 *
	 * @param sqlMethod
	 * @return
	 */
	protected String sqlStatement(SqlMethod sqlMethod) {
		return SqlHelper.table(currentModleClass()).getSqlStatement(sqlMethod.getMethod());
	}

	/**
	 * <p>
	 * 判断数据库操作是否成功
	 * </p>
	 * <p>
	 * 注意！！ 该方法为 Integer 判断，不可传入 int 基本类型
	 * </p>
	 *
	 * @param result
	 *            数据库操作返回影响条数
	 * @return boolean
	 */
	protected static boolean retBool(Integer result) {
		return SqlHelper.retBool(result);
	}

	public T save(T entity) {
		baseMapper.insert(entity);
		return entity;
	}

	public boolean saveBatch(List<T> entityList) {
		return saveBatch(entityList, 30);
	}

	/**
	 * 批量插入
	 *
	 * @param entityList
	 * @param batchSize
	 * @return
	 */
	public boolean saveBatch(List<T> entityList, int batchSize) {
		if (CollectionUtils.isEmpty(entityList)) {
			throw new IllegalArgumentException("Error: entityList must not be empty");
		}
		SqlSession batchSqlSession = sqlSessionBatch();
		try {
			int size = entityList.size();
			String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
			for (int i = 0; i < size; i++) {
				batchSqlSession.insert(sqlStatement, entityList.get(i));
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
			}
			batchSqlSession.flushStatements();
		} catch (Exception e) {
			logger.error("Error: Cannot execute insertBatch Method. Cause:" + e);
			return false;
		} finally {
			batchSqlSession.close();
		}
		return true;

	}

	/**
	 * <p>
	 * TableId 注解存在更新记录，否插入一条记录
	 * </p>
	 *
	 * @param entity
	 *            实体对象
	 * @return boolean
	 */
	public T saveOrUpdate(T entity) {
		if (null != entity) {
			Class<?> cls = entity.getClass();
			TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
			if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
				Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
				if (StringUtils.checkValNull(idVal)) {
					return save(entity);
				} else {
					/*
					 * 更新成功直接返回，失败执行插入逻辑
					 */
					T rlt = updateById(entity);
					if (rlt == null) {
						return save(entity);
					}
					return entity;
				}
			} else {
				throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
			}
		}
		return entity;
	}

	public boolean saveOrUpdateBatch(List<T> entityList) {
		return saveOrUpdateBatch(entityList, 30);
	}

	public boolean saveOrUpdateBatch(List<T> entityList, int batchSize) {
		if (CollectionUtils.isEmpty(entityList)) {
			throw new IllegalArgumentException("Error: entityList must not be empty");
		}
		SqlSession batchSqlSession = sqlSessionBatch();
		try {
			int size = entityList.size();
			for (int i = 0; i < size; i++) {
				saveOrUpdate(entityList.get(i));
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
			}
			batchSqlSession.flushStatements();
		} catch (Exception e) {
			logger.error("Error: Cannot execute insertOrUpdateBatch Method. Cause:" + e);
			return false;
		} finally {
			batchSqlSession.close();
		}
		return true;
	}

	public boolean deleteById(ID id) {
		return retBool(baseMapper.deleteById(id));
	}

	public boolean deleteBatchIds(List<ID> idList) {
		return retBool(baseMapper.deleteBatchIds(idList));
	}

	public T updateById(T entity) {
		boolean res = retBool(baseMapper.updateById(entity));
		return res ? entity : null;
	}

	public T update(T entity, Wrapper<T> wrapper) {
		boolean res = retBool(baseMapper.update(entity, wrapper));
		return res ? entity : null;
	}

	public boolean updateBatchById(List<T> entityList) {
		return updateBatchById(entityList, 30);
	}

	public boolean updateBatchById(List<T> entityList, int batchSize) {
		if (CollectionUtils.isEmpty(entityList)) {
			throw new IllegalArgumentException("Error: entityList must not be empty");
		}
		SqlSession batchSqlSession = sqlSessionBatch();
		try {
			int size = entityList.size();
			String sqlStatement = sqlStatement(SqlMethod.UPDATE_BY_ID);
			for (int i = 0; i < size; i++) {
				batchSqlSession.update(sqlStatement, entityList.get(i));
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
			}
			batchSqlSession.flushStatements();
		} catch (Exception e) {
			logger.error("Error: Cannot execute insertBatch Method. Cause:" + e);
			return false;
		} finally {
			batchSqlSession.close();
		}
		return true;
	}
	public T findById(ID id) {
		return baseMapper.selectById(id);
	}

	public List<T> findBatchIds(List<ID> idList) {
		return baseMapper.selectBatchIds(idList);
	}

	public List<T> findByMap(Map<String, Object> columnMap) {
		return baseMapper.selectByMap(columnMap);
	}

	public T findOne(Wrapper<T> wrapper) {
		return SqlHelper.getObject(baseMapper.selectList(wrapper));
	}

	public Map<String, Object> findMap(Wrapper<T> wrapper) {
		return SqlHelper.getObject(baseMapper.selectMaps(wrapper));
	}

	public Object findObj(Wrapper<T> wrapper) {
		return SqlHelper.getObject(baseMapper.selectObjs(wrapper));
	}

	public int findCount(Wrapper<T> wrapper) {
		return SqlHelper.retCount(baseMapper.selectCount(wrapper));
	}

	public List<T> findList(Wrapper<T> wrapper) {
		return baseMapper.selectList(wrapper);
	}

	public List<T> findAll() {
		return baseMapper.selectList(null);
	}

	@SuppressWarnings("unchecked")
	public Page<T> findPage(Page<T> page) {
		return findPage(page, Condition.instance());
	}

	public List<Map<String, Object>> findMaps(Wrapper<T> wrapper) {
		return baseMapper.selectMaps(wrapper);
	}

	public List<Object> findObjs(Wrapper<T> wrapper) {
		return baseMapper.selectObjs(wrapper);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<Map<String, Object>> findMapsPage(Page page, Wrapper<T> wrapper) {
		SqlUtil.fillWrapper(page, wrapper);
		page.setRecords(baseMapper.selectMapsPage(page, wrapper));
		return page;
	}

	public Page<T> findPage(Page<T> page, Wrapper<T> wrapper) {
		SqlUtil.fillWrapper(page, wrapper);
		page.setRecords(baseMapper.selectPage(page, wrapper));
		return page;
	}

	private String getCacheName() {
		CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
		if (cacheConfig == null || cacheConfig.cacheNames().length < 1) {
			return DEFAULT_CACHE_NAME;
		} else {
			return cacheConfig.cacheNames()[0];
		}
	}

	private String getCacheKey(T t){
		return getCacheKey(t.getId());
	}
	private String getCacheKey(ID id) {
		return getCacheName()+":"+id;
	}
}
