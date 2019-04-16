package com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean;

public class DocVersionDTO {
 
	private String  docVerCode;  //单证类型代码
	private String  docVerName;    //单证类型名称
	private String  printTemplate; //打印模板 
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getDocVerName() {
		return docVerName;
	}
	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}
	public String getPrintTemplate() {
		return printTemplate;
	}
	public void setPrintTemplate(String printTemplate) {
		this.printTemplate = printTemplate;
	}
	
}
