package com.tapi.tcs.vc.webservice.provider.RiskInfoService.component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.CommonProductFactoryResponseHead;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.ComonProductFactoryResponse;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.RiskInfoProductFactoryRequest;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.service.DocService;
@WebService(targetNamespace="http://service.tapi.com/vc/docService/intf", serviceName="RiskInfoService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.RiskInfoService.component.RiskInfoService")
public class RiskInfoServiceImpl implements RiskInfoService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public DocService docService;
	public DocService getDocService() {
		return docService;
	}
	public void setDocService(DocService docService) {
		this.docService = docService;
	}
	@SuppressWarnings("static-access")
	@Override
	public String RiskInfoService(String request) {
		logger.error("请求报文为-->"+request);
		RiskInfoProductFactoryRequest  reqPackage=new RiskInfoProductFactoryRequest();//请求对象
		ComonProductFactoryResponse resPackage=new ComonProductFactoryResponse();//返回对象
		CommonProductFactoryResponseHead resHead=new CommonProductFactoryResponseHead();
		parserXml parser= new parserXml();
		String resultXml=null;
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String time= format.format( new Date());
	    Date date= null;
		try {
			 date= format.parse(time);
			 reqPackage=(RiskInfoProductFactoryRequest)parser.guXmlToObject(request.trim(), RiskInfoProductFactoryRequest.class);
			 String [] message=  checkHead(reqPackage);
			if(!"0".equals(message[0])){
				resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
				resHead.setResponseCode("999");
				resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
				resHead.setResponseTime(date);
				resHead.setErroCode("0003");
				resHead.setErrorMessage(message[1]);
				resPackage.setCommonProductFactoryResponseHead(resHead);
				resultXml=parser.guObjectToXml(resPackage,ComonProductFactoryResponse.class);
				return resultXml;
			}else{  ////判断是新增还是修改
				    String operation=reqPackage.getCommonProductFactoryRequestHead().getOperation();
				    logger.error("新增还是修改-->"+operation);
				    //归属险类代码
				    String classCode=reqPackage.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getClassCode();
				    logger.error("归属险类代码-->"+classCode);
				    //险种代码
				    String riskCode=reqPackage.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getRiskCode();
				    logger.error("险种代码-->"+riskCode);
				    //险种名称
				    String riskNameSimple=reqPackage.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getRiskName();
				    logger.error("险种名称-->"+riskNameSimple);
				    String documentType=reqPackage.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getDocumentType();
				    logger.error("documentType-->"+documentType);
				    //新增
				    resHead=  docService.savedocInsuKind(operation,classCode,riskCode,riskNameSimple,documentType);
				    resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
				    resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
				    resHead.setResponseTime(date);
				    resPackage.setCommonProductFactoryResponseHead(resHead);
			}
			}catch (Exception e) {
				logger.error("错误信息--》"+e.getMessage());
				resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
				resHead.setResponseCode("999");
				resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
				resHead.setResponseTime(date);
				resHead.setErroCode("0003");
				resHead.setErrorMessage(e.getMessage());
				resPackage.setCommonProductFactoryResponseHead(resHead);
				//resultXml=parser.guObjectToXml(resPackage,ComonProductFactoryResponse.class);
		}
		try {
			resultXml= parser.guObjectToXml(resPackage, resPackage.getClass());
		} catch (Exception e) {
			resultXml=parser.getRiskXml(resPackage);
			logger.error("返回自定义的报文"+resultXml);
			logger.error("返回报文转换异常"+e.getMessage());
		}
		logger.equals("返回报文-->"+resultXml);
		return resultXml;
	}
	
	
	public String[] checkHead(RiskInfoProductFactoryRequest  request){
		 String[] message=new String[2];
		if(null==request){
			 message[0]="10";
			 message[1]="服务请求不能为空!";
			 return  message;
		}
		if(null==request.getCommonProductFactoryRequestHead()){
			 message[0]="10";
			 message[1]="服务请求head不能为空!";
			 return  message;
		}
		if(null==request.getRiskInfoProductFactoryRequestBody()){
			 message[0]="10";
			 message[1]="服务请求body不能为空!";
			 return  message;
		}
		
		if(null==request.getCommonProductFactoryRequestHead().getSystemId()){
			 message[0]="10";
			 message[1]="系统代码不能为空!";
			 return  message;
		}
		if(null==request.getCommonProductFactoryRequestHead().getRequestType()){
			 message[0]="10";
			 message[1]="请求类型不能为空!";
			 return  message;
		}
		if(null==request.getCommonProductFactoryRequestHead().getSerialNo()){
			 message[0]="10";
			 message[1]="SERIALNO不能为空!";
			 return  message;
		}
		if(null==request.getCommonProductFactoryRequestHead().getServiceName()){
			 message[0]="10";
			 message[1]="服务名称不能为空!";
			 return  message;
		}if(null==request.getCommonProductFactoryRequestHead().getOperation()){
			 message[0]="10";
			 message[1]="操作标识不能为空!";
			 return  message;
		}
		if(null==request.getCommonProductFactoryRequestHead().getRequestTime()){
			 message[0]="10";
			 message[1]="请求时间不能为空!";
			 return  message;
		}
		if(null==request.getCommonProductFactoryRequestHead().getSecretKey()){
			 message[0]="10";
			 message[1]="加密串不能为空!";
			 return  message;
		}
	 StringBuilder sb=new StringBuilder("");
		 if(null==request.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getRiskCode()||"".equals(request.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getRiskCode())){
			 sb.append("险种代码不能为空");
		 }
		 if(null==request.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getRiskName()||"".equals(request.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getRiskName())){
			 sb.append("险种中文名称不能为空");
		 }
		 if(null==request.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getDocumentType()||"".equals(request.getRiskInfoProductFactoryRequestBody().getPrpdRisk().getDocumentType())){
			 sb.append("单证类型不能为空");
		 }
		 if(StringUtils.isNotEmpty(sb.toString())){
				message[0] = "10";
				message[1] = sb.toString();
				return message;
		 }
		 message[0]="0";
		 return message;
	 }	
	
	
}
