<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Available Rooms</title>
    <link rel="stylesheet" href="css/style.css" />
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        th, td {
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #667eea;
            color: white;
            border-radius: 5px 5px 0 0;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1ff;
        }

        .btn[disabled] {
            background: #bbb;
            cursor: not-allowed;
        }

        .table-container {
            max-height: 70vh;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h2>Available Rooms</h2>
        <div class="table-container">
            <table>
                <tr>
                    <th>Room ID</th>
                    <th>Room Number</th>
                    <th>Type</th>
                    <th>Capacity</th>
                    <th>Beds Remaining</th>
                    <th>Price</th>
                    <th>Hostel</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="room" items="${rooms}">
                    <tr>
                        <td>${room.roomId}</td>
                        <td>${room.roomNumber}</td>
                        <td>${room.roomType}</td>
                        <td>${room.capacity}</td>
                        <td>${room.capacity - room.occupiedBeds}</td>
                        <td>${room.price}</td>
                        <td>${room.hostelName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${room.capacity - room.occupiedBeds > 0}">
                                    <form action="bookRoom" method="post" style="margin:0;">
                                        <input type="hidden" name="roomId" value="${room.roomId}" />
                                        <input type="hidden" name="amount" value="${room.price}" />
                                        <button type="submit" class="btn">Book Room</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn" disabled>Full</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="links">
            <a href="student-dashboard.jsp" class="btn">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
