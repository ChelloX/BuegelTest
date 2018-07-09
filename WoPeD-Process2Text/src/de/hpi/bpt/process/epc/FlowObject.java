package de.hpi.bpt.process.epc;

/**
 * EPC flow object implementation
 * 
 * @author Artem Polyvyanyy
 */
public abstract class FlowObject extends Node implements IFlowObject {
	FlowObject() {
		super();
	}

	FlowObject(String name, String desc) {
		super(name, desc);
	}

	FlowObject(String name) {
		super(name);
	}

	public FlowObjectType getType() {
		return FlowObjectType.UNDEFINED;
	}
}