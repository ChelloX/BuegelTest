package de.hpi.bpt.graph.abs;

import de.hpi.bpt.hypergraph.abs.IHyperEdge;
import de.hpi.bpt.hypergraph.abs.IVertex;

/**
 * Interface describing binary graph edge behavior (constrained by implementation)
 * Binary edge is an edge that connects exactly two vertices
 *
 * @param <V> Vertex type employed in the edge
 * @author Artem Polyvyanyy
 */
public interface IEdge<V extends IVertex> extends IHyperEdge<V> {
    /**
     * Set graph edge vertices
     *
     * @param v1 Vertex
     * @param v2 Vertex
     */
    void setVertices(V v1, V v2);

    /**
     * Get other vertex than specified
     *
     * @param v Vertex
     * @return Other connected vertex by the edge
     */
    V getOtherVertex(V v);

    /**
     * Determines whether this edge is a self-loop
     *
     * @return <code>true</code> if this edge is a self-loop, <code>false</code> otherwise
     */
    boolean isSelfLoop();

    /**
     * Get first vertex of the edge
     *
     * @return First vertex of the edge, <code>null</code> if such does not exist
     */
    V getV1();

    /**
     * Get second vertex of the edge
     *
     * @return Second vertex of the edge, <code>null</code> if such does not exist
     */
    V getV2();

    /**
     * Check if the edge connects two vertices
     *
     * @param v1 Vertex
     * @param v2 Vertex
     * @return <code>true</code> if this edge connects v1 and v2, <code>false</code> otherwise
     */
    boolean connectsVertices(V v1, V v2);
}
