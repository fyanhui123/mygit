package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryResponse;
@WebService(targetNamespace = "http://service.tapi.com/vc/docNumStatusInquiry/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocNumStatusInquiryService {
 
	/**** 
	 *    新核心系统    单证流水号状态查询接口
	 * @param request
	 * @return
	 */
	public  DocNumStatusInquiryResponse   statusQuery(DocNumStatusInquiryRequest request);

}
