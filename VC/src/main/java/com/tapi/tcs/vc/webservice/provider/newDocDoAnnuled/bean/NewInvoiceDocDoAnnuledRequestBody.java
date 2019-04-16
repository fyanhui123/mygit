package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class NewInvoiceDocDoAnnuledRequestBody {
	private String docVerCode;//单证类型代码
	private String pressBatchNo;//发票代码
	private String docNum;//发票号码
	private String orgcode;//机构
	private String TakeCode;//核销操作人代码
	private String counteractFlag;//红冲标志
	private String counteractedInvoiceCode;//冲红发票代码
	private String counteractedInvoiceNo;//  红冲号码
	private String flag;//合并标志
	private List<BillDTO> billDTO;
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
	public String getOrgcode() {
		return orgcode;
	}
	@XmlElement(name = "HXJG", required = true)
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getTakeCode() {
		return TakeCode;
	}
	@XmlElement(name = "HXRDM", required = true)
	public void setTakeCode(String takeCode) {
		TakeCode = takeCode;
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
	public List<BillDTO> getBillDTO() {
		return billDTO;
	}
	@XmlElementWrapper (name = "BILLlIST")
	@XmlElement(name="BILL")
	public void setBillDTO(List<BillDTO> billDTO) {
		this.billDTO = billDTO;
	}
	public String getFlag() {
		return flag;
	}
	@XmlElement(name = "HBBZ", required = true)
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
