package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;
import dao.VendaDao;
import model.Cliente;
import model.Produto;
import model.Usuario;
import model.Venda;
import swingDesign.JTableViridisSinus;

import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.ImageIcon;

public class TelaRelatorioComissao extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Venda> listaComissao;
	private Date entrada;
	private Date saida;
	
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);


	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	

	/**
	 * Create the frame.
	 */
	public TelaRelatorioComissao(ArrayList<Venda> listaComissao, Usuario usuarioLogado) throws ParseException {
		this.listaComissao=listaComissao;
		VendaDao dao = new VendaDao();
		setResizable(false);
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
		
		JLabel lblNewLabel = new JLabel("Relat??rio de comiss??o");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JPanel pCima = new JPanel();
		pCima.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) pCima.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_1.add(pCima);
		
		JPanel pBaixo = new JPanel();
		pBaixo.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) pBaixo.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(pBaixo);
		
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setIcon(new ImageIcon(TelaRelatorioComissao.class.getResource("/images/icons8-??-esquerda-dentro-de-um-c??rculo-24.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorios relatorio = new TelaRelatorios(usuarioLogado);
				relatorio.setVisible(true);
				relatorio.setLocationRelativeTo(null);
				dispose();
			}
		});
		panel_1.setLayout(new GridLayout(2, 7, 0, 0));
		
		
		JLabel lblEntrada = new JLabel("Data de entrada");
		lblEntrada.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pCima.add(lblEntrada);
		
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pBaixo.add(btnNewButton);
		
		JFormattedTextField txtDataEntrada = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataEntrada.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtDataEntrada.setColumns(7);
		pCima.add(txtDataEntrada);
		
		JLabel lblDataSaida = new JLabel("Data de sa??da");
		lblDataSaida.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pCima.add(lblDataSaida);
		txtDataEntrada.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataEntrada.setBorder(bordaNormal);
			}
		});
		
		JFormattedTextField txtDataSaida = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataSaida.setColumns(7);
		txtDataSaida.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pCima.add(txtDataSaida);
		txtDataSaida.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataEntrada.setBorder(bordaNormal);
			}
		});
		
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setIcon(new ImageIcon(TelaRelatorioComissao.class.getResource("/images/icons8-pesquisar-24.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				VendaDao dao = new VendaDao();
				
				LocalDate dataEntrada = null;
				LocalDate dataSaida = null;
				
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
				try {
					ArrayList<Venda> listaComissao = dao.resgatarComissao(Date.valueOf(dataEntrada), Date.valueOf(dataSaida));
					atualizarJTable(listaComissao);
				} catch (NullPointerException e2) {
					TelaMensagem telaMensagem = new TelaMensagem("Data inv??lida");
					telaMensagem.setVisible(true);
					telaMensagem.setLocationRelativeTo(null);
				}
		}});
		btnPesquisar.setBackground(new Color(85, 107, 47));
		btnPesquisar.setForeground(new Color(255, 255, 255));
		btnPesquisar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pCima.add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTableViridisSinus().padraoJtable();
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
	protected void atualizarJTable(ArrayList<Venda> listaComissao) {
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Vendedor", "Comiss\u00E3o", "Vendas realizadas"
				}
			);
		for(int i=0; i< listaComissao.size(); i++) {
			Venda v = listaComissao.get(i);
			
			modelo.addRow(new Object[] {v.getVendedor().getNome(), v.getComissaoVendedor(), v.getVendas_Vendedor()});
		}
		table.setModel(modelo);
	}
}
