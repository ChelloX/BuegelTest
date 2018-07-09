package de.hpi.bpt.process;

import de.hpi.bpt.graph.abs.AbstractDirectedGraph;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Basic process model implementation
 *
 * @author Artem Polyvyanyy
 */
public class Process extends AbstractDirectedGraph<ControlFlow, Node> {
    private String name;

    /**
     * Construct an empty process
     */
    public Process() {
        this.name = "";
    }

    /**
     * Construct an empty process with name
     */
    public Process(String name) {
        this.name = name;
    }

    /**
     * Create a control flow
     *
     * @param from Source node
     * @param to   Target node
     * @return The fresh control flow, or <code>null</code> if control flow between source and target already exists
     */
    public ControlFlow addControlFlow(Node from, Node to) {
        if (from == null || to == null) return null;

        Collection<Node> ss = new ArrayList<>();
        ss.add(from);
        Collection<Node> ts = new ArrayList<>();
        ts.add(to);

        if (!this.checkEdge(ss, ts)) return null;

        return new ControlFlow(this, from, to);
    }

    /**
     * Get tasks of the process
     *
     * @return A collection of process tasks
     */
    public Collection<Task> getTasks() {
        Collection<Task> result = new ArrayList<>();

        Collection<Node> nodes = this.getVertices();
        for (Node obj : nodes) {
            if (obj instanceof Task)
                result.add((Task) obj);
        }

        return result;
    }

    private Collection<Gateway> getGateways(Collection<GatewayType> types) {
        Collection<Gateway> result = new ArrayList<>();

        Collection<Node> nodes = this.getVertices();
        for (Node obj : nodes) {
            if (obj instanceof Gateway) {
                Gateway g = (Gateway) obj;
                if (types.contains(g.getGatewayType()))
                    result.add(g);
            }
        }

        return result;
    }

    /**
     * Get gateways of the process
     *
     * @return A collection of process gateways
     */
    public Collection<Gateway> getGateways() {
        Collection<GatewayType> types = new ArrayList<>();
        types.add(GatewayType.AND);
        types.add(GatewayType.XOR);
        types.add(GatewayType.OR);
        types.add(GatewayType.UNDEFINED);
        return this.getGateways(types);
    }

    /**
     * Get process nodes
     *
     * @return A collection of process nodes
     */
    public Collection<Node> getNodes() {
        return this.getVertices();
    }

    /**
     * Get control flow of the process
     *
     * @return A collection of process flows of the process
     */
    public Collection<ControlFlow> getControlFlow() {
        return this.getEdges();
    }

    /**
     * Get process name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set process name
     *
     * @param name Process name
     */
    public void setName(String name) {
        this.name = name;
    }
}