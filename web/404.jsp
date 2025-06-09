<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 - Page Not Found</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="error-page">
                <h1><i class="fas fa-exclamation-triangle"></i> 404</h1>
                <h2>Oops! Page Not Found</h2>
                <p>The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.</p>
                <div class="error-actions">
                    <a href="${pageContext.request.contextPath}/" class="btn">
                        <i class="fas fa-home"></i> Go to Homepage
                    </a>
                </div>
            </div>
        </div>
    </body>
</html> 