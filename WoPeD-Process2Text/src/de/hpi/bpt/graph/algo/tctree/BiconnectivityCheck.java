package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.graph.abs.IEdge;
import de.hpi.bpt.graph.abs.IGraph;
import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.*;

class BiconnectivityCheck<E extends IEdge<V>, V extends IVertex> {
    private final IGraph<E, V> graph;
    private final Hashtable<V, NodeAttrs> attrs;
    private final Collection<EdgeList<E, V>> components = new ArrayList<>();
    private final Stack<E> s = new Stack<>();
    private final boolean isBiconnected;
    private int time;

    BiconnectivityCheck(IGraph<E, V> graph) {
        Iterator<V> nodes = graph.getVertices().iterator();
        this.attrs = new Hashtable<>(graph.getVertices().size());
        this.graph = graph;
        while (nodes.hasNext()) {
            prepareNode(nodes.next());
        }

        V startNode = graph.getVertices().iterator().next();

        this.time = 0;

        if (startNode != null) {
            process(startNode);
            this.isBiconnected = this.components.size() == 1;
        } else
            this.isBiconnected = false;
    }

    boolean isBiconnected() {
        return this.isBiconnected;
    }

    private void process(V v) {
        NodeAttrs att = attrs.get(v);
        att.visited = true;
        time++;
        att.dis = time;
        att.low = att.dis;
        V w;

        Collection<E> edges = new ArrayList<>(this.graph.getEdges(v));

        for (E e : edges) {
            if (v.equals(e.getV1())) w = e.getV2();
            else w = e.getV1();

            NodeAttrs watt = attrs.get(w);

            if (!watt.visited) {
                s.push(e);
                watt.parent = v;
                process(w);

                if (watt.low >= att.dis) {
                    addComponent(e);
                }
                if (watt.low < att.low) {
                    att.low = watt.low;
                }
            } else if (!compareInts(att.parent, w) && (watt.dis < att.dis)) { // (att.parent != w)
                s.push(e);
                if (watt.dis < att.low) {
                    att.low = watt.dis;
                }
            }
        }

        time++;
    }

    private void addComponent(E e) {
        EdgeList<E, V> comp = new EdgeList<>();

        E f;
        do {
            f = s.pop();
            comp.add(f);
        } while (e != f);

        components.add(comp);
    }

    private boolean compareInts(V i1, V i2) {
        if (i1 == null && i2 == null) return true;
        if (i1 != null) return i1.equals(i2);
        return false;
    }

    private void prepareNode(V node) {
        attrs.put(node, new NodeAttrs());
    }

    class NodeAttrs {
        boolean visited;
        V parent;
        int low;
        int dis;

        NodeAttrs() {
            visited = false;
            parent = null;
            low = 0;
            dis = 0;
        }
    }
}