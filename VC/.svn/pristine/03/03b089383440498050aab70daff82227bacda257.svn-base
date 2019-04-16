package com.tapi.tcs.vc.subfunc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DoAnnuledDocNumInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledResponse;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.component.DocDoAnnuledService;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldResponse;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocSerialNoScope;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.component.DocDoAnnuledOldService;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DoUsedBusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DoUsedDocNumInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.component.DocDoUsedService;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.component.DocNumInquiryOldService;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.component.DocNumStatusInquiryService;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.component.DocNumStatusInquiryOldService;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.component.DocVerInquiryService;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.component.InsuCardInfoInquiryService;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedResponse;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.component.InsuranceCardDoActivatedService;

public class WebServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	 //  getDocNumInquiryOldService();
	//	getDocNumStatusInquiryOldService();
//		getDocNumStatusInquiryService();            // 单证流水号状态（新）
//		getDocVerInquiryService();                 //单证类型（新）
//		 getDocDoUsedService();                    //单证核销（新）
	//	getDocDoAnnuledOldService();
//		getDocDoAnnuledService();
		getInsuranceCardDoActivatedService();        //激活卡激活接口测试
//		getInsuCardInfoInquiryService();       //激活卡信息查询
	}

	
	// 老系统    单证号段查询接口 
	 public static DocNumInquiryOldResponse getDocNumInquiryOldService() {
 		JaxWsProxyFactoryBean jfb = new JaxWsProxyFactoryBean();
		jfb.setAddress("http://127.0.0.1:7001/visa/services/DocNumInquiryOldService");
		jfb.setServiceClass(DocNumInquiryOldService.class);
		DocNumInquiryOldService service = (DocNumInquiryOldService) jfb.create();
		DocNumInquiryOldResponse response=new  DocNumInquiryOldResponse();
	     DocNumInquiryOldRequest request=new  DocNumInquiryOldRequest();
		 RequestHeadDTO  requestHead=new  RequestHeadDTO();
		 DocNumInquiryOldRequestDTO requestBody=new DocNumInquiryOldRequestDTO();
		 String str="10042";
		 //单证类型
		 requestBody.setDocVerID(Long.valueOf(str));  
		 //操作员
		 requestBody.setOrgID("1108");   //orgCode: 0164030204
		 String ope="536";
		 // 操作员归属机构
		 requestBody.setOperatorID(Long.valueOf(ope));  //userCode: 3301000012
	//	 requestBody.setBusinessNo(businessNo);
	//	 requestBody.setDepID(depID);
	//	 requestBody.setInsuKindID(insuKindID);
	//	 requestBody.setInsuTypeID(insuTypeID);
	//	 requestBody.setInvoiceVersion(invoiceVersion);
	//	 requestBody.setOrgID(orgID);
		 request.setRequestHead(requestHead);
		 request.setRequestBody(requestBody);
		 try {
			  response=service.docNumInquiryQuery(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	// 老系统    单证流水号状态查询
	 public static DocNumStatusInquiryOldResponse getDocNumStatusInquiryOldService() {
		 JaxWsProxyFactoryBean  jfb = new  JaxWsProxyFactoryBean();
		 jfb.setServiceClass(DocNumStatusInquiryOldService.class);
		 jfb.setAddress("http://127.0.0.1:7001/visa/services/DocNumStatusInquiryOldService");
		DocNumStatusInquiryOldService service = (DocNumStatusInquiryOldService)jfb.create();
		DocNumStatusInquiryOldRequest request=new DocNumStatusInquiryOldRequest();
		DocNumStatusInquiryOldResponse response=new DocNumStatusInquiryOldResponse();
		RequestHeadDTO  requestHead=new  RequestHeadDTO();
		DocNumStatusInquiryOldRequestDTO  requestBody=new DocNumStatusInquiryOldRequestDTO();
		 String str="10042";
		 //单证类型
		 requestBody.setDocVerID(Long.valueOf(str));  
		 //操作员
		 requestBody.setOrgID("1108");
		 String ope="536";
		 // 操作员归属机构
		requestBody.setOperatorID(Long.valueOf(ope)); 
	//	requestBody.setOrgID("");
//		requestBody.setStartNum("1303000015");  //1303000008   1303000010
//		requestBody.setEndNum("1303000015");
		//  yuwuhao
//		requestBody.setBusinessNo("BD0000000001");
		request.setRequestHead(requestHead);
		request.setRequestBody(requestBody);
		try {
			response=service.queryDocNumStatus(request);	 
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return response;
	}
	
	 
	  // 老系统     作废接口
	 public static DocDoAnnuledOldResponse getDocDoAnnuledOldService() {
		 JaxWsProxyFactoryBean  jfb = new  JaxWsProxyFactoryBean();
		 jfb.setServiceClass(DocDoAnnuledOldService.class);
		 jfb.setAddress("http://127.0.0.1:7001/visa/services/DocDoAnnuledOldService");
		 DocDoAnnuledOldService service = (DocDoAnnuledOldService)jfb.create();
		 DocDoAnnuledOldResponse response=new DocDoAnnuledOldResponse();
		 RequestHeadDTO  requestHead=new  RequestHeadDTO();
		 DocDoAnnuledOldRequest  request=new DocDoAnnuledOldRequest();
		 DocDoAnnuledOldRequestDTO  requestBody=new  DocDoAnnuledOldRequestDTO();
		 String str="205";
		 //单证类型
		 requestBody.setDocVersionID(Long.parseLong(str)); 
		 String  usrId="536";
		 requestBody.setUserId(Long.parseLong(usrId));   
		 requestBody.setCompanyId("1108"); 
		 DocSerialNoScope  docSerialNoScope=new DocSerialNoScope();
//		 docSerialNoScope.setDocBeginSerialNo("1310000037");
//		 docSerialNoScope.setDocEndSerialNo("1310000037");
		 List<DocSerialNoScope> listScope=new  ArrayList<DocSerialNoScope>();
		 listScope.add(docSerialNoScope);
		 requestBody.setDocSerialNoScope(listScope);
		 request.setRequestHead(requestHead);
		 request.setRequestBody(requestBody);
		 try {
			 response=service.saveDocDoAnnul(request);
	    	} catch (Exception e) {
			e.printStackTrace();
		 }
		 return  response;
	 }
	 
	 
	  //    单证流水号状态接口测试 
	 public  static  DocNumStatusInquiryResponse  getDocNumStatusInquiryService(){
		     JaxWsProxyFactoryBean jfb = new JaxWsProxyFactoryBean();
			jfb.setAddress("http://127.0.0.1:7001/visa_all/services/DocNumStatusInquiryService");
			jfb.setServiceClass(DocNumStatusInquiryService.class);
			DocNumStatusInquiryService service = (DocNumStatusInquiryService) jfb.create(); 
			DocNumStatusInquiryResponse response=new DocNumStatusInquiryResponse();
		//	ResponseHeadDTO  responseHead=new ResponseHeadDTO();
		//	DocNumStatusInquiryResponseDTO   responseBody=new DocNumStatusInquiryResponseDTO();
			DocNumStatusInquiryRequest request=new DocNumStatusInquiryRequest();
			RequestHeadDTO   requestHead=new  RequestHeadDTO();
			DocNumStatusInquiryRequestDTO  requestBody=new DocNumStatusInquiryRequestDTO();
			//  FP-ZJFP-13   6780001  678  1303000006 1303000006
			requestBody.setDocVerCode("FP-ZJFP-13");
			requestBody.setOperatorCode("hjiansh");
			requestBody.setOrgCode("0164340631");
			requestBody.setStartNum("90000001");
			requestBody.setEndNum("90000501");
			request.setRequestHead(requestHead);
			request.setRequestBody(requestBody); 
			try{
				response=service.statusQuery(request);
			}catch(Exception e){
				e.printStackTrace();
			}
		   return  response;
	 }
	 
	 // 单证类型查询接口测试
	 public  static  DocVerInquiryResponse  getDocVerInquiryService(){
		    DocVerInquiryResponse  response=new DocVerInquiryResponse();
		    JaxWsProxyFactoryBean jfb = new JaxWsProxyFactoryBean();
			jfb.setAddress("http://127.0.0.1:7001/visa_all/services/DocVerInquiryService");
			jfb.setServiceClass(DocVerInquiryService.class);
			DocVerInquiryService service = (DocVerInquiryService) jfb.create(); 
			RequestHeadDTO requestHead=new RequestHeadDTO();
			DocVerInquiryResponseDTO   responseBody=new DocVerInquiryResponseDTO();
			DocVerInquiryRequestDTO requestBody=new DocVerInquiryRequestDTO();
			DocVerInquiryRequest request=new DocVerInquiryRequest();
			request.setRequestHead(requestHead);
			requestBody.setOrgCode("0164310701");
			requestBody.setInsuKindCode("C711");
			requestBody.setDocTypeCode("BD");
			requestBody.setTemplateCode("AE0802N2009AA03");
			request.setRequestBody(requestBody);
			try {
				response=service.verQuery(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    return  response;
	 }
	 
	  // 单证核销接口测试
	 public  static  DocDoUsedResponse  getDocDoUsedService(){
		 DocDoUsedResponse response=new DocDoUsedResponse();
		 JaxWsProxyFactoryBean jfb = new JaxWsProxyFactoryBean();
		 jfb.setAddress("http://127.0.0.1:7001/visa_all/services/DocDoUsedService");
		 jfb.setServiceClass(DocDoUsedService.class);
		 DocDoUsedService service = (DocDoUsedService) jfb.create(); 
		 DocDoUsedRequest request=new DocDoUsedRequest();
		 RequestHeadDTO  requestHead=new RequestHeadDTO();
		 DocDoUsedRequestDTO  requestBody=new  DocDoUsedRequestDTO();
		 // 不可使用
		 requestBody.setDocTypeCode("21649");
		 requestBody.setDocVerCode("21649");
		 requestBody.setOperatorCode("djun");
		 requestBody.setOrgCode("0164310701"); 
		 requestBody.setPressBatchNo("000"); 
		 // 单证流水号列表
		 List<DoUsedDocNumInfoDTO> docNumInfoDTOs=new ArrayList<DoUsedDocNumInfoDTO>();
		 DoUsedDocNumInfoDTO doDto=new  DoUsedDocNumInfoDTO();
		 doDto.setDocNum("1300000005");
			 List<DoUsedBusinessNoDTO> businessNoDTOs=new ArrayList<DoUsedBusinessNoDTO>();
			 DoUsedBusinessNoDTO bussDto=new DoUsedBusinessNoDTO();
			 bussDto.setBusinessNo("6643107060320130000001"); 
			 bussDto.setRelationBusinessNo("");
			 businessNoDTOs.add(bussDto);
		 doDto.setBusinessNoDTOs(businessNoDTOs); 
		
		 docNumInfoDTOs.add(doDto);
		
		 requestBody.setDocNumInfoDTOs(docNumInfoDTOs);
		 request.setRequestHead(requestHead);
		 request.setRequestBody(requestBody);
		try {
			response=service.docDoUsedQuery(request);
		} catch (Exception e) {
			 e.printStackTrace();
		} 
		 return  response;
	 }
	 
	 
	 // 作废接口测试
	 public static DocDoAnnuledResponse getDocDoAnnuledService() {
		 JaxWsProxyFactoryBean  jfb = new  JaxWsProxyFactoryBean();
		 jfb.setServiceClass(DocDoAnnuledService.class);
		 jfb.setAddress("http://127.0.0.1:7001/visa_all/services/DocDoAnnuledService");
		 DocDoAnnuledService service = (DocDoAnnuledService)jfb.create();
		 DocDoAnnuledResponse response=new DocDoAnnuledResponse();
		 RequestHeadDTO  requestHead=new  RequestHeadDTO();
		 DocDoAnnuledRequest  request=new DocDoAnnuledRequest();
		 DocDoAnnuledRequestDTO  requestBody=new  DocDoAnnuledRequestDTO();
		 requestHead.setPassword(null);
		 requestHead.setSystemCode("NEWBIZ");
		 requestHead.setUser("0100000001");
		 //单证类型
		 requestBody.setDocVerCode("w806");   //BD-AA01-1
		 //操作员代码
		 requestBody.setOperatorCode("3301010110"); //3301010110
		 //操作员归属机构代码
		 requestBody.setOrgCode("0164001801");//01640802
		 //印刷批次
		 requestBody.setPressBatchNo(null);
		// 单证流水列表
		 List<DoAnnuledDocNumInfoDTO> docNumInfoDTOs=new ArrayList<DoAnnuledDocNumInfoDTO>();
		  //单证流水号
		 DoAnnuledDocNumInfoDTO  doAnnuledDocNumInfoDTO=new  DoAnnuledDocNumInfoDTO();  
		 doAnnuledDocNumInfoDTO.setDocNum("1300000253");  //1320000025  (1310000034  1310000035  1310000036)
		  //业务号列表
		 List<BusinessNoDTO> businessNoDTOs=new ArrayList<BusinessNoDTO>();
		 BusinessNoDTO  businessNoDTO=new BusinessNoDTO();
		 //(业务号/批单对应的保单业务号)
		 businessNoDTO.setBusinessNo("6643107080120130000017");   //13033
		 businessNoDTO.setRelationBusinessNo(null);  
		 businessNoDTOs.add(businessNoDTO);
		 doAnnuledDocNumInfoDTO.setBusinessNoDTOs(businessNoDTOs);
		 docNumInfoDTOs.add(doAnnuledDocNumInfoDTO);

		 requestBody.setDocNumInfoDTOs(docNumInfoDTOs);
		
		 request.setRequestHead(requestHead);
		 request.setRequestBody(requestBody);
		 try {
			 response=service.saveDocDoAnnul(request);
	    	} catch (Exception e) {
			e.printStackTrace();
		 }
		 return  response;
	 }
	 
	 
	  // 激活卡激活接口测试
	 public static InsuranceCardDoActivatedResponse getInsuranceCardDoActivatedService() {
		 JaxWsProxyFactoryBean  jfb = new  JaxWsProxyFactoryBean();
		 jfb.setServiceClass(InsuranceCardDoActivatedService.class);
		 jfb.setAddress("http://127.0.0.1:7001/visa_all/services/InsuranceCardDoActivatedService");
		 InsuranceCardDoActivatedService service = (InsuranceCardDoActivatedService)jfb.create();
		 InsuranceCardDoActivatedResponse response=new InsuranceCardDoActivatedResponse();
		 RequestHeadDTO  requestHead=new  RequestHeadDTO();
		 InsuranceCardDoActivatedRequest  request=new InsuranceCardDoActivatedRequest();
		 InsuranceCardDoActivatedRequestDTO  requestBody=new  InsuranceCardDoActivatedRequestDTO();
		 //单证类型
		 requestBody.setDocVerCode("23364");   
		 //卡号
		 requestBody.setCardNO("00247890");
		 //业务号
		 requestBody.setBusinessNo("000000");
		 //激活方式
		 requestBody.setActiveType("T");
		 //激活时间
		 Date date=new Date();
		 requestBody.setActiveTime(date); 
		
		 
		 request.setRequestHead(requestHead);
		 request.setRequestBody(requestBody);
		 try {
			 response=service.insuranceCardDoActivatedService(request); 
	    	} catch (Exception e) {
			e.printStackTrace();
		 }
		 return  response;
	 }
	 
	  @Test
	  private static InsuCardInfoInquiryResponse getInsuCardInfoInquiryService(){
	   InsuCardInfoInquiryResponse response = new InsuCardInfoInquiryResponse();
	    JaxWsProxyFactoryBean jfb = new JaxWsProxyFactoryBean();
	   jfb.setAddress("http://127.0.0.1:7001/visa_all/services/InsuCardInfoInquiryService");
	   jfb.setServiceClass(InsuCardInfoInquiryService.class);
	   InsuCardInfoInquiryService service = (InsuCardInfoInquiryService) jfb.create(); 
	   RequestHeadDTO requestHead = new RequestHeadDTO();
	   InsuCardInfoInquiryResponseDTO   responseBody=new InsuCardInfoInquiryResponseDTO();
	   InsuCardInfoInquiryRequestDTO requestBody=new InsuCardInfoInquiryRequestDTO();
	   InsuCardInfoInquiryRequest request=new InsuCardInfoInquiryRequest();
	   
	   request.setRequestHead(requestHead);
	   String cardNO = "1300269599";  // 1300269599
	   String encryptedPW = "738876";  //46208E1048C9421F04BAAB5DC8814DF3
	   requestBody.setCardNO(cardNO);
	   requestBody.setEncryptedPW(encryptedPW);
	   request.setRequestBody(requestBody);
	   
	   try {
	    response=service.insuCardInfoInquiry(request);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	      return  response;
	  }
	 
}