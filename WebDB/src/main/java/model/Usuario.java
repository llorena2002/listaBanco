package model;

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String email;
	
	public Usuario() {
		
	}
	
	public Usuario(String login, String nome, String email) {
		super();
		this.login = login;
		this.nome = nome;
		this.email = email;
	}
	
	public Usuario(String login, String senha, String nome, String email) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}