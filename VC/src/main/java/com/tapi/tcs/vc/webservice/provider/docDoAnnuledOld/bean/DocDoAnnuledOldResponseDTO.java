package com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean;

public class DocDoAnnuledOldResponseDTO {
	
	/** 单证类型代码 */
	private String docVerCode;
	/** 单证流水号 */
	private String docSerialNo;
	/**单证作废成功与否标记 */
	private String doAnnuledStatus;
	
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getDocSerialNo() {
		return docSerialNo;
	}
	public void setDocSerialNo(String docSerialNo) {
		this.docSerialNo = docSerialNo;
	}
	public String getDoAnnuledStatus() {
		return doAnnuledStatus;
	}
	public void setDoAnnuledStatus(String doAnnuledStatus) {
		this.doAnnuledStatus = doAnnuledStatus;
	}
}
