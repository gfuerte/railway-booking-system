package servlets;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import POJOs.Train;
import POJOs.TrainSchedule;
import POJOs.Stop;

/**
 * Servlet implementation class reservationOptions
 */
@WebServlet("/reservationOptions")
public class reservationOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public reservationOptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("goBack") != null) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/Customer/loginCustomer.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String optionsMessage = "";
		request.setAttribute("optionsMessage", optionsMessage);
		HttpSession session = request.getSession();  

		String action = "SELECT * FROM RailwayBookingSystem.Station";
		ResultSet stationsQuery = getQuery(action);

		ArrayList<String> possibleStations = new ArrayList<>();

		try {
			while (stationsQuery.next()) possibleStations.add(stationsQuery.getString("city"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		request.setAttribute("stations", possibleStations);

		if (request.getParameter("Reservations Options") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/reservationOptions.jsp");
			dispatcher.forward(request, response);
		} else if (request.getParameter("submitOptions") != null) {
			String selectedOrigin = request.getParameter("origin");
			String selectedDestination = request.getParameter("destination");

			if (selectedOrigin != null && selectedDestination != null && !selectedOrigin.isEmpty() && !selectedDestination.isEmpty()) {
				ArrayList<Stop> possibleLines = new ArrayList<>();
				possibleLines = getTransitLine(selectedOrigin, selectedDestination);
				
				if (possibleLines.size() > 0) {
					System.out.println("Sucess: Found Transit Lines");
					ArrayList<Train> possibleTrains = new ArrayList<>();
					
					for(int i = 0; i < possibleLines.size(); i++) {
						String line = possibleLines.get(i).getLine();
						int oid = possibleLines.get(i).getStation1();
						int did = possibleLines.get(i).getStation2();
						double fare = possibleLines.get(i).getFare();
						int minTravel = possibleLines.get(i).getMinTravel();
						int numStops = possibleLines.get(i).getNumStops();
						
						action = "SELECT * FROM RailwayBookingSystem.Schedule WHERE transitLine=\"" + line + "\";";
						ResultSet trainQuery = getQuery(action);
						
						try {
							while(trainQuery.next()) {
								SimpleDateFormat format =  new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
								int trainNum = trainQuery.getInt("train");
								int availableSeats = trainQuery.getInt("avaliableSeats");
								//int availableSeats = trainQuery.getInt("availableSeats");
								String departure;
								String arrival;
								
								//Available Seats Cases
								if(availableSeats <= 0) continue;
								
								//Travel Time Cases
								int trainOrigin = getStationId(trainQuery.getString("origin")); //obtain the origin of the train
								int travelTime = getMinTravel(line, trainOrigin, oid);
								int travelTime2 = getMinTravel(line, trainOrigin, did);
								
								if(travelTime == -1 || travelTime2 == -1) continue;
								if (travelTime2 <= travelTime) continue;
								
								//Departure & Arrival Time Conversions
								Timestamp departureDatetime = trainQuery.getTimestamp("departureDatetime");
								
								Timestamp departureTime = new Timestamp(departureDatetime.getTime() + TimeUnit.MINUTES.toMillis(travelTime));
								Timestamp arrivalTime = new Timestamp(departureTime.getTime() + TimeUnit.MINUTES.toMillis(minTravel));
								
								departure = format.format(departureTime);
								arrival = format.format(arrivalTime);
								
								possibleTrains.add(new Train(trainNum, line, selectedOrigin, oid, selectedDestination, did, availableSeats, departure, arrival, fare, minTravel, numStops));
							}
						} catch (Exception ex) { ex.printStackTrace(); }	
					}
					if(possibleTrains.size() > 0) {
						System.out.println("Sucess: Found Trains");
						
						request.setAttribute("availableTrainsRequest", possibleTrains);
						session.setAttribute("availableTrainsSession", possibleTrains);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/createReservations.jsp");
						dispatcher.forward(request, response);
					} else {
						System.out.println("Failure: Trains Not Found");
						optionsMessage = "There Are No Trains Available For These Stops";
						request.setAttribute("optionsMessage", optionsMessage);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/reservationOptions.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					System.out.println("Failure: Transit Lines Not Found");
					optionsMessage = "There Are No Transit Lines Available For These Stops";
					request.setAttribute("optionsMessage", optionsMessage);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/reservationOptions.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				optionsMessage = "Please Select an Origin and Destination";
				request.setAttribute("optionsMessage", optionsMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/reservationOptions.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	public int getMinTravel(String line, int station1, int station2) {
		int result = -1;
		if(station1 == station2) return 0;
		String action = "SELECT minTravel FROM RailwayBookingSystem.Stop WHERE transitLine=\"" + line + "\" AND station1=" + station1 + " AND station2=" + station2 + ";";
		String backup = "SELECT minTravel FROM RailwayBookingSystem.Stop WHERE transitLine=\"" + line + "\" AND station1=" + station2 + " AND station2=" + station1 + ";";
		ResultSet query = getQuery(action);
		ResultSet query2 = getQuery(backup);
		try {
			while(query.next()) result = query.getInt("minTravel");		
			if(result == -1) {
				while(query2.next()) result = query2.getInt("minTravel");
			}
		} catch (Exception ex) { ex.printStackTrace(); }
		return result;
	}
	
	public ResultSet getQuery(String action) {
		try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin",
					"rutgerscs336");
			Statement stmt = con.createStatement();

			ResultSet query = stmt.executeQuery(action);
			
			//con.close();
			return query;
		} catch (Exception ex) { ex.printStackTrace(); }
		return null;
	}
	
	public int getStationId(String station) {
		int id = -1;
		String action = "SELECT idStation FROM Station WHERE city=\"" + station + "\";";
		ResultSet query = getQuery(action);
		try {
			while(query.next()) id = query.getInt("idStation"); 
		} catch (Exception ex) { ex.printStackTrace(); }
		return id;
	}
	
	public ArrayList<Stop> getTransitLine(String origin, String destination) {
		ArrayList<Stop> result = new ArrayList<>();
		
		int oid = getStationId(origin);
		int did = getStationId(destination);
			
		if(oid == did || oid == -1 || did == -1) {
			return result;
		} else {
			String action = "SELECT * FROM Stop WHERE station1=" + oid + " AND station2=" + did + ";";
			String action2 = "SELECT * FROM Stop WHERE station1=" + did + " AND station2=" + oid + ";";
			ResultSet query = getQuery(action);
			ResultSet query2 = getQuery(action2);
			boolean check = false;
			try {
				while(query.next()) {
					String line = query.getString("transitLine");
					double fare = query.getDouble("fare");
					int numStop = query.getInt("numStop");
					int minTravel = query.getInt("minTravel");
					result.add(new Stop(line, oid, did, fare, numStop, minTravel));
					check = true;
				}
				if (check == false) {
					while(query2.next()) {
						String line = query2.getString("transitLine");
						double fare = query2.getDouble("fare");
						int numStop = query2.getInt("numStop");
						int minTravel = query2.getInt("minTravel");
						result.add(new Stop(line, oid, did, fare, numStop, minTravel));
					}
				}
			} catch (Exception ex) { ex.printStackTrace(); }
		}
		return result;
	}
}
