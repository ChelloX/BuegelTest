package de.hpi.bpt.graph.algo.rpst;

import de.hpi.bpt.graph.abs.AbstractDirectedGraph;
import de.hpi.bpt.graph.abs.IDirectedEdge;
import de.hpi.bpt.graph.abs.IDirectedGraph;
import de.hpi.bpt.graph.algo.tctree.TCType;
import de.hpi.bpt.hypergraph.abs.IVertex;
import de.hpi.bpt.hypergraph.abs.Vertex;

public class RPSTNode<E extends IDirectedEdge<V>, V extends IVertex> extends Vertex {
    private boolean isQuasi = false;

    private V entry = null;

    private V exit = null;

    private TCType type = TCType.UNDEFINED;

    private final RPSTSkeleton<E, V> skeleton = new RPSTSkeleton<>();

    private final AbstractDirectedGraph<E, V> fragment = new AbstractDirectedGraph<>();

    public boolean isQuasi() {
        return isQuasi;
    }

    public IDirectedGraph<E, V> getFragment() {
        return this.fragment;
    }

    void setQuasi(boolean isQuasi) {
        this.isQuasi = isQuasi;
    }

    public V getEntry() {
        return this.entry;
    }

    void setEntry(V entry) {
        this.entry = entry;
    }

    public V getExit() {
        return this.exit;
    }

    void setExit(V exit) {
        this.exit = exit;
    }

    RPSTSkeleton<E, V> getSkeleton() {
        return this.skeleton;
    }

    public TCType getType() {
        return this.type;
    }

    void setType(TCType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return (this.isQuasi ? "*" : "") + this.getName() + " [" + this.entry + "," + this.exit + "] - " + this.getSkeleton() + " - " + this.getSkeleton().getVirtualEdges() + " : " + this.getFragment();
    }
}