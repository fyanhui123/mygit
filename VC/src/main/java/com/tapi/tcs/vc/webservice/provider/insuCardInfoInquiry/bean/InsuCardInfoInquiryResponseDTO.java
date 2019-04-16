package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean;

import java.io.Serializable;

public class InsuCardInfoInquiryResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**结果状态*/
	private String status;
	/**结果描述*/
	private String resultMessage;
	/**卡信息*/
	private CardInfoDTO cardInfoDTO;
	/**产品信息*/
	private ProductInfoDTO productInfoDTO;
	/**销售信息*/
	private SalesInfoDTO salesInfoDTO;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public CardInfoDTO getCardInfoDTO() {
		return cardInfoDTO;
	}
	public void setCardInfoDTO(CardInfoDTO cardInfoDTO) {
		this.cardInfoDTO = cardInfoDTO;
	}
	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}
	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}
	public SalesInfoDTO getSalesInfoDTO() {
		return salesInfoDTO;
	}
	public void setSalesInfoDTO(SalesInfoDTO salesInfoDTO) {
		this.salesInfoDTO = salesInfoDTO;
	}
}
