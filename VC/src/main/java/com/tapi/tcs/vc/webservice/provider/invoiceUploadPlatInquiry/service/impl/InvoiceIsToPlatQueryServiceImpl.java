package com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.service.impl;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.service.InvoiceIsToPlatQueryService;

public class InvoiceIsToPlatQueryServiceImpl implements
		InvoiceIsToPlatQueryService {
	/**基础代码DAO*/
	private VcBaseInfoDao vcBaseInfoDao;
	
	@Override
	public InvoiceUploadPlatInquiryResponse queryInvoiceStatus(InvoiceUploadPlatInquiryRequestDTO requestBody) throws BusinessException{
		InvoiceUploadPlatInquiryResponse response = new InvoiceUploadPlatInquiryResponse();
		try{
			//新系统不传部门id，只传代码
			String orgCode = requestBody.getDepartCode();
			//老系统传入部门id，需要进行转换
			if(requestBody.getDepartId()!=null){
				orgCode = vcBaseInfoDao.translateOrgCode(requestBody.getDepartId(), requestBody.getDepartCode());
			}
			//发票代码
			String invoiceCode = requestBody.getInvoiceCode();
			//发票号码
			String invoiceNo = requestBody.getInvoiceNo();
			//发票开具类型
			String printKind = requestBody.getPrintKind();
			//查询发票开具信息
			VcInvoicePrint vcInvoicePrint = vcBaseInfoDao.findVcInvoicePrint(orgCode, invoiceCode, invoiceNo, printKind);
			if(vcInvoicePrint==null){
				throw new BusinessException("未查询到发票开具信息！");
			}
			String status = "0"; 
			//是否上传平台
			String isToPlat = vcInvoicePrint.getIsUploadPlat();
			if("1".equals(isToPlat)){
				status = "1";
			}
			InvoiceUploadPlatInquiryResponseDTO responseBody = new InvoiceUploadPlatInquiryResponseDTO();
			responseBody.setStatus(status);
			ResponseHeadDTO responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("查询成功!");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		}catch(BusinessException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("查询失败！", e);
		}
		return response;
	}

	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}
}
