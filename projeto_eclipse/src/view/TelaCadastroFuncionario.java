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
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
	 * Create the frame.
	 * 
	 */
	public TelaCadastroFuncionario(Usuario usuarioLogado) {
		setBackground(new Color(240, 255, 240));
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
		panel.setForeground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setForeground(Color.WHITE);
		lblCadastro.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblCadastro);
		
		
		
		JPanel panel_9 = new JPanel();
		contentPane.add(panel_9, BorderLayout.WEST);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(240, 255, 240));
		panel_9.add(panel_10);
		
		JPanel pEsquerda = new JPanel();
		pEsquerda.setBackground(new Color(240, 255, 240));
		panel_9.add(pEsquerda);
		pEsquerda.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBackground(new Color(240, 255, 240));
		pEsquerda.add(lblNome);
		lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBackground(new Color(240, 255, 240));
		pEsquerda.add(lblCpf);
		lblCpf.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JLabel lblDataNascimento = new JLabel("Data de Nascimento");
		lblDataNascimento.setBackground(new Color(240, 255, 240));
		pEsquerda.add(lblDataNascimento);
		lblDataNascimento.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBackground(new Color(240, 255, 240));
		pEsquerda.add(lblLogin);
		lblLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBackground(new Color(240, 255, 240));
		pEsquerda.add(lblSenha);
		lblSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
				
		JLabel lblConfSenha = new JLabel("Confirmar senha");
		lblConfSenha.setBackground(new Color(240, 255, 240));
		pEsquerda.add(lblConfSenha);
		lblConfSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JLabel lblFuncao = new JLabel("Fun\u00E7\u00E3o");
		lblFuncao.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		pEsquerda.add(lblFuncao);

		JPanel pDireita = new JPanel();
		pDireita.setBackground(new Color(240, 255, 240));
		contentPane.add(pDireita);
		pDireita.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.setBackground(new Color(240, 255, 240));
		pDireita.add(panel_3);

		txtNome = new JTextField();
		panel_3.add(txtNome);
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNome.setBorder(bordaNormal);
			}
		});
		txtNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtNome.setColumns(15);
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(new Color(240, 255, 240));
		pDireita.add(panel_4);
		try {
			txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		panel_4.add(txtCpf);
		txtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCpf.setBorder(bordaNormal);
			}
		});
		txtCpf.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtCpf.setColumns(15);
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_5.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(new Color(240, 255, 240));
		pDireita.add(panel_5);
		try {
			txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_5.add(txtDataNascimento);
		txtDataNascimento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataNascimento.setBorder(bordaNormal);
			}
		});
		txtDataNascimento.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtDataNascimento.setColumns(15);
				
				JPanel panel_6 = new JPanel();
				FlowLayout flowLayout_6 = (FlowLayout) panel_6.getLayout();
				flowLayout_6.setAlignment(FlowLayout.LEFT);
				panel_6.setBackground(new Color(240, 255, 240));
				pDireita.add(panel_6);
		
				txtLogin = new JTextField();
				panel_6.add(txtLogin);
				txtLogin.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						txtLogin.setBorder(bordaNormal);
						txtLogin.setForeground(Color.DARK_GRAY);
					}
				});
				txtLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
				txtLogin.setColumns(15);
						
						JPanel panel_7 = new JPanel();
						FlowLayout flowLayout_7 = (FlowLayout) panel_7.getLayout();
						flowLayout_7.setAlignment(FlowLayout.LEFT);
						panel_7.setBackground(new Color(240, 255, 240));
						pDireita.add(panel_7);
				
						txtSenha = new JPasswordField();
						panel_7.add(txtSenha);
						
						txtSenha.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent e) {
								txtSenha.setBorder(bordaNormal);
							}
						});
						txtSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
						txtSenha.setColumns(15);
								
								JPanel panel_8 = new JPanel();
								FlowLayout flowLayout_8 = (FlowLayout) panel_8.getLayout();
								flowLayout_8.setAlignment(FlowLayout.LEFT);
								panel_8.setBackground(new Color(240, 255, 240));
								pDireita.add(panel_8);
								
										txtConfSenha = new JPasswordField();
										panel_8.add(txtConfSenha);
										txtConfSenha.addFocusListener(new FocusAdapter() {
											@Override
											public void focusGained(FocusEvent e) {
												txtConfSenha.setBorder(bordaNormal);
											}
										});
										txtConfSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
										txtConfSenha.setColumns(15);
								
								JPanel panel_1 = new JPanel();
								FlowLayout flowLayout_9 = (FlowLayout) panel_1.getLayout();
								flowLayout_9.setAlignment(FlowLayout.LEFT);
								panel_1.setBackground(new Color(240, 255, 240));
								pDireita.add(panel_1);
								
								JComboBox cbPermissao = new JComboBox();
								cbPermissao.setFont(new Font("Tahoma", Font.PLAIN, 16));
								cbPermissao.setModel(new DefaultComboBoxModel(new String[] {"Vendedor", "Administrador"}));
								panel_1.add(cbPermissao);
								
								JPanel panel_2 = new JPanel();
								contentPane.add(panel_2, BorderLayout.SOUTH);
								panel_2.setLayout(new GridLayout(0, 2, 0, 0));
								
								JPanel pbtnEsquerda = new JPanel();
								FlowLayout flowLayout_1 = (FlowLayout) pbtnEsquerda.getLayout();
								flowLayout_1.setAlignment(FlowLayout.LEFT);
								pbtnEsquerda.setBackground(new Color(240, 255, 240));
								panel_2.add(pbtnEsquerda);
								
								JPanel pbtnDireita = new JPanel();
								FlowLayout flowLayout_2 = (FlowLayout) pbtnDireita.getLayout();
								flowLayout_2.setAlignment(FlowLayout.RIGHT);
								pbtnDireita.setBackground(new Color(240, 255, 240));
								panel_2.add(pbtnDireita);
		
		
		
		
		
		
		
		
		
		
		
		

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(TelaCadastroFuncionario.class.getResource("/images/icons8-usuário-homem-com-círculo-24.png")));
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
				boolean permissao = false;
				if (cbPermissao.getSelectedIndex()==0) {
					permissao = false;
				} else if (cbPermissao.getSelectedIndex()==1) {
					permissao = true;
				} 
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
					Usuario novoUsuario = new Usuario(login, nome, senha, LocalDate.parse(dataNascimentoStr, formatacao), cpf, permissao);
					UsuarioDao dao = new UsuarioDao();
					if (dao.cadastro(novoUsuario)== true) {
						TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios(dao.resgatarUsuarios(), usuarioLogado);
						telaListaFuncionarios.setVisible(true);
						dispose();
					} else {
						TelaMensagem m = new TelaMensagem("Login j� utilizado");
						m.setVisible(true);
						txtLogin.setForeground(new Color(255, 0, 0));
					}
				}
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(TelaCadastroFuncionario.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDao dao = new UsuarioDao();
				TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios(dao.resgatarUsuarios(), usuarioLogado);
				telaListaFuncionarios.setVisible(true);
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
