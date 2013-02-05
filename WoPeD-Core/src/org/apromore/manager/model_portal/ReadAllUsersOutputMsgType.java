
package org.apromore.manager.model_portal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReadAllUsersOutputMsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReadAllUsersOutputMsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://www.apromore.org/manager/model_portal}ResultType"/>
 *         &lt;element name="Usernames" type="{http://www.apromore.org/manager/model_portal}UsernamesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReadAllUsersOutputMsgType", propOrder = {
    "result",
    "usernames"
})
public class ReadAllUsersOutputMsgType {

    @XmlElement(name = "Result", required = true)
    protected ResultType result;
    @XmlElement(name = "Usernames", required = true)
    protected UsernamesType usernames;

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
     * Gets the value of the usernames property.
     * 
     * @return
     *     possible object is
     *     {@link UsernamesType }
     *     
     */
    public UsernamesType getUsernames() {
        return usernames;
    }

    /**
     * Sets the value of the usernames property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsernamesType }
     *     
     */
    public void setUsernames(UsernamesType value) {
        this.usernames = value;
    }

}
