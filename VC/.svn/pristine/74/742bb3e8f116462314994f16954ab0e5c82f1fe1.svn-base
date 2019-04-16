/**
 * 
 */
package com.tapi.tcs.vc.store.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.store.service.ApplyService;
import com.tapi.tcs.vc.store.service.ProvideService;


/**
 * 发放确认Action入口
 * @author hanmiao.diao
 *
 */
public class ProvideConfirmAction extends TFAction {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -38087685380091880L;
	
	private ProvideService provideService;

	private ApplyService applyService;
	
	private VcLevelService vcLevelService;
	
	@JSON(serialize=false)
	public ProvideService getProvideService() {
		return provideService;
	}

	public void setProvideService(ProvideService provideService) {
		this.provideService = provideService;
	}
	@JSON(serialize=false)
	public ApplyService getApplyService() {
		return applyService;
	}

	public void setApplyService(ApplyService applyService) {
		this.applyService = applyService;
	}
	
	@JSON(serialize=false)
	public VcLevelService getVcLevelService() {
		return vcLevelService;
	}

	public void setVcLevelService(VcLevelService vcLevelService) {
		this.vcLevelService = vcLevelService;
	}

	/**
	 * 分页查询结果
	 */
	@SuppressWarnings("rawtypes")
	private List resultList;	
	
	/**
	 * 申领信息
	 */
	private VcApply vcApply;
	
	/**
	 * json字符串
	 */
	private String json;
	
	@SuppressWarnings("rawtypes")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	public VcApply getVcApply() {
		return vcApply;
	}

	public void setVcApply(VcApply vcApply) {
		this.vcApply = vcApply;
	}

	/**
	 * 请求领用确认查询页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewProvideConfirmQuery() throws BusinessException {
		Date sysdate = new Date();
		Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
		
		getRequest().setAttribute("initStartDate", twoMonthAgo);
		getRequest().setAttribute("initEndDate", sysdate);		
		
		return SUCCESS;
	}
	
	/**
	 * 请求领用确认操作页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewProvideConfirmEdit() throws BusinessException {
		String id = getRequest().getParameter("id");
		try{
		if(StringUtils.isNotEmpty(id)) {
			//查询申领表
			VcApply vcApply = applyService.getVcApply(Long.valueOf(id));
			vcApply.setApplyOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOrgCode()));
			vcApply.setApplyOprName(vcLevelService.getUnitNameByUnitCode(vcApply.getApplyOprCode()));
			vcApply.setProvideOrgName(vcLevelService.getUnitNameByUnitCode(vcApply.getProvideOrgCode()));
			
			this.vcApply = vcApply;
			
			List<VcApplyDet> detList = applyService.getApplyDetListByVcApplyId(Long.valueOf(id));
			
			for (Iterator it = detList.iterator(); it.hasNext();) {
				VcApplyDet vcApplyDet = (VcApplyDet) it.next();
				vcApplyDet.setDeadlineStr(vcApplyDet.getDeadline()==null?"":DateUtils.format(vcApplyDet.getDeadline(), "yyyy-MM-dd"));
			}
			
			this.json = TFJSON.toJSONString(detList);
		}
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
	 * 保存领用确认操作
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String saveProvideConfirm() throws BusinessException {
		
		try {
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String userCode = userInfo.getUserCode();
			
			VcApply provideConfrim = (VcApply) TFJSON.parseObject(json, VcApply.class);
			
			logger.info("解析provide字符串  " + (provideConfrim == null?"":provideConfrim.getApplyStatus()));
			
			provideConfrim.setConfirmOprCode(userCode);
			provideConfrim.setUpdatedBy(userCode);
			
			provideService.saveProvideConfirm(provideConfrim);
			
			this.json = "操作成功";
		} catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		    }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
		
		return SUCCESS;
	}

}
