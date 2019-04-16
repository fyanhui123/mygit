package com.tapi.tcs.vc.inquiry.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.dao.ApplyInquiryDao;
import com.tapi.tcs.vc.inquiry.service.ApplyInquiryService;
import com.tapi.tcs.vc.inquiry.vo.ApplyInquiryVo;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.store.dao.ApplyDao;

/**
 * 单证申领查询统计Service
 * <p>
 * Date 2013-04-11
 * </p>
 * <p>
 * Module：统计查询
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author zhxiao
 * @version 1.0
 */
public class ApplyInquiryServiceImpl implements ApplyInquiryService {

    private ApplyInquiryDao applyInquiryDao;
    private ApplyDao applyDao;
    /**
     * 机构人员
     */
    private VcLevelDao vcLevelDao;

    /**
     * 查询单证申领查询统计信息
     * 
     * @param applyInquiryVo
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page queryApplyInquiryListByPages(ApplyInquiryVo applyInquiryVo, UserInfo userInfo, int pageNo,
            int pageSize) throws BusinessException {
        // TODO Auto-generated method stub
        try {
            return applyInquiryDao.queryApplyInquiryList(applyInquiryVo, userInfo, pageNo, pageSize);
        } catch (DaoException e) {
            throw new BusinessException("数据库操作失败！", e);
        }
    }
    
    @Override
    public List<ApplyInquiryVo> findApplyInquiryList(ApplyInquiryVo applyInquiryVo, String comCode) throws BusinessException {
    	try{
    		return applyInquiryDao.findApplyInquiryList(applyInquiryVo, comCode);
    	}catch(DaoException e){
    		throw new BusinessException(e.getMessage(), e);
    	}
    	
    }

    public void setApplyInquiryDao(ApplyInquiryDao applyInquiryDao) {
        this.applyInquiryDao = applyInquiryDao;
    }

    public ApplyInquiryDao getApplyInquiryDao() {
        return applyInquiryDao;
    }

    public void setApplyDao(ApplyDao applyDao) {
        this.applyDao = applyDao;
    }

    public ApplyDao getApplyDao() {
        return applyDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }
}
