package com.tapi.tcs.vc.visausage.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.Assert;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;
import com.tapi.tcs.vc.visausage.dao.DestroyDao;
import com.tapi.tcs.vc.visausage.service.DestroyService;

public class DestroyServiceImpl implements DestroyService {

	private DestroyDao destroyDao;
	private ApproveDao approveDao;
	private VcStorageDao vcStorageDao;
	private AbnormalVerificationDao abnormalVerificationDao;
	private TakeNoDao takeNoDao;
	private VcLevelDao vcLevelDao;

	@Override
	public Page queryDestroy(VcDestroy vcDestroy, int pageNo, int pageSize,
			String action) throws BusinessException {
		Page page = null;
		try{
			page = destroyDao.queryDestroy(vcDestroy, pageNo, pageSize, action);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 保存/修改销毁
	 * 
	 * @param vcDestroy
	 * @param actionType
	 *            添加/修改
	 * @return 操作结果 成功/失败原因
	 * @throws BusinessException
	 */
	@Override
	public String saveDestroy(VcDestroy vcDestroy,
			List<VcDestroyDet> vcDestroyDets, String actionType,
			String destroyStatus,File file) throws BusinessException {
		try{
			String fileName_old = null;
			String orgCode = vcDestroy.getDestroyOrgCode();
			String userCode = vcDestroy.getDestroyOprCode();
			String destroyReason = vcDestroy.getDestroyReason();
			Date nowDate = new Date();
			
			// 修改时检验状态
			if ("modify".equals(actionType)) {
				String fileName_new = vcDestroy.getFileName();
				String destroyType = vcDestroy.getDestroyType();
				vcDestroy = destroyDao.get(vcDestroy.getId());
				if (file != null) {
					fileName_old = vcDestroy.getFileName();
					File savefile = new File(new File(vcDestroy.getFilePath()),
							fileName_new);
					if (!fileName_old.equals(fileName_new) && savefile.exists()) {
						Assert.isTrue(false, "文件已存在");
					}
					vcDestroy.setFileName(fileName_new);
				}
				// 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
				if (!"1".equals(vcDestroy.getDestroyStatus())
						&& !"3".equals(vcDestroy.getDestroyStatus())) {
					return "只有新建、审批退回的申请可以修改，请刷新后重试";
				} else {
					// 锁表
					destroyDao.lockVcDestroy(vcDestroy.getId());
				}
				vcDestroy.setDestroyType(destroyType); 
			}
			
			// 校验数据
			String checkResult = checkDoc(vcDestroyDets);
			if (checkResult.length() > 0) {
				return checkResult;
			}
			List updateVcStorages = null;
	
			// 2.待审批-提交 保存 VcApprove
			if ("2".equals(destroyStatus)) {
				//vcDestroy.setVcDestroyDets(vcDestroyDets);
				List returnList = chcekSubmit(vcDestroy,vcDestroyDets);
				String resultStr = (String) returnList.get(0);
				if (resultStr.length() > 0) {
					return resultStr;
				} else {
					updateVcStorages = (List) returnList.get(1);
				}
			}
	
			// 单证信息通过检验
			if ("add".equals(actionType)) {
				// 销毁单号
				String destroyCode = takeNoDao.getNo("DD");
				vcDestroy.setDestroyCode(destroyCode);
				// 销毁操作人代码
				// 销毁提交机构代码
				// 销毁提交时间
				vcDestroy.setDestroyAppTime(nowDate);
				
			}
			// 销毁原因
			vcDestroy.setDestroyReason(destroyReason);
			vcDestroy.setModifyOprCode(userCode);
			vcDestroy.setModifyTime(nowDate);
			vcDestroy.setCreatedBy(userCode);
			vcDestroy.setDateCreated(nowDate);
			vcDestroy.setUpdatedBy(userCode);
			vcDestroy.setDateUpdated(nowDate);
			vcDestroy.setDestroyStatus(destroyStatus);
			
			// 修改时删除原VcDestroyDet
			if ("modify".equals(actionType)) {
				String sql = "DELETE FROM vc_destroy_det t WHERE t.id_vc_destroy = ? ";
				destroyDao.updateBySQL(sql, vcDestroy.getId());
				// 新建
				vcDestroy.setDestroyStatus("1");
			}
	
			for (Iterator iterator = vcDestroyDets.iterator(); iterator.hasNext();) {
				VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
				vcDestroyDet.setVcDestroy(vcDestroy);
				// 创建人
				vcDestroyDet.setCreatedBy(userCode);
				// 创建时间
				vcDestroyDet.setDateCreated(nowDate);
				// 修改人
				vcDestroyDet.setUpdatedBy(userCode);
				// 修改时间
				vcDestroyDet.setDateUpdated(nowDate);
			}
			// 2.待审批-提交
			if ("2".equals(destroyStatus)) {
				String upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
				vcDestroy.setApproveOrgCode(upperOrgCode);
				
				//新建状态下-修改-提交 
				vcDestroy.setDestroyStatus("2");
			}
			vcDestroy.setVcDestroyDets(vcDestroyDets);
			destroyDao.save(vcDestroy);
	
			// 2.待审批-提交
			if ("2".equals(destroyStatus)) {
				// 拆分库存
				submitSave(updateVcStorages);
	
				VcApprove vcApprove = new VcApprove();
				// 业务流水
				vcApprove.setApplyId(vcDestroy.getId());
				// 申请类型 D
				vcApprove.setApplyType("D");
				// 审批机构
				vcApprove.setCheckOrgId(orgCode);
				// 审批人
				vcApprove.setCheckOprId(userCode);
				// 审批状态
				vcApprove.setCheckStatus("0");
				// 审批时间
				vcApprove.setCheckTime(nowDate);
				// 审批意见
				vcApprove.setCheckExpl("提交");
				// 创建人
				vcApprove.setCreatedBy(userCode);
				// 创建时间
				vcApprove.setDateCreated(nowDate);
				// 修改人
				vcApprove.setUpdatedBy(userCode);
				// 修改时间
				vcApprove.setDateUpdated(nowDate);
				
				approveDao.save(vcApprove);
			}
			
			if ("modify".equals(actionType) && file != null) {
				File f = new File(vcDestroy.getFilePath() + "/" + fileName_old);
				if (f.isFile() && f.exists()) {
					f.delete();
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("保存数据出错！", e);
		}
		return "操作成功";
	}

	/**
	 * 校验流水对应库存是否存在
	 * 
	 * @param vcDestroyDets
	 * @return List(0)全部通过校验为空字符串，失败返回失败原因的字符串 </p>
	 *         List(1)为一个List包含一个需要拆分库存的List包含
	 *         (0)start(1)end(2)库存对象vcStorage，只有成功时才需要从此取值
	 */
	private List chcekSubmit(VcDestroy vcDestroy,List<VcDestroyDet> vcDestroyDets) throws BusinessException{
		//List<VcDestroyDet> vcDestroyDets = vcDestroy.getVcDestroyDets();
		List returnList = new ArrayList();
		List updateVcStorages = new ArrayList();
		try{
			StringBuffer str = new StringBuffer();
			for (Iterator iterator = vcDestroyDets.iterator(); iterator.hasNext();) {
				VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
	
				String start = vcDestroyDet.getStartNum();
				String end = vcDestroyDet.getEndNum();
	
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("docVerCode", vcDestroyDet.getDocVerCode());
				queryRule.addEqual("pressBatchNo", vcDestroyDet.getPressBatchNo());
				queryRule.addEqual("docStatus", vcDestroyDet.getDocStatus());
				queryRule.addGreaterEqual("endNum", end);
				queryRule.addLessEqual("startNum", start);  
	
				List<VcStorage> vcStorageList = vcStorageDao.find(queryRule);
				int size = vcStorageList.size();
	
				String destroyCode = vcDestroy.getDestroyCode() == null ? ""
						: vcDestroy.getDestroyCode();
				// 1. 未找到库存记录
				if (size == 0) {
					str.append("销毁申请[" + destroyCode + "]\n" + "  流水号[" + start
							+ "-" + end + "]未找到库存\n");
					continue;
				}
				// 2.数据异常
				else if (size > 1) {
					str.append("销毁申请[" + destroyCode + "]\n" + "  流水号[" + start
							+ "-" + end + "]数据异常\n");
					continue;
				}
	
				if (str.length() == 0) {
					List ids = new ArrayList();
	
					for (Iterator iterator2 = vcStorageList.iterator(); iterator2
							.hasNext();) {
						VcStorage vcStorage = (VcStorage) iterator2.next();
						ids.add(vcStorage.getId());
					}
					// 锁表
					vcStorageDao.lockVcStorage(ids.toArray());
					List updateVcStorage = new ArrayList();
	
					updateVcStorage.add(start);
					updateVcStorage.add(end);
					updateVcStorage.add(vcStorageList.get(0));
					updateVcStorage.add(vcDestroy.getDestroyOprCode());
					updateVcStorages.add(updateVcStorage);
				}
			}
	
			returnList.add(str.toString());
			returnList.add(updateVcStorages);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("查询数据出错！", e);
		}
		return returnList;
	}

	/**
	 * 将提交的销毁申请对应的库存记录冻结
	 * 
	 * @param updateVcStorages
	 */
	private void submitSave(List updateVcStorages) throws BusinessException {
		try{
			// 拆分 冻结.
			for (Iterator iterator = updateVcStorages.iterator(); iterator
					.hasNext();) {
				List updateVcStorage = (List) iterator.next();
				String start = updateVcStorage.get(0).toString();
				String end = updateVcStorage.get(1).toString();
				VcStorage vcStorage = (VcStorage) updateVcStorage.get(2);
				String oprCode=updateVcStorage.get(3).toString();
	
				vcStorageDao.splitStorage(start, end, null, null, null, null, "DT",
						vcStorage,oprCode);
	
			} // 拆分完毕
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/**
	 * 检验单证信息 1.是否为空 2.单证类型是否重复 3.流水号是否正确
	 * 
	 * @param vcDestroyDets
	 * @return
	 */
	private String checkDoc(List<VcDestroyDet> vcDestroyDets) {
		StringBuffer str = new StringBuffer();
		if (vcDestroyDets.size() == 0) {
			return "单证信息不能为空";
		}

		/*// 判断单证类型代码是否重复 重写了equals hashCode方法
		HashSet hs = new HashSet(vcDestroyDets);
		if (hs.size() != vcDestroyDets.size()) {
			return "单证类型代码不可重复";
		}*/

		for (Iterator iterator = vcDestroyDets.iterator(); iterator.hasNext();) {
			VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
			String startStr = vcDestroyDet.getStartNum();
			String endStr = vcDestroyDet.getEndNum();
			/*if (startStr.length() != 10 || endStr.length() != 10
					&& (startStr.length() != 16 || endStr.length() != 16)) {
				str
						.append("请输入10位或者16位的流水号[" + startStr + "-" + endStr
								+ "]\n");
				continue;
			}*/

			String startStr_ = startStr.length() == 16 ? startStr.substring(6)
					: startStr;
			String endStr_ = endStr.length() == 16 ? endStr.substring(6)
					: endStr;

			if (!startStr_.matches("[0-9]*") || !endStr_.matches("[0-9]*")) {
				str.append("流水号[" + startStr + "-" + endStr + "]错误\n");
				continue;
			}

			Long start = Long.valueOf(startStr_);
			Long end = Long.valueOf(endStr_);

			if (end < start) {
				str.append("起始流水号大于终止流水号[" + startStr + "-" + endStr + "]");
				continue;
			}
		}
		return str.toString();
	}

	/**
	 * 删除销毁申请
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public String deleteDestroy(String ids) throws BusinessException {
		StringBuffer result = new StringBuffer();
		try{
			// 校验数据
			String[] idsArr = ids.split(",");
			StringBuffer params = new StringBuffer();
			List idList = new ArrayList();
			for (int i = 0; i < idsArr.length; i++) {
				VcDestroy vcDestroy = destroyDao.get(Long.valueOf(idsArr[i]));
	
				// 0删除/1新建/2待审批/3审批退回/4审批通过
				String destroyStatus = vcDestroy.getDestroyStatus();
				String destroyCode = vcDestroy.getDestroyCode();
				if ("0".equals(destroyStatus)) {
					result.append("销毁申请[" + destroyCode + "]已经删除不可重复删除\n");
				} else if ("2".equals(destroyStatus)) {
					result.append("销毁申请[" + destroyCode + "]已经提交不允许删除\n");
				} else if ("4".equals(destroyStatus)) {
					result.append("销毁申请[" + destroyCode + "]已经审批通过不允许删除\n");
				} else {
					idList.add(idsArr[i]);
					params.append("?,");
				}
	
			}
			if (params.length() > 0) {
	
				String sql = "UPDATE  vc_destroy t SET t.destroy_status = '0' WHERE t.id_vc_destroy IN ( "
						+ params.toString().substring(0, params.length()-1) + " )";
				destroyDao.updateBySQL(sql, idList.toArray());
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return result.length() > 0 ? result.toString() : "操作成功";
	}

	/**
	 * 提交销毁申请
	 * 
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String executeSubmitDestroy(String ids, String userCode, String org) throws BusinessException {
		StringBuffer errors = new StringBuffer();
		try{
			// 提交需要冻结库存 校验数据
			String[] idsArr = ids.split(",");
			// 错误信息
			// 最后需要冻结的库存信息
			List updateVcStorages = new ArrayList();
			// 最后需要保存的审批记录 vcApproves
			List<VcApprove> vcApproves = new ArrayList<VcApprove>();
			List<VcDestroy> vcDestroys = new ArrayList<VcDestroy>();
			Date nowDate = new Date();
			for (int i = 0; i < idsArr.length; i++) {
				VcDestroy vcDestroy = destroyDao.get(Long.valueOf(idsArr[i]));
				// 0删除/1新建/2待审批/3审批退回/4审批通过
				String destroyStatus = vcDestroy.getDestroyStatus();
				if ("0".equals(destroyStatus)) {
					errors.append("销毁申请[" + vcDestroy.getDestroyCode()
							+ "]已删除，不能提交\n");
					continue;
				} else if ("2".equals(destroyStatus)) {
					errors.append("销毁申请[" + vcDestroy.getDestroyCode()
							+ "]已提交，不能重复提交\n");
					continue;
				} else if ("4".equals(destroyStatus)) {
					errors.append("销毁申请[" + vcDestroy.getDestroyCode()
							+ "]已审批通过，不能提交\n");
					continue;
				}
				// 校验流水对应库存是否存在
				List<VcDestroyDet> vcDestroyDets=vcDestroy.getVcDestroyDets();
				List returnList = chcekSubmit(vcDestroy,vcDestroyDets); 
	
				String resultStr = (String) returnList.get(0);
				if (resultStr.length() > 0) {
					errors.append(resultStr);
					continue;
				} else {
					// 保存 VcApprove
					VcApprove vcApprove = new VcApprove();
					// 业务流水
					vcApprove.setApplyId(vcDestroy.getId());
					// 申请类型 D
					vcApprove.setApplyType("D");
					// 审批机构
					vcApprove.setCheckOrgId(org);
					// 审批人
					vcApprove.setCheckOprId(userCode);
					// 审批状态
					vcApprove.setCheckStatus("0");
					// 审批时间
					vcApprove.setCheckTime(nowDate);
					// 审批意见
					vcApprove.setCheckExpl("提交");
					// 创建人
					vcApprove.setCreatedBy(userCode);
					// 创建时间
					vcApprove.setDateCreated(nowDate);
					// 修改人
					vcApprove.setUpdatedBy(userCode);
					// 修改时间
					vcApprove.setDateUpdated(nowDate);
					
					vcApproves.add(vcApprove);
	
					vcDestroy.setDestroyStatus("2");
					String upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
					vcDestroy.setApproveOrgCode(upperOrgCode);
					vcDestroys.add(vcDestroy);
					updateVcStorages.addAll((List) returnList.get(1));
	
				}
			}
	
			if (errors.length() > 0) {
				return errors.toString();
			}
			// 保存vcApprove
			approveDao.saveAll(vcApproves);
			// 更新vcDestroy
			destroyDao.saveAll(vcDestroys);
	
			// 将提交的销毁对应的库存记录冻结 拆分
			submitSave(updateVcStorages);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}

		return errors.length() > 0 ? errors.toString() : "操作成功";
	}

	/**
	 * 销毁审批
	 * 
	 * @param vcApprove
	 * @param id
	 * @param actionType
	 * @return
	 */
	@Override
	public String executeApprove(VcApprove vcApprove, String id,
			String actionType) throws BusinessException {
		try{
				VcDestroy vcDestroy = destroyDao.get(Long.valueOf(id));
				String destroyStatus = vcDestroy.getDestroyStatus();
				Date nowDate = new Date();
				// 0删除/1新建/2待审批/3审批退回/4审批通过
				if ("0".equals(destroyStatus) || "1".equals(destroyStatus)) {
					return "未提交的申请不可审批，请刷新后重试";
				} else if ("3".equals(destroyStatus) || "4".equals(destroyStatus)) {
					return "已经审批的申请不可再次审批，请刷新后重试";
				}
				
				String orgCode = vcDestroy.getDestroyOrgCode();//销毁提交机构
				String operatorCode = vcDestroy.getDestroyOprCode();//销毁操作人代码
				String operatorName = vcLevelDao.getUnitNameByUnitCode(operatorCode);//销毁操作人名称
		
				// 退回 需要将冻结的库存恢复、合并
				if ("3".equals(actionType)) {
					List<VcDestroyDet> vcDestroyDets = vcDestroy.getVcDestroyDets();
					for (Iterator iterator = vcDestroyDets.iterator(); iterator
							.hasNext();) {
						VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
		
						String start = vcDestroyDet.getStartNum();
						String end = vcDestroyDet.getEndNum();
		
						// 找到已经冻结的库存
						QueryRule queryRule = QueryRule.getInstance();
						queryRule.addEqual("docVerCode", vcDestroyDet.getDocVerCode());
						queryRule.addEqual("pressBatchNo", vcDestroyDet
								.getPressBatchNo());
						queryRule.addEqual("docStatus", "DT");
						queryRule.addLessEqual("startNum", start);
						queryRule.addGreaterEqual("endNum", end);
						List<VcStorage> vcStorageList_ = vcStorageDao.find(queryRule);
						int size_ = vcStorageList_.size();
		
						if (size_ != 1) {
							return "销毁冻结数据错误";
						}
						// 被冻结的库存信息
						VcStorage vcStorage = vcStorageList_.get(0);
		
						// 根据被冻结的库存信息进行合并
						vcStorageDao.mergeStorage(start, end, vcDestroyDet
								.getDocVerCode(), vcDestroyDet.getPressBatchNo(),
								vcDestroy.getDestroyOrgCode(), "DT", vcDestroyDet
										.getDocStatus(), vcApprove.getCheckOrgId(),vcStorage.getDeadline());
						// 合并结束
					}
					// 审批状态
					vcApprove.setCheckStatus("1");
				}
				// 通过 - 需要将冻结的库存物理删除
				else if ("4".equals(actionType)) {
					List<VcDestroyDet> vcDestroyDets = vcDestroy.getVcDestroyDets();
					List<VcAbnormalVerification> vcAbnormalVerifications = new ArrayList<VcAbnormalVerification>();
					
					for (Iterator iterator = vcDestroyDets.iterator(); iterator
							.hasNext();) {
						VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
		
						String startStr = vcDestroyDet.getStartNum();
						String endStr = vcDestroyDet.getEndNum();
						Long start = Long.valueOf(startStr);
						Long end = Long.valueOf(endStr);
						
						String docVerCode = vcDestroyDet.getDocVerCode();//单证类型代码
						String pressBatchNo = vcDestroyDet.getPressBatchNo();//印刷批次
		
						// 找到已经冻结的库存
						QueryRule queryRule = QueryRule.getInstance();
						queryRule.addEqual("docVerCode", vcDestroyDet.getDocVerCode());
						queryRule.addEqual("pressBatchNo", vcDestroyDet
								.getPressBatchNo());
						queryRule.addEqual("docStatus", "DT");
						queryRule.addLessEqual("startNum", startStr);
						queryRule.addGreaterEqual("endNum", endStr);
						List<VcStorage> vcStorageList_ = vcStorageDao.find(queryRule);
						int size_ = vcStorageList_.size();
		
						if (size_ != 1) {
							return "销毁冻结数据错误";
						}
						// 保存VC_ABNORMAL_VERIFICATION
						Long totalAmount = end - start + 1;
						DecimalFormat df = new DecimalFormat("0");
						df.setMinimumIntegerDigits(startStr.length());
						
						for (int i = 0; i < totalAmount; i++) {
							VcAbnormalVerification vcAbnormalVerification = new VcAbnormalVerification();
							// vcAbnormalVerification.setBusinessNo("");
							vcAbnormalVerification.setDocNum(df.format(start + i));
							vcAbnormalVerification.setDocStatus("D");
							vcAbnormalVerification.setDocVerCode(vcDestroyDet
									.getDocVerCode());
							vcAbnormalVerification.setPressBatchNo(vcDestroyDet
									.getPressBatchNo());
							vcAbnormalVerification.setType("D");
							vcAbnormalVerification.setVerifiedOrgCode(vcDestroy
									.getDestroyOrgCode());
							vcAbnormalVerification.setVerifiedOprCode(vcDestroy
									.getDestroyOprCode());
							vcAbnormalVerification.setVerifiedTime(nowDate);
							vcAbnormalVerification.setCreatedBy(vcDestroy
									.getDestroyOprCode());
							vcAbnormalVerification.setDateCreated(nowDate);
							vcAbnormalVerification.setUpdatedBy(vcDestroy
									.getDestroyOprCode());
							vcAbnormalVerification.setDateUpdated(nowDate);
							vcAbnormalVerifications.add(vcAbnormalVerification);
						}
						// 删除被冻结的库存信息
						VcStorage vcStorage = vcStorageList_.get(0);
						vcStorageDao.delete(vcStorage);
						//**************对地税平台的处理 begin*************
						//通过单证类型判断是否为发票
						boolean isInvoice = abnormalVerificationDao.checkDocType(docVerCode);
						//上海、福建、航保中心地区：发票销毁做缴销处理
						if(isInvoice && orgCode.length()>=4){
							if(SysConst.COMCODE_SHANGHAI.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_HANGBAO.equals(orgCode.substring(0,4))
									//modify by zhxiao3 VC-119福建发票缴销功能完善 begin
									||SysConst.COMCODE_FJ.equals(orgCode.substring(0,4))){
									//modify by zhxiao3 VC-119福建发票缴销功能完善 end
								VcInvoiceRevoke vcInvoiceRevoke = new VcInvoiceRevoke();
								vcInvoiceRevoke.setDocVerCode(docVerCode);//单证类型代码
								vcInvoiceRevoke.setInvoiceCode(pressBatchNo);//发票代码
								vcInvoiceRevoke.setStartNum(startStr);//起始号码
								vcInvoiceRevoke.setEndNum(endStr);//终止号码
								vcInvoiceRevoke.setQuantity(vcDestroyDet.getDestroyNum().intValue());//发票分数
								vcInvoiceRevoke.setRevokeOprCode(operatorCode);//缴销人代码
								vcInvoiceRevoke.setRevokeOprName(operatorName);//缴销人名称
								vcInvoiceRevoke.setStatus("1");//有效
								vcInvoiceRevoke.setIsUploadPlat("0");//未上传平台
								vcInvoiceRevoke.setRevokeType("50");//缴销类型代码：50-空白发票；70-遗失发票
								vcInvoiceRevoke.setRevokeCode(vcDestroy.getDestroyType());//缴销情形代码
								vcInvoiceRevoke.setOrgCode(orgCode);//缴销机构代码
								vcInvoiceRevoke.setLostDate(null);//遗失日期
								vcInvoiceRevoke.setRegisterDate(nowDate);//登记日期：审批通过日期
								vcInvoiceRevoke.setCreatedBy(vcApprove.getCheckOprId());
								vcInvoiceRevoke.setDateCreated(nowDate);
								vcInvoiceRevoke.setUpdatedBy(vcApprove.getCheckOprId());
								vcInvoiceRevoke.setDateUpdated(nowDate);
								abnormalVerificationDao.save(vcInvoiceRevoke);
							}
						
						//山西、河南、重庆、天津、黑龙江
						else if(SysConst.COMCODE_SX.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_HeNan.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_ChongQing.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_TJ.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_HLJ.equals(orgCode.substring(0, 4))){
							String TaxpayerName = "";//纳税人名称
							String TaxpayerCode = "";//纳税人识别号
							PubCompany PubCompany = abnormalVerificationDao.queryPubCompany(orgCode);
							TaxpayerCode = abnormalVerificationDao.queryPubCompany(orgCode).getTaxNumber();
							TaxpayerName = abnormalVerificationDao.queryPubCompany(orgCode).getCompanyCname();
							for (int i = 0; i < totalAmount; i++) {
								VcInvoicePrint vcInvoicePrint = new VcInvoicePrint();
								vcInvoicePrint.setTaxpayerName(TaxpayerName);
								vcInvoicePrint.setTaxpayerCode(TaxpayerCode);
								vcInvoicePrint.setDocVerCode(vcDestroyDet.getDocVerCode());
								vcInvoicePrint.setOrgCode(orgCode);
								vcInvoicePrint.setInvoiceCode(vcDestroyDet.getPressBatchNo());
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
					// 保存VC_ABNORMAL_VERIFICATION
					abnormalVerificationDao.saveAll(vcAbnormalVerifications);
					
					// 更新vcDestroy
					vcDestroy.setApproveOprCode(vcApprove.getCheckOprId());
					vcDestroy.setApproveOrgCode(vcApprove.getCheckOrgId());
					vcDestroy.setApproveTime(nowDate);
					// 审批状态
					vcApprove.setCheckStatus("2");
				}
		
				// 更新vcDestroy
				vcDestroy.setDestroyStatus(actionType);
				destroyDao.save(vcDestroy);
		
				// 保存 vcApprove
				// 业务流水
				vcApprove.setApplyId(vcDestroy.getId());
				// 申请类型 D 暂
				vcApprove.setApplyType("D");
				// 审批机构
				// 审批人
				// 审批状态
				// 审批时间
				vcApprove.setCheckTime(nowDate);
				// 创建人
				vcApprove.setCreatedBy(vcApprove.getCheckOprId());
				// 创建时间
				vcApprove.setDateCreated(nowDate);
				// 修改人
				vcApprove.setUpdatedBy(vcApprove.getCheckOprId());
				// 修改时间
				vcApprove.setDateUpdated(nowDate);
				
				approveDao.save(vcApprove);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作失败！", e);
		}
		return "操作成功";
	}
	
	public List initDestroyView(Long id,String action) throws BusinessException{
		List list = null;
		try{
			list = destroyDao.initDestroyView(id, action);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}

	public void setDestroyDao(DestroyDao destroyDao) {
		this.destroyDao = destroyDao;
	}

	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}

	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
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
