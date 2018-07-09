package dataModel.process;

import java.util.ArrayList;

public class Activity extends Element {
    private final ArrayList<Annotation> annotations;
    private final ArrayList<Integer> attachedEvents;

    public Activity(int id, String label, Lane lane, Pool pool) {
        super(id, label, lane, pool);
        annotations = new ArrayList<>();
        attachedEvents = new ArrayList<>();
    }

    public boolean hasAttachedEvents() {
        return attachedEvents.size() > 0;
    }

    public ArrayList<Integer> getAttachedEvents() {
        return attachedEvents;
    }

    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }

    void addAnnotation(Annotation a) {
        annotations.add(a);
    }
}