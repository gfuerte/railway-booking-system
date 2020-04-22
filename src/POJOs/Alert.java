package POJOs;

public class Alert {
	
	public String alert;
	public String date;
	
	public Alert(String alert, String date) {
		this.alert = alert;
		this.date = date;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
