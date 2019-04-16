package com.tapi.tcs.vc.webservice.provider.outStorage.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.HeadDTO;
import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.common.webservice.StorageResponseDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.store.dao.VcAvailableDocDao;
import com.tapi.tcs.vc.store.dao.VcDocTakerIoDao;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.webservice.provider.inStorage.dao.inputStorage;
import com.tapi.tcs.vc.webservice.provider.outStorage.bean.OutStorageRequest;
import com.tapi.tcs.vc.webservice.provider.outStorage.dao.OutStorageDao;
import com.tapi.tcs.vc.webservice.provider.outStorage.service.VcOutStorageService;
public class VcOutStorageServiceImpl implements VcOutStorageService{
	 protected final Logger logger = LoggerFactory.getLogger(getClass());
	  /** 库存Dao */
	    private VcStorageDao vcStorageDao;
	    /** 使用人单证发放回收轨迹Dao */
	    private VcDocTakerIoDao vcDocTakerIoDao;
	    /** 可使用单证Dao */
	    private VcAvailableDocDao vcAvailableDocDao;
	    /** 单证使用人 Dao*/
	    private VcTakerDao vcTakerDao;
	    private inputStorage inputStorageDao;
	    private OutStorageDao outStorageDao;
	@Override
	public StorageResponse updateSplitStorageHandle(
			OutStorageRequest outStorageRequest) {
		HeadDTO responseHead = outStorageRequest.getHeadDTO();
	    ResponseDTO responses =new ResponseDTO();
		responses.setfLOWINTIME(responseHead.getfLOWINTIME());
		responses.setrEQSERIALNO(responseHead.getrEQSERIALNO());
		responses.setrEQUESTTYPE(responseHead.getrEQUESTTYPE());
		StorageResponseDTO responseBody=new StorageResponseDTO();
		StorageResponse response =new StorageResponse();
		StringBuffer errors = new StringBuffer();
		String docVeroCode=null;
		 List mergeVcStorages = new ArrayList();
		try {
			docVeroCode = inputStorageDao.queryDocverCode(outStorageRequest.getOutStorageRequestDTO().getDocVerCode());
			if(docVeroCode!=null&&docVeroCode!=""){
			    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String pressBatchNo=outStorageRequest.getOutStorageRequestDTO().getPressBatchNo();
				String startNum =outStorageRequest.getOutStorageRequestDTO().getStartNum();
				String endNum =outStorageRequest.getOutStorageRequestDTO().getEndNum();
				String docNum =outStorageRequest.getOutStorageRequestDTO().getDocNum();
				String createBy =outStorageRequest.getOutStorageRequestDTO().getTakeCode();//管理员代码
				String takecode =outStorageRequest.getOutStorageRequestDTO().getCreateBy();//使用人代码
				//得到管理员的归属机构
				String provideOrgCode=outStorageRequest.getOutStorageRequestDTO().getTakeOrgcode();
			   
				String createByOrgCode=inputStorageDao.queryUserInfo(createBy, "4",null);//查询发票管理员的归属机构
			    String createByCode=inputStorageDao.queryUserInfo(createBy, "2",provideOrgCode);//查询发票管理员的代码
			    
			    String takeCode=inputStorageDao.queryUserInfo(takecode, "1",provideOrgCode);//得到使用人的employid
				String orgCode=inputStorageDao.queryUserInfo(takecode, "3",provideOrgCode);//使用人的归属机构
				
				    Calendar calendar = Calendar.getInstance(); 
			        Date dates = new Date(System.currentTimeMillis()); 
			        calendar.setTime(dates); 
			        calendar.add(Calendar.YEAR, +1); 
			        dates = calendar.getTime();
			        String date=sf.format(dates);
				if(takeCode!=null&&takeCode!=""){
					    VcDocTakerIo vcDocTakerIo=new VcDocTakerIo();
						vcDocTakerIo.setDocVerCode(docVeroCode);
						vcDocTakerIo.setPressBatchNo(pressBatchNo);
						vcDocTakerIo.setStartNum(startNum);
						vcDocTakerIo.setEndNum(endNum);
						vcDocTakerIo.setDocNum(Long.parseLong(docNum));
						vcDocTakerIo.setOprCode(createByCode);
						Date nowDate = new Date();
						vcDocTakerIo.setOprTime(nowDate);
						vcDocTakerIo.setTakerCode(takeCode);//使用人的id
						vcDocTakerIo.setAcceptOrgCode(orgCode);  //单证使用人所属机构
						vcDocTakerIo.setProvideOrgCode(createByOrgCode);
						vcDocTakerIo.setCreatedBy(createByCode);
						vcDocTakerIo.setDateCreated(nowDate);
						vcDocTakerIo.setUpdatedBy(createByCode);
						vcDocTakerIo.setDateUpdated(nowDate);
						vcDocTakerIo.setDeadline(date);
						Long start  = Long.valueOf(startNum);
						Long end   = Long.valueOf(endNum);
						if(responseHead.getrEQUESTTYPE().equals("0008")){
							//回收
							vcDocTakerIo.setOprType("R");
						
							String sql = " SELECT COUNT(*) FROM VC_STORAGE t " + " WHERE t.doc_ver_code = ? "
				                    + " AND t.press_batch_no = ? " + " AND t.doc_status IN ('S1','S2','S3') " + " AND t.org_code = ? "
				                    + " AND(( ? between t.start_num and t.end_num)or( ? between t.start_num and t.end_num)  )";

				            // 判断起止流水号对应库存信息有没有对应的记录
				            Object[] objs = { docVeroCode,pressBatchNo,
				            		createByOrgCode, startNum, endNum};
				            List list=vcStorageDao.findBySQL(sql, objs);
				            int amount = Integer.parseInt(list.get(0).toString()) ;
				            if(amount>0){
				            	errors.append("流水号[" + startNum + "-" + endNum + "]对应库存有记录，数据错误");
				            }
				            QueryRule queryRule = QueryRule.getInstance();
				            queryRule.addEqual("docStatus", "A");
				            Object[] values = { startNum, endNum };
				            queryRule.addBetween("docNum", values);
				            queryRule.addEqual("docVerCode", vcDocTakerIo.getDocVerCode());
				            queryRule.addEqual("pressBatchNo", vcDocTakerIo.getPressBatchNo());
				            queryRule.addEqual("takerCode", takeCode);
				            List<VcAvailableDoc> vcAvailableDocs = vcAvailableDocDao.find(queryRule);
				            int amount_ = vcAvailableDocs.size();
				            if (end - start + 1 == amount_) {
				                // 保存vcDocTakerIo
				                vcDocTakerIo.setStartNum(startNum);
				                vcDocTakerIo.setEndNum(endNum);
				                // 增加VC_STORAGE库存记录
				                List mergeVcStorage = new ArrayList();
				                mergeVcStorage.add(vcDocTakerIo);
				                mergeVcStorage.add(vcAvailableDocs);
				                mergeVcStorages.add(mergeVcStorage);
				            } else {
				                errors.append("操作失败，单证状态不对或单证不存在 ");
				            }
				            if (errors.length() == 0) {
				            	/*inputStorageDao.inMergeStorage(startNum, endNum, 
				            			vcDocTakerIo.getDocVerCode(), vcDocTakerIo.getPressBatchNo(),
				            			orgCode, "S3", null,
				            			takeCode,"0");*/
				                mergeVcStorage(mergeVcStorages);
				                responseBody.setErrorInfo("回收成功");
			    				responseBody.setResultType("000");
			    				response.setHead(responses);
			    				response.setResult(responseBody);
				            } else {
				            	    responseBody.setErrorInfo("回收失败");
				    				responseBody.setResultType("999");
				    				response.setHead(responses);
				    				response.setResult(responseBody);
				            }
						}else{
							//发放
							vcDocTakerIo.setOprType("P");
							try {
					            if((end-start+1) > 10000){
					            	errors.append("使用人单证发放数量不可超过一万，当前发放号段[" + start +":"+end+ "]数量为"+(end-start+1)+",超过10000！ ");
					            }else{
					            	 String[] params = { "S1", "S2", "S3" };
					 	            List<VcStorage> vcStorages = vcStorageDao.isExistAll(startNum, endNum, docVeroCode,
					 	            		pressBatchNo, createByOrgCode,false, params);
					 	            List updateStorages = new ArrayList();
					 	           if (vcStorages.size() == 1) {
					 	                VcStorage vcStorage = vcStorages.get(0);
					 	                // 需要拆分库存的记录
					 	                List updateStorage = new ArrayList();
					 	                updateStorage.add(vcDocTakerIo);
					 	                updateStorage.add(vcStorage);
					 	                updateStorages.add(updateStorage);
					 	            } else {
					 	            	errors.append("库存[" + start + "—" + end + "]不存在或存在多条库存\n");
					 	            }
					 	          if(errors.length()<=0){
					          		    saveVcStorage(updateStorages);
					          		    responseBody.setErrorInfo("分发成功");
					    				responseBody.setResultType("000");
					    				response.setHead(responses);
					    				response.setResult(responseBody);
					          	}else{
					          		responseBody.setErrorInfo(errors.toString());
				    				responseBody.setResultType("999");
				    				response.setHead(responses);
				    				response.setResult(responseBody);
					          	}
					            }
							} catch (Exception e) {
								throw new DaoException("发放异常！");
							}
						}
				 }else{
					 throw new DaoException("该机构下没有发票使用人！");
				 }
			}else{
				throw new DaoException("单证类型不存在！");
			}
		} catch (Exception e1) {
			    responseBody.setErrorInfo(e1.getMessage());
				responseBody.setResultType("999");
				response.setHead(responses);
				response.setResult(responseBody);
		}
		return response;
	}
/*	 @Override
		public StorageResponse DocRecoveryStorageHandle(
				OutStorageRequest outStorageRequest) throws BusinessException {
		    HeadDTO responseHead = outStorageRequest.getHeadDTO();
		    ResponseDTO responses =new ResponseDTO();
			responses.setfLOWINTIME(responseHead.getfLOWINTIME());
			responses.setrEQSERIALNO(responseHead.getrEQSERIALNO());
			responses.setrEQUESTTYPE(responseHead.getrEQUESTTYPE());
			StorageResponseDTO responseBody=new StorageResponseDTO();
			StorageResponse response =new StorageResponse();
			StringBuffer erros = new StringBuffer();
			return null;
		}*/
		
	 public void saveVcStorage(List updateStorages) throws BusinessException{
	    	try{
	        for (Iterator iterator = updateStorages.iterator(); iterator.hasNext();) {

	            List updateStorage = (List) iterator.next();

	            VcDocTakerIo vcDocTakerIo = (VcDocTakerIo) updateStorage.get(0);
	            VcStorage vcStorage = (VcStorage) updateStorage.get(1);
	            // 锁表
	            //vcStorageDao.lockVcStorage(vcStorage.getId());

	            // 库存表拆分
	            Date nowDate = vcDocTakerIo.getOprTime();

	            String startStr = vcDocTakerIo.getStartNum();
	            String endStr = vcDocTakerIo.getEndNum();
	            Long start = startStr.length() == 16 ? Long.valueOf(startStr.substring(6)) : Long.valueOf(startStr);
	            Long end = endStr.length() == 16 ? Long.valueOf(endStr.substring(6)) : Long.valueOf(endStr);

	            // 拆分
	            //vcStorageDao.splitStorage(startStr, endStr, null, null, null, null, null, vcStorage);
	            outStorageDao.splitStorage1(startStr, endStr, vcStorage.getDocVerCode(), vcStorage.getPressBatchNo(), vcStorage.getOrgCode(), vcStorage.getDocStatus(), 
	                    null, null,vcDocTakerIo.getOprCode());
	            // 拆分完毕
	            
		        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  BEGIN
	            //保存VC_DOC_TAKER_IO
	            vcDocTakerIoDao.save(vcDocTakerIo);
		        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  end

	            //add by whj进行使用人单证发放时，若使用人有归属中介机构，中介机构未保存进系统
	            VcTaker takerVo=vcTakerDao.getTakerByTakerCode(vcDocTakerIo.getTakerCode());
	            String agencyOrgCode=null;
	            if(takerVo!=null){
	               agencyOrgCode=takerVo.getAgencyOrgCode(); 
	            }
	            
	            // 保存 VC_AVAILABLE_DOC 数据 start
	            Long totalAmount = end - start + 1;
	            List<VcAvailableDoc> vcAvailableDocs = new ArrayList<VcAvailableDoc>();
	            DecimalFormat df = new DecimalFormat("0");
	            df.setMinimumIntegerDigits(startStr.length());
	            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            VcAvailableDoc vcAvailableDoc = null;
	            Calendar calendar = Calendar.getInstance();
				 calendar.setTimeInMillis(System.currentTimeMillis());
				 int currenYear = calendar.get(Calendar.YEAR);
				 calendar.set(Calendar.YEAR, currenYear + 2);
				 java.sql.Date newDate = new java.sql.Date(calendar.getTimeInMillis());//得到截止日期（两年后）
				
	            for (int i = 0; i < totalAmount; i++) {
	                vcAvailableDoc = new VcAvailableDoc();
	                // 单证类型代码
	                vcAvailableDoc.setDocVerCode(vcDocTakerIo.getDocVerCode());
	                // 单证种类代码 数据库字段已删除
	                // vcAvailableDoc.setDocTypeCode(vcDocVersionInfoDao.getDocTypeCode(vcDocTakerIo.getDocVerCode()));
	                // 单证流水号
	                vcAvailableDoc.setDocNum(df.format(start + i));
	                // 印刷批次
	                vcAvailableDoc.setPressBatchNo(vcDocTakerIo.getPressBatchNo());
	                // 单证状态
	                vcAvailableDoc.setDocStatus("A");
	                // 使用人代码
	                vcAvailableDoc.setTakerCode(vcDocTakerIo.getTakerCode());
	                // 使用人归属机构
	                vcAvailableDoc.setOrgCode(vcDocTakerIo.getAcceptOrgCode());
	                //中介机构
	                vcAvailableDoc.setAgentCode(agencyOrgCode);
	                // 发放时间
	                vcAvailableDoc.setProvideTime(nowDate);
	                // 使用截止日期
	                vcAvailableDoc.setDeadline(newDate);
	                // 创建人
	                vcAvailableDoc.setCreatedBy(vcDocTakerIo.getOprCode());
	                // 创建时间
	                vcAvailableDoc.setDateCreated(nowDate);
	                // 修改人代码
	                vcAvailableDoc.setUpdatedBy(vcDocTakerIo.getOprCode());
	                // 修改时间
	                vcAvailableDoc.setDateUpdated(nowDate);
	                
	    	        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  BEGIN
	                //使用人单证发放回收轨迹表流水
	                vcAvailableDoc.setIdTakerIO(vcDocTakerIo.getId());
	    	        //ADD BY zhxiao3 VC-115-单证库存查询存在统计号段重复的问题  END
	                vcAvailableDocs.add(vcAvailableDoc);
	            }
	            vcAvailableDocDao.saveAll(vcAvailableDocs);
	            // 保存 VC_AVAILABLE_DOC 数据 end
	        }
	        }catch (DaoException e) {
	            throw new BusinessException(e.getMessage());
	        }catch (Exception e) {
	            throw new BusinessException(e.getMessage());
	        }
	    }
	   
	  /**
	     * 合并库存记录
	     * 
	     * @param vcDocTakerIo
	     */
	    public void mergeVcStorage(List mergeVcStorages) throws BusinessException{
	        // 需要删除的VcAvailableDoc
	        List<VcAvailableDoc> vcAvailableDocsDel = new ArrayList<VcAvailableDoc>();
	        // 需要保存的VcDocTakerIo
	        List<VcDocTakerIo> vcDocTakerIos = new ArrayList<VcDocTakerIo>();
	        try{
	        for (Iterator iterator = mergeVcStorages.iterator(); iterator.hasNext();) {
	            List mergeVcStorage = (List) iterator.next();
	            vcAvailableDocsDel.addAll((Collection<? extends VcAvailableDoc>) mergeVcStorage.get(1));
	            VcDocTakerIo vcDocTakerIo = (VcDocTakerIo) mergeVcStorage.get(0);
	            vcDocTakerIos.add(vcDocTakerIo);
	            String start = vcDocTakerIo.getStartNum();
	            String end = vcDocTakerIo.getEndNum();

	            // 合并S3的库存
	           /* vcStorageDao.mergeStorage(start, end, vcDocTakerIo.getDocVerCode(), vcDocTakerIo.getPressBatchNo(),
	                    vcDocTakerIo.getAcceptOrgCode(), "S3", null, vcDocTakerIo.getOprCode()); */
	            vcStorageDao.mergeStorage(start, end, vcDocTakerIo.getDocVerCode(), vcDocTakerIo.getPressBatchNo(),
	                    vcDocTakerIo.getProvideOrgCode(), "S1", null, vcDocTakerIo.getOprCode(),null);
	        }
	        vcAvailableDocDao.deleteAll(vcAvailableDocsDel);
	        vcDocTakerIoDao.saveAll(vcDocTakerIos);
	        }catch(DaoException e){
	        	throw new BusinessException(e.getMessage());
	        }
	    }

	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
	}
	public void setVcDocTakerIoDao(VcDocTakerIoDao vcDocTakerIoDao) {
		this.vcDocTakerIoDao = vcDocTakerIoDao;
	}
	public void setVcAvailableDocDao(VcAvailableDocDao vcAvailableDocDao) {
		this.vcAvailableDocDao = vcAvailableDocDao;
	}
	public void setVcTakerDao(VcTakerDao vcTakerDao) {
		this.vcTakerDao = vcTakerDao;
	}
	public void setInputStorageDao(inputStorage inputStorageDao) {
		this.inputStorageDao = inputStorageDao;
	}
	public void setOutStorageDao(OutStorageDao outStorageDao) {
		this.outStorageDao = outStorageDao;
	}
	
}
