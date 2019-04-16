package com.tapi.tcs.vc.inquiry.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.DocInstoreApplyInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.LostVerificationInquiryVo;

/**
 * 查询统计Service
 * <p>
 * Date 2013-03-28
 * </p>
 * <p>
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public interface InquiryManageService {

    /**
     * 查询印刷入库查询统计信息
     * 
     * @param vcDocInStore
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryVcDocInStoreInquiry(DocInstoreApplyInquiryVo inStoreInquiryDto, UserInfo userInfo,
            int pageNo, int pageSize) throws BusinessException;

    
    /**
     * 单证明细查询统计导出
     * 
     * @param queryDto
     * @param 
     * @param 
     * @return
     * @throws BusinessException 
     */
    public List<DocDetailInquiryVo> queryDocDetailExplore(DocDetailInquiryVo queryDto, UserInfo userInfo) throws  BusinessException;
    /**
     * 单证明细查询统计
     * 
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryDocDetailInquiryListByPages(DocDetailInquiryVo queryDto, UserInfo userInfo, int pageNo,
            int pageSize) throws BusinessException;

    public Page queryLostVerification(LostVerificationInquiryVo lostVo, int pageNo, int pageSize)
            throws BusinessException;
    
    /**
     * 单证明细查询导出
     * @param queryDto
     * @param comCode
     * @return
     * @throws BusinessException
     */
    public List<DocDetailInquiryVo> findDocDetailInquiryList(DocDetailInquiryVo queryDto, String comCode) 
    		throws BusinessException;
    
    /**
     * 印刷入库查询导出
     * @param inStoreInquiryDto
     * @param comCode
     * @return
     * @throws BusinessException
     */
    public List<DocInstoreApplyInquiryVo> findVcDocInStore(DocInstoreApplyInquiryVo inStoreInquiryDto, String comCode) 
    		throws BusinessException;
    
    /**
     * 遗失核销统计查询导出
     * @param lostVo
     * @return
     * @throws BusinessException
     */
    public List<LostVerificationInquiryVo> findLostVerification(LostVerificationInquiryVo lostVo) throws BusinessException;
}
