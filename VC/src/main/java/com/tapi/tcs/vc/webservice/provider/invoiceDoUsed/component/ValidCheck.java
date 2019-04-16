package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.component;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedBusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedInfoDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoicePrintDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoicePrintExtDto;

public class ValidCheck {
    /***
     * 校验请求头、请求体内容
     * **/
    public static String[] checkNumStatus(InvoiceDoUsedRequest request) {
        String[] message = new String[2];
        if (request == null) {
            message[0] = "10";
            message[1] = "服务请求不能为空!";
            return message;
        }
        if (request.getRequestHead() == null) {
            message[0] = "10";
            message[1] = "请求头不能为空!";
            return message;
        }
        if (StringUtils.isEmpty(request.getRequestHead().getSystemCode())) {
            message[0] = "10";
            message[1] = "系统代码不能为空!";
            return message;
        }
        if (request.getRequestBody() == null) {
            message[0] = "10";
            message[1] = "请求体不能为空!";
            return message;
        }

        //RequestHeadDTO requestHead = request.getRequestHead();
        InvoiceDoUsedRequestDTO body = request.getRequestBody();
        String orgCode = body.getOrgCode();
        StringBuilder sb = new StringBuilder("");

        // body.getPressBatchNo() 发票核销时、不能为空
        if (StringUtils.isEmpty(body.getDocVerCode())) {
            sb.append("单证类型代码不能为空！");
        }

        // /操作员
        if (StringUtils.isEmpty(body.getOperatorCode())) {
            sb.append("操作员代码不能为空！");
        }

        if (StringUtils.isEmpty(orgCode)) {
            sb.append("操作员归属机构代码不能为空！");
        }
        if (StringUtils.isEmpty(body.getDocTypeCode())) {
            sb.append("单证种类代码不能为空！");
        }

        // 单证流水列表
        List<InvoiceDoUsedInfoDTO> docNumInfoDTOs = body.getDocNumInfoDTOs();
        if (docNumInfoDTOs == null || docNumInfoDTOs.size() == 0) {
            sb.append("单证流水号列表为空！");
        }else{

	        for (InvoiceDoUsedInfoDTO doUsedDocNumInfoDTO : docNumInfoDTOs) {
	            if (StringUtils.isEmpty(doUsedDocNumInfoDTO.getDocNum())) {
	                sb.append("单证流水号不能为空,");
	                break;
	            }
	
	            if (StringUtils.isEmpty(doUsedDocNumInfoDTO.getPressBatchNo())) {
	                sb.append("印刷批次不能为空,");
	                break;
	            }
	          
	            List<InvoiceDoUsedBusinessNoDTO> businessNoDTOs = doUsedDocNumInfoDTO.getBusinessNoDTOs();
	            if (businessNoDTOs == null || businessNoDTOs.size() == 0) {
	                sb.append("业务号列表不能为空,");
	                break;
	            }
	            for (InvoiceDoUsedBusinessNoDTO businessNoDTO : businessNoDTOs) {
	                if (StringUtils.isEmpty(businessNoDTO.getBusinessNo())) {
	                    sb.append("业务号不能为空,");
	                    break;
	                }
	
	                // 核销单证为批单时，需提供对应的保单号
	                if (StringUtils.isNotEmpty(body.getDocTypeCode()) && "PD".equals(body.getDocTypeCode())
	                        && StringUtils.isEmpty(businessNoDTO.getRelationBusinessNo())) {
	                    sb.append("批单对应的保单业务号不能为空,");
	                    break;
	                }
	
	                if (StringUtils.isEmpty(businessNoDTO.getPayNo())) {
	                    sb.append("缴费期次不能为空,");
	                    break;
	                }
	            }
	
	            InvoicePrintDTO invoicePrintDTO = doUsedDocNumInfoDTO.getInvoicePrintDTO();
	            InvoicePrintExtDto invoicePrintExtDto = doUsedDocNumInfoDTO.getInvoicePrintExtDto();
	            if (invoicePrintDTO == null) {
	                sb.append("发票开具信息不能为空,");
	                break;
	            }
	
	            if (StringUtils.isEmpty(invoicePrintDTO.getPolicyNo())) {
	                sb.append("保单号不能为空,");
	                break;
	            }
	
	            // 核销单证为批单时，需提供对应的保单号
	            if (StringUtils.isNotEmpty(body.getDocTypeCode()) && "PD".equals(body.getDocTypeCode())
	                    && StringUtils.isEmpty(invoicePrintDTO.getEndorseNo())) {
	                sb.append("批单号不能为空,");
	                break;
	            }
	
	            if (StringUtils.isEmpty(invoicePrintDTO.getBillerName())) {
	                sb.append("开票员名称不能为空,");
	                break;
	            }
	
	            if (invoicePrintDTO.getPrintTime()== null ) {
	                sb.append("开票日期不能为空,");
	                break;
	            }
	
	            if (StringUtils.isEmpty(invoicePrintDTO.getPayerName())) {
	                sb.append("付款方名称不能为空,");
	                break;
	            }
	            
	            if (invoicePrintDTO.getSumAmount()==null) {
	                sb.append("开票总金额不能为空,");
	                break;
	            }
	            
	            if(StringUtils.isBlank(invoicePrintDTO.getPrintKind())){
	            	sb.append("发票开具类型不能为空；");
	            	break;
	    		}
	            
	            if(orgCode.startsWith(SysConst.COMCODE_XM)){
	            	String msg = checkInvoiceXM(invoicePrintDTO);
	            	if(StringUtils.isNotEmpty(msg)){
	            		sb.append(msg);
	            		break;
	            	}
	            }else if(orgCode.startsWith(SysConst.COMCODE_TJ)){
	            	String msg = checkInvoiceTJ(invoicePrintDTO);
	            	if(StringUtils.isNotEmpty(msg)){
	            		sb.append(msg);
	            		break;
	            	}
	            }else if(orgCode.startsWith(SysConst.COMCODE_JS)){
	            	String msg = checkInvoiceJS(invoicePrintDTO,invoicePrintExtDto);
	            	if(StringUtils.isNotEmpty(msg)){
	            		sb.append(msg);
	            		break;
	            	}
	            }else if(orgCode.startsWith(SysConst.COMCODE_ChongQing)){
	            	String msg = checkInvoiceCQ(invoicePrintDTO);
	            	if(StringUtils.isNotEmpty(msg)){
	            		sb.append(msg);
	            		break;
	            	}
	            }else if(orgCode.startsWith(SysConst.COMCODE_FJ)){
	            	String msg = checkInvoiceFJ(invoicePrintDTO,invoicePrintExtDto);
	            	if(StringUtils.isNotEmpty(msg)){
	            		sb.append(msg);
	            		break;
	            	}
	            }
	        }
        }

        if (StringUtils.isNotEmpty(sb.toString())) {
            message[0] = "10";
            message[1] = sb.toString();
            return message;
        }
        message[0] = "0";
        return message;
    }
    
    /**厦门必录校验*/
    private static String checkInvoiceXM(InvoicePrintDTO invoicePrintDTO){
    	StringBuffer sb = new StringBuffer();
    	if(StringUtils.isBlank(invoicePrintDTO.getBillerName())){
			sb.append("开票员名称");
		}
    	if(invoicePrintDTO.getOperatorDate()==null){
			sb.append("操作时间不能为空；");
		}
    	return sb.toString();
    }
    
    /**天津必录校验*/
    private static String checkInvoiceTJ(InvoicePrintDTO invoicePrintDTO){
    	StringBuffer sb = new StringBuffer();
    	if(StringUtils.isBlank(invoicePrintDTO.getTaxpayerID())){
			sb.append("收款方纳税人识别号不能为空；");
		}
    	if(StringUtils.isBlank(invoicePrintDTO.getTaxpayerName())){
			sb.append("收款方名称不能为空；");
		}
    	return sb.toString();
    }
    
    /**江苏必录校验*/
    private static String checkInvoiceJS(InvoicePrintDTO invoicePrintDTO,InvoicePrintExtDto invoicePrintExtDto){
    	StringBuffer sb = new StringBuffer();
    	if(StringUtils.isBlank(invoicePrintDTO.getTaxpayerName())){
			sb.append("收款方名称不能为空；");
		}
    	if(StringUtils.isBlank(invoicePrintDTO.getCheckNum())){
			sb.append("验证码不能为空；");
		}
    	if(StringUtils.isBlank(invoicePrintExtDto.getRiskCode())){
    		sb.append("承保险种代码不能为空");
		}
		if(StringUtils.isBlank(invoicePrintExtDto.getRiskName())){
			sb.append("承保险种名称不能为空");
		}
    	return sb.toString();
    }
    
    /**重庆必录校验*/
    private static String checkInvoiceCQ(InvoicePrintDTO invoicePrintDTO){
    	StringBuffer sb = new StringBuffer();
    	if(StringUtils.isBlank(invoicePrintDTO.getTaxpayerID())){
			sb.append("收款方纳税人识别号不能为空；");
		}
    	if(StringUtils.isBlank(invoicePrintDTO.getTaxpayerName())){
			sb.append("收款方名称不能为空；");
		}
    	return sb.toString();
    }
    
    /**福建必录校验*/
    private static String checkInvoiceFJ(InvoicePrintDTO invoicePrintDTO,InvoicePrintExtDto invoicePrintExtDto){
    	StringBuffer sb = new StringBuffer();
    	if (StringUtils.isEmpty(invoicePrintDTO.getUpperSumAmount())) {
            sb.append("大写开票总金额不能为空,");
        }
    	if (StringUtils.isEmpty(invoicePrintDTO.getCheckNum())) {
    		sb.append("校验码不能为空,");
    	}
        if (StringUtils.isEmpty(invoicePrintDTO.getPrintTypeCode())) {
            sb.append("套打样式代码不能为空,");
        }
        if (StringUtils.isNotEmpty(invoicePrintDTO.getPrintKind()) && "2".equals(invoicePrintDTO.getPrintKind())) {
            if (StringUtils.isEmpty(invoicePrintDTO.getCounteractedInvoiceCode())) {
                sb.append("被冲红的发票代码不能为空,");
            }
            if (StringUtils.isEmpty(invoicePrintDTO.getCounteractedInvoiceNo())) {
                sb.append("被冲红的发票号码不能为空,");
            }
        }
        //套打样式代码
        String printType = invoicePrintDTO.getPrintTypeCode();
        if(invoicePrintExtDto == null){
        	sb.append("发票开具扩展信息不能为空；");
        }else{
        	if(StringUtils.isBlank(invoicePrintExtDto.getApplyName())){
        		sb.append("投保人不能为空；");
        	}
        	if("03".equals(printType)){
        		if(StringUtils.isBlank(invoicePrintExtDto.getAreaCode())){
        			sb.append("投保地代码不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getAreaName())){
        			sb.append("投保地名称不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getItemCode()) || StringUtils.isBlank(invoicePrintExtDto.getItemName())){
        			sb.append("开具项目不能为空；");
        		}
        	}else if("02".equals(printType)){
        		if(StringUtils.isBlank(invoicePrintExtDto.getCarAreaCode())){
        			sb.append("车籍地代码不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getCarAreaName())){
        			sb.append("车籍地名称不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getLicenseNo())){
        			sb.append("车牌号不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getCarKind())){
        			sb.append("车辆类别不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getUnit())){
        			sb.append("单位不能为空；");
        		}
        		if(invoicePrintExtDto.getQuantity() == null){
        			sb.append("数量不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getUseNature())){
        			sb.append("使用性质不能为空；");
        		}
        		if(invoicePrintExtDto.getPremium() == null){
        			sb.append("保险金额不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getEngineNo())){
        			sb.append("发动机号不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getFrameNo())){
        			sb.append("车架号不能为空；");
        		}
        		if(StringUtils.isBlank(invoicePrintExtDto.getFuelCategory())){
        			sb.append("能源类别不能为空；");
        		}
        		if (StringUtils.isEmpty(invoicePrintExtDto.getTaxYear())) {
                    sb.append("税款所属期年不能为空,");
                }

                if (StringUtils.isEmpty(invoicePrintExtDto.getTaxMonthStart())) {
                    sb.append("税款所属期起始月不能为空,");
                }
                if (StringUtils.isEmpty(invoicePrintExtDto.getTaxMonthStart())) {
                    sb.append("税款所属期终止月不能为空,");
                }
        	}
        }
    	return sb.toString();
    }
}
