/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.InitialContext;

/**
 *
 * @author 636334
 */
public class DBUtil
{
    public static Connection getConnection() throws Exception
    {
        Connection conn;
        InitialContext ic = new InitialContext();
        String dbUsername = (String) ic.lookup("java:comp/env/han/dbusername");
        String dbPassword = (String) ic.lookup("java:comp/env/han/dbpassword");
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", dbUsername, dbPassword);
        return conn;
    }
}
