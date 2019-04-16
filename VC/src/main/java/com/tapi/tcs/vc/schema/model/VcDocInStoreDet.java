package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 表 VC_DOC_IN_STORE 对应实体类
 * 
 * @author Leo
 * 
 */
@Entity
@Table(name = "VC_DOC_IN_STORE_DET")
@SequenceGenerator(name = "SEQ_VC_DOC_IN_STORE_DET", sequenceName = "SEQ_VC_DOC_IN_STORE_DET", allocationSize = 1)
public class VcDocInStoreDet implements Serializable {

	private static final long serialVersionUID = 1461092026715031998L;

	/** 单证印刷入库表流水 */
	private Long id;
	/** 印刷入库申请单号 */
	private VcDocInStore vcDocInStore;
	/** 单证版本代码 */
	private String docVerCode;
	/** 印刷批次 */
	private String pressBatchNo;
	/** 起始流水号 */
	private String startNum;
	/** 终止流水号 */
	private String endNum;
	/** 入库数量 */
	private Long totalAmount;
	/** 印刷厂代码 */
	private String printeryCode;
	/** 印刷单价 */
	private BigDecimal unitPrice;
	/** 印刷费用 */
	private BigDecimal printingFee;
	/** 创建人 */
	private String createOprCode;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private String modifyOprCode;
	/** 修改时间 */
	private Date modifyTime;

	
	// 自定义属性  //
	/** 单证版本代码 */
	private String docVerName;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_IN_STORE_DET")
	@Column(name = "ID_VC_DOC_IN_STORE_DET")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_VC_DOC_IN_STORE")
	public VcDocInStore getVcDocInStore() {
		return vcDocInStore;
	}

	public void setVcDocInStore(VcDocInStore vcDocInStore) {
		this.vcDocInStore = vcDocInStore;
	}

	@Column(name = "DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	@Column(name = "PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}

	@Column(name = "START_NUM")
	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	@Column(name = "END_NUM")
	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

	@Column(name = "TOTAL_AMOUNT")
	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "PRINTERY_CODE")
	public String getPrinteryCode() {
		return printeryCode;
	}

	public void setPrinteryCode(String printeryCode) {
		this.printeryCode = printeryCode;
	}

	@Column(name = "UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "PRINTING_FEE")
	public BigDecimal getPrintingFee() {
		return printingFee;
	}

	public void setPrintingFee(BigDecimal printingFee) {
		this.printingFee = printingFee;
	}

	@Column(name = "CREATED_BY")
	public String getCreateOprCode() {
		return createOprCode;
	}

	public void setCreateOprCode(String createOprCode) {
		this.createOprCode = createOprCode;
	}

	@Column(name = "DATE_CREATED")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATED_BY")
	public String getModifyOprCode() {
		return modifyOprCode;
	}

	public void setModifyOprCode(String modifyOprCode) {
		this.modifyOprCode = modifyOprCode;
	}

	@Column(name = "DATE_UPDATED")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Transient
	public String getDocVerName() {
		return docVerName;
	}

	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docVerCode == null) ? 0 : docVerCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VcDocInStoreDet other = (VcDocInStoreDet) obj;
		if (docVerCode == null) {
			if (other.docVerCode != null)
				return false;
		} else if (!docVerCode.equals(other.docVerCode))
			return false;
		return true;
	}
}
