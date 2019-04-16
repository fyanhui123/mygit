package com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVersionInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.dao.DocVerInfoInquiryDao;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.service.DocVerInfoInquiryQueryService;

public class DocVerInfoInquiryQueryServiceImpl implements DocVerInfoInquiryQueryService {

	private DocVerInfoInquiryDao docVerInfoInquiryDao;

	public void setDocVerInfoInquiryDao(DocVerInfoInquiryDao docVerInfoInquiryDao) {
		this.docVerInfoInquiryDao = docVerInfoInquiryDao;
	}

	@Override
	public DocVerInfoInquiryResponse handleQuery(
			DocVerInfoInquiryRequest request) throws BusinessException {

		DocVerInfoInquiryRequestDTO body = request.getRequestBody();
		DocVerInfoInquiryResponse response = new DocVerInfoInquiryResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocVerInfoInquiryResponseDTO responseBody = new DocVerInfoInquiryResponseDTO();
		List<DocVersionInfoDTO> DocVersionInfoDTOs = new ArrayList<DocVersionInfoDTO>();
		DocVersionInfoDTO DocVersionInfoDTO = null;
		StringBuffer sb = new StringBuffer();
		
		
		Page pages =docVerInfoInquiryDao.docVerInfoInquiry(body);
		List<VcDocVersionInfo> vcDocVersionInfos=null;
		vcDocVersionInfos = (List<VcDocVersionInfo>)pages.getResult();
		
		try {
			if (vcDocVersionInfos.size() > 0 && vcDocVersionInfos != null) {
				for (VcDocVersionInfo obj : vcDocVersionInfos) {
					// 单证类型信息表
					VcDocVersionInfo doc = (VcDocVersionInfo) obj;
					DocVersionInfoDTO = new DocVersionInfoDTO();
					//单证类型代码
					DocVersionInfoDTO.setDocVerCode(doc.getDocVerCode());
					//单证类型ID
					DocVersionInfoDTO.setDocVerId(doc.getIdVcDocVersionInfo());
					//根据单证ID查询关联险种代码
					List<VcDocRelInsuKind> vcDocRelInsuKindList = docVerInfoInquiryDao.queryDocRelInsuKindList(doc.getIdVcDocVersionInfo());
					for(Iterator it = vcDocRelInsuKindList.iterator(); it.hasNext();){
						VcDocRelInsuKind vcDocRelInsuKind = (VcDocRelInsuKind) it.next();
						//险种代码返回格式为'险种代码|险种代码|险种代码'
						sb.append(vcDocRelInsuKind.getInsuKindCode()).append("|");
					}
					DocVersionInfoDTO.setRiskCodeList(sb.substring(0, sb.length() - 1).toString());
					DocVersionInfoDTOs.add(DocVersionInfoDTO);
				}
				responseHead.setResponseCode("000");
				responseHead.setResponseMessage("查询成功！");
				responseBody.setDocVersionInfoDTOs(DocVersionInfoDTOs);
				responseBody.setPageNo(pages.getCurrentPageNo());
				responseBody.setPageSize(pages.getPageSize());
				responseBody.setTotalCount(pages.getTotalCount());
				
				response.setResponseBody(responseBody);
				response.setResponseHead(responseHead);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		responseHead.setResponseCode("999");
		responseHead.setResponseMessage("查询失败、未查询到相关联的数据！");
		responseBody.setDocVersionInfoDTOs(DocVersionInfoDTOs);
		response.setResponseBody(responseBody);
		response.setResponseHead(responseHead);
		return response;
	}
}
