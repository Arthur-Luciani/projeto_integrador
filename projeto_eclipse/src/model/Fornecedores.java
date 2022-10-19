package model;

public class Fornecedores extends Endereco {
	
	private String nome; 
	private String email;
	private String telefone;
	private String cnpj;
	private int idEndereco;
	private Estado estado;
	
	public Fornecedores() {super();}
	
	
	public Fornecedores(String nomeEmpresa, String email, String telefone, String cnpj, String rua, String bairro, String cidade, String cep, int idEstado, Estado estado) {
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
		this.estado = estado;
	}
	public Fornecedores(String nomeEmpresa, String email, String telefone, String cnpj, String rua, String bairro, String cidade, String cep, int idEstado, Estado estado, int idEndereco) {
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
		this.estado = estado;
		this.idEndereco = idEndereco;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
	
	

}
