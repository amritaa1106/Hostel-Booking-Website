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
import com.hostel.dao.RoomDAO;
import com.hostel.model.Booking;
import com.hostel.model.Room;
import com.hostel.utils.DBConnection;

@WebServlet("/createBooking")
public class CreateBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("CreateBookingServlet.doPost called. Session ID: " + request.getSession().getId());

        // session email (you agreed to use email as identifier)
        String studentEmail = (String) request.getSession().getAttribute("studentEmail");
        if (studentEmail == null) {
            System.out.println("No studentEmail in session -> redirecting to index.jsp");
            response.sendRedirect("index.jsp");
            return;
        }

        // Read form params
        String roomIdParam = request.getParameter("roomId");
        String checkInParam = request.getParameter("checkInDate");
        String checkOutParam = request.getParameter("checkOutDate");
        String priceParam = request.getParameter("price"); // ensure your form uses name="price"

        // Basic validation: require either a roomId or a roomNumber
        String roomNumberParam = request.getParameter("roomNumber");
        if ((roomIdParam == null || roomIdParam.isEmpty()) && (roomNumberParam == null || roomNumberParam.isEmpty())) {
            request.setAttribute("message", "No room selected!");
            request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
            return;
        }

        int roomId = -1;
        if (roomIdParam != null && !roomIdParam.isEmpty()) {
            try {
                roomId = Integer.parseInt(roomIdParam);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid roomId: " + roomIdParam);
                request.setAttribute("message", "Invalid room selected.");
                request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
                return;
            }
        }

        // parse dates - check-in required, check-out auto-calculated as 6 months from check-in
        Date checkInDate = null;
        Date checkOutDate = null;
        try {
            if (checkInParam != null && !checkInParam.isEmpty()) {
                checkInDate = Date.valueOf(checkInParam);
                // Auto-calculate check-out as exactly 6 months from check-in
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(checkInDate);
                cal.add(java.util.Calendar.MONTH, 6);
                checkOutDate = new Date(cal.getTimeInMillis());
            } else {
                checkInDate = new Date(System.currentTimeMillis());
                // Auto-calculate check-out as exactly 6 months from check-in
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTime(checkInDate);
                cal.add(java.util.Calendar.MONTH, 6);
                checkOutDate = new Date(cal.getTimeInMillis());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            request.setAttribute("message", "Invalid dates provided.");
            request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
            return;
        }

        // parse price -> BigDecimal (safe)
        BigDecimal amount = BigDecimal.ZERO;
        if (priceParam != null && !priceParam.trim().isEmpty()) {
            try {
                // remove common formatting like commas (if any)
                String normalized = priceParam.replaceAll(",", "").trim();
                amount = new BigDecimal(normalized);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price format: '" + priceParam + "'. Using 0.");
                amount = BigDecimal.ZERO;
            }
        }

        // Build booking object
        Booking booking = new Booking();
        booking.setStudentEmail(studentEmail);
        // If roomId is not provided but roomNumber was submitted, resolve it
        if ((roomIdParam == null || roomIdParam.isEmpty()) && roomNumberParam != null && !roomNumberParam.isEmpty()) {
            try {
                RoomDAO roomDAO = new RoomDAO(DBConnection.getConnection());
                Room r = roomDAO.getRoomByNumber(roomNumberParam.trim());
                if (r != null) {
                    roomId = r.getRoomId();
                } else {
                    System.out.println("No room found for roomNumber=" + roomNumberParam);
                }
            } catch (Exception ex) {
                System.out.println("Error resolving room by number: " + ex.getMessage());
            }
        }
        booking.setRoomId(roomId);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setAmount(amount);
        booking.setStatus("Pending");

        // Debug logging before save
        System.out.println("Booking to save:");
        System.out.println("  studentEmail=" + booking.getStudentEmail());
        System.out.println("  roomId=" + booking.getRoomId());
        System.out.println("  checkIn=" + booking.getCheckInDate());
        System.out.println("  checkOut=" + booking.getCheckOutDate());
        System.out.println("  amount=" + booking.getAmount());

        BookingDAO bookingDAO = new BookingDAO();
        boolean success = bookingDAO.createBooking(booking);

        if (success) {
            System.out.println("Booking created successfully. Redirecting to /myBookings");
            // Redirect to your servlet that populates bookings and forwards to booking.jsp
            response.sendRedirect(request.getContextPath() + "/myBookings");
        } else {
            System.out.println("Booking creation failed.");
            request.setAttribute("message", "Booking failed. Please try again.");
            request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
        }
    }
}
