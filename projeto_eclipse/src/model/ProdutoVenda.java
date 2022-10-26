package model;

public class ProdutoVenda extends Produto{
	private int quantidade;
	
	
	
	
	public ProdutoVenda(int quantidade, Produto produto) {
		super();
		
		
		setId(produto.getId());
		setNome(produto.getNome()); 
		setFornecedor(produto.getFornecedor());
		setPreco(produto.getPreco());
		setQuantEstoque(produto.getQuantEstoque());
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
	
	
	
}
