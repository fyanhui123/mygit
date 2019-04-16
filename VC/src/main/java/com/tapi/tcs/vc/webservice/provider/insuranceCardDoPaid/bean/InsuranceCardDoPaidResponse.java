package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JHKPAY_RESP", propOrder = {
		"responseHead",
		"responseBody"
})
@XmlRootElement(name = "REQUEST_BODY")
public class InsuranceCardDoPaidResponse {

    /** 公共响应头 */
	@XmlElement(name = "responseHead", required = true)
    private ResponseHeadDTO responseHead;
    /** 响应体 */
	@XmlElement(name = "responseBody", required = true)
    private InsuranceCardDoPaidResponseDTO responseBody;

    public ResponseHeadDTO getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHeadDTO responseHead) {
        this.responseHead = responseHead;
    }

    /**
     * @return the responseBody
     */
    public InsuranceCardDoPaidResponseDTO getResponseBody() {
        return responseBody;
    }

    /**
     * @param responseBody
     *            the responseBody to set
     */
    public void setResponseBody(InsuranceCardDoPaidResponseDTO responseBody) {
        this.responseBody = responseBody;
    }

}
