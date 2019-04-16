package com.tapi.tcs.vc.oauth.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.StringUtil;
import com.tapi.tcs.vc.oauth.dao.OAuthDao;
import com.tapi.tcs.vc.oauth.service.OAuthService;
import com.tapi.tcs.vc.oauth.vo.OAuthCompany;
import com.tapi.tcs.vc.oauth.vo.OAuthResponse;
import com.tapi.tcs.vc.oauth.vo.OAuthRole;
import com.tapi.tcs.vc.oauth.vo.OAuthUserRole;
import com.tapi.tcs.vc.oauth.vo.UserRole;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.schema.model.VcPubCode;
import com.tapi.tcs.vc.schema.model.VcRoleDef;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.schema.model.VcUserRole;

public class OAuthServiceImpl implements OAuthService {

	protected final Logger logger = Logger.getLogger(getClass());
	private OAuthDao oAuthDao;
	private VcLevelDao vcLevelDao;
	 private VcPubCodeManagerDao vcPubCodeManagerDao;
	@Override
	public OAuthResponse saveRole(OAuthRole role) throws Exception {
		if (StringUtils.isEmpty(role.getRoleCode()) || StringUtils.isEmpty(role.getRoleName())
				|| StringUtils.isEmpty(role.getState())) {
			throw new DaoException("角色信息不能为空");
		}
		boolean isAdd = false;// 是否新增
		VcRoleDef roleDef = oAuthDao.queryRole(role.getRoleCode());
		if (roleDef == null) {
			isAdd = true;
			roleDef = new VcRoleDef();
		}
		Date nowdate = new Date();
		roleDef.setRoleId(Long.valueOf(role.getRoleCode()));
		roleDef.setRoleName(role.getRoleName());
		roleDef.setRoleDesc(role.getRoleName());
		roleDef.setValidFlag(role.getState());
		roleDef.setDateUpdated(nowdate);
		if (isAdd) {
			// 新增
			// roleDef.setRoleLevel("1");
			roleDef.setUpdateBy("oAuth_add");
			roleDef.setCreatedBy("oAuth_add");
			roleDef.setDateCreated(nowdate);
			oAuthDao.saveVcRoleDef(roleDef);
		} else {
			// 修改
			roleDef.setUpdateBy("oAuth_updated");
			oAuthDao.updateVcRoleDef(roleDef);
		}
		return null;
	}

	@Override
	public OAuthResponse saveUserRole(String value1,List<UserRole> userRoleList,String value2) throws Exception {
		if (StringUtils.isEmpty(value1) || userRoleList.size()<0) {
			throw new DaoException("用户信息不能为空");
		}
		Date nowdate = new Date();
		String flag=null;
		VcPubCode roleCode=vcPubCodeManagerDao.getVcPubCode("QueryRoleCode", "QueryRoleCode");
		if(StringUtils.isNotEmpty(roleCode.getCodeEName())){
			flag=roleCode.getCodeEName();
		}
		PubUserDef userDef = vcLevelDao.findPubUserDefByCode(value1);
		if(userDef!=null){
			List<VcUserRole> listVcUserRole = oAuthDao.queryUserRole(value1);
			if(listVcUserRole!=null && listVcUserRole.size()>0){
				for (int i = 0; i < listVcUserRole.size(); i++) {
					VcUserRole vc=listVcUserRole.get(i);
					vc.setValidFlag("0");
					oAuthDao.saveVcUserRole(vc);
				}
			}
		}else{
			throw new DaoException("此用户不存在");
		}
		for (int k = 0; k < userRoleList.size(); k++) {
			UserRole userRole=userRoleList.get(k);
			if(flag.indexOf(userRole.getRoleCode())!=-1){
				VcTaker vcTaker = new VcTaker();
				List<VcTaker> vcTakerList = vcLevelDao.findVcTakerByCode(value1);
				if (!CollectionUtils.isEmpty(vcTakerList)) {
					vcTaker = vcTakerList.get(0);
				}
					vcTaker.setTakerCode(userDef.getEmployeeId());
					vcTaker.setTakerName(userDef.getUserName());
					vcTaker.setStatus("1");
					vcTaker.setOrgCode(userDef.getCompanyCode());
					vcTaker.setTakerTypeCode("3");
					vcTaker.setDateUpdated(nowdate);
					if (vcTaker.getId() != null) {
						vcTaker.setUpdatedBy("oAuth_updated");
						vcLevelDao.updateVcTaker(vcTaker);
					} else {
						vcTaker.setUpdatedBy("oAuth_add");
						vcTaker.setCreatedBy("oAuth_add");
						vcTaker.setDateCreated(nowdate);
						vcLevelDao.saveVcTaker(vcTaker);
					}
			
			}
			VcUserRole vcUserRole = new VcUserRole();
			vcUserRole.setEmployeeId(value1);
			vcUserRole.setRoleId(userRole.getRoleCode());
			vcUserRole.setValidFlag("1");
			vcUserRole.setUpdateBy("oAuth_add");
			vcUserRole.setDateUpdated(nowdate);
			vcUserRole.setCreatedBy("oAuth_add");
			vcUserRole.setDateCreated(nowdate);
			oAuthDao.saveVcUserRole(vcUserRole);
		}
		return null;
	}

	@Override
	public OAuthResponse saveCompany(OAuthCompany company) throws Exception {
		VcLevel parentVcLevel = null;
		if (StringUtils.isEmpty(company.getCompanyCode()) || StringUtils.isEmpty(company.getUpperCompanyCode())
				|| (parentVcLevel = vcLevelDao.getVcLevel("0", company.getUpperCompanyCode())) == null) {
			throw new Exception("机构同步入参为空或上级机构不存在");
		}
		VcLevel vcLevel = vcLevelDao.getVcLevel("0", company.getCompanyCode());
		if (vcLevel == null) {
			vcLevel = new VcLevel();
		}
		// 级别单位类型：0-机构，1-员工
		vcLevel.setUnitType("0");
		vcLevel.setUnitCode(company.getCompanyCode());
		vcLevel.setUnitName(company.getCompanyCname());
		// ( 根据父级机构查询出来父级机构id）
		vcLevel.setParentOrgId(parentVcLevel.getId());
		vcLevel.setParentOrgCode(company.getUpperCompanyCode());
		vcLevel.setComType("O".equals(company.getComAttribute()) ? "0" : "1");
		vcLevel.setLevelNo(parentVcLevel.getLevelNo() + 1);
		vcLevel.setManageLevel(vcLevel.getLevelNo() + 1);
		vcLevel.setValidStatus(company.getValidInd() + "");
		// 查询出（ 同一个机构下最大序号+1）
		Integer maxDisplayNo = vcLevelDao.getMaxDisplayNoFromLevel(company.getUpperCompanyCode());
		vcLevel.setDisplayNo(maxDisplayNo + 1);
		vcLevel.setCreatedBy(StringUtils.isEmpty(company.getCreatorCode())? "oAuth_add" :company.getCreatorCode());
		vcLevel.setDateCreated(DateUtils.parse(company.getCreateTime(), "yyyy-MM-dd"));
		vcLevel.setUpdatedBy(StringUtils.isEmpty(company.getUpdaterCode())? "oAuth_updater" :company.getUpdaterCode());
		vcLevel.setDateUpdated(DateUtils.parse(company.getUpdateTime(), "yyyy-MM-dd"));
		if (vcLevel.getId() == null) {
			vcLevelDao.saveVcLevel(vcLevel);
		} else {
			vcLevelDao.updateVcLevel(vcLevel);
		}
		return null;
	}

	public OAuthDao getoAuthDao() {
		return oAuthDao;
	}

	public void setoAuthDao(OAuthDao oAuthDao) {
		this.oAuthDao = oAuthDao;
	}

	public VcLevelDao getVcLevelDao() {
		return vcLevelDao;
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}

	public VcPubCodeManagerDao getVcPubCodeManagerDao() {
		return vcPubCodeManagerDao;
	}

	public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
		this.vcPubCodeManagerDao = vcPubCodeManagerDao;
	}
	
}
