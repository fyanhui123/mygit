package com.tapi.tcs.vc.inquiry.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.StorageInquiryVo;
/**
 * 单证库存查询统计Service实现类
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
public interface StorageInquiryService {

	
	/**
	 * 查询单证库存查询统计信息
	 * @param storageInquiryVo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryStorageInquiryListByPages(StorageInquiryVo storageInquiryVo, UserInfo userInfo,
			int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 导出查询
	 * @param storageInquiryVo
	 * @param userInfo
	 * @return
	 * @throws BusinessException
	 */
	public List<StorageInquiryVo> findStorageInquiryList(StorageInquiryVo storageInquiryVo, UserInfo userInfo) throws BusinessException;

}
