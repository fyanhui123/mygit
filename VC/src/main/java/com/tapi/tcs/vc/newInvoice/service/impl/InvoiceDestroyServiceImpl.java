package com.tapi.tcs.vc.newInvoice.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.newInvoice.dao.InvoiceDestroyDao;
import com.tapi.tcs.vc.newInvoice.dao.NewDestroyDao;
import com.tapi.tcs.vc.newInvoice.dao.QueryInvoiceDao;
import com.tapi.tcs.vc.newInvoice.service.InvoiceDestroyService;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;
import com.tapi.tcs.vc.visausage.dao.DestroyDao;

public class InvoiceDestroyServiceImpl implements InvoiceDestroyService{
	private DestroyDao destroyDao;
	private TakeNoDao takeNoDao;
	private VcLevelDao vcLevelDao;
	private ApproveDao approveDao;
	private InvoiceDestroyDao invoiceDestroyDao;
	private QueryInvoiceDao queryInvoiceDao;
	private NewDestroyDao newDestroyDao;
	
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
				String operatorName = newDestroyDao.getUnitNameByUnitCode(operatorCode);//销毁操作人名称
				List<VcAbnormalVerification> updateAbnormalVerification = new ArrayList<VcAbnormalVerification>();
				// 退回 需要将冻结的库存恢复
				if ("3".equals(actionType)) {
					List<VcDestroyDet> vcDestroyDets = vcDestroy.getVcDestroyDets();
					for (Iterator iterator = vcDestroyDets.iterator(); iterator
							.hasNext();) {
						VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
		
						String start = vcDestroyDet.getStartNum();
						String end = vcDestroyDet.getEndNum();
		
						// 找到状态为销毁冻结的库存
						updateAbnormalVerification =newDestroyDao.queryVcAbnormalVerification(vcDestroyDet.getDocVerCode(), start, vcDestroyDet.getPressBatchNo(),"ZPD" );
						int size_ = updateAbnormalVerification.size();
						
						if (size_ != 1) {
							return "销毁冻结数据错误";
						}
						//修改非正常核销表被退回的数据状态
						for(int i=0;i<updateAbnormalVerification.size();i++){
							VcAbnormalVerification vcAbnormalVerification=updateAbnormalVerification.get(i);
							if(vcAbnormalVerification.getDocStatus().equals("ZPD")){
								vcAbnormalVerification.setDocStatus("JX");//修改状态为缴销状态
								vcAbnormalVerification.setDateUpdated(nowDate);
								queryInvoiceDao.update(vcAbnormalVerification);
							}
						}
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
						updateAbnormalVerification =newDestroyDao.queryVcAbnormalVerification(vcDestroyDet.getDocVerCode(), startStr, vcDestroyDet.getPressBatchNo(), "ZPD");
						int size_ = updateAbnormalVerification.size();
		
						if (size_ != 1) {
							return "销毁冻结数据错误";
						}
						//修改非正常核销表被
						for(int i=0;i<updateAbnormalVerification.size();i++){
							VcAbnormalVerification vcAbnormalVerification=updateAbnormalVerification.get(i);
							if(vcAbnormalVerification.getDocStatus().equals("ZPD")){
								vcAbnormalVerification.setDocStatus("ZD");//修改状态为缴销状态
								vcAbnormalVerification.setVerifiedTime(nowDate);
								vcAbnormalVerification.setDateCreated(nowDate);
								vcAbnormalVerification.setDateUpdated(nowDate);
								queryInvoiceDao.update(vcAbnormalVerification);
							}
						}
					}				
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
				// 申请类型 j 暂
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
	@Override
	public String executeSubmitDestroy(String ids, String userCode, String org)
			throws BusinessException {
		StringBuffer errors = new StringBuffer();
		try{
			// 提交需要冻结信息 校验数据
			String[] idsArr = ids.split(",");
			// 错误信息
			// 最后需要冻结的信息
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
				// 校验流水对应是否存在
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
	@Override
	public String saveDestroy(VcDestroy vcDestroy,
			List<VcDestroyDet> vcDestroyDets, String actionType,
			String destroyStatus, File file) throws BusinessException {
		
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
			List updateVcabnormal = null;
	
			// 2.待审批-提交 保存 VcApprove
			if ("2".equals(destroyStatus)) {
				//vcDestroy.setVcDestroyDets(vcDestroyDets);
				List returnList = chcekSubmit(vcDestroy,vcDestroyDets);
				String resultStr = (String) returnList.get(0);
				if (resultStr.length() > 0) {
					return resultStr;
				} else {
					updateVcabnormal = (List) returnList.get(1);
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
				submitSave(updateVcabnormal);
				
				VcApprove vcApprove = new VcApprove();
				// 业务流水
				vcApprove.setApplyId(vcDestroy.getId());
				// 申请类型 D
				vcApprove.setApplyType("D");//销毁的申请类型
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
	 * 校验流水对应非正常核销表是否存在
	 * 
	 * @param vcDestroyDets
	 * @return List(0)全部通过校验为空字符串，失败返回失败原因的字符串 </p>
	 *         List(1)为一个List包含一个需要拆分库存的List包含
	 *         (0)start(1)end(2)库存对象vcabnormal，只有成功时才需要从此取值
	 */
	private List chcekSubmit(VcDestroy vcDestroy,List<VcDestroyDet> vcDestroyDets) throws BusinessException{
		//List<VcDestroyDet> vcDestroyDets = vcDestroy.getVcDestroyDets();
		List returnList = new ArrayList();
		List<VcAbnormalVerification>list=new ArrayList<VcAbnormalVerification>();
		try{
			StringBuffer str = new StringBuffer();
			for (Iterator iterator = vcDestroyDets.iterator(); iterator.hasNext();) {
				VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
				
				String start = vcDestroyDet.getStartNum();
				String end = vcDestroyDet.getEndNum();
				//查询非正常核销表里面已经缴销的数据
				list=newDestroyDao.queryVcAbnormalVerification(vcDestroyDet.getDocVerCode(), start, vcDestroyDet.getPressBatchNo(),"JX");
				int size = list.size();
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
			}
			returnList.add(str.toString());
			returnList.add(list);
		}catch(Exception e){
			throw new BusinessException("查询数据出错！", e);
		}
		return returnList;
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

		for (Iterator iterator = vcDestroyDets.iterator(); iterator.hasNext();) {
			VcDestroyDet vcDestroyDet = (VcDestroyDet) iterator.next();
			String startStr = vcDestroyDet.getStartNum();
			String endStr = vcDestroyDet.getEndNum();
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
	 * 将提交的销毁申请对应的库存记录冻结
	 * 
	 * @param VcAbnormalVerification
	 */
	private void submitSave(List<VcAbnormalVerification> updateVcAbnormalVerification) throws BusinessException {
		Date nowDate = new Date();
		try{
			for( int i=0;i<updateVcAbnormalVerification.size();i++){
				VcAbnormalVerification result=updateVcAbnormalVerification.get(i);
				if(result.getDocStatus().equals("JX")){
					result.setDocStatus("ZPD");//修改状态为销毁冻结状态
					result.setDateUpdated(nowDate);
					queryInvoiceDao.update(result);
				}
			}
		}catch(Exception e){
			throw new BusinessException("修改为销毁冻结状态异常");
		}
	}

	@Override
	public Page queryAbNormalList(Map<String, Object> params, int pageNo,
			int pageSize) throws BusinessException {
		try{
	        return invoiceDestroyDao.queryAbNormalList(params, pageNo, pageSize);	 
	        }catch(DaoException e){
	     	   throw new BusinessException(e.getMessage(),e);
	        }
	}
	public void setDestroyDao(DestroyDao destroyDao) {
		this.destroyDao = destroyDao;
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
	public void setInvoiceDestroyDao(InvoiceDestroyDao invoiceDestroyDao) {
		this.invoiceDestroyDao = invoiceDestroyDao;
	}
	public void setQueryInvoiceDao(QueryInvoiceDao queryInvoiceDao) {
		this.queryInvoiceDao = queryInvoiceDao;
	}
	public void setNewDestroyDao(NewDestroyDao newDestroyDao) {
		this.newDestroyDao = newDestroyDao;
	}
	
}
