package com.tapi.tcs.vc.store.service.impl;

import java.util.Date;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.VcStorageInOut;
import com.tapi.tcs.vc.schema.model.VcStorageInOutDet;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.store.dao.VcStorageInOutDao;
import com.tapi.tcs.vc.store.service.VcStorageInOutService;

/**
 * @author whj
 * 
 */
public class VcStorageInOutServiceImpl implements VcStorageInOutService {

    private VcStorageInOutDao vcStorageInOutDao;

    private VcStorageDao vcStorageDao;

    

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
    public Page queryVcStorageInOutList(VcStorageInOut vcStorageInOut, int pageNo, int pageSize)
            throws BusinessException {
        try{
            return vcStorageInOutDao.queryVcStorageInOutList(vcStorageInOut, pageNo, pageSize); 
        }catch(DaoException e){
            throw new BusinessException(e.getMessage(),e);
        }
       
    }
 

    /**
     * 根据id获取VC_STORAGE_IN_OUT_DET中关联明细
     * 
     * @param vcStorageInOutId
     * @return 明细集合
     * @throws BusinessException
     *             异常
     */
    public List<VcStorageInOutDet> getDetListByVcStorageInOutId(Long vcStorageInOutId) throws BusinessException {
        return null;
    }

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
    public VcStorageInOut getVcStorageInOut(Long vcStorageInOutId) throws BusinessException {
        return null;
    }

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
            throws BusinessException {
        List<VcStorageInOutDet> detList = vcStorageInOut.getVcStorageInOutDets();
        if (detList == null || detList.size() < 1) {
            throw new BusinessException("单证明细信息不存在！");
        }
        Date nowDate = new Date();
        String docStatusTo = "OT";
        // 默认出库
        vcStorageInOut.setOperateFlag("O");
        if ("I".equals(type)) {
            docStatusTo = "S2"; // 入库
            vcStorageInOut.setOperateFlag("I");
        }
        try {
            // 逐条操作单证库存
            for (VcStorageInOutDet det : detList) {
                vcStorageDao.executeDocInOut(det.getStartNum(), det.getEndNum(), det.getDocVerCode(), 
                        det.getPressBatchNo(), userInfo.getComCode(), det.getDocStatus(), docStatusTo,
                        userInfo.getUserCode());                        
                det.setCreatedBy(userInfo.getUserCode());
                det.setUpdatedBy(userInfo.getUserCode());
                det.setDateCreated(nowDate);
                det.setDateUpdated(nowDate);
                det.setVcStorageInOut(vcStorageInOut);
            }
            vcStorageInOut.setOrgCode(userInfo.getComCode());
            vcStorageInOut.setOprCode(userInfo.getUserCode());
            vcStorageInOut.setCreatedBy(userInfo.getUserCode());
            vcStorageInOut.setOperateTime(nowDate);
            vcStorageInOut.setUpdatedBy(userInfo.getUserCode());
            vcStorageInOut.setDateCreated(nowDate);
            vcStorageInOut.setDateUpdated(nowDate);
            vcStorageInOutDao.save(vcStorageInOut);
            return "1";
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public void setVcStorageInOutDao(VcStorageInOutDao vcStorageInOutDao) {
        this.vcStorageInOutDao = vcStorageInOutDao;
    }

    public void setVcStorageDao(VcStorageDao vcStorageDao) {
        this.vcStorageDao = vcStorageDao;
    }

}
