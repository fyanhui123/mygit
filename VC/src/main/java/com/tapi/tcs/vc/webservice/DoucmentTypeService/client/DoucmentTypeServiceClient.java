package com.tapi.tcs.vc.webservice.DoucmentTypeService.client;

import java.util.Date;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto.Request;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto.RationCode;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto.RequestBody;
import com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto.RequestHeadDto;

public class DoucmentTypeServiceClient {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
		public String doucmentTypeService(String flag,RationCode rationCode,String url){
			logger.error("进入到新增单证类型的接口里面！！！");
			String soapResponse="";
			try {
				parserXml pas=new parserXml();
			        //拼接xml请求,带有请求头
			    Request requestDto=new Request();
				RequestHeadDto  requestHeadDto  = getHead(flag);
				RequestBody body =new RequestBody();
				body.setRationCode(rationCode);
				requestDto.setRequestBody(body);
				//String getBody=pas.guObjectToXml(body,RequestBody.class);//得到body
				//logger.error("组装成的body-->"+getBody);
				//MD5加密
				String key = "DoucmentTypeService";
				String secretKey = DigestUtils.md5DigestAsHex(key.getBytes("UTF-8"));
				logger.error("加密后的secretKey-->"+secretKey);
				requestHeadDto.setSecretKey(secretKey);
				requestDto.setRequestHeadDto(requestHeadDto);//头信息
			    requestDto.setRequestBody(body);
				String soapRequestData=pas.guObjectToXml(requestDto,Request.class);
				//String requestxml="<![CDATA["+soapRequestData+"]]>";
			    logger.error("组装后的请求信息soapRequestData-->"+soapRequestData);
			   // logger.error("组装后的请求信息requestxml-->"+requestxml);
	            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	   		    logger.error("获取产品工厂的wsdl地址-->"+url);
	   		       //注册WebService接口  
		            factory.setAddress(url);  
	   			    //factory.setAddress("http://10.100.135.77:10101/VISA/PRODFY/ESystemRequest");
		            factory.setServiceClass(ProductService.class); 
		            ProductService productService = (ProductService)factory.create(); 
		            soapResponse=  productService.eSystemRequest(soapRequestData);
		            System.out.println(soapResponse); 
		            logger.error("同步产品工厂返回报文为"+soapResponse);
			} catch (Exception e) {
	            logger.error("同步产品工厂失败"+e.getMessage());
	        }
			logger.error("返回报文--->"+soapResponse);
			return soapResponse;
}
		public RequestHeadDto getHead(String flag){
			int x=1;
			int k =x++;
			RequestHeadDto requestHeadDto=new RequestHeadDto();
			requestHeadDto.setSystemId("VC");
			requestHeadDto.setRequestType("DT");
			requestHeadDto.setSerialNo(String.valueOf(k));
			requestHeadDto.setRequestTime(new Date().toString());
			requestHeadDto.setSecretKey(null);
			requestHeadDto.setOperation(flag);
			requestHeadDto.setServiceName("DoucmentTypeService");
			return requestHeadDto;
		}
		public String guTitleXml(String arry1,String arry2){
			 StringBuffer xml=new StringBuffer();
			 xml.append("<?xml  version=\'1.0\' encoding=\'UTF-8\'?>");
			 xml.append("<packet>");
			 xml.append(arry1);
			 xml.append(arry2);
			 xml.append("</packet>");
			 return xml.toString();
		 }
		
		public String getHeadToXml_T(Request request){/*
			 StringBuffer xml=new StringBuffer();
			   xml.append("<head>");
			      xml.append("<systemId>");
			     // xml.append(""+"PF"+"");
			      xml.append(""+request.getHEAD().getSystemId()+"");
			      xml.append("</systemId>");
			      xml.append("<requestType>");
			      //xml.append(""+"DT"+"");
			      xml.append(""+request.getHEAD().getRequestType()+"");
			      xml.append("</requestType>");
			      xml.append("<serialNo>");
			    //  xml.append(""+"94c800eba3314035af2da4291c056844"+"");
			      xml.append(""+request.getHEAD().getSerialNo()+"");
			      xml.append("</serialNo>");
			      xml.append("<requestTime>");
			     //   xml.append(""+"2018-12-20 11:20:46"+"");
			      xml.append(""+request.getHEAD().getRequestTime()+"");
			      xml.append("</requestTime>");
			      
			      xml.append("<secretKey>");
			    //  xml.append(""+"38250213dac873a58d61e254233b7185"+"");
			      xml.append(""+request.getHEAD().getSecretKey()+"");
			      xml.append("</secretKey>");
			      
			      xml.append("<operation>");
			    //  xml.append(""+"I"+"");
			      xml.append(""+request.getHEAD().getOperation()+"");
			      xml.append("</operation>");
			      
			      xml.append("<serviceName>");
			   //   xml.append(""+"DoucmentTypeService"+"");
			      xml.append(""+request.getHEAD().getServiceName()+"");
			      xml.append("</serviceName>");
			      
			   xml.append("</head>");
			 return xml.toString();
		 */
			return null;
			}
		public String guBodyToXml_T(RequestBody request){/*
			 StringBuffer xml=new StringBuffer();
			   xml.append("<body>");
			    xml.append("<documentTypeInfo>");
			       xml.append("<idVcDocversionInfo>");
			       xml.append(""+request.getRationCode().getDocumentState()+"");
			       xml.append("</idVcDocversionInfo>");
			       xml.append("<documentCode>");
			       xml.append(""+request.getRationCode().getDocumentCode()+"");
			       xml.append("</documentCode>");
			       xml.append("<documentName>");
			       xml.append(""+request.getRationCode().getDocumentName()+"");
			       xml.append("</documentName>");
			       xml.append("<documentState>");
			       xml.append(""+request.getRationCode().getIdVcDocversionInfo()+"");
			       xml.append("</documentState>");
			     xml.append("</documentTypeInfo>");
			     xml.append("</body>");
			 return xml.toString();
		 */
			return null;}
}