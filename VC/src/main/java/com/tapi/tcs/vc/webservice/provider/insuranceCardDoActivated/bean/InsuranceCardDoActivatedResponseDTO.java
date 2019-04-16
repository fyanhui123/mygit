package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JHKJH_RESP_BODY", propOrder = {
		"status",
		"flag"
})
public class InsuranceCardDoActivatedResponseDTO {

	// 返回实体
	@XmlElement(name = "status", required = true)
	private  String  status;  //标志状态
	@XmlElement(name = "flag", required = true)
	private  String  flag;    //备用标志位
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
