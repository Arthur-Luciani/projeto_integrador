package view;
 
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClienteDao;
import dao.EstadoDao;
import dao.UsuarioDao;
import model.Cliente;
import model.Estado;
import model.Usuario;

public class TelaInicial extends JFrame {
	private Usuario usuario;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial(null);
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
	public TelaInicial(Usuario usuario) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBounds(89, 276, 142, 39);
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaEstoque telaEstoque = new TelaEstoque();
				telaEstoque.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		btnEstoque.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnEstoque.setBackground(new Color(85, 107, 47));
		btnEstoque.setForeground(new Color(255, 255, 255));
		contentPane.add(btnEstoque);

		JButton btnRelatorios = new JButton("Rel\u00E1t\u00F3rios");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorios telaRelatorios = new TelaRelatorios();
				telaRelatorios.setVisible(true);
				dispose();
			}
		});
		btnRelatorios.setBounds(328, 276, 170, 39);
		btnRelatorios.setForeground(new Color(255, 255, 255));
		btnRelatorios.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnRelatorios.setBackground(new Color(85, 107, 47));
		contentPane.add(btnRelatorios);

		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 824, 99);
		panel.setBackground(new Color(85, 107, 47));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setForeground(Color.WHITE);
		contentPane.add(panel);

		JLabel lblViridiSinus = new JLabel("Viridi Sinus");
		lblViridiSinus.setForeground(Color.WHITE);
		lblViridiSinus.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblViridiSinus);

		JButton btnCadastroDeClientes = new JButton("Cadastro de Clientes");
		btnCadastroDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDao dao = new ClienteDao();
				LinkedList<Cliente> listaCliente = dao.resgatarCliente();
				TelaListaClientes cadastros = new TelaListaClientes(listaCliente);
				cadastros.setVisible(true);
				dispose();
			}
		});
		
		btnCadastroDeClientes.setBounds(578, 276, 232, 39);
		btnCadastroDeClientes.setToolTipText("");
		btnCadastroDeClientes.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnCadastroDeClientes.setBackground(new Color(85, 107, 47));
		btnCadastroDeClientes.setForeground(new Color(255, 255, 255));
		contentPane.add(btnCadastroDeClientes);

		JLabel label = new JLabel("");
		label.setBounds(839, 257, 0, 0);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(839, 257, 0, 0);
		contentPane.add(label_1);	
		
		JButton btnFuncionarios = new JButton("Funcionários");		
		/*if (usuario.isPermissao()) {
			btnFuncionarios.setEnabled(true);
		} else {
			btnFuncionarios.setEnabled(false);
		}*/
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDao dao = new UsuarioDao();
				TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios(dao.resgatarUsuarios());
				telaListaFuncionarios.setVisible(true);
				dispose();
			}
		});
		btnFuncionarios.setForeground(Color.WHITE);
		btnFuncionarios.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnFuncionarios.setBackground(new Color(85, 107, 47));
		btnFuncionarios.setBounds(668, 439, 142, 39);
		contentPane.add(btnFuncionarios);

	}
}
