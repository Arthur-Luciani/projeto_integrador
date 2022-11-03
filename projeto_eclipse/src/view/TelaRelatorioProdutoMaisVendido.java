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

import dao.EstadoDao;
import dao.FornecedorDao;
import dao.ProdutoDao;
import model.AtualizacaoProduto;
import model.Estado;
import model.Fornecedores;
import model.Produto;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import swingDesign.JTableViridisSinus;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class TelaRelatorioProdutoMaisVendido extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstoque frame = new TelaEstoque();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public TelaRelatorioProdutoMaisVendido() {
		
		//calculos arraylist
		
		
		
		
		
		setBackground(new Color(240, 255, 240));
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
		pTitulo.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Produtos Mais Vendidos");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		pTitulo.add(lblNewLabel);
		
		JPanel pTable = new JPanel();
		contentPane.add(pTable, BorderLayout.CENTER);
		pTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTableViridisSinus().padraoJtable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		table.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(85, 107, 47)));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setBackground(new Color(240, 255, 240));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nome", "Lucro", "Quantidade vendida"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(123);
		scrollPane.setViewportView(table);
		
		JPanel pBotoes = new JPanel();
		pBotoes.setBackground(new Color(240, 255, 240));
		contentPane.add(pBotoes, BorderLayout.SOUTH);
		pBotoes.setLayout(new BoxLayout(pBotoes, BoxLayout.X_AXIS));
		
		
		JPanel pBotoesEsquerda = new JPanel();
		pBotoesEsquerda.setBackground(new Color(240, 255, 240));
		FlowLayout fl_pBotoesEsquerda = (FlowLayout) pBotoesEsquerda.getLayout();
		fl_pBotoesEsquerda.setAlignment(FlowLayout.LEFT);
		pBotoes.add(pBotoesEsquerda);
		
		JPanel pBotoesDireita = new JPanel();
		pBotoesDireita.setBackground(new Color(240, 255, 240));
		FlowLayout fl_pBotoesDireita = (FlowLayout) pBotoesDireita.getLayout();
		fl_pBotoesDireita.setAlignment(FlowLayout.TRAILING);
		pBotoes.add(pBotoesDireita);
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProduto cadastroProduto;
				try {
					FornecedorDao dao = new FornecedorDao();
					ArrayList<Fornecedores> listaFornecedores= dao.resgatarFornecedores();
					
					cadastroProduto = new TelaCadastroProduto(listaFornecedores, null);
					cadastroProduto.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorios telaEstoque = new TelaRelatorios();
				telaEstoque.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		pBotoesEsquerda.add(btnVoltar);
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setBackground(new Color(85, 107, 47));
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnAdicionar);
		
		
		
		
	}

}
 

