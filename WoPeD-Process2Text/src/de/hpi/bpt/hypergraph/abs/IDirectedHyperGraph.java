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
     * Add edge to the graph
     *
     * @param ss Collection of source vertices to create edge from
     * @param ts Collection of target vertices to create edge from
     * @return Edge that was added to the graph, <code>null</code> otherwise
     */
    E addEdge(Collection<V> ss, Collection<V> ts);

    /**
     * Get collection of edges that contain target vertex 'v'
     *
     * @param v Vertex
     * @return Collection of edges that contain target vertex 'v'
     */
    Collection<E> getEdgesWithTarget(V v);

    /**
     * Get collection of edges that contain source vertex 'v'
     *
     * @param v Vertex
     * @return Collection edges that contain source vertex 'v'
     */
    Collection<E> getEdgesWithSource(V v);

    /**
     * Get collection of edges that contain source vertex 's' and target vertex 't'
     *
     * @param s Source vertex
     * @param t Target vertex
     * @return Collection of edges that contain source vertex 's' and target vertex 't'
     */
    Collection<E> getEdgesWithSourceAndTarget(V s, V t);

    /**
     * Get collection of edges that contain source vertices 'ss' and target vertices 'ts'
     *
     * @param ss Source vertices
     * @param ts Target vertices
     * @return Collection of edges that contain source vertices 'ss' and target vertices 'ts'
     */
    Collection<E> getEdgesWithSourcesAndTargets(Collection<V> ss, Collection<V> ts);

    /**
     * Get collection of vertices that are predecessors to a given vertex
     *
     * @param v Vertex
     * @return Collection of predecessor vertices
     */
    Collection<V> getPredecessors(V v);

    /**
     * Get first arbitrary predecessor of the vertex
     *
     * @param v Vertex
     * @return Arbitrary predecessor of vertex, <code>null</code> if it does not exist
     */
    V getFirstPredecessor(V v);

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