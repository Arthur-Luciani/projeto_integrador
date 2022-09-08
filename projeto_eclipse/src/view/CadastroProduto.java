package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto();
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
	public CadastroProduto() {
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
		
		JLabel lblNewLabel = new JLabel("Atualizar/Adicionar");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel_1.add(lblNewLabel);
		
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
		panel_3.setLayout(new MigLayout("", "[][][][][grow]", "[][]"));
		
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_3.add(lblPreco, "cell 2 1");
		
		JTextField txtPreco = new JTextField();
		panel_3.add(txtPreco, "cell 4 1,growx");
		txtPreco.setColumns(10);
		
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
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_4.add(btnNewButton_1, "cell 3 1");
		
		JButton btnNewButton = new JButton("Atualizar/Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				String id = txtId.getText();
				String preco = txtPreco.getText();
				
				int idInt = Integer.parseInt(id);
				float precoFloat = Float.parseFloat(preco);
				
				if (nome.isEmpty() || id.isEmpty() || preco.isEmpty()) {
					if (nome.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nenhuma informação preenchida para 'Nome'");
					}
					if(id.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nenhuma informação preenchida para 'Código'");
					}if(preco.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nenhuma informação preenchida para 'Nome'");
					}
				}else {
					TelaListaProdutos telaListaProdutos = new TelaListaProdutos();
					
					telaListaProdutos.setVisible(true);
					
					
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_4.add(btnNewButton, "cell 24 1");
	}

}
