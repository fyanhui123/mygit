package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PubRiskRationId implements Serializable {

	private static final long serialVersionUID = -1329570716146988994L;

	private String riskCode; // 险种代码
	private Long serialNo; // 定额类型代码+标的代码+险别代码或者定额类型代码+标的代码+险别代码+责任
	private String companyCode; // 机构代码

	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	@Column(name = "SERIALNO")
	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "COMPANYCODE")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PubRiskRationId))
			return false;
		PubRiskRationId castOther = (PubRiskRationId) other;

		return ((this.getRiskCode() == castOther.getRiskCode()) || (this
				.getRiskCode() != null && castOther.getRiskCode() != null && this
				.getRiskCode().equals(castOther.getRiskCode())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
						.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRiskCode() == null ? 0 : this.getRiskCode().hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37 * result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode());
		return result;
	}
}
