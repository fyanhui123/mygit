package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.service.security.agreementdetails.AgreementDetailRequest;
import com.tapi.service.security.agreementdetails.AgreementDetailResponse;
import com.tapi.service.security.agreementdetails.AgreementDetailService;
import com.tapi.service.security.agreementdetails1.AgreementDtoRequest;
import com.tapi.service.security.agreementdetails1.AgreementDtoResponse;
import com.tapi.service.security.agreementdetails1.AgreementDtoService;
import com.tapi.service.security.teamdetails.TeamDetailRequest;
import com.tapi.service.security.teamdetails.TeamDetailResponse;
import com.tapi.service.security.teamdetails.TeamDetailService;
import com.tapi.service.security.userdetails.UserDetailRequest;
import com.tapi.service.security.userdetails.UserDetailResponse;
import com.tapi.service.security.userdetails.UserDetailService;
import com.tapi.service.security.userdetails1.UserDtoRequest;
import com.tapi.service.security.userdetails1.UserDtoResponse;
import com.tapi.service.security.userdetails1.UserDtoService;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.baseinfo.service.VcTakerService;
import com.tapi.tcs.vc.schema.model.PubCode;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcTaker;

public class VcTakerServiceImpl implements VcTakerService {

	private VcTakerDao vcTakerDao;
	/**销管查询团队接口服务*/
	private TeamDetailService teamDetailService;
	/**销管查询团队下人员接口服务*/
	private UserDetailService userDetailService;
	/**销管查询人员对象信息接口服务*/
	private UserDtoService userDtoService;
	/**销管查询协议列表接口服务*/
	private AgreementDetailService agreementDetailService;
	/**销管查询协议对象信息接口服务*/
	private AgreementDtoService agreementDtoService;

	@Override
	public List<VcTaker> getTakerJson(QueryRule qr) {
		return vcTakerDao.find(qr);
	}

	@Override
	public String queryOrgInfo(String takerCode) throws BusinessException {
		try{
			VcLevel vcLevel = vcTakerDao.queryOrgName(takerCode);
			if (vcLevel != null) {
				return "{orgCode:'" + vcLevel.getUnitCode() + "',orgName: '" + vcLevel.getUnitName()+"'}";
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public List<VcTaker> getTakerJson(String takerCode, String orgCode) throws BusinessException {
		List<VcTaker> list = null;
		try{
			list = vcTakerDao.getTakerJson(takerCode, orgCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	/**
     * 根据条件查找可使用人
     * @param queryDto
     * @return
     * @throws DaoException
     *@author whj
     *@since Mar 6, 2014
     */
	@Override
    public List<VcTaker> queryVcTakerList(VcTaker queryDto) throws BusinessException {
        List<VcTaker> list = null;
        try{
            list = vcTakerDao.queryVcTakerList(queryDto);
        }catch(DaoException e){
            throw new BusinessException(e.getMessage(), e);
        }
        return list;
    }
	
	@Override
	public List<VcTaker> getJsonVcTaker(String codeValue, String orgCode) throws BusinessException {
		List<VcTaker> list = null;
		try{
			list = vcTakerDao.getJsonVcTaker(codeValue, orgCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public TeamDetailResponse queryTeamDetail(String orgCode, String channelDetailCode) throws BusinessException {
		TeamDetailResponse response = null;
		TeamDetailRequest request = new TeamDetailRequest();
		com.tapi.service.security.teamdetails.RequestHeadDto requestHead  
			= new com.tapi.service.security.teamdetails.RequestHeadDto();
		requestHead.setSystemCode("");
		requestHead.setUser("");
		requestHead.setPassword("");
		List<String> comCodes = new ArrayList<String>();
		comCodes.add(orgCode);
		request.setHeader(requestHead);
		request.setComCodes(comCodes);
		request.setTeamType(channelDetailCode);
		try{
			response = teamDetailService.queryTeamDetail(request);
		}catch(Exception e){
			throw new BusinessException("调用销管服务出错！", e);
		}
		return response;
	}
	
	@Override
	public AgreementDetailResponse queryAgreementDetail(String orgCode, String channelDetailCode) throws BusinessException {
		AgreementDetailResponse response = null;
		AgreementDetailRequest request = new AgreementDetailRequest();
		com.tapi.service.security.agreementdetails.RequestHeadDto requestHead  
			= new com.tapi.service.security.agreementdetails.RequestHeadDto();
		requestHead.setSystemCode("");
		requestHead.setUser("");
		requestHead.setPassword("");
		List<String> comCodes = new ArrayList<String>();
		comCodes.add(orgCode);
		request.setHeader(requestHead);
		request.setComCodes(comCodes);
		request.setTeamType(channelDetailCode);
		try{
			response = agreementDetailService.queryAgreementDetail(request);
		}catch(Exception e){
			throw new BusinessException("调用销管服务出错！", e);
		}
		return response;
	};
	
	@Override
	public UserDetailResponse queryUserDetail(String teamCode) throws BusinessException {
		UserDetailResponse response = null;
		UserDetailRequest request = new UserDetailRequest();
		com.tapi.service.security.userdetails.RequestHeadDto requestHead 
			= new com.tapi.service.security.userdetails.RequestHeadDto();
		requestHead.setSystemCode("");
		requestHead.setUser("");
		requestHead.setPassword("");
		request.setHeader(requestHead);
		request.setTeamCode(teamCode);
		try{
			response = userDetailService.queryUserDetail(request);
		}catch(Exception e){
			throw new BusinessException("调用销管服务出错！", e);
		}
		return response;
	}
	
	@Override
	public String queryOrgByUserCode(String userCode) throws BusinessException {
		UserDtoResponse response = null;
		UserDtoRequest request = new UserDtoRequest();
		com.tapi.service.security.userdetails1.RequestHeadDto head 
			= new com.tapi.service.security.userdetails1.RequestHeadDto();
		head.setSystemCode("");
		head.setUser("");
		head.setPassword("");
		request.setHeader(head);
		request.setUserCode(userCode);
		try{
			response = userDtoService.queryUserDto(request);
		}catch(Exception e){
			throw new BusinessException("调用销管服务出错！", e);
		}
		if("999".equals(response.getHeader().getResponseCode())){
			throw new BusinessException(response.getHeader().getResponseMessage());
		}
		String deptCode = response.getSaUserNewDto().getDeptCode();
		String deptName = deptCode;
		try{
			PubCompany pubCompany = vcTakerDao.findCompanyNameByCode(deptCode);
			if(pubCompany!=null){
				deptName = pubCompany.getCompanyCname();
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return "{orgCode:'" + deptCode + "',orgName: '" + deptName+"'}";
	}
	
	@Override
	public List<PubCode> queryPubCodeList(String codeType, String codeCode, String remark) throws BusinessException {
		
		List<PubCode> pubCodeList = null;
		try{
			
			if(codeType.equals("UnderWriteChannel") && StringUtils.isNotBlank(remark) ){
				//渠道类型根据销售机构代码获取
				String orgCode = remark;
				PubCompany pubCompany = vcTakerDao.findCompanyNameByCode(orgCode);
				codeCode = pubCompany.getChannelCode();
				pubCodeList = vcTakerDao.queryPubCodeList(codeType, codeCode, null); 
			}else if(codeType.equals("BusinessSource") && StringUtils.isNotBlank(remark)){
				//业务来源根据渠道类型获取
				pubCodeList = vcTakerDao.queryPubCodeForbusinessSource(codeType,remark);
			}else{
				pubCodeList = vcTakerDao.queryPubCodeList(codeType, codeCode, remark); 
			}
			
			return pubCodeList;
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<PubCompany> querySaleOrgCodeList(String companyCode) throws BusinessException {
		try{
			return vcTakerDao.querySaleOrgCodeList(companyCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	public AgreementDtoResponse queryAgreementDto(String agreementNo) throws BusinessException {
		AgreementDtoResponse response=null;
		AgreementDtoRequest request = new AgreementDtoRequest();
		com.tapi.service.security.agreementdetails1.RequestHeadDto head 
			= new com.tapi.service.security.agreementdetails1.RequestHeadDto();
		head.setSystemCode("");
		head.setUser("");
		head.setPassword("");
		request.setHeader(head);
		request.setAgreementNo(agreementNo);
		try{
			response = agreementDtoService.queryAgreementDto(request);
		}catch(Exception e){
			throw new BusinessException("调用销管服务出错！", e);
		}
		if("999".equals(response.getHeader().getResponseCode())){
			throw new BusinessException(response.getHeader().getResponseMessage());
		}
		return response;
	}

	public void setVcTakerDao(VcTakerDao vcTakerDao) {
		this.vcTakerDao = vcTakerDao;
	}

	public void setTeamDetailService(TeamDetailService teamDetailService) {
		this.teamDetailService = teamDetailService;
	}

	public void setUserDetailService(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	public void setUserDtoService(UserDtoService userDtoService) {
		this.userDtoService = userDtoService;
	}

	public void setAgreementDetailService(
			AgreementDetailService agreementDetailService) {
		this.agreementDetailService = agreementDetailService;
	}

	public void setAgreementDtoService(AgreementDtoService agreementDtoService) {
		this.agreementDtoService = agreementDtoService;
	}
}
