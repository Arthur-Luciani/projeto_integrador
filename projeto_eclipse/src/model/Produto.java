package model;

public class Produto {
	
	private String nome;
	private float preco;
	private int id;
	private int quantEstoque;
	private Fornecedores fornecedor;
	
	
	public Produto() {
		super();
	}

	public Produto(String nome, float preco, int quantEstoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantEstoque = quantEstoque;
	}
	
	public Produto(String nome, float preco, int quantEstoque, Fornecedores fornecedor) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantEstoque = quantEstoque;
		this.fornecedor = fornecedor;
	}
	public Produto(String nome, float preco, int quantEstoque, Fornecedores fornecedor, int id) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantEstoque = quantEstoque;
		this.fornecedor = fornecedor;
		this.id = id;
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
	public Fornecedores getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}

}
