package com.bhanu.webservices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

    Connection cons=null;
    String query;

    public AlienRepository(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cons = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestAPI","root","bhanu1234");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public List<Alien> getAliens(){

        List<Alien> aliens = new ArrayList<>();
        query = "select * from Aliens";
        try {
            Statement stat = cons.createStatement();
            ResultSet rs = stat.executeQuery(query);

            while (rs.next()){

                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setAddress(rs.getString(3));
                aliens.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return aliens;
    }

    public Alien getAlien(int id){

        query = "select * from Aliens where id ="+id;
        Alien alien = new Alien();

        try {
            Statement stat = cons.createStatement();
            ResultSet rs = stat.executeQuery(query);
            if (rs.next()) {
                alien.setId(rs.getInt(1));
                alien.setName(rs.getString(2));
                alien.setAddress(rs.getString(3));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alien;
    }


    public void create(Alien a) {

        query = "INSERT INTO Aliens VALUES (?,?,?)";
        try {
            PreparedStatement stat = cons.prepareStatement(query);
            stat.setInt(1,a.getId());
            stat.setString(2,a.getName());
            stat.setString(3,a.getAddress());
            stat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Alien a){
        query = "update Aliens set aName= ?, address=? where id = ?";
        try {
            PreparedStatement stat = cons.prepareStatement(query);
            stat.setString(1,a.getName());
            stat.setString(2, a.getAddress());
            stat.setInt(3, a.getId());
            stat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        query = "delete from Aliens where id=?";

        try {
            PreparedStatement stat = cons.prepareStatement(query);
            stat.setInt(1,id);
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
