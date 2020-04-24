package POJOs;

import java.sql.Date;

public class TrainSchedule {

	
	public int trainnum;
	public String origin;
	public String destination;
	public Date arrival;
	public Date departure;
	public Double fare;
	public String transitline;
	
	public TrainSchedule(int t, String o, String d, Date a, Date de, Double f, String tl) {
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
	
	public Date getArrival() {
		return arrival;
	}
	
	public Date getDeparture() {
		return departure;
	}
	
	public Double getFare() {
		return fare;
	}
	
	public String getTransitline() {
		return transitline;
	}

}
