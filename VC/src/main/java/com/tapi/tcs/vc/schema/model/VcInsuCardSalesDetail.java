/**
 * 
 */
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
import javax.persistence.Transient;

/**
 * 激活卡销售明细表
 * 
 * @author whj
 * @date 2013-06-27
 * 
 */
@Entity
@Table(name = "VC_INSU_CARD_SALES_DETAIL")
@SequenceGenerator(name = "SEQ_VC_INSU_CARD_SALES_DETAIL", sequenceName = "SEQ_VC_INSU_CARD_SALES_DETAIL", allocationSize = 1)
public class VcInsuCardSalesDetail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5778450802910116313L;

    /** 激活卡销售明细表流水号 */
    @Id
    @Column(name = "ID_VC_INSU_CARD_SALES_DETAIL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_INSU_CARD_SALES_DETAIL")
    private Long idVcInsuCardSalesDetail;

    /** 激活卡销售表流水号 */
    @Column(name = "ID_VC_INSU_CARD_SALES_RECORD", insertable = false, updatable = false)
    private Long idVcInsuCardSalesRecord;

    /** 激活卡销售表 */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = VcInsuCardSalesRecord.class)
    @JoinColumn(name = "ID_VC_INSU_CARD_SALES_RECORD", referencedColumnName = "ID_VC_INSU_CARD_SALES_RECORD", nullable = false)
    private VcInsuCardSalesRecord vcInsuCardSalesRecord;

    /** 单证类型代码 */
    @Column(name = "DOC_VER_CODE")
    private String docVerCode;

    /** 印刷批次 */
    @Column(name = "PRESS_BATCH_NO")
    private String pressBatchNo;

    /** 起始流水号 */
    @Column(name = "START_NUM")
    private String startNum;

    /** 终止流水号 */
    @Column(name = "END_NUM")
    private String endNum;

    /** 销售数量 Number (10) */
    @Column(name = "SALE_NUM")
    private Long saleNum;

    /** 面值 NUMBER(14,2) */
    @Column(name = "VALUE")
    private double value;

    /** 销售金额 NUMBER(14,2) */
    @Column(name = "FEE")
    private double fee;

    /** 单证状态▲ */
    @Column(name = "DOC_STATUS")
    private String docStatus;

    /** 创建人 */
    @Column(name = "created_by")
    private String createdBy;

    /** 创建时间 */
    @Column(name = "date_created")
    private Date dateCreated;

    /** 修改人 */
    @Column(name = "updated_by")
    private String updatedBy;

    /** 修改时间 */
    @Column(name = "date_updated")
    private Date dateUpdated;
    
    
    
   
    

    /**
     * @return the idVcInsuCardSalesDetail
     */
    public Long getIdVcInsuCardSalesDetail() {
        return idVcInsuCardSalesDetail;
    }

    /**
     * @param idVcInsuCardSalesDetail
     *            the idVcInsuCardSalesDetail to set
     */
    public void setIdVcInsuCardSalesDetail(Long idVcInsuCardSalesDetail) {
        this.idVcInsuCardSalesDetail = idVcInsuCardSalesDetail;
    }

    /**
     * @return the idVcInsuCardSalesRecord
     */
    public Long getIdVcInsuCardSalesRecord() {
        return idVcInsuCardSalesRecord;
    }

    /**
     * @param idVcInsuCardSalesRecord
     *            the idVcInsuCardSalesRecord to set
     */
    public void setIdVcInsuCardSalesRecord(Long idVcInsuCardSalesRecord) {
        this.idVcInsuCardSalesRecord = idVcInsuCardSalesRecord;
    }

    /**
     * @return the vcInsuCardSalesRecord
     */
    public VcInsuCardSalesRecord getVcInsuCardSalesRecord() {
        return vcInsuCardSalesRecord;
    }

    /**
     * @param vcInsuCardSalesRecord
     *            the vcInsuCardSalesRecord to set
     */
    public void setVcInsuCardSalesRecord(VcInsuCardSalesRecord vcInsuCardSalesRecord) {
        this.vcInsuCardSalesRecord = vcInsuCardSalesRecord;
    }

    /**
     * @return the docVerCode
     */
    public String getDocVerCode() {
        return docVerCode;
    }

    /**
     * @param docVerCode
     *            the docVerCode to set
     */
    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    /**
     * @return the pressBatchNo
     */
    public String getPressBatchNo() {
        return pressBatchNo;
    }

    /**
     * @param pressBatchNo
     *            the pressBatchNo to set
     */
    public void setPressBatchNo(String pressBatchNo) {
        this.pressBatchNo = pressBatchNo;
    }

    /**
     * @return the startNum
     */
    public String getStartNum() {
        return startNum;
    }

    /**
     * @param startNum
     *            the startNum to set
     */
    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    /**
     * @return the endNum
     */
    public String getEndNum() {
        return endNum;
    }

    /**
     * @param endNum
     *            the endNum to set
     */
    public void setEndNum(String endNum) {
        this.endNum = endNum;
    }

    /**
     * @return the saleNum
     */
    public Long getSaleNum() {
        return saleNum;
    }

    /**
     * @param saleNum
     *            the saleNum to set
     */
    public void setSaleNum(Long saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return the fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * @param fee
     *            the fee to set
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * @return the docStatus
     */
    public String getDocStatus() {
        return docStatus;
    }

    /**
     * @param docStatus
     *            the docStatus to set
     */
    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated
     *            the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     *            the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the dateUpdated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated
     *            the dateUpdated to set
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
