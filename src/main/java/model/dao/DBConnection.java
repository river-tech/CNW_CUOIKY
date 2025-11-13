package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:6969/template1";
    private static final String USER = "postgres";
    private static final String PASS = ""; // nếu có password thì điền vào

    static {
        try {
            Class.forName("org.postgresql.Driver");  // ✔ đúng Driver PostgreSQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load PostgreSQL driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}