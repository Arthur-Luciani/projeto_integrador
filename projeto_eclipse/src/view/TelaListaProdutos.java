package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ProdutoDao;
import model.Produto;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaListaProdutos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	ArrayList<Produto> listaProdutos = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaProdutos frame = new TelaListaProdutos();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListaProdutos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ProdutoDao produto;
		try {
			produto = new ProdutoDao();
			listaProdutos = produto.resgatarProdutos();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(85, 107, 47));
		contentPane.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Lista de Produtos");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel_1.add(lblNewLabel, "cell 15 1");
		
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
				"C\u00F3digo", "Nome", "Pre\u00E7o", "Quantidade no estoque"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(123);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][]", "[][][]"));
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto cadastroProduto;
				try {
					cadastroProduto = new CadastroProduto();
					cadastroProduto.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel.add(btnNewButton, "cell 2 2");
		
		JButton btnNewButton_1 = new JButton("Histórico de preços");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel.add(btnNewButton_1, "cell 8 2");
		
		JButton btnNewButton_2 = new JButton("Atualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto cadastroProduto;
				try {
					cadastroProduto = new CadastroProduto();
					cadastroProduto.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(85, 107, 47));
		btnNewButton_2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel.add(btnNewButton_2, "cell 14 2");
	}
	protected void atualizarJTable() {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00F3digo", "Nome", "Pre\u00E7o", "Quantidade no estoque"
				}
			);
		for(int i=0; i< listaProdutos.size(); i++) {
			Produto p = listaProdutos.get(i);
			modelo.addRow(new Object[] { p.getId(), p.getNome(), p.getPreco(), p.getQuantEstoque()});
		}
		
		table.setModel(modelo);
	}

}
