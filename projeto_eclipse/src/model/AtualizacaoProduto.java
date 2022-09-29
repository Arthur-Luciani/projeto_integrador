package model;

import java.util.Date;

public class AtualizacaoProduto extends Produto{
	
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	private Date dataAtualizacao;
	
	

}
