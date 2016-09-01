//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.18 at 08:57:29 AM CEST 
//


package org.woped.file.yawl.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NetFactsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NetFactsType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.yawlfoundation.org/yawlschema}DecompositionFactsType">
 *       &lt;sequence>
 *         &lt;element name="localVariable" type="{http://www.yawlfoundation.org/yawlschema}VariableFactsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="processControlElements">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="inputCondition" type="{http://www.yawlfoundation.org/yawlschema}ExternalConditionFactsType"/>
 *                   &lt;choice maxOccurs="unbounded">
 *                     &lt;element name="task" type="{http://www.yawlfoundation.org/yawlschema}ExternalTaskFactsType" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element name="condition" type="{http://www.yawlfoundation.org/yawlschema}ExternalConditionFactsType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/choice>
 *                   &lt;element name="outputCondition" type="{http://www.yawlfoundation.org/yawlschema}OutputConditionFactsType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="externalDataGateway" type="{http://www.yawlfoundation.org/yawlschema}NameType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isRootNet" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetFactsType", propOrder = {
    "localVariable",
    "processControlElements",
    "externalDataGateway"
})
public class NetFactsType
    extends DecompositionFactsType
{

    protected List<VariableFactsType> localVariable;
    @XmlElement(required = true)
    protected NetFactsType.ProcessControlElements processControlElements;
    protected String externalDataGateway;
    @XmlAttribute(name = "isRootNet")
    protected Boolean isRootNet;

    /**
     * Gets the value of the localVariable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localVariable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalVariable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VariableFactsType }
     * 
     * 
     */
    public List<VariableFactsType> getLocalVariable() {
        if (localVariable == null) {
            localVariable = new ArrayList<VariableFactsType>();
        }
        return this.localVariable;
    }

    /**
     * Gets the value of the processControlElements property.
     * 
     * @return
     *     possible object is
     *     {@link NetFactsType.ProcessControlElements }
     *     
     */
    public NetFactsType.ProcessControlElements getProcessControlElements() {
        return processControlElements;
    }

    /**
     * Sets the value of the processControlElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetFactsType.ProcessControlElements }
     *     
     */
    public void setProcessControlElements(NetFactsType.ProcessControlElements value) {
        this.processControlElements = value;
    }

    /**
     * Gets the value of the externalDataGateway property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDataGateway() {
        return externalDataGateway;
    }

    /**
     * Sets the value of the externalDataGateway property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDataGateway(String value) {
        this.externalDataGateway = value;
    }

    /**
     * Gets the value of the isRootNet property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRootNet() {
        return isRootNet;
    }

    /**
     * Sets the value of the isRootNet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRootNet(Boolean value) {
        this.isRootNet = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="inputCondition" type="{http://www.yawlfoundation.org/yawlschema}ExternalConditionFactsType"/>
     *         &lt;choice maxOccurs="unbounded">
     *           &lt;element name="task" type="{http://www.yawlfoundation.org/yawlschema}ExternalTaskFactsType" maxOccurs="unbounded" minOccurs="0"/>
     *           &lt;element name="condition" type="{http://www.yawlfoundation.org/yawlschema}ExternalConditionFactsType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;/choice>
     *         &lt;element name="outputCondition" type="{http://www.yawlfoundation.org/yawlschema}OutputConditionFactsType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "inputCondition",
        "taskOrCondition",
        "outputCondition"
    })
    public static class ProcessControlElements {

        @XmlElement(required = true)
        protected ExternalConditionFactsType inputCondition;
        @XmlElements({
            @XmlElement(name = "task", type = ExternalTaskFactsType.class),
            @XmlElement(name = "condition", type = ExternalConditionFactsType.class)
        })
        protected List<ExternalNetElementFactsType> taskOrCondition;
        @XmlElement(required = true)
        protected OutputConditionFactsType outputCondition;

        /**
         * Gets the value of the inputCondition property.
         * 
         * @return
         *     possible object is
         *     {@link ExternalConditionFactsType }
         *     
         */
        public ExternalConditionFactsType getInputCondition() {
            return inputCondition;
        }

        /**
         * Sets the value of the inputCondition property.
         * 
         * @param value
         *     allowed object is
         *     {@link ExternalConditionFactsType }
         *     
         */
        public void setInputCondition(ExternalConditionFactsType value) {
            this.inputCondition = value;
        }

        /**
         * Gets the value of the taskOrCondition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the taskOrCondition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTaskOrCondition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ExternalTaskFactsType }
         * {@link ExternalConditionFactsType }
         * 
         * 
         */
        public List<ExternalNetElementFactsType> getTaskOrCondition() {
            if (taskOrCondition == null) {
                taskOrCondition = new ArrayList<ExternalNetElementFactsType>();
            }
            return this.taskOrCondition;
        }

        /**
         * Gets the value of the outputCondition property.
         * 
         * @return
         *     possible object is
         *     {@link OutputConditionFactsType }
         *     
         */
        public OutputConditionFactsType getOutputCondition() {
            return outputCondition;
        }

        /**
         * Sets the value of the outputCondition property.
         * 
         * @param value
         *     allowed object is
         *     {@link OutputConditionFactsType }
         *     
         */
        public void setOutputCondition(OutputConditionFactsType value) {
            this.outputCondition = value;
        }

    }

}