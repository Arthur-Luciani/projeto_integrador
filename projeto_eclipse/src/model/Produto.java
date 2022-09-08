package model;

public class Produto {
	
	String nome;
	float preco;
	int id, quantEstoque;
	
	public Produto(String nome, float preco, int quantEstoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantEstoque = quantEstoque;
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
