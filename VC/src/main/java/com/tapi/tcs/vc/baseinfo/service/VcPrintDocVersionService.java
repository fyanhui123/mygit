package com.tapi.tcs.vc.baseinfo.service;

import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;

/**
 * 承印单证版本Service实现
 * <p>
 * Date: 2013-03-21
 * </p>
 * <p>
 * Module: 承印单证版本
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public interface VcPrintDocVersionService {

	/**
	 * 新增承印单证版本
	 */
	public void createVcPrintDocVersion(VcPrintDocVersion vcPrintDocVersion) throws BusinessException ;

	/**
	 * 更新承印单证版本
	 */
	public void updateVcPrintDocVersion(VcPrintDocVersion vcPrintDocVersion) throws BusinessException ;

	/**
	 * 根据承印单证版本主键获取承印单证版本
	 */
	public VcPrintDocVersion getVcPrintDocVersion(Long idVcPrintDocVersion) throws BusinessException ;
	
	/**
	 * 查询承印单证版本信息
	 */
	public Page queryVcPrintDocVersionByPages(VcPrintDocVersion vcPrintDocVersion ,int currentPage, int pageNumber) throws BusinessException ;
	
	/**
	 * 删除承印单证版本
	 */
	public void deleteVcPrintDocVersions(String idVcPrintDocVersions) throws BusinessException ;
	
	/**
	 * 根据印刷厂主键查找承印List
	 * @param printeryId
	 * @return vcPrintDocVersionList
	 * @throws BusinessException
	 * @author chy
	 * @date 2013-05-20
	 */
	public Map<String, String> findVcPrintDocVersionList(Long printeryId) throws BusinessException;

}
