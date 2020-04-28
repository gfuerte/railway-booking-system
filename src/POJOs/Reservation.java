package POJOs;

public class Reservation {
	public String created;
	public int rid;
	public int trainNum;
	public String origin;
	public String destination;
	public String departure;
	public String arrival;
	public double fare;
	public String line;
	//public int seat;
	
	public Reservation(String created, int rid, int trainNum, String origin, String destination, 
			String departure, String arrival, double fare, String line) {
		this.created = created;
		this.rid = rid;
		this.trainNum = trainNum;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.fare = fare;
		this.line = line;
	}
	
	public String getCreated(){
		return this.created;
	}
	
	public int getRid() {
		return this.rid;
	}

	public int getTrainNum() {
		return this.trainNum;
	}
	
	public String getOrigin() {
		return this.origin;
	}

	public String getDestination() {
		return this.destination;
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
	
	public String getLine() {
		return this.line;
	}
	
	/*public int getSeat() {
	 	return this.seat;
	}*/
}