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
		HttpSession session=request.getSession(); 
		
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
			
			if(request.getParameter("showTrains") != null) {
				session.setAttribute("da", null);
				session.setAttribute("o", null);
				session.setAttribute("de", null);
				all = stmt.executeQuery(query);
			}
			else if(request.getParameter("showStops") != null) {
				String selectedtrainnum = request.getParameter("trainNumber");
				query += " WHERE train = " + selectedtrainnum;
			} else if (request.getParameter("searchconditions") != null) {
				if (!date.isEmpty() || !sorigin.isEmpty() || !sdestination.isEmpty()) {
					query += " WHERE";
				
					if (!date.isEmpty()) {
						query += " departureDatetime like \"" + date + "%\""; 
						session.setAttribute("da", date);
					}
					
					if (!sorigin.isEmpty()) {
						if (!date.isEmpty()) {
							query += " AND origin = \"" + sorigin  + "\"";
						} else {
							query += " origin = \"" + sorigin + "\"";
						}
						session.setAttribute("o", sorigin);
					}
					
					if (!sdestination.isEmpty()) {
						if (!date.isEmpty() || !sorigin.isEmpty()) {
							query += " AND destination = \"" + sdestination + "\"";
						} else {
							query += " destination = \"" + sdestination + "\"";						
						}
						session.setAttribute("de", sdestination);
					}
				
				}

			} else if (session.getAttribute("da") != null || session.getAttribute("o") != null || session.getAttribute("de") != null) {
				query += " WHERE";
			
				if (session.getAttribute("da") != null) {
					query += " departureDatetime like \"" + session.getAttribute("da") + "%\""; 
				}
				
				if (session.getAttribute("o") != null) {
					if (session.getAttribute("da") != null) {
						query += " AND origin = \"" + session.getAttribute("o")  + "\"";
					} else {
						query += " origin = \"" + session.getAttribute("o") + "\"";
					}
				}
				
				if (session.getAttribute("de") != null) {
					if (session.getAttribute("da") != null || session.getAttribute("o") != null) {
						query += " AND destination = \"" + session.getAttribute("de") + "\"";
					} else {
						query += " destination = \"" + session.getAttribute("de") + "\"";						
					}
				}
			
			}
				
			
			
			if (request.getParameter("sortdeparture") != null || request.getParameter("showStops") != null) {
				
				query += " ORDER BY departureDatetime";
				
			} else if (request.getParameter("sortarrival") != null) {
				
				query += " ORDER BY arrivalDatetime";
				
			} else if (request.getParameter("sortorigin") != null) {
				
				query += " ORDER BY origin";
				
			} else if (request.getParameter("sortdestination") != null) {
				
				query += " ORDER BY destination";
				
			} else if (request.getParameter("sortfare") != null) {
				
				query += " ORDER BY fare";
				
			}

			all = stmt.executeQuery(query);
			
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
			
//	    	System.out.println(query);
	    	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/schedule.jsp");
		dispatcher.forward(request, response);
		
			
		
	}
}
