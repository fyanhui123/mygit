package com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequestDTO;

public interface DocVerInfoInquiryDao {

	/****
	 *    单证单证类型信息表
	 * @param request
	 * @return
	 * @throws DaoException
	 */
	public Page docVerInfoInquiry(DocVerInfoInquiryRequestDTO requestBody) throws DaoException;

	
	/**查询单证险种关联表
	 * 
	 * @param idVcDocVersionInfo
	 * @return
	 * @throws DaoException
	 */
	public List<VcDocRelInsuKind> queryDocRelInsuKindList(Long idVcDocVersionInfo) throws DaoException;
}
