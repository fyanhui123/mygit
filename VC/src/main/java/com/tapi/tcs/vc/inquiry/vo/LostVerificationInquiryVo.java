package com.tapi.tcs.vc.inquiry.vo;

import java.util.Date;

/**
 * 遗失核销查询统计VO
 * <p>
 * Date 2013-04-25
 * </p>
 * <p>
 * Module：遗失核销查询统计VO
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 */

public class LostVerificationInquiryVo {

	/** 单证印刷入库表流水 */
	private Long id;
	/** 单证种类代码 */
	private String docTypeCode;
	/** 单证种类名称 */
	private String docTypeName;
	/** 单证大类 发票/单证 */
	private String docType;
	/** 险类 */
	private String insuType;
	/** 险种 */
	private String insuKind;
	/** 单证版本代码 */
	private String docVerName;
	/** 单证版本代码 */
	private String docVerCode;
	/** 多个单证版本代码组成的字符串，以逗号"，"分割 */
    private String strDocVerCodes;
	/** 入库申请单状态（0删除/1新建/2等待审批/3审批通过/4审批退回） */
	private String lostStatus;
	private String lostOprCode;
	private String lostOrgCode;
	/** 申请时间 */
	private Date applyStartDate;
	private Date applyEndDate;
	/** 遗失时间 */
	private Date lostTime;
	/** 遗失单号 */
	private String lostCode;
	/** 印刷批次 */
	private String pressBatchNo;
	/** 起始流水号 */
	private String startNum;
	/** 终止流水号 */
	private String endNum;
	/** 遗失数量 */
	private Long lostNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getDocTypeName() {
		return docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public String getInsuType() {
		return insuType;
	}

	public void setInsuType(String insuType) {
		this.insuType = insuType;
	}

	public String getInsuKind() {
		return insuKind;
	}

	public void setInsuKind(String insuKind) {
		this.insuKind = insuKind;
	}

	public String getDocVerName() {
		return docVerName;
	}

	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}

	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	public String getLostStatus() {
		return lostStatus;
	}

	public void setLostStatus(String lostStatus) {
		this.lostStatus = lostStatus;
	}

	public String getLostOprCode() {
		return lostOprCode;
	}

	public void setLostOprCode(String lostOprCode) {
		this.lostOprCode = lostOprCode;
	}

	public String getLostOrgCode() {
		return lostOrgCode;
	}

	public void setLostOrgCode(String lostOrgCode) {
		this.lostOrgCode = lostOrgCode;
	}

	public Date getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(Date applyStartDate) {
		this.applyStartDate = applyStartDate;
	}

	public Date getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	public Date getLostTime() {
		return lostTime;
	}

	public void setLostTime(Date lostTime) {
		this.lostTime = lostTime;
	}

	public String getLostCode() {
		return lostCode;
	}

	public void setLostCode(String lostCode) {
		this.lostCode = lostCode;
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

	public Long getLostNum() {
		return lostNum;
	}

	public void setLostNum(Long lostNum) {
		this.lostNum = lostNum;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

    /**
     * @return the strDocVerCodes
     */
    public String getStrDocVerCodes() {
        return strDocVerCodes;
    }

    /**
     * @param strDocVerCodes the strDocVerCodes to set
     */
    public void setStrDocVerCodes(String strDocVerCodes) {
        this.strDocVerCodes = strDocVerCodes;
    }

   
	
	
}
