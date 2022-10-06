package model;

import java.time.LocalDate;

public class Cliente extends Endereco{
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataNascimento;
	private int id;
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	
	
	

	
	


}
