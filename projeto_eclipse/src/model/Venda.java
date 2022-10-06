package model;

import java.time.LocalDate;

public class Venda {
	
	private int idVenda, idCliente, idVendedor;
	private LocalDate dataVenda;
	private float comissaoVendedor, lucro;
	private String  nomeCliente, nomeVendedor;
	
	public Venda(LocalDate dataVenda, float comissaoVendedor, float lucro, String nomeCliente, String nomeVendedor) {
		super();
		this.comissaoVendedor = comissaoVendedor;
		this.dataVenda = dataVenda;
		this.lucro = lucro;
		this.nomeCliente = nomeCliente;
		this.nomeVendedor = nomeVendedor;
	}
	
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
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	
	
}
