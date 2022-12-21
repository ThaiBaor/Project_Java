package database;


import java.sql.DriverManager;
import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class DatabaseUtil {
    public static Connection c;
    private static String url = "jdbc:mysql://localhost:3306/qlbenhvien";
    private static String username = "root";
    private static String password ="";
    public static Connection  getConnection() throws Exception{
        if (c==null){
            c=DriverManager.getConnection(url, username, password);
        }
        return c;
    }
    
}
