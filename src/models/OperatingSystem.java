package models;

import java.util.ArrayList;


public class OperatingSystem implements Runnable{

	private static final int timeInProcess = 1000;
	private Queue<MyProcess> processQueueReady;
	private ArrayList<MyProcess> processQueueLocked;
	private ArrayList<MyProcess> processTerminated;
	private ArrayList<MyProcess> processExpired;
	private MyProcess processInExecition;
	private Thread thread;

	public OperatingSystem(Queue<MyProcess> processQueue) {
		super();
		this.processQueueReady = processQueue;
		this.processQueueLocked = new ArrayList<>();
		this.processTerminated = new ArrayList<>();
		processExpired = new ArrayList<>();
		this.thread = new Thread(this);
	}
	
	@Override
	public void run() {
		try {
			startSimulation();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void addProcess(String name, double time, String input) {
		MyProcess  myProcess = new MyProcess(name, time);
		if(input.equalsIgnoreCase("SI")) {			
			myProcess.setInput(true);
		}
		processQueueReady.push(new MyProcess(name,time));
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
			processExpired.add(process);
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
	
	public void delete(String name) {
		Node<MyProcess> temp = processQueueReady.getFirstNode();
		if (temp.getData().getName().equals(name)) {
			
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

	
	public ArrayList<MyProcess> getProcessExpired() {
		return processExpired;
	}
	public void start() {
		
		thread.start();
	}
}

