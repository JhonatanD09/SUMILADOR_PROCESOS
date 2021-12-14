package models;

public class Process {

	private String name;
	private double time;
	private StatusEnum status;

	public Process(String name, double time) {
		super();
		this.name = name;
		this.time = time;
		this.status = StatusEnum.READY;
	}

	public String getName() {
		return name;
	}

	public double getTime() {
		return time;
	}

	public void setTime(int time ) {
		this.time-= time;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
