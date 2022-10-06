package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import dao.FornecedorDao;
import dao.ProdutoDao;
import model.Produto;
import net.miginfocom.swing.MigLayout;

public class TelaListaFornecedores extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	

	public TelaListaFornecedores() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(85, 107, 47));
		contentPane.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][]", "[][][]"));
		
		JLabel lblListaFornecedores = new JLabel("Lista de Fornecedores");
		lblListaFornecedores.setForeground(new Color(255, 255, 255));
		lblListaFornecedores.setBackground(new Color(85, 107, 47));
		lblListaFornecedores.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel_1.add(lblListaFornecedores, "cell 15 1");
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		
		table.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(85, 107, 47)));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setBackground(new Color(240, 255, 240));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CNPJ", "Nome", "Email", "Localização", "Telefone"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][]", "[][][][][]"));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque();
				telaEstoque.setVisible(true);
				dispose();
			}
		});
		
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		panel.add(btnVoltar, "cell 0 3");
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFornecedor telaCadastroFornecedor = null;
				try {
					telaCadastroFornecedor = new TelaCadastroFornecedor(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				telaCadastroFornecedor.setVisible(true);
				dispose();
			}
		});
		btnAtualizar.setForeground(Color.WHITE);
		btnAtualizar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnAtualizar.setBackground(new Color(85, 107, 47));
		panel.add(btnAtualizar, "cell 2 3");
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFornecedor telaCadastroFornecedor = null;
				try {
					telaCadastroFornecedor = new TelaCadastroFornecedor(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				telaCadastroFornecedor.setVisible(true);
				dispose();
			}
		});
		
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnAdicionar.setBackground(new Color(85, 107, 47));
		panel.add(btnAdicionar, "cell 4 3");
		
	}
	



}
