package com.tapi.tcs.vc.invoice.taxupload.common.service;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/**/*.xml"
		})
public class VCInvoiceTaxUploadProxyServiceTest extends AbstractJUnit4SpringContextTests {

	@Resource(name="vcInvoiceTaxUploadProxyService")
	private VCInvoiceTaxUploadProxyService vcInvoiceTaxUploadProxyService;

	@Test
	/**
	 * 贵州发票上传地税测试
	 */
	public void testUploadInvoiceTax() {
		vcInvoiceTaxUploadProxyService.uploadInvoiceTax("0153");
	}

	public VCInvoiceTaxUploadProxyService getVcInvoiceTaxUploadProxyService() {
		return vcInvoiceTaxUploadProxyService;
	}

	public void setVcInvoiceTaxUploadProxyService(
			VCInvoiceTaxUploadProxyService vcInvoiceTaxUploadProxyService) {
		this.vcInvoiceTaxUploadProxyService = vcInvoiceTaxUploadProxyService;
	}

}
