package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.graph.abs.IGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

/**
 * This class computes the tree structure during the DFS, stored in a
 * {@link NodeMap} containing for each vertex its parent node
 * within the DFS palm tree, accessible through the MetaInfo
 * It also assigns each edge the number of the path on which it occurs,
 * and a flag whether it is a first
 * edge on some path or not.
 *
 * @author Martin Mader
 * @author Christian Wiggert
 */
class ParentAndPathDFS<E extends IEdge<V>, V extends IVertex> extends AbstractDFS<E, V> {
    /**
     * NodeMap storing for each vertex its parent vertex
     */
    private final NodeMap<V> parentMap;
    /**
     * NodeMap storing for each vertex the tree edge entering the vertex
     */
    private final NodeMap<V> treeArcMap;
    /**
     * EdgeMap storing for each edge whether it starts a new path
     */
    private final EdgeMap<E, V> startsNewPathMap;
    /**
     * EdgeMap storing for each edge the number of the path on which it lies
     */
    private final EdgeMap<E, V> pathNumMap;

    private boolean isNewPath = true;
    private int pathNumber = 1;

    /**
     * @param graph  the graph on which DFS is to be executed on
     * @param adjMap the adjaceny map of graph
     */
    ParentAndPathDFS(IGraph<E, V> graph, MetaInfoContainer container, NodeMap<V> adjMap) {
        super(graph, container, adjMap);
        parentMap = this.createNodeMap(g);
        treeArcMap = this.createNodeMap(g);
        startsNewPathMap = this.createEdgeMap(g);
        pathNumMap = this.createEdgeMap(g);

        for (V node : g.getVertices()) {
            parentMap.put(node, INVALID_NODE);
            treeArcMap.put(node, INVALID_EDGE);
        }
        for (E edge : g.getEdges()) {
            pathNumMap.put(edge, -1);
            startsNewPathMap.put(edge, false);
        }

        // add data provider
        meta.setMetaInfo(MetaInfo.DFS_PARENT, parentMap);
        meta.setMetaInfo(MetaInfo.DFS_PATH_NUMBER, pathNumMap);
        meta.setMetaInfo(MetaInfo.DFS_STARTS_NEW_PATH, startsNewPathMap);
    }

    @Override
    void preTraverse(E e, V w, boolean treeEdge) {
        super.preTraverse(e, w, treeEdge);

        V v = e.getOtherVertex(w);
        if (treeEdge) {
            // set parent of w
            parentMap.put(w, v);
            // set tree arc leading to w
            treeArcMap.put(w, e);
            // update path information
            pathNumMap.put(e, pathNumber);
            if (isNewPath) {
                startsNewPathMap.put(e, true);
                isNewPath = false;
            }
        } else {
            // update path information
            pathNumMap.put(e, pathNumber);
            if (isNewPath) {
                startsNewPathMap.put(e, true);
            }
            pathNumber++;
            isNewPath = true;
        }
    }

    /**
     * returns the NodeMap storing parent information
     *
     * @return parentMap
     */
    NodeMap<V> getParentMap() {
        return parentMap;
    }

    /**
     * returns the NodeMap storing the entering tree edges
     *
     * @return treeArcMap
     */
    NodeMap<V> getTreeArcMap() {
        return treeArcMap;
    }
}