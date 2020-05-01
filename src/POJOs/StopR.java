package POJOs;

public class StopR {
	String origin;
	String destination;
	String transitLine;
	int seats;
	int stops;
	String deptTime;
	String arrvTime;
	String trvlTime;
	double fare;
	int train;
	
	public StopR (String origin, String destination, String transitLine, int seats, int stops, String deptTime,String arrvTime, String trvlTime, double fare, int train) {
		this.origin = origin;
		this.destination = destination;
		this.transitLine = transitLine;
		this.seats = seats;
		this.stops = stops;
		this.deptTime = deptTime;
		this.arrvTime = arrvTime;
		this.trvlTime = trvlTime;
		this.fare = fare;
		this.train = train;
	}
	
	public String getOrigin() { return this.origin; }
	public String getDestination() { return this.destination; }
	public String getTransitLine() { return this.transitLine; }
	public int getSeats() { return this.seats; }
	public int getStops() { return this.stops; }
	public String getDepartureTime() { return this.deptTime; }
	public String getArrivalTime() { return this.arrvTime; }
	public String getTravelTime() { return this.trvlTime; }
	public double getFare() { return this.fare; }
	public int getTrain() { return this.train; }
	
}
