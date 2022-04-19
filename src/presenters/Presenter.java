package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.MyProcess;
import models.OperatingSystem;
import models.Queue;
import views.MainFrame;

public class Presenter implements ActionListener {

	private MainFrame mainFrame;
	private OperatingSystem operatingSystem;

	public Presenter() {
		init();
	}

	private void init() {
		this.mainFrame = new MainFrame(this);
		mainFrame.setVisible(true);
		Queue<MyProcess> queue = new Queue<>();
		operatingSystem = new OperatingSystem(queue);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case INIT:
			try {
				operatingSystem.startSimulation();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			break;
		case ADD:
			mainFrame.showDialogAdd(this);
			break;
		case DELETE:

			break;
		case ACEPT:
			Object[] tempObjects = mainFrame.dataCreate();
			System.out.print((boolean)tempObjects[2]);
			MyProcess myProcess = new MyProcess((String)tempObjects[0],(int)tempObjects[1],(boolean)tempObjects[2]);
			if (operatingSystem.addProcess(myProcess)) {
				mainFrame.deleteDialogCreate();
				mainFrame.clearTable();
				mainFrame.addProcessReady(operatingSystem.getProcessQueue());
			}else {
				JOptionPane.showMessageDialog(mainFrame, "El nombre no esta disponible");
			}
			break;
		case EXPIRED:
			break;
		case SEARCH:
			mainFrame.report(mainFrame.getSelectedReport(),operatingSystem.getReport(mainFrame.getSelectedReport()));
			break;
		}
	}
}
