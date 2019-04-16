package com.tapi.tcs.vc.webservice.provider.ClassInfoService.component;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.InsuTypeDao;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.webservice.provider.ClassInfoService.bean.ClassInfoProductFactoryRequest;
import com.tapi.tcs.vc.webservice.provider.ClassInfoService.service.VcClassInfoService;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.CommonProductFactoryResponseHead;
import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.ComonProductFactoryResponse;
@WebService(targetNamespace="http://service.tapi.com/vc/vcClassInfoService/intf", 
serviceName="ClassInfoService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.ClassInfoService.component.ClassInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public VcClassInfoService vcClassInfoService;
	
	public VcClassInfoService getVcClassInfoService() {
		return vcClassInfoService;
	}
	public void setVcClassInfoService(VcClassInfoService vcClassInfoService) {
		this.vcClassInfoService = vcClassInfoService;
	}
	@SuppressWarnings("static-access")
	@Override
	public String ClassInfoService(String request) throws DaoException {
		logger.error("进入新增险类接口请求报文为-->"+request);
		ClassInfoProductFactoryRequest reqPackage=new ClassInfoProductFactoryRequest();//请求对象
		ComonProductFactoryResponse resPackage=new ComonProductFactoryResponse();//返回对象
		CommonProductFactoryResponseHead resHead=new CommonProductFactoryResponseHead();//返回head信息
		parserXml parser= new parserXml();
		String resultXml=null;
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String time= format.format( new Date());
	    Date date= null;
		try {
			 date= format.parse(time);
			 reqPackage=(ClassInfoProductFactoryRequest)parser.guXmlToObject(request.trim(), ClassInfoProductFactoryRequest.class);
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
				}else{
					resPackage =vcClassInfoService.saveClassinfo(reqPackage);
					resultXml=parser.guObjectToXml(resPackage,ComonProductFactoryResponse.class);
					logger.error("返回报文为"+resultXml);
					System.out.println("返回报文"+resultXml);
			 }
		} catch (Exception e) {
			logger.error("错误信息-->"+e.getMessage());
			resHead.setRequestType(reqPackage.getCommonProductFactoryRequestHead().getRequestType());
			resHead.setResponseCode("999");
			resHead.setSerialNo(reqPackage.getCommonProductFactoryRequestHead().getSerialNo());
			resHead.setResponseTime(date);
			resHead.setErroCode("0003");
			resHead.setErrorMessage(e.getMessage());
			resPackage.setCommonProductFactoryResponseHead(resHead);
			resultXml=parser.getRiskXml(resPackage);
		}
		logger.error("返回的报文为"+resultXml);
		return resultXml;
	}
	public String[] checkHead(ClassInfoProductFactoryRequest  request){
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
		if(null==request.getClassInfoProductFactoryRequestBody()){
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
		 if(null==request.getClassInfoProductFactoryRequestBody().getPrpdClass().getClassCode()||"".equals(request.getClassInfoProductFactoryRequestBody().getPrpdClass().getClassCode())){
			 sb.append("险类代码不能为空");
		 }
		 if(null==request.getClassInfoProductFactoryRequestBody().getPrpdClass().getClassName()||"".equals(request.getClassInfoProductFactoryRequestBody().getPrpdClass().getClassName())){
			 sb.append("险类名称不能为空");
		 }
		 if(null==request.getClassInfoProductFactoryRequestBody().getPrpdClass().getValidind()||"".equals(request.getClassInfoProductFactoryRequestBody().getPrpdClass().getValidind())){
			 sb.append("是否有效不能为空");
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
