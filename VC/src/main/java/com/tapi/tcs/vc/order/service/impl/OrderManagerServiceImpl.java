package com.tapi.tcs.vc.order.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.baseconst.BaseInfoConst;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVerMaxNumDao;
import com.tapi.tcs.vc.baseinfo.service.VcDocVersionInfoService;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.baseinfo.service.VcPrintDocVersionService;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.ApproveService;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.order.dao.OrderManagerDao;
import com.tapi.tcs.vc.order.service.OrderManagerService;
import com.tapi.tcs.vc.order.vo.OrderLaunchDetVo;
import com.tapi.tcs.vc.order.vo.OrderLaunchVo;
import com.tapi.tcs.vc.order.vo.VcPurchaseVo;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocPrtNoRule;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcOrderLaunch;
import com.tapi.tcs.vc.schema.model.VcOrderLaunchDet;
import com.tapi.tcs.vc.schema.model.VcPrintDocVersion;
import com.tapi.tcs.vc.schema.model.VcPrintery;
import com.tapi.tcs.vc.schema.model.VcPurchase;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcStorageDao;

/**
 * 征订管理Service
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
public class OrderManagerServiceImpl implements OrderManagerService{

	private OrderManagerDao orderManagerDao;
	private ApproveService approveService;
	private PubCodeManagerService pubCodeManagerService;
	private VcDocVersionInfoService vcDocVersionInfoService;
	private VcStorageDao vcStorageDao;
	private TakeNoDao takeNoDao;
	private VcDocVerMaxNumDao vcDocVerMaxNumDao;
	private VcLevelService vcLevelService;
	private VcPrintDocVersionService vcPrintDocVersionService;
	 /** 单证管理控制规则Dao */
    private VcDocMngRuleDao vcDocMngRuleDao;

	/**
	 * 订单申请查询
	 * @param orderLaunchVoTemp
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return Page
	 * @throws BusinessException
	 */
	public Page queryOrderLaunch(OrderLaunchVo orderLaunchVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException{
		Page newpage = null;
		try{
			/*if(orderLaunchVoTemp.getApplyStartDate()==null || orderLaunchVoTemp.getApplyEndDate()==null){
				throw new BusinessException("申请时间不能为空！");
			}*/
			Page page = orderManagerDao.queryOrderLaunch(orderLaunchVoTemp, comCode, pageNo, pageSize);
			List<VcOrderLaunch> vcOrderLaunchs = (List<VcOrderLaunch>)page.getResult();
			List<OrderLaunchVo> orderLaunchVos = new ArrayList<OrderLaunchVo>();
			//把结果集从VcOrderLaunch对象转换成OrderLaunchVo对象
			for(VcOrderLaunch vcOrderLaunch : vcOrderLaunchs){
				OrderLaunchVo orderLaunchVo = new OrderLaunchVo();
				orderLaunchVo.setOrderId(vcOrderLaunch.getId()+"");
				orderLaunchVo.setOrderCode(vcOrderLaunch.getOrderCode());
				orderLaunchVo.setOrderType(vcOrderLaunch.getOrderType());
				orderLaunchVo.setOrgId(vcOrderLaunch.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getOrgCode());
				orderLaunchVo.setOrgName(orgName);
				//转换订单状态
				String flag = pubCodeManagerService.getCodeCname("OrderStatus", vcOrderLaunch.getFlag());
				orderLaunchVo.setFlag(flag);
				orderLaunchVo.setCreateUserId(vcOrderLaunch.getCreatedBy());
				//转换创建人姓名
				String userName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getCreatedBy());
				orderLaunchVo.setCreateUserName(userName);//创建人姓名需转码
				orderLaunchVo.setCreateDate(vcOrderLaunch.getDateCreated());
				orderLaunchVos.add(orderLaunchVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), orderLaunchVos);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		
		return newpage;
	}
	
	/**
	 * 保存订单申请
	 * @param vcOrderLaunch
	 * @return
	 * @throws BusinessException
	 */
	public void saveOrderLaunch(VcOrderLaunch vcOrderLaunch) throws BusinessException {
		try{
			//订单号生成规则：OR+YYYYMMDD+10位流水号
			String orderCode = takeNoDao.getNo("OR");
			vcOrderLaunch.setOrderCode(orderCode);
			
			//申请机构、审批机构赋值 begin
			String userCode = vcOrderLaunch.getCreatedBy();
			String checkOrgCode = vcLevelService.getUpperOrgCode(userCode);
			vcOrderLaunch.setCheckOrgCode(checkOrgCode);
			//申请机构、审批机构赋值 end
			orderManagerDao.saveOrderLaunch(vcOrderLaunch);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 保存汇总订单
	 */
	public void saveOrderGath(VcOrderLaunch vcOrderLaunch, List<VcOrderLaunch> subOrderList) throws BusinessException {
		try{
			//1、保存汇总订单
			this.saveOrderLaunch(vcOrderLaunch);
			//2、修改子订单
			orderManagerDao.saveAll(subOrderList);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("保存数据出错！", e);
		}
	}
	
	/**
	 * 根据订单id查找订单详细信息
	 */
	public List<VcOrderLaunchDet> findOrderLaunchDets(Long orderId, String comCode) throws BusinessException {
		List<VcOrderLaunchDet> list = null;
		try{
			list = orderManagerDao.findOrderLaunchDets(orderId);
			//计算有效库存
			for(VcOrderLaunchDet vcOrderLaunchDet : list){
				Long allStore = this.findValidStorageList(vcOrderLaunchDet.getVersionCode(), comCode);
				vcOrderLaunchDet.setAllStore(allStore);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	/**
	 * 修改订单申请
	 * @param vcOrderLaunch
	 * @param vcOrderLaunchDetList
	 * @return
	 * @throws BusinessException
	 */
	public void updateOrderLaunch(VcOrderLaunch vcOrderLaunch, List<VcOrderLaunchDet> vcOrderLaunchDetList) throws BusinessException {
		try{
			//第一步：删除det表数据
			orderManagerDao.deleteVcOrderLaunchDets(vcOrderLaunch.getVcOrderLaunchDetList());
			//第二步：保存订单主表及det表
			//2、循环处理vcOrderLaunchDetList
			//2-1、给vcOrderLaunchDetList设置vcOrderLaunch对象，防止不能级联保存vcOrderLaunchDet的情况
			//2-2、设置订单详情表中的申印数量,对于订单发起，申印数量=订单申请数量
			if(vcOrderLaunchDetList!=null && vcOrderLaunchDetList.size()>0){
				for(VcOrderLaunchDet det : vcOrderLaunchDetList){
					det.setVcOrderLaunch(vcOrderLaunch);
					det.setApplyPrintNum(det.getOrderCount());
				}
			}
			//3、设置vcOrderLaunchDetList
			vcOrderLaunch.setVcOrderLaunchDetList(vcOrderLaunchDetList);
			orderManagerDao.updateOrderLaunch(vcOrderLaunch);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 修改订单申请
	 */
	public void updateOrderLaunch(VcOrderLaunch vcOrderLaunch) throws BusinessException {
		try{
			orderManagerDao.updateOrderLaunch(vcOrderLaunch);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 根据主键查询订单申请：包括基本信息和详细信息
	 * @param id
	 * @return VcOrderLaunch
	 * @throws BusinessException
	 */
	public VcOrderLaunch findOrderLaunchByPK(Long id) throws BusinessException {
		VcOrderLaunch vcOrderLaunch = null;
		try{
			vcOrderLaunch = orderManagerDao.findOrderLaunchByPK(id);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcOrderLaunch;
	}
	
	/**
	 * 重新组织订单基本信息和订单详细信息VO对象
	 */
	public void generateOrderLaunch(VcOrderLaunch vcOrderLaunchTemp, 
			OrderLaunchVo orderLaunchVo, List<OrderLaunchDetVo> orderLaunchDetVos) throws BusinessException {
		try{
			//组织orderLaunchVo
			orderLaunchVo.setOrderId(vcOrderLaunchTemp.getId()+"");
			orderLaunchVo.setOrderCode(vcOrderLaunchTemp.getOrderCode());
			orderLaunchVo.setOrderType(vcOrderLaunchTemp.getOrderType());
			orderLaunchVo.setOrgId(vcOrderLaunchTemp.getOrgCode());
			//转换机构名称
			String orgName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunchTemp.getOrgCode());
			orderLaunchVo.setOrgName(orgName);
			orderLaunchVo.setFlag(vcOrderLaunchTemp.getFlag());
			orderLaunchVo.setCreateUserId(vcOrderLaunchTemp.getCreatedBy());
			//转换创建人名称
			String userName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunchTemp.getCreatedBy());
			orderLaunchVo.setCreateUserName(userName);//创建人名称需转码
			orderLaunchVo.setModifyUserId(vcOrderLaunchTemp.getModifyUserCode());
			orderLaunchVo.setCreateDate(vcOrderLaunchTemp.getDateCreated());
			orderLaunchVo.setModifyDate(vcOrderLaunchTemp.getModifyDate());
			
			//组织List<OrderLaunchDetVo>
			List<VcOrderLaunchDet>detList = vcOrderLaunchTemp.getVcOrderLaunchDetList();
			if(detList!=null && detList.size()>0){
				OrderLaunchDetVo detVo = null;
				for(VcOrderLaunchDet det : detList){
					detVo = new OrderLaunchDetVo();
					detVo.setId(det.getId());
					detVo.setOrderId(vcOrderLaunchTemp.getId());
					detVo.setVersionCode(det.getVersionCode());
					String versionName = vcDocVersionInfoService.getVersionName(det.getVersionCode());
					detVo.setVersionName(versionName);
					detVo.setOrderCount(det.getOrderCount());
					detVo.setApplyPrintNum(det.getApplyPrintNum());
					Long store = this.findValidStorageList(det.getVersionCode(), vcOrderLaunchTemp.getOrgCode());
					detVo.setAllStore(store.intValue());
					orderLaunchDetVos.add(detVo);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 查询审批记录
	 */
	public List<VcApprove> queryVcApprove(Long applyId, String appType) throws BusinessException {
		return approveService.queryVcApprove(applyId, appType);
	}
	
	/**
	 * 批量查询订单
	 * @param id
	 * @return List<VcOrderLaunch>
	 * @throws BusinessException
	 */
	public List<VcOrderLaunch> getOrderLaunchList(String[] idList) throws BusinessException {
		List<VcOrderLaunch> list = null;
		try{
			Long[] idTmp = new Long[idList.length];
			for(int i=0;i<idList.length;i++){
				idTmp[i] = Long.valueOf(idList[i]);
			}
			list = orderManagerDao.getOrderLaunchList(idTmp);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @param approveService
	 * @throws BusinessException
	 */
	public void deleteOrderLaunchByPK(Long id) throws BusinessException {
		try{
			//1、删除订单信息
			orderManagerDao.deleteOrderLaunch(id);
			//2、根据申请编号，查询出订单的审批记录
			List<VcApprove> vcApproveList = approveService.queryVcApproveObj(id, "I");
			//3、删除审批记录
			approveService.deleteVcApprove(vcApproveList);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 查询审批列表
	 * @param orderLaunchVoTemp
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return Page
	 * @throws BusinessException
	 */
	public Page queryOrderApprove(OrderLaunchVo orderLaunchVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			Page page = orderManagerDao.queryOrderApprove(orderLaunchVoTemp, comCode, pageNo, pageSize);
			List<VcOrderLaunch> vcOrderLaunchs = (List<VcOrderLaunch>)page.getResult();
			List<OrderLaunchVo> orderLaunchVos = new ArrayList<OrderLaunchVo>();
			//把结果集从VcOrderLaunch对象转换成OrderLaunchVo对象
			for(VcOrderLaunch vcOrderLaunch : vcOrderLaunchs){
				OrderLaunchVo orderLaunchVo = new OrderLaunchVo();
				orderLaunchVo.setOrderId(vcOrderLaunch.getId()+"");
				orderLaunchVo.setOrderCode(vcOrderLaunch.getOrderCode());
				orderLaunchVo.setOrderType(vcOrderLaunch.getOrderType());
				orderLaunchVo.setOrgId(vcOrderLaunch.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getOrgCode());
				orderLaunchVo.setOrgName(orgName);
				//转换订单状态
				String flag = pubCodeManagerService.getCodeCname("OrderStatus", vcOrderLaunch.getFlag());
				orderLaunchVo.setFlag(flag);
				orderLaunchVo.setCreateUserId(vcOrderLaunch.getCreatedBy());
				//转换创建人名称
				String userName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getCreatedBy());
				orderLaunchVo.setCreateUserName(userName);
				orderLaunchVo.setCreateDate(vcOrderLaunch.getDateCreated());
				orderLaunchVos.add(orderLaunchVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), orderLaunchVos);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	}
	
	/**
	 * 订单审批
	 * @param vcOrderLaunch
	 * @param vcApprove
	 * @param approveService
	 * @return 
	 * @throws BusinessException
	 */
	public void executeOrderApprove(VcOrderLaunch vcOrderLaunch,
			VcApprove vcApprove) throws BusinessException {
		try{
			//更新订单基本信息
			orderManagerDao.updateOrderLaunch(vcOrderLaunch);
			//插入审批记录
			approveService.saveVcApprove(vcApprove);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 查询汇总订单
	 * @param orderLaunchVoTemp
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return Page
	 * @throws BusinessException
	 */
	public Page queryOrderGather(OrderLaunchVo orderLaunchVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			Page page = orderManagerDao.queryOrderGather(orderLaunchVoTemp, comCode, pageNo, pageSize);
			List<VcOrderLaunch> vcOrderLaunchs = (List<VcOrderLaunch>)page.getResult();
			List<OrderLaunchVo> orderLaunchVos = new ArrayList<OrderLaunchVo>();
			//把结果集从VcOrderLaunch对象转换成OrderLaunchVo对象
			for(VcOrderLaunch vcOrderLaunch : vcOrderLaunchs){
				OrderLaunchVo orderLaunchVo = new OrderLaunchVo();
				orderLaunchVo.setOrderId(vcOrderLaunch.getId()+"");
				orderLaunchVo.setOrderCode(vcOrderLaunch.getOrderCode());
				orderLaunchVo.setOrderType(vcOrderLaunch.getOrderType());
				orderLaunchVo.setOrgId(vcOrderLaunch.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getOrgCode());
				orderLaunchVo.setOrgName(orgName);
				//转换订单状态
				String flag = pubCodeManagerService.getCodeCname("OrderStatus", vcOrderLaunch.getFlag());
				orderLaunchVo.setFlag(flag);
				orderLaunchVo.setCreateUserId(vcOrderLaunch.getCreatedBy());
				//转换创建人名称
				String userName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getCreatedBy());
				orderLaunchVo.setCreateUserName(userName);
				orderLaunchVo.setCreateDate(vcOrderLaunch.getDateCreated());
				orderLaunchVos.add(orderLaunchVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), orderLaunchVos);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	}
	
	/**
	 * 新增订单汇总-查询可汇总的订单
	 * @param orderLaunchVoTemp
	 * @param comCode
	 * @return List
	 * @throws BusinessException
	 */
	public List queryOrderGatherInput(OrderLaunchVo orderLaunchVoTemp, String comCode) throws BusinessException {
		List orderLaunchVos = new ArrayList();
		try{
			//查询出当前用户归属机构的所有下级机构
			List<String> childOrgList = vcLevelService.getChildOrgCode(comCode);
			//查询分页列表
			List<VcOrderLaunch> list = orderManagerDao.queryOrderGatherInput(orderLaunchVoTemp, childOrgList);
			//把结果集从VcOrderLaunch对象转换成OrderLaunchVo对象
			for(VcOrderLaunch vcOrderLaunch : list){
				OrderLaunchVo orderLaunchVo = new OrderLaunchVo();
				orderLaunchVo.setOrderId(vcOrderLaunch.getId()+"");
				orderLaunchVo.setOrderCode(vcOrderLaunch.getOrderCode());
				orderLaunchVo.setOrderType(vcOrderLaunch.getOrderType());
				orderLaunchVo.setOrgId(vcOrderLaunch.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getOrgCode());
				orderLaunchVo.setOrgName(orgName);
				//转换订单状态
				String flag = pubCodeManagerService.getCodeCname("OrderStatus", vcOrderLaunch.getFlag());
				orderLaunchVo.setFlag(flag);
				orderLaunchVo.setCreateUserId(vcOrderLaunch.getCreatedBy());
				//转换创建人名称
				String userName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getCreatedBy());
				orderLaunchVo.setCreateUserName(userName);
				orderLaunchVo.setCreateDate(vcOrderLaunch.getDateCreated());
				orderLaunchVos.add(orderLaunchVo);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return orderLaunchVos;
	}
	
	/**
	 * 按单证类型汇总订单
	 * @param id
	 * @param comCode
	 * @return List<OrderLaunchDetVo>
	 * @throws BusinessException
	 */
	public List<OrderLaunchDetVo> orderGather(String id, String comCode) throws BusinessException {
		List<OrderLaunchDetVo>voList = new ArrayList<OrderLaunchDetVo>();
		try{
			List<VcOrderLaunchDet> list = orderManagerDao.orderGather(id);
			if(list!=null && list.size()>0){
				OrderLaunchDetVo detVo = null;
				for(VcOrderLaunchDet det : list){
					detVo = new OrderLaunchDetVo();
					detVo.setVersionCode(det.getVersionCode());
					String versionName = vcDocVersionInfoService.getVersionName(det.getVersionCode());
					detVo.setVersionName(versionName);
					detVo.setOrderCount(det.getOrderCount());
					detVo.setApplyPrintNum(det.getApplyPrintNum());
					Long storageNum = this.findValidStorageList(det.getVersionCode(), comCode);
					detVo.setAllStore(storageNum.intValue());
					voList.add(detVo);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return voList;
	}
	
	/**
	 * 根据订单ID查找子订单列表
	 * @param id
	 * @return list
	 * @throws BusinessException
	 */
	public List getSubOrderListByOrderId(Long id) throws BusinessException {
		List<OrderLaunchVo> orderLaunchVos = new ArrayList<OrderLaunchVo>();
		try{
			//查找出子订单ID
			List<Long> idList = orderManagerDao.getSubOrderIds(id);
			//根据订单ID查找订单数组
			List<VcOrderLaunch> orderLaunchList = orderManagerDao.getSubOrderListByOrderId(idList);
			//把结果集从VcOrderLaunch对象转换成OrderLaunchVo对象
			for(VcOrderLaunch vcOrderLaunch : orderLaunchList){
				OrderLaunchVo orderLaunchVo = new OrderLaunchVo();
				orderLaunchVo.setOrderId(vcOrderLaunch.getId()+"");
				orderLaunchVo.setOrderCode(vcOrderLaunch.getOrderCode());
				orderLaunchVo.setOrderType(vcOrderLaunch.getOrderType());
				orderLaunchVo.setOrgId(vcOrderLaunch.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getOrgCode());
				orderLaunchVo.setOrgName(orgName);
				//转换订单状态
				String flag = pubCodeManagerService.getCodeCname("OrderStatus", vcOrderLaunch.getFlag());
				orderLaunchVo.setFlag(flag);
				orderLaunchVo.setCreateUserId(vcOrderLaunch.getCreatedBy());
				//转换创建人名称
				String userName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getCreatedBy());
				orderLaunchVo.setCreateUserName(userName);
				orderLaunchVo.setCreateDate(vcOrderLaunch.getDateCreated());
				orderLaunchVos.add(orderLaunchVo);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return orderLaunchVos;
	}
	
	
	/**
	 * 订单申请：保存+提交
	 */
	public void saveAndSubmitOrderLaunch(VcOrderLaunch vcOrderLaunch, VcApprove vcApprove) throws BusinessException{
		try{
			//第一步：保存订单
			this.saveOrderLaunch(vcOrderLaunch);
			//第二步：保存审批 记录
			vcApprove.setApplyId(vcOrderLaunch.getId());//申请编号：订单号
			approveService.saveVcApprove(vcApprove);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 订单申请：修改+保存
	 */
	public void updateAndSubmitOrderLaunch(VcOrderLaunch vcOrderLaunch,List<VcOrderLaunchDet> vcOrderLaunchDetList,VcApprove vcApprove) throws BusinessException {
		try{
			//第一步：调用修改方法
			this.updateOrderLaunch(vcOrderLaunch, vcOrderLaunchDetList);
			//第二步：保存审批 记录
			approveService.saveVcApprove(vcApprove);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	/**
	 * 汇总订单：保存+提交
	 */
	public void saveAndSubmitOrderGather(VcOrderLaunch vcOrderLaunch, List<VcOrderLaunch> subOrderList, VcApprove vcApprove) throws BusinessException {
		try{
			//第一步：保存订单
			this.saveOrderLaunch(vcOrderLaunch);
			//第二步：修改子订单（汇总标志改为已汇总）
			orderManagerDao.saveAll(subOrderList);
			//第三步：保存审批记录
			vcApprove.setApplyId(vcOrderLaunch.getId());//申请编号：订单号
			approveService.saveVcApprove(vcApprove);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("保存数据出错！", e);
		}
	}
	
	/**
	 * 根据条件查询采购单
	 * 
	 */
	public Page queryPurchase(VcPurchaseVo vcPurchaseVoTemp,String userCode,
			String comCode,int pageNo,int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			Page page = orderManagerDao.queryPurchase(vcPurchaseVoTemp, userCode, comCode, pageNo, pageSize);
			List<VcPurchase> vcPurchases = (List<VcPurchase>)page.getResult();
			List<VcPurchaseVo> vcPurchaseVos = new ArrayList<VcPurchaseVo>();
			//把结果集从VcPurchase对象转换成VcPurchaseVo对象
			for(VcPurchase vcPurchase : vcPurchases){
				VcPurchaseVo vcPurchaseVo = new VcPurchaseVo();
				vcPurchaseVo.setPurchaseId(vcPurchase.getId());
				vcPurchaseVo.setPurchaseCode(vcPurchase.getPurchaseCode());
				vcPurchaseVo.setOrgId(vcPurchase.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcPurchase.getOrgCode());
				vcPurchaseVo.setOrgName(orgName);
				vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());
				String versionName = vcDocVersionInfoService.getVersionName(vcPurchase.getVersionCode());
				vcPurchaseVo.setVersionName(versionName);
				vcPurchaseVo.setApplyPrintNum(vcPurchase.getApplyPrintNum());
				vcPurchaseVo.setStartSerialNo(vcPurchase.getStartSerialNo());
				vcPurchaseVo.setEndSerialNo(vcPurchase.getEndSerialNo());
				//转换采购单状态
				String flag = pubCodeManagerService.getCodeCname("PurchaseStatus", vcPurchase.getFlag());
				vcPurchaseVo.setFlag(flag);
				vcPurchaseVo.setValidStatus(vcPurchase.getValidStatus());
				vcPurchaseVos.add(vcPurchaseVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), vcPurchaseVos);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	}
	
	/**
	 * 新增采购单-查询可生成采购单的汇总订单
	 */
	public Page queryPurchaseGenInput(OrderLaunchVo orderLaunchVoTemp, String comCode, int pageNo, int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			//查询出当前用户归属机构的所有下级机构
			List<String> childOrgList = vcLevelService.getChildOrgCode(comCode);
			//查询分页列表
			Page page = orderManagerDao.queryPurchaseGenInput(orderLaunchVoTemp, childOrgList, pageNo, pageSize);
			
			List<VcOrderLaunch> vcOrderLaunchs = (List<VcOrderLaunch>)page.getResult();
			List<OrderLaunchVo> orderLaunchVos = new ArrayList<OrderLaunchVo>();
			//把结果集从VcOrderLaunch对象转换成OrderLaunchVo对象
			for(VcOrderLaunch vcOrderLaunch : vcOrderLaunchs){
				OrderLaunchVo orderLaunchVo = new OrderLaunchVo();
				orderLaunchVo.setOrderId(vcOrderLaunch.getId()+"");
				orderLaunchVo.setOrderCode(vcOrderLaunch.getOrderCode());
				orderLaunchVo.setOrderType(vcOrderLaunch.getOrderType());
				orderLaunchVo.setOrgId(vcOrderLaunch.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getOrgCode());
				orderLaunchVo.setOrgName(orgName);
				//转换订单状态
				String flag = pubCodeManagerService.getCodeCname("OrderStatus", vcOrderLaunch.getFlag());
				orderLaunchVo.setFlag(flag);
				orderLaunchVo.setCreateUserId(vcOrderLaunch.getCreatedBy());
				//转换创建人名称
				String userName = vcLevelService.getUnitNameByUnitCode(vcOrderLaunch.getCreatedBy());
				orderLaunchVo.setCreateUserName(userName);
				orderLaunchVo.setCreateDate(vcOrderLaunch.getDateCreated());
				orderLaunchVos.add(orderLaunchVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), orderLaunchVos);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	
	}
	
	/**
	 * 保存采购单
	 */
	public List<VcPurchase> executePurchaseGen(String id,String userCode,String comCode) throws BusinessException {
		List<VcPurchase> vcPurchaseList = null;
		try{
			//第一步：根据订单号查询订单信息，按单证类型、申请机构进行分组
			List<Object[]> objList = orderManagerDao.findOrderForPurchase(id);
			
			if(objList!=null && objList.size()>0){
				//第二步：保存采购单
				vcPurchaseList = new ArrayList<VcPurchase>();
				VcPurchase vcPurchase = null;
				Date nowDate = new Date();
				for(Object[] obj : objList){
					String orgId = (String)obj[0];
					String versionCode = (String)obj[1];
					Integer applyPrintNum = ((BigDecimal)obj[2]).intValue();
					
					vcPurchase = new VcPurchase();
					//采购号生成规则：DP+YYYYMMDD+10位流水号
					String purchaseCode = takeNoDao.getNo("DP");
					
					vcPurchase.setPurchaseCode(purchaseCode);
					vcPurchase.setOrgCode(orgId);
					vcPurchase.setVersionCode(versionCode);
					vcPurchase.setApplyPrintNum(applyPrintNum);		
					//获取单证种类
					String strDocTypeCode=vcDocVersionInfoService.getDocTypeCode(versionCode);
					String startNum=null;
					String endNum =null;
					if(strDocTypeCode.equals(BaseInfoConst.DOC_TYPE_ACTIVATE_CARD)){
					    // ********激活卡生成起始流水号，结束流水号，并更新最大流水号表 begin***********
					    //两位年份
					    SimpleDateFormat sdf=new SimpleDateFormat("yy");
				        String strDate=sdf.format(new Date());
					    //流水格式化为8位
			            DecimalFormat df = new DecimalFormat("0");
			            df.setMinimumIntegerDigits(8);
                        // 查询最大流水号
                        Integer maxNum = vcDocVerMaxNumDao.getMaxNum(BaseInfoConst.DOC_TYPE_ACTIVATE_CARD, userCode);
                        // 起始流水号 = 在已印刷单证最大流水号基础上，按照单证类型流水号生成规则，号码递增1
                        Integer start = maxNum + 1;
                        startNum =strDate+df.format(start);
                        // 结束流水号=起始流水号+印刷数量-1
                        Integer end = start + applyPrintNum - 1;
                        endNum = strDate+df.format(end); 
                        //更新最大值
                        vcDocVerMaxNumDao.updateVcDocVerMaxNum(BaseInfoConst.DOC_TYPE_ACTIVATE_CARD, end, userCode);
                    } else {
                        // ********非激活卡生成起始流水号，结束流水号，并更新最大流水号表 begin***********
                        // 查询生成规则表
                        List<Object[]> ruleList = vcDocVersionInfoService.getDocNumRule(versionCode);
                        if (ruleList == null || ruleList.size() < 1) {
                            throw new BusinessException("该单证类型没有配置流水号生成规则，请确认！");
                        }
                        // 查询最大流水号
                        Integer maxNum = vcDocVerMaxNumDao.getMaxNum(versionCode, userCode);
                        // 起始流水号 = 在已印刷单证最大流水号基础上，按照单证类型流水号生成规则，号码递增1
                        Integer start = maxNum + 1;
                        startNum = getVersionSerialNo(ruleList, start);
                        // 结束流水号=起始流水号+印刷数量-1
                        Integer end = (maxNum + 1) + applyPrintNum - 1;
                        endNum = getVersionSerialNo(ruleList, end);
                      //更新最大值
                        vcDocVerMaxNumDao.updateVcDocVerMaxNum(versionCode, end, userCode);
                    }
					
					//********生成起始流水号，结束流水号，并更新最大流水号表 end*************
					vcPurchase.setStartSerialNo(startNum);
					vcPurchase.setEndSerialNo(endNum);
					vcPurchase.setFlag("0");//0-新建
					vcPurchase.setValidStatus("1");
					vcPurchase.setCreatedBy(userCode);
					vcPurchase.setDateCreated(nowDate);
					vcPurchase.setUpdatedBy(userCode);
					vcPurchase.setDateUpdated(nowDate);
					vcPurchaseList.add(vcPurchase);
				}
				orderManagerDao.savePurchase(vcPurchaseList);
				//第三步：更新订单状态
				String[] ids = id.split(",");
				for(String orderId : ids){
					VcOrderLaunch vcOrderLaunch = orderManagerDao.findOrderLaunchByPK(Long.valueOf(orderId));
					//修改订单状态为"采购中"
					vcOrderLaunch.setFlag("3");
					vcOrderLaunch.setUpdatedBy(userCode);
					vcOrderLaunch.setDateUpdated(nowDate);
					orderManagerDao.updateOrderLaunch(vcOrderLaunch);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcPurchaseList;
	}
	
	/**
	 * 生成单证流水号
	 * @param ruleList 流水号规则列表
	 * @param serialNo
	 * @return
	 */
	private String getVersionSerialNo(List<Object[]> ruleList, Integer serialNo) throws BusinessException {
		String number = "";
		try{
			//格式化流水号为8位
			DecimalFormat df = new DecimalFormat("0");
			df.setMinimumIntegerDigits(8);
			
			for(Object[] obj : ruleList){
				VcDocPrtNoRule vcDocPrtNoRule = (VcDocPrtNoRule)obj[0];
				//元素项为流水号(ItemTypeCode=S)
				if("S".equals(vcDocPrtNoRule.getItemTypeCode())){
					number += df.format(serialNo);
				}else{
					number += vcDocPrtNoRule.getItemValue();
				}
			}
		}catch(Exception e){
			throw new BusinessException("生成单证流水号出错！", e);
		}
		return number;
	}
	
	/**
	 * 显示采购单
	 */
	public List generatePurchase(List<VcPurchase> list) throws BusinessException {
		List vcPurchaseList = new ArrayList();
		try{
			if(list!=null && list.size()>0){
				//把结果集从VcPurchase对象转换成VcPurchaseVo对象
				for(VcPurchase vcPurchase : list){
					VcPurchaseVo vcPurchaseVo = new VcPurchaseVo();
					vcPurchaseVo.setPurchaseId(vcPurchase.getId());
					vcPurchaseVo.setPurchaseCode(vcPurchase.getPurchaseCode());
					vcPurchaseVo.setOrgId(vcPurchase.getOrgCode());
					//转换机构名称
					String orgName = vcLevelService.getUnitNameByUnitCode(vcPurchase.getOrgCode());
					vcPurchaseVo.setOrgName(orgName);
					vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());
					String versionName = vcDocVersionInfoService.getVersionName(vcPurchase.getVersionCode());
					vcPurchaseVo.setVersionName(versionName);
					vcPurchaseVo.setApplyPrintNum(vcPurchase.getApplyPrintNum());
					vcPurchaseVo.setStartSerialNo(vcPurchase.getStartSerialNo());
					vcPurchaseVo.setEndSerialNo(vcPurchase.getEndSerialNo());
					vcPurchaseList.add(vcPurchaseVo);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcPurchaseList;
	}
	
	/**
	 * 采购下单查询
	 * 
	 */
	public Page queryPurchaseOrder(VcPurchaseVo vcPurchaseVoTemp,String userCode,
			String comCode,int pageNo,int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			//查询印刷厂对应的单证类型、单价：key-versionCode,value-unitPrice
			Map<String, String> map =
				vcPrintDocVersionService.findVcPrintDocVersionList(Long.valueOf(vcPurchaseVoTemp.getPrinteryId()));
			if(map==null || map.size()<1){
				newpage = new Page(pageNo, pageSize, 0, null);
				return newpage;
			}
			Object[] versionCodes = map.keySet().toArray();
			
			Page page = orderManagerDao.queryPurchaseOrder(vcPurchaseVoTemp, versionCodes, userCode, comCode, pageNo, pageSize);
			
			List<VcPurchase> vcPurchases = (List<VcPurchase>)page.getResult();
			List<VcPurchaseVo> vcPurchaseVos = new ArrayList<VcPurchaseVo>();
			//把结果集从VcPurchase对象转换成VcPurchaseVo对象
			for(VcPurchase vcPurchase : vcPurchases){
				VcPurchaseVo vcPurchaseVo = new VcPurchaseVo();
				vcPurchaseVo.setPurchaseId(vcPurchase.getId());
				vcPurchaseVo.setPurchaseCode(vcPurchase.getPurchaseCode());
				vcPurchaseVo.setOrgId(vcPurchase.getOrgCode());
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcPurchase.getOrgCode());
				vcPurchaseVo.setOrgName(orgName);
				vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());
				String versionName = vcDocVersionInfoService.getVersionName(vcPurchase.getVersionCode());
				vcPurchaseVo.setVersionName(versionName);
				vcPurchaseVo.setApplyPrintNum(vcPurchase.getApplyPrintNum());
				vcPurchaseVo.setStartSerialNo(vcPurchase.getStartSerialNo());
				vcPurchaseVo.setEndSerialNo(vcPurchase.getEndSerialNo());
				String unitPrice = map.get(vcPurchase.getVersionCode());
				//根据单证类型从map中读取单价
				BigDecimal price = new BigDecimal(unitPrice);
				//承印厂商从页面查询条件读取
				vcPurchaseVo.setPrinteryId(vcPurchaseVoTemp.getPrinteryId());
				vcPurchaseVo.setPrinteryName(vcPurchaseVoTemp.getPrinteryName());
				vcPurchaseVo.setUnitPrice(price);
				//总额=单价*申印数量
				vcPurchaseVo.setTotalAmount(price.multiply(new BigDecimal(vcPurchase.getApplyPrintNum())));
				
				vcPurchaseVos.add(vcPurchaseVo);
			}
			//返回OrderLaunchVo的Page对象
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), vcPurchaseVos);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	}
	
	
	/**
	 * 采购下单结果查询
	 * @param vcPurchaseVoTemp
	 * @param userCode
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 *@author whj
	 *@since Mar 11, 2014
	 */     
    public List<VcPurchaseVo> queryPurchaseOrderList(VcPurchaseVo vcPurchaseVoTemp, String userCode, String comCode)
            throws BusinessException {
        List<VcPurchaseVo> vcPurchaseVos = new ArrayList<VcPurchaseVo>();
        try {
            // VcPurchase , VcDocVersionInfo , VcPrintery ,PubCompany , VcPrintDocVersion
            List<Object[]> list = orderManagerDao.queryPurchaseOrderList(vcPurchaseVoTemp, userCode, comCode);
            // 对象转换成VcPurchaseVo对象
            VcPurchase vcPurchase = null;
            VcDocVersionInfo vcDocVersionInfo = null;
            VcPrintery vcPrintery = null;
            PubCompany pubCompany = null;
            VcPrintDocVersion vcPrintDocVersion = null;
            VcPurchaseVo vcPurchaseVo = null;
            for (Object[] object : list) {
                vcPurchase = (VcPurchase) object[0];
                vcDocVersionInfo = (VcDocVersionInfo) object[1];
                vcPrintery = (VcPrintery) object[2];
                pubCompany = (PubCompany) object[3];
                vcPrintDocVersion = (VcPrintDocVersion) object[4];

                vcPurchaseVo = new VcPurchaseVo();
                vcPurchaseVo.setPurchaseId(vcPurchase.getId());
                vcPurchaseVo.setPurchaseCode(vcPurchase.getPurchaseCode());
                vcPurchaseVo.setOrgId(vcPurchase.getOrgCode());
                // 机构名称
                vcPurchaseVo.setOrgName(pubCompany.getCompanyCname());
                vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());
                vcPurchaseVo.setVersionName(vcDocVersionInfo.getDocVerName());
                vcPurchaseVo.setApplyPrintNum(vcPurchase.getApplyPrintNum());
                vcPurchaseVo.setStartSerialNo(vcPurchase.getStartSerialNo());
                vcPurchaseVo.setEndSerialNo(vcPurchase.getEndSerialNo());
                // 单价
                BigDecimal price = new BigDecimal(vcPrintDocVersion.getUnitPrice());
                vcPurchaseVo.setUnitPrice(price);
                // 承印厂商
                vcPurchaseVo.setPrinteryId(vcPrintery.getIdVcPrintery().toString());
                vcPurchaseVo.setPrinteryName(vcPrintery.getPrinteryName());
                // 总额=单价*申印数量
                vcPurchaseVo.setTotalAmount(price.multiply(new BigDecimal(vcPurchase.getApplyPrintNum())));
                vcPurchaseVos.add(vcPurchaseVo);
            }

        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return vcPurchaseVos;
    }

	/**
	 * 采购下单
	 * @param list
	 * @throws Exception
	 */
	public void executePurchaseOrder(List<VcPurchaseVo> list, String userCode) throws BusinessException {
		try{
			orderManagerDao.executePurchaseOrder(list, userCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 收货确认查询
	 */
	public Page queryPurchaseReceipt(VcPurchaseVo vcPurchaseVoTemp, String comCode,int pageNo,int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			Page page = orderManagerDao.queryPurchaseReceipt(vcPurchaseVoTemp, comCode, pageNo, pageSize);
			List<VcPurchase> vcPurchases = (List<VcPurchase>)page.getResult();
			List<VcPurchaseVo> vcPurchaseVos = new ArrayList<VcPurchaseVo>();
			//把结果集从VcPurchase对象转换成VcPurchaseVo对象
			for(VcPurchase vcPurchase : vcPurchases){
				VcPurchaseVo vcPurchaseVo = new VcPurchaseVo();
				vcPurchaseVo.setPressBatchNo("");
				vcPurchaseVo.setPurchaseId(vcPurchase.getId());//id
				vcPurchaseVo.setPurchaseCode(vcPurchase.getPurchaseCode());//采购单号
				vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());//单证类型代码
				String versionName = vcDocVersionInfoService.getVersionName(vcPurchase.getVersionCode());
				vcPurchaseVo.setVersionName(versionName);//单证类型名称
				vcPurchaseVo.setApplyPrintNum(vcPurchase.getApplyPrintNum());//申印数量
				vcPurchaseVo.setStartSerialNo(vcPurchase.getStartSerialNo());//起始流水号
				vcPurchaseVo.setEndSerialNo(vcPurchase.getEndSerialNo());//结束流水号
				//新建或者已下单状态，收货流水号、数量为空，默认等于流水号、申印数量
				if("0".equals(vcPurchase.getFlag()) || "1".equals(vcPurchase.getFlag())){
					vcPurchaseVo.setReceivedStartNum(vcPurchase.getStartSerialNo());//收货起始流水号：默认等于起始流水号
					vcPurchaseVo.setReceivedEndNum(vcPurchase.getEndSerialNo());//收货结束流水号：默认等于结束流水号
					vcPurchaseVo.setReceivedQuantity(vcPurchase.getApplyPrintNum());//收货数量：默认等于申印数量
				}else{
					vcPurchaseVo.setReceivedStartNum(vcPurchase.getReceivedStartNum());//收货起始流水号
					vcPurchaseVo.setReceivedEndNum(vcPurchase.getReceivedEndNum());//收货结束流水号
					vcPurchaseVo.setReceivedQuantity(vcPurchase.getReceivedQuantity());//收货数量
				}
				vcPurchaseVo.setPrinteryId(vcPurchase.getPrinteryId());//印刷厂id
				vcPurchaseVo.setUnitPrice(vcPurchase.getUnitPrice());//单价
				vcPurchaseVo.setTotalAmount(vcPurchase.getTotalAmount());//总额
				//转换采购单状态
				String flag = pubCodeManagerService.getCodeCname("PurchaseStatus", vcPurchase.getFlag());
				vcPurchaseVo.setFlag(flag);//采购单状态
				vcPurchaseVo.setValidStatus(vcPurchase.getValidStatus());
				
				vcPurchaseVos.add(vcPurchaseVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), vcPurchaseVos);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	}
	
	/**
	 * 校验库存状态
	 * @param vcPurchase
	 * @return
	 * @throws Exception
	 */
	public boolean checkStorage(VcPurchase vcPurchase) throws BusinessException{
		try{
			String startNum = vcPurchase.getReceivedStartNum();
			String endNum = vcPurchase.getReceivedEndNum();
			//根据单证类型代码、机构代码、起始流水号、终止流水号、库存状态查找唯一记录
			List<VcStorage> list = vcStorageDao.findByConditions(vcPurchase.getVersionCode(), 
					vcPurchase.getOrgCode(), startNum.substring(startNum.length()-10), endNum.substring(endNum.length()-10), "S1");
			if(list==null || list.size()!=1)
				return false;
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return true;
	}
	
	/**
	 * 确认收货
	 */
	/*public void executeReceiptConfirm(String[] ids, String comCode, String userCode) throws Exception {
		//循环处理采购单
		for(String id : ids){
			//1、更新采购单状态
			VcPurchase vcPurchase = orderManagerDao.findPurchaseById(Long.valueOf(id));
			vcPurchase.setFlag("2");//2-已收货
			vcPurchase.setReceivedOpr(userCode);
			vcPurchase.setReceivedTime(new Date());
			vcPurchase.setUpdatedBy(userCode);
			vcPurchase.setDateUpdated(new Date());
			orderManagerDao.updateVcPurchase(vcPurchase);
			//2、保存单证流水号到库存表
			VcStorage vcStorage = new VcStorage();
			vcStorage.setDocVerCode(vcPurchase.getVersionCode());
			vcStorage.setPressBatchNo("000");//批次号，一期上线写死'000'
			String startNum = vcPurchase.getStartSerialNo();
			String endNum = vcPurchase.getEndSerialNo();
			vcStorage.setStartNum(startNum.substring(startNum.length()-10));//流水号取收货流水号还是采购单的流水号？？？
			vcStorage.setEndNum(endNum.substring(endNum.length()-10));//流水号取收货流水号还是采购单的流水号？？？
			vcStorage.setDocNum(vcPurchase.getApplyPrintNum().longValue());//单证数量取申印数量还是收货数量？？
			vcStorage.setDocStatus("S1");//S1-印刷入库
			vcStorage.setOrgCode(comCode);
			vcStorage.setInStoreTime(new Date());
			vcStorage.setDeadline(DateUtils.addDay(new Date(), 90));//使用截止时间，暂时写死90天？？？？
			vcStorage.setCreateUserCode(userCode);
			vcStorage.setCreateTime(new Date());
			vcStorage.setModifyUserCode(userCode);
			vcStorage.setModifyTime(new Date());
			vcStorage.setCreatedBy(userCode);
			vcStorage.setDateCreated(new Date());
			vcStorage.setUpdatedBy(userCode);
			vcStorage.setDateUpdated(new Date());
			vcStorageDao.saveVcStorage(vcStorage);
		}
	}*/
	@Override
	public void executeReceiptConfirm(List<VcPurchaseVo> vcPurchaseVoList, String userCode) throws BusinessException {
		Date nowDate = new Date();
		try{
			//循环处理采购单
			for(VcPurchaseVo vcPurchaseVo : vcPurchaseVoList){
				//1、更新采购单状态
				VcPurchase vcPurchase = orderManagerDao.findPurchaseById(Long.valueOf(vcPurchaseVo.getPurchaseId()));
				vcPurchase.setFlag("2");//2-已收货
				vcPurchase.setReceivedStartNum(vcPurchaseVo.getReceivedStartNum());//收货起始流水号
				vcPurchase.setReceivedEndNum(vcPurchaseVo.getReceivedEndNum());//收货结束流水号
				vcPurchase.setReceivedQuantity(vcPurchaseVo.getReceivedQuantity());//收货数量
				vcPurchase.setReceivedOpr(userCode);//收货人
				vcPurchase.setReceivedTime(nowDate);//收货时间
				vcPurchase.setUpdatedBy(userCode);
				vcPurchase.setDateUpdated(nowDate);
				orderManagerDao.updateVcPurchase(vcPurchase);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 取消收货
	 */
	public void executeReceiptCancel(String[] ids, String userCode) throws BusinessException {
		Date nowDate = new Date();
		try{
			//循环处理采购单
			for(String id : ids){
				//1、更新采购单状态
				VcPurchase vcPurchase = orderManagerDao.findPurchaseById(Long.valueOf(id));
				vcPurchase.setFlag("3");//3-取消收货
				vcPurchase.setCancelReceivedOrp(userCode);
				vcPurchase.setCancelReceivedTime(nowDate);
				vcPurchase.setUpdatedBy(userCode);
				vcPurchase.setDateUpdated(nowDate);
				orderManagerDao.updateVcPurchase(vcPurchase);
				//2、删除库存表中的单证流水号
				String startNum = vcPurchase.getReceivedStartNum();
				String endNum = vcPurchase.getReceivedEndNum();
				String comCode = vcPurchase.getOrgCode();
				List<VcStorage> list = vcStorageDao.findByConditions(vcPurchase.getVersionCode(), 
						comCode, startNum.substring(startNum.length()-10), endNum.substring(endNum.length()-10), "S1");
				vcStorageDao.deleteVcStorage(list);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	@Override
	public void executeReceivedSubmit(String[] ids, String comCode, String userCode) throws BusinessException {
		Date nowDate = new Date();
		try{
			//循环处理采购单
			for(String id : ids){
				//1、更新采购单状态
				VcPurchase vcPurchase = orderManagerDao.findPurchaseById(Long.valueOf(id));
				vcPurchase.setFlag("4");//4-待审批
				vcPurchase.setCancelReceivedOrp(userCode);
				vcPurchase.setCancelReceivedTime(nowDate);
				vcPurchase.setUpdatedBy(userCode);
				vcPurchase.setDateUpdated(nowDate);
				orderManagerDao.updateVcPurchase(vcPurchase);
				//2、插入审批
				VcApprove vcApprove = new VcApprove();
				vcApprove.setApplyId(Long.valueOf(id));//申请编号
				vcApprove.setApplyType("P");//申请类型：P-采购单
				vcApprove.setCheckOrgId(comCode);//审批机构：当前登录用户的机构代码
				vcApprove.setCheckOprId(userCode);//审批人：当前登录用户代码
				vcApprove.setCheckStatus("0");//审批状态：0-提交审批；1-审批退回；2-审批通过；
				vcApprove.setCheckTime(nowDate);//审批时间
				vcApprove.setCheckExpl("提交");//审批意见
				vcApprove.setCreatedBy(userCode);
				vcApprove.setDateCreated(nowDate);
				vcApprove.setUpdatedBy(userCode);
				vcApprove.setDateUpdated(nowDate);
				approveService.saveVcApprove(vcApprove);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 根据主键查找采购单
	 * @param id
	 * @throws BusinessException
	 */
	public VcPurchase findPurchaseById(Long id) throws BusinessException {
		VcPurchase vcPurchase = null;
		try{
			vcPurchase = orderManagerDao.findPurchaseById(id);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcPurchase;
	}
	
	/**
	 * 查询当前机构及其下级机构的有效库存
	 * @param versionCode
	 * @param orgCode
	 * @return validStoreNum
	 * @throws BusinessException
	 */
	public Long findValidStorageList(String versionCode, String orgCode) throws BusinessException {
		Long validStoreNum = 0L;
		try{
			List<VcStorage> vcStorageList = orderManagerDao.findValidStorageList(versionCode, orgCode);
			for(VcStorage vcStorage : vcStorageList){
				validStoreNum += vcStorage.getDocNum();
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return validStoreNum;
	}
	
	@Override
	public Page queryPurchaseApprove(VcPurchaseVo vcPurchaseVoTemp,String comCode,int pageNo,int pageSize) throws BusinessException{
		Page newpage = null;
		try{
			//查询出当前用户归属机构的所有下级机构
			List<String> childOrgList = vcLevelService.getChildOrgCode(comCode);
			Page page = orderManagerDao.queryPurchaseApprove(vcPurchaseVoTemp, childOrgList, pageNo, pageSize);
			List<VcPurchase> vcPurchases = (List<VcPurchase>)page.getResult();
			List<VcPurchaseVo> vcPurchaseVos = new ArrayList<VcPurchaseVo>();
			//把结果集从VcPurchase对象转换成VcPurchaseVo对象
			for(VcPurchase vcPurchase : vcPurchases){
				VcPurchaseVo vcPurchaseVo = new VcPurchaseVo();
				vcPurchaseVo.setPurchaseId(vcPurchase.getId());//id
				vcPurchaseVo.setPurchaseCode(vcPurchase.getPurchaseCode());//采购单号
				vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());//单证类型代码
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcPurchase.getOrgCode());
				vcPurchaseVo.setOrgName(orgName);//机构名称
				vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());//单证类型代码
				//转换单证类型名称
				String versionName = vcDocVersionInfoService.getVersionName(vcPurchase.getVersionCode());
				vcPurchaseVo.setVersionName(versionName);//单证类型名称
				vcPurchaseVo.setReceivedStartNum(vcPurchase.getReceivedStartNum());//收货起始流水号
				vcPurchaseVo.setReceivedEndNum(vcPurchase.getReceivedEndNum());//收货结束流水号
				vcPurchaseVo.setReceivedQuantity(vcPurchase.getReceivedQuantity());//收货数量
				//转换收货人名称
				String receivedOpr = vcLevelService.getUnitNameByUnitCode(vcPurchase.getReceivedOpr());
				vcPurchaseVo.setReceivedOpr(receivedOpr);//收货人
				vcPurchaseVo.setReceivedTime(vcPurchase.getReceivedTime());//收货时间
				//转换采购单状态
				String flag = pubCodeManagerService.getCodeCname("PurchaseStatus", vcPurchase.getFlag());
				vcPurchaseVo.setFlag(flag);//采购单状态
				
				vcPurchaseVos.add(vcPurchaseVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), vcPurchaseVos);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	}
	
	@Override
	public VcPurchaseVo findPurchaseApprove(Long id) throws BusinessException {
		VcPurchaseVo vcPurchaseVo = null;
		try{
			VcPurchase vcPurchase = orderManagerDao.findPurchaseById(id);
			if(vcPurchase!=null){
				vcPurchaseVo = new VcPurchaseVo();
				vcPurchaseVo.setPurchaseId(vcPurchase.getId());//采购单id
				vcPurchaseVo.setPurchaseCode(vcPurchase.getPurchaseCode());//采购单号
				//转换机构名称
				String orgName = vcLevelService.getUnitNameByUnitCode(vcPurchase.getOrgCode());
				vcPurchaseVo.setOrgName(orgName);//机构名称
				vcPurchaseVo.setVersionCode(vcPurchase.getVersionCode());//单证类型代码
				//转换单证类型名称
				String versionName = vcDocVersionInfoService.getVersionName(vcPurchase.getVersionCode());
				vcPurchaseVo.setVersionName(versionName);//单证类型名称
				vcPurchaseVo.setStartSerialNo(vcPurchase.getStartSerialNo());//起始流水号
				vcPurchaseVo.setReceivedStartNum(vcPurchase.getReceivedStartNum());//收货起始流水号
				vcPurchaseVo.setEndSerialNo(vcPurchase.getEndSerialNo());//结束流水号
				vcPurchaseVo.setReceivedEndNum(vcPurchase.getReceivedEndNum());//收货结束流水号
				vcPurchaseVo.setApplyPrintNum(vcPurchase.getApplyPrintNum());//申印数量
				vcPurchaseVo.setReceivedQuantity(vcPurchase.getReceivedQuantity());//收货数量
				vcPurchaseVo.setUnitPrice(vcPurchase.getUnitPrice());//单价
				vcPurchaseVo.setTotalAmount(vcPurchase.getTotalAmount());//总金额
				//转换收货人名称
				String receivedOpr = vcLevelService.getUnitNameByUnitCode(vcPurchase.getReceivedOpr());
				vcPurchaseVo.setReceivedOpr(receivedOpr);//收货人
				vcPurchaseVo.setReceivedTime(vcPurchase.getReceivedTime());//收货时间
			}
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return vcPurchaseVo;
	}
	
	@Override
	public void executePurchaseApp(VcPurchase vcPurchase, VcApprove vcApprove, String checkStatus) throws BusinessException {
		try{
			//1、更新订单基本信息
			orderManagerDao.updateVcPurchase(vcPurchase);
			//2、插入审批记录
			approveService.saveVcApprove(vcApprove);
			//3、审批通过：保存单证流水号到库存表
			if("2".equals(checkStatus)){//审批动作：1-审批退回；2-审批通过
				Date nowDate = new Date();
				//用户代码fyh123
				//////////////////////////////////////////
				String userCode = vcApprove.getCheckOprId();
				VcStorage vcStorage = new VcStorage();
				vcStorage.setDocVerCode(vcPurchase.getVersionCode());
				vcStorage.setPressBatchNo("000");//批次号，一期上线写死'000'
				String startNum = vcPurchase.getReceivedStartNum();
				String endNum = vcPurchase.getReceivedEndNum();
				vcStorage.setStartNum(startNum.substring(startNum.length()-10));
				vcStorage.setEndNum(endNum.substring(endNum.length()-10));
				vcStorage.setDocNum(vcPurchase.getReceivedQuantity().longValue());
				vcStorage.setDocStatus("S1");//S1-印刷入库
				vcStorage.setOrgCode(vcPurchase.getOrgCode());
				vcStorage.setInStoreTime(nowDate);				
				int maxStoreTime=vcDocMngRuleDao.getMaxStoreTime(vcPurchase.getVersionCode(), "0", vcPurchase.getOrgCode());
				vcStorage.setDeadline(DateUtils.addDay(nowDate, maxStoreTime));//使用截止时间
				vcStorage.setCreateUserCode(userCode);
				vcStorage.setCreateTime(nowDate);
				vcStorage.setModifyUserCode(userCode);
				vcStorage.setModifyTime(nowDate);
				vcStorage.setCreatedBy(userCode);
				vcStorage.setDateCreated(nowDate);
				vcStorage.setUpdatedBy(userCode);
				vcStorage.setDateUpdated(nowDate);
				vcStorageDao.saveVcStorage(vcStorage);
			}
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	@Override
	public void saveVcStorage(VcPurchase vcPurchase, UserInfo userInfo
			) throws BusinessException {
		try{
			//1、更新订单基本信息
			    orderManagerDao.updateVcPurchase(vcPurchase);
				Date nowDate = new Date();
				//用户代码fyh123
				//////////////////////////////////////////
				String userCode = userInfo.getComCode();
				VcStorage vcStorage = new VcStorage();
				vcStorage.setDocVerCode(vcPurchase.getVersionCode());
				vcStorage.setPressBatchNo("000");//批次号，一期上线写死'000'
				String startNum = vcPurchase.getReceivedStartNum();
				String endNum = vcPurchase.getReceivedEndNum();
				vcStorage.setStartNum(startNum.substring(startNum.length()-10));
				vcStorage.setEndNum(endNum.substring(endNum.length()-10));
				vcStorage.setDocNum(vcPurchase.getReceivedQuantity().longValue());
				vcStorage.setDocStatus("S1");//S1-印刷入库
				vcStorage.setOrgCode(vcPurchase.getOrgCode());
				vcStorage.setInStoreTime(nowDate);				
				int maxStoreTime=vcDocMngRuleDao.getMaxStoreTime(vcPurchase.getVersionCode(), "0", vcPurchase.getOrgCode());
				vcStorage.setDeadline(DateUtils.addDay(nowDate, maxStoreTime));//使用截止时间
				vcStorage.setCreateUserCode(userCode);
				vcStorage.setCreateTime(nowDate);
				vcStorage.setModifyUserCode(userCode);
				vcStorage.setModifyTime(nowDate);
				vcStorage.setCreatedBy(userCode);
				vcStorage.setDateCreated(nowDate);
				vcStorage.setUpdatedBy(userCode);
				vcStorage.setDateUpdated(nowDate);
				vcStorageDao.saveVcStorage(vcStorage);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	public OrderManagerDao getOrderManagerDao() {
		return orderManagerDao;
	}

	public void setOrderManagerDao(OrderManagerDao orderManagerDao) {
		this.orderManagerDao = orderManagerDao;
	}
	
	public ApproveService getApproveService() {
		return approveService;
	}

	public void setApproveService(ApproveService approveService) {
		this.approveService = approveService;
	}
	
	public VcStorageDao getVcStorageDao() {
		return vcStorageDao;
	}

	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
	}
	
	public TakeNoDao getTakeNoDao() {
		return takeNoDao;
	}

	public void setTakeNoDao(TakeNoDao takeNoDao) {
		this.takeNoDao = takeNoDao;
	}
	
	public PubCodeManagerService getPubCodeManagerService() {
		return pubCodeManagerService;
	}

	public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
		this.pubCodeManagerService = pubCodeManagerService;
	}
	
	public VcDocVersionInfoService getVcDocVersionInfoService() {
		return vcDocVersionInfoService;
	}

	public void setVcDocVersionInfoService(
			VcDocVersionInfoService vcDocVersionInfoService) {
		this.vcDocVersionInfoService = vcDocVersionInfoService;
	}
	public VcDocVerMaxNumDao getVcDocVerMaxNumDao() {
		return vcDocVerMaxNumDao;
	}

	public void setVcDocVerMaxNumDao(VcDocVerMaxNumDao vcDocVerMaxNumDao) {
		this.vcDocVerMaxNumDao = vcDocVerMaxNumDao;
	}

	public void setVcLevelService(VcLevelService vcLevelService) {
		this.vcLevelService = vcLevelService;
	}

	public void setVcPrintDocVersionService(
			VcPrintDocVersionService vcPrintDocVersionService) {
		this.vcPrintDocVersionService = vcPrintDocVersionService;
	}

	public VcPrintDocVersionService getVcPrintDocVersionService() {
		return vcPrintDocVersionService;
	}

    public void setVcDocMngRuleDao(VcDocMngRuleDao vcDocMngRuleDao) {
        this.vcDocMngRuleDao = vcDocMngRuleDao;
    }

	
	
	
}
