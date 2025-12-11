<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>404 - Page Not Found</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
            text-align: center;
        }
        .error-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .error-code {
            font-size: 72px;
            color: #d32f2f;
            margin: 0;
        }
        .error-message {
            font-size: 24px;
            color: #666;
            margin-top: 10px;
        }
        .home-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #2196f3;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .home-link:hover {
            background-color: #1976d2;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1 class="error-code">404</h1>
        <p class="error-message">Page Not Found</p>
        <p>The page you're looking for doesn't exist or has been moved.</p>
        <a href="${pageContext.request.contextPath}/" class="home-link">Return to Home Page</a>
    </div>
</body>
</html>