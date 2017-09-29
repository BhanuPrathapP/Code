package com.bhanu.jdbc;

import java.sql.*;

public class ExecuteQuery {

    public static void main(String[] args) throws Exception{

        String url = "jdbc:mysql://localhost:3306/aliens";
        String userName = "root";
        String password ="bhanu1234";
        String query = "select * from students";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,userName,password);
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);


        while (rs.next()){
            String name = rs.getString("sName");
            String address = rs.getString("address");
            int id = rs.getInt("id");
            System.out.println(id+" "+name+" "+address);
        }

        stat.close();
        con.close();
    }
}
