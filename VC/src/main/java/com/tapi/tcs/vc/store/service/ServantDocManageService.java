package com.tapi.tcs.vc.store.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;

/**
 * 使用人单证发放/回收Service Interface
 * <p>
 * Date: 2013-04-07
 * </p>
 * <p>
 * Module: 使用人单证发放/回收Service
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author Leo
 * @version 1.0
 */
public interface ServantDocManageService {

	/**
	 * 保存使用人单证发放
	 * @param vcDocTakerIos 使用人单证List
	 * @param userCode 当前操作人
	 * @param takerCode 使用人
	 * @param acceptOrgCode 使用人机构
 	 */
	public String saveServantDocIssued(List<VcDocTakerIo> vcDocTakerIos,
			String userCode, String orgCode, String takerCode, String acceptOrgCode) throws BusinessException; 
	
	/**
	 * 查询使用人单证发放
	 * @param vcDocTakerIo
	 * @return
	 * @throws Exception 
	 */
	public Page queryServantDocIssued(VcDocTakerIo vcDocTakerIo, int pageNo,
			int pageSize) throws Exception;
	
	
	/**
	 * 保存使用人单证回收
	 * @param vcDocTakerIos 使用人单证List
	 * @param userCode 当前操作人
	 * @param takerCode 使用人
	 * @param acceptOrgCode 使用人机构
 	 */
	public String saveServantDocRecovery(List<VcDocTakerIo> vcDocTakerIos,
			String userCode, String orgCode, String takerCode, String acceptOrgCode) throws BusinessException;
	
	/**
	 * 获取最长库存时间
	 * @param docVerCode
	 * @param orgCode
	 * @return
	 */
	public int getMaxStoreTime(String docVerCode,String orgCode ) throws BusinessException;
}
