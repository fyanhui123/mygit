package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class NewInvoiceDoUsedRequestDTO {
	private String docVerCode;//发票类型	
	private String pressBatchNo;//发票代码
	private String docNum;//发票号
	private String orgCode;//核销机构代码	
	private String verifiedOprCode;//核销操作人代码	
	private String verifiedReason;//开具原因	
	private String counteractFlag;//红冲标志	
	private String counteractedInvoiceCode;//被冲红的发票代码	
	private String counteractedInvoiceNo;//被冲红的发票号码
	private List<PolicyDTO>  policyDTO; //保单信息	
	
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

	public String getDocNum() {
		return docNum;
	}
	@XmlElement(name = "FPHM", required = true)
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getOrgCode() {
		return orgCode;
	}
	@XmlElement(name = "HXJG", required = true)
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getVerifiedOprCode() {
		return verifiedOprCode;
	}
	@XmlElement(name = "HXRDM", required = true)
	public void setVerifiedOprCode(String verifiedOprCode) {
		this.verifiedOprCode = verifiedOprCode;
	}

	public String getVerifiedReason() {
		return verifiedReason;
	}
	@XmlElement(name = "KJYY", required = true)
	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}
	public String getCounteractFlag() {
		return counteractFlag;
	}
	@XmlElement(name = "HCBZ", required = true)
	public void setCounteractFlag(String counteractFlag) {
		this.counteractFlag = counteractFlag;
	}

	public String getCounteractedInvoiceCode() {
		return counteractedInvoiceCode;
	}
	@XmlElement(name = "HCDM", required = true)
	public void setCounteractedInvoiceCode(String counteractedInvoiceCode) {
		this.counteractedInvoiceCode = counteractedInvoiceCode;
	}

	public String getCounteractedInvoiceNo() {
		return counteractedInvoiceNo;
	}
	@XmlElement(name = "HCHM", required = true)
	public void setCounteractedInvoiceNo(String counteractedInvoiceNo) {
		this.counteractedInvoiceNo = counteractedInvoiceNo;
	}
	public List<PolicyDTO> getPolicyDTO() {
		return policyDTO;
	}
	@XmlElementWrapper (name = "BILLlIST")
	@XmlElement(name="BILL")
	public void setPolicyDTO(List<PolicyDTO> policyDTO) {
		this.policyDTO = policyDTO;
	}

}
