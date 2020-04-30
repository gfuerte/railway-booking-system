package POJOs;

public class Stop {
	public String line;
	public int station1;
	public int station2;
	public double fare;
	public int numStops;
	public int minTravel;
	
	public Stop(String line, int station1, int station2, double fare, int numStops, int minTravel) {
		this.line = line;
		this.station1 = station1;
		this.station2 = station2;
		this.fare = fare;
		this.numStops = numStops;
		this.minTravel = minTravel;
	}
	
	public String getLine() {
		return this.line;
	}
	
	public int getStation1() {
		return this.station1;
	}
	
	public int getStation2() {
		return this.station2;
	}
	
	public double getFare() {
		return this.fare;
	}
	
	public int getNumStops() {
		return this.numStops;
	}
	
	public int getMinTravel() {
		return this.minTravel;
	}
}
