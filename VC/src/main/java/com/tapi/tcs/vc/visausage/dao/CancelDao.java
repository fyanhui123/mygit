/**
 * 
 */
package com.tapi.tcs.vc.visausage.dao;

import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcCancel;

/**
 * @author hanmiao.diao
 *
 */
public interface CancelDao extends GenericDao<VcCancel> {
	
	/**
	 * 保存VC_CANCEL表数据
	 * @param cancel 待插记录
	 * @throws DaoException 异常
	 */
	void insertVcCancel(VcCancel cancel) throws DaoException;

	/**
	 * 分页查询
	 * @param params 查询条件
	 * @param page 分页参数
	 * @param rows 分页参数
	 * @return 查询结果
	 * @throws DaoException 异常
	 */
	Page findCancelListByPages(Map<String, Object> params, int page,
			int rows) throws DaoException;
	
	/**
	 * 删除明细信息
	 * @param id VC_CANCEL主键
	 * @throws DaoException 异常
	 */
	void deleteVcCancelDetByVcCancelId(Long id) throws DaoException;
}
