package de.hpi.bpt.process.epc;

import de.hpi.bpt.graph.abs.AbstractDirectedGraph;

import java.util.ArrayList;
import java.util.Collection;

/**
 * EPC implementation
 *
 * @author Artem Polyvyanyy
 */
public class EPC extends AbstractDirectedGraph<ControlFlow, FlowObject> implements IEPC<ControlFlow, FlowObject, Event, Function, NonFlowObject> {
    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.process.epc.IEPC#filter(java.util.Collection, de.hpi.bpt.process.epc.FlowObjectType)
     */
    public Collection<FlowObject> filter(Collection<FlowObject> objs, FlowObjectType type) {
        Collection<FlowObject> result = new ArrayList<>();

        for (FlowObject obj : objs) {
            if (obj.getType() == type)
                result.add(obj);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.process.epc.IEPC#filter(java.util.Collection, de.hpi.bpt.process.epc.NonFlowObjectType)
     */
    public Collection<NonFlowObject> filter(Collection<NonFlowObject> objs, NonFlowObjectType type) {
        Collection<NonFlowObject> result = new ArrayList<>();

        for (NonFlowObject obj : objs) {
            if (obj.getType() == type)
                result.add(obj);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.process.epc.IEPC#getEvents()
     */
    public Collection<Event> getEvents() {
        Collection<Event> result = new ArrayList<>();

        Collection<FlowObject> flowObjs = this.getFlowObjects();
        for (FlowObject obj : flowObjs) {
            if (obj instanceof Event)
                result.add((Event) obj);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.process.epc.IEPC#getFlowObjects()
     */
    public Collection<FlowObject> getFlowObjects() {
        return super.getVertices();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.process.epc.IEPC#getFunctions()
     */
    public Collection<Function> getFunctions() {
        Collection<Function> result = new ArrayList<>();

        Collection<FlowObject> flowObjs = this.getFlowObjects();
        for (FlowObject obj : flowObjs) {
            if (obj instanceof Function)
                result.add((Function) obj);
        }

        return result;
    }
}