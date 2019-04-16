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
import com.tapi.tcs.vc.newInvoice.service.NewDestroyService;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;
import com.tapi.tcs.vc.visausage.service.DestroyService;
public class NewDestroyAction extends TFAction{
	private static final long serialVersionUID = 1L;
	/** 申请开始日期 */
	private Date applyStartDate;
	/** 申请终止日期 */
	private Date applyEndDate;
	/** vcDestroy id */
	private String id;
	/** 操作类型 add modify... */
	private String actionType;
	/** vcDestroy */
	private VcDestroy vcDestroy;
	/** vcDestroyDet json数据 */
	private String jsonStr;
	
	/** 上传的文件 */
	private File file;
	/** 文件名称 */
	private String fileFileName;
	/** 文件类型 */
	private String fileContentType;

	private String result;
	private List<VcDestroyDet> vcDestroyDet;
	private List<VcDestroy> vcDestroys;
	private List<VcApprove> vcApprove;
	private NewDestroyService newDestroyService;
	private DestroyService destroyService;
	public String initDestroyQuery() {
		applyEndDate = new Date();
		applyStartDate = DateUtils.addMonth(applyEndDate, -2);
		return SUCCESS;
	}
	/**
	 * 缴销审批
	 * 
	 * @return
	 */
	public String approveDestroy() {
		try {
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			VcApprove vcApprove = new VcApprove();
			vcApprove.setCheckOprId(userInfo.getUserCode());
			vcApprove.setCheckOrgId(userInfo.getComCode());
			String approveOpp = getRequest().getParameter("approveOpp");
			vcApprove.setCheckExpl(approveOpp);
			result = newDestroyService.executeApprove(vcApprove, id, actionType);
		}catch(BusinessException e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	/**
	 * 进入缴销审批
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws NumberFormatException
	 */
	public String initDestroyApprove() throws BusinessException {
		try{
			//查询审批记录
			List reuslt = newDestroyService.initDestroyView(Long.valueOf(id),null,"J");
			vcDestroy = (VcDestroy) reuslt.get(0);
			vcDestroyDet = vcDestroy.getVcDestroyDets();
			vcApprove = (List<VcApprove>) reuslt.get(1);
		}catch(BusinessException e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	/**
	 * 缴销审批查询
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String queryDestroyApprove() throws BusinessException {
		try{
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			String orgCode = userInfo.getComCode();
			vcDestroy = new VcDestroy();
			String destroyStatus = getRequest().getParameter("destroyStatus");
			String destroyCode = getRequest().getParameter("destroyCode");
			vcDestroy.setApplyStartDate(applyStartDate);
			vcDestroy.setApplyEndDate(applyEndDate);
			vcDestroy.setDestroyStatus(destroyStatus);
			vcDestroy.setDestroyCode(destroyCode);
			vcDestroy.setApproveOrgCode(orgCode);
			vcDestroy.setInvoiceFlag("1");
			vcDestroy.setJdFlag("1");
			Page resultPage = newDestroyService.queryDestroy(vcDestroy, page, rows, "approve");
			
			vcDestroys = (List<VcDestroy>) resultPage.getResult();
	
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
	/**
	 * 进入缴销审批页面
	 * 
	 * @return
	 */
	public String initDestroyApproveQuery() {
		applyEndDate = new Date();
		applyStartDate = DateUtils.addMonth(applyEndDate, -2);
		return SUCCESS;
	}
	/**
	 * 提交缴销
	 * 
	 * @return
	 */
	public String submitDestroy() throws BusinessException {
		try {
		
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			String userCode = userInfo.getUserCode();
			String orgCode = userInfo.getComCode();
	
			result = newDestroyService.executeSubmitDestroy(id, userCode, orgCode);
		}catch(BusinessException e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	/**
	 * 进入缴销编辑页面
	 * 
	 * @return
	 * @throws BusinessException
	 * @throws NumberFormatException
	 */
	public String initDestroyEdit() throws BusinessException {
		try{
		    String strType="edit";
		    if ("view".equals(actionType)) {
		        strType="view";
		    }
		    //查询审批记录
			List reuslt = newDestroyService.initDestroyView(Long.valueOf(id),strType,"J");
			vcDestroy = (VcDestroy) reuslt.get(0);
			vcDestroyDet = vcDestroy.getVcDestroyDets();
			vcApprove = (List<VcApprove>) reuslt.get(1);
			if ("modify".equals(actionType)) {
				for (Iterator iterator = vcDestroyDet.iterator(); iterator.hasNext();) {
					VcDestroyDet vcDestroyDet_ = (VcDestroyDet) iterator.next();
					vcDestroyDet_.setVcDestroy(null);
				}
				jsonStr = TFJSON.toJSONString(vcDestroyDet);
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
	 * 缴销查询
	 * 
	 * @return
	 * @throws BusinessException 
	 */
	public String queryDestroy() throws BusinessException {
		try{
			UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
			vcDestroy = new VcDestroy();
			String destroyStatus = getRequest().getParameter("destroyStatus");
			String destroyCode = getRequest().getParameter("destroyCode");
			vcDestroy.setApplyStartDate(applyStartDate);
			vcDestroy.setApplyEndDate(applyEndDate);
			vcDestroy.setDestroyStatus(destroyStatus);
			vcDestroy.setDestroyCode(destroyCode);
			vcDestroy.setDestroyOrgCode(userInfo.getComCode());
			vcDestroy.setInvoiceFlag("1");
			vcDestroy.setJdFlag("1");
			Page resultPage = newDestroyService.queryDestroy(vcDestroy, page, rows, actionType);
	
			vcDestroys = (List<VcDestroy>) resultPage.getResult();
	
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

	/**
	 * 进入缴销添加页面
	 * 
	 * @return
	 */
	public String initDestroyAdd() {
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		String destroyOrgName = userInfo.getComName();
		String destroyOprName = userInfo.getUserName();
		Date destroyAppTime = new Date();
		vcDestroy = new VcDestroy();
		vcDestroy.setDestroyOrgName(destroyOrgName);
		vcDestroy.setDestroyOprName(destroyOprName);
		vcDestroy.setDestroyAppTime(destroyAppTime);
		actionType = "add";
		return SUCCESS;
	}
	/**
	 * 保存/更新缴销
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveDestroy() throws Exception {
		Message message = new Message();
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
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
			String destroyReason = getRequest().getParameter("destroyReason");
			String destroyType = getRequest().getParameter("destroyType");
			String realPath = SysConst.ZPJX_FILE_SAVE_PATH;
			String fileName_ = null;
			File savefile = null;
			vcDestroy = new VcDestroy();
			if (file != null) {
				savefile = new File(new File(realPath), fileFileName);
			}

			// 销毁处理状态（0删除/1新建/2待审批/3审批退回/4审批通过） 对应提交(2)/保存(1)
			String destroyStatus = getRequest().getParameter("destroyStatus");
			
			vcDestroyDet = TFJSON.parseArray(jsonStr, VcDestroyDet.class);
			if ("add".equals(actionType)) {
				if (file == null) {
					throw new BusinessException("文件不能为空");
				}
				if (savefile.exists()) {
					throw new BusinessException("文件已存在");
				}
				// 操作人代码

			} else if ("modify".equals(actionType)) {
				vcDestroy.setId(Long.valueOf(id));
			}

			vcDestroy.setDestroyType(destroyType);

			vcDestroy.setDestroyOprCode(userCode);
			// 提交机构代码
			vcDestroy.setDestroyOrgCode(orgCode);
			// 销毁处理状态
			vcDestroy.setDestroyStatus(destroyStatus);
			// 销毁原因
			vcDestroy.setDestroyReason(destroyReason);
			//区分是发票还是单证的标志1发票为空是单证
			vcDestroy.setInvoiceFlag("1");
			//状态时1的时候是缴销为2的时候是销毁
			vcDestroy.setJdFlag("1");
			if (file != null) {
				// 文件名
				vcDestroy.setFileName(fileFileName);
				// 文件路径
				vcDestroy.setFilePath(realPath);
			}
			result = newDestroyService.saveDestroy(vcDestroy, vcDestroyDet, actionType, destroyStatus, file);

			if (!"操作成功".equals(result)) {
				throw new BusinessException(result);
			}

			// 保存文件/删除文件
			// String realpath =
			// ServletActionContext.getServletContext().getRealPath();
			if (file != null) {
				if ("modify".equals(actionType)) {
					File f = new File(realPath + "/" + fileName_);
					if (f.isFile() && f.exists()) {
						f.delete();
					}
				}
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
		messageInfo = TFJSON.toJSONString(message);
		if("success".equals(message.getType())){
			applyEndDate = new Date();
			applyStartDate = DateUtils.addMonth(applyEndDate, -2);
			return SUCCESS;
		}else{
			String userName = userInfo.getUserName();
			String orgName = userInfo.getComName();
			vcDestroy = new VcDestroy();
			vcDestroy.setDestroyOrgName(orgName);
			vcDestroy.setDestroyOprName(userName);
			vcDestroy.setDestroyAppTime(new Date());
			actionType = "add";
			return "fail";
		}
		
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
	public VcDestroy getVcDestroy() {
		return vcDestroy;
	}
	public void setVcDestroy(VcDestroy vcDestroy) {
		this.vcDestroy = vcDestroy;
	}
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<VcDestroyDet> getVcDestroyDet() {
		return vcDestroyDet;
	}
	public void setVcDestroyDet(List<VcDestroyDet> vcDestroyDet) {
		this.vcDestroyDet = vcDestroyDet;
	}
	public List<VcDestroy> getVcDestroys() {
		return vcDestroys;
	}
	public void setVcDestroys(List<VcDestroy> vcDestroys) {
		this.vcDestroys = vcDestroys;
	}
	public List<VcApprove> getVcApprove() {
		return vcApprove;
	}
	public void setVcApprove(List<VcApprove> vcApprove) {
		this.vcApprove = vcApprove;
	}
	public DestroyService getDestroyService() {
		return destroyService;
	}
	public void setDestroyService(DestroyService destroyService) {
		this.destroyService = destroyService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public NewDestroyService getNewDestroyService() {
		return newDestroyService;
	}
	public void setNewDestroyService(NewDestroyService newDestroyService) {
		this.newDestroyService = newDestroyService;
	}
}
