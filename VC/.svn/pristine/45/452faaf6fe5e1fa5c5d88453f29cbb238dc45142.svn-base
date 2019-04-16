package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PubCompanyMapId implements Serializable{

	private static final long serialVersionUID = 1L;
	/**旧机构ID*/
	private Integer companyIdOld;
	/**旧机构编码*/
	private String companyCodeOld;
	
	@Column(name = "COMPANYID_OLD")
	public Integer getCompanyIdOld() {
		return companyIdOld;
	}
	public void setCompanyIdOld(Integer companyIdOld) {
		this.companyIdOld = companyIdOld;
	}
	@Column(name = "COMPANYCODE_OLD")
	public String getCompanyCodeOld() {
		return companyCodeOld;
	}
	public void setCompanyCodeOld(String companyCodeOld) {
		this.companyCodeOld = companyCodeOld;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PubCompanyMapId))
			return false;
		PubCompanyMapId castOther = (PubCompanyMapId) other;

		return ((this.getCompanyIdOld() == castOther.getCompanyIdOld()) || (this
				.getCompanyIdOld() != null
				&& castOther.getCompanyIdOld() != null && this.getCompanyIdOld()
				.equals(castOther.getCompanyIdOld())))
				&& ((this.getCompanyCodeOld() == castOther.getCompanyCodeOld()) || (this
						.getCompanyCodeOld() != null
						&& castOther.getCompanyCodeOld() != null && this
						.getCompanyCodeOld().equals(castOther.getCompanyCodeOld())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCompanyIdOld() == null ? 0 : this.getCompanyIdOld()
						.hashCode());
		result = 37 * result
				+ (getCompanyCodeOld() == null ? 0 : this.getCompanyCodeOld().hashCode());
		return result;
	}
}
