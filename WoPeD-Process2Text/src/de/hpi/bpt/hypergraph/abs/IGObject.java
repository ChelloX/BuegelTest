package de.hpi.bpt.hypergraph.abs;

/**
 * Basic graph object interface
 * 
 * @author Artem Polyvyanyy
 */
public interface IGObject extends Comparable<IGObject> {
	/**
	 * Get unique identifier
	 */
    String getId();

	/**
	 * Set unique identifier
	 * @param id Unique identifier
	 */
    void setId(String id);
	
	/**
	 * Get graph object associated tag object
	 * @return Tag object
	 */
    Object getTag();
	
	/**
	 * Set graph object associated tag object
	 * @param tag Tag object to set
	 */
    void setTag(Object tag);
	
	/**
	 * Get name
	 * @return Name string
	 */
    String getName();
	
	/**
	 * Set name
	 * @param name Name to set
	 */
    void setName(String name);

	/**
	 * Get description
	 * @return Description string
	 */
    String getDescription();
	
	/**
	 * Set description
	 * @param desc Description to set
	 */
    void setDescription(String desc);
}