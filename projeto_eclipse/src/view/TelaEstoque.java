package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;
import dao.FornecedorDao;
import dao.ProdutoDao;
import dao.UsuarioDao;
import model.Cliente;
import model.Fornecedores;
import model.Produto;
import model.ProdutoVenda;
import model.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaEstoque extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public TelaEstoque(Usuario usuarioLogado) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 824, 99);
		panel.setBackground(new Color(85, 107, 47));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setForeground(Color.WHITE);
		contentPane.add(panel);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setForeground(Color.WHITE);
		lblEstoque.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblEstoque);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setIcon(new ImageIcon(TelaEstoque.class.getResource("/images/icons8-caixa-de-papelão-24.png")));
		btnProdutos.setBackground(new Color(85, 107, 47));
		btnProdutos.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnProdutos.setForeground(new Color(255, 255, 255));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoDao dao = new ProdutoDao();


				ArrayList<Produto>listaProdutos =  dao.resgatarProdutos();
				
				TelaListaProdutos telaListaProdutos = new TelaListaProdutos(listaProdutos, usuarioLogado);
				telaListaProdutos.atualizarJTable();
				telaListaProdutos.setVisible(true);
				telaListaProdutos.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnProdutos.setBounds(94, 295, 183, 58);
		contentPane.add(btnProdutos);
		
		JButton btnVenda = new JButton("Venda");
		btnVenda.setIcon(new ImageIcon(TelaEstoque.class.getResource("/images/icons8-cesto-de-compras-2-24.png")));
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsuarioDao daoUsuario = new UsuarioDao();
				ClienteDao daoCliente = new ClienteDao();
				LinkedList<Usuario> listaNomesUsuarios = daoUsuario.resgatarUsuarios();
				LinkedList<Cliente> listaNomesClientes = daoCliente.resgatarCliente();
				ArrayList<ProdutoVenda> listaProdutosVendidos = new ArrayList<>();
				TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(listaProdutosVendidos, listaNomesUsuarios, listaNomesClientes, usuarioLogado);
				telaCadastroVenda.setVisible(true);
				telaCadastroVenda.setLocationRelativeTo(null);
				dispose();

			}
		});
		btnVenda.setForeground(Color.WHITE);
		btnVenda.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVenda.setBackground(new Color(85, 107, 47));
		btnVenda.setBounds(336, 295, 183, 58);
		contentPane.add(btnVenda);
		
		JButton btnFornecedores = new JButton("Fornecedores");
		btnFornecedores.setIcon(new ImageIcon(TelaEstoque.class.getResource("/images/icons8-fornecedor-24.png")));
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecedorDao dao;
				dao = new FornecedorDao();
				ArrayList<Fornecedores> listaFornecedores= dao.resgatarFornecedores();
				
					TelaListaFornecedores telaFornecedores = new TelaListaFornecedores(listaFornecedores, usuarioLogado);
					telaFornecedores.atualizarJTable();
					telaFornecedores.setVisible(true);
					telaFornecedores.setLocationRelativeTo(null);
					dispose();
			}
		});
		btnFornecedores.setForeground(Color.WHITE);
		btnFornecedores.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnFornecedores.setBackground(new Color(85, 107, 47));
		btnFornecedores.setBounds(571, 295, 183, 58);
		contentPane.add(btnFornecedores);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaEstoque.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial(usuarioLogado);
				telaInicial.setVisible(true);
				telaInicial.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		btnVoltar.setBounds(10, 461, 142, 39);
		contentPane.add(btnVoltar);

	}
}
