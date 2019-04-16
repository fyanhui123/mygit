package com.tapi.tcs.vc.baseinfo.service;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.vo.DTreeDto;
import com.tapi.tcs.vc.baseinfo.vo.UserVo;
import com.tapi.tcs.vc.baseinfo.vo.ZTreeDto;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcAgencyOrg;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcTaker;

public interface VcLevelService {
	/**
	 * 根据用户id查询是否已经存在
	 * @param unitCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkUser(String unitCode,String invoiceFlag) throws BusinessException;
	public List<DTreeDto> queryOrgTreeList() throws BusinessException;

	/**
	 * 根据根机构code查询到指定级别的机构树
	 * 
	 * @param rootOrgCode根机构
	 * @param minLevel
	 *            查询到哪一级机构
	 * @return
	 * @throws BusinessException
	 * @author whjwanghuajian
	 */
	public List<ZTreeDto> queryOrgZTreeList(String rootOrgCode, int minLevel, String onlyOrgFlag) throws BusinessException;

	/** 分页查询级别单位设置 */
	public Page queryVcLevelPage(String parentOrgId, String unitType, int pageNo, int pageSize) throws BusinessException;

	/** 根据主键查找 */
	public VcLevel findVcLevelByPK(Long id) throws BusinessException;
	
	/**
	 * 根据主键查找，不管状态是否有效
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public VcLevel findVcLevel(Long id) throws BusinessException;

	/** 保存级别设置 */
	public void saveVcLevel(VcLevel vcLevel) throws BusinessException;
	
	/**
	 * 保存中介机构
	 * @param vcAgencyOrg
	 * @throws BusinessException
	 */
	public void saveVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws BusinessException;

	/** 校验级别代码是否重复 */
	public boolean checkUnitCode(String unitCode) throws BusinessException;
	
	/**
	 * 检查中介机构代码是否重复
	 * @param agencyOrgCode
	 * @return
	 * @throws BusinessException
	 * @author chy
	 */
	public boolean checkAgencyOrgCode(String agencyOrgCode) throws BusinessException;

	/** 修改级别设置 */
	public void updateVcLevel(VcLevel vcLevel) throws BusinessException;

	/** 根据主键删除 */
	public void deleteVcLevelByPK(Long id) throws BusinessException;

	/** 校验是否存在下级机构或人员 */
	public boolean checkChildCompany(Long id) throws BusinessException;

	/**
	 * 根据员工代码在pub_user_def 中查找对应的人员_wcl
	 */
	public PubUserDef findPubUserDefByCode(String userCode)throws BusinessException;

	/**
	 * @Description：获取用户归属机构代码
	 * @Remark：用户保存数据时存
	 */
	public String getComCode(String userCode) throws BusinessException;

	/**
	 * @Description：查询使用人机构代码
	 */
	public String getTakerComCode(String userCode) throws BusinessException ;
	
	/**
	 * @Description：获取上级机构
	 * @Remark：用于提交审批
	 */
	public String getUpperOrgCode(String userCode) throws BusinessException;

	/**
	 * @Description：获取下级机构机构
	 */
	public List<String> getChildOrgCode(String comCode) throws BusinessException;

	/**
	 * 根据类型及code查询
	 * 
	 * @param unitType
	 * @param unitCode
	 * @return
	 * @throws BusinessException
	 */
	public VcLevel getVcLevel(String unitType, String unitCode) throws BusinessException;

	/**
	 * 根据机构代码获得机构名称
	 * 
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 */
	public String getComName(String comCode) throws BusinessException;

	/**
	 * 获取名称
	 * 
	 * @param unitCode
	 *            代码
	 * @return 名称
	 * @throws BusinessException
	 *             异常
	 */
	public String getUnitNameByUnitCode(String unitCode) throws BusinessException;

	public Page queryUsersForSelector(UserVo userVo, int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 查找公共机构表
	 * @param comAttribute
	 * @param upperComCode
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 * @author chy
	 */
	public List<PubCompany> getPubCompanyList(String comAttribute, String upperComCode, String comCode) throws BusinessException;
	
	/**
	 * 根据归属机构获取用户信息
	 * @param comCode
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public List<PubUserDef> getUserList(String comCode, String userCode) throws BusinessException;
	
	/**
	 * 分页查询中介机构
	 * @param orgCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 * @author chy
	 */
	public Page queryVcAgencyOrgPage(String orgCode, int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 获取销售渠道列表
	 * @param codeType
	 * @return
	 * @throws BusinessException
	 * @author chy
	 */
	public List<VcPubCode> getVcPubCodeList(String codeType) throws BusinessException ;
	
	/**
	 * 根据主键查找中介机构
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public VcAgencyOrg findVcAgencyOrgById(Long id) throws BusinessException;
	
	/**
	 * 修改中介机构
	 * @param vcAgencyOrg
	 * @throws BusinessException
	 */
	public void updateVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws BusinessException;
	
	/**
	 * 删除中介机构
	 * @param id
	 * @throws BusinessException
	 */
	public void deleteVcAgencyOrg(Long id) throws BusinessException;
	
	/**
	 * 分页查询使用人
	 * @param orgCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public Page queryVcTakerPage(String orgCode, int pageNo, int pageSize) throws BusinessException;
	
	/**
	 * 根据归属机构查询中介机构列表
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 */
	public List<VcAgencyOrg> getVcAgencyOrgList(String comCode) throws BusinessException;
	
	/**
	 * 检查使用人代码是否存在
	 * @param takerCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkTakerCode(String takerCode) throws BusinessException;
	
	/**
	 * 根据用户代码、机构代码判断该用户在UM_USER_DEF表是否存在
	 * @param userCode
	 * @param comCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkUserCode(String userCode, String comCode) throws BusinessException;
	
	/**
	 * 保存使用人
	 * @param vcTaker
	 * @throws BusinessException
	 */
	public void saveVcTaker(VcTaker vcTaker) throws BusinessException;
	
	/**
	 * 根据ID查找使用人信息
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public VcTaker findVcTakerById(Long id) throws BusinessException;
	
	/**
	 * 修改使用人信息
	 * @param vcTaker
	 * @throws BusinessException
	 */
	public void updateVcTaker(VcTaker vcTaker) throws BusinessException;
	
	/**
	 * 删除使用人
	 * @param id
	 * @throws BusinessException
	 */
	public void deleteVcTaker(Long id) throws BusinessException;
	
	 /**
     * 根据机构代码在pub_Company中查找对应的机构
     * 
     * @param companyCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Nov 1, 2013
     */
    public PubCompany findPubCompanyByCode(String companyCode) throws BusinessException;
    
    /**
	 * 查询使用人列表
	 * @param comCode
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public List<PubUserDef> getTakerList(String comCode, String takerCode) throws BusinessException;
	
	/**
	 * 校验使用人代码和名称是否和公共表匹配
	 * @param takerCode
	 * @param takerName
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkTakerName(String takerCode, String takerName) throws BusinessException;
	
    
    /**
     * 所属机构变更前校验
     * @param id 对象主键
     * @param objectFlag  对象标识 1-机构 2-岗位人员 3-单证使用人
     * @return map  [hasError:boolean, msg:String]
     * @author whj
     * @date 2013-12-11
     */    
    public Map<String,Object> checkBeforeChangeOrg(Long id,String objectFlag)throws BusinessException;
    
    
    /**
     *单证系统岗位人员机构变更
     * @param vcLevel 待变更机构的对象
     * @param 当前用户
     * @return String
     * @exception BusinessException
     * @author whj
     * @date 2013-12-16 
     */
    public String executeRoleOrgChange(VcLevel vcLevel,UserInfo userInfo)throws BusinessException;
    
    /**
     *单证使用人机构变更
     * @param vcTaker 待变更机构的对象
     * @param 当前用户
     * @return String
     * @exception BusinessException
     * @author whj
     * @date 2013-12-16 
     */
    public String executeTakerOrgChange(VcTaker vcTaker,UserInfo userInfo)throws BusinessException;
    
    /**
     * 校验upperOrg是否是curOrg上级的机构或两机构相等
     * 【eg：01、0164均为016432的上级机构】
     */
   public boolean isUpperOrg(String curOrg,String upperOrg)throws BusinessException;
}
