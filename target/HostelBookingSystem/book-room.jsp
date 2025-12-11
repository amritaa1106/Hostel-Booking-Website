<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hostel.model.Room" %>
<%
    Room room = (Room) request.getAttribute("room");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Book This Room - Campus Hostel</title>
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
        .room-card {
            background: #fff;
            border-radius: 22px;
            width: 410px;
            max-width: 97vw;
            margin-bottom: 2.4rem;
            box-shadow: 0 2.5px 17px 2px #e6e9fd51;
            border: 1.3px solid #eceafd;
            display: flex;
            flex-direction: column;
            position: relative;
            overflow: hidden;
            height: 100%;
            min-height: 520px;
        }
        .room-card:hover {
            box-shadow: 0 8px 32px 0px #d6d2fd27;
            border-color: #7367f0;
        }
        .room-img {
            width: 100%;
            height: 220px;
            object-fit: cover;
            border-radius: 13px 13px 0 0;
            display: block;
            background: #ece7fe;
        }
        .room-status {
            position: absolute; top: 10px; right: 18px;
            background: #28c76f;
            color: #fff;
            font-size: 1.01rem;
            font-weight: 700;
            border-radius: 9px;
            padding: 5px 13px;
            box-shadow: 0 2px 8px #28c76f22;
        }
        .card-content {
            padding: 1.65rem 2rem 1.5rem 2rem;
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }
        .room-title-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 2.2em;
            margin-bottom: 0.4em;
        }
        .room-title {
            font-size: 1.46rem;
            font-weight: 700;
            color: #232150;
            display: flex;
            align-items: center;
            gap: 0.7rem;
        }
        .room-title .bi {
            font-size: 1.3rem;
            color: #7367f0;
        }
        .room-price {
            font-size: 1.45rem;
            font-weight: 700;
            color: #7367f0;
            white-space: nowrap;
        }
        .room-meta {
            font-size: 1.09rem;
            color: #595976;
            margin-bottom: 0.23em;
        }
        .capacity {
            font-size: 1rem;
            font-weight: 600;
            color: #232150;
            margin-bottom: 0.23em;
            display: flex;
            align-items: center; gap: .65em;
        }
        .capacity .bi {
            opacity: 0.8; margin-right: 0.15em; font-size: 1.05rem;
        }
        .room-desc {
            color: #68758c;
            font-size: 1.06rem;
            margin-bottom: 0.5em;
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            display: box;
            -webkit-line-clamp: 3;
            line-clamp: 3;
            -webkit-box-orient: vertical;
            box-orient: vertical;
        }
        .amenities {
            display: flex; gap: 0.6em; flex-wrap: wrap; margin-bottom: 1.2em;
        }
        .amenity-tag {
            background: #f1f3fc;
            border-radius: 9px;
            color: #232150;
            font-size: 0.98em;
            padding: 0.48em 1.05em;
            font-weight: 600;
            display: flex; align-items: center; gap: 0.21em;
        }
        .book-btn {
            background: linear-gradient(135deg, #7367f0 0%, #8a7afc 100%);
            color: white;
            font-weight: 600;
            border: none;
            border-radius: 12px;
            padding: 0.9em 2.5em;
            font-size: 1.05rem;
            cursor: pointer;
            width: 100%;
            text-align: center;
            box-shadow: 0 4px 15px rgba(115, 103, 240, 0.3);
            transition: all 0.2s ease-in-out;
        }
        .book-btn:hover {
            background: linear-gradient(135deg, #5b4fe0 0%, #7261f5 100%);
            transform: translateY(-2px);
            box-shadow: 0 6px 18px rgba(115, 103, 240, 0.4);
        }
        .search-box {
            display: flex;
            align-items: center;
            background: #f7f7fb;
            border-radius: 17px;
            padding: 0.7em 1.3em;
            font-size: 1.2rem;
            color: #656597;
            border: 1.5px solid #eceafd;
            transition: border-color 0.2s;
            width: 100%;
            box-sizing: border-box;
        }
        .search-box:focus-within {
            border-color: #7367f0;
        }
        .search-box input, .search-box textarea {
            flex: 1;
            border: none;
            background: none;
            font-size: 1.1rem;
            color: #232150;
            outline: none;
            font-family: inherit;
        }
    </style>
</head>
<body>
<div class="header-bar">
    <span class="header-brand"><i class="bi bi-building"></i> Campus Hostel</span>
    <div class="header-links">
        <a href="rooms.jsp" class="navbar-link">Browse Rooms</a>
        <a href="dashboard.jsp" class="navbar-link">My Bookings</a>
    </div>
</div>
<div class="page-title" style="margin-top:2.5rem;">Book This Room</div>
<div class="subtitle">Finish your room booking request below – admin will review after you submit.</div>
<div style="display: flex; gap: 3vw; flex-wrap:wrap; justify-content:center; padding: 1rem 2vw 2.5rem 2vw;">
    <div class="room-card" style="flex: 1 1 440px; min-width:340px; max-width:490px;">
        <img class="room-img" src="<%= room.getImageUrl() %>" alt="Room Image">
        <span class="room-status">Available</span>
        <div class="card-content">
            <div class="room-title-row">
                <div class="room-title"><i class="bi bi-building"></i> Room <%= room.getRoomNumber() %></div>
            </div>
            <div class="room-meta"><b>Type</b>: <%= room.getRoomType() %>   <b>Floor</b>: <%= room.getFloor() %></div>
            <div class="capacity"><i class="bi bi-people"></i> <%= room.getCapacity() %> people</div>
            <div class="room-price">₹<%= room.getPrice().toPlainString() %>/semester</div>
            <div class="room-desc">Spacious <%= room.getRoomType().toLowerCase() %> room with balcony view</div>
            <div><b>Amenities</b>:
                <div class="amenities">
                    <% for (String amenity : room.getAmenities().split(",")) { %>
                        <span class="amenity-tag"><%= amenity.trim() %></span>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
    <div class="room-card" style="flex:1 1 420px; min-width:340px; max-width:470px; justify-content: flex-start; align-items: stretch;">
        <div class="card-content">
            <form method="post" action="submitBooking">
                <input type="hidden" name="roomId" value="<%= room.getRoomId() %>">
                <div style="margin-bottom:1.4em;">
                    <label for="startDate"><b>Start Date</b></label><br>
                    <input id="startDate" name="startDate" type="date" class="search-box" required style="width:100%;margin-top:0.32em;">
                </div>
                <div style="
