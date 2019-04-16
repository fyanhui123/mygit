package com.tapi.tcs.vc.common.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.service.MenuService;
import com.tapi.tcs.vc.common.vo.MenuVo;

/**
 * 主页面ViewAction
 * 
 * @author lianchao
 * @Date: 2013-4-23
 * @version 1.0
 */
public class IndexAction extends BaseAction {
	/** serialVersionUID */
	private static final long serialVersionUID = -2248031194541049469L;

	/** 日志对象 */
	protected final Logger logger = LoggerFactory.getLogger(IndexAction.class);

	/** f5Check */
	private InputStream inputStream;

	/** 查询主菜单Service */
	private MenuService menuService;

	/** 查询权限机构设置的 */
	private VcLevelService vcLevelService;

	/** 单点登录验证 */
	private LoginAction loginAction;

	/**
	 * 主页面View
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	public String viewIndex() throws BusinessException, ServletException, IOException{
		logger.info("访问index/index.do?action=main&ajax=true进入系统主页...");
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		//从session中取用户Id,并完善用户信息后放到session_userInfo中,否则抛出异常
		if (session.getAttribute("userInfo")==null) {
			loginAction.login();
		}
		String action = request.getParameter("action");
		if ("forwordmain".equals(action)) {
			return "forwordmain";
		} else if ("main".equals(action)) {
			return "main";
		} else if ("topPage".equals(action)) {
			return "topPage";
		} else if ("topSwitch".equals(action)) {
			return "topSwitch";
		} else if ("leftPage".equals(action)) {
			return "leftPage";
		} else if ("leftSwitch".equals(action)) {
			return "leftSwitch";
		} else if ("mainCenter".equals(action)) {
			return "mainCenter";
		} else {
			return SUCCESS;
		}
	}

	/**
	 * 查询主页面菜单信息
	 * 
	 * @throws IOException
	 * 
	 */
	public void queryMenuList() throws IOException {
		logger.info("访问/queryMenuList.do?ajax=true查询菜单信息...");
		/*
		 * MenuVo menuVo = new MenuVo(); menuVo.setMenuID("590");
		 * menuVo.setMenuName("征订管理"); // menuVo.setIsChild(""); //
		 * menuVo.setIsRemind(""); menuVo.setSeqNo(1); // menuVo.setUrl(url);
		 * menuVo.setParentID("-1"); menuList = new ArrayList<MenuVo>();
		 * menuList.add(menuVo);
		 */
		//String menuList1 = "[{\"isChild\":null,\"isRemind\":null,\"menuID\":\"590\",\"menuName\":\"征订管理\",\"parentID\":\"-1\",\"seqNo\":1,\"url\":null},{\"isChild\":null,\"isRemind\":null,\"menuID\":\"601\",\"menuName\":\"库存管理\",\"parentID\":\"-1\",\"seqNo\":12,\"url\":null},{\"isChild\":null,\"isRemind\":null,\"menuID\":\"615\",\"menuName\":\"单证核销\",\"parentID\":\"-1\",\"seqNo\":24,\"url\":null},{\"isChild\":null,\"isRemind\":null,\"menuID\":\"627\",\"menuName\":\"基础信息维护\",\"parentID\":\"-1\",\"seqNo\":35,\"url\":null}]";
		UserInfo userInfo = (UserInfo)getRequest().getSession().getAttribute("userInfo");
		List<MenuVo> queryMenuList = null;
		try {
			queryMenuList = menuService.queryMenuList(userInfo);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.renderJson(getResponse(), queryMenuList);
	}


	/**
	 * f5Check
	 * 
	 * @return
	 */
	public String f5Check() {
		String str = new String("F5Checkok");
		inputStream = new ByteArrayInputStream(str.getBytes());
		return SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public VcLevelService getVcLevelService() {
		return vcLevelService;
	}

	public void setVcLevelService(VcLevelService vcLevelService) {
		this.vcLevelService = vcLevelService;
	}

	public LoginAction getLoginAction() {
		return loginAction;
	}

	public void setLoginAction(LoginAction loginAction) {
		this.loginAction = loginAction;
	}

	public Logger getLogger() {
		return logger;
	}

	

}