package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainScheduleR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Submit Add Train Schedule
		if(request.getParameter("submitAddScheduleR") != null) {
			addSchedule(request, response);
		}
		
	}
	
	/*
	 * Checks to see if schedule with a specified train already exists
	 * If not, add new schedule into SQL table
	 */
	private void addSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		// Connect to SQL database
		String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(url,"admin","dbgroup20");
		
		// Get fields from user input
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String transitLine = request.getParameter("transitline");
		String departureTime = request.getParameter("departureTime");
		String arrivalTime = request.getParameter("arrivalTime");
		String travelTime = request.getParameter("travelTime");
		String fare = request.getParameter("fare");
		String train = request.getParameter("train");
		
		// Query to execute
		String s = "";
		PreparedStatement ps;
		
		// Check to see if train exists
		s = "SELECT train FROM RailwayBookingSystem.Train WHERE idTrain = ?";
		ps = c.prepareStatement(s);
		ps.setString(1, train);
		if (ps.executeQuery() == null) {
		    request.setAttribute("message", "Train " + train + "does not exist. Cannot schedule route.");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainScheduleR.jsp");
            dispatcher.forward(request, response);
            return;
		}
		
		// Check to see if train we want to schedule is already scheduled to run
		s = "SELECT train FROM RailwayBookingSystem.Schedule WHERE train = ?";
		ps = c.prepareStatement(s);
		ps.setString(1, train);
		if (ps.executeQuery() != null) {
		    request.setAttribute("message", "Train already running. Cannot create schedule with train " + train + ".");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainScheduleR.jsp");
            dispatcher.forward(request, response);
            return;
		}
		
		// Train exists and is not in use - can schedule a route
	
		// Check to see that all parameters are filled out
		if (origin.isEmpty() || destination.isEmpty() || transitLine.isEmpty() ||
			departureTime.isEmpty() || arrivalTime.isEmpty() || fare.isEmpty() || train.isEmpty()) {
		    request.setAttribute("message", "Please fill out all parameters to schedule route.");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainScheduleR.jsp");
            dispatcher.forward(request, response);
            return;
		}
		
		// Schedule new route in database
		s = "INSERT INTO RailwayBookingSystem.Schedule(origin, destination, transitLine, availableSeats, stops, departureDatetime, arrivalDatetime, travelTime, fare, train) VALUES (?,?,?,?,?,?,?,?,?)";
		ps = c.prepareStatement(s);
		ps.setString(1, origin);
		ps.setString(2, destination);
		ps.setString(3, transitLine);
		ps.setString(4, departureTime);
		ps.setString(5, arrivalTime);
		ps.setString(6, travelTime);
		ps.setString(7, origin);
		ps.setString(8, origin);
		ps.setString(9, origin);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
