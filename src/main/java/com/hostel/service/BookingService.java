package com.hostel.service;

import java.util.List;

import com.hostel.dao.BookingDAO;
import com.hostel.model.Booking;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();

    // Called from servlet when student/booker submits booking form
    public boolean createBooking(Booking booking) {
        // Optionally, verify input & add business logic
        return bookingDAO.createBooking(booking);
    }

    // For displaying current user's bookings (dashboard)
    public List<Booking> getBookingsByStudent(String studentEmail) {
      return bookingDAO.getBookingsByStudent(studentEmail);
    }

    // Expand for Admin actions if required
}
