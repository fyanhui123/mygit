package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author 核销列表
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FPHX_INFO", propOrder = {
    "docNum",
    "pressBatchNo",
    "businessNoDTOs",
    "invoicePrintDTO",
    "invoicePrintExtDto",
    "expandsList"
})
public class InvoiceDoUsedInfoDTO {	
	@XmlElement(name = "docNum", required = true)
    private String docNum;                            //单证流水号
	
	@XmlElement(name = "pressBatchNo", required = true)
    private String pressBatchNo;
	
	@XmlElement(name = "businessNoDTOs", required = true)
    private List<InvoiceDoUsedBusinessNoDTO> businessNoDTOs;       //业务号列表
	
	@XmlElement(name = "invoicePrintDTO", required = true)
    private InvoicePrintDTO invoicePrintDTO;          //发票开具信息
	
	@XmlElement(name = "invoicePrintExtDto", required = true)
    private InvoicePrintExtDto invoicePrintExtDto;    //发票开具扩展信息
	
	@XmlElement(name = "expandsList", required = true)
    private List<ExtendDTO> expandsList;              //预留字段
    
    
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getPressBatchNo() {
		return pressBatchNo;
	}
	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}	

    public List<InvoiceDoUsedBusinessNoDTO> getBusinessNoDTOs() {
		return businessNoDTOs;
	}
	public void setBusinessNoDTOs(List<InvoiceDoUsedBusinessNoDTO> businessNoDTOs) {
		this.businessNoDTOs = businessNoDTOs;
	}
	public InvoicePrintDTO getInvoicePrintDTO() {
		return invoicePrintDTO;
	}
	public void setInvoicePrintDTO(InvoicePrintDTO invoicePrintDTO) {
		this.invoicePrintDTO = invoicePrintDTO;
	}
	public InvoicePrintExtDto getInvoicePrintExtDto() {
		return invoicePrintExtDto;
	}
	public void setInvoicePrintExtDto(InvoicePrintExtDto invoicePrintExtDto) {
		this.invoicePrintExtDto = invoicePrintExtDto;
	}
	public List<ExtendDTO> getExpandsList() {
		return expandsList;
	}
	public void setExpandsList(List<ExtendDTO> expandsList) {
		this.expandsList = expandsList;
	}
      

}
