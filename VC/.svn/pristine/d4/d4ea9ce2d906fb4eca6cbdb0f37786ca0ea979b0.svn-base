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
import com.tapi.tcs.vc.order.service.OrderManagerService;
import com.tapi.tcs.vc.order.vo.OrderLaunchVo;
import com.tapi.tcs.vc.order.vo.VcPurchaseVo;
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
public class PurchaseGenerateAction extends TFAction{

	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**征订管理Service类注入*/
	private OrderManagerService orderManagerService;
	/**订单基本信息vo*/
	private OrderLaunchVo orderLaunchVo;
	/**查询结果集列表*/
	private List orderLaunchList;
	/**采购单VO*/
	private VcPurchaseVo vcPurchaseVo;
	/**采购单列表*/
	private List vcPurchaseList;
	/**订单id*/
	private String orderId;
	/**申请时间*/
	private Date applyStartDate;
	private Date applyEndDate;
	/**采购单号*/
	private String purchaseCode;
	/**采购单状态*/
	private String flag;
	
	/**
	 * 进入生成采购单查询界面
	 * @return
	 */
	public String prePurchaseQuery() {
		logger.info("访问/orderManager/prePurchaseQuery.do进入采购单查询界面...");
		return SUCCESS;
	}
	
	/**
	 * 查询采购单
	 * @return
	 * @throws BusinessException
	 */
	public String queryPurchase() throws BusinessException{
		logger.info("访问/orderJson/queryPurchase.do查询采购单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//查询Page对象
			vcPurchaseVo = new VcPurchaseVo();
			vcPurchaseVo.setPurchaseCode(purchaseCode);
			vcPurchaseVo.setFlag(flag);
			
			Page returnPage = orderManagerService.queryPurchase(vcPurchaseVo, userCode, comCode, page, rows);
			
			//返回页面的结果集
			vcPurchaseList = returnPage.getResult();
			//总页数
			total = (int)returnPage.getTotalPageCount();
			//总记录数
			records = (int)returnPage.getTotalCount();
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
	 * 新增采购单-查询可生成采购单的汇总订单
	 * @return
	 * @throws BusinessException
	 */
	public String queryPurchaseGenInput() throws BusinessException {
		logger.info("访问/orderJson/queryPurchaseGenInput.do新增采购单-查询可生成采购单的汇总订单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//查询Page对象
			orderLaunchVo = new OrderLaunchVo();
			orderLaunchVo.setApplyStartDate(applyStartDate);
			orderLaunchVo.setApplyEndDate(applyEndDate);
			
			Page returnPage = orderManagerService.queryPurchaseGenInput(orderLaunchVo, comCode, page, rows);
			
			//返回页面的结果集
			orderLaunchList = returnPage.getResult();
			//总页数
			total = (int)returnPage.getTotalPageCount();
			//总记录数
			records = (int)returnPage.getTotalCount();
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
	 * 生成采购单
	 * @return
	 * @throws BusinessException
	 */
	public String generatePruchase() throws BusinessException {
		logger.info("访问/orderJson/generatePruchase.do生成采购单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			
			if(StringUtils.isNotEmpty(orderId)){
				//保存采购单，更新订单状态
				//orderId：从页面收集的订单号。如1,2,3格式
				List<VcPurchase> list = orderManagerService.executePurchaseGen(orderId, userCode, comCode);
				vcPurchaseList = orderManagerService.generatePurchase(list);
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

	public OrderLaunchVo getOrderLaunchVo() {
		return orderLaunchVo;
	}

	public void setOrderLaunchVo(OrderLaunchVo orderLaunchVo) {
		this.orderLaunchVo = orderLaunchVo;
	}

	public List getOrderLaunchList() {
		return orderLaunchList;
	}

	public void setOrderLaunchList(List orderLaunchList) {
		this.orderLaunchList = orderLaunchList;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public void setOrderManagerService(OrderManagerService orderManagerService) {
		this.orderManagerService = orderManagerService;
	}
	
	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
