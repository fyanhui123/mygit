package com.tapi.tcs.vc.inquiry.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.inquiry.vo.ApplyInquiryVo;
import com.tapi.tcs.vc.schema.model.VcApply;
/**
 * 单证申领查询统计Dao实现类
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
public interface ApplyInquiryDao extends GenericDao<VcApply> {

	/**
	 * <p> 查询单证申领信息 </p>
	 * @param applyInquiryVo
	 * @param pageNo 
	 * @param pageSize
	 * @return
	 */
	public Page queryApplyInquiryList(ApplyInquiryVo applyInquiryVo,UserInfo userInfo,
			int pageNo, int pageSize) throws DaoException;

	/**
	 * 单证申领查询导出
	 * @param applyInquiryVo
	 * @param comCode
	 * @return
	 * @throws DaoException
	 */
	public List<ApplyInquiryVo> findApplyInquiryList(ApplyInquiryVo applyInquiryVo, String comCode) throws DaoException;
}
