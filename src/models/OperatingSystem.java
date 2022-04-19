package models;

import java.util.ArrayList;


public class OperatingSystem {

	private Queue<MyProcess> processQueueReady;
	private ArrayList<MyProcess> locked;
	private ArrayList<MyProcess> executing;
	private ArrayList<MyProcess> expired;
	private ArrayList<MyProcess> processTerminated;
	private ArrayList<MyProcess> despachados;
	private MyProcess processInExecition;
	private Thread thread;
	private int timeCronometer = 0;

	public OperatingSystem(Queue<MyProcess> processQueue) {
		super();
		this.processQueueReady = processQueue;
		this.locked = new ArrayList<>();
		this.processTerminated = new ArrayList<>();
		executing = new ArrayList<>();
		expired = new ArrayList<>();
		despachados = new ArrayList<>();
	}
	

	
	public boolean addProcess(MyProcess myProcess) {
		if (search(myProcess.getName())==null) {			
			processQueueReady.push(myProcess);
			return true;
		}
		return false;
	}

	private MyProcess search(String name) {
		Node<MyProcess> temp = processQueueReady.peek();
		while (temp!=null) {
			System.out.println(temp.getData().getName()+"  "+ name);
			if (temp.getData().getName().equals(name)) {
				return temp.getData();
			}else {
				temp = temp.getNext();
			}
		}
		return null;
	}
	
	
	public void startSimulation() throws InterruptedException {
		while (processQueueReady.isEmpty() == false) {
			MyProcess process = processQueueReady.peek().getData();
			despachados.add(process);
			executing.add(process);
			if ((process.getTime()-2)>0) {
				process.setTime(2);
				expired.add(process);
				processQueueReady.push(processQueueReady.pop());
			}else {
				processTerminated.add(processQueueReady.pop());
			}
		}
	}

	

	
	private void operations(MyProcess process) {
		if (process.isLocked()==true) {
			locked.add(process);
		}else {
			valideTimeProcess(process);
		}
	}



	private void valideTimeProcess(MyProcess process) {
		if(process.getTime()<=0) {
			processTerminated.add(processQueueReady.pop());
		}else {				
			process.setTime(1);
		}
	}

	public Queue<MyProcess> getProcessQueue() {
		return processQueueReady;
	}

	public MyProcess getProcessInExecition() {
		return processInExecition;
	}

	public ArrayList<MyProcess> getProcessQueueLocked() {
		return locked;
	}

	public void delete(String name) {
		Node<MyProcess> temp = processQueueReady.peek();
		if (temp.getData().getName().equals(name)) {
			
		}
	}


	public ArrayList<MyProcess> getProcessTerminated() {
		return processTerminated;
	}

	
	public void start() { 
		thread.start();
	}



	public ArrayList<MyProcess> getReport(String selectedReport) {
		switch (selectedReport) {
		case "Procesos bloqueados":
			return locked;
		case "Porcesos despachados":
			return despachados;
		case "Procesos en ejecucion":
			return executing;
		case "Procesos expirados":
			return expired;
		case "Terminados":
			return processTerminated;
		case "Despachados":
			return despachados;
		}
		return null;
	}
}

