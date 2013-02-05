
package org.apromore.manager.model_portal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReadNativeTypesOutputMsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReadNativeTypesOutputMsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://www.apromore.org/manager/model_portal}ResultType"/>
 *         &lt;element name="NativeTypes" type="{http://www.apromore.org/manager/model_portal}NativeTypesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReadNativeTypesOutputMsgType", propOrder = {
    "result",
    "nativeTypes"
})
public class ReadNativeTypesOutputMsgType {

    @XmlElement(name = "Result", required = true)
    protected ResultType result;
    @XmlElement(name = "NativeTypes", required = true)
    protected NativeTypesType nativeTypes;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ResultType }
     *     
     */
    public ResultType getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultType }
     *     
     */
    public void setResult(ResultType value) {
        this.result = value;
    }

    /**
     * Gets the value of the nativeTypes property.
     * 
     * @return
     *     possible object is
     *     {@link NativeTypesType }
     *     
     */
    public NativeTypesType getNativeTypes() {
        return nativeTypes;
    }

    /**
     * Sets the value of the nativeTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link NativeTypesType }
     *     
     */
    public void setNativeTypes(NativeTypesType value) {
        this.nativeTypes = value;
    }

}
