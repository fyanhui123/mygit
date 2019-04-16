package com.tapi.tcs.vc.inquiry.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocTypeDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.dao.InquiryManageDAO;
import com.tapi.tcs.vc.inquiry.service.InquiryManageService;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.DocInstoreApplyInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.LostVerificationInquiryVo;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.store.dao.DocInStoreManageDAO;

/**
 * 查询统计Service
 * <p>
 * Date 2013-03-28
 * </p>
 * <p>
 * Module：查询统计
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class InquiryManageServiceImpl implements InquiryManageService {

    /** 查询统计Dao */
    private InquiryManageDAO inquiryManageDAO;

    /**
     * 印刷入库DAO
     */
    private DocInStoreManageDAO docInStoreManageDAO;
    /**
     * 审批DAO
     */
    private ApproveDao approveDao;

    /**
     * 印刷入库DAO
     */
    private VcDocTypeDao vcDocTypeDao;

    /**
     * 公共代码Dao
     */
    private VcPubCodeManagerDao vcPubCodeManagerDao;

    /**
     * 机构人员
     */
    private VcLevelDao vcLevelDao;

    /**
     * 查询印刷入库查询统计信息
     * 
     * @param inStoreInquiryDto
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page queryVcDocInStoreInquiry(DocInstoreApplyInquiryVo inStoreInquiryDto, UserInfo userInfo,
            int pageNo, int pageSize) throws BusinessException {
        try {
            return inquiryManageDAO.queryVcDocInStoreInquiry(inStoreInquiryDto, userInfo, pageNo,
                    pageSize);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<DocInstoreApplyInquiryVo> findVcDocInStore(DocInstoreApplyInquiryVo inStoreInquiryDto, String comCode) throws BusinessException {
    	try{
    		return inquiryManageDAO.findVcDocInStore(inStoreInquiryDto, comCode);
        
    	}catch(DaoException e){
    		throw new BusinessException(e.getMessage(), e);
    	}
    }
    
    /**
     * 单证明细查询导出
     * @param queryDto
     * @param fyh
     * @param 
     * @return
     * @throws BusinessException 
     */
    public List<DocDetailInquiryVo> queryDocDetailExplore(DocDetailInquiryVo queryDto, UserInfo userInfo) throws BusinessException{
    	List<DocDetailInquiryVo> result = new ArrayList<DocDetailInquiryVo>();
    	try{
    		result = inquiryManageDAO.queryDocDetailExplore(queryDto, userInfo);
	    	if(result!=null && result.size()>0){
		    	for (DocDetailInquiryVo tempVo : result) {
		            if (StringUtils.isNotBlank(tempVo.getStatus())) {
		                tempVo.setStatus(vcPubCodeManagerDao.getCodeCname("DocStatus", tempVo.getStatus()));
		            }
		        }
	    	}
    	}catch(DaoException e){
    		throw new BusinessException(e.getMessage(), e);
    	}
    	return result;
    }
    /**
     * 单证明细查询统计
     * 
     * @param queryDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryDocDetailInquiryListByPages(DocDetailInquiryVo queryDto, UserInfo userInfo, int pageNo,
            int pageSize) throws BusinessException {
        try {
            Page page = inquiryManageDAO.queryDocDetailInquiryListByPages(queryDto, userInfo, pageNo,
                    pageSize);
           /* if (page != null && page.getResult() != null && page.getResult().size() > 0) {
                List<DocDetailInquiryVo> resultList = (List<DocDetailInquiryVo>) page.getResult();
                for (DocDetailInquiryVo tempVo : resultList) {
                    if (StringUtils.isNotBlank(tempVo.getStatus())) {
                        tempVo.setStatus(vcPubCodeManagerDao.getCodeCname("DocStatus", tempVo.getStatus()));

                    }
                }
            }*/

            return page;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<DocDetailInquiryVo> findDocDetailInquiryList(DocDetailInquiryVo queryDto, String comCode) 
			throws BusinessException{
    	List<DocDetailInquiryVo> result = new ArrayList<DocDetailInquiryVo>();
    	try{
    		result = inquiryManageDAO.findDocDetailInquiryList(queryDto, comCode);
	    	if(result!=null && result.size()>0){
		    	for (DocDetailInquiryVo tempVo : result) {
		            if (StringUtils.isNotBlank(tempVo.getStatus())) {
		                tempVo.setStatus(vcPubCodeManagerDao.getCodeCname("DocStatus", tempVo.getStatus()));
		            }
		        }
	    	}
    	}catch(DaoException e){
    		throw new BusinessException(e.getMessage(), e);
    	}
    	return result;
    }

    @Override
    public Page queryLostVerification(LostVerificationInquiryVo lostVo, int pageNo, int pageSize)
            throws BusinessException {
        return inquiryManageDAO.queryLostVerification(lostVo, pageNo, pageSize);
    }
    
    @Override
    public List<LostVerificationInquiryVo> findLostVerification(LostVerificationInquiryVo lostVo) throws BusinessException {
    	List<LostVerificationInquiryVo> list = null;
    	try{
    		list = inquiryManageDAO.findLostVerification(lostVo);
    	}catch(DaoException e){
    		throw new DaoException(e.getMessage(), e);
    	}
    	return list;
    }

    public void setInquiryManageDAO(InquiryManageDAO inquiryManageDAO) {
        this.inquiryManageDAO = inquiryManageDAO;
    }

    public void setDocInStoreManageDAO(DocInStoreManageDAO docInStoreManageDAO) {
        this.docInStoreManageDAO = docInStoreManageDAO;
    }

    public void setApproveDao(ApproveDao approveDao) {
        this.approveDao = approveDao;
    }

    public void setVcDocTypeDao(VcDocTypeDao vcDocTypeDao) {
        this.vcDocTypeDao = vcDocTypeDao;
    }

    public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
        this.vcPubCodeManagerDao = vcPubCodeManagerDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

}
