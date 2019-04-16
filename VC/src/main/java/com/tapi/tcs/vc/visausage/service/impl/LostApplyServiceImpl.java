package com.tapi.tcs.vc.visausage.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcLost;
import com.tapi.tcs.vc.schema.model.VcLostDet;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcAvailableDocDao;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;
import com.tapi.tcs.vc.visausage.dao.VcLostDao;
import com.tapi.tcs.vc.visausage.service.LostApplyService;

public class LostApplyServiceImpl implements LostApplyService {

	private VcLostDao vcLostDao;
	private VcAvailableDocDao vcAvailableDocDao;
	private VcStorageDao vcStorageDao;
	private ApproveDao approveDao;
	private AbnormalVerificationDao abnormalVerificationDao;
	private TakeNoDao takeNoDao;
	private VcLevelDao vcLevelDao;
	
	/**
	 * 查询遗失申请信息
	 * 
	 * @param vcLost
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Page queryLostApply(VcLost vcLost, int pageNo, int pageSize) throws BusinessException {
		Page page = null;
		try{
			if(StringUtils.isNotEmpty(vcLost.getInvoiceFlag())){
				page = vcLostDao.findVcInvoiceLost(vcLost, pageNo, pageSize, null);
			}else{
				page = vcLostDao.findVcLost(vcLost, pageNo, pageSize, null);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 遗失申请新增/修改
	 * 
	 * @param vcLost
	 * @param vcLostDets
	 * @param actionType
	 *            保存/提交
	 * @param actionType2
	 *            新增/修改
	 * @return
	 * @throws Exception
	 */
	@Override
	public String saveLostApply(VcLost vcLost, List<VcLostDet> vcLostDets,
			String actionType, String actionType2, File file) throws BusinessException {
		String result = "操作成功";
		Date nowDate = new Date();
		try{
			// 机构
			String orgCode = vcLost.getLostOrgCode();
			// 用户
			String oprCode = vcLost.getLostOprCode();
			// 原因
			String reason = vcLost.getLostReason();
			// 原遗失证明材料扫描件名称
			String fileName_old = "";
	
			if ("modify".equals(actionType2)) {
				String fileName_new = vcLost.getFileName();
				vcLost = vcLostDao.get(vcLost.getId());
				fileName_old = vcLost.getFileName();
	
				if (file != null) {
					File savefile = new File(new File(vcLost.getFilePath()),
							fileName_new);
					if (!fileName_old.equals(fileName_new) && savefile.exists()) {
						throw new BusinessException("上传的文件已[" + fileName_new
								+ "]存在");
					}
					vcLost.setFileName(fileName_new);
				}
				// 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
				if (!"1".equals(vcLost.getLostStatus())
						&& !"3".equals(vcLost.getLostStatus())) {
					return "只有新建、审批退回的申请可以修改，请刷新后重试";
				}
			}
			
			// 最后需要更新的 VcAvailableDoc
			List<VcAvailableDoc> updateVcAvailableDocs = new ArrayList<VcAvailableDoc>();
			// 最后需要拆分的 vcStorage 信息 包括vcLostDet的 start、end和vcStorage对象
			List updateVcStorages = new ArrayList();
	
			// 校验数据
			if (vcLostDets == null && vcLostDets.size() == 0) {
				return "请输入单证信息";
			}
			/*// 判断单证类型代码是否重复 重写了equals hashCode方法
			HashSet hs = new HashSet(vcLostDets);
			if (hs.size() != vcLostDets.size()) {
				return "单证类型代码不可重复";
			}*/
			// 校验流水号
			List resultList = checkData(vcLost, vcLostDets);
			List<String> checkResult = (List<String>) resultList.get(0);
			StringBuffer str = new StringBuffer();
			// 0.成功
			// 1.起始流水号大于终止流水号，请重新输入
			// 2.存在不可用单证状态
			// 3.未找到库存记录
			for (int i = 0; i < checkResult.size(); i++) {
				String resultFlag = checkResult.get(i);
				VcLostDet tempVcLostDet = vcLostDets.get(i);
				String start = tempVcLostDet.getStartNum();
				String end = tempVcLostDet.getEndNum();
				if ("1".equals(resultFlag)) {
					str.append("起始流水号[" + start + "]大于终止流水号[" + end + "]，请重新输入");
				} else if ("2".equals(resultFlag)) {
					str.append("流水号[" + start + "-" + end + "]存在不可用单证状态");
				} else if ("3".equals(resultFlag)) {
					str.append("流水号[" + start + "-" + end + "]未找到库存记录");
				}else  if ("4".equals(resultFlag)) {
                    str.append("库存记录异常：流水号[" + start + "-" + end + "]存在多个库存记录");
                }
			}
			if (str.length() > 0) {
				return str.toString();
			}
			updateVcAvailableDocs = (List<VcAvailableDoc>) resultList.get(1);
			updateVcStorages = (List) resultList.get(2);
	
			// 校验通过
	
			// 保存VC_LOST 和 VC_LOST_DET
			if ("add".equals(actionType2)) {
				// 遗失单号
				String lostCode = takeNoDao.getNo("DL");
				vcLost.setLostCode(lostCode);
				// 遗失操作人代码
				// 遗失提交机构代码
				// 遗失提交时间
				vcLost.setLostTime(nowDate);
				// 遗失原因
				vcLost.setCreatedBy(oprCode);
				vcLost.setDateCreated(nowDate);
			
			}
			
			// 修改人代码
			vcLost.setModifyOprCode(oprCode);
			// 修改时间
			vcLost.setModifyTime(nowDate);
			vcLost.setUpdatedBy(oprCode);
			vcLost.setDateUpdated(nowDate);
			// // 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过） action 对应 1.保存 2.提交
			vcLost.setLostStatus(actionType);
			// 遗失原因
			vcLost.setLostReason(reason);
			
			// 修改时删除原VcLostDet
			if ("modify".equals(actionType2)) {
				String sql = "DELETE FROM vc_lost_det t WHERE t.id_vc_lost = ? ";
				vcLostDao.updateBySql(sql, vcLost.getId());
			}
			for (Iterator iterator = vcLostDets.iterator(); iterator.hasNext();) {
				VcLostDet vcLostDet = (VcLostDet) iterator.next();
				vcLostDet.setVcLost(vcLost);
				// 创建人
				vcLostDet.setCreatedBy(vcLost.getLostOprCode());
				// 创建时间
				vcLostDet.setDateCreated(vcLost.getLostTime());
				// 修改人
				vcLostDet.setUpdatedBy(vcLost.getLostOprCode());
				// 修改时间
				vcLostDet.setDateUpdated(vcLost.getLostTime());
			}
			
			if ("2".equals(actionType)) {
				String upperOrgCode = vcLevelDao.getUpperOrgCode(oprCode);
				vcLost.setApproveOrgCode(upperOrgCode);
			}
			vcLost.setVcLostDets(vcLostDets);
			vcLostDao.save(vcLost);
	
			// 1.保存 2.提交
			if ("2".equals(actionType)) {
				updateStorage(vcLost, updateVcAvailableDocs, updateVcStorages,orgCode,oprCode,nowDate);
			}
			if ("modify".equals(actionType2) && file != null) {
				File f = new File(vcLost.getFilePath() + "/" + fileName_old);
				if (f.isFile() && f.exists()) {
					f.delete();
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return result;
	}

	/**
	 * 检验单证信息 1.是否为空 2.单证类型是否重复 3.流水号是否正确
	 * 
	 * @param vcDestroyDets
	 * @return
	 */

	/**
	 * 
	 * @param vcLost
	 * @param vcLostDets
	 * @return List(0) 0.成功 1.起始流水号大于终止流水号，请重新输入 2.存在不可用单证状态 3.未找到库存记录
	 */
	private List checkData(VcLost vcLost, List<VcLostDet> vcLostDets) throws BusinessException {
		List resultList = new ArrayList();
		try{
			List<VcAvailableDoc> updateVcAvailableDocs = new ArrayList<VcAvailableDoc>();
			List updateVcStorages = new ArrayList();
	
			// 0.成功
			// 1.起始流水号大于终止流水号，请重新输入
			// 2.存在不可用单证状态
			// 3.未找到库存记录
			List<String> result = new ArrayList<String>();
			for (int i = 0; i < vcLostDets.size(); i++) {
				VcLostDet vcLostDet = vcLostDets.get(i);
	
				String startStr = vcLostDet.getStartNum();
				String endStr = vcLostDet.getEndNum();
	
				// 用户可能输入10位或16位 需要截取
				Long start = startStr.length() == 16 ? Long.valueOf(startStr
						.substring(6)) : Long.valueOf(startStr);
				Long end = endStr.length() == 16 ? Long
						.valueOf(endStr.substring(6)) : Long.valueOf(endStr);
	
				if (start > end) {
					// 1. 起始流水号大于终止流水号，请重新输入
					result.add("1");
					continue;
					//return resultList;
				}
				String docStatus = vcLostDet.getDocStatus();
				// 单证为状态为"A" 单证类型代码＋印刷批次＋遗失号段的所有单证流水
				// ＋当前操作员归属机构＋单证状态（=A），如果查询结果>0 则通过
				// 当前操作员归属机构 待完善
				if ("A".equals(docStatus)) {
					QueryRule queryRule = QueryRule.getInstance();
					queryRule.addEqual("docStatus", "A");
					Object[] values = { startStr, endStr };
					queryRule.addBetween("docNum", values);
					queryRule.addEqual("docVerCode", vcLostDet.getDocVerCode());
					queryRule.addEqual("pressBatchNo", vcLostDet.getPressBatchNo());
					List<VcAvailableDoc> vcAvailableDocs = vcAvailableDocDao
							.find(queryRule);
					// 判断库存是否存在
					int amount = vcAvailableDocs.size();
					if (end - start + 1 != amount) {
						// 2. 存在不可用单证状态
						result.add("2");
						continue;
					} else {
						List ids = new ArrayList();
						for (Iterator iterator = vcAvailableDocs.iterator(); iterator
								.hasNext();) {
							VcAvailableDoc vcAvailableDoc = (VcAvailableDoc) iterator
									.next();
							ids.add(vcAvailableDoc.getId());
						}
						// 锁表
						vcAvailableDocDao.lockVcAvailableDoc(ids.toArray());
						updateVcAvailableDocs.addAll(vcAvailableDocs);
						result.add("0");
					}
				}
				// 如果单证状态为“库存（含S1、S2、S3）”，
				// 则：从VC_STORAGE（单证库存表）里使用如下查询条件进行查询：
				// 单证类型代码＋印刷批次＋遗失号段的所有单证流水号＋当前操作员归属机构＋单证状态（＝S1、S2或S3）
				// 当前操作员归属机构 待完善
				else if ("S1".equals(docStatus) || "S2".equals(docStatus)
						|| "S3".equals(docStatus)) {
	
					String[] parms = { docStatus };
					List<VcStorage> vcStorageList = vcStorageDao.isExistAll(startStr,
							endStr, vcLostDet.getDocVerCode(), vcLostDet
									.getPressBatchNo(), vcLost.getLostOrgCode(),true,
							parms);
	
					if (vcStorageList.size() == 0) {
						// 3.未找到库存记录
						result.add("3");
						continue;
					} else if (vcStorageList.size() > 1) {
						// 4.数据库记录异常
						result.add("4");
						continue;
					}
					List ids = new ArrayList();
	
					for (Iterator iterator = vcStorageList.iterator(); iterator
							.hasNext();) {
						VcStorage vcStorage = (VcStorage) iterator.next();
						ids.add(vcStorage.getId());
					}
					// 锁表
					vcStorageDao.lockVcStorage(ids.toArray());
					List updateVcStorage = new ArrayList();
	
					updateVcStorage.add(startStr);
					updateVcStorage.add(endStr);
					updateVcStorage.add(vcStorageList.get(0));
					updateVcStorages.add(updateVcStorage);
					result.add("0");
				}
			}
			resultList.add(result);
			resultList.add(updateVcAvailableDocs);
			resultList.add(updateVcStorages);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return resultList;
	}

	/**
	 * 提交遗失申请
	 * 
	 * @param vcLost
	 * @param updateVcAvailableDocs
	 * @param updateVcStorages
	 */
	public void updateStorage(VcLost vcLost,
			List<VcAvailableDoc> updateVcAvailableDocs, List updateVcStorages,String orgCode,String oprCode,Date nowDate) 
	throws BusinessException {
		try{
			VcApprove vcApprove = new VcApprove();
			// 业务流水
			vcApprove.setApplyId(vcLost.getId());
			// 申请类型 L-遗失核销
			vcApprove.setApplyType("L");
			// 审批机构
			vcApprove.setCheckOrgId(orgCode);
			// 审批人
			vcApprove.setCheckOprId(oprCode);
			// 审批状态
			vcApprove.setCheckStatus("0");
			// 审批时间
			vcApprove.setCheckTime(nowDate);
			// 审批意见
			vcApprove.setCheckExpl("提交");
			// 创建人
			vcApprove.setCreatedBy(oprCode);
			// 创建时间
			vcApprove.setDateCreated(nowDate);
			// 修改人
			vcApprove.setUpdatedBy(oprCode);
			// 修改时间
			vcApprove.setDateUpdated(nowDate);
			
			approveDao.save(vcApprove);
			
			// 提交操作 需要更新 VC_AVAILABLE_DOC（可使用单证表）
			// 单证状态 DOC_STATUS LT 遗失冻结
			for (Iterator iterator = updateVcAvailableDocs.iterator(); iterator
					.hasNext();) {
				VcAvailableDoc vcAvailableDoc = (VcAvailableDoc) iterator.next();
				if ("A".equals(vcAvailableDoc.getDocStatus())) {
					vcAvailableDoc.setDocStatus("LT");
					vcAvailableDocDao.update(vcAvailableDoc);
				} else {
					Assert.isTrue(false, "单证类型错误，请重新操作");
				}
			}
			// 拆分 冻结.
			for (Iterator iterator = updateVcStorages.iterator(); iterator
					.hasNext();) {
				List updateVcStorage = (List) iterator.next();
				String start = updateVcStorage.get(0).toString();
				String end = updateVcStorage.get(1).toString();
				VcStorage vcStorage = (VcStorage) updateVcStorage.get(2);
				vcStorageDao.splitStorage(start, end, null, null, null, null, "LT",
						vcStorage,oprCode);
			} // 拆分完毕
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
	}

	/**
	 * 查看遗失
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	@Override
	public List viewLostApply(String id)throws BusinessException {
		List list = null;
		try{
			list = vcLostDao.viewLostApply(Long.valueOf(id));
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * 提交遗失申请
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String executeSubmitLostApply(String ids,String userCode,String orgCode) throws BusinessException {
		String result = "操作成功";
		try{
			StringBuffer error = new StringBuffer();
			if (Beans.isNotEmpty(ids)) {
				String[] idArr = ids.split(",");
				Date nowDate = new Date();
				for (int i = 0; i < idArr.length; i++) {
					String id = idArr[i];
					List<VcAvailableDoc> updateVcAvailableDocs = new ArrayList<VcAvailableDoc>();
					List updateVcStorages = new ArrayList();
					// 锁表
					vcLostDao.lockVcLost(Long.valueOf(id));
					VcLost vcLost = vcLostDao.get(Long.valueOf(id));
					List<VcLostDet> vcLostDets = vcLost.getVcLostDets();
					// 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过
					String lostStatus = vcLost.getLostStatus();
	
					if ("0".equals(lostStatus)) {
						error.append("遗失申请单[" + vcLost.getLostCode()
								+ "]已删除，提交失败\n");
						continue;
					} else if ("2".equals(lostStatus)) {
						error.append("遗失申请单[" + vcLost.getLostCode()
								+ "]已提交，提交失败\n");
						continue;
					} else if ("4".equals(lostStatus)) {
						error.append("遗失申请单[" + vcLost.getLostCode()
								+ "]已审批通过，提交失败\n");
						continue;
					}
	
					// 校验数据
					List resultList = checkData(vcLost, vcLostDets);
					List<String> checkResult = (List<String>) resultList.get(0);
					// 0.成功
					// 1.起始流水号大于终止流水号，请重新输入
					// 2.存在不可用单证状态
					// 3.未找到库存记录
	
					StringBuffer error2 = new StringBuffer();
					for (int j = 0; j < checkResult.size(); j++) {
						VcLostDet vcLostDet = vcLostDets.get(j);
						String checkFlag = checkResult.get(j);
						String start = vcLostDet.getStartNum();
						String end = vcLostDet.getEndNum();
	
						if ("1".equals(checkFlag)) {
							error2.append("遗失申请单[" + vcLost.getLostCode()
									+ "]中起始流水号[" + start + "]大于终止流水号[" + end
									+ "]，请修改\n");
						} else if ("2".equals(checkFlag)) {
							error2.append("遗失申请单[" + vcLost.getLostCode() + "]流水号["
									+ start + "-" + end + "]存在不可用单证，请修改\n");
						} else if ("3".equals(checkFlag)) {
							error2.append("遗失申请单[" + vcLost.getLostCode() + "]流水号["
									+ start + "-" + end + "]未找到库存记录，请修改\n");
						}
					}
	
					error.append(error2);
	
					if (error2.length() > 0) {
						continue;
					}
	
					updateVcAvailableDocs = (List<VcAvailableDoc>) resultList
							.get(1);
					updateVcStorages = (List) resultList.get(2);
					
					vcLost.setLostStatus("2");
					String upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
					vcLost.setApproveOrgCode(upperOrgCode);
					
					vcLostDao.update(vcLost);
					// 校验通过 保存
					updateStorage(vcLost, updateVcAvailableDocs, updateVcStorages,orgCode,userCode,nowDate);
				}
			} else {
				return "操作失败，无效数据";
			}
			if (error.length() > 0) {
				result = error.toString();
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return result;
	}

	/**
	 * 删除LostApply
	 */
	@Override
	public String deleteLostApply(String id) throws BusinessException {
		StringBuffer result = new StringBuffer();
		try{
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				VcLost vcLost = vcLostDao.get(Long.valueOf(ids[i]));
				if (vcLost != null) {
					// 锁表
					vcLostDao.lockVcLost(Long.valueOf(ids[i]));
					// 判断是否符合条件 0删除/1新建/2待审批/3审批退回/4审批通过
					String lostStatus = vcLost.getLostStatus();
					if ("0".equals(lostStatus)) {
						result.append("遗失申请[" + vcLost.getLostCode() + "]已经删除\n");
					} else if ("2".equals(lostStatus)) {
						result.append("遗失申请[" + vcLost.getLostCode()
								+ "]已经提交，不可删除\n");
					} else if ("4".equals(lostStatus)) {
						result.append("遗失申请[" + vcLost.getLostCode()
								+ "]已经审批通过，不可删除\n");
					} else {
						vcLost.setLostStatus("0");
						vcLostDao.update(vcLost);
					}
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return result.length() == 0 ? "操作成功" : result.toString();
	}

	/**
	 * 遗失审批查询
	 * 
	 * @param vcLost
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException 
	 */
	public Page queryLostApprove(VcLost vcLost, int pageNo, int pageSize) throws BusinessException {
		Page page = null;
		try{
			page = vcLostDao.findVcLost(vcLost, pageNo, pageSize, "approve");
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 审批
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String executeApprove(VcApprove vcApprove, String id,
			String actionType) throws BusinessException {
		try{
			if (!id.matches("[0-9]*")) {
				return "数据错误";
			}
			// 锁表
			vcLostDao.lockVcLost(Long.valueOf(id));
			VcLost vcLost = vcLostDao.get(Long.valueOf(id));
			String status = vcLost.getLostStatus();
			// 判断是否符合条件 0删除/1新建/2待审批/3审批退回/4审批通过
			if (!"2".equals(status)) {
				return "状态错误，请刷新后重试";
			}
			
			String orgCode = vcLost.getLostOrgCode();//遗失申请提交机构
			String operatorCode = vcLost.getLostOprCode();//遗失申请操作人代码
			String operatorName = vcLevelDao.getUnitNameByUnitCode(operatorCode);//遗失申请操作人名称
			
			Date nowDate = new Date();
			// 校验数据
			List<VcLostDet> VcLostDets = vcLost.getVcLostDets();
			DecimalFormat df = new DecimalFormat("0");
			for (Iterator iterator = VcLostDets.iterator(); iterator.hasNext();) {
				VcLostDet vcLostDet = (VcLostDet) iterator.next();
				String docStatus = vcLostDet.getDocStatus();
	
				String startStr = vcLostDet.getStartNum();
				String endStr = vcLostDet.getEndNum(); 
				
				Long start = Long.valueOf(startStr);
				Long end = Long.valueOf(endStr);
				df.setMinimumIntegerDigits(startStr.length());
				QueryRule queryRule = null;
				// docStatus 为 A
				if ("A".equals(docStatus)) {
					// 状态为LT的数量是否为 end-start+1
					queryRule = QueryRule.getInstance();
					queryRule.addEqual("docStatus", "LT");
					Object[] values = { startStr, endStr};
					queryRule.addBetween("docNum", values);
					queryRule.addEqual("docVerCode", vcLostDet.getDocVerCode());
					queryRule.addEqual("pressBatchNo", vcLostDet.getPressBatchNo());
					List<VcAvailableDoc> vcAvailableDocs = vcAvailableDocDao
							.find(queryRule);
					// 判断库存是否存在
					int amount = vcAvailableDocs.size();
					List<VcAbnormalVerification> vcAbnormalVerifications = new ArrayList<VcAbnormalVerification>();
					for (int k=0;k<amount;k++){
						VcAvailableDoc vc=vcAvailableDocs.get(k);
					if (amount == end - start + 1) {
						String sql = "";
						// 退回
						//遗失申请审核退回后单证恢复为之前的状态  by lfengxia
						if ("3".equals(actionType)) {
							sql = "UPDATE vc_available_doc t SET t.doc_status = 'A' ,t.date_updated=sysdate WHERE t.doc_ver_code= ? and t.press_batch_no= ? and t.doc_num =? ";
						}
						// 通过						
						else if ("4".equals(actionType)) {
							sql = "DELETE FROM vc_available_doc t WHERE t.doc_ver_code= ? and t.press_batch_no= ? and t.doc_num =? ";
							
							//Long totalAmount = end - start + 1;
							//for (int i = 0; i < totalAmount; i++) {
								VcAbnormalVerification vcAbnormalVerification = new VcAbnormalVerification();
								
								//创建人、创建时间、修改人、修改时间
								vcAbnormalVerification.setCreatedBy(vcApprove.getCheckOprId());
								vcAbnormalVerification.setDateCreated(nowDate);
								vcAbnormalVerification.setUpdatedBy(vcApprove.getCheckOprId());
								vcAbnormalVerification.setDateUpdated(nowDate);
								
								vcAbnormalVerification.setDocVerCode(vcLostDet
										.getDocVerCode());
								vcAbnormalVerification.setPressBatchNo(vcLostDet
										.getPressBatchNo());
								vcAbnormalVerification.setDocNum(df.format(start + k));
								vcAbnormalVerification.setVerifiedOprCode(vcLost
										.getLostOprCode());
								vcAbnormalVerification.setVerifiedOrgCode(vcLost
										.getLostOrgCode());
								vcAbnormalVerification.setVerifiedTime(nowDate);
								// 单证状态 L1：库存遗失 L2：可使用遗失
								vcAbnormalVerification.setDocStatus("L2");
								vcAbnormalVerification.setType("L");
								vcAbnormalVerification.setCreatedBy(vcLost
										.getLostOprCode());
								vcAbnormalVerification.setDateCreated(nowDate);
								vcAbnormalVerification.setUpdatedBy(vcLost
										.getLostOprCode());
								vcAbnormalVerification.setDateUpdated(nowDate);
								vcAbnormalVerifications.add(vcAbnormalVerification);
							//}
						}
						abnormalVerificationDao.saveAll(vcAbnormalVerifications);
						// Object[] params = {start+"", end+""};
						vcAvailableDocDao.updateBySql(sql,vc.getDocVerCode(),vc.getPressBatchNo(), vc.getDocNum());
					}}
					
					
					// docStatus 为 S1 S2 S3
				} else {
					queryRule = QueryRule.getInstance();
					queryRule.addEqual("docVerCode", vcLostDet.getDocVerCode());
					queryRule.addEqual("pressBatchNo", vcLostDet.getPressBatchNo());
					queryRule.addEqual("docStatus", "LT");
					queryRule.addLessEqual("startNum", startStr);
					queryRule.addGreaterEqual("endNum", endStr);
					List<VcStorage> vcStorageList = vcStorageDao.find(queryRule);
					if (vcStorageList.size() == 1) {
						VcStorage vcStorage = vcStorageList.get(0);
						// 3审批退回/4审批通过
						if ("4".equals(actionType)) {
							List<VcAbnormalVerification> vcAbnormalVerifications = new ArrayList<VcAbnormalVerification>();
							Long totalAmount = end - start + 1;
							for (int i = 0; i < totalAmount; i++) {
								VcAbnormalVerification vcAbnormalVerification = new VcAbnormalVerification();
								
								//创建人、创建时间、修改人、修改时间
								vcAbnormalVerification.setCreatedBy(vcApprove.getCheckOprId());
								vcAbnormalVerification.setDateCreated(nowDate);
								vcAbnormalVerification.setUpdatedBy(vcApprove.getCheckOprId());
								vcAbnormalVerification.setDateUpdated(nowDate);
								
								vcAbnormalVerification.setDocVerCode(vcStorage
										.getDocVerCode());
								vcAbnormalVerification.setPressBatchNo(vcStorage
										.getPressBatchNo());
								vcAbnormalVerification.setDocNum(df.format(start+i));
								// 单证状态 L1：库存遗失 L2：可使用遗失
								vcAbnormalVerification.setDocStatus("L1");
								vcAbnormalVerification.setVerifiedOprCode(vcLost
										.getLostOprCode());
								vcAbnormalVerification.setVerifiedOrgCode(vcLost
										.getLostOrgCode());
								vcAbnormalVerification.setVerifiedTime(nowDate);
								vcAbnormalVerification.setType("L");
								vcAbnormalVerifications.add(vcAbnormalVerification);
							}
	
							abnormalVerificationDao
									.saveAll(vcAbnormalVerifications);
	
							vcStorageDao.delete(vcStorage);
						} else if ("3".equals(actionType)) {
							// 解冻结 合并
							vcStorageDao.mergeStorage(startStr, endStr, vcLostDet
									.getDocVerCode(), vcLostDet.getPressBatchNo(),
									vcLost.getLostOrgCode(), "LT", vcLostDet
											.getDocStatus(), vcApprove
											.getCheckOrgId(),vcStorage.getDeadline());
	
						}
					}
				}
				// 更新 VC_LOST 保存VC_APPROVE
				if("4".equals(actionType) || "3".equals(actionType)){
					// 遗失审批操作人代码
					vcLost.setApproveOprCode(vcApprove.getCheckOprId());
					// 遗失审批机构代码
					vcLost.setApproveOrgCode(vcApprove.getCheckOrgId());
					// 遗失审批时间
					vcLost.setApproveTime(nowDate);	
				}
				// 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
				vcLost.setLostStatus(actionType);
				vcLostDao.update(vcLostDet);
	
				vcApprove.setApplyId(vcLost.getId());
				vcApprove.setApplyType("L");
				if ("3".equals(actionType)) {
					vcApprove.setCheckStatus("1");
				} else if ("4".equals(actionType)) {
					vcApprove.setCheckStatus("2");
				}
				vcApprove.setCheckTime(nowDate);
				vcApprove.setCreatedBy(vcApprove.getCheckOprId());
				vcApprove.setDateCreated(nowDate);
				vcApprove.setUpdatedBy(vcApprove.getCheckOprId());
				vcApprove.setDateUpdated(nowDate);
				approveDao.save(vcApprove);
				//**************对地税平台的处理 begin*************
				// 3审批退回/4审批通过
				if ("4".equals(actionType)){
					//通过单证类型判断是否为发票
					boolean isInvoice = abnormalVerificationDao.checkDocType(vcLostDet.getDocVerCode());
					//上海、福建、航保中心地区：发票遗失做缴销处理
					if(isInvoice && orgCode.length()>=4){
						if(SysConst.COMCODE_SHANGHAI.equals(orgCode.substring(0,4))
								||SysConst.COMCODE_HANGBAO.equals(orgCode.substring(0,4))
								//modify by zhxiao3 VC-119福建发票缴销功能完善 begin
								||SysConst.COMCODE_FJ.equals(orgCode.substring(0,4))){
								//modify by zhxiao3 VC-119福建发票缴销功能完善 end
							VcInvoiceRevoke vcInvoiceRevoke = new VcInvoiceRevoke();
							vcInvoiceRevoke.setDocVerCode(vcLostDet.getDocVerCode());//单证类型代码
							vcInvoiceRevoke.setInvoiceCode(vcLostDet.getPressBatchNo());//发票代码
							vcInvoiceRevoke.setStartNum(startStr);//起始号码
							vcInvoiceRevoke.setEndNum(endStr);//终止号码
							vcInvoiceRevoke.setQuantity(vcLostDet.getLostNum().intValue());//发票分数
							vcInvoiceRevoke.setRevokeOprCode(operatorCode);//缴销人代码
							vcInvoiceRevoke.setRevokeOprName(operatorName);//缴销人名称
							vcInvoiceRevoke.setStatus("1");//有效
							vcInvoiceRevoke.setIsUploadPlat("0");//未上传平台
							vcInvoiceRevoke.setRevokeType("70");//缴销类型代码：50-空白发票；70-遗失发票
							vcInvoiceRevoke.setRevokeCode("7");//缴销情形代码
							vcInvoiceRevoke.setOrgCode(orgCode);//缴销机构代码
							vcInvoiceRevoke.setLostDate(vcLost.getLostDate());//遗失日期
							vcInvoiceRevoke.setRegisterDate(nowDate);//登记日期：审批通过日期
							vcInvoiceRevoke.setCreatedBy(vcApprove.getCheckOprId());
							vcInvoiceRevoke.setDateCreated(nowDate);
							vcInvoiceRevoke.setUpdatedBy(vcApprove.getCheckOprId());
							vcInvoiceRevoke.setDateUpdated(nowDate);
							abnormalVerificationDao.save(vcInvoiceRevoke);
						}
						//山西、河南、重庆、天津
						else if(SysConst.COMCODE_SX.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_HeNan.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_ChongQing.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_TJ.equals(orgCode.substring(0, 4))){
							Long totalAmount = end - start + 1;
							String TaxpayerName = "";//纳税人名称
							String TaxpayerCode = "";//纳税人识别号
							PubCompany PubCompany = abnormalVerificationDao.queryPubCompany(orgCode);
							TaxpayerCode = abnormalVerificationDao.queryPubCompany(orgCode).getTaxNumber();
							TaxpayerName = abnormalVerificationDao.queryPubCompany(orgCode).getCompanyCname();
							for (int i = 0; i < totalAmount; i++) {
								VcInvoicePrint vcInvoicePrint = new VcInvoicePrint();
								vcInvoicePrint.setTaxpayerName(TaxpayerName);
								vcInvoicePrint.setTaxpayerCode(TaxpayerCode);
								vcInvoicePrint.setDocVerCode(vcLostDet.getDocVerCode());
								vcInvoicePrint.setOrgCode(orgCode);
								vcInvoicePrint.setInvoiceCode(vcLostDet.getPressBatchNo());
								vcInvoicePrint.setInvoiceNo(df.format(start + i));
								vcInvoicePrint.setPrintDate(nowDate);
								vcInvoicePrint.setPayerName("空白作废");
								vcInvoicePrint.setAmount(new BigDecimal("0.00"));
								vcInvoicePrint.setPrintKind("4");
								vcInvoicePrint.setIsUploadPlat("0");
								vcInvoicePrint.setStatus("1");
								vcInvoicePrint.setRecipientName(operatorName);//收款人名称
								vcInvoicePrint.setCanceledOprCode(operatorCode);// 作废人代码
								vcInvoicePrint.setCanceledOpr(operatorName);// 作废人名称
								vcInvoicePrint.setCanceldDate(nowDate);
								vcInvoicePrint.setCreatedBy(vcApprove.getCheckOprId());
								vcInvoicePrint.setDateCreated(nowDate);
								vcInvoicePrint.setUpdatedBy(vcApprove.getCheckOprId());
								vcInvoicePrint.setDateUpdated(nowDate);
								if(SysConst.COMCODE_HeNan.equals(orgCode.substring(0, 4))){								   
								    //vcInvoicePrint.setTaxOrgCode(); //"纳税人主管税务机关代码不能为空；";
								    vcInvoicePrint.setIndustryCode("020302");//"行业代码不能为空；";
								}
								abnormalVerificationDao.save(vcInvoicePrint);
							}
						}
					}
					//**************对地税平台的处理 end*************
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return "操作成功";
	}

	public void setVcLostDao(VcLostDao vcLostDao) {
		this.vcLostDao = vcLostDao;
	}

	public void setVcAvailableDocDao(VcAvailableDocDao vcAvailableDocDao) {
		this.vcAvailableDocDao = vcAvailableDocDao;
	}

	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
	}

	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}

	public void setAbnormalVerificationDao(
			AbnormalVerificationDao abnormalVerificationDao) {
		this.abnormalVerificationDao = abnormalVerificationDao;
	}

	public void setTakeNoDao(TakeNoDao takeNoDao) {
		this.takeNoDao = takeNoDao;
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
}
