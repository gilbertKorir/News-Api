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

    public News(String title, String description,int department_id, int user_id){
        this.title = title;
        this.description = description;
        this.user_id=user_id;
        this.department_id = department_id;
        this.news_type="department";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getTitle() {
        return title;
    }

    public String getNews_type() {
        return news_type;
    }

    public String getDescription() {
        return description;
    }

}
