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
<<<<<<< HEAD
 * @author Administrator
 */
public class GetConnection {

    private Connection conn;

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://PHUC-PC:1433;databaseName=StampDuty";
            String url = "jdbc:sqlserver://YOSHINO\\SQLEXPRESS:1433;databaseName=StampDuty";
            //String url = "jdbc:sqlserver://SONNGUYEN\\SQLEXPRESS:1433;databaseName=StampDuty";
=======
 * @author Phuc
 */
public class GetConnection {
    private Connection conn;
    
    public Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://PHUC-PC:1433;databaseName=StampDuty";
            //String url = "jdbc:sqlserver://YOSHINO\\SQLEXPRESS:1433;databaseName=StampDuty";
            String url = "jdbc:sqlserver://SONNGUYEN\\SQLEXPRESS:1433;databaseName=StampDuty";
>>>>>>> c7f2e316d321fe2b33bd5865509ac372ee72870c
            String id = "sa";
            String password = "123456";
            conn = DriverManager.getConnection(url, id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
<<<<<<< HEAD

=======
>>>>>>> c7f2e316d321fe2b33bd5865509ac372ee72870c
}
