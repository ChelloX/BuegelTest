package de.hpi.bpt.oryx.erdf;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Interface to an eRDF model
 *
 * @author Artem Polyvyanyy
 */
interface IERDFModel<E extends ERDFEdge<V>, V extends ERDFNode> {
    /**
     * Parse eRDF model form eRDF string
     *
     * @param erdfString String containing eRDF encoding
     */
    void parseERDF(String erdfString) throws SAXException, IOException, ParserConfigurationException;

    V createNode();

    E createEdge(V s, V t);
}