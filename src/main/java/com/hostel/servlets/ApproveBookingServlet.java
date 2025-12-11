package com.hostel.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostel.dao.BookingDAO;

@WebServlet("/approveBooking")
public class ApproveBookingServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Check if admin is logged in
        String userRole = (String) request.getSession().getAttribute("userRole");
        if (userRole == null || !"admin".equals(userRole)) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String bookingIdParam = request.getParameter("bookingId");
        if (bookingIdParam == null || bookingIdParam.isEmpty()) {
            response.sendRedirect("admin-dashboard.jsp");
            return;
        }
        
        try {
            int bookingId = Integer.parseInt(bookingIdParam);
            BookingDAO bookingDAO = new BookingDAO();
            boolean success = bookingDAO.updateBookingStatus(bookingId, "Approved");
            
            if (success) {
                response.sendRedirect("admin");
            } else {
                request.setAttribute("error", "Failed to approve booking");
                request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("admin");
        }
    }
}

