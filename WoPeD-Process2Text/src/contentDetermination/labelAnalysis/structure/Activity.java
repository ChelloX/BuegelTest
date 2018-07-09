package contentDetermination.labelAnalysis.structure;

import java.util.ArrayList;

public class Activity {
    private final String label;
    private final ArrayList<Activity> model;

    public Activity(String label, ArrayList<Activity> model) {
        this.label = label;
        this.model = model;
    }

}