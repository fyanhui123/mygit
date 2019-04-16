package com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.component;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docVerInfoInquiry/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocVerInfoInquiryService {  
	
	/****
	 * 单证类型同步接口
	 * @param request
	 * @return
	 */
	public  DocVerInfoInquiryResponse   docVerInfoQuery(DocVerInfoInquiryRequest request);
	
}
