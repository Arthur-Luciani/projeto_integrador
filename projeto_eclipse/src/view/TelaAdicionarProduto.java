package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;
import dao.ProdutoDao;
import dao.UsuarioDao;
import model.Cliente;
import model.Produto;
import model.ProdutoVenda;
import model.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaAdicionarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuantidade;
	private ArrayList<ProdutoVenda> listaProdutosVendidos = new ArrayList<>();

	/**
	 * Create the frame.
	 * 
	 * @param listaNomesProdutos
	 */
	public TelaAdicionarProduto(ArrayList<Produto> listaProdutos, ArrayList<ProdutoVenda> listaProdutosVendidos, int usuarioSelecionado, int clienteSelecionado) {
		if (listaProdutosVendidos.isEmpty()) {
			this.listaProdutosVendidos = new ArrayList<>();
		} else {
			this.listaProdutosVendidos = listaProdutosVendidos;
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(new Color(85, 107, 47));
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Adicionar Produto");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_12);

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) panel_14.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_14);

		String[] arrayProdutos = new String[listaProdutos.size()];
		for(int i = 0; i < arrayProdutos.length; i++) {
		    Produto produto = listaProdutos.get(i);
			arrayProdutos[i] = produto.getNome();
		}
		JComboBox cbProduto = new JComboBox(arrayProdutos);
		panel_14.add(cbProduto);
		cbProduto.addFocusListener(new FocusAdapter() {
		});
		cbProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		cbProduto.setForeground(new Color(255, 255, 255));
		cbProduto.setBackground(new Color(85, 107, 47));
		cbProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_15 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_15.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_15.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_15);

		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_15.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_13.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_13);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_16 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_16.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_16.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_16);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDao daoUsuario = new UsuarioDao();
				ClienteDao daoCliente = new ClienteDao();
				LinkedList<Usuario> listaNomesUsuarios = daoUsuario.resgatarUsuarios();
				LinkedList<Cliente> listaNomesClientes = daoCliente.resgatarClientes();
				TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(listaProdutosVendidos, listaNomesUsuarios, listaNomesClientes);
				telaCadastroVenda.atualizarJTable();
				telaCadastroVenda.atualizarComboBox(clienteSelecionado, usuarioSelecionado);
				telaCadastroVenda.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		panel_16.add(btnVoltar);
		
		JPanel panel_17 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_17.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_17.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_17);
		
				JButton btnAdicionar = new JButton("Adicionar");
				panel_17.add(btnAdicionar);
				btnAdicionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {				
						
						UsuarioDao daoUsuario = new UsuarioDao();
						ClienteDao daoCliente = new ClienteDao();
						LinkedList<Usuario> listaNomesUsuarios = daoUsuario.resgatarUsuarios();
						LinkedList<Cliente> listaNomesClientes = daoCliente.resgatarClientes();
						Produto produto = listaProdutos.get(cbProduto.getSelectedIndex());
						ProdutoVenda produtoVenda = new ProdutoVenda(Integer.parseInt(txtQuantidade.getText()), produto);							
						listaProdutosVendidos.add(produtoVenda);
						TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(listaProdutosVendidos, listaNomesUsuarios, listaNomesClientes);
						telaCadastroVenda.atualizarJTable();
						telaCadastroVenda.atualizarComboBox(clienteSelecionado, usuarioSelecionado);
						telaCadastroVenda.setVisible(true);
						dispose();
					}
				});
		btnAdicionar.setBackground(new Color(85, 107, 47));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(4, 2, 0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_6);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_7);

		JLabel lblProduto = new JLabel("Produto");
		panel_7.add(lblProduto);
		lblProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_8);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_9);

		JLabel lblQuantidade = new JLabel("Quantidade");
		panel_9.add(lblQuantidade);
		lblQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_10);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_11);
	}
}
