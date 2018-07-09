package de.hpi.bpt.graph.abs;

import de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

/**
 * Directed graph interface
 *
 * @param <E> template for edge (extends IDirectedEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public interface IDirectedGraph<E extends IDirectedEdge<V>, V extends IVertex> extends IDirectedHyperGraph<E, V>, IGraph<E, V> {
}
