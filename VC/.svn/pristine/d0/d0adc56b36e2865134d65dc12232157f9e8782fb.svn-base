package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JHKJH", propOrder = {
		"docVerCode",
		"cardNO",
		"businessNo",
		"activeTime",
		"activeType"
})
@XmlRootElement(name = "REQUEST_BODY")
public class InsuranceCardDoActivatedRequestDTO {

	// 请求实体
	@XmlElement(name = "docVerCode", required = true)
	private  String  docVerCode;  //单证类型代码
	@XmlElement(name = "cardNO", required = true)
	private  String  cardNO;     //卡号
	@XmlElement(name = "businessNo", required = true)
	private  String  businessNo;  //业务号
	@XmlElement(name = "activeTime", required = true)
	private  Date    activeTime;  //激活时间
	@XmlElement(name = "activeType", required = true)
	private  String  activeType;  //激活方式
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
	public Date getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	public String getActiveType() {
		return activeType;
	}
	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}
}
