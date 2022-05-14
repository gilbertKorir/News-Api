package dao;

import models.Departments;
import models.News;
import models.Users;

import java.util.List;

public class Sql2oDepartmentsDao implements DepartmentsDao {
    @Override
    public void add(Departments department) {

    }

    @Override
    public void addUserToDepartment(Users user, Departments department) {

    }

    @Override
    public List<Departments> getAll() {
        return null;
    }

    @Override
    public Departments findById(int id) {
        return null;
    }

    @Override
    public List<Users> getAllUsersInDepartment(int department_id) {
        return null;
    }

    @Override
    public List<News> getDepartmentNews(int id) {
        return null;
    }

    @Override
    public void clearAll() {

    }
}
