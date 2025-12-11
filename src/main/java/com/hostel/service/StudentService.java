package com.hostel.service;

import com.hostel.dao.StudentDAO;
import com.hostel.model.Student;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();

    public Student login(String email, String password) {
        Student student = studentDAO.getStudentByEmail(email);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }

    // You can add registration/profile update functions here if needed
}
