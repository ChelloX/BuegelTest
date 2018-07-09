package de.hpi.bpt.process.epc;

/**
 * Abstract non flow object implementation
 * @author Artem Polyvyanyy
 *
 */
public abstract class NonFlowObject extends Node implements INonFlowObject {
	NonFlowObject() {
		super();
	}

	NonFlowObject(String name, String desc) {
		super(name, desc);
	}

	NonFlowObject(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * @see de.hpi.bpt.process.epc.meta.INonFlowObject#getType()
	 */
	public NonFlowObjectType getType() {
		return NonFlowObjectType.UNDEFINED;
	}
}