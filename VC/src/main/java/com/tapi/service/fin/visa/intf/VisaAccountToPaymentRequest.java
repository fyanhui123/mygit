
package com.tapi.service.fin.visa.intf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.tapi.service.fin.common.dto.BaseRequest;


/**
 * <p>Java class for visaAccountToPaymentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="visaAccountToPaymentRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/fin/common/dto}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="visaAccountToPayment" type="{http://service.tapi.com/fin/visa/intf}visaAccountToPayment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visaAccountToPaymentRequest", propOrder = {
    "visaAccountToPayment"
})
public class VisaAccountToPaymentRequest
    extends BaseRequest
{

    protected List<VisaAccountToPayment> visaAccountToPayment;

    /**
     * Gets the value of the visaAccountToPayment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the visaAccountToPayment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVisaAccountToPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VisaAccountToPayment }
     * 
     * 
     */
    public List<VisaAccountToPayment> getVisaAccountToPayment() {
        if (visaAccountToPayment == null) {
            visaAccountToPayment = new ArrayList<VisaAccountToPayment>();
        }
        return this.visaAccountToPayment;
    }

}
