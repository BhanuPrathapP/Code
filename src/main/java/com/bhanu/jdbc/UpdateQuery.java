package com.bhanu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateQuery
{
    public static void main(String[] args) throws Exception{

        String url ="jdbc:mysql://localhost:3306/aliens";
        String userName = "root";
        String password = "bhanu1234";
        String query = "insert into students(id, sName, address) values (5,'venkat', 'Hyderabad'),(6,'swetha', 'Hyderabad'),(7,'vasu','hyderabad'),(8,'nikhil', 'pune')";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement stat = con.createStatement();
        int count = stat.executeUpdate(query);

        System.out.println("rows effected: "+count);


    }
}
