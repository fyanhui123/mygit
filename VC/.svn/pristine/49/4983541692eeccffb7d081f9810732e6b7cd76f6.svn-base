package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JHK_PAY_REFUND", propOrder = {
    "businessNoDTOs",
    "payOrRefund",
    "paidStatus",
    "failReason",
    "failDesc"
})
public class InsuranceCardDoPaidorRefundDTO {

    /** 业务号列表 */
	@XmlElement(name = "businessNoDTOs", required = true)
    private String businessNoDTOs;

    /** 缴费退费标志(P：缴费 R：退费) */
	@XmlElement(name = "payOrRefund", required = true)
    private String payOrRefund;

    /** 缴费成功和失败标志 (1：成功) */
	@XmlElement(name = "paidStatus", required = true)
    private String paidStatus;
    
    /**
     * 退费失败原因代码 (01-账号不存在或账号停用
     *                  09-其他非账户原因)
     * 注：退费失败时该字段不能为空
     *  
     */
	@XmlElement(name = "failReason", required = true)
    private String    failReason;
    
   /**
    *  退费失败原因详述    
    */
	@XmlElement(name = "failDesc", required = true)
    private String failDesc;

    /**
     * @return the businessNoDTOs
     */
    public String getBusinessNoDTOs() {
        return businessNoDTOs;
    }

    /**
     * @param businessNoDTOs
     *            the businessNoDTOs to set
     */
    public void setBusinessNoDTOs(String businessNoDTOs) {
        this.businessNoDTOs = businessNoDTOs;
    }

    /**
     * @return the payOrRefund
     */
    public String getPayOrRefund() {
        return payOrRefund;
    }

    /**
     * @param payOrRefund
     *            the payOrRefund to set
     */
    public void setPayOrRefund(String payOrRefund) {
        this.payOrRefund = payOrRefund;
    }

    /**
     * @return the paidStatus
     */
    public String getPaidStatus() {
        return paidStatus;
    }

    /**
     * @param paidStatus
     *            the paidStatus to set
     */
    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    /**
     * @return the failReason
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * @param failReason the failReason to set
     */
    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    /**
     * @return the failDesc
     */
    public String getFailDesc() {
        return failDesc;
    }

    /**
     * @param failDesc the failDesc to set
     */
    public void setFailDesc(String failDesc) {
        this.failDesc = failDesc;
    }
    
    

}
