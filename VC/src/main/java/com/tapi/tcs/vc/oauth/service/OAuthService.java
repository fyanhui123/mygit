package com.tapi.tcs.vc.oauth.service;

import java.util.List;

import com.tapi.tcs.vc.oauth.vo.OAuthCompany;
import com.tapi.tcs.vc.oauth.vo.OAuthResponse;
import com.tapi.tcs.vc.oauth.vo.OAuthRole;
import com.tapi.tcs.vc.oauth.vo.OAuthUserRole;
import com.tapi.tcs.vc.oauth.vo.UserRole;

public interface OAuthService {

	public OAuthResponse saveRole(OAuthRole role) throws Exception;
	public OAuthResponse saveUserRole(String value1,List<UserRole> oAuthUserRole,String value2) throws Exception;
	public OAuthResponse saveCompany(OAuthCompany company) throws Exception;

}
