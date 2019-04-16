package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JHK_PAY", propOrder = {
		"insuranceCardDoPaidorRefundList"
})
@XmlRootElement(name = "REQUEST_BODY")
public class InsuranceCardDoPaidRequestDTO {

    /** 激活卡收付费信息列表 */
	@XmlElement(name = "insuranceCardDoPaidorRefundList", required = true)
    private List<InsuranceCardDoPaidorRefundDTO> insuranceCardDoPaidorRefundList;

    /**
     * @return the insuranceCardDoPaidorRefundList
     */
    public List<InsuranceCardDoPaidorRefundDTO> getInsuranceCardDoPaidorRefundList() {
        return insuranceCardDoPaidorRefundList;
    }

    /**
     * @param insuranceCardDoPaidorRefundList
     *            the insuranceCardDoPaidorRefundList to set
     */
    public void setInsuranceCardDoPaidorRefundList(List<InsuranceCardDoPaidorRefundDTO> insuranceCardDoPaidorRefundList) {
        this.insuranceCardDoPaidorRefundList = insuranceCardDoPaidorRefundList;
    }

}
