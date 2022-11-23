package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.EstadoDao;
import dao.FornecedorDao;
import dao.ProdutoDao;
import dao.RelatorioDao;
import model.AtualizacaoProduto;
import model.Estado;
import model.Fornecedores;
import model.Produto;
import model.RelatorioVendas;
import model.Usuario;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import swingDesign.JTableViridisSinus;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaRelatorioVendas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtDataEntrada;
	private JTextField txtDataSaida;
	
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private JTable table_1;
	private JTable table_2;
	

	/**
	 * Create the frame.
	 */
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public TelaRelatorioVendas(Usuario usuarioLogado) {
		
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
		
		JLabel lblNewLabel = new JLabel("Relat\u00F3rio de vendas");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		pTitulo.add(lblNewLabel);
		
		JPanel pTable = new JPanel();
		contentPane.add(pTable, BorderLayout.CENTER);
		pTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table_2 = new JTableViridisSinus().padraoJtable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Nome", "Quantidade", "Lucro"
			}
		));
		scrollPane.setViewportView(table_2);
		

		
		
		JPanel pBotoes = new JPanel();
		pBotoes.setBackground(new Color(240, 255, 240));
		contentPane.add(pBotoes, BorderLayout.SOUTH);
		pBotoes.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel pBotoesDireita = new JPanel();
		pBotoesDireita.setBackground(new Color(240, 255, 240));
		FlowLayout fl_pBotoesDireita = (FlowLayout) pBotoesDireita.getLayout();
		fl_pBotoesDireita.setAlignment(FlowLayout.LEFT);
		pBotoes.add(pBotoesDireita);
		
		JLabel lblDataEntrada = new JLabel("Data de entrada");
		lblDataEntrada.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(lblDataEntrada);
		
		try {
			txtDataEntrada = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtDataEntrada.setColumns(7);
			txtDataEntrada.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txtDataEntrada.setBorder(bordaNormal);
				}
			});
			txtDataEntrada.setFont(new Font("Segoe Print", Font.PLAIN, 16));
			pBotoesDireita.add(txtDataEntrada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblSaida = new JLabel("Data de saída:");
		lblSaida.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(lblSaida);
		
		try {
			txtDataSaida = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtDataSaida.setColumns(7);
			txtDataSaida.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txtDataSaida.setBorder(bordaNormal);
				}
			});
			txtDataSaida.setFont(new Font("Segoe Print", Font.PLAIN, 16));
			pBotoesDireita.add(txtDataSaida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JComboBox cbTipoPesquisa = new JComboBox();
		cbTipoPesquisa.setBackground(new Color(85, 107, 47));
		cbTipoPesquisa.setForeground(new Color(255, 255, 255));
		cbTipoPesquisa.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		cbTipoPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Lucro", "Quantidade"}));
		pBotoesDireita.add(cbTipoPesquisa);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMensagem telaMensagem = null;
				
				LocalDate dataEntrada = null;
				LocalDate dataSaida = null;
				String tipoRelatorio = cbTipoPesquisa.getSelectedItem().toString();
				
				DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								
				try {
					dataEntrada = LocalDate.parse(txtDataEntrada.getText(), formatacao);
				} catch (DateTimeException e2) {
					txtDataEntrada.setBorder(bordaVermelha);
				}
				try {
					dataSaida = LocalDate.parse(txtDataSaida.getText(), formatacao);
				} catch (DateTimeException e2) {
					txtDataSaida.setBorder(bordaVermelha);
				}
				
				if (dataEntrada != null && dataSaida!= null) {
					if (dataEntrada.isBefore(dataSaida)) {
						RelatorioDao dao = new RelatorioDao();	
						if (cbTipoPesquisa.getSelectedIndex() == 0) {
							atualizarJTable(dao.relatorioVendasLucro(Date.valueOf(dataEntrada), Date.valueOf(dataSaida)));
						} else {
							atualizarJTable(dao.relatorioVendasQuantidade(Date.valueOf(dataEntrada), Date.valueOf(dataSaida)));
						}
						
					} else {
						if (dataSaida.isAfter(LocalDate.now())) {
							telaMensagem = new TelaMensagem("Data de sa�da inv�lida");
							txtDataSaida.setBorder(bordaVermelha);
						} else {
							telaMensagem = new TelaMensagem("Datas inv�lidas");
							txtDataEntrada.setBorder(bordaVermelha);
							txtDataSaida.setBorder(bordaVermelha);
						}
						telaMensagem.setVisible(true);
					
					}
				} else {
					if (dataEntrada == null) {
						txtDataEntrada.setBorder(bordaVermelha);
						telaMensagem = new TelaMensagem("Data de entrada inv�lida");
					} else if (dataSaida == null) {
						txtDataSaida.setBorder(bordaVermelha);
						telaMensagem = new TelaMensagem("Data de sa�da inv�lida");
					} else {
						txtDataEntrada.setBorder(bordaVermelha);
						txtDataSaida.setBorder(bordaVermelha);
						telaMensagem = new TelaMensagem("Datas inv�lidas");
					}
					telaMensagem.setVisible(true);					
				}
			}
		});
		btnPesquisar.setForeground(new Color(255, 255, 255));
		btnPesquisar.setBackground(new Color(85, 107, 47));
		btnPesquisar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBotoesDireita.add(btnPesquisar);
		
		
		JPanel pBotoesEsquerda = new JPanel();
		pBotoesEsquerda.setBackground(new Color(240, 255, 240));
		FlowLayout fl_pBotoesEsquerda = (FlowLayout) pBotoesEsquerda.getLayout();
		fl_pBotoesEsquerda.setAlignment(FlowLayout.LEFT);
		pBotoes.add(pBotoesEsquerda);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorios telaRelatorios = new TelaRelatorios(usuarioLogado);
				telaRelatorios.setVisible(true);
				dispose();
				
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		pBotoesEsquerda.add(btnVoltar);
		
		
	}
	
	
	protected void atualizarJTable(ArrayList<RelatorioVendas> listaRelatorioVendas) {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nome", "Lucro", "Quantidade vendida"
				}
			);
		for(int i=0; i< listaRelatorioVendas.size(); i++) {
			RelatorioVendas r = listaRelatorioVendas.get(i);
			modelo.addRow(new Object[] { r.getNome(), r.getLucro(), r.getQuantidade()});
		}
		if (listaRelatorioVendas.size()<17) {
			for (int i = 0; i < (17-listaRelatorioVendas.size()); i++) {
				
				modelo.addRow(new Object[][] {null, null, null});
			}
		}
		
		table_2.setModel(modelo);
	}

}
