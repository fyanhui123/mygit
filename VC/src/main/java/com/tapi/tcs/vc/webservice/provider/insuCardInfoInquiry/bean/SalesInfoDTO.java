package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean;

import java.io.Serializable;

public class SalesInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**渠道类型*/
	private String channelDetailCode;
	/**业务来源*/
	private String businessSource;
	/**销售人员代码*/
	private String sellerCode;
	/**销售人员名称*/
	private String sellerName;
	/**销售人员所属机构代码*/
	private String saleOrgCode;
	/**销售人员所属机构名称*/
	private String saleOrgName;
	/**团队代码*/
	private String teamCode;
	/**所属中介机构代码*/
	private String agencyOrgCode;
	/**协议代码*/
	private String agreementNo;
	
	/**跟单业务员代码*/
	private String handlerCode;
	/**跟单业务员名称*/
	private String handlerName;
	
	
	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	public String getBusinessSource() {
		return businessSource;
	}
	public void setBusinessSource(String businessSource) {
		this.businessSource = businessSource;
	}
	public String getSaleOrgCode() {
		return saleOrgCode;
	}
	public void setSaleOrgCode(String saleOrgCode) {
		this.saleOrgCode = saleOrgCode;
	}
	public String getSaleOrgName() {
		return saleOrgName;
	}
	public void setSaleOrgName(String saleOrgName) {
		this.saleOrgName = saleOrgName;
	}
	public String getAgencyOrgCode() {
		return agencyOrgCode;
	}
	public void setAgencyOrgCode(String agencyOrgCode) {
		this.agencyOrgCode = agencyOrgCode;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getChannelDetailCode() {
		return channelDetailCode;
	}
	public void setChannelDetailCode(String channelDetailCode) {
		this.channelDetailCode = channelDetailCode;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	
}
