package com.tapi.tcs.vc.webservice.provider.docDoUsed.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;
@WebService(targetNamespace = "http://service.tapi.com/vc/docDoUsed/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocDoUsedService {
 
	/**** 
	 *    新核心系统    单证核销接口 
	 * @param request
	 * @return
	 */
	public  DocDoUsedResponse   docDoUsedQuery(DocDoUsedRequest request);

}
