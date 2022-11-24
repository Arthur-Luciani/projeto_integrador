package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import dao.EstadoDao;
import dao.FornecedorDao;
import dao.ProdutoDao;
import model.Estado;
import model.Fornecedores;
import model.Produto;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import swingDesign.JTableViridisSinus;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;

public class TelaListaFornecedores extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Fornecedores> listaFornecedores = new ArrayList<Fornecedores>();
	private Fornecedores fornecedorSelecionado;

	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	

	public TelaListaFornecedores(ArrayList<Fornecedores> listaFornecedores, Usuario usuarioLogado) {
		setResizable(false);
		setBackground(new Color(240, 255, 240));
		this.listaFornecedores = listaFornecedores;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(85, 107, 47));
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		
		JLabel lblListaFornecedores = new JLabel("Lista de Fornecedores");
		lblListaFornecedores.setForeground(new Color(255, 255, 255));
		lblListaFornecedores.setBackground(new Color(85, 107, 47));
		lblListaFornecedores.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel_1.add(lblListaFornecedores);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTableViridisSinus().padraoJtable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posicaoProduto = table.getSelectedRow();
				fornecedorSelecionado = listaFornecedores.get(posicaoProduto);
			}
		});
		
		table.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(85, 107, 47)));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setBackground(new Color(240, 255, 240));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CNPJ", "Email", "Nome", "Telefone", "CEP"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_4);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaListaFornecedores.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque(usuarioLogado);
				telaEstoque.setVisible(true);
				telaEstoque.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		panel_3.add(btnVoltar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setIcon(new ImageIcon(TelaListaFornecedores.class.getResource("/images/icons8-confirmação-e-atualização-24.png")));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAtualizarFornecedor telaAtualizarFornecedor;
				if (fornecedorSelecionado != null) {
					FornecedorDao daoFornecedor = new FornecedorDao();
					EstadoDao daoEstado = new EstadoDao();
					ArrayList<Fornecedores> listaFornecedores = daoFornecedor.resgatarFornecedores();
					LinkedList<Estado>listaEstados = daoEstado.resgatarEstados();
					telaAtualizarFornecedor = new TelaAtualizarFornecedor(listaFornecedores, fornecedorSelecionado, listaEstados, usuarioLogado);
					telaAtualizarFornecedor.setVisible(true);
					telaAtualizarFornecedor.setLocationRelativeTo(null);
					dispose();
				} else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum fornecedor selecionado para atualizar");
					telaMensagem.setVisible(true);
					telaMensagem.setLocationRelativeTo(null);
				}
			}
		});
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(TelaListaFornecedores.class.getResource("/images/icons8-mais-2-matemática-24.png")));
		
		btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaCadastroFornecedor cadastroFornecedor;
					FornecedorDao daoFornecedor = new FornecedorDao();
					EstadoDao  daoEstado = new EstadoDao();
					ArrayList<Fornecedores> listaFornecedores= daoFornecedor.resgatarFornecedores();
					LinkedList<Estado> listaEstado = daoEstado.resgatarEstados();
					cadastroFornecedor = new TelaCadastroFornecedor(listaFornecedores, fornecedorSelecionado, listaEstado, usuarioLogado);
					cadastroFornecedor.setVisible(true);
					cadastroFornecedor.setLocationRelativeTo(null);
					dispose();
				}
			 
		});
		
		
		
		
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnCadastrar.setBackground(new Color(85, 107, 47));
		panel_4.add(btnCadastrar);
		btnAtualizar.setForeground(Color.WHITE);
		btnAtualizar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnAtualizar.setBackground(new Color(85, 107, 47));
		panel_4.add(btnAtualizar);		
		
		if (usuarioLogado.isPermissao()) {
			btnAtualizar.setEnabled(true);
			btnCadastrar.setEnabled(true);
		} else {
			btnAtualizar.setEnabled(false);
			btnCadastrar.setEnabled(false);
		}
	}

	protected void atualizarJTable() {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"CNPJ", "Email", "Nome", "Telefone", "CEP"
				}
			);
		for(int i=0; i< listaFornecedores.size(); i++) {
			Fornecedores f = listaFornecedores.get(i);
			modelo.addRow(new Object[] { f.getCnpj(), f.getEmail(), f.getNome(), f.getTelefone(), f.getCep()});
		}
		
		table.setModel(modelo);
	}


}
