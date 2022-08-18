package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UsuarioDao;
import model.Usuario;

import java.awt.Color;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
		setBounds(100, 100, 700, 400);
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
		panel_1.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(150, 64, 44, 30);
		lblLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(233, 61, 217, 36);
		txtLogin.setHorizontalAlignment(SwingConstants.LEFT);
		txtLogin.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(150, 133, 48, 30);
		lblSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setHorizontalAlignment(SwingConstants.LEFT);
		txtSenha.setBounds(233, 130, 217, 36);
		txtSenha.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_1.add(txtSenha);
		txtSenha.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				
				Usuario usuario = new Usuario();
				usuario.setLogin(login);
				usuario.setSenha(senha);
				try {
					UsuarioDao usuarioDao = new UsuarioDao();
					if (usuarioDao.verificacao(usuario)==true) {
						//Ir para tela inicial
					} else {
						//Mensagem erro
					}
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		});
		btnEntrar.setBackground(new Color(85, 107, 47));
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		panel_2.add(btnEntrar);
	}

}
