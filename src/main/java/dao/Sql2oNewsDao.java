package dao;

import models.DepartmentNews;
import models.News;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;

    @Override
    public void addNews(News news) {

    }

    @Override
    public void addDepartmentNews(DepartmentNews departmentNews) {

    }

    @Override
    public List<News> getAll() {
        return null;
    }

    @Override
    public News findById(int id) {
        return null;
    }

    @Override
    public void clearAll() {

    }
}
