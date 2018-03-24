/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import sait.domain.User;

public class UsersDB
{

    public static void addUser(String username, String salt, String hashandsaltedPassword) throws ClassNotFoundException, SQLException, Exception
    {
        Connection conn = DBUtil.getConnection();

        String preparedQuery = "insert into users(username, salt, hashedandsaltedpassword) values (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(preparedQuery);
        ps.setString(1, username);
        ps.setString(2, salt);
        ps.setString(3, hashandsaltedPassword);

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void deleteUser(String username) throws ClassNotFoundException, SQLException, Exception
    {
        Connection conn = DBUtil.getConnection();

        String preparedQuery = "delete from users where username = ?";
        PreparedStatement ps = conn.prepareStatement(preparedQuery);
        ps.setString(1, username);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public static List<User> getUsers() throws ClassNotFoundException, SQLException, Exception
    {
        Connection conn = DBUtil.getConnection();

        String preparedQuery = "select * from users";
        PreparedStatement ps = conn.prepareStatement(preparedQuery);
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<User>();

        while (rs.next())
        {
            User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
            users.add(user);
        }

        rs.close();
        ps.close();
        conn.close();

        return users;
    }

    public static boolean isAdmin(String username) throws Exception
    {
        boolean isAdmin = false;
        Connection conn = DBUtil.getConnection();
        try
        {
            String preparedQuery = "select admin from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(preparedQuery);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                isAdmin = rs.getBoolean(1);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex)
        {
        }
        return isAdmin;
    }

    public static User getUser(String username) throws Exception
    {
        Connection conn = DBUtil.getConnection();
        User user = null;

        String preparedQuery = "select * from users where username = ?";

        PreparedStatement ps = conn.prepareStatement(preparedQuery);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            user = new User(rs.getString("username"), rs.getString("salt"), rs.getString("hashedandsaltedpassword"), rs.getBoolean("admin"));
        }
        rs.close();
        ps.close();
        conn.close();
        return user;

    }

    public static boolean validate(String username, String password) throws Exception
    {
        Connection conn = DBUtil.getConnection();
        try
        {
            boolean valid = false;

            String preparedQuery = "select * from users where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(preparedQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            int count = 0;

            while (rs.next())
            {
                count++;
            }

            rs.close();
            ps.close();
            conn.close();

            if (count > 0)
            {
                valid = true;
            }

            return valid;
        } catch (SQLException ex)
        {
            return false;
        }

    }

    //method to generate salt (random number)
    public static String generateSalt()
    {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    //method to hash password
    public static String hashPassword(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for (byte b : mdArray)
        {
            int v = b & 0xff;
            if (v < 16)
            {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    //method to hash the combination of password and salt
//    public static String hashAndSaltPassword(String password) throws NoSuchAlgorithmException
//    {
//        String salt = generateSalt();
//        return hashPassword(password + salt);
//    }
    //method to check password length
    public static void checkPasswordStrength(String password) throws Exception
    {
        if (password == null || password.trim().isEmpty())
        {
            throw new Exception("Password cannot be empty.");
        } else if (password.length() < 8)
        {
            throw new Exception("Password is too short. Must be at least 8 characters long");
        }
    }
}
