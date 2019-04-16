package com.tapi.tcs.vc.baseinfo.vo;

import java.io.Serializable;

public class DocVerSimpleVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3481400623417446271L;
	
	private String docVerCode;
	private String docVerName;
	private String docTypeCode;
	
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getDocVerName() {
		return docVerName;
	}
	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	
}
