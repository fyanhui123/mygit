package com.tapi.tcs.vc.newInvoice.web;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.tf.common.vo.Message;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.newInvoice.service.QueryInvoiceService;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcLost;
import com.tapi.tcs.vc.schema.model.VcLostDet;
import com.tapi.tcs.vc.visausage.service.LostApplyService;

@SuppressWarnings("serial")
public class QueryInvoiceInfoAction extends TFAction{
	    private LostApplyService lostApplyService;
	    /** 遗失申请基本信息List */
        private List<VcLost> vcLosts;
	    /** 遗失单号 */
        private String lostCode;
	    /** 操作结果 */
        private String result;
	    /** 申请单状态 0删除/1新建/2待审批/3审批退回/4审批通过 */
        private String lostStatus;
	    private Date applyStartDate;
	    /** 申请结束时间 */
	    private Date applyEndDate;
	    /** 上传的文件 */
	    private File file;
	    /** 文件类型 */
	    private String fileContentType;
	    /** 文件名称 */
	    private String fileFileName;
	    /** 遗失申请id */
	    private String id;
	    private String actionType;
	    /**遗失日期*/
	    private Date lostDate;
	    /**登报日期*/
	    private Date reportLostDate;
	    /** 遗失申请基本信息 */
	    private VcLost vcLost;
	    /** 遗失详细基本信息List */
	    private List<VcLostDet> vcLostDet;
	    private String jsonStr;
	    /** 审批List */
	    private List<VcApprove> vcApprove;
	    private QueryInvoiceService queryInvoiceService;
	    /** 操作返回信息 */
	    private String resultMsg;
	    /**
	     * 是否发生错误
	     */
	    private boolean hasError;
	    
	    
     public String initLostApproveQuery() {
	        applyEndDate = new Date();
	        applyStartDate = DateUtils.addMonth(applyEndDate, -2);
	        return SUCCESS;
	    }
     /**
      * 审批
      * 
      * @return
      */
     public String executeApprove() throws BusinessException {
     	try {
 	        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
 	        String userCode = userInfo.getUserCode();
 	        String orgCode = userInfo.getComCode();
 	        VcApprove vcApprove = new VcApprove();
 	        vcApprove.setCheckOprId(userCode);
 	        vcApprove.setCheckOrgId(orgCode);
 	        String approveOpp = getRequest().getParameter("approveOpp");
 	        vcApprove.setCheckExpl(approveOpp);
             result = queryInvoiceService.executeApprove(vcApprove, id, actionType);
     	}catch(BusinessException e){
 			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
 			return NONE;
 		}catch(Exception e){
 			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
 			return NONE;
 		}
         return SUCCESS;
     }   
	public  String invoiceLoser()throws Exception{
		 logger.info("访问/newinvoice/invoiceLoser.do 发票遗失...");
		 Date nowDate = new Date();
	        applyStartDate = DateUtils.addMonth(nowDate, -2);
	        applyEndDate = nowDate;
	        return SUCCESS;
	}
	public  String invoiceAddLostApply()throws Exception{
		 logger.info("访问/newinvoice/invoiceAddLostApply.do 发票遗失新增...");
		 UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	        String comName = userInfo.getComName();
	        String userName = userInfo.getUserName();
	        Date nowDate = new Date();
	        vcLost = new VcLost();
	        vcLost.setLostOprName(userName);
	        vcLost.setLostOrgName(comName);
	        vcLost.setLostTime(nowDate);
	        actionType = "add";
	        return SUCCESS;
	}
	//提交遗失申请
	 public String submitLostApply() throws BusinessException {
	    	try {
		        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		        String userCode = userInfo.getUserCode();
		        String orgCode = userInfo.getComCode();
	            result = lostApplyService.executeSubmitLostApply(id, userCode, orgCode);
	    	}catch(BusinessException e){
				this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
				return NONE;
			}catch(Exception e){
				this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
				return NONE;
			}
	        return SUCCESS;
	    }
	
	//遗失申请查询
	public String queryLostApply() throws BusinessException{
		logger.info("访问/newinvoiceJson/queryLostApply.action?ajax=true分页查询遗失申请信息...");
		 try{
		        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		        String orgCode = userInfo.getComCode();
		        VcLost vcLost = new VcLost();
		        vcLost.setLostCode(lostCode);
		        vcLost.setLostStatus(lostStatus);
		        vcLost.setApplyStartDate(applyStartDate);
		        vcLost.setApplyEndDate(applyEndDate);
		        vcLost.setLostOrgCode(orgCode);
		        vcLost.setInvoiceFlag("1");
		        Page resultPage = lostApplyService.queryLostApply(vcLost, page, rows);
		
		        vcLosts = (List<VcLost>) resultPage.getResult();
		        // 总页数
		        total = (int) resultPage.getTotalPageCount();
		        // 总记录数
		        records = (int) resultPage.getTotalCount();
	        }catch(BusinessException e){
				this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
				return NONE;
			}catch(Exception e){
				this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
				return NONE;
			}
	        return SUCCESS;
	}
	public String saveInvoiceLostApply()throws Exception{
		    Message message = new Message();
	        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	        // 申请人
	        String userCode = userInfo.getUserCode();
	        String orgCode = userInfo.getComCode();
	        try {
	        	 if (file != null) {
	                 if (!fileContentType.equals("image/x-png") && !fileContentType.equals("image/pjpeg")
	                         && !fileContentType.equals("image/jpeg") && !fileContentType.equals("image/png")) {
	                     throw new BusinessException("请上传jpg或png格式的图片");
	                 }

	                 if (file.length() > SysConst.MB * 2) {
	                     throw new BusinessException("文件不可超过2M");
	                 }
	             }
	        	 String realPath = SysConst.ZPLOST_FILE_SAVE_PATH;
	        	 String lostReason = getRequest().getParameter("lostReason");
	             String jsonStr = getRequest().getParameter("jsonStr");
	             File savefile = null;
	             vcLost = new VcLost();
	             if (file != null) {
	                 savefile = new File(new File(realPath), fileFileName);
	             }
	             vcLostDet = TFJSON.parseArray(jsonStr, VcLostDet.class);
	             if ("modify".equals(actionType)) {
	                 vcLost.setId(Long.valueOf(id));
	             } else if ("add".equals(actionType)) {
	                 if (file == null) {
	                     throw new BusinessException("文件不能为空");
	                 }
	                 if (savefile.exists()) {
	                     throw new BusinessException("文件已存在");
	                 }
	             }
	             vcLost.setLostOprCode(userCode);
	             // 遗失提交机构代码
	             vcLost.setLostOrgCode(orgCode);
	             // 遗失原因
	             vcLost.setLostReason(lostReason);
	             //add by chy 增加遗失日期、登报日期
	             vcLost.setLostDate(lostDate);
	             vcLost.setReportLostDate(reportLostDate);
	             boolean flag=queryInvoiceService.queryInvoice(userCode);
	             if(flag){
	             	vcLost.setInvoiceFlag("1");
	             }
	             if (file != null) {
	                 // 文件名
	                 vcLost.setFileName(fileFileName);
	                 // 文件路径
	                 vcLost.setFilePath(realPath);
	             }
	             result = queryInvoiceService.saveLostApply(vcLost, vcLostDet, lostStatus, actionType, file);
	             if (!"操作成功".equals(result)) {
	                 throw new BusinessException(result);
	             }
	             // 保存文件
	             if (file != null) {
	                 if (!savefile.getParentFile().exists())
	                     savefile.getParentFile().mkdirs();
	                 FileUtils.copyFile(file, savefile);
	             }
	             message.setType("success");
	             message.setMessage("保存成功!");
	        } catch (Exception e) {
			    message.setType("error");
	            message.setMessage(e.getMessage());
		}
	             if ("success".equals(message.getType())) {
	                 applyEndDate = new Date();
	                 applyStartDate = DateUtils.addMonth(applyEndDate, -2);
	                 this.actionType = "SaveAfter";
	                 this.resultMsg = message.getMessage();
	                 return SUCCESS;
	             } else {
	                 String comName = userInfo.getComName();
	                 String userName = userInfo.getUserName();
	                 vcLost = new VcLost();
	                 vcLost.setLostOprName(userName);
	                 vcLost.setLostOrgName(comName);
	                 vcLost.setLostTime(new Date());
	                 actionType = "add";
	                 this.hasError = true;
	                 this.resultMsg = message.getMessage();
	                 return "fail";
	             }
	}	
	 /**
     * 遗失申请查看
     * 
     * @return
     * @throws Exception
     */
    public String viewLostApply() throws BusinessException {
        try{
	    	List result = lostApplyService.viewLostApply(id);
	        vcLost = (VcLost) result.get(0);
	        vcLostDet = (List<VcLostDet>) result.get(1);
	        vcApprove = (List<VcApprove>) result.get(2);
	
	        // 修改时初始化多行输入域
	        if ("modify".equals(actionType)) {
	            for (Iterator iterator = vcLostDet.iterator(); iterator.hasNext();) {
	                VcLostDet vcLostDet = (VcLostDet) iterator.next();
	                vcLostDet.setVcLost(null);
	            }
	            jsonStr = TFJSON.toJSONString(vcLostDet);
	        }
        }catch(BusinessException e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
        return actionType;
    }

	 /**
     * 遗失审批查询
     * 
     * @return
     * @throws Exception
     */
    public String queryLostApprove() throws BusinessException {
    	try{
	        UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
	        String orgCode = userInfo.getComCode();
	
	        VcLost vcLost = new VcLost();
	        vcLost.setLostCode(lostCode);
	        vcLost.setLostStatus(lostStatus);
	        vcLost.setApplyStartDate(applyStartDate);
	        vcLost.setApplyEndDate(applyEndDate);
	        vcLost.setApproveOrgCode(orgCode);
	        vcLost.setInvoiceFlag("1");
	        Page resultPage = lostApplyService.queryLostApprove(vcLost, page, rows);
	
	        vcLosts = (List<VcLost>) resultPage.getResult();
	        // 总页数
	        total = (int) resultPage.getTotalPageCount();
	        // 总记录数
	        records = (int) resultPage.getTotalCount();
    	}catch(BusinessException e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
        return SUCCESS;
    }
    

	public LostApplyService getLostApplyService() {
		return lostApplyService;
	}
	public void setLostApplyService(LostApplyService lostApplyService) {
		this.lostApplyService = lostApplyService;
	}
	public List<VcLost> getVcLosts() {
		return vcLosts;
	}
	public void setVcLosts(List<VcLost> vcLosts) {
		this.vcLosts = vcLosts;
	}
	public String getLostCode() {
		return lostCode;
	}
	public void setLostCode(String lostCode) {
		this.lostCode = lostCode;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getLostStatus() {
		return lostStatus;
	}
	public void setLostStatus(String lostStatus) {
		this.lostStatus = lostStatus;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public Date getLostDate() {
		return lostDate;
	}
	public void setLostDate(Date lostDate) {
		this.lostDate = lostDate;
	}
	public Date getReportLostDate() {
		return reportLostDate;
	}
	public void setReportLostDate(Date reportLostDate) {
		this.reportLostDate = reportLostDate;
	}
	public VcLost getVcLost() {
		return vcLost;
	}
	public void setVcLost(VcLost vcLost) {
		this.vcLost = vcLost;
	}
	public List<VcLostDet> getVcLostDet() {
		return vcLostDet;
	}
	public void setVcLostDet(List<VcLostDet> vcLostDet) {
		this.vcLostDet = vcLostDet;
	}
	public QueryInvoiceService getQueryInvoiceService() {
		return queryInvoiceService;
	}
	public void setQueryInvoiceService(QueryInvoiceService queryInvoiceService) {
		this.queryInvoiceService = queryInvoiceService;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public List<VcApprove> getVcApprove() {
		return vcApprove;
	}

	public void setVcApprove(List<VcApprove> vcApprove) {
		this.vcApprove = vcApprove;
	}
	
}
