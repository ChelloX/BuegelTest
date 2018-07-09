package de.hpi.bpt.process;

import de.hpi.bpt.hypergraph.abs.Vertex;

public abstract class Node extends Vertex {
    Node() {
        super();
    }

    Node(String name) {
        super(name);
    }
}