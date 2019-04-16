/**
 * 
 */
package com.tapi.tcs.vc.subfunc.service.impl;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.User;
import com.tapi.tcs.vc.subfunc.dao.UserDao;
import com.tapi.tcs.vc.subfunc.service.UserService;

/**
 * @author Lincoln
 * 
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @see com.tapi.tcs.vc.subfunc.service.UserService#getUserByName(java.lang.String)
	 */
	public User getUserByName(String userName) throws BusinessException {
		if (userName == null) {
			BusinessException be = new BusinessException("用户名不能为空！");
			be.setModule("用户_通过用户名来查找用户对象");
			throw be;
		}

		return this.userDao.getUserByName(userName);
	}

}
