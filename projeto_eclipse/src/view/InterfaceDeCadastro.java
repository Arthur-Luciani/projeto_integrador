package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class InterfaceDeCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtConfirmar;
	private JTextField txtNome;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDeCadastro frame = new InterfaceDeCadastro();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceDeCadastro() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(85, 107, 47));
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cadastro funcionarios");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][][][][][][grow]", "[][]"));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_2.add(lblNome, "cell 2 1");
		
		txtNome = new JTextField();
		panel_2.add(txtNome, "cell 6 1,growx");
		txtNome.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[][][][][][][407.00,grow]", "[][]"));
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_3.add(lblLogin, "cell 2 1");
		
		txtLogin = new JTextField();
		panel_3.add(txtLogin, "cell 6 1,growx");
		txtLogin.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[][][][][][][grow]", "[][]"));
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_4.add(lblSenha, "cell 2 1");
		
		txtSenha = new JTextField();
		panel_4.add(txtSenha, "cell 6 1,growx");
		txtSenha.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_5);
		panel_5.setLayout(new MigLayout("", "[][][][grow]", "[][]"));
		
		JLabel lblConfirmar = new JLabel("Confirmar senha:");
		lblConfirmar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_5.add(lblConfirmar, "cell 2 1,alignx trailing");
		
		txtConfirmar = new JTextField();
		panel_5.add(txtConfirmar, "cell 3 1,growx");
		txtConfirmar.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][grow]", "[][]"));
		
		JLabel lblPermissao = new JLabel("Permissão");
		lblPermissao.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel.add(lblPermissao, "cell 2 1");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(85, 107, 47));
		comboBox.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Vendedor", "Administrador"}));
		panel.add(comboBox, "flowx,cell 5 1,growx");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(85, 107, 47));
		btnCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel.add(btnCadastrar, "cell 5 1");
	}

}
