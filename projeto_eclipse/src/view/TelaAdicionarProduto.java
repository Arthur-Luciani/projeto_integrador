package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;
import dao.ProdutoDao;
import dao.UsuarioDao;
import model.Cliente;
import model.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaAdicionarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuantidade;
	private static ArrayList<Float> listaLucro;

	/**
	 * Create the frame.
	 * 
	 * @param listaNomesProdutos
	 */
	public TelaAdicionarProduto(ArrayList<String> listaNomesProdutos, ArrayList<Float> lucros) {
		if (lucros == null || listaLucro == null) {
			listaLucro = new ArrayList<>();
		}

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
		txtQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_15.add(txtQuantidade);
		txtQuantidade.setColumns(20);

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_13.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_13);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				UsuarioDao daoUsuario = new UsuarioDao();
				ClienteDao daoCliente = new ClienteDao();
				ProdutoDao daoProduto =  new ProdutoDao();
				LinkedList<Usuario> listaNomesUsuarios = new LinkedList<>();
				LinkedList<Cliente> listaNomesClientes = new LinkedList<>(); 
				try {
					listaNomesUsuarios = daoUsuario.resgatarUsuarios();
					listaNomesClientes = daoCliente.resgatarClientes();
					String nomeProduto = cbProduto.getSelectedItem().toString();
					float quant = Float.parseFloat(txtQuantidade.getText());
					float lucro = daoProduto.precoProdutos(nomeProduto) * quant;
					listaLucro.add(lucro);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(listaLucro, listaNomesUsuarios, listaNomesClientes);
				telaCadastroVenda.setVisible(true);
				dispose();
				
				
				
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
