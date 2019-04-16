package com.tapi.tcs.vc.account.web;

import java.util.Date;

import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.account.service.DocNumAccountService;

public class DocNumAccountAction extends TFAction {
	private static final long serialVersionUID = -2201935600076140624L;
	//文件导入日期
	private Date accountDate;
	private DocNumAccountService docNumAccountService;

	/**
     * 初始化进入外围银保通对账页面
     * 
     * @author zhxiao3
     * @date 2015-02-07
     * 
     */
    public String initBisAccount() {
        return SUCCESS;
    }
    
    
	public String executeImportBisAcctFilEmanual() throws Exception {
		try {
			
			docNumAccountService.executeImportBisFileByDate(accountDate);
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("导入异常！"));
            return NONE;
        }
		
		return SUCCESS;
	}

	public String executeBisAcctEmanual() throws Exception {
		try {
			
			docNumAccountService.executeBisAcct();
        } catch (Exception e) {
            e.printStackTrace();
            this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("对账异常！"));
            return NONE;
        }
		
		return SUCCESS;
	}
	
	public Date getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}
	
	public void setDocNumAccountService(DocNumAccountService docNumAccountService) {
		this.docNumAccountService = docNumAccountService;
	}
}
