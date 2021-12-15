package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import models.MyProcess;

public class JDialogAddProcess extends JDialog{

	private String[] head = new String[] {"Nombre del proceso", "Tiempo restante"};
	private DefaultTableModel proccesDefaultTable;
	
	public JDialogAddProcess( String name) {
		
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		proccesDefaultTable = new DefaultTableModel();
		proccesDefaultTable.setColumnIdentifiers(head);
		
		JTable proccesTable = new JTable(proccesDefaultTable);
		editTable(proccesTable);
		
		JScrollPane tableGenreJScrollPane = new JScrollPane(proccesTable);
		editScrollPane(tableGenreJScrollPane);
		
		add(label(name),BorderLayout.NORTH);
		add(tableGenreJScrollPane, BorderLayout.CENTER);
		
	}
	
	private JLabel label(String name) {
		JLabel jLabel = new JLabel(name);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		return jLabel;
	}
	
	private void editScrollPane(JScrollPane tableGenreJScrollPane) {
		tableGenreJScrollPane.getVerticalScrollBar().setBackground(Color.decode("#f0f1f1"));
		tableGenreJScrollPane.getHorizontalScrollBar().setBackground(Color.decode("#f0f1f1"));
		tableGenreJScrollPane.setBorder(BorderFactory.createEmptyBorder());
		tableGenreJScrollPane.setBackground(Color.WHITE);
	}

	private void editTable(JTable proccesTable) {
		proccesTable.getTableHeader().setBackground(Color.decode("#4FCD69"));
		proccesTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.decode("#4FCD69")));
		proccesTable.getTableHeader().setForeground(Color.WHITE);
		proccesTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 30));
		proccesTable.setBackground(Color.WHITE);
		proccesTable.setForeground(Color.decode("#505050"));
		proccesTable.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		proccesTable.setBorder(BorderFactory.createLineBorder(Color.decode("#CECECE")));
		proccesTable.setRowHeight(40);
	}

	public void clearTable() {
		proccesDefaultTable.setRowCount(0);
	}

	public void showProcessInTable(MyProcess process) {
		proccesDefaultTable.addRow(new Object[] { process.getName(),process.getTime() });
	}
}
