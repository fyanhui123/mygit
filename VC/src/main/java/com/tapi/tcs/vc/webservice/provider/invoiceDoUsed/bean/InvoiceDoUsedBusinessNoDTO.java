package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FPHX_BUS", propOrder = {
    "businessNo",
    "relationBusinessNo",
    "payNo",
    "counteractFlag",
    "batchNo",
    "expandsList"
})
public class InvoiceDoUsedBusinessNoDTO {
	@XmlElement(name = "businessNo", required = true)
    private String businessNo; // 业务号

	@XmlElement(name = "relationBusinessNo", required = true)
    private String relationBusinessNo; // 批单对应的保单业务号
	
	@XmlElement(name = "payNo", required = true)
    private String payNo; // 缴费期次
	
	@XmlElement(name = "counteractFlag", required = true)
    private String counteractFlag; // 红冲标志
	
	@XmlElement(name = "batchNo", required = true)
    private String batchNo; // 拆分批次
	
	@XmlElement(name = "expandsList", required = true)
    private List<ExtendDTO> expandsList; // 预留字段

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getRelationBusinessNo() {
        return relationBusinessNo;
    }

    public void setRelationBusinessNo(String relationBusinessNo) {
        this.relationBusinessNo = relationBusinessNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getCounteractFlag() {
        return counteractFlag;
    }

    public void setCounteractFlag(String counteractFlag) {
        this.counteractFlag = counteractFlag;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public List<ExtendDTO> getExpandsList() {
        return expandsList;
    }

    public void setExpandsList(List<ExtendDTO> expandsList) {
        this.expandsList = expandsList;
    }

}
