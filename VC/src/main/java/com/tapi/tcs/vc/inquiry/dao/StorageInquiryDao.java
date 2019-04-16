package com.tapi.tcs.vc.inquiry.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;
import com.tapi.tcs.vc.schema.model.VcStorage;
/**
 * 单证库存查询统计Dao实现类
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
 * @author zhxiao
 * @version 1.0
 */
public interface StorageInquiryDao extends GenericDao<VcStorage> {
	

	/**
	 * <p> 查询单证库存信息-机构\使用人 </p>
	 * @param storageInquiryVo
	 * @param pageNo 
	 * @param pageSize
	 * @return
	 */
	public Page queryStorageInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo,int pageNo,
		int pageSize) throws DaoException;
	
	/**
	 * 导出查询
	 * @param storageInquiryVo
	 * @param comCode
	 * @return
	 * @throws DaoException
	 */
	public List<StorageInquiryVo> findStorageInquiryList(StorageInquiryVo storageInquiryVo, String comCode) throws DaoException;
	
	/**
	 * 单证库存查询（查询库存表）/导出Excel
	 * @param storageInquiryVo
	 * @param userInfo
	 * @param pageNo
	 * @param pageSize
	 * @param queryType 1-分页查询；2-导出
	 * @return
	 * @throws DaoException
	 */
	public Object queryStorageInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo, int pageNo,
            int pageSize, String queryType) throws DaoException;
	
	/**
	 * 单证库存查询（查询可使用表）/导出Excel
	 * @param storageInquiryVo
	 * @param userInfo
	 * @param pageNo
	 * @param pageSize
	 * @param queryType 1-分页查询；2-导出
	 * @return
	 * @throws DaoException
	 */
	public Object queryAvailableInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo, int pageNo,
            int pageSize, String queryType) throws DaoException;
}