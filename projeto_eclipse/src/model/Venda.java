package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venda {
	private int idVenda;
	private LocalDate dataVenda;
	private float comissaoVendedor;
	private float lucro;
	private Cliente cliente;
	private Usuario vendedor;
	private ArrayList<ProdutoVenda>listaProdutosVendidos;
	private String tipoPagamento;
	
	
	public Venda( LocalDate dataVenda, float comissaoVendedor, float lucro, Cliente cliente, Usuario vendedor, String tipoPagamento) {
		super();
		this.dataVenda = dataVenda;
		this.comissaoVendedor = comissaoVendedor;
		this.lucro = lucro;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.tipoPagamento = tipoPagamento;
	}
	public Venda() {}
	
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public LocalDate getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}
	public float getComissaoVendedor() {
		return comissaoVendedor;
	}
	public void setComissaoVendedor(float comissaoVendedor) {
		this.comissaoVendedor = comissaoVendedor;
	}
	public float getLucro() {
		return lucro;
	}
	public void setLucro(float lucro) {
		this.lucro = lucro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	public ArrayList<ProdutoVenda> getListaProdutosVendidos() {
		return listaProdutosVendidos;
	}
	public void setListaProdutosVendidos(ArrayList<ProdutoVenda> listaProdutosVendidos) {
		this.listaProdutosVendidos = listaProdutosVendidos;
	}
	
	
	
}
