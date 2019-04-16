package com.tapi.tcs.vc.order.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.order.vo.OrderLaunchDetVo;
import com.tapi.tcs.vc.order.vo.OrderLaunchVo;
import com.tapi.tcs.vc.order.vo.VcPurchaseVo;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcOrderLaunch;
import com.tapi.tcs.vc.schema.model.VcOrderLaunchDet;
import com.tapi.tcs.vc.schema.model.VcPurchase;

/**
 * 征订管理Service实现类
 * <p>
 * Date 2013-03-15
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
public interface OrderManagerService {
	/**确认收货之后直接保存到库存表*/
	public void saveVcStorage(VcPurchase vcPurchase, UserInfo userInfo) throws BusinessException;
	/**订单申请查询*/
	public Page queryOrderLaunch(OrderLaunchVo orderLaunchVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException;
	/**订单申请保存*/
	public void saveOrderLaunch(VcOrderLaunch vcOrderLaunch) throws BusinessException;
	/**保存汇总订单*/
	public void saveOrderGath(VcOrderLaunch vcOrderLaunch, List<VcOrderLaunch> subOrderList) throws BusinessException;
	/**订单申请修改*/
	public void updateOrderLaunch(VcOrderLaunch vcOrderLaunch,List<VcOrderLaunchDet> vcOrderLaunchDetList) throws BusinessException;
	/**修改订单申请*/
	public void updateOrderLaunch(VcOrderLaunch vcOrderLaunch) throws Exception;
	/**根据订单id查找订单详细信息*/
	public List<VcOrderLaunchDet> findOrderLaunchDets(Long orderId, String comCode) throws BusinessException;
	/**根据主键查询订单申请：包括基本信息和详细信息 */
	public VcOrderLaunch findOrderLaunchByPK(Long id) throws BusinessException;
	/**重新组织订单基本信息和订单详细信息VO对象*/
	public void generateOrderLaunch(VcOrderLaunch vcOrderLaunchTemp, OrderLaunchVo orderLaunchVo, List<OrderLaunchDetVo> orderLaunchDetVos) throws BusinessException;
	/**查询审批记录*/
	public List<VcApprove> queryVcApprove(Long applyId, String appType) throws BusinessException ;
	/**批量查询订单申请*/
	public List<VcOrderLaunch> getOrderLaunchList(String[] id) throws BusinessException;
	/**根据主键删除*/
	public void deleteOrderLaunchByPK(Long id) throws BusinessException;
	/**查询审批列表*/
	public Page queryOrderApprove(OrderLaunchVo orderLaunchVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException;
	/**订单审批*/
	public void executeOrderApprove(VcOrderLaunch vcOrderLaunch,
			VcApprove vcApprove) throws BusinessException;
	/**查询汇总订单*/
	public Page queryOrderGather(OrderLaunchVo orderLaunchVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException;
	/**新增订单汇总-查询可汇总的订单*/
	public List queryOrderGatherInput(OrderLaunchVo orderLaunchVoTemp, String comCode) throws BusinessException;
	/**按单证类型汇总订单*/
	public List<OrderLaunchDetVo> orderGather(String id, String comCode) throws BusinessException;
	/**根据订单ID查找子订单列表*/
	public List getSubOrderListByOrderId(Long id) throws BusinessException;
	/**订单申请：保存+提交*/
	public void saveAndSubmitOrderLaunch(VcOrderLaunch vcOrderLaunch, VcApprove vcApprove) throws BusinessException;
	/**订单申请：修改+提交*/
	public void updateAndSubmitOrderLaunch(VcOrderLaunch vcOrderLaunch,List<VcOrderLaunchDet> vcOrderLaunchDetList,VcApprove vcApprove) throws BusinessException;
	/**汇总订单：保存+提交*/
	public void saveAndSubmitOrderGather(VcOrderLaunch vcOrderLaunch, List<VcOrderLaunch> subOrderList, VcApprove vcApprove) throws BusinessException;
	/**根据条件查询采购单*/
	public Page queryPurchase(VcPurchaseVo vcPurchaseVoTemp,String userCode,
			String comCode,int pageNo,int pageSize) throws BusinessException;
	/**新增采购单-查询可生成采购单的汇总订单*/
	public Page queryPurchaseGenInput(OrderLaunchVo orderLaunchVoTemp, String comCode, int pageNo, int pageSize) throws BusinessException;
	/**保存采购单*/
	public List<VcPurchase> executePurchaseGen(String id,String userCode,String comCode) throws BusinessException;
	/**显示采购单*/
	public List generatePurchase(List<VcPurchase> list) throws BusinessException;
	/**采购下单查询*/
	public Page queryPurchaseOrder(VcPurchaseVo vcPurchaseVoTemp,String userCode,
			String comCode,int pageNo,int pageSize) throws BusinessException;
	/**采购下单*/
	public void executePurchaseOrder(List<VcPurchaseVo> list, String userCode) throws BusinessException;
	
	/**收货确认查询*/
	public Page queryPurchaseReceipt(VcPurchaseVo vcPurchaseVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException;
	
	/**根据主键查找采购单*/
	public VcPurchase findPurchaseById(Long id) throws BusinessException;
	
	/**校验库存状态*/
	public boolean checkStorage(VcPurchase vcPurchase) throws BusinessException;
	
	/**确认收货*/
	/*public void executeReceiptConfirm(String[] ids, String comCode, String userCode) throws Exception;*/
	public void executeReceiptConfirm(List<VcPurchaseVo> vcPurchaseVoList, String userCode) throws BusinessException;
	
	/**取消收货*/
	public void executeReceiptCancel(String[] ids, String userCode) throws BusinessException;
	
	/**
	 * 采购单提交审批
	 * @param ids
	 * @param userCode
	 * @throws Exception
	 */
	public void executeReceivedSubmit(String[] ids, String comCode, String userCode) throws BusinessException;
	
	/** 查询当前机构及其下级机构的有效库存*/
	public Long findValidStorageList(String versionCode, String orgCode) throws BusinessException;
	
	/**
	 * 查询收货确认审批列表
	 * @param vcPurchaseVoTemp
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page queryPurchaseApprove(VcPurchaseVo vcPurchaseVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException;
	
	/**
	 * 根据id查找采购单并转换成vo
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @author chy
	 */
	public VcPurchaseVo findPurchaseApprove(Long id) throws BusinessException;
	
	/**
	 * 执行收货确认审批/退回
	 * @param vcPurchase
	 * @param vcApprove
	 * @param checkStatus 审批动作：1-审批退回；2-审批通过
	 * @throws BusinessException
	 */
	public void executePurchaseApp(VcPurchase vcPurchase, VcApprove vcApprove, String checkStatus) throws BusinessException;
	
	
    /**
     * 采购下单结果查询
     * 
     * @param vcPurchaseVoTemp
     * @param userCode
     * @param comCode
     * @return
     * @throws BusinessException
     *@author whj
     *@since Mar 11, 2014
     */
    public List<VcPurchaseVo> queryPurchaseOrderList(VcPurchaseVo vcPurchaseVoTemp, String userCode, String comCode)
            throws BusinessException;
}
