/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 *
 */
public interface InsuTypeDao extends GenericDao<VcDocInsuType> {

	/**
	 * 分页查询
	 * @param insuTypeCode 查询条件
	 * @param insuTypeName 查询条件
	 * @param pageNo 查询当前页数
	 * @param pageSize 查询每页数量
	 * @return 分页查询结果
	 */
	public Page queryInsuTypeList(String insuTypeCode, String insuTypeName, int pageNo, int pageSize) throws DaoException;
	
	/**
	 * 
	 * @param key 属性
	 * @param value 值
	 * @return
	 * @throws DaoException
	 */
	public List<VcDocInsuType> getInsuTypeListByEquals(String key, String value) throws DaoException;

	/**
	 * 
	 * @param vo
	 * @throws DaoException
	 */
	public void updateInsuTypeName(VcDocInsuType vo) throws DaoException;
}
