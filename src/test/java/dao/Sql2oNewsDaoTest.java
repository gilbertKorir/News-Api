package dao;

import models.DepartmentNews;
import models.Departments;
import models.News;
import models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.testng.annotations.AfterClass;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oNewsDaoTest {
    private static Sql2oDepartmentsDao sql2oDepartmentsDao;
    private static Sql2oUsersDao sql2oUsersDao;
    private static Sql2oNewsDao sql2oNewsDao;
    private static Connection conn;

    @BeforeEach
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");

        sql2oDepartmentsDao = new Sql2oDepartmentsDao(sql2o);
        sql2oUsersDao = new Sql2oUsersDao(sql2o);
        sql2oNewsDao = new Sql2oNewsDao(sql2o);
        System.out.println("connected to database");
        conn = sql2o.open();
    }

    @AfterEach
    public void tearDown() throws Exception {
        sql2oDepartmentsDao.clearAll();
        sql2oUsersDao.clearAll();
        sql2oNewsDao.clearAll();
        System.out.println("clearing database");
    }
}
