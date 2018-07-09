package de.hpi.bpt.graph.algo.tctree;

import java.util.HashMap;

/**
 * This container stores additional meta information.
 * The elements of {@link MetaInfo} are used as keys.
 *
 * @author Christian Wiggert
 */
class MetaInfoContainer {
    private final HashMap<MetaInfo, Object> map;

    MetaInfoContainer() {
        map = new HashMap<>();
    }

    Object getMetaInfo(MetaInfo name) {
        if (map.containsKey(name))
            return map.get(name);
        return null;
    }

    void setMetaInfo(MetaInfo name, Object content) {
        map.put(name, content);
    }
}