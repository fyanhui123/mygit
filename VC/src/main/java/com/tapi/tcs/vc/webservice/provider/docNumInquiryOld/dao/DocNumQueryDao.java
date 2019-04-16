package com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.vo.DocNumInquiryRequestVO;

public interface DocNumQueryDao extends GenericDao<VcAvailableDoc> {

	@SuppressWarnings("rawtypes")
	public List vcAvailableQuery(DocNumInquiryRequestVO requestVo) throws DaoException;
}
