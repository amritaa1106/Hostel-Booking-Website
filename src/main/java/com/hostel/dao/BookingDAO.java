package com.hostel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hostel.model.Booking;
import com.hostel.utils.DBConnection;

public class BookingDAO {
    private static final Logger LOGGER = Logger.getLogger(BookingDAO.class.getName());

    // ✅ Create new booking entry using student_email with booking_date
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (student_email, room_id, check_in_date, check_out_date, amount, status, booking_date) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, booking.getStudentEmail());
            ps.setInt(2, booking.getRoomId());
            ps.setDate(3, booking.getCheckInDate());
            ps.setDate(4, booking.getCheckOutDate());
            ps.setBigDecimal(5, booking.getAmount());
            ps.setString(6, booking.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error creating booking", e);
            return false;
        }
    }

    // ✅ Get bookings for one student (using email) WITH room info
    public List<Booking> getBookingsByStudent(String studentEmail) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT b.*, r.room_type, r.price, r.room_number " +
                     "FROM bookings b " +
                     "JOIN rooms r ON b.room_id = r.room_id " +
                     "WHERE b.student_email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentEmail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getInt("booking_id"));
                b.setStudentEmail(rs.getString("student_email"));
                b.setRoomId(rs.getInt("room_id"));
                b.setCheckInDate(rs.getDate("check_in_date"));
                b.setCheckOutDate(rs.getDate("check_out_date"));
                b.setAmount(rs.getBigDecimal("amount"));
                b.setStatus(rs.getString("status"));

                // Room info for JSP
                b.setRoomType(rs.getString("room_type"));
                b.setPrice(rs.getBigDecimal("price"));
                b.setRoomNumber(rs.getString("room_number"));
                b.setRequestedDate(rs.getDate("booking_date"));

                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // ✅ New method to fetch bookings using student email (excluding cancelled)
    public List<Booking> getBookingsByStudentEmail(String email) {
    List<Booking> list = new ArrayList<>();
    String sql = "SELECT b.*, r.room_type, r.price, r.room_number, DATE(b.booking_date) as booking_date_val " +
                 "FROM bookings b JOIN rooms r ON b.room_id = r.room_id " +
                 "WHERE b.student_email = ? AND b.status != 'Cancelled'";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Booking b = new Booking();
            b.setBookingId(rs.getInt("booking_id"));
            b.setStudentEmail(rs.getString("student_email"));
            b.setRoomId(rs.getInt("room_id"));
            b.setCheckInDate(rs.getDate("check_in_date"));
            b.setCheckOutDate(rs.getDate("check_out_date"));
            b.setAmount(rs.getBigDecimal("amount"));
            b.setStatus(rs.getString("status"));
            b.setRoomType(rs.getString("room_type"));
            b.setRoomNumber(rs.getString("room_number"));
            b.setPrice(rs.getBigDecimal("price"));
            // Set requested date from booking_date
            if (rs.getDate("booking_date_val") != null) {
                b.setRequestedDate(rs.getDate("booking_date_val"));
            }
            list.add(b);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }

    // ✅ Get all bookings (for admin, no joins)
    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getInt("booking_id"));
                b.setStudentEmail(rs.getString("student_email"));
                b.setRoomId(rs.getInt("room_id"));
                b.setCheckInDate(rs.getDate("check_in_date"));
                b.setCheckOutDate(rs.getDate("check_out_date"));
                b.setAmount(rs.getBigDecimal("amount"));
                b.setStatus(rs.getString("status"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ All bookings WITH student and room info for admin dashboard
    public List<Booking> getAllBookingsWithDetails() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT b.*, r.room_number, r.room_type, r.price " +
                     "FROM bookings b " +
                     "JOIN rooms r ON b.room_id = r.room_id " +
                     "ORDER BY b.booking_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getInt("booking_id"));
                b.setStudentEmail(rs.getString("student_email"));
                b.setRoomId(rs.getInt("room_id"));
                b.setCheckInDate(rs.getDate("check_in_date"));
                b.setCheckOutDate(rs.getDate("check_out_date"));
                b.setAmount(rs.getBigDecimal("amount"));
                b.setStatus(rs.getString("status"));

                // Extra info for admin JSP
                b.setRoomNumber(rs.getString("room_number"));
                b.setRoomType(rs.getString("room_type"));
                b.setPrice(rs.getBigDecimal("price"));

                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // ✅ Update booking status (for approve/cancel)
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error updating booking status", e);
            return false;
        }
    }
    
    // ✅ Get count of pending bookings for notifications
    public int getPendingBookingsCount() {
        String sql = "SELECT COUNT(*) as count FROM bookings WHERE status = 'Pending'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error getting pending bookings count", e);
        }
        return 0;
    }
    
    // ✅ Get list of pending bookings for notifications
    public List<String> getPendingBookingsNotifications() {
        List<String> notifications = new ArrayList<>();
        String sql = "SELECT student_email, room_id FROM bookings WHERE status = 'Pending' ORDER BY booking_id DESC LIMIT 5";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                String email = rs.getString("student_email");
                int roomId = rs.getInt("room_id");
                notifications.add("Booking pending from " + email + " for room " + roomId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error getting pending bookings notifications", e);
        }
        return notifications;
    }
}
