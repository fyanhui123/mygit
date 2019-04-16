package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JHKPAY_RESP_RES", propOrder = {
    "businessNoDTOs",
    "status",
    "flag"
})
public class InsuranceCardDoPaidResultDTO {
    /**
     * 业务号
     */
	@XmlElement(name = "businessNoDTOs", required = true)
    private String businessNoDTOs;

    /** 回写成功和失败标志 (1：成功 0：失败) */
	@XmlElement(name = "status", required = true)
	
    private String status;
    /** 备用标志位 */
	@XmlElement(name = "flag", required = true)
    private String flag;

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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

}
