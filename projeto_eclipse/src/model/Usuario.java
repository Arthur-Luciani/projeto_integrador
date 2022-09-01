package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Usuario {
	private String nome;
	private String login;
	private String senha;
	private String cep;
	private String cpf;
	private LocalDate dataNascimento;
	private int idade;
	private int id;
	private boolean permissao;

	public Usuario() {
	}

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimentoStr) {
		this.dataNascimento = dataNascimentoStr;
		idade = Period.between(dataNascimento, LocalDate.now()).getYears();
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPermissao() {
		return permissao;
	}

	public void setPermissao(boolean permissao) {
		this.permissao = permissao;
	}
	
	

}
