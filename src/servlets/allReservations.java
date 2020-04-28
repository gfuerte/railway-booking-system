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
		SimpleDateFormat format =  new SimpleDateFormat ("yyyy-MM-dd");
		
        HttpSession session = request.getSession();  
        String username = (String) session.getAttribute("Name");
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        String currentDate = format.format(stamp);
        
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
				if(query.getString("username").equals(username)) {
					int rid = query.getInt("rid");
					double fare = query.getDouble("fare");
					String created = format.format(query.getTimestamp("date"));
					int trainNum = query.getInt("train");
					
					if(created.compareTo(currentDate) >= 0) {
					//current
						insertReservation(current, username, created, rid, trainNum, fare);
					} else {
					//past
						insertReservation(past, username, created, rid, trainNum, fare);
					}

					request.setAttribute("current", current);
					request.setAttribute("past", past);
				}
			}
			
			if(request.getParameter("cancel") != null) {
				String rid = request.getParameter("reservationNumber");
				if(rid != null && !rid.isEmpty()) {
					String delete = "DELETE FROM Reservations WHERE rid=" + rid + ";";
					PreparedStatement statement = con.prepareStatement(delete);
					statement.executeUpdate();
					
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

	public void insertReservation(ArrayList<Reservation> list, String username, String created, int rid, int trainNum, double fare) {
		SimpleDateFormat format =  new SimpleDateFormat ("yyyy-MM-dd 'at' hh:mm:ss");
		try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"admin","dbgroup20");
			Statement stmt = con.createStatement();
			String command = "SELECT * FROM RailwayBookingSystem.Schedule WHERE train=" + trainNum;
			ResultSet query = stmt.executeQuery(command);
			
			while(query.next()) {
				String origin = query.getString("origin");
				String destination = query.getString("destination");
		    	String departure = format.format(query.getTimestamp("departureDatetime"));
		    	String arrival = format.format(query.getTimestamp("arrivalDatetime"));
		    	String line = query.getString("transitLine");
		    	
		    	list.add(new Reservation(created, rid, trainNum, origin, destination, departure, arrival, fare, line));
		    	break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
