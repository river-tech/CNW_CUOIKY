package model.dao;

import model.bean.StudentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<StudentBean> findAll() {
        List<StudentBean> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY hoten";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<StudentBean> search(String keyword, String faculty) {
        List<StudentBean> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM students WHERE 1=1");
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND hoten LIKE ?");
        }
        if (faculty != null && !faculty.isEmpty()) {
            sql.append(" AND khoa LIKE ?");
        }
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int idx = 1;
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(idx++, "%" + keyword + "%");
            }
            if (faculty != null && !faculty.isEmpty()) {
                ps.setString(idx, "%" + faculty + "%");
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapStudent(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(StudentBean student) {
        String sql = "INSERT INTO students (masv, hoten, gioitinh, khoa) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, student.getMasv());
            ps.setString(2, student.getHoten());
            ps.setString(3, student.getGioitinh());
            ps.setString(4, student.getKhoa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public StudentBean findById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapStudent(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(StudentBean student) {
        String sql = "UPDATE students SET masv=?, hoten=?, gioitinh=?, khoa=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, student.getMasv());
            ps.setString(2, student.getHoten());
            ps.setString(3, student.getGioitinh());
            ps.setString(4, student.getKhoa());
            ps.setInt(5, student.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private StudentBean mapStudent(ResultSet rs) throws SQLException {
        return new StudentBean(
                rs.getInt("id"),
                rs.getString("masv"),
                rs.getString("hoten"),
                rs.getString("gioitinh"),
                rs.getString("khoa"));
    }
}
