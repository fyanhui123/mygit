
package com.tapi.service.security.agreementdetails;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tapi.service.security.agreementdetails package. 
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

    private final static QName _QueryAgreementDetailResponse_QNAME = new QName("http://service.tapi.com/security/agreementdetails", "queryAgreementDetailResponse");
    private final static QName _QueryAgreementDetail_QNAME = new QName("http://service.tapi.com/security/agreementdetails", "queryAgreementDetail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tapi.service.security.agreementdetails
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryAgreementDetail }
     * 
     */
    public QueryAgreementDetail createQueryAgreementDetail() {
        return new QueryAgreementDetail();
    }

    /**
     * Create an instance of {@link QueryAgreementDetailResponse }
     * 
     */
    public QueryAgreementDetailResponse createQueryAgreementDetailResponse() {
        return new QueryAgreementDetailResponse();
    }

    /**
     * Create an instance of {@link PrpDagreementNewDto }
     * 
     */
    public PrpDagreementNewDto createPrpDagreementNewDto() {
        return new PrpDagreementNewDto();
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
     * Create an instance of {@link PrpdSalePersonNewDto }
     * 
     */
    public PrpdSalePersonNewDto createPrpdSalePersonNewDto() {
        return new PrpdSalePersonNewDto();
    }

    /**
     * Create an instance of {@link DateTime }
     * 
     */
    public DateTime createDateTime() {
        return new DateTime();
    }

    /**
     * Create an instance of {@link PrpDagreeDetailImpDto }
     * 
     */
    public PrpDagreeDetailImpDto createPrpDagreeDetailImpDto() {
        return new PrpDagreeDetailImpDto();
    }

    /**
     * Create an instance of {@link BaseResponseDto }
     * 
     */
    public BaseResponseDto createBaseResponseDto() {
        return new BaseResponseDto();
    }

    /**
     * Create an instance of {@link AgreementDetailResponse }
     * 
     */
    public AgreementDetailResponse createAgreementDetailResponse() {
        return new AgreementDetailResponse();
    }

    /**
     * Create an instance of {@link AgreementDetailRequest }
     * 
     */
    public AgreementDetailRequest createAgreementDetailRequest() {
        return new AgreementDetailRequest();
    }

    /**
     * Create an instance of {@link RequestHeadDto }
     * 
     */
    public RequestHeadDto createRequestHeadDto() {
        return new RequestHeadDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryAgreementDetailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tapi.com/security/agreementdetails", name = "queryAgreementDetailResponse")
    public JAXBElement<QueryAgreementDetailResponse> createQueryAgreementDetailResponse(QueryAgreementDetailResponse value) {
        return new JAXBElement<QueryAgreementDetailResponse>(_QueryAgreementDetailResponse_QNAME, QueryAgreementDetailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryAgreementDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.tapi.com/security/agreementdetails", name = "queryAgreementDetail")
    public JAXBElement<QueryAgreementDetail> createQueryAgreementDetail(QueryAgreementDetail value) {
        return new JAXBElement<QueryAgreementDetail>(_QueryAgreementDetail_QNAME, QueryAgreementDetail.class, null, value);
    }

}
