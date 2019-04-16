package com.tapi.tcs.vc.order.web;

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
public class PurchaseRecieptAction extends TFAction{

	private static final long serialVersionUID = 1;
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**征订管理Service类注入*/
	private OrderManagerService orderManagerService;
	/**采购单VO*/
	private VcPurchaseVo vcPurchaseVo;
	/**采购单列表*/
	private List vcPurchaseList;
	/**状态*/
	private String flag;
	/**申请时间*/
	private Date applyStartDate;
	private Date applyEndDate;
	/**校验信息*/
	private String[] message;
	/**json数据*/
	private String jsonData;

	/**
	 * 进入收货确认页面
	 * @return
	 * @throws Exception
	 */
	public String prePurchaseReceiptQuery() {
		logger.info("访问/orderManager/prePurchaseReceiptQuery.do进入收货确认页面...");
		Date nowDate = new Date();
		applyStartDate = DateUtils.addMonth(nowDate, -2);
		applyEndDate = nowDate;
		return SUCCESS;
	}
	
	/**
	 * 收货确认查询方法
	 * @return
	 * @throws BusinessException
	 */
	public String queryPurchaseReceipt() throws BusinessException {
		logger.info("访问/orderJson/queryPurchaseReceipt.do收货确认查询方法...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			vcPurchaseVo = new VcPurchaseVo();
			//拼接查询条件
			vcPurchaseVo.setApplyStartDate(applyStartDate);
			vcPurchaseVo.setApplyEndDate(applyEndDate);
			vcPurchaseVo.setFlag(flag);
			
			Page returnPage = orderManagerService.queryPurchaseReceipt(vcPurchaseVo, comCode, page, rows);
			
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
	 * 校验采购单状态
	 * @return
	 * @throws BusinessException
	 */
	public String checkPurchase() throws BusinessException {
		logger.info("访问/orderJson/checkPurchase.do校验采购单状态...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//页面选中的ID
			String id = getRequest().getParameter("id");
			//校验标志：receiptConfirm-确认收货；receiptCancel-取消收货；submitApprove-提交审批
			String checkflag = getRequest().getParameter("checkflag");
			if(StringUtils.isEmpty(id)){
				//id为空
				message = new String[2];
				message[0]="0";
				message[1]="请选择采购单！";
				return SUCCESS;
			}
			//错误提示信息
			String msg = "";
			//采购单不存在
			String message1 = "";
			//采购单状态是否正确：
			//1、确认收货时，采购单状态需为1-已下单、3-取消收货或5-审批退回
			String message2 = "";
			//2、取消收货时，采购单状态需为审批通过；且在库存表的状态必须为S1-印刷入库
			String message3 = "";
			String message4 = "";
			//3、提交审批时，采购单状态需为'收货确认'
			String message5 = "";
			//4、审批，采购单状态需为'待审批'
			String message6 = "";
			
			//循环校验采购单
			String[] ids = id.split(",");
			int len = ids.length;
			for(int i=0;i<len;i++){
				VcPurchase vcPurchase = orderManagerService.findPurchaseById(Long.valueOf(ids[i]));
				//记录不存在的采购单号
				if(vcPurchase==null){
					message1 += ","+ids[i];
					continue;
				}
				//记录状态不正确的采购单号
				if("receiptConfirm".equals(checkflag)){
					//确认收货，采购单状态必须为1-已下单、3-取消收货、5-审批退回
					if(!"1".equals(vcPurchase.getFlag()) && !"3".equals(vcPurchase.getFlag()) && !"5".equals(vcPurchase.getFlag())){
						message2 += ","+vcPurchase.getPurchaseCode();
					}
				}else if("receiptCancel".equals(checkflag)){
					//采购单状态必须为6-审批通过
					if(!"2".equals(vcPurchase.getFlag())){
						message3 += ","+vcPurchase.getPurchaseCode();
					}else{
						//进一步判断库存表的状态是否为S1-印刷入库
						if(!orderManagerService.checkStorage(vcPurchase)){
							message4 += ","+vcPurchase.getPurchaseCode();
						}
					}
				}else if("submitApprove".equals(checkflag)){
					//提交审批时，采购单状态需为'收货确认'
					//if(!"2".equals(vcPurchase.getFlag()) && !"5".equals(vcPurchase.getFlag())){
					if(!"2".equals(vcPurchase.getFlag())){
						message5 += ","+vcPurchase.getPurchaseCode();
					}
				}else if("purchaseAppEdit".equals(checkflag)){
					//审批，采购单状态需为'待审批'
					if(!"4".equals(vcPurchase.getFlag())){
						message6 += ","+vcPurchase.getPurchaseCode();
					}
				}
			}
			//采购单不存在的提示信息
			if(StringUtils.isNotEmpty(message1)){
				msg += "采购单【"+message1.substring(1)+"】不存在！";
			}
			//采购单状态不正确的提示信息
			if(StringUtils.isNotEmpty(message2)){
				msg += "采购单【"+message2.substring(1)+"】不是'已下单'、'取消收货'状态，不能确认收货！";
			}
			if(StringUtils.isNotEmpty(message3)){
				msg += "采购单【"+message3.substring(1)+"】不是'确认收货'状态，不能取消收货！";
			}
			//采购单在库存表的状态不为S1-印刷入库
			if(StringUtils.isNotEmpty(message4)){
				msg += "采购单【"+message4.substring(1)+"】对应的单证流水号已下发，不能取消收货！";
			}
			//采购单状态不正确的提示信息
			if(StringUtils.isNotEmpty(message5)){
				msg += "采购单【"+message5.substring(1)+"】不是'收货确认'状态，不能提交审批！";
			}
			//采购单状态不正确的提示信息
			if(StringUtils.isNotEmpty(message6)){
				msg += "采购单【"+message6.substring(1)+"】不是'待审批'状态，不能进行审批！";
			}
			//返回提示信息
			if(StringUtils.isNotBlank(msg)){
				message = new String[2];
				message[0]="0";
				message[1]=msg;
			}else{
				message = new String[2];
				message[0]="1";
				message[1]="校验成功！";
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
	 * 确认收货方法
	 * @return
	 * @throws BusinessException
	 */
	public String executeReceiptConfirm() throws BusinessException {
		logger.info("访问/orderManager/executeReceiptConfirm.do确认收货方法...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//确认收货的方法
			List<VcPurchaseVo> vcPurchaseVoList = TFJSON.parseArray(jsonData, VcPurchaseVo.class);
			orderManagerService.executeReceiptConfirm(vcPurchaseVoList, userCode);
			try {
				for(VcPurchaseVo vcPurchaseVo : vcPurchaseVoList){
					VcPurchase vcPurchaseTmp = orderManagerService.findPurchaseById(Long.valueOf(vcPurchaseVo.getPurchaseId()));
					orderManagerService.saveVcStorage(vcPurchaseTmp, userInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
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
	 * 取消收货方法
	 * @return
	 * @throws BusinessException
	 */
	public String executeReceiptCancel() throws BusinessException {
		logger.info("访问/orderManager/executeReceiptCancel.do取消收货方法...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//页面选中的ID
			String id = getRequest().getParameter("id");
			if(StringUtils.isEmpty(id)){
				throw new BusinessException("请选择记录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			
			String[] ids = id.split(",");
			orderManagerService.executeReceiptCancel(ids, userCode);
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
	public String receivedSubmit() throws BusinessException {
		logger.info("访问/orderManager/receivedSubmit.do提交审批...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			//页面选中的ID
			String id = getRequest().getParameter("id");
			if(StringUtils.isEmpty(id)){
				throw new BusinessException("请选择一条记录！");
			}
			//获取当前用户的用户代码
			String userCode = userInfo.getUserCode();
			//获取当前用户的机构代码
			String comCode = userInfo.getComCode();
			
			String[] ids = id.split(",");
			orderManagerService.executeReceivedSubmit(ids, comCode, userCode);
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

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	public void setOrderManagerService(OrderManagerService orderManagerService) {
		this.orderManagerService = orderManagerService;
	}
	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
