package com.tapi.tcs.vc.menu.web;

import java.util.List;
import org.apache.log4j.Logger;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.vc.menu.service.MenuService;

/**
 * 
 * @author lianchao
 * @date 2013-02-19
 */
public class MenuAction extends TFAction{

	private static final long serialVersionUID = -2248031194541049469L;

	protected final Logger logger = Logger.getLogger(getClass());

	private MenuService menuService;
	private List menuList;

	/*
	 * 查询主菜单信息
	 * 
	 * @author lianchao
	 * 
	 * @date 2013-02-19
	 */
	public String queryMainMenu() {
		logger.info("访问/subfunc/queryMainMenu.do?ajax=true查询主菜单信息...");
		
		menuList = menuService.queryMainMenuList("-1", "N");
		
		return "mainMenuList";
	}
	
	/*
	 * 查询所有子菜单信息
	 * 
	 * @author lianchao
	 * 
	 * @date 2013-02-19
	 */
	public String queryAllChildMenu() {
		logger.info("访问/index.do?ajax=true查询所有子菜单信息...");
		
		menuList = menuService.queryMainMenuList("", "");
		return SUCCESS;
	}



	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}


}