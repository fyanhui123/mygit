package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Jtlgxxresponse", propOrder = {
    "title",
    "body"
})
@XmlRootElement(name = "JtLgxxResponse")
public class DownloadResponse implements Serializable{

    @XmlElement(name = "TITLE", required = true)
    protected  DownloadResponseTitle title;
    @XmlElement(name = "BODY", required = true)
    protected  DownloadResponseBody body;
    
	public DownloadResponseTitle getTitle() {
		return title;
	}
	public void setTitle(DownloadResponseTitle title) {
		this.title = title;
	}
	public DownloadResponseBody getBody() {
		return body;
	}
	public void setBody(DownloadResponseBody body) {
		this.body = body;
	}
}
