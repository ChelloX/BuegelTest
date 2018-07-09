package dataModel.pnmlReader;

import dataModel.pnmlReader.PetriNet.Element;
import dataModel.pnmlReader.PetriNet.PetriNet;
import dataModel.process.*;

import java.util.HashMap;

public class PetriNetToProcessConverter {
    private final String[] loopSet = new String[100];
    public HashMap<Integer, String> transformedElemsRev;
    private HashMap<String, Integer> transformedElems;
    private int xor_split;
    private int xor_join;
    private int and_join;
    private int and_split;
    private int transitions;
    private int places;
    private int x = 0;

    public ProcessModel convertToProcess(PetriNet petriNet) {
        ProcessModel model = new ProcessModel();
        Pool pool = new Pool("");
        Lane lane = new Lane("");
        model.addPool("");
        model.addLane("");

        transformedElems = new HashMap<>();
        transformedElemsRev = new HashMap<>();

        Element startElem = petriNet.getElements().get(petriNet.getStartPlace());
        transformElem(startElem, -1, petriNet, model, pool, lane);
        return model;
    }

    private void transformElem(Element elem, int precElem, PetriNet petriNet, ProcessModel model, Pool pool, Lane lane) {
        // Id of current petri net element
        String elemId = elem.getId();
        // If element not already ecists
        if (!transformedElems.keySet().contains(elemId)) {
            // Places ...
            if (elem.getClass().toString().endsWith("Place")) {
                places++;

                // Simple place with 1 or more incoming and no outgoing arc
                if (petriNet.getSuccessor(elemId).size() == 0 && petriNet.getPredecessor(elemId).size() == 1) {
                    loopSet[x] = elemId + ": simple place, no ougoing arc";
                    x++;
                    int newId = model.getNewId();
                    model.addGateway(new Gateway(newId, "", lane, pool, GatewayType.XOR));
                    transformedElems.put(elemId, newId);
                    transformedElemsRev.put(newId, elemId);
                    if (precElem != -1) {
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(precElem), model.getElem(newId)));
                    }
                }

                // Simple place with 1 incoming and one outgoing arc
                if (petriNet.getSuccessor(elemId).size() == 1 && petriNet.getPredecessor(elemId).size() <= 1) {
                    loopSet[x] = elemId + ": simple place, one incoming, one outgoing arc";
                    x++;
                    String suc = petriNet.getSuccessor(elemId).get(0);
                    transformElem(petriNet.getElements().get(suc), precElem, petriNet, model, pool, lane);
                }

                //  Place with multiple outgoing arcs (XOR-Join)
                if (petriNet.getSuccessor(elemId).size() >= 0 && petriNet.getPredecessor(elemId).size() > 1) {
                    xor_join++;
                    loopSet[x] = elemId + ": XOR Join";
                    x++;
                    // Create new element
                    int newId = model.getNewId();
                    model.addGateway(new Gateway(newId, "", lane, pool, GatewayType.XOR));
                    transformedElems.put(elemId, newId);
                    transformedElemsRev.put(newId, elemId);
                    if (precElem != -1) {
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(precElem), model.getElem(newId)));
                    }

                    //if it is 0, there is no successor ...
                    if (!(petriNet.getSuccessor(elemId).size() == 0)) {
                        // Recursively go through the model
                        String suc = petriNet.getSuccessor(elemId).get(0);
                        transformElem(petriNet.getElements().get(suc), newId, petriNet, model, pool, lane);
                    } else {
                        //PetriNet ends with a XOR-Join
                        int newId2 = model.getNewId();
                        model.addActivity(new Activity(newId2, "finish", null, null));
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(newId), model.getElem(newId2)));
                        int newId3 = model.getNewId();
                        model.addGateway(new Gateway(newId3, "", lane, pool, GatewayType.XOR));
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(newId2), model.getElem(newId3)));

                    }
                }

                // Place with multiple incoming arcs (XOR-Split)
                if (petriNet.getSuccessor(elemId).size() > 1 && petriNet.getPredecessor(elemId).size() >= 0) {
                    xor_split++;
                    loopSet[x] = elemId + ": XOR Split";
                    x++;
                    // Create new element
                    int newId = model.getNewId();
                    model.addGateway(new Gateway(newId, "", lane, pool, GatewayType.XOR));
                    transformedElems.put(elemId, newId);
                    transformedElemsRev.put(newId, elemId);
                    if (precElem != -1) {
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(precElem), model.getElem(newId)));
                    }

                    // Recursively go through the model
                    for (String suc : petriNet.getSuccessor(elemId)) {
                        transformElem(petriNet.getElements().get(suc), newId, petriNet, model, pool, lane);
                    }

                }

                // Transitions...
            } else {
                transitions++;
                // Simple place with 1 incoming and one outgoing arc
                if (petriNet.getSuccessor(elemId).size() == 1 && petriNet.getPredecessor(elemId).size() == 1) {
                    loopSet[x] = elemId + ": simple transition, one incoming, one outgoing arc";
                    x++;
                    int newId = model.getNewId();
                    model.addActivity(new Activity(newId, elem.getLabel(), null, null));
                    transformedElems.put(elemId, newId);
                    transformedElemsRev.put(newId, elemId);
                    if (precElem != -1) {
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(precElem), model.getElem(newId)));
                    }
                    // Recursively go through the model
                    for (String suc : petriNet.getSuccessor(elemId)) {
                        transformElem(petriNet.getElements().get(suc), newId, petriNet, model, pool, lane);
                    }
                }

                //  Transition with multiple incoming arcs (AND-Join)
                if (petriNet.getSuccessor(elemId).size() == 1 && petriNet.getPredecessor(elemId).size() > 1) {
                    loopSet[x] = elemId + ": AND Join";
                    and_join++;
                    // Create new element
                    int newId = model.getNewId();
                    model.addGateway(new Gateway(newId, "", lane, pool, GatewayType.AND));
                    transformedElems.put(elemId, newId);
                    transformedElemsRev.put(newId, elemId);
                    if (precElem != -1) {
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(precElem), model.getElem(newId)));
                    }

                    // Recursively go through the model
                    String suc = petriNet.getSuccessor(elemId).get(0);
                    transformElem(petriNet.getElements().get(suc), newId, petriNet, model, pool, lane);
                }

                //  Transition with multiple incoming arcs (AND-Join)
                if (petriNet.getSuccessor(elemId).size() > 1 && petriNet.getPredecessor(elemId).size() == 1) {
                    loopSet[x] = elemId + ": AND Split";
                    and_split++;
                    // Create new element
                    int newId = model.getNewId();
                    model.addGateway(new Gateway(newId, "", lane, pool, GatewayType.AND));
                    transformedElems.put(elemId, newId);
                    transformedElemsRev.put(newId, elemId);
                    if (precElem != -1) {
                        model.addArc(new Arc(model.getNewId(), "", model.getElem(precElem), model.getElem(newId)));
                    }

                    // Recursively go through the model
                    for (String suc : petriNet.getSuccessor(elemId)) {
                        transformElem(petriNet.getElements().get(suc), newId, petriNet, model, pool, lane);
                    }
                }
            }
        } else {
            model.addArc(new Arc(model.getNewId(), "", model.getElem(precElem), model.getElem(transformedElems.get(elem.getId()))));
        }
    }

    @SuppressWarnings("unused")
    public void printConversion() {
        for (int i = loopSet.length; i > 0; i--) {
            if (loopSet[i - 1] != null) {
                System.out.println(i + ". Element: " + loopSet[i - 1]);
            }
        }
        System.out.println("Places: " + places);
        System.out.println("Transition " + transitions);
        System.out.println("XOR-Splits: " + xor_split);
        System.out.println("XOR-Joins: " + xor_join);
        System.out.println("AND-Splits: " + and_split);
        System.out.println("AND-Joins: " + and_join);
    }
}