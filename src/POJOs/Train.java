package POJOs;

public class Train {
	public int trainNum;
	public String line;
	public String origin;
	public int originId;
	public String destination;
	public int destinationId;
	public int availableSeats;
	public String departure;
	public String arrival;
	public double fare;
	public int minTravel;
	public int numStops;
	
	public Train(int trainNum, String line, String origin, int originId, String destination, int destinationId, int availableSeats, String departure, String arrival, double fare, int minTravel, int numStops) {
		this.trainNum = trainNum;
		this.line = line;
		this.origin = origin;
		this.originId = originId;
		this.destination = destination;
		this.destinationId = destinationId;
		this.availableSeats = availableSeats;
		this.departure = departure;
		this.arrival = arrival;
		this.fare = fare;
		this.minTravel = minTravel;
		this.numStops = numStops;
	}
	
	public int getTrainNum() {
		return this.trainNum;
	}
	
	public String getLine() {
		return this.line;
	}
	
	public String getOrigin() {
		return this.origin;
	}
	
	public int getOriginId() {
		return this.originId;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public int getDestinationId() {
		return this.destinationId;
	}
	
	public int getAvailableSeats() {
		return this.availableSeats;
	}
	
	public String getDeparture() {
		return this.departure;
	}
	
	public String getArrival() {
		return this.arrival;
	}
	
	public double getFare() {
		return this.fare;
	}
	
	public int getMinTravel() {
		return this.minTravel;
	}
	
	public int getNumStops() {
		return this.numStops;
	}
}

