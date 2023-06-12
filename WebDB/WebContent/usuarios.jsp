<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>
<%@ page import ="java.util.stream.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <style>
	    .container {
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  height: 5vh;
		}
	</style>
</head>
<body>
    <h1>Lista de Usuários</h1>
    
    <table>
        <thead>
            <tr>
                <th>Login</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Ações</th>
                <th><a href="adicionarUsuario">Cadastrar Usuario</a></th>
            </tr>
        </thead>
        <tbody>
            <% List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios"); %>
            <% for (Usuario usuario : usuarios) { %>
                <tr>
                    <td><%= usuario.getLogin() %></td>
                    <td><%= usuario.getNome() %></td>
                    <td><%= usuario.getEmail() %></td>
                    <td>
                    	<div class="container">
                        <form method="POST" action="deletarUsuario?login=<%= usuario.getLogin()%>" class="form">
                        <input type="submit" value="Deletar">
                        </form>
                        <button><a href="editarUsuario?login=<%= usuario.getLogin()%>">Editar</a></button>

                        </div>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
