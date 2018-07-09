package de.hpi.bpt.hypergraph.abs;

import java.util.UUID;

/**
 * Graph object implementation
 *
 * @author Artem Polyvyanyy
 */
public abstract class GObject implements IGObject, Cloneable {
    private String id = "";
    private String name = "";
    private String desc = "";

    /**
     * Empty constructor
     */
    GObject() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Constructor with object name parameter
     */
    GObject(String name) {
        this();
        setName(name);
    }

    /**
     * Get unique identifier
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set unique identifier
     *
     * @param id Unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return Name string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set name
     *
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get description
     *
     * @return Description string
     */

    public String getDescription() {
        return this.desc;
    }

    /**
     * Set description
     *
     * @param desc Description to set
     */
    public void setDescription(String desc) {
        this.desc = desc;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return (getName() == null || getName().equals("")) ? this.id : this.name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GObject)) return false;

        return this.id.equals(((GObject) obj).id);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(IGObject o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GObject clone = (GObject) super.clone();

        if (this.getId() != null)
            clone.setId(this.getId());
        if (this.getName() != null)
            clone.setName(this.getName());
        if (this.getDescription() != null)
            clone.setDescription(this.getDescription());

        return clone;
    }
}