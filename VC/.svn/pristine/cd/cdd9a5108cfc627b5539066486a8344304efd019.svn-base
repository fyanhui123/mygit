package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 新老机构map表
 * <p>
 * Date 2013-06-08
 * </p>
 * <p>
 * Module：
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：实体类，不允许修改
 * </p>
 * @author chy
 * @version 1.0
 */
@Entity
@Table(name = "PUB_COMPANY_MAP")
public class PubCompanyMap implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private PubCompanyMapId id; 
	/**机构代码*/
	private String companyCode;

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "companyIdOld", column = @Column(name = "COMPANYID_OLD", nullable = false)),
			@AttributeOverride(name = "companyCodeOld", column = @Column(name = "COMPANYCODE_OLD", nullable = false)) })
	public PubCompanyMapId getId() {
		return id;
	}
	public void setId(PubCompanyMapId id) {
		this.id = id;
	}
	@Column(name = "COMPANYCODE")
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	
}
