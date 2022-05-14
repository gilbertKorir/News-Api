package dao;

import models.DepartmentNews;
import models.News;

import java.util.List;

public interface NewsDao {
    //CREATE
    void addNews(News news);
    void addDepartmentNews(DepartmentNews departmentNews);

    //READ
    List<News> getAll();
    News findById(int id);

    //UPDATE

    //DELETE
    void clearAll();
}
