package POJOs;

public class Reservation {
	String rid;
	Double fare;
	String cUsername;
	String rUsername;
	String date;
	
	public Reservation(String rid, Double fare, String cUsername, String rUsername, String date) {
		this.rid = rid;
		this.fare = fare;
		this.cUsername = cUsername;
		this.rUsername = rUsername;
		this.date = date;
	}
	
	public String getrid() { return this.rid; }
	public void setrid(String rid) { this.rid = rid; }

	public Double getfare() { return this.fare; }
	public void setfare(Double fare) { this.fare = fare; }

	public String getcusername() { return this.cUsername; }
	public void setcusername(String cUsername) { this.cUsername = cUsername; }

	public String getrusername() { return this.rUsername; }
	public void setrusername(String rUsername) { this.rUsername = rUsername; }
	
	public String getdate() { return this.date; }
	public void setdate(String date) { this.date = date; }

}