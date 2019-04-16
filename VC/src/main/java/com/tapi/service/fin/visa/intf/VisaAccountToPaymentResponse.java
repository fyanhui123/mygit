
package com.tapi.service.fin.visa.intf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.tapi.service.fin.common.dto.BaseResponse;


/**
 * <p>Java class for visaAccountToPaymentResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="visaAccountToPaymentResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/fin/common/dto}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="responseDetailDto" type="{http://service.tapi.com/fin/visa/intf}responseDetialDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visaAccountToPaymentResponse", propOrder = {
    "responseDetailDto"
})
public class VisaAccountToPaymentResponse
    extends BaseResponse
{

    protected List<ResponseDetialDto> responseDetailDto;

    /**
     * Gets the value of the responseDetailDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseDetailDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseDetailDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseDetialDto }
     * 
     * 
     */
    public List<ResponseDetialDto> getResponseDetailDto() {
        if (responseDetailDto == null) {
            responseDetailDto = new ArrayList<ResponseDetialDto>();
        }
        return this.responseDetailDto;
    }

}
