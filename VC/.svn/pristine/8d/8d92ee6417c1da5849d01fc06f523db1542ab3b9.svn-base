package com.tapi.tcs.vc.invoice.taxupload.common.service;

import java.util.List;



public interface VCInvoiceTaxUploadProxyService {

	/**
	 * 当数据为数据集时，外层包装的节点名称
	 */
	public abstract void setRootXmlName(String rootXmlName);
	/**
	 * 生成发票XML格式数据,参数顺序需要注意与自己写的SQL中的点位符对应
	 * @return 发票XML格式数据
	 */
	public abstract String generateInvoiceXML(Object... args);
	/**
	 * 根据机构代码生成上传平台使用的登录信息报文
	 * @param companyCode 机构代码
	 * @return 登录信息报文
	 */
	public abstract List<String> generateLoginXML(final String companyCode);
	
	/**
	 * 发票上传地税入口,根据机构代码提取需要上传地税的机构，并上传地税
	 */
	public abstract void uploadInvoiceTax(final String companyCode);
	/**
	 * 发票上传地税入口，根据已生成的发票XML格式数据和地税登录XML数据调用上传地税接口
	 */
	public abstract void uploadInvoiceTax(final String invoiceXML, final String taxLoginXML);
	/**
	 * 定时发票上传地税入口,机构代码(companyCode)注入多个时，用逗号分隔
	 */
	public abstract void uploadInvoiceTaxSchedule();
}
