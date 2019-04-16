package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Title", propOrder = {
    "nsrdnbm",
    "loginId",
    "password",
    "jkId",
    "jtkhlx",
    "requestUuid",
    "requestTime",
    "v1",
    "v2",
    "v3"
})
@XmlRootElement(name = "Title")
public class RequestTitle implements Serializable{
	@XmlElement(name = "NSRDNBM", required = true)
    protected  String nsrdnbm;
	@XmlElement(name = "LOGIN_ID", required = true)
    protected  String loginId;
	@XmlElement(name = "PASSWORD", required = true)
    protected  String password;
	@XmlElement(name = "JK_ID", required = true)
    protected  String jkId;
	@XmlElement(name = "JTKHLX", required = true)
    protected  String jtkhlx;
	@XmlElement(name = "REQUEST_UUID", required = true)
    protected  String requestUuid;
	@XmlElement(name = "REQUEST_TIME", required = true)
    protected  String requestTime;
	@XmlElement(name = "V1", required = true)
    protected  String v1;
	@XmlElement(name = "V2", required = true)
    protected  String v2;
	@XmlElement(name = "V3", required = true)
    protected  String v3;
	
	/**默认构造器*/
	public RequestTitle(){
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UUID uuid = UUID.randomUUID(); 
		String strUUID = uuid.toString();
		this.jtkhlx="A10001";
		this.requestUuid=strUUID.replaceAll("-", "");
		this.requestTime=sdf.format(new Date());
	}
	public String getNsrdnbm() {
		return nsrdnbm;
	}
	public void setNsrdnbm(String nsrdnbm) {
		this.nsrdnbm = nsrdnbm;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJkId() {
		return jkId;
	}
	public void setJkId(String jkId) {
		this.jkId = jkId;
	}
	public String getJtkhlx() {
		return jtkhlx;
	}
	public void setJtkhlx(String jtkhlx) {
		this.jtkhlx = jtkhlx;
	}
	public String getRequestUuid() {
		return requestUuid;
	}
	public void setRequestUuid(String requestUuid) {
		this.requestUuid = requestUuid;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getV1() {
		return v1;
	}
	public void setV1(String v1) {
		this.v1 = v1;
	}
	public String getV2() {
		return v2;
	}
	public void setV2(String v2) {
		this.v2 = v2;
	}
	public String getV3() {
		return v3;
	}
	public void setV3(String v3) {
		this.v3 = v3;
	}
}
