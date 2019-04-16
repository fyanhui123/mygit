package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.vo.UserVo;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcAgencyOrg;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcTaker;

public interface VcLevelDao extends GenericDao<VcLevel> {
	/**根据用户代码查询发票管理员或者单证管理员是否存在*/
	public List<VcLevel> findByUserCode(String unitCode,String invoiceFlag) throws DaoException;
	/** 分页查询级别单位设置 */
	public Page queryVcLevelPage(String parentOrgId, String unitType, int pageNo, int pageSize) throws DaoException;

	/** 根据主键查找 */
	public VcLevel findVcLevelByPK(Long id) throws DaoException;
	public Integer getMaxDisplayNoFromLevel(String upperCompanyCode) throws DaoException;
	/**
	 * 根据主键查找，不管状态是否有效
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public VcLevel findVcLevel(Long id) throws DaoException;

	/** 保存级别设置 */
	public void saveVcLevel(VcLevel vcLevel) throws DaoException;

	/** 根据级别代码查找 */
	public List<VcLevel> findByUnitCode(String unitCode) throws DaoException;

	/** 修改级别设置 */
	public void updateVcLevel(VcLevel vcLevel) throws DaoException;

	/** 根据主键删除 */
	public void deleteVcLevelByPK(Long id) throws DaoException;

	/** 根据机构ID查找下级机构或人员 */
	public List<VcLevel> findChileByParentOrgId(Long id) throws DaoException;
	
	/**
	 * 校验机构是否可以删除：若存在下级机构或人员则不能删除
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean checkIsDeletable(Long id) throws DaoException;

	/** 根据级别代码查找上级机构或者所属机构 */
	public String findParentOrgByUnitCode(String unitCode) throws DaoException;

	/** 获取用户归属机构代码 */
	public String getComCode(String userCode) throws DaoException;
	
	/** 获取使用人机构代码 */
	public String getTakerComCode(String userCode) throws DaoException;

	/** 获取上级机构 */
	public String getUpperOrgCode(String userCode) throws DaoException;

	/** 获取下级机构机构 */
	public List<String> getChildOrgCode(String comCode) throws DaoException;

	

	/**
	 * 根据类型及code查询
	 * @param unitType
	 * @param unitCode
	 * @return
	 * @throws DaoException
	 */
	public VcLevel getVcLevel(String unitType, String unitCode) throws DaoException;
	
	/**
	 * 根据根机构code查询到指定级别的机构树
	 * @param rootOrgCode根机构(若为空则默认为总公司)
	 * @param minLevel 查询到哪一级机构（若minLevel<1则不限制）
	 * @return
	 * @throws DaoException
	 * @author whjwanghuajian
	 */

	public List<VcLevel> queryOrgZTreeList(String rootOrgCode, int minLevel)throws DaoException;
	
	/**
	 * 根据根机构code查询到指定级别的机构树
	 * @param rootOrgCode根机构(若为空则默认为总公司)
	 * @param minLevel 查询到哪一级机构（若minLevel<1则不限制）
	 * @param onlyOrgFlag 是否只查询机构
	 * @return
	 * @throws DaoException
	 */
	public List<VcLevel> queryOrgZTreeList(String rootOrgCode, int minLevel, String onlyOrgFlag)throws DaoException;
	
	/**
	 * 根据根机构id查询到指定级别的机构树，结果包含根节点(用递归查询)
	 * 
	 * @param rootOrgId根机构Id （若<1则默认为总公司）
	 * @param minLevel
	 *            查询到哪一级机构（若<1则不限制）
	 * @return
	 * @throws DaoException
	 * @author whjwanghuajian
	 */
	public List<VcLevel> queryOrgZTreeListByRootOrgId(Long rootOrgId, int minLevel) throws DaoException;
	
	/**
	 * 根据根机构id查询到指定级别的机构树，结果包含根节点(用递归查询)
	 * @param rootOrgId 根机构Id （若<1则默认为总公司）
	 * @param minLevel
	 * 			查询到哪一级机构（若<1则不限制）
	 * @param onlyOrgFlag	是否只查询机构
	 * @return
	 * @throws DaoException
	 */
	public List<VcLevel> queryOrgZTreeListByRootOrgId(Long rootOrgId, int minLevel, String onlyOrgFlag) throws DaoException;
	
	/**
     * 根据根机构/人员 code  查询其对应级别的上级机构(用递归start with查询)
     * 
     * @param unitCode 机构/人员code    
     * @param minLevel
     *            查询到1~~minLevel级别间的上级机构（若<1则不限制）
     * @return
     * @throws DaoException
     * @author whjwanghuajian
     * @since 2013-06-20 18：44
     */
    public List<VcLevel> queryUpOrgListByUnitCode(String unitCode, int minLevel) throws DaoException ;
	
	/**
	 * 根据级别代码获得级别名称
	 * @param unitCode
	 * @return
	 */
	public String getUnitNameByUnitCode(String unitCode) throws DaoException;
	
	/**
	 * 用户选择查询
	 * @param userVo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryUsersForSelector(UserVo userVo, int pageNo, int pageSize) throws DaoException;

	/**
	 * 查找公共机构表
	 * @param comAttribute
	 * @param upperComCode
	 * @param comCode
	 * @return
	 * @throws DaoException
	 * @author chy
	 */
	public List<PubCompany> getPubCompanyList(String comAttribute, String upperComCode, String comCode) throws DaoException;
	
	/**
	 * 根据归属机构获取用户信息
	 * @param comCode
	 * @param userCode
	 * @return
	 * @throws DaoException
	 */
	public List<PubUserDef> getUserList(String comCode, String userCode) throws DaoException;
	
	/**
	 * 分页查询中介机构
	 * @param orgCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 * @author chy
	 */
	public Page queryVcAgencyOrgPage(String orgCode, int pageNo, int pageSize) throws DaoException;
	
	/**
	 * 根据中介机构代码查找中介机构信息
	 * @param agencyOrgCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcAgencyOrg> findVcAgencyOrgByCode(String agencyOrgCode) throws DaoException;
	
	/**
	 * 保存中介机构
	 * @param vcAgencyOrg
	 * @throws DaoException
	 */
	public void saveVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws DaoException ;
	
	/**
	 * 根据主键查找中介机构
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public VcAgencyOrg findVcAgencyOrgById(Long id) throws DaoException;
	
	/**
	 * 修改中介机构
	 * @param vcAgencyOrg
	 * @throws DaoException
	 */
	public void updateVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws DaoException ;
	
	/**
	 * 删除中介机构
	 * @param id
	 * @throws DaoException
	 */
	public void deleteVcAgencyOrg(Long id) throws DaoException;
	
	/**
	 * 分页查询使用人
	 * @param orgCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page queryVcTakerPage(String orgCode, int pageNo, int pageSize) throws DaoException;
	
	/**
	 * 根据归属机构查询中介机构列表
	 * @param comCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcAgencyOrg> getVcAgencyOrgList(String comCode) throws DaoException;
	
	/**
	 * 根据使用人代码查找使用人信息
	 * @param takerCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcTaker> findVcTakerByCode(String takerCode) throws DaoException;
	
	/**
	 * 根据用户代码、机构代码查找用户表
	 * @param userCode
	 * @param comCode
	 * @return
	 * @throws DaoException
	 */
	public List<PubUserDef> findUmUserDef(String userCode, String comCode) throws DaoException;
	
	/**
	 * 保存使用人
	 * @param vcTaker
	 * @throws DaoException
	 */
	public void saveVcTaker(VcTaker vcTaker) throws DaoException;
	
	/**
	 * 根据ID查找使用人信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public VcTaker findVcTakerById(Long id) throws DaoException;
	
	/**
	 * 更新使用人信息
	 * @param vcTaker
	 * @throws DaoException
	 */
	public void updateVcTaker(VcTaker vcTaker) throws DaoException;
	
	/**
	 * 删除使用人
	 * @param id
	 * @throws DaoException
	 */
	public void deleteVcTaker(Long id) throws DaoException;
	
	/**
     * 根据机构代码在pub_Company中查找对应的机构
     * 
     * @param companyCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Nov 1, 2013
     */
    public PubCompany findPubCompanyByCode(String companyCode) throws DaoException;
    
    /**
     * 根据员工代码在pub_user_def中查找对应的人员
     * @param userCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Nov 4, 2013
     */
     public PubUserDef findPubUserDefByCode(String userCode) throws DaoException;
     
     /**
      * 根据员工代码在pub_user_def中查找对应的人员名称
      * @param userCode
      * @return
      * @throws DaoException
      *@author whj
      *@since Nov 4, 2013
      */
      public String getPubUseDefNameByCode(String userCode) throws DaoException;
      
    /**
  	 * 查询使用人列表
  	 * @param comCode
  	 * @param takerCode
  	 * @return
  	 * @throws DaoException
  	 */
  	public List<PubUserDef> getTakerList(String comCode, String takerCode) throws DaoException;
  	
  	/**
  	 * 根据用户名
  	 * @param takerCode
  	 * @param takerName
  	 * @return
  	 * @throws DaoException
  	 */
  	public PubUserDef findVcTakerInfo(String takerCode, String takerName) throws DaoException;
  	
  
    /**
     * 变更机构前校验前
     * @param objectCode 机构人员code
     * @param objectFlag 类型  1-机构 2-岗位人员  3-单证使用人
     * @return map  [hasError:boolean, msg:String]
     * @author whj
     * @date 2013-12-11
     */  
    public Map<String, Object> checkBeforeChangeOrg(String objectCode, String objectFlag) throws DaoException;
}
