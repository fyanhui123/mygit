/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
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
 * 激活卡导入信息记录表
 * @author whj
 * 
 */
@Entity
@Table(name = "VC_INSU_CARD_IMPORT_INFO")
@SequenceGenerator(name = "SEQ_VC_INSU_CARD_IMPORT_INFO", sequenceName = "SEQ_VC_INSU_CARD_IMPORT_INFO", allocationSize = 1)
public class VcInsuCardImportInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3542837816721058773L;

    /** 导入操作流水 */  
    @Id
    @Column(name = "ID_VC_INSU_CARD_IMPORT_INFO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_INSU_CARD_IMPORT_INFO")
    private Long idVcInsuCardImportInfo;
    
    /** 单证类型代码 */
    @Column(name = "DOC_VER_CODE")
    private String docVerCode;
    
    /** 起始卡号 */
    @Column(name = "START_CARD_NO")
    private String startCardNo;
    
    /** 终止卡号 */
    @Column(name = "END_CARD_NO")
    private String endCardNo;
   
    /** 导入数量 */
    @Column(name = "IMPORT_NUM")
    private Long importNum;
   
    /** 导入时间 */
    @Column(name = "IMPORT_TIME")
    private Date importTime;
   
    /** 导入操作人代码 */
    @Column(name = "OPR_CODE")
    private String oprCode;
   
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
    
    
    //------自定义字段---------
    /** 异常记录数目 */ 
    @Transient
    private Long badRecordNum;
    /** 导入校验结果 */    
    @Transient
    private boolean importResult;
    @Transient
    private String importResultDesc;
    /** 导入校验结果 */  
    @Transient
    private String importResultMsg;
    
 // 单证承印信息
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcInsuCardImportInfo")
    private List<VcInsuranceCard> vcInsuranceCardList = new ArrayList<VcInsuranceCard>();

    /**
     * @return the idVcInsuCardImportInfo
     */
    public Long getIdVcInsuCardImportInfo() {
        return idVcInsuCardImportInfo;
    }

    /**
     * @param idVcInsuCardImportInfo the idVcInsuCardImportInfo to set
     */
    public void setIdVcInsuCardImportInfo(Long idVcInsuCardImportInfo) {
        this.idVcInsuCardImportInfo = idVcInsuCardImportInfo;
    }

    /**
     * @return the docVerCode
     */
    public String getDocVerCode() {
        return docVerCode;
    }

    /**
     * @param docVerCode the docVerCode to set
     */
    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    /**
     * @return the startCardNo
     */
    public String getStartCardNo() {
        return startCardNo;
    }

    /**
     * @param startCardNo the startCardNo to set
     */
    public void setStartCardNo(String startCardNo) {
        this.startCardNo = startCardNo;
    }

    /**
     * @return the endCardNo
     */
    public String getEndCardNo() {
        return endCardNo;
    }

    /**
     * @param endCardNo the endCardNo to set
     */
    public void setEndCardNo(String endCardNo) {
        this.endCardNo = endCardNo;
    }

    /**
     * @return the importNum
     */
    public Long getImportNum() {
        return importNum;
    }

    /**
     * @param importNum the importNum to set
     */
    public void setImportNum(Long importNum) {
        this.importNum = importNum;
    }

    /**
     * @return the importTime
     */
    public Date getImportTime() {
        return importTime;
    }

    /**
     * @param importTime the importTime to set
     */
    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    /**
     * @return the oprCode
     */
    public String getOprCode() {
        return oprCode;
    }

    /**
     * @param oprCode the oprCode to set
     */
    public void setOprCode(String oprCode) {
        this.oprCode = oprCode;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
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
     * @param dateCreated the dateCreated to set
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
     * @param updatedBy the updatedBy to set
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
     * @param dateUpdated the dateUpdated to set
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * @return the vcInsuranceCardList
     */
    public List<VcInsuranceCard> getVcInsuranceCardList() {
        return vcInsuranceCardList;
    }

    /**
     * @param vcInsuranceCardList the vcInsuranceCardList to set
     */
    public void setVcInsuranceCardList(List<VcInsuranceCard> vcInsuranceCardList) {
        this.vcInsuranceCardList = vcInsuranceCardList;
    }

  
    /**
     * @return the importResult
     */
    public boolean isImportResult() {
        return importResult;
    }

    /**
     * @param importResult the importResult to set
     */
    public void setImportResult(boolean importResult) {
        this.importResult = importResult;
    }
    

    /**
     * @return the importResultDesc
     */
    public String getImportResultDesc() {
        return importResultDesc;
    }

    /**
     * @param importResultDesc the importResultDesc to set
     */
    public void setImportResultDesc(String importResultDesc) {
        this.importResultDesc = importResultDesc;
    }

    /**
     * @return the importResultMsg
     */
    public String getImportResultMsg() {
        return importResultMsg;
    }

    /**
     * @param importResultMsg the importResultMsg to set
     */
    public void setImportResultMsg(String importResultMsg) {
        this.importResultMsg = importResultMsg;
    }

    /**
     * @return the badRecordNum
     */
    public Long getBadRecordNum() {
        return badRecordNum;
    }

    /**
     * @param badRecordNum the badRecordNum to set
     */
    public void setBadRecordNum(Long badRecordNum) {
        this.badRecordNum = badRecordNum;
    }


    
}
