package com.tapi.tcs.vc.oauth.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.web.BaseAction;
import com.tapi.tcs.vc.oauth.service.OAuthService;
import com.tapi.tcs.vc.oauth.vo.OAuthCompany;
import com.tapi.tcs.vc.oauth.vo.OAuthResponse;
import com.tapi.tcs.vc.oauth.vo.OAuthRole;
import com.tapi.tcs.vc.oauth.vo.OAuthUserRole;
import com.tapi.tcs.vc.oauth.vo.UserRole;


/**
 * 提供服务接口
 * @author Administrator
 *
 */

public class OAuthAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3999593538244577804L;
	protected final Logger logger = Logger.getLogger(getClass());

	private OAuthService oAuthService;
	/**
	 * 角色管理
	 * @throws ParseException 
	 */
	public void roleManage() throws ParseException {
		logger.info("访问/services/oauth/role角色管理 ...");
		OAuthResponse response = new OAuthResponse();
		 Date d = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HttpServletRequest request = getRequest();
			String postData = null;
			postData = getRequestPostData(request);
			logger.info("角色管理请求报文：" + postData);
			// String url=vcDocVersionInfoService.getUrladdress("productUrl", "productUrl");
			Map<String,String> map = JSONObject.parseObject(postData, Map.class);
			List<OAuthRole>list =null;
			for (String key : map.keySet()) { 
                if (map.get(key) != null) {
                    if("roles".equals(key)){
                    	 Object ob=map.get(key);
                    	 list = JSONObject.parseArray(ob.toString(),OAuthRole.class);
                    }
                }
    		}
			//OAuthRole oAuthRole = JSON.parseObject(postData, OAuthRole.class);
			for (int i=0;i<list.size();i++){
				OAuthRole oAuthRole=list.get(i);
				oAuthService.saveRole(oAuthRole);
			}
			
		} catch (Exception e) {
			logger.error("统一授权角色管理出错："+e);
			response.setCode("N");
			response.setMessage(e.getMessage());
			response.setNowTime(sdf.format(d));
		}
		if (response.getCode() == null) {
			response.setCode("Y");
			response.setMessage("操作成功");
			response.setNowTime(sdf.format(d));
			logger.info("统一授权角色管理操作成功...");
		}
		this.renderJson(getResponse(), response);
	}
	/**
	 * 用户角色管理
	 * @throws ParseException 
	 */
	public void userRoleManage() throws ParseException {
		logger.info("访问/services/oauth/userRole用户角色管理 ...");
		OAuthResponse response = new OAuthResponse();
	    Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HttpServletRequest request = getRequest();
			String postData = null;
			postData = getRequestPostData(request);
			logger.info("用户角色管理请求报文：" + postData);
			Map<String,String> map = JSONObject.parseObject(postData, Map.class);
			List<UserRole> list =null;
			String value1=null;
			for (String key : map.keySet()) { 
                if (map.get(key) != null) {
                    if ("employeeCode".equals(key)) {
                    	 value1=(String) map.get(key);
                    }
                    if("roles".equals(key)){
                    	 Object ob=map.get(key);
                    	 list = JSONObject.parseArray(ob.toString(),UserRole.class);
                    }
                }
    		}
			/*List<OAuthUserRole> listOAuthUserRole=new ArrayList<OAuthUserRole>();
			for(int i=0;i<list.size();i++){
				UserRole roleCode=list.get(i);
				OAuthUserRole userRole=new OAuthUserRole();
				userRole.setEmployeeCode(value1);
				userRole.setFlag(value2);
				userRole.setRoleCode(roleCode.getRoleCode());
				listOAuthUserRole.add(userRole);
			}*/
			oAuthService.saveUserRole(value1,list,"1");
		} catch (Exception e) {
			logger.error("统一授权用户角色管理出错："+e);
			response.setCode("N");
			response.setNowTime(sdf.format(d));
			response.setMessage(e.getMessage());
		}
		if (response.getCode() == null) {
			response.setCode("Y");
			response.setMessage("操作成功");
			response.setNowTime(sdf.format(d));
			logger.info("统一授权用户角色管理操作成功...");
		}
		this.renderJson(getResponse(), response);
	}
	/**
	 * 从发布订阅系统订阅机构数据
	 * @throws ParseException 
	 */
	public void companySync() throws ParseException {
		logger.info("访问/services/company从发布订阅系统订阅机构数据 ...");
		OAuthResponse response = new OAuthResponse();
		 Date d = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HttpServletRequest request = getRequest();
			String postData = getRequestPostData(request);
			logger.info("请求报文：" + postData);
			// String url=vcDocVersionInfoService.getUrladdress("productUrl", "productUrl");
			OAuthCompany company = JSON.parseObject(postData, OAuthCompany.class);
			oAuthService.saveCompany(company);
		} catch (Exception e) {
			logger.error("从发布订阅系统订阅机构数据出错："+e);
			response.setCode("N");
			response.setMessage(e.getMessage());
			response.setNowTime(sdf.format(d));
		}
		if (response.getCode() == null) {
			response.setCode("Y");
			response.setNowTime(sdf.format(d));
			response.setMessage("操作成功");
			logger.info("从发布订阅系统订阅机构数据操作成功...");
		}
		this.renderJson(getResponse(), response);
	}
	
	
	
	public OAuthService getoAuthService() {
		return oAuthService;
	}
	public void setoAuthService(OAuthService oAuthService) {
		this.oAuthService = oAuthService;
	}
}
