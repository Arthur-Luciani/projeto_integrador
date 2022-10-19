package model;

public class ProdutoVenda extends Produto{
	private int quantidade;
	
	
	public ProdutoVenda(int quantidade, Produto produto) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.nomeFornecedor = produto.getNomeFornecedor();
		this.preco = produto.getPreco();
		this.quantEstoque = produto.getQuantEstoque();
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
	
	
	
}
