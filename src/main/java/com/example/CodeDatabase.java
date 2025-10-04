package com.example;

import java.sql.*;

public class CodeDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/webcompiler";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "ragavi"; // 🔁 Change this!

    public static void saveCode(String code) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "INSERT INTO code_snippets (code) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("✅ Code saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Failed to save code.");
        }
    }
}
