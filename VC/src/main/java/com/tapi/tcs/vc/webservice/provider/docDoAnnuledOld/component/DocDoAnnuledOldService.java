package com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docDoAnnuledOld/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocDoAnnuledOldService {

	/**
	 * 单证作废
	 * @param request
	 * @return
	 */
	public DocDoAnnuledOldResponse saveDocDoAnnul(DocDoAnnuledOldRequest request);
}
