package com.tapi.tcs.vc.invoice.taxupload.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.invoice.dao.VCInvoiceUploadTaxPlatDao;
import com.tapi.tcs.vc.invoice.taxupload.common.helper.Constant;
import com.tapi.tcs.vc.invoice.taxupload.common.intf.VCInvoiceTaxUploadService;
import com.tapi.tcs.vc.invoice.taxupload.common.service.VCInvoiceTaxUploadProxyService;
import com.tapi.tcs.vc.invoice.taxupload.common.vo.InvoiceTaxUploadResult;
import com.tapi.tcs.vc.invoice.taxupload.common.vo.VCUploadTaxRequestBodyDto;
import com.tapi.tcs.vc.invoice.taxupload.common.vo.VCUploadTaxRequestDto;
import com.tapi.tcs.vc.invoice.taxupload.common.vo.VCUploadTaxRequestHeadDto;
import com.tapi.tcs.vc.invoice.taxupload.common.vo.VCUploadTaxResponseDto;
import com.tapi.tcs.vc.schema.model.VcPubCode;
/**
 * 通用上传地税实现
 *
 */
public class VCCommonInvoiceTaxUploadProxyImpl implements VCInvoiceTaxUploadProxyService {

	private VCInvoiceTaxUploadService vcInvoiceTaxUploadService;
	private VCInvoiceUploadTaxPlatDao vcInvoiceUploadTaxPlatDao;
	private VcPubCodeManagerDao vcPubCodeManagerDao;

    
	private String rootXmlName;
	private String params;
	private String companyCode;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 定时发票上传地税入口,机构代码(companyCode)注入多个时，用逗号分隔
	 */
	public void uploadInvoiceTaxSchedule() {
		String[] arrCompanyCode = null;
		if(!StringUtils.isEmpty(this.companyCode)) {
			arrCompanyCode = this.companyCode.split(",");
		}
		logger.info(String.format("定时发票上传地税任务启动，上传机构{%s}", this.companyCode));
		for(String comCode : arrCompanyCode) {
			
			uploadInvoiceTax(comCode);
		}
		logger.info(String.format("定时发票上传地税任务结束，上传机构{%s}", this.companyCode));
	}
	
	/**
	 * 根据机构代码生成上传平台使用的登录信息报文
	 * @param companyCode 机构代码
	 * @return 登录信息报文
	 */
	public List<String> generateLoginXML(final String companyCode) {
		if(StringUtils.isEmpty(companyCode)) {
			logger.error("机构代码不能为空!");
			return null;
		}
		List<String> resultList = null;
		VcPubCode batchNumPubCode=null;
		VcPubCode curBatchPubCode=null;
		try {
		    
		    //贵州上传地税，分批次上传
		    long totalNum=vcInvoiceUploadTaxPlatDao.queryTaxLoginCount(companyCode+"%");
		    int curBatch=1;          
            int batchNo=1;   
		    
            //分几批进行上传
		    batchNumPubCode= vcPubCodeManagerDao.getVcPubCode("GzInvoiceUpLoad", "TotalBatchNum");
		    //当前是第几批
            curBatchPubCode= vcPubCodeManagerDao.getVcPubCode("GzInvoiceUpLoad", "CurBatchNum");
            if(batchNumPubCode!=null && curBatchPubCode!=null){
                if(StringUtils.isNotEmpty(batchNumPubCode.getCodeCName())){
                    batchNo =Integer.valueOf(batchNumPubCode.getCodeCName());
                }
                if(batchNo<=0) {
                    batchNo=1; 
                }                
                if(StringUtils.isNotEmpty(curBatchPubCode.getCodeCName())){
                    curBatch =Integer.valueOf(curBatchPubCode.getCodeCName());
                } 
            }else{
                curBatch=1;          
                batchNo=1;   
            }
		   
            long perPage=totalNum/batchNo+1;   
		    long start=perPage*(curBatch-1)+1;
		    long end=perPage*curBatch;
		    logger.info(String.format("\t**贵州机构{%s}上传地税第{%s}批[{%s}- {%s}] *****************", companyCode,curBatch,start,end));
			resultList = vcInvoiceUploadTaxPlatDao.generateTaxLoginToXML(companyCode+"%",start,end);
			
			if(batchNumPubCode!=null && curBatchPubCode!=null){
    			//下一次上传的批次
    			int nextBatch=curBatch+1;
    			if(nextBatch>batchNo){
    			    nextBatch=1; 
    			}
    			this.updateCurBatchNo(curBatchPubCode,String.valueOf(nextBatch));
			}
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		if(resultList == null || resultList.size() == 0) {
			
			logger.error(String.format("机构{%s}未查询到地税用户配置数据！", companyCode));
		}
		return resultList;
	}
	
	/**
	 * 生成发票XML格式数据,参数顺序需要注意与自己写的SQL中的点位符对应
	 * @return 发票XML格式数据
	 */
	public String generateInvoiceXML(Object... args) {
		String result = "";
		try {
			
			result = vcInvoiceUploadTaxPlatDao.generateInvoiceToXML(args);
			if (result != null && result.length() > 0) {
				logger.debug("=======贵州发票报文begin========");	
				logger.debug(result);				
			}
		}catch(DaoException e) {
			logger.error(e.getMessage(), e);
		}catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
		//附加根节点
		if(!StringUtils.isEmpty(result)) {
			
			result = String.format("<%s>%s</%s>", this.rootXmlName, result, this.rootXmlName);
		}
		
		return result;
	}
	
	/**
	 * 发票上传地税入口,根据机构代码提取需要上传地税的机构，并上传地税
	 */
	public void uploadInvoiceTax(final String companyCode) {
		if(StringUtils.isEmpty(companyCode)) {
			logger.error("机构代码不能为空!");
		}else {
			//地税用户信息XML串
			List<String> loginXmlList = generateLoginXML(companyCode);
			for(String loginXml : loginXmlList) {
				Pattern p = Pattern.compile("<ORG_CODE>(.*)</ORG_CODE>");
				Matcher m = p.matcher(loginXml);
				String orgCode = "";
				if(m.find()) {
					orgCode = m.group(1);
				}
				logger.info(String.format("\t*****************  上传地税机构{%s}  *****************", orgCode));
				//发票数据XML串
				String invoiceXml = generateInvoiceXML(new Object[]{orgCode,orgCode});
				sendToESBService(invoiceXml, loginXml);
//				}
			}
		}
	}
	
	/**
	 * 发票上传地税入口，根据已生成的发票XML格式数据和地税登录XML数据调用上传地税接口
	 */
	public void uploadInvoiceTax(final String invoiceXML, final String taxLoginXML) {

		sendToESBService(invoiceXML, taxLoginXML);
	}
	/**
	 * 调用ESB服务，发送请求给地税平台
	 * @param invoiceXML 上传地税数据报文
	 * @param taxLoginXML 上传地税登录信息报文
	 */
	private void sendToESBService(final String invoiceXML, final String taxLoginXML) {
		if(StringUtils.isEmpty(taxLoginXML)) {
			
			logger.error("\t\t未执行上传地税操作，原因：上传地税登录信息未配置！");
		}else if(StringUtils.isEmpty(invoiceXML)) {
			
			logger.error("\t\t未执行上传地税操作，原因：未查询到需要上传地税的发票信息！");
		}else {
			
			//组织调用ESB数据
			VCUploadTaxRequestHeadDto head = new VCUploadTaxRequestHeadDto();
			head.setTaxPayerLoginXML(taxLoginXML);
			
			VCUploadTaxRequestBodyDto body = new VCUploadTaxRequestBodyDto();
			body.setInvoicePrintXML(invoiceXML);
			
			VCUploadTaxRequestDto req = new VCUploadTaxRequestDto();
			req.setVcUploadTaxRequestBodyDto(body);
			req.setVcUploadTaxRequestHeadDto(head);
			
			VCUploadTaxResponseDto resp = null;
			try {
				resp = this.vcInvoiceTaxUploadService.uploadInvoiceTax(req);
			} catch (Exception e) {
				logger.error(String.format("上传地税登录信息：%s", taxLoginXML));
				logger.error(String.format("发票上传地税调用ESB接口失败，原因：%s", e.getMessage()), e);
			}
			//更新是否上平台标志
			updateUploadedPlatFlag(resp);
		}
	}
	/**
	 * 更新是否上平台标志
	 * @param resp 上传平台结果
	 */
	private void updateUploadedPlatFlag(final VCUploadTaxResponseDto resp) {
		if(resp == null) {
			return;
		}
		logger.info(String.format("发票上传平台结果：%s-%s", resp.getVcUploadTaxResponseHeadDto().getResultCode(),
														resp.getVcUploadTaxResponseHeadDto().getResultMessage()));
		
		List<InvoiceTaxUploadResult> respDetails = resp.getVcUploadTaxResponseBodyDto().getInvoiceTaxUploadDetails();
		for(InvoiceTaxUploadResult ir : respDetails) {
			//将地税反馈的”该发票不在该纳税人发票本内“此类情况的发票更改为已上传   by lfengxia
			if(Constant.INVOICE_TAX_UPLOAD_SUCCESS_CODE.equals(ir.getResultCode()) || ir.getResultMessage().equals("该发票不在该纳税人发票本内")) { //上传成功 or该发票不在该纳税人发票本内
				vcInvoiceUploadTaxPlatDao.updateUploadedPlatFlag(ir.getInvoiceCode(),ir.getInvoiceNo());
			}
			logger.info(String.format("\t\t发票代码{%s}－发票号{%s}－上传地税结果：%s-%s", ir.getInvoiceCode(),ir.getInvoiceNo(),ir.getResultCode(),ir.getResultMessage()));
		}
	}
	
	/**
     * 更新当前批次数
     *
     */
    private void updateCurBatchNo(VcPubCode vcPubCode,String nextBatchNumum) {
        if(vcPubCode!=null){ 
            try{
              vcInvoiceUploadTaxPlatDao.updateCurBatchNo(vcPubCode, nextBatchNumum);
              logger.info(String.format("\t贵州发票上传批次号更新为{%s}", nextBatchNumum));
            }catch(DaoException de){
              logger.error(String.format("\t贵州发票上传批次号更新为{%s}异常：%s", nextBatchNumum,de.getMessage()));  
            }
        }      
    }


	public void setVcInvoiceTaxUploadService(
			VCInvoiceTaxUploadService vcInvoiceTaxUploadService) {
		this.vcInvoiceTaxUploadService = vcInvoiceTaxUploadService;
	}
	public String getRootXmlName() {
		return rootXmlName;
	}
	public void setRootXmlName(String rootXmlName) {
		this.rootXmlName = rootXmlName;
	}
	public void setVcInvoiceUploadTaxPlatDao(
			VCInvoiceUploadTaxPlatDao vcInvoiceUploadTaxPlatDao) {
		this.vcInvoiceUploadTaxPlatDao = vcInvoiceUploadTaxPlatDao;
	}
	
	public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
        this.vcPubCodeManagerDao = vcPubCodeManagerDao;
    }

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}



}
