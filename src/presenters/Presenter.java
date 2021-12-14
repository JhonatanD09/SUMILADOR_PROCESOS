package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import models.MyProcess;
import models.OperatingSystem;
import models.Queue;
import views.MainFrame;

public class Presenter {

	private MainFrame mainFrame;
	private OperatingSystem operatingSystem;

	public Presenter() {
		init();
		timer();
		startSimulation();
	}

	private void init() {
		this.mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		Queue<MyProcess> queue = new Queue<>();
		fillProcess(queue);
		operatingSystem = new OperatingSystem(queue);
	}

	private void startSimulation() {
		try {
			operatingSystem.startSimulation();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private void timer() {
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.clearTable();
				mainFrame.addProcessReady(operatingSystem.getProcessQueue());
			}
		});
		timer.start();
	}

	private void fillProcess(Queue<MyProcess> queue) {
		queue.push(new MyProcess("Proceso 1", 30));
		queue.push(new MyProcess("Proceso A", 50));
		queue.push(new MyProcess("Proceso 2", 30));
		queue.push(new MyProcess("Proceso 3", 110));
		queue.push(new MyProcess("Proceso B", 50));
		queue.push(new MyProcess("Proceso 4", 30));
	}
}
