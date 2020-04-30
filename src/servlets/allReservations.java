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

import POJOs.Reservation;
/**
 * Servlet implementation class allReservations
 */
@WebServlet("/allReservations")
public class allReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public allReservations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("goBack") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/loginCustomer.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat simple =  new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat extended =  new SimpleDateFormat ("yyyy-MM-dd 'at' hh:mm:ss");
		
        HttpSession session = request.getSession();  
        String username = (String) session.getAttribute("Name");
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        String currentDate = simple.format(stamp);
        
		String message = "";
	    request.setAttribute("notice", message);
		
	    try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"admin","dbgroup20");
			Statement stmt = con.createStatement();
			
			ResultSet query = stmt.executeQuery("SELECT * FROM RailwayBookingSystem.Reservations");
			
			ArrayList<Reservation> current = new ArrayList<>();
			ArrayList<Reservation> past = new ArrayList<>();
					
			while(query.next()) {
				if(query.getString("customerUsername").equals(username)) {
					int rid = query.getInt("rid");
					int trainNum = query.getInt("train");
					String line = query.getString("transitLine");
					String origin = query.getString("origin");
					String destination = query.getString("destination");
					String departure = extended.format(query.getTimestamp("departureDatetime"));
					String arrival = extended.format(query.getTimestamp("arrivalDatetime"));
					double fee = query.getDouble("fee");
					String seat = query.getString("seat");
					String trainClass = query.getString("class");
					String note = query.getString("note");
					String ticketType = query.getString("ticketType");
					
					String departureDate = simple.format(query.getTimestamp("departureDatetime"));
					if(departureDate.compareTo(currentDate) >= 0) {
					//current
						current.add(new Reservation(currentDate, rid, trainNum, origin, destination, departure, arrival, fee, line, trainClass, note, ticketType, seat));
					} else {
					//past
						past.add(new Reservation(currentDate, rid, trainNum, origin, destination, departure, arrival, fee, line, trainClass, note, ticketType, seat));
					}

					request.setAttribute("current", current);
					request.setAttribute("past", past);
				}
			}
			
			if(request.getParameter("cancel") != null) {
				String rid = request.getParameter("reservationNumber");
				if(rid != null && !rid.isEmpty()) {
					String action = "SELECT train FROM Reservations WHERE rid=" + rid + ";";
					ResultSet reservationQuery = getQuery(action);
					int train = -1;
					while(reservationQuery.next()) train = reservationQuery.getInt("train");
					
					String delete = "DELETE FROM Reservations WHERE rid=" + rid + ";";
					PreparedStatement statement1 = con.prepareStatement(delete);
					statement1.executeUpdate();
					
					String update = "UPDATE RailwayBookingSystem.Schedule SET avaliableSeats=avaliableSeats+1 WHERE train=" + train + ";";
					PreparedStatement statement2 = con.prepareStatement(update);
					statement2.executeUpdate();
					
					message = "Sucessfully Canceled Reservation";
				    request.setAttribute("confirmation", message);
				} else {
					message = "Please Select Reservation Number";
				    request.setAttribute("confirmation", message);
				}
			}
			
	    } catch (Exception ex) {
			ex.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/allReservations.jsp");
		dispatcher.forward(request, response);
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
		} catch (Exception ex) { ex.printStackTrace(); }
		return null;
	}
}
