package com.tapi.tcs.vc.webservice.provider.docVerInquiry.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcDocPrintSet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVerInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVersionDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVersionInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.dao.DocVerInquiryDao;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.service.DocVerInquiryQueryService;

public class DocVerInquiryQueryServiceImpl implements DocVerInquiryQueryService{

	private   DocVerInquiryDao  docVerInquiryDao;
	public void setDocVerInquiryDao(DocVerInquiryDao docVerInquiryDao) {
		this.docVerInquiryDao = docVerInquiryDao;
	}
	
	private  VcBaseInfoDao  vcBaseInfoDao; 
	
	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}

	@Override  
	public DocVerInquiryResponse handleQuery(DocVerInquiryRequest request) throws BusinessException{
		
		RequestHeadDTO  requestHead = request.getRequestHead();
		DocVerInquiryRequestDTO body = request.getRequestBody();
		DocVersionInfoDTO  info=changeToDTO(body);
        DocVerInquiryResponse response=new  DocVerInquiryResponse();
        DocVerInquiryResponseDTO responseBody=new DocVerInquiryResponseDTO();
        List<DocVersionDTO> docVersionDTOs=new  ArrayList<DocVersionDTO>(); 
        DocVersionDTO  docVersionDTO=null; 
        ResponseHeadDTO responseHead=new ResponseHeadDTO();
        //接口调用方为外部接口
		//if (SysConst.EXTERNAL_SYSTEM_CODE.indexOf(requestHead.getSystemCode()) >= 0){
			List<VcDocVersionInfo> vcDocVersionInfo=docVerInquiryDao.docVerInquiryForPublic(info);
	        try {
	        	if(vcDocVersionInfo.size()>0 && vcDocVersionInfo!=null){
	        		for(VcDocVersionInfo obj: vcDocVersionInfo){
	        			//单证类型信息表
	        			VcDocVersionInfo doc=(VcDocVersionInfo)obj;
	        			docVersionDTO=new DocVersionDTO();
	        			docVersionDTO.setDocVerCode(doc.getDocVerCode());
	        			docVersionDTO.setDocVerName(doc.getDocVerName());
	        			docVersionDTOs.add(docVersionDTO);
	        			responseHead.setResponseCode("000");
	        			responseHead.setResponseMessage("查询成功！");
	        			responseBody.setDocVersionDTOs(docVersionDTOs);
	        			response.setResponseBody(responseBody);
	        			response.setResponseHead(responseHead);
	        		}
	        		return response;
	        	}
			}catch(Exception e) {
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}
		/*}else{
			List<Object[]> listObj=docVerInquiryDao.docVerInquiry(info);
	        try {
	        	if(listObj.size()>0 && listObj!=null){
	        		for(Object[] obj: listObj){
	        			//单证打印配置表 
	        			VcDocPrintSet    pri=(VcDocPrintSet)obj[0];
	        			//单证类型信息表
	        			VcDocVersionInfo doc=(VcDocVersionInfo)obj[1];
	        			docVersionDTO=new DocVersionDTO();
	        			docVersionDTO.setDocVerCode(doc.getDocVerCode());
	        			docVersionDTO.setDocVerName(doc.getDocVerName());
	        			docVersionDTO.setPrintTemplate(pri.getPrintTemplate());
	        			docVersionDTOs.add(docVersionDTO);
	        			responseHead.setResponseCode("000");
	        			responseHead.setResponseMessage("查询成功！");
	        			responseBody.setDocVersionDTOs(docVersionDTOs);
	        			response.setResponseBody(responseBody);
	        			response.setResponseHead(responseHead);
	        		}
		        	return response;
	        	}
			}catch(Exception e) {
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}
			
		}*/
		responseHead.setResponseCode("999");
    	responseHead.setResponseMessage("查询失败、未查询到相关联的数据！");
		responseBody.setDocVersionDTOs(docVersionDTOs);
    	response.setResponseBody(responseBody);
    	response.setResponseHead(responseHead);
		return response;
	}

	/****
	 *   封装参数成实体
	 * @param body
	 * @return
	 */
	public  DocVersionInfoDTO  changeToDTO(DocVerInquiryRequestDTO body) throws BusinessException{
		DocVersionInfoDTO  docVersionInfo=new DocVersionInfoDTO();
		docVersionInfo.setDocTypeCode(body.getDocTypeCode());
		docVersionInfo.setInsuKindCode(body.getInsuKindCode());
		List<String> orgCode=null;
		try {
			 orgCode=vcBaseInfoDao.getOrgCodeListByOrgCode(body.getOrgCode());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		docVersionInfo.setOrgCode(orgCode);
		docVersionInfo.setTemplateCode(body.getTemplateCode());
		return   docVersionInfo;
	}
}
