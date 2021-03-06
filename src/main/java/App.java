import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.*;
import exceptions.ApiException;
import models.Departments;
import models.News;
import models.Users;
//import org.h2.util.New;
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
        Sql2oWebDao sql2oWebDao;
        Connection conn;
        Gson gson = new Gson();
//
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
        staticFileLocation("/public");

        /*---------------- LOCAL DATABASE-----------------------------*/
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");
        /*-----------------------------------------------------------*/

        /*---------------- HEROKU DATABASE-----------------------------*/
//        String connectionString = "jdbc:postgresql://ec2-44-194-117-205.compute-1.amazonaws.com:5432/d3ao1fne8ns4sq"; //!
//        Sql2o sql2o = new Sql2o(connectionString, "qolprnzdjugeme", "d31953431c986ba7f809654330bb35d8c501656c4148ad93055a4f55a973fb54");
        /*------------------------------------------------------------*/

        sql2oDepartmentsDao=new Sql2oDepartmentsDao(sql2o);
        sql2oNewsDao=new Sql2oNewsDao(sql2o);
        sql2oUsersDao=new Sql2oUsersDao(sql2o);
        sql2oWebDao= new Sql2oWebDao(sql2o);
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
        post("/department/new", "application/json",(request, response) -> {
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
        /*--------------------------DEPARTMENTS END-----------------------------------------*/

        /*--------------------------USERS-----------------------------------------*/
        get("/users", "application/json",(request, response) -> {
            if(sql2oUsersDao.getAll().size()>0){
                return gson.toJson(sql2oUsersDao.getAll());
            }else {
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });
        post("users/new", "application/json",(request, response) -> {
            Users user = gson.fromJson(request.body(), Users.class);
            sql2oUsersDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });
        get("users/:id","application/json",(request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            if(sql2oUsersDao.findById(id)==null){
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")));
            }else{
                return gson.toJson(sql2oUsersDao.findById(id));
            }
        });
        get("/users/:id/departments","application/json",(request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            if (sql2oUsersDao.getAllUserDepartments(id).size()>0){
                return gson.toJson(sql2oUsersDao.getAllUserDepartments(id));
            }
            else {
                return "{\"message\":\"I'm sorry, but user is in no department.\"}";
            }
        });
        post("/add/user/:user_id/department/:department_id","application/json",(request, response) -> {

            int user_id=Integer.parseInt(request.params("user_id"));
            int department_id=Integer.parseInt(request.params("department_id"));
            Departments departments=sql2oDepartmentsDao.findById(department_id);
            Users users=sql2oUsersDao.findById(user_id);
            if(departments==null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("department_id")));
            }
            if(users==null){
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("user_id")));
            }
            sql2oDepartmentsDao.addUserToDepartment(users,departments);

            List<Users> departmentUsers=sql2oDepartmentsDao.getAllUsersInDepartment(departments.getId());

            response.status(201);
            return gson.toJson(departmentUsers);
        });
        /*--------------------------USERS END-----------------------------------------*/

        /*--------------------------NEWS-----------------------------------------*/
        get("/news/general", "application/json", (request, response) -> {
            if(sql2oNewsDao.getAll().size()>0){
                return gson.toJson(sql2oNewsDao.getAll());
            }else{
                return "{\"message\":\"I'm sorry, but user is in no department.\"}";
            }
        });
        get("/news/department/:id", "application/json",(request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            Departments departments = sql2oDepartmentsDao.findById(id);
            if(departments==null){
                throw new ApiException(404, String.format("No department with the Id : \"%s\"," +
                        request.params("id")));
            }
            else if(sql2oDepartmentsDao.getDepartmentNews(id).size()>0){
                return gson.toJson(sql2oDepartmentsDao.getDepartmentNews(id));
            }
            else{
                return "{\"message\":\"I'm sorry, but no news in this department.\"}";
            }
        });
        post("/news/general/new","application/json",(request, response) -> {
            News news = gson.fromJson(request.body(),News.class);
            sql2oNewsDao.addNews(news);
            response.status(201);
            return gson.toJson(news);
        });
        post("/news/department/new","application/json", (request, response) -> {
            News departmentNews = gson.fromJson(request.body(), News.class);
            Departments departments = sql2oDepartmentsDao.findById(departmentNews.getDepartment_id());
            Users users = sql2oUsersDao.findById(departmentNews.getUser_id());

            if(departments==null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            if(users==null){
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")));
            }
            sql2oNewsDao.addNews(departmentNews);
            response.status(201);
            return gson.toJson(departmentNews);
        });
//        get("/sitemap","application/json",(request, response) ->{
//            return gson.toJson(sql2oWebDao.allPaths());
//        });
        /*----------------------FILTERS------------------------*/
        exception(ApiException.class, (exception, request,response)->{
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });
        after((request, response) -> {
            response.type("application/json");
        });
    }
}








































