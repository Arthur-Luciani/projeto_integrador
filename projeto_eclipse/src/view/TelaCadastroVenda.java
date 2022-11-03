package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.ProdutoDao;
import dao.VendaDao;
import model.Cliente;
import model.Estado;
import model.Produto;
import model.ProdutoVenda;
import model.Usuario;
import model.Venda;
import net.miginfocom.swing.MigLayout;
import swingDesign.JTableViridisSinus;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;

public class TelaCadastroVenda extends JFrame {

	private JPanel contentPane;
	
	private ArrayList<ProdutoVenda> listaProdutosVendidos = new ArrayList<>();
	
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private JTable table;
	private	JComboBox cbClientes;
	private JComboBox cbVendedor;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaCadastroVenda(ArrayList<ProdutoVenda> listaProdutosVendidos, LinkedList<Usuario> listaNomesUsuarios, 
			LinkedList<Cliente> listaNomesCliente){
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
		
		JPanel pTitulo = new JPanel();
		pTitulo.setBackground(new Color(85, 107, 47));
		contentPane.add(pTitulo, BorderLayout.NORTH);
		
		JPanel pBorderEsq = new JPanel();
		pBorderEsq.setBackground(new Color(240, 255, 240));
		contentPane.add(pBorderEsq, BorderLayout.WEST);
		
		JPanel pBorderDir = new JPanel();
		pBorderDir.setBackground(new Color(240, 255, 240));
		contentPane.add(pBorderDir, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("Cadastro das vendas");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		pTitulo.add(lblNewLabel);
		pBorderEsq.setLayout(new BorderLayout(0, 0));
		
		JPanel ptxt = new JPanel();
		ptxt.setBackground(new Color(240, 255, 240));
		pBorderEsq.add(ptxt, BorderLayout.EAST);
		ptxt.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout = (FlowLayout) panel_14.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		ptxt.add(panel_14);
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		JLabel txtData = new JLabel(LocalDate.now().format(formatacao));
		txtData.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_14.add(txtData);
		
	
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_17.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		ptxt.add(panel_17);
		
		String[] arrayClientes = new String[listaNomesCliente.size()];
		for(int i = 0; i < arrayClientes.length; i++) {
		    Cliente cliente = listaNomesCliente.get(i);
		    
			arrayClientes[i] = cliente.getNome();
		}
		cbClientes = new JComboBox(arrayClientes);
		cbClientes.setBackground(new Color(85, 107, 47));
		cbClientes.setForeground(new Color(255, 255, 255));
		cbClientes.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_17.add(cbClientes);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) panel_20.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		ptxt.add(panel_20);
		
		String[] arrayVendedores = new String[listaNomesUsuarios.size()];
		for(int i = 0; i < arrayVendedores.length; i++) {
		    Usuario usuario = listaNomesUsuarios.get(i);
			arrayVendedores[i] = usuario.getNome();
		}
		
		cbVendedor = new JComboBox(arrayVendedores);
		cbVendedor.setBackground(new Color(85, 107, 47));
		cbVendedor.setForeground(new Color(255, 255, 255));
		cbVendedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_20.add(cbVendedor);
		
		JPanel pLbls = new JPanel();
		pLbls.setBackground(new Color(240, 255, 240));
		pBorderEsq.add(pLbls, BorderLayout.WEST);
		pLbls.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		pLbls.add(panel);
		
		JLabel lblData = new JLabel("Data:");
		panel.add(lblData);
		lblData.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel pBotoes = new JPanel();
		pBotoes.setBackground(new Color(240, 255, 240));
		contentPane.add(pBotoes, BorderLayout.SOUTH);
		pBotoes.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pBotoes.add(panel_4);
		
		JButton btnVoltar = new JButton("Voltar");
		panel_4.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque();
				telaEstoque.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(new Color(85, 107, 47));
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_5 = (FlowLayout) panel_8.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		pBotoes.add(panel_8);
		
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 240));
		pLbls.add(panel_6);
		
		JLabel lblCliente = new JLabel("Cliente:");
		panel_6.add(lblCliente);
		lblCliente.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 255, 240));
		pLbls.add(panel_9);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		panel_9.add(lblVendedor);
		lblVendedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBorderDir.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pBorderDir.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTableViridisSinus().padraoJtable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Produto", "Pre\u00E7o", "Quantidade"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel pBtnsTabela = new JPanel();
		pBtnsTabela.setBackground(new Color(240, 255, 240));
		pBorderDir.add(pBtnsTabela, BorderLayout.SOUTH);
		
		JButton btnContinuar = new JButton("Continuar");
		panel_8.add(btnContinuar);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listaProdutosVendidos.isEmpty()) {
					TelaConfirmarVenda telaConfirmarVenda = new TelaConfirmarVenda
							(listaProdutosVendidos, listaNomesUsuarios.get(cbVendedor.getSelectedIndex()),
							 listaNomesCliente.get(cbClientes.getSelectedIndex()), cbVendedor.getSelectedIndex(), cbClientes.getSelectedIndex());
					telaConfirmarVenda.setVisible(true);
					telaConfirmarVenda.atualizarJTable();
					telaConfirmarVenda.atualizarCampos();
					dispose();
				} else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum produto adicionado");
					telaMensagem.setVisible(true);
				}
			}
		});
		btnContinuar.setBackground(new Color(85, 107, 47));
		btnContinuar.setForeground(new Color(255, 255, 255));
		btnContinuar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnAdicionar = new JButton("Adicionar produtos");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdicionarProdutoVenda telaAdicionaProduto;
				ProdutoDao dao = new ProdutoDao();
				ArrayList<Produto> listaProdutos = dao.resgatarProdutos();
				telaAdicionaProduto = new TelaAdicionarProdutoVenda(listaProdutos, listaProdutosVendidos, cbVendedor.getSelectedIndex(), cbClientes.getSelectedIndex());
				telaAdicionaProduto.setVisible(true);
				dispose();
			
			}
		});
		btnAdicionar.setBackground(new Color(85, 107, 47));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBtnsTabela.add(btnAdicionar);
	}
	protected void atualizarJTable() {
		DefaultTableModel modelo = new DefaultTableModel(
				
				new Object[][] {
				},
				new String[] {
						"Produto", "Pre\u00E7o", "Quantidade"
				}
			);
		for(int i=0; i< listaProdutosVendidos.size(); i++) {
			ProdutoVenda produtoVenda = listaProdutosVendidos.get(i);
			modelo.addRow(new Object[] { produtoVenda.getNome(),"R$"+produtoVenda.getPreco(), produtoVenda.getQuantidade() });
		}
		
		table.setModel(modelo);
	}
	protected void atualizarComboBox(int clienteSelecionado, int vendedorSelecionado) {
		cbClientes.setSelectedIndex(clienteSelecionado);
		cbVendedor.setSelectedIndex(vendedorSelecionado);
	}

}
