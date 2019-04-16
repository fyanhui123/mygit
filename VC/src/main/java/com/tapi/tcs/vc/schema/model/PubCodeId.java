package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PubCodeId implements Serializable{

	private static final long serialVersionUID = -5261403300083825632L;
	private String codeType;
	private String companyCode;
	private String codeCode;
	
	@Column(name = "CODETYPE")
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	@Column(name = "COMPANYCODE")
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	@Column(name = "CODECODE")
	public String getCodeCode() {
		return codeCode;
	}
	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PubCodeId))
			return false;
		PubCodeId castOther = (PubCodeId) other;

		return ((this.getCodeType() == castOther.getCodeType()) || (this
				.getCodeType() != null
				&& castOther.getCodeType() != null && this.getCodeType()
				.equals(castOther.getCodeType())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
						.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())))
				&&(this.getCodeCode() == castOther.getCodeCode()) || (this
						.getCodeCode() != null
						&& castOther.getCodeCode() != null && this
						.getCodeCode().equals(castOther.getCodeCode()));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeType() == null ? 0 : this.getCodeType()
						.hashCode());
		result = 37 * result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode());
		result = 37 * result
				+ (getCodeCode() == null ? 0 : this.getCodeCode().hashCode());
		return result;
	}
}
