package com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 险种条款信息
 */
public class PrpdRiskClause {
	/**
	 * 条款代码
	 */
	private String clauseCode;
	/**
	 * 条款中文简称 
	 */
	private String clauseNameSimple;
	/**
	 * 条款中文全称
	 */
	private String clauseName;
	/**
	 * 条款英文简称
	 */
	private String clauseENameSimple;
	/**
	 * 条款英文全称 
	 */
	private String clauseEName;
	/**
	 * 条款繁体简称
	 */
	private String clauseTNameSimple;
	/**
	 * 条款繁体全称
	 */
	private String clauseTName;
	/**
	 * 险类代码
	 */
	private String classCode;
	/**
	 * 险类名称
	 */
	private String className;
	/**
	 * 报备/报批号
	 */
	private String reportno;
	/**
	 * 语言（C:中文、E:英文）
	 */
	private String language;
	/**
	 * 条款属性（'1':'主险'、'2':'附加险'） 默认为“主险”
	 */
	private String clauseAttribute;
	/**
	 * 涉农标志：（'1':'涉农'、'2':'非涉农'、'3':'兼有'）默认为"兼有"
	 */
	private String agricultureFlag;
	/**
	 * 归属机构名称及代码
	 */
	private List<String> institution;
	/**
	 * 适用层级
	 */
	private List<String> areaLevel;
	/**
	 * 条款中文内容
	 */
	private String clauseChContent;
	/**
	 * 条款英文内容
	 */
	private String clauseEnContent;
	/**
	 * 中文文档URL
	 */
	private String clauseCnFile;
	/**
	 * 英文文档URL
	 */
	private String clauseEnFile;
	/**
	 * 条款中文文件地址
	 */
	private String clauseFileCNUrl;
	/**
	 * 条款英文文件地址
	 */
	private String clauseFileENUrl;
	/**
	 * 状态
	 */
	private String state;	
	/**
	 * 备注
	 */
	private String remark;
	public String getClauseCode() {
		return clauseCode;
	}
	@XmlElement(name = "clauseCode", required = false)
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	public String getClauseNameSimple() {
		return clauseNameSimple;
	}
	@XmlElement(name = "clauseNameSimple", required = false)
	public void setClauseNameSimple(String clauseNameSimple) {
		this.clauseNameSimple = clauseNameSimple;
	}
	public String getClauseName() {
		return clauseName;
	}
	@XmlElement(name = "clauseName", required = false)
	public void setClauseName(String clauseName) {
		this.clauseName = clauseName;
	}
	public String getClauseENameSimple() {
		return clauseENameSimple;
	}
	@XmlElement(name = "clauseENameSimple", required = false)
	public void setClauseENameSimple(String clauseENameSimple) {
		this.clauseENameSimple = clauseENameSimple;
	}
	public String getClauseEName() {
		return clauseEName;
	}
	@XmlElement(name = "clauseEName", required = false)
	public void setClauseEName(String clauseEName) {
		this.clauseEName = clauseEName;
	}
	public String getClauseTNameSimple() {
		return clauseTNameSimple;
	}
	@XmlElement(name = "clauseTNameSimple", required = false)
	public void setClauseTNameSimple(String clauseTNameSimple) {
		this.clauseTNameSimple = clauseTNameSimple;
	}
	public String getClauseTName() {
		return clauseTName;
	}
	@XmlElement(name = "clauseTName", required = false)
	public void setClauseTName(String clauseTName) {
		this.clauseTName = clauseTName;
	}
	public String getClassCode() {
		return classCode;
	}
	@XmlElement(name = "clauseTName", required = false)
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	@XmlElement(name = "className", required = false)
	public void setClassName(String className) {
		this.className = className;
	}
	public String getReportno() {
		return reportno;
	}
	@XmlElement(name = "reportno", required = false)
	public void setReportno(String reportno) {
		this.reportno = reportno;
	}
	public String getLanguage() {
		return language;
	}
	@XmlElement(name = "language", required = false)
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getClauseAttribute() {
		return clauseAttribute;
	}
	@XmlElement(name = "clauseAttribute", required = false)
	public void setClauseAttribute(String clauseAttribute) {
		this.clauseAttribute = clauseAttribute;
	}
	public String getAgricultureFlag() {
		return agricultureFlag;
	}
	@XmlElement(name = "agricultureFlag", required = false)
	public void setAgricultureFlag(String agricultureFlag) {
		this.agricultureFlag = agricultureFlag;
	}
	public List<String> getInstitution() {
		return institution;
	}
	@XmlElementWrapper (name = "institution")
	public void setInstitution(List<String> institution) {
		this.institution = institution;
	}
	public List<String> getAreaLevel() {
		return areaLevel;
	}
	@XmlElementWrapper (name = "areaLevel")
	public void setAreaLevel(List<String> areaLevel) {
		this.areaLevel = areaLevel;
	}
	public String getClauseChContent() {
		return clauseChContent;
	}
	@XmlElement(name = "clauseChContent", required = false)
	public void setClauseChContent(String clauseChContent) {
		this.clauseChContent = clauseChContent;
	}
	public String getClauseEnContent() {
		return clauseEnContent;
	}
	@XmlElement(name = "clauseEnContent", required = false)
	public void setClauseEnContent(String clauseEnContent) {
		this.clauseEnContent = clauseEnContent;
	}
	public String getClauseCnFile() {
		return clauseCnFile;
	}
	@XmlElement(name = "clauseCnFile", required = false)
	public void setClauseCnFile(String clauseCnFile) {
		this.clauseCnFile = clauseCnFile;
	}
	public String getClauseEnFile() {
		return clauseEnFile;
	}
	@XmlElement(name = "clauseEnFile", required = false)
	public void setClauseEnFile(String clauseEnFile) {
		this.clauseEnFile = clauseEnFile;
	}
	public String getClauseFileCNUrl() {
		return clauseFileCNUrl;
	}
	@XmlElement(name = "clauseFileCNUrl", required = false)
	public void setClauseFileCNUrl(String clauseFileCNUrl) {
		this.clauseFileCNUrl = clauseFileCNUrl;
	}
	public String getClauseFileENUrl() {
		return clauseFileENUrl;
	}
	@XmlElement(name = "clauseFileENUrl", required = false)
	public void setClauseFileENUrl(String clauseFileENUrl) {
		this.clauseFileENUrl = clauseFileENUrl;
	}
	public String getState() {
		return state;
	}
	@XmlElement(name = "state", required = false)
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	@XmlElement(name = "remark", required = false)
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
