package de.hpi.bpt.process;

import de.hpi.bpt.graph.abs.AbstractDirectedEdge;
import de.hpi.bpt.graph.abs.AbstractMultiDirectedGraph;

public class ControlFlow extends AbstractDirectedEdge<Node> {
    @SuppressWarnings("unchecked")
    ControlFlow(AbstractMultiDirectedGraph g, Node source, Node target) {
        super(g, source, target);
    }
}