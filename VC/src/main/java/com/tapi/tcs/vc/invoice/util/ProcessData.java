package com.tapi.tcs.vc.invoice.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.invoice.service.FuJianInvoiceService;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

public class ProcessData {

	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**福建发票服务类*/
	private FuJianInvoiceService fuJianInvoiceService;
	
	/**
	 * 福建地税定时上传程序入口
	 */
	public void fjInvoiceSendToPlat() {
		logger.warn("====福建发票上传地税平台处理开始====");
		List<VcTaxPayerLogin> loginList = null;
		try{
			loginList = fuJianInvoiceService.findVcTaxPayerLoginList();
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
		}
		//以纳税人电脑编码为单位，循环上传平台
		if(loginList!=null && loginList.size()>0){
			for (VcTaxPayerLogin login : loginList) {
				//每个纳税人作为一个事物，相互不影响
				try {
					logger.warn("===="+login.getComputerNo()+"开始上传====");
					fuJianInvoiceService.executeInvoiceUploadToPlat(login);
				} catch (BusinessException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		logger.warn("====福建发票上传地税平台处理结束====");
	}

	public void setFuJianInvoiceService(FuJianInvoiceService fuJianInvoiceService) {
		this.fuJianInvoiceService = fuJianInvoiceService;
	}
}
