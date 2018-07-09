package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.AbstractDirectedGraph;
import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.graph.abs.IGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * The tree of the triconnected components
 *
 * @param <E> template for edge (extends IEdge)
 * @param <V> template for vertex (extends IVertex)
 * @author Artem Polyvyanyy
 * @author Christian Wiggert
 */
public class TCTree<E extends IEdge<V>, V extends IVertex> extends AbstractDirectedGraph<TCTreeEdge<E, V>, TCTreeNode<E, V>> {
    private IGraph<E, V> graph;
    private final Collection<TCTreeNode<E, V>> nodes = new ArrayList<>();
    private E backEdge;
    private TCTreeNode<E, V> root = null;

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.graph.abs.AbstractDirectedGraph#addEdge(de.hpi.bpt.hypergraph.abs.IVertex, de.hpi.bpt.hypergraph.abs.IVertex)
     */
    @Override
    public TCTreeEdge<E, V> addEdge(TCTreeNode<E, V> v1, TCTreeNode<E, V> v2) {
        if (v1 == null || v2 == null) return null;

        Collection<TCTreeNode<E, V>> ss = new ArrayList<>();
        ss.add(v1);
        Collection<TCTreeNode<E, V>> ts = new ArrayList<>();
        ts.add(v2);

        if (!this.checkEdge(ss, ts)) return null;

        return new TCTreeEdge<>(this, v1, v2);
    }

    /**
     * This class decomposes the given a given biconnected graph into a tree of triconnected components.
     * If the given back edge is null, a random edge of the graph is chosen as back edge
     *
     * @param g    - a biconnected graph
     * @param edge - the according back edge
     */
    public TCTree(IGraph<E, V> g, E edge) {
        if (g == null) return;
        this.graph = g;

        if (isTrivialCase()) {
            TCTreeSkeleton<E, V> sk = new TCTreeSkeleton<>(this.graph);
            sk.copyOriginalGraph();
            this.root = new TCTreeNode<>("T0");
            root.setSkeleton(sk);
            root.setType(TCType.T);
            this.addVertex(this.root);
            this.root.setBoundaryNodes(this.graph.getVertices());
        } else {
            TCTreeSkeleton<E, V> sk = new TCTreeSkeleton<>(this.graph);
            sk.copyOriginalGraph();
            if (edge == null)
                // choose one edge to be the backEdge
                this.backEdge = sk.getEdges().iterator().next();
            else
                this.backEdge = edge;
            // graph must be biconnected
            BiconnectivityCheck<E, V> check = new BiconnectivityCheck<>(sk);
            if (!check.isBiconnected()) return;

            // make first node
            this.root = new TCTreeNode<>("0");
            root.setSkeleton(sk);

            // decompose
            ModelDecomposer<E, V> decomposer = new ModelDecomposer<>();
            Collection<TCTreeNode<E, V>> newNodes = decomposer.getTriconnectedComponents(graph, root, backEdge);
            if (newNodes != null) {
                for (TCTreeNode<E, V> node : newNodes) {
                    this.putNode(node);
                }
            }

            // classify node types
            this.classifyNodes();

            // construct tree
            this.constructTree();

            this.nodes.clear();
        }
    }

    private boolean isTrivialCase() {
        return this.graph.getEdges().size() == 1 && !(this.graph.getVertices().size() > 2);
    }

    /**
     * Get original graph
     *
     * @return Graph
     */
    IGraph<E, V> getGraph() {
        return this.graph;
    }

    /**
     * Add TC-tree node, if it was not added before
     *
     * @param node Node to add
     */
    private void putNode(TCTreeNode<E, V> node) {
        Iterator<TCTreeNode<E, V>> i = this.nodes.iterator();

        Collection<V> vs1 = node.getSkeleton().getVertices();
        boolean flag = true;
        while (i.hasNext()) {
            TCTreeNode<E, V> n = i.next();

            Collection<V> vs2 = n.getSkeleton().getVertices();

            if (vs2.containsAll(vs1) && vs2.size() == vs1.size()) {
                flag = false;
                break;
            }
        }

        if (flag)
            this.nodes.add(node);
    }

    public Collection<TCTreeNode<E, V>> getVertices(TCType type) {
        Collection<TCTreeNode<E, V>> result = new ArrayList<>();

        for (TCTreeNode<E, V> n : this.getVertices()) {
            if (n.getType() == type)
                result.add(n);
        }

        return result;
    }

    /**
     * Classify TCTree nodes on types: P,B,R
     */
    private void classifyNodes() {
        int Pc, Bc, Rc;
        Pc = Bc = Rc = 0;

        for (TCTreeNode<E, V> n : this.nodes) {
            if (n.getSkeleton().countVertices() == 2) {
                n.setType(TCType.B);
                n.setName("B" + Bc++);
                continue;
            }

            boolean isS = true;
            for (V v : n.getSkeleton().getVertices()) {
                if (n.getSkeleton().getEdges(v).size() != 2) {
                    isS = false;
                    break;
                }
            }

            if (isS) {
                n.setType(TCType.P);
                n.setName("P" + Pc++);
            } else {
                n.setType(TCType.R);
                n.setName("R" + Rc++);
            }
        }
    }

    private void constructTree() {
        // get root node - a node with back edge
        for (TCTreeNode<E, V> n : this.nodes) {
            E e = n.getSkeleton().getEdge(this.backEdge.getV1(), this.backEdge.getV2());
            if (e != null) // && n.getSkeleton().isVirtual(e))
            {
                this.root = n;
                break;
            }
        }
        if (this.root == null) return;
        this.addVertex(this.root);
        this.root.setBoundaryNodes(this.backEdge.getVertices());

        Collection<TCTreeNode<E, V>> ns = new ArrayList<>(this.nodes);
        ns.remove(this.root);
        constructChildren(this.root, ns);

        // construct trivial nodes
        int Tc = 0;
        for (TCTreeNode<E, V> node : this.getVertices()) {
            Collection<E> ts = new ArrayList<>(node.getSkeleton().getEdges());
            ts.removeAll(node.getSkeleton().getVirtualEdges());
            for (E t : ts) {
                TCTreeNode<E, V> n = new TCTreeNode<>();
                n.setType(TCType.T);
                n.setBoundaryNodes(t.getVertices());
                TCTreeSkeleton<E, V> sk = new TCTreeSkeleton<>(this.graph);
                sk.addEdge(t.getV1(), t.getV2());
                n.setSkeleton(sk);
                n.setName("T" + (Tc++));
                this.addEdge(node, n);
            }
        }
    }

    private void constructChildren(TCTreeNode<E, V> n, Collection<TCTreeNode<E, V>> ns) {
        Collection<TCTreeNode<E, V>> nss = new ArrayList<>(ns);
        Collection<TCTreeNode<E, V>> ncs = new ArrayList<>();
        Collection<TCTreeNode<E, V>> nps = new ArrayList<>();

        for (E ve : n.getSkeleton().getVirtualEdges()) {
            for (TCTreeNode<E, V> vn : ns) {
                Collection<E> ves = vn.getSkeleton().getEdges(ve.getV1(), ve.getV2());
                if (this.containsVirtual(vn, ves)) {

                    vn.setBoundaryNodes(ve.getVertices());

                    nss.remove(vn);

                    if (vn.getType() == TCType.B)
                        nps.add(vn);
                    else {
                        ncs.add(vn);
                    }
                }
            }
        }

        // first work with Bs
        Collection<TCTreeNode<E, V>> cncs = new ArrayList<>(ncs);
        Iterator<TCTreeNode<E, V>> j = nps.iterator();
        while (j.hasNext()) {
            TCTreeNode<E, V> p = j.next();

            // look for children
            Collection<TCTreeNode<E, V>> npc = new ArrayList<>();

            for (TCTreeNode<E, V> cc : cncs) {
                Iterator<V> pi = p.getSkeleton().getVertices().iterator();

                Collection<E> ves = cc.getSkeleton().getEdges(pi.next(), pi.next());
                if (this.containsVirtual(cc, ves)) {
                    npc.add(cc);
                    nss.remove(cc);
                    ncs.remove(cc);
                }
            }

            // call recursion
            this.addEdge(n, p);
            Collection<TCTreeNode<E, V>> u = new ArrayList<>(nss);
            u.addAll(npc);
            this.constructChildren(p, u);
        }

        // now rest
        j = ncs.iterator();
        while (j.hasNext()) {
            TCTreeNode<E, V> vn = j.next();
            this.addEdge(n, vn);
            this.constructChildren(vn, nss);
        }
    }

    private boolean containsVirtual(TCTreeNode<E, V> n, Collection<E> ves) {

        for (E ve : ves) {
            if (n.getSkeleton().getVirtualEdges().contains(ve))
                return true;
        }

        return false;
    }

    /**
     * Check if TCTree node is root
     *
     * @param node Node of the TCTree
     * @return <code>true</code> if node is the root of TCTree, <code>false</code> otherwise
     */
    public boolean isRoot(TCTreeNode<E, V> node) {
        if (node == null) return false;
        return node.equals(this.root);
    }

    /**
     * Get root node
     *
     * @return root node
     */
    public TCTreeNode<E, V> getRoot() {
        return this.root;
    }

    public void setRoot(TCTreeNode<E, V> node) {
        if (this.getVertices().contains(node))
            this.root = node;
    }

    /**
     * Get parent of the TCTree node
     *
     * @param node TCTree node
     * @return Parent of the node
     */
    public TCTreeNode<E, V> getParent(TCTreeNode<E, V> node) {
        return this.getFirstPredecessor(node);
    }

    /**
     * Get children of the TCTree node
     *
     * @param node TCTree node
     * @return Children of the node
     */
    public Collection<TCTreeNode<E, V>> getChildren(TCTreeNode<E, V> node) {
        return this.getSuccessors(node);
    }

    @Override
    public String toString() {
        return toStringHelper(this.getRoot(), 0);
    }

    private String toStringHelper(TCTreeNode<E, V> tn, int depth) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            result.append("   ");
        }
        result.append(tn.toString());
        result.append("\n");
        for (TCTreeNode<E, V> c : this.getChildren(tn)) {
            result.append(toStringHelper(c, depth + 1));
        }
        return result.toString();
    }
}