package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequest;


@WebService(targetNamespace = "http://service.tapi.com/vc/invoiceDoUsed/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface InvoiceDoUsedService {

	public InvoiceDoUsedResponse invoiceDoUsedQuery(InvoiceDoUsedRequest invoiceDoUsedRequest)throws DaoException;
}
