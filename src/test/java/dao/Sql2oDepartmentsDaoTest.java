package dao;

import models.Departments;
import models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentsDaoTest {

    private static Sql2oDepartmentsDao sql2oDepartmentsDao;
    private static Sql2oUsersDao sql2oUsersDao;
    private static Sql2oNewsDao sql2oNewsDao;
    private static Connection conn;

    @BeforeEach
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");

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
    //helper
    private Departments newDept(){
        Departments department = new Departments("Producers","We the best",7);
        sql2oDepartmentsDao.add(department);
        return department;
    }
    @Test
    public void departmentAddedToDatabase(){
        Departments department = newDept();
        System.out.println(department.getId());
        assertNotEquals(0,department.getId());
    }
    @Test
    public void getDepartmentUsingId(){
        Departments department = newDept();
        Departments department2 = new Departments("Delivery", "Everywhere you are",12);
        assertTrue(sql2oDepartmentsDao.getAll().contains(department));
        assertTrue(sql2oDepartmentsDao.getAll().contains(department2));
    }

    @Test
    public void addUserToDepartment(){
        Departments department = newDept();
        Users user = new Users ("jared", "Manager","delivery");
        sql2oDepartmentsDao.addUserToDepartment(user,department);
        assertEquals("Delivery",user.getName());
    }
    @Test
    public void getUsersInADepartment(){
        Departments department = newDept();
        Users user1 = new Users ("victor", "Manager","delivery");
        Users user2 = new Users ("makori", "Team builder","delivery");
        sql2oDepartmentsDao.addUserToDepartment(user1, department);
        sql2oDepartmentsDao.addUserToDepartment(user2, department);
        int IdOne = sql2oDepartmentsDao.getAllUsersInDepartment(department.getId()).get(0).getId();
        assertEquals(user1.getId(),IdOne);
    }

}











