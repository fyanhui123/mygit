package com.tapi.tcs.vc.webservice.provider.docNumSynchronize.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeRequest;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docNumSynchronize/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)

public interface DocNumSynchronizeService {
	/**
	 * 单证同步接口
	 * @param request
	 * @return
	 */
	
	public DocNumSynchronizeResponse docNumSynchronize(DocNumSynchronizeRequest request);

}
