package de.hpi.bpt.hypergraph.abs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Abstract hyper edge implementation
 *
 * @param <V> Vertex type employed in the edge
 * @author Artem Polyvyanyy
 */
public class AbstractHyperEdge<V extends IVertex> extends GObject implements IHyperEdge<V> {
    private final Collection<V> vertices;
    @SuppressWarnings("unchecked")
    private AbstractMultiHyperGraph graph;

    @SuppressWarnings("unchecked")
    protected AbstractHyperEdge(AbstractMultiHyperGraph g) {
        super();
        this.vertices = new ArrayList<>();
        this.graph = g;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperEdge#addVertices(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public Collection<V> addVertices(Collection<V> vs) {
        if (this.graph == null) return null;
        if (vs == null) return null;

        if (!this.graph.isMultiGraph()) {
            Collection<V> vvs = new ArrayList<>(this.vertices);
            vvs.addAll(vs);

            if (!this.checkEdge(vs)) return null;
        }

        boolean result = this.vertices.addAll(vs);
        this.graph.addIndex(this, vs);

        return result ? vs : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperEdge#connectsVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    public boolean connectsVertex(V v) {
        return this.vertices.contains(v);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperEdge#connectsVertices(java.util.Collection)
     */
    public boolean connectsVertices(Collection<V> vs) {
        return this.vertices.containsAll(vs);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperEdge#getVertices()
     */
    public Collection<V> getVertices() {
        return new ArrayList<>(this.vertices);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperEdge#removeVertex(de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @SuppressWarnings("unchecked")
    public V removeVertex(V v) {
        if (this.graph == null) return null;
        if (v == null) return null;

        if (!this.graph.isMultiGraph()) {
            Collection<V> vs = new ArrayList<>(this.vertices);
            vs.remove(v);

            if (!this.checkEdge(vs)) return null;
        }

        boolean result = this.vertices.remove(v);
        if (result) this.graph.removeIndex(this, v);

        return result ? v : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperEdge#removeVertices(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public Collection<V> removeVertices(Collection<V> vs) {
        if (this.graph == null) return null;
        if (vs == null) return null;

        if (!this.graph.isMultiGraph()) {
            Collection<V> vvs = new ArrayList<>(this.vertices);
            vvs.removeAll(vs);

            if (!this.checkEdge(vvs)) return null;
        }

        boolean result = this.vertices.remove(vs);
        if (result) this.graph.removeIndex(this, vs);

        return result ? vs : null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IHyperEdge#destroy()
     */
    @SuppressWarnings("unchecked")
    public void destroy() {
        this.graph.removeIndex(this, this.vertices);
        this.graph.addVertices(this.vertices);
        this.graph = null;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.GObject#toString()
     */
    @Override
    public String toString() {
        String res = "";

        Iterator<V> i = this.vertices.iterator();
        if (i.hasNext()) res = String.format("%1s", i.next());
        while (i.hasNext())
            res = String.format("%1s,%1s", res, i.next());

        return String.format("[{%1s}]", res);
    }

    @SuppressWarnings("unchecked")
    private boolean checkEdge(Collection<V> vs) {
        Collection<IHyperEdge<V>> es = this.graph.getEdges(vs);
        if (es.size() > 0) {
            for (IHyperEdge<V> e : es) {
                if (e.getVertices().size() == vs.size())
                    return false;
            }
        }

        return true;
    }
}