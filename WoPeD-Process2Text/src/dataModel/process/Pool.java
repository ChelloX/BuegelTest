package dataModel.process;

public class Pool {
    private final int id;
    private final String name;

    public Pool(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}