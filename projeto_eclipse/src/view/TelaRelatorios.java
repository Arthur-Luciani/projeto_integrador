package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.Border;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import java.awt.Canvas;

public class TelaRelatorios extends JFrame {
	private JButton btnVendaPeriodo;

	/**
	 * Create the frame.
	 */
	public TelaRelatorios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(new Color(85, 107, 47));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Relatórios");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_7);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_1 = (FlowLayout) panel_9.getLayout();
		flowLayout_1.setVgap(50);
		panel_1.add(panel_9);
		
		btnVendaPeriodo = new JButton("Reatório venda por período");
		btnVendaPeriodo.setForeground(new Color(255, 255, 255));
		btnVendaPeriodo.setBackground(new Color(85, 107, 47));
		btnVendaPeriodo.setBounds(new Rectangle(58, 202, 180, 58));
		ImageIcon iconSalvar = new ImageIcon("file:///C:/Users/Aluno/Documents/projeto_integrador/projeto_eclipse/src/Imagens/folha-de-papel.png");
		iconSalvar.setImage(iconSalvar.getImage().getScaledInstance(15, 15, 15));
		btnVendaPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_9.add(btnVendaPeriodo);
		btnVendaPeriodo.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(240, 255, 240));
		panel_1.add(panel_11);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial inicio = new TelaInicial(null);
				inicio.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_11.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_3 = (FlowLayout) panel_17.getLayout();
		flowLayout_3.setVgap(50);
		panel_2.add(panel_17);
		
		JButton btnNewButton_2 = new JButton("Relatório de comissão");
		btnNewButton_2.setBackground(new Color(85, 107, 47));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_17.add(btnNewButton_2);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_18);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(new Color(240, 255, 240));
		panel_2.add(panel_19);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_6);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(240, 255, 240));
		FlowLayout flowLayout_2 = (FlowLayout) panel_13.getLayout();
		flowLayout_2.setVgap(50);
		panel_3.add(panel_13);
		
		JButton btnNewButton_1 = new JButton("Relatório de produtos mais vendidos");
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		panel_13.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(240, 255, 240));
		panel_3.add(panel_15);
		
	}

	public Border getBtnVendaPeriodoBorder() {
		return btnVendaPeriodo.getBorder();
	}
	public void setBtnVendaPeriodoBorder(Border border) {
		btnVendaPeriodo.setBorder(border);
	}
}
