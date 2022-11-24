package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDao;
import dao.UsuarioDao;
import dao.VendaDao;
import model.Cliente;
import model.ProdutoVenda;
import model.Usuario;
import model.Venda;
import swingDesign.JTableViridisSinus;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

public class TelaConfirmarVenda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtParcelas;
	private ArrayList<ProdutoVenda>listaProdutosVendidos;
	private float lucroTotal;
	private float comissao;
	private JLabel lblValorTotal;
	private JLabel lblComissao;
	private JRadioButton rBtnCredito;
	private String tipoPagamento;

	/**
	 * Create the frame.
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public TelaConfirmarVenda(ArrayList<ProdutoVenda>listaProdutosVendidos, Usuario usuario, Cliente cliente, int usuarioSelecionado, int clienteSelecionado, Usuario usuarioLogado) {
		this.listaProdutosVendidos= listaProdutosVendidos;
		
		
		setResizable(false);
		getContentPane().setBackground(new Color(240, 255, 240));
		
		JPanel pTitulo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pTitulo.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		pTitulo.setBackground(new Color(85, 107, 47));
		getContentPane().add(pTitulo, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Confirmar Venda");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		pTitulo.add(lblNewLabel_1);
		
		JPanel pBorderDireita = new JPanel();
		pBorderDireita.setBackground(new Color(240, 255, 240));
		getContentPane().add(pBorderDireita, BorderLayout.EAST);
		pBorderDireita.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pBorderDireita.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTableViridisSinus().padraoJtable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Produto", "Pre\u00E7o", "Quantidade"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Carrinho");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBorderDireita.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel pBorderEsq = new JPanel();
		pBorderEsq.setBackground(new Color(240, 255, 240));
		getContentPane().add(pBorderEsq, BorderLayout.WEST);
		pBorderEsq.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pPagamento = new JPanel();
		pPagamento.setBackground(new Color(240, 255, 240));
		pBorderEsq.add(pPagamento);
		pPagamento.setLayout(new BorderLayout(0, 0));
		
		JPanel pTituloPagamento = new JPanel();
		pTituloPagamento.setBackground(new Color(240, 255, 240));
		pPagamento.add(pTituloPagamento, BorderLayout.NORTH);
		
		JPanel pRBtn = new JPanel();
		pRBtn.setBackground(new Color(240, 255, 240));
		pPagamento.add(pRBtn);
		
		JLabel lblNewLabel_2 = new JLabel("Forma de pagamento");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pTituloPagamento.add(lblNewLabel_2);
		pRBtn.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rBtnDinheiro = new JRadioButton("Dinheiro");
		rBtnDinheiro.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		rBtnDinheiro.setBackground(new Color(240, 255, 240));
		pRBtn.add(rBtnDinheiro);
		
		rBtnCredito = new JRadioButton("Cart\u00E3o de cr\u00E9dito");
		pRBtn.add(rBtnCredito);
		rBtnCredito.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		rBtnCredito.setBackground(new Color(240, 255, 240));
		
		JRadioButton rBtnDebito = new JRadioButton("Cart\u00E3o de d\u00E9bito");
		rBtnDebito.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		rBtnDebito.setBackground(new Color(240, 255, 240));
		pRBtn.add(rBtnDebito);
		
		JRadioButton rBtnPix = new JRadioButton("PIX");
		rBtnPix.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		rBtnPix.setBackground(new Color(240, 255, 240));
		pRBtn.add(rBtnPix);
		
		ButtonGroup btnsPagamento = new ButtonGroup();
		btnsPagamento.add(rBtnDinheiro);
		btnsPagamento.add(rBtnCredito);
		btnsPagamento.add(rBtnDebito);
		btnsPagamento.add(rBtnPix);
		
		JPanel pDados = new JPanel();
		pDados.setBackground(new Color(240, 255, 240));
		pBorderEsq.add(pDados);
		pDados.setLayout(new GridLayout(6, 0, 0, 0));
		
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		JPanel pParcelas = new JPanel();
		pParcelas.setBackground(new Color(240, 255, 240));
		pDados.add(pParcelas);
		
		JLabel lblParcela = new JLabel("Parcelas:");
		pParcelas.add(lblParcela);
		lblParcela.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		txtParcelas = new JTextField();
		txtParcelas.setEditable(false);
		pParcelas.add(txtParcelas);
		txtParcelas.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtParcelas.setColumns(10);
		
		JLabel lblData = new JLabel("Data: "+LocalDate.now().format(formatacao));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBackground(new Color(240, 255, 240));
		lblData.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pDados.add(lblData);
		
		JLabel lblCliente = new JLabel("Cliente: "+ cliente.getNome());
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setBackground(new Color(240, 255, 240));
		lblCliente.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pDados.add(lblCliente);
		
		JLabel lblVendedor = new JLabel("Vendedor: "+ usuario.getNome());
		lblVendedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendedor.setBackground(new Color(240, 255, 240));
		lblVendedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pDados.add(lblVendedor);
		
		lblValorTotal = new JLabel("Valor total: ");
		pDados.add(lblValorTotal);
		lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotal.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		lblComissao = new JLabel("Comiss\u00E3o: ");
		pDados.add(lblComissao);
		lblComissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblComissao.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		
		JPanel pBotoes = new JPanel();
		pBotoes.setBackground(new Color(240, 255, 240));
		getContentPane().add(pBotoes, BorderLayout.SOUTH);
		pBotoes.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pEsqBtns = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pEsqBtns.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pEsqBtns.setBackground(new Color(240, 255, 240));
		pBotoes.add(pEsqBtns);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaConfirmarVenda.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.setBackground(new Color(85, 107, 47));
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pEsqBtns.add(btnVoltar);
		
		JPanel pDireiraBtns = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pDireiraBtns.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pDireiraBtns.setBackground(new Color(240, 255, 240));
		pBotoes.add(pDireiraBtns);
		
		JButton btnCadastrarVenda = new JButton("Cadastrar Venda");
		btnCadastrarVenda.setIcon(new ImageIcon(TelaConfirmarVenda.class.getResource("/images/icons8-tag-de-preço-de-venda-24.png")));
		btnCadastrarVenda.setForeground(new Color(255, 255, 255));
		btnCadastrarVenda.setBackground(new Color(85, 107, 47));
		btnCadastrarVenda.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pDireiraBtns.add(btnCadastrarVenda);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		//Progrmacao
		
		//Aparecer e Esconder Panel Parcelas
		rBtnDinheiro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NumberFormat nf= NumberFormat.getInstance();
		        nf.setMaximumFractionDigits(2);
				
				tipoPagamento = "Dinheiro";
				txtParcelas.setEditable(false);
				
				//Calcular Valor total e comiss�o
				lucroTotal=0;
				for(int i=0; i< listaProdutosVendidos.size(); i++) {
					ProdutoVenda p = listaProdutosVendidos.get(i);
					float lucroProduto = p.getPreco()*p.getQuantidade();
					lucroTotal = lucroTotal + lucroProduto;
				}				
				lblValorTotal.setText("Valor total: R$"+ nf.format(lucroTotal));
				lblComissao.setText("Comiss�o: R$"+ nf.format(lucroTotal*5/100));
			}
		});
		
		rBtnDebito.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoPagamento = "Debito";
				txtParcelas.setEditable(false);
				
				atualizarCampos();
			}
		});
		
		rBtnPix.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoPagamento = "Pix";
				txtParcelas.setEditable(false);
				atualizarCampos();
			}
		});	
		
		rBtnCredito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoPagamento = "Credito";
				txtParcelas.setEditable(true);
				atualizarCampos();
				
			}
		});
		
		//Calcular Parcelas
		txtParcelas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				atualizarCampos();
			}
		});
		
		//Tela Cadastro Venda
		UsuarioDao daoUsuario = new UsuarioDao();
		ClienteDao daoCliente = new ClienteDao();
		LinkedList<Usuario> listaNomesUsuarios = daoUsuario.resgatarUsuarios();
		LinkedList<Cliente> listaNomesClientes = daoCliente.resgatarCliente();
		TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(listaProdutosVendidos, listaNomesUsuarios, listaNomesClientes, usuarioLogado);
		
		
		//Voltar
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaCadastroVenda.atualizarJTable(listaProdutosVendidos);
				telaCadastroVenda.atualizarComboBox(clienteSelecionado, usuarioSelecionado);
				telaCadastroVenda.setVisible(true);
				telaCadastroVenda.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		//Cadastrar Venda
		btnCadastrarVenda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tipoPagamento!=null) {
					Venda venda = new Venda(LocalDate.now(), comissao, lucroTotal, cliente, usuario, tipoPagamento);
					VendaDao dao = new VendaDao();
					dao.cadastroVenda(venda, listaProdutosVendidos);
					
					telaCadastroVenda.setVisible(true);
					telaCadastroVenda.setLocationRelativeTo(null);
					dispose();
				} else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum tipo de pagamento selecionado");
					telaMensagem.setVisible(true);
					telaMensagem.setLocationRelativeTo(null);
				}
				
			}
		});
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
	
	protected void atualizarCampos() {
		lucroTotal=0;
		NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        
		for(int i=0; i< listaProdutosVendidos.size(); i++) {
			ProdutoVenda p = listaProdutosVendidos.get(i);
			float lucroProduto = p.getPreco()*p.getQuantidade();
			lucroTotal = lucroTotal + lucroProduto;
		}
		comissao = lucroTotal*5/100;
		lblValorTotal.setText("Valor total: R$"+ nf.format(lucroTotal));
		lblComissao.setText("Comiss�o: R$"+ nf.format(lucroTotal*5/100));
		
		if (rBtnCredito.isSelected()) {
			int parcelas =1;
			try {
				parcelas = Integer.parseInt(txtParcelas.getText());
			} catch (NumberFormatException e2) {
				parcelas=1;
			}		
			
			float lucroParcelado = lucroTotal/parcelas;
			
			lblValorTotal.setText("Valor total: R$"+ nf.format(lucroParcelado)+" ("+txtParcelas.getText()+" vezes)");
			lblComissao.setText("Comiss�o: R$"+ nf.format(lucroTotal*5/100));
		}
		
	}

}
