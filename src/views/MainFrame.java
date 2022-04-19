package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

	private PanelTable tableProcessReady, tableProcessLocked, tableProcessTerminated, tableProcessSusLocked,tableProcessSusReady;
	private JLabel executingLabel;
	private JDialogReportProcess addProcess;
	private JDialogCreatePorcess createPorcess;
	private JComboBox<String> reportsBox;
	private JPanel center;
	private JPanel reports;

	private PanelTable tableProcessExpired;

	public MainFrame(ActionListener l) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setSize(1500, 800);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		this.tableProcessReady = new PanelTable("Procesos");
		this.tableProcessLocked = new PanelTable("Procesos Bloqueados");
		this.tableProcessTerminated = new PanelTable("Procesos Terminados");
		this.tableProcessSusLocked = new PanelTable("Procesos suspendidos bloqueados");
		this.tableProcessSusReady = new PanelTable("Procesos suspendidos listos");
		this.tableProcessExpired = new PanelTable("Procesos expirados");
		this.executingLabel = new JLabel("No hay procesos en ejecucion");

		center = new JPanel();
		center.setBackground(Color.WHITE);
		center.setLayout(new BorderLayout());

		reports = new JPanel();

		JPanel reportsSearch = new JPanel();
		reportsSearch.setLayout(new BorderLayout());
		
		reportsBox = new JComboBox<String>();
		reportsBox.addItem("Procesos listos");
		reportsBox.addItem("Porcesos despachados");
		reportsBox.addItem("Procesos en ejecucion");
		reportsBox.addItem("Porcesos bloqueado");
		reportsBox.addItem("Procesos bloqueados");
		reportsBox.addItem("Procesos expirados");
		reportsBox.addItem("Procesos terminados");
		
		
		JLabel jLabel = new JLabel("Reportes");
		label(jLabel);
		reportsSearch.add(jLabel, BorderLayout.NORTH);
		reportsSearch.add(reportsBox, BorderLayout.CENTER);
		reportsSearch.add(button(l, "Buscar", Events.SEARCH.name()), BorderLayout.EAST);
		
		center.add(reportsSearch, BorderLayout.NORTH);
		center.add(reports, BorderLayout.CENTER);

		add(tableProcessReady, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);

		JPanel butons = new JPanel();
		butons.setLayout(new GridLayout(1, 3, 10, 10));

		JButton button = button(l, "Iniciar Simulacion", Events.INIT.name());
		JButton button2 = button(l, "Agregar Proceso", Events.ADD.name());

		butons.add(button2);
		butons.add(button);

		add(butons, BorderLayout.SOUTH);
	}

	public void report(String report, ArrayList<MyProcess> p) {
		reports.removeAll();
		showReport(report,p);
		revalidate();
		repaint();
	}

	private void showReport(String report,ArrayList<MyProcess> p) {
		switch (report) {
		case "Procesos bloqueados":
			addProcessLocked(p);
			reports.add(tableProcessLocked);
			break;
		case "Porcesos despachados":
			addProcessSusReady(p);
			reports.add(tableProcessSusReady);
			break;
		case "Procesos en ejecucion":
			addProcessSusLocked(p);
			reports.add(tableProcessSusLocked);
			break;
		case "Procesos expirados":
			addProcessExpired(p);
			reports.add(tableProcessExpired);
			break;
		case "Terminados":
			addProcessTerminated(p);
			reports.add(tableProcessTerminated);
			break;
		}
	}

	private JButton button(ActionListener l, String name, String command) {
		JButton button = new JButton(name);
		button.setBackground(Color.decode("#4FCD69"));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		button.setActionCommand(command);
		button.addActionListener(l);
		return button;
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
	
	public void addProcessSusLocked(ArrayList<MyProcess> processSusLocked) {
		addProcessList(processSusLocked, tableProcessSusLocked);
	}
	
	public void addProcessSusReady(ArrayList<MyProcess> processSusReady) {
		addProcessList(processSusReady, tableProcessSusReady);
	}

	public void addProcessExpired(ArrayList<MyProcess> processExpired) {
		addProcessList(processExpired, tableProcessExpired);
	}
	
	private void addProcessList(ArrayList<MyProcess> processLocked, PanelTable table) {
		for (MyProcess myProcess : processLocked) {
			table.showProcessInTable(myProcess);
		}
	}

	private void addProcces(Queue<MyProcess> processReady, PanelTable table) {
		Node<MyProcess> temp = processReady.peek();
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

//	public void showReport(String name, ArrayList<MyProcess> arrayList) {
//		addProcess = new JDialogReportProcess(name);
//		for (MyProcess myProcess : arrayList) {
//			addProcess.showProcessInTable(myProcess);
//		}
//		addProcess.setVisible(true);
//	}

	public void showDialogAdd(ActionListener l) {
		createPorcess = new JDialogCreatePorcess(l);
		createPorcess.setVisible(true);
	}

	public Object[] dataCreate() {
		return createPorcess.data();
	}

	public void deleteDialogCreate() {
		createPorcess.dispose();
	}
	
	public String getSelectedReport() {
		return (String)reportsBox.getSelectedItem();
	}
}
