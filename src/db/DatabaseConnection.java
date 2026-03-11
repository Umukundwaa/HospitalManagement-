package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    private static final String URL ="jdbc:mysql://localhost:3306/HospitalManagement";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConnection(){
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(URL, user, password);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}