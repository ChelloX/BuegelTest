package de.hpi.bpt.hypergraph.abs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Multi (same edges are allowed) hyper graph implementation
 * Hyper graph is collection of hyper edges and disconnected vertices
 *
 * @param <E> template for hyper edge (extends IHyperEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public class AbstractMultiHyperGraph<E extends IHyperEdge<V>, V extends IVertex>
        extends AbstractGraphNotifier<E, V>
        implements IHyperGraph<E, V> {
    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#removeEdge(de.hpi.bpt.hypergraph.abs.IHyperEdge)
     */
    public E removeEdge(E e) {
        if (e == null) return null;
        if (this.contains(e)) {
            e.destroy();
            return e;
        } else return null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#removeEdges(java.util.Collection)
     */
    public Collection<E> removeEdges(Collection<E> es) {
        if (es == null) return null;
        Collection<E> result = new ArrayList<>();

        for (E e : es) {
            if (this.removeEdge(e) != null)
                result.add(e);
        }

        return (result.size() > 0) ? result : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#addVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public V addVertex(V v) {
        if (v == null) return null;
        if (this.contains(v)) return null;
        this.vertices.put(v, new HashSet<>());

        return v;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#addVertices(java.util.Collection)
     */
    public Collection<V> addVertices(Collection<V> vs) {
        if (vs == null) return null;
        Collection<V> result = new ArrayList<>();

        for (V v : vs) {
            if (this.addVertex(v) != null)
                result.add(v);
        }

        return (result.size() > 0) ? result : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#removeVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public V removeVertex(V v) {
        if (v == null) return null;

        if (this.contains(v)) {
            Collection<E> es = this.getEdges(v);
            for (E e : es) e.removeVertex(v);

            this.vertices.remove(v);
            return v;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#getVertices()
     */
    public Collection<V> getVertices() {
        Collection<V> result = this.vertices.keySet();
        return new ArrayList<>(result);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#getEdges()
     */
    public Collection<E> getEdges() {
        Collection<E> result = this.edges.keySet();
        return new ArrayList<>(result);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#getEdges(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public Collection<E> getEdges(V v) {
        Collection<E> result = this.vertices.get(v);
        return (result == null) ? (new ArrayList<>()) : (new ArrayList<>(result));
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#getEdges(java.util.Collection)
     */
    public Collection<E> getEdges(Collection<V> vs) {
        Collection<E> result = new ArrayList<>();
        if (vs == null) return result;
        if (vs.size() == 0) return result;

        V v = vs.iterator().next();
        Collection<E> es = this.getEdges(v);
        for (E e : es) {
            if (e.connectsVertices(vs))
                result.add(e);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (V v : this.getDisconnectedVertices()) {
            res.append(v);
        }

        for (E e : this.getEdges()) res.append(e);

        return res.toString().trim();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#contains(de.hpi.bpt.hypergraph.abs.IHyperEdge)
     */
    boolean contains(E e) {
        return this.getEdges().contains(e);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#contains(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public boolean contains(V v) {
        return this.getVertices().contains(v);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#getConnectedVertices()
     */
    Collection<V> getConnectedVertices() {
        Set<V> result = new HashSet<>();

        for (E e : this.getEdges()) result.addAll(e.getVertices());

        return new ArrayList<>(result);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#getDisconnectedVertices()
     */
    public Collection<V> getDisconnectedVertices() {
        Collection<V> result = new ArrayList<>(this.getVertices());
        result.removeAll(this.getConnectedVertices());

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#countVertices()
     */
    public int countVertices() {
        return this.vertices.size();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#isMultiGraph()
     */
    public boolean isMultiGraph() {
        return true;
    }
}