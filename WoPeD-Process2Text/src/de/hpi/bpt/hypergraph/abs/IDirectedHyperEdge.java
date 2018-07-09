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
     * Add source and target vertices to the edge
     *
     * @param ss Source vertices
     * @param ts Target vertices
     * @return Collection of vertices added to the edge, <code>null</code> upon failure
     */
    Collection<V> addSourceAndTagetVertices(Collection<V> ss, Collection<V> ts);

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