<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Atualizar Usuário</title>
</head>
<body>
    <h1>Atualizar Usuário</h1>

    <% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
    <% if (usuario != null) { %>
        <form action="editarUsuario" method="POST">
            <input type="hidden" name="login" value="<%= usuario.getLogin() %>">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= usuario.getNome() %>">
            <br>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="<%= usuario.getEmail() %>">
            <br>

            <input type="submit" value="Atualizar">
        </form>
    <% } else { %>
        <p>Usuário não encontrado.</p>
    <% } %>
</body>
</html>
