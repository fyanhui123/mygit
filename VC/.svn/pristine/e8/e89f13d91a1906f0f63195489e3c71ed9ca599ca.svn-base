package com.tapi.tcs.vc.common.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.common.util.URLUtils;
import com.tapi.tcs.vc.schema.model.PubUserDef;

/**
 * 登录Action Module：
 */
public class LoginAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3321786963574773573L;

	/** 查询权限机构设置的 */
	private VcLevelService vcLevelService;
	private String loginUrl;
	private String oAuthLogoutUrl;
	private String oAuthSwitchSysUrl;

	/**
	 * 登录
	 * 
	 * @throws BusinessException
	 * @throws IOException
	 * @throws ServletException
	 */
	public void login() throws BusinessException, ServletException, IOException {
		logger.info("访问index/login.do开始验证登录...");
		UserInfo userInfo = new UserInfo();
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();

		String userCode = (String) session.getAttribute("CURRENT_USER");
		PubUserDef userDef = null;
		if (StringUtils.isBlank(userCode) || (userDef = vcLevelService.findPubUserDefByCode(userCode)) == null) {
			throw new BusinessException("此用户不存在！");
		}
		userInfo.setCompanyCode(userDef.getCompanyCode());
		String companyName = vcLevelService.getComName(userDef.getCompanyCode());
		userInfo.setCompanyName(companyName);
		
		
		String comCode = vcLevelService.getComCode(userCode);
		if(StringUtils.isBlank(comCode)){
			//非管理员，查询使用人机构代码
			comCode = vcLevelService.getTakerComCode(userCode);
			if (StringUtils.isBlank(comCode)) {
				throw new BusinessException("没有登录单证管理系统的权限！");					
			}
		}
		
		String comName = vcLevelService.getComName(comCode);
		
		userInfo.setUserCode(userCode);
		userInfo.setUserName(userDef.getUserName());
		userInfo.setComCode(comCode);
		userInfo.setComName(comName);
		userInfo.setSysAddr(request.getRemoteAddr() + ":" + request.getRemotePort());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		userInfo.setCurrentDate(sdf.format(new Date()));
		session.setAttribute("userInfo", userInfo);

		logger.info("用户[" + userInfo.getUserCode() + userInfo.getUserName() + "]登录成功...");
		request.getRequestDispatcher("/index.jsp").forward(request, getResponse());
	}

	/**
	 * 用户注销
	 */
	public void logout() {
		logger.info("访问/logout.do用户注销 ...");
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.removeAttribute("CURRENT_USER");
		session.removeAttribute("userInfo");
		// String sessionToken = (String) session.getAttribute("sessionToken");
		logger.info("用户[" + userInfo.getUserName() + "]注销...");
		try {
			getResponse().sendRedirect(oAuthLogoutUrl);// +"?sessionToken="+sessionToken+"&url="+loginUrl
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 查询系统列表 点击切换系统
	 * @throws IOException 
	 */
	public void querySystemList() throws IOException {
		logger.info("访问/querySystemList.do查询系统列表...");
		String employeeId = (String) getRequest().getSession().getAttribute("CURRENT_USER");
		JSONObject resObj = URLUtils.getUrlResourceObj(oAuthSwitchSysUrl + "?employee_id=" + employeeId);
		String message = "";
		if (resObj != null && "Y".equals(resObj.getString("code"))) {
			message = resObj.getString("message");
		}
		this.renderJson(getResponse(), message);
	}

	public VcLevelService getVcLevelService() {
		return vcLevelService;
	}

	public void setVcLevelService(VcLevelService vcLevelService) {
		this.vcLevelService = vcLevelService;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getoAuthLogoutUrl() {
		return oAuthLogoutUrl;
	}

	public void setoAuthLogoutUrl(String oAuthLogoutUrl) {
		this.oAuthLogoutUrl = oAuthLogoutUrl;
	}

	public String getoAuthSwitchSysUrl() {
		return oAuthSwitchSysUrl;
	}

	public void setoAuthSwitchSysUrl(String oAuthSwitchSysUrl) {
		this.oAuthSwitchSysUrl = oAuthSwitchSysUrl;
	}

}
