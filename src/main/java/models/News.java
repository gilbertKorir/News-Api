package models;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                department_id == news.department_id &&
                user_id == news.user_id &&
                Objects.equals(news_type, news.news_type) &&
                Objects.equals(title, news.title) &&
                Objects.equals(description, news.description) &&
                Objects.equals(TYPE_OF_NEWS, news.TYPE_OF_NEWS);
    }

    @Override
    public  int hashCode(){
        return Objects.hash(id, news_type, department_id, user_id, title, description, TYPE_OF_NEWS);
    }
}







