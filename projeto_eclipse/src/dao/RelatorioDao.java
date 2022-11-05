package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProdutoVenda;
import model.RelatorioVendas;

public class RelatorioDao {
	private Connection conexao;
	
	public RelatorioDao() {}
	
	
	public ArrayList<RelatorioVendas> relatorioVendasLucro(Date entrada, Date saida) {
		conexao = BD.getConexao();
		ArrayList<RelatorioVendas> listaRelatorioVendas = new ArrayList<>();
		
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select produto.nome_produto, select_lucro.lucro, select_quantidade.quantidade "
							+ "from produto "
							+ "inner join (historico_produto) "
							+ "on (produto.id_produto = historico_produto.id_produto) "
							
							+ "inner join (select id_produto, sum(historico_produto.preco_novo * venda_produtos.quantidade_produto)  as lucro "
							+ "from historico_produto "
							+ "inner join venda_produtos "
							+ "on historico_produto.id_historico_produto = venda_produtos.id_historico_produto "
							+ "inner join venda "
							+ "on venda_produtos.id_venda = venda.id_venda "
							+ "where mydb.venda.data_venda between ? and ? "
							+ "group by historico_produto.id_produto) as select_lucro  "
							+ "on mydb.historico_produto.id_produto = mydb.select_lucro.id_produto "
							
							+ "inner join (select id_produto,  sum(quantidade_produto) as quantidade "
							+ "from historico_produto "
							+ "inner join venda_produtos "
							+ "on historico_produto.id_historico_produto = venda_produtos.id_historico_produto "
							+ "inner join venda "
							+ "on venda_produtos.id_venda = venda.id_venda "
							+ "where mydb.venda.data_venda between ? and ? "
							+ "group by id_produto) as select_quantidade "
							+ "on mydb.historico_produto.id_produto = mydb.select_quantidade.id_produto "
							+ "group by historico_produto.id_produto "
							+ "order by lucro desc; ");
			ps.setDate(1, entrada);
			ps.setDate(2, saida);
			ps.setDate(3, entrada);
			ps.setDate(4, saida);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					RelatorioVendas rv = new RelatorioVendas();
					rv.setNome(rs.getString("nome_produto"));
					rv.setLucro(rs.getFloat("lucro"));
					rv.setQuantidade(rs.getInt("quantidade"));
					
					listaRelatorioVendas.add(rv);
				} while (rs.next());			
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return listaRelatorioVendas;
	}
	

	public ArrayList<RelatorioVendas> relatorioVendasQuantidade(Date entrada, Date saida) {
		conexao = BD.getConexao();
		ArrayList<RelatorioVendas> listaRelatorioVendas = new ArrayList<>();
		
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select produto.nome_produto, select_lucro.lucro, select_quantidade.quantidade "
							+ "from produto "
							+ "inner join (historico_produto) "
							+ "on (produto.id_produto = historico_produto.id_produto) "
							
							+ "inner join (select id_produto, sum(historico_produto.preco_novo * venda_produtos.quantidade_produto)  as lucro "
							+ "from historico_produto "
							+ "inner join venda_produtos "
							+ "on historico_produto.id_historico_produto = venda_produtos.id_historico_produto "
							+ "inner join venda "
							+ "on venda_produtos.id_venda = venda.id_venda "
							+ "where mydb.venda.data_venda between ? and ? "
							+ "group by historico_produto.id_produto) as select_lucro  "
							+ "on mydb.historico_produto.id_produto = mydb.select_lucro.id_produto "
							
							+ "inner join (select id_produto,  sum(quantidade_produto) as quantidade "
							+ "from historico_produto "
							+ "inner join venda_produtos "
							+ "on historico_produto.id_historico_produto = venda_produtos.id_historico_produto "
							+ "inner join venda "
							+ "on venda_produtos.id_venda = venda.id_venda "
							+ "where mydb.venda.data_venda between ? and ? "
							+ "group by id_produto) as select_quantidade "
							+ "on mydb.historico_produto.id_produto = mydb.select_quantidade.id_produto "
							+ "group by historico_produto.id_produto "
							+ "order by quantidade desc; ");
			ps.setDate(1, entrada);
			ps.setDate(2, saida);
			ps.setDate(3, entrada);
			ps.setDate(4, saida);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					RelatorioVendas rv = new RelatorioVendas();
					rv.setNome(rs.getString("nome_produto"));
					rv.setLucro(rs.getFloat("lucro"));
					rv.setQuantidade(rs.getInt("quantidade"));
					
					listaRelatorioVendas.add(rv);
				} while (rs.next());			
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return listaRelatorioVendas;
	}

	
	
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







MAGIA NEGRA FUNCIONANDO

select produto.nome_produto, select_lucro.lucro, select_quantidade.quantidade
from produto 
inner join (historico_produto)
on (produto.id_produto = historico_produto.id_produto)
inner join (select id_produto, sum(historico_produto.preco_novo * venda_produtos.quantidade_produto)  as lucro
from historico_produto inner join venda_produtos
on historico_produto.id_historico_produto = venda_produtos.id_historico_produto
group by historico_produto.id_produto) as select_lucro 
on mydb.historico_produto.id_produto = mydb.select_lucro.id_produto
inner join (select id_produto,  sum(quantidade_produto) as quantidade
from historico_produto inner join venda_produtos
on historico_produto.id_historico_produto = venda_produtos.id_historico_produto
group by id_produto) as select_quantidade
on mydb.historico_produto.id_produto = mydb.select_quantidade.id_produto
group by historico_produto.id_produto;
		 */
//	}
}
