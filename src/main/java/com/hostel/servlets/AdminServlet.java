package com.hostel.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostel.dao.BookingDAO;
import com.hostel.model.Admin;
import com.hostel.model.Booking;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String userRole = (String) request.getSession().getAttribute("userRole");
        if (userRole == null || !"admin".equals(userRole)) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Load actual bookings data for dashboard with details
        BookingDAO bookingDAO = new BookingDAO();
        List<Booking> bookings = bookingDAO.getAllBookingsWithDetails();
        
        // Get pending bookings count for widget
        int pendingCount = bookingDAO.getPendingBookingsCount();
        
        // Get notifications
        List<String> notifications = bookingDAO.getPendingBookingsNotifications();

        request.setAttribute("bookings", bookings);
        request.setAttribute("pendingBookingsCount", pendingCount);
        request.setAttribute("notifications", notifications);

        // TODO: You can also load rooms, students, statistics here for more info
        // Example:
        // RoomDAO roomDAO = new RoomDAO();
        // List<Room> rooms = roomDAO.getAllRooms();
        // request.setAttribute("rooms", rooms);

        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }
}
