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
import org.apache.log4j.Logger;
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
 * @author hanmiao.diao
 *
 */
public class RecycleAction extends TFAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 574082211530128045L;
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RecycleAction.class);
	
	private RecycleService recycleService;
	
	private VcLevelService vcLevelService;
	
	private PubCodeManagerService pubCodeManagerService;
	
	@JSON(serialize=false)
	public RecycleService getRecycleService() {
		return recycleService;
	}

	public void setRecycleService(RecycleService recycleService) {
		this.recycleService = recycleService;
	}	
	
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

	/**
	 * 页面中封装的对象
	 */
	private VcRecycle vcRecycle;
	
	/**
	 * json字符串
	 */
	private String jsonStr;
	
	/**
	 * 分页查询结果list
	 */
	@SuppressWarnings("rawtypes")
	private List resultList; 
	
	/**
	 * 历史审批集合
	 */
	@SuppressWarnings("rawtypes")
	private List approveList;
	
	@SuppressWarnings("rawtypes")
	@org.apache.struts2.json.annotations.JSON(serialize=false)
	public List getApproveList() {
		return approveList;
	}

	@SuppressWarnings("rawtypes")
	public void setApproveList(List approveList) {
		this.approveList = approveList;
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

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	
	/**
	 * 请求回收单修改页面
	 * @return page
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewRecycleEdit() throws BusinessException {
		String idStr = getRequest().getParameter("id");
		try{
		if(StringUtils.isNotEmpty(idStr)) {
			// 进入修改页面
			Long id = Long.valueOf(idStr);
			VcRecycle dbObject = recycleService.getVcRecycle(id);
			dbObject.setRecycleOprName(vcLevelService.getUnitNameByUnitCode(dbObject.getRecycleOprCode()));
			dbObject.setRecycleOrgName(vcLevelService.getUnitNameByUnitCode(dbObject.getRecycleOrgCode()));
			
			
			this.vcRecycle = dbObject;
			
			List<VcRecycleDet> vcRecycleDets = dbObject.getVcRecycleDets();
			for (Iterator it = vcRecycleDets.iterator(); it
					.hasNext();) {
				VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();
				vcRecycleDet.setVcRecycle(null);
			}
			
			this.jsonStr = TFJSON.toJSONString(vcRecycleDets);
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
	 * 请求显示详情页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewRecycleReadonly() throws BusinessException {
		String idStr = getRequest().getParameter("id");
		try{
		if(StringUtils.isNotEmpty(idStr)) {
			// 进入修改页面
			Long id = Long.valueOf(idStr);
			VcRecycle dbObject = recycleService.getVcRecycle(id);
			dbObject.setRecycleOprName(vcLevelService.getUnitNameByUnitCode(dbObject.getRecycleOprCode()));
			dbObject.setRecycleOrgName(vcLevelService.getUnitNameByUnitCode(dbObject.getRecycleOrgCode()));
			dbObject.setConfirmOrgName(vcLevelService.getUnitNameByUnitCode(dbObject.getConfirmOrgCode())); 
			//dbObject.setRecycleStatusZh(recycleStatusZh);
			this.vcRecycle = dbObject;
			
			List<VcRecycleDet> vcRecycleDets = dbObject.getVcRecycleDets();
			
			for (Iterator it = vcRecycleDets.iterator(); it
					.hasNext();) {
				VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();
				vcRecycleDet.setVcRecycle(null);
			}
			this.jsonStr = TFJSON.toJSONString(vcRecycleDets);		
			
			this.approveList = recycleService.getVcApprove(id, "R");
		}}catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		    }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }

		return SUCCESS;
	}	
	
	/**
	 * 请求新增页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewRecycleNew() throws BusinessException {
	try{
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
		String comCode = userInfo.getComCode();
		String userCode = userInfo.getUserCode();
		String userName = userInfo.getUserName();
		String comName = userInfo.getComName();
		
		VcRecycle vcRecycleTmp = new VcRecycle();
		
		vcRecycleTmp.setRecycleOprCode(userCode);
		vcRecycleTmp.setRecycleOprName(userName);
		vcRecycleTmp.setRecycleOrgCode(comCode);
		vcRecycleTmp.setRecycleOrgName(comName);
		
		vcRecycleTmp.setRecycleTime(new Date());
		
		this.vcRecycle = vcRecycleTmp;
	}catch(Exception e){
 	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	   return NONE;
     }
		return SUCCESS;
	}	

	/**
	 * 请求回收单查询页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewRecycleQuery() throws BusinessException {
		try{
		Date sysdate = new Date();
		Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
		
		getRequest().setAttribute("initStartDate", twoMonthAgo);
		getRequest().setAttribute("initEndDate", sysdate);
		}
		catch(Exception e){
	    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	    	   return NONE;
	    }
		return SUCCESS;
	}
	
	/**
	 * 保存或者提交单一回收单信息入口
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String saveRecycle() throws BusinessException {
		if (logger.isInfoEnabled()) {
			logger.info("saveRecycle() - start"); //$NON-NLS-1$
		}
		
		String json = this.jsonStr;
		
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
		String userCode = userInfo.getUserCode();
		
		try {
			VcRecycle vcRecycle = (VcRecycle) TFJSON.parseObject(json, VcRecycle.class);
			
			vcRecycle.setCreatedBy(userCode);
			vcRecycle.setUpdatedBy(userCode);
			
			String confirmOrgCode = vcLevelService.getUpperOrgCode(userCode);
			vcRecycle.setConfirmOrgCode(confirmOrgCode);
			
			this.jsonStr  = recycleService.saveRecycle(vcRecycle, false);
			
		 }
		   catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		    }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
		
		if (logger.isInfoEnabled()) {
			logger.info("saveRecycle() - end"); //$NON-NLS-1$
		}
		return SUCCESS;
	}
	
	/**
	 * 批量提交回收单
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String submitRecycleList() throws BusinessException {
		String submitList = this.jsonStr;
		String[] idArray = submitList.split(",");
		StringBuffer sb = new StringBuffer();
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
		String userCode = userInfo.getUserCode();
		String updatedBy = userCode;

		try{
		if(idArray!=null && idArray.length>0) {
			for (int i = 0; i < idArray.length; i++) {
				Long id = Long.valueOf(idArray[i]);
				try {
					VcRecycle vcRecycle = recycleService.getVcRecycle(id);
					vcRecycle.setUpdatedBy(updatedBy);
					recycleService.saveRecycle(vcRecycle, true);
					sb.append("回收单号["+vcRecycle.getRecycleCode()+"]提交成功");
				} catch (Exception e) {
					e.printStackTrace();
					sb.append("回收单号["+vcRecycle.getRecycleCode()+"]提交失败");
				}
			}
		this.jsonStr = sb.toString();
		}}catch(Exception e){
	    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	    	   return NONE;
	    }
		
		return SUCCESS;
	}
	
	/**
	 * 批量逻辑删除回收单
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String deleteRecycleList() throws BusinessException {
		
		String deleteList = this.jsonStr;
		
		String[] idArray = deleteList.split(",");
		try {
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
		String userCode = userInfo.getUserCode();
		
		String currentUser = userCode;
		
		
			this.jsonStr = recycleService.deleteByLogic(idArray, currentUser);
		}catch(Exception e){
	    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
	    	   return NONE;
	    }
		
		return SUCCESS;
	}
	
	/**
	 * 分页查询申领信息
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String queryRecycleListByPages() throws BusinessException {
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
		params.put("recycleOrgCode", comCode);
		
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
	 * 获取回收明细信息
	 * @return json对象
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String getRecycleDetListByJson() throws BusinessException {
		String idStr = getRequest().getParameter("id");
		
		try{
		
		if(StringUtils.isNotEmpty(idStr)) {
			Long id = Long.valueOf(idStr);
			
			VcRecycle vcRecycle = recycleService.getVcRecycle(id);
			
			List<VcRecycleDet> vcRecycleDets = vcRecycle.getVcRecycleDets();
			for (Iterator it = vcRecycleDets.iterator(); it
					.hasNext();) {
				VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();
				vcRecycleDet.setVcRecycle(null);
			}
			
			this.jsonStr = TFJSON.toJSONString(vcRecycleDets);
			
		}}catch(BusinessException e){
	     	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
	    	   return NONE;
		  }catch(Exception e){
		    	   this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
		    	   return NONE;
		    }
		return SUCCESS;
	}
}
