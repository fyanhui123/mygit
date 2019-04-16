/**
 * 
 */
package com.tapi.tcs.vc.subfunc.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.User;

/**
 * @author Lincoln
 * 
 */
public interface UserService {

	/**
	 * 通过用户名来查找用户对象
	 * 
	 * @param userName
	 *            用户名
	 * @return 用户对象
	 * @throws BusinessException
	 */
	public User getUserByName(String userName) throws BusinessException;

}
