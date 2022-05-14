package models;

public class DepartmentNews extends News{

    private int department_id;
    private String news_type;
    private final String TYPE_OF_NEWS="department";

    public DepartmentNews(String title, String description, int department_id, int user_id) {
        super(title, description, user_id);
        this.department_id = department_id;
        this.news_type=TYPE_OF_NEWS;
    }

}
