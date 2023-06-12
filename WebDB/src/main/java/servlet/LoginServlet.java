package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Banco;

import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private Banco bd = new Banco();
	private Connection con;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("/WebDB/home");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validar campos em branco
        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Por favor, preencha todos os campos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Validar no banco de dados
        boolean isValidUser = validarUser(username, password);
        if (isValidUser) {
            // Usuário válido, redirecionar para a página de sucesso
            response.sendRedirect("/WebDB/home");
        } else {
            // Usuário inválido, exibir mensagem de erro
            request.setAttribute("error", "Credenciais inválidas.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean validarUser(String username, String password) {
        // Lógica para validar usuário no banco de dados usando JDBC
        try (Connection con = Banco.getConnection()) {
            String query = "SELECT COUNT(*) FROM usuario WHERE login = ? AND senha = ?";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratar erros de banco de dados ou lançar exceção para lidar com eles em outro lugar
        }
        return false;
    }

}
