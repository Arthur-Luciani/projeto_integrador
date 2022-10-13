package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EstadoDao;
import model.Estado;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class TelaCliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
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
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Cliente");
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 32));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(240, 255, 240));
		panel_3.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(new Color(240, 255, 240));
		panel_4.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setForeground(new Color(240, 255, 240));
		panel_5.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_5);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoDao estados = new EstadoDao();
				LinkedList<Estado> listaEstados = estados.resgatarEstados();
				TelaCadastroCliente cliente = new TelaCadastroCliente(listaEstados);
				cliente.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setForeground(new Color(255, 255, 255));
		panel_5.add(btnNewButton);
		
		JPanel panel_6 = new JPanel();
		panel_6.setForeground(new Color(240, 255, 240));
		panel_6.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_6);
		
		JButton btnNewButton_1 = new JButton("Consultar clientes cadastrados");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaClienteCadastros cadastros = new TelaClienteCadastros();
				cadastros.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		panel_6.add(btnNewButton_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setForeground(new Color(240, 255, 240));
		panel_7.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setForeground(new Color(240, 255, 240));
		panel_8.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_8);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		panel_2.setForeground(new Color(240, 255, 240));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial inicio = new TelaInicial(null);
				inicio.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnNewButton_2.setBackground(new Color(85, 107, 47));
		panel_2.add(btnNewButton_2);
	}

}
