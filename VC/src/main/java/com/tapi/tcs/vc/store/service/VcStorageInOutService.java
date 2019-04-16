package com.tapi.tcs.vc.store.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcStorageInOut;
import com.tapi.tcs.vc.schema.model.VcStorageInOutDet;


/**
 * @author whj
 *
 */
public interface VcStorageInOutService {
    
   

    /**
     * 分页查询出入库记录
     * 
     * @param params
     *            查询条件
     * @param pageNo
     *            分页参数
     * @param pageSize
     *            分页参数
     * @return 查询结果
     * @throws BusinessException
     *             异常
     */
    public Page queryVcStorageInOutList(VcStorageInOut vcStorageInOut, int pageNo, int pageSize) throws BusinessException;

   
    /**
     * 根据id获取VC_STORAGE_IN_OUT_DET中关联明细
     * 
     * @param vcStorageInOutId
     * @return 明细集合
     * @throws BusinessException
     *             异常
     */
    public List<VcStorageInOutDet> getDetListByVcStorageInOutId(Long vcStorageInOutId) throws BusinessException;

    /**
     * 获取单证出入库记录信息
     * 
     * @param vcStorageInOutId
     * 
     * @param docVerCode
     *            单证类型代码
     * @return 申领明细对象
     * @throws BusinessException
     *             异常
     */
    public VcStorageInOut getVcStorageInOut(Long vcStorageInOutId) throws BusinessException;
    
    
    /**
     * 执行单证出入库操作
     * 
     * @param vcDocTakerIos
     *            使用人单证发放List
     * @param userInfo
     *            当前用户
     * @param type
     *            I-入库  O-出库
     * @param acceptOrgCode
     *            使用人机构
     */

    public String executeDocInOut(VcStorageInOut vcStorageInOut, String type, UserInfo userInfo)
            throws BusinessException;
	
}
