package view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.ProdutoDao;
import model.Estado;
import model.Fornecedores;
import model.Produto;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaAtualizarProduto extends JFrame {

	private JPanel contentPane;
	
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTextField txtQuantidade;
	
	ArrayList<Fornecedores> listaFornecedor;
	Produto produtoSelecionado;
	private int fornecedorSelecionado;


	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaAtualizarProduto(ArrayList<Fornecedores> listaFornecedores, Produto produtoSelecionado) throws ParseException {
		this.listaFornecedor = listaFornecedores;
		this.produtoSelecionado =produtoSelecionado;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel Botoes = new JPanel();
		contentPane.add(Botoes, BorderLayout.SOUTH);
		Botoes.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 240));
		Botoes.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoDao dao = new ProdutoDao();
				
				TelaListaProdutos telaListaProdutos = new TelaListaProdutos(dao.resgatarProdutos());
				telaListaProdutos.atualizarJTable();
				telaListaProdutos.setVisible(true);
				dispose();
			}
		});
		panel_6.add(btnVoltar);
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 255, 240));
		Botoes.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		JPanel Titulo = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) Titulo.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		Titulo.setBackground(new Color(85, 107, 47));
		contentPane.add(Titulo, BorderLayout.NORTH);
		
		JLabel lbAtualizaCadastrar = new JLabel("Atualizar");
		
		lbAtualizaCadastrar.setForeground(Color.WHITE);
		lbAtualizaCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		Titulo.add(lbAtualizaCadastrar);
		
		JPanel Lbls = new JPanel();
		contentPane.add(Lbls, BorderLayout.WEST);
		Lbls.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_3);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_9.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_9);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_9.add(lblNome);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_2);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_11.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_11.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_11);
		
		JLabel lblNomeDoFornecedor = new JLabel("Nome do fornecedor:");
		lblNomeDoFornecedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_11.add(lblNomeDoFornecedor);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_15);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_12.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_12.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_12);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_12.add(lblQuantidade);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_16);
		
		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_13.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_13.setBackground(new Color(240, 255, 240));
		Lbls.add(panel_13);
		
		JLabel lblPreco = new JLabel("Pre\u00C3\u00A7o:");
		lblPreco.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_13.add(lblPreco);
		
		JPanel Txts = new JPanel();
		contentPane.add(Txts, BorderLayout.CENTER);
		Txts.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel.setBackground(new Color(240, 255, 240));
		Txts.add(panel);
		
		txtNome = new JTextField(produtoSelecionado.getNome());
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtNome.setBorder(bordaNormal);
			}
		});
		txtNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtNome.setColumns(20);
		panel.add(txtNome);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_5.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(new Color(240, 255, 240));
		Txts.add(panel_5);
		
		
		String[] arrayFornecedores = new String[listaFornecedores.size()];
		for(int i = 0; i < arrayFornecedores.length; i++) {
		    Fornecedores fornecedor = listaFornecedores.get(i);
		    if (fornecedor.getCnpj() == produtoSelecionado.getFornecedor().getCnpj()) {
		    	fornecedorSelecionado = i;
		    }
		    
		    
			arrayFornecedores[i] = fornecedor.getNome();
		}		
		JComboBox cbFornecedores = new JComboBox(arrayFornecedores);
		cbFornecedores.setSelectedIndex(fornecedorSelecionado);
		cbFornecedores.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				cbFornecedores.setBorder(bordaNormal);
			}
		});
		cbFornecedores.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_5.add(cbFornecedores);
		
		
		
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_8.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_8.setBackground(new Color(240, 255, 240));
		Txts.add(panel_8);
		
		txtQuantidade = new JTextField(String.valueOf(produtoSelecionado.getQuantEstoque()));
		
		txtQuantidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtQuantidade.setBorder(bordaNormal);
			}
		});
		txtQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtQuantidade.setColumns(20);
		panel_8.add(txtQuantidade);
		
		JPanel panel_14 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_14.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_14.setBackground(new Color(240, 255, 240));
		Txts.add(panel_14);
		
		
		txtPreco = new JFormattedTextField(new MaskFormatter("R$ ##,##"));
		txtPreco.setText("R$ "+String.valueOf(produtoSelecionado.getPreco()).replace(".", ","));
		txtPreco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtPreco.setBorder(bordaNormal);
			}
		});
		txtPreco.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtPreco.setColumns(20);
		panel_14.add(txtPreco);
		
		JButton btnAtualizarCadastrar = new JButton("Atualizar");
		btnAtualizarCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				Fornecedores fornecedor = listaFornecedores.get(cbFornecedores.getSelectedIndex());
				int quantidade = 0;
				float preco = 0;
				try {
					quantidade= Integer.parseInt(txtQuantidade.getText());
					String precoStr = txtPreco.getText().replace("R$ ", "");
					preco = Float.parseFloat(precoStr.replace(",", "."));
				} catch (NumberFormatException e2) {
					// TODO: handle exception
					txtQuantidade.setBorder(bordaVermelha);
				}				
				if (nome.isEmpty() || fornecedor==null || txtPreco.getText().equals("R$   ,  ") ) {
					if (nome.isEmpty()) {
						txtNome.setBorder(bordaVermelha);
					}
					if (fornecedor == null) {
						cbFornecedores.setBorder(bordaVermelha);
					}
					if (txtPreco.getText().equals("R$   ,  ")) {
						txtPreco.setBorder(bordaVermelha);
					}
				} else {
					Produto produto = new Produto(nome, preco, quantidade, fornecedor);					
					ProdutoDao dao = new ProdutoDao();
					produto.setId(produtoSelecionado.getId());
					dao.atualizarProduto(produto);
					
					
					TelaListaProdutos telaListaProdutos = new TelaListaProdutos(dao.resgatarProdutos());
					telaListaProdutos.atualizarJTable();
					telaListaProdutos.setVisible(true);
					dispose();
				}
			}
		});
		panel_7.add(btnAtualizarCadastrar);
		btnAtualizarCadastrar.setForeground(Color.WHITE);
		btnAtualizarCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnAtualizarCadastrar.setBackground(new Color(85, 107, 47));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoDao dao = new ProdutoDao();
				dao.deletarProduto(produtoSelecionado.getId());
				TelaListaProdutos telaListaProdutos = new TelaListaProdutos(dao.resgatarProdutos());
				telaListaProdutos.atualizarJTable();
				telaListaProdutos.setVisible(true);
				dispose();
			}
		});
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		btnExcluir.setBackground(new Color(85, 107, 47));
		panel_7.add(btnExcluir);
		
	}
}
