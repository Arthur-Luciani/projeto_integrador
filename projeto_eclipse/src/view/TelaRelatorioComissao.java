package view;

import java.awt.Color;
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

import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

public class TelaRelatorioComissao extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Venda> listaComissao;
	private Date entrada;
	private Date saida;
	
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);



	/**
	 * Create the frame.
	 */
	public TelaRelatorioComissao(ArrayList<Venda> listaComissao) throws ParseException {
		this.listaComissao=listaComissao;
		VendaDao dao = new VendaDao();
		
		
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
		
		JFormattedTextField txtDataEntrada = new JFormattedTextField(new MaskFormatter("##/##/####"));
		panel_1.add(txtDataEntrada);
		txtDataEntrada.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataEntrada.setBorder(bordaNormal);
			}
		});
		
		
		
		JLabel lblAte = new JLabel("Até");
		lblAte.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblAte);
		
		JFormattedTextField txtDataSaida = new JFormattedTextField(new MaskFormatter("##/##/####"));
		panel_1.add(txtDataSaida);
		txtDataSaida.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataEntrada.setBorder(bordaNormal);
			}
		});
		
		
		JButton btnPesquisar = new JButton("Pesquisar");
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
				ArrayList<Venda> listaComissao = dao.resgatarComissao(Date.valueOf(dataEntrada), Date.valueOf(dataSaida));
				atualizarJTable(listaComissao);
		}});
		btnPesquisar.setBackground(new Color(85, 107, 47));
		btnPesquisar.setForeground(new Color(255, 255, 255));
		btnPesquisar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(btnPesquisar);
		
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
