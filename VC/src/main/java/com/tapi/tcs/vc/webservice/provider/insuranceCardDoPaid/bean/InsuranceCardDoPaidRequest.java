package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class InsuranceCardDoPaidRequest {

    /** 公共请求头 */
    private RequestHeadDTO requestHead;

    /** 请求体 */
    private InsuranceCardDoPaidRequestDTO requestBody;

    public RequestHeadDTO getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(RequestHeadDTO requestHead) {
        this.requestHead = requestHead;
    }

    /**
     * @return the requestBody
     */
    public InsuranceCardDoPaidRequestDTO getRequestBody() {
        return requestBody;
    }

    /**
     * @param requestBody
     *            the requestBody to set
     */
    public void setRequestBody(InsuranceCardDoPaidRequestDTO requestBody) {
        this.requestBody = requestBody;
    }

}
