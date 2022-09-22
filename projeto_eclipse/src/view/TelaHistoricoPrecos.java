package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.AtualizacaoProduto;
import model.Produto;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class TelaHistoricoPrecos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private ArrayList<AtualizacaoProduto>  listaAtualizacoes= new ArrayList<AtualizacaoProduto>(); 

	

	/**
	 * Create the frame.
	 */
	public TelaHistoricoPrecos(ArrayList<AtualizacaoProduto> listaAtualizacoes) {
		this.listaAtualizacoes = listaAtualizacoes;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(new Color(85, 107, 47));
		panel.setForeground(new Color(0, 0, 0));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblHistoricoPreco = new JLabel("Hist\u00F3rico de pre\u00E7os");
		lblHistoricoPreco.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblHistoricoPreco);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Data","C\u00F3digo", "Nome", "Pre\u00E7o" 
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(new Color(240, 255, 240));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnVoltar = new JButton("New button");
		btnVoltar.setBackground(new Color(85, 107, 47));
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(btnVoltar);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	protected void atualizarJTable() {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Data", "C\u00F3digo", "Nome", "Pre\u00E7o"
				}
			);
		for(int i=0; i< listaAtualizacoes.size(); i++) {
			AtualizacaoProduto p = listaAtualizacoes.get(i);
			modelo.addRow(new Object[] {p.getDataAtualizacao(), p.getId(), p.getNome(),"R$"+p.getPreco()});
		}
		
		table.setModel(modelo);
	}

}
	