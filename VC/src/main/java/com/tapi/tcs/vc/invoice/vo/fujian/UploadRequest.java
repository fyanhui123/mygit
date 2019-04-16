package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JtUploadKpxxRequest", propOrder = {
    "title",
    "body"
})
@XmlRootElement(name = "JtUploadKpxxRequest")
public class UploadRequest implements Serializable{
	
	@XmlElement(name = "TITLE", required = true)
	protected RequestTitle title;
	@XmlElement(name = "BODY", required = true)
	protected UploadRequestBody body;
	
	public RequestTitle getTitle() {
		return title;
	}
	public void setTitle(RequestTitle title) {
		this.title = title;
	}
	public UploadRequestBody getBody() {
		return body;
	}
	public void setBody(UploadRequestBody body) {
		this.body = body;
	}
}
