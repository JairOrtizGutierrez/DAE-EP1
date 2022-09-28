package com.isil.jdbc;

import java.sql.*;

public class MainApp {

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DESA1", "root", "root");

            // CREATE
            statement(connection);

            // UPDATE
            preparedStatement(connection);

            // READ
            callableStatement(connection);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void statement(Connection connection) throws SQLException {

        Statement st = connection.createStatement();
        int affectedRows = st.executeUpdate("INSERT INTO Users (nombre, dni, ciudad) VALUES ('Luis', '01234567', 'Lima')");

        System.out.println("Affected rows: " + affectedRows);

    }

    public static void preparedStatement(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET nombre = ? WHERE id = ?");
        preparedStatement.setString(1, "Franco");
        preparedStatement.setInt(2,3);

        int affectedRows = preparedStatement.executeUpdate();

        System.out.println("Affected rows: " + affectedRows);

    }

    public static void callableStatement(Connection connection) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall("{call getAllUsers()}");
        ResultSet resultSet = callableStatement.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String nombre = resultSet.getString("nombre");
            String dni = resultSet.getString("dni");
            String ciudad = resultSet.getString("ciudad");

            System.out.println(id + " " + nombre + " " + dni + " " + ciudad);
        }

    }

}