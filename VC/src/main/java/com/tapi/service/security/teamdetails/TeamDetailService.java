package com.tapi.service.security.teamdetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.4
 * 2013-12-20T17:23:28.937+08:00
 * Generated source version: 2.4.4
 * 
 */
@WebService(targetNamespace = "http://service.tapi.com/security/teamdetails", name = "TeamDetailService")
@XmlSeeAlso({ObjectFactory.class})
public interface TeamDetailService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "queryTeamDetail", targetNamespace = "http://service.tapi.com/security/teamdetails", className = "com.tapi.service.security.teamdetails.QueryTeamDetail")
    @WebMethod
    @ResponseWrapper(localName = "queryTeamDetailResponse", targetNamespace = "http://service.tapi.com/security/teamdetails", className = "com.tapi.service.security.teamdetails.QueryTeamDetailResponse")
    public com.tapi.service.security.teamdetails.TeamDetailResponse queryTeamDetail(
        @WebParam(name = "request", targetNamespace = "")
        com.tapi.service.security.teamdetails.TeamDetailRequest request
    );
}
