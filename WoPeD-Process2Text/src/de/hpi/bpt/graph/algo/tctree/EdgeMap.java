package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.HashMap;

/**
 * This map is a convenient solution to store values for edges.
 *
 * @author Christian Wiggert
 */
class EdgeMap<E extends IEdge<V>, V extends IVertex> extends HashMap<E, Object> {
    private static final long serialVersionUID = -3122883772335954023L;

    int getInt(E edge) {
        return (Integer) this.get(edge);
    }

    boolean getBool(E edge) {
        if (this.get(edge) == null)
            return false;
        return (Boolean) this.get(edge);
    }

    void initialiseWithFalse() {
        for (E edge : this.keySet()) {
            this.put(edge, false);
        }
    }
}