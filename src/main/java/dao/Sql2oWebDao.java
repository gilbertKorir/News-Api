package dao;

import models.Web;
import org.sql2o.Connection;
//import java.sql.Connection;
import java.util.List;
import org.sql2o.Sql2o;

public class Sql2oWebDao {
    private final Sql2o sql2o;

    public Sql2oWebDao(Sql2o sql2o){

        this.sql2o = sql2o;
    }
    public List<Web> allPaths() {
        String sql = "SELECT * from web;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Web.class);
        }
    }
}
