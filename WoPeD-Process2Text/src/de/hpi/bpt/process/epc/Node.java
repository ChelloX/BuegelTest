package de.hpi.bpt.process.epc;

import de.hpi.bpt.hypergraph.abs.Vertex;

/**
 * EPC node implementation
 *
 * @author Artem Polyvyanyy
 */
public abstract class Node extends Vertex implements INode {
    private int x = 0, y = 0, w = 0, h = 0;

    Node() {
        super();
    }

    Node(String name, String desc) {
        super(name, desc);
    }

    Node(String name) {
        super(name);
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#getX()
     */
    public int getX() {
        return this.x;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#setX(int)
     */
    public void setX(int x) {
        this.x = x;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#getY()
     */
    public int getY() {
        return this.y;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#setY(int)
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#getWidth()
     */
    public int getWidth() {
        return this.w;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#setWidth(int)
     */
    public void setWidth(int w) {
        this.w = w;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#getHeight()
     */
    public int getHeight() {
        return this.h;
    }

    /*
     * (non-Javadoc)
     * @see de.hpi.bpt.hypergraph.abs.IVertex#setHeight(int)
     */
    public void setHeight(int h) {
        this.h = h;
    }
}