package com.tapi.tcs.common.webservice;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean.ComonProductFactoryResponse;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageResponse;
public class parserXml {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
    //解析xml----Dto
 public static Object guXmlToObject(String requestXML,
   Class class1) throws Exception{
	 
   JAXBContext jaxbContext=JAXBContext.newInstance(class1);
   StringReader reader=new StringReader(requestXML);
   Unmarshaller unsharller=jaxbContext.createUnmarshaller();
   Object obj=unsharller.unmarshal(reader);
   return obj;   
 }
 
 //解析Dto----XML
 public static String guObjectToXml(Object object,
   Class class1)  throws Exception{
  JAXBContext jaxbContext=JAXBContext.newInstance(class1);
  Marshaller marshaller=jaxbContext.createMarshaller();
  StringWriter writer=new StringWriter();
  marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
  marshaller.marshal(object, writer);
  return writer.toString();
 }
 /**
  *  单证入库
  * @param response 单证入库的返回对象
  * @return
  */
 public static String guObjectToXml_T(InputStorageResponse response){
	 StringBuffer xml=new StringBuffer();
	 xml.append("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
	 xml.append("<RESPONSE>");
	   xml.append("<HEAD>");
	      xml.append("<REQUESTTYPE>");
	      xml.append(""+response.gethEAD().getrEQUESTTYPE()+"");
	      xml.append("</REQUESTTYPE>");
	      xml.append("<REQSERIALNO>");
	      xml.append(""+response.gethEAD().getrEQSERIALNO()+"");
	      xml.append("</REQSERIALNO>");
	      xml.append("<FLOWINTIME>");
	      xml.append(""+response.gethEAD().getfLOWINTIME()+"");
	      xml.append("</FLOWINTIME>");
	   xml.append("</HEAD>");
	      
	     xml.append("<RESULT>");
	       xml.append("<RESULTTYPE>");
	       xml.append(""+response.getrESULT().getrESULTTYPE()+"");
	       xml.append("</RESULTTYPE>");
	       xml.append("<ERRORINFO>");
	       xml.append(""+response.getrESULT().geteRRORINFO()+"");
	       xml.append("</ERRORINFO>");
	     xml.append("</RESULT>");
	 xml.append("</RESPONSE>");
	 return xml.toString();
 }
 public static String getObjectToXml_T(StorageResponse response){
	 StringBuffer xml=new StringBuffer();
	 xml.append("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
	 xml.append("<RESPONSE>");
	   xml.append("<HEAD>");
	      xml.append("<REQUESTTYPE>");
	      xml.append(""+response.getHead().getrEQUESTTYPE()+"");
	      xml.append("</REQUESTTYPE>");
	      xml.append("<REQSERIALNO>");
	      xml.append(""+response.getHead().getrEQSERIALNO()+"");
	      xml.append("</REQSERIALNO>");
	      xml.append("<FLOWINTIME>");
	      xml.append(""+response.getHead().getfLOWINTIME()+"");
	      xml.append("</FLOWINTIME>");
	   xml.append("</HEAD>");
	      
	     xml.append("<RESULT>");
	       xml.append("<RESULTTYPE>");
	       xml.append(""+response.getResult().getResultType()+"");
	       xml.append("</RESULTTYPE>");
	       xml.append("<ERRORINFO>");
	       xml.append(""+response.getResult().getErrorInfo()+"");
	       xml.append("</ERRORINFO>");
	     xml.append("</RESULT>");
	 xml.append("</RESPONSE>");
	 return xml.toString();
 }
 public static String getRiskXml(ComonProductFactoryResponse resPackage){
	 StringBuffer xml=new StringBuffer();
	 xml.append("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
	 xml.append("<PACKET>");
	     xml.append("<HEAD>");  
	       xml.append("<requestType>");
	       xml.append(""+resPackage.getCommonProductFactoryResponseHead().getRequestType()+"");
	       xml.append("</requestType>");
	       xml.append("<responseCode>");
	       xml.append(""+resPackage.getCommonProductFactoryResponseHead().getResponseCode()+"");
	       xml.append("</responseCode>");
	   
	    	  xml.append("<serialno>");
		      xml.append(""+resPackage.getCommonProductFactoryResponseHead().getSerialNo()+"");
		      xml.append("</serialno>"); 
		      xml.append("<responseTime>");
		      xml.append(""+resPackage.getCommonProductFactoryResponseHead().getResponseTime()+"");
		      xml.append("</responseTime>"); 
	    
	    	  xml.append("<errorCode>");
		      xml.append(""+resPackage.getCommonProductFactoryResponseHead().getErroCode()+"");
		      xml.append("</errorCode>"); 
		      
		      xml.append("<errorMessage>");
		      xml.append(""+resPackage.getCommonProductFactoryResponseHead().getErrorMessage()+"");
		      xml.append("</errorMessage>"); 
	      xml.append("</HEAD>");
	 xml.append("</PACKET>");
	 return xml.toString();
 }
 public  String getMD5(String str) {
     try {           
         MessageDigest md = MessageDigest.getInstance("MD5");        
         md.update(str.getBytes());  
         return new BigInteger(1, md.digest()).toString(16);
     } catch (Exception e) {
    	logger.error("加密错误"+e.getMessage());
        return null;
     }
 }
 
 public  String convertToXml(Object obj) {
     return convertToXmlS(obj, "UTF-8");
 }

 /**
  * JavaBean转换成xml
  */
 public  String convertToXmlS(Object obj, String encoding) {
     String result = null;
     try {
         JAXBContext context = JAXBContext.newInstance(obj.getClass());
         Marshaller marshaller = context.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
         marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
         StringWriter writer = new StringWriter();
         writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
         marshaller.marshal(obj, writer);
         result = writer.toString();
     } catch (Exception e) {
    		logger.error("加密错误"+e.getMessage());
     }
     return result;
 }
}
