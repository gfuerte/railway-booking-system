package POJOs;

public class TrainSchedule {

	
	public int trainnum;
	public String origin;
	public String destination;
	public String arrival;
	public String departure;
	public Double fare;
	public String transitline;
	
	public TrainSchedule(int t, String o, String d, String a, String de, Double f, String tl) {
		trainnum = t;
		origin = o;
		destination = d;
		arrival = a;
		departure = de;
		fare = f;
		transitline = tl;
	
	}
	
	public int getTrainnum() {
		return trainnum;
	}

	public String getOrigin() {
		return origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String getArrival() {
		return arrival;
	}
	
	public String getDeparture() {
		return departure;
	}
	
	public Double getFare() {
		return fare;
	}
	
	public String getTransitline() {
		return transitline;
	}

}
