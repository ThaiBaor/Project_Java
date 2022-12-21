/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author ADMIN
 */
public class DAO {
    protected static Connection connection() throws Exception{
        Connection con = DatabaseUtil.getConnection();
        return con;
    }
    protected static PreparedStatement createPreparedStatement(String sql) throws Exception{
        Connection con = connection();
        PreparedStatement ps = con.prepareStatement(sql);
        return ps;
    }
}
