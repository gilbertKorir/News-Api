package models;

public class News {
    private int id;
    private int user_id;
    private int department_id;
    private String title;
    private String news_type;
    private String description;
    private final String TYPE_OF_NEWS = "general";

    public News(String title, String description, int user_id) {
        this.user_id = user_id;
        this.department_id = 0;
        this.title = title;
        this.news_type = TYPE_OF_NEWS;
        this.description = description;
    }
}
