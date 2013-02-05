
package org.apromore.manager.model_portal;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateProcessInputMsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateProcessInputMsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Native" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="EditSession" type="{http://www.apromore.org/manager/model_portal}EditSessionType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EditSessionCode" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="PreVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateProcessInputMsgType", propOrder = {
    "_native",
    "editSession"
})
public class UpdateProcessInputMsgType {

    @XmlElement(name = "Native", required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler _native;
    @XmlElement(name = "EditSession", required = true)
    protected EditSessionType editSession;
    @XmlAttribute(name = "EditSessionCode")
    protected Integer editSessionCode;
    @XmlAttribute(name = "PreVersion")
    protected String preVersion;

    /**
     * Gets the value of the native property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getNative() {
        return _native;
    }

    /**
     * Sets the value of the native property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setNative(DataHandler value) {
        this._native = value;
    }

    /**
     * Gets the value of the editSession property.
     * 
     * @return
     *     possible object is
     *     {@link EditSessionType }
     *     
     */
    public EditSessionType getEditSession() {
        return editSession;
    }

    /**
     * Sets the value of the editSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link EditSessionType }
     *     
     */
    public void setEditSession(EditSessionType value) {
        this.editSession = value;
    }

    /**
     * Gets the value of the editSessionCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEditSessionCode() {
        return editSessionCode;
    }

    /**
     * Sets the value of the editSessionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEditSessionCode(Integer value) {
        this.editSessionCode = value;
    }

    /**
     * Gets the value of the preVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreVersion() {
        return preVersion;
    }

    /**
     * Sets the value of the preVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreVersion(String value) {
        this.preVersion = value;
    }

}
