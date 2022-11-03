package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;
import dao.VendaDao;
import model.Cliente;
import model.Produto;
import model.Venda;

import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class TelaRelatorioComissao extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Venda> listaComissao = new ArrayList<Venda>();
	float comissaoTotal=0;

	/**
	 * Create the frame.
	 */
	public TelaRelatorioComissao(ArrayList<Venda> listaComissao) {
		this.listaComissao = listaComissao;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(85, 107, 47));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Relatorios de comissão");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorios relatorio = new TelaRelatorios();
				relatorio.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(new Color(240, 255, 240));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Vendedor", "Comiss\u00E3o", "Vendas realizadas"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(97);
		scrollPane.setViewportView(table);
		
		
	}
	protected void atualizarJTable() {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Vendedor", "Comiss\u00E3o", "Vendas realizadas"
				}
			);
		for(int i=0; i< listaComissao.size(); i++) {
			Venda v = listaComissao.get(i);
			
			
			modelo.addRow(new Object[] {v.getVendedor(), v.getComissaoVendedor()});
		}
		table.setModel(modelo);
	}
}
