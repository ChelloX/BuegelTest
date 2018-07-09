package dataModel.pnmlReader;

import dataModel.pnmlReader.PetriNet.Arc;
import dataModel.pnmlReader.PetriNet.PetriNet;
import dataModel.pnmlReader.PetriNet.Place;
import dataModel.pnmlReader.PetriNet.Transition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

public class PNMLReader {
    private static final String[] roles = new String[100];

    private static void extractFlow(Document doc, PetriNet petriNet) {
        NodeList list = doc.getElementsByTagName("arc");
        for (int i = 0; i < list.getLength(); i++) {
            Node fstNode = list.item(i);
            Element arc = (Element) fstNode;
            String id = (arc.getAttribute("id"));
            String source = arc.getAttribute("source");
            String target = arc.getAttribute("target");

            //If there is already an arc with the same ID --> new ID neccessary
            //Hashmap to check for existing arcs
            HashMap<String, Arc> arcs = petriNet.getArcs();
            if (arcs.keySet().contains(id)) {
                id = id + "_vorhanden";
            }

            petriNet.addArc(new Arc(id, source, target));
        }
    }

    private static void extractRoles(Document doc) {
        NodeList list = doc.getElementsByTagName("transition");
        for (int i = 0; i < list.getLength(); i++) {
            Node fstnode = list.item(i);
            NodeList test = fstnode.getChildNodes();
            for (int j = 0; j < test.getLength(); j++) {
                Node scndNode = test.item(j);
                if (scndNode.getNodeName().equals("toolspecific")) {
                    NodeList thrdNode = scndNode.getChildNodes();
                    for (int k = 0; thrdNode.getLength() > k; k++) {
                        Node thrd = thrdNode.item(k);
                        if (thrd.getNodeName().equals("transitionResource")) {
                            Element test2 = (Element) thrd;
                            roles[0] = test2.getAttribute("roleName");
                        }
                    }
                }
            }

            Element element = (Element) fstnode;
            roles[i] = element.getAttribute("roleName");
        }
    }

    private static void extractElements(Document doc, String type, PetriNet petriNet) {
        NodeList list = doc.getElementsByTagName(type);
        for (int i = 0; i < list.getLength(); i++) {
            Node fstNode = list.item(i);
            Element element = (Element) fstNode;
            String id = (element.getAttribute("id"));
            NodeList fstNodeElems = fstNode.getChildNodes();
            for (int j = 0; j < fstNodeElems.getLength(); j++) {
                Node sndNode = fstNodeElems.item(j);
                NodeList sndNodeElems = sndNode.getChildNodes();
                for (int k = 0; k < sndNodeElems.getLength(); k++) {
                    Node thdNode = sndNodeElems.item(k);
                    if (thdNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (thdNode.getNodeName().contains("text")) {
                            if (type.equals("place")) {
                                petriNet.addElements(new Place(id, thdNode.getTextContent()));
                            } else {
                                petriNet.addElements(new Transition(id, thdNode.getTextContent()));
                            }
                        }
                    }
                }
            }
        }
    }

    public PetriNet getPetriNetFromPNML(File file) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            PetriNet petriNet = new PetriNet();
            extractElements(doc, "place", petriNet);
            extractElements(doc, "transition", petriNet);
            extractFlow(doc, petriNet);
            extractRoles(doc);

            return petriNet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}