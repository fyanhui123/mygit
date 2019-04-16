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

/**
 * 订单申请Action
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
public class OrderLaunchAction extends TFAction{
	
	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**征订管理Service类注入*/
	private OrderManagerService orderManagerService;
	/**查询结果集列表*/
	private List orderLaunchList;
	/**订单基本信息*/
	private VcOrderLaunch vcOrderLaunch;
	/**订单详细信息*/
	private List<VcOrderLaunchDet> vcOrderLaunchDet;
	/**历史审批信息*/
	private List<VcApprove> vcApprove;
	/**基本vo对象*/
	private OrderLaunchVo orderLaunchVo;
	/**订单详细信息VO*/
	private List<OrderLaunchDetVo> orderLaunchDetVos;
	/**编辑类型：NEW-新增，EDIT-修改*/
	private String editType;
	/**订单id*/
	private String orderId;
	/**订单号*/
	private String orderCode;
	/**状态*/
	private String flag;
	/**进入订单已审批退回页面标志*/
	private String approvedBackFlag;
	/**申请时间*/
	private Date applyStartDate;
	private Date applyEndDate;
	/**json数据*/
	private String jsonData;
	/**修改的订单id*/
	private String editId;
	/**
	 * 进入订单申请查询页面
	 * @return
	 */
	public String preOrderLaunchQuery(){
		logger.info("访问/orderManager/preOrderLaunchQuery.do进入订单查询页面...");
		Date nowDate = new Date();
		applyStartDate = DateUtils.addMonth(nowDate, -2);
		applyEndDate = nowDate;
		return SUCCESS;
	}
	
	/**
	 * 查询订单申请
	 * @return
	 * @throws BusinessException
	 */
	public String queryOrderLaunch() throws BusinessException{
		logger.info("访问/orderJson/queryOrderLaunch.do查询订单申请...");
		try{
			//从session中取得UserInfo对象
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//接收页面查询条件
			orderLaunchVo = new OrderLaunchVo();
			orderLaunchVo.setOrderCode(orderCode);
			orderLaunchVo.setFlag(flag);
			orderLaunchVo.setApplyStartDate(applyStartDate);
			orderLaunchVo.setApplyEndDate(applyEndDate);

			//分页查询
			Page returnPage = orderManagerService.queryOrderLaunch(orderLaunchVo, comCode, page, rows);
			
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
	 * 初始化修改页面多行录入域
	 * @return
	 * @throws BusinessException
	 */
	public String initOrderLaunchDet() throws BusinessException {
		logger.info("访问/orderJson/initOrderLaunchDet.do初始化修改页面多行录入域...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			String comCode = userInfo.getComCode();
			String id = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(id)){
				vcOrderLaunchDet = orderManagerService.findOrderLaunchDets(Long.valueOf(id), comCode);
			}
		}catch(BusinessException be){
			be.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(be.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 保存订单申请
	 * @return
	 * @throws BusinessException
	 */
	public String saveOrderLaunch() throws BusinessException{
		logger.info("访问/orderManager/saveOrderLaunch.do保存订单申请...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//提交按钮标志
			String submitFlag = getRequest().getParameter("submitFlag");
			//把json数据转换成对象
			vcOrderLaunchDet = TFJSON.parseArray(jsonData, VcOrderLaunchDet.class);
			VcOrderLaunch vcOrderLaunchTemp = null;
			Date nowDate = new Date();
			//修改保存
			if("EDIT".equals(editType))	{
				if(StringUtils.isEmpty(editId)){
					throw new BusinessException("editId为null！");
				}
				vcOrderLaunchTemp = orderManagerService.findOrderLaunchByPK(Long.parseLong(editId));
				if(vcOrderLaunchTemp==null){
					throw new BusinessException("订单不存在！");
				}
				vcOrderLaunchTemp.setFlag("0");//修改后状态为‘新建’
				vcOrderLaunchTemp.setModifyUserCode(userCode);
				vcOrderLaunchTemp.setModifyDate(nowDate);
				vcOrderLaunchTemp.setUpdatedBy(userCode);
				vcOrderLaunchTemp.setDateUpdated(nowDate);
				
				//记录修改时间
				for(VcOrderLaunchDet det : vcOrderLaunchDet){
					det.setCreatedBy(userCode);
					det.setDateCreated(nowDate);
					det.setUpdatedBy(userCode);
					det.setDateUpdated(nowDate);
				}
				//修改+提交
				if("submit".equals(submitFlag)){
					vcOrderLaunchTemp.setFlag("9");//待审核
					
					//插入审批记录
					VcApprove vcApprove = new VcApprove();
					vcApprove.setApplyId(vcOrderLaunchTemp.getId());
					vcApprove.setApplyType("O");//申请类型：O-征订
					vcApprove.setCheckOrgId(comCode);//审批机构：当前登录用户的机构代码
					vcApprove.setCheckOprId(userCode);//审批人：当前登录用户代码
					vcApprove.setCheckStatus("0");//审批状态：0-提交审批；1-审批退回；2-审批通过；
					vcApprove.setCheckTime(nowDate);//审批时间
					vcApprove.setCheckExpl("提交");//审批意见
					vcApprove.setCreatedBy(userCode);
					vcApprove.setDateCreated(nowDate);
					vcApprove.setUpdatedBy(userCode);
					vcApprove.setDateUpdated(nowDate);
					//调用修改+提交方法
					orderManagerService.updateAndSubmitOrderLaunch(vcOrderLaunchTemp, vcOrderLaunchDet, vcApprove);
				}else{
					//调用修改方法
					orderManagerService.updateOrderLaunch(vcOrderLaunchTemp,vcOrderLaunchDet);
				}
			}
			//新增保存
			else if("NEW".equals(editType)){
				//组织订单对象
				vcOrderLaunchTemp = new VcOrderLaunch();
				vcOrderLaunchTemp.setOrderType("1");//订单类型：1-原始订单；2-汇总订单；
				vcOrderLaunchTemp.setOrgCode(comCode);
				vcOrderLaunchTemp.setFlag("0");//标志位：0-新建；1-审批通过；2-审批退回；9-待审批；
				vcOrderLaunchTemp.setValidStatus("1");//有效状态
				vcOrderLaunchTemp.setGatherFlag("0");//汇总标志：0-未汇总，1-已汇总
				vcOrderLaunchTemp.setModifyUserCode(userCode);
				vcOrderLaunchTemp.setModifyDate(nowDate);
				vcOrderLaunchTemp.setCreatedBy(userCode);
				vcOrderLaunchTemp.setDateCreated(nowDate);
				vcOrderLaunchTemp.setUpdatedBy(userCode);
				vcOrderLaunchTemp.setDateUpdated(nowDate);
				//循环处理List<VcOrderLaunchDet>
				//1、给vcOrderLaunchDetList设置vcOrderLaunch对象，防止不能级联保存vcOrderLaunchDet的情况
				//2、设置订单详情表中的申印数量,对于订单发起，申印数量=订单申请数量
				if(vcOrderLaunchDet!=null && vcOrderLaunchDet.size()>0){
					for(VcOrderLaunchDet det : vcOrderLaunchDet){
						det.setVcOrderLaunch(vcOrderLaunchTemp);
						det.setApplyPrintNum(det.getOrderCount());
						
						det.setCreatedBy(userCode);
						det.setDateCreated(nowDate);
						det.setUpdatedBy(userCode);
						det.setDateUpdated(nowDate);
					}
					vcOrderLaunchTemp.setVcOrderLaunchDetList(vcOrderLaunchDet);
				}
				//保存+提交
				if("submit".equals(submitFlag)){
					vcOrderLaunchTemp.setFlag("9");//待审核
					
					//插入审批记录
					VcApprove vcApprove = new VcApprove();
					vcApprove.setApplyType("O");//申请类型：O-征订
					vcApprove.setCheckOrgId(comCode);//审批机构：当前登录用户的机构代码
					vcApprove.setCheckOprId(userCode);//审批人：当前登录用户代码
					vcApprove.setCheckStatus("0");//审批状态：0-提交审批；1-审批退回；2-审批通过；
					vcApprove.setCheckTime(nowDate);//审批时间
					vcApprove.setCheckExpl("提交");//审批意见
					vcApprove.setCreatedBy(userCode);
					vcApprove.setDateCreated(nowDate);
					vcApprove.setUpdatedBy(userCode);
					vcApprove.setDateUpdated(nowDate);
					//调用保存+提交方法
					orderManagerService.saveAndSubmitOrderLaunch(vcOrderLaunchTemp, vcApprove);
				}else{
					//调用保存方法
					orderManagerService.saveOrderLaunch(vcOrderLaunchTemp);
				}
			}else{
				throw new BusinessException("editType错误！");
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
	 * 浏览订单申请/浏览汇总订单
	 * @return
	 * @throws BusinessException
	 */
	public String viewOrderLaunch() throws BusinessException {
		logger.info("访问/orderManager/viewOrderLaunch.do浏览订单...");
		try{
			String id = getRequest().getParameter("id");
			//approvedBackFlag = getRequest().getParameter("flag");//进入订单已审批退回页面标志
			if(StringUtils.isEmpty(id)){
				throw new RuntimeException("请选择一条记录！");
			}
			//根据订单号查询订单基本信息和订单详细信息
			VcOrderLaunch vcOrderLaunchTemp = orderManagerService.findOrderLaunchByPK(Long.valueOf(id));
			if(vcOrderLaunchTemp==null){
				throw new BusinessException("订单申请不存在！");
			}
			//组织orderLaunchVo和orderLaunchDetVo对象
			orderLaunchVo = new OrderLaunchVo();
			orderLaunchDetVos = new ArrayList<OrderLaunchDetVo>();
			orderManagerService.generateOrderLaunch(vcOrderLaunchTemp, orderLaunchVo, orderLaunchDetVos);
			
			//查询审批记录
			vcApprove = orderManagerService.queryVcApprove(Long.valueOf(id), "O");//O-征订
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
	 * 校验订单状态
	 * <p>描述：
	 * 1-成功；0-没有选择记录；2-记录不存在；
	 * 3-订单已提交；4-订单已审批通过;5-采购中
	 * </p>
	 * @return
	 * @throws BusinessException
	 */
	public String checkOrderLaunch() throws BusinessException {
		try{
			String status = "1";
			//获取参数
			String id = getRequest().getParameter("id");
			if(StringUtils.isEmpty(id)){
				status="0";//没有选择记录
				getResponse().getWriter().write(status);
				return NONE;
			}
			String[] idArr = id.split(",");
			int idLen = idArr.length;
			//根据ID查找
			List<VcOrderLaunch> vcOrderLaunchListTemp = orderManagerService.getOrderLaunchList(idArr);
			//有记录不存在
			if(vcOrderLaunchListTemp==null || vcOrderLaunchListTemp.size()<idLen){
				status="2";//部分记录不存在
				getResponse().getWriter().write(status);
				return NONE;
			}
			//取得订单标志位：0-新建；1-审批通过；2-审批退回；9-待审批；
			for(VcOrderLaunch orderLaunchTemp : vcOrderLaunchListTemp){
				String flag = orderLaunchTemp.getFlag();
				if("1".equals(flag)){
					status="4";//订单已审批通过
					break;
				}else if("9".equals(flag)){
					status="3";//订单已提交审批
					break;
				}else if("3".equals(flag)){
					status="5";//采购中
					break;
				}
			}
			getResponse().getWriter().write(status);
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return NONE;
	}
	
	/**
	 * 删除订单
	 * @return
	 * @throws BusinessException
	 */
	public String deleteOrderLaunch() throws BusinessException {
		logger.info("访问/orderManager/deleteOrderLaunch.do删除订单...");
		try{
			//获取参数
			String id = getRequest().getParameter("id");
			String[] ids = id.split(",");
			for(String orderId : ids){
				orderManagerService.deleteOrderLaunchByPK(Long.valueOf(orderId));
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
	 * 提交审批
	 * @return
	 * @throws BusinessException
	 */
	public String submitOrderLaunch() throws BusinessException {
		logger.info("访问/orderManager/submitOrderLaunch.do订单提交审批...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取参数
			String id = getRequest().getParameter("id");
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			String[] ids = id.split(",");
			if(ids==null || ids.length<1){
				throw new BusinessException("请选择订单！");
			}
			for(String orderId : ids){
				//1、修改vcOrderLaunch标志位
				VcOrderLaunch vcOrderLaunch = orderManagerService.findOrderLaunchByPK(Long.valueOf(orderId));
				//0-新建；1-审批通过；2-审批退回；9-待审批；
				vcOrderLaunch.setFlag("9");
				vcOrderLaunch.setUpdatedBy(userCode);
				vcOrderLaunch.setDateUpdated(new Date());
				
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
	 * 计算辖内有效库存
	 * @return
	 * @throws BusinessException
	 * @author chy
	 * @date 2013-05-20
	 */
	public String findValidStorageNum() throws BusinessException {
		logger.info("访问/orderJson/findValidStorageNum.do计算辖内有效库存...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode(); 
			//页面传入单证类型
			String versionCode = this.jsonData;
			//查找本级机构及其下级机构有效库存数量
			Long storageNum = orderManagerService.findValidStorageList(versionCode, comCode);
			jsonData = storageNum.toString();
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

	public void setOrderManagerService(OrderManagerService orderManagerService) {
		this.orderManagerService = orderManagerService;
	}

	public List getOrderLaunchList() {
		return orderLaunchList;
	}

	public void setOrderLaunchList(List orderLaunchList) {
		this.orderLaunchList = orderLaunchList;
	}

	public VcOrderLaunch getVcOrderLaunch() {
		return vcOrderLaunch;
	}

	public void setVcOrderLaunch(VcOrderLaunch vcOrderLaunch) {
		this.vcOrderLaunch = vcOrderLaunch;
	}

	public List<VcOrderLaunchDet> getVcOrderLaunchDet() {
		return vcOrderLaunchDet;
	}

	public void setVcOrderLaunchDet(List<VcOrderLaunchDet> vcOrderLaunchDet) {
		this.vcOrderLaunchDet = vcOrderLaunchDet;
	}

	public List<VcApprove> getVcApprove() {
		return vcApprove;
	}

	public void setVcApprove(List<VcApprove> vcApprove) {
		this.vcApprove = vcApprove;
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

	public String getEditType() {
		return editType;
	}

	public void setEditType(String editType) {
		this.editType = editType;
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

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

    public String getApprovedBackFlag() {
        return approvedBackFlag;
    }

    public void setApprovedBackFlag(String approvedBackFlag) {
        this.approvedBackFlag = approvedBackFlag;
    }
	
	
}
