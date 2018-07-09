package dataModel.jsonStructure;

import java.util.ArrayList;

class ElementLevel {
    private String resourceId;
    private ElementProperties properties;
    private Stencil stencil;
    private ArrayList<ElementLevel> outgoing;

    String getResourceId() {
        return resourceId;
    }

    ElementProperties getProps() {
        return properties;
    }

    public Stencil getStencil() {
        return stencil;
    }

    public void setStencil(Stencil stencil) {
        this.stencil = stencil;
    }

    public ArrayList<ElementLevel> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(ArrayList<ElementLevel> outgoing) {
        this.outgoing = outgoing;
    }
}