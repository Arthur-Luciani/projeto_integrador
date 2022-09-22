package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ProdutoDao;
import model.Produto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaEstoque extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstoque frame = new TelaEstoque();
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
	public TelaEstoque() {
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

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setForeground(Color.WHITE);
		lblEstoque.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblEstoque);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setBackground(new Color(85, 107, 47));
		btnProdutos.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnProdutos.setForeground(new Color(255, 255, 255));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListaProdutos telaListaProdutos = new TelaListaProdutos();
				telaListaProdutos.setVisible(true);
				dispose();
			}
		});
		btnProdutos.setBounds(94, 295, 183, 58);
		contentPane.add(btnProdutos);
		
		JButton btnVenda = new JButton("Venda");
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroVenda telaVenda;
				try {
					telaVenda = new TelaCadastroVenda();
					telaVenda.setVisible(true);
					dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVenda.setForeground(Color.WHITE);
		btnVenda.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVenda.setBackground(new Color(85, 107, 47));
		btnVenda.setBounds(336, 295, 183, 58);
		contentPane.add(btnVenda);
		
		JButton btnFornecedores = new JButton("Fornecedores");
		btnFornecedores.setForeground(Color.WHITE);
		btnFornecedores.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnFornecedores.setBackground(new Color(85, 107, 47));
		btnFornecedores.setBounds(571, 295, 183, 58);
		contentPane.add(btnFornecedores);

	}
}
