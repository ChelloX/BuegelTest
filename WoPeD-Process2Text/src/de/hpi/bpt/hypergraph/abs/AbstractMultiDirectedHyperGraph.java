package de.hpi.bpt.hypergraph.abs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Multi directed hyper graph implementation
 * Multi directed hyper graph is collection of directed hyper edges and disconnected vertices
 * Multi edges are allowed
 *
 * @param <E> template for hyper edge (extends IDirectedHyperEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public class AbstractMultiDirectedHyperGraph<E extends IDirectedHyperEdge<V>, V extends IVertex>
        extends AbstractMultiHyperGraph<E, V>
        implements IDirectedHyperGraph<E, V> {

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#addEdge(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @SuppressWarnings("unchecked")
    public E addEdge(V s, V t) {
        if (s == null || t == null) return null;

        E e = (E) new AbstractDirectedHyperEdge<V>(this);
        Collection<V> ss = new ArrayList<>();
        ss.add(s);
        Collection<V> ts = new ArrayList<>();
        ts.add(t);
        e.addSourceAndTagetVertices(ss, ts);
        return e;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getEdgesWithSource(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    Collection<E> getEdgesWithSource(V v) {
        Collection<E> result = new ArrayList<>();

        Collection<E> es = this.getEdges(v);
        for (E e : es) {
            if (e.hasSource(v))
                result.add(e);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getEdgesWithSourceAndTarget(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public Collection<E> getEdgesWithSourceAndTarget(V s, V t) {
        Collection<E> result = new ArrayList<>();

        Collection<E> es = this.getEdges(s);
        for (E e : es) {
            if (e.hasSource(s) && e.hasTarget(t))
                result.add(e);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getEdgesWithSourcesAndTargets(java.util.Collection, java.util.Collection)
     */
    public Collection<E> getEdgesWithSourcesAndTargets(Collection<V> ss, Collection<V> ts) {
        Collection<E> result = new ArrayList<>();
        if (ss == null && ts == null) return result;
        if (ss.size() == 0 && ts.size() == 0) return result;

        if (ss.size() > 0) {
            V v = ss.iterator().next();
            Collection<E> es = this.getEdgesWithSource(v);
            if (es == null) return result;
            for (E e : es) {
                if (e.hasSources(ss) && e.hasTargets(ts))
                    result.add(e);
            }
        } else {
            V v = ts.iterator().next();
            Collection<E> es = this.getEdgesWithTarget(v);
            if (es == null) return result;
            for (E e : es) {
                if (e.hasSources(ss) && e.hasTargets(ts))
                    result.add(e);
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getEdgesWithTarget(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    Collection<E> getEdgesWithTarget(V v) {
        Collection<E> result = new ArrayList<>();

        for (E e : this.getEdges(v)) {
            if (e.hasTarget(v))
                result.add(e);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getIncomingEdges(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public Collection<E> getIncomingEdges(V v) {
        return this.getEdgesWithTarget(v);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getOutgoingEdges(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public Collection<E> getOutgoingEdges(V v) {
        return this.getEdgesWithSource(v);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getPredecessors(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public Collection<V> getPredecessors(V v) {
        Set<V> result = new HashSet<>();

        Collection<E> es = this.getIncomingEdges(v);
        for (E e : es) result.addAll(e.getSourceVertices());

        return new ArrayList<>(result);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getFirstPredecessor(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public V getFirstPredecessor(V v) {
        Collection<V> vs = this.getPredecessors(v);
        if (vs.size() == 0) return null;
        return vs.iterator().next();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperGraph#getSuccessors(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public Collection<V> getSuccessors(V v) {
        Set<V> result = new HashSet<>();

        Collection<E> es = this.getOutgoingEdges(v);
        for (E e : es) result.addAll(e.getTargetVertices());

        return new ArrayList<>(result);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperGraph#isMultiGraph()
     */
    @Override
    public boolean isMultiGraph() {
        return true;
    }

    /**
     * Check if edge with collection of vertices exists in the graph
     *
     * @param ss Collection of vertices
     * @return <code>true</code> if edge exists, <code>false</code> otherwise
     */
    protected boolean checkEdge(Collection<V> ss, Collection<V> ts) {
        Collection<E> es = this.getEdgesWithSourcesAndTargets(ss, ts);
        if (es.size() > 0) {
            for (E e : es) {
                if (e.getSourceVertices().size() == ss.size() && e.getTargetVertices().size() == ts.size())
                    return false;
            }
        }

        return true;
    }
}