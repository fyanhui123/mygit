package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponse;

public interface DocNumStatusInquiryDao extends GenericDao<VcDocVersionInfo> {
 
	public  DocNumStatusInquiryOldResponse  queryDocNumStatus(DocNumStatusInquiryOldRequestDTO request) throws DaoException;
	
	public  List checkBizNoIsUsed(String businessNo, String versionCode) throws DaoException;
	
	
}
 