package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Banco;
import model.Usuario;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet {
	private Banco bd = new Banco();
	private Connection con;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = obterUsuariosDoBanco();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
    }

    private List<Usuario> obterUsuariosDoBanco() {
        List<Usuario> usuarios = new ArrayList<>();

        // Lógica para obter usuários do banco de dados usando JDBC
        try (Connection con = Banco.getConnection()) {
            String query = "SELECT * FROM usuario";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String login = resultSet.getString("login");
                    String senha = resultSet.getString("senha");
                    String email = resultSet.getString("email");

                    Usuario usuario = new Usuario(login,senha, nome, email);
                    usuarios.add(usuario);
                    System.out.println("usuario listado: login:"+usuarios.get(0).getLogin()+" email:"+usuarios.get(0).getEmail());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratar erros de banco de dados ou lançar exceção para lidar com eles em outro lugar
        }
        return usuarios;
    }
}