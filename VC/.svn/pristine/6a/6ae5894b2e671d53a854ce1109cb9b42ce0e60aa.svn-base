/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.service;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 *
 */
public interface InsuKindService {
	/**
	 * 
	 * @param insuKind
	 * @throws BusinessException
	 */
	public void createInsuKind(VcDocInsuKind insuKind) throws BusinessException;

	/**
	 * 
	 * @param insuTypeCode
	 * @param insuKindCode
	 * @param insuKindName
	 * @param page
	 * @param rows
	 * @return
	 * @throws BusinessException
	 */
	public Page queryDocInsuKindListByPages(Long insuTypeCode, String insuKindCode,
			String insuKindName, int page, int rows) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public VcDocInsuKind getInsuKind(Long id)  throws BusinessException;

	/**
	 * 
	 * @param insuKind
	 * @throws BusinessException
	 */
	public void updateInsuKind(VcDocInsuKind insuKind) throws BusinessException;

	/**
	 * 
	 * @param idArray
	 * @throws BusinessException
	 */
	public String deleteByLogic(String[] idArray, String currentUser) throws BusinessException;

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<VcDocInsuType> getValidInsuTypeList()  throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<VcDocInsuKind> getInsuKindList(Map<String,Object> map)  throws BusinessException;
	
	
	/**
	 * 根据险类代码查询险种
	 * 
	 * @param insuTypeCode
	 * @return
	 */
	public List<VcDocInsuKind> queryInsuKindListByInsuTypeCode(String insuKindCode) throws BusinessException;

}
