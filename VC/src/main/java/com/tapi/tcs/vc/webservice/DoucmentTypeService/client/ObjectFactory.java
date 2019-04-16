
package com.tapi.tcs.vc.webservice.DoucmentTypeService.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tapi.tcs.vc.webservice.DoucmentTypeService.client package. 
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

    private final static QName _ESystemRequest_QNAME = new QName("server.webservice.abutment.sinosoft.com", "ESystemRequest");
    private final static QName _ESystemRequestResponse_QNAME = new QName("server.webservice.abutment.sinosoft.com", "ESystemRequestResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tapi.tcs.vc.webservice.DoucmentTypeService.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ESystemRequest }
     * 
     */
    public ESystemRequest createESystemRequest() {
        return new ESystemRequest();
    }

    /**
     * Create an instance of {@link ESystemRequestResponse }
     * 
     */
    public ESystemRequestResponse createESystemRequestResponse() {
        return new ESystemRequestResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ESystemRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "server.webservice.abutment.sinosoft.com", name = "ESystemRequest")
    public JAXBElement<ESystemRequest> createESystemRequest(ESystemRequest value) {
        return new JAXBElement<ESystemRequest>(_ESystemRequest_QNAME, ESystemRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ESystemRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "server.webservice.abutment.sinosoft.com", name = "ESystemRequestResponse")
    public JAXBElement<ESystemRequestResponse> createESystemRequestResponse(ESystemRequestResponse value) {
        return new JAXBElement<ESystemRequestResponse>(_ESystemRequestResponse_QNAME, ESystemRequestResponse.class, null, value);
    }

}
