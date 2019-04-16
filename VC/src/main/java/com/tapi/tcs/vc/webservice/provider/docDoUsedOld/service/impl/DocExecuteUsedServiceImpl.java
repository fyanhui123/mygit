package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.StringUtil;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintExt;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcDocStatusDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldResponse;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocSerialNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.InvoiceDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.InvoicePrintDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.InvoicePrintDetailDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.InvoicePrintExtDto;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.service.DocExecuteUsedService;

public class DocExecuteUsedServiceImpl implements DocExecuteUsedService {

	/**基础代码DAO*/
	private VcBaseInfoDao vcBaseInfoDao;
	/**单证状态查询公用方法DAO*/
	private VcDocStatusDao vcDocStatusDao;
	/**单证核销接口DAO*/
	private DocExecuteUsedDao docExecuteUsedDao;

	
	@Override
	public DocDoUsedOldResponse saveDocDoUsed(DocDoUsedOldRequest request) throws BusinessException {
		DocDoUsedOldResponse response = new DocDoUsedOldResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocDoUsedOldResponseDTO responseBody = new DocDoUsedOldResponseDTO();

		DocDoUsedOldRequestDTO requestBody = request.getRequestBody();
		//流水区间段
		List<DocSerialNoDTO> docSerialNos = requestBody.getDocSerialNos();
		//发票信息
		List<InvoiceDTO> invoiceDTOs = requestBody.getInvoiceDTOs();
		
		Long docVersionId = requestBody.getDocVersionID();
		Long userId = requestBody.getUserId();
		
		String userCode="";
		Date  nowDate=new Date();
		/*String companyId = requestBody.getCompanyId();*/
		try{
			//机构代码
			/*String  orgCode = vcBaseInfoDao.translateOrgCode(Long.valueOf(companyId));*/
			String  orgCode = vcBaseInfoDao.translateOrgCode(requestBody.getDepartId(), requestBody.getDepartCode());
			//校验发票打印信息
			String message = checkInvoiceDTO(invoiceDTOs, orgCode);
			if(StringUtils.isNotEmpty(message)){
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(message);
				responseBody.setStatus("3");
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			}
			
			//根据单证类型id查询单证版本信息
			VcDocVersionInfo vcDocVersionInfo = docExecuteUsedDao.getVcDocVersionInfo(docVersionId);
			if(vcDocVersionInfo==null){
				throw new BusinessException("单证类型不存在！");
			}
			//单证类型代码
			String versionCode = vcDocVersionInfo.getDocVerCode();
			//印刷批次号：发票=发票代码；业务单证=000
			String pressBatchNo = "000";
			if(invoiceDTOs!=null && invoiceDTOs.size()>0){
				pressBatchNo = invoiceDTOs.get(0).getInvoicePrintDTO().getInvoiceCode();
			}
			
			//用户代码
			userCode = vcBaseInfoDao.translateUserCode(userId);
			
			List<VcAvailableDoc> vcAvailableDocs = new ArrayList<VcAvailableDoc>();
			List<VcNormalVerification> vcNormalVerificationList = new ArrayList<VcNormalVerification>();
			//遍历流水号段区间
			for (DocSerialNoDTO docSerialNoDTO : docSerialNos) {
				//业务号
				/*String businessNo = docSerialNoDTO.getBusinessNo();*/
				//单证流水号
				String docNum = docSerialNoDTO.getDocNum();
				//业务号列表
				List<BusinessNoDTO> businessNoDtos = docSerialNoDTO.getBusinessNoDTOs();
				// 核销明细列表 
				List<VcNormalVerifiedDet> vcNormalVerifiedDetList = new ArrayList<VcNormalVerifiedDet>();
				//核销标志：0-未核销；1-重复核销；2-核销失败,业务号已核销
				String verifiedFlag = "0";
				VcNormalVerification vcNormalVerificationOld = null;
				for(BusinessNoDTO businessNoDTO : businessNoDtos){
					//业务号
					String businessNo = businessNoDTO.getBusinessNo();
					//关联的业务号
					String relationBusinessNo = businessNoDTO.getRelationBusinessNo();
					//缴费期次
					String payNo = businessNoDTO.getPayNo();
					if(StringUtils.isEmpty(payNo)){
						payNo = "1";//默认等于1
					}
					//红冲标志:0-非红冲；1-红冲负数；2-红冲正数
					String counteractFlag = businessNoDTO.getCounteractFlag();
					if(StringUtils.isBlank(counteractFlag)){
						counteractFlag = "0";
					}
					//拆分批次
					String batchNo = businessNoDTO.getBatchNo();
					// 判断是否已经核销
					List<Object[]> vcNormalVerifications = vcDocStatusDao.checkBizNoIsUsed(businessNo,payNo,
							versionCode,counteractFlag,batchNo,"");
					if (vcNormalVerifications!=null && vcNormalVerifications.size() != 0) {
						Object[] obj = vcNormalVerifications.get(0);
						vcNormalVerificationOld = (VcNormalVerification)obj[0];
						
						if(vcNormalVerificationOld.getDocVerCode().equals(versionCode)
								&&vcNormalVerificationOld.getPressBatchNo().equals(pressBatchNo)
								&&vcNormalVerificationOld.getDocNum().equals(docNum)){
							verifiedFlag += ",1";//核销成功
						}else{
							verifiedFlag += ",2";//核销失败
						}
					}
					//增加明细表的保存
					VcNormalVerifiedDet vcNormalVerifiedDet = new VcNormalVerifiedDet();
					vcNormalVerifiedDet.setBusinessNo(businessNo);
					if (!StringUtils.isEmpty(relationBusinessNo)){
						vcNormalVerifiedDet.setRelBusinessNo(relationBusinessNo);
					}
					vcNormalVerifiedDet.setPayNo(payNo);
					vcNormalVerifiedDet.setCounteractFlag(counteractFlag);//红冲标志
					vcNormalVerifiedDet.setBatchNo(batchNo);//拆分批次
					vcNormalVerifiedDet.setCreatedBy(userCode);
					vcNormalVerifiedDet.setDateCreated(nowDate);
					vcNormalVerifiedDet.setUpdatedBy(userCode);
					vcNormalVerifiedDet.setDateUpdated(nowDate);
					vcNormalVerifiedDetList.add(vcNormalVerifiedDet);
				}
				//根据业务号查找核销记录不为空
				if(!"0".equals(verifiedFlag)){
					//根据业务号、流水号、印刷批次、单证类型匹配失败
					if(verifiedFlag.indexOf("2")!=-1){
						responseHead.setResponseCode("999");
						responseHead.setResponseMessage("核销失败，业务号已经核销！");
						responseBody.setStatus("1");
						response.setResponseHead(responseHead);
						response.setResponseBody(responseBody);
						return response;
					}else{//重复核销的情况（根据业务号、流水号、印刷批次、单证类型匹配成功）
						// 核销时间在同一个月
						/*Date verifiedTime = vcNormalVerificationOld.getVerifiedTime();
						int months = DateUtils.getMonthDifference(verifiedTime, nowDate);
						String docStatus = "U3";
						if (months == 0) {
							docStatus = "U1";
						}
						vcNormalVerificationOld.setVerifiedTime(nowDate);
						vcNormalVerificationOld.setDocStatus(docStatus);
						vcNormalVerificationOld.setUpdatedBy(userCode);
						vcNormalVerificationOld.setDateUpdated(nowDate);
						docExecuteUsedDao.update(vcNormalVerificationOld);*/
						responseHead.setResponseCode("000");
						responseHead.setResponseMessage("核销成功！");
						responseBody.setStatus("0");
						response.setResponseHead(responseHead);
						response.setResponseBody(responseBody);
						return response;
					}
				}
	
				// 判断流水号(单证)是否可用
				List<VcAvailableDoc> vcAvailableDocList = vcDocStatusDao.checkDocNumIsValid(docNum, versionCode, userCode, orgCode, pressBatchNo,"");
				if(vcAvailableDocList==null || vcAvailableDocList.size()<1){
					responseHead.setResponseCode("999");
					responseHead.setResponseMessage("核销失败，单证流水号不可用！");
					responseBody.setStatus("1");
					responseBody.setDocSerialNo(docNum);
					response.setResponseHead(responseHead);
					response.setResponseBody(responseBody);
					return response;
				}
				// 若流水号不可用，返回错误提示信息
				VcAvailableDoc vcAvailableDoc=vcAvailableDocList.get(0);
                if (DateUtils.compare(DateUtils.reset(vcAvailableDoc.getDeadline()), DateUtils.reset(new Date())) < 0) {
                    responseHead.setResponseCode("999");
                    responseHead.setResponseMessage("核销失败，单证["+docNum+"]已过使用截止期！");
                    responseBody.setStatus("1");
                    responseBody.setDocSerialNo(docNum);
                    response.setResponseHead(responseHead);
                    response.setResponseBody(responseBody);
                    return response;
                }
				vcAvailableDocs.add(vcAvailableDocList.get(0));
				VcNormalVerification vcNormalVerification = new VcNormalVerification();
				vcNormalVerification.setDocVerCode(versionCode);
				vcNormalVerification.setDocNum(docNum);
				vcNormalVerification.setPressBatchNo(pressBatchNo);
				//取第一个业务号
				vcNormalVerification.setBusinessNo(vcNormalVerifiedDetList.get(0).getBusinessNo());
				vcNormalVerification.setDocStatus("U1");
				vcNormalVerification.setVerifiedOprCode(userCode);
				vcNormalVerification.setVerifiedOrgCode(orgCode);
				vcNormalVerification.setVerifiedTime(nowDate);
				// 核销原因（1：打印/2：补录/3：其他）
				vcNormalVerification.setVerifiedReason("1");
				vcNormalVerification.setCreatedBy(userCode);
				vcNormalVerification.setDateCreated(nowDate);
				vcNormalVerification.setUpdatedBy(userCode);
				vcNormalVerification.setDateUpdated(nowDate);
				//设置明细列表
				vcNormalVerification.setVcNormalVerifiedDet(vcNormalVerifiedDetList);
				//设置明细表和主表的关联关系
				for(VcNormalVerifiedDet vcNormalVerifiedDet:vcNormalVerifiedDetList){
					vcNormalVerifiedDet.setVcNormalVerification(vcNormalVerification);
				}
				vcNormalVerificationList.add(vcNormalVerification);
			}
			
			//发票开具信息表
			List<VcInvoicePrint> vcInvoicePrintList = new ArrayList<VcInvoicePrint>();
			if(orgCode.length()>=4 && (SysConst.TAXATION_ORGS).indexOf(orgCode.substring(0, 4))>-1){
				//遍历发票对象
				if(invoiceDTOs!=null && invoiceDTOs.size()>0){
					for(InvoiceDTO invoiceDTO : invoiceDTOs){
						VcInvoicePrint vcInvoicePrint = generateInvoice(invoiceDTO,userCode,versionCode,orgCode);
//						vcInvoicePrint.setDocVerCode(versionCode);//单证类型代码
//						vcInvoicePrint.setOrgCode(orgCode);//开票机构/核销机构
//						vcInvoicePrint.setStatus("1");//发票状态
//						vcInvoicePrint.setIsUploadPlat("0");//是否上传平台
						vcInvoicePrintList.add(vcInvoicePrint);
					}
				}
			}
			
			//保存核销表
			docExecuteUsedDao.saveVcNormalVerificationList(vcNormalVerificationList);
			//删除可使用表
			docExecuteUsedDao.deleteVcAvailableDocList(vcAvailableDocs);
			if(vcInvoicePrintList!=null && vcInvoicePrintList.size()>0){
				docExecuteUsedDao.saveVcInvoicePrintList(vcInvoicePrintList);
			}
		}catch(DaoException de){
			throw new BusinessException(de.getMessage());
		}
		
		responseHead.setResponseCode("000");
		responseHead.setResponseMessage("核销成功！");
		responseBody.setStatus("0");
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		
		return response;
	}
	
	/**
	 * 各地区发票信息必录校验
	 * @param invoiceDTOs
	 * @param orgCode
	 * @return
	 */
	private String checkInvoiceDTO(List<InvoiceDTO> invoiceDTOs, String orgCode) {
		String msg = null;
		if(invoiceDTOs!=null && invoiceDTOs.size()>0 && orgCode.length()>=4){
			//浙江地区
			if(SysConst.COMCODE_ZJ.equals(orgCode.substring(0,4))){
				msg = checkInvoiceZJ(invoiceDTOs);
			}
			//厦门地区
			else if(SysConst.COMCODE_XM.equals(orgCode.substring(0,4))){
				msg = checkInvoiceXM(invoiceDTOs);
			}
			//贵州地区
			else if(SysConst.COMCODE_GZ.equals(orgCode.substring(0,4))){
				msg = checkInvoiceGZ(invoiceDTOs);
			}
			//大连地区
			else if(SysConst.COMCODE_DL.equals(orgCode.substring(0,4))){
				msg = checkInvoiceDL(invoiceDTOs);
			}
			//江苏地区
			else if(SysConst.COMCODE_JS.equals(orgCode.substring(0,4))){
				msg = checkInvoiceJS(invoiceDTOs);
			}
			//上海地区
			else if(SysConst.COMCODE_SHANGHAI.equals(orgCode.substring(0,4))){
				msg = checkInvoiceSH(invoiceDTOs);
			}
			//山西地区
			else if(SysConst.COMCODE_SX.equals(orgCode.substring(0,4))){
				msg = checkInvoiceShanXi(invoiceDTOs);
			}
			//河南地区
            else if(SysConst.COMCODE_HeNan.equals(orgCode.substring(0,4))){
                msg = checkInvoiceHeNan(invoiceDTOs);
            }
			//重庆地区
            else if(SysConst.COMCODE_ChongQing.equals(orgCode.substring(0,4))){
                msg = checkInvoiceChongQing(invoiceDTOs);
            }
			//福建
            //else if(SysConst.COMCODE_FJ.equals(orgCode.substring(0,4))){
            else if(orgCode.length()>=6 && (SysConst.COMCODE_FJ_UPLOAD.indexOf(orgCode.substring(0,6))>-1)){
            	msg = checkInvoiceFujian(invoiceDTOs);
            }
			//天津
            else if(SysConst.COMCODE_TJ.equals(orgCode.substring(0,4))){
            	msg = checkInvoiceTJ(invoiceDTOs);
            }
		}
		return msg;
	}
	
	private String checkInvoiceZJ(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
					msg += "发票开具类型不能为空；";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}
	
	private String checkInvoiceXM(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getMachinePrintedCode())){
					msg += "机打代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getMachinePrintedNo())){
					msg += "机打号码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getBillerName())){
					msg += "开票员名称";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
					msg += "发票开具类型不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getIndustryCode())){
					msg += "行业分类对应的代码不能为空；";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
				if(invoicePrintDTO.getOperatorDate()==null){
					msg += "操作时间不能为空；";
				}
			}
			if(invoicePrintDetialDtos==null || invoicePrintDetialDtos.size()<1){
				msg += "发票开具明细信息不能为空；";
			}/*else{
				String str1 = "";
				for(InvoicePrintDetailDTO detail : invoicePrintDetialDtos){
					if(detail.getSerialNo()==null || detail.getSerialNo()<1 || detail.getSerialNo()>5){
						str1 += "发票明细信息中的序号不能为空，且值的范围为1-5；";
					}
					if(StringUtils.isBlank(detail.getIndustry())){
						str1 += "发票明细信息中的行业分类不能为空；";
					}
					if(StringUtils.isBlank(detail.getItemKind())){
						str1 += "发票明细信息中的项目大类不能为空；";
					}
					if(detail.getAmount()==null){
						str1 += "发票明细信息中的金额不能为空；";
					}
					if(StringUtils.isNotEmpty(str1)){
						msg += str1;
						break;
					}
				}
			}*/
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}
	
	private String checkInvoiceGZ(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
			InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getSettleItem())){
					msg += "结算项目不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerCode())){
					msg += "付款方号码不能为空；";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getRegistration())){
					msg += "工商登记证号不能为空；";
				}
			}
			if(invoicePrintExtDto==null){
				msg += "发票开具扩展信息不能为空；";
			}else{
				String str1 = "";
				if(invoicePrintExtDto.getPremium()==null&&invoicePrintExtDto.getTaxAmount()==null
						&&invoicePrintExtDto.getLateFee()==null){
					str1 += "保险费、车船税、滞纳金不能全部为空；";
				}
				if(StringUtils.isNotEmpty(str1)){
					msg += str1;
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}

	
	private String checkInvoiceDL(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
			InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
					msg += "发票开具类型不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getBillerName())){
					msg += "开票员名称不能为空";
				}
			}
			if(invoicePrintExtDto==null){
				msg += "发票开具扩展信息不能为空；";
			}else{
				String str1 = "";
				if(invoicePrintExtDto.getPremium()==null&&invoicePrintExtDto.getTaxAmount()==null
						&&invoicePrintExtDto.getLateFee()==null){
					str1 += "保险费、车船税、滞纳金不能全部为空；";
				}
				if(StringUtils.isNotEmpty(str1)){
					msg += str1;
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}
	
	/**
	 * 天津发票必录校验
	 * @param invoiceDTOs
	 * @return
	 */
	private String checkInvoiceTJ(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
			InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
					msg += "发票开具类型不能为空；";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
                if (StringUtils.isBlank(invoicePrintDTO.getTaxpayerCode())) {
                    msg += "纳税人识别号不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getTaxpayerName())) {
                    msg += "纳税人名称不能为空；";
                }
			}
			if(invoicePrintExtDto==null){
				msg += "发票开具扩展信息不能为空；";
			}else{
				String str1 = "";
				if(invoicePrintExtDto.getPremium()==null&&invoicePrintExtDto.getTaxAmount()==null
						&&invoicePrintExtDto.getLateFee()==null){
					str1 += "保险费、车船税、滞纳金不能全部为空；";
				}
				if(StringUtils.isNotEmpty(str1)){
					msg += str1;
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}
	

	/**
	 * 江苏发票必录校验
	 * @param invoiceDTOs
	 * @return
	 */
	private String checkInvoiceJS(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getIndustryCode())){
					msg += "行业代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空；";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getBillerName())){
					msg += "开票人名称不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getRecipientName())){
					msg += "收款方名称不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getCheckNum())){
					msg += "验证码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
					msg += "发票开具类型不能为空；";
				}
			}
			if(invoicePrintExtDto==null){
				msg += "发票开具扩展信息不能为空；";
			}else{
				String str1 = "";
				if(StringUtils.isBlank(invoicePrintExtDto.getRiskCode())){
					str1 += "承保险种代码不能为空";
				}
				if(StringUtils.isBlank(invoicePrintExtDto.getRiskName())){
					str1 += "承保险种名称不能为空";
				}
				if(invoicePrintExtDto.getPremium()==null&&invoicePrintExtDto.getTaxAmount()==null
						&&invoicePrintExtDto.getLateFee()==null){
					str1 += "保险费、车船税、滞纳金不能全部为空；";
				}
				if(StringUtils.isNotEmpty(str1)){
					msg += str1;
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}
	
	/**
	 * 上海发票必录校验
	 * @param invoiceDTOs
	 * @return
	 */
	private String checkInvoiceSH(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			List<InvoicePrintDetailDTO> detailDtoList = invoiceDTO.getInvoicePrintDetailDTOs();

			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
//				if(StringUtils.isBlank(invoicePrintDTO.getRecipientName())){
//					msg += "收款方名称不能为空；";
//				}
//				if(StringUtils.isBlank(invoicePrintDTO.getRecipientCode())){
//					msg += "收款方号码不能为空；";
//				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getBillerName())){
					msg += "开票人名称不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
					msg += "发票开具类型不能为空；";
				}
			}
			/*if(detailDtoList!=null && detailDtoList.size()!=0){
				for(InvoicePrintDetailDTO detailDto : detailDtoList){
					if(detailDto.getAmount()==null){
						msg += "开票明细信息中的金额不能为空；";
						break;
					}
				}
			}*/
			if(detailDtoList==null || detailDtoList.size()==0){
				msg += "开票明细信息不能为空；";
			}
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}
	
	/**
	 * 山西发票必录校验
	 * @param invoiceDTOs
	 * @return
	 */
	private String checkInvoiceShanXi(List<InvoiceDTO> invoiceDTOs){
		String msg = "";
		for(InvoiceDTO invoiceDTO : invoiceDTOs){
			InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
			List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
			InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

			if(invoicePrintDTO==null){
				msg += "发票开具信息不能为空；";
			}else{
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())){
					msg += "发票代码不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())){
					msg += "发票号码不能为空";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
					msg += "发票开具类型不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){
					msg += "付款方名称不能为空";
				}
				if(invoicePrintDTO.getPrintDate()==null){
					msg += "开票日期不能为空；";
				}
				if(invoicePrintDTO.getAmount()==null){
					msg += "开票金额不能为空；";
				}
				if(StringUtils.isBlank(invoicePrintDTO.getBillerName())){
					msg += "开票员名称";
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				return msg;
			}
		}
		return msg;
	}

    /**
     * 河南发票必录校验
     * 
     * @param invoiceDTOs
     * @return
     */
    private String checkInvoiceHeNan(List<InvoiceDTO> invoiceDTOs) {
        String msg = "";
        for (InvoiceDTO invoiceDTO : invoiceDTOs) {
            InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
            List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
            InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

            if (invoicePrintDTO == null) {
                msg += "发票开具信息不能为空；";
            } else {
                if (StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())) {
                    msg += "发票代码不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())) {
                    msg += "发票号码不能为空";
                }
                /*if (StringUtils.isBlank(invoicePrintDTO.getTaxpayerCode())) {
                    msg += "纳税人识别号不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getTaxpayerName())) {
                    msg += "纳税人名称不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getTaxOrgCode())) {
                    msg += "纳税人主管税务机关代码不能为空；";
                }*/
                /*
                 * if(invoicePrintDTO.getPrintDate()==null){ msg += "开票日期不能为空；"; }
                 */
                /*
                 * if(invoicePrintDTO.getAmount()==null){ msg += "开票金额不能为空；"; }
                 */
                // if(StringUtils.isBlank(invoicePrintDTO.getRecipientName())){
                // msg += "收款方名称不能为空；";
                // }
                // if(StringUtils.isBlank(invoicePrintDTO.getRecipientCode())){
                // msg += "收款方号码不能为空；";
                // }
                /*
                 * if(StringUtils.isBlank(invoicePrintDTO.getPayerName())){ msg += "付款方名称不能为空；"; }
                 * if(StringUtils.isBlank(invoicePrintDTO.getBillerName())){ msg += "开票人名称不能为空；"; }
                 */
                if (StringUtils.isBlank(invoicePrintDTO.getPrintKind())) {
                    msg += "发票开具类型不能为空；";
                }
               /* if (StringUtils.isBlank(invoicePrintDTO.getIndustryCode())) {
                    msg += "行业代码不能为空；";
                }*/
            }

            if (StringUtils.isNotEmpty(msg)) {
                return msg;
            }
        }
        return msg;
    }
    /**
     * 重庆发票必录校验
     * @param invoiceDTOs
     * @return
     */
    private String checkInvoiceChongQing(List<InvoiceDTO> invoiceDTOs) {
        String msg = "";
        for (InvoiceDTO invoiceDTO : invoiceDTOs) {
            InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
            List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
            InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

            if (invoicePrintDTO == null) {
                msg += "发票开具信息不能为空；";
            } else {
                if (StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())) {
                    msg += "发票代码不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())) {
                    msg += "发票号码不能为空";
                }

                if (StringUtils.isBlank(invoicePrintDTO.getTaxpayerCode())) {
                    msg += "收款方纳税人识别号不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getTaxpayerName())) {
                    msg += "收款方纳税人名称不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getPayerName())) {
                    msg += "付款人名称不能为空；";
                }
                /*
                 * if(StringUtils.isBlank(invoicePrintDTO.getTaxOrgCode())){ msg += "纳税人主管税务机关代码不能为空；"; }
                 */
                if (invoicePrintDTO.getPrintDate() == null) {
                    msg += "开票日期不能为空；";
                }
                if (invoicePrintDTO.getAmount() == null) {
                    msg += "开票金额不能为空；";
                }

                if (StringUtils.isBlank(invoicePrintDTO.getPrintKind())) {
                    msg += "发票开具类型不能为空；";
                }

            }

            if (StringUtils.isNotEmpty(msg)) {
                return msg;
            }
        }
        return msg;
    }
    
    /**
     * 福建发票必录校验
     * @param invoiceDTOs
     * @return
     */
    private String checkInvoiceFujian(List<InvoiceDTO> invoiceDTOs) {
        String msg = "";
        for (InvoiceDTO invoiceDTO : invoiceDTOs) {
            InvoicePrintDTO invoicePrintDTO = invoiceDTO.getInvoicePrintDTO();
            List<InvoicePrintDetailDTO> invoicePrintDetialDtos = invoiceDTO.getInvoicePrintDetailDTOs();
            InvoicePrintExtDto invoicePrintExtDto = invoiceDTO.getInvoicePrintExtDto();

            if (invoicePrintDTO == null) {
                msg += "发票开具信息不能为空；";
            } else {
                if (StringUtils.isBlank(invoicePrintDTO.getInvoiceCode())) {
                    msg += "发票代码不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getInvoiceNo())) {
                    msg += "发票号码不能为空";
                }
                if (invoicePrintDTO.getPrintDate() == null) {
                    msg += "开票日期不能为空；";
                }
                if(invoicePrintDTO.getStartDate() == null){
                	msg += "起始时间不能为空；";
                }
                if(invoicePrintDTO.getEndDate() == null){
               	 	msg += "结束时间不能为空；";
                }
                if(StringUtils.isBlank(invoicePrintDTO.getCheckNum())){
                	msg += "校验码不能为空；";
                }
                if (invoicePrintDTO.getAmount() == null) {
                    msg += "开票金额不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getPayerName())) {
                    msg += "付款人名称不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getUpperAmount())) {
                    msg += "大写金额不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getRecipientName())) {
                    msg += "收款方名称不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getRecipientCode())) {
                    msg += "收款方号码不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getBillerName())) {
                    msg += "开票员名称不能为空；";
                }
                if (invoicePrintDTO.getOperatorDate() == null) {
                    msg += "操作时间不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getPrintTypeCode())) {
                    msg += "套打样式代码不能为空；";
                }
                if (StringUtils.isBlank(invoicePrintDTO.getPrintKind())) {
                    msg += "发票开具类型不能为空；";
                }else if("2".equals(invoicePrintDTO.getPrintKind()) || invoicePrintDTO.getAmount().signum() < 0){
                	//红冲时被红冲的发票代码/号码不能为空
                	if (StringUtils.isBlank(invoicePrintDTO.getCounteractedInvoiceCode())) {
                        msg += "被冲红的发票代码不能为空；";
                    }
                	if (StringUtils.isBlank(invoicePrintDTO.getCounteractedInvoiceNo())) {
                        msg += "被冲红的发票号码不能为空；";
                    }
                }
            }
            //套打样式代码
            String printType = invoicePrintDTO.getPrintTypeCode();
            if(invoicePrintExtDto == null){
            	msg += "发票开具扩展信息不能为空；";
            }else{
            	if(StringUtils.isBlank(invoicePrintExtDto.getApplyName())){
            		msg += "投保人不能为空；";
            	}
            	if("03".equals(printType)){
            		if(StringUtils.isBlank(invoicePrintExtDto.getAreaCode())){
            			msg += "投保地代码不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getAreaName())){
            			msg += "投保地名称不能为空；";
            		}
            	}else if("02".equals(printType)){
            		if(StringUtils.isBlank(invoicePrintExtDto.getCarAreaCode())){
            			msg += "车籍地代码不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getCarAreaName())){
            			msg += "车籍地名称不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getLicenseNo())){
            			msg += "车牌号不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getCarKind())){
            			msg += "车辆类别不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getUnit())){
            			msg += "单位不能为空；";
            		}
            		if(invoicePrintExtDto.getQuantity() == null){
            			msg += "数量不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getUseNature())){
            			msg += "使用性质不能为空；";
            		}
            		if(invoicePrintExtDto.getPremium() == null){
            			msg += "保险金额不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getEngineNo())){
            			msg += "发动机号不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getFrameNo())){
            			msg += "车架号不能为空；";
            		}
            		if(StringUtils.isBlank(invoicePrintExtDto.getFuelCategory())){
            			msg += "能源类别不能为空；";
            		}
            	}
            }
            if(invoicePrintDetialDtos==null || invoicePrintDetialDtos.size()==0){
            	msg += "明细信息不能为空；";
            }else{
            	String s = "";
            	for(InvoicePrintDetailDTO detail : invoicePrintDetialDtos){
            		if(detail.getSerialNo() == null){
            			s += "明细序号不能为空";
            		}
            		if("02".equals(printType)){
            			/*if(detail.getVesselTax() == null){
            				s += "代收车船税不能为空；";
            			}*/
            			if(StringUtils.isBlank(detail.getVesselTax())){
            				s += "代收车船税不能为空；";
            			}
            			if(StringUtils.isBlank(detail.getTaxYear())){
            				s += "税款所属期年不能为空；";
            			}
            			if(StringUtils.isBlank(detail.getTaxMonthStart())){
            				s += "税款所属期起始月不能为空；";
            			}
            			if(StringUtils.isBlank(detail.getTaxMonthEnd())){
            				s += "税款所属期终止月不能为空；";
            			}
            		}else{
            			if(StringUtils.isBlank(detail.getItemCode())){
            				s += "开具项目代码不能为空";
            			}
            			if(StringUtils.isBlank(detail.getItemName())){
            				s += "开具项目名称不能为空；";
            			}
            			if(detail.getAmount() == null){
            				s += "明细金额不能为空；";
            			}
            		}
            		if(StringUtils.isNotEmpty(s)){
            			msg += s;
            			break;
            		}
            	}
            }
            if (StringUtils.isNotEmpty(msg)) {
                return msg;
            }
        }
        return msg;
    }

	/**
	 * 把请求发票信息转换成数据库发票信息表
	 * @param invoiceDTO
	 * @return
	 */
	private VcInvoicePrint generateInvoice(InvoiceDTO invoiceDTO, String userCode, String versionCode, String orgCode) throws BusinessException{
		VcInvoicePrint vcInvoicePrint = generateInvoicePrint(invoiceDTO.getInvoicePrintDTO(), userCode, versionCode, orgCode);
		//发票扩展信息
		if(invoiceDTO.getInvoicePrintExtDto()!=null){
			List<VcInvoicePrintExt> extList = generateInvoicePrintExt(invoiceDTO.getInvoicePrintExtDto(), userCode);
			for(VcInvoicePrintExt vcInvoicePrintExt : extList){
				vcInvoicePrintExt.setVcInvoicePrint(vcInvoicePrint);
			}
			vcInvoicePrint.setVcInvoicePrintExtList(extList);
		}
		//发票明细信息
		if(invoiceDTO.getInvoicePrintDetailDTOs()!=null && invoiceDTO.getInvoicePrintDetailDTOs().size()>0){
			List<VcInvoicePrintDet> detList = generateInvoicePrintDet(invoiceDTO.getInvoicePrintDetailDTOs(), userCode);
			for(VcInvoicePrintDet vcInvoicePrintDet : detList){
				vcInvoicePrintDet.setVcInvoicePrint(vcInvoicePrint);
			}
			vcInvoicePrint.setVcInvoicePrintDetList(detList);
		}
		return vcInvoicePrint;
	}
	/**
	 * 转换发票主表信息
	 * @param printDto
	 * @return
	 */
	private VcInvoicePrint generateInvoicePrint(InvoicePrintDTO printDto, String userCode, String versionCode, String orgCode) throws BusinessException {
		VcInvoicePrint vcInvoicePrint = new VcInvoicePrint();
		vcInvoicePrint.setPolicyNo(printDto.getPolicyNo());//保单号
		vcInvoicePrint.setEndorseNo(printDto.getEndorseNo());//批单号
		vcInvoicePrint.setInvoiceCode(printDto.getInvoiceCode());//发票代码
		vcInvoicePrint.setInvoiceName(printDto.getInvoiceName());//发票名称
		vcInvoicePrint.setInvoiceNo(printDto.getInvoiceNo());//发票号码
		vcInvoicePrint.setPrintDate(printDto.getPrintDate());//开票日期
		vcInvoicePrint.setAmount(printDto.getAmount());//开票金额
		vcInvoicePrint.setPayerName(printDto.getPayerName());//付款方名称
		vcInvoicePrint.setPayerCode(printDto.getPayerCode());//付款方号码
		vcInvoicePrint.setRecipientName(printDto.getRecipientName());//收款方名称
		vcInvoicePrint.setRecipientCode(printDto.getRecipientCode());//收款方号码
		vcInvoicePrint.setTaxpayerName(printDto.getTaxpayerName());//纳税人名称
		vcInvoicePrint.setTaxpayerCode(printDto.getTaxpayerCode());//纳税人识别号
		vcInvoicePrint.setBillerName(printDto.getBillerName());//开票员名称
		vcInvoicePrint.setRecipientOpr(printDto.getRecipientOpr());//收款人名称
		vcInvoicePrint.setCounteractInvoiceCode(printDto.getCounteractInvoiceCode());//冲红的发票代码
		if(StringUtils.isNotEmpty(printDto.getCounteractInvoiceNo())){
			vcInvoicePrint.setCounteractInvoiceNo(StringUtil.formatNumberLength(printDto.getCounteractInvoiceNo(), 8));//冲红的发票号码
		}
		vcInvoicePrint.setCounteractedInvoiceCode(printDto.getCounteractedInvoiceCode());//被冲红的发票代码
		if(StringUtils.isNotEmpty(printDto.getCounteractedInvoiceNo())){
			vcInvoicePrint.setCounteractedInvoiceNo(StringUtil.formatNumberLength(printDto.getCounteractedInvoiceNo(), 8));//被冲红的发票号码
		}
		//vcInvoicePrint.setCanceledOpr(printDto.getCanceledOpr());//作废人
		//vcInvoicePrint.setCanceldDate(printDto.getCanceldDate());//作废时间
		vcInvoicePrint.setRemark(printDto.getRemark());//备注
		vcInvoicePrint.setOperatorDate(printDto.getOperatorDate());//操作时间
		vcInvoicePrint.setPrintKind(printDto.getPrintKind());//发票开具类型
		vcInvoicePrint.setTaxOrgCode(printDto.getTaxOrgCode());//纳税人主管税务机关代码
		vcInvoicePrint.setManageCode(printDto.getManageCode());//管理编码
		vcInvoicePrint.setSettleItem(printDto.getSettleItem());//结算项目
		vcInvoicePrint.setBelongStage(printDto.getBelongStage());//所属期
		vcInvoicePrint.setMachinePrintedCode(printDto.getMachinePrintedCode());//机打代码
		vcInvoicePrint.setMachinePrintedNo(printDto.getMachinePrintedNo());//机打号码
		vcInvoicePrint.setStartDate(printDto.getStartDate());//起始时间
		vcInvoicePrint.setEndDate(printDto.getEndDate());//结束时间
		vcInvoicePrint.setCheckNum(printDto.getCheckNum());//校验码
		vcInvoicePrint.setTaxAmount(printDto.getTaxAmount());//税额
		vcInvoicePrint.setTaxRate(printDto.getTaxRate());//税率
		vcInvoicePrint.setUpperAmount(printDto.getUpperAmount());//大写金额
		vcInvoicePrint.setIndustryCode(printDto.getIndustryCode());//行业分类对应的代码
		vcInvoicePrint.setIndustryName(printDto.getIndustryName());//行业分类对应的名称
		vcInvoicePrint.setPrintTypeCode(printDto.getPrintTypeCode());//套打样式代码
		vcInvoicePrint.setAccount(printDto.getAccount());//银行账号
		vcInvoicePrint.setBank(printDto.getBank());//开户银行
		vcInvoicePrint.setRegistration(printDto.getRegistration());//工商登记证号
		vcInvoicePrint.setZgIndustry(printDto.getZgIndustry());//征管行业
		vcInvoicePrint.setBankCode(printDto.getBankCode());//银行编码
		vcInvoicePrint.setInvoiceKindCode(printDto.getInvoiceKindCode());//发票种类代码
		vcInvoicePrint.setInvoiceKindName(printDto.getInvoiceKindName());//发票种类名称
		vcInvoicePrint.setInvoiceType(printDto.getInvoiceType());//发票类型
		vcInvoicePrint.setItems(printDto.getItems());//项目摘要
		vcInvoicePrint.setCreatedBy(userCode);    //创建人
		vcInvoicePrint.setDateCreated(new Date());  //创建时间
		vcInvoicePrint.setUpdatedBy(userCode);  //修改人
		vcInvoicePrint.setDateUpdated(new Date());  //修改时间
		
		vcInvoicePrint.setDocVerCode(versionCode);//单证类型代码
		vcInvoicePrint.setOrgCode(orgCode);//开票机构/核销机构
		vcInvoicePrint.setStatus("1");//发票状态
		vcInvoicePrint.setIsUploadPlat("0");//是否上传平台
		
		//增加纳税人电脑编码的保存
		if(SysConst.COMCODE_FJ.equals(orgCode.substring(0, 4))){
			VcInvoicePurchase vcInvoicePurchase = docExecuteUsedDao.
				findVcInvoicePurchase(versionCode, printDto.getInvoiceCode(), printDto.getInvoiceNo());
			if(vcInvoicePurchase!=null){
				vcInvoicePrint.setComputerNo(vcInvoicePurchase.getComputerNo());
			}
		}
		return vcInvoicePrint;
	}
	/**
	 * 转换发票扩展表
	 * @param extDto
	 * @return
	 */
	private List<VcInvoicePrintExt> generateInvoicePrintExt(InvoicePrintExtDto extDto, String userCode){
		List<VcInvoicePrintExt> list = new ArrayList<VcInvoicePrintExt>();
		VcInvoicePrintExt printExt = new VcInvoicePrintExt();
		printExt.setRiskCode(extDto.getRiskCode());//承保险种代码
		printExt.setRiskName(extDto.getRiskName());//承保险种名称
		printExt.setPremium(extDto.getPremium());//保险费
		printExt.setApplyName(extDto.getApplyName());//投保人
		printExt.setCarAreaName(extDto.getCarAreaName());//车籍地名称
		printExt.setCarAreaCode(extDto.getCarAreaCode());//车籍地代码
		printExt.setLicenseNo(extDto.getLicenseNo());//车牌号码
		printExt.setCarKind(extDto.getCarKind());//车辆类别
		printExt.setUnit(extDto.getUnit());//单位
		printExt.setQuantity(extDto.getQuantity());//数量
		printExt.setLastEndDate(extDto.getLastEndDate());//上期缴纳交强险截止日期
		printExt.setUseNature(extDto.getUseNature());//使用性质
		printExt.setItem(extDto.getItem());//项目
		printExt.setAmount(extDto.getAmount());//保险金额
		printExt.setEngineNo(extDto.getEngineNo());//发动机号
		printExt.setFrameNo(extDto.getFrameNo());//车架号
		printExt.setPayDateStart(extDto.getPayDateStart());//所缴日期起
		printExt.setPayDateEnd(extDto.getPayDateEnd());//所缴日期止
		printExt.setTaxAmount(extDto.getTaxAmount());//代收车船税
		printExt.setLateFee(extDto.getLateFee());//滞纳金
		printExt.setCurrency(extDto.getCurrency());//币种
		printExt.setExchangeRate(extDto.getExchangeRate());//汇率
		printExt.setQuotePriceDate(extDto.getQuotePriceDate());//牌价日
		printExt.setForCurrAmount(extDto.getForCurrAmount());//外币金额
		printExt.setAreaCode(extDto.getAreaCode());//投保地代码
		printExt.setAreaName(extDto.getAreaName());//投保地名称
		printExt.setFuelCategory(extDto.getFuelCategory());//能源类别
		printExt.setCreatedBy(userCode);
		printExt.setDateCreated(new Date());
		printExt.setUpdatedBy(userCode);
		printExt.setDateUpdated(new Date());
		list.add(printExt);
		return list;
	}
	/**
	 * 转换发票明细表
	 * @param detDtoList
	 * @return
	 */
	private List<VcInvoicePrintDet> generateInvoicePrintDet(List<InvoicePrintDetailDTO> detDtoList, String userCode){
		List<VcInvoicePrintDet> list = new ArrayList<VcInvoicePrintDet>();
		for(InvoicePrintDetailDTO detailDto : detDtoList){
			VcInvoicePrintDet printDet = new VcInvoicePrintDet();
			printDet.setSerialNo(detailDto.getSerialNo());//序号
			printDet.setIndustry(detailDto.getIndustry());//行业分类
			printDet.setItemKind(detailDto.getItemKind());//项目大类
			printDet.setItemCode(detailDto.getItemCode());//开具项目代码
			printDet.setItemName(detailDto.getItemName());//开具项目名称
			printDet.setItemRemark(detailDto.getItemRemark());//项目说明
			printDet.setTaxRate(detailDto.getTaxRate());//税率
			printDet.setTaxAmount(detailDto.getTaxAmount());//税额
			printDet.setContent(detailDto.getContent());//开具内容
			printDet.setUnitName(detailDto.getUnitName());//单位名称
			printDet.setUnitPrice(detailDto.getUnitPrice());//单价
			printDet.setQuantity(detailDto.getQuantity());//数量
			printDet.setAmount(detailDto.getAmount());//金额
			printDet.setVesselTax(detailDto.getVesselTax());//代收车船税
			printDet.setTaxYear(detailDto.getTaxYear());//税款所属期年
			printDet.setTaxMonthStart(detailDto.getTaxMonthStart());//税款所属期起始月
			printDet.setTaxMonthEnd(detailDto.getTaxMonthEnd());//税款所属期终止月
			printDet.setLateFee(detailDto.getLateFee());//滞纳金
			printDet.setCreatedBy(userCode);
			printDet.setDateCreated(new Date());
			printDet.setUpdatedBy(userCode);
			printDet.setDateUpdated(new Date());
			list.add(printDet);
		}
		return list;
	}

	public void setVcDocStatusDao(VcDocStatusDao vcDocStatusDao) {
		this.vcDocStatusDao = vcDocStatusDao;
	}

	public void setDocExecuteUsedDao(DocExecuteUsedDao docExecuteUsedDao) {
		this.docExecuteUsedDao = docExecuteUsedDao;
	}

	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}
	
}
