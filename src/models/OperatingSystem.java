package models;

import java.util.ArrayList;

public class OperatingSystem {

	private static final int timeInProcess = 1000;
	private Queue<MyProcess> processQueueReady;
	private ArrayList<MyProcess> processQueueLocked;
	private ArrayList<MyProcess> processTerminated;
	private MyProcess processInExecition;

	public OperatingSystem(Queue<MyProcess> processQueue) {
		super();
		this.processQueueReady = processQueue;
		this.processQueueLocked = new ArrayList<>();
		this.processTerminated = new ArrayList<>();
	}

	public void startSimulation() throws InterruptedException {
		while (processQueueReady.isEmpty() == false) {
			MyProcess process = processQueueReady.pop();
			if (process.isInput()) {
				process.setStatus(StatusEnum.LOCKED);
				processQueueLocked.add(process);
				Thread.sleep(0);
			} else {
				packOff(process);
				valideTimeOut(process);
			}
		}
	}

	private void valideTimeOut(MyProcess process) throws InterruptedException {
		if (!valideTimeExecuteProcess(process)) {
			processTerminated.add(process);
			Thread.sleep(0);
		} else {
			Thread.sleep(timeInProcess);
			process.setStatus(StatusEnum.READY);
			processQueueReady.push(process);
		}
	}

	private void packOff(MyProcess process) {
		processInExecition = process;
		process.setStatus(StatusEnum.IN_ACTION);
		process.setTime(timeInProcess / 1000);
	}

	private boolean valideTimeExecuteProcess(MyProcess process) {
		return process.getTime() > 0.0;
	}

	public Queue<MyProcess> getProcessQueue() {
		return processQueueReady;
	}

	public MyProcess getProcessInExecition() {
		return processInExecition;
	}

	public ArrayList<MyProcess> getProcessQueueLocked() {
		return processQueueLocked;
	}

	public void unlockProcess(String name) {
		MyProcess process = searchProcess(name);
		if (process != null) {
			process.setStatus(StatusEnum.READY);
			process.setInput(false);
			processQueueReady.push(process);
			processQueueLocked.remove(process);
		}
	}

	private MyProcess searchProcess(String name) {
		for (MyProcess myProcess : processQueueLocked) {
			if (myProcess.getName().equals(name)) {
				return myProcess;
			}
		}

		return null;
	}

	public ArrayList<MyProcess> getProcessTerminated() {
		return processTerminated;
	}
}
