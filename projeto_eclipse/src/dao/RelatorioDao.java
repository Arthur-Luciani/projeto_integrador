package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.ProdutoVenda;

public class RelatorioDao {
	private Connection conexao;
	
	public RelatorioDao() {}
	
	//public ArrayList<RelatorioDao> name() {
		
		
		/*
		 * 
		 * 	Magia negra para resgatar o lucro de um produto
select sum(lucro), id_produto  
from (select id_produto,  historico_produto.preco_novo * venda_produtos.quantidade_produto as lucro
from historico_produto inner join venda_produtos
on historico_produto.id_historico_produto = venda_produtos.id_historico_produto) as sub
group by id_produto;

	calcular lucro
select sum(lucro), id_produto  
from (select id_produto,  historico_produto.preco_novo * venda_produtos.quantidade_produto as lucro
from historico_produto inner join venda_produtos
on historico_produto.id_historico_produto = venda_produtos.id_historico_produto) as sub
group by id_produto;

calcular quantidade vendida
select id_produto,  sum(quantidade_produto) as quantidade
from historico_produto inner join venda_produtos
on historico_produto.id_historico_produto = venda_produtos.id_historico_produto
group by id_produto;






quase pronto

select produto.nome_produto, sum(lucro), sum(quantidade)
from produto 
inner join historico_produto
on (produto.id_produto = historico_produto.id_produto),
(select id_produto, historico_produto.preco_novo * venda_produtos.quantidade_produto as lucro
from historico_produto inner join venda_produtos
on historico_produto.id_historico_produto = venda_produtos.id_historico_produto
) as select_lucro,
(select id_produto, sum(quantidade_produto) as quantidade
from historico_produto inner join venda_produtos
on historico_produto.id_historico_produto = venda_produtos.id_historico_produto
group by id_produto) as select_quantidade
where select_lucro.id_produto = produto.id_produto 
	and select_lucro.id_produto = historico_produto.id_produto
    and select_quantidade.id_produto = produto.id_produto
    and select_quantidade.id_produto = historico_produto.id_produto
    and select_quantidade.id_produto = select_lucro.id_produto
group by historico_produto.id_produto;
		 */
//	}
}
