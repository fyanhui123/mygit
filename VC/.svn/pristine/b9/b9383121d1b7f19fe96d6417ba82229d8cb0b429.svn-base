package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 单证出入库记录明细表
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
@Table(name = "VC_STORAGE_IN_OUT_DET")
@SequenceGenerator(name = "SEQ_VC_STORAGE_IN_OUT_DET", sequenceName = "SEQ_VC_STORAGE_IN_OUT_DET", allocationSize = 1)
public class VcStorageInOutDet implements Serializable {
   
    private static final long serialVersionUID = -3363601530747720463L;

    /** 单证出入库记录明细表流水 */
    @Id
    @Column(name = "ID_VC_STORAGE_IN_OUT_DET")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_STORAGE_IN_OUT_DET")
    private Long idVcStorageInOutDet;
    
    /** 单证出入库记录表流水*/
    @Column(name = "ID_VC_STORAGE_IN_OUT" ,insertable = false, updatable = false)
    private Long idVcStorageInOut;
    
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_VC_STORAGE_IN_OUT")
    private  VcStorageInOut vcStorageInOut;
    
    
    /** 单证类型 */
    @Column(name = "DOC_VER_CODE")
    private String docVerCode;
    
    /** 印刷批次 */
    @Column(name = "PRESS_BATCH_NO")
    private String pressBatchNo;
    
    /** 起始流水 */
    @Column(name = "START_NUM")
    private String startNum;
    
    /** 终止流水 */
    @Column(name = "END_NUM")
    private String endNum;
    
    /** 数量 */
    @Column(name = "DOC_NUM")
    private Long docNum;
    
    /** 出库入库前单证状态 */
    @Column(name = "DOC_STATUS")
    private String docStatus;

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

    public Long getIdVcStorageInOutDet() {
        return idVcStorageInOutDet;
    }

    public void setIdVcStorageInOutDet(Long idVcStorageInOutDet) {
        this.idVcStorageInOutDet = idVcStorageInOutDet;
    }

    public Long getIdVcStorageInOut() {
        return idVcStorageInOut;
    }

    public void setIdVcStorageInOut(Long idVcStorageInOut) {
        this.idVcStorageInOut = idVcStorageInOut;
    }

    public VcStorageInOut getVcStorageInOut() {
        return vcStorageInOut;
    }

    public void setVcStorageInOut(VcStorageInOut vcStorageInOut) {
        this.vcStorageInOut = vcStorageInOut;
    }

    public String getDocVerCode() {
        return docVerCode;
    }

    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    public String getPressBatchNo() {
        return pressBatchNo;
    }

    public void setPressBatchNo(String pressBatchNo) {
        this.pressBatchNo = pressBatchNo;
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

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
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
    

   
}
