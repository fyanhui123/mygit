package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInsuCardSalesRecord;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.CardInfoDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.ProductInfoDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.RiskDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.SalesInfoDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.dao.VcInsuCardInquiryDao;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.service.VcInsuCardInquiryService;

public class VcInsuCardInquiryServiceImpl implements VcInsuCardInquiryService {

	private VcInsuCardInquiryDao vcInsuCardInquiryDao;
	private VcLevelDao vcLevelDao;

	@Override
	public InsuCardInfoInquiryResponse queryInsuCardInfo(InsuCardInfoInquiryRequestDTO requestBody) throws BusinessException {
		InsuCardInfoInquiryResponse response = new InsuCardInfoInquiryResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		InsuCardInfoInquiryResponseDTO responseBody = new InsuCardInfoInquiryResponseDTO();
		try{
			//1、查询激活卡、单证类型信息
			//modify by zhxiao3 VC-131仅以保险卡卡号为查询条件查询保险卡信息 begin
			Object[] obj = vcInsuCardInquiryDao.findVcInsuranceCard(requestBody.getCardNO(), null);
			if(obj==null || obj.length<1){
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage("卡号不存在！");
				responseBody.setStatus("1");
				responseBody.setResultMessage("卡号不存在！");
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			}
			//激活卡信息
			VcInsuranceCard vcInsuranceCard = (VcInsuranceCard)obj[0];
			//单证类型信息
			VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo)obj[1];
			CardInfoDTO cardInfoDTO = generateCardInfo(vcInsuranceCard, vcDocVersionInfo);
			responseBody.setCardInfoDTO(cardInfoDTO);
			
			if(StringUtils.equals(vcInsuranceCard.getCardStatus(), "1") 
					|| StringUtils.equals(vcInsuranceCard.getCardStatus(), "22")) {
				//2、查询产品信息
				List<RiskDTO> riskDTOs = vcInsuCardInquiryDao.findRiskDTOs(vcDocVersionInfo.getIdVcDocVersionInfo());
				if(riskDTOs==null || riskDTOs.size()<1){
					//throw new BusinessException("没有查询到产品信息！");
					responseHead.setResponseCode("999");
					responseHead.setResponseMessage("没有查询到产品信息！");
					responseBody.setStatus("3");
					responseBody.setResultMessage("没有查询到产品信息！");
					response.setResponseHead(responseHead);
					response.setResponseBody(responseBody);
					return response;
				}
				ProductInfoDTO productInfoDto = new ProductInfoDTO();
				productInfoDto.setRiskDTOs(riskDTOs);
				responseBody.setProductInfoDTO(productInfoDto);
				
				//3、查询销售信息
				/*VcTaker vcTaker = vcInsuCardInquiryDao.findVcTaker(vcInsuranceCard.getDocVerCode(), vcInsuranceCard.getCardNo());
				if(vcTaker==null){
					//throw new BusinessException("没有查询到销售信息！");
					responseHead.setResponseCode("999");
					responseHead.setResponseMessage("没有查询到销售信息！");
					responseBody.setStatus("3");
					responseBody.setResultMessage("没有查询到销售信息！");
					response.setResponseHead(responseHead);
					response.setResponseBody(responseBody);
					return response;
				}
				SalesInfoDTO salesInfoDTO = generateSalesInfo(vcTaker);*/
				VcInsuCardSalesRecord vcInsuCardSalesRecord = vcInsuCardInquiryDao
					.findSalesInfo(vcInsuranceCard.getDocVerCode(), vcInsuranceCard.getCardNo());
				if(vcInsuCardSalesRecord==null){
					responseHead.setResponseCode("999");
					responseHead.setResponseMessage("没有查询到销售信息！");
					responseBody.setStatus("3");
					responseBody.setResultMessage("没有查询到销售信息！");
					response.setResponseHead(responseHead);
					response.setResponseBody(responseBody);
					return response;
				}
				SalesInfoDTO salesInfoDTO = generateSalesInfo(vcInsuCardSalesRecord);
				responseBody.setSalesInfoDTO(salesInfoDTO);
			}
			
			//校验激活卡密码与使用截止期
			String message = this.checkData(requestBody,vcInsuranceCard);
			if(StringUtils.isNotEmpty(message)){
				responseHead.setResponseCode("000");
				responseHead.setResponseMessage(message);
				responseBody.setStatus("2");
				responseBody.setResultMessage(message);
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			}
			//modify by zhxiao3 VC-131仅以保险卡卡号为查询条件查询保险卡信息 end
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("查询成功！");
			responseBody.setStatus("0");
			responseBody.setResultMessage("查询成功！");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return response;
	}
	
	/**
	 * 转换卡信息-CardInfoDTO
	 * @param vcInsuranceCard
	 * @param vcDocVersionInfo
	 * @return
	 */
	private CardInfoDTO generateCardInfo(VcInsuranceCard vcInsuranceCard, VcDocVersionInfo vcDocVersionInfo){
		CardInfoDTO cardInfoDTO = new CardInfoDTO();
		cardInfoDTO.setDocVerCode(vcDocVersionInfo.getDocVerCode());
		cardInfoDTO.setDocVerName(vcDocVersionInfo.getDocVerName());
		cardInfoDTO.setCardNO(vcInsuranceCard.getCardNo());
		cardInfoDTO.setValue(new BigDecimal(vcInsuranceCard.getValue()));
		cardInfoDTO.setValidDate(vcInsuranceCard.getValidDate());
		cardInfoDTO.setCardStatus(vcInsuranceCard.getCardStatus());
		//modify by zhxiao3 VC-117 激活卡信息新增"关联大保单" begin
		cardInfoDTO.setBigPolicyNo(vcInsuranceCard.getBigPolicyNo());
		//modify by zhxiao3 VC-117 激活卡信息新增"关联大保单" end
		return cardInfoDTO;
	}
	
	/**
	 * 转换销售信息-SalesInfoDTO
	 * @param vcTaker
	 * @return
	 */
	private SalesInfoDTO generateSalesInfo(VcTaker vcTaker) throws BusinessException{
		SalesInfoDTO salesInfoDTO = new SalesInfoDTO();
		salesInfoDTO.setSellerCode(vcTaker.getTakerCode());
		salesInfoDTO.setSaleOrgCode(vcTaker.getOrgCode());
		if(StringUtils.isNotEmpty(vcTaker.getAgencyOrgCode())){
			salesInfoDTO.setAgencyOrgCode(vcTaker.getAgencyOrgCode());
		}else{
			salesInfoDTO.setAgencyOrgCode("");
		}
		try{
			String orgName = vcLevelDao.getUnitNameByUnitCode(vcTaker.getOrgCode());
			salesInfoDTO.setSaleOrgName(orgName);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return salesInfoDTO;
	}
	
	/**
	 * 转换销售信息
	 * @param vcInsuCardSalesRecord
	 * @return
	 * @throws BusinessException
	 */
	private SalesInfoDTO generateSalesInfo(VcInsuCardSalesRecord vcInsuCardSalesRecord) throws BusinessException {
		SalesInfoDTO salesInfoDTO = new SalesInfoDTO();
		salesInfoDTO.setChannelDetailCode(vcInsuCardSalesRecord.getChannelDetailCode()); //渠道类型
		salesInfoDTO.setSellerCode(vcInsuCardSalesRecord.getSellerCode());
		salesInfoDTO.setSaleOrgCode(vcInsuCardSalesRecord.getSaleOrgCode());
		salesInfoDTO.setSellerName(vcInsuCardSalesRecord.getSellerName());
		if(StringUtils.isNotEmpty(vcInsuCardSalesRecord.getTeamCode())){
			salesInfoDTO.setTeamCode(vcInsuCardSalesRecord.getTeamCode()); //团队代码
		}else{
			salesInfoDTO.setTeamCode("");
		}
		salesInfoDTO.setAgreementNo(vcInsuCardSalesRecord.getAgreementNo()); //协议代码
		salesInfoDTO.setBusinessSource(vcInsuCardSalesRecord.getBusinessSource()); //业务来源
		if(StringUtils.isNotEmpty(vcInsuCardSalesRecord.getAgencyOrgCode())){
			salesInfoDTO.setAgencyOrgCode(vcInsuCardSalesRecord.getAgencyOrgCode());
		}else{
			salesInfoDTO.setAgencyOrgCode("");
		}
		if(StringUtils.isNotEmpty(vcInsuCardSalesRecord.getHandlerCode())){
			salesInfoDTO.setHandlerCode(vcInsuCardSalesRecord.getHandlerCode());//跟单业务员代码
		}else{
			salesInfoDTO.setHandlerCode("");
		}
		if(StringUtils.isNotEmpty(vcInsuCardSalesRecord.getHandlerName())){
			salesInfoDTO.setHandlerName(vcInsuCardSalesRecord.getHandlerName());//跟单业务员名称
		}else{
			salesInfoDTO.setHandlerName("");
		}
		try{
			String orgName = vcLevelDao.getUnitNameByUnitCode(vcInsuCardSalesRecord.getSaleOrgCode());
			salesInfoDTO.setSaleOrgName(orgName);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return salesInfoDTO;
	}
	
	public void setVcInsuCardInquiryDao(VcInsuCardInquiryDao vcInsuCardInquiryDao) {
		this.vcInsuCardInquiryDao = vcInsuCardInquiryDao;
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
	
	/**
	 * 校验激活卡是否可激活
	 * @param request
	 * @return
	 */
	private String checkData(InsuCardInfoInquiryRequestDTO requestBody,VcInsuranceCard vcInsuranceCard) {
		String msg = "";
		
		if(StringUtils.isBlank(requestBody.getEncryptedPW())){
			msg += "密码为空！";
		}else{
			if(!requestBody.getEncryptedPW().equals(vcInsuranceCard.getEncryptedPw())){
				String a = requestBody.getEncryptedPW();
				String b = vcInsuranceCard.getEncryptedPw();
				System.out.println("a is:"+a);
				System.out.println("b is:"+b);
				msg += "密码错误！";
			}
		}
		Date tempDate=vcInsuranceCard.getValidDate();
		if(tempDate==null){
			msg="激活卡有效期不存在！";
		}else{
			Date date=new Date();
			//有效期向后顺延3个月
			date=DateUtils.addMonth(date, -3); 
            tempDate=DateUtils.addDay(tempDate, 1);//由于有效期不带时分秒，故天数加一
            if(DateUtils.compare(date, tempDate)>0){
                msg="激活卡已过期！";
            }
        }
		return msg;
	}
	
	
}
