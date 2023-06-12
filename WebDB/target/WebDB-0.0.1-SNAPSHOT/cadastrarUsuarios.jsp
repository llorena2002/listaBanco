<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar</title>
</head>
<body>
    <h1>Formulário de cadastro</h1>
    
    <form action="singup" method="POST">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login">
        <span style="color: red;"><%= request.getAttribute("loginError") %></span>
        <br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <span style="color: red;"><%= request.getAttribute("passwordError") %></span>
        <br>
        
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword">
        <span style="color: red;"><%= request.getAttribute("confirmError") %></span>
        <br>
        
        <label for="email">Email:</label>
        <input type="text" id="email" name="email">
        <span style="color: red;"><%= request.getAttribute("emailError") %></span>
        <br>
        
        <label for="confirmEmail">Email:</label>
        <input type="text" id="confirmEmail" name="confirmEmail">
        <span style="color: red;"><%= request.getAttribute("emailError") %></span>
        <br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>