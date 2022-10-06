package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import dao.ProdutoDao;
import dao.UsuarioDao;
import model.Produto;
import model.Usuario;
import model.ValidaCPF;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;

public class TelaCadastroFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtDataNascimento;
	private JTextField txtCnpj;
	private JPasswordField txtSenha;
	private JPasswordField txtBairro;
	private static TelaCadastroFuncionario frame;

	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private JPasswordField txtCidade;
	private JPasswordField txtCep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaCadastroFuncionario();
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
	 * 
	 * @throws ParseException
	 */
	public TelaCadastroFornecedor(Boolean atualizarCadastrar) throws ParseException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 824, 99);
		panel.setBackground(new Color(85, 107, 47));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setForeground(Color.WHITE);
		contentPane.add(panel);
		
		
		JLabel lbAtualizaCadastrar = new JLabel("Teste");
		if (atualizarCadastrar == true) {
			lbAtualizaCadastrar = new JLabel("Adicionar");
			
		}	else {
			lbAtualizaCadastrar = new JLabel("Atualizar");

		}
		lbAtualizaCadastrar.setForeground(Color.WHITE);
		lbAtualizaCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lbAtualizaCadastrar);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		panel_1.setBounds(20, 104, 814, 407);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(65, 3, 46, 29);
		lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(320, 0, 265, 35);
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNome.setBorder(bordaNormal);
			}
		});
		txtNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(65, 43, 192, 29);
		lblTelefone.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblTelefone);

		txtTelefone = new JFormattedTextField(new MaskFormatter("## #####-####"));
		txtTelefone.setBounds(320, 40, 265, 35);
		txtTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTelefone.setBorder(bordaNormal);
			}
		});
		txtTelefone.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(65, 84, 192, 26);
		lblEmail.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		panel_1.add(lblEmail);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(320, 160, 265, 35);

		txtSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSenha.setBorder(bordaNormal);
			}
		});

		txtDataNascimento = new JFormattedTextField();
		txtDataNascimento.setBounds(320, 80, 265, 35);
		txtDataNascimento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataNascimento.setBorder(bordaNormal);
			}
		});
		txtDataNascimento.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtDataNascimento.setColumns(10);
		panel_1.add(txtDataNascimento);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(65, 123, 192, 29);
		lblCnpj.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblCnpj);

		txtCnpj = new JTextField();
		txtCnpj.setBounds(320, 120, 265, 35);
		txtCnpj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCnpj.setBorder(bordaNormal);
				txtCnpj.setForeground(Color.DARK_GRAY);
			}
		});
		txtCnpj.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtCnpj.setColumns(10);
		panel_1.add(txtCnpj);

		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(65, 163, 192, 29);
		lblRua.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblRua);
		txtSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtSenha.setColumns(10);
		panel_1.add(txtSenha);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(65, 203, 51, 29);
		lblBairro.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblBairro);

		txtBairro = new JPasswordField();
		txtBairro.setBounds(320, 200, 265, 35);
		txtBairro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBairro.setBorder(bordaNormal);
			}
		});
		txtBairro.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtBairro.setColumns(10);
		panel_1.add(txtBairro);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(65, 360, 115, 37);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaFornecedores telaListaFornecedores = new TelaListaFornecedores();
				telaListaFornecedores.setVisible(true);
				dispose();
			}
		});

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(65, 243, 55, 29);
		lblCidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblCidade);

		txtCidade = new JPasswordField();
		txtCidade.setBounds(320, 240, 265, 35);
		txtCidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtCidade.setColumns(10);
		panel_1.add(txtCidade);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(65, 284, 33, 29);
		lblCep.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblCep);

		txtCep = new JPasswordField();
		txtCep.setBounds(320, 281, 265, 35);
		txtCep.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtCep.setColumns(10);
		panel_1.add(txtCep);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(65, 323, 55, 29);
		lblEstado.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblEstado);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(320, 321, 265, 34);
		comboBox.setFont(new Font("Segoe Script", Font.ITALIC, 16));
		panel_1.add(comboBox);
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		panel_1.add(btnVoltar);
	}
}
	