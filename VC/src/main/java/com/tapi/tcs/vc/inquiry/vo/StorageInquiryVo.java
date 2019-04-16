package com.tapi.tcs.vc.inquiry.vo;

import java.util.Date;
/**
 * 单证库存查询统计VO
 * <p>
 * Date 2013-04-16
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
 * @author zhxiao
 * @version 1.0
 */
public class StorageInquiryVo {
	
	/**单证库存表流水 */
	private Long idVcStorage;
	/**可使用单证表流水 */
	private Long idVcAvailable;
	
	/**单证种类 代码*/
	private String docTypeCode;
	/**单证险类代码 */
	private String insuTypeCode;
	/**单证归属险种代码 */
	private String insuKindCode;
	
	/**单证类型代码 */
	private String docVerCode;
	/**单证类型名称 */
	private String docVerName;
	/**起始流水号 */
	private String startNum;
	/**终止流水号 */
	private String endNum;
	/**单证数量 */
	private Long docNum;
	/**单证状态 */
	private String docStatus;
	/**机构代码 */
	private String orgCode;
	/**使用人代码 */
	private String takerCode;
	/**单证持有人类型 */
	private String takerDocType;
	/**单证持有人代码 */
	private String docTakerCode;
	/**单证持有人名称 */
	private String docTakerName;
	/**是否查询下级机构 */
	private String isContainSubOrg;
	/**入库时间 */
	private Date inStoreTime;
	/**发放时间 */
	private Date provideTime;
	/**印刷批次 */
	private String pressBatchNo;
	
	/**申请时间 */
	private Date applyStartDate;
	private Date applyEndDate;
	
	/**使用截止期 */
	private Date deadLine;
	
	
	public void setIdVcStorage(Long idVcStorage) {
		this.idVcStorage = idVcStorage;
	}
	public Long getIdVcStorage() {
		return idVcStorage;
	}

	public Long getIdVcAvailable() {
		return idVcAvailable;
	}
	public void setIdVcAvailable(Long idVcAvailable) {
		this.idVcAvailable = idVcAvailable;
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getTakerCode() {
		return takerCode;
	}
	public void setTakerCode(String takerCode) {
		this.takerCode = takerCode;
	}


	public String getDocTakerCode() {
		return docTakerCode;
	}
	public void setDocTakerCode(String docTakerCode) {
		this.docTakerCode = docTakerCode;
	}
	public String getDocTakerName() {
		return docTakerName;
	}
	public void setDocTakerName(String docTakerName) {
		this.docTakerName = docTakerName;
	}
	public String getIsContainSubOrg() {
		return isContainSubOrg;
	}
	public void setIsContainSubOrg(String isContainSubOrg) {
		this.isContainSubOrg = isContainSubOrg;
	}
	public Date getInStoreTime() {
		return inStoreTime;
	}
	public void setInStoreTime(Date inStoreTime) {
		this.inStoreTime = inStoreTime;
	}
	public Date getProvideTime() {
		return provideTime;
	}
	public void setProvideTime(Date provideTime) {
		this.provideTime = provideTime;
	}
	public String getPressBatchNo() {
		return pressBatchNo;
	}
	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
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
	public void setTakerDocType(String takerDocType) {
		this.takerDocType = takerDocType;
	}
	public String getTakerDocType() {
		return takerDocType;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

}
