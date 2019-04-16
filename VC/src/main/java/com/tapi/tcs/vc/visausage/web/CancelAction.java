/**
 * 
 */
package com.tapi.tcs.vc.visausage.web;

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
import com.tapi.tcs.vc.schema.model.VcCancel;
import com.tapi.tcs.vc.schema.model.VcCancelDet;
import com.tapi.tcs.vc.visausage.service.CancelService;
import com.tapi.tcs.vc.visausage.vo.QueryCondition;

/**
 * 作废处理ACTION
 * @author hanmiao.diao
 *
 */
public class CancelAction extends TFAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9029266921400755451L;

	/**
	 * VcCancel对象
	 */
	private VcCancel cancel;
	
	/**
	 * json字符串
	 */
	private String jsonStr;
	
	/**
	 * 分页查询结果
	 */
	@SuppressWarnings("rawtypes")
	private List resultList;
	
	/**
	 * 历史审批集合
	 */
	@SuppressWarnings("rawtypes")
	private List approveList;

	private CancelService cancelService;
	
	private VcLevelService vcLevelService;
	
	private PubCodeManagerService pubCodeManagerService;
	
	
	public VcCancel getCancel() {
		return cancel;
	}

	public void setCancel(VcCancel cancel) {
		this.cancel = cancel;
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
	
	@SuppressWarnings("rawtypes")
	public List getApproveList() {
		return approveList;
	}

	@SuppressWarnings("rawtypes")
	public void setApproveList(List approveList) {
		this.approveList = approveList;
	}

	@JSON(serialize=false)
	public CancelService getCancelService() {
		return cancelService;
	}

	public void setCancelService(CancelService cancelService) {
		this.cancelService = cancelService;
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
	 * 请求库存作废查询页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewCancelSQuery() throws BusinessException {
		Date sysdate = new Date();
		Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
		
		getRequest().setAttribute("initStartDate", twoMonthAgo);
		getRequest().setAttribute("initEndDate", sysdate);
		
		return SUCCESS;
	}
	
	/**
	 * 请求使用作废查询页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewCancelUsageQuery() throws BusinessException {
		Date sysdate = new Date();
		Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
		
		getRequest().setAttribute("initStartDate", twoMonthAgo);
		getRequest().setAttribute("initEndDate", sysdate);
		
		return SUCCESS;
	}	
	
	/**
	 * 请求作废审批查询页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewCancelApproveQuery() throws BusinessException {
		Date sysdate = new Date();
		Date twoMonthAgo = DateUtils.addDay(sysdate, -60);
		
		getRequest().setAttribute("initStartDate", twoMonthAgo);
		getRequest().setAttribute("initEndDate", sysdate);
		
		return SUCCESS;
	}		
	
	/**
	 * 请求作废审批操作页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewCancelApproveEdit() throws BusinessException {
		try{
			String idStr = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(idStr)) {
				Long id = Long.valueOf(idStr);
				
				VcCancel initCancel = cancelService.getVcCancel(id);
				initCancel.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOrgCode()));
				initCancel.setCancelOprName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOprCode()));
				this.cancel = initCancel;
				
				VcCancel vcCancel = cancelService.getVcCancel(Long.valueOf(idStr));
				
				List<VcCancelDet> detList = vcCancel.getVcCancelDets();
				
				for (Iterator it = detList.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					vcCancelDet.setVcCancel(null);
				}
				this.jsonStr = TFJSON.toJSONString(detList);		
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 请求作废审批详情页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewCancelApproveReadonly() throws BusinessException {
		try{
			String idStr = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(idStr)) {
				Long id = Long.valueOf(idStr);
				
				VcCancel initCancel = cancelService.getVcCancel(id);
				initCancel.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOrgCode()));
				initCancel.setCancelOprName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOprCode()));
				this.cancel = initCancel;
				
				VcCancel vcCancel = cancelService.getVcCancel(Long.valueOf(idStr));
				
				List<VcCancelDet> detList = vcCancel.getVcCancelDets();
				
				for (Iterator it = detList.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					vcCancelDet.setVcCancel(null);
				}
				this.jsonStr = TFJSON.toJSONString(detList);	
				
				this.approveList = cancelService.getVcApprove(id, "C");
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 请求库存作废新增页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewCancelSNew() throws BusinessException {
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
	//		String comCode = userInfo.getComCode();
	//		String userCode = userInfo.getUserCode();
			String userName = userInfo.getUserName();
			String comName = userInfo.getComName();
			
			VcCancel initCancel = new VcCancel();
			Date cancelTime = new Date();
			initCancel.setCancelOrgName(comName);
			initCancel.setCancelOprName(userName);
			initCancel.setCancelTime(cancelTime);
			
			this.cancel = initCancel;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}	
	
	/**
	 * 请求库存作废修改页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewCancelSEdit() throws BusinessException {
		try{
			String idStr = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(idStr)) {
				Long id = Long.valueOf(idStr);
				
				VcCancel initCancel = cancelService.getVcCancel(id);
				initCancel.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOrgCode()));
				initCancel.setCancelOprName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOprCode()));
				this.cancel = initCancel;
				
				VcCancel vcCancel = cancelService.getVcCancel(Long.valueOf(idStr));
				
				List<VcCancelDet> detList = vcCancel.getVcCancelDets();
				
				for (Iterator it = detList.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					vcCancelDet.setVcCancel(null);
				}
				this.jsonStr = TFJSON.toJSONString(detList);		
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 请求使用作废新增页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	public String viewCancelUsageNew() throws BusinessException {
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
//		String comCode = userInfo.getComCode();
//		String userCode = userInfo.getUserCode();
		String userName = userInfo.getUserName();
		String comName = userInfo.getComName();
		
		VcCancel initCancel = new VcCancel();
		Date cancelTime = new Date();
		initCancel.setCancelOrgName(comName);
		initCancel.setCancelOprName(userName);
		initCancel.setCancelTime(cancelTime);
		
		this.cancel = initCancel;

		return SUCCESS;
	}	
	
	/**
	 * 请求使用作废修改页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewCancelUsageEdit() throws BusinessException {
		try{
			String idStr = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(idStr)) {
				Long id = Long.valueOf(idStr);
				
				VcCancel initCancel = cancelService.getVcCancel(id);
				initCancel.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOrgCode()));
				initCancel.setCancelOprName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOprCode()));
				this.cancel = initCancel;
				VcCancel vcCancel = cancelService.getVcCancel(Long.valueOf(idStr));
				
				List<VcCancelDet> detList = vcCancel.getVcCancelDets();
				
				for (Iterator it = detList.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					vcCancelDet.setVcCancel(null);
				}
				this.jsonStr = TFJSON.toJSONString(detList);				
			} 
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}	
	
	/**
	 * 请求库存作废详情页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewCancelSReadonly() throws BusinessException {
		try{
			String idStr = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(idStr)) {
				Long id = Long.valueOf(idStr);
				
				VcCancel initCancel = cancelService.getVcCancel(id);
				initCancel.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOrgCode()));
				initCancel.setCancelOprName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOprCode()));
				this.cancel = initCancel;
				
				VcCancel vcCancel = cancelService.getVcCancel(Long.valueOf(idStr));
				
				List<VcCancelDet> detList = vcCancel.getVcCancelDets();
				
				for (Iterator it = detList.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					vcCancelDet.setVcCancel(null);
				}
				this.jsonStr = TFJSON.toJSONString(detList);
				this.approveList = cancelService.getVcApprove(id, "C");
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}	
	
	/**
	 * 请求使用作废详情页面
	 * @return jsp
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String viewCancelUsageReadonly() throws BusinessException {
		try{
			String idStr = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(idStr)) {
				Long id = Long.valueOf(idStr);
				
				VcCancel initCancel = cancelService.getVcCancel(id);
				initCancel.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOrgCode()));
				initCancel.setCancelOprName(vcLevelService.getUnitNameByUnitCode(initCancel.getCancelOprCode()));
				this.cancel = initCancel;
	
				VcCancel vcCancel = cancelService.getVcCancel(Long.valueOf(idStr));
				
				List<VcCancelDet> detList = vcCancel.getVcCancelDets();
				
				for (Iterator it = detList.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					vcCancelDet.setVcCancel(null);
				}
				this.jsonStr = TFJSON.toJSONString(detList);	
				this.approveList = cancelService.getVcApprove(id, "C");
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}		
	
	/**
	 * 分页查询库存作废申请列表
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String queryCancelSListByPages() throws BusinessException {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			// 当前用户信息
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String comCode = userInfo.getComCode();
	//		String userCode = userInfo.getUserCode();
	//		String userName = userInfo.getUserName();
			// 查询参数
			QueryCondition qc = TFJSON.parseObject(jsonStr, QueryCondition.class);
			
			String cancelCode  = qc.getCancelCode();
			String cancelStatus = qc.getCancelStatus();
			String startDate = qc.getStartDate();
			String endDate = qc.getEndDate();
	
			params.put("cancelCode", cancelCode);
			params.put("cancelType", "S");
			params.put("cancelStatus", cancelStatus);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("cancelOrgCode", comCode);
			
			Page pageObj = cancelService.queryCancelListByPages(params, page, rows);
			
			List pageList = pageObj.getResult();
			
			for (Iterator it = pageList.iterator(); it.hasNext();) {
				VcCancel v = (VcCancel) it.next();
				v.setCancelOprName(vcLevelService.getUnitNameByUnitCode(v.getCancelOprCode()));
				v.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(v.getCancelOrgCode()));
				v.setCancelStatusZh(pubCodeManagerService.getCodeCname("CancelStatus", v.getCancelStatus()));
			}
			
			this.resultList = pageList;
			total = (int) pageObj.getTotalPageCount();
			records = (int) pageObj.getTotalCount();
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 分页查询使用作废申请列表
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String queryCancelUsageListByPages() throws BusinessException {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			// 当前用户信息
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String comCode = userInfo.getComCode();
	//		String userCode = userInfo.getUserCode();
	//		String userName = userInfo.getUserName();
			// 查询参数
			QueryCondition qc = TFJSON.parseObject(jsonStr, QueryCondition.class);
			
			String cancelCode  = qc.getCancelCode();
			String cancelStatus = qc.getCancelStatus();
			String startDate = qc.getStartDate();
			String endDate = qc.getEndDate();
	
			params.put("cancelCode", cancelCode);
			params.put("cancelType", "U");
			params.put("cancelStatus", cancelStatus);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("cancelOrgCode", comCode);
			
			Page pageObj = cancelService.queryCancelListByPages(params, page, rows);
			
			List pageList = pageObj.getResult();
			
			for (Iterator it = pageList.iterator(); it.hasNext();) {
				VcCancel v = (VcCancel) it.next();
				v.setCancelOprName(vcLevelService.getUnitNameByUnitCode(v.getCancelOprCode()));
				v.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(v.getCancelOrgCode()));
				v.setCancelStatusZh(pubCodeManagerService.getCodeCname("CancelStatus", v.getCancelStatus()));
			}
			
			this.resultList = pageList;
			
			total = (int) pageObj.getTotalPageCount();
			records = (int) pageObj.getTotalCount();
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}	
	
	/**
	 * 分页查询待审批作废申请列表
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String queryCancelListByPages() throws BusinessException {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			// 当前用户信息
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String comCode = userInfo.getComCode();
	//		String userCode = userInfo.getUserCode();
	//		String userName = userInfo.getUserName();
			// 查询参数
			QueryCondition qc = TFJSON.parseObject(jsonStr, QueryCondition.class);
			
			String cancelCode  = qc.getCancelCode();
			String cancelType = qc.getCancelType();
			String cancelStatus = qc.getCancelStatus();
			String startDate = qc.getStartDate();
			String endDate = qc.getEndDate();
	
			params.put("cancelCode", cancelCode);
			params.put("cancelType", cancelType);
			params.put("cancelStatus", cancelStatus);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("approveOrgCode", comCode);
			
			Page pageObj = cancelService.queryCancelListByPages(params, page, rows);
			
			List pageList = pageObj.getResult();
			
			for (Iterator it = pageList.iterator(); it.hasNext();) {
				VcCancel v = (VcCancel) it.next();
				v.setCancelOprName(vcLevelService.getUnitNameByUnitCode(v.getCancelOprCode()));
				v.setCancelOrgName(vcLevelService.getUnitNameByUnitCode(v.getCancelOrgCode()));
				v.setCancelStatusZh(pubCodeManagerService.getCodeCname("CancelStatus", v.getCancelStatus()));
				v.setCancelTypeZh(pubCodeManagerService.getCodeCname("CancelType", v.getCancelType()));
			}
			
			this.resultList = pageList;
			total = (int) pageObj.getTotalPageCount();
			records = (int) pageObj.getTotalCount();
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}	
	
	/**
	 * 保存或者提交单一库存作废申请
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String saveCancelS() throws BusinessException {
		try {
			VcCancel cancelS = TFJSON.parseObject(jsonStr, VcCancel.class);
			// 获取user信息
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String comCode = userInfo.getComCode();
			String userCode = userInfo.getUserCode();
			//String userName = userInfo.getUserName();
			// 从UM中取
			String cancelOrgCode = comCode;
			String cancelOprCode = userCode;
			String approveOrgCode = vcLevelService.getUpperOrgCode(userCode);
			
			cancelS.setCancelType("S");
			cancelS.setCancelOrgCode(cancelOrgCode);
			cancelS.setCancelOprCode(cancelOprCode);
			
			cancelS.setCreatedBy(cancelOprCode);
			cancelS.setUpdatedBy(cancelOprCode);
			
			cancelS.setApproveOrgCode(approveOrgCode);//##
			
			String rs = cancelService.saveCancelS(cancelS);
			this.jsonStr = rs;
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 保存或者提交单一使用作废申请
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String saveCancelUsage() throws BusinessException {
		try {
			VcCancel cancelUsage = TFJSON.parseObject(jsonStr, VcCancel.class);
			
			// 获取user信息
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			String comCode = userInfo.getComCode();
			String userCode = userInfo.getUserCode();
//		String userName = userInfo.getUserName();
			
			String cancelOrgCode = comCode;  //从session中获取 
			String cancelOprCode = userCode; //从session中获取
			
			String approveOrgCode = vcLevelService.getUpperOrgCode(userCode);
			
			cancelUsage.setCancelType("U");
			cancelUsage.setCancelOrgCode(cancelOrgCode);
			cancelUsage.setCancelOprCode(cancelOprCode);
			
			cancelUsage.setCreatedBy(cancelOprCode);
			cancelUsage.setUpdatedBy(cancelOprCode);
			cancelUsage.setApproveOrgCode(approveOrgCode); //##
			
			String rs = cancelService.saveCancelUsage(cancelUsage);
			this.jsonStr = rs;
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		
		return SUCCESS;
	}	
	
	/**
	 * 获取作废明细json对象
	 * @return json对象
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	public String getDetList2Json() throws BusinessException {
		try{
			String idStr = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(idStr)) {
				VcCancel vcCancel = cancelService.getVcCancel(Long.valueOf(idStr));
				
				List<VcCancelDet> detList = vcCancel.getVcCancelDets();
				
				for (Iterator it = detList.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					vcCancelDet.setVcCancel(null);
				}
				this.jsonStr = TFJSON.toJSONString(detList);
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 批量逻辑删除作废申请
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String deleteCancelList() throws BusinessException {
		try{
			String deleteList = this.jsonStr;
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
	//		String comCode = userInfo.getComCode();
			String userCode = userInfo.getUserCode();
			
			String[] idArray = deleteList.split(",");
			
			String currentUser = userCode; // 换成从UM中取
			
			this.jsonStr = cancelService.deleteByLogic(idArray, currentUser);
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 批量提交库存作废申请
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String submitCancelSList() throws BusinessException {
		String submitList = this.jsonStr;
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
//		String comCode = userInfo.getComCode();
		String userCode = userInfo.getUserCode();
		
		String[] idArray = submitList.split(",");
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < idArray.length; i++) {
				Long id = Long.valueOf(idArray[i]);
				VcCancel vcCancel = cancelService.getVcCancel(id);
				if("1".equals(vcCancel.getCancelStatus()) || "3".equals(vcCancel.getCancelStatus()) ) { // 要修改的1
					vcCancel.setCancelStatus("2"); // 要修改的2
					vcCancel.setUpdatedBy(userCode); // 换成从UM中取
					try {
						cancelService.saveCancelS(vcCancel);
						sb.append("作废单号["+vcCancel.getCancelCode()+"]提交审批成功\n");
					} catch (Exception e) {
						e.printStackTrace();
						sb.append("作废单号["+vcCancel.getCancelCode()+"]提交审批失败,失败原因:" + e.getMessage() + "\n");
					}
				} else {
					sb.append("作废单号["+vcCancel.getCancelCode()+"]提交审批失败,状态已发生变更\n");
				}
			}
			this.jsonStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			this.jsonStr = e.getMessage();
		}
		
		return SUCCESS;
	}	
	
	/**
	 * 批量提交使用作废申请
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String submitCancelUsageList() throws BusinessException {
		String submitList = this.jsonStr;
		
		String[] idArray = submitList.split(",");
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
//		String comCode = userInfo.getComCode();
		String userCode = userInfo.getUserCode();
		String currentUser = userCode; //从UM 中取
		
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < idArray.length; i++) {
				Long id = Long.valueOf(idArray[i]);
				VcCancel vcCancel = cancelService.getVcCancel(id);
				if("1".equals(vcCancel.getCancelStatus())|| "3".equals(vcCancel.getCancelStatus())) { // 要修改的1
					vcCancel.setCancelStatus("2"); // 要修改的2
					vcCancel.setUpdatedBy(currentUser);
					try {
						cancelService.saveCancelUsage(vcCancel);
						sb.append("作废单号["+vcCancel.getCancelCode()+"]提交审批成功\n");
					} catch (Exception e) {
						e.printStackTrace();
						sb.append("作废单号["+vcCancel.getCancelCode()+"]提交审批失败,失败原因:" + e.getMessage() + "\n");
					}
				} else {
					sb.append("作废单号["+vcCancel.getCancelCode()+"]提交审批失败,失败原因:状态已发生变更\n");
				}
			}
			this.jsonStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			this.jsonStr = e.getMessage();
		}
		
		return SUCCESS;
	}	
	
	/**
	 * 提交作废审批
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	public String saveCancelApprove() throws BusinessException {
		VcCancel vcCancel = TFJSON.parseObject(jsonStr, VcCancel.class);
		Long id = vcCancel.getIdVcCancel();
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
//		String comCode = userInfo.getComCode();
		String userCode = userInfo.getUserCode();
//		String userName = userInfo.getUserName();
		
		if(id == null) {
			this.jsonStr = "审批失败,审批对象为空";
		} else {
			// 调用Service方法
			vcCancel.setApproveOprCode(userCode);
			vcCancel.setUpdatedBy(userCode);
			try {
				this.jsonStr = cancelService.saveCancelApprove(vcCancel);
			} catch (Exception e) {
				e.printStackTrace();
				this.jsonStr = e.getMessage();
			}
		}
		
		return SUCCESS;
	}
	
}
