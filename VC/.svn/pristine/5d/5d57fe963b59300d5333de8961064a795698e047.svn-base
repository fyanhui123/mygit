package com.tapi.tcs.vc.store.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.store.service.ProvideService;
import com.tapi.tcs.vc.store.service.ServantDocManageService;

/**
 * 使用人单证发放/回收Action
 * <p>
 * Date: 2013-04-07
 * </p>
 * <p>
 * Module: 使用人单证发放/回收
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author Leo
 * @version 1.0
 */
public class ServantDocManageAction extends TFAction {

	private static final long serialVersionUID = -2248031194541049469L;

	/** VcDocTakerIo List */
	private List<VcDocTakerIo> vcDocTakerIos;
	/** 单证类型代码 */
	private String docVerCode;
	/** 单证类型名称 */
	private String docVerName;
	/** 使用人代码 */
	private String takerCode;
	/** 使用人名称 */
	private String takerName;
	/** 使用人所属机构代码 */
	private String acceptOrgCode;
	/** 使用人所属机构名称 */
	private String acceptOrgName;
	/** 发放起始时间 */
	private Date provideStartDate;
	/** 发放终止时间 */
	private Date provideEndDate;
	/** 操作结果 */
	private String result;
	/** 操作类型 */
	private String actionType;

	/** 使用人发放/回收 Service  */
	private ServantDocManageService servantDocManageService;

	 /***单证发放***/
	private ProvideService provideService;
	
	public void setProvideService(ProvideService provideService) {
		this.provideService = provideService;
	}

	/**
	 * 进入查询使用人单证发放查询页面
	 * 
	 * @return
	 */
	public String initServantDocIssuedQuery() {
		logger.info("访问/servantDocIssued/initServantDocIssuedQuery.action?ajax=true进入查询使用人单证发放页面...");
		try{
			actionType = "P";
			Date nowDate = new Date();
			provideStartDate = DateUtils.addMonth(nowDate, -2);
			provideEndDate = nowDate;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}

	/**
	 * 查询使用人单证发放
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String queryServantDoc() throws Exception {
		logger.info("访问/servantDocIssuedJson/queryServantDocIssued.action?ajax=true查询使用人单证发放...");
		try{
		VcDocTakerIo vcDocTakerIo = new VcDocTakerIo();
		vcDocTakerIo.setProvideStartDate(provideStartDate);
		vcDocTakerIo.setProvideEndDate(provideEndDate);
		vcDocTakerIo.setDocVerCode(docVerCode);
		vcDocTakerIo.setDocVerName(docVerName);
		vcDocTakerIo.setTakerCode(takerCode);
		vcDocTakerIo.setTakerName(takerName);
		vcDocTakerIo.setAcceptOrgCode(acceptOrgCode);
		vcDocTakerIo.setAcceptOrgName(acceptOrgName);
		vcDocTakerIo.setOprType(actionType);
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		String userCode = userInfo.getUserCode();
		String comCode = userInfo.getComCode();
		vcDocTakerIo.setOprCode(userCode);
		vcDocTakerIo.setUserComCode(comCode);

		Page resultPage = servantDocManageService.queryServantDocIssued(
				vcDocTakerIo, page, rows);

		vcDocTakerIos = (List<VcDocTakerIo>) resultPage.getResult();

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
		// 只可查询自己发放的记录。
		return SUCCESS;
	}
	
	/**
	 * 初始化发放新增
	 * @return
	 */
	public String initServantDocIssuedAdd() {
		return SUCCESS;
	}

	/**
	 * 保存使用人单证发放
	 * 
	 * @return
	 */
	public String saveServantDocIssued() {
		logger.info("访问/servantDocIssued/saveServantDocIssued.action?ajax=true保存使用人单证发放...");
		
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		// 操作员代码
		String userCode = userInfo.getUserCode();
		String comCode = userInfo.getComCode();
		String vcDocTakerIosStr = getRequest().getParameter("jsonStr");
		vcDocTakerIos = TFJSON.parseArray(vcDocTakerIosStr,VcDocTakerIo.class);
		try {
			result = servantDocManageService.saveServantDocIssued(vcDocTakerIos,
					userCode, comCode, takerCode, acceptOrgCode);
		} catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		    }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
		return SUCCESS;
	}

	/**
	 * 进入查询使用人单证回收查询页面
	 * @return
	 */
	public String initServantDocRecoveryQuery() {
		logger.info("访问/servantDocIssued/initServantDocRecoveryQuery.action?ajax=true进入查询使用人单证回收页面...");
		try{
			actionType = "R";
			Date nowDate = new Date();
			provideStartDate = DateUtils.addMonth(nowDate, -2);
			provideEndDate = nowDate;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			this.setMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化回收新增
	 * @return
	 */
	public String initServantDocRecoveryAdd() {
		return SUCCESS;
	}
	

	/**
	 * 保存使用人单证回收
	 * @return
	 */
	public String saveServantDocRecovery() {
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute("userInfo");
		String userCode = userInfo.getUserCode();
		String orgCode = userInfo.getComCode();
		String vcDocTakerIosStr = getRequest().getParameter("jsonStr");
		vcDocTakerIos = TFJSON.parseArray(vcDocTakerIosStr,VcDocTakerIo.class);
		try {
			result = servantDocManageService.saveServantDocRecovery(vcDocTakerIos,
					userCode,orgCode, takerCode, acceptOrgCode);
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
	 * 获取最长库存时间
	 * @return
	 */
	public String findMaxStoreTime() throws BusinessException{
		try{
			 String  orgCode = getRequest().getParameter("orgCode");
			 String  editTakerCode=getRequest().getParameter("editTakerCode");
			 String  mngType=getRequest().getParameter("mngType");           //使用人发放
	         String  maxStoreType=getRequest().getParameter("maxStoreType");  //最大单证保天数
		//	int maxStoreTime = servantDocManageService.getMaxStoreTime(docVerCode, orgCode);
	        if(StringUtils.isEmpty(editTakerCode)){ 
	        	throw  new  BusinessException("使用人代码不能为空！"); 
	        }
	        if(StringUtils.isEmpty(orgCode)){ 
	        	throw  new  BusinessException("使用人所属机构不能为空！"); 
	        }
			int maxStoreTime=provideService.getMaxStoreTime(docVerCode, mngType,editTakerCode); // 查询数据库得到最长库存时间
			
			Date maxDate = DateUtils.addDay(new Date(), maxStoreTime);
			result = DateUtils.format(maxDate);
		}catch(BusinessException e){
			 this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			 return NONE;
		}catch(Exception e){
			 this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	} 
	
	public List<VcDocTakerIo> getVcDocTakerIos() {
		return vcDocTakerIos;
	}

	public void setVcDocTakerIos(List<VcDocTakerIo> vcDocTakerIos) {
		this.vcDocTakerIos = vcDocTakerIos;
	}

	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	public String getTakerCode() {
		return takerCode;
	}

	public void setTakerCode(String takerCode) {
		this.takerCode = takerCode;
	}

	public String getAcceptOrgCode() {
		return acceptOrgCode;
	}

	public void setAcceptOrgCode(String acceptOrgCode) {
		this.acceptOrgCode = acceptOrgCode;
	}

	public Date getProvideStartDate() {
		return provideStartDate;
	}

	public void setProvideStartDate(Date provideStartDate) {
		this.provideStartDate = provideStartDate;
	}

	public Date getProvideEndDate() {
		return provideEndDate;
	}

	public void setProvideEndDate(Date provideEndDate) {
		this.provideEndDate = provideEndDate;
	}
	
	public void setServantDocManageService(ServantDocManageService servantDocManageService) {
		this.servantDocManageService = servantDocManageService;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDocVerName() {
		return docVerName;
	}

	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}

	public String getTakerName() {
		return takerName;
	}

	public void setTakerName(String takerName) {
		this.takerName = takerName;
	}

	public String getAcceptOrgName() {
		return acceptOrgName;
	}

	public void setAcceptOrgName(String acceptOrgName) {
		this.acceptOrgName = acceptOrgName;
	}
}
