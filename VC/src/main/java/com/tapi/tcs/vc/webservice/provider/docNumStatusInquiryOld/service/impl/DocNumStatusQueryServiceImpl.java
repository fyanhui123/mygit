package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcDocStatusDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocSerialNoDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.dao.DocNumStatusInquiryDao;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.service.DocNumStatusQueryService;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.vo.DocNumStatusInquiryOldRequestVO;

public class DocNumStatusQueryServiceImpl implements DocNumStatusQueryService{
	private   DocNumStatusInquiryDao docNumStatusInquiryDao;
	 /**单证状态查询公用方法DAO*/
    private VcDocStatusDao vcDocStatusDao;
    /**单证核销接口DAO*/
    private DocExecuteUsedDao docExecuteUsedDao;
    private VcBaseInfoDao vcBaseInfoDao;
    
	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}
	public void setDocExecuteUsedDao(DocExecuteUsedDao docExecuteUsedDao) {
		this.docExecuteUsedDao = docExecuteUsedDao;
	}
	public void setVcDocStatusDao(VcDocStatusDao vcDocStatusDao) {
		this.vcDocStatusDao = vcDocStatusDao;
	}
	@Override
	public DocNumStatusInquiryOldResponse queryDocNumStatus(DocNumStatusInquiryOldRequestDTO request) throws BusinessException {
		
		DocNumStatusInquiryOldResponse response=new DocNumStatusInquiryOldResponse();
		// 条件单证类型code   操作员code  操作员归属机构code
	    
		// 单证类型id转换成单证类型code
		String  docVerCode=vcBaseInfoDao.getDocVerCode(request.getDocVerID()); 
		if(docVerCode.equals("")){ 
			throw new  BusinessException("单证类型不能为空！"); 
		}
		
		// 用户id转换成用户code
		String  userCode=vcBaseInfoDao.translateUserCode(request.getOperatorID());
		if(userCode.equals("")){
			throw new  BusinessException("用户代码不能为空！"); 
		}
		//转换机构代码
     	/*String  orgCode = vcBaseInfoDao.translateOrgCode(Long.parseLong(request.getOrgID()));*/ 
     	String  orgCode = vcBaseInfoDao.translateOrgCode(request.getDepID(), request.getDepCode());
     	if(orgCode.equals("")){
     		throw new  BusinessException("机构代码不能为空！");  
     	}
		// 业务号  
		//String  businessNo=request.getBusinessNo();
		// 发票代码(印刷批次)
		String invoiceVersion=request.getInvoiceVersion();
		if(StringUtils.isEmpty(invoiceVersion)){
			invoiceVersion = "000";
		}
	   //起始流水号、终止流水号
//		Long  startNum=Long.valueOf(request.getStartNum());  
//		Long  endNum=Long.valueOf(request.getEndNum());
		//格式化流水号；防止丢失前缀0
//		DecimalFormat df = new DecimalFormat("0");
//		df.setMinimumIntegerDigits(request.getStartNum().length());
		// 险种   险类(long)
		String  insuKindID=request.getInsuKindID();
		String  insuTypeID=String.valueOf(request.getInsuTypeID()); 
		 //第一个实体VcAvailableDoc   可使用
		VcAvailableDoc  vcAvailableDoc=null;
		//第二个实体VcDocVersionInfo
		VcDocVersionInfo vcDocVersionInfo=null; 
		//第一个实体   核销
		VcNormalVerification  vcNormalVerificationInfo=new VcNormalVerification();
		DocNumStatusInquiryOldResponseDTO  responseBody=new DocNumStatusInquiryOldResponseDTO();
		Object[] obj=null;
        List<DocNumStatusInquiryOldResponseDTO>  returnList=new ArrayList<DocNumStatusInquiryOldResponseDTO>();
        ResponseHeadDTO  responseHead=new ResponseHeadDTO();
		//根据业务号查询单证正常核销表：    是否为空判断是否是正常核销   多了一个业务号（用于提前核对正常核销表状态） 
		 // 正常核销表(流水号、状态、单证类型判断是否是正常核销)
        List<DocSerialNoDTO>docSerialNoDtos = request.getDocSerialNoDTOs();
        for(DocSerialNoDTO docSerialNoDTO : docSerialNoDtos){
		 //for (long j = startNum; j< endNum + 1; j++) {
		        // 判断流水号(单证)是否可用
			  //String  docNum=String.valueOf(j);  
			  //String  docNum=df.format(j);
        	String docNum = docSerialNoDTO.getDocNum();
        	List<BusinessNoDTO> businessNoDtoList = docSerialNoDTO.getBusinessNoDTOs();
			List<VcAvailableDoc> vcAvailableDocList = vcDocStatusDao.checkDocNumIsValid(docNum, docVerCode, userCode, orgCode, invoiceVersion,"");
			if(vcAvailableDocList==null || vcAvailableDocList.size()<1){
				for(BusinessNoDTO businessNoDTO:businessNoDtoList){
					String businessNo = businessNoDTO.getBusinessNo();
					String payNo = businessNoDTO.getPayNo();
					if(StringUtils.isEmpty(payNo)){
						payNo = "1";
					}
					//红冲标志:0-非红冲；1-红冲负数；2-红冲正数
					String counteractFlag = businessNoDTO.getCounteractFlag();
					if(StringUtils.isBlank(counteractFlag)){
						counteractFlag = "0";
					}
					//拆分批次
					String batchNo = businessNoDTO.getBatchNo();
				    //判断业务号+流水号是否已核销
				    VcNormalVerification vcNormalVerification = vcDocStatusDao.findVcNormalVerification(docVerCode, 
						  invoiceVersion, docNum, businessNo,payNo,batchNo,counteractFlag);
				    //如果流水号不是已核销的，则提示流水号不可用
				    if(vcNormalVerification==null){
					   	responseBody.setStartNum(docNum);
					   	responseBody.setEndNum(docNum);
				    	response.setStatus("2");
					   	returnList.add(responseBody);
					   	responseHead.setResponseCode("999");
					   	responseHead.setResponseMessage("流水号不可用!");
					   	response.setResponseHead(responseHead);
					   	response.setResponseBody(returnList);
					   	return response;
				    }
				}
			 }
			  //delete by chy 20130821 经与孙晋芳确认，去掉业务号是否已核销的校验 begin
			else {
				for (BusinessNoDTO businessNoDTO : businessNoDtoList) {
					String businessNo = businessNoDTO.getBusinessNo();
					String payNo = businessNoDTO.getPayNo();
					if (StringUtils.isEmpty(payNo)) {
						payNo = "1";
					}
					//红冲标志:0-非红冲；1-红冲负数；2-红冲正数
					String counteractFlag = businessNoDTO.getCounteractFlag();
					if(StringUtils.isBlank(counteractFlag)){
						counteractFlag = "0";
					}
					//拆分批次
					String batchNo = businessNoDTO.getBatchNo();
					// 判断是否已经核销 2：核销失败,业务号已核销（一个个业务号进行遍历） 其它条件不带测试下
					// List vcNormalVerifications =
					// docNumStatusInquiryDao.checkBizNoIsUsed(businessNo,docVerCode);
					List<Object[]> vcNormalVerifications = vcDocStatusDao
							.checkBizNoIsUsed(businessNo, payNo, docVerCode, counteractFlag, batchNo,"");
					// 0: 成功 结果列表为空
					/*if (vcNormalVerifications.size() < 1) {
						response.setStatus("0");
						responseHead.setResponseCode("000");
						responseHead.setResponseMessage("成功!");
						returnList.add(responseBody);
						response.setResponseBody(returnList);
						response.setResponseHead(responseHead);
						return response;
					}
					// 1. 业务号已经用掉
					else {*/
					if(vcNormalVerifications!=null && vcNormalVerifications.size()!=0){
						for (int i = 0; i < vcNormalVerifications.size(); i++) {
							obj = (Object[]) vcNormalVerifications.get(i);
							vcNormalVerificationInfo = (VcNormalVerification) obj[0];
							vcDocVersionInfo = (VcDocVersionInfo) obj[1];
							responseBody = new DocNumStatusInquiryOldResponseDTO();
							// 操作员ID
							responseBody.setOperatorID(request.getOperatorID());
							// 使用截止期
							responseBody.setDeadLine(null);
							// 发票代码
							responseBody.setPressBatchNo(vcNormalVerificationInfo.getPressBatchNo());
							// 单证类型Id
							responseBody.setDocVerID(String.valueOf(vcDocVersionInfo.getIdVcDocVersionInfo()));
							// 单证类型名称
							responseBody.setDocVerName(vcDocVersionInfo.getDocVerName());
							// 业务号 请求参数为必填项
							responseBody.setBusinessNo(businessNo);
							// 版本
							responseBody.setVersion(null);
							// 纳税人电脑编码
							responseBody.setTaxpayerComputerCode(null);
							// 起始流水号
							responseBody.setStartNum(vcNormalVerificationInfo.getDocNum());
							// 终止流水号
							responseBody.setEndNum(vcNormalVerificationInfo.getDocNum());
//							response.setStatus("1");
//							responseHead.setResponseCode("999");
//							responseHead.setResponseMessage("业务号已核销!");
							returnList.add(responseBody);
//							response.setResponseHead(responseHead);
//							response.setResponseBody(returnList);
						}
//						return response;
					}
				}
				if(returnList!=null && returnList.size()!=0){
					response.setStatus("1");
					responseHead.setResponseCode("999");
					responseHead.setResponseMessage("业务号已核销!");
					response.setResponseHead(responseHead);
					response.setResponseBody(returnList);
					return response;
				}
			}
		 }
		response.setStatus("0");
	 	responseHead.setResponseCode("000");
	   	responseHead.setResponseMessage("成功!");
		returnList.add(responseBody);
		response.setResponseBody(returnList);
		response.setResponseHead(responseHead);
		 return response;
	}
	
	public void setDocNumStatusInquiryDao(DocNumStatusInquiryDao docNumStatusInquiryDao) {
		this.docNumStatusInquiryDao = docNumStatusInquiryDao;
	}

	/**
	 * 把requestBody转换成requestVo
	 * 
	 * @param request
	 * @return requestVo
	 * @throws BusinessException
	 */
	/*private DocNumStatusInquiryOldRequestVO translateRequestVo(
			DocNumStatusInquiryOldRequestDTO request) throws BusinessException {
		
		DocNumStatusInquiryOldRequestVO requestVo = new DocNumStatusInquiryOldRequestVO();
		try {
			// 单证类型id转换成单证类型code
			String docVerCode = vcBaseInfoDao.getDocVerCode(request.getDocVerID());
			// 操作员ID转换成操作员code
			String operatorCode = vcBaseInfoDao.translateUserCode(request.getOperatorID());
			// 转换机构代码
			String orgCode = vcBaseInfoDao.translateOrgCode(Long.parseLong(request.getOrgID()));
			String orgCode = vcBaseInfoDao.translateOrgCode(request.getDepID(), request.getDepCode());
			// 业务号
			String businessNo = request.getBusinessNo();
			// 发票版本(印刷批次)
			String invoiceVersion = request.getInvoiceVersion();

			requestVo.setDocVerCode(docVerCode);// 单证类型代码
			requestVo.setOperatorCode(operatorCode); // 操作员代码
			requestVo.setOrgCode(orgCode); // 操作员归属机构代码
			requestVo.setBusinessNo(businessNo); // 业务号
			requestVo.setInvoiceVersion(invoiceVersion); //发票版本
			requestVo.setStartNum(request.getStartNum()); // 单证起始流水号
			requestVo.setEndNum(request.getEndNum()); // 单证终止流水号

		} catch (DaoException de) {
			de.printStackTrace();
			throw new BusinessException(de.getMessage());
		}
		return requestVo;
	}*/
	 
}
