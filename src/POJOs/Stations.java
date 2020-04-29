package POJOs;

public class Stations {
	int id;
	String city;
	
	public Stations(int id, String city) {
		this.id = id;
		this.city = city;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getCity() {
		return this.city;
	}
}
