package com.hostel.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostel.dao.BookingDAO;
import com.hostel.model.Booking;

public class BookingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer studentId = (Integer) request.getSession().getAttribute("studentId");
        if (studentId == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        BookingDAO bookingDAO = new BookingDAO();
        String studentEmail = (String) request.getSession().getAttribute("studentEmail");
        List<Booking> bookings = bookingDAO.getBookingsByStudent(studentEmail);


        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }
}

