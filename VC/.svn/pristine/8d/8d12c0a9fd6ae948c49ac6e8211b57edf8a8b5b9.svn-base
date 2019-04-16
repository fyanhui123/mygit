package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.ReportUtils;
import com.tapi.tcs.vc.schema.model.VcReportRecord;
import com.tapi.tcs.vc.webservice.provider.common.service.VcReportService;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResponse;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResponseDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResultDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidorRefundDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.service.VcInsuranceCardDoPaidService;

@WebService(targetNamespace = "http://service.tapi.com/vc/insuranceCardDoPaid/intf", serviceName = "InsuranceCardDoPaidService", endpointInterface = "com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.component.InsuranceCardDoPaidService")
public class InsuranceCardDoPaidServiceImpl implements InsuranceCardDoPaidService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 记录报文service
	 */
	private VcReportService vcReportService;


    public VcReportService getVcReportService() {
		return vcReportService;
	}

	public void setVcReportService(VcReportService vcReportService) {
		this.vcReportService = vcReportService;
	}

	private VcInsuranceCardDoPaidService vcInsuranceCardDoPaidService;

    /**
     * @param vcInsuranceCardDoPaidService the vcInsuranceCardDoPaidService to set
     */
    public void setVcInsuranceCardDoPaidService(VcInsuranceCardDoPaidService vcInsuranceCardDoPaidService) {
        this.vcInsuranceCardDoPaidService = vcInsuranceCardDoPaidService;
    }

    /**
     * 激活卡缴费、退费回写单证系统接口入口程序
     */
    @Override
    public InsuranceCardDoPaidResponse insuranceCardDoPaid(InsuranceCardDoPaidRequest request) {
        InsuranceCardDoPaidResponse response = new InsuranceCardDoPaidResponse();
        ResponseHeadDTO responseHead = new ResponseHeadDTO();
        InsuranceCardDoPaidResponseDTO responseBody = new InsuranceCardDoPaidResponseDTO();
      

        
        //modify by chy 20131015 解决事务无法自动回滚问题 begin
        /*try {
            //InsuranceCardDoPaidRequestDTO requestBody = request.getRequestBody();
            //激活卡缴费、退费回写单证
            List<InsuranceCardDoPaidResultDTO> dealResultList= vcInsuranceCardDoPaidService.executeDoPaidOrRefund(request);
            responseHead.setResponseCode("000");
            responseHead.setResponseMessage("操作成功！");
            //1：成功  0：失败
            responseBody.setInsuranceCardDoPaidResultList(dealResultList);
            response.setResponseHead(responseHead);
            response.setResponseBody(responseBody);
            return response;
        } catch (BusinessException e) {
            e.printStackTrace();
            responseHead.setResponseCode("999");
            responseHead.setResponseMessage(message[1]);
            responseBody.setInsuranceCardDoPaidResultList(null);
            response.setResponseHead(responseHead);
            response.setResponseBody(responseBody);
            return response;
        } */
		
		// 校验请求对象
        String[] message = checkParams(request);
        if (!"0".equals(message[0])) {
            responseHead.setResponseCode("999");
            responseHead.setResponseMessage(message[1]);
            responseBody.setInsuranceCardDoPaidResultList(null);
            response.setResponseHead(responseHead);
            response.setResponseBody(responseBody);
            return response;
        }
		
        InsuranceCardDoPaidRequestDTO requestBody = request.getRequestBody();
        List<InsuranceCardDoPaidorRefundDTO> list = requestBody.getInsuranceCardDoPaidorRefundList();
        List<InsuranceCardDoPaidResultDTO> dealResultList = new ArrayList<InsuranceCardDoPaidResultDTO>();
        InsuranceCardDoPaidResultDTO dealRresultDto = null;
        String errMsg = "";
        
        for(InsuranceCardDoPaidorRefundDTO dto : list){

        	try{
        		dealRresultDto = vcInsuranceCardDoPaidService.
        			executeDoPaidOrRefund(request, dto);
        	}catch(BusinessException e){
        		logger.error(e.getMessage(), e);
        		dealRresultDto = new InsuranceCardDoPaidResultDTO();
        		dealRresultDto.setBusinessNoDTOs(dto.getBusinessNoDTOs());
        		dealRresultDto.setStatus("0");
        		dealRresultDto.setFlag(e.getMessage());
        		errMsg += e.getMessage();
        	}
        	dealResultList.add(dealRresultDto);
        }
        if(StringUtils.isNotEmpty(errMsg)){
	        responseHead.setResponseCode("999");
	        responseHead.setResponseMessage(errMsg);
        }else{
        	responseHead.setResponseCode("000");
            responseHead.setResponseMessage("操作成功！");
        }
        responseBody.setInsuranceCardDoPaidResultList(dealResultList);
        response.setResponseHead(responseHead);
        response.setResponseBody(responseBody);
        
        
      //----写入交互报文 xshibai----
        VcReportRecord record = null;
    	try {
    		responseHead = response.getResponseHead();
    		if ( StringUtils.isNotBlank(responseHead.getResponseCode()) && !responseHead.getResponseCode().equals("000")) {
    			//InsuranceCardDoPaidRequestDTO requestBody = request.getRequestBody();
    			String businessNo = "";
    			if (requestBody.getInsuranceCardDoPaidorRefundList() != null && requestBody.getInsuranceCardDoPaidorRefundList().size() > 0) {
    				businessNo = requestBody.getInsuranceCardDoPaidorRefundList().get(0).getBusinessNoDTOs();
    			}
    			String requestXml = ReportUtils.DTOtoXML(request.getRequestBody(), InsuranceCardDoPaidRequestDTO.class.getName());
    			record = ReportUtils.generateReport( "1", "C1", ReportUtils.substring(requestXml, 3999),businessNo,"");
    			String responseXml = ReportUtils.DTOtoXML(response, InsuranceCardDoPaidResponse.class.getName());
    			record.setErrorCode(responseHead.getResponseCode());
    			record.setReportResult(responseHead.getResponseCode().equals("000") ? "1" : "0" );				
    			record.setErrorDesc(responseHead.getResponseCode().equals("000") ? "" : responseHead.getResponseMessage());
    			record.setResponseXml(responseXml);
    			record.setResponseTime(new Date());
    			vcReportService.nestSaveReportNest(record);
    			
    		}
		} catch (Exception e) {
			logger.info("激活卡缴费报文新增失败：" + e.getMessage());
			e.printStackTrace();
		}
        return response;
        //modify by chy 20131015 解决事务无法自动回滚问题 end
    }

    /**
     * 校验请求对象
     * 
     * @param request
     * @return
     */
    private String[] checkParams(InsuranceCardDoPaidRequest request) {
        String[] message = new String[2];
        if (request == null) {
            message[0] = "10";
            message[1] = "服务请求不能为空！";
            return message;
        }
        if (request.getRequestHead() == null) {
            message[0] = "10";
            message[1] = "请求头不能为空！";
            return message;
        }
        if (request.getRequestBody() == null) {
            message[0] = "10";
            message[1] = "请求体不能为空！";
            return message;
        }

        InsuranceCardDoPaidRequestDTO requestBody = request.getRequestBody();
        List<InsuranceCardDoPaidorRefundDTO> list = requestBody.getInsuranceCardDoPaidorRefundList();
        if (list == null || list.size() < 1) {
            message[0] = "10";
            message[1] = "请求体收付费信息列表为空！";
            return message;

        }
        
       
       StringBuffer msg=new StringBuffer();
       StringBuffer sbMsg=new StringBuffer();
       Boolean hasError=false;
       InsuranceCardDoPaidorRefundDTO refundDto= null;
        for(int i=0;i<list.size();i++){
            refundDto = list.get(i);
            sbMsg.setLength(0);
            if(StringUtils.isBlank(refundDto.getBusinessNoDTOs())){
                 sbMsg.append("业务号列表为空 ");
            }
            if(StringUtils.isBlank(refundDto.getPayOrRefund())|| (!"R".equals(refundDto.getPayOrRefund()) && !"P".equals(refundDto.getPayOrRefund()))){
               sbMsg.append("缴费退费标志为空或未知代码；"); 
            }
            if(sbMsg.length()>0){
                hasError=true;
                msg.append("收付费信息记录").append(i+1).append("数据异常【").append(sbMsg).append("】\n");
            }
        }
        if(hasError){
            message[0] = "10";
            message[1] = msg.toString();
            return message;  
        }
        
        message[0]="0";
        return message;
    }

   
}
