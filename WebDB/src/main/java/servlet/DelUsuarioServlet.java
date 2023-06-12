package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Banco;

@WebServlet("/deletarUsuario")
public class DelUsuarioServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar o login do usuário a ser deletado
        String login = request.getParameter("login");

        // Deletar o usuário no banco de dados
        boolean sucesso = deletarUsuarioNoBanco(login);

        if (sucesso) {
            // Redirecionar para uma página de sucesso após a exclusão
        	request.setAttribute("login", login);
        	request.getRequestDispatcher("sucessoDeletado.jsp").forward(request, response);
        } else {
            // Exibir mensagem de erro caso ocorra algum problema na exclusão
            request.setAttribute("erro", "Ocorreu um erro ao deletar o usuário.");
            request.getRequestDispatcher("paginaDeErro.jsp").forward(request, response);
        }
    }

    private boolean deletarUsuarioNoBanco(String login) {
        boolean sucesso = false;

        try (Connection connection = Banco.getConnection()) {
            // Preparar a consulta SQL para deletar o usuário pelo login
            String query = "DELETE FROM usuario WHERE login = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, login);

                // Executar a consulta SQL
                int linhasAfetadas = statement.executeUpdate();
                sucesso = (linhasAfetadas > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }

}

