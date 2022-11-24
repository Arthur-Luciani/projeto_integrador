package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.UsuarioDao;
import model.Usuario;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private static TelaLogin frame;
	private JPasswordField txtPassSenha;

	/**
	 * Launch the application.
	 */
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaLogin();
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
	public TelaLogin() {
		
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
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblLoginTitulo = new JLabel("Login");
		lblLoginTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginTitulo.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		lblLoginTitulo.setForeground(new Color(255, 255, 255));
		lblLoginTitulo.setBackground(new Color(85, 107, 47));
		panel.add(lblLoginTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{150, 81, 0, 104, 207, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{61, 36, 36, 34, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
						
								JLabel lblLogin = new JLabel("Login");
								lblLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
								GridBagConstraints gbc_lblLogin = new GridBagConstraints();
								gbc_lblLogin.fill = GridBagConstraints.HORIZONTAL;
								gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
								gbc_lblLogin.gridx = 2;
								gbc_lblLogin.gridy = 1;
								panel_1.add(lblLogin, gbc_lblLogin);
				
						txtLogin = new JTextField();
						txtLogin.setHorizontalAlignment(SwingConstants.LEFT);
						txtLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
						GridBagConstraints gbc_txtLogin = new GridBagConstraints();
						gbc_txtLogin.fill = GridBagConstraints.BOTH;
						gbc_txtLogin.insets = new Insets(0, 0, 5, 5);
						gbc_txtLogin.gridx = 4;
						gbc_txtLogin.gridy = 1;
						panel_1.add(txtLogin, gbc_txtLogin);
						txtLogin.setColumns(10);
														
																JLabel lblSenha = new JLabel("Senha");
																lblSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
																GridBagConstraints gbc_lblSenha = new GridBagConstraints();
																gbc_lblSenha.anchor = GridBagConstraints.NORTH;
																gbc_lblSenha.fill = GridBagConstraints.HORIZONTAL;
																gbc_lblSenha.insets = new Insets(0, 0, 0, 5);
																gbc_lblSenha.gridx = 2;
																gbc_lblSenha.gridy = 3;
																panel_1.add(lblSenha, gbc_lblSenha);
												
														txtPassSenha = new JPasswordField();
														GridBagConstraints gbc_txtPassSenha = new GridBagConstraints();
														gbc_txtPassSenha.insets = new Insets(0, 0, 0, 5);
														gbc_txtPassSenha.fill = GridBagConstraints.BOTH;
														gbc_txtPassSenha.gridx = 4;
														gbc_txtPassSenha.gridy = 3;
														panel_1.add(txtPassSenha, gbc_txtPassSenha);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
								
						
		JButton btnEntrar = new JButton("Entrar");
		panel_2.add(btnEntrar);
		btnEntrar.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icons8-entrar-24.png")));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login = txtLogin.getText();
				String senha = txtPassSenha.getText();

				Usuario usuario = null;

				if (!login.isEmpty() && !senha.isEmpty()) {
					UsuarioDao usuarioDao;
					usuarioDao = new UsuarioDao();

					usuario = usuarioDao.verificacao(new Usuario(login, senha));

					if (usuario != null) {
						TelaInicial telaInicial = new TelaInicial(usuario);
						telaInicial.setVisible(true);
						dispose();
					} else {
						TelaMensagem telaMensagem = new TelaMensagem("Usuário ou senha inválidos");
						telaMensagem.setVisible(true);
					}

				}
			}
		});
		btnEntrar.setBackground(new Color(85, 107, 47));
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setFont(new Font("Segoe Script", Font.PLAIN, 16));
	}
}
