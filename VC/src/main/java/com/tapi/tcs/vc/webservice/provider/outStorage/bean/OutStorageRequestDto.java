package com.tapi.tcs.vc.webservice.provider.outStorage.bean;

import javax.xml.bind.annotation.XmlElement;

public class OutStorageRequestDto {
	private String employId;//单证使用人标示id
	private String docVerCode;//单证类型代码
	private String pressBatchNo;//发票代码
	private String startNum; //起始号段
	private String endNum;//终止号段
	private String docNum;//发放份数
	private String takeCode;//管理员代码
	private String takeOrgcode;//管理员所属机构
	private String createBy;//使用人代码
	public String getEmployId() {
		return employId;
	}
	@XmlElement(name = "KPZDBS", required = true)
	public void setEmployId(String employId) {
		this.employId = employId;
	}
	public String getDocVerCode() {
		return docVerCode;
	}
	@XmlElement(name = "FPLXDM", required = true)
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getPressBatchNo() {
		return pressBatchNo;
	}
	@XmlElement(name = "FPDM", required = true)
	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}
	public String getStartNum() {
		return startNum;
	}
	@XmlElement(name = "QSHM", required = true)
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	public String getEndNum() {
		return endNum;
	}
	@XmlElement(name = "ZZHM", required = true)
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	public String getDocNum() {
		return docNum;
	}
	@XmlElement(name = "FPFS", required = true)
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getTakeCode() {
		return takeCode;
	}
	@XmlElement(name = "LGRYCODE", required = true)
	public void setTakeCode(String takeCode) {
		this.takeCode = takeCode;
	}
	public String getTakeOrgcode() {
		return takeOrgcode;
	}
	@XmlElement(name = "INSTCODE", required = true)
	public void setTakeOrgcode(String takeOrgcode) {
		this.takeOrgcode = takeOrgcode;
	}
	public String getCreateBy() {
		return createBy;
	}
	@XmlElement(name = "USERCODE", required = true)
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
}
