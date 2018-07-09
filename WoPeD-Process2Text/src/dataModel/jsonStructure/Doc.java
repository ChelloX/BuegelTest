package dataModel.jsonStructure;

import java.util.ArrayList;

class Doc {
    private DocProperties properties;
    private Stencil stencil;
    private ArrayList<PoolLevel> childShapes;

    private DocProperties getProps() {
        return properties;
    }

    public Stencil getStencil() {
        return stencil;
    }

    public void setStencil(Stencil stencil) {
        this.stencil = stencil;
    }

    private ArrayList<PoolLevel> getChildShapes() {
        return childShapes;
    }

    public void print() {
        System.out.println("Document: " + this.getProps().getName());
        ArrayList<PoolLevel> pools = this.getChildShapes();
        for (PoolLevel pool : pools) {
            System.out.print("\t" + pool.getResourceId() + " (");
            System.out.print(pool.getProps().getName() + ", ");
            System.out.println(pool.getStencil() + ")");

            ArrayList<LaneLevel> lanes = pool.getChildShapes();
            for (LaneLevel lane : lanes) {
                System.out.print("\t\t" + lane.getResourceId() + " (");
                System.out.print(lane.getProps().getName() + ", ");
                System.out.println(lane.getStencil() + ")");

                ArrayList<ElementLevel> elems = lane.getChildShapes();
                for (ElementLevel elem : elems) {
                    System.out.print("\t\t\t" + elem.getResourceId() + " (");
                    System.out.print(elem.getProps().getName() + ", ");
                    System.out.println(elem.getStencil() + ")");
                }
            }
        }
    }

    public ArrayList<String> getTasks() {
        ArrayList<String> tasks = new ArrayList<>();

        // Pool level
        for (PoolLevel pool : this.getChildShapes()) {
            String elemName = pool.getStencil().toString();
            if (elemName.contains(" ")) {
                elemName = elemName.replace(" ", "");
            }
            if (elemName.toLowerCase().equals("task")) {
                tasks.add(pool.getProps().getName());
            }

            // Lane level
            for (LaneLevel lane : pool.getChildShapes()) {
                if (lane.getStencil().toString().toLowerCase().equals("task")) {
                    tasks.add(lane.getProps().getName());
                }

                // Element level
                for (ElementLevel elem : lane.getChildShapes()) {
                    if (elem.getStencil().toString().toLowerCase().equals("task")) {
                        tasks.add(elem.getProps().getName());
                    }
                }
            }
        }
        return tasks;
    }
}