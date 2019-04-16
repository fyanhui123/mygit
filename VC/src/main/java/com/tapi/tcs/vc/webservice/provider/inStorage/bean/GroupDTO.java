package com.tapi.tcs.vc.webservice.provider.inStorage.bean;

import javax.xml.bind.annotation.XmlElement;

public class GroupDTO {
	private String fPDM;//发票代码
	private String qSHM;//起始号码
	private String zZHM; //终止号码
	private String fPFS;//发票份数
	private String sYFS;//剩余份数
	private String lGRQ;//领购日期
	private String lGRY; //领购人员名称
	private String lGRYCODE;// 领购人员代码
	private String iNSTCODE;//所属机构
	
	public String getfPDM() {
		return fPDM;
	}
	@XmlElement(name = "FPDM", required = true)
	public void setfPDM(String fPDM) {
		this.fPDM = fPDM;
	}
	public String getqSHM() {
		return qSHM;
	}
	@XmlElement(name = "QSHM", required = true)
	public void setqSHM(String qSHM) {
		this.qSHM = qSHM;
	}
	
	public String getzZHM() {
		return zZHM;
	}
	@XmlElement(name = "ZZHM", required = true)
	public void setzZHM(String zZHM) {
		this.zZHM = zZHM;
	}
	
	public String getfPFS() {
		return fPFS;
	}
	@XmlElement(name = "FPFS", required = true)
	public void setfPFS(String fPFS) {
		this.fPFS = fPFS;
	}
	
	public String getsYFS() {
		return sYFS;
	}
	@XmlElement(name = "SYFS", required = true)
	public void setsYFS(String sYFS) {
		this.sYFS = sYFS;
	}
	
	public String getlGRQ() {
		return lGRQ;
	}
	@XmlElement(name = "LGRQ", required = true)
	public void setlGRQ(String lGRQ) {
		this.lGRQ = lGRQ;
	}
	
	public String getlGRY() {
		return lGRY;
	}
	@XmlElement(name = "LGRY", required = true)
	public void setlGRY(String lGRY) {
		this.lGRY = lGRY;
	}
	
	public String getlGRYCODE() {
		return lGRYCODE;
	}
	@XmlElement(name = "LGRYCODE", required = true)
	public void setlGRYCODE(String lGRYCODE) {
		this.lGRYCODE = lGRYCODE;
	}
	public String getiNSTCODE() {
		return iNSTCODE;
	}
	@XmlElement(name = "INSTCODE", required = true)
	public void setiNSTCODE(String iNSTCODE) {
		this.iNSTCODE = iNSTCODE;
	}
	
	
}
