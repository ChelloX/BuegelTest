package de.hpi.bpt.process.epc;

/**
 * The connector in the process model. Connector is the flow object.
 *
 * @author Artem Polyvyanyy
 */
interface IConnector extends IFlowObject {
    /**
     * Check if connector is of type XOR
     *
     * @return <code>true</code> if connector is of type XOR, <code>false</code> otherwise
     */
    boolean isXOR();

    /**
     * Check if connector is of type AND
     *
     * @return <code>true</code> if connector is of type AND, <code>false</code> otherwise
     */
    boolean isAND();

    /**
     * Check if connector is of type OR
     *
     * @return <code>true</code> if connector is of type OR, <code>false</code> otherwise
     */
    boolean isOR();
}