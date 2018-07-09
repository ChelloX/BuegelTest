package de.hpi.bpt.hypergraph.abs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Abstract directed hyper edge implementation
 *
 * @param <V> Vertex type employed in the edge
 * @author Artem Polyvyanyy
 */
public class AbstractDirectedHyperEdge<V extends IVertex> extends AbstractHyperEdge<V> implements IDirectedHyperEdge<V> {
    private final Collection<V> source;
    private final Collection<V> target;

    @SuppressWarnings("unchecked")
    private AbstractMultiDirectedHyperGraph graph;

    @SuppressWarnings("unchecked")
    public AbstractDirectedHyperEdge(AbstractMultiDirectedHyperGraph g) {
        super(g);
        this.source = new ArrayList<>();
        this.target = new ArrayList<>();
        this.graph = g;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#addSourceAndTagetVertices(java.util.Collection, java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public Collection<V> addSourceAndTagetVertices(Collection<V> ss, Collection<V> ts) {
        if (this.graph == null) return null;
        if (ss == null && ts == null) return null;
        if (ss.size() == 0 && ts.size() == 0) return null;
        boolean result = false;

        if (!this.graph.isMultiGraph()) {
            Collection<V> nss = new ArrayList<>(this.source);
            Collection<V> nts = new ArrayList<>(this.target);

            if (ss.size() != 0) nss.addAll(ss);
            if (ts != null && ts.size() != 0) nts.addAll(ts);

            if (!this.checkEdge(nss, nts))
                return null;
        }

        Collection<V> vresult = new ArrayList<>();
        if (ss.size() != 0) {
            super.addVertices(ss);
            result = this.source.addAll(ss);
            this.graph.addIndex(this, ss);
            vresult.addAll(ss);
        }

        if (ts != null && ts.size() != 0) {
            super.addVertices(ts);
            result = this.target.addAll(ts);
            this.graph.addIndex(this, ts);
            vresult.addAll(ts);
        }

        return result ? vresult : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#removeSourceAndTagetVertices(java.util.Collection, java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public Collection<V> removeSourceAndTagetVertices(Collection<V> ss, Collection<V> ts) {
        if (this.graph == null) return null;
        if (ss == null && ts == null) return null;
        if (ss.size() == 0 && ts.size() == 0) return null;
        boolean result = false;

        if (!this.graph.isMultiGraph()) {
            Collection<V> nss = new ArrayList<>(this.source);
            Collection<V> nts = new ArrayList<>(this.target);

            if (ss.size() != 0) nss.removeAll(ss);
            if (ts != null && ts.size() != 0) nts.removeAll(ts);

            if (!this.checkEdge(nss, nts))
                return null;
        }

        Collection<V> vresult = new ArrayList<>();
        if (ss.size() != 0) {
            super.removeVertices(ss);
            result = this.source.removeAll(ss);
            this.graph.removeIndex(this, ss);
            vresult.addAll(ss);
        }

        if (ts != null && ts.size() != 0) {
            super.removeVertices(ts);
            result = this.target.removeAll(ts);
            this.graph.removeIndex(this, ts);
            vresult.addAll(ts);
        }

        return result ? vresult : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#getSourceVertices()
     */
    public Collection<V> getSourceVertices() {
        return new ArrayList<>(this.source);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#getTargetVertices()
     */
    public Collection<V> getTargetVertices() {
        return new ArrayList<>(this.target);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractHyperEdge#toString()
     */
    @Override
    public String toString() {
        String s = "";
        String t = "";

        Iterator<V> i = this.source.iterator();
        if (i.hasNext()) s = String.format("%1s", i.next());
        while (i.hasNext())
            s = String.format("%1s,%1s", s, i.next());

        i = this.target.iterator();
        if (i.hasNext()) t = String.format("%1s", i.next());
        while (i.hasNext())
            t = String.format("%1s,%1s", t, i.next());

        return String.format("[{%1s}->{%1s}]", s, t);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#hasSource(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public boolean hasSource(V v) {
        return this.source.contains(v);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#hasTarget(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public boolean hasTarget(V v) {
        return this.target.contains(v);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#hasSources(java.util.Collection)
     */
    public boolean hasSources(Collection<V> vs) {
        return this.source.containsAll(vs);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge#hasTargets(java.util.Collection)
     */
    public boolean hasTargets(Collection<V> vs) {
        return this.target.containsAll(vs);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractHyperEdge#removeVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @Override
    public V removeVertex(V v) {
        if (this.graph == null) return null;
        Collection<V> vs = new ArrayList<>();
        vs.add(v);
        boolean result = (this.hasSource(v) || this.hasTarget(v));
        this.removeSourceAndTagetVertices(vs, vs);

        return result ? v : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractHyperEdge#removeVertices(java.util.Collection)
     */
    @Override
    public Collection<V> removeVertices(Collection<V> vs) {
        if (this.graph == null) return null;
        return this.removeSourceAndTagetVertices(vs, vs);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractHyperEdge#addVertices(java.util.Collection)
     */
    @Override
    public Collection<V> addVertices(Collection<V> vs) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    private boolean checkEdge(Collection<V> ss, Collection<V> ts) {
        Collection<IDirectedHyperEdge<V>> es = this.graph.getEdgesWithSourcesAndTargets(ss, ts);
        if (es.size() > 0) {
            for (IDirectedHyperEdge<V> e : es) {
                if (e.getSourceVertices().size() == ss.size() && e.getTargetVertices().size() == ts.size())
                    return false;
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractHyperEdge#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
        this.graph = null;
    }
}