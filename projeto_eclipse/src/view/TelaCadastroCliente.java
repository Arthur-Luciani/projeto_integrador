package view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.ClienteDao;
import dao.UsuarioDao;
import model.AtualizacaoProduto;
import model.Cliente;
import model.DadosCadastroVenda;
import model.Estado;
import model.Usuario;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import validacoes.ValidaCPF;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtDataNascimento;
	private JTextField txtEmail;
	private static TelaCadastroCliente frame;

	private static Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
	private static Border bordaNormal = BorderFactory.createLineBorder(Color.GRAY);
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtCep;
	private JTextField txtCPF;

	/**
	 * Create the frame.
	 * 
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public TelaCadastroCliente(LinkedList<Estado> listaEstados, boolean depoisDaLista, Object objeto, Usuario usuarioLogado )  {
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

		JLabel lblCadastro = new JLabel("Cadastro Cliente");
		lblCadastro.setForeground(Color.WHITE);
		lblCadastro.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblCadastro);

		JPanel panel_lbl = new JPanel();
		panel_lbl.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_lbl, BorderLayout.WEST);
		panel_lbl.setLayout(new GridLayout(10, 0, 0, 0));

		JPanel lbl1 = new JPanel();
		lbl1.setBackground(new Color(240, 255, 240));
		panel_lbl.add(lbl1);
		
		JLabel lblNewLabel_3 = new JLabel("                      ");
		lbl1.add(lblNewLabel_3);

		JPanel lbl3 = new JPanel();
		lbl3.setBackground(new Color(240, 255, 240));
		panel_lbl.add(lbl3);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		panel_lbl.add(panel_2);

		JPanel lbl4 = new JPanel();
		lbl4.setBackground(new Color(240, 255, 240));
		panel_lbl.add(lbl4);

		JPanel lbl6 = new JPanel();
		lbl6.setBackground(new Color(240, 255, 240));
		panel_lbl.add(lbl6);

		JPanel lbl7 = new JPanel();
		lbl7.setBackground(new Color(240, 255, 240));
		panel_lbl.add(lbl7);

		JPanel lbl8 = new JPanel();
		lbl8.setBackground(new Color(240, 255, 240));
		panel_lbl.add(lbl8);

		JPanel lbl9 = new JPanel();
		lbl9.setBackground(new Color(240, 255, 240));
		panel_lbl.add(lbl9);

		JPanel panel_txt = new JPanel();
		panel_txt.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_txt, BorderLayout.CENTER);

		JPanel t1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) t1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		t1.setBackground(new Color(240, 255, 240));
		panel_txt.add(t1);
		
				JLabel lblNome = new JLabel("Nome");
				t1.add(lblNome);
				lblNome.setBounds(65, 10, 46, 29);
				lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel t3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) t3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		t3.setBackground(new Color(240, 255, 240));
		panel_txt.add(t3);

		txtNome = new JTextField();
		t3.add(txtNome);
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNome.setBorder(bordaNormal);
			}
		});
		txtNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtNome.setColumns(10);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_3.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		panel_3.setBackground(new Color(240, 255, 240));
		panel_txt.add(panel_3);
		
				JLabel lblDataNascimento = new JLabel("Data de Nascimento");
				panel_3.add(lblDataNascimento);
				lblDataNascimento.setBounds(65, 139, 197, 26);
				lblDataNascimento.setFont(new Font("Segoe Script", Font.PLAIN, 16));

		JPanel t4 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) t4.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		t4.setBackground(new Color(240, 255, 240));
		panel_txt.add(t4);
		try {
			txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		t4.add(txtDataNascimento);
		txtDataNascimento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataNascimento.setBorder(bordaNormal);
			}
		});
		txtDataNascimento.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtDataNascimento.setColumns(10);

		JPanel t5 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) t5.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		t5.setBackground(new Color(240, 255, 240));
		panel_txt.add(t5);
		
				JLabel lblNewLabel_2 = new JLabel("CPF");
				t5.add(lblNewLabel_2);
				lblNewLabel_2.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel t6 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) t6.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		t6.setBackground(new Color(240, 255, 240));
		panel_txt.add(t6);

		txtCPF = new JTextField();
		t6.add(txtCPF);
		txtCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCPF.setBorder(bordaNormal);
			}
		});
		txtCPF.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtCPF.setColumns(10);

		JPanel t7 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) t7.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		t7.setBackground(new Color(240, 255, 240));
		panel_txt.add(t7);
		
				JLabel lblEmail = new JLabel("Email");
				t7.add(lblEmail);
				lblEmail.setBounds(65, 200, 197, 29);
				lblEmail.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel t8 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) t8.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		t8.setBackground(new Color(240, 255, 240));
		panel_txt.add(t8);

		txtEmail = new JTextField();
		t8.add(txtEmail);
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEmail.setBorder(bordaNormal);
				txtEmail.setForeground(Color.DARK_GRAY);
			}
		});
		txtEmail.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtEmail.setColumns(10);

		JPanel t9 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) t9.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		t9.setBackground(new Color(240, 255, 240));
		panel_txt.add(t9);
		
				JPanel lbl5 = new JPanel();
				t9.add(lbl5);
				lbl5.setBackground(new Color(240, 255, 240));
				
						JLabel lblRua = new JLabel("Rua");
						lblRua.setFont(new Font("Segoe Print", Font.PLAIN, 16));
						lbl5.add(lblRua);

		String[] arrayEstados = new String[listaEstados.size()];
		for (int i = 0; i < arrayEstados.length; i++) {
			Estado estado = listaEstados.get(i);

			arrayEstados[i] = estado.getNomeEstado();
		}
		panel_txt.setLayout(new GridLayout(10, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		panel_lbl.add(panel_1);

		JPanel t10 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) t10.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		t10.setBackground(new Color(240, 255, 240));
		panel_txt.add(t10);

		txtRua = new JTextField();
		t10.add(txtRua);
		txtRua.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtRua.setBorder(bordaNormal);
			}
		});
		txtRua.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtRua.setColumns(10);

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_18 = (FlowLayout) panel_5.getLayout();
		flowLayout_18.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(new Color(240, 255, 240));
		panel_txt.add(panel_5);
		
				JLabel lblBairro = new JLabel("Bairro");
				panel_5.add(lblBairro);
				lblBairro.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_13 = (FlowLayout) panel_6.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		panel_txt.add(panel_6);

		txtBairro = new JTextField();
		panel_6.add(txtBairro);
		txtBairro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBairro.setBorder(bordaNormal);
			}
		});
		txtBairro.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtBairro.setColumns(10);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_19 = (FlowLayout) panel_7.getLayout();
		flowLayout_19.setAlignment(FlowLayout.LEFT);
		panel_7.setBackground(new Color(240, 255, 240));
		panel_txt.add(panel_7);
		
				JLabel lblCidade = new JLabel("Cidade");
				panel_7.add(lblCidade);
				lblCidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_14 = (FlowLayout) panel_8.getLayout();
		flowLayout_14.setAlignment(FlowLayout.LEFT);
		panel_txt.add(panel_8);

		txtCidade = new JTextField();
		panel_8.add(txtCidade);
		txtCidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCidade.setBorder(bordaNormal);
			}
		});
		txtCidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtCidade.setColumns(10);

		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_20 = (FlowLayout) panel_9.getLayout();
		flowLayout_20.setAlignment(FlowLayout.LEFT);
		panel_9.setBackground(new Color(240, 255, 240));
		panel_txt.add(panel_9);
		
				JLabel lblNumero = new JLabel("Numero");
				panel_9.add(lblNumero);
				lblNumero.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_15 = (FlowLayout) panel_10.getLayout();
		flowLayout_15.setAlignment(FlowLayout.LEFT);
		panel_txt.add(panel_10);

		txtNumero = new JTextField();
		panel_10.add(txtNumero);
		txtNumero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNumero.setBorder(bordaNormal);
			}
		});
		txtNumero.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtNumero.setColumns(10);

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_21 = (FlowLayout) panel_11.getLayout();
		flowLayout_21.setAlignment(FlowLayout.LEFT);
		panel_11.setBackground(new Color(240, 255, 240));
		panel_txt.add(panel_11);
		
				JLabel lblNewLabel = new JLabel("Estado");
				panel_11.add(lblNewLabel);
				lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_16 = (FlowLayout) panel_12.getLayout();
		flowLayout_16.setAlignment(FlowLayout.LEFT);
		panel_txt.add(panel_12);

		JComboBox cbEstados = new JComboBox(arrayEstados);
		cbEstados.setBackground(new Color(85, 107, 47));
		panel_12.add(cbEstados);
		cbEstados.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_22 = (FlowLayout) panel_13.getLayout();
		flowLayout_22.setAlignment(FlowLayout.LEFT);
		panel_13.setBackground(new Color(240, 255, 240));
		panel_txt.add(panel_13);
		
				JLabel lblNewLabel_1 = new JLabel("CEP");
				panel_13.add(lblNewLabel_1);
				lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_17 = (FlowLayout) panel_14.getLayout();
		flowLayout_17.setAlignment(FlowLayout.LEFT);
		panel_txt.add(panel_14);

		txtCep = new JTextField();
		panel_14.add(txtCep);
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCep.setBorder(bordaNormal);
			}
		});
		txtCep.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		txtCep.setColumns(10);

		JPanel panel_botoes = new JPanel();
		contentPane.add(panel_botoes, BorderLayout.SOUTH);
		panel_botoes.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel p_esquerda = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) p_esquerda.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		p_esquerda.setBackground(new Color(240, 255, 240));
		panel_botoes.add(p_esquerda);

		JButton btnNewButton = new JButton("Voltar");
		p_esquerda.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDao dao = new ClienteDao();
				LinkedList<Cliente> listaCliente = dao.resgatarCliente();
				if (depoisDaLista) {
					TelaListaClientes cadastros = new TelaListaClientes(listaCliente, usuarioLogado);
					cadastros.setVisible(true);
					cadastros.setLocationRelativeTo(null);
					dispose();
				} else {
					DadosCadastroVenda dados = (DadosCadastroVenda) objeto;

					TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(dados.getListaProdutosVendidos(),
							dados.getListaNomesUsuarios(), listaCliente, usuarioLogado);
					telaCadastroVenda.atualizarJTable(dados.getListaProdutosVendidos());
					telaCadastroVenda.atualizarComboBox(dados.getClienteSelecionado(), dados.getUsuarioSelecionado());
					telaCadastroVenda.setVisible(true);
					telaCadastroVenda.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_11 = (FlowLayout) panel_4.getLayout();
		flowLayout_11.setAlignment(FlowLayout.RIGHT);
		panel_botoes.add(panel_4);

		JButton btnCadastrar = new JButton("Cadastrar");
		panel_4.add(btnCadastrar);
		btnCadastrar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/images/icons8-mais-2-matemática-24.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				String dataNascimento = txtDataNascimento.getText();
				DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String cpf = txtCPF.getText();
				String email = txtEmail.getText();
				String rua = txtRua.getText();
				String bairro = txtBairro.getText();
				String cidade = txtCidade.getText();
				String numero = txtNumero.getText();
				Estado estado = listaEstados.get(cbEstados.getSelectedIndex());
				String idEstado = String.valueOf(estado.getIdEstado());
				String cep = txtCep.getText();

				if (nome.isEmpty() || dataNascimento.equals("  /  /    ") || cpf.isEmpty() || email.isEmpty()
						|| rua.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || numero.isEmpty()
						|| idEstado.isEmpty() || cep.isEmpty()) {
					if (nome.isEmpty()) {
						txtNome.setBorder(bordaVermelha);
					}
					if (dataNascimento.equals("  /  /    ")) {
						txtDataNascimento.setBorder(bordaVermelha);
					}
					if (cpf.isEmpty()) {
						txtCPF.setBorder(bordaVermelha);
					}
					if (email.isEmpty()) {
						txtEmail.setBorder(bordaVermelha);
					}
					if (rua.isEmpty()) {
						txtRua.setBorder(bordaVermelha);
					}
					if (bairro.isEmpty()) {
						txtBairro.setBorder(bordaVermelha);
					}
					if (cidade.isEmpty()) {
						txtCidade.setBorder(bordaVermelha);
					}
					if (numero.isEmpty()) {
						txtNumero.setBorder(bordaVermelha);
					}
					if (idEstado.isEmpty()) {
						cbEstados.setBorder(bordaVermelha);
					}
					if (cep.isEmpty()) {
						txtCep.setBorder(bordaVermelha);
					}
				} else {
					LocalDate dataNascimentoLD = LocalDate.parse(dataNascimento, formatacao);
					int NumeroInt = Integer.parseInt(numero);
					int idEstadoInt = Integer.parseInt(idEstado);

					Cliente cliente = new Cliente(0, nome, cpf, email, dataNascimentoLD, rua, bairro, cidade, cep,
							idEstadoInt, NumeroInt);

					ClienteDao dao = new ClienteDao();
					dao.cadastrarCliente(cliente);
					LinkedList<Cliente> listaCliente = dao.resgatarCliente();

					if (depoisDaLista) {
						TelaListaClientes cadastros = new TelaListaClientes(listaCliente, usuarioLogado);
						cadastros.setVisible(true);
						cadastros.setLocationRelativeTo(null);
						dispose();
					} else {
						DadosCadastroVenda dados = (DadosCadastroVenda) objeto;

						TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda(dados.getListaProdutosVendidos(),
								dados.getListaNomesUsuarios(), listaCliente, usuarioLogado);
						telaCadastroVenda.atualizarJTable(dados.getListaProdutosVendidos());
						telaCadastroVenda.atualizarComboBox(dados.getClienteSelecionado(),
								dados.getUsuarioSelecionado());
						telaCadastroVenda.setVisible(true);
						telaCadastroVenda.setLocationRelativeTo(null);
						dispose();
					}

				}

			}
		});
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnCadastrar.setBackground(new Color(85, 107, 47));
	}
}
