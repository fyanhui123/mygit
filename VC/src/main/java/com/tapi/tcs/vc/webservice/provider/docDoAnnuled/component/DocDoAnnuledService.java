package com.tapi.tcs.vc.webservice.provider.docDoAnnuled.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docDoAnnuled/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocDoAnnuledService {

	/**
	 *  单证作废接口       新核心系统
	 * @param request
	 * @return
	 */
	public DocDoAnnuledResponse saveDocDoAnnul(DocDoAnnuledRequest request) throws BusinessException;
}
