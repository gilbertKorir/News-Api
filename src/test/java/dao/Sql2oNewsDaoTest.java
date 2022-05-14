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
        //uncomment the two lines below to run locally and change to your  credentials
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");

        //comment the two lines below to run locally
//        String connectionString = "jdbc:postgresql://ec2-18-215-99-63.compute-1.amazonaws.com:5432/da93g9c21mukon";
//        Sql2o sql2o = new Sql2o(connectionString, "fvvikmppgjhovk", "c5fc3da5048cda471e429f687669b2eec7bca3c7c07d83df5681f43d9f5bfc28");
        sql2oDepartmentsDao=new Sql2oDepartmentsDao(sql2o);
        sql2oUsersDao=new Sql2oUsersDao(sql2o);
        sql2oNewsDao=new Sql2oNewsDao(sql2o);
        System.out.println("connected to database");
        conn=sql2o.open();
    }
    @AfterEach
    public void tearDown() throws Exception {
        sql2oDepartmentsDao.clearAll();
        sql2oUsersDao.clearAll();
        sql2oNewsDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }
    @Test
    public void addNews() {
        Users users=setUpNewUsers();
        sql2oUsersDao.add(users);
        Departments departments=setUpDepartment();
        sql2oDepartmentsDao.add(departments);
        News news=new News("Meeting","Meeting to set activities for team building",users.getId());
        sql2oNewsDao.addNews(news);

        assertEquals(users.getId(),sql2oNewsDao.findById(news.getId()).getUser_id());
        assertEquals(news.getDepartment_id(),sql2oNewsDao.findById(news.getId()).getDepartment_id());
    }

    @Test
    public void addDepartmentNews() {
        Users users=setUpNewUsers();
        sql2oUsersDao.add(users);
        Departments departments=setUpDepartment();
        sql2oDepartmentsDao.add(departments);
        DepartmentNews department_news =new DepartmentNews("Meeting","To nominate new chairman",departments.getId()
                ,users.getId());
        sql2oNewsDao.addDepartmentNews(department_news);
        assertEquals(users.getId(),sql2oNewsDao.findById(department_news.getId()).getUser_id());
        assertEquals(department_news.getDepartment_id(),sql2oNewsDao.findById(department_news.getId()).getDepartment_id());

    }
    @Test
    public void getAll() {
        Users users=setUpNewUsers();
        sql2oUsersDao.add(users);
        Departments departments=setUpDepartment();
        sql2oDepartmentsDao.add(departments);
        DepartmentNews department_news =new DepartmentNews("Meeting","To nominate new chairman",departments.getId()
                ,users.getId());
        sql2oNewsDao.addDepartmentNews(department_news);
        News news=new News("Meeting","Meeting to set activities for team building",users.getId());
        sql2oNewsDao.addNews(news);
        assertEquals(2,sql2oNewsDao.getAll().size());
    }

    private Departments setUpDepartment() {
        return new Departments("Editing","editing of books",3);
    }
    private Users setUpNewUsers() {
        return new Users("Moses John","Manager","Editor");
    }
}