package model.dao;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("✅ Kết nối PostgreSQL thành công!");
        } catch (Exception e) {
            System.out.println("❌ Kết nối thất bại:");
            e.printStackTrace();
        }
    }
}