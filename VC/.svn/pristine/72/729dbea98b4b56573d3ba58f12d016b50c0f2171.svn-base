package com.tapi.tcs.vc.pub.web;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.platform.schema.vo.CodeLabelDto;
import com.tapi.tcs.vc.pub.service.BankManagerService;
import com.tapi.tcs.vc.schema.model.PubBanklocations;
import com.tapi.tcs.vc.schema.model.PubBanks;
import com.tapi.tcs.vc.schema.model.PubStandardareas;

/**
 * 银行信息Action
 * <p>
 * Date: 2013-08-05
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public class BankManagerAction extends TFAction {
    
    private static final long serialVersionUID = -2048307181281027587L;
    
    /**
     * 银行信息service
     */
    private BankManagerService bankManagerService;
    
    
    /** 自动完成组件返回list */
    private List<CodeLabelDto> codeLabelList;
    
   /**
    * 银行编码
    */
    private  String bankCode;
    /**
     * 区域编码
     */
   private  String areaCode;
    

    /**
     * 根据条件查询银行list
     */   
    public String queryPubBanksList() throws BusinessException{
       try {
           String codeValue = getRequest().getParameter("codeCode");
           PubBanks queryVo=new PubBanks();
           queryVo.setCodeNameLike(codeValue);
           codeLabelList = bankManagerService.queryPubBanksList(queryVo);
           return SUCCESS;
       } catch (Exception e) {
           e.printStackTrace();
           this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
           return NONE;
       }
    }
    
    /**
     * 根据条件查询区域list
     */   
    public String  queryPubStandardareasList() throws BusinessException {
       try {
           String codeValue = getRequest().getParameter("codeCode");
           PubStandardareas queryVo=new PubStandardareas();
           queryVo.setCodeNameLike(codeValue);
           codeLabelList = bankManagerService.queryPubStandardareasList(queryVo);
           return SUCCESS;
       } catch (Exception e) {
           e.printStackTrace();
           this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
           return NONE;
       }
    }

    
    /**
     * 根据条件查询银行支行list
     */   
    public String  queryPubBanklocationsList() throws BusinessException {
       try {           
           String codeValue = getRequest().getParameter("codeCode");
           String bankCode = getRequest().getParameter("bankCode");
           PubBanklocations queryVo=new PubBanklocations();
           queryVo.setCodeNameLike(codeValue);
           queryVo.setBankCode(bankCode);
           queryVo.setAreaCode(areaCode);
           codeLabelList = bankManagerService.queryPubBanklocationsList(queryVo);
           return SUCCESS;
       } catch (Exception e) {
           e.printStackTrace();
           this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
           return NONE;
       }
    }

    /**
     * @param bankManagerService the bankManagerService to set
     */
    public void setBankManagerService(BankManagerService bankManagerService) {
        this.bankManagerService = bankManagerService;
    }

    /**
     * @return the codeLabelList
     */
    public List<CodeLabelDto> getCodeLabelList() {
        return codeLabelList;
    }

    /**
     * @param codeLabelList the codeLabelList to set
     */
    public void setCodeLabelList(List<CodeLabelDto> codeLabelList) {
        this.codeLabelList = codeLabelList;
    }

    /**
     * @return the bankCode
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode the bankCode to set
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @return the areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }



   
}
