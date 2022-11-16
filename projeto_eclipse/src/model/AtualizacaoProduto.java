package model;

import java.text.NumberFormat;
import java.util.Date;

public class AtualizacaoProduto extends Produto{

	private Date dataAtualizacao;
	private double diferencaPorcent;
	
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public double getDiferencaPorcent() {
		return diferencaPorcent;
	}

	public void setDiferencaPorcent(double diferencaPorcent) {
		this.diferencaPorcent = diferencaPorcent;
	}
	

	

	
	
	

}
