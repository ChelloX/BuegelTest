
package org.apromore.manager.model_portal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchForSimilarProcessesOutputMsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchForSimilarProcessesOutputMsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://www.apromore.org/manager/model_portal}ResultType"/>
 *         &lt;element name="ProcessSummaries" type="{http://www.apromore.org/manager/model_portal}ProcessSummariesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchForSimilarProcessesOutputMsgType", propOrder = {
    "result",
    "processSummaries"
})
public class SearchForSimilarProcessesOutputMsgType {

    @XmlElement(name = "Result", required = true)
    protected ResultType result;
    @XmlElement(name = "ProcessSummaries", required = true)
    protected ProcessSummariesType processSummaries;

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
     * Gets the value of the processSummaries property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessSummariesType }
     *     
     */
    public ProcessSummariesType getProcessSummaries() {
        return processSummaries;
    }

    /**
     * Sets the value of the processSummaries property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessSummariesType }
     *     
     */
    public void setProcessSummaries(ProcessSummariesType value) {
        this.processSummaries = value;
    }

}
