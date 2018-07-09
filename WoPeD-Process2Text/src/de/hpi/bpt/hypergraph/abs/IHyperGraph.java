package de.hpi.bpt.hypergraph.abs;

import java.util.Collection;

/**
 * Interface describing hyper graph behavior
 * Hyper graph is collection of hyper edges and disconnected vertices
 *
 * @param <E> Edge type employed in the graph
 * @param <V> Vertex type employed in the graph
 * @author Artem Polyvyanyy
 */
public interface IHyperGraph<E extends IHyperEdge<V>, V extends IVertex> extends IGObject {
    /**
     * Add vertex to the graph
     *
     * @param v Vertex to add
     * @return Vertex that was added to the graph, <code>null</code> otherwise
     */
    V addVertex(V v);

    /**
     * Remove edge from the graph
     *
     * @param e Edge to remove
     * @return Edge that was removed from the graph, <code>null</code> if edge was not removed
     */
    E removeEdge(E e);

    /**
     * Remove vertex from the graph
     *
     * @param v Vertex to remove
     * @return Vertex that was removed from the graph, <code>null</code> if vertex was not removed
     */
    V removeVertex(V v);

    /**
     * Get graph vertices
     *
     * @return Graph vertices
     */
    Collection<V> getVertices();

    /**
     * Get graph edges
     *
     * @return Graph edges
     */
    Collection<E> getEdges();

    /**
     * Get a collection of all edges that connect vertex 'v'
     *
     * @param v Vertex
     * @return Collection of all edges that connect vertex 'v'
     */
    Collection<E> getEdges(V v);

    /**
     * Check if graph contains vertex
     *
     * @param v Vertex to check
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    boolean contains(V v);

    /**
     * Get all vertices not connected by any edge
     *
     * @return Disconnected vertices
     */
    Collection<V> getDisconnectedVertices();

    /**
     * Get the number of vertices in this graph
     *
     * @return The number of vertices in this graph
     */
    int countVertices();
}