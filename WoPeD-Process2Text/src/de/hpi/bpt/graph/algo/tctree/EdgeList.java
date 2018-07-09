package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.LinkedList;

/**
 * This EdgeList is an abstraction of the underlying list type, which stores edges.
 *
 * @param <E> Edge class
 * @param <V> Vertex class
 * @author Christian Wiggert
 */
class EdgeList<E extends IEdge<V>, V extends IVertex> extends LinkedList<E> {
    private static final long serialVersionUID = 2649534465829537370L;

    EdgeList(E edge) {
        super();
        this.add(edge);
    }

    EdgeList() {
        super();
    }
}