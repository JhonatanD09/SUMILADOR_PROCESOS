package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

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
			int minutes = LocalDateTime.now().getMinute() + 1;
			int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				valideActiveAlarm();
				if (count == 80) {
					operatingSystem.unlockProcess("Proceso B");
				}
				if (operatingSystem.getProcessQueue().isEmpty()
						&& operatingSystem.getProcessInExecition().getTime() == 0) {
					mainFrame.setExecutiongProcess("No hay procesos en ejecuicion");
				} else {
					mainFrame.setExecutiongProcess(operatingSystem.getProcessInExecition().getName());
				}

				mainFrame.clearTable();
				mainFrame.addProcessReady(operatingSystem.getProcessQueue());
				mainFrame.addProcessLocked(operatingSystem.getProcessQueueLocked());
				mainFrame.addProcessTerminated(operatingSystem.getProcessTerminated());
				count++;
			}

			private void valideActiveAlarm() {
				if (minutes == LocalDateTime.now().getMinute()) {
					operatingSystem.unlockProcess("Proceso Alarma");
				}
			}
		});
		timer.start();
	}

	private void fillProcess(Queue<MyProcess> queue) {
		queue.push(new MyProcess("Proceso 1", 30));
		MyProcess myProcessAlarm = new MyProcess("Proceso Alarma", 24);
		myProcessAlarm.setInput(true);
		queue.push(myProcessAlarm);
		queue.push(new MyProcess("Proceso A", 10));
		queue.push(new MyProcess("Proceso 2", 12));
		queue.push(new MyProcess("Proceso 3", 18));
		MyProcess myProcess = new MyProcess("Proceso B", 20);
		myProcess.setInput(true);
		queue.push(myProcess);
		queue.push(new MyProcess("Proceso 4", 5));
	}
}
