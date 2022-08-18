package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 674, 99);
		panel.setBackground(new Color(85, 107, 47));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setForeground(Color.WHITE);
		contentPane.add(panel);
		
		JLabel lblViridiSinus = new JLabel("Viridi Sinus");
		lblViridiSinus.setForeground(Color.WHITE);
		lblViridiSinus.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblViridiSinus);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEstoque.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnEstoque.setBackground(new Color(85, 107, 47));
		btnEstoque.setForeground(new Color(255, 255, 255));
		btnEstoque.setBounds(25, 203, 199, 85);
		contentPane.add(btnEstoque);
		
		JButton btnNewButton = new JButton("Rel\u00E1t\u00F3rios");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setBounds(245, 203, 199, 85);
		contentPane.add(btnNewButton);
		
		JButton btnCadastroDeClientes = new JButton("Cadastro de Clientes");
		btnCadastroDeClientes.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnCadastroDeClientes.setBackground(new Color(85, 107, 47));
		btnCadastroDeClientes.setForeground(new Color(255, 255, 255));
		btnCadastroDeClientes.setBounds(461, 203, 199, 85);
		contentPane.add(btnCadastroDeClientes);
	}
}
