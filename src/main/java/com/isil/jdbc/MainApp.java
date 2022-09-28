package com.isil.jdbc;

import java.sql.*;

public class MainApp {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/DESA1";
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "root");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from Users");
            while(rs.next()){
                System.out.println(rs.getString("idUser") + " " +rs.getString("name") +" " + rs.getString("phone") +" " + rs.getString("city"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}