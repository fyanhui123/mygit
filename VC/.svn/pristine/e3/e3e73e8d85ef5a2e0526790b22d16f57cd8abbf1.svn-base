package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcDocPrintSet;
import com.tapi.tcs.vc.schema.model.VcDocRelArea;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.dao.VcDocVerInfoDao;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.service.VcDocVerInfoService;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.vo.DocVerInquiryRequestVO;

public class VcDocVerInfoServiceImpl implements VcDocVerInfoService {

	private VcDocVerInfoDao vcDocVerInfoDao;
	private VcBaseInfoDao vcBaseInfoDao;
	
	@Override
	public DocVerInquiryOldResponseDTO docVerInquiry(
			DocVerInquiryOldRequestDTO requestBody) throws BusinessException{
		DocVerInquiryOldResponseDTO responseBody = null;
		//把请求体转换成vo对象
		DocVerInquiryRequestVO requestVo = translateRequestVo(requestBody);
		try{
			List<Object[]> list = vcDocVerInfoDao.docVerInquiry(requestVo);
			if(list!=null && list.size()>0){
				responseBody = generateResponseBody(list, requestBody, requestVo);
			}
		}catch(DaoException de){
			de.printStackTrace();
			throw new BusinessException(de.getMessage());
		}
		return responseBody;
	}
	
	/**
	 * 把requestBody转换成requestVo
	 * @param requestBody
	 * @return requestVo
	 * @throws Exception
	 */
	private DocVerInquiryRequestVO translateRequestVo(DocVerInquiryOldRequestDTO requestBody) throws BusinessException{
		DocVerInquiryRequestVO requestVo = new DocVerInquiryRequestVO();
		try{
			//转换单证种类id
			/*List<Long> idVcDocTypeList = vcBaseInfoDao.translateDocTypeId(requestBody.getDocTypeIDList());*/
			//转换险类ID
			Long insuTypeId = vcBaseInfoDao.translateInsuTypeId(requestBody.getInsuTypeID());
			//转换险种代码
			String insuKindCode = vcBaseInfoDao.translateInsuKindCode(requestBody.getInsuKindID(),insuTypeId);
			//转换机构代码
			//List<String> orgCode = vcBaseInfoDao.getOrgCodeListByOldId(requestBody.getOrgID());
			List<String> orgCode = vcBaseInfoDao.getOrgCodeListByOldId(requestBody.getDepID(),requestBody.getDepCode());
			if(orgCode==null || orgCode.size()<1)
				throw new BusinessException("机构ID不可用！");
	
			/*requestVo.setIdVcDocTypeList(idVcDocTypeList);*/
			requestVo.setIdVcDocTypeList(requestBody.getDocTypeIDList());//单证种类
			requestVo.setInsuKindCode(insuKindCode);//险种-做映射关系转换
			requestVo.setOrgCodeList(orgCode);//机构-做映射关系转换
			requestVo.setIdVcDocVer(requestBody.getDocTypeID());//单证类型
		}catch(DaoException de){
			de.printStackTrace();
			throw new BusinessException(de.getMessage());
		}
		return requestVo;
	}
	
	/**
	 * 把查询结果转换成响应体
	 * @param list
	 * @return
	 */
	private DocVerInquiryOldResponseDTO generateResponseBody(List<Object[]> list, 
			DocVerInquiryOldRequestDTO requestBody, DocVerInquiryRequestVO requestVo) throws BusinessException{
		DocVerInquiryOldResponseDTO responseBody = new DocVerInquiryOldResponseDTO();
		List<DocVerDTO> docVerDTOList = new ArrayList<DocVerDTO>();
		//把老系统机构id转换成新系统机构代码
		//String orgCode = vcBaseInfoDao.translateOrgCode(requestBody.getOrgID());
		String orgCode = vcBaseInfoDao.translateOrgCode(requestBody.getDepID(), requestBody.getDepCode());
		try{
			for(Object[] obj: list){
				//单证类型
				VcDocVersionInfo versionInfo = (VcDocVersionInfo)obj[0];
				//单证种类
				VcDocType docType= (VcDocType)obj[1];
				
				//获取打印配置信息
				VcDocPrintSet vcDocPrintSet = vcDocVerInfoDao.queryVcDocPrintSet(versionInfo.getIdVcDocVersionInfo(), 
						requestVo.getInsuKindCode(), orgCode);
				
				DocVerDTO docVerDto = new DocVerDTO();
				docVerDto.setType(Integer.parseInt(docType.getDocType()));//单证分类
				docVerDto.setDocTypeID(docType.getIdVcDocType());//单证种类ID
				docVerDto.setDocTypeName(docType.getDocTypeName());//单证种类名称
				docVerDto.setDocVerID(versionInfo.getIdVcDocVersionInfo());//单证类型ID
				docVerDto.setDocVerCode(versionInfo.getDocVerCode());//单证类型代码
				docVerDto.setDocVerName(versionInfo.getDocVerName());//单证类型名称
				docVerDto.setOrgID(requestBody.getOrgID()+"");//机构id
				docVerDto.setInsuKindID(requestBody.getInsuKindID());//险种代码
				if(vcDocPrintSet!=null){
					docVerDto.setPrintTemplate(vcDocPrintSet.getPrintTemplate());//打印模板
					docVerDto.setIsInvoice(vcDocPrintSet.getIsInvoice());//是否是连发票的保单
				}
				docVerDTOList.add(docVerDto);
			}
		}catch(DaoException de){
			de.printStackTrace();
			throw new BusinessException(de.getMessage());
		}
		responseBody.setDocVerDTOList(docVerDTOList);
		return responseBody;
	}
	/*private DocVerInquiryOldResponseDTO generateResponseBody(List<Object[]> list, DocVerInquiryOldRequestDTO requestBody) throws BusinessException{
		DocVerInquiryOldResponseDTO responseBody = new DocVerInquiryOldResponseDTO();
		List<DocVerDTO> docVerDTOList = new ArrayList<DocVerDTO>();
		try{
			for(Object[] obj: list){
				//单证类型
				VcDocVersionInfo versionInfo = (VcDocVersionInfo)obj[0];
				//关联险种
				VcDocRelInsuKind kind = (VcDocRelInsuKind)obj[1];
				//关联地区
				VcDocRelArea area = (VcDocRelArea)obj[2];
				//单证种类
				VcDocType docType= (VcDocType)obj[3];
				
				//获取打印配置信息
				VcDocPrintSet vcDocPrintSet = vcDocVerInfoDao.queryVcDocPrintSet(versionInfo.getIdVcDocVersionInfo(), 
								kind.getInsuKindCode(), area.getOrgCode());
				
				DocVerDTO docVerDto = new DocVerDTO();
				docVerDto.setType(Integer.parseInt(docType.getDocType()));//单证分类
				docVerDto.setDocTypeID(docType.getIdVcDocType());//单证种类ID
				docVerDto.setDocTypeName(docType.getDocTypeName());//单证种类名称
				docVerDto.setDocVerID(versionInfo.getIdVcDocVersionInfo());//单证类型ID
				docVerDto.setDocVerCode(versionInfo.getDocVerCode());//单证类型代码
				docVerDto.setDocVerName(versionInfo.getDocVerName());//单证类型名称
				//根据机构代码查找对应的老系统机构id
				String orgId = vcBaseInfoDao.getCompanyId(area.getOrgCode());
				docVerDto.setOrgID(orgId);
				docVerDto.setInsuKindID(requestBody.getInsuKindID());//险种代码
				if(vcDocPrintSet!=null){
					docVerDto.setPrintTemplate(vcDocPrintSet.getPrintTemplate());//打印模板
					docVerDto.setIsInvoice(vcDocPrintSet.getIsInvoice());//是否是连发票的保单
				}
				docVerDTOList.add(docVerDto);
			}
		}catch(DaoException de){
			de.printStackTrace();
			throw new BusinessException(de.getMessage());
		}
		responseBody.setDocVerDTOList(docVerDTOList);
		return responseBody;
	}*/
	

	public void setVcDocVerInfoDao(VcDocVerInfoDao vcDocVerInfoDao) {
		this.vcDocVerInfoDao = vcDocVerInfoDao;
	}

	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}
}
