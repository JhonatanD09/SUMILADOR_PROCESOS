package models;

public class MyProcess {

	private String name;
	private double time;
	private StatusEnum status;
	private boolean isInput;

	public MyProcess(String name, double time) {
		super();
		this.name = name;
		this.time = time;
		this.status = StatusEnum.READY;
		this.isInput = false;
	}

	public String getName() {
		return name;
	}

	public double getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = (this.time-time);
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

}
