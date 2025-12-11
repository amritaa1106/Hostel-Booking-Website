package com.hostel.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostel.dao.BookingDAO;

@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String bookingIdParam = request.getParameter("bookingId");
        if (bookingIdParam == null || bookingIdParam.isEmpty()) {
            response.sendRedirect("myBookings");
            return;
        }
        
        // Verify the booking belongs to the logged-in student
        String studentEmail = (String) request.getSession().getAttribute("studentEmail");
        if (studentEmail == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        try {
            int bookingId = Integer.parseInt(bookingIdParam);
            BookingDAO bookingDAO = new BookingDAO();
            boolean success = bookingDAO.updateBookingStatus(bookingId, "Cancelled");
            
            if (success) {
                response.sendRedirect("myBookings");
            } else {
                request.setAttribute("error", "Failed to cancel booking");
                request.getRequestDispatcher("booking.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("myBookings");
        }
    }
}

