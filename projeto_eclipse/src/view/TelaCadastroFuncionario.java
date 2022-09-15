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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.UsuarioDao;
import model.Usuario;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtDataNascimento;
	private JTextField txtLogin;
	private JTextField txtSenha;
	private JTextField txtConfSenha;
	private static TelaCadastroFuncionario frame;

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
	public TelaCadastroFuncionario() throws ParseException {
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

		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setForeground(Color.WHITE);
		lblCadastro.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblCadastro);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		panel_1.setBounds(20, 104, 814, 407);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(65, 10, 46, 29);
		lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(320, 7, 263, 35);
		txtNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(65, 73, 31, 29);
		lblCpf.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblCpf);

		txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCpf.setBounds(320, 70, 263, 35);
		txtCpf.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblDataNascimento = new JLabel("Data de Nascimento");
		lblDataNascimento.setBounds(65, 139, 170, 26);
		lblDataNascimento.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		panel_1.add(lblDataNascimento);

		txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNascimento.setBounds(320, 134, 263, 35);
		txtDataNascimento.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtDataNascimento.setColumns(10);
		panel_1.add(txtDataNascimento);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(65, 200, 44, 29);
		lblLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setBounds(320, 197, 263, 35);
		txtLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtLogin.setColumns(10);
		panel_1.add(txtLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(65, 264, 48, 29);
		lblSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblSenha);

		txtSenha = new JTextField();
		txtSenha.setBounds(320, 261, 263, 35);
		txtSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtSenha.setColumns(10);
		panel_1.add(txtSenha);

		JLabel lblConfSenha = new JLabel("Confirmar senha");
		lblConfSenha.setBounds(65, 327, 134, 29);
		lblConfSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblConfSenha);

		txtConfSenha = new JTextField();
		txtConfSenha.setBounds(320, 324, 263, 35);
		txtConfSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtConfSenha.setColumns(10);
		panel_1.add(txtConfSenha);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				String cpf = txtCpf.getText();
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				String confSenha = txtConfSenha.getText();
				DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText(), formatacao);

				if (nome.isEmpty() || cpf.isEmpty() || login.isEmpty() || senha.isEmpty() || confSenha.isEmpty()) {
					if (nome.isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Nome'");
						m.setVisible(true);
					}
					if (cpf.isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Cpf'");
						m.setVisible(true);
					}
					if (login.isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Login'");
						m.setVisible(true);
					}
					if (senha.isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Senha'");
						m.setVisible(true);
					}
					if (confSenha.isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Confirmar senha'");
						m.setVisible(true);
					}
					if (txtDataNascimento.getText().isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Data de nascimento'");
						m.setVisible(true);
					}

				} else if (nome.isEmpty() && cpf.isEmpty() && login.isEmpty() && senha.isEmpty()
						&& confSenha.isEmpty()) {
					TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida");
					m.setVisible(true);
				} else {
					if (senha.equals(confSenha) != true) {
						TelaMensagem m = new TelaMensagem("As senhas não coincidem");
						m.setVisible(true);
					} else {
						Usuario novoUsuario = new Usuario(login, nome, senha, dataNascimento, cpf);
						try {
							UsuarioDao dao = new UsuarioDao();
							dao.cadastro(novoUsuario);
							TelaLogin telalogin = new TelaLogin();
							telalogin.setVisible(true);
							dispose();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							TelaMensagem m = new TelaMensagem("Não foi possível cadastrar o usuário");
							m.setVisible(true);
						}

					}

				}

			}
		});
		panel_1.add(btnCadastrar);
		btnCadastrar.setBounds(616, 363, 191, 37);
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnCadastrar.setBackground(new Color(85, 107, 47));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		btnVoltar.setBounds(65, 367, 134, 37);
		panel_1.add(btnVoltar);

	}
}
