package de.hpi.bpt.hypergraph.abs;

import java.util.Collection;

/**
 * Interface describing directed hyper graph behavior
 * Directed hyper graph is collection of directed hyper edges and disconnected vertices
 *
 * @param <E> Edge type employed in the graph
 * @param <V> Vertex type employed in the graph
 * @author Artem Polyvyanyy
 */
public interface IDirectedHyperGraph<E extends IDirectedHyperEdge<V>, V extends IVertex> extends IHyperGraph<E, V> {
    /**
     * Add edge to the graph
     *
     * @param s Source vertex to create edge from
     * @param t Target vertex to create edge from
     * @return Edge that was added to the graph, <code>null</code> otherwise
     */
    E addEdge(V s, V t);

    /**
     * Get collection of vertices that are predecessors to a given vertex
     *
     * @param v Vertex
     * @return Collection of predecessor vertices
     */
    Collection<V> getPredecessors(V v);

    /**
     * Get collection of vertices that are successors to a given vertex
     *
     * @param v Vertex
     * @return Collection of successor vertices
     */
    Collection<V> getSuccessors(V v);

    /**
     * Get vertex incoming edges
     *
     * @param v Vertex
     * @return Vertex incoming edges
     */
    Collection<E> getIncomingEdges(V v);

    /**
     * Get vertex outgoing edges
     *
     * @param v Vertex
     * @return Vertex outgoing edges
     */
    Collection<E> getOutgoingEdges(V v);
}