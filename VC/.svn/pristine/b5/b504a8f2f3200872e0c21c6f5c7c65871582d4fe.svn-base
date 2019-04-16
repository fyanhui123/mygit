/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;

/**
 * @author Administrator
 *
 */
public interface InsuKindDao extends GenericDao<VcDocInsuKind> {

	/**
	 * 
	 * @param insuTypeCode
	 * @param insuKindCode
	 * @param insuKindName
	 * @param page
	 * @param rows
	 * @return
	 * @throws DaoException
	 */
	Page queryInsuKindList(Long insuTypeId, String insuKindCode, String insuKindName, int page,
			int rows) throws DaoException;

	/**
	 * 
	 * @param insuTypeId
	 * @return
	 * @throws DaoException
	 */
	List<VcDocInsuKind> queryInsuKindListByInsuTypeId(Long insuTypeId) throws DaoException;

	/**
	 * 
	 * @param string
	 * @param insuKindCode
	 * @return
	 * @throws DaoException
	 */
	List<VcDocInsuKind> getInsuKindListByEquals(String key, String value)  throws DaoException;
	
	/**
	 * 根据给定的条件查询
	 * @wanghuajian
	 */
	public List<VcDocInsuKind> getInsuKindList(Map<String,Object> map) throws DaoException;
	
	
/**
 * 根据险类代码查询险种
 * @param insuTypeCode
 * @return
 */
	List<VcDocInsuKind> queryInsuKindListByInsuTypeCode(String insuTypeCode)throws DaoException;


}
