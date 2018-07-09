package de.hpi.bpt.process.epc;

import de.hpi.bpt.hypergraph.abs.IVertex;

/**
 * An element of an EPC diagram which is a node of a graph.
 * 
 * @author Artem Polyvyanyy
 */
interface INode extends IVertex {
	/**
	 * Get vertex coordinate X
	 * @return x
	 */
	int getX();
	
	/**
	 * Set vertex coordinate X
	 */
	void setX(int x);
	
	/**
	 * Get vertex coordinate Y
	 * @return y
	 */
	int getY();
	
	/**
	 * Set vertex coordinate Y
	 */
	void setY(int y);
	
	/**
	 * Get vertex width
	 * @return Width
	 */
	int getWidth();
	
	/**
	 * Set vertex width
	 * @param w Width
	 */
	void setWidth(int w);
	
	/**
	 * Get vertex height
	 * @return Height
	 */
	int getHeight();
	
	/**
	 * Set vertex height
	 * @param h Height
	 */
	void setHeight(int h);
}