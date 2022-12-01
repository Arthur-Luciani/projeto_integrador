package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
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
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TelaRelatorios extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	
	public TelaRelatorios(Usuario usuarioLogado) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				contentPane.setLayout(null);
		
				JPanel panel = new JPanel();
				panel.setBounds(3, 5, 823, 99);
				panel.setBackground(new Color(85, 107, 47));
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				panel.setForeground(Color.WHITE);
				contentPane.add(panel);
				
						JLabel lblRelatorios = new JLabel("Relatórios");
						lblRelatorios.setForeground(Color.WHITE);
						lblRelatorios.setFont(new Font("Segoe Print", Font.PLAIN, 50));
						panel.add(lblRelatorios);
		
		JButton btnProdutosVendidos = new JButton("Produtos");
		btnProdutosVendidos.setBounds(246, 267, 162, 37);
		btnProdutosVendidos.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/images/icons8-caixa-de-papelão-24.png")));
		btnProdutosVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRelatorioVendas telaRelatorioVendas = new TelaRelatorioVendas(usuarioLogado);
				telaRelatorioVendas.setVisible(true);
				telaRelatorioVendas.setLocationRelativeTo(null);
				dispose();
			}
		});
				
						JLabel label_1 = new JLabel("");
						label_1.setBounds(0, 0, 0, 0);
						contentPane.add(label_1);	
		
				JLabel label = new JLabel("");
				label.setBounds(0, 0, 0, 0);
				contentPane.add(label);
		btnProdutosVendidos.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnProdutosVendidos.setBackground(new Color(85, 107, 47));
		btnProdutosVendidos.setForeground(new Color(255, 255, 255));
		contentPane.add(btnProdutosVendidos);
		
				JButton btnComissao = new JButton("Comissão");
				btnComissao.setBounds(452, 267, 162, 37);
				btnComissao.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/images/icons8-usuário-homem-com-círculo-24.png")));
				btnComissao.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaRelatorioComissao comissao;
						try {
							comissao = new TelaRelatorioComissao(null, usuarioLogado);
							comissao.setVisible(true);
							comissao.setLocationRelativeTo(null);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
					}
				});
				btnComissao.setToolTipText("");
				btnComissao.setFont(new Font("Segoe Print", Font.PLAIN, 16));
				btnComissao.setBackground(new Color(85, 107, 47));
				btnComissao.setForeground(new Color(255, 255, 255));
				contentPane.add(btnComissao);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(3, 455, 188, 39);
		btnVoltar.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/images/icons8-à-esquerda-dentro-de-um-círculo-24.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial(usuarioLogado);
				telaInicial.setVisible(true);
				telaInicial.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnVoltar.setBackground(new Color(85, 107, 47));
		contentPane.add(btnVoltar);

		
		
	}
}
