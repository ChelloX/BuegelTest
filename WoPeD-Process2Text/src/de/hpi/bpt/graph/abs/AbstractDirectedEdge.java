package de.hpi.bpt.graph.abs;

import de.hpi.bpt.hypergraph.abs.AbstractDirectedHyperEdge;
import de.hpi.bpt.hypergraph.abs.IDirectedHyperEdge;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract directed edge implementation
 * Directed connects two vertices: source and target
 *
 * @param <V> Vertex type employed in the edge
 * @author Artem Polyvyanyy
 */
public class AbstractDirectedEdge<V extends IVertex> extends AbstractDirectedHyperEdge<V> implements IDirectedEdge<V>, IEdge<V> {
    private V source;
    private V target;

    @SuppressWarnings("unchecked")
    private AbstractMultiDirectedGraph graph;

    @SuppressWarnings("unchecked")
    public AbstractDirectedEdge(AbstractMultiDirectedGraph g, V source, V target) {
        super(g);
        this.graph = g;
        this.setVertices(source, target);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IDirectedEdge#getSource()
     */
    public V getSource() {
        return this.source;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IDirectedEdge#getTarget()
     */
    public V getTarget() {
        return this.target;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IEdge#getOtherVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public V getOtherVertex(V v) {
        if (v == null) return null;
        if (v.equals(this.source)) return this.target;
        if (v.equals(this.target)) return this.source;
        return null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IEdge#isSelfLoop()
     */
    public boolean isSelfLoop() {
        return this.source.equals(this.target);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IDirectedEdge#setVertices(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @SuppressWarnings("unchecked")
    public void setVertices(V s, V t) {
        if (this.graph == null) return;
        if (s == null || t == null) return;

        if (!this.graph.isMultiGraph()) {
            Collection<IDirectedHyperEdge<V>> es = this.graph.getEdgesWithSourceAndTarget(s, t);
            if (es.size() > 0) {
                for (IDirectedHyperEdge<V> e : es) {
                    if (e.getVertices().size() == 2)
                        return;
                }
            }
        }

        Collection<V> ss = new ArrayList<>();
        Collection<V> ts = new ArrayList<>();
        if (this.source != null && this.target != null) {
            ss.add(this.source);
            ts.add(this.target);
            super.removeSourceAndTagetVertices(ss, ts);
        }
        ss.clear();
        ss.add(s);
        ts.clear();
        ts.add(t);
        super.addSourceAndTagetVertices(ss, ts);
        this.source = s;
        this.target = t;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractDirectedHyperEdge#addSourceAndTagetVertices(java.util.Collection, java.util.Collection)
     */
    @Override
    public Collection<V> addSourceAndTagetVertices(Collection<V> ss, Collection<V> ts) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractDirectedHyperEdge#removeSourceAndTagetVertices(java.util.Collection, java.util.Collection)
     */
    @Override
    public Collection<V> removeSourceAndTagetVertices(Collection<V> ss, Collection<V> ts) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractDirectedHyperEdge#removeVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @Override
    public V removeVertex(V v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractDirectedHyperEdge#removeVertices(java.util.Collection)
     */
    @Override
    public Collection<V> removeVertices(Collection<V> vs) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.AbstractDirectedHyperEdge#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
        this.graph = null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IEdge#getV1()
     */
    public V getV1() {
        return this.source;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IEdge#getV2()
     */
    public V getV2() {
        return this.target;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.IEdge#connectsVertices(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public boolean connectsVertices(V v1, V v2) {
        return this.connectsVertex(v1) && this.connectsVertex(v2);
    }
}
