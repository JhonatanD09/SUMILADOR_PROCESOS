package presenters;

import models.OperatingSystem;
import models.Queue;
import views.MainFrame;
import models.Process;

public class Presenter {
	
	private MainFrame mainFrame;
	private OperatingSystem operatingSystem;
	
	public Presenter() {
		initOperatingSystem();
		this.mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	private void initOperatingSystem() {
		Queue<Process> queue = new Queue<>();
		fillProcess(queue);
		operatingSystem = new OperatingSystem(queue);
		try {
			operatingSystem.startSimulation();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void fillProcess(Queue<Process> queue) {
		queue.push(new Process("Proceso 1", 11));
		queue.push(new Process("Proceso A", 5));
		queue.push(new Process("Proceso 2", 3));
	}
}
