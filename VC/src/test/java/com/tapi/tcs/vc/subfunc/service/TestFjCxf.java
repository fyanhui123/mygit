package com.tapi.tcs.vc.subfunc.service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import cn.com.pansky.JTServicePortType;

public class TestFjCxf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JaxWsProxyFactoryBean jfb = new JaxWsProxyFactoryBean();
		jfb.setAddress("http://218.66.6.170/services/JTService");
		jfb.setServiceClass(JTServicePortType.class);
		JTServicePortType port = (JTServicePortType)jfb.create();
		String result = port.service("", null);
		System.out.println(result);
	}

}
