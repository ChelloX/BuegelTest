package de.hpi.bpt.hypergraph.abs;

import java.util.Collection;

/**
 * Interface describing directed hyper edge behavior
 * Directed hyper edge is composed of two directed sets of vertices
 *
 * @param <V> Vertex type employed in the edge
 * @author Artem Polyvyanyy
 */
public interface IDirectedHyperEdge<V extends IVertex> extends IHyperEdge<V> {
    /**
     * Add source vertex to the edge
     *
     * @param v Source vertex to add
     * @return Source vertex added to the edge, <code>null</code> upon failure
     */
    V addSourceVertex(V v);

    /**
     * Add target vertex to the edge
     *
     * @param v Target vertex to add
     * @return Target vertex added to the edge, <code>null</code> upon failure
     */
    V addTargetVertex(V v);

    /**
     * Add source and target vertices to the edge
     *
     * @param ss Source vertices
     * @param ts Target vertices
     * @return Collection of vertices added to the edge, <code>null</code> upon failure
     */
    Collection<V> addSourceAndTagetVertices(Collection<V> ss, Collection<V> ts);

    /**
     * Remove source vertex from the edge
     *
     * @param v Source vertex to remove
     * @return Source vertex that was removed, <code>null</code> upon failure
     */
    V removeSourceVertex(V v);

    /**
     * Remove target vertex from the edge
     *
     * @param v Target vertex to remove
     * @return Target vertex that was removed, <code>null</code> upon failure
     */
    V removeTargetVertex(V v);

    /**
     * Remove source and target vertices from the edge
     *
     * @param ss Source vertices
     * @param ts Target vertices
     * @return Collection of vertices removed from the edge, <code>null</code> upon failure
     */
    Collection<V> removeSourceAndTagetVertices(Collection<V> ss, Collection<V> ts);

    /**
     * Get source vertices
     *
     * @return Source vertices
     */
    Collection<V> getSourceVertices();

    /**
     * Get target vertices
     *
     * @return Target vertices
     */
    Collection<V> getTargetVertices();

    /**
     * Check if the edge has source vertex 'v'
     *
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    boolean hasSource(V v);

    /**
     * Check if the edge has source vertices 'vs'
     *
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    boolean hasSources(Collection<V> vs);

    /**
     * Check if the edge has target vertex 'v'
     *
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    boolean hasTarget(V v);

    /**
     * Check if the edge has target vertices 'vs'
     *
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    boolean hasTargets(Collection<V> vs);
}