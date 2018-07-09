package de.hpi.bpt.hypergraph.abs;

import java.util.*;

/**
 * Notification mechanism of edge updates to graph
 * 
 * @author Artem Polyvyanyy
 *
 * @param <E> Edge type employed in the graph
 * @param <V> Vertex type employed in the graph
 */
public abstract class AbstractGraphNotifier<E extends IHyperEdge<V>, V extends IVertex> extends GObject {
	protected final Map<V,Set<E>> vertices = new Hashtable<>();
	final Map<E,Set<V>> edges = new Hashtable<>();
	
	/**
	 * Index vertex in the edge
	 * @param e Edge
	 * @param v Vertex
	 */
    void addIndex(E e, V v) {
		if (e == null || v == null) return;
		if (!this.edges.containsKey(e))
			this.edges.put(e, new HashSet<>());
			
		this.edges.get(e).add(v);
		
		if (!this.vertices.containsKey(v))
			this.vertices.put(v, new HashSet<>());
			
		this.vertices.get(v).add(e);
	}
	
	/**
	 * Index collection of vertices in the edge
	 * @param e Edge
	 * @param vs Collection of vertices
	 */
    void addIndex(E e, Collection<V> vs) {
		if (e == null || vs == null) return;
		for (V v : vs) {
			this.addIndex(e, v);
		}
	}
	
	/**
	 * Remove vertex index from the edge
	 * @param e Edge
	 * @param v Vertex
	 */
    void removeIndex(E e, V v) {
		if (e == null || v == null) return;
		if (this.edges.containsKey(e))
		{
			this.edges.get(e).remove(v);
			
			if (this.edges.get(e).size() == 0)
				this.edges.remove(e);
		}
		
		if (this.vertices.containsKey(v))
		{
			this.vertices.get(v).remove(e);
			
			if (this.vertices.get(v).size() == 0)
				this.vertices.remove(v);
		}
	}
	
	/**
	 * Remove vertex index for collection of vertices from the edge
	 * @param e Edge
	 * @param vs Collection of vertices
	 */
    void removeIndex(E e, Collection<V> vs) {
		if (e == null || vs == null) return;
		for (V v : vs) {
			this.removeIndex(e, v);
		}
	}
}