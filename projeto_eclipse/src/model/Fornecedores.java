package model;

public class Fornecedores  {
	
	private String nomeEmpresa, email, telefone, cnpj;
	
	public Fornecedores() {
		super();
	}

	
	
	public Fornecedores(String nomeEmpresa, String email, String telefone, String cnpj) {
		super();
		this.nomeEmpresa = nomeEmpresa;
		this.email = email;
		this.telefone = telefone;
		this.cnpj = cnpj;
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
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
