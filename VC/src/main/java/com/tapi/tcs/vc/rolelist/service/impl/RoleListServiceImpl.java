package com.tapi.tcs.vc.rolelist.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.rolelist.dao.RoleListDao;
import com.tapi.tcs.vc.rolelist.service.RolelistService;
import com.tapi.tcs.vc.rolelist.vo.RoleListVo;
public class RoleListServiceImpl implements RolelistService{
	private RoleListDao roleListDao;
	@Override
	public Page queryRoleList(RoleListVo roleListVo, UserInfo userInfo, int pageNo,
			int pageSize) throws DaoException {
		if(StringUtils.isNotEmpty(roleListVo.getUserType())&&roleListVo.getUserType().equals("0")){
			Page page = (Page)roleListDao.queryRoleList(roleListVo, userInfo, pageNo, pageSize,"1");
			if (page != null) {
				return page;
			}else{
				return null;
			}
		}else{
			Page page = (Page)roleListDao.queryRoleTakerList(roleListVo, userInfo, pageNo, pageSize,"1");
			if(null!=page){
				return page;
			}else{
				return null;
			}
		}
		
	}
	@Override
	public List<RoleListVo> findRoleListInquiryList(RoleListVo roleListVo,UserInfo userInfo
			) throws BusinessException {
		List<RoleListVo> result = new ArrayList<RoleListVo>();
		try {
			if(StringUtils.isNotEmpty(roleListVo.getUserType())&&roleListVo.getUserType().equals("0")){
				result = (List<RoleListVo>)roleListDao.queryRoleList(roleListVo, userInfo, 0, 0, "2");
				if(null!=result){
					return result;
				}else{
					return null;
				}
			}else{
				result = (List<RoleListVo>)roleListDao.queryRoleTakerList(roleListVo, userInfo, 0, 0, "2");
				if(null!=result){
					return result;
				}else{
					return null;
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
		
	}
	public RoleListDao getRoleListDao() {
		return roleListDao;
	}

	public void setRoleListDao(RoleListDao roleListDao) {
		this.roleListDao = roleListDao;
	}
	
}
