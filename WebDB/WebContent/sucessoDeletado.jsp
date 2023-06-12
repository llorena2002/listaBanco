<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuario Deletado</title>
</head>
<body>
	<% String login = (String) request.getAttribute("login"); %>
    <h1>Sucesso ao excluir usuário!</h1>
    
    <p>Usuario de login: <%= login %> foi deletado com sucesso</p>
    
    <form action="usuarios" method="GET">
        <input type="submit" value="Ir para a Página de usuarios">
    </form>
</body>
</html>