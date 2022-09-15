package model;

public class Produto {
	
	String nome, nomeFornecedor;
	float preco;
	int id, quantEstoque;
	
	public Produto(String nome, float preco, int quantEstoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantEstoque = quantEstoque;
	}
	
	public Produto(String nome, float preco, int id, int quantEstoque, String nomeFornecedor) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.id = id;
		this.quantEstoque = quantEstoque;
		this.nomeFornecedor = nomeFornecedor;
	}
	
	

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantEstoque() {
		return quantEstoque;
	}

	public void setQuantEstoque(int quantEstoque) {
		this.quantEstoque = quantEstoque;
	}

}
