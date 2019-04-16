package com.tapi.tcs.vc.order.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.order.dao.OrderManagerDao;
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
public class OrderManagerDaoImpl extends GenericDaoHibernate<VcOrderLaunch> implements OrderManagerDao {

	/**
	 * 删除订单申请
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	@Override
	public void deleteOrderLaunch(Long id) throws DaoException {
		// TODO Auto-generated method stub
		try{
			this.deleteByPK(VcOrderLaunch.class, id);
		}catch(Exception e){
			throw new DaoException("删除订单数据失败！", e);
		}
	}

	/**
	 * 根据主键查找订单申请
	 * @param id
	 * @return vcOrderLaunch
	 * @throws Exception
	 */
	@Override
	public VcOrderLaunch findOrderLaunchByPK(Long id) throws DaoException {
		VcOrderLaunch vcOrderLaunch = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id", id);
			vcOrderLaunch =(VcOrderLaunch)this.findUnique(VcOrderLaunch.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询订单失败！", e);
		}
		return vcOrderLaunch;
	}
	
	/**
	 * 根据条件查询订单申请
	 * @param orderLaunchVoTemp
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	@Override
	public Page queryOrderLaunch(OrderLaunchVo orderLaunchVoTemp,String comCode,int pageNo,int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//拼接订单号查询条件
			if(StringUtils.isNotEmpty(orderLaunchVoTemp.getOrderCode())){
				queryRule.addEqual("orderCode", orderLaunchVoTemp.getOrderCode());
			}
			//拼接申请时间查询条件
			if(orderLaunchVoTemp.getApplyStartDate()!=null){
				queryRule.addGreaterEqual("dateCreated", orderLaunchVoTemp.getApplyStartDate());
			}
			if(orderLaunchVoTemp.getApplyEndDate()!=null){
				queryRule.addLessThan("dateCreated", DateUtils.addDay(orderLaunchVoTemp.getApplyEndDate(), +1));
			}
			//拼接状态查询条件
			if(StringUtils.isNotEmpty(orderLaunchVoTemp.getFlag()) && !"all".equals(orderLaunchVoTemp.getFlag())){
				queryRule.addEqual("flag", orderLaunchVoTemp.getFlag());
			}
			//订单申请查询界面，只能查出原始订单
			queryRule.addEqual("orderType", "1");//orderType：1-原始订单；2-汇总订单
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			
			//权限控制：只能查询同一归属机构的数据
			queryRule.addEqual("orgCode", comCode);
			
			queryRule.addDescOrder("id");
			
			page = this.find(VcOrderLaunch.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		
		return page;
	}

	/**
	 * 保存订单申请
	 * @param vcOrderLaunch
	 * @return
	 * @throws DaoException
	 */
	@Override
	public void saveOrderLaunch(VcOrderLaunch vcOrderLaunch) throws DaoException {
		try{
			this.save(vcOrderLaunch);
		}catch(Exception e){
			throw new DaoException("保存订单数据时发生异常！", e);
		}
	}
	
	/**
	 * 根据订单ID删除det表数据
	 * @param vcOrderLaunchDetList
	 * @return
	 * @throws DaoException
	 */
	public void deleteVcOrderLaunchDets( List<VcOrderLaunchDet> vcOrderLaunchDetList) throws DaoException {
		try{
			this.deleteAll(vcOrderLaunchDetList);
		}catch(Exception e){
			throw new DaoException("删除数据失败！", e);
		}
	}
	
	/**
	 * 根据订单id查找订单详细信息
	 */
	public List<VcOrderLaunchDet> findOrderLaunchDets(Long orderId) throws DaoException {
		List<VcOrderLaunchDet> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			String sql = " ID_VC_ORDER_LAUNCH = "+orderId;
			queryRule.addSql(sql);
			list = this.find(VcOrderLaunchDet.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return list;
	}

	/**
	 * 修改订单申请
	 * @param vcOrderLaunch
	 * @return
	 * @throws DaoException
	 */
	@Override
	public void updateOrderLaunch(VcOrderLaunch vcOrderLaunch) throws DaoException {
		try{
			this.update(vcOrderLaunch);
		}catch(Exception e){
			throw new DaoException("更新数据失败！", e);
		}
	}

	/**
	 * 批量查询订单
	 * @param id
	 * @return List<VcOrderLaunch>
	 * @throws DaoException
	 */
	@Override
	public List<VcOrderLaunch> getOrderLaunchList(Long[] id) throws DaoException {
		List<VcOrderLaunch> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addIn("id", id);
			list = this.find(VcOrderLaunch.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询订单数据时发生异常！", e);
		}
		return list;
	}
	
	/**
	 * 查询审批列表
	 * @param orderLaunchVoTemp
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	public Page queryOrderApprove(OrderLaunchVo orderLaunchVoTemp, String comCode, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//拼接订单号查询条件
			if(StringUtils.isNotEmpty(orderLaunchVoTemp.getOrderCode())){
				queryRule.addEqual("orderCode", orderLaunchVoTemp.getOrderCode());
			}
			//拼接申请时间查询条件
			if(orderLaunchVoTemp.getApplyStartDate()!=null){
				queryRule.addGreaterEqual("dateCreated", orderLaunchVoTemp.getApplyStartDate());
			}
			if(orderLaunchVoTemp.getApplyEndDate()!=null){
				queryRule.addLessThan("dateCreated", DateUtils.addDay(orderLaunchVoTemp.getApplyEndDate(), +1));
			}
			//查询待审核的数据
			if ("01".equals(comCode)){
			    //总公司时可以对已审批通过的进行退回，故需要查询转台为1-审批通过的记录
			    //【VC-80征订管理，订单审批，可退回已审批通过但未生成采购单的征订申请单】
			    queryRule.addIn("flag", new Object[]{"1","9"});//标志位：0-新建；1-审批通过；2-审批退回；9-待审批；
			}else{
			    queryRule.addEqual("flag", "9");//标志位：0-新建；1-审批通过；2-审批退回；9-待审批；  
			}
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			
			//权限控制：订单的审批机构=当前用户归属机构
			queryRule.addEqual("checkOrgCode", comCode);
			
			queryRule.addDescOrder("id");
			
			page = this.find(VcOrderLaunch.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询订单数据时发生异常！" ,e);
		}
		return page;
	}
	
	/**
	 * 查询汇总订单
	 * @param orderLaunchVoTemp
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	public Page queryOrderGather(OrderLaunchVo orderLaunchVoTemp, String comCode, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//拼接订单号查询条件
			if(StringUtils.isNotEmpty(orderLaunchVoTemp.getOrderCode())){
				queryRule.addEqual("orderCode", orderLaunchVoTemp.getOrderCode());
			}
			//拼接订单状态查询条件
			if(StringUtils.isNotEmpty(orderLaunchVoTemp.getFlag())){
				queryRule.addEqual("flag", orderLaunchVoTemp.getFlag());
			}
			//拼接申请时间查询条件
			if(orderLaunchVoTemp.getApplyStartDate()!=null){
				queryRule.addGreaterEqual("dateCreated", orderLaunchVoTemp.getApplyStartDate());
			}
			if(orderLaunchVoTemp.getApplyEndDate()!=null){
				queryRule.addLessThan("dateCreated", DateUtils.addDay(orderLaunchVoTemp.getApplyEndDate(), +1));
			}
			//订单汇总查询界面，只能查出汇总订单
			queryRule.addEqual("orderType", "2");//orderType：1-原始订单；2-汇总订单
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			
			//权限控制：只能查询同一归属权限机构的数据
			queryRule.addEqual("orgCode", comCode);
			
			queryRule.addDescOrder("id");
			
			page = this.find(VcOrderLaunch.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询订单数据失败！", e);
		}
		
		return page;
	}
	
	/**
	 * 根据订单ID查找关联的子订单ID
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<Long> getSubOrderIds(Long id) throws DaoException {
		List<Long> idList = new ArrayList<Long>();
		try{
			List<BigDecimal> list = null;
			String sql = "select SUB_DOC_LAUNCH from VC_ORDER_LAUNCH_SUB" +
					" where ID_VC_ORDER_LAUNCH = "+id;
			list = (List<BigDecimal>)this.findBySql(sql, null);
			for(BigDecimal bid : list){
				idList.add(bid.longValue());
			}
		}catch(Exception e){
			throw new DaoException("查询子订单失败！", e);
		}
		return idList;
	}
	
	/**
	 * 查找订单列表
	 * @param idList
	 * @return list
	 * @throws DaoException
	 */
	public List<VcOrderLaunch> getSubOrderListByOrderId(List<Long> idList) throws DaoException {
		List<VcOrderLaunch> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addIn("id", idList);
			list = this.find(VcOrderLaunch.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询订单时发生异常！", e);
		}
		return list;
	}
	
	/**
	 * 新增订单汇总-查询可汇总的订单
	 * @param orderLaunchVoTemp
	 * @param childOrgList
	 * @return vcOrderLaunchs
	 */
	public List<VcOrderLaunch> queryOrderGatherInput(OrderLaunchVo orderLaunchVoTemp, List<String> childOrgList) throws DaoException {
		List<VcOrderLaunch> vcOrderLaunchs = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//拼接订单号查询条件
			if(StringUtils.isNotEmpty(orderLaunchVoTemp.getOrderCode())){
				queryRule.addEqual("orderCode", orderLaunchVoTemp.getOrderCode());
			}
			//拼接申请时间查询条件
			if(orderLaunchVoTemp.getApplyStartDate()!=null){
				queryRule.addGreaterEqual("dateCreated", orderLaunchVoTemp.getApplyStartDate());
			}
			if(orderLaunchVoTemp.getApplyEndDate()!=null){
				queryRule.addLessThan("dateCreated", DateUtils.addDay(orderLaunchVoTemp.getApplyEndDate(), +1));
			}
			//待汇总的订单必须是审核通过的
			queryRule.addEqual("flag", "1");//标志位：0-新建；1-审批通过；2-审批退回；9-待审批；
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			//只能查询未汇总过的订单
			queryRule.addEqual("gatherFlag", "0");
			//权限控制：订单申请机构=当前用户所属机构的下级机构
			queryRule.addIn("orgCode", childOrgList);
			
			queryRule.addDescOrder("id");
			
			vcOrderLaunchs = this.find(VcOrderLaunch.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询订单数据失败！", e);
		}
		
		return vcOrderLaunchs;
	}
	
	/**
	 * 按单证类型汇总订单
	 * @param id
	 * @return List<VcOrderLaunchDet>
	 * @throws DaoException
	 */
	public List<VcOrderLaunchDet> orderGather(String id) throws DaoException {
		List<VcOrderLaunchDet> vcOrderLaunchDetList = null;
		try{
			String sql = "select a.doc_ver_code,sum(a.APPLY_PRINT_NUM) " +
					" from vc_order_launch_det a where a.ID_VC_ORDER_LAUNCH in ("+id+")" +
					" group by a.doc_ver_code";
			List<Object[]> list = this.findBySql(sql,null);
			if(list!=null && list.size()>0){
				vcOrderLaunchDetList = new ArrayList<VcOrderLaunchDet>();
				for(Object[] obj : list){
					VcOrderLaunchDet vcOrderLaunchDet = new VcOrderLaunchDet();
					vcOrderLaunchDet.setVersionCode((String)obj[0]);
					vcOrderLaunchDet.setOrderCount(((BigDecimal)obj[1]).intValue());
					vcOrderLaunchDet.setApplyPrintNum(((BigDecimal)obj[1]).intValue());
					vcOrderLaunchDetList.add(vcOrderLaunchDet);
				}
			}
		}catch(Exception e){
			throw new DaoException(e.getMessage(), e);
		}
		return vcOrderLaunchDetList;
	}
	
	/**
	 * 根据条件查询采购单
	 */
	public Page queryPurchase(VcPurchaseVo vcPurchaseVoTemp,String userCode,
			String comCode,int pageNo,int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//*********拼接查询条件begin*********
			if(StringUtils.isNotEmpty(vcPurchaseVoTemp.getPurchaseCode())){
				queryRule.addEqual("purchaseCode", vcPurchaseVoTemp.getPurchaseCode());
			}
			if(StringUtils.isNotEmpty(vcPurchaseVoTemp.getFlag())){
				queryRule.addEqual("flag", vcPurchaseVoTemp.getFlag());
			}
			//*********拼接查询条件end*********
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			
			queryRule.addDescOrder("id");
			
			page= this.find(VcPurchase.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询采购单记录出错！", e);
		}
		return page;
	}
	
	/**
	 * 新增采购单-查询可生成采购单的汇总订单
	 */
	public Page queryPurchaseGenInput(OrderLaunchVo orderLaunchVoTemp,List<String> childOrgList, int pageNo, int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//拼接申请时间查询条件
			if(orderLaunchVoTemp.getApplyStartDate()!=null){
				queryRule.addGreaterEqual("dateCreated", orderLaunchVoTemp.getApplyStartDate());
			}
			if(orderLaunchVoTemp.getApplyEndDate()!=null){
				queryRule.addLessThan("dateCreated", DateUtils.addDay(orderLaunchVoTemp.getApplyEndDate(), +1));
			}
			//生成采购单的订单必须是审核通过的
			queryRule.addEqual("flag", "1");//标志位：0-新建；1-审批通过；2-审批退回；9-待审批；
			//生成采购单的订单必须是汇总订单？
			//queryRule.addEqual("orderType", "2");//1-原始订单；2-汇总订单；
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			
			//权限控制：下级机构提交的订单。
			queryRule.addIn("orgCode", childOrgList);
			
			queryRule.addDescOrder("id");
			
			page = this.find(VcOrderLaunch.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询订单数据出错！", e);
		}
		return page;
	}
	
	/**
	 * 按单证类型、申请机构进行分组查询
	 */
	public List<Object[]> findOrderForPurchase(String id) throws DaoException {
		List<Object[]> list = null;
		try{
			String sql = "select a.org_code,b.doc_ver_code,sum(b.apply_print_num)  " +
					" from vc_order_launch a,vc_order_launch_det b" +
					" where a.ID_VC_ORDER_LAUNCH = b.ID_VC_ORDER_LAUNCH" +
					" and a.ID_VC_ORDER_LAUNCH in ("+id+")" +
					" group by a.org_code,b.doc_ver_code";
			
			list = this.findBySql(sql,null);
		}catch(Exception e){
			throw new DaoException("查询数据库出错！", e);
		}
		return list;
	}
	
	/**
	 * 保存采购单
	 */
	public void savePurchase(List<VcPurchase> vcPurchaseList) throws DaoException {
		try{
			this.saveAll(vcPurchaseList);
		}catch(Exception e){
			throw new DaoException("保存采购单数据出错！", e);
		}
	}
	
	/**
	 * 采购下单查询
	 */
	public Page queryPurchaseOrder(VcPurchaseVo vcPurchaseVoTemp,Object[] versionCodes,String userCode,
			String comCode,int pageNo,int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//*********拼接查询条件begin*********
			if(StringUtils.isNotEmpty(vcPurchaseVoTemp.getPurchaseCode())){
				queryRule.addEqual("purchaseCode", vcPurchaseVoTemp.getPurchaseCode());
			}
			//*********拼接查询条件end*********
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			//根据印刷厂查询该印刷厂能承印的单证类型
			queryRule.addIn("versionCode", versionCodes);
			//处于新建状态的采购单才能下单
			queryRule.addEqual("flag", "0");
			
			queryRule.addDescOrder("id");
			
			page= this.find(VcPurchase.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询采购单数据出错！", e);
		}
		return page;
	}
	
    /**
     * 采购下单查询
     * @param vcPurchaseVoTemp
     * @param userCode
     * @param comCode
     * @return  List Object[VcPurchase , VcDocVersionInfo , VcPrintery ,PubCompany , VcPrintDocVersion ]
     * @throws DaoException
     *@author whj
     *@since Mar 11, 2014
     */

    public List<Object[]> queryPurchaseOrderList(VcPurchaseVo vcPurchaseVoTemp, String userCode, String comCode)
            throws DaoException {
        List<Object[]> resultList = null;
        try {
            StringBuffer hql = new StringBuffer();
            List<Object> values = new ArrayList<Object>();
            hql.append(" from VcPurchase p, VcDocVersionInfo v, VcPrintery vp,PubCompany pc, VcPrintDocVersion pr ");
            hql.append(" where p.versionCode=v.docVerCode and p.versionCode=pr.docVerCode and v.docVerCode=pr.docVerCode ");
            hql.append(" and p.orgCode=pc.companyCode and pr.vcPrintery=vp  "); // /pr.idVcPrintery=idVcPrintery
            // 采购单号
            if (StringUtils.isNotEmpty(vcPurchaseVoTemp.getPurchaseCode())) {
                hql.append(" and p.purchaseCode= ? ");
                values.add(vcPurchaseVoTemp.getPurchaseCode());
            }
            // 印刷厂id
            if (StringUtils.isNotEmpty(vcPurchaseVoTemp.getPrinteryId())) {
                hql.append(" and pr.idVcPrintery= ? ");
                values.add(Long.valueOf(vcPurchaseVoTemp.getPrinteryId()));
            }

            // 1-有效的数据。0-删除的数据 status
            hql.append(" and p.validStatus= ? ");
            values.add("1");
            // 处于新建状态的采购单才能下单purchase_status
            hql.append(" and p.flag= ? ");
            values.add("0");
            // 激活卡状态
            hql.append(" and v.status= ? ");
            values.add("1");
            // 机构状态
            hql.append(" and pc.validInd= ? ");
            values.add("1");

            hql.append(" order by p.orgCode asc ");
            resultList = this.findByHql(hql.toString(), values.toArray());
        } catch (Exception e) {
            throw new DaoException("采购下单查询出错！", e);
        }
        return resultList;
    }
	
	/**
	 * 采购下单
	 * @param list
	 * @throws Exception
	 */
	public void executePurchaseOrder(List<VcPurchaseVo> list, String userCode) throws DaoException {
		try{
			for(VcPurchaseVo vcPurchaseVo : list){
				//根据主键查找出采购单
				VcPurchase vcPurchase = this.findPurchaseById(vcPurchaseVo.getPurchaseId());
				
				//修改采购单信息
				vcPurchase.setPrinteryId(vcPurchaseVo.getPrinteryId());//印刷厂代码
				vcPurchase.setUnitPrice(vcPurchaseVo.getUnitPrice());//单价
				vcPurchase.setTotalAmount(vcPurchaseVo.getTotalAmount());//总金额
				vcPurchase.setOrderTime(new Date());//下单时间
				vcPurchase.setFlag("1");//0-新建,1-已下单,2-已收货,3-取消收货
				
				vcPurchase.setUpdatedBy(userCode);
				vcPurchase.setDateUpdated(new Date());
				
				//更新采购单
				this.updateVcPurchase(vcPurchase);
			}
		}catch(Exception e){
			throw new DaoException("更新采购单数据出错！", e);
		}
	}
	
	/**
	 * 收货确认查询
	 */
	public Page queryPurchaseReceipt(VcPurchaseVo vcPurchaseVoTemp,String comCode,int pageNo,int pageSize) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//*********拼接查询条件begin*********
			//拼接申请时间查询条件
			if(vcPurchaseVoTemp.getApplyStartDate()!=null){
				queryRule.addGreaterEqual("dateCreated", vcPurchaseVoTemp.getApplyStartDate());
			}
			if(vcPurchaseVoTemp.getApplyEndDate()!=null){
				queryRule.addLessThan("dateCreated", DateUtils.addDay(vcPurchaseVoTemp.getApplyEndDate(), +1));
			}
			//拼接状态查询条件
			if(StringUtils.isNotEmpty(vcPurchaseVoTemp.getFlag()) && !"all".equals(vcPurchaseVoTemp.getFlag())){
				queryRule.addEqual("flag", vcPurchaseVoTemp.getFlag());
			}
			//*********拼接查询条件end*********
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			
			//权限控制：申请机构=当前机构
			queryRule.addEqual("orgCode", comCode);
			
			queryRule.addDescOrder("id");
			
			page= this.find(VcPurchase.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询采购单数据出错！", e);
		}
		return page;
	}
	
	/**
	 * 根据主键查找采购单
	 * @param id
	 * @throws DaoException
	 */
	public VcPurchase findPurchaseById(Long id) throws DaoException {
		VcPurchase vcPurchase = null;
		try{
			vcPurchase = (VcPurchase)this.findUnique(VcPurchase.class, "id", id);
		}catch(Exception e){
			throw new DaoException("查找采购单出错！", e);
		}
		return vcPurchase;
	}
	
	/**
	 * 更新采购单
	 * @param vcPurchase
	 * @throws Exception
	 */
	public void updateVcPurchase(VcPurchase vcPurchase) throws DaoException {
		try{
			this.update(vcPurchase);
		}catch(Exception e){
			throw new DaoException("更新采购单数据出错！", e);
		}
	}
	
	/**
	 * 查询当前机构及其下级机构的有效库存
	 * @param versionCode
	 * @param orgCode
	 * @return vcStorageList
	 * @throws DaoException
	 */
	public List<VcStorage> findValidStorageList(String versionCode, String orgCode) throws DaoException {
		List<VcStorage> vcStorageList = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//递归查找下级机构
			queryRule.addSql(" ORG_CODE IN (" +
					" SELECT T.UNIT_CODE " +
					" FROM VC_LEVEL T " +
					" WHERE T.UNIT_TYPE = '0' AND T.VALID_STATUS='1'" +
					" START WITH T.UNIT_CODE = '"+orgCode+"' " +
					" CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL " 
					+") ");
			//使用截止日期大于等于当前日期
			queryRule.addGreaterEqual("deadline", new Date());
			//增加单证类型查询条件
			queryRule.addEqual("docVerCode", versionCode);
			//增加单证状态查询条件
			queryRule.addIn("docStatus", new String[] {"S1","S2","S3"});
			vcStorageList = this.find(VcStorage.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return vcStorageList;
	}
	
	/**
	 * 根据条件查找订单详细信息
	 * @param vcOrderLaunchDet
	 * @return
	 * @throws Exception
	 * @author wanghuajian
	 * since 2013-4-15上午09:32:03
	 */
	public List<VcOrderLaunchDet> queryOrderLaunchDetList(VcOrderLaunchDet vcOrderLaunchDet) throws Exception{
		List<VcOrderLaunchDet> list = null;
		QueryRule queryRule = QueryRule.getInstance();
		
		//拼接订单号查询条件
		if(vcOrderLaunchDet.getId()!=null){
			queryRule.addGreaterEqual("id", vcOrderLaunchDet.getId());			
		}
		//拼接单证类型
		if(StringUtils.isNotEmpty(vcOrderLaunchDet.getVersionCode())){
			queryRule.addEqual("versionCode", vcOrderLaunchDet.getVersionCode());
		}		
		//拼接状态查询条件
		/*if(StringUtils.isNotEmpty(vcOrderLaunchDet.getValidStatus())){
			queryRule.addEqual("validStatus", vcOrderLaunchDet.getValidStatus());
		}*/	
		//订单主表存在
		String sql = " exists(select 1 from VC_ORDER_LAUNCH launch where launch.ID_VC_ORDER_LAUNCH=ID_VC_ORDER_LAUNCH) ";
		queryRule.addSql(sql);
		list = this.find(VcOrderLaunchDet.class, queryRule);
		return list;
	}
	
	@Override
	public Page queryPurchaseApprove(VcPurchaseVo vcPurchaseVoTemp,List<String> childOrgList,int pageNo,int pageSize) throws DaoException{
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//*********拼接查询条件begin*********
			//拼接申请时间查询条件
			if(vcPurchaseVoTemp.getApplyStartDate()!=null){
				queryRule.addGreaterEqual("dateCreated", vcPurchaseVoTemp.getApplyStartDate());
			}
			if(vcPurchaseVoTemp.getApplyEndDate()!=null){
				queryRule.addLessThan("dateCreated", DateUtils.addDay(vcPurchaseVoTemp.getApplyEndDate(), +1));
			}
			//拼接采购单号查询条件
			if(StringUtils.isNotEmpty(vcPurchaseVoTemp.getPurchaseCode())){
				queryRule.addEqual("purchaseCode", vcPurchaseVoTemp.getPurchaseCode());
			}
			//*********拼接查询条件end*********
			//1-有效的数据。0-删除的数据
			queryRule.addEqual("validStatus", "1");
			
			//状态为：4-待审批、6-审批通过
			//queryRule.addEqual("flag", "4");
			if(StringUtils.isNotEmpty(vcPurchaseVoTemp.getFlag())){
				queryRule.addEqual("flag", vcPurchaseVoTemp.getFlag());
			}else{
				List<String> flagList = new ArrayList<String>();
				flagList.add("4");
				flagList.add("6");
				queryRule.addIn("flag", flagList);
			}
			
			//权限控制：申请机构=当前机构的下级机构
			queryRule.addIn("orgCode", childOrgList);
			
			queryRule.addDescOrder("id");
			
			page= this.find(VcPurchase.class, queryRule, pageNo, pageSize);
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！", e);
		}
		return page;
	}
}
