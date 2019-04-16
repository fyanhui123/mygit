package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean;

import java.util.Date;

public class SingleLibEntity {

	/**单证库存ID*/
	private String singleLibId;
	/**单证类型ID*/
	private String singleTypeId;
	/**单证起始号码*/
	private String singleStartNum;
	/**单证结束号码*/
	private String singleEndNum;
	/**机构代码*/
	private String deptId;
	/**领用人代码*/
	private String opAndProxyId;
	/**当前页*/
	private Integer currentpage;
	/**每页大小*/
	private Integer pagesize;
	/**总记录数*/
	private Integer countRecord;
	/**上次同步时间*/
	private Date lastSynTime;
	
	/**单证类型代码*/
	private String docVerCode;
	/**使用截止期*/
	private Date deadLine;
	
	/**险种代码*/
	private String ContPlantList;
	
	public String getSingleLibId() {
		return singleLibId;
	}
	public void setSingleLibId(String singleLibId) {
		this.singleLibId = singleLibId;
	}
	public String getSingleTypeId() {
		return singleTypeId;
	}
	public void setSingleTypeId(String singleTypeId) {
		this.singleTypeId = singleTypeId;
	}
	public String getSingleStartNum() {
		return singleStartNum;
	}
	public void setSingleStartNum(String singleStartNum) {
		this.singleStartNum = singleStartNum;
	}
	public String getSingleEndNum() {
		return singleEndNum;
	}
	public void setSingleEndNum(String singleEndNum) {
		this.singleEndNum = singleEndNum;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getOpAndProxyId() {
		return opAndProxyId;
	}
	public void setOpAndProxyId(String opAndProxyId) {
		this.opAndProxyId = opAndProxyId;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getCountRecord() {
		return countRecord;
	}
	public void setCountRecord(Integer countRecord) {
		this.countRecord = countRecord;
	}
	public Date getLastSynTime() {
		return lastSynTime;
	}
	public void setLastSynTime(Date lastSynTime) {
		this.lastSynTime = lastSynTime;
	}
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	public String getContPlantList() {
		return ContPlantList;
	}
	public void setContPlantList(String contPlantList) {
		ContPlantList = contPlantList;
	}
	
}
