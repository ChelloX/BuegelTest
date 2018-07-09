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
     * Add vertex to the edge
     *
     * @param v Vertex to add
     * @return Vertex added to the edge, <code>null</code> upon failure
     */
    V addVertex(V v);

    /**
     * Add collection of vertices to the edge
     *
     * @param vs Collection of vertices to add
     * @return Collection of vertices added to the edge, <code>null</code> if no vertex was added
     */
    Collection<V> addVertices(Collection<V> vs);

    /**
     * Remove vertex from the edge
     *
     * @param v Vertex to remove
     * @return Vertex that was removed, <code>null</code> upon failure
     */
    V removeVertex(V v);

    /**
     * Remove collection of vertices from the edge
     *
     * @param vs Collection of vertices to remove
     * @return Collection of vertices removed from the edge, <code>null</code> if no vertex was removed
     */
    Collection<V> removeVertices(Collection<V> vs);

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