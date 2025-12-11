<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Test Page</title>
</head>
<body>
    <h1>Error Test Page</h1>
    
    <h2>Test Links:</h2>
    <ul>
        <li><a href="nonexistent-page.jsp">Test 404 Error</a></li>
        <li><a href="javascript:void(0)" onclick="testError()">Test 500 Error</a></li>
    </ul>

    <%
        // Test for error if parameter is present
        if (request.getParameter("error") != null) {
            throw new Exception("This is a test exception!");
        }
    %>

    <script>
        function testError() {
            window.location.href = "errortest.jsp?error=true";
        }
    </script>
</body>
</html>