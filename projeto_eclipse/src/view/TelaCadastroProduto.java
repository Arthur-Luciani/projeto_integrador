package view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.ProdutoDao;
import model.Produto;
import net.miginfocom.swing.MigLayout;

public class TelaCadastroProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtId;
	private JTextField txtPreco;
	private JTextField txtQuantidade;
	private JTextField txtNomeFornecedor;


	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaCadastroProduto(Boolean atualizarCadastrar) throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(85, 107, 47));
		contentPane.add(panel_1);
		
		if (atualizarCadastrar == true) {
			JLabel lbAtualizaCadastrar = new JLabel("Adicionar");
			lbAtualizaCadastrar.setForeground(new Color(255, 255, 255));
			lbAtualizaCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 50));
			panel_1.add(lbAtualizaCadastrar);
		}	else {
			JLabel lbAtualizaCadastrar = new JLabel("Atualizar");
			lbAtualizaCadastrar.setForeground(new Color(255, 255, 255));
			lbAtualizaCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 50));
			panel_1.add(lbAtualizaCadastrar);
		}
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][grow]", "[][]"));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel.add(lblNome, "cell 2 1");
		
		txtNome = new JTextField();
		panel.add(txtNome, "cell 4 1,growx");
		txtNome.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][][][][grow]", "[][]"));
		
		JLabel lblId = new JLabel("Código:");
		lblId.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_2.add(lblId, "cell 2 1");
		
		txtId = new JTextField();
		panel_2.add(txtId, "cell 4 1,growx");
		txtId.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 240));
		panel_3.setForeground(new Color(240, 255, 240));
		contentPane.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[][][][][grow]", "[][][][]"));
		
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_3.add(lblPreco, "cell 2 1");
		
		txtPreco = new JTextField();
		panel_3.add(txtPreco, "cell 4 1,growx");
		txtPreco.setColumns(10);
		
		txtNomeFornecedor = new JTextField();
		txtNomeFornecedor.setColumns(10);
		panel_3.add(txtNomeFornecedor, "cell 4 2,growx");
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_3.add(lblQuantidade, "flowy,cell 2 3");
		
		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		panel_3.add(txtQuantidade, "cell 4 3,growx");
		
		JLabel lblNomeDoFornecedor = new JLabel("Nome do fornecedor");
		lblNomeDoFornecedor.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_3.add(lblNomeDoFornecedor, "cell 2 3");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		panel_4.setForeground(new Color(240, 255, 240));
		contentPane.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][]"));
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaProdutos telaListaProdutos = new TelaListaProdutos();
				
				telaListaProdutos.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_4.add(btnNewButton_1, "cell 3 1");
		
		JButton btnAtualizarCadastrar;
		if (atualizarCadastrar == true) {
			btnAtualizarCadastrar = new JButton("Adicionar");
		} else {
			btnAtualizarCadastrar = new JButton("Atualizar");
		}
		btnAtualizarCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtNome.getText().isEmpty() || txtId.getText().isEmpty() || txtPreco.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
					if (txtNome.getText().isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Nome'");
						m.setVisible(true);
					}
					if(txtId.getText().isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Código'");
						m.setVisible(true);
					}if(txtPreco.getText().isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Preço'");
						m.setVisible(true);
					}if (txtQuantidade.getText().isEmpty()) {
						TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida para 'Quantidade'");
						m.setVisible(true);
					}
				} else if (txtNome.getText().isEmpty() && txtId.getText().isEmpty() && txtPreco.getText().isEmpty()) {
					TelaMensagem m = new TelaMensagem("Nenhuma informação preenchida");
					m.setVisible(true);
				} else {
					
					String nome = txtNome.getText();
					int id = Integer.parseInt(txtId.getText());
					float preco = Float.parseFloat(txtPreco.getText());
					int quantidade = Integer.parseInt(txtQuantidade.getText());
					String forncecedorNome = txtNomeFornecedor.getText();
					
					
					Produto novoProduto = new Produto(nome, preco, id, quantidade, forncecedorNome);
					ProdutoDao dao;
					try {
						dao = new ProdutoDao();
						dao.cadastroProduto(novoProduto);
						
						TelaListaProdutos telaListaProdutos = new TelaListaProdutos();
						telaListaProdutos.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();	
					}
					
				}
				
			}
		});
		btnAtualizarCadastrar.setForeground(new Color(255, 255, 255));
		btnAtualizarCadastrar.setBackground(new Color(85, 107, 47));
		btnAtualizarCadastrar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_4.add(btnAtualizarCadastrar, "cell 24 1");
	}
}
