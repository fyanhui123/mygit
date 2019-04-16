package com.tapi.tcs.vc.webservice.provider.docDoUsed.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;

public interface DocDoUsedQueryService {
 
	/**** 
	 *    新核心系统    单证核销接口 
	 * @param request
	 * @return  
	 */
	public  DocDoUsedResponse  executeDocDoUsedHandle(DocDoUsedRequest request) throws BusinessException;
}
