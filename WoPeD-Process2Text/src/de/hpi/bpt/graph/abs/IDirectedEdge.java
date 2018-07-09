package de.hpi.bpt.graph.abs;

import de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge;
import de.hpi.bpt.hypergraph.abs.IVertex;

/**
 * Interface describing directed binary graph edge behavior (constrained by implementation)
 * Directed binary edge is an edge that connects exactly two vertices and makes a difference between source and target
 *
 * @param <V> Vertex type employed in the edge
 * @author Artem Polyvyanyy
 */
public interface IDirectedEdge<V extends IVertex> extends IDirectedHyperEdge<V>, IEdge<V> {
    /**
     * Get source vertex
     *
     * @return Source vertex
     */
    V getSource();

    /**
     * Get target vertex
     *
     * @return Target vertex
     */
    V getTarget();

    /**
     * Set directed graph edge vertices
     *
     * @param s Source vertex
     * @param t Target vertex
     */
    void setVertices(V s, V t);
}
