<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hostel.utils.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Database Test</title>
</head>
<body>
    <h1>Database Connection Test</h1>
    <%
        try {
            Connection conn = DBConnection.getConnection();
            if(conn != null && !conn.isClosed()) {
                out.println("<p style='color: green;'>Database connection successful!</p>");
                conn.close();
            }
        } catch (Exception e) {
            out.println("<p style='color: red;'>Database connection failed: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    %>
</body>
</html>