package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.MyProcess;
import models.Node;
import models.Queue;
import presenters.Events;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelTable tableProcessReady, tableProcessLocked, tableProcessTerminated;
	private JLabel executingLabel;
	private JDialogAddProcess addProcess;

	public MainFrame(ActionListener l) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setSize(1500, 800);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		this.tableProcessReady = new PanelTable("Procesos Listos");
		this.tableProcessLocked = new PanelTable("Procesos Bloqueados");
		this.tableProcessTerminated = new PanelTable("Procesos Terminados");
		
		this.executingLabel= new JLabel("No hay procesos en ejecucion"); 
		
		JPanel center = new JPanel();
		center.setBackground(Color.WHITE);
		center.setLayout(new GridLayout(2, 1,20,20));
		center.add(panelExecuting());
		center.add(tableProcessTerminated);
		
		add(tableProcessReady, BorderLayout.WEST);
		add(tableProcessLocked, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
		
		
		JPanel butons = new JPanel();
		butons.setLayout(new GridLayout(1, 3, 10,10));
		
		JButton button = button(l,"Iniciar Simulacion",Events.INIT.name());
		JButton button2 = button(l,"Agregar Proceso",Events.ADD.name());
		JButton button3 = button(l,"Reporte de procesos expirados ",Events.EXPIRED.name());
		
		butons.add(button2);
		butons.add(button3);
		butons.add(button);
		
		add(butons, BorderLayout.SOUTH);
	}

	private JButton button(ActionListener l, String name, String command) {
		JButton button = new JButton(name);
		button.setBackground(Color.decode("#4FCD69"));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		button.setActionCommand(command);
		button.addActionListener(l);
		return button;
	}

	private JPanel panelExecuting() {
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.decode("#4FCD69"));
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		
		jPanel.setLayout(new GridLayout(2,1,20,5));
		JLabel titleJLabel = new JLabel("En ejecucion");
		label(titleJLabel);
		jPanel.add(titleJLabel);
		label(executingLabel);
		jPanel.add(executingLabel);
		return jPanel;
	}
	
	private void label(JLabel label) {
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	}
	
	public void addProcessReady(Queue<MyProcess> processReady) {
		addProcces(processReady, tableProcessReady);
	}
	
	public void addProcessLocked(ArrayList<MyProcess> processLocked) {
		addProcessList(processLocked, tableProcessLocked);
	}
	
	public void addProcessTerminated(ArrayList<MyProcess> processTerminated) {
		addProcessList(processTerminated, tableProcessTerminated);
	}
	
	private void addProcessList(ArrayList<MyProcess> processLocked, PanelTable table) {
		for (MyProcess myProcess : processLocked) {
			table.showProcessInTable(myProcess);
		}
	}
	
	private void addProcces(Queue<MyProcess> processReady, PanelTable table) {
		Node<MyProcess> temp = processReady.getFirstNode();
		while (temp != null) {
			table.showProcessInTable(temp.getData());
			temp = temp.getNext();
		}
	}

	public void setExecutiongProcess(String text) {
		executingLabel.setText(text);
	}
	
	public void clearTable() {
		tableProcessReady.clearTable();
		tableProcessLocked.clearTable();
		tableProcessTerminated.clearTable();
	}

	public void showReport(String name, ArrayList<MyProcess> arrayList) {
		addProcess = new JDialogAddProcess(name);
		for (MyProcess myProcess : arrayList) {
			addProcess.showProcessInTable(myProcess);
		}
		addProcess.setVisible(true);
	}
}
