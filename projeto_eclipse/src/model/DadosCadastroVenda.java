package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class DadosCadastroVenda {
	
	private ArrayList<ProdutoVenda> listaProdutosVendidos;
	private LinkedList<Usuario> listaNomesUsuarios;
	private LinkedList<Cliente> listaNomesCliente;
	private int clienteSelecionado;
	private int usuarioSelecionado;
	
	public DadosCadastroVenda(ArrayList<ProdutoVenda> listaProdutosVendidos, LinkedList<Usuario> listaNomesUsuarios,
			LinkedList<Cliente> listaNomesCliente, int clienteSelecionado, int usuarioSelecionado) {
		super();
		this.listaProdutosVendidos = listaProdutosVendidos;
		this.listaNomesUsuarios = listaNomesUsuarios;
		this.listaNomesCliente = listaNomesCliente;
		this.clienteSelecionado = clienteSelecionado;
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public ArrayList<ProdutoVenda> getListaProdutosVendidos() {
		return listaProdutosVendidos;
	}
	public void setListaProdutosVendidos(ArrayList<ProdutoVenda> listaProdutosVendidos) {
		this.listaProdutosVendidos = listaProdutosVendidos;
	}
	public LinkedList<Usuario> getListaNomesUsuarios() {
		return listaNomesUsuarios;
	}
	public void setListaNomesUsuarios(LinkedList<Usuario> listaNomesUsuarios) {
		this.listaNomesUsuarios = listaNomesUsuarios;
	}
	public LinkedList<Cliente> getListaNomesCliente() {
		return listaNomesCliente;
	}
	public void setListaNomesCliente(LinkedList<Cliente> listaNomesCliente) {
		this.listaNomesCliente = listaNomesCliente;
	}
	public int getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(int clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public int getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(int usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	
	
}
