/**
 * 
 */
package com.tapi.tcs.vc.store.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcRecycle;
import com.tapi.tcs.vc.schema.model.VcRecycleDet;
import com.tapi.tcs.vc.store.service.RecycleService;
import com.tapi.tcs.vc.store.vo.QueryCondition;

/**
 * 回收确认调用Action
 * @author hanmiao.diao
 *
 */
public class RecycleConfirmAction extends TFAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3654239483296624439L;
		
	private RecycleService recycleService;
	
	private VcLevelService vcLevelService;
	
	private PubCodeManagerService pubCodeManagerService;
	
	@SuppressWarnings("rawtypes")
	private List resultList;
	
	private VcRecycle vcRecycle;
	
	private String jsonStr;
	
	@JSON(serialize=false)
	public VcLevelService getVcLevelService() {
		return vcLevelService;
	}

	public void setVcLevelService(VcLevelService vcLevelService) {
		this.vcLevelService = vcLevelService;
	}

	@JSON(serialize=false)
	public PubCodeManagerService getPubCodeManagerService() {
		return pubCodeManagerService;
	}

	public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
		this.pubCodeManagerService = pubCodeManagerService;
	}

	@JSON(serialize=false)
	public RecycleService getRecycleService() {
		return recycleService;
	}

	public void setRecycleService(RecycleService recycleService) {
		this.recycleService = recycleService;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	public VcRecycle getVcRecycle() {
		return vcRecycle;
	}

	public void setVcRecycle(VcRecycle vcRecycle) {
		this.vcRecycle = vcRecycle;
	}
	
	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	/**
	 * 请求recycleConfirmEdit.jsp
	 * @return page
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewRecycleConfirmEdit() throws BusinessException {
		String idStr = getRequest().getParameter("id");
		try{
		if(StringUtils.isNotEmpty(idStr)) {
			Long id = Long.valueOf(idStr);
			VcRecycle dbVcRecycle = recycleService.getVcRecycle(id);
			dbVcRecycle.setRecycleOprName(vcLevelService.getUnitNameByUnitCode(dbVcRecycle.getRecycleOprCode()));
			dbVcRecycle.setRecycleOrgName(vcLevelService.getUnitNameByUnitCode(dbVcRecycle.getRecycleOrgCode()));
			this.vcRecycle = dbVcRecycle;
			
			List<VcRecycleDet> detList = dbVcRecycle.getVcRecycleDets();
			for (Iterator it = detList.iterator(); it.hasNext();) {
				VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();
				vcRecycleDet.setVcRecycle(null);
			}
			
			this.jsonStr = TFJSON.toJSONString(detList);
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
	 * 请求recycleConfrimQuery.jsp
	 * @return page
	 * @throws BusinessException 异常
	 */
	public String viewRecycleConfirmQuery() throws BusinessException {
		try{
		Date sysdate = new Date();
		Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
		
		getRequest().setAttribute("initStartDate", twoMonthAgo);
		getRequest().setAttribute("initEndDate", sysdate);		
		}catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
		
		return SUCCESS;
	}
	
	/**
	 * 查询待确认的回收单对象
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String getAwaitConfrimRecycleList() throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 当前用户信息
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
		String comCode = userInfo.getComCode();
		try{
		// 查询参数
		QueryCondition qc = TFJSON.parseObject(jsonStr, QueryCondition.class);
		
		String queryCode  = qc.getQueryCode();
		String queryStatus = qc.getQueryStatus();
		String startDate = qc.getStartDate();
		String endDate = qc.getEndDate();

		params.put("recycleStatus", queryStatus);
		params.put("recycleCode", queryCode);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("confirmOrgCode", comCode);
		
		Page pageObj = recycleService.queryRecycleListByPages(params, page, rows);
		
		List pageList = pageObj.getResult();
		
		for (Iterator it = pageList.iterator(); it.hasNext();) {
			VcRecycle v = (VcRecycle) it.next();
			v.setRecycleOprName(vcLevelService.getUnitNameByUnitCode(v.getRecycleOprCode()));
			v.setRecycleOrgName(vcLevelService.getUnitNameByUnitCode(v.getRecycleOrgCode()));
			v.setConfirmOrgName(vcLevelService.getUnitNameByUnitCode(v.getConfirmOrgCode()));
			v.setRecycleStatusZh(pubCodeManagerService.getCodeCname("RecycleStatus", v.getRecycleStatus()));
		}
		
		this.resultList = pageList;
		total = (int) pageObj.getTotalPageCount();
		records = (int) pageObj.getTotalCount();
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
	 * 回收确认[同意]或者[退回]操作时调用的方法
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String saveRecycleConfirm() throws BusinessException {
		try {
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String comCode = userInfo.getComCode();
			String userCode = userInfo.getUserCode();
			
			VcRecycle pageVcRecycle = (VcRecycle) TFJSON.parseObject(this.jsonStr, VcRecycle.class);
			pageVcRecycle.setConfirmOprCode(userCode);
			pageVcRecycle.setConfirmOrgCode(comCode);
			pageVcRecycle.setUpdatedBy(userCode);
			
			String s = recycleService.saveRecycleConfirm(pageVcRecycle);
			this.jsonStr = s;
		}catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		    }catch (Exception e) {
			e.printStackTrace();
			this.jsonStr = e.getMessage();
		}
		return SUCCESS;
	}

}
