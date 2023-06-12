package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Banco;

import java.io.IOException;
import java.sql.*;
import javax.servlet.http.HttpServlet;

@WebServlet("/adicionarUsuario")
public class AdicionarUsuarioServlet extends HttpServlet {
	private Banco bd = new Banco();
	private Connection con;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("adicionarUsuario.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String confirmEmail = request.getParameter("confirmEmail");

		boolean hasErrors = false;

		// a) Verificar se o login já existe na tabela
		if (verificarLoginExistente(login)) {
			hasErrors = true;
			request.setAttribute("loginError", "Login já existente");
		}

		// b) Verificar se a senha é diferente do login e possui 4 a 8 caracteres
		if (password.equals(login) || password.length() < 4 || password.length() > 8) {
			hasErrors = true;
			request.setAttribute("passwordError", "Senha inválida");
		}

		// c) Verificar se o email e senha foram confirmados
		if (!password.equals(confirmPassword) || (!email.equals(confirmEmail) && email.isEmpty())) {
			hasErrors = true;
			request.setAttribute("confirmError", "Email e/ou senha não confirmados");
		}

		if (hasErrors) {
			// Redirecionar de volta para o formulário exibindo as mensagens de erro
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarUsuarios.jsp");
			dispatcher.forward(request, response);
		} else {
			// Todas as validações passaram, fazer o cadastro do usuário
			cadastrarUsuario(login, password, email);

			// Redirecionar para uma página de sucesso ou realizar outras ações necessárias
			response.sendRedirect("/WebDB/home");
		}
	}

	private boolean verificarLoginExistente(String login) {
		try (Connection con = Banco.getConnection()) {
			String query = "SELECT COUNT(*) FROM usuario WHERE login = ?";
			try (PreparedStatement statement = con.prepareStatement(query)) {
				statement.setString(1, login);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void cadastrarUsuario(String login, String password, String email) {
		new Banco();
		// Lógica para inserir o usuário no banco de dados utilizando JDBC
		try (Connection con = Banco.getConnection()) {
			String query = "INSERT INTO usuario (login, senha, email) VALUES (?, ?, ?)";
			try (PreparedStatement statement = con.prepareStatement(query)) {
				statement.setString(1, login);
				statement.setString(2, password);
				// statement.setString(3, nome);
				statement.setString(3, email);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Tratar erros de banco de dados ou lançar exceção para lidar com eles em outro
			// lugar
		}
	}

}
