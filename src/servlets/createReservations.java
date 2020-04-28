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

import POJOs.Alert;
import POJOs.TrainSchedule;

/**
 * Servlet implementation class search
 */
@WebServlet("/createReservations")
public class createReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createReservations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getParameter("goBack") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/loginCustomer.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd 'at' hh:mm:ss");
        HttpSession session = request.getSession();  
		String message = "";
	    request.setAttribute("confirmation", message);
		
		try {
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"admin","dbgroup20");
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM RailwayBookingSystem.Schedule";
				
			if (request.getParameter("reset") != null) {
				query = "SELECT * FROM RailwayBookingSystem.Schedule";
			} else if (request.getParameter("filter") != null) {
				String origin = request.getParameter("origin");
				String destination = request.getParameter("destination");
				
				query = "SELECT * FROM RailwayBookingSystem.Schedule";
				
				if ((origin!= null && !origin.isEmpty()) || (destination != null &&!destination.isEmpty())) {
					query += " WHERE";
				}
				
				if (origin!= null && !origin.isEmpty()) {
					query += " origin LIKE \"" + origin + "%\"";
				}
				
				if (destination != null &&!destination.isEmpty()) {
					if (!origin.isEmpty()) {
						query += " AND destination LIKE \"" + destination + "%\"";
					} else {
						query += " destination LIKE \"%" + destination + "%\"";
					}
				}
			} 
			
			ResultSet all = stmt.executeQuery(query);
				
			ArrayList<TrainSchedule> trains = new ArrayList<>();
		    
		    while(all.next()) {
		    	int trainnum = all.getInt("train");
		    	String origin = all.getString("origin");
		    	String destination = all.getString("destination");
		    	String arrival = ft.format(all.getTimestamp("arrivalDatetime"));
		    	String departure = ft.format(all.getTimestamp("departureDatetime"));
		    	Double fare = all.getDouble("fare");
		    	String transitline = all.getString("transitLine");
		    	
		    	trains.add(new TrainSchedule(trainnum, origin, destination, arrival, departure, fare, transitline));	
		    }
		    
		    request.setAttribute("list", trains);
		    if(request.getParameter("reserve") != null) {
				String trainNumber = request.getParameter("trainNumber");
				if(trainNumber != null && !trainNumber.isEmpty()) {
					int rid = (int)(Math.random()*9999); 
					while (checkRID(rid) == false) rid = (int)(Math.random()*9999);
					double fare;
					String username;
					Timestamp date;
					int train;
					
					for(int i = 0; i < trains.size(); i++) {
						if((trains.get(i).getTrainnum() == Integer.parseInt(trainNumber))) {	
							fare = trains.get(i).getFare();
							username = (String)session.getAttribute("Name");
							date = new Timestamp(System.currentTimeMillis());
							train = trains.get(i).getTrainnum();
							
							System.out.println("rid is: " + rid);
							System.out.println("fare is: " + fare);
							System.out.println("username is: " + username);
							System.out.println("date is: " + date);
							System.out.println("train is: " + train);
							
							String insert = "INSERT INTO RailwayBookingSystem.Reservations(`rid`,`fare`,`username`, `date`, `train`) VALUES (?,?,?,?,?);";

							PreparedStatement statement = con.prepareStatement(insert);
							statement.setInt(1, rid);
							statement.setDouble(2, fare);
							statement.setString(3, username);
							statement.setTimestamp(4, date);
							statement.setInt(5, train);

							statement.executeUpdate();
							
							message = "Sucessfully Created Reservation";
						    request.setAttribute("confirmation", message);
							break;
							
						}
					}
				} else {
					message = "Please Select Train Number";
				    request.setAttribute("confirmation", message);
				}
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/createReservations.jsp");
		dispatcher.forward(request, response);
			
		
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
}
