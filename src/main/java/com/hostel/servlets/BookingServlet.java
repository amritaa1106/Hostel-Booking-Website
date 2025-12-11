package com.hostel.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostel.dao.BookingDAO;
import com.hostel.model.Booking;

@WebServlet("/processBooking")
public class BookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("BookingServlet doPost CALLED!");
        String studentEmail = (String) request.getSession().getAttribute("studentEmail");

        if (studentEmail == null) {
            System.out.println("⚠️ Session expired or user not logged in!");
            request.setAttribute("message", "Please log in again to continue.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        String roomIdParam = request.getParameter("roomId");
        String amountParam = request.getParameter("amount");
        String checkInParam = request.getParameter("checkInDate");
        String checkOutParam = request.getParameter("checkOutDate");

        if (roomIdParam == null || roomIdParam.isEmpty()) {
            System.out.println("❌ No room selected!");
            request.setAttribute("message", "No room selected!");
            request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
            return;
        }

        int roomId = Integer.parseInt(roomIdParam);
        BigDecimal amount = (amountParam != null && !amountParam.isEmpty())
                ? new BigDecimal(amountParam)
                : BigDecimal.ZERO;
        
        // Parse check-in date and auto-calculate check-out as 6 months from check-in
        Date checkInDate = null;
        Date checkOutDate = null;
        if (checkInParam != null && !checkInParam.isEmpty()) {
            checkInDate = Date.valueOf(checkInParam);
        } else {
            checkInDate = new Date(System.currentTimeMillis());
        }
        
        // Auto-calculate check-out as exactly 6 months from check-in
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(checkInDate);
        cal.add(java.util.Calendar.MONTH, 6);
        checkOutDate = new Date(cal.getTimeInMillis());

        // ✅ Create booking
        Booking booking = new Booking();
        booking.setStudentEmail(studentEmail);
        booking.setRoomId(roomId);
        booking.setAmount(amount);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setStatus("Pending");

        System.out.println("Booking details before save:");
        System.out.println("  Email: " + studentEmail);
        System.out.println("  Room ID: " + roomId);
        System.out.println("  Amount: " + amount);
        System.out.println("  Check-in: " + checkInDate);
        System.out.println("  Check-out: " + checkOutDate);

        BookingDAO bookingDAO = new BookingDAO();
        boolean success = bookingDAO.createBooking(booking);

        if (success) {
            System.out.println("✅ Booking created successfully for " + studentEmail);
            response.sendRedirect("myBookings");
        } else {
            System.out.println("❌ Booking failed to save!");
            request.setAttribute("message", "Booking failed. Please try again.");
            request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
        }
    }
}
