package view;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.ClienteDao;
import dao.UsuarioDao;
import model.Cliente;
import model.Usuario;
import javax.swing.ImageIcon;


public class TelaInicial extends JFrame {
	private JPanel contentPane;
	private static TelaInicial frame;


	/**
	 * Launch the application.
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	

	/**
	 * Create the frame.
	 */
	public TelaInicial(Usuario usuarioLogado) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setIcon(new ImageIcon(TelaInicial.class.getResource("/images/icons8-mover-por-carrinho-24.png")));
		btnEstoque.setBounds(89, 276, 142, 39);
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaEstoque telaEstoque = new TelaEstoque(usuarioLogado);
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
		btnRelatorios.setIcon(new ImageIcon(TelaInicial.class.getResource("/images/icons8-editar-relatório-gráfico-24.png")));
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorios telaRelatorios = new TelaRelatorios(usuarioLogado);
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
		btnCadastroDeClientes.setIcon(new ImageIcon(TelaInicial.class.getResource("/images/icons8-usuário-homem-com-círculo-24.png")));
		btnCadastroDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDao dao = new ClienteDao();
				LinkedList<Cliente> listaCliente = dao.resgatarCliente();
				TelaListaClientes cadastros = new TelaListaClientes(listaCliente, usuarioLogado);
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
		if (usuarioLogado.isPermissao()) {
			btnFuncionarios.setEnabled(true);
			btnRelatorios.setEnabled(true);
		} else {
			btnFuncionarios.setEnabled(false);
			btnRelatorios.setEnabled(false);
		}
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDao dao = new UsuarioDao();
				TelaListaFuncionarios telaListaFuncionarios = new TelaListaFuncionarios(dao.resgatarUsuarios(), usuarioLogado);
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
