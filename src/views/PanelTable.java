package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.MyProcess;

public class PanelTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] head = new String[] { "Nombre del proceso" };
	private DefaultTableModel proccesDefaultTable;

	public PanelTable(String name) {

		setLayout(new BorderLayout());

		proccesDefaultTable = new DefaultTableModel();
		proccesDefaultTable.setColumnIdentifiers(head);
		
		JTable proccesTable = new JTable(proccesDefaultTable);
		editTable(proccesTable);
		
		JScrollPane tableGenreJScrollPane = new JScrollPane(proccesTable);
		editScrollPane(tableGenreJScrollPane);
		
		add(tableGenreJScrollPane, BorderLayout.CENTER);
	}

	private void editScrollPane(JScrollPane tableGenreJScrollPane) {
		tableGenreJScrollPane.getVerticalScrollBar().setBackground(Color.decode("#f0f1f1"));
		tableGenreJScrollPane.getHorizontalScrollBar().setBackground(Color.decode("#f0f1f1"));
		tableGenreJScrollPane.setBorder(BorderFactory.createEmptyBorder());
		tableGenreJScrollPane.setBackground(Color.decode("#f0f1f1"));
	}

	private void editTable(JTable proccesTable) {
		proccesTable.getTableHeader().setBackground(Color.decode("#4FCD69"));
		proccesTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.decode("#4FCD69")));
		proccesTable.getTableHeader().setForeground(Color.WHITE);
		proccesTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
		proccesTable.setBackground(Color.WHITE);
		proccesTable.setForeground(Color.decode("#505050"));
		proccesTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		proccesTable.setBorder(BorderFactory.createLineBorder(Color.decode("#CECECE")));
	}

	public void clearTable() {
		proccesDefaultTable.setRowCount(0);
	}

	public void showProcessInTable(MyProcess process) {
		proccesDefaultTable.addRow(new Object[] { process.getName() });
	}

}
