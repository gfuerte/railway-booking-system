package POJOs;

public class Reservation {
	public String created;
	public int rid;
	public int trainNum;
	public String origin;
	public String destination;
	public String departure;
	public String arrival;
	public double fee;
	public String line;
	public String trainClass;
	public String note;
	public String ticket;
	public int seat;
	public String ticketInfo;
	
	public Reservation(String created, int rid, int trainNum, String origin, String destination, 
			String departure, String arrival, double fee, String line, String trainClass, String note, String ticket, int seat) {
		this.created = created;
		this.rid = rid;
		this.trainNum = trainNum;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.fee = fee;
		this.line = line;
		this.trainClass = trainClass;
		this.note = note;
		this.ticket = ticket;
		this.seat = seat;
		if(note == null || note.equals("")) {
			this.ticketInfo = trainClass + " Class - " + ticket; 
		} else {
			this.ticketInfo = trainClass + " Class - " + ticket + " " + note;
		}
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
	
	public double getFee() {
		return this.fee;
	}
	
	public String getLine() {
		return this.line;
	}
	
	public String getTrainClass() {
	 	return this.trainClass;
	}
	
	public String getNote() {
		return this.note;
	}
	
	public String getTicket() {
	 	return this.ticket;
	}
	
	public int getSeat() {
	 	return this.seat;
	}
	
	public String getTicketInfo() {
		return this.ticketInfo;
	}
}