
package com.tapi.service.security.teamdetails;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tapi.service.security.teamdetails package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryTeamDetail_QNAME = new QName("http://service.tapi.com/security/teamdetails", "queryTeamDetail");
    private final static QName _QueryTeamDetailResponse_QNAME = new QName("http://service.tapi.com/security/teamdetails", "queryTeamDetailResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tapi.service.security.teamdetails
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryTeamDetailResponse }
     * 
     */
    public QueryTeamDetailResponse createQueryTeamDetailResponse() {
        return new QueryTeamDetailResponse();
    }

    /**
     * Create an instance of {@link QueryTeamDetail }
     * 
     */
    public QueryTeamDetail createQueryTeamDetail() {
        return new QueryTeamDetail();
    }

    /**
     * Create an instance of {@link BaseRequestDto }
     * 
     */
    public BaseRequestDto createBaseRequestDto() {
        return new BaseRequestDto();
    }

    /**
     * Create an instance of {@link ResponseHeadDto }
     * 
     */
    public ResponseHeadDto createResponseHeadDto() {
        return new ResponseHeadDto();
    }

    /**
     * Create an instance of {@link DateTime }
     * 
     */
    public DateTime createDateTime() {
        return new DateTime();
    }

    /**
     * Create an instance of {@link TeamDetailRequest }
     * 
     */
    public TeamDetailRequest createTeamDetailRequest() {
        return new TeamDetailRequest();
    }

    /**
     * Create an instance of {@link SaTeamNewDto }
     * 
     */
    public SaTeamNewDto createSaTeamNewDto() {
        return new SaTeamNewDto();
    }

    /**
     * Create an instance of {@link TeamDetailResponse }
     * 
     */
    public TeamDetailResponse createTeamDetailResponse() {
        return new TeamDetailResponse();
    }

    /**
     * Create an instance of {@link BaseResponseDto }
     * 
     */
    public BaseResponseDto createBaseResponseDto() {
        return new BaseResponseDto();
    }

    /**
     * Create an instance of {@link RequestHeadDto }
     * 
     */
    public RequestHeadDto createRequestHeadDto() {
        return new RequestHeadDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryTeamDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tapi.com/security/teamdetails", name = "queryTeamDetail")
    public JAXBElement<QueryTeamDetail> createQueryTeamDetail(QueryTeamDetail value) {
        return new JAXBElement<QueryTeamDetail>(_QueryTeamDetail_QNAME, QueryTeamDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryTeamDetailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tapi.com/security/teamdetails", name = "queryTeamDetailResponse")
    public JAXBElement<QueryTeamDetailResponse> createQueryTeamDetailResponse(QueryTeamDetailResponse value) {
        return new JAXBElement<QueryTeamDetailResponse>(_QueryTeamDetailResponse_QNAME, QueryTeamDetailResponse.class, null, value);
    }

}
