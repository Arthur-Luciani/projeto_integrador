package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
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

import dao.EstadoDao;
import dao.ProdutoDao;
import dao.VendaDao;
import model.Cliente;
import model.DadosCadastroVenda;
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
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class TelaCadastroVenda extends JFrame {

	private JPanel contentPane;
	
	private ArrayList<ProdutoVenda> listaProdutosVendidos = new ArrayList<>();
	
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private static Border bordaInvisivel = BorderFactory.createLineBorder(new Color(240, 255, 240));
	private JTable table;
	private	JComboBox cbClientes;
	private JComboBox cbVendedor;
	private JTextField txtQuantidade;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public TelaCadastroVenda(ArrayList<ProdutoVenda> listaProdutosVendidos, LinkedList<Usuario> listaNomesUsuarios, 
			LinkedList<Cliente> listaNomesCliente, Usuario usuarioLogado){
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
		
	
		
		JPanel pCliente = new JPanel();
		pCliente.setBackground(new Color(240, 255, 240));
		ptxt.add(pCliente);
		
		JPanel pCbCli = new JPanel();
		pCbCli.setBackground(new Color(240, 255, 240));
		pCliente.add(pCbCli);
		
		JPanel pbtnCli = new JPanel();
		pbtnCli.setBackground(new Color(240, 255, 240));
		pCliente.add(pbtnCli);
		
		String[] arrayClientes = new String[listaNomesCliente.size()];
		for(int i = 0; i < arrayClientes.length; i++) {
		    Cliente cliente = listaNomesCliente.get(i);
		    
			arrayClientes[i] = cliente.getNome();
		}
		pCliente.setLayout(new GridLayout(0, 1, 0, 0));
		cbClientes = new JComboBox(arrayClientes);
		cbClientes.setBackground(new Color(85, 107, 47));
		cbClientes.setForeground(new Color(255, 255, 255));
		cbClientes.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pCbCli.add(cbClientes);
		
		
		JButton btnAdicionarCliente = new JButton("Cadastrar cliente");
		btnAdicionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoDao dao = new EstadoDao();
				DadosCadastroVenda dados = new DadosCadastroVenda(
						listaProdutosVendidos, 
						listaNomesUsuarios, 
						listaNomesCliente, 
						cbClientes.getSelectedIndex(), 
						cbVendedor.getSelectedIndex()
						);
				
				TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(dao.resgatarEstados(), false, dados, usuarioLogado);
				telaCadastroCliente.setVisible(true);
				dispose();
			}
		});
		btnAdicionarCliente.setBackground(new Color(240, 255, 240));
		btnAdicionarCliente.setBorder(bordaInvisivel);
		btnAdicionarCliente.setForeground(new Color(0, 0, 0));
		btnAdicionarCliente.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		pbtnCli.add(btnAdicionarCliente);
		
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) panel_20.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		ptxt.add(panel_20);
		
		String[] arrayVendedores = new String[listaNomesUsuarios.size()];
		for(int i = 0; i < arrayVendedores.length; i++) {
		    Usuario usuarioLista = listaNomesUsuarios.get(i);
			arrayVendedores[i] = usuarioLista.getNome();
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
		btnVoltar.setIcon(new ImageIcon(TelaCadastroVenda.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		panel_4.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque(usuarioLogado);
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
		
		JPanel p1pbtn = new JPanel();
		p1pbtn.setBackground(new Color(240, 255, 240));
		pBtnsTabela.add(p1pbtn);
		
		JPanel p1addProd = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) p1addProd.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		p1addProd.setBackground(new Color(240, 255, 240));
		p1pbtn.setBackground(new Color(240, 255, 240));
		p1pbtn.setLayout(new GridLayout(0, 1, 0, 0));
		p1pbtn.add(p1addProd);
		
		
		
		JPanel p2addProd = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) p2addProd.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		p2addProd.setBackground(new Color(240, 255, 240));
		p1pbtn.setBackground(new Color(240, 255, 240));
		p1pbtn.add(p2addProd);
		
		pBtnsTabela.add(p1pbtn);
		
		
		
		JPanel p2pbtn = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) p2pbtn.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		p2pbtn.setBackground(new Color(240, 255, 240));
		pBtnsTabela.add(p2pbtn);	
		
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setIcon(new ImageIcon(TelaCadastroVenda.class.getResource("/images/icons8-à-direita-dentro-de-um-círculo-24.png")));
		panel_8.add(btnContinuar);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listaProdutosVendidos.isEmpty()) {
					TelaConfirmarVenda telaConfirmarVenda = new TelaConfirmarVenda
							(listaProdutosVendidos, listaNomesUsuarios.get(cbVendedor.getSelectedIndex()),
							 listaNomesCliente.get(cbClientes.getSelectedIndex()), cbVendedor.getSelectedIndex(), cbClientes.getSelectedIndex(), usuarioLogado);
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
		
		
		ProdutoDao dao = new ProdutoDao();
		ArrayList<Produto> listaProdutos = dao.resgatarProdutos(); 
		String[] arrayProdutos = new String[listaProdutos.size()];
		for(int i = 0; i < arrayProdutos.length; i++) {
		    Produto produto = listaProdutos.get(i);
			arrayProdutos[i] = produto.getNome();
		}
		pBtnsTabela.setLayout(new GridLayout(0, 2, 0, 0));
		JComboBox cbProduto = new JComboBox(arrayProdutos);
		p1addProd.add(cbProduto);
		cbProduto.addFocusListener(new FocusAdapter() {
		});
		cbProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		cbProduto.setForeground(new Color(255, 255, 255));
		cbProduto.setBackground(new Color(85, 107, 47));
		cbProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		p2addProd.add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtQuantidade.setBorder(bordaNormal);
			}
		});
		txtQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		p2addProd.add(txtQuantidade);
		txtQuantidade.setColumns(4);
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(TelaCadastroVenda.class.getResource("/images/icons8-mais-2-matemática-24.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto produto = listaProdutos.get(cbProduto.getSelectedIndex());
				ProdutoVenda produtoVenda = null;
				
				
				try {
					produtoVenda = new ProdutoVenda(Integer.parseInt(txtQuantidade.getText()), produto);
					
					boolean jaExiste = false;
					int posicaoLista=0;
					ProdutoVenda produtoLista = null;
					
					if (listaProdutosVendidos.isEmpty()) {
						jaExiste = false;
					} else {
						for (int i = 0; i < listaProdutosVendidos.size(); i++) {
							produtoLista =listaProdutosVendidos.get(i);
							
							if (produtoVenda.getId()==produtoLista.getId()) {
								jaExiste = true;
								posicaoLista = i;
								break;
							} else {
								jaExiste = false;
							}
						}
					}
					if (jaExiste) {
						produtoVenda.setQuantidade(produtoLista.getQuantidade()+produtoVenda.getQuantidade());
						if (produtoVenda.getQuantEstoque()>=produtoVenda.getQuantidade()) {
							listaProdutosVendidos.set(posicaoLista, produtoVenda);
						}else {
							TelaMensagem telaMensagem = new TelaMensagem("Estoque indisponível");
							telaMensagem.setVisible(true);
						}
					} else {
						if (produtoVenda.getQuantEstoque()>=produtoVenda.getQuantidade()) {
							listaProdutosVendidos.add(produtoVenda);
						} else {
							TelaMensagem telaMensagem = new TelaMensagem("Estoque indisponível");
							telaMensagem.setVisible(true);
						}
					}
					atualizarJTable(listaProdutosVendidos);
				} catch (NumberFormatException e2) {
					TelaMensagem telaMensagem = new TelaMensagem("Indique a quantidade");
					telaMensagem.setVisible(true);
					txtQuantidade.setBorder(bordaVermelha);
				}

			
				
				
				
				/*
				
					if (listaProdutosVendidos.isEmpty()) {
						listaProdutosVendidos.add(produtoVenda);
					} else {
						boolean jaExiste =false;
						for (int i = 0; i < listaProdutosVendidos.size(); i++) {
							ProdutoVenda produtoVendido = listaProdutosVendidos.get(i);
							if (produtoVendido.getId() == produtoVenda.getId()) {
								jaExiste = true;								
								produtoVendido.setQuantidade(produtoVendido.getQuantidade()+produtoVenda.getQuantidade());
								if (produtoVendido.getQuantEstoque()>=produtoVendido.getQuantidade()) {
									listaProdutosVendidos.set(i, produtoVendido);
								} else {
									TelaMensagem telaMensagem = new TelaMensagem("Estoque indisponível");
									telaMensagem.setVisible(true);
									break;
								}
								
							}
							else {
								jaExiste = false;
							}
						}
						if (!jaExiste) {
							if (produtoVenda.getQuantEstoque()>produtoVenda.getQuantidade()) {
								listaProdutosVendidos.add(produtoVenda);
							} else {
								TelaMensagem telaMensagem = new TelaMensagem("Estoque indisponível");
								telaMensagem.setVisible(true);
							}
						}	
					}		*/			
					
			}
		});
		
		btnAdicionar.setBackground(new Color(85, 107, 47));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		p2pbtn.add(btnAdicionar);
		
		
	}
	protected void atualizarJTable(ArrayList<ProdutoVenda>listaProdutosVendidos) {
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
