package dataModel.process;

public class Lane {
    private final int id;
    private final String name;
    private final Pool pool;

    public Lane(int id, String name, Pool pool) {
        this.id = id;
        this.name = name;
        this.pool = pool;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Pool getPool() {
        return pool;
    }
}