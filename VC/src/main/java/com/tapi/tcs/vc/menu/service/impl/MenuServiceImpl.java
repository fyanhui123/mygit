/**
 * 
 */
package com.tapi.tcs.vc.menu.service.impl;

import java.util.List;

import com.tapi.tcs.vc.menu.dao.MenuDao;
import com.tapi.tcs.vc.menu.service.MenuService;
import com.tapi.tcs.vc.schema.model.Menu;

/**
 * @author Lincoln
 * 
 */
public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao;


	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}


	public List<Menu> queryMainMenuList(String parentNode, String isChild){
		return this.menuDao.queryMainMenuList(parentNode, isChild);
	}

}
