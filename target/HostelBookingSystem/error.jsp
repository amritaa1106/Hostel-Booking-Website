<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
        }
        .error-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .error-heading {
            color: #d32f2f;
        }
        .error-details {
            margin-top: 20px;
            padding: 10px;
            background-color: #f5f5f5;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1 class="error-heading">Oops! Something went wrong</h1>
        <p>We're sorry, but there was an error processing your request.</p>
        
        <div class="error-details">
            <% if (exception != null) { %>
                <h3>Error Details:</h3>
                <p><%= exception.getMessage() %></p>
            <% } %>
            
            <p><a href="${pageContext.request.contextPath}/">Return to Home Page</a></p>
        </div>
    </div>
</body>
</html>