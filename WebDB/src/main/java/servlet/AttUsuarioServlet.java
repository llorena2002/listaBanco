package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Banco;
import model.Usuario;

@WebServlet("/editarUsuario")
public class AttUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar o ID do usuário a ser atualizado
        String loginUsuario = request.getParameter("login");
        

        System.out.println("O login do usuario é: "+loginUsuario);
        // Buscar o usuário no banco de dados pelo ID
        Usuario usuario = buscarUsuarioNoBanco(loginUsuario);

        // Verificar se o usuário foi encontrado
        if (usuario != null) {
            // Definir o usuário como atributo no request
            request.setAttribute("usuario", usuario);
            // Encaminhar a requisição para o JSP de atualização do usuário
            request.getRequestDispatcher("atualizarUsuario.jsp").forward(request, response);
        } else {
            // Caso o usuário não seja encontrado, redirecionar para uma página de erro ou exibir uma mensagem de erro adequada
            response.sendRedirect("paginaDeErro.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar os dados atualizados do usuário
        String loginUsuario = request.getParameter("login");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        // Atualizar o usuário no banco de dados
        boolean sucesso = atualizarUsuarioNoBanco(loginUsuario, nome, email);

        if (sucesso) {
            // Redirecionar para uma página de sucesso após a atualização
            response.sendRedirect("/WebDB/usuarios");
        } else {
            // Exibir mensagem de erro caso ocorra algum problema na atualização
            request.setAttribute("erro", "Ocorreu um erro ao atualizar o usuário.");
            request.getRequestDispatcher("atualizarUsuario.jsp").forward(request, response);
        }
    }

    private Usuario buscarUsuarioNoBanco(String loginUsuario) {
        Usuario usuario = null;

        try (Connection connection = Banco.getConnection()) {
        	System.out.println("Entrei no try");
            // Preparar a consulta SQL para buscar o usuário pelo ID
            String query = "SELECT * FROM usuario WHERE login = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, loginUsuario);
                System.out.println("preparei o statement");

                // Executar a consulta SQL
                try (ResultSet resultSet = statement.executeQuery()) {
                	System.out.println("executei a consulta " + resultSet.getRow());
                    // Verificar se o usuário foi encontrado
                    if (resultSet.next()) {
                    	System.out.println("tem coisa na consulta");
                        // Extrair os dados do usuário do ResultSet e criar um objeto Usuario
                    	String login = resultSet.getString("login");
                        String nome = resultSet.getString("nome");
                        String email = resultSet.getString("email");
                        usuario = new Usuario(login, nome, email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    private boolean atualizarUsuarioNoBanco(String loginUsuario, String nome, String email) {
        boolean sucesso = false;

        try (Connection connection = Banco.getConnection()) {
            // Preparar a consulta SQL para atualizar o usuário
            String query = "UPDATE usuario SET nome = ?, email = ? WHERE login = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nome);
                statement.setString(2, email);
                statement.setString(3, loginUsuario);

                // Executar a atualização
                int linhasAfetadas = statement.executeUpdate();
                sucesso = (linhasAfetadas > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }

}
