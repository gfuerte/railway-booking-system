package servlets;

import java.io.*;
import java.util.*;
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
				for(int i = 0; i < possibleLines.size(); i++) {
					System.out.println(possibleLines.get(i).line);
				}
				
				if (possibleLines.size() > 0) {
					System.out.println("Sucess: Found Transit Lines");
					HttpSession session=request.getSession(); 
					session.setAttribute("possibleLines", possibleLines);
					try {
						SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss");
						String message = "";
						request.setAttribute("confirmation", message);
	
						action = "SELECT * FROM RailwayBookingSystem.Schedule";
						ResultSet trainsQuery = getQuery(action);
	
						ArrayList<TrainSchedule> trains = new ArrayList<>();
	
						while (trainsQuery.next()) {
							int trainnum = trainsQuery.getInt("train");
							String origin = trainsQuery.getString("origin");
							String destination = trainsQuery.getString("destination");
							String arrival = ft.format(trainsQuery.getTimestamp("arrivalDatetime"));
							String departure = ft.format(trainsQuery.getTimestamp("departureDatetime"));
							Double fare = trainsQuery.getDouble("fare");
							String transitline = trainsQuery.getString("transitLine");
	
							trains.add(new TrainSchedule(trainnum, origin, destination, arrival, departure, fare, transitline));
						}
						request.setAttribute("list", trains);
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/createReservations.jsp");
					dispatcher.forward(request, response);
				} else {
					System.out.println("Failure: Transit Lines Not Found");
					optionsMessage = "There Are No Trains Available For These Stops";
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

	public ArrayList<Stop> getTransitLine(String origin, String destination) {
		ArrayList<Stop> result = new ArrayList<>();
		String actionO = "SELECT idStation FROM Station WHERE city=\"" + origin + "\"";
		String actionD = "SELECT idStation FROM Station WHERE city=\"" + destination + "\"";
		
		System.out.println("origin is: " + origin);
		System.out.println("destination is: " + destination);
		
		ResultSet queryO = getQuery(actionO);
		ResultSet queryD = getQuery(actionD);
		int oid = -1;
		int did = -1;
		try {
			while(queryO.next()) oid = queryO.getInt("idStation");
			while(queryD.next()) did = queryD.getInt("idStation");
			System.out.println("oid is: " + oid);
			System.out.println("did is: " + did);
		} catch (Exception ex) { ex.printStackTrace(); }
			
		if(oid == did || oid == -1 || did == -1) {
			return result;
		} else {
			String action = "SELECT * FROM Stop WHERE station1=" + oid + " AND station2=" + did + ";";
			ResultSet query = getQuery(action);
			try {
				while(query.next()) {
					String line = query.getString("transitLine");
					double fare = query.getDouble("fare");
					int numStop = query.getInt("numStop");
					int minTravel = query.getInt("minTravel");
					result.add(new Stop(line, oid, did, fare, numStop, minTravel));
				}
			} catch (Exception ex) { ex.printStackTrace(); }
		}
		return result;
	}
	
	public ResultSet getQuery(String action) {
		try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "admin",
					"dbgroup20");
			Statement stmt = con.createStatement();

			ResultSet query = stmt.executeQuery(action);

			return query;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
