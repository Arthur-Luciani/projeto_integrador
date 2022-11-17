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
import dao.UsuarioDao;
import model.Cliente;
import model.Estado;
import model.Produto;
import model.Usuario;
import swingDesign.JTableViridisSinus;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class TelaListaFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LinkedList<Usuario> listaFuncionario = new LinkedList<>();
	private Usuario funcionarioSelecionado;
	/**
	 * Create the frame.
	 */
	public TelaListaFuncionarios(LinkedList<Usuario> listaFuncionario, Usuario usuarioLogado) {
		this.listaFuncionario = listaFuncionario;
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
		
		JLabel lblNewLabel = new JLabel("Funcionários Cadastrados");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTableViridisSinus().padraoJtable();
		table.setBackground(new Color(240, 255, 240));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posicaoCliente = table.getSelectedRow();
				funcionarioSelecionado = listaFuncionario.get(posicaoCliente);
			}
		});
		table.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(85, 107, 47)));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Idade", "Função"
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
		panel_3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial inicio = new TelaInicial(usuarioLogado);
				inicio.setVisible(true);
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
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(usuarioLogado);
				telaCadastroFuncionario.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnNewButton_3 = new JButton("Atualizar");
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(funcionarioSelecionado!=null) {
					TelaAtualizarFuncionario telaAtualizarFuncionario = new TelaAtualizarFuncionario(funcionarioSelecionado, usuarioLogado);
					telaAtualizarFuncionario.setVisible(true);
					dispose();
				}else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum funcionário selecionado");
					telaMensagem.setVisible(true);
				}
			}
		});
		btnNewButton_3.setBackground(new Color(85, 107, 47));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnNewButton_2 = new JButton("Deletar");
		panel_2.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (funcionarioSelecionado!=null) {
					UsuarioDao dao;
					dao = new UsuarioDao();
					dao.deletarUsuario(funcionarioSelecionado.getIdUsuario());
					TelaListaFuncionarios.this.listaFuncionario = dao.resgatarUsuarios();
					atualizarJTable();
				} else {
					TelaMensagem telaMensagem = new TelaMensagem("Nenhum funcionario selecionado");
					telaMensagem.setVisible(true);
				}
				
				
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
						"Nome", "Idade", "Função"
				}
			);
		for (int i = 0; i < listaFuncionario.size(); i++) {
			Usuario f = listaFuncionario.get(i);
			modelo.addRow(new Object[] {f.getNome(), f.getIdade(), f.getFuncao()});;
		}
		table.setModel(modelo);
	}

}
