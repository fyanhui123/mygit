package com.tapi.tcs.vc.inquiry.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.dao.StorageInquiryDao;
import com.tapi.tcs.vc.inquiry.service.StorageInquiryService;
import com.tapi.tcs.vc.inquiry.vo.ApplyInquiryVo;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;

/**
 * 单证库存查询统计Service
 * <p>
 * Date 2013-04-16
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
public class StorageInquiryServiceImpl implements StorageInquiryService {
    private StorageInquiryDao storageInquiryDao;
    private VcLevelDao vcLevelDao;
    private VcTakerDao vcTakerDao;

    /**
     * 查询单证库存查询统计信息
     * 
     * @param storageInquiryVo
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page queryStorageInquiryListByPages(StorageInquiryVo storageInquiryVo, UserInfo userInfo,
            int pageNo, int pageSize) throws BusinessException {
        try {
            /*Page page = storageInquiryDao.queryStorageInquiryList(storageInquiryVo, userInfo, pageNo,
                    pageSize);*/
        	Page page = null;
        	//查询库存表
        	if("0".equals(storageInquiryVo.getTakerDocType())){
        		page = (Page)storageInquiryDao.queryStorageInquiryList(storageInquiryVo, userInfo, pageNo,
                        pageSize,"1");
        	}
        	//查询可使用表
        	else if("1".equals(storageInquiryVo.getTakerDocType())){
        		page = (Page)storageInquiryDao.queryAvailableInquiryList(storageInquiryVo, userInfo, pageNo,
                        pageSize,"1");
        	}
        	
        	if(page !=null){
	            List<StorageInquiryVo> StorageInquiryVos = (List<StorageInquiryVo>) page.getResult();
	            List<StorageInquiryVo> resultList = new ArrayList<StorageInquiryVo>();
	            if (StorageInquiryVos != null && StorageInquiryVos.size() > 0) {
	                // 机构代码转换为名称
	                if (storageInquiryVo.getTakerDocType().equals("0")) {
	                    for (StorageInquiryVo tempVo : StorageInquiryVos) {
	                        tempVo.setOrgCode(vcLevelDao.getUnitNameByUnitCode(tempVo.getOrgCode()));
	                        tempVo.setDocTakerCode(vcLevelDao.getUnitNameByUnitCode(tempVo.getDocTakerCode()));
	                        resultList.add(tempVo);
	                    }
	                    // 使用人代码装换为名称
	                } else if (storageInquiryVo.getTakerDocType().equals("1")) {
	                    for (StorageInquiryVo tempVo : StorageInquiryVos) {
	                        tempVo.setOrgCode(vcLevelDao.getUnitNameByUnitCode(tempVo.getOrgCode()));
	                        tempVo.setDocTakerCode(vcTakerDao.getUnitNameByUnitCode(tempVo.getDocTakerCode()));
	                        resultList.add(tempVo);
	                    }
	                }
	            }
	            page.setData(resultList);
        	}
            return page;
        } catch (DaoException e) {
            throw new BusinessException("数据库操作失败！", e);
        }
    }
    
    public List<StorageInquiryVo> findStorageInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo) throws BusinessException {
    	List<StorageInquiryVo> result = new ArrayList<StorageInquiryVo>();
    	try{
    		List<StorageInquiryVo> list = null;
        	//查询库存表
        	if("0".equals(storageInquiryVo.getTakerDocType())){
        		list = (List<StorageInquiryVo>)storageInquiryDao.queryStorageInquiryList(storageInquiryVo, userInfo, 0,
                        0,"2");
        	}
        	//查询可使用表
        	else if("1".equals(storageInquiryVo.getTakerDocType())){
        		list = (List<StorageInquiryVo>)storageInquiryDao.queryAvailableInquiryList(storageInquiryVo, userInfo, 0,
                        0,"2");
        	}
    		if (list != null && list.size() > 0) {
                // 机构代码转换为名称
                if (storageInquiryVo.getTakerDocType().equals("0")) {
                    for (StorageInquiryVo tempVo : list) {
                        tempVo.setOrgCode(vcLevelDao.getUnitNameByUnitCode(tempVo.getOrgCode()));
                        tempVo.setDocTakerCode(vcLevelDao.getUnitNameByUnitCode(tempVo.getDocTakerCode()));
                        result.add(tempVo);
                    }
                    // 使用人代码装换为名称
                } else if (storageInquiryVo.getTakerDocType().equals("1")) {
                    for (StorageInquiryVo tempVo : list) {
                        tempVo.setOrgCode(vcLevelDao.getUnitNameByUnitCode(tempVo.getOrgCode()));
                        tempVo.setDocTakerCode(vcTakerDao.getUnitNameByUnitCode(tempVo.getDocTakerCode()));
                        result.add(tempVo);
                    }
                }
            }
    	}catch(DaoException e){
    		throw new BusinessException(e.getMessage(), e);
    	}
    	return result;
    }

    public void setStorageInquiryDao(StorageInquiryDao storageInquiryDao) {
        this.storageInquiryDao = storageInquiryDao;
    }

    public void setVcLevelDao(VcLevelDao vcLevelDao) {
        this.vcLevelDao = vcLevelDao;
    }

    public void setVcTakerDao(VcTakerDao vcTakerDao) {
        this.vcTakerDao = vcTakerDao;
    }

}
