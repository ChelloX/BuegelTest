package dataModel.jsonStructure;

import java.util.ArrayList;

class Collection {
    private final ArrayList<Doc> models;

    public Collection() {
        models = new ArrayList<>();
    }

    public void add(Doc model) {
        models.add(model);
    }

    public ArrayList<Doc> getModels() {
        return models;
    }
}