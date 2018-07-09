package de.hpi.bpt.process.epc;

/**
 * A flow object in an EPC. Events, functions, connectors, and process interfaces are the flow objects.
 *
 * @author Artem Polyvyanyy
 */
interface IFlowObject extends INode {
    /**
     * Get flow object type
     *
     * @return Flow object type
     */
    FlowObjectType getType();
}