package de.hpi.bpt.hypergraph.abs;

/**
 * Basic graph vertex implementation
 *
 * @author Artem Polyvyanyy
 */
public class Vertex extends GObject implements IVertex {
    public Vertex() {
        super();
    }

    protected Vertex(String name) {
        super(name);
    }
}