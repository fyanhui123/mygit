package com.tapi.tcs.vc.order.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.order.service.OrderManagerService;
import com.tapi.tcs.vc.order.vo.OrderLaunchDetVo;
import com.tapi.tcs.vc.order.vo.OrderLaunchVo;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcOrderLaunch;
import com.tapi.tcs.vc.schema.model.VcOrderLaunchDet;
import com.tapi.tcs.vc.schema.model.VcOrderLaunchSub;

/**
 * 订单汇总Action
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
public class OrderGatherAction extends TFAction{

	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**征订管理Service类注入*/
	private OrderManagerService orderManagerService;
	/**订单基本信息*/
	private VcOrderLaunch vcOrderLaunch;
	/**订单详细信息*/
	private List<VcOrderLaunchDet> vcOrderLaunchDet;
	/**订单基本信息vo*/
	private OrderLaunchVo orderLaunchVo;
	/**订单详细信息VO*/
	private List<OrderLaunchDetVo> orderLaunchDetVos;
	/**查询结果集列表*/
	private List orderLaunchList;
	/**订单id*/
	private String orderId;
	/**订单号*/
	private String orderCode;
	/**状态*/
	private String flag;
	/**申请时间*/
	private Date applyStartDate;
	private Date applyEndDate;
	/**json数据*/
	private String jsonData;

	/**
	 * 进入订单汇总界面
	 * @return
	 */
	public String preOrderGatherQuery() {
		logger.info("访问/orderManager/preOrderGatherQuery.do进入订单汇总界面...");
		Date nowDate = new Date();
		applyStartDate = DateUtils.addMonth(nowDate, -2);
		applyEndDate = nowDate;
		return SUCCESS;
	}
	
	/**
	 * 查询汇总订单
	 * @return
	 * @throws BusinessException
	 */
	public String queryOrderGather() throws BusinessException{
		logger.info("访问/orderJson/queryOrderGather.do查询汇总订单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//查询Page对象
			orderLaunchVo = new OrderLaunchVo();
			orderLaunchVo.setOrderCode(orderCode);
			orderLaunchVo.setFlag(flag);
			orderLaunchVo.setApplyStartDate(applyStartDate);
			orderLaunchVo.setApplyEndDate(applyEndDate);
			
			Page returnPage = orderManagerService.queryOrderGather(orderLaunchVo, comCode, page, rows);
			
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
	 * 新增订单汇总-查询可汇总的订单
	 * @return
	 * @throws BusinessException
	 */
	public String queryOrderGatherInput() throws BusinessException {
		logger.info("访问/orderJson/queryOrderGatherInput.do新增订单汇总-查询可汇总的订单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//查询Page对象
			orderLaunchVo = new OrderLaunchVo();
			orderLaunchVo.setOrderCode(orderCode);
			orderLaunchVo.setApplyStartDate(applyStartDate);
			orderLaunchVo.setApplyEndDate(applyEndDate);
	
			orderLaunchList = orderManagerService.queryOrderGatherInput(orderLaunchVo, comCode);
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
	 * 订单汇总
	 * 按单证类型汇总订单列表
	 * @return
	 * @throws BusinessException
	 */
	public String orderGather() throws BusinessException {
		logger.info("访问/orderJson/orderGather.do按单证类型汇总订单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			if(StringUtils.isNotEmpty(orderId)){
				//出入订单号，按单证类型分组进行汇总
				//orderId：从页面收集的订单号。如1,2,3格式
				orderLaunchDetVos = orderManagerService.orderGather(orderId, comCode);
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
	 * 保存订单汇总
	 * @return
	 * @throws BusinessException
	 */
	public String saveOrderGather() throws BusinessException{
		logger.info("访问/orderManager/saveOrderGather.do保存汇总订单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			
			//操作类型：提交还是保存
			String bizType = getRequest().getParameter("bizType");
			
			List<VcOrderLaunch> subOrderList = new ArrayList<VcOrderLaunch>();
	
			//组织订单基本信息
			VcOrderLaunch vcOrderLaunchTemp = new VcOrderLaunch();
			vcOrderLaunchTemp.setOrderType("2");//订单类型：1-原始订单；2-汇总订单；
			vcOrderLaunchTemp.setOrgCode(comCode);
			vcOrderLaunchTemp.setFlag("0");//标志位：0-新建；1-审批通过；2-审批退回；9-待审批；
			vcOrderLaunchTemp.setValidStatus("1");//有效状态
			vcOrderLaunchTemp.setGatherFlag("0");//汇总标志：0-未汇总，1-已汇总
			vcOrderLaunchTemp.setModifyUserCode(userCode);
			vcOrderLaunchTemp.setModifyDate(new Date());
			vcOrderLaunchTemp.setCreatedBy(userCode);
			vcOrderLaunchTemp.setDateCreated(new Date());
			vcOrderLaunchTemp.setUpdatedBy(userCode);
			vcOrderLaunchTemp.setDateUpdated(new Date());
			
			//把JSON数据转换成List
			vcOrderLaunchDet = TFJSON.parseArray(jsonData, VcOrderLaunchDet.class);
			//给vcOrderLaunchDetList设置vcOrderLaunch对象，防止不能级联保存vcOrderLaunchDet的情况		
			if(vcOrderLaunchDet!=null && vcOrderLaunchDet.size()>0){
				for(VcOrderLaunchDet det : vcOrderLaunchDet){
					det.setVcOrderLaunch(vcOrderLaunchTemp);
					
					det.setCreatedBy(userCode);
					det.setDateCreated(new Date());
					det.setUpdatedBy(userCode);
					det.setDateUpdated(new Date());
				}
				vcOrderLaunchTemp.setVcOrderLaunchDetList(vcOrderLaunchDet);
			}
			
			//组织子订单信息
			if(StringUtils.isNotEmpty(orderId)){
				String[] orderIds = orderId.split(",");
				int size = orderIds.length;
				List<VcOrderLaunchSub> vcOrderLaunchSubList = new ArrayList<VcOrderLaunchSub>();
				for(int i=0;i<size;i++){
					VcOrderLaunchSub vcOrderLaunchSub = new VcOrderLaunchSub();
					vcOrderLaunchSub.setSubOrderId(Long.valueOf(orderIds[i]));
					vcOrderLaunchSub.setCreatedBy(userCode);
					vcOrderLaunchSub.setDateCreated(new Date());
					vcOrderLaunchSub.setUpdatedBy(userCode);
					vcOrderLaunchSub.setDateUpdated(new Date());
					vcOrderLaunchSub.setVcOrderLaunch(vcOrderLaunchTemp);
					vcOrderLaunchSubList.add(vcOrderLaunchSub);
					
					//查找出子订单，更新汇总标志为已汇总 begin
					VcOrderLaunch subOrder = orderManagerService.findOrderLaunchByPK(Long.valueOf(orderIds[i]));
					subOrder.setGatherFlag("1");
					subOrder.setUpdatedBy(userCode);
					subOrder.setDateUpdated(new Date());
					subOrderList.add(subOrder);
					//查找出子订单，更新汇总标志为已汇总 end
				}
				vcOrderLaunchTemp.setVcOrderLaunchSubList(vcOrderLaunchSubList);
			}
			
			//提交按钮：保存+提交
			if("submit".equals(bizType)){
				vcOrderLaunchTemp.setFlag("9");//待审核
				
				//插入审批记录
				VcApprove vcApprove = new VcApprove();
				vcApprove.setApplyType("O");//申请类型：O-征订
				vcApprove.setCheckOrgId(comCode);//审批机构：当前登录用户的机构代码
				vcApprove.setCheckOprId(userCode);//审批人：当前登录用户代码
				vcApprove.setCheckStatus("0");//审批状态：0-提交审批；1-审批退回；2-审批通过；
				vcApprove.setCheckTime(new Date());//审批时间
				vcApprove.setCheckExpl("提交");//审批意见
				vcApprove.setCreatedBy(userCode);
				vcApprove.setDateCreated(new Date());
				vcApprove.setUpdatedBy(userCode);
				vcApprove.setDateUpdated(new Date());
				orderManagerService.saveAndSubmitOrderGather(vcOrderLaunchTemp, subOrderList, vcApprove);
			}else{
				//调用保存方法
				orderManagerService.saveOrderGath(vcOrderLaunchTemp, subOrderList);
			}
			jsonData = "保存成功，订单号为"+vcOrderLaunchTemp.getOrderCode();
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
	 * 修改汇总订单
	 * @return
	 * @throws BusinessException
	 */
	public String updateOrderGather() throws BusinessException{
		logger.info("访问/orderManager/updateOrderGather.do修改汇总订单...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			
			//操作类型：提交还是更新
			String bizType = getRequest().getParameter("bizType");
			
			if(StringUtils.isEmpty(orderId)){
				throw new BusinessException("请选择订单！");
			}
			if(orderLaunchDetVos!=null && orderLaunchDetVos.size()>0){
				//根据主键查找订单
				VcOrderLaunch vcOrderLaunch = orderManagerService.findOrderLaunchByPK(Long.valueOf(orderId));
				List<VcOrderLaunchDet> detList = vcOrderLaunch.getVcOrderLaunchDetList();
				//循环处理订单详情
				for(VcOrderLaunchDet det : detList){
					//拿页面收集的订单详情与数据库中的进行比较
					for(OrderLaunchDetVo vo : orderLaunchDetVos){
						if(vo.getId().equals(det.getId())){
							//若对申印数量进行了修改，则更新数据库中的记录，并记录更新人、更新时间
							if(vo.getApplyPrintNum().intValue() != det.getApplyPrintNum().intValue()){
								det.setApplyPrintNum(vo.getApplyPrintNum());
								det.setUpdatedBy(userCode);
								det.setDateUpdated(new Date());
							}
							break;
						}
					}
				}
				vcOrderLaunch.setFlag("0");//修改后状态为‘新建’
				vcOrderLaunch.setModifyUserCode(userCode);
				vcOrderLaunch.setModifyDate(new Date());
				vcOrderLaunch.setUpdatedBy(userCode);
				vcOrderLaunch.setDateUpdated(new Date());
				//更新+提交
				if("submit".equals(bizType)){
					vcOrderLaunch.setFlag("9");//待审核
					//2、插入审批记录
					VcApprove vcApprove = new VcApprove();
					vcApprove.setApplyId(vcOrderLaunch.getId());//申请编号：订单号
					vcApprove.setApplyType("O");//申请类型：O-征订
					vcApprove.setCheckOrgId(comCode);//审批机构：当前登录用户的机构代码
					vcApprove.setCheckOprId(userCode);//审批人：当前登录用户代码
					vcApprove.setCheckStatus("0");//审批状态：0-提交审批；1-审批退回；2-审批通过；
					vcApprove.setCheckTime(new Date());//审批时间
					vcApprove.setCheckExpl("提交");//审批意见
					vcApprove.setCreatedBy(userCode);
					vcApprove.setDateCreated(new Date());
					vcApprove.setUpdatedBy(userCode);
					vcApprove.setDateUpdated(new Date());
					orderManagerService.executeOrderApprove(vcOrderLaunch, vcApprove);
				}else{
					//更新订单
					orderManagerService.updateOrderLaunch(vcOrderLaunch);
				}
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
	 * 查看订单明细
	 * @return
	 * @throws BusinessException
	 */
	public String viewSubOrder() throws BusinessException {
		logger.info("访问/orderManager/viewSubOrder.do 查看订单明细...");
		try{
			if(StringUtils.isEmpty(orderId)){
				throw new BusinessException("订单号不能为空！");
			}
			orderLaunchList = orderManagerService.getSubOrderListByOrderId(Long.valueOf(orderId));
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

	public VcOrderLaunch getVcOrderLaunch() {
		return vcOrderLaunch;
	}

	public void setVcOrderLaunch(VcOrderLaunch vcOrderLaunch) {
		this.vcOrderLaunch = vcOrderLaunch;
	}

	public OrderLaunchVo getOrderLaunchVo() {
		return orderLaunchVo;
	}

	public void setOrderLaunchVo(OrderLaunchVo orderLaunchVo) {
		this.orderLaunchVo = orderLaunchVo;
	}

	public List<OrderLaunchDetVo> getOrderLaunchDetVos() {
		return orderLaunchDetVos;
	}

	public void setOrderLaunchDetVos(List<OrderLaunchDetVo> orderLaunchDetVos) {
		this.orderLaunchDetVos = orderLaunchDetVos;
	}
	
	public List getOrderLaunchList() {
		return orderLaunchList;
	}

	public void setOrderLaunchList(List orderLaunchList) {
		this.orderLaunchList = orderLaunchList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
	
	public List<VcOrderLaunchDet> getVcOrderLaunchDet() {
		return vcOrderLaunchDet;
	}

	public void setVcOrderLaunchDet(List<VcOrderLaunchDet> vcOrderLaunchDet) {
		this.vcOrderLaunchDet = vcOrderLaunchDet;
	}
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
