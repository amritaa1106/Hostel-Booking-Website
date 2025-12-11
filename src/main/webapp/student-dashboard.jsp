<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hostel.model.Room" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Browse Available Rooms | Campus Hostel</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/css?family=Inter:400,600,700,900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <style>
    body {
      margin: 0;
      min-height: 100vh;
      background: linear-gradient(140deg, #fcfcfe 45%, #f5f5ff 100%),
                  url('images/campushostel.jpg') center/cover no-repeat fixed;
      font-family: 'Inter', Arial, sans-serif;
    }

    df-messenger {
      z-index: 999;
      position: fixed;
      bottom: 16px;
      right: 16px;
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
    .nav-btn {
      background: #7367f0;
      color: #fff;
      font-size: 1.16rem;
      font-weight: 600;
      padding: 0.75em 1.5em;
      border-radius: 13px;
      border: none;
      box-shadow: 0 3px 12px #d5cafd42;
      cursor: pointer;
      transition: background .16s;
      margin-right: 0.5rem;
    }
    .nav-btn:hover { background: #5541c9; }
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
    .search-row {
      display: flex;
      gap: 1.1rem;
      margin: 2rem 2vw 0 2vw;
      align-items: center;
    }
    .search-box {
      flex: 2;
      display: flex;
      align-items: center;
      background: #f7f7fb;
      border-radius: 17px;
      padding: 0.7em 1.3em;
      font-size: 1.2rem;
      color: #656597;
      border: 1.5px solid #eceafd;
      transition: border-color 0.2s;
    }
    .search-box:focus-within { border-color: #7367f0; }
    .search-box .bi {
      font-size: 1.4rem;
      margin-right: 0.7em;
      color: #a4a4d5;
    }
    .search-input {
      flex: 1;
      border: none;
      background: none;
      font-size: 1.1rem;
      color: #232150;
      outline: none;
      font-family: inherit;
    }
    .search-input::placeholder { color: #a4a4d5; }
    .type-filter {
      background: #f7f7fb;
      border-radius: 17px;
      padding: 1em 1.3em;
      font-size: 1.09rem;
      border: 1.5px solid #eceafd;
      color: #656597;
      cursor: pointer;
      min-width: 120px;
    }
    .rooms-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
      gap: 2.2rem;
      margin: 2rem 2vw 2rem 2vw;
      justify-content: center;
      align-items: stretch;
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
      position: absolute;
      top: 10px; right: 18px;
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
    .room-title .bi { font-size: 1.3rem; color: #7367f0; }
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
      display: flex; align-items: center; gap: .65em;
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
      display: box;                /* standard */
      -webkit-line-clamp: 3;
      line-clamp: 3;               /* standard */
      -webkit-box-orient: vertical;
      box-orient: vertical;        /* standard */
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
      background: #7367f0;
      color: #fff;
      width: 98%;
      border: none;
      border-radius: 11px;
      font-size: 1.23rem;
      font-weight: 700;
      padding: 0.83rem 0;
      margin-top: auto;
      box-shadow: 0 2.5px 11px #d5cafd55;
      transition: background .13s;
      letter-spacing: 0.01em;
      font-family: 'Inter', Arial, sans-serif;
      cursor: pointer;
    }
    .book-btn:hover { background: #5541c9; }
  </style>
</head>
<body>

  <div class="header-bar">
    <span class="header-brand"><i class="bi bi-building"></i> Campus Hostel</span>
    <div class="header-links">
      <button class="nav-btn">Browse Rooms</button>
      <a href="${pageContext.request.contextPath}/myBookings" class="navbar-link">My Bookings</a>

    </div>
  </div>

  <div class="page-title">Browse Available Rooms</div>
  <div class="subtitle">Find your perfect accommodation from our 6 available rooms</div>

  <div class="rooms-grid">
    <%
      List<Room> rooms = (List<Room>) request.getAttribute("rooms");
      if (rooms != null && !rooms.isEmpty()) {
        for (Room room : rooms) {
    %>
      <div class="room-card">
        <img src="<%= room.getImageUrl() %>" alt="Room <%= room.getRoomNumber() %>" class="room-img" />
        <span class="room-status"><%= room.getStatus() %></span>
        <div class="card-content">
          <div class="room-title-row">
            <span class="room-title"><i class="bi bi-building"></i> Room <%= room.getRoomNumber() %></span>
            <span class="room-price">₹<%= room.getPrice() %><br>
              <span style="font-size:0.95em;font-weight:400;color:#6a6a8c;">per semester</span>
            </span>
          </div>
          <div class="room-meta"><%= room.getRoomType() %> • Floor <%= room.getFloor() %></div>
          <div class="capacity"><i class="bi bi-people"></i> Capacity: <%= room.getCapacity() %></div>
          <div class="room-desc"><%= room.getAmenities() %></div>
          
          <form action="${pageContext.request.contextPath}/createBooking" method="post">

            <input type="hidden" name="roomId" value="<%= room.getRoomId() %>">
            <input type="hidden" name="roomNumber" value="<%= room.getRoomNumber() %>">
            <input type="hidden" name="roomType" value="<%= room.getRoomType() %>">
            <input type="hidden" name="hostelName" value="<%= room.getHostelName() %>">
            <input type="hidden" name="price" value="<%= room.getPrice() %>">
          
            <label>Check-In Date:</label>
            <input type="date" name="checkInDate" id="checkInDate_<%= room.getRoomId() %>" required onchange="calculateCheckOut(<%= room.getRoomId() %>)">
          
            <label>Check-Out Date (Auto: 6 months from check-in):</label>
            <input type="date" name="checkOutDate" id="checkOutDate_<%= room.getRoomId() %>" readonly style="background-color: #f0f0f0;">
          
          <script>
          function calculateCheckOut(roomId) {
            var checkIn = document.getElementById('checkInDate_' + roomId).value;
            if (checkIn) {
              var date = new Date(checkIn);
              date.setMonth(date.getMonth() + 6);
              var checkOut = date.toISOString().split('T')[0];
              document.getElementById('checkOutDate_' + roomId).value = checkOut;
            }
          }
          </script>
          
            <button type="submit" class="book-btn">Book Room</button>
          </form>


        </div>
      </div>
    <%
        }
      } else {
    %>
      <div style="text-align: center; padding: 3rem; color: #999; font-size: 1.2rem;">
        No rooms available at this time. Please check back later.
      </div>
    <%
      }
    %>
  </div>

<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
  intent="WELCOME"
  chat-title="HostelBooking"
  agent-id="361fdeb8-faca-49a0-a739-3fc38a73aa0e"
  language-code="en"
></df-messenger>
</body>
</html>
