/**
 * 
 */
package com.tapi.tcs.vc.store.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 定义查询条件
 * @author hanmiao.diao
 *
 */
public class QueryCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4274957967849161706L;

	private String queryCode;
	private String startDate;
	private String endDate;
	private String queryStatus;
	private String docVerCode;
	private String validOrg;
	private String takerCode;
	private String validQueryType;
	private String validDocStatus;//类型
	private String validStatus;//单证查询状态(遗失的时候用)

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getQueryCode() {
		return queryCode;
	}
	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}
	public String getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getValidOrg() {
		return validOrg;
	}
	public void setValidOrg(String validOrg) {
		this.validOrg = validOrg;
	}
	public String getTakerCode() {
		return takerCode;
	}
	public void setTakerCode(String takerCode) {
		this.takerCode = takerCode;
	}
    public String getValidQueryType() {
        return validQueryType;
    }
    public void setValidQueryType(String validQueryType) {
        this.validQueryType = validQueryType;
    }
    public String getValidDocStatus() {
        return validDocStatus;
    }
    public void setValidDocStatus(String validDocStatus) {
        this.validDocStatus = validDocStatus;
    }
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
}
