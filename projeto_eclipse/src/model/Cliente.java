package model;

import java.time.LocalDate;

public class Cliente extends Endereco{
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataNascimento;
	private int id;
	private int id_endereco;	
	
	public Cliente(String nome, String cpf, String email, LocalDate dataNascimento, String rua, String bairro, String cidade, String cep, int idEstado, int numero) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
		setRua(rua);
		setBairro(bairro);
		setCidade(cidade);
		setCep(cep);
		setEstado(idEstado);
		setNumero(numero);
	}


	public Cliente() {
		// TODO Auto-generated constructor stub
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}
	
}
