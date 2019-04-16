package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PUB_RISK_CLASS")
public class PubRiskClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * RISKCLASS VARCHAR2(3) 险类代码
     */
    @Id
    @Column(name = "RISKCLASS")
    private String riskClass;

    /**
     * CLASSCNAME VARCHAR2(400) 险类简体中文名称
     */
    @Column(name = "CLASSCNAME")
    private String classCname;

    /**
     * CLASSTNAME VARCHAR2(400) 险类繁体中文名称
     */
    @Column(name = "CLASSTNAME")
    private String classTname;

    /**
     * CLASSENAME VARCHAR2(400) 险类英文名称
     */
    @Column(name = "CLASSENAME")
    private String classEname;

    /**
     * CREATORCODE VARCHAR2(50) 创建人
     */
    @Column(name = "CREATORCODE")
    private String creatorCode;

    /**
     * CREATETIME DATE 创建时间
     */
    @Column(name = "CREATETIME")
    private Date createTime;

    /**
     * UPDATERCODE VARCHAR2(50)最后修改人
     */
    @Column(name = "UPDATERCODE")
    private String updaterCode;

    /**
     * UPDATETIME DATE 最后修改时间
     */
    @Column(name = "UPDATETIME")
    private Date updateTime;

    /**
     * VALIDDATE DATE 生效日期
     */
    @Column(name = "VALIDDATE")
    private Date validDate;

    /**
     * INVALIDDATE DATE 失效日期
     */
    @Column(name = "INVALIDDATE")
    private Date invalidDate;

    /**
     * VALIDIND VARCHAR2(1) 1:有效 0:无效
     */
    @Column(name = "VALIDIND")
    private String validInd;

    /**
     * REMARK VARCHAR2(4000) 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * FLAG VARCHAR2(2) 备用标志字段
     */
    @Column(name = "FLAG")
    private String flag;
    
    /******自定义字段**********************/
    /**险种代码*/
    @Transient
    private String riskClassLike;

    /**
     * @return the riskClass
     */
    public String getRiskClass() {
        return riskClass;
    }

    /**
     * @param riskClass
     *            the riskClass to set
     */
    public void setRiskClass(String riskClass) {
        this.riskClass = riskClass;
    }

    /**
     * @return the classCname
     */
    public String getClassCname() {
        return classCname;
    }

    /**
     * @param classCname
     *            the classCname to set
     */
    public void setClassCname(String classCname) {
        this.classCname = classCname;
    }

    /**
     * @return the classTname
     */
    public String getClassTname() {
        return classTname;
    }

    /**
     * @param classTname
     *            the classTname to set
     */
    public void setClassTname(String classTname) {
        this.classTname = classTname;
    }

    /**
     * @return the classEname
     */
    public String getClassEname() {
        return classEname;
    }

    /**
     * @param classEname
     *            the classEname to set
     */
    public void setClassEname(String classEname) {
        this.classEname = classEname;
    }

    /**
     * @return the creatorCode
     */
    public String getCreatorCode() {
        return creatorCode;
    }

    /**
     * @param creatorCode
     *            the creatorCode to set
     */
    public void setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updaterCode
     */
    public String getUpdaterCode() {
        return updaterCode;
    }

    /**
     * @param updaterCode
     *            the updaterCode to set
     */
    public void setUpdaterCode(String updaterCode) {
        this.updaterCode = updaterCode;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the validDate
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * @param validDate
     *            the validDate to set
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * @return the invalidDate
     */
    public Date getInvalidDate() {
        return invalidDate;
    }

    /**
     * @param invalidDate
     *            the invalidDate to set
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    /**
     * @return the validInd
     */
    public String getValidInd() {
        return validInd;
    }

    /**
     * @param validInd
     *            the validInd to set
     */
    public void setValidInd(String validInd) {
        this.validInd = validInd;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRiskClassLike() {
        return riskClassLike;
    }

    public void setRiskClassLike(String riskClassLike) {
        this.riskClassLike = riskClassLike;
    }

}
