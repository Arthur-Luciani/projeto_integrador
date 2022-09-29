package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.ProdutoDao;
import model.AtualizacaoProduto;
import model.Produto;

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
	private JTextField txtCliente;
	private JTextField txtVendedor;
	
	private static ArrayList<String>  listaProduto= new ArrayList<String>(); 
	
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private JTextField txtProduto;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaCadastroVenda(ArrayList<String> listaProduto) throws ParseException {
		this.listaProduto = listaProduto;
		
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
		FlowLayout flowLayout = (FlowLayout) panel_14.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_14);
		
		txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		panel_14.add(txtData);
		txtData.setColumns(50);
		
		JPanel panel_17 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_17.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_17);
		
		txtCliente = new JTextField();
		panel_17.add(txtCliente);
		txtCliente.setColumns(50);
		
		JPanel panel_20 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_20.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_20);
		
		txtVendedor = new JTextField();
		txtVendedor.setText("");
		panel_20.add(txtVendedor);
		txtVendedor.setColumns(50);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_7);
		
		JComboBox cbProduto = new JComboBox();
		cbProduto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				cbProduto.setBorder(bordaNormal);
			}
		});
		
		txtProduto = new JTextField();
		panel_7.add(txtProduto);
		txtProduto.setColumns(50);
		cbProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		cbProduto.setModel(new DefaultComboBoxModel<String>(listaProduto.toArray(new String[0])));
		panel_7.add(cbProduto);
		
		JPanel panel_23 = new JPanel();
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
				String nomeproduto = cbProduto.getSelectedItem().toString();
				
				try {
					ProdutoDao dao = new ProdutoDao();
					dao.precoProdutos(nomeproduto);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
				String data = txtData.getText();
				String cliente = txtCliente.getText();
				String vendedor = txtVendedor.getText();
				
				if (data.isEmpty() || cliente.isEmpty() || vendedor.isEmpty()) {
					if(data.isEmpty()) {
						txtData.setBorder(bordaVermelha);
					}
					if(cliente.isEmpty()) {
						txtCliente.setBorder(bordaVermelha);
					}
					if(vendedor.isEmpty()) {
						txtVendedor.setBorder(bordaVermelha);
					}
				}else {
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
		panel_3.add(panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		
		JLabel lblData = new JLabel("Data:");
		panel_6.add(lblData);
		lblData.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_3.add(panel_9);
		
		JLabel lblCliente = new JLabel("Cliente:");
		panel_9.add(lblCliente);
		lblCliente.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		panel_11.add(lblVendedor);
		lblVendedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_12 = new JPanel();
		panel_3.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_3.add(panel_13);
		
		JLabel lblQuant = new JLabel("Quantidade");
		lblQuant.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_13.add(lblQuant);
		
		JLabel lblProduto = new JLabel("Produtos:");
		lblProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_13.add(lblProduto);
		
		JPanel panel_15 = new JPanel();
		panel_3.add(panel_15);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel lblLucro = new JLabel("Lucro:");
		panel_5.add(lblLucro);
		lblLucro.setFont(new Font("Segoe Print", Font.PLAIN, 16));
	}

}
