package com.tapi.tcs.vc.webservice.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.tapi.service.um.authorities.intf.AuthoritiesPortType;
import com.tapi.tcs.vc.common.util.SysConst;

/**
 * 服务客户端
 * <p>
 * Date: 2013-3-29
 * </p>
 * <p>
 * Module: UM WebService服务客户端
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author xweibin
 * @version 1.0
 */
public class UMClient {

	public static AuthoritiesPortType createAuthoritiesService(String serviceUrl) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(serviceUrl);
		factory.setServiceClass(AuthoritiesPortType.class);
		AuthoritiesPortType service = (AuthoritiesPortType) factory.create();
		Client client = ClientProxy.getClient(service);
		if (client != null) {
			HTTPConduit conduit = (HTTPConduit) client.getConduit();
			HTTPClientPolicy policy = new HTTPClientPolicy();
			policy.setConnectionTimeout(SysConst.CONNECTION_TIME_OUT);// 连接超时
			policy.setReceiveTimeout(SysConst.RECEIVE_TIME_OUT);// 响应超时
			conduit.setClient(policy);
			client.getInInterceptors().add(new LoggingInInterceptor());
			client.getOutInterceptors().add(new LoggingOutInterceptor());
		}
		return service;
	}

}
