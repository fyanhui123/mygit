package com.tapi.tcs.vc.menu.dao;


import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.vc.schema.model.Menu;


/**
 * 菜单Dao
 * @author Lincoln
 * @date lianchao
 */
public interface MenuDao extends GenericDao<Menu>{

	public void createMenu(Menu menu);
	
	/**
	 * 查询菜单信息
	 *
	 * @return List<Menu>
	 */
	public List<Menu> queryMainMenuList(String parentNode, String isChild);

}
