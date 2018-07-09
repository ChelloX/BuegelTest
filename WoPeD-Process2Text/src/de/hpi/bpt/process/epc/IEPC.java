package de.hpi.bpt.process.epc;

import de.hpi.bpt.graph.abs.IDirectedGraph;

import java.util.Collection;

/**
 * A business process captured in the event-driven process chain (EPC) notation.
 *
 * @author Artem Polyvyanyy
 */
public interface IEPC<CF extends IControlFlow<FO>,
        FO extends IFlowObject,
        E extends IEvent,
        F extends IFunction,
        NFO extends INonFlowObject> extends IDirectedGraph<CF, FO> {

    /**
     * Returns all the flow objects of this EPC
     * The flow objects are: events, functions, connectors, and process interfaces
     *
     * @return Collection of flow objects of this EPC
     */
    Collection<FO> getFlowObjects();

    /**
     * Get all the events of this EPC
     *
     * @return Collection of EPC events
     */
    Collection<E> getEvents();

    /**
     * Get all the functions of this EPC
     *
     * @return Collection of EPC functions
     */
    Collection<F> getFunctions();

    /**
     * Filter out flow objects of a certain type
     *
     * @param objs Collection of flow objects
     * @param type Flow object type
     * @return Sub-collection of flow objects of certain type
     */
    Collection<FO> filter(Collection<FO> objs, FlowObjectType type);

    /**
     * Filter out non flow objects of a certain type
     *
     * @param objs Collection of flow objects
     * @param type Flow object type
     * @return Sub-collection of flow objects of certain type
     */
    Collection<NFO> filter(Collection<NFO> objs, NonFlowObjectType type);
}