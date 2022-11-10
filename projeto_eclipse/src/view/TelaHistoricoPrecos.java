package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ProdutoDao;
import model.AtualizacaoProduto;
import model.Produto;
import swingDesign.JTableViridisSinus;

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
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class TelaHistoricoPrecos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private ArrayList<AtualizacaoProduto>  listaAtualizacoes= new ArrayList<AtualizacaoProduto>(); 
	private Produto produtoSelecionado;
	

	/**
	 * Create the frame.
	 */
	public TelaHistoricoPrecos(ArrayList<AtualizacaoProduto> listaAtualizacoes, Produto produtoSelecionado) {
		this.listaAtualizacoes = listaAtualizacoes;
		this.produtoSelecionado = produtoSelecionado;
		
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
		
		JPanel pCentro = new JPanel();
		getContentPane().add(pCentro, BorderLayout.CENTER);
		pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		pCentro.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNome = new JLabel("           Nome: "+produtoSelecionado.getNome());
		lblNome.setBackground(new Color(240, 255, 240));
		lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_2.add(lblNome);
		
		JLabel lblNewLabel_1 = new JLabel("           C\u00F3digo: "+String.valueOf(produtoSelecionado.getId()));
		lblNewLabel_1.setBackground(new Color(240, 255, 240));
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_2.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		//
		pCentro.add(scrollPane);
		
		table = new JTableViridisSinus().padraoJtable();
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(85, 107, 47)));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(240, 255, 240));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data", "Pre\u00E7o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(new Color(240, 255, 240));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaHistoricoPrecos.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoDao dao = new ProdutoDao();
				
				TelaListaProdutos telaListaProdutos = new TelaListaProdutos(dao.resgatarProdutos());
				telaListaProdutos.atualizarJTable();
				telaListaProdutos.setVisible(true);
				dispose();
			}
		});
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
					"Data", "Pre\u00E7o"
				}
			);
		for(int i=0; i< listaAtualizacoes.size(); i++) {
			AtualizacaoProduto p = listaAtualizacoes.get(i);
			modelo.addRow(new Object[] {p.getDataAtualizacao(), "R$"+p.getPreco()});
		}
		
		table.setModel(modelo);
	}

}
	