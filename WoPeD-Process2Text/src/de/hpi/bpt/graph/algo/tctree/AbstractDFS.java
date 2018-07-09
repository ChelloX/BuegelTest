package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.graph.abs.IGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

/**
 * A standard Depth-first-search (DFS) implementation. It implements
 * the same dummy-methods as the DFS algorithm in Dfs, but operates
 * on a given adjacency structure to determine the order in which paths
 * are generated.
 *
 * @author Martin Mader
 * @author Christian Wiggert
 */
public class AbstractDFS<E extends IEdge<V>, V extends IVertex> {
    /**
     * Tree edge
     */
    static final int TREE_EDGE = 1;
    /**
     * Vertex not yet visited
     */
    private static final int WHITE = 0;
    /**
     * Vertex visited, but not yet finished
     */
    private static final int GRAY = 1;
    /**
     * Vertex processed completely
     */
    private static final int BLACK = 2;
    private static final int EDGE_NOT_VISITED = 0;
    /**
     * Back edge
     */
    private static final int BACK_EDGE = 2;
    final V INVALID_NODE = null;
    final E INVALID_EDGE = null;
    /**
     * The graph to operate on
     */
    final IGraph<E, V> g;
    /**
     * The MetaInfoContainer for the additional maps related to the graph
     */
    final MetaInfoContainer meta;
    /**
     * NodeMap storing DFS-Numbers
     */
    final NodeMap<V> dfsNumMap;
    /**
     * Adjacency Map
     */
    private final NodeMap<V> adj;
    /**
     * NodeMap storing Completion Numbers
     */
    private final NodeMap<V> complNumMap;

    /**
     * NodeMap storing current vertex status: WHITE, GRAY, or BLACK
     */
    private final NodeMap<V> nodeStateMap;

    /**
     * EdgeMap storing current edge type: EDGE_NOT_VISITED, TREE_EDGE or BACK_EDGE
     */
    private final EdgeMap<E, V> edgeTypeMap;
    private int dfsNum = 0;
    private int complNum = 0;

    /**
     * creates an Instance of DFS which operates on the given graph
     * and adjacency structure.
     *
     * @param graph  the graph on which to perform DFS
     * @param adjMap the adjacency structure to be used.
     *               in each entry must be contained an EdgeList
     *               of adjacent neighbors. DFS traverses edges
     *               the according order.
     */
    AbstractDFS(IGraph<E, V> graph, MetaInfoContainer container, NodeMap<V> adjMap) {
        g = graph;
        adj = adjMap;
        meta = container;
        nodeStateMap = this.createNodeMap(g);
        dfsNumMap = this.createNodeMap(g);
        complNumMap = this.createNodeMap(g);
        for (V node : g.getVertices()) {
            nodeStateMap.put(node, WHITE);
            dfsNumMap.put(node, -1);
            complNumMap.put(node, -1);
        }
        edgeTypeMap = this.createEdgeMap(g);
        for (E edge : g.getEdges()) {
            edgeTypeMap.put(edge, EDGE_NOT_VISITED);
        }
        // add data provider
        meta.setMetaInfo(MetaInfo.DFS_NUM, dfsNumMap);
        meta.setMetaInfo(MetaInfo.DFS_COMPL_NUM, complNumMap);
        meta.setMetaInfo(MetaInfo.DFS_NODE_STATE, nodeStateMap);
        meta.setMetaInfo(MetaInfo.DFS_EDGE_TYPE, edgeTypeMap);
    }

    /**
     * starts a depth-first-search (DFS) beginning at the given
     *
     * @param root the root node of the DFS
     */
    public void start(V root) {
        dfsNum = 0;
        complNum = 0;
        dfs(root);
    }

    @SuppressWarnings("unchecked")
    private void dfs(V v) {
        dfsNum++;
        dfsNumMap.put(v, dfsNum);
        nodeStateMap.put(v, GRAY);
        EdgeList<E, V> adjV = (EdgeList<E, V>) adj.get(v);
        preVisit(v, (Integer) dfsNumMap.get(v));

        for (E e : adjV) {
            // traverse only not yet visited edges
            if ((Integer) edgeTypeMap.get(e) == EDGE_NOT_VISITED) {
                V w = e.getOtherVertex(v);
                // re-orient edge
                e.setVertices(v, w);

                if ((Integer) nodeStateMap.get(w) == WHITE) {
                    // tree edge found -> traverse edge
                    edgeTypeMap.put(e, TREE_EDGE);
                    preTraverse(e, w, true);
                    dfs(w);
                    postTraverse(e, w);
                } else {
                    // back edge found
                    edgeTypeMap.put(e, BACK_EDGE);
                    preTraverse(e, w, false);
                }
            }
        }

        // backtrack
        nodeStateMap.put(v, BLACK);
        complNum++;
        complNumMap.put(v, complNum);

        postVisit(v, (Integer) dfsNumMap.get(v), (Integer) complNumMap.get(v));
    }

    void preVisit(V v, int dfsNumber) {
    }

    void preTraverse(E e, V w, boolean treeEdge) {
    }

    void postTraverse(E e, V w) {
    }

    void postVisit(V v, int dfsNumber, int complNumber) {
    }

    EdgeMap<E, V> getEdgeTypeMap() {
        return edgeTypeMap;
    }

    /**
     * Move to graph algorithms.
     */
    @Deprecated
    protected EdgeMap<E, V> createEdgeMap(IGraph<E, V> g) {
        EdgeMap<E, V> map = new EdgeMap<>();
        for (E e : g.getEdges()) {
            map.put(e, null);
        }
        return map;
    }

    /**
     * Move to graph algorithms.
     */
    @Deprecated
    protected NodeMap<V> createNodeMap(IGraph<E, V> g) {
        NodeMap<V> map = new NodeMap<>();
        for (V v : g.getVertices()) {
            map.put(v, null);
        }
        return map;
    }
}