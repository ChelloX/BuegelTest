package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.HashMap;

/**
 * This map is a convenient solution to store values for edges.
 *
 * @author Christian Wiggert
 */
class NodeMap<V extends IVertex> extends HashMap<V, Object> {
    private static final long serialVersionUID = -474286340181229387L;

    int getInt(V node) {
        return (Integer) this.get(node);
    }
}