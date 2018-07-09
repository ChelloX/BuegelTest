package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.AbstractMultiGraphFragment;
import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.graph.abs.IGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation of SPQR-tree fragment skeleton
 * SPQR-tree skeleton gives a basic structure to the triconnected component of the graph
 * along with relations of this component with other skeletons
 *
 * @param <E> template for edge (extends IEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 */
public class TCTreeSkeleton<E extends IEdge<V>, V extends IVertex> extends AbstractMultiGraphFragment<E, V> {
    private static final String VIRTUAL_EDGE_LABEL = "virtual";

    /**
     * Constructor
     *
     * @param g Parent graph of the skeleton
     */
    TCTreeSkeleton(IGraph<E, V> g) {
        super(g);
    }

    /**
     * Add virtual edge to the skeleton
     *
     * @param v1 Vertex
     * @param v2 Vertex
     * @return Edge added to the skeleton
     */
    E addVirtualEdge(V v1, V v2) {
        E e = super.addNonFragmentEdge(v1, v2);

        // mark edge virtual
        e.setDescription(TCTreeSkeleton.VIRTUAL_EDGE_LABEL);

        return e;
    }

    /**
     * Get all virtual edges of the skeleton
     *
     * @return Collection of virtual edges of the skeleton
     */
    Collection<E> getVirtualEdges() {
        Collection<E> result = new ArrayList<>();

        for (E e : this.getEdges()) {
            if (this.isVirtual(e))
                result.add(e);
        }

        return result;
    }

    /**
     * Check if edge is virtual
     *
     * @param e Edge
     * @return <code>true</code> if edge is virtual, <code>false</code> otherwise
     */
    public boolean isVirtual(E e) {
        return e.getDescription().equals(TCTreeSkeleton.VIRTUAL_EDGE_LABEL);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.AbstractMultiGraphFragment#copyOriginalGraph()
     */
    void copyOriginalGraph() {
        if (this.graph == null) return;

        this.removeEdges(this.getEdges());
        this.removeVertices(this.getDisconnectedVertices());

        this.addVertices(this.graph.getDisconnectedVertices());
        for (E e : this.graph.getEdges()) {
            E newE = this.addEdge(e.getV1(), e.getV2());
            newE.setDescription(e.getDescription());
        }
    }
}