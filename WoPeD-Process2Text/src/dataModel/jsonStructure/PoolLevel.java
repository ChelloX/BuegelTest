package dataModel.jsonStructure;

import java.util.ArrayList;

class PoolLevel {
    private String resourceId;
    private PoolProperties properties;
    private Stencil stencil;
    private ArrayList<LaneLevel> childShapes;
    private Target target;
    private ArrayList<Outgoing> outgoing;

    public ArrayList<Outgoing> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(ArrayList<Outgoing> outgoing) {
        this.outgoing = outgoing;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    String getResourceId() {
        return resourceId;
    }

    PoolProperties getProps() {
        return properties;
    }

    public Stencil getStencil() {
        return stencil;
    }

    public void setStencil(Stencil stencil) {
        this.stencil = stencil;
    }

    ArrayList<LaneLevel> getChildShapes() {
        return childShapes;
    }
}