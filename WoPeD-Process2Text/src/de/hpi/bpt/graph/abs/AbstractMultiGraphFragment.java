package de.hpi.bpt.graph.abs;

import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Implementation of the multi graph fragment
 * Graph fragment is a collection of edges of the original graph
 *
 * @param <E> template for edge (extends IEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public class AbstractMultiGraphFragment<E extends IEdge<V>, V extends IVertex> extends AbstractMultiGraph<E, V> {

    protected final IGraph<E, V> graph;
    private final Map<E, E> esMap = new HashMap<>();

    /**
     * Constructor
     *
     * @param parent Parent graph of the fragment
     */
    protected AbstractMultiGraphFragment(IGraph<E, V> parent) {
        this.graph = parent;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractMultiDirectedHyperGraph#addEdge(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @Override
    public E addEdge(V v1, V v2) {
        if (this.graph == null) return null;

        // get edges of the original graph you can bind to
        Collection<E> edges = this.graph.getEdges(v1, v2);
        edges.removeAll(this.getOriginalEdges(v1, v2));
        if (edges.size() == 0) return null;

        E e = super.addEdge(v1, v2);
        this.esMap.put(e, edges.iterator().next());

        return e;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractMultiHyperGraph#removeEdge(de.hpi.bpt.hypergraph.abs.IHyperEdge)
     */
    @Override
    public E removeEdge(E e) {
        E result = super.removeEdge(e);
        this.esMap.remove(e);

        return result;
    }

    /**
     * Add non fragment edge to the fragment (there is no link to original graph)
     *
     * @param v1 Vertex
     * @param v2 Vertex
     * @return Edge added to the graph, <code>null</code> upon failure
     */
    protected E addNonFragmentEdge(V v1, V v2) {
        if (this.graph == null) return null;

        return super.addEdge(v1, v2);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractMultiHyperGraph#addVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @Override
    public V addVertex(V v) {
        if (this.graph != null && this.graph.contains(v))
            return super.addVertex(v);

        return null;
    }

    /**
     * Get original graph edge represented by the edge in the fragment
     *
     * @param e Edge in the fragment
     * @return Edge in the original graph
     */
    public E getOriginal(E e) {
        // TODO make efficient (get methode has a bug??!!)
        for (Entry<E, E> pairs : this.esMap.entrySet()) {
            if (pairs.getKey().getId().equals(e.getId()))
                return pairs.getValue();
        }

        return null;
    }

    /**
     * Get original graph edges between given pair of vertices
     *
     * @param v1 Vertex
     * @param v2 Vertex
     * @return Collection of graph edges between given pair of vertices
     */
    private Collection<E> getOriginalEdges(V v1, V v2) {
        Collection<E> result = new ArrayList<>();

        for (E e : this.esMap.values()) {
            if (e.connectsVertices(v1, v2))
                result.add(e);
        }

        return result;
    }
}
