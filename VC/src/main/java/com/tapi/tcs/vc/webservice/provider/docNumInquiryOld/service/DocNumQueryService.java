package com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldResponse;

/****
 *      按条件查询业务
 *      
 *      配合接口、另外定义的service方法、封装查询条件、对数据库进行操作 
 * @author Administrator
 *
 */
public interface DocNumQueryService {
	
	public  DocNumInquiryOldResponse  vcAvailableQuery(DocNumInquiryOldRequestDTO body) throws BusinessException;
	 
}
