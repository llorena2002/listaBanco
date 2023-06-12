<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } %>
    
    <form action="login" method="POST">
        <label for="username">Usuário:</label>
        <input type="text" id="username" name="username">
        <br>
        
        <label for="password">Senha:</label>
        <input type="password" id="password" name="password">
        <br>
        
        <input type="submit" value="Login">
    </form>
    <a href="signup">Cadastrar</a>
</body>
</html>
