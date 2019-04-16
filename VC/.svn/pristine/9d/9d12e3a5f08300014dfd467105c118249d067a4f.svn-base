package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.util.logging.Logger;
import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.StringUtil;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoicePrintExt;
import com.tapi.tcs.vc.schema.model.VcInvoicePurchase;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.store.dao.VcAvailableDocDao;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcDocStatusDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.ExtendDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedBusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedResultDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedInfoDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoicePrintDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoicePrintExtDto;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.service.VcInvoiceDoUsedService;

public class VcInvoiceDoUsedServiceImpl implements VcInvoiceDoUsedService {
    private VcDocStatusDao vcDocStatusDao;
    private NormalVerificationDao normalVerificationDao;
    private VcAvailableDocDao vcAvailableDocDao;
    /**单证核销接口DAO*/
	private DocExecuteUsedDao docExecuteUsedDao;

    public InvoiceDoUsedResponse executeInvoiceDoUsedHandle(InvoiceDoUsedRequest invoiceDoUsedRequest)
            throws BusinessException {
        RequestHeadDTO requestHead = invoiceDoUsedRequest.getRequestHead();
        InvoiceDoUsedRequestDTO body = invoiceDoUsedRequest.getRequestBody();
        ResponseHeadDTO responseHead = new ResponseHeadDTO();
        InvoiceDoUsedResponseDTO responseBody = new InvoiceDoUsedResponseDTO();
        InvoiceDoUsedResponse response = new InvoiceDoUsedResponse();
        // 单证种类代码
        String docTypeCode = body.getDocTypeCode();
        // 单证类型代码
        String docVerCode = body.getDocVerCode();
        // 操作员代码
        String operatorCode = body.getOperatorCode();
        // 操作员归属机构代码
        String orgCode = body.getOrgCode();

        // 系统代码
        String sysCode = requestHead.getSystemCode();
        Date nowDate = new Date();
        List<InvoiceDoUsedResultDTO> docDoUsedResultList=new ArrayList<InvoiceDoUsedResultDTO>();
        InvoiceDoUsedResultDTO  resultDTO=null;  
        try {
            // 单证流水号列表(单证流水号/业务号列表)
            List<InvoiceDoUsedInfoDTO> docNumInfoDTOs = body.getDocNumInfoDTOs();
            // 装载实体、用于保存核销表
            List<VcNormalVerification> saveList = new ArrayList<VcNormalVerification>();
            // 装载实体、用于删除可使用单证表
            List<VcAvailableDoc> delList = new ArrayList<VcAvailableDoc>();
            boolean hasError=false;
            for (InvoiceDoUsedInfoDTO doUsedInfoDTO : docNumInfoDTOs) {
                hasError=false;
                resultDTO=new InvoiceDoUsedResultDTO();
                // 发票号码
                String docNum = doUsedInfoDTO.getDocNum();
                // 发票代码（印刷批次）
                String pressBatchNo = doUsedInfoDTO.getPressBatchNo();
                List<VcAvailableDoc> vcAvailableDocList =null;
                try {
                	 vcAvailableDocList = vcDocStatusDao.checkDocNumIsValid(docNum, docVerCode,
                             operatorCode, orgCode, pressBatchNo, null);
				} catch (Exception e) {
					throw new BusinessException("查询流水号是否可用时发生异常");
				}
                // 业务号列表(业务号/保单业务号)
                List<InvoiceDoUsedBusinessNoDTO> businessNoDTOs = doUsedInfoDTO.getBusinessNoDTOs();
                // 核销明细列表
                List<VcNormalVerifiedDet> vcNormalVerifiedDetList = new ArrayList<VcNormalVerifiedDet>();
                boolean reprintFlag = true;// 是否重打
                VcNormalVerification vcNormal = null;
                for (InvoiceDoUsedBusinessNoDTO doUsedBusinessNoDTO : businessNoDTOs) {
                    // 业务号(保单号、批单号)
                    String businessNo = doUsedBusinessNoDTO.getBusinessNo();
                    // 批单对应的保单业务号
                    String relationBusinessNo = doUsedBusinessNoDTO.getRelationBusinessNo();

                    // 缴费期次
                    String payNo = doUsedBusinessNoDTO.getPayNo();
                    
                    if (StringUtils.isEmpty(payNo)) {
                        payNo = "1";// 默认等于1
                    }
                    // 红冲标志:0-非红冲；1-红冲负数；2-红冲正数
                    String counteractFlag = doUsedBusinessNoDTO.getCounteractFlag();
                    if (StringUtils.isEmpty(counteractFlag)) {
                        counteractFlag = "0";
                    }
                    // 拆分批次
                    String batchNo = doUsedBusinessNoDTO.getBatchNo();

                    //modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
                    String payerCode = "";
                    List<ExtendDTO> expandsLists = null;
                    if(doUsedBusinessNoDTO.getExpandsList() !=null && doUsedBusinessNoDTO.getExpandsList().size() >0 ){
                    	expandsLists = doUsedBusinessNoDTO.getExpandsList();
                    	//收付系统共保业务发票打印
                    	for(ExtendDTO extendDTO : expandsLists){
                    		if(("payerCode").equals(extendDTO.getKey())){
                    			payerCode = extendDTO.getValue();
                    		}
                    	}
                    }
                    
                    List<Object[]> vcNormalVerifications = vcDocStatusDao.checkBizNoIsUsed(businessNo, payNo,
                            docVerCode, counteractFlag, batchNo, payerCode);
                    //modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
                    if (vcNormalVerifications != null && vcNormalVerifications.size() > 0) {
                        Object[] obj = vcNormalVerifications.get(0);
                        // 已核销更新数据
                        vcNormal = (VcNormalVerification) obj[0];

                        // 已核销（非同一个流水号）
                        if (!(docVerCode.equals(vcNormal.getDocVerCode())
                                && pressBatchNo.equals(vcNormal.getPressBatchNo()) && docNum.equals(vcNormal
                                .getDocNum()))) {    
                            resultDTO.setStatus("2");
                            resultDTO.setBusinessNo(businessNo);
                            resultDTO.setDocVerCode(vcNormal.getDocVerCode());
                            // 核销的业务号，关联的流水号
                            resultDTO.setDocNum(vcNormal.getDocNum());
                            // 核销的业务号，关联的流水号的批次号
                            resultDTO.setPressBatchNo(vcNormal.getPressBatchNo());
                            resultDTO.setRemark("核销失败，业务号已核销！");
                            hasError=true;
                            break;  //跳出本次业务号循环判断                       
                        }
                    } else {
                        // 若流水号不可用，返回错误提示信息
                        if (vcAvailableDocList==null || vcAvailableDocList.size() < 1) {                           
                            resultDTO.setStatus("1");
                            resultDTO.setDocNum(docNum);
                            resultDTO.setPressBatchNo(pressBatchNo);
                            resultDTO.setRemark("核销失败，单证流水号不可用！");
                            hasError=true;
                            break;//跳出本次业务号循环判断
                        }
                        if (DateUtils.compare(DateUtils.reset(vcAvailableDocList.get(0).getDeadline()), DateUtils.reset(new Date())) < 0) {
                            resultDTO.setStatus("1");
                            resultDTO.setDocNum(docNum);
                            resultDTO.setPressBatchNo(pressBatchNo);
                            resultDTO.setRemark("核销失败，单证["+docNum+"]已过使用截止期！");
                            hasError=true;
                            break;//跳出本次业务号循环判断
                        }
                        reprintFlag = false; // /非重打

                        // 增加核销明细表的保存
                        VcNormalVerifiedDet vcNormalVerifiedDet = new VcNormalVerifiedDet();
                        vcNormalVerifiedDet.setBusinessNo(businessNo);

                        vcNormalVerifiedDet.setRelBusinessNo(relationBusinessNo);

                        // 缴费期次、红冲标志、拆分标志
                        vcNormalVerifiedDet.setPayNo(payNo); // 缴费期次
                        vcNormalVerifiedDet.setCounteractFlag(counteractFlag);// 红冲标志
                        vcNormalVerifiedDet.setBatchNo(batchNo);// 拆分批次
                        //modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
                        vcNormalVerifiedDet.setPayerCode(payerCode);//共保付款人
                        //modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
                        
                        // 收付系统操作人不为空
                        vcNormalVerifiedDet.setCreatedBy(operatorCode);
                        vcNormalVerifiedDet.setDateCreated(nowDate);
                        vcNormalVerifiedDet.setUpdatedBy(operatorCode);
                        vcNormalVerifiedDet.setDateUpdated(nowDate);
                        vcNormalVerifiedDetList.add(vcNormalVerifiedDet);

                    }
                }
                if (hasError){
                    docDoUsedResultList.add(resultDTO);
                }else{
                    // 重打(已经用同一个发票号核销过)
                    if (reprintFlag) {
                        // 核销时间在同一个月
                        Date verifiedTime = vcNormal.getVerifiedTime();
                        int months = DateUtils.getMonthDifference(verifiedTime, nowDate);
                        String docStatus = "U3";
                        if (months == 0) {
                            docStatus = "U1";
                        }
                        vcNormal.setVerifiedTime(nowDate);
                        vcNormal.setDocStatus(docStatus);
                        vcNormal.setUpdatedBy(operatorCode);
                        vcNormal.setDateUpdated(nowDate);
                        normalVerificationDao.update(vcNormal);
                        resultDTO.setRemark("重打成功！");
                    } else {//新核销
                        // 业务号无核销记录且流水号可用，进行核销操作
                        VcAvailableDoc vcAvailableDoc = vcAvailableDocList.get(0);
                        VcNormalVerification vcNormalVerification = new VcNormalVerification();
                        vcNormalVerification.setDocVerCode(docVerCode);
                        vcNormalVerification.setDocNum(docNum);
                        vcNormalVerification.setPressBatchNo(pressBatchNo);
                        // 取第一个业务号                       
                        if (vcNormalVerifiedDetList==null || vcNormalVerifiedDetList.size()<1) {
                            resultDTO.setStatus("12");                
                            resultDTO.setDocVerCode(vcNormal.getDocVerCode());             
                            resultDTO.setDocNum(docNum);
                            //流水号的批次号
                            resultDTO.setPressBatchNo(pressBatchNo); 
                            resultDTO.setRemark("核销失败，未有对应的 核销子记录！");
                            docDoUsedResultList.add(resultDTO);
                            continue; //进入下一个发票流水号的操作
                        }
                        vcNormalVerification.setBusinessNo(vcNormalVerifiedDetList.get(0).getBusinessNo());
                        vcNormalVerification.setRelBusinessNo(vcNormalVerifiedDetList.get(0).getRelBusinessNo());
                        vcNormalVerification.setDocStatus("U1");
                       
                       vcNormalVerification.setVerifiedOprCode(operatorCode);
                       
                        vcNormalVerification.setVerifiedOrgCode(orgCode);
                        vcNormalVerification.setVerifiedTime(nowDate);
                        // 核销原因（1：打印/2：补录/3：其他）
                        vcNormalVerification.setVerifiedReason("1");
                      
                        vcNormalVerification.setCreatedBy(operatorCode);
                        
                        vcNormalVerification.setDateCreated(nowDate);
                       
                        vcNormalVerification.setUpdatedBy(operatorCode);
                       
                        vcNormalVerification.setDateUpdated(nowDate);
                        
                        // 设置明细列表
                        vcNormalVerification.setVcNormalVerifiedDet(vcNormalVerifiedDetList);
                        // 设置明细表和主表的关联关系
                        for (VcNormalVerifiedDet vcNormalVerifiedDet : vcNormalVerifiedDetList) {
                            vcNormalVerifiedDet.setVcNormalVerification(vcNormalVerification);
                        }
                       // 保存核销表
                        normalVerificationDao.save(vcNormalVerification);
                        // 删除可使用单证表
                        vcAvailableDocDao.delete(vcAvailableDoc);
                        
                        //发票开具信息表                       
                        if(orgCode.length()>=4 && (SysConst.TAXATION_ORGS).indexOf(orgCode.substring(0, 4))>-1){
                           VcInvoicePrint vcInvoicePrint = this.generateInvoicePrintAndExt(doUsedInfoDTO, docVerCode, docTypeCode, operatorCode, orgCode);  
                           try{
                           normalVerificationDao.save(vcInvoicePrint);
                           }catch(Exception e){
                               throw new DaoException("保存发票开具信息出错!");  
                           }
                        }
                        resultDTO.setRemark("核销成功！");
                    }                
                resultDTO.setStatus("0");                
                resultDTO.setDocVerCode(docVerCode);             
                resultDTO.setDocNum(docNum);
                //流水号的批次号
                resultDTO.setPressBatchNo(pressBatchNo);               
                docDoUsedResultList.add(resultDTO);
                }
            }          
        } catch (DaoException de) {
            throw new BusinessException(de.getMessage());
        }
        responseHead.setResponseCode("000");
        responseHead.setResponseMessage("操作成功！");
        responseBody.setInvoiceDoUsedResultList(docDoUsedResultList);
        response.setResponseHead(responseHead);
        response.setResponseBody(responseBody);
        return response;
    }

    
    /**
     * 把请求发票信息转换成数据库发票信息表
     * 
     * @param invoiceDTO
     * @return
     */
    private VcInvoicePrint generateInvoicePrintAndExt(InvoiceDoUsedInfoDTO invoiceDTO, String docVerCode, String docTypeCode,
           String operatorCode, String orgCode) throws BusinessException {
       //发票开具主表信息
        VcInvoicePrint vcInvoicePrint = generateInvoicePrint(invoiceDTO, docVerCode, docTypeCode, operatorCode,orgCode);
        // 发票开具信息扩展表        
        VcInvoicePrintExt extDto= generateInvoicePrintExt(invoiceDTO, docVerCode, docTypeCode, operatorCode,orgCode);
        extDto.setVcInvoicePrint(vcInvoicePrint);
        List<VcInvoicePrintExt> extList = new ArrayList<VcInvoicePrintExt>();
        extList.add(extDto);
        vcInvoicePrint.setVcInvoicePrintExtList(extList);
        return vcInvoicePrint;
    }

    /**
     * 转换发票主表信息
     * 
     * @param printDto
     * @return
     */
    private VcInvoicePrint generateInvoicePrint(InvoiceDoUsedInfoDTO invoiceDTO, String docVerCode, String docTypeCode,
            String operatorCode, String orgCode) throws BusinessException {
        VcInvoicePrint vcInvoicePrint = new VcInvoicePrint();
       InvoicePrintDTO printDto = invoiceDTO.getInvoicePrintDTO();
        vcInvoicePrint.setPolicyNo(printDto.getPolicyNo());// 保单号
        vcInvoicePrint.setEndorseNo(printDto.getEndorseNo());// 批单号
         vcInvoicePrint.setInvoiceCode(invoiceDTO.getPressBatchNo());//发票代码
        // vcInvoicePrint.setInvoiceName(printDto.getInvoiceName());//发票名称
        vcInvoicePrint.setInvoiceNo(invoiceDTO.getDocNum());//发票号码
        vcInvoicePrint.setPrintDate(printDto.getPrintTime());// 开票日期
        vcInvoicePrint.setAmount(printDto.getSumAmount());// 开票金额
        vcInvoicePrint.setPayerName(printDto.getPayerName());// 付款方名称
        vcInvoicePrint.setPayerCode(printDto.getPayerID());// 付款方号码
        vcInvoicePrint.setRecipientName(printDto.getTaxpayerName());// 收款方名称
        vcInvoicePrint.setRecipientCode(printDto.getTaxpayerID());// 收款方号码
        vcInvoicePrint.setTaxpayerName(printDto.getTaxpayerName());//纳税人名称
        vcInvoicePrint.setTaxpayerCode(printDto.getTaxpayerID());//纳税人识别号
        vcInvoicePrint.setBillerName(printDto.getBillerName());// 开票员名称
        vcInvoicePrint.setRecipientOpr(printDto.getRecipientOpr());// 收款人名称
        //vcInvoicePrint.setCounteractInvoiceCode(invoiceDTO.getPressBatchNo());//冲红的发票代码
        //vcInvoicePrint.setCounteractInvoiceNo(invoiceDTO.getDocNum());//冲红的发票hao码
        //vcInvoicePrint.setCounteractedInvoiceCode(printDto.getCounteractedInvoiceCode());//冲红的发票代码
        //vcInvoicePrint.setCounteractedInvoiceNo(printDto.getCounteractedInvoiceNo());//冲红的发票hao码
        
        vcInvoicePrint.setCounteractedInvoiceCode(printDto.getCounteractedInvoiceCode());// 被冲红的发票代码
        if (StringUtils.isNotEmpty(printDto.getCounteractedInvoiceNo())) {
            vcInvoicePrint.setCounteractedInvoiceNo(StringUtil.formatNumberLength(printDto.getCounteractedInvoiceNo(),
                    8));// 被冲红的发票号码
        }
        vcInvoicePrint.setCanceledOpr(printDto.getCanceledOprName());// 作废人
        vcInvoicePrint.setCanceldDate(printDto.getCanceledTime());// 作废时间
        vcInvoicePrint.setRemark(printDto.getRemark());// 备注
        vcInvoicePrint.setOperatorDate(printDto.getOperatorDate());// 操作时间
        vcInvoicePrint.setPrintKind(printDto.getPrintKind());// 发票开具类型
        // vcInvoicePrint.setTaxOrgCode(printDto.getTaxOrgCode());//纳税人主管税务机关代码
        // vcInvoicePrint.setManageCode(printDto.getManageCode());//管理编码
        // vcInvoicePrint.setSettleItem(printDto.getSettleItem());//结算项目
        // vcInvoicePrint.setBelongStage(printDto.getBelongStage());//所属期
        // vcInvoicePrint.setMachinePrintedCode(printDto.getMachinePrintedCode());//机打代码
        // vcInvoicePrint.setMachinePrintedNo(printDto.getMachinePrintedNo());//机打号码
        vcInvoicePrint.setStartDate(printDto.getStartDate());// 起始时间
        vcInvoicePrint.setEndDate(printDto.getEndDate());// 结束时间
        vcInvoicePrint.setCheckNum(printDto.getCheckNum());// 校验码
        // vcInvoicePrint.setTaxAmount(printDto.getTaxAmount());//税额
        // vcInvoicePrint.setTaxRate(printDto.getTaxRate());//税率
        vcInvoicePrint.setUpperAmount(printDto.getUpperSumAmount());// 大写金额
        // vcInvoicePrint.setIndustryCode(printDto.getIndustryCode());//行业分类对应的代码
        // vcInvoicePrint.setIndustryName(printDto.getIndustryName());//行业分类对应的名称
        vcInvoicePrint.setPrintTypeCode(printDto.getPrintTypeCode());// 套打样式代码
        // vcInvoicePrint.setAccount(printDto.getAccount());//银行账号
        // vcInvoicePrint.setBank(printDto.getBank());//开户银行
        // vcInvoicePrint.setRegistration(printDto.getRegistration());//工商登记证号
        // vcInvoicePrint.setZgIndustry(printDto.getZgIndustry());//征管行业
        // vcInvoicePrint.setBankCode(printDto.getBankCode());//银行编码
        // vcInvoicePrint.setInvoiceKindCode(printDto.getInvoiceKindCode());//发票种类代码
        // vcInvoicePrint.setInvoiceKindName(printDto.getInvoiceKindName());//发票种类名称
        // vcInvoicePrint.setInvoiceType(printDto.getInvoiceType());//发票类型
        // vcInvoicePrint.setItems(printDto.getItems());//项目摘要
        vcInvoicePrint.setCreatedBy(operatorCode); // 创建人
        vcInvoicePrint.setDateCreated(new Date()); // 创建时间
        vcInvoicePrint.setUpdatedBy(operatorCode); // 修改人
        vcInvoicePrint.setDateUpdated(new Date()); // 修改时间

        vcInvoicePrint.setDocVerCode(docVerCode);// 单证类型代码
        vcInvoicePrint.setOrgCode(orgCode);// 开票机构/核销机构
        vcInvoicePrint.setStatus("1");// 发票状态
        vcInvoicePrint.setIsUploadPlat("0");// 是否上传平台

        // 增加纳税人电脑编码的保存
         if(SysConst.COMCODE_FJ.equals(orgCode.substring(0, 4))){
	         VcInvoicePurchase vcInvoicePurchase = docExecuteUsedDao.findVcInvoicePurchase(docVerCode, vcInvoicePrint.getInvoiceCode(), vcInvoicePrint.getInvoiceNo());
	         if(vcInvoicePurchase!=null){
	        	 vcInvoicePrint.setComputerNo(vcInvoicePurchase.getComputerNo());
	         }
         }
        return vcInvoicePrint;
    }

    /**
     * 转换发票扩展表
     * 
     * @param extDto
     * @return
     */
    private VcInvoicePrintExt generateInvoicePrintExt(InvoiceDoUsedInfoDTO invoiceDTO, String docVerCode, String docTypeCode,
            String operatorCode, String orgCode) throws BusinessException {
       // List<VcInvoicePrintExt> list = new ArrayList<VcInvoicePrintExt>();
        InvoicePrintExtDto extDto = invoiceDTO.getInvoicePrintExtDto();
        VcInvoicePrintExt printExt = new VcInvoicePrintExt();
        printExt.setRiskCode(extDto.getRiskCode());// 承保险种代码
        printExt.setRiskName(extDto.getRiskName());// 承保险种名称
        printExt.setPremium(extDto.getPremium());// 保险费
        printExt.setApplyName(extDto.getApplyName());// 投保人
        printExt.setCarAreaName(extDto.getCarAreaName());// 车籍地名称
        printExt.setCarAreaCode(extDto.getCarAreaCode());// 车籍地代码
        printExt.setLicenseNo(extDto.getLicenseNo());// 车牌号码
        printExt.setCarKind(extDto.getCarKind());// 车辆类别
        printExt.setUnit(extDto.getUnit());// 单位
        printExt.setQuantity(extDto.getQuantity());// 数量
        printExt.setLastEndDate(extDto.getLastEndDate());// 上期缴纳交强险截止日期
        printExt.setUseNature(extDto.getUseNature());// 使用性质
        // printExt.setItem(extDto.getItem());//项目
        // printExt.setAmount(extDto.getAmount());//保险金额
        printExt.setEngineNo(extDto.getEngineNo());// 发动机号
        printExt.setFrameNo(extDto.getFrameNo());// 车架号
        // printExt.setPayDateStart(extDto.getTaxMonthStart());//所缴日期起
        // printExt.setPayDateEnd(extDto.getTaxMonthEnd());//所缴日期止
        printExt.setTaxAmount(extDto.getTaxAmount());// 代收车船税
        printExt.setLateFee(extDto.getLateFee());// 滞纳金
        printExt.setCurrency(extDto.getCurrency());// 币种
        printExt.setExchangeRate(extDto.getExchangeRate());// 汇率
        printExt.setQuotePriceDate(extDto.getQuotePriceDate());// 牌价日
        printExt.setForCurrAmount(extDto.getForCurrAmount());// 外币金额
        printExt.setAreaCode(extDto.getAreaCode());// 投保地代码
        printExt.setAreaName(extDto.getAreaName());// 投保地名称
        printExt.setFuelCategory(extDto.getFuelCategory());// 能源类别
        printExt.setCreatedBy(operatorCode);
        printExt.setDateCreated(new Date());
        printExt.setUpdatedBy(operatorCode);
        printExt.setDateUpdated(new Date());
        
        printExt.setItemCode(extDto.getItemCode());
        printExt.setItemName(extDto.getItemName());
        printExt.setTaxYear(extDto.getTaxYear());
        printExt.setTaxMonthStart(extDto.getTaxMonthStart());
        printExt.setTaxMonthEnd(extDto.getTaxMonthEnd());
        return printExt;
    }

    public void setVcDocStatusDao(VcDocStatusDao vcDocStatusDao) {
        this.vcDocStatusDao = vcDocStatusDao;
    }
    public void setNormalVerificationDao(NormalVerificationDao normalVerificationDao) {
        this.normalVerificationDao = normalVerificationDao;
    }

    public void setVcAvailableDocDao(VcAvailableDocDao vcAvailableDocDao) {
        this.vcAvailableDocDao = vcAvailableDocDao;
    }

	public void setDocExecuteUsedDao(DocExecuteUsedDao docExecuteUsedDao) {
		this.docExecuteUsedDao = docExecuteUsedDao;
	}

}
