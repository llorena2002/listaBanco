<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adicionar</title>
    <style>
    .error-message{
    color: red;
    }
    </style>
</head>
<body>
    <h1>Formulário de adicionar Usuário</h1>
    
    <form action="signup" method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login">
        <% String erro = (String) request.getAttribute("loginError"); %>

		<!-- Verifica se o erro não é nulo e não está vazio -->
		<% if (erro != null && !erro.isEmpty()) { %>
		  <div class="error-message">
		    Erro: <%= erro %>
		  </div>
		<% } %>
        <br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <% erro = (String) request.getAttribute("passwordError"); %>

		<!-- Verifica se o erro não é nulo e não está vazio -->
		<% if (erro != null && !erro.isEmpty()) { %>
		  <div class="error-message">
		    Erro: <%= erro %>
		  </div>
		<% } %>
        <br><br>
        
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword">
        <% erro = (String) request.getAttribute("confirmError"); %>

		<!-- Verifica se o erro não é nulo e não está vazio -->
		<% if (erro != null && !erro.isEmpty()) { %>
		  <div class="error-message">
		    Erro: <%= erro %>
		  </div>
		<% } %>
        <br><br>
        
        <label for="email">Email:</label>
        <input type="text" id="email" name="email">
        <% erro = (String) request.getAttribute("emailError"); %>

		<!-- Verifica se o erro não é nulo e não está vazio -->
		<% if (erro != null && !erro.isEmpty()) { %>
		  <div class="error-message">
		    Erro: <%= erro %>
		  </div>
		<% } %>
        <br><br>
        
        <label for="confirmEmail">Email:</label>
        <input type="text" id="confirmEmail" name="confirmEmail">
        <% erro = (String) request.getAttribute("emailError"); %>

		<!-- Verifica se o erro não é nulo e não está vazio -->
		<% if (erro != null && !erro.isEmpty()) { %>
		  <div class="error-message">
		    Erro: <%= erro %>
		  </div>
		<% } %>
        <br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>