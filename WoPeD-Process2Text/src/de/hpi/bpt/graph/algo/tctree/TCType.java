package de.hpi.bpt.graph.algo.tctree;

/**
 * Structural types of triconnected components
 * <p>
 * P - polygon
 * B - bond
 * T - trivial (edge)
 * R - rigid
 */
public enum TCType {
    P,
    B,
    T,
    R,
    UNDEFINED
}