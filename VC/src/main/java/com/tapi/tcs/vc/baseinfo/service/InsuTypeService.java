/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 *
 */
public interface InsuTypeService {
	
	/**
	 * 
	 * @param docInsuType
	 * @throws BusinessException
	 */
	public void createInsuType(VcDocInsuType docInsuType) throws BusinessException;
	
	/**
	 * 
	 * @param insuTypeCode
	 * @param insuTypeName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public Page queryDocInsuTypeListByPages(String insuTypeCode, String insuTypeName, int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public VcDocInsuType getInsuType(Long id) throws BusinessException;
	
	/**
	 * 
	 * @param docInsuType
	 * @throws BusinessException
	 */
	public void updateInsuType(VcDocInsuType docInsuType) throws BusinessException;
	
	/**
	 * 
	 * @param id
	 * @throws BusinessException
	 */
	public void deleteByLogic(Long id) throws BusinessException;

	/**
	 * 
	 * @param idArray
	 * @param currentUser
	 * @throws BusinessException
	 */
	public String deleteByLogic(String[] idArray, String currentUser)  throws BusinessException;

}
