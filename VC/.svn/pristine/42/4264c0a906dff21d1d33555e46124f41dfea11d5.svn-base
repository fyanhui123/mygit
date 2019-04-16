package com.tapi.tcs.vc.webservice.provider.inStorage.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.tf.base.api.exception.DaoException;
@WebService(targetNamespace = "http://service.tapi.com/vc/inputStorageService/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface InputStorageService {
	public String saveStorage(String request) throws DaoException;
}
