package com.tapi.tcs.vc.webservice.provider.inStorage.bean;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class InputStorageRequestDTO {
	private String nSRSBH; //纳税人识别号
	private String kPZDBS; //开票终端标识
	private String fPLXDM;//发票类型代码
	private String fPZT;//发票状态
	private String dQFPDM;//当前未开发票代码
	private String dQFPHM;//当前未开发票号码
	private String ZSYFS; //总剩余份数
	private List<GroupDTO>  rESULTLIST; //发票入库信息
	public String getnSRSBH() {
		return nSRSBH;
	}
	@XmlElement(name = "NSRSBH", required = true)
	public void setnSRSBH(String nSRSBH) {
		this.nSRSBH = nSRSBH;
	}
	
	public String getkPZDBS() {
		return kPZDBS;
	}
	@XmlElement(name = "KPZDBS", required = true)
	public void setkPZDBS(String kPZDBS) {
		this.kPZDBS = kPZDBS;
	}
	
	public String getfPLXDM() {
		return fPLXDM;
	}
	@XmlElement(name = "FPLXDM", required = true)
	public void setfPLXDM(String fPLXDM) {
		this.fPLXDM = fPLXDM;
	}
	
	public String getfPZT() {
		return fPZT;
	}
	@XmlElement(name = "FPZT", required = true)
	public void setfPZT(String fPZT) {
		this.fPZT = fPZT;
	}
	
	public String getdQFPDM() {
		return dQFPDM;
	}
	@XmlElement(name = "DQFPDM", required = true)
	public void setdQFPDM(String dQFPDM) {
		this.dQFPDM = dQFPDM;
	}
	
	public String getdQFPHM() {
		return dQFPHM;
	}
	@XmlElement(name = "DQFPHM", required = true)
	public void setdQFPHM(String dQFPHM) {
		this.dQFPHM = dQFPHM;
	}
	
	public String getZSYFS() {
		return ZSYFS;
	}
	@XmlElement(name = "zSYFS", required = true)
	public void setZSYFS(String zSYFS) {
		ZSYFS = zSYFS;
	}
	public List<GroupDTO> getrESULTLIST() {
		return rESULTLIST;
	}
	@XmlElementWrapper (name = "RESULTLIST")
	@XmlElement(name="GROUP")
	public void setrESULTLIST(List<GroupDTO> rESULTLIST) {
		this.rESULTLIST = rESULTLIST;
	}


	
	
	

}
