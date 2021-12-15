package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import models.MyProcess;
import models.OperatingSystem;
import models.Queue;
import views.MainFrame;

public class Presenter implements ActionListener {

	private MainFrame mainFrame;
	private OperatingSystem operatingSystem;

	public Presenter() {
		init();
		timer();
	}

	private void init() {
		this.mainFrame = new MainFrame(this);
		mainFrame.setVisible(true);
		Queue<MyProcess> queue = new Queue<>();
		fillProcess(queue);
		operatingSystem = new OperatingSystem(queue);
	}

	private void timer() {
		Timer timer = new Timer(500, new ActionListener() {
			int minutes = LocalDateTime.now().getMinute() + 1;
			int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				valideActiveAlarm();
				valieProcessB();
				valideExecuteProcess();
				updateFrame();
				count++;
			}

			private void updateFrame() {
				mainFrame.clearTable();
				mainFrame.addProcessReady(operatingSystem.getProcessQueue());
				mainFrame.addProcessLocked(operatingSystem.getProcessQueueLocked());
				mainFrame.addProcessTerminated(operatingSystem.getProcessTerminated());
			}

			private void valideExecuteProcess() {
				if (operatingSystem.getProcessQueue().isEmpty()
						&& operatingSystem.getProcessInExecition().getTime() == 0) {
					mainFrame.setExecutiongProcess("No hay procesos en ejecuicion");
				} else {
					if (operatingSystem.getProcessInExecition() != null)
						mainFrame.setExecutiongProcess(operatingSystem.getProcessInExecition().getName());
				}
			}

			private void valieProcessB() {
				if (count == 80) {
					operatingSystem.unlockProcess("Proceso B");
				}
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
//		queue.push(new MyProcess("Proceso 1", 30));
//		MyProcess myProcessAlarm = new MyProcess("Proceso Alarma", 24);
//		myProcessAlarm.setInput(true);
//		queue.push(myProcessAlarm);
//		queue.push(new MyProcess("Proceso A", 10));
//		queue.push(new MyProcess("Proceso 2", 12));
//		queue.push(new MyProcess("Proceso 3", 18));
//		MyProcess myProcess = new MyProcess("Proceso B", 20);
//		myProcess.setInput(true);
//		queue.push(myProcess);
//		queue.push(new MyProcess("Proceso 4", 5));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case INIT:
			timer();
			operatingSystem.start();
			break;
		case ADD:
			operatingSystem.addProcess(JOptionPane.showInputDialog("Nombre del proceso"), Double.parseDouble(
					JOptionPane.showInputDialog("Tiempo del proceso")),JOptionPane.showInputDialog("Se bloquea?"));
			break;
		case DELETE:

			break;
		case EXPIRED:
			mainFrame.showReport("Procesos que expiraron", operatingSystem.getProcessExpired());
			break;
		}
	}
}
