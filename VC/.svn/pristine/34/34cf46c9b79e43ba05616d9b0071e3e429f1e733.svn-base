package com.tapi.tcs.vc.inquiry.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.ApplyInquiryVo;

/**
 * 单证申领查询统计Service实现类
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
 * @author zhxiao
 * @version 1.0
 */

public interface ApplyInquiryService {
	/**
	 * 查询单证申领查询统计信息
	 * @param applyInquiryVo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryApplyInquiryListByPages(ApplyInquiryVo applyInquiryVo, UserInfo userInfo,
			int pageNo, int pageSize)  throws BusinessException;

	/**
	 * 单证申领查询导出
	 * @param applyInquiryVo
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 */
	public List<ApplyInquiryVo> findApplyInquiryList(ApplyInquiryVo applyInquiryVo, String comCode) throws BusinessException;
}
