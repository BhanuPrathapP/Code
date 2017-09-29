package com.bhanu.jdbc;


import java.sql.*;

public class DAO {

    public static void main(String[] args){

        StudentDAO dao = new StudentDAO();
        dao.connect();
        Student s1 = dao.getStudent(2);
        System.out.println(s1.sName);

        Student s2 = new Student();
        s2.sName ="gokul";
        s2.id = 12;
        s2.address = "pune";
        dao.connect();
        dao.addStudent(s2);
        System.out.println(s2);
        dao.connect();
        dao.removeStudent(8);
    }
}

class Student{

    int id;
    String sName;
    String address;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sName='" + sName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class StudentDAO{

    Connection con = null;
    String query = null;

    public void connect(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aliens","root","bhanu1234");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Student getStudent(int id) {

        Student s = new Student();
        s.id = id;
        String query = "select * from students where id="+id;

        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(query);
            rs.next();
            s.sName = rs.getString(2);

        } catch (Exception e) {
            System.out.println(e);
        }

        return s;
    }

    public  void addStudent(Student s){
        query = "insert into students values (?,?,?)";
        try {
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setInt(1,s.id);
            pstat.setString(2,s.sName);
            pstat.setString(3,s.address);
            pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void removeStudent(int id) {

        query = "delete from students where id ="+id;

        try {
            Statement stat = con.createStatement();
            stat.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
