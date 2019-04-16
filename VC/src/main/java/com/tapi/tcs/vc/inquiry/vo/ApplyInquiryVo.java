package com.tapi.tcs.vc.inquiry.vo;

import java.util.Date;

/**
 * 单证申领查询统计VO
 * <p>
 * Date 2013-04-09
 * </p>
 * <p>
 * Module：统计查询
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author zhxiao
 * @version 1.0
 */
public class ApplyInquiryVo {

    /** 申领单号 */
    private String applyCode;
    /** 申请人代码 */
    private String applyOprCode;
    /** 申请人名称 */

    /** 单证种类 代码 */
    private String docTypeCode;

    /** 单证险类代码 */
    private String insuTypeCode;
    /** 单证归属险种代码 */
    private String insuKindCode;

    private String applyOprName;
    /** 单证类型代码 */
    private String docVerCode;
    /** 单证类型名称 */
    private String docVerName;
    /** 申领机构代码 */
    private String applyOrgCode;
    /** 申领机构名称 */
    private String applyOrgName;
    /** 申领单状态 */
    private String applyStatus;
    /** 申请时间 */
    private Date applyStartDate;
    private Date applyEndDate;
    /** 申领数量 */
    private Integer applyNum;
    /** 申领时间 */
    private Date applyTime;
    /** 是否包含下级部门0-否 1-是 */
    private String isContainSubOrg;

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyOprCode(String applyOprCode) {
        this.applyOprCode = applyOprCode;
    }

    public String getApplyOprCode() {
        return applyOprCode;
    }

    public void setApplyOprName(String applyOprName) {
        this.applyOprName = applyOprName;
    }

    public String getApplyOprName() {
        return applyOprName;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getInsuTypeCode() {
        return insuTypeCode;
    }

    public void setInsuTypeCode(String insuTypeCode) {
        this.insuTypeCode = insuTypeCode;
    }

    public String getInsuKindCode() {
        return insuKindCode;
    }

    public void setInsuKindCode(String insuKindCode) {
        this.insuKindCode = insuKindCode;
    }

    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    public String getDocVerCode() {
        return docVerCode;
    }

    public void setDocVerName(String docVerName) {
        this.docVerName = docVerName;
    }

    public String getDocVerName() {
        return docVerName;
    }

    public void setApplyOrgCode(String applyOrgCode) {
        this.applyOrgCode = applyOrgCode;
    }

    public String getApplyOrgCode() {
        return applyOrgCode;
    }

    public void setApplyOrgName(String applyOrgName) {
        this.applyOrgName = applyOrgName;
    }

    public String getApplyOrgName() {
        return applyOrgName;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public Date getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyEndDate(Date applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

    public Date getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setIsContainSubOrg(String isContainSubOrg) {
        this.isContainSubOrg = isContainSubOrg;
    }

    public String getIsContainSubOrg() {
        return isContainSubOrg;
    }
}
