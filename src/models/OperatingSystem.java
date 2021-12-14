package models;

public class OperatingSystem {

	private static final int timeInProcess = 5000;
	private Queue<MyProcess> processQueue;

	public OperatingSystem(Queue<MyProcess> processQueue) {
		super();
		this.processQueue = processQueue;
	}

	public void startSimulation() throws InterruptedException {
		while (processQueue.isEmpty() == false) {
			MyProcess process = processQueue.pop();
			packOff(process);
			valideTimeOut(process);
		}
	}

	private void valideTimeOut(MyProcess process) throws InterruptedException {
		if (valideTimeExecuteProcess(process)) {
			Thread.sleep(0);
		} else {
			Thread.sleep(timeInProcess);
		}
	}

	private void packOff(MyProcess process) {
		process.setStatus(StatusEnum.IN_ACTION);
		process.setTime(timeInProcess/1000);
	}

	private boolean valideTimeExecuteProcess(MyProcess process) {
		if (process.getTime() > 0.0) {
			process.setStatus(StatusEnum.READY);
			processQueue.push(process);
			return false;
		} else {
			return true;
		}
	}
	
	public Queue<MyProcess> getProcessQueue() {
		return processQueue;
	}
}
