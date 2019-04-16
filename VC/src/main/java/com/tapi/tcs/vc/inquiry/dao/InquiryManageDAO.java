package com.tapi.tcs.vc.inquiry.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.DocInstoreApplyInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.LostVerificationInquiryVo;
import com.tapi.tcs.vc.schema.model.VcDocInStore;

public interface InquiryManageDAO extends GenericDao<VcDocInStore> {
	
	/**
     * <p>
     * 单证明细查询导出
     * </p>
     * 
     * @param DocDetailInquiryVo
     *            
     * @param UserInfo
     * @param 
     * @return List<DocDetailInquiryVo>
     */
	 public List<DocDetailInquiryVo> queryDocDetailExplore(DocDetailInquiryVo queryDto, UserInfo userInfo) throws DaoException;
	
    /**
     * <p>
     * 查询印刷入库信息
     * </p>
     * 
     * @param vcDocInStore
     *            印刷入库对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryVcDocInStoreInquiry(DocInstoreApplyInquiryVo inStoreInquiryDto, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException;

    /**
     * 单证明细查询统计
     * 
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryDocDetailInquiryListByPages(DocDetailInquiryVo queryDto, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException;

    public Page queryLostVerification(LostVerificationInquiryVo lostVo, int pageNo, int pageSize)throws DaoException;
    
    /**
     * 单证明细查询导出
     * @param queryDto
     * @param comCode
     * @return
     * @throws DaoException
     */
    public List<DocDetailInquiryVo> findDocDetailInquiryList(DocDetailInquiryVo queryDto, String comCode) 
			throws DaoException;
    
    /**
     * 印刷入库查询导出
     * @param inStoreInquiryDto
     * @param comCode
     * @return
     * @throws DaoException
     */
    public List<DocInstoreApplyInquiryVo> findVcDocInStore(DocInstoreApplyInquiryVo inStoreInquiryDto, String comCode) throws DaoException;
    
    /**
     * 遗失核销统计查询导出
     * @param lostVo
     * @return
     * @throws DaoException
     */
    public List<LostVerificationInquiryVo> findLostVerification(LostVerificationInquiryVo lostVo) throws DaoException;
}
