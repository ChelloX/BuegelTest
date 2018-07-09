package de.hpi.bpt.graph.abs;

import de.hpi.bpt.hypergraph.abs.IHyperGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.Collection;

/**
 * Graph interface
 * Graph consists of IEdge(s) and IVertex(ies)
 *
 * @param <E> template for edge (extends IEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public interface IGraph<E extends IEdge<V>, V extends IVertex> extends IHyperGraph<E, V> {
    /**
     * Get collection of edges that connect two vertices
     *
     * @param v1 Vertex
     * @param v2 Vertex
     * @return Collection of edges that connect two vertices
     */
    Collection<E> getEdges(V v1, V v2);

    /**
     * Add edge to the graph
     *
     * @param v1 Vertex
     * @param v2 Vertex
     * @return Edge added to the graph, <code>null</code> upon failure
     */
    E addEdge(V v1, V v2);
}
