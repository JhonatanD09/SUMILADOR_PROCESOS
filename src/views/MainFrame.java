package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import models.MyProcess;
import models.Node;
import models.Queue;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelTable tableProcessReady, tableProcessLocked;

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		this.tableProcessReady = new PanelTable("Procesos Listos");
		this.tableProcessLocked = new PanelTable("Procesos Bloqueados");
		
		add(tableProcessReady, BorderLayout.WEST);
		add(tableProcessLocked, BorderLayout.EAST);
	}

	public void addProcessReady(Queue<MyProcess> processReady) {
		addProcces(processReady, tableProcessReady);
	}
	
	public void addProcessLocked(Queue<MyProcess> processLocked) {
		addProcces(processLocked, tableProcessLocked);
	}
	
	private void addProcces(Queue<MyProcess> processReady, PanelTable table) {
		Node<MyProcess> temp = processReady.getFirstNode();
		while (temp != null) {
			table.showProcessInTable(temp.getData());
			temp = temp.getNext();
		}
	}

	public void clearTable() {
		tableProcessReady.clearTable();
	}
}
