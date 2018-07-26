
package org.woped.qualanalysis.paraphrasing.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "p2t", targetNamespace = "http://ws.processtotext/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProcessToTextWebService {


    /**
     * 
     * @param processSpecification
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "generateTextFromProcessSpecification", targetNamespace = "http://ws.processtotext/", className = "org.woped.qualanalysis.paraphrasing.webservice.GenerateTextFromProcessSpecification")
    @ResponseWrapper(localName = "generateTextFromProcessSpecificationResponse", targetNamespace = "http://ws.processtotext/", className = "org.woped.qualanalysis.paraphrasing.webservice.GenerateTextFromProcessSpecificationResponse")
    public String generateTextFromProcessSpecification(
        @WebParam(name = "processSpecification", targetNamespace = "")
        String processSpecification)
        throws Exception_Exception
    ;

}
