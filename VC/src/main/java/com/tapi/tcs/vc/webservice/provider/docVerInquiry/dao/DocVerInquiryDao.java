package com.tapi.tcs.vc.webservice.provider.docVerInquiry.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVersionInfoDTO;

public interface DocVerInquiryDao {

	/****
	 *    单证打印配置表、单证类型信息表
	 * @param request
	 * @return
	 * @throws DaoException
	 */
	public List<Object[]>  docVerInquiry(DocVersionInfoDTO request) throws DaoException;

	public List<VcDocVersionInfo>  docVerInquiryForPublic(DocVersionInfoDTO info) throws DaoException;
}
