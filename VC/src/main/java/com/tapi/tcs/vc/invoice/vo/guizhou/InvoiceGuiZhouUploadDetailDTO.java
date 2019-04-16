package com.tapi.tcs.vc.invoice.vo.guizhou;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tapi.tcs.vc.invoice.vo.fujian.InvoicePrintDetDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FPKJ_EXT", propOrder = {
		"dateUpdated",
		"updatedBy",
		"dateCreated",
		"createdBy",
		"isUploadPlat",
		"status",
		"items",
		"invoiceType",
		"invoiceKindName",
		"invoicekindCode",
		"bankCode",
		"zgIndustry",
		"registration",
		"bank",
		"account",
		"printTypeCode",
		"industryName",
		"industryCode",
		"upperAmount",
		"taxRate",
		"taxAmount",
		"checkNum",
		"endDate",
		"startDate",
		"machinePrintedNo",
		"machinePrintedCode",
		"belongStage",
		"settleItem",
		"manageCode",
		"taxOrg",
		"printKind",
		"operateDate",
		"remark",
		"canceledDate",
		"canceledOprName",
		"counteractedInvoiceNo",
		"counteractedInvoiceCode",
		"counteractInvoiceNo",
		"counteractInvoiceCode",
		"recipientOpr",
		"billerName",
		"taxpayerCode",
		"taxpayerName",
		"recipientCode",
		"recipientName",
		"payerCode",
		"payerName",
		"amount",
		"printDate",
		"invoiceNo",
		"invoiceName",
		"invoiceCode",
		"endorseNo",
		"policyNo",
		"orgCode",
		"docVerCode",
		"idVcInvoicePrint",
		"origionId",
		"canceledOprCode",
		"computerNo",
		"vcInvoicePrintExtt"
})
@XmlRootElement(name = "VC_INVOICE_PRINT")
public class InvoiceGuiZhouUploadDetailDTO implements Serializable{

	@XmlElement(name = "DATE_UPDATED", required = true)
	protected String dateUpdated;
	@XmlElement(name = "UPDATED_BY", required = true)
    protected String updatedBy;
	@XmlElement(name = "DATE_CREATED", required = true)
    protected String dateCreated;
	@XmlElement(name = "CREATED_BY", required = true)
    protected String createdBy;
	@XmlElement(name = "IS_UPLOAD_PLAT", required = true)
    protected String isUploadPlat;
	@XmlElement(name = "STATUS", required = true)
    protected String status;
	@XmlElement(name = "ITEMS", required = true)
    protected String items;
	@XmlElement(name = "INVOICE_TYPE", required = true)
    protected String invoiceType;
	@XmlElement(name = "INVOICE_KIND_NAME", required = true)
    protected String invoiceKindName;
	@XmlElement(name = "INVOICE_KIND_CODE", required = true)
    protected String invoicekindCode;
	@XmlElement(name = "BANK_CODE", required = true)
    protected String bankCode;
	@XmlElement(name = "ZG_INDUSTRY", required = true)
    protected String zgIndustry;
	@XmlElement(name = "REGISTRATION", required = true)
    protected String registration;
	@XmlElement(name = "BANK", required = true)
    protected String bank;
	@XmlElement(name = "ACCOUNT", required = true)
    protected String account;
	@XmlElement(name = "PRINT_TYPE_CODE", required = true)
    protected String printTypeCode;
	@XmlElement(name = "INDUSTRY_NAME", required = true)
    protected String industryName;
	@XmlElement(name = "INDUSTRY_CODE", required = true)
    protected String industryCode;
	@XmlElement(name = "UPPER_AMOUNT", required = true)
    protected String upperAmount;
	@XmlElement(name = "TAX_RATE", required = true)
    protected String taxRate;
	@XmlElement(name = "TAX_AMOUNT", required = true)
    protected String taxAmount;
	@XmlElement(name = "CHECK_NUM", required = true)
    protected String checkNum;
	@XmlElement(name = "END_DATE", required = true)
    protected String endDate;
	@XmlElement(name = "START_DATE", required = true)
    protected String startDate;
	@XmlElement(name = "MACHINE_PRINTED_NO", required = true)
    protected String machinePrintedNo;
	@XmlElement(name = "MACHINE_PRINTED_CODE", required = true)
    protected String machinePrintedCode;
	@XmlElement(name = "BELONG_STAGE", required = true)
    protected String belongStage;
	@XmlElement(name = "SETTLE_ITEM", required = true)
    protected String settleItem;
	@XmlElement(name = "MANAGE_CODE", required = true)
    protected String manageCode;
	@XmlElement(name = "TAX_ORG", required = true)
    protected String taxOrg;
	@XmlElement(name = "PRINT_KIND", required = true)
    protected String printKind;
	@XmlElement(name = "OPERATE_DATE", required = true)
    protected String operateDate;
	@XmlElement(name = "REMARK", required = true)
    protected String remark;
	@XmlElement(name = "CANCELED_DATE", required = true)
    protected String canceledDate;
	@XmlElement(name = "CANCELED_OPR_NAME", required = true)
    protected String canceledOprName;
	@XmlElement(name = "COUNTERACTED_INVOICE_NO", required = true)
    protected String counteractedInvoiceNo;
	@XmlElement(name = "COUNTERACTED_INVOICE_CODE", required = true)
    protected String counteractedInvoiceCode;
	@XmlElement(name = "COUNTERACT_INVOICE_NO", required = true)
    protected String counteractInvoiceNo;
	@XmlElement(name = "COUNTERACT_INVOICE_CODE", required = true)
    protected String counteractInvoiceCode;
	@XmlElement(name = "RECIPIENT_OPR", required = true)
    protected String recipientOpr;
	@XmlElement(name = "BILLER_NAME", required = true)
    protected String billerName;
	@XmlElement(name = "TAXPAYER_CODE", required = true)
    protected String taxpayerCode;
	@XmlElement(name = "TAXPAYER_NAME", required = true)
    protected String taxpayerName;
	@XmlElement(name = "RECIPIENT_CODE", required = true)
    protected String recipientCode;
	@XmlElement(name = "RECIPIENT_NAME", required = true)
    protected String recipientName;
	@XmlElement(name = "PAYER_CODE", required = true)
    protected String payerCode;
	@XmlElement(name = "PAYER_NAME", required = true)
    protected String payerName;
	@XmlElement(name = "AMOUNT", required = true)
    protected String amount;
	@XmlElement(name = "PRINT_DATE", required = true)
    protected String printDate;
	@XmlElement(name = "INVOICE_NO", required = true)
    protected String invoiceNo;
	@XmlElement(name = "INVOICE_NAME", required = true)
    protected String invoiceName;
	@XmlElement(name = "INVOICE_CODE", required = true)
    protected String invoiceCode;
	@XmlElement(name = "ENDORSE_NO", required = true)
    protected String endorseNo;
	@XmlElement(name = "POLICY_NO", required = true)
    protected String policyNo;
	@XmlElement(name = "ORG_CODE", required = true)
    protected String orgCode;
	@XmlElement(name = "DOC_VER_CODE", required = true)
    protected String docVerCode;
	@XmlElement(name = "ID_VC_INVOICE_PRINT", required = true)
    protected String idVcInvoicePrint;
	@XmlElement(name = "ORIGION_ID", required = true)
    protected String origionId;
	@XmlElement(name = "CANCELED_OPR_CODE", required = true)
    protected String canceledOprCode;
	@XmlElement(name = "COMPUTER_NO", required = true)
    protected String computerNo;
	@XmlElement(name = "VC_INVOICE_PRINT_EXTT", required = true)
	protected List<InvoiceGuiZhouUploadExtDTO> vcInvoicePrintExtt;
	
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getIsUploadPlat() {
		return isUploadPlat;
	}
	public void setIsUploadPlat(String isUploadPlat) {
		this.isUploadPlat = isUploadPlat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceKindName() {
		return invoiceKindName;
	}
	public void setInvoiceKindName(String invoiceKindName) {
		this.invoiceKindName = invoiceKindName;
	}
	public String getInvoicekindCode() {
		return invoicekindCode;
	}
	public void setInvoicekindCode(String invoicekindCode) {
		this.invoicekindCode = invoicekindCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getZgIndustry() {
		return zgIndustry;
	}
	public void setZgIndustry(String zgIndustry) {
		this.zgIndustry = zgIndustry;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPrintTypeCode() {
		return printTypeCode;
	}
	public void setPrintTypeCode(String printTypeCode) {
		this.printTypeCode = printTypeCode;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getUpperAmount() {
		return upperAmount;
	}
	public void setUpperAmount(String upperAmount) {
		this.upperAmount = upperAmount;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getMachinePrintedNo() {
		return machinePrintedNo;
	}
	public void setMachinePrintedNo(String machinePrintedNo) {
		this.machinePrintedNo = machinePrintedNo;
	}
	public String getMachinePrintedCode() {
		return machinePrintedCode;
	}
	public void setMachinePrintedCode(String machinePrintedCode) {
		this.machinePrintedCode = machinePrintedCode;
	}
	public String getBelongStage() {
		return belongStage;
	}
	public void setBelongStage(String belongStage) {
		this.belongStage = belongStage;
	}
	public String getSettleItem() {
		return settleItem;
	}
	public void setSettleItem(String settleItem) {
		this.settleItem = settleItem;
	}
	public String getManageCode() {
		return manageCode;
	}
	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}
	public String getTaxOrg() {
		return taxOrg;
	}
	public void setTaxOrg(String taxOrg) {
		this.taxOrg = taxOrg;
	}
	public String getPrintKind() {
		return printKind;
	}
	public void setPrintKind(String printKind) {
		this.printKind = printKind;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCanceledDate() {
		return canceledDate;
	}
	public void setCanceledDate(String canceledDate) {
		this.canceledDate = canceledDate;
	}
	public String getCanceledOprName() {
		return canceledOprName;
	}
	public void setCanceledOprName(String canceledOprName) {
		this.canceledOprName = canceledOprName;
	}
	public String getCounteractedInvoiceNo() {
		return counteractedInvoiceNo;
	}
	public void setCounteractedInvoiceNo(String counteractedInvoiceNo) {
		this.counteractedInvoiceNo = counteractedInvoiceNo;
	}
	public String getCounteractedInvoiceCode() {
		return counteractedInvoiceCode;
	}
	public void setCounteractedInvoiceCode(String counteractedInvoiceCode) {
		this.counteractedInvoiceCode = counteractedInvoiceCode;
	}
	public String getCounteractInvoiceNo() {
		return counteractInvoiceNo;
	}
	public void setCounteractInvoiceNo(String counteractInvoiceNo) {
		this.counteractInvoiceNo = counteractInvoiceNo;
	}
	public String getCounteractInvoiceCode() {
		return counteractInvoiceCode;
	}
	public void setCounteractInvoiceCode(String counteractInvoiceCode) {
		this.counteractInvoiceCode = counteractInvoiceCode;
	}
	public String getRecipientOpr() {
		return recipientOpr;
	}
	public void setRecipientOpr(String recipientOpr) {
		this.recipientOpr = recipientOpr;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getTaxpayerCode() {
		return taxpayerCode;
	}
	public void setTaxpayerCode(String taxpayerCode) {
		this.taxpayerCode = taxpayerCode;
	}
	public String getTaxpayerName() {
		return taxpayerName;
	}
	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}
	public String getRecipientCode() {
		return recipientCode;
	}
	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getPayerCode() {
		return payerCode;
	}
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPrintDate() {
		return printDate;
	}
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getEndorseNo() {
		return endorseNo;
	}
	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getIdVcInvoicePrint() {
		return idVcInvoicePrint;
	}
	public void setIdVcInvoicePrint(String idVcInvoicePrint) {
		this.idVcInvoicePrint = idVcInvoicePrint;
	}
	public String getOrigionId() {
		return origionId;
	}
	public void setOrigionId(String origionId) {
		this.origionId = origionId;
	}
	public String getCanceledOprCode() {
		return canceledOprCode;
	}
	public void setCanceledOprCode(String canceledOprCode) {
		this.canceledOprCode = canceledOprCode;
	}
	public String getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(String computerNo) {
		this.computerNo = computerNo;
	}
	public List<InvoiceGuiZhouUploadExtDTO> getVcInvoicePrintExtt() {
		return vcInvoicePrintExtt;
	}
	public void setVcInvoicePrintExtt(
			List<InvoiceGuiZhouUploadExtDTO> vcInvoicePrintExtt) {
		this.vcInvoicePrintExtt = vcInvoicePrintExtt;
	}
	
	public static void main(String[] args) {
		InvoiceGuiZhouUploadDetailDTO dto1 = new InvoiceGuiZhouUploadDetailDTO();
		dto1.setAccount("test1");
		dto1.setBank("");
		dto1.setCanceledDate("2015-01-01");
		
		InvoiceGuiZhouUploadExtDTO dto2 = new InvoiceGuiZhouUploadExtDTO();
		dto2.setAmount("650");
		dto2.setCarKind("test4");
		
		List<InvoiceGuiZhouUploadExtDTO> dto2List = new ArrayList<InvoiceGuiZhouUploadExtDTO>();
		dto2List.add(dto2);
		dto1.setVcInvoicePrintExtt(dto2List);
		System.out.println(InvoiceGuiZhouUploadDetailDTO.class.getName());
		try{
			JAXBContext jc = JAXBContext.newInstance(new Class[]{Class.forName(InvoiceGuiZhouUploadDetailDTO.class.getName())});
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);

			StringWriter sw = new StringWriter();
			m.marshal(dto1, sw);
			String xml = sw.toString();
			xml = xml.substring(xml.indexOf("\n")+1);
			System.out.println(xml);
			sw.close();
		}catch(Exception e){	
			e.printStackTrace();
		}
	}
	
}
