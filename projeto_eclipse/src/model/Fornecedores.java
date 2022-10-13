package model;

public class Fornecedores extends Endereco {
	
	private String nome, email, telefone, cnpj;
	
	public Fornecedores() {
		super();
	}

	
	
	public Fornecedores(String nomeEmpresa, String email, String telefone, String cnpj, String rua, String bairro, String cidade, String cep, int idEstado, int numero) {
		super();
		this.nome = nomeEmpresa;
		this.email = email;
		this.telefone = telefone;
		this.cnpj = cnpj;
		setRua(rua);
		setBairro(bairro);
		setCidade(cidade);
		setCep(cep);
		setEstado(idEstado);
		setNumero(numero);
	}
	
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeEmpresa() {
		return nome;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nome = nomeEmpresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
