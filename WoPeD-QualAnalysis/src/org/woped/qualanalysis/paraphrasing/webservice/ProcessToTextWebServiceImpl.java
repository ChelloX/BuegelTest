
package org.woped.qualanalysis.paraphrasing.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import org.woped.core.config.ConfigurationManager;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "p2t", targetNamespace = "http://ws.processtotext/")
public class ProcessToTextWebServiceImpl
    extends Service
{

    private static URL PROCESSTOTEXTWEBSERVICESERVICE_WSDL_LOCATION = null;
    private static WebServiceException PROCESSTOTEXTWEBSERVICESERVICE_EXCEPTION = null;
    private static QName PROCESSTOTEXTWEBSERVICESERVICE_QNAME = new QName("http://ws.processtotext/", "p2t");
    private static String CONNECTIONSTRING = "";

    /**
     * Generate new URL for P2T web service.
     * @author allgaier
     */
    public static void generateURL(){
    	URL url = null;
        WebServiceException e = null;
        CONNECTIONSTRING = "http://" + ConfigurationManager.getConfiguration().getProcess2TextServerHost() + ":" + ConfigurationManager.getConfiguration().getProcess2TextServerPort() + ConfigurationManager.getConfiguration().getProcess2TextServerURI();
        try {
            url = new URL(CONNECTIONSTRING);
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PROCESSTOTEXTWEBSERVICESERVICE_WSDL_LOCATION = url;
        PROCESSTOTEXTWEBSERVICESERVICE_EXCEPTION = e;
        System.out.println(CONNECTIONSTRING);
    }

    public ProcessToTextWebServiceImpl() {
    	super(__getWsdlLocation(), PROCESSTOTEXTWEBSERVICESERVICE_QNAME);
    }

    public ProcessToTextWebServiceImpl(WebServiceFeature... features) {
        super(__getWsdlLocation(), PROCESSTOTEXTWEBSERVICESERVICE_QNAME, features);
    }

    public ProcessToTextWebServiceImpl(URL wsdlLocation) {
        super(wsdlLocation, PROCESSTOTEXTWEBSERVICESERVICE_QNAME);
    }

    public ProcessToTextWebServiceImpl(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PROCESSTOTEXTWEBSERVICESERVICE_QNAME, features);
    }

    public ProcessToTextWebServiceImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProcessToTextWebServiceImpl(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ProcessToTextWebService
     */
    @WebEndpoint(name = "ProcessToTextWebServicePort")
    public ProcessToTextWebService getProcessToTextWebServicePort() {
        return super.getPort(new QName("http://ws.processtotext/", "ProcessToTextWebServicePort"), ProcessToTextWebService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProcessToTextWebService
     */
    @WebEndpoint(name = "ProcessToTextWebServicePort")
    public ProcessToTextWebService getProcessToTextWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.processtotext/", "ProcessToTextWebServicePort"), ProcessToTextWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PROCESSTOTEXTWEBSERVICESERVICE_EXCEPTION!= null) {
            throw PROCESSTOTEXTWEBSERVICESERVICE_EXCEPTION;
        }

        // Ensure that for each call, we generate a new URL to reflect potential
        // configuration changes.
        generateURL();

        return PROCESSTOTEXTWEBSERVICESERVICE_WSDL_LOCATION;
    }

}
