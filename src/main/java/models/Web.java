package models;

public class Web {

    private String id;
    private String descr;
    private String path;

    public Web(String descr, String path) {
        this.descr = descr;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public String getDescr() {
        return descr;
    }

    public String getPath() {
        return path;
    }
}
