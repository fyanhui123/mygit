package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelHisDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerDao;
import com.tapi.tcs.vc.baseinfo.dao.VcTakerHisDao;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.baseinfo.vo.DTreeDto;
import com.tapi.tcs.vc.baseinfo.vo.UserVo;
import com.tapi.tcs.vc.baseinfo.vo.VcAgencyOrgVo;
import com.tapi.tcs.vc.baseinfo.vo.VcLevelVo;
import com.tapi.tcs.vc.baseinfo.vo.VcTakerVo;
import com.tapi.tcs.vc.baseinfo.vo.ZTreeDto;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcAgencyOrg;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcLevelHis;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.schema.model.VcTakerHis;

public class VcLevelServiceImpl implements VcLevelService {
	
	private VcLevelDao vcLevelDao;
	private VcTakerDao vcTakerDao;
	private VcLevelHisDao vcLevelHisDao;
	private VcTakerHisDao vcTakerHisDao;
	/**公用代码Dao*/
	private VcPubCodeManagerDao vcPubCodeManagerDao;
	@Override
	public boolean checkUser(String unitCode, String invoiceFlag)
			throws BusinessException {
		boolean flag = false;
		try{
			List<VcLevel> list = vcLevelDao.findByUserCode(unitCode,invoiceFlag);
			if(list!=null && list.size()>0){
				flag = false;
			}else{
				flag = true;
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return flag;
		
	}
	@Override
	public List<DTreeDto> queryOrgTreeList()  throws BusinessException {
		try{
			QueryRule queryRule = QueryRule.getInstance();
			/**级别单位类型--**0=机构;1=员工*/
			queryRule.addEqual("unitType", "0");
			/**状态*/
			queryRule.addEqual("validStatus", "1");
			List<VcLevel> vcLevels = vcLevelDao.find(queryRule);
			List<DTreeDto> dTreeDtoList = new ArrayList<DTreeDto>();
			for (VcLevel vcLevel : vcLevels) {
				DTreeDto dTreeDto=new DTreeDto();
				dTreeDto.setId(vcLevel.getId()+"");
				dTreeDto.setInfo(vcLevel.getUnitName());
				dTreeDto.setPid(vcLevel.getParentOrgId()+"");
				dTreeDto.setValue(vcLevel.getUnitCode());
				
				dTreeDtoList.add(dTreeDto);
			}
			
			return dTreeDtoList;
		}catch(Exception e){
			throw new BusinessException("查询数据出错！", e);
		}
	}
	
	
	/**
	 * 根据根机构code查询到指定级别的机构树
	 * @param rootOrgCode根机构
	 * @param minLevel 查询到哪一级机构
	 * @return
	 * @throws BusinessException
	 * @author whjwanghuajian
	 */
	public List<ZTreeDto> queryOrgZTreeList(String rootOrgCode, int minLevel, String onlyOrgFlag)throws BusinessException{
		/*QueryRule queryRule = QueryRule.getInstance();
		*//**级别单位类型--**0=机构;1=员工*//*
		queryRule.addEqual("unitType", "0");
		*//**状态*//*
		queryRule.addEqual("validStatus", "1");
		//List<VcLevel> vcLevels = vcLevelDao.find(queryRule);
		*/
		List<ZTreeDto> zTreeDtoList = new ArrayList<ZTreeDto>();
		try{
			//List<VcLevel> vcLevels = vcLevelDao.queryOrgZTreeList(rootOrgCode, minLevel);
			List<VcLevel> vcLevels = vcLevelDao.queryOrgZTreeList(rootOrgCode, minLevel, onlyOrgFlag);
			for (VcLevel vcLevel : vcLevels) {
				ZTreeDto zTreeDto=new ZTreeDto();
				zTreeDto.setId(vcLevel.getId()+"");
				zTreeDto.setInfo(vcLevel.getUnitName());
				zTreeDto.setPid(vcLevel.getParentOrgId()+"");
				zTreeDto.setValue(vcLevel.getUnitCode());
				
				if(vcLevel.getLevelNo()==1){
				  //设置根节点图标
				 zTreeDto.setIconSkin("icon_"+vcLevel.getLevelNo());
				//根节点展开
				 zTreeDto.setOpen(true);
				 //zTreeDto.setParent(true);
				}
				zTreeDtoList.add(zTreeDto);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return zTreeDtoList;
	}
	
	/**
	 * 分页查询级别单位设置
	 * @param parentOrgId
	 * @param unitType
	 * @param pageNo
	 * @param pageSize
	 * @return newpage
	 * @throws BusinessException
	 * @author chy
	 * @date 2013-05-06
	 */
	public Page queryVcLevelPage(String parentOrgId, String unitType, int pageNo, int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			Page page = vcLevelDao.queryVcLevelPage(parentOrgId, unitType, pageNo, pageSize);
			List<VcLevel> vcLevelList = (List<VcLevel>)page.getResult();
			List<VcLevelVo> vcLevelVoList = new ArrayList<VcLevelVo>();
			for(VcLevel vcLevel : vcLevelList){
				VcLevelVo vcLevelVo = new VcLevelVo();
				vcLevelVo.setId(vcLevel.getId());
				vcLevelVo.setUnitCode(vcLevel.getUnitCode());
				vcLevelVo.setUnitName(vcLevel.getUnitName());
				vcLevelVo.setDisplayNo(vcLevel.getDisplayNo());
				VcLevel vcLevelTmp = vcLevelDao.findVcLevelByPK(vcLevel.getParentOrgId()); 
				vcLevelVo.setComName(vcLevelTmp.getUnitName());
				String flag=vcLevel.getInvoiceFlag();
				if(null==flag){
					vcLevelVo.setInvoiceFlag("单证管理员");
				}else{
					vcLevelVo.setInvoiceFlag(flag.equals("1")? "发票管理员" : "单证管理员");
				}
				if("1".equals(vcLevel.getValidStatus())){
					vcLevelVo.setValidStatus("有效");
				}else if("0".equals(vcLevel.getValidStatus())){
					vcLevelVo.setValidStatus("无效");
				}
				vcLevelVoList.add(vcLevelVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), vcLevelVoList);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return newpage;
	}
	
	/**
	 * 根据主键查找
	 * @param id
	 * @return VcLevel
	 * @throws Exception
	 * @author chy
	 * @date 2013-05-07
	 */
	public VcLevel findVcLevelByPK(Long id)throws BusinessException {
		VcLevel vcLevel = null;
		try{
			vcLevel= vcLevelDao.findVcLevelByPK(id);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcLevel;
	}
	
	@Override
	public VcLevel findVcLevel(Long id) throws BusinessException {
		VcLevel vcLevel = null;
		try{
			vcLevel= vcLevelDao.findVcLevel(id);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcLevel;
	}
	
	/**
	 * 保存级别设置
	 * @param vcLevel
	 * @return
	 * @throws Exception
	 * @author chy
	 * @date 2013-05-07
	 */
	public void saveVcLevel(VcLevel vcLevel) throws BusinessException {
		try{
			vcLevelDao.saveVcLevel(vcLevel);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public void saveVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws BusinessException {
		try{
			vcLevelDao.saveVcAgencyOrg(vcAgencyOrg);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	
	/**
	 * 校验级别代码是否重复
	 * @param unitCode
	 * @return flag
	 * @throws BusinessException
	 * @author chy
	 * @date 2013-05-07
	 */
	public boolean checkUnitCode(String unitCode) throws BusinessException {
		boolean flag = false;
		try{
			List<VcLevel> list = vcLevelDao.findByUnitCode(unitCode);
			if(list!=null && list.size()>0){
				flag = false;
			}else{
				flag = true;
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return flag;
	}
	
	@Override
	public boolean checkAgencyOrgCode(String agencyOrgCode) throws BusinessException {
		boolean flag = false;
		try{
			List<VcAgencyOrg> list = vcLevelDao.findVcAgencyOrgByCode(agencyOrgCode);
			if(list!=null && list.size()>0){
				flag = false;
			}else{
				flag = true;
			}
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return flag;
	}
	
	/**
	 * 修改级别设置
	 * @param vcLevel
	 * @return
	 * @author chy
	 * @date 2013-05-07
	 */
	public void updateVcLevel(VcLevel vcLevel)throws BusinessException {
		try{
			vcLevelDao.updateVcLevel(vcLevel);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 * @author chy
	 * @date 2013-05-07
	 */
	public void deleteVcLevelByPK(Long id)throws BusinessException {
		try{
			//vcLevelDao.deleteVcLevelByPK(id);
			VcLevel vcLevel = vcLevelDao.findVcLevel(id);
			if(vcLevel==null){
				throw new BusinessException("要注销/恢复的数据不存在，请刷新后重试！");
			}
			if("1".equals(vcLevel.getValidStatus())){
				vcLevel.setValidStatus("0");
			}else if("0".equals(vcLevel.getValidStatus())){
				vcLevel.setValidStatus("1");
			}
			vcLevelDao.updateVcLevel(vcLevel);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * 是否可以注销
	 * @param id
	 * @return flag
	 * @author chy
	 * @date 2013-05-08
	 */
	public boolean checkChildCompany(Long id)throws BusinessException {
		boolean flag = false;
		try{
			/*List<VcLevel> list = vcLevelDao.findChileByParentOrgId(id);
			if(list!=null && list.size()>0){
				flag = true;
			}else{
				flag = false;
			}*/
			flag = vcLevelDao.checkIsDeletable(id);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return flag;
	}
	
	/**
	 * @Description：获取用户归属机构代码
	 * @param userCode
	 * @return comCode
	 * @author chy
	 * @date 2013-05-13
	 */
	public String getComCode(String userCode) throws BusinessException {
		String comCode = "";
		try{
			comCode = vcLevelDao.getComCode(userCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return comCode;
	}
	
	/**
	 * @Description： 查询使用人机构代码
	 * @param userCode
	 * @return comCode
	 * @author chy
	 * @date 2015-07-27
	 */
	public String getTakerComCode(String userCode) throws BusinessException {
		String comCode = "";
		try{
			comCode = vcLevelDao.getTakerComCode(userCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return comCode;
	}
	
	/**
	 * @Description：获取上级机构
	 * @param userCode
	 * @return upperOrgCode
	 * @author chy
	 * @date 2013-05-13
	 */
	public String getUpperOrgCode(String userCode) throws BusinessException {
		String upperOrgCode = "";
		try{
			upperOrgCode = vcLevelDao.getUpperOrgCode(userCode);
			if(StringUtils.isEmpty(upperOrgCode)){
				throw new BusinessException("没有查询到上级机构！");
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return upperOrgCode;
	}
	
	/**
	 * @Description：获取下级机构机构
	 * @param comCode
	 * @return list
	 * @author chy
	 * @date 2013-05-14
	 */
	public List<String> getChildOrgCode(String comCode) throws BusinessException {
		List<String> list = null;
		try{
			list = vcLevelDao.getChildOrgCode(comCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	/**
	 * 根据机构代码获得机构名称
	 * @param comCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getComName(String comCode) throws BusinessException {
		String comName="";
		try{
			comName = vcLevelDao.getUnitNameByUnitCode(comCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return comName;
	}
	
	/**
	 * 根据类型及code查询
	 * @param unitType
	 * @param unitCode
	 * @return
	 * @throws BusinessException
	 */
	public VcLevel getVcLevel(String unitType, String  unitCode) throws BusinessException{
		VcLevel vcLevel = null;
		try{
			vcLevel = vcLevelDao.getVcLevel(unitType, unitCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return vcLevel;
	}
	

	@Override
	public String getUnitNameByUnitCode(String unitCode)
			throws BusinessException {
		String unitName = "";
		try{
			unitName = vcLevelDao.getUnitNameByUnitCode(unitCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return unitName;
	}


	@Override
	public Page queryUsersForSelector(UserVo userVo, int pageNo, int pageSize) throws BusinessException{
		Page page = null;
		try{
			page = vcLevelDao.queryUsersForSelector(userVo, pageNo, pageSize);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return page;
	}
	
	@Override
	public List<PubCompany> getPubCompanyList(String comAttribute, String upperComCode, String comCode) throws BusinessException {
		List<PubCompany> list = null;
		try{
			list = vcLevelDao.getPubCompanyList(comAttribute, upperComCode, comCode);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage());
		}
		return list;
	}
	
	@Override
	public List<PubUserDef> getUserList(String comCode, String userCode) throws BusinessException {
		List<PubUserDef> list = null;
		try{
			list = vcLevelDao.getUserList(comCode, userCode);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return list;
	}
	
	@Override
	public Page queryVcAgencyOrgPage(String orgCode, int pageNo, int pageSize) throws BusinessException {
		Page newPage = null;
		try{
			Page page = vcLevelDao.queryVcAgencyOrgPage(orgCode, pageNo, pageSize);
			List<VcAgencyOrg> vcAgencyOrgList = (List<VcAgencyOrg>)page.getResult();
			List<VcAgencyOrgVo> agencyVoList = new ArrayList<VcAgencyOrgVo>();
			for(VcAgencyOrg vcAgencyOrg : vcAgencyOrgList){
				VcAgencyOrgVo agencyVo = new VcAgencyOrgVo();
				agencyVo.setId(vcAgencyOrg.getId());
				agencyVo.setAgencyOrgCode(vcAgencyOrg.getAgencyOrgCode());
				agencyVo.setAgencyOrgName(vcAgencyOrg.getAgencyOrgName());
				String orgName = this.getComName(vcAgencyOrg.getOrgCode());
				agencyVo.setOrgName(orgName);
				String businessName = vcPubCodeManagerDao.getCodeCname("BusinessCode", vcAgencyOrg.getBusinessCode());
				agencyVo.setBusinessName(businessName);
				String businessDetailName = vcPubCodeManagerDao.getCodeCname("BusinessDetailCode", vcAgencyOrg.getBusinessDetailCode());
				agencyVo.setBusinessDetailName(businessDetailName);
				if("1".equals(vcAgencyOrg.getIsSalesNet())){
					agencyVo.setIsSalesNet("是");
				}else{
					agencyVo.setIsSalesNet("否");
				}
				agencyVo.setDisplayNo(vcAgencyOrg.getDisplayNo());
				if("1".equals(vcAgencyOrg.getStatus())){
					agencyVo.setStatus("有效");
				}else if("0".equals(vcAgencyOrg.getStatus())){
					agencyVo.setStatus("无效");
				}
				agencyVoList.add(agencyVo);
			}
			newPage = new Page(pageNo, pageSize, page.getTotalCount(), agencyVoList);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return newPage;
	}
	
	@Override
	public List<VcPubCode> getVcPubCodeList(String codeType)
			throws BusinessException {
		List<VcPubCode> list = null;
		try{
			list = vcPubCodeManagerDao.getVcPubCodeList(codeType, null);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return list;
	}
	
	@Override
	public VcAgencyOrg findVcAgencyOrgById(Long id) throws BusinessException {
		VcAgencyOrg vcAgencyOrg = null;
		try{
			vcAgencyOrg = vcLevelDao.findVcAgencyOrgById(id);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return vcAgencyOrg;
	}
	
	@Override
	public void updateVcAgencyOrg(VcAgencyOrg vcAgencyOrg) throws BusinessException {
		try{
			vcLevelDao.updateVcAgencyOrg(vcAgencyOrg);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	
	@Override
	public void deleteVcAgencyOrg(Long id) throws BusinessException {
		try{
			//vcLevelDao.deleteVcAgencyOrg(id);
			VcAgencyOrg vcAgencyOrg = vcLevelDao.findVcAgencyOrgById(id);
			if(vcAgencyOrg==null){
				throw new BusinessException("要注销/恢复的数据不存在，请刷新后重试！");
			}
			if("1".equals(vcAgencyOrg.getStatus())){
				vcAgencyOrg.setStatus("0");
			}else if("0".equals(vcAgencyOrg.getStatus())){
				vcAgencyOrg.setStatus("1");
			}
			vcLevelDao.updateVcAgencyOrg(vcAgencyOrg);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	
	@Override
	public Page queryVcTakerPage(String orgCode, int pageNo, int pageSize) throws BusinessException {
		Page newpage = null;
		try{
			Page page = vcLevelDao.queryVcTakerPage(orgCode, pageNo, pageSize);
			List<VcTaker> vcTakerList = (List<VcTaker>)page.getResult();
			List<VcTakerVo> vcTakerVoList = new ArrayList<VcTakerVo>();
			for(VcTaker vcTaker : vcTakerList){
				VcTakerVo vcTakerVo = new VcTakerVo();
				vcTakerVo.setId(vcTaker.getId());
				vcTakerVo.setTakerCode(vcTaker.getTakerCode());//使用人代码
				vcTakerVo.setTakerName(vcTaker.getTakerName());//使用人名称
				//归属机构名称
				String orgName = this.getComName(vcTaker.getOrgCode());
				vcTakerVo.setOrgName(orgName);
				//所属中介机构名称
				String agencyOrgName = "";
				List<VcAgencyOrg> vcAgencyOrgList = vcLevelDao.findVcAgencyOrgByCode(vcTaker.getAgencyOrgCode());
				if(vcAgencyOrgList!=null && vcAgencyOrgList.size()>0){
					agencyOrgName = vcAgencyOrgList.get(0).getAgencyOrgName();
				}
				vcTakerVo.setAgencyOrgName(agencyOrgName);
				//使用人类型
				String takerTypeName = vcPubCodeManagerDao.getCodeCname("TakerType",vcTaker.getTakerTypeCode());
				vcTakerVo.setTakerTypeName(takerTypeName);
				if("1".equals(vcTaker.getStatus())){
					vcTakerVo.setStatus("有效");
				}else if("0".equals(vcTaker.getStatus())){
					vcTakerVo.setStatus("无效");
				}
				vcTakerVoList.add(vcTakerVo);
			}
			newpage = new Page(pageNo, pageSize, page.getTotalCount(), vcTakerVoList);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return newpage;
	}
	
	@Override
	public List<VcAgencyOrg> getVcAgencyOrgList(String comCode) throws BusinessException {
		List<VcAgencyOrg> list = null;
		try{
			list = vcLevelDao.getVcAgencyOrgList(comCode);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return list;
	}
	
	@Override
	public boolean checkTakerCode(String takerCode) throws BusinessException{
		boolean flag = false;
		try{
			List<VcTaker> list = vcLevelDao.findVcTakerByCode(takerCode);
			if(list!=null && list.size()>0){
				flag = false;
			}else{
				flag = true;
			}
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return flag;
	}
	
	@Override
	public boolean checkUserCode(String userCode, String comCode) throws BusinessException {
		boolean flag = false;
		try{
			List<PubUserDef> list = vcLevelDao.findUmUserDef(userCode, comCode);
			//找不到记录，则返回false
			if(list==null || list.size()<1){
				flag = false;
			}else{
				flag = true;
			}
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return flag;
	}
	
	@Override
	public void saveVcTaker(VcTaker vcTaker) throws BusinessException {
		try{
			vcLevelDao.saveVcTaker(vcTaker);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	
	@Override
	public VcTaker findVcTakerById(Long id) throws BusinessException {
		VcTaker vcTaker = null;
		try{
			vcTaker = vcLevelDao.findVcTakerById(id);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return vcTaker;
	}
	
	@Override
	public void updateVcTaker(VcTaker vcTaker) throws BusinessException {
		try{
			vcLevelDao.updateVcTaker(vcTaker);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	
	@Override
	public void deleteVcTaker(Long id) throws BusinessException {
		try{
			//vcLevelDao.deleteVcTaker(id);
			VcTaker vcTaker = vcLevelDao.findVcTakerById(id);
			if(vcTaker==null){
				throw new BusinessException("要注销/恢复的数据不存在，请刷新后重试！");
			}
			if("1".equals(vcTaker.getStatus())){
				vcTaker.setStatus("0");
			}else if("0".equals(vcTaker.getStatus())){
				vcTaker.setStatus("1");
			}
			vcLevelDao.updateVcTaker(vcTaker);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
	}
	/**
	 * 根据员工代码在pub_user_def中查找对应的人员_wcl
	 */
	public PubUserDef findPubUserDefByCode(String userCode)throws BusinessException {
		try {
			return vcLevelDao.findPubUserDefByCode(userCode);
			
		} catch (DaoException de) {
			throw new BusinessException(de.getMessage(), de);
		}
	}
    /**
     * 根据机构代码在pub_Company中查找对应的机构
     * 
     * @param companyCode
     * @return
     * @throws DaoException
     *@author whj
     *@since Nov 1, 2013
     */
    public PubCompany findPubCompanyByCode(String companyCode) throws BusinessException {
        try {
            return vcLevelDao.findPubCompanyByCode(companyCode);
        } catch (DaoException de) {
            throw new BusinessException(de.getMessage(), de);
        }
    }
    
    @Override
    public List<PubUserDef> getTakerList(String comCode, String takerCode) throws BusinessException{
		List<PubUserDef> list = null;
		try{
			list = vcLevelDao.getTakerList(comCode, takerCode);
		}catch(DaoException de){
			throw new BusinessException(de.getMessage(), de);
		}
		return list;
	}
    
    @Override
    public boolean checkTakerName(String takerCode, String takerName) throws BusinessException {
    	boolean flag = false;
    	PubUserDef pubUserDef = vcLevelDao.findVcTakerInfo(takerCode, takerName);
    	if(pubUserDef != null){
    		flag = true;
    	}
    	return flag;
    }
   
    /**
     * 所属机构变更前校验
     * @param id 对象主键
     * @param objectFlag  对象标识 1-机构 2-岗位人员 3-单证使用人
     * @return map  [hasError:boolean, msg:String]
     * @author whj
     * @date 2013-12-11
     */
    @Override
    public Map<String,Object> checkBeforeChangeOrg(Long id,String objectFlag)throws BusinessException { 
        try{
           if("3".equals(objectFlag)){
               VcTaker vcTaker = vcLevelDao.findVcTakerById(id);
               if(vcTaker==null){
                   throw new BusinessException("待删除的数据不存在，请刷新后重试！");
               }               
             return   vcLevelDao.checkBeforeChangeOrg(vcTaker.getTakerCode(),"3");
           }else{
               VcLevel vcLevel = vcLevelDao.findVcLevel(id);
               if(vcLevel==null){
                   throw new BusinessException("待删除的数据不存在，请刷新后重试！");
               }
               if("1".equals(vcLevel.getUnitType())){
                   //岗位人员校验
                   return   vcLevelDao.checkBeforeChangeOrg(vcLevel.getUnitCode(),"2");                              
               }else{//0-jigou
                  //机构校验
                   return  vcLevelDao.checkBeforeChangeOrg(vcLevel.getUnitCode(),"1");
               }
           }
        }catch(DaoException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }
    
    
    /**
     *单证系统岗位人员机构变更
     * @param vcLevel 待变更机构的对象
     * @param 当前用户
     * @return String
     * @exception BusinessException
     * @author whj
     * @date 2013-12-16 
     */
    public String executeRoleOrgChange(VcLevel vcLevel,UserInfo userInfo)throws BusinessException{
        try{
            ///变更前的对象
            VcLevel origVcLevel = vcLevelDao.findVcLevel(vcLevel.getId());
            if(origVcLevel==null){
                throw new BusinessException("当前机构不存在，请刷新后重试！");
            }
            Map<String,Object> chackMap=null;
            if("1".equals(origVcLevel.getUnitType())){
                //岗位人员校验，校验不通过则抛出异常
                chackMap = vcLevelDao.checkBeforeChangeOrg(origVcLevel.getUnitCode(),"2");
                           
            }else{//0-jigou
               //机构校验，校验不通过则抛出异常
                chackMap = vcLevelDao.checkBeforeChangeOrg(origVcLevel.getUnitCode(),"1");
            }
            if((Boolean)chackMap.get("hasError")){
                throw new BusinessException((String)chackMap.get("msg"));   
            }
            Date nowDate=new Date();
           //变更前的记录放入历史痕迹表=======================================================
            VcLevelHis vcLevelHis=new VcLevelHis();
            BeanUtils.copyProperties(origVcLevel, vcLevelHis); 
            vcLevelHis.setDateCreated(nowDate);  
            vcLevelHis.setCreatedBy(userInfo.getUserCode());
            vcLevelHisDao.saveVcLevelHis(vcLevelHis); 
            
            //生成变更机构后的记录==============================================================
            VcLevel newVcLevel=new VcLevel();            
            BeanUtils.copyProperties(origVcLevel, newVcLevel); 
            newVcLevel.setId(null);
            //新上级机构
             VcLevel newParentObj= vcLevelDao.getVcLevel("0", vcLevel.getNewParentOrgCode());
             if(newParentObj==null){
                 throw new BusinessException("新机构不存在，请先配置！");  
             }             
            newVcLevel.setParentOrgId(newParentObj.getId());
            newVcLevel.setParentOrgCode(newParentObj.getUnitCode());
            newVcLevel.setDateCreated(nowDate);
            newVcLevel.setDateUpdated(nowDate);
            newVcLevel.setCreatedBy(userInfo.getUserCode());
            newVcLevel.setUpdatedBy(userInfo.getUserCode());
            
          //删除原始记录==============================================================
            vcLevelDao.delete(origVcLevel);
            
            vcLevelDao.saveVcLevel(newVcLevel);
            
          
        }catch(DaoException e){
            throw new BusinessException(e.getMessage(), e);
        }
        return "Success";
        
    }
    
    /**
     *单证使用人机构变更
     * @param vcTaker 待变更机构的对象
     * @param 当前用户
     * @return String
     * @exception BusinessException
     * @author whj
     * @date 2013-12-16 
     */
    public String executeTakerOrgChange(VcTaker vcTaker,UserInfo userInfo)throws BusinessException{        
        try{
            //vcLevelDao.deleteVcTaker(id);
            VcTaker origVcTaker = vcLevelDao.findVcTakerById(vcTaker.getId());
            if(origVcTaker==null){
                throw new BusinessException("当前单证使用人不存在，请刷新后重试！");
            }
            //校验不通过则抛出异常
           Map<String,Object> chackMap= vcLevelDao.checkBeforeChangeOrg(origVcTaker.getTakerCode(),"3");
           if((Boolean)chackMap.get("hasError")){
               throw new BusinessException((String)chackMap.get("msg"));   
           }
            
            Date nowDate=new Date();
          //变更前的记录放入历史痕迹表=======================================================
            VcTakerHis vcTakerHis=new VcTakerHis();
            BeanUtils.copyProperties(origVcTaker, vcTakerHis);            
            vcTakerHis.setDateCreated(nowDate);
            vcTakerHis.setCreatedBy(userInfo.getUserCode());
            vcTakerHisDao.saveVcTakerHis(vcTakerHis);
            
          //生成变更机构后的记录==============================================================
            VcTaker newVcTaker=new VcTaker();
            BeanUtils.copyProperties(origVcTaker, newVcTaker);
            newVcTaker.setId(null);
            //新上级机构
            VcLevel newParentObj= vcLevelDao.getVcLevel("0", vcTaker.getNewOrgCode());
            if(newParentObj==null){
                throw new BusinessException("新机构不存在，请先配置！");  
            } 
            newVcTaker.setOrgCode(newParentObj.getUnitCode());
            newVcTaker.setDateCreated(nowDate);
            newVcTaker.setDateUpdated(nowDate);
            newVcTaker.setCreatedBy(userInfo.getUserCode());
            newVcTaker.setUpdatedBy(userInfo.getUserCode());
            
            //删除原始记录==============================================================
            vcTakerDao.delete(origVcTaker);
            
            vcTakerDao.save(newVcTaker);
        }catch(DaoException de){
            throw new BusinessException(de.getMessage(), de);
        }
        return "Success"; 
   }
	
    
    /**
     * 校验upperOrg是否是curOrg上级的机构或两机构相等
     * 【eg：01、0164均为016432的上级机构】
     */
   public boolean isUpperOrg(String curOrg,String upperOrg)throws BusinessException{
       boolean isUpper=false;
       if(curOrg.equals(upperOrg)){
           isUpper= true;
       }else{
           ///查询所有上级机构          
           List<VcLevel> orgList = vcLevelDao.queryUpOrgListByUnitCode(curOrg,7);
           if(orgList!=null && orgList.size()>0){
               for(VcLevel obj:orgList){
                   if(upperOrg.equals(obj.getUnitCode())){
                       isUpper=true;
                       break;
                   }                   
               }
           }
       }
       return isUpper;
   }
   
   
   
	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}    
    public void setVcTakerDao(VcTakerDao vcTakerDao) {
        this.vcTakerDao = vcTakerDao;
    }


    public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
		this.vcPubCodeManagerDao = vcPubCodeManagerDao;
	}	
   
    public void setVcTakerHisDao(VcTakerHisDao vcTakerHisDao) {
        this.vcTakerHisDao = vcTakerHisDao;
    }


    public void setVcLevelHisDao(VcLevelHisDao vcLevelHisDao) {
        this.vcLevelHisDao = vcLevelHisDao;
    }


	
	
	
}
