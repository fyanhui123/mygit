package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PubCodeTransferId implements Serializable{

	private static final long serialVersionUID = -5261403300083825632L;
	private String companyCode;
	private String configCode;
	private String innerCode;
	private String outerCodeType;
	
	@Column(name = "COMPANYCODE")
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	@Column(name = "CONFIGCODE")
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	
	@Column(name = "INNERCODE")
	public String getInnerCode() {
		return innerCode;
	}
	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
	
	@Column(name = "OUTERCODETYPE")
	public String getOuterCodeType() {
		return outerCodeType;
	}
	public void setOuterCodeType(String outerCodeType) {
		this.outerCodeType = outerCodeType;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PubCodeId))
			return false;
		PubCodeTransferId castOther = (PubCodeTransferId) other;

		return ((this.getCompanyCode() == castOther.getCompanyCode()) || (this.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this.getCompanyCode().equals(castOther.getCompanyCode())))
				&& ((this.getConfigCode() == castOther.getConfigCode()) || (this.getConfigCode() != null
						&& castOther.getConfigCode() != null && this.getConfigCode().equals(castOther.getConfigCode())))
				&&(this.getInnerCode() == castOther.getInnerCode()) || (this.getInnerCode() != null
						&& castOther.getInnerCode() != null && this.getInnerCode().equals(castOther.getInnerCode()))
				&& ((this.getOuterCodeType() == castOther.getOuterCodeType()) || (this.getOuterCodeType() != null
						&& castOther.getOuterCodeType() != null && this.getOuterCodeType().equals(castOther.getOuterCodeType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode());
		result = 37 * result + (getConfigCode() == null ? 0 : this.getConfigCode().hashCode());
		result = 37 * result + (getInnerCode() == null ? 0 : this.getInnerCode().hashCode());
		result = 37 * result + (getOuterCodeType() == null ? 0 : this.getOuterCodeType().hashCode());
		return result;
	}
}
