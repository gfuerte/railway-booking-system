package POJOs;

public class ReservationR {
	int rid;
	double fee;
	String date;
	int train;
	String transitLine;
	String origin;
	String destination;
	String deptTime;
	String arrvTime;
	String customer;
	String representative;
	String tclass;
	String note;
	String ticketType;
	String seat;
	
	public ReservationR(int rid, double fee, String date, int train, String transitLine, String origin, String destination, String deptTime, String arrvTime, String customer, String representative, String tclass, String note, String ticketType, String seat) {
		this.rid = rid;
		this.fee = fee;
		this.date = date;
		this.train = train;
		this.transitLine = transitLine;
		this.origin = origin;
		this.destination = destination;
		this.deptTime = deptTime;
		this.arrvTime = arrvTime;
		this.customer = customer;
		this.representative = representative;
		this.tclass = tclass;
		this.note = note;
		this.ticketType = ticketType;
		this.seat = seat;
	}
	
	public int getrid() { return this.rid; }
	public double getFee() { return this.fee; }
	public String getDate() { return this.date; }
	public int getTrain() { return this.train; }
	public String getTransitLine() { return this.transitLine; }
	public String getOrigin() { return this.origin; }
	public String getDestination() { return this.destination; }
	public String getDepartureTime() { return this.deptTime; }
	public String getArrivalTime() { return this.arrvTime; }
	public String getCustomer() { return this.customer; }
	public String getRepresentative() { return this.representative; }
	public String gettClass() { return this.tclass; }
	public String getNote() { return this.note; }
	public String getTicketType() { return this.ticketType; }
	public String getSeat() { return this.seat; }
	
}