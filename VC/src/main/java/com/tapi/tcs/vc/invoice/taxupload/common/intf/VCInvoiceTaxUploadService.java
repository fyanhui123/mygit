package com.tapi.tcs.vc.invoice.taxupload.common.intf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.4.8
 * 2013-12-30T11:26:57.731+08:00
 * Generated source version: 2.4.8
 * 
 */
@WebService(targetNamespace = "http://vc.tcs.tapi.com/invoice/taxUpload/common/intf", name = "VCInvoiceTaxUploadService")
@XmlSeeAlso({com.tapi.tcs.vc.invoice.taxupload.common.vo.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface VCInvoiceTaxUploadService {

    @WebResult(name = "VCUploadTaxResponseDto", targetNamespace = "http://vc.tcs.tapi.com/invoice/taxUpload/common/vo", partName = "uploadInvoiceTaxResponse")
    @WebMethod
    public com.tapi.tcs.vc.invoice.taxupload.common.vo.VCUploadTaxResponseDto uploadInvoiceTax(
        @WebParam(partName = "uploadInvoiceTaxRequest", name = "VCUploadTaxRequestDto", targetNamespace = "http://vc.tcs.tapi.com/invoice/taxUpload/common/vo")
        com.tapi.tcs.vc.invoice.taxupload.common.vo.VCUploadTaxRequestDto uploadInvoiceTaxRequest
    );
}