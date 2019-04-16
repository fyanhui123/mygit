package com.tapi.tcs.vc.webservice.provider.docVerInquiry.component;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryResponse;
@WebService(targetNamespace = "http://service.tapi.com/vc/docVerInquiry/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocVerInquiryService {  
	
	/****
	 *    新核心系统    单证类型查询接口
	 * @param request
	 * @return
	 */
	public  DocVerInquiryResponse   verQuery(DocVerInquiryRequest request);
	
}
