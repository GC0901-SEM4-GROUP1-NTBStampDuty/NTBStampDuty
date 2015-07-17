/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {

    private Connection conn;

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
<<<<<<< HEAD
            //        String url = "jdbc:sqlserver://PHUC-PC:1433;databaseName=StampDuty";
            String url = "jdbc:sqlserver://YOSHINO\\SQLEXPRESS:1433;databaseName=StampDuty";
=======
//            String url = "jdbc:sqlserver://PHUC-PC:1433;databaseName=StampDuty";
//            String url = "jdbc:sqlserver://YOSHINO\\SQLEXPRESS:1433;databaseName=StampDuty";
>>>>>>> 0bd42aaf5a7a34f4928a3096971ad73c0f74584c
            //String url = "jdbc:sqlserver://SONNGUYEN\\SQLEXPRESS:1433;databaseName=StampDuty";
            String url = "jdbc:sqlserver://ADMIN-PC\\SQLEXPRESS:1433;databaseName=StampDuty";
            String id = "sa";
            String password = "123456";
            conn = DriverManager.getConnection(url, id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
