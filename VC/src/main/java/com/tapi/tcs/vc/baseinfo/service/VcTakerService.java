package com.tapi.tcs.vc.baseinfo.service;

import java.util.List;

import com.tapi.service.security.agreementdetails.AgreementDetailResponse;
import com.tapi.service.security.agreementdetails1.AgreementDtoResponse;
import com.tapi.service.security.teamdetails.TeamDetailResponse;
import com.tapi.service.security.userdetails.UserDetailResponse;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.PubCode;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcTaker;

public interface VcTakerService {

	public List<VcTaker> getTakerJson(QueryRule qr);
	
	
	/**
     * 根据条件查找可使用人
     * @param queryDto
     * @return
     * @throws DaoException
     *@author whj
     *@since Mar 6, 2014
     */
    public List<VcTaker> queryVcTakerList(VcTaker queryDto) throws BusinessException;
	
	/**
	 * 根据使用人编码获取所在机构
	 * @param takerCode
	 * @return
	 */
	public String queryOrgInfo(String takerCode) throws BusinessException;
	
	/**
	 * 根据归属机构查找使用人
	 * @param takerCode
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public List<VcTaker> getTakerJson(String takerCode, String orgCode) throws BusinessException;
	
	/**
	 * 递归查找归属机构下的使用人
	 * @param codeValue
	 * @param orgCode
	 * @return
	 * @throws BusinessException
	 */
	public List<VcTaker> getJsonVcTaker(String codeValue, String orgCode) throws BusinessException;
	
	/**
	 * 查询销管系统团队列表
	 * @param orgCode
	 * @param channelDetailCode
	 * @return
	 * @throws BusinessException
	 */
	public TeamDetailResponse queryTeamDetail(String orgCode, String channelDetailCode) throws BusinessException;
	
	/**
	 * 查询中介协议列表
	 * @param orgCode
	 * @param channelDetailCode
	 * @return
	 * @throws BusinessException
	 */
	public AgreementDetailResponse queryAgreementDetail(String orgCode, String channelDetailCode) throws BusinessException;
	
	/**
	 * 查询销管系统团队下人员列表
	 * @param teamCode
	 * @return
	 * @throws BusinessException
	 */
	public UserDetailResponse queryUserDetail(String teamCode) throws BusinessException;
	
	/**
	 * 根据用户代码查询机构信息
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public String queryOrgByUserCode(String userCode) throws BusinessException;
	
	/**
	 * 查询公共表PUB_CODE
	 * @param codeType
	 * @param codeCode
	 * @param remark
	 * @return
	 * @throws BusinessException
	 */
	public List<PubCode> queryPubCodeList(String codeType, String codeCode, String remark) throws BusinessException;
	
	/**
	 * 根据当前机构查询出销售机构列表
	 * @param companyCode
	 * @return
	 * @throws BusinessException
	 */
	public List<PubCompany> querySaleOrgCodeList(String companyCode) throws BusinessException;
	
	/**
	 * 查询协议对象信息
	 * @param agreementNo
	 * @return
	 * @throws BusinessException
	 */
	public AgreementDtoResponse queryAgreementDto(String agreementNo) throws BusinessException;
}
