package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDao;
import dao.EstadoDao;
import dao.ProdutoDao;
import model.Cliente;
import model.Estado;
import model.Produto;
import model.Usuario;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ImageIcon;

public class TelaListaClientes extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LinkedList<Cliente> listaCliente = new LinkedList<Cliente>();
	private Cliente clienteSelecionado;
	/**
	 * Create the frame.
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public TelaListaClientes(LinkedList<Cliente> listaCliente, Usuario usuarioLogado) {
		setResizable(false);
		this.listaCliente = listaCliente;
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
		
		JLabel lblNewLabel = new JLabel("Clientes cadastrados");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(new Color(240, 255, 240));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posicaoCliente = table.getSelectedRow();
				clienteSelecionado = listaCliente.get(posicaoCliente);
			}
		});
		table.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(85, 107, 47)));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Email", "CPF"
			}
		));
		scrollPane.setViewportView(table);
		atualizarJTable();
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_3);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setIcon(new ImageIcon(TelaListaClientes.class.getResource("/images/icons8-??-esquerda-dentro-de-um-c??rculo-24.png")));
		panel_3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial inicio = new TelaInicial(usuarioLogado);
				inicio.setVisible(true);
				inicio.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_2);
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.setIcon(new ImageIcon(TelaListaClientes.class.getResource("/images/icons8-mais-2-matem??tica-24.png")));
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoDao estados = new EstadoDao();
				LinkedList<Estado> listaEstados = estados.resgatarEstados();
				TelaCadastroCliente cadastro = new TelaCadastroCliente(listaEstados, true, null, usuarioLogado);
				cadastro.setVisible(true);
				cadastro.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnNewButton_3 = new JButton("Atualizar");
		btnNewButton_3.setIcon(new ImageIcon(TelaListaClientes.class.getResource("/images/icons8-confirma????o-e-atualiza????o-24.png")));
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clienteSelecionado!=null) {
					EstadoDao estados = new EstadoDao();
					LinkedList<Estado> listaEstados = estados.resgatarEstados();
					TelaAtualizarCliente atualizado = new TelaAtualizarCliente(listaEstados, clienteSelecionado, usuarioLogado);
					atualizado.setVisible(true);
					atualizado.setLocationRelativeTo(null);
					dispose();
				}else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum cliente selecionado para atualizar");
					telaMensagem.setVisible(true);
					telaMensagem.setLocationRelativeTo(null);
				}
			}
		});
		btnNewButton_3.setBackground(new Color(85, 107, 47));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnNewButton_2 = new JButton("Deletar");
		btnNewButton_2.setIcon(new ImageIcon(TelaListaClientes.class.getResource("/images/icons8-excluir-24.png")));
		panel_2.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDao dao;
				dao = new ClienteDao();
				dao.deletarCliente(clienteSelecionado.getId());
				TelaListaClientes.this.listaCliente = dao.resgatarCliente();
				atualizarJTable();
			}
		});
		btnNewButton_2.setBackground(new Color(85, 107, 47));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
	}
	protected void atualizarJTable() {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nome", "Email", "CPF"
				}
			);
		for (int i = 0; i < listaCliente.size(); i++) {
			Cliente c = listaCliente.get(i);
			modelo.addRow(new Object[] {c.getNome(), c.getEmail(), c.getCpf()});;
		}
		table.setModel(modelo);
	}

}
