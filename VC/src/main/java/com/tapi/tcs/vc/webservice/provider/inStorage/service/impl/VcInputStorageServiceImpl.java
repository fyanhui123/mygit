package com.tapi.tcs.vc.webservice.provider.inStorage.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.HeadDTO;
import com.tapi.tcs.common.webservice.ResponseDTO;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.GroupDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageRequest;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageRequestDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageResponse;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageResponseDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.dao.inputStorage;
import com.tapi.tcs.vc.webservice.provider.inStorage.service.VcInputStorageService;

public class VcInputStorageServiceImpl implements VcInputStorageService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private inputStorage inputStorageDao;
	private VcStorageDao vcStorageDao;

	@Override
	public InputStorageResponse saveStorageHandle(
			InputStorageRequest inputStorageRequest) throws DaoException {
		logger.error("进入saveStorageHandle方法");
		logger.error("请求inputStorageRequest-->" + inputStorageRequest);
		InputStorageRequestDTO requestBody = inputStorageRequest
				.getInputStorageRequestDTO();
		HeadDTO responseHead = inputStorageRequest.getHeadDTO();
		ResponseDTO responses = new ResponseDTO();
		InputStorageResponse response = new InputStorageResponse();
		InputStorageResponseDTO responseBody = new InputStorageResponseDTO();
		responses.setfLOWINTIME(responseHead.getfLOWINTIME());
		responses.setrEQSERIALNO(responseHead.getrEQSERIALNO());
		responses.setrEQUESTTYPE(responseHead.getrEQUESTTYPE());
		try {
			Date nowDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			int currenYear = calendar.get(Calendar.YEAR);
			calendar.set(Calendar.YEAR, currenYear + 2);
			java.sql.Date newDate = new java.sql.Date(
					calendar.getTimeInMillis());// 得到截止日期（两年后）
			List<VcStorage> inputStorageResultList = new ArrayList<VcStorage>();
			List<VcStorage> inputStorageResultLists = new ArrayList<VcStorage>();
			String flag = "2";
			List<GroupDTO> GROUPList = requestBody.getrESULTLIST();
			String docVerCode = requestBody.getfPLXDM();
			String qshm = null;
			String zzhm = null;
			String userCode = null;
			String instCODE = null;
			String docment = null;
			if (GROUPList != null && GROUPList.size() > 0) {
				for (int i = 0; i < GROUPList.size(); i++) {
					GroupDTO dto = GROUPList.get(i);
					VcStorage vcStorage = new VcStorage();
					String fpdm = dto.getfPDM(); // 发票代码
					qshm = dto.getqSHM(); // 起始号段
					zzhm = dto.getzZHM(); // 终止号段
					String fpfs = dto.getfPFS(); // 发票份数
					String lgryCODE = dto.getlGRYCODE(); // 领购人员代码
					instCODE = dto.getiNSTCODE(); // 所属机构
					userCode = inputStorageDao.queryUserInfo(lgryCODE, "2",
							instCODE);
					if (null==userCode ||"".equals(userCode)) {
						throw new  BusinessException("该机构下无此发票管理员-->"+lgryCODE);
					} 
					logger.error("转换后的用户代码————>" + userCode);
					if (inputStorageDao.queryLevel(instCODE) > 0) { // 查询该机构下有没有发票管理员
						docment = inputStorageDao.queryDocverCode(docVerCode);
						logger.error("转换后的单证类型", docment);
						vcStorage.setDocVerCode(docment);// 单证类型
						vcStorage.setPressBatchNo(fpdm);
						vcStorage.setStartNum(qshm);
						vcStorage.setEndNum(zzhm);
						vcStorage.setDocNum(Long.parseLong(fpfs));
						vcStorage.setDocStatus("S1");
						vcStorage.setOrgCode(instCODE);
						vcStorage.setInStoreTime(nowDate);
						vcStorage.setDeadline(newDate); // 截止时间
						vcStorage.setCreateUserCode(userCode);
						vcStorage.setCreateTime(nowDate);
						vcStorage.setModifyUserCode(lgryCODE);
						vcStorage.setModifyTime(nowDate);
						vcStorage.setCreatedBy(userCode);
						vcStorage.setDateCreated(nowDate);
						vcStorage.setUpdatedBy(userCode);
						vcStorage.setDateUpdated(nowDate);
						inputStorageResultList.add(vcStorage);
						
						StringBuffer sb = new StringBuffer();
						sb.append(" SELECT COUNT(*) FROM VC_STORAGE t ");
						sb.append(" WHERE t.doc_ver_code = ?  ");
						sb.append(" AND t.press_batch_no = ? ");
						sb.append(" and  (( ? between t.start_num and t.end_num)or( ? between t.start_num and t.end_num) ) ");
						Object[] params = { vcStorage.getDocVerCode(),
								vcStorage.getPressBatchNo(),
								vcStorage.getStartNum(), vcStorage.getEndNum()
								};
						int amount = Integer.valueOf(vcStorageDao
								.findBySQL(sb.toString(), params).get(0)
								.toString());
						if (amount < 1) {
							inputStorageDao.inMergeStorage(
									vcStorage.getStartNum(), vcStorage.getEndNum(),
									vcStorage.getDocVerCode(),
									vcStorage.getPressBatchNo(),
									vcStorage.getOrgCode(), "S1", null,
									vcStorage.getCreatedBy(),"1");
							responseBody.seteRRORINFO("发票入库成功");
							responseBody.setrESULTTYPE("000");
							response.sethEAD(responses);
							response.setrESULT(responseBody);
						} else if (amount == 1) {
							// 库存内存在有交叉的情况
							List<VcStorage> vcStorages = inputStorageDao
									.queryVcStroage(vcStorage.getDocVerCode(),
											vcStorage.getStartNum(),
											vcStorage.getEndNum(),
											vcStorage.getPressBatchNo());
							logger.error("查询库存数量-->"+vcStorages.size());
							for (int k = 0; k < vcStorages.size(); k++) {
								VcStorage vs = vcStorages.get(k);
								String doc = vs.getDocVerCode();// 单证类型
								String starNum = vs.getStartNum();// 库存内的起始号段
								String endNum = vs.getEndNum(); // 库存内的终止号段
								String pressNo = vs.getPressBatchNo();// 单证印刷批次
								long id = vs.getId();
								Integer tempStart = Integer.parseInt(starNum);// 库存内起始号码
								Integer tempEnd = Integer.parseInt(endNum); // 库存内终止号码
								Integer start = Integer.parseInt(qshm);// 起始号码
								Integer end = Integer.parseInt(zzhm);// 终止号码
								String strminNum = inputStorageDao.min(starNum,
										qshm);// 返回最小值
								String endmaxNum = inputStorageDao.max(endNum,
										zzhm);// 结束号段最大值
								Integer minStr = Integer.parseInt(strminNum);
								Integer maxEnd = Integer.parseInt(endmaxNum);
								long docNum = maxEnd - minStr + 1;
								VcStorage newVs = new VcStorage();
								newVs.setDocVerCode(docment);
								newVs.setPressBatchNo(pressNo);
								newVs.setStartNum(String.valueOf(strminNum)); // 放进去最小值
								newVs.setEndNum(String.valueOf(endmaxNum)); // 最大的放进去最大值;
								newVs.setDocNum(docNum);
								newVs.setDocStatus("S1");
								newVs.setOrgCode(instCODE);
								newVs.setInStoreTime(nowDate);
								newVs.setDeadline(newDate);
								newVs.setCreateUserCode(userCode);
								newVs.setCreateTime(nowDate);
								newVs.setModifyUserCode(userCode);
								newVs.setModifyTime(nowDate);
								newVs.setCreatedBy(userCode);
								newVs.setDateCreated(nowDate);
								newVs.setUpdatedBy(userCode);
								newVs.setDateUpdated(nowDate);
								inputStorageResultLists.add(newVs);
								try {
									// 删除原有库存
									inputStorageDao.deleteStorage(id);
									inputStorageDao.saveVcStorageList(inputStorageResultLists);
									responseBody.seteRRORINFO("发票入库成功");
									responseBody.setrESULTTYPE("000");
									response.sethEAD(responses);
									response.setrESULT(responseBody);
								}catch (Exception e) {
									throw new DaoException("删除数据出错！", e);
								}
							}
						}else if(amount==2){
							try {
								    List<VcStorage> vcStoragesTemp=inputStorageDao
											.queryVcStroage(vcStorage.getDocVerCode(),
													vcStorage.getStartNum(),
													vcStorage.getEndNum(),
													vcStorage.getPressBatchNo());
								    VcStorage vcStorage1 = (VcStorage) vcStoragesTemp.get(0);
						            VcStorage vcStorage2 = (VcStorage) vcStoragesTemp.get(1);
						            String strminNum0 = inputStorageDao.min(vcStorage1.getStartNum(),
						            		vcStorage.getStartNum());// 较小值
						            long strminNum = Long.parseLong(inputStorageDao.min(vcStorage2.getStartNum(),
						            		strminNum0));// 返回最小值
						            String endmaxNum0=inputStorageDao.max(vcStorage.getEndNum(), vcStorage1.getEndNum());//较大值
						            long strMaxNum=Long.parseLong(inputStorageDao.max(endmaxNum0, vcStorage2.getEndNum()));//较大值
						            long docNum =strMaxNum-strminNum+1;
						            // 删除已经存在的 插入库存表
						            VcStorage newVs = new VcStorage();
						            docment =vcStorage.getDocVerCode();
						            newVs.setDocVerCode(docment);
									newVs.setPressBatchNo(vcStorage.getPressBatchNo());
									newVs.setStartNum(String.valueOf(strminNum)); // 放进去最小值
									newVs.setEndNum(String.valueOf(strMaxNum)); // 最大的放进去最大值;
									newVs.setDocNum(docNum);
									newVs.setDocStatus("S1");
									newVs.setOrgCode(instCODE);
									newVs.setInStoreTime(nowDate);
									newVs.setDeadline(newDate);
									newVs.setCreateUserCode(userCode);
									newVs.setCreateTime(nowDate);
									newVs.setModifyUserCode(userCode);
									newVs.setModifyTime(nowDate);
									newVs.setCreatedBy(userCode);
									newVs.setDateCreated(nowDate);
									newVs.setUpdatedBy(userCode);
									newVs.setDateUpdated(nowDate);
									inputStorageResultLists.add(newVs);
						            inputStorageDao.deleteAll(vcStoragesTemp);
						            inputStorageDao.saveVcStorageList(inputStorageResultLists);
						            responseBody.seteRRORINFO("发票入库成功");
						    		responseBody.setrESULTTYPE("000");
						    		response.sethEAD(responses);
						    		response.setrESULT(responseBody);
							} catch (Exception e) {
								throw new  BusinessException(qshm+"--"+zzhm+"发票入库失败");
							}
						} else {
							throw new  BusinessException("发票入库失败,库存内已经存在多个号段"); 
						}
					} else {
						throw new  BusinessException("发票入库失败,该机构下没有发票管理员");
					}
				}
			}
		} catch (Exception e) {
			responseBody.seteRRORINFO(e.getMessage());
			responseBody.setrESULTTYPE("999");
			response.sethEAD(responses);
			response.setrESULT(responseBody);
		}
		return response;
	}

	@Override
	public String getdocVerCode(String type) throws DaoException {
		String result = null;
		try {
			result = inputStorageDao.queryDocverCode(type);
		} catch (Exception e) {
			throw new DaoException("单证类型转换异常");
		}
		return result;
	}

	public inputStorage getInputStorageDao() {
		return inputStorageDao;
	}

	public void setInputStorageDao(inputStorage inputStorageDao) {
		this.inputStorageDao = inputStorageDao;
	}

	public VcStorageDao getVcStorageDao() {
		return vcStorageDao;
	}

	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
	}

}
