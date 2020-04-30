package servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import POJOs.Reservation;
import POJOs.Train; 

/**
 * Servlet implementation class finalizeReservation
 */
@WebServlet("/finalizeReservation")
public class finalizeReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public finalizeReservation() {
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
		SimpleDateFormat format =  new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		HttpSession session = request.getSession();  
		String message = "";
	    request.setAttribute("finalizeMessage", message);
	    
	    ArrayList<Train> selected = (ArrayList<Train>) session.getAttribute("selectedTrainSession");
	    Train train = selected.get(0);
	    double fare = train.fare;
	    
	    if(request.getParameter("select") != null) {
	    	String ticket = request.getParameter("ticket");
	    	String trainClass = request.getParameter("class");
	    	
	    	if(ticket != null && trainClass != null && !ticket.isEmpty() && !trainClass.isEmpty()) {
	    		int rid = (int)(Math.random()*9999); 
	    		while (checkRID(rid) == false) rid = (int)(Math.random()*9999);
	    		double fee = 0;
	    		Timestamp date = new Timestamp(System.currentTimeMillis());
	    		int trainNum = train.getTrainNum();
	    		String line = train.getLine();
	    		String origin = train.getOrigin();
	    		String destination = train.getDestination();
	    		String departureString = train.getDeparture();
	    		String arrivalString = train.getArrival();
	    		String username = (String) session.getAttribute("Name");
	    		String note = null;
	    		String ticketType = null;
	    		int seat = -1;
	    		
	    		Timestamp departure = null;
	    		Timestamp arrival = null;
	    		try {
		    		Date d = (Date) format.parse(departureString);
		    		Date a = (Date) format.parse(arrivalString);
		    		departure = new Timestamp(d.getTime());
		    		arrival = new Timestamp(a.getTime());
	    		} catch (ParseException e) { };
	    		System.out.println("departure: " + departure);
	    		System.out.println("arrival: " + arrival);
	    		
	    		//Ticket
	    		int value = Integer.parseInt(ticket);
	    		if(value == 1) { //owa
	    			fee = fare;
	    			note = "Adult";
	    			ticketType = "One Way";
	    		} else if (value == 2) { //owc
	    			fee = fare;
	    			note = "Child";
	    			ticketType = "One Way";
	    		}  else if (value == 3) { //owd
	    			fee = fare/2;
	    			note = "Sr/Dis";
	    			ticketType = "One Way";
	    		}  else if (value == 4) { //rta 
	    			fee = fare*2;
	    			note = "Adult";
	    			ticketType = "Round Trip";
	    		}  else if (value == 5) { //rtc
	    			fee = fare;
	    			note = "Child";
	    			ticketType = "Round Trip";
	    		} else if (value == 6) { //rtd
	    			fee = fare;
	    			note = "Sr/Dis";
	    			ticketType = "Round Trip";
	    		} else if (value == 7) { //wt
	    			fee = fare*7;
	    			note = "Open";
	    			ticketType = "Weekly Ticket";
	    		} else if (value == 8) { //mt
	    			fee = fare*28;
	    			note = "Open";
	    			ticketType = "Monthly Ticket";
	    		}
	    		
	    		if(trainClass.equals("Business")) {
	    			fee = fee*1.3;
	    		} else if(trainClass.equals("First")) {
	    			fee = fee*1.5;
	    		}
	    		
	    		//Seat
	    		String action = "SELECT avaliableSeats FROM RailwayBookingSystem.Schedule WHERE train=" + trainNum + ";";
	    		System.out.println("action is: " + action);
	    		ResultSet query = getQuery(action);
	    		try {
	    			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
	    			Class.forName("com.mysql.jdbc.Driver");
	    			Connection con = DriverManager.getConnection(url,"admin","dbgroup20");
	    			
	    			while(query.next()) seat = query.getInt("avaliableSeats");
	    			
	    			if(fee <= 0 || departure == null || arrival == null || line == null || origin == null || destination == null || departure == null || arrival == null || username == null || note == null || ticketType == null || seat <= 0) {
	    				request.setAttribute("selectedTrainRequest", selected);
	    	    		request.setAttribute("owa", fare);
	    	    		request.setAttribute("owc", fare/2);
	    	    		request.setAttribute("owd", fare/2);
	    	    		request.setAttribute("rta", fare*2);
	    	    		request.setAttribute("rtc", fare);
	    	    		request.setAttribute("rtd", fare);
	    	    		request.setAttribute("wt", fare*7);
	    	    		request.setAttribute("mt", fare*28);  
	    	    		
	    		    	message = "Something Went Wrong. Please Logout And Retry Again";
	    			    request.setAttribute("finalizeMessage", message);
	    			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/finalizeReservation.jsp");
	    				dispatcher.forward(request, response);
	    			}
	    			
	    			System.out.println(seat);
	    			String update = "UPDATE RailwayBookingSystem.Schedule SET avaliableSeats=avaliableSeats-1 WHERE train=" + trainNum + ";";
	    			System.out.println(update);
	    			PreparedStatement statement = con.prepareStatement(update);
	    			statement.executeUpdate();
	    			
	    			//Insert Reservation
	    			String insert = "INSERT INTO RailwayBookingSystem.Reservations(`rid`,`fee`, `date`, `train`, `transitLine`, `origin`, `destination`, `departureDatetime`, `arrivalDatetime`, `customerUsername`, `class`, `note`, `ticketType`, `seat`)"
	    					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	    			PreparedStatement insertPS = con.prepareStatement(insert);
	    			insertPS.setInt(1, rid);
	    			insertPS.setDouble(2, fee);
	    			insertPS.setTimestamp(3, date);
	    			insertPS.setInt(4, trainNum);
	    			insertPS.setString(5, line);
	    			insertPS.setString(6, origin);
	    			insertPS.setString(7, destination);
	    			insertPS.setTimestamp(8, departure);
	    			insertPS.setTimestamp(9, arrival);
	    			insertPS.setString(10, username);
	    			insertPS.setString(11, trainClass);
	    			insertPS.setString(12, note);
	    			insertPS.setString(13, ticketType);
	    			insertPS.setInt(14, seat);
	    			
	    			
	    			System.out.println(insertPS);
	    			insertPS.executeUpdate();
	    			
	    			message = "Successfully Created Reservation";
				    request.setAttribute("success", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/allReservations.jsp");
					dispatcher.forward(request, response);
	    			
	    		} catch (Exception ex) { ex.printStackTrace(); }
	    	} else {
	    		request.setAttribute("selectedTrainRequest", selected);
	    		request.setAttribute("owa", fare);
	    		request.setAttribute("owc", fare/2);
	    		request.setAttribute("owd", fare/2);
	    		request.setAttribute("rta", fare*2);
	    		request.setAttribute("rtc", fare);
	    		request.setAttribute("rtd", fare);
	    		request.setAttribute("wt", fare*7);
	    		request.setAttribute("mt", fare*28);  
	    		
		    	message = "Please Select Ticket";
			    request.setAttribute("finalizeMessage", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/finalizeReservation.jsp");
				dispatcher.forward(request, response);
	    	}
	    }   
	}
	
	public boolean checkRID(int rid) {
		try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"admin","dbgroup20");
			Statement stmt = con.createStatement();
			ResultSet query = stmt.executeQuery("SELECT * FROM RailwayBookingSystem.Reservations WHERE rid=" + rid + ";");
			
			int count = 0;
			while(query.next()) count++;
			if(count != 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
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
