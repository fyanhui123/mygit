package com.tapi.tcs.vc.order.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.order.service.OrderManagerService;
import com.tapi.tcs.vc.order.vo.VcPurchaseVo;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcPurchase;

/**
 * 生成采购单Action
 * <p>
 * Date 2013-04-24
 * </p>
 * <p>
 * Module：征订管理
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class PurchaseApproveAction extends TFAction{

	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**征订管理Service类注入*/
	private OrderManagerService orderManagerService;
	/**采购单VO*/
	private VcPurchaseVo vcPurchaseVo;
	/**审批记录*/
	private List<VcApprove> vcApprove;
	/**采购单列表*/
	private List vcPurchaseList;
	/**采购单号*/
	private String purchaseCode;
	/**采购单状态*/
	private String flag;
	/**申请时间*/
	private Date applyStartDate;
	private Date applyEndDate;
	/**审批意见*/
	private String checkExpl;
	
	/**
	 * 进入收货确认审批查询界面
	 * @return
	 */
	public String prePurchaseApproveQuery() {
		logger.info("访问/orderManager/prePurchaseApproveQuery.do进入收货确认审批查询界面...");
		Date nowDate = new Date();
		applyStartDate = DateUtils.addMonth(nowDate, -2);
		applyEndDate = nowDate;
		return SUCCESS;
	}
	/**
	 * 查询方法
	 * @return
	 * @throws Exception
	 */
	public String queryPurchaseApprove() throws BusinessException {
		logger.info("访问/orderJson/queryPurchaseApprove.do查询待审批的采购单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			vcPurchaseVo = new VcPurchaseVo();
			//拼接查询条件
			vcPurchaseVo.setPurchaseCode(purchaseCode);
			vcPurchaseVo.setFlag(flag);
			vcPurchaseVo.setApplyStartDate(applyStartDate);
			vcPurchaseVo.setApplyEndDate(applyEndDate);
			
			Page returnPage = orderManagerService.queryPurchaseApprove(vcPurchaseVo, comCode, page, rows);
			
			//返回页面的结果集
			vcPurchaseList = returnPage.getResult();
			//总页数
			total = (int)returnPage.getTotalPageCount();
			//总记录数
			records = (int)returnPage.getTotalCount();
		}catch(BusinessException be){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
			return NONE;
		}catch(Exception e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 进入收货确认审批界面
	 * @return
	 * @throws BusinessException
	 */
	public String editPurchaseApprove() throws BusinessException {
		logger.info("访问/orderManager/editPurchaseApprove.do进入收货确认审批界面...");
		try{
			String id = getRequest().getParameter("id");
			if(StringUtils.isEmpty(id)){
				throw new BusinessException("请选择一条记录！");
			}
			//查询采购单信息
			vcPurchaseVo = orderManagerService.findPurchaseApprove(Long.valueOf(id));
			//查询审批记录
			vcApprove = orderManagerService.queryVcApprove(Long.valueOf(id), "P");//P-采购单
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
	 * 收货确认审批通过/退回
	 * @return
	 * @throws BusinessException
	 */
	public String purchaseApprove() throws BusinessException {
		logger.info("访问/orderManager/purchaseApprove.do收货确认审批通过/退回...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//审批动作：1-审批退回；2-审批通过
			String checkStatus = getRequest().getParameter("checkStatus");
			//从页面获取采购单id
			Long purchaseId = vcPurchaseVo.getPurchaseId();
			//1、修改采购单表
			VcPurchase vcPurchaseTmp = orderManagerService.findPurchaseById(purchaseId);
			//标志位：4-待审批；5-审批退回；6-审批通过；
			if("1".equals(checkStatus)){
				vcPurchaseTmp.setFlag("5");
			}else if("2".equals(checkStatus)){
				vcPurchaseTmp.setFlag("6");
			}
			vcPurchaseTmp.setUpdatedBy(userCode);
			vcPurchaseTmp.setDateUpdated(new Date());
			
			//2、保存Approve表
			//组织VcApprove对象
			VcApprove vcApproveTmp = new VcApprove();
			vcApproveTmp.setApplyId(purchaseId);
			vcApproveTmp.setApplyType("P");//申请类型：P-采购单
			vcApproveTmp.setCheckOrgId(comCode);
			vcApproveTmp.setCheckOprId(userCode);
			vcApproveTmp.setCheckStatus(checkStatus);//审批状态：0-提交审批；1-审批退回；2-审批通过；
			vcApproveTmp.setCheckTime(new Date());
			vcApproveTmp.setCheckExpl(checkExpl);
			vcApproveTmp.setCreatedBy(userCode);
			vcApproveTmp.setDateCreated(new Date());
			vcApproveTmp.setUpdatedBy(userCode);
			vcApproveTmp.setDateUpdated(new Date());
			
			//3、执行审批操作
			orderManagerService.executePurchaseApp(vcPurchaseTmp, vcApproveTmp, checkStatus);
		}catch(BusinessException e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	
	public void setOrderManagerService(OrderManagerService orderManagerService) {
		this.orderManagerService = orderManagerService;
	}
	
	public VcPurchaseVo getVcPurchaseVo() {
		return vcPurchaseVo;
	}
	public void setVcPurchaseVo(VcPurchaseVo vcPurchaseVo) {
		this.vcPurchaseVo = vcPurchaseVo;
	}
	public List getVcPurchaseList() {
		return vcPurchaseList;
	}
	public void setVcPurchaseList(List vcPurchaseList) {
		this.vcPurchaseList = vcPurchaseList;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
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
	public List<VcApprove> getVcApprove() {
		return vcApprove;
	}
	public void setVcApprove(List<VcApprove> vcApprove) {
		this.vcApprove = vcApprove;
	}
	public String getCheckExpl() {
		return checkExpl;
	}
	public void setCheckExpl(String checkExpl) {
		this.checkExpl = checkExpl;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
