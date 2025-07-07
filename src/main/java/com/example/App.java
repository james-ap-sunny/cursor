package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://mysql:3306/testdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "testuser";
        String password = "testpass";

        // 连接数据库
        Connection conn = DriverManager.getConnection(url, user, password);

        // 创建表
        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS demo (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))");

        // 插入数据
        PreparedStatement ps = conn.prepareStatement("INSERT INTO demo (name) VALUES (?)");
        ps.setString(1, "Hello Docker MySQL");
        ps.executeUpdate();

        // 查询并打印
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM demo");
        while (rs.next()) {
            System.out.println("id: " + rs.getInt("id") + ", name: " + rs.getString("name"));
        }

        conn.close();
    }
} 
