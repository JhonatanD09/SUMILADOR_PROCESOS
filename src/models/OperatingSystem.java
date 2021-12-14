package models;

public class OperatingSystem {

	private static final int timeInProcess = 3000;
	private Queue<Process> processQueue;

	public OperatingSystem(Queue<Process> processQueue) {
		super();
		this.processQueue = processQueue;
	}

	public void startSimulation() throws InterruptedException {
		while (processQueue.isEmpty() == false) {
			Process process = processQueue.pop();
			packOff(process);
			if (valideTimeExecuteProcess(process)) {
				Thread.sleep(0);
			} else {
				Thread.sleep(timeInProcess);
			}
		}
	}

	private void packOff(Process process) {
		System.out.println(process.getName() + " " + process.getTime());
		process.setStatus(StatusEnum.IN_ACTION);
		process.setTime(timeInProcess/1000);
	}

	private boolean valideTimeExecuteProcess(Process process) {
		if (process.getTime() > 0.0) {
			process.setStatus(StatusEnum.READY);
			processQueue.push(process);
			return false;
		} else {
			return true;
		}
	}
}
