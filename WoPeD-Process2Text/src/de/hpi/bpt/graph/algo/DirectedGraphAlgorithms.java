package de.hpi.bpt.graph.algo;

import de.hpi.bpt.graph.abs.IDirectedEdge;
import de.hpi.bpt.graph.abs.IDirectedGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Collection of directed graph algorithms
 *
 * @param <E> template for edge (extends IDirectedEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public class DirectedGraphAlgorithms<E extends IDirectedEdge<V>, V extends IVertex> {
    /**
     * Get boundary vertices in the directed graph - vertices without predecessors or successors
     *
     * @param g Directed graph
     * @return Collection of boundary vertices
     */
    private Collection<V> getBoundaryVertices(IDirectedGraph<E, V> g) {
        Collection<V> result = new ArrayList<>();

        for (V v : g.getVertices()) {
            int in = g.getIncomingEdges(v).size();
            int out = g.getOutgoingEdges(v).size();
            if (in == 0 || out == 0) result.add(v);
        }

        return result;
    }

    /**
     * Get directed graph input vertices
     *
     * @param g Directed graph
     * @return Collection of graph input vertices
     */
    public Collection<V> getInputVertices(IDirectedGraph<E, V> g) {
        Collection<V> result = new ArrayList<>();
        for (V v : this.getBoundaryVertices(g)) {
            if (g.getPredecessors(v).size() == 0)
                result.add(v);
        }

        return result;
    }

    /**
     * Get directed graph output vertices
     *
     * @param g Directed graph
     * @return Collection of graph output vertices
     */
    public Collection<V> getOutputVertices(IDirectedGraph<E, V> g) {
        Collection<V> result = new ArrayList<>();
        for (V v : this.getBoundaryVertices(g)) {
            if (g.getSuccessors(v).size() == 0)
                result.add(v);
        }

        return result;
    }
}