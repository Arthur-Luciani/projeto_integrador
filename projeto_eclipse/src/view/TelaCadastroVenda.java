package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.ProdutoDao;
import dao.VendaDao;
import model.AtualizacaoProduto;
import model.Produto;
import model.Venda;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaCadastroVenda extends JFrame {

	private JPanel contentPane;
	private JTextField txtData;
	
	private ArrayList<Float>  listaLucro= new ArrayList<>(); 
	
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaCadastroVenda(ArrayList<Float> listaLucro, ArrayList<String> listaNomesUsuarios, ArrayList<String> listaNomesCliente){
		this.listaLucro = listaLucro;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(85, 107, 47));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Cadastro das vendas");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 2, 0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout = (FlowLayout) panel_14.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_14);
		
		try {
			txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_14.add(txtData);
		txtData.setColumns(50);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_17.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_17);
		
		JComboBox cbClientes = new JComboBox();
		cbClientes.setBackground(new Color(85, 107, 47));
		cbClientes.setForeground(new Color(255, 255, 255));
		cbClientes.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		cbClientes.setModel(new DefaultComboBoxModel<String>(listaNomesCliente.toArray(new String[0])));
		panel_17.add(cbClientes);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) panel_20.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_20);
		
		JComboBox cbVendedor = new JComboBox();
		cbVendedor.setBackground(new Color(85, 107, 47));
		cbVendedor.setForeground(new Color(255, 255, 255));
		cbVendedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		cbVendedor.setModel(new DefaultComboBoxModel<String>(listaNomesUsuarios.toArray(new String[0])));
		panel_20.add(cbVendedor);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_7);
		
		JButton btnAdicionar = new JButton("Adicionar produtos");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdicionarProduto telaAdicionaProduto;
				try {
					ProdutoDao dao = new ProdutoDao();
					ArrayList<String> listaNomesProdutos = dao.nomeProdutos();
					telaAdicionaProduto = new TelaAdicionarProduto(listaNomesProdutos, listaLucro);
					telaAdicionaProduto.setVisible(true);
					dispose();
				}catch(SQLException e2) {
					
				}
			}
		});
		btnAdicionar.setBackground(new Color(85, 107, 47));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_7.add(btnAdicionar);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_23);
		panel_23.setLayout(new MigLayout("", "[10px][][][][][][][][][][][][][][][][][][][][][][][]", "[29px]"));
		
		JLabel lblLucroResul = new JLabel("-\r\n");
		lblLucroResul.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_23.add(lblLucroResul, "cell 1 0,alignx left,aligny top");
		
		JLabel lblComissao = new JLabel("Comissão:");
		panel_23.add(lblComissao, "cell 10 0");
		lblComissao.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JLabel lblComissaoResul = new JLabel("-");
		panel_23.add(lblComissaoResul, "cell 13 0");
		lblComissaoResul.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float lucroTotal = 0;
				for(int i=0; i< listaLucro.size(); i++) {
					float l = listaLucro.get(i);
					lucroTotal = lucroTotal+l;
				}
				float comissao = lucroTotal*5/100;
				
				lblLucroResul.setText(String.valueOf(lucroTotal));
				lblComissaoResul.setText(String.valueOf(comissao));
			}
		});
		btnCalcular.setForeground(new Color(255, 255, 255));
		btnCalcular.setBackground(new Color(85, 107, 47));
		btnCalcular.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_23.add(btnCalcular, "cell 22 0");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque();
				telaEstoque.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(new Color(85, 107, 47));
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_2.add(btnVoltar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = txtData.getText(); // variável criada para o isEmpty funcionar
				String nomeCliente = cbClientes.getSelectedItem().toString();
				String nomeVendedor = cbVendedor.getSelectedItem().toString();
				
				float lucroTotal = lblLucroResul.getAlignmentX();
				float comissao = lblComissaoResul.getAlignmentX();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //o LocalDate.parse cobra essa linha para funcionar
				LocalDate dataa = LocalDate.parse(data, formatter);
				
					if(data.isEmpty()) {
						txtData.setBorder(bordaVermelha);
					}else {
						Venda venda = new Venda(dataa, comissao, lucroTotal, nomeCliente, nomeVendedor);
						VendaDao vendaDao = new VendaDao();
						vendaDao.cadastroVenda(venda);
						
						TelaEstoque telaEstoque = new TelaEstoque();
						telaEstoque.setVisible(true);
						dispose();
				}
			}
		});
		btnCadastrar.setBackground(new Color(85, 107, 47));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_2.add(btnCadastrar);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(5, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_6);
		
		JLabel lblData = new JLabel("Data:");
		panel_6.add(lblData);
		lblData.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_9);
		
		JLabel lblCliente = new JLabel("Cliente:");
		panel_9.add(lblCliente);
		lblCliente.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_11);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		panel_11.add(lblVendedor);
		lblVendedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_13);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_15);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_5);
		
		JLabel lblLucro = new JLabel("Lucro:");
		panel_5.add(lblLucro);
		lblLucro.setFont(new Font("Segoe Print", Font.PLAIN, 16));
	}

}
