package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class adminFunctions
 */
@WebServlet("/representativeFunctions")
public class representativeFunctions extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public representativeFunctions() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Go back to main Representative Screen
		if(request.getParameter("returnToMainR") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/loginRepresentative.jsp");
			dispatcher.forward(request, response);
		}
		
		// Go back to View Train Schedule Menu
		if(request.getParameter("returnToScheduleViewR") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.forward(request, response);
		}
		
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// View Reservations
		if(request.getParameter("viewReservationsR") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewReservationsR.jsp");
			dispatcher.forward(request, response);
		}
		
		// View Train Schedules
		if(request.getParameter("viewSchedulesR") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Add Train Schedule Menu
		if(request.getParameter("addScheduleR") != null) {
			getScheduleOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainScheduleR.jsp");
			dispatcher.forward(request, response);
		}
		
		if (request.getParameter("addNewSchedule") != null) {			
			confirmAddSchedule(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.include(request, response);
		}
		
	}
	
	
	/*
	 * ADDING SCHEDULE
	 * Gets data for drop down lists - transit lines, origins, available trains, times (15 minute intervals)
	 */
	private void getScheduleOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","dbgroup20");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			ResultSet rs3 = null;
			ArrayList<String> alTransitLines = new ArrayList<>();
			ArrayList<String> alOrigins = new ArrayList<>();
			ArrayList<Integer> alTrains = new ArrayList<>();
			ArrayList<String> alTimes = new ArrayList<>();
			
			// Get list of transit lines
			s = "SELECT transitLine FROM Route";
			ps = c.prepareStatement(s);
			rs1 = ps.executeQuery();
			while(rs1.next()) { alTransitLines.add(rs1.getString(1)); }
			request.setAttribute("transitLineList", alTransitLines);

			// Get list of origins
			s = "(SELECT name FROM Station WHERE idStation IN (SELECT station1 FROM Route)) UNION DISTINCT (SELECT name FROM Station WHERE idStation IN (SELECT station2 FROM Route))";
			ps = c.prepareStatement(s);
			rs2 = ps.executeQuery();
			while(rs2.next()) { alOrigins.add(rs2.getString(1)); }
			request.setAttribute("originList", alOrigins);

			// Get list of available trains
			s = "SELECT idTrain FROM Train WHERE idTrain NOT IN (SELECT train FROM Schedule)";
			ps = c.prepareStatement(s);
			rs3 = ps.executeQuery();
			while(rs3.next()) { alTrains.add(rs3.getInt(1)); }
			request.setAttribute("trainList", alTrains);
			
			// Make list of times in 15 minute intervals
			DateFormat df = new SimpleDateFormat("HH:mm");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			int startDate = cal.get(Calendar.DATE);
			while (cal.get(Calendar.DATE) == startDate) {
			    alTimes.add(df.format(cal.getTime()));
			    cal.add(Calendar.MINUTE, 15);
			    
			}
			request.setAttribute("timeList", alTimes);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
	/*
	 * ADDING SCHEDULE
	 * Adds a new schedule if no errors
	 */
	private void confirmAddSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","dbgroup20");

			// Query to execute
			String s = "";
			PreparedStatement ps;

			// User inputs
			String transitLine = request.getParameter("selectTransitLine");
			String origin = request.getParameter("selectOrigin");
			s = "SELECT idStation FROM Station WHERE name = ?";
			ps = c.prepareStatement(s);
			ps.setString(1, origin);
			ResultSet orig = ps.executeQuery();
			int o = 0;
			while (orig.next()) { o = orig.getInt(1); }
			int train = Integer.parseInt(request.getParameter("selectTrain")); 
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("selectDate"));
			Date time = new SimpleDateFormat("HH:mm").parse(request.getParameter("selectTime"));
			Date dt = dateTime(date, time);
			
			// Check that selected origin is part of transit line
			s = "SELECT station1, station2 FROM Route WHERE transitLine = ?";
			ps = c.prepareStatement(s);
			ps.setString(1, transitLine);
			ResultSet possibleOrigins = ps.executeQuery();
			int po1 = -1, po2 = -1;;
			while(possibleOrigins.next()) {
				po1 = possibleOrigins.getInt(1);
				po2 = possibleOrigins.getInt(2);
			}
			if (po1 != o && po2 != o) {
				PreparedStatement ps1 = c.prepareStatement("SELECT name FROM Station WHERE idStation = " + po1);
				PreparedStatement ps2 = c.prepareStatement("SELECT name FROM Station WHERE idStation = " + po2);
				ResultSet rs1 = ps1.executeQuery();
				ResultSet rs2 = ps2.executeQuery();
				String porig1 = "", porig2 = "";
				while(rs1.next() && rs2.next()) {
					porig1 = rs1.getString(1);
					porig2 = rs2.getString(1);
				}
				String m = transitLine + " cannot originate from " + origin + ". " + transitLine + " must originate from " + porig1 + " or " + porig2 +".";
				request.setAttribute("message", m);
				getScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainScheduleR.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
		
			
			
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	
	}
	
	/*
	 * Combine date and time into one
	 */
	private Date dateTime(Date date, Date time) {

	    Calendar aDate = Calendar.getInstance();
	    aDate.setTime(date);

	    Calendar aTime = Calendar.getInstance();
	    aTime.setTime(time);

	    Calendar aDateTime = Calendar.getInstance();
	    aDateTime.set(Calendar.DAY_OF_MONTH, aDate.get(Calendar.DAY_OF_MONTH));
	    aDateTime.set(Calendar.MONTH, aDate.get(Calendar.MONTH));
	    aDateTime.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
	    aDateTime.set(Calendar.HOUR, aTime.get(Calendar.HOUR));
	    aDateTime.set(Calendar.MINUTE, aTime.get(Calendar.MINUTE));
	    aDateTime.set(Calendar.SECOND, aTime.get(Calendar.SECOND));

	    return aDateTime.getTime();
	}   

}
