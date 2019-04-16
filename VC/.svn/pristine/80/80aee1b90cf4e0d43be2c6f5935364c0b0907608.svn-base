package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 单证出入库记录表
 * <p>
 * Date 2014-04-03
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
@Table(name = "VC_STORAGE_IN_OUT")
@SequenceGenerator(name = "SEQ_VC_STORAGE_IN_OUT", sequenceName = "SEQ_VC_STORAGE_IN_OUT", allocationSize = 1)
public class VcStorageInOut implements Serializable {
    private static final long serialVersionUID = -9011158836712498514L;

    /** 单证出入库记录表流水 */
    @Id
    @Column(name = "ID_VC_STORAGE_IN_OUT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_STORAGE_IN_OUT")
    private Long idVcStorageInOut;

    /* *//** 单证出入库记录代码 */
    /*
     * @Column(name = "IN_OUT_CODE") private String inOutCode;
     */

    /** 机构代码 */
    @Column(name = "ORG_CODE")
    private String orgCode;

    /** 操作人代码 */
    @Column(name = "OPR_CODE")
    private String oprCode;

    /** 操作时间 */
    @Column(name = "OPERATE_TIME")
    private Date operateTime;

    /** 出入库标志 I-入库 O-出库 */
    @Column(name = "OPERATE_FLAG")
    private String operateFlag;
    
    /** 出入库原因 */
    @Column(name = "IN_OUT_REASON")
    private String inOutReason;

    /** 创建人 */
    @Column(name = "REMARK")
    private String remark;

    /** 创建人 */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /** 创建时间 */
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    /** 修改人 */
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    /** 修改时间 */
    @Column(name = "DATE_UPDATED")
    private Date dateUpdated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcStorageInOut")
    List<VcStorageInOutDet> VcStorageInOutDets;

    // ----自定义字段--------------------//
    @Transient
    private Date startOperateTime;
    @Transient
    private Date endOperateTime;
    @Transient
    private String orgName;
    @Transient
    private String oprName;

    /** 单证类型 */
    @Transient
    private String docVerCode;
    @Transient
    private String docVerName;
    /** 印刷批次 */
    @Transient
    private String pressBatchNo;
    @Transient
    private String startNumFrom;
    @Transient
    private String startNumTo;

    @Transient
    private String startNum;
    @Transient
    private String endNum;
    @Transient
    private Long idVcStorageInOutDet;

    /** 数量 */
    @Transient
    private Long docNum;

    public Long getIdVcStorageInOut() {
        return idVcStorageInOut;
    }

    public void setIdVcStorageInOut(Long idVcStorageInOut) {
        this.idVcStorageInOut = idVcStorageInOut;
    }

    /*
     * public String getInOutCode() { return inOutCode; }
     * 
     * public void setInOutCode(String inOutCode) { this.inOutCode = inOutCode; }
     */
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOprCode() {
        return oprCode;
    }

    public void setOprCode(String oprCode) {
        this.oprCode = oprCode;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateFlag() {
        return operateFlag;
    }

    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }

    public String getInOutReason() {
        return inOutReason;
    }

    public void setInOutReason(String inOutReason) {
        this.inOutReason = inOutReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<VcStorageInOutDet> getVcStorageInOutDets() {
        return VcStorageInOutDets;
    }

    public void setVcStorageInOutDets(List<VcStorageInOutDet> vcStorageInOutDets) {
        VcStorageInOutDets = vcStorageInOutDets;
    }

    public Date getStartOperateTime() {
        return startOperateTime;
    }

    public void setStartOperateTime(Date startOperateTime) {
        this.startOperateTime = startOperateTime;
    }

    public Date getEndOperateTime() {
        return endOperateTime;
    }

    public void setEndOperateTime(Date endOperateTime) {
        this.endOperateTime = endOperateTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOprName() {
        return oprName;
    }

    public void setOprName(String oprName) {
        this.oprName = oprName;
    }

    public String getDocVerCode() {
        return docVerCode;
    }

    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    public String getDocVerName() {
        return docVerName;
    }

    public void setDocVerName(String docVerName) {
        this.docVerName = docVerName;
    }

    public String getPressBatchNo() {
        return pressBatchNo;
    }

    public void setPressBatchNo(String pressBatchNo) {
        this.pressBatchNo = pressBatchNo;
    }

    public String getStartNumFrom() {
        return startNumFrom;
    }

    public void setStartNumFrom(String startNumFrom) {
        this.startNumFrom = startNumFrom;
    }

    public String getStartNumTo() {
        return startNumTo;
    }

    public void setStartNumTo(String startNumTo) {
        this.startNumTo = startNumTo;
    }

    public String getStartNum() {
        return startNum;
    }

    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    public String getEndNum() {
        return endNum;
    }

    public void setEndNum(String endNum) {
        this.endNum = endNum;
    }

    public Long getDocNum() {
        return docNum;
    }

    public void setDocNum(Long docNum) {
        this.docNum = docNum;
    }

    public Long getIdVcStorageInOutDet() {
        return idVcStorageInOutDet;
    }

    public void setIdVcStorageInOutDet(Long idVcStorageInOutDet) {
        this.idVcStorageInOutDet = idVcStorageInOutDet;
    }

}
