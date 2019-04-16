/**
 * 
 */
package com.tapi.tcs.vc.store.dao;

import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcRecycle;

/**
 * @author hanmiao.diao
 *
 */
public interface RecycleDao extends GenericDao<VcRecycle> {

	/**
	 * 分页查询
	 * @param params 查询参数
	 * @param page 分页参数
	 * @param rows 分布参数
	 * @return 查询结果
	 * @throws DaoException 数据库异常
	 */
	Page findRecycleListByPages(Map<String, Object> params, int page, int rows) throws DaoException;
	
	/**
	 * 根据VC_RECYCLE主键删除其关联的VC_RECYCLE_DET表中记录
	 * @param id VC_RECYCLE
	 * @throws DaoException 异常
	 */
	void deleteRecycleDetListByRecycleId(Long id) throws DaoException;
	
}
