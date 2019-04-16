package com.tapi.tcs.vc.invoice.vo.jiangsu;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoice", propOrder = {
	"data",
	"detail"
})
public class JSInvoiceDTO implements Serializable{

	@XmlElement(name = "data", required = true)
	protected JSInvoiceDataDTO data;
	@XmlElement(name = "detail", required = true)
	protected String detail;
	
	public JSInvoiceDataDTO getData() {
		return data;
	}
	public void setData(JSInvoiceDataDTO data) {
		this.data = data;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
