package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;



import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewRequest;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docNumInquiryNew/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocNumInquiryNewService {

	/**
	 * 可使用单证流水号查询接口
	 * @param request
	 * @return
	 */
	public DocNumInquiryNewResponse docNumInquiryNew(DocNumInquiryNewRequest request);
}
