package com.tapi.tcs.vc.subfunc.dao.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.vc.schema.model.User;
import com.tapi.tcs.vc.subfunc.dao.UserDao;

/**
 * @author Lincoln
 * 
 */
public class UserDaoImpl extends GenericDaoHibernate<User> implements UserDao {

	/**
	 * @see com.tapi.tcs.tf.base.api.dao.UserDao#getUserByName(java.lang.String)
	 */
	public User getUserByName(String userName) {
		List<User> users = this.findByHql("from User u where u.username = ?", new Object[] { userName });

		if (users != null && !users.isEmpty()) {
			return users.iterator().next();
		}

		return null;
	}

}
