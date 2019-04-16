package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.component;


import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docVerInquiryOld/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocVerInquiryOldService {

	public DocVerInquiryOldResponse docVerInquiry(DocVerInquiryOldRequest request);
}
