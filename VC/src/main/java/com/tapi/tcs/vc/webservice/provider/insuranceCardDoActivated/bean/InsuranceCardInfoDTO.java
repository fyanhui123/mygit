package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean;

public class InsuranceCardInfoDTO {

	private  String docVerCode;  //单证类型代码
	private  String cardNO;     //卡号
	private  String  businessNo;  //业务号
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getCardNO() {
		return cardNO;
	}
	public void setCardNO(String cardNO) {
		this.cardNO = cardNO;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
}
