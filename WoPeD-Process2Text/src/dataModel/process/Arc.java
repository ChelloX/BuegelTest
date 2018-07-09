package dataModel.process;

public class Arc {
    private final int id;
    private final String label;
    private Element source;
    private Element target;
    private final String type;

    public Arc(int id, String label, Element source, Element target) {
        this.id = id;
        this.label = label;
        this.source = source;
        this.target = target;
        this.type = "";
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Element getSource() {
        return source;
    }

    public Element getTarget() {
        return target;
    }

    public void setSource(Element source) {
        this.source = source;
    }

    public void setTarget(Element target) {
        this.target = target;
    }
}