package com.hostel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hostel.model.Student;
import com.hostel.utils.DBConnection;

public class StudentDAO {

    // Get student by EMAIL AND PASSWORD (for login)
    public Student getStudentByEmailAndPassword(String email, String password) {
        String sql = "SELECT student_id, reg_number, full_name, email, password FROM students WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setRegNumber(rs.getString("reg_number"));
                student.setFullName(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get student by EMAIL ONLY (for admin dashboard, profiles, etc)
    public Student getStudentByEmail(String email) {
        String sql = "SELECT student_id, reg_number, full_name, email, password FROM students WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setRegNumber(rs.getString("reg_number"));
                student.setFullName(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
