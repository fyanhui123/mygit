package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.vo;

import java.util.List;

public class DocVerInquiryRequestVO {

	/**
	 * 险种代码
	 */
	private String insuKindCode;
	
	/**
	 * 机构代码
	 */
	private List<String> orgCodeList;
	
	/**
	 * 单证种类ID列表
	 */
	private List<Long> idVcDocTypeList;
	
	/**
	 * 单证类型ID
	 */
	private Long idVcDocVer;

	public String getInsuKindCode() {
		return insuKindCode;
	}

	public void setInsuKindCode(String insuKindCode) {
		this.insuKindCode = insuKindCode;
	}

	public List<String> getOrgCodeList() {
		return orgCodeList;
	}

	public void setOrgCodeList(List<String> orgCodeList) {
		this.orgCodeList = orgCodeList;
	}

	public List<Long> getIdVcDocTypeList() {
		return idVcDocTypeList;
	}

	public void setIdVcDocTypeList(List<Long> idVcDocTypeList) {
		this.idVcDocTypeList = idVcDocTypeList;
	}

	public Long getIdVcDocVer() {
		return idVcDocVer;
	}

	public void setIdVcDocVer(Long idVcDocVer) {
		this.idVcDocVer = idVcDocVer;
	}
	
}
