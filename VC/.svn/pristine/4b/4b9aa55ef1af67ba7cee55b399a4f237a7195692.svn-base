package com.tapi.tcs.vc.baseinfo.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.service.security.agreementdetails.AgreementDetailResponse;
import com.tapi.service.security.agreementdetails.PrpDagreementNewDto;
import com.tapi.service.security.agreementdetails1.AgreementDtoResponse;
import com.tapi.service.security.teamdetails.SaTeamNewDto;
import com.tapi.service.security.teamdetails.TeamDetailResponse;
import com.tapi.service.security.userdetails.SaUserNewDto;
import com.tapi.service.security.userdetails.UserDetailResponse;
import com.tapi.tcs.json.TFJSON;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.utils.text.TFStrUtils;
import com.tapi.tcs.vc.baseinfo.service.VcTakerService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.PubCode;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcTaker;

public class VcTakerAction extends TFAction {

	private static final long serialVersionUID = 937368504267001540L;

	private List<Map<String, String>> mapList;

	private VcTakerService vcTakerService;

	private String jsonStr;

	/**
	 * 使用人 自动完成/下拉
	 * 
	 * @return
	 */
	public String getTakerJson() throws BusinessException {
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			// type:auto-自动完成组件 /select-下拉框
			String orgCode = userInfo.getComCode();
			String type = getRequest().getParameter("type");
			// action: query-显示“-请选择-” 空 不显示
			String action = getRequest().getParameter("action");
			String codeValue = getRequest().getParameter("codeCode");
			mapList = new ArrayList<Map<String, String>>();
	
			String lableName = "label";
			if (StringUtils.isNotEmpty(type) && "auto".equals(type)) {
				lableName = "lable";
			} else {
				if ("query".equals(action)) {
					Map<String, String> map_ = new HashMap<String, String>();
					map_.put(lableName, "-请选择-");
					map_.put("value", "");
					mapList.add(map_);
				}
			}
	
			/*QueryRule qr = QueryRule.getInstance();
			qr.addEqual("status", "1");
			if (Beans.isNotEmpty(codeValue)) {
				qr.addLike("takerCode", "%" + codeValue + "%");
			}
			
			qr.addSql(" org_code IN  (" +
					" SELECT Z.UNIT_CODE " +
					" FROM VC_LEVEL Z " +
					" WHERE Z.UNIT_TYPE = '0' " +
					" START WITH Z.UNIT_CODE = '"+ orgCode+ "' " +
					" CONNECT BY Z.PARENT_ORG_ID = PRIOR Z.ID_VC_LEVEL " 
					+") ");
			
			List<VcTaker> vcTakers = vcTakerService.getTakerJson(qr);*/
			List<VcTaker> vcTakers = vcTakerService.getTakerJson(codeValue, orgCode);
			if(vcTakers!=null && vcTakers.size()>0){
				for (Iterator iterator = vcTakers.iterator(); iterator.hasNext();) {
					VcTaker vcTaker = (VcTaker) iterator.next();
					Map<String, String> map = new HashMap<String, String>();
					map.put(lableName, vcTaker.getTakerName());
					map.put("value", vcTaker.getTakerCode());
					mapList.add(map);
				}
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 递归查询机构下的使用人
	 * @return
	 * @throws BusinessException
	 */
	public String queryJsonVcTaker() throws BusinessException {
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			String orgCode = userInfo.getComCode();
			String codeValue = getRequest().getParameter("codeCode");
			mapList = new ArrayList<Map<String, String>>();
	
			List<VcTaker> vcTakers = vcTakerService.getJsonVcTaker(codeValue, orgCode);
			if(vcTakers!=null && vcTakers.size()>0){
				Map<String, String> map = null;
				for (VcTaker vcTaker : vcTakers) {
					map = new HashMap<String, String>();
					map.put("lable", vcTaker.getTakerName());
					map.put("value", vcTaker.getTakerCode());
					mapList.add(map);
				}
			}
		}catch(BusinessException e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage("系统异常，请与管理员联系！"));
			return NONE;
		}
		return SUCCESS;
	}

	
	/**
	 * 根据使用人编码获取使用人所在机构
	 * @return
	 */
	public String findOrgCodeJson() {
		try{
			String takerCode = getRequest().getParameter("takerCode");
			jsonStr = vcTakerService.queryOrgInfo(takerCode);
		}catch(BusinessException e){
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 团队自动完成
	 * @return
	 * @throws BusinessException
	 */
	public String queryTeamListJson() throws BusinessException {
		logger.info("访问/baseinfoJson/queryTeamListJson.do查询所属团队自动完成...");
		try{
			String orgCode = getRequest().getParameter("orgCode");
			String channelDetailCode = getRequest().getParameter("channelDetailCode");
			if(StringUtils.isEmpty(orgCode)){
				throw new BusinessException("请选择销售机构！");
			}
			if(StringUtils.isEmpty(channelDetailCode)){
				throw new BusinessException("请选择渠道类型！");
			}
			TeamDetailResponse response = vcTakerService.queryTeamDetail(orgCode, channelDetailCode);
			if(response!=null&&response.getHeader()!=null){
				if("999".equals(response.getHeader().getResponseCode())){
					throw new BusinessException(response.getHeader().getResponseMessage());
				}
				mapList = new ArrayList<Map<String, String>>();
				List<SaTeamNewDto> teamList = response.getTeamNewDetails();
				if(teamList!=null && teamList.size()!=0){
					Map<String, String> map = null;
					for(SaTeamNewDto saTeamDto : teamList){
						map = new HashMap<String, String>();
						map.put("lable", saTeamDto.getTeamName());
						map.put("value", saTeamDto.getTeamCode());
						mapList.add(map);
					}
				}
			}
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error("系统异常，请与管理员联系！", e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 人员自动完成
	 * @return
	 * @throws BusinessException
	 */
	public String queryUserListJson() throws BusinessException {
		logger.info("访问/baseinfoJson/queryUserListJson.do查询团队下人员自动完成...");
		try{
			String teamCode = getRequest().getParameter("teamCode");
			if(StringUtils.isEmpty(teamCode)){
				throw new BusinessException("请选择所属团队！");
			}
			UserDetailResponse response = vcTakerService.queryUserDetail(teamCode);
			if(response!=null&&response.getHeader()!=null){
				if("999".equals(response.getHeader().getResponseCode())){
					throw new BusinessException(response.getHeader().getResponseMessage());
				}
				mapList = new ArrayList<Map<String, String>>();
				//List<SaUserDto> userList = response.getUserDetails();
				List<SaUserNewDto> userList = response.getUserNewDetails();
				if(userList!=null && userList.size()!=0){
					Map<String, String> map = null;
					for(SaUserNewDto saUserDto : userList){
						map = new HashMap<String, String>();
						map.put("lable", saUserDto.getUserName());
						map.put("value", saUserDto.getUserCode());
						mapList.add(map);
					}
				}
			}
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error("系统异常，请与管理员联系！", e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public String findUserDetailJson() throws BusinessException {
		try{
			String userCode = getRequest().getParameter("userCode");
			jsonStr = vcTakerService.queryOrgByUserCode(userCode);
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 渠道类型/业务来源自动完成
	 * @return
	 * @throws BusinessException
	 */
	public String queryChannelDetailCode() throws BusinessException {
		logger.info("访问/baseinfoJson/queryChannelDetailCode.do查询渠道类型自动完成...");
		try{
			String codeType = getRequest().getParameter("codeType");
			String remark = getRequest().getParameter("remark");
			List<PubCode> list = vcTakerService.queryPubCodeList(codeType, null, remark);
			mapList = new ArrayList<Map<String, String>>();
			if(list!=null && list.size()>0){
				Map<String, String> map = null;
				for (PubCode pubCode : list) {
					map = new HashMap<String, String>();
					map.put("lable", pubCode.getCodeCname());
					map.put("value", pubCode.getId().getCodeCode());
					mapList.add(map);
				}
			}
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error("系统异常，请与管理员联系！", e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 销售机构自动完成
	 * @return
	 * @throws BusinessException
	 */
	public String querySaleOrgCodeList() throws BusinessException {
		logger.info("访问/baseinfoJson/querySaleOrgCodeList.do查询销售机构自动完成...");
		try{
			UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
			if(userInfo==null){
				throw new BusinessException("登录超时，请重新登录！");
			}
			String companyCode = userInfo.getComCode();
			List<PubCompany> list = vcTakerService.querySaleOrgCodeList(companyCode);
			mapList = new ArrayList<Map<String, String>>();
			if(list!=null && list.size()>0){
				Map<String, String> map = null;
				for (PubCompany pubCompany : list) {
					map = new HashMap<String, String>();
					map.put("lable", pubCompany.getCompanyCname());
					map.put("value", pubCompany.getCompanyCode());
					mapList.add(map);
				}
			}
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error("系统异常，请与管理员联系！", e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	
	}
	
	/**
	 * 中介协议自动完成
	 * @return
	 * @throws BusinessException
	 */
	public String queryAgreementNoList() throws BusinessException {
		logger.info("访问/baseinfoJson/queryAgreementNoList.do查询中介协议自动完成...");
		try{
			String orgCode = getRequest().getParameter("orgCode");
			String channelDetailCode = getRequest().getParameter("channelDetailCode");
			if(StringUtils.isEmpty(orgCode)){
				throw new BusinessException("请选择销售机构！");
			}
			if(StringUtils.isEmpty(channelDetailCode)){
				throw new BusinessException("请选择渠道类型！");
			}
			AgreementDetailResponse response = vcTakerService.queryAgreementDetail(orgCode, channelDetailCode);
			if(response!=null&&response.getHeader()!=null){
				if("999".equals(response.getHeader().getResponseCode())){
					throw new BusinessException(response.getHeader().getResponseMessage());
				}
				mapList = new ArrayList<Map<String, String>>();
				List<PrpDagreementNewDto> agreementList = response.getAgreementNewDetails();
				if(agreementList!=null && agreementList.size()!=0){
					Map<String, String> map = null;
					for(PrpDagreementNewDto prpDagreementDto : agreementList){
						map = new HashMap<String, String>();
						map.put("lable", prpDagreementDto.getAgentName());
						map.put("value", prpDagreementDto.getAgreementNo());
						mapList.add(map);
					}
				}
			}
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}catch(Exception e){
			logger.error("系统异常，请与管理员联系！", e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询协议对象信息
	 * @return
	 * @throws BusinessException
	 */
	public String queryAgreementDto() throws BusinessException {
		try{
			String agreementNo = getRequest().getParameter("agreementNo");
			AgreementDtoResponse response = vcTakerService.queryAgreementDto(agreementNo);
			com.tapi.service.security.agreementdetails1.PrpDagreementNewDto dto = response.getPrpDagreementNewDto();
			//转换业务来源
			List<PubCode> pubCodeList = vcTakerService.queryPubCodeList("BusinessSource", dto.getAgentClass(), null);
			if(pubCodeList!=null && pubCodeList.size()!=0){
				//借用'渠道大类'字段保存业务来源名称
				dto.setAgentType(pubCodeList.get(0).getCodeCname());
			}else{
				dto.setAgentType("");
			}
			jsonStr = TFJSON.toJSONString(dto);
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			this.setAjaxMessageInfo(TFStrUtils.getJSONErrorMessage(e.getMessage()));
			return NONE;
		}
		return SUCCESS;
	}

	public List<Map<String, String>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}

	public void setVcTakerService(VcTakerService vcTakerService) {
		this.vcTakerService = vcTakerService;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
}
