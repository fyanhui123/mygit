package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.PubCode;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcTaker;

public interface VcTakerDao extends GenericDao<VcTaker> {
	
	public VcLevel queryOrgName(String takerCode)throws DaoException;

	public String getUnitNameByUnitCode(String takerCode)throws DaoException;
	
	/**
	 * 根据归属机构查找使用人
	 * @param takerCode
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcTaker> getTakerJson(String takerCode, String orgCode) throws DaoException;
	
	
	/**
     * 根据条件查找可使用人
     * @param queryDto
     * @return
     * @throws DaoException
     *@author whj
     *@since Mar 6, 2014
     */   
    public List<VcTaker> queryVcTakerList(VcTaker queryDto) throws DaoException;
    
	
	/**
	 * 递归查询机构下的使用人
	 * @param codeValue
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcTaker> getJsonVcTaker(String codeValue, String orgCode) throws DaoException;
	
	
	/**
     * 根据单证使用人code获取对象
     * @param takerCode
     * @return
     * @throws DaoException
     * add by whj 20130918
     */
    public VcTaker getTakerByTakerCode(String takerCode) throws DaoException;
    
    /**
     * 根据机构代码查询机构名称
     * @param companyCode
     * @return
     * @throws DaoException
     */
    public PubCompany findCompanyNameByCode(String companyCode) throws DaoException;
    
    /**
     * 查询公共表PUB_CODE
     * @param codeType
     * @param codeCode
	 * @param remark
     * @return
     * @throws DaoException
     */
    public List<PubCode> queryPubCodeList(String codeType, String codeCode, String remark) throws DaoException;
    
    /**
	 * 根据当前机构查询出销售机构列表
	 * @param companyCode
	 * @return
	 * @throws BusinessException
	 */
	public List<PubCompany> querySaleOrgCodeList(String companyCode) throws DaoException;

	public List<PubCode> queryPubCodeForbusinessSource(String codeType, String innerCode) throws DaoException;
}
