/**
 * 
 */
package com.tapi.tcs.vc.subfunc.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.invoice.service.FuJianInvoiceService;
import com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponse;
import com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponseBody;
import com.tapi.tcs.vc.invoice.vo.fujian.DownloadResponseTitle;
import com.tapi.tcs.vc.schema.model.VcTaxPayerLogin;

/**
 * @author Lincoln
 * 
 */
@ContextConfiguration(locations = { "classpath:spring/**/*.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
public class FuJianInvoiceServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private FuJianInvoiceService fujianInvoiceService;

	@Test
	public void testDownload() throws Exception{
		/*String orgCode = "01653232";
		try{
			DownloadResponse response = fujianInvoiceService.invoiceDownload(orgCode);
			fujianInvoiceService.saveInvoice(response, orgCode, "admin");
		}catch(BusinessException e){
			e.printStackTrace();
		}*/
		
		List<VcTaxPayerLogin> loginList = null;
		try{
			loginList = fujianInvoiceService.findVcTaxPayerLoginList();
		}catch(BusinessException e){
			logger.info(e.getMessage(), e);
		}
		//以纳税人电脑编码为单位，循环上传平台
		if(loginList!=null && loginList.size()>0){
			for (VcTaxPayerLogin login : loginList) {
				//每个纳税人作为一个事物，相互不影响
				try {
					fujianInvoiceService.executeInvoiceUploadToPlat(login);
				} catch (BusinessException e) {
					logger.info(e.getMessage(), e);
				}
			}
		}
	}
}
