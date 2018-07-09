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
     * Add edge to the graph
     *
     * @param v Vertex to create edge from
     * @return Edge that was added to the graph, <code>null</code> otherwise
     */
    E addEdge(V v);

    /**
     * Add edge to the graph
     *
     * @param vs Vertices to create edge from
     * @return Edge that was added to the graph, <code>null</code> otherwise
     */
    E addEdge(Collection<V> vs);

    /**
     * Add vertex to the graph
     *
     * @param v Vertex to add
     * @return Vertex that was added to the graph, <code>null</code> otherwise
     */
    V addVertex(V v);

    /**
     * Add vertices to the graph
     *
     * @param vs Vertices to add
     * @return Vertices that were added to the graph, <code>null</code> if no vertex was added
     */
    Collection<V> addVertices(Collection<V> vs);

    /**
     * Remove edge from the graph
     *
     * @param e Edge to remove
     * @return Edge that was removed from the graph, <code>null</code> if edge was not removed
     */
    E removeEdge(E e);

    /**
     * Remove edges from the graph
     *
     * @param es Edges to remove
     * @return Edges that were removed from the graph, <code>null</code> if no edge was removed
     */
    Collection<E> removeEdges(Collection<E> es);

    /**
     * Remove vertex from the graph
     *
     * @param v Vertex to remove
     * @return Vertex that was removed from the graph, <code>null</code> if vertex was not removed
     */
    V removeVertex(V v);

    /**
     * Remove vertices from the graph
     *
     * @param vs Vertices to remove
     * @return Vertices that were removed from the graph, <code>null</code> if no vertex was not removed
     */
    Collection<V> removeVertices(Collection<V> vs);

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
     * Get a collection of all edges that connect vertices 'vs'
     *
     * @param vs Collection of vertices
     * @return Collection of all edges that connect vertices 'vs'
     */
    Collection<E> getEdges(Collection<V> vs);

    /**
     * Check if graph contains edge
     *
     * @param e Edge to check
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    boolean contains(E e);

    /**
     * Check if graph contains vertex
     *
     * @param v Vertex to check
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    boolean contains(V v);

    /**
     * Get all vertices connected by some edge
     *
     * @return Connected vertices
     */
    Collection<V> getConnectedVertices();

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

    /**
     * Check if graph is a multi graph (multiple edges allowed)
     *
     * @return <code>true</code> if graph is a multi graph, <code>false</code> otherwise
     */
    boolean isMultiGraph();
}