package com.tapi.tcs.vc.baseinfo.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPrintery;

/**
 * 印刷厂维护Service实现
 * <p>
 * Date: 2013-03-12
 * </p>
 * <p>
 * Module: 印刷厂维护
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
public interface VcPrinteryService {

    /**
     * 新增印刷厂
     * @param printery 印刷厂信息
     * @param userInfo 当前用户
     * @param file 上传的文件
     * @throws BusinessException 异常
     */
	public void createPrintery(VcPrintery printery, UserInfo userInfo,File file) throws BusinessException ;

	 /**
     * 更新印刷厂
     * 
     * @param printery 印刷厂信息
     * @param userInfo 当前用户
     * @param file
     *            上传文件
     * @throws Exception
     */
	public void updatePrintery(VcPrintery printery,UserInfo userInfo,File file) throws Exception ;

	/**
	 * 根据印刷厂主键获取印刷厂
	 */
	public VcPrintery getPrintery(Long idVcPrintery) throws BusinessException ;
	
	/**
	 * 查询印刷厂信息
	 */
	public Page queryPrinteryByPages(VcPrintery printery ,int currentPage, int pageNumber) throws BusinessException ;
	
	/**
	 * 注销、恢复印刷厂
	 */
	public String deleteOrUnDeletePrintery(String idVcPrinterys) throws BusinessException ;
	
	/**
	 * @return 查询印刷厂承印信息
	 * @throws BusinessException
	 * @author wanghuajian
	 * since 2013-4-13下午01:36:22
	 */
	public List<VcPrintDocVersion> queryJsonPrtDocVersionListByPrinteryId(Long idVcPrintery) throws BusinessException;
	
	public List<VcPrintery> queryPrintery(Map params)throws BusinessException;
	
	public String queryDocUnitPrice(String printeryCode,String docVerCode)throws BusinessException;
	
	/**
	 * 查询印刷厂列表
	 * @return
	 * @throws Exception
	 * @author chy
	 * @date 2013-05-20
	 */
	public List<VcPrintery> findVcPrinteryList() throws Exception;

}
