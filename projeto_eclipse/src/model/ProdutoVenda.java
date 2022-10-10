package model;

public class ProdutoVenda extends Produto{
	private int quantidade;
	
	
	public ProdutoVenda(int quantidade, Produto produto) {
		super();
		this.nome =produto.getNome();
		this.preco = produto.getPreco();
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
	
	
	
}
