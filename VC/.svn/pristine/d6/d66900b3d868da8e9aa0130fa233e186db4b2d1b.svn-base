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
 * 激活卡销售表
 * 
 * @author whj
 * @date 2013-06-27
 * 
 */
@Entity
@Table(name = "VC_INSU_CARD_SALES_RECORD")
@SequenceGenerator(name = "SEQ_VC_INSU_CARD_SALES_RECORD", sequenceName = "SEQ_VC_INSU_CARD_SALES_RECORD", allocationSize = 1)
public class VcInsuCardSalesRecord implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4837537590090657316L;

    /** 激活卡销售表流水号 */
    @Id
    @Column(name = "ID_VC_INSU_CARD_SALES_RECORD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_INSU_CARD_SALES_RECORD")
    private Long idVcInsuCardSalesRecord;

    /** 销售记录号 */
    @Column(name = "SALES_RECORD_CODE")
    private String salesRecordCode;

    /** 销售人代码 */
    @Column(name = "SELLER_CODE")
    private String sellerCode;
    
    /** 销售人名称 */
    @Column(name = "SELLER_NAME")
    private String sellerName;

    /** 销售人员所属机构代码 */
    @Column(name = "SALE_ORG_CODE")
    private String saleOrgCode;
    
    /**激活卡销售/退卡标志（P_销售 R-退卡） */
    @Column(name = "SALE_REFUND_FLAG")
    private String saleRefundFlag;

    /** 销售人员所属中介机构代码 */
    @Column(name = "AGENCY_ORG_CODE")
    private String agencyOrgCode;

    /** 激活卡发放机构代码 */
    @Column(name = "PROVIDE_ORG_CODE")
    private String provideOrgCode;

    /** 销售日期 */
    @Column(name = "SALE_DATE")
    private Date saleDate;

    /** 销售总额 NUMBER(14,2) */
    @Column(name = "SUM_FEE")
    private double sumFee;

    /** 缴费截止日期 */
    @Column(name = "PAY_END_DATE")
    private Date payEndDate;

    /** 销售说明 */
    @Column(name = "SALE_REASON")
    private String saleReason;

    /** 修改人代码 */
    @Column(name = "MODIFY_OPR_CODE")
    private String modifyOprCode;

    /** 修改时间 */
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    /** 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败） */
    @Column(name = "SALES_RECORD_STATUS")
    private String salesRecordStatus;

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

    /** 激活卡销售明细表 */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcInsuCardSalesRecord")
    private List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList = new ArrayList<VcInsuCardSalesDetail>();
    
    //add by chy 20131025 销售人从销管接口查询 begin
    /**业务方式:1-直接业务,2-间接业务*/
    @Column(name = "BUSINESS_MODE")
    private String businessMode;
    /**渠道类型*/
    @Column(name = "CHANNEL_DETAIL_CODE")
    private String channelDetailCode;
    /**业务来源*/
    @Column(name = "BUSINESS_SOURCE")
    private String businessSource;
    /**团队代码*/
    @Column(name = "TEAM_CODE")
    private String teamCode;
    /**中介协议号*/
    @Column(name = "AGREEMENT_NO")
    private String agreementNo;
    /**渠道类型名称*/
    @Transient
    private String channelDetailName;
    /**业务来源名称*/
    @Transient
    private String businessSourceName;
    //add by chy 20131025 销售人从销管接口查询 end

    //MODIFY BY ZHXIAO3 VC-121激活卡激活卡未保存跟单业务员 begin
    //跟单业务员代码
    @Column(name = "HANDLER_CODE")
    private String handlerCode;
    //跟单业务员名称
    @Column(name = "HANDLER_NAME")
    private String handlerName;
    //MODIFY BY ZHXIAO3 VC-121激活卡激活卡未保存跟单业务员 end

	// /自定义子段------------------
    /** 销售日期查询条件 */
    @Transient
    private Date saleStartDate;
    @Transient
    private Date saleEndDate;

    /** 销售人代码 */
    /*@Transient
    private String sellerName;*/

    /** 销售人员所属机构代码 */
    @Transient
    private String saleOrgName;
    
    /** 退费银行账户信息 */
    @Transient
    private VcPayFeeAccount vcPayFeeAccount;
    
    

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
     * @return the salesRecordCode
     */
    public String getSalesRecordCode() {
        return salesRecordCode;
    }

    /**
     * @param salesRecordCode
     *            the salesRecordCode to set
     */
    public void setSalesRecordCode(String salesRecordCode) {
        this.salesRecordCode = salesRecordCode;
    }

    /**
     * @return the sellerCode
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * @param sellerCode
     *            the sellerCode to set
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * @return the saleOrgCode
     */
    public String getSaleOrgCode() {
        return saleOrgCode;
    }

    /**
     * @param saleOrgCode
     *            the saleOrgCode to set
     */
    public void setSaleOrgCode(String saleOrgCode) {
        this.saleOrgCode = saleOrgCode;
    }

    
    /**
     * @return the saleRefundFlag
     */
    public String getSaleRefundFlag() {
        return saleRefundFlag;
    }

    /**
     * @param saleRefundFlag the saleRefundFlag to set
     */
    public void setSaleRefundFlag(String saleRefundFlag) {
        this.saleRefundFlag = saleRefundFlag;
    }

    /**
     * @return the agencyOrgCode
     */
    public String getAgencyOrgCode() {
        return agencyOrgCode;
    }

    /**
     * @param agencyOrgCode
     *            the agencyOrgCode to set
     */
    public void setAgencyOrgCode(String agencyOrgCode) {
        this.agencyOrgCode = agencyOrgCode;
    }

    /**
     * @return the provideOrgCode
     */
    public String getProvideOrgCode() {
        return provideOrgCode;
    }

    /**
     * @param provideOrgCode
     *            the provideOrgCode to set
     */
    public void setProvideOrgCode(String provideOrgCode) {
        this.provideOrgCode = provideOrgCode;
    }

    /**
     * @return the saleDate
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * @param saleDate
     *            the saleDate to set
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * @return the sumFee
     */
    public double getSumFee() {
        return sumFee;
    }

    /**
     * @param sumFee
     *            the sumFee to set
     */
    public void setSumFee(double sumFee) {
        this.sumFee = sumFee;
    }

    /**
     * @return the payEndDate
     */
    public Date getPayEndDate() {
        return payEndDate;
    }

    /**
     * @param payEndDate
     *            the payEndDate to set
     */
    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    /**
     * @return the saleReason
     */
    public String getSaleReason() {
        return saleReason;
    }

    /**
     * @param saleReason
     *            the saleReason to set
     */
    public void setSaleReason(String saleReason) {
        this.saleReason = saleReason;
    }

    /**
     * @return the modifyOprCode
     */
    public String getModifyOprCode() {
        return modifyOprCode;
    }

    /**
     * @param modifyOprCode
     *            the modifyOprCode to set
     */
    public void setModifyOprCode(String modifyOprCode) {
        this.modifyOprCode = modifyOprCode;
    }

    /**
     * @return the modifyTime
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     *            the modifyTime to set
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return the salesRecordStatus
     */
    public String getSalesRecordStatus() {
        return salesRecordStatus;
    }

    /**
     * @param salesRecordStatus
     *            the salesRecordStatus to set
     */
    public void setSalesRecordStatus(String salesRecordStatus) {
        this.salesRecordStatus = salesRecordStatus;
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

    /**
     * @return the vcInsuCardSalesDetailList
     */
    public List<VcInsuCardSalesDetail> getVcInsuCardSalesDetailList() {
        return vcInsuCardSalesDetailList;
    }

    /**
     * @param vcInsuCardSalesDetailList
     *            the vcInsuCardSalesDetailList to set
     */
    public void setVcInsuCardSalesDetailList(List<VcInsuCardSalesDetail> vcInsuCardSalesDetailList) {
        this.vcInsuCardSalesDetailList = vcInsuCardSalesDetailList;
    }

    /**
     * @return the saleStartDate
     */
    public Date getSaleStartDate() {
        return saleStartDate;
    }

    /**
     * @param saleStartDate
     *            the saleStartDate to set
     */
    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    /**
     * @return the saleEndDate
     */
    public Date getSaleEndDate() {
        return saleEndDate;
    }

    /**
     * @param saleEndDate
     *            the saleEndDate to set
     */
    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    /**
     * @return the sellerName
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * @param sellerName
     *            the sellerName to set
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * @return the saleOrgName
     */
    public String getSaleOrgName() {
        return saleOrgName;
    }

    /**
     * @param saleOrgName
     *            the saleOrgName to set
     */
    public void setSaleOrgName(String saleOrgName) {
        this.saleOrgName = saleOrgName;
    }

    /**
     * @return the vcPayFeeAccount
     */
    public VcPayFeeAccount getVcPayFeeAccount() {
        return vcPayFeeAccount;
    }

    /**
     * @param vcPayFeeAccount the vcPayFeeAccount to set
     */
    public void setVcPayFeeAccount(VcPayFeeAccount vcPayFeeAccount) {
        this.vcPayFeeAccount = vcPayFeeAccount;
    }

	public String getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(String businessMode) {
		this.businessMode = businessMode;
	}

	public String getChannelDetailCode() {
		return channelDetailCode;
	}

	public void setChannelDetailCode(String channelDetailCode) {
		this.channelDetailCode = channelDetailCode;
	}

	public String getBusinessSource() {
		return businessSource;
	}

	public void setBusinessSource(String businessSource) {
		this.businessSource = businessSource;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getChannelDetailName() {
		return channelDetailName;
	}

	public void setChannelDetailName(String channelDetailName) {
		this.channelDetailName = channelDetailName;
	}

	public String getBusinessSourceName() {
		return businessSourceName;
	}

	public void setBusinessSourceName(String businessSourceName) {
		this.businessSourceName = businessSourceName;
	}

	public String getHandlerCode() {
		return handlerCode;
	}

	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
    
	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
}
