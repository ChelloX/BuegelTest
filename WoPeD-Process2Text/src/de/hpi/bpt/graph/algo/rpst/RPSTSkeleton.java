package de.hpi.bpt.graph.algo.rpst;

import de.hpi.bpt.graph.abs.AbstractDirectedEdge;
import de.hpi.bpt.graph.abs.AbstractDirectedGraph;
import de.hpi.bpt.graph.abs.IDirectedEdge;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;
import java.util.Collection;

public class RPSTSkeleton<E extends IDirectedEdge<V>, V extends IVertex>
        extends AbstractDirectedGraph<E, V> {
    private final Collection<Collection<V>> vEdges = new ArrayList<>();

    @Override
    public E addEdge(V from, V to) {
        if (from == null || to == null) return null;

        Collection<V> ss = new ArrayList<>();
        ss.add(from);
        Collection<V> ts = new ArrayList<>();
        ts.add(to);

        if (!this.checkEdge(ss, ts)) return null;

        AbstractDirectedEdge<V> abstractDirectedEdge = new AbstractDirectedEdge<>(this, from, to);
        return (E) abstractDirectedEdge;
    }

    void addVirtualEdge(V v1, V v2) {
        Collection<V> edge = new ArrayList<>();
        edge.add(v1);
        edge.add(v2);
        vEdges.add(edge);
    }

    @Override
    public E removeEdge(E e) {
        vEdges.remove(e);
        return super.removeEdge(e);
    }

    Collection<Collection<V>> getVirtualEdges() {
        return this.vEdges;
    }
}