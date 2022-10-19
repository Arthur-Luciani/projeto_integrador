package swingDesign;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;

public class JTableViridisSinus extends JTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTableViridisSinus() {}
	
	public JTable padraoJtable() {
		JTable table = new JTable();
		table = new JTable();
		table.setOpaque(false);
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(85, 107, 47));
		table.getTableHeader().setFont(new Font("Segoe Print", Font.PLAIN, 14));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setBackground(new Color(85, 107, 47));
		table.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		table.setBackground(new Color(240, 255, 240));
		
		return table;
	}

}
