/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Phuc
 */
public class GetConnection {
    private Connection conn;
    
    public Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://PHUC-PC:1433;databaseName=StampDuty";
            String url = "jdbc:sqlserver://PHUC-PC:1433;databaseName=StampDuty";
            String id = "sa";
            String password = "123456";
            conn = DriverManager.getConnection(url, id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
