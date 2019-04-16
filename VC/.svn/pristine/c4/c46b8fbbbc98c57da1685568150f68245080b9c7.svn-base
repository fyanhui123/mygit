package com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/invoiceUploadPlatInquiry/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface InvoiceUploadPlatInquiryService {

	/**
	 * 查询发票是否已上传平台（供老核心福建地区发票作废时调用）
	 * @param request
	 * @return
	 */
	public InvoiceUploadPlatInquiryResponse queryInvoiceIsUploadPlat(InvoiceUploadPlatInquiryRequest request);
}
