package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ProdutoDao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAdicionarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuantidade;
	private ArrayList<Float> listaLucro; 
	

	/**
	 * Create the frame.
	 * @param listaNomesProdutos 
	 */
	public TelaAdicionarProduto(ArrayList<String> listaNomesProdutos, ArrayList<Float>listaLucro) {
		this.listaLucro = listaLucro;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(new Color(85, 107, 47));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Adicionar Produto");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[]"));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_12);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) panel_14.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_14);
		
		JComboBox cbProduto = new JComboBox();
		panel_14.add(cbProduto);
		cbProduto.addFocusListener(new FocusAdapter() {
		});
		cbProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		cbProduto.setModel(new DefaultComboBoxModel<String>(listaNomesProdutos.toArray(new String[0])));
		cbProduto.setForeground(new Color(255, 255, 255));
		cbProduto.setBackground(new Color(85, 107, 47));
		cbProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_15);
		
		txtQuantidade = new JTextField();
		panel_15.add(txtQuantidade);
		txtQuantidade.setColumns(50);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_13.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_13);
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeProduto = cbProduto.getSelectedItem().toString();
				float quant = Float.parseFloat(txtQuantidade.getText());
				
				try {
					ProdutoDao dao = new ProdutoDao();
					float lucro = dao.precoProdutos(nomeProduto)*quant;
					listaLucro.add(lucro);
					
					TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(listaLucro);
					//telaCadastroVenda = new TelaAdicionarProduto(lucro1);
					telaCadastroVenda.setVisible(true);
					dispose();	
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		btnAdicionar.setBackground(new Color(85, 107, 47));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(btnAdicionar, "cell 11 0");
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_7);
		
		JLabel lblProduto = new JLabel("Produto");
		panel_7.add(lblProduto);
		lblProduto.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_9);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		panel_9.add(lblQuantidade);
		lblQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_11);
	}
}
