package com.hostel.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int studentId;
    private int roomId;
    private Timestamp bookingDate;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private String adminRemarks;
    private String paymentStatus;
    private BigDecimal amount;
    private Integer approvedBy;
    private Timestamp approvalDate;

    // Display-only fields
    private String studentName;
    private String roomNumber;
    private String hostelName;

    // Added JSP fields
    private String roomType;
    private BigDecimal price;
    private Date requestedDate;

    // Constructors
    public Booking() {}

    public Booking(int studentId, int roomId, Date checkInDate, Date checkOutDate, BigDecimal amount) {
        this.studentId = studentId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.amount = amount;
    }
    private String studentEmail;

    public String getStudentEmail() {
      return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
       this.studentEmail = studentEmail;
    }
  

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public Timestamp getBookingDate() { return bookingDate; }
    public void setBookingDate(Timestamp bookingDate) { this.bookingDate = bookingDate; }

    public Date getCheckInDate() { return checkInDate; }
    public void setCheckInDate(Date checkInDate) { this.checkInDate = checkInDate; }

    public Date getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(Date checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAdminRemarks() { return adminRemarks; }
    public void setAdminRemarks(String adminRemarks) { this.adminRemarks = adminRemarks; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Integer getApprovedBy() { return approvedBy; }
    public void setApprovedBy(Integer approvedBy) { this.approvedBy = approvedBy; }

    public Timestamp getApprovalDate() { return approvalDate; }
    public void setApprovalDate(Timestamp approvalDate) { this.approvalDate = approvalDate; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getHostelName() { return hostelName; }
    public void setHostelName(String hostelName) { this.hostelName = hostelName; }

    // --- JSP Getters/Setters ---
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Date getStartDate() { return checkInDate; }

    public Date getEndDate() { return checkOutDate; }

    public Date getRequestedDate() { return requestedDate; }
    public void setRequestedDate(Date requestedDate) { this.requestedDate = requestedDate; }
}
