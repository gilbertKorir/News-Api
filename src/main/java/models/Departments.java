package models;

public class Departments {
    private int id;
    private String name;
    private String description;
    private int size;

    public Departments(String name, String description, int size) {
        this.name = name;
        this.description = description;
        this.size = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
