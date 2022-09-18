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

import dao.UsuarioDao;
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

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtDataNascimento;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JPasswordField txtConfSenha;
	private static TelaCadastroFuncionario frame;
	
	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	
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
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNome.setBorder(bordaNormal);
			}
		});
		txtNome.setBounds(320, 7, 263, 35);
		txtNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(65, 73, 197, 29);
		lblCpf.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblCpf);

		txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCpf.setBorder(bordaNormal);
			}
		});
		txtCpf.setBounds(320, 70, 263, 35);
		txtCpf.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblDataNascimento = new JLabel("Data de Nascimento");
		lblDataNascimento.setBounds(65, 139, 197, 26);
		lblDataNascimento.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		panel_1.add(lblDataNascimento);

		txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNascimento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataNascimento.setBorder(bordaNormal);
			}
		});
		txtDataNascimento.setBounds(320, 134, 263, 35);
		txtDataNascimento.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtDataNascimento.setColumns(10);
		panel_1.add(txtDataNascimento);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(65, 200, 197, 29);
		lblLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtLogin.setBorder(bordaNormal);
				txtLogin.setForeground(Color.DARK_GRAY);
			}
		});
		txtLogin.setBounds(320, 197, 263, 35);
		txtLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtLogin.setColumns(10);
		panel_1.add(txtLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(65, 264, 197, 29);
		lblSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblSenha);

		txtSenha = new JPasswordField();
		
		txtSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSenha.setBorder(bordaNormal);
			}
		});
		txtSenha.setBounds(320, 261, 263, 35);
		txtSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtSenha.setColumns(10);
		panel_1.add(txtSenha);

		JLabel lblConfSenha = new JLabel("Confirmar senha");
		lblConfSenha.setBounds(65, 327, 134, 29);
		lblConfSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblConfSenha);

		txtConfSenha = new JPasswordField();
		txtConfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtConfSenha.setBorder(bordaNormal);
			}
		});
		txtConfSenha.setBounds(320, 324, 263, 35);
		txtConfSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtConfSenha.setColumns(10);
		panel_1.add(txtConfSenha);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String nome = txtNome.getText();
				String cpf = txtCpf.getText();
				cpf = cpf.replaceAll("[^0-9]", "");
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				String confSenha = txtConfSenha.getText();
				String dataNascimentoStr = txtDataNascimento.getText();
				LocalDate dataNascimento = null;	
				DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				try {
					dataNascimento = LocalDate.parse(txtDataNascimento.getText(), formatacao);
				} catch (DateTimeException e2) {
					txtDataNascimento.setBorder(bordaVermelha);
				}
				
				if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || confSenha.isEmpty() || dataNascimentoStr.equals("  /  /    ") || senha.equals(confSenha)!=true || ValidaCPF.isCPF(cpf)!=true) {
					if (nome.isEmpty()) {
						txtNome.setBorder(bordaVermelha);
					}
					if (login.isEmpty()) {
						txtLogin.setBorder(bordaVermelha);
					}
					if (senha.isEmpty()) {
						txtSenha.setBorder(bordaVermelha);
					}
					if (confSenha.isEmpty()) {
						txtConfSenha.setBorder(bordaVermelha);
					}
					if (txtSenha.getText().equals(txtConfSenha.getText()) != true) {
						TelaMensagem m = new TelaMensagem("As senhas não coincidem");
						m.setVisible(true);
					}
					if (ValidaCPF.isCPF(cpf) != true) {
						txtCpf.setBorder(bordaVermelha);
					}
					
				} else {
					Usuario novoUsuario = new Usuario(login, nome, senha, LocalDate.parse(dataNascimentoStr, formatacao), cpf);
					try {
						UsuarioDao dao = new UsuarioDao();
						if (dao.cadastro(novoUsuario)== true) {
							TelaLogin telalogin = new TelaLogin();
							telalogin.setVisible(true);
							dispose();
						} else {
							TelaMensagem m = new TelaMensagem("Login j� utilizado");
							m.setVisible(true);
							txtLogin.setForeground(new Color(255, 0, 0));
						}		
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						TelaMensagem m = new TelaMensagem("Não foi possível cadastrar o usuário");
						m.setVisible(true);
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
