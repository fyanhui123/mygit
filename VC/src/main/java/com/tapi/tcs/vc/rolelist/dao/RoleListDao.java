package com.tapi.tcs.vc.rolelist.dao;



import com.tapi.tcs.tf.base.api.dao.GenericDao;


import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.rolelist.vo.RoleListVo;
import com.tapi.tcs.vc.schema.model.PubUserDef;
public interface RoleListDao extends GenericDao<PubUserDef>{
		//fyh  根据条件查询d单证管理员信息
	public Object queryRoleList(RoleListVo RoleListVo, UserInfo userInfo, int pageNo,
            int pageSize,String queryType) throws DaoException;
	//根据条件查询单证使用人信息
	public Object queryRoleTakerList(RoleListVo RoleListVo, UserInfo userInfo, int pageNo,
            int pageSize,String queryType) throws DaoException;
}
