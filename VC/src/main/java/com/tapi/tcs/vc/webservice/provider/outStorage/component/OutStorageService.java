package com.tapi.tcs.vc.webservice.provider.outStorage.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.tf.base.api.exception.DaoException;
@WebService(targetNamespace = "http://service.tapi.com/vc/outStorageService/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OutStorageService {
	public String outStorage(String request) throws DaoException;
}
