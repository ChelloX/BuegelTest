package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.hypergraph.abs.IVertex;
import de.hpi.bpt.hypergraph.abs.Vertex;

import java.util.ArrayList;
import java.util.Collection;

public class TCTreeNode<E extends IEdge<V>, V extends IVertex> extends Vertex {
    private TCType type = TCType.UNDEFINED;
    private TCTreeSkeleton<E, V> skeleton;
    private Collection<V> boundary = new ArrayList<>();

    /**
     * Constructor
     */
    TCTreeNode() {
        super();
    }

    /**
     * Constructor
     *
     * @param name Node name
     */
    TCTreeNode(String name) {
        super(name);
    }

    public TCType getType() {
        return type;
    }

    void setType(TCType type) {
        this.type = type;
    }

    public TCTreeSkeleton<E, V> getSkeleton() {
        return this.skeleton;
    }

    void setSkeleton(TCTreeSkeleton<E, V> skeleton) {
        this.skeleton = skeleton;
    }

    public Collection<V> getBoundaryNodes() {
        return new ArrayList<>(this.boundary);
    }

    void setBoundaryNodes(Collection<V> boundary) {
        if (boundary == null || boundary.size() != 2) return;

        this.boundary = new ArrayList<>(boundary);
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getBoundaryNodes() + " - " + this.getSkeleton() + " - " + this.getSkeleton().getVirtualEdges();
    }
}