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
import model.Cliente;
import model.Usuario;
import model.Venda;

public class TelaRelatorios extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TelaRelatorios(Usuario usuarioLogado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnProdutosVendidos = new JButton("Produtos");
		btnProdutosVendidos.setBounds(10, 276, 256, 39);
		btnProdutosVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRelatorioVendas telaRelatorioVendas = new TelaRelatorioVendas(usuarioLogado);
				telaRelatorioVendas.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		btnProdutosVendidos.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnProdutosVendidos.setBackground(new Color(85, 107, 47));
		btnProdutosVendidos.setForeground(new Color(255, 255, 255));
		contentPane.add(btnProdutosVendidos);
		
		JButton btnVendas = new JButton("Vendas");
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorioVendas vendas = new TelaRelatorioVendas(usuarioLogado);
				vendas.setVisible(true);
				dispose();
			}
		});
		btnVendas.setBounds(315, 276, 232, 39);
		btnVendas.setForeground(new Color(255, 255, 255));
		btnVendas.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVendas.setBackground(new Color(85, 107, 47));
		contentPane.add(btnVendas);

		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 824, 99);
		panel.setBackground(new Color(85, 107, 47));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setForeground(Color.WHITE);
		contentPane.add(panel);

		JLabel lblRelatorios = new JLabel("Relatórios");
		lblRelatorios.setForeground(Color.WHITE);
		lblRelatorios.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblRelatorios);

		JButton btnComissao = new JButton("Comissão");
		btnComissao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorioComissao comissao;
				try {
					comissao = new TelaRelatorioComissao(null, usuarioLogado);
					comissao.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		btnComissao.setBounds(592, 276, 232, 39);
		btnComissao.setToolTipText("");
		btnComissao.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnComissao.setBackground(new Color(85, 107, 47));
		btnComissao.setForeground(new Color(255, 255, 255));
		contentPane.add(btnComissao);

		JLabel label = new JLabel("");
		label.setBounds(839, 257, 0, 0);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(839, 257, 0, 0);
		contentPane.add(label_1);	
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial(usuarioLogado);
				telaInicial.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		btnVoltar.setBounds(10, 461, 140, 39);
		contentPane.add(btnVoltar);

		
		
	}
}
