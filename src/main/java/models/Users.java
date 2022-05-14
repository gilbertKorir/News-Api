package models;

public class Users {
    private int id;
    private String name;
    private String position;
    private String staff_role;

    public Users(String name, String position, String staff_role) {
        this.name = name;
        this.position = position;
        this.staff_role = staff_role;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getStaff_role() {
        return staff_role;
    }

    public void setId(int id) {
        this.id = id;
    }
}
