package com.tapi.tcs.vc.newInvoice.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.newInvoice.dao.NewDestroyDao;
import com.tapi.tcs.vc.newInvoice.dao.QueryInvoiceDao;
import com.tapi.tcs.vc.newInvoice.service.QueryInvoiceService;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcLost;
import com.tapi.tcs.vc.schema.model.VcLostDet;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.visausage.dao.VcLostDao;

public class QueryInvoiceServiceImpl implements QueryInvoiceService{
	private QueryInvoiceDao  queryInvoiceDao;
	private VcLostDao vcLostDao;
	private TakeNoDao takeNoDao;
	private VcLevelDao vcLevelDao;
	private ApproveDao approveDao;
	private NewDestroyDao newDestroyDao;
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
			List<VcAbnormalVerification> updateAbnormalVerification = new ArrayList<VcAbnormalVerification>();
			for (Iterator iterator = VcLostDets.iterator(); iterator.hasNext();) {
				VcLostDet vcLostDet = (VcLostDet) iterator.next();
				String docStatus = vcLostDet.getDocStatus();
	
				String startStr = vcLostDet.getStartNum();
				String endStr = vcLostDet.getEndNum(); 
				
				Long start = Long.valueOf(startStr);
				Long end = Long.valueOf(endStr);
				df.setMinimumIntegerDigits(startStr.length());
					// 判断非正常核销表是否存在状态为ZPL(遗失冻结状态的单证)
				updateAbnormalVerification= newDestroyDao.queryVcAbnormalVerification(vcLostDet.getDocVerCode(), startStr, vcLostDet.getPressBatchNo(), "ZPL");
				int amount = updateAbnormalVerification.size();
					if (amount == end - start + 1) {
						//退回
						//遗失申请审核退回后单证恢复为之前的状态  by lfengxia
						if ("3".equals(actionType)) {
							for(int i=0;i<updateAbnormalVerification.size();i++){
								VcAbnormalVerification vcAbnormalVerification=updateAbnormalVerification.get(i);
								if(vcAbnormalVerification.getDocStatus().equals("ZPL")){
									vcAbnormalVerification.setDocStatus("C1");
									vcAbnormalVerification.setDateUpdated(nowDate);
									queryInvoiceDao.update(vcAbnormalVerification);
								}
							}
						}
						// 通过						
						else if ("4".equals(actionType)) {
							for(int i=0;i<updateAbnormalVerification.size();i++){
								VcAbnormalVerification vcAbnormalVerification=updateAbnormalVerification.get(i);
								if(vcAbnormalVerification.getDocStatus().equals("ZPL")){
									vcAbnormalVerification.setDateUpdated(nowDate);
									vcAbnormalVerification.setVerifiedTime(nowDate);
									vcAbnormalVerification.setDocStatus("L3");
									queryInvoiceDao.update(vcAbnormalVerification);
								}
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
				} 
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return "操作成功";
	}
	@Override
	public boolean queryInvoice(String employid) throws BusinessException {
		boolean result=false;
		try {
			result= queryInvoiceDao.queryInvoice(employid);
		} catch (Exception e) {
			throw new BusinessException("查询异常！", e);
		}
		return result;
	}
	@Override
	public String executeSubmitLostApply(String ids, String userCode,
			String orgCode) throws BusinessException {
		String result = "操作成功";
		try{
			StringBuffer error = new StringBuffer();
			if (Beans.isNotEmpty(ids)) {
				String[] idArr = ids.split(",");
				Date nowDate = new Date();
				for (int i = 0; i < idArr.length; i++) {
					String id = idArr[i];
					List updateVcAbnormalVerification = new ArrayList();
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
	
					updateVcAbnormalVerification = (List<VcAbnormalVerification>) resultList.get(1);
					
					vcLost.setLostStatus("2");
					String upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
					vcLost.setApproveOrgCode(upperOrgCode);
					vcLostDao.update(vcLost);
					// 校验通过 保存
					updateAbnormalVerification(vcLost, updateVcAbnormalVerification,orgCode,userCode,nowDate);
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
	@Override
	public String saveLostApply(VcLost vcLost, List<VcLostDet> vcLostDets,
			String actionType, String actionType2, File file)
			throws BusinessException {
		String result = "操作成功";
		Date nowDate = new Date();
		try {
			String orgCode = vcLost.getLostOrgCode();
			// 用户
			String oprCode = vcLost.getLostOprCode();
			// 原因
			String reason = vcLost.getLostReason();
			// 原遗失证明材料扫描件名称
			String fileName_old = "";
			if ("modify".equals(actionType2)){
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
			//
			if (vcLostDets == null && vcLostDets.size() == 0) {
				return "请输入单证信息";
			}
			List updateVcAbnormalVerification = new ArrayList();
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
			updateVcAbnormalVerification = (List<VcAbnormalVerification>) resultList.get(1);
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
						updateAbnormalVerification(vcLost, updateVcAbnormalVerification,orgCode,oprCode,nowDate);
					}
					if ("modify".equals(actionType2) && file != null) {
						File f = new File(vcLost.getFilePath() + "/" + fileName_old);
						if (f.isFile() && f.exists()) {
							f.delete();
						}
					}
		} catch (Exception e) {
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
			List<VcAbnormalVerification> updateAbnormalVerification = new ArrayList<VcAbnormalVerification>();
			// 0.成功
			// 1.起始流水号大于终止流水号，请重新输入
			// 2.存在不可用单证状态
			// 3.未找到库存记录
			List<String> result = new ArrayList<String>();
			for (int i = 0; i < vcLostDets.size(); i++) {
				VcLostDet vcLostDet = vcLostDets.get(i);
				String docVerCode=vcLostDet.getDocVerCode();
				String startStr = vcLostDet.getStartNum();
				String endStr = vcLostDet.getEndNum();
				String pressBatchNo=vcLostDet.getPressBatchNo();
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
				// 单证为状态为"A" 单证类型代码＋印刷批次＋遗失号段的所有单证流水
				// ＋当前操作员归属机构＋单证状态（=A），如果查询结果>0 则通过
				// 当前操作员归属机构 待完善
					//查询非正常核销表内是否存在
					updateAbnormalVerification =queryInvoiceDao.queryVcAbnormalVerification(docVerCode, startStr, pressBatchNo);
					// 判断库存是否存在
					int amount = updateAbnormalVerification.size();
					if (end - start + 1 != amount) {
						// 2. 存在不可用单证状态
						result.add("2");
						continue;
					}
			}
			resultList.add(result);
			resultList.add(updateAbnormalVerification);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return resultList;
	}
	public void updateAbnormalVerification(VcLost vcLost,
			List<VcAbnormalVerification> updateVcAbnormalVerification,String orgCode,String oprCode,Date nowDate) 
	throws BusinessException {
		try {
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
			for( int i=0;i<updateVcAbnormalVerification.size();i++){
				VcAbnormalVerification result=updateVcAbnormalVerification.get(i);
				if(result.getDocStatus().equals("C1")){
					result.setDateUpdated(nowDate);
					result.setDocStatus("ZPL");
					result.setVerifiedTime(nowDate);
					queryInvoiceDao.update(result);
				}
			}
		} catch (Exception e) {
			throw new BusinessException("操作失败！", e);
		}
	}
	public void setQueryInvoiceDao(QueryInvoiceDao queryInvoiceDao) {
		this.queryInvoiceDao = queryInvoiceDao;
	}
	public void setVcLostDao(VcLostDao vcLostDao) {
		this.vcLostDao = vcLostDao;
	}
	public void setTakeNoDao(TakeNoDao takeNoDao) {
		this.takeNoDao = takeNoDao;
	}
	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}
	public void setNewDestroyDao(NewDestroyDao newDestroyDao) {
		this.newDestroyDao = newDestroyDao;
	}
	
	
	
}
