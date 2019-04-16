package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 银行支行信息表
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
 * 
 * @author whj
 * @version 1.0
 */
@Entity
@Table(name = "PUB_BANKLOCATIONS")
public class PubBanklocations implements Serializable {

    private static final long serialVersionUID = -1426555457718577492L;
    /** 主键 */
    @Id
    @Column(name = "URID")
    private Long urid;
    /** 银行联行号 */
    @Column(name = "CODE")
    private String code;
    /** 支行名称 */
    @Column(name = "NAME")
    private String name;
    /** 银行ID */
    @Column(name = "SYBANKID")
    private Long sybankid;
    /** 区域ID */
    @Column(name = "STANDARDAREAID")
    private Long standardareaid;
    /** 主键 */
    @Column(name = "DESCRIPTION")
    private String description;
    /** 更新时间 */
    @Column(name = "UPTODATE")
    private Date uptodate;
    /** 是否有效 1=有效 0=无效 */
    @Column(name = "ISACTIVE")
    private int isactive;
    /** 显示排序 */
    @Column(name = "DISPLAYORDER")
    private Long displayorder;
    /** 创建时间 */
    @Column(name = "CREATEDON")
    private Date createdon;
    /** 创建者 */
    @Column(name = "CREATEDBY")
    private Long createdby;
    /** 最后修改时间 */
    @Column(name = "LASTMODIFIEDON")
    private Date lastmodifiedon;
    /** 最后修改者 */
    @Column(name = "LASTMODIFIEDBY")
    private Long lastmodifiedby;
    /** 行版本 */
    @Column(name = "ROWVERSION")
    private Long rowversion;

    // 自定义字段star+++++++++++///

    // 银行联行号模糊匹配查询
    @Transient
    private String codeLike;
    // 银行联行名称模糊匹配查询
    @Transient
    private String nameLike;

    // 代码/名称模糊匹配查询
    @Transient
    private String codeNameLike;
    
    // 银行编码
    @Transient
    private String bankCode;
    // 区域编码
    @Transient
    private String areaCode;

    // 自定义字段end+++++++++++///

    /**
     * @return the urid
     */
    public Long getUrid() {
        return urid;
    }

    /**
     * @param urid
     *            the urid to set
     */
    public void setUrid(Long urid) {
        this.urid = urid;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sybankid
     */
    public Long getSybankid() {
        return sybankid;
    }

    /**
     * @param sybankid
     *            the sybankid to set
     */
    public void setSybankid(Long sybankid) {
        this.sybankid = sybankid;
    }

    /**
     * @return the standardareaid
     */
    public Long getStandardareaid() {
        return standardareaid;
    }

    /**
     * @param standardareaid
     *            the standardareaid to set
     */
    public void setStandardareaid(Long standardareaid) {
        this.standardareaid = standardareaid;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the uptodate
     */
    public Date getUptodate() {
        return uptodate;
    }

    /**
     * @param uptodate
     *            the uptodate to set
     */
    public void setUptodate(Date uptodate) {
        this.uptodate = uptodate;
    }

    /**
     * @return the isactive
     */
    public int getIsactive() {
        return isactive;
    }

    /**
     * @param isactive
     *            the isactive to set
     */
    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    /**
     * @return the displayorder
     */
    public Long getDisplayorder() {
        return displayorder;
    }

    /**
     * @param displayorder
     *            the displayorder to set
     */
    public void setDisplayorder(Long displayorder) {
        this.displayorder = displayorder;
    }

    /**
     * @return the createdon
     */
    public Date getCreatedon() {
        return createdon;
    }

    /**
     * @param createdon
     *            the createdon to set
     */
    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    /**
     * @return the createdby
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * @param createdby
     *            the createdby to set
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * @return the lastmodifiedon
     */
    public Date getLastmodifiedon() {
        return lastmodifiedon;
    }

    /**
     * @param lastmodifiedon
     *            the lastmodifiedon to set
     */
    public void setLastmodifiedon(Date lastmodifiedon) {
        this.lastmodifiedon = lastmodifiedon;
    }

    /**
     * @return the lastmodifiedby
     */
    public Long getLastmodifiedby() {
        return lastmodifiedby;
    }

    /**
     * @param lastmodifiedby
     *            the lastmodifiedby to set
     */
    public void setLastmodifiedby(Long lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    /**
     * @return the rowversion
     */
    public Long getRowversion() {
        return rowversion;
    }

    /**
     * @param rowversion
     *            the rowversion to set
     */
    public void setRowversion(Long rowversion) {
        this.rowversion = rowversion;
    }

    /**
     * @return the codeLike
     */
    public String getCodeLike() {
        return codeLike;
    }

    /**
     * @param codeLike
     *            the codeLike to set
     */
    public void setCodeLike(String codeLike) {
        this.codeLike = codeLike;
    }

    /**
     * @return the nameLike
     */
    public String getNameLike() {
        return nameLike;
    }

    /**
     * @param nameLike
     *            the nameLike to set
     */
    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    /**
     * @return the codeNameLike
     */
    public String getCodeNameLike() {
        return codeNameLike;
    }

    /**
     * @param codeNameLike
     *            the codeNameLike to set
     */
    public void setCodeNameLike(String codeNameLike) {
        this.codeNameLike = codeNameLike;
    }

    /**
     * @return the bankCode
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode the bankCode to set
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @return the areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}
