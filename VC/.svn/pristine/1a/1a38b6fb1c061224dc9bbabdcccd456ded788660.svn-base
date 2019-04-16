package com.tapi.tcs.vc.store.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;

/**
 * 印刷入库Service
 * <p>
 * Date 2013-03-28
 * </p>
 * <p>
 * Module：印刷入库
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author Leo
 * @version 1.0
 */
public interface DocInStoreManageService {

	/**
	 * 查询印刷入库信息
	 * 
	 * @param vcDocInStore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryVcDocInStore(VcDocInStore vcDocInStore, int pageNo, int pageSize) throws BusinessException;

	/**
	 * 保存印刷入库信息
	 * @param vcDocInStore
	 * @param vcDocInStoreDets
	 * @param actionType 新增/修改
	 * @param actionType2 保存/提交
	 * @throws Exception 
	 */
	public String saveVcDocInStore(VcDocInStore vcDocInStore,
			List<VcDocInStoreDet> vcDocInStoreDets, String actionType,String actionType2,File file) throws Exception;

	/**
	 * 查看印刷入库信息
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException 
	 * @throws Exception 
	 */
	public List viewVcDocInStore(Long id) throws BusinessException;

	/**
	 * 删除印刷入库信息
	 * 
	 * @param id
	 */
	public String deleteVcDocInStore(String id) throws BusinessException;

	/**
	 * 提交印刷入库申请
	 * 
	 * @param id
	 * @throws Exception 
	 */
	public String executeSubmitDocInStore(String id,String userCode,String orgCode) throws Exception;

	/**
	 * 印刷入库审批查询
	 * 
	 * @param vcDocInStore
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryDocInStoreApprove(VcDocInStore vcDocInStore,int pageNo, int pageSize) throws BusinessException;

	/**
	 * 印刷入库审批
	 * 
	 * @param id
	 * @param vcApprove
	 */
	public String executeApproveDocInStore(String id, VcApprove vcApprove) throws BusinessException;

}
