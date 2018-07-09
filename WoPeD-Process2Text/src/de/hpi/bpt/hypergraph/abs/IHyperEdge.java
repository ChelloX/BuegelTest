package de.hpi.bpt.hypergraph.abs;

import java.util.Collection;

/**
 * Interface describing hyper edge behavior
 * Hyper edge is a set of vertices
 *
 * @param <V> Vertex type employed in the edge
 * @author Artem Polyvyanyy
 */
public interface IHyperEdge<V extends IVertex> extends IGObject {
    /**
     * Remove vertex from the edge
     *
     * @param v Vertex to remove
     * @return Vertex that was removed, <code>null</code> upon failure
     */
    V removeVertex(V v);

    /**
     * Check if the edge connects vertex
     *
     * @param v Vertex to check
     * @return <code>true</code> if the edge connects vertex, <code>false<code> otherwise
     */
    boolean connectsVertex(V v);

    /**
     * Check if the edge connects vertices
     *
     * @param vs Collection of vertices to check
     * @return <code>true</code> if the edge connects all the vertices, <code>false<code> otherwise
     */
    boolean connectsVertices(Collection<V> vs);

    /**
     * Get vertices of the edge
     *
     * @return Collection of the edge vertices
     */
    Collection<V> getVertices();

    /**
     * Destroy the edge, unlink from the graph
     */
    void destroy();
}