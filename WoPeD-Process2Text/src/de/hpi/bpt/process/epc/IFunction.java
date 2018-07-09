package de.hpi.bpt.process.epc;

/**
 * EPC function interface
 *
 * @author Artem Polyvyanyy
 */
interface IFunction extends IFlowObject {
    /**
     * Get function duration in milliseconds
     *
     * @return Function duration
     */
    long getDuration();

    /**
     * Set function duration
     *
     * @param duration Duration in milliseconds
     */
    void setDuration(long duration);
}