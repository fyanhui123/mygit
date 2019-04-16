package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 区域表
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
@Table(name = "PUB_STANDARDAREAS")
public class PubStandardareas implements Serializable {

    private static final long serialVersionUID = 6930272806072177475L;
    /** 主键 */
    @Id
    @Column(name = "URID")
    private Long urid;
    /** 区域代码 */
    @Column(name = "CODE")
    private String code;
    /** 区域名称 */
    @Column(name = "NAME")
    private String name;
    /** 描述 */
    @Column(name = "DESCRIPTION")
    private String description;
    /** 更新时间 */
    @Column(name = "UPTODATE")
    private Date uptodate;
    /** 是否有效 1=有效 0=无效 */
    @Column(name = "ISACTIVE")
    private int isactive;
    /** 显示排序 */
    @Column(name = "DISPLAYORDER ")
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
    private Long nlastmodifiedby;
    /** 行版本 */
    @Column(name = "ROWVERSION")
    private Long rowversion;
    /** 省ID */
    @Column(name = "PROVINCEID")
    private Long provinceid;

    // 自定义字段star+++++++++++///
    // 区域代码模糊匹配查询
    @Transient
    private String codeLike;
    // 区域名称模糊匹配查询
    @Transient
    private String nameLike;

    // 代码/名称模糊匹配查询
    @Transient
    private String codeNameLike;

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
     * @return the nlastmodifiedby
     */
    public Long getNlastmodifiedby() {
        return nlastmodifiedby;
    }

    /**
     * @param nlastmodifiedby
     *            the nlastmodifiedby to set
     */
    public void setNlastmodifiedby(Long nlastmodifiedby) {
        this.nlastmodifiedby = nlastmodifiedby;
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
     * @return the provinceid
     */
    public Long getProvinceid() {
        return provinceid;
    }

    /**
     * @param provinceid
     *            the provinceid to set
     */
    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
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

}
