package de.hpi.bpt.process.epc;

/**
 * EPC non flow object interface
 *
 * @author Artem Polyvyanyy
 */
interface INonFlowObject extends INode {
    /**
     * Get non flow object type
     *
     * @return Non flow object type
     */
    NonFlowObjectType getType();
}