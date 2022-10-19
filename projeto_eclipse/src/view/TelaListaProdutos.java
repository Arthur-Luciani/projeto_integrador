package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.FornecedorDao;
import dao.ProdutoDao;
import model.AtualizacaoProduto;
import model.Fornecedores;
import model.Produto;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import swingDesign.JTableViridisSinus;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class TelaListaProdutos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Produto> listaProduto = new ArrayList<Produto>();
	private Produto produtoSelecionado = new Produto();

	/**
	 * Create the frame.
	 */
	public TelaListaProdutos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ProdutoDao dao = new ProdutoDao();
				listaProduto = dao.resgatarProdutos();
				atualizarJTable();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pTitulo = new JPanel();
		pTitulo.setBackground(new Color(85, 107, 47));
		contentPane.add(pTitulo, BorderLayout.NORTH);
		pTitulo.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Lista de Produtos");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		pTitulo.add(lblNewLabel);
		
		JPanel pTable = new JPanel();
		contentPane.add(pTable, BorderLayout.CENTER);
		pTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTableViridisSinus().padraoJtable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posicaoProduto = table.getSelectedRow();
				produtoSelecionado = listaProduto.get(posicaoProduto);
			}
		});
		table.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(85, 107, 47)));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setBackground(new Color(240, 255, 240));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Pre\u00E7o", "Quantidade no estoque"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(123);
		scrollPane.setViewportView(table);
		
		JPanel pBotoes = new JPanel();
		pBotoes.setBackground(new Color(240, 255, 240));
		contentPane.add(pBotoes, BorderLayout.SOUTH);
		pBotoes.setLayout(new BoxLayout(pBotoes, BoxLayout.X_AXIS));
		
		
		JPanel pBotoesEsquerda = new JPanel();
		FlowLayout fl_pBotoesEsquerda = (FlowLayout) pBotoesEsquerda.getLayout();
		fl_pBotoesEsquerda.setAlignment(FlowLayout.LEFT);
		pBotoes.add(pBotoesEsquerda);
		
		JPanel pBotoesDireita = new JPanel();
		FlowLayout fl_pBotoesDireita = (FlowLayout) pBotoesDireita.getLayout();
		fl_pBotoesDireita.setAlignment(FlowLayout.TRAILING);
		pBotoes.add(pBotoesDireita);
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProduto cadastroProduto;
				try {
					FornecedorDao dao = new FornecedorDao();
					ArrayList<Fornecedores> listaFornecedores= dao.resgatarFornecedores();
					
					cadastroProduto = new TelaCadastroProduto(true, listaFornecedores, null);
					cadastroProduto.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque();
				telaEstoque.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		pBotoesEsquerda.add(btnVoltar);
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setBackground(new Color(85, 107, 47));
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnAdicionar);
		
		JButton btnHistorico = new JButton("Histórico de preços");
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(produtoSelecionado);
				if (!produtoSelecionado.equals(null)) {
					ProdutoDao dao = new ProdutoDao();
					ArrayList<AtualizacaoProduto> listaAtualizacaoProdutos = dao.historicoPreco(produtoSelecionado.getId());
					TelaHistoricoPrecos telaHistoricoPrecos = new TelaHistoricoPrecos(listaAtualizacaoProdutos, produtoSelecionado);
					telaHistoricoPrecos.atualizarJTable();
					telaHistoricoPrecos.setVisible(true);
					dispose();
				} else {
					System.out.println(produtoSelecionado);
				}
				
				
			}
		});
		btnHistorico.setForeground(new Color(255, 255, 255));
		btnHistorico.setBackground(new Color(85, 107, 47));
		btnHistorico.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnHistorico);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				TelaCadastroProduto cadastroProduto;
				try {
					if (produtoSelecionado != null) {
						FornecedorDao dao = new FornecedorDao();
						ArrayList<Fornecedores> listaFornecedores = dao.resgatarFornecedores();
						cadastroProduto = new TelaCadastroProduto(false,listaFornecedores, produtoSelecionado);
						cadastroProduto.setVisible(true);
						dispose();
					} else {
						TelaMensagem telaMensagem = new TelaMensagem("Nenhum produto selecionado para atualizar");
						telaMensagem.setVisible(true);
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAtualizar.setForeground(new Color(255, 255, 255));
		btnAtualizar.setBackground(new Color(85, 107, 47));
		btnAtualizar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnAtualizar);
		
		
		
		
	}
	
	
	protected void atualizarJTable() {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00F3digo", "Nome", "Pre\u00E7o", "Quantidade no estoque"
				}
			);
		for(int i=0; i< listaProduto.size(); i++) {
			Produto p = listaProduto.get(i);
			modelo.addRow(new Object[] { p.getId(), p.getNome(),"R$"+p.getPreco(), p.getQuantEstoque()});
		}
		
		table.setModel(modelo);
	}

}
