import com.google.gson.Gson;
import dao.NewsDao;
import dao.Sql2oDepartmentsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import exceptions.ApiException;
import models.Departments;
import models.News;
import org.h2.util.New;
import org.sql2o.Sql2o;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;
//import java.sql.Connection;

import org.sql2o.Connection;

import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {

        Sql2oNewsDao sql2oNewsDao;
        Sql2oUsersDao sql2oUsersDao;
        Sql2oDepartmentsDao sql2oDepartmentsDao;
        Connection conn;
        Gson gson = new Gson();
        staticFileLocation("/public");

        /*---------------- LOCAL DATABASE-----------------------------*/
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");
        /*-----------------------------------------------------------*/

        /*---------------- HEROKU DATABASE-----------------------------*/
//        String connectionString = "jdbc:postgresql://ec2-50-17-21-170.compute-1.amazonaws.com:5432/d8b8ehu0safpui"; //!
//        Sql2o sql2o = new Sql2o(connectionString, "mihpivzxyyqmlv", "5b4f9d76874ad368465a325b3993140263c6d254771908c3d283842d54fcad11");
        /*------------------------------------------------------------*/

        sql2oDepartmentsDao=new Sql2oDepartmentsDao(sql2o);
        sql2oNewsDao=new Sql2oNewsDao(sql2o);
        sql2oUsersDao=new Sql2oUsersDao(sql2o);
        conn=sql2o.open();

        /*----------------  DEPARTMENTS -----------------------------*/
        get("/department","application/json",(request, response) -> {
            if(sql2oDepartmentsDao.getAll().size()>0){
                return gson.toJson(sql2oDepartmentsDao.getAll());
            }
            else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }
        });
        post("department/new", "application/json",(request, response) -> {
            Departments departments = gson.fromJson(request.body(), Departments.class);
            sql2oDepartmentsDao.add(departments);
            response.status(201);
            return gson.toJson(departments);
        });
        get("/department/:id","application/json", (request, response) -> {
           int id = Integer.parseInt(request.params("id"));
           if(sql2oDepartmentsDao.findById(id)==null){
               throw new ApiException(404, String.format("No department with the id: \"%s\" exists"));
           }else {
               return gson.toJson(sql2oDepartmentsDao.findById(id));
           }
        });
        get("/department/:id/users", "application.json", (request, response) -> {
           int id = Integer.parseInt(request.params("id"));
           if(sql2oDepartmentsDao.getAllUsersInDepartment(id).size()>0){
               return gson.toJson(sql2oDepartmentsDao.getAllUsersInDepartment(id));
           }else {
               return "{\"message\":\"I'm sorry, but department has no users.\"}";
           }
        });
    }
}














