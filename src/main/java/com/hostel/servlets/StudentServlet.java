package com.hostel.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostel.dao.RoomDAO;
import com.hostel.model.Room;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // -- Direct JDBC Connection (change values if needed)
        String dbURL = "jdbc:mysql://localhost:3306/hostel_booking_system";
        String dbUser = "root";                  // <-- USE your MySQL username
        String dbPass = "Discodisco11*";         // <-- USE your MySQL password
        List<Room> rooms = new java.util.ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            RoomDAO roomDAO = new RoomDAO(conn);
            rooms = roomDAO.getAllAvailableRooms();
            System.out.println("ROOMS DEBUG: Loaded " + rooms.size() + " rooms"); // Should be >0!
            conn.close();
        } catch (Exception e) {
            System.err.println("ERROR loading rooms: " + e.getMessage());
            e.printStackTrace();
            // Keep rooms as empty list instead of null
        }
        request.setAttribute("rooms", rooms);

        request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
    }
}
