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

		String action = "";
		ArrayList<String> possibleOrigins = new ArrayList<>();
		ArrayList<String> possibleDestinations = new ArrayList<>();
		try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"admin","rutgerscs336");
			Statement stmt = con.createStatement();
			
			action = "SELECT DISTINCT origin FROM RailwayBookingSystem.Schedule";
			ResultSet scheduleQuery = stmt.executeQuery(action);

			while (scheduleQuery.next()) possibleOrigins.add(scheduleQuery.getString("origin"));
			
			if(scheduleQuery != null) scheduleQuery.close();				
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		} catch (Exception ex) { ex.printStackTrace(); }
		
		try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"admin","rutgerscs336");
			Statement stmt = con.createStatement();
			
			action = "SELECT DISTINCT destination FROM RailwayBookingSystem.Schedule";
			ResultSet scheduleQuery = stmt.executeQuery(action);

			while (scheduleQuery.next()) possibleDestinations.add(scheduleQuery.getString("destination"));
			if(scheduleQuery != null) scheduleQuery.close();				
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		} catch (Exception ex) { ex.printStackTrace(); }
			
		
		request.setAttribute("pOrigins", possibleOrigins);
		request.setAttribute("pDestinations", possibleDestinations);

		if (request.getParameter("Reservations Options") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/reservationOptions.jsp");
			dispatcher.forward(request, response);
		} else if (request.getParameter("submitOptions") != null) {
			String selectedOrigin = request.getParameter("origin");
			String selectedDestination = request.getParameter("destination");
			SimpleDateFormat format =  new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			
			if (selectedOrigin != null && selectedDestination != null && !selectedOrigin.isEmpty() && !selectedDestination.isEmpty()) {
				ArrayList<Train> possibleTrains = new ArrayList<>();
				try {
					String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(url,"admin","rutgerscs336");
					Statement stmt = con.createStatement();
					
					action = "SELECT * FROM RailwayBookingSystem.Schedule WHERE origin=\"" + selectedOrigin + "\" AND destination=\"" + selectedDestination + "\";";
					ResultSet query = stmt.executeQuery(action);
					
					while(query.next()) {
						int trainNum = query.getInt("train");
						String line = query.getString("transitLine");
						int availableSeats = query.getInt("avaliableSeats");
						String departure = format.format(query.getTimestamp("departureDatetime"));
						String arrival = format.format(query.getTimestamp("arrivalDatetime"));
						double fare = query.getDouble("fare");
						int numStops = query.getInt("stops");
						
						if(availableSeats <= 0) continue;
						
						possibleTrains.add(new Train(trainNum, line, selectedOrigin, selectedDestination, availableSeats, departure, arrival, fare, numStops));
					}
					
					if(query != null) query.close();				
					if(stmt != null) stmt.close();
					if(con != null) con.close();
				} catch (Exception ex) { ex.printStackTrace(); }							
				if(possibleTrains.size() > 0) {
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
				optionsMessage = "Please Select an Origin and Destination";
				request.setAttribute("optionsMessage", optionsMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/reservationOptions.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
