package de.hpi.bpt.graph.abs;

import de.hpi.bpt.hypergraph.abs.AbstractMultiHyperGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Multi (same edges are allowed) graph implementation
 *
 * @param <E> template for edge (extends IEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public class AbstractMultiGraph<E extends IEdge<V>, V extends IVertex>
        extends AbstractMultiHyperGraph<E, V>
        implements IGraph<E, V> {
    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IGraph#getEdge(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public E getEdge(V v1, V v2) {
        Collection<E> es = this.vertices.get(v1);
        if (es == null) return null;

        for (E e : es) {
            if (e.connectsVertex(v1) && e.connectsVertex(v2))
                return e;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IGraph#addEdge(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @SuppressWarnings("unchecked")
    public E addEdge(V v1, V v2) {
        if (v1 == null || v2 == null) return null;
        return (E) new AbstractEdge<>(this, v1, v2);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractMultiHyperGraph#isMultiGraph()
     */
    @Override
    public boolean isMultiGraph() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractMultiHyperGraph#removeVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @Override
    public V removeVertex(V v) {
        if (v == null) return null;

        if (this.contains(v)) {
            Collection<E> es = this.getEdges(v);
            for (E e : es) this.removeEdge(e);

            this.vertices.remove(v);
            return v;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractMultiHyperGraph#removeVertices(java.util.Collection)
     */
    protected Collection<V> removeVertices(Collection<V> vs) {
        Collection<V> result = new ArrayList<>();

        for (V v : vs) {
            if (this.removeVertex(v) != null)
                result.add(v);
        }
        return (result.size() > 0) ? result : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IGraph#getEdges(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public Collection<E> getEdges(V v1, V v2) {
        if (v1 == null || v2 == null) return new ArrayList<>();
        Collection<V> vs = new ArrayList<>();
        vs.add(v1);
        vs.add(v2);

        return this.getEdges(vs);
    }
}
