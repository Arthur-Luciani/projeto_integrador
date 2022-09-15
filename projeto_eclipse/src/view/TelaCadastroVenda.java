package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroVenda extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVenda frame = new TelaCadastroVenda();
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
	public TelaCadastroVenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(85, 107, 47));
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Cadastro vendas");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 50));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][][][][][grow]", "[][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Código: ");
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_2.add(lblNewLabel_1, "cell 2 1");
		
		textField = new JTextField();
		panel_2.add(textField, "cell 5 1,growx");
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[][][][][][16.00][grow]", "[][]"));
		
		JLabel lblNewLabel_2 = new JLabel("Data:");
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_4.add(lblNewLabel_2, "cell 2 1");
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 255, 255));
		panel_4.add(textField_1, "cell 6 1,growx");
		textField_1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 240));
		contentPane.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[][][][][][][191.00][251.00][][][][][][][][][][][][][][][][][][][][]", "[][]"));
		
		JLabel lblNewLabel_3 = new JLabel("Lucro:");
		lblNewLabel_3.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_3, "cell 2 1");
		
		JLabel lblNewLabel_4 = new JLabel("-");
		lblNewLabel_4.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_4, "cell 6 1");
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque();
				
				telaEstoque.setVisible(true);
				
				dispose();
			}
		});
		panel_3.add(btnNewButton_1, "cell 23 1");
		btnNewButton_1.setBackground(new Color(85, 107, 47));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][308.00][][][][][][][][][][][][][][][][][-32.00][]", "[][]"));
		
		JLabel lblNewLabel_5 = new JLabel("Comissão do vendedor:");
		panel.add(lblNewLabel_5, "cell 2 1");
		lblNewLabel_5.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JLabel lblNewLabel_6 = new JLabel("-");
		panel.add(lblNewLabel_6, "cell 5 1");
		lblNewLabel_6.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("   Voltar  ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEstoque telaEstoque = new TelaEstoque();
				
				telaEstoque.setVisible(true);
				
				dispose();
			}
		});
		panel.add(btnNewButton, "cell 22 1 3 1");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 16));
	}

}
