package com.hostel.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostel.dao.StudentDAO;
import com.hostel.model.Admin;
import com.hostel.model.Student;
import com.hostel.service.AdminService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String userType = request.getParameter("userType"); // "admin" or "student"
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(userType)) {
            Admin admin = adminService.login(username, password);
            if (admin != null) {
                request.getSession().setAttribute("admin", admin);
                response.sendRedirect("admin-dashboard.jsp");
                return;
            }
        } else { // student login
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getStudentByEmailAndPassword(username, password);


            if (student != null) {
                // Debug: print actual fields after login
                
                System.out.println("DEBUG Student object: email=" + student.getEmail() + ", id=" + student.getStudentId());
            
                // Save session attributes
                request.getSession().setAttribute("student", student);
                request.getSession().setAttribute("studentEmail", student.getEmail());
                request.getSession().setAttribute("studentId", String.valueOf(student.getStudentId()));
            
                // Print what is saved to session
                System.out.println("DEBUG session studentId: " + request.getSession().getAttribute("studentId"));
            
                response.sendRedirect("student-dashboard.jsp");
                return;
                }

         



        }
        request.setAttribute("error", "Invalid credentials!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
