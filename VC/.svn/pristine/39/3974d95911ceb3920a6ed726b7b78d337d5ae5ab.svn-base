package com.tapi.tcs.vc.order.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.order.service.OrderManagerService;
import com.tapi.tcs.vc.order.vo.OrderLaunchVo;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcOrderLaunch;

/**
 * 订单审批Action
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
public class OrderApproveAction extends TFAction{

	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**征订管理Service类注入*/
	private OrderManagerService orderManagerService;
	/**订单基本信息vo*/
	private OrderLaunchVo orderLaunchVo;
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
	/**审批意见*/
	private String checkExpl;
	/**当前机构*/
	private String curComCode;

	/**
	 * 进入订单审批页面
	 * @return
	 */
	public String preOrderApproveQuery()throws BusinessException {
		logger.info("访问/orderManager/preOrderApproveQuery.do进入订单审批页面...");
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
        if(userInfo==null){
            throw new BusinessException("登录超时，请重新登录！");
        }
        //获取当前用户的机构代码
        curComCode = userInfo.getComCode();
		Date nowDate = new Date();
		applyStartDate = DateUtils.addMonth(nowDate, -2);
		applyEndDate = nowDate;
		return SUCCESS;
	}
	
	/**
	 * 查询审批列表
	 * @return
	 * @throws BusinessException
	 */
	public String queryOrderApprove() throws BusinessException{
		logger.info("访问/orderJson/queryOrderApprove.do查询订单审批列表...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			//查询Page对象
			//if(orderLaunchVo==null){
				orderLaunchVo = new OrderLaunchVo();
				//orderLaunchVo.setOrderId(orderId);
				orderLaunchVo.setOrderCode(orderCode);
				orderLaunchVo.setApplyStartDate(applyStartDate);
				orderLaunchVo.setApplyEndDate(applyEndDate);
			//}
			
			Page returnPage = orderManagerService.queryOrderApprove(orderLaunchVo, comCode, page, rows);
			
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
	 * 审批通过/退回
	 * @return
	 * @throws BusinessException
	 */
	public String approvePass() throws BusinessException {
		logger.info("访问/orderManager/approvePass.do审批通过/退回...");
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
			//从页面获取订单号
			String orderId = orderLaunchVo.getOrderId();
			//1、修改VcOrderLaunch表
			VcOrderLaunch vcOrderLaunchTemp = orderManagerService.findOrderLaunchByPK(Long.valueOf(orderId));
			//标志位：0-新建；1-审批通过；2-审批退回；9-待审批；
            if ("9".equals(vcOrderLaunchTemp.getFlag())) {
                if ("1".equals(checkStatus)) {
                    vcOrderLaunchTemp.setFlag("2");
                } else if ("2".equals(checkStatus)) {
                    vcOrderLaunchTemp.setFlag("1");
                }
            } else if("1".equals(vcOrderLaunchTemp.getFlag())){
                if ("1".equals(checkStatus)) {
                    vcOrderLaunchTemp.setFlag("2");
                }
            } else {
                throw new BusinessException("订单当前状态不为1【审批通过】或9【待审批】，操作失败！");
            }
			vcOrderLaunchTemp.setUpdatedBy(userCode);
			vcOrderLaunchTemp.setDateUpdated(new Date());
			
			//2、保存Approve表
			//组织VcApprove对象
			VcApprove vcApproveTmp = new VcApprove();
			vcApproveTmp.setApplyId(Long.valueOf(orderId));
			vcApproveTmp.setApplyType("O");//申请类型：O-征订
			vcApproveTmp.setCheckOrgId(comCode);
			vcApproveTmp.setCheckOprId(userCode);
			vcApproveTmp.setCheckStatus(checkStatus);//审批状态：0-提交审批；1-审批退回；2-审批通过；
			vcApproveTmp.setCheckTime(new Date());
			vcApproveTmp.setCheckExpl(checkExpl);
			vcApproveTmp.setCreatedBy(userCode);
			vcApproveTmp.setDateCreated(new Date());
			vcApproveTmp.setUpdatedBy(userCode);
			vcApproveTmp.setDateUpdated(new Date());
			//3、执行操作
			orderManagerService.executeOrderApprove(vcOrderLaunchTemp, vcApproveTmp);
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

	public String getCheckExpl() {
		return checkExpl;
	}

	public void setCheckExpl(String checkExpl) {
		this.checkExpl = checkExpl;
	}

	public String getCurComCode() {
        return curComCode;
    }

    public void setCurComCode(String curComCode) {
        this.curComCode = curComCode;
    }

    public void setOrderManagerService(OrderManagerService orderManagerService) {
		this.orderManagerService = orderManagerService;
	}
}
