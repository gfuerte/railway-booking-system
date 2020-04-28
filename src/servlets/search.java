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
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
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
		
		
		try {
			//Get the database connection
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url,"admin","dbgroup20");
			
			String date = request.getParameter("date");
			String sorigin = request.getParameter("origin");
			String sdestination = request.getParameter("destination");
			
			Statement stmt = con.createStatement();
			ResultSet all;
			
			String query = "SELECT * FROM RailwayBookingSystem.Schedule";
			
			if(request.getParameter("showAllStops") != null) {
				all = stmt.executeQuery(query);
			}
			else if(request.getParameter("showStops") != null) {
				String selectedtrainnum = request.getParameter("trainNumber");
				query += " WHERE train = " + selectedtrainnum;
			} else if (request.getParameter("searchconditions") != null) {
				
				if (!date.isEmpty() || !sorigin.isEmpty() || !sdestination.isEmpty()) {
					query += " WHERE";
				}
				
				if (!date.isEmpty()) {
					query += " departureDatetime like \"" + date + "%\""; 
				}
				
				if (!sorigin.isEmpty()) {
					if (!date.isEmpty()) {
						query += " AND origin = \"" + sorigin  + "\"";
					} else {
						query += " origin = \"" + sorigin + "\"";
					}
				}
				
				if (!sdestination.isEmpty()) {
					if (!date.isEmpty() || !sorigin.isEmpty()) {
						query += " AND destination = \"" + sdestination + "\"";
					} else {
						query += " destination = \"" + sdestination + "\"";						
					}
				}
				
				request.setAttribute("da", date);
				request.setAttribute("o", sorigin);
				request.setAttribute("de", sdestination);
			} 
			
			
			if (request.getParameter("sortdeparture") != null || request.getParameter("showStops") != null) {
				
				all = stmt.executeQuery(query + " ORDER BY departureDatetime");
				
			} else if (request.getParameter("sortarrival") != null) {
				
				all = stmt.executeQuery(query + " ORDER BY arrivalDatetime");
				
			} else if (request.getParameter("sortorigin") != null) {
				
				all = stmt.executeQuery(query + " ORDER BY origin");
				
			} else if (request.getParameter("sortdestination") != null) {
				
				all = stmt.executeQuery(query + " ORDER BY destination");
				
			} else if (request.getParameter("sortfare") != null) {
				
				all = stmt.executeQuery(query + " ORDER BY fare");
				
			} else {
				
				all = stmt.executeQuery(query);
			}
			
			ArrayList<TrainSchedule> trains = new ArrayList<TrainSchedule>();
			
			ArrayList<Integer> nums = new ArrayList<Integer>();
		    
		    while(all.next()) {
		    	Integer trainnum = all.getInt("train");
		    	String origin = all.getString("origin");
		    	String destination = all.getString("destination");
		    	String arrival = ft.format(all.getTimestamp("arrivalDatetime"));
		    	String departure = ft.format(all.getTimestamp("departureDatetime"));
		    	Double fare = all.getDouble("fare");
		    	String transitline = all.getString("transitLine");
		    	
		    	trains.add(new TrainSchedule(trainnum, origin, destination, arrival, departure, fare, transitline));
		    	
		    	if(!nums.contains(trainnum)) {
		    		nums.add(trainnum);
		    	}
		    	
		    }
		    
	    	request.setAttribute("list", trains);
	    	request.setAttribute("slist", nums);	
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/schedule.jsp");
		dispatcher.forward(request, response);
			
		
	}
}
