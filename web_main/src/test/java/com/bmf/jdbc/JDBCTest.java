package com.bmf.jdbc;

import org.junit.Test;

import java.sql.*;

public class JDBCTest {

    @Test
    public void main() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            new com.mysql.jdbc.Driver();
            Connection connection = DriverManager.getConnection("jdbc:mysql://112.74.19.176:3500/za_forum?autoReconnect=true&amp;characterEncoding=utf8mb4",
                    "power_ad", "codespace0@#$");
            DatabaseMetaData metaData = connection.getMetaData();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM common_account.ac_feedback WHERE feedbackId = ? ");
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int anInt = resultSet.getInt("feedbackId");
                String feedbackContent = resultSet.getString("feedbackContent");
                System.out.println(anInt);
                System.out.println(feedbackContent);
            }
            connection.createStatement(1, 2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
