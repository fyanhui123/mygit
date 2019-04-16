package com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean;

import java.util.Date;

public class DocNumSynchronizeRequestDTO {
//	/**单证库存ID*/
//	private String singleLibId;
//	/**单证类型ID*/
//	private String singleTypeId;
//	/**单证起始号码*/
//	private String singleStartNum;
//	/**单证结束号码*/
//	private String singleEndNum;
//	/**机构代码*/
//	private String deptId;
//	/**当前页*/
//	private Integer currentpage;
//	/**每页大小*/
//	private Integer pagesize;
//	/**总记录数*/
//	private Integer countRecord;
//	/**上次同步时间*/
//	private Date lastSynTime;
//	/**使用截止期*/
//	private Date deadLine;	
//	/**险种代码*/
//	private String ContPlantList;
	private  Date startDate;
	private  Date endDate;
	/**单证类型代码*/
	private String docVerCode;
	/**领用人代码*/
	private String opAndProxyId;
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getOpAndProxyId() {
		return opAndProxyId;
	}
	public void setOpAndProxyId(String opAndProxyId) {
		this.opAndProxyId = opAndProxyId;
	}
	
	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
