package com.tapi.tcs.vc.webservice.provider.ClassInfoService.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.tf.base.api.exception.DaoException;
@WebService(targetNamespace = "http://service.tapi.com/vc/vcClassInfoService/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ClassInfoService {
		public String ClassInfoService(String arry) throws DaoException;
}
