package com.tapi.tcs.vc.order.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.order.vo.OrderLaunchVo;
import com.tapi.tcs.vc.order.vo.VcPurchaseVo;
import com.tapi.tcs.vc.schema.model.VcOrderLaunch;
import com.tapi.tcs.vc.schema.model.VcOrderLaunchDet;
import com.tapi.tcs.vc.schema.model.VcPurchase;
import com.tapi.tcs.vc.schema.model.VcStorage;

/**
 * 征订管理DAO实现类
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
public interface OrderManagerDao extends GenericDao<VcOrderLaunch>{

	/** 保存订单申请 */
	public void saveOrderLaunch(VcOrderLaunch vcOrderLaunch) throws DaoException;
	
	/**删除det表数据*/
	public void deleteVcOrderLaunchDets(List<VcOrderLaunchDet> vcOrderLaunchDetList) throws DaoException;
	
	/**查询订单申请*/
	public Page queryOrderLaunch(OrderLaunchVo orderLuanchVoTemp,String comCode,int pageNo,int pageSize) throws DaoException;
	
	/**根据主键查找*/
	public VcOrderLaunch findOrderLaunchByPK(Long id) throws DaoException;
	
	/**根据订单id查找订单详细信息*/
	public List<VcOrderLaunchDet> findOrderLaunchDets(Long orderId) throws DaoException;
	
	/**修改订单申请*/
	public void updateOrderLaunch(VcOrderLaunch vcOrderLaunch) throws DaoException;
	
	/**删除订单申请*/
	public void deleteOrderLaunch(Long id) throws DaoException;
	
	/**批量查询订单*/
	public List<VcOrderLaunch> getOrderLaunchList(Long[] id) throws DaoException;
	
	/**查询审批列表*/
	public Page queryOrderApprove(OrderLaunchVo orderLaunchVoTemp, String comCode, int pageNo, int pageSize) throws DaoException;
	
	/**查询汇总订单*/
	public Page queryOrderGather(OrderLaunchVo orderLaunchVoTemp, String comCode, int pageNo, int pageSize) throws DaoException;
	
	/**根据订单ID查找关联的子订单ID*/
	public List<Long> getSubOrderIds(Long id) throws DaoException;
	
	/**查找订单列表*/
	public List<VcOrderLaunch> getSubOrderListByOrderId(List<Long> idList) throws DaoException;
	
	/**新增订单汇总-查询可汇总的订单*/
	public List<VcOrderLaunch> queryOrderGatherInput(OrderLaunchVo orderLaunchVoTemp, List<String> childOrgList) throws DaoException;
	
	/**按单证类型汇总订单*/
	public List<VcOrderLaunchDet> orderGather(String id) throws DaoException;
	
	/**根据条件查询采购单*/
	public Page queryPurchase(VcPurchaseVo vcPurchaseVoTemp,String userCode,
			String comCode,int pageNo,int pageSize) throws DaoException;
	
	/**新增采购单-查询可生成采购单的汇总订单*/
	public Page queryPurchaseGenInput(OrderLaunchVo orderLaunchVoTemp,List<String> childOrgList, int pageNo, int pageSize) throws DaoException;
	
	/**按单证类型、申请机构进行分组查询*/
	public List<Object[]> findOrderForPurchase(String id) throws DaoException;
	
	/**保存采购单*/
	public void savePurchase(List<VcPurchase> vcPurchaseList) throws DaoException;
	
	/**采购下单查询*/
	public Page queryPurchaseOrder(VcPurchaseVo vcPurchaseVoTemp,Object[] versionCodes,String userCode,
			String comCode,int pageNo,int pageSize) throws DaoException;
	
	/**采购下单*/
	public void executePurchaseOrder(List<VcPurchaseVo> list, String userCode) throws DaoException;
	
	/**收货确认查询*/
	public Page queryPurchaseReceipt(VcPurchaseVo vcPurchaseVoTemp,String comCode,int pageNo,int pageSize) throws DaoException;
	
	/**根据主键查找采购单*/
	public VcPurchase findPurchaseById(Long id) throws DaoException;
	
	/** 更新采购单*/
	public void updateVcPurchase(VcPurchase vcPurchase) throws DaoException;
	
	/**查询当前机构及其下级机构的有效库存 */
	public List<VcStorage> findValidStorageList(String versionCode, String orgCode) throws DaoException;
	
	
	/**
	 * 根据条件查找订单详细信息
	 * @param vcOrderLaunchDet
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * since 2013-4-15上午09:32:03
	 */
	public List<VcOrderLaunchDet> queryOrderLaunchDetList(VcOrderLaunchDet vcOrderLaunchDet) throws Exception;
	
	/**
	 * 查询收货确认审批列表
	 * @param vcPurchaseVoTemp
	 * @param childOrgList
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page queryPurchaseApprove(VcPurchaseVo vcPurchaseVoTemp,List<String> childOrgList,int pageNo,int pageSize) throws DaoException;
	
	
    /**
     * 采购下单查询
     * 
     * @param vcPurchaseVoTemp
     * @param userCode
     * @param comCode
     * @return List Object[VcPurchase , VcDocVersionInfo , VcPrintery ,PubCompany , VcPrintDocVersion ]
     * @throws DaoException
     *@author whj
     *@since Mar 11, 2014
     */
    public List<Object[]> queryPurchaseOrderList(VcPurchaseVo vcPurchaseVoTemp, String userCode, String comCode)
            throws DaoException;
}
