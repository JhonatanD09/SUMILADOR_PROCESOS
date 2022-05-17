package models;

public class MyProcess {

	private String name;
	private double time;
	private boolean locked;

	public MyProcess(String name, double time, boolean locked ) {
		super();
		this.name = name;
		this.time = time;
		this.locked = locked;
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

	public boolean isLocked() {
		return locked;
	}
		
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	@Override
	public String toString() {
		return "name: " + name + " time: "+ time + " bloqueo? : " + locked;
	}
}
