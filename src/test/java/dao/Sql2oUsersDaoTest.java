package dao;

import models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oUsersDaoTest {
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

    @Test
    public void newUser(){
        Users user = new Users("John", "Delivery Assosciate", "editor");
        sql2oUsersDao.add(user);
    }
    @Test
    public void allInstancesAreReturned() {
        Users user1 = new Users("joe","Administrator","boundary");
        Users user2 = new Users("mark","developer","general");
        sql2oUsersDao.add(user1);
        sql2oUsersDao.add(user2);
        assertEquals(user1.getName(),sql2oUsersDao.getAll().get(0).getName());
        assertEquals(user2.getName(),sql2oUsersDao.getAll().get(1).getName());
    }
    @Test
    public void addedUserIsReturnedCorrectly() {
        Users user = new Users("John", "Delivery Assosciate", "editor");
        sql2oUsersDao.add(user);
        assertEquals(user.getName(),sql2oUsersDao.findById(user.getId()).getName());
    }
    @Test
    public void findingParticularUser(){
        Users user = new Users("joe","Administrator","boundary");
        Users user3 = new Users("paul","Administrator","boundary");
        Users foundUser = sql2oUsersDao.findById(user3.getId());
        assertEquals(false, user.equals(foundUser));
    }
    @Test
    public void deleteAllUsers_int(){
        Users user1 = new Users("joe","Administrator","boundary");
        Users user2 = new Users("mark","developer","general");
        sql2oUsersDao.clearAll();
        assertEquals(0, sql2oUsersDao.getAll().size());
    }

}




