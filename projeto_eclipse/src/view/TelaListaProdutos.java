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

import dao.ClienteDao;
import dao.EstadoDao;
import dao.FornecedorDao;
import dao.ProdutoDao;
import model.AtualizacaoProduto;
import model.Estado;
import model.Fornecedores;
import model.Produto;
import model.Usuario;

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
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class TelaListaProdutos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Produto> listaProduto = new ArrayList<Produto>();
	private Produto produtoSelecionado = null;
	private JTextField txtPesquisa;
	private ProdutoDao dao = new ProdutoDao();

	/**
	 * Create the frame.
	 */
	public TelaListaProdutos(ArrayList<Produto> listaProdutos, Usuario usuario) {
		this.listaProduto = listaProdutos;
		
		setBackground(new Color(240, 255, 240));
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
		
		JPanel pPesquisa = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pPesquisa.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pPesquisa.setBackground(new Color(240, 255, 240));
		pTable.add(pPesquisa, BorderLayout.NORTH);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pPesquisa.add(txtPesquisa);
		txtPesquisa.setColumns(20);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(TelaListaProdutos.class.getResource("/images/icons8-pesquisar-24.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaProdutos.this.listaProduto = dao.pesquisaProduto(txtPesquisa.getText());
				atualizarJTable();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pPesquisa.add(btnNewButton);
		
		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPesquisa.setText("");
				TelaListaProdutos.this.listaProduto = dao.pesquisaProduto(txtPesquisa.getText());
				atualizarJTable();
			}
		});
		btnLimpar.setIcon(new ImageIcon(TelaListaProdutos.class.getResource("/images/icons8-cancelar-24.png")));
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnLimpar.setBackground(new Color(85, 107, 47));
		pPesquisa.add(btnLimpar);
		
		JPanel pBotoes = new JPanel();
		pBotoes.setBackground(new Color(240, 255, 240));
		contentPane.add(pBotoes, BorderLayout.SOUTH);
		pBotoes.setLayout(new BoxLayout(pBotoes, BoxLayout.X_AXIS));
		
		
		JPanel pBotoesEsquerda = new JPanel();
		pBotoesEsquerda.setBackground(new Color(240, 255, 240));
		FlowLayout fl_pBotoesEsquerda = (FlowLayout) pBotoesEsquerda.getLayout();
		fl_pBotoesEsquerda.setAlignment(FlowLayout.LEFT);
		pBotoes.add(pBotoesEsquerda);
		
		JPanel pBotoesDireita = new JPanel();
		pBotoesDireita.setBackground(new Color(240, 255, 240));
		FlowLayout fl_pBotoesDireita = (FlowLayout) pBotoesDireita.getLayout();
		fl_pBotoesDireita.setAlignment(FlowLayout.TRAILING);
		pBotoes.add(pBotoesDireita);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(TelaListaProdutos.class.getResource("/images/icons8-mais-2-matemática-24.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProduto cadastroProduto;
				FornecedorDao daoF = new FornecedorDao();
				ArrayList<Fornecedores> listaFornecedores= daoF.resgatarFornecedores();
				
				cadastroProduto = new TelaCadastroProduto(listaFornecedores, null, usuario);
				cadastroProduto.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaListaProdutos.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque(usuario);
				telaEstoque.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		pBotoesEsquerda.add(btnVoltar);
		
		JButton btnHistorico = new JButton("Histórico de preços");
		btnHistorico.setIcon(new ImageIcon(TelaListaProdutos.class.getResource("/images/icons8-tag-de-preço-de-venda-24.png")));
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produtoSelecionado != null) {
					ArrayList<AtualizacaoProduto> listaAtualizacaoProdutos = dao.historicoPreco(produtoSelecionado.getId());
					TelaHistoricoPrecos telaHistoricoPrecos = new TelaHistoricoPrecos(listaAtualizacaoProdutos, produtoSelecionado, usuario);
					telaHistoricoPrecos.atualizarJTable();
					telaHistoricoPrecos.setVisible(true);
					dispose();
				} else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum produto selecionado");
					telaMensagem.setVisible(true);
				}
				
				
			}
		});
		btnHistorico.setForeground(new Color(255, 255, 255));
		btnHistorico.setBackground(new Color(85, 107, 47));
		btnHistorico.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnHistorico);
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(85, 107, 47));
		btnCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setIcon(new ImageIcon(TelaListaProdutos.class.getResource("/images/icons8-confirmação-e-atualização-24.png")));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (produtoSelecionado != null) {
					
					dao.resgatarProdutos();
					FornecedorDao daoF = new FornecedorDao();
					ArrayList<Produto> listaproduto = dao.resgatarProdutos();
					ArrayList<Fornecedores> listaFornecedores = daoF.resgatarFornecedores();
					TelaAtualizarProduto telaAtualizarProduto = new TelaAtualizarProduto(listaFornecedores, produtoSelecionado, usuario);
					telaAtualizarProduto.setVisible(true);
					dispose();
				} else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum produto selecionado");
					telaMensagem.setVisible(true);
				}
			}
			
	
		});
		btnAtualizar.setForeground(new Color(255, 255, 255));
		btnAtualizar.setBackground(new Color(85, 107, 47));
		btnAtualizar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setIcon(new ImageIcon(TelaListaProdutos.class.getResource("/images/icons8-excluir-24.png")));
		btnDeletar.setForeground(new Color(255, 255, 255));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produtoSelecionado != null) {
					dao.deletarProduto(produtoSelecionado.getId());
					TelaListaProdutos.this.listaProduto = dao.resgatarProdutos();
					atualizarJTable();
				} else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum produto selecionado");
					telaMensagem.setVisible(true);
				}
				
				
				
			}
		});
		btnDeletar.setBackground(new Color(85, 107, 47));
		btnDeletar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnDeletar);
		
		if (usuario.isPermissao()) {
			btnCadastrar.setEnabled(true);
			btnAtualizar.setEnabled(true);
			btnDeletar.setEnabled(true);
		} else {
			btnCadastrar.setEnabled(false);
			btnAtualizar.setEnabled(false);
			btnDeletar.setEnabled(false);
		}
		
		
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
			String quantidade;
			if (p.getQuantEstoque()<=0) {
				quantidade = "Indispon�vel";
			} else {
				quantidade = String.valueOf(p.getQuantEstoque());
			}
			modelo.addRow(new Object[] { 
					p.getId(), 
					p.getNome(),
					"R$"+p.getPreco(),
					quantidade					
					});
		}
		
		table.setModel(modelo);
	}

}
