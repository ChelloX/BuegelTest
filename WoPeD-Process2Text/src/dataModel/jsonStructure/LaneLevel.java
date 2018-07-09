package dataModel.jsonStructure;

import java.util.ArrayList;

class LaneLevel {
    private String resourceId;
    private LaneProperties properties;
    private Stencil stencil;
    private ArrayList<ElementLevel> childShapes;

    String getResourceId() {
        return resourceId;
    }

    LaneProperties getProps() {
        return properties;
    }

    public Stencil getStencil() {
        return stencil;
    }

    public void setStencil(Stencil stencil) {
        this.stencil = stencil;
    }

    ArrayList<ElementLevel> getChildShapes() {
        return childShapes;
    }
}