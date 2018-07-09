package dataModel.pnmlReader.PetriNet;

public abstract class Element {
    private final String label;
    private final String id;

    Element(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getId() {
        return id;
    }

}