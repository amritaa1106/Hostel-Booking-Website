<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hostel.model.Booking" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>My Bookings - Campus Hostel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:400,600,700,900&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            margin: 0;
            min-height: 100vh;
            background: linear-gradient(140deg, #fcfcfe 45%, #f5f5ff 100%), url('images/campushostel.jpg') center/cover no-repeat fixed;
            font-family: 'Inter', Arial, sans-serif;
        }
        .header-bar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 1.4rem 2vw 1.1rem 2vw;
            background: #fff;
            box-shadow: 0 1.5px 20px #e5e5f25f;
            border-bottom: 1.5px solid #eceafd;
        }
        .header-brand {
            font-size: 2.05rem;
            font-weight: 800;
            color: #232150;
            display: flex;
            align-items: center;
            gap: 0.7rem;
        }
        .header-brand .bi {
            font-size: 2.1rem;
            background: #ece7fe;
            border-radius: 7px;
            color: #7367f0;
            padding: 0.18em;
            margin-right: 0.13em;
        }
        .header-links {
            display: flex;
            align-items: center;
            gap: 2rem;
        }
        .navbar-link {
            font-weight: 600;
            color: #232150;
            text-decoration: none;
            font-size: 1.08rem;
            margin-right: 0.9em;
        }
        .page-title {
            font-size: 2.8rem;
            font-weight: 800;
            color: #232150;
            margin: 2.2rem 0 0.5rem 2vw;
            letter-spacing: -0.02em;
        }
        .subtitle {
            font-size: 1.17rem;
            color: #5a5a78;
            margin-left: 2vw;
            margin-bottom: 2.7rem;
        }
        .booking-card {
            background: #fff;
            border-radius: 22px;
            width: 92vw;
            max-width: 1400px;
            box-shadow: 0 2.5px 17px 2px #e6e9fd51;
            border: 1.3px solid #eceafd;
            margin: 2.2rem auto 0 auto;
            display: flex;
            flex-direction: column;
            padding: 2rem 2.8rem;
        }
        .booking-room-row {
            display: flex;
            align-items: center;
            gap: 1.8em;
            margin-bottom: 0.6em;
        }
        .booking-room-title {
            font-size: 1.5rem;
            font-weight: 700;
            color: #232150;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }
        .booking-status {
            background: #ece7fe;
            color: #7367f0;
            font-weight: 600;
            padding: 0.28em 1.23em;
            border-radius: 8px;
            font-size: 1rem;
            margin-left: 1.1rem;
        }
        .booking-details-row {
            display: flex;
            align-items: flex-end;
            gap: 5.5vw;
            margin: 1.7em 0 0 0;
            font-size: 1.17rem;
        }
        .booking-label {
            color: #8181b2;
        }
        .booking-value {
            font-weight: 700;
            color: #232150;
        }
    </style>
</head>
<body>
<div class="header-bar">
    <span class="header-brand"><i class="bi bi-building"></i> Campus Hostel</span>
    <div class="header-links">
        <a href="/student" class="navbar-link">Browse Rooms</a>
        <a href="/logout" class="navbar-link">Logout</a>
    </div>
</div>
<div class="page-title">My Bookings</div>
<div class="subtitle">Track and manage your room booking requests</div>
<%
    if (bookings != null && !bookings.isEmpty()) {
        for (Booking booking : bookings) {
%>
    <div class="booking-card">
        <div class="booking-room-row">
            <span class="booking-room-title">
                <i class="bi bi-building"></i>
                Room <%= booking.getRoomNumber() %>
            </span>
            <span class="booking-status"><%= booking.getStatus().toUpperCase() %></span>
        </div>
        <div class="booking-label">Type - </div> <span class="booking-value"><%= booking.getRoomType() %></span>
          <span class="booking-label" style="margin-left:1.7rem">₹<%= booking.getPrice() %>/semester</span>
        <div class="booking-details-row" style="margin-top:1.3em;">
        <div>
            <span class="booking-label">Check-In<br></span>
            <span class="booking-value"><%= booking.getCheckInDate() %></span>
        </div>
        <div>
            <span class="booking-label">Check-Out<br></span>
            <span class="booking-value"><%= booking.getCheckOutDate() %></span>
        </div>
        <div>
           <span class="booking-label">Requested On<br></span>
            <span class="booking-value"><%= booking.getRequestedDate() != null ? booking.getRequestedDate() : (booking.getCheckInDate() != null ? booking.getCheckInDate() : "N/A") %></span>
        </div>
       </div>
       <div style="margin-top: 1.5rem;">
           <% if ("Pending".equalsIgnoreCase(booking.getStatus()) || "Approved".equalsIgnoreCase(booking.getStatus())) { %>
           <form action="/cancelBooking" method="post" style="display: inline;">
               <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
               <button type="submit" style="background: #dc3545; color: white; padding: 0.6rem 1.5rem; border: none; border-radius: 8px; cursor: pointer; font-weight: 600; font-size: 0.95rem;">Cancel Booking</button>
           </form>
           <% } %>
       </div>

    </div>
<%
        }
    } else {
%>
    <div class="booking-card" style="text-align:center;font-size:1.25rem;font-weight:500;color:#999;margin-top:3.2rem;">
        No bookings yet.
    </div>
<%
    }
%>

</body>
</html>
