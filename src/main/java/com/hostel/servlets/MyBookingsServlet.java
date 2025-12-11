package com.hostel.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hostel.dao.BookingDAO;
import com.hostel.model.Booking;

public class MyBookingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentEmail") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String studentEmail = (String) session.getAttribute("studentEmail");

        BookingDAO bookingDAO = new BookingDAO();
        List<Booking> bookings = bookingDAO.getBookingsByStudentEmail(studentEmail);

        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }
}
