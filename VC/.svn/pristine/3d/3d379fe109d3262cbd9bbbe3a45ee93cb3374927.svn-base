package com.tapi.tcs.vc.rolelist.service;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.User;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.rolelist.vo.RoleListVo;

public interface RolelistService {
	public Page queryRoleList(RoleListVo pubuserdef, UserInfo userInfo, int pageNo,
            int pageSize) throws DaoException;
	//导出查询
	public List<RoleListVo> findRoleListInquiryList(RoleListVo applyInquiryVo, UserInfo userInfo) throws BusinessException;
	
}
