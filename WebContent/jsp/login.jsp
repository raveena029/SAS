<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>SNU Mart Login</title>
    <style>
        body { font-family: Arial; background: #f2f2f2; text-align: center; margin-top: 100px; }
        form { display: inline-block; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px gray; }
    </style>
</head>
<body>
    <h2>Login to SNU Mart</h2>
    <form action="../LoginServlet" method="post">
        <input type="text" name="username" placeholder="Username" required/><br/><br/>
        <input type="password" name="password" placeholder="Password" required/><br/><br/>
        <button type="submit">Login</button><br/><br/>
        <p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
    </form>
</body>
</html>
