package models;

public class Departments {
    private String name;
    private String description;
    private int id;
    private int size;

    public Departments(String name, String description, int size) {
        this.name = name;
        this.description = description;
        this.size = 0;
    }
}
