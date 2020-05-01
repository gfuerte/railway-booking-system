package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
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
import javax.servlet.http.HttpSession;

import POJOs.ReservationR;
import POJOs.StopR;


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
			getScheduleView(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Go back to View Reservation Menu
		if(request.getParameter("returnToReservationViewR") != null) {
			getReservationOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewReservationsR.jsp");
			dispatcher.forward(request, response);
		}
		
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// View Train Schedules
		if(request.getParameter("viewSchedulesR") != null) {
			getScheduleView(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Add Train Schedule Menu
		if(request.getParameter("addScheduleR") != null) {
			getAddScheduleOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainSchedulesR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Confirm Adding Schedule
		if (request.getParameter("addNewSchedule") != null) {			
			confirmAddSchedule(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.include(request, response);
		}
		
		// Delete Train Schedule Menu
		if (request.getParameter("deleteScheduleR") != null) {			
			getDeleteScheduleOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/deleteTrainSchedulesR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Confirm Deleting Schedule
		if (request.getParameter("deleteFromSchedule") != null) {			
			confirmDeleteSchedule(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.include(request, response);
		}
		
		// Edit Train Schedule Menu
		if (request.getParameter("editScheduleR") != null) {			
			getEditScheduleOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editTrainSchedulesR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Confirm Editing Schedule
		if (request.getParameter("editTrainSchedule") != null) {			
			confirmEditSchedule(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewTrainSchedulesR.jsp");
			dispatcher.include(request, response);
		}
		
		// View Reservations
		if(request.getParameter("viewReservationsR") != null) {
			getReservationOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewReservationsR.jsp");
			dispatcher.forward(request, response);
		}
		
		// Delete Reservation
		if(request.getParameter("deleteReservationR") != null) {
			deleteReservation(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewReservationsR.jsp");
			dispatcher.include(request, response);
		}
		
		// Edit Reservation Menu
		if(request.getParameter("editReservationR") != null) {
			getEditReservationOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editReservation.jsp");
			dispatcher.forward(request, response);
		}
		
		// Confirm Editing Reservation
		if(request.getParameter("editReservation") != null) {
			confirmEditReservation(request, response);
			getEditReservationOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewReservationsR.jsp");
			dispatcher.include(request, response);
		}
		
		// Add Reservation View
		if(request.getParameter("addReservationR") != null) {
			getAddReservationOptions(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addReservation.jsp");
			dispatcher.include(request, response);
		}
		
		if(request.getParameter("getReservationsByTrain") != null) {
			if(request.getParameter("transitLine").isEmpty() || request.getParameter("trainNum").isEmpty()) {
				String message = "Please fill out both train information fields.";
			    request.setAttribute("message", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/loginRepresentative.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/listOfCustomerSeats.jsp");
			dispatcher.forward(request, response);
		}

		if(request.getParameter("getSchedule") != null) {
			if(request.getParameter("origin").isEmpty() || request.getParameter("destination").isEmpty()) {
				String message = "Please fill out both fields.";
			    request.setAttribute("message2", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/loginRepresentative.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/scheduleList.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("getScheduleByStation") != null) {
			if(request.getParameter("station").isEmpty()) {
				String message = "Please fill out station field.";
			    request.setAttribute("message3", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/loginRepresentative.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/scheduleList.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		
	}

	/*
	 * VIEWING SCHEDULE
	 */
	private void getScheduleView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArrayList<StopR> al = getStops(request, response);
			request.setAttribute("scheduleList", al);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
	
	/*
	 * ADDING SCHEDULE
	 * Gets data for drop down lists - transit lines, origins, available trains, times (15 minute intervals)
	 */
	private void getAddScheduleOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

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
			rs1.close();

			// Get list of origins
			s = "(SELECT name FROM Station WHERE idStation IN (SELECT station1 FROM Route)) UNION DISTINCT (SELECT name FROM Station WHERE idStation IN (SELECT station2 FROM Route))";
			ps = c.prepareStatement(s);
			rs2 = ps.executeQuery();
			while(rs2.next()) { alOrigins.add(rs2.getString(1)); }
			request.setAttribute("originList", alOrigins);
			rs2.close();

			// Get list of available trains
			s = "SELECT idTrain FROM Train WHERE idTrain NOT IN (SELECT train FROM Schedule)";
			ps = c.prepareStatement(s);
			rs3 = ps.executeQuery();
			while(rs3.next()) { alTrains.add(rs3.getInt(1)); }
			request.setAttribute("trainList", alTrains);
			rs3.close();
			
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
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;

			// User inputs
			String transitLine = request.getParameter("selectTransitLine");
			String origin = request.getParameter("selectOrigin");
			String ptrain = request.getParameter("selectTrain");
			String pdate = request.getParameter("selectDate");
			String ptime = request.getParameter("selectTime");
			
			// Check for null values
			if (ptrain == null || pdate == null || ptime == null) {
				String m = "Must fill in all fields. Please fill in all fiends and try again.";
				request.setAttribute("message", m);
				getAddScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			s = "SELECT idStation FROM Station WHERE name = ?";
			ps = c.prepareStatement(s);
			ps.setString(1, origin);
			ResultSet orig = ps.executeQuery();
			int o = 0;
			while (orig.next()) { o = orig.getInt(1); }
			orig.close();
			
			int train = Integer.parseInt(request.getParameter("selectTrain")); 
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("selectDate"));
			Date time = new SimpleDateFormat("HH:mm").parse(request.getParameter("selectTime"));
			Date dt = dateTime(date, time);
			
			// Get possible origins for a transit line
			s = "SELECT station1, station2 FROM Route WHERE transitLine = ?";
			ps = c.prepareStatement(s);
			ps.setString(1, transitLine);
			ResultSet possibleOrigins = ps.executeQuery();
			int po1 = -1, po2 = -1;;
			while(possibleOrigins.next()) {
				po1 = possibleOrigins.getInt(1);
				po2 = possibleOrigins.getInt(2);
			}
			possibleOrigins.close();
			PreparedStatement ps1 = c.prepareStatement("SELECT name FROM Station WHERE idStation = " + po1);
			PreparedStatement ps2 = c.prepareStatement("SELECT name FROM Station WHERE idStation = " + po2);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			String porig1 = "", porig2 = "";
			while(rs1.next() && rs2.next()) {
				porig1 = rs1.getString(1);
				porig2 = rs2.getString(1);
			}
			rs1.close();
			rs2.close();

			// Check that selected origin is part of transit line
			if (po1 != o && po2 != o) {
				String m = transitLine + " cannot originate from " + origin + ". " + transitLine + " must originate from " + porig1 + " or " + porig2 +".";
				request.setAttribute("message", m);
				getAddScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/addTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// Get number of seats on train
			s = "SELECT numSeats FROM Train where idTrain = " + train;
			ps = c.prepareStatement(s);
			ResultSet ns = ps.executeQuery();
			int seats = 0;
			while(ns.next()) { seats = ns.getInt(1); }
			ns.close();
			
			// Get all stops in selected transit line
			s = "SELECT t1.transitLine, Station.name station1, t1.station2, t1.fare, t1.numStop, t1.minTravel, t1.station1 idStation1, t1.idStation2 FROM (SELECT Stop.transitLine, Stop.station1, Station.name station2, Stop.fare, Stop.numStop, Stop.minTravel, Stop.Station2 idStation2 FROM Stop JOIN Station ON Stop.station2=Station.idStation WHERE Stop.transitLine = ?) t1 JOIN Station ON Station.idStation=t1.station1";
			ps = c.prepareStatement(s);
			ps.setString(1, transitLine);
			ResultSet rs = ps.executeQuery();
			
			// Check to see where origin is in table -- station 1 or 2
			int whereO = 1;
			while(rs.next()) {
				if(rs.getString(3).equals(origin)) { whereO = 2; break; }
			}
			rs.beforeFirst();
			
			// Insert new entries into schedule table
			while(rs.next()) {
				s = "INSERT INTO RailwayBookingSystem.Schedule(origin, destination, transitLine, avaliableSeats, stops, departureDatetime, arrivalDatetime, travelTime, fare, train) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				ps = c.prepareStatement(s);
				if (whereO == 1) {
					ps.setString(1, rs.getString(2));
					ps.setString(2, rs.getString(3));
				} else {
					ps.setString(1, rs.getString(3));
					ps.setString(2, rs.getString(2));
				}
				ps.setString(3, transitLine);
				ps.setInt(4, seats);
				ps.setInt(5, rs.getInt(5));
				
				
				Date dest = calculateTime(dt, 0);
				if (o == rs.getInt(7) || o == rs.getInt(8)) {
					ps.setTimestamp(6, new Timestamp(dt.getTime()));
				} else {
					String q = "SELECT minTravel FROM Stop WHERE station1 = ? AND station2 = ? AND TransitLine = ?";
					PreparedStatement qs = c.prepareStatement(q);
					if (whereO == 1) {
						qs.setInt(1, o);
						qs.setInt(2, rs.getInt(7));
					} else {
						qs.setInt(1, rs.getInt(7));
						qs.setInt(2, o);
					}
					qs.setString(3, transitLine);					
					ResultSet qr = qs.executeQuery();
					qr.next();
					dest = calculateTime(dest, qr.getInt(1));
					ps.setTimestamp(6, new Timestamp(dest.getTime()));
					qr.close();
				}
				String q = "SELECT minTravel FROM Stop WHERE station1 = ? AND station2 = ? AND TransitLine = ?";
				PreparedStatement qs = c.prepareStatement(q);
				qs.setInt(1, rs.getInt(7));
				qs.setInt(2, rs.getInt(8));
				qs.setString(3, transitLine);					
				ResultSet qr = qs.executeQuery();
				qr.next();
				Date arrt= calculateTime(dest, qr.getInt(1));
				ps.setTimestamp(7, new Timestamp(arrt.getTime()));

				ps.setTimestamp(8, new Timestamp(convertMin(rs.getInt(6)).getTime()));
				ps.setDouble(9, rs.getDouble(4));
				ps.setInt(10, train);
			
				ps.executeUpdate();
			}
			rs.close();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	
	}
	
	/*
	 * DELETING SCHEDULE
	 * Gets data from drop down lists - by train or transitLine
	 */
	private void getDeleteScheduleOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			ArrayList<String> alTransitLines = new ArrayList<>();
			ArrayList<Integer> alTrains = new ArrayList<>();
			
			// Get list of transit lines
			s = "SELECT DISTINCT transitLine FROM Schedule";
			ps = c.prepareStatement(s);
			rs1 = ps.executeQuery();
			while(rs1.next()) { alTransitLines.add(rs1.getString(1)); }
			request.setAttribute("transitLineList", alTransitLines);
			rs1.close();

			// Get list of available trains
			s = "SELECT DISTINCT train FROM Schedule";
			ps = c.prepareStatement(s);
			rs2 = ps.executeQuery();
			while(rs2.next()) { alTrains.add(rs2.getInt(1)); }
			request.setAttribute("trainList", alTrains);
			rs2.close();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}	
	
	/*
	 * DELETING SCHEDULE
	 * Deletes a schedule if no errors
	 */
	private void confirmDeleteSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;

			// User inputs
			String transitLine = request.getParameter("selectTransitLine");
			String ptrain = request.getParameter("selectTrain");
			
			// Error if user doesn't select one of the two options
			if (transitLine == null && ptrain == null) {
				String m = "Must select either Transit Line or Train Number to delete. Please select one.";
				request.setAttribute("message", m);
				getDeleteScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/deleteTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// Error if user selects both options
			if (transitLine != null && ptrain != null) {
				String m = "Cannot select both Transit Line or Train Number to delete. Please select one.";
				request.setAttribute("message", m);
				getDeleteScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/deleteTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// Delete by transit line
			if (transitLine != null) {
				s = "DELETE FROM Schedule WHERE transitLine = ?";
				ps = c.prepareStatement(s);
				ps.setString(1, transitLine);
				ps.executeUpdate();
			}
			
			// Delete by train number
			if (ptrain != null) {
				s = "DELETE FROM Schedule WHERE train = ?";
				ps = c.prepareStatement(s);
				ps.setInt(1, Integer.parseInt(ptrain));
				ps.executeUpdate();
			}
			
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
	
	/*
	 * EDITING SCHEDULE
	 * Gets data from server to display
	 */
	private void getEditScheduleOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			ArrayList<Integer> alTrains = new ArrayList<>();
			ArrayList<Integer> alNewTrains = new ArrayList<>();
			
			// Get list of trains
			s = "SELECT DISTINCT train FROM Schedule";
			ps = c.prepareStatement(s);
			rs1 = ps.executeQuery();
			while(rs1.next()) { alTrains.add(rs1.getInt(1)); }
			request.setAttribute("trainList", alTrains);
			rs1.close();

			// Get list of available trains
			s = "SELECT idTrain FROM Train WHERE idTrain NOT IN (SELECT train FROM Schedule)";
			ps = c.prepareStatement(s);
			rs2 = ps.executeQuery();
			while(rs2.next()) { alNewTrains.add(rs2.getInt(1)); }
			request.setAttribute("newTrainList", alNewTrains);
			rs2.close();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
	
	/*
	 * EDITING SCHEDULE
	 * Edits schedule if no errors
	 */
	private void confirmEditSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Get user inputs
			String pTrain = request.getParameter("selectTrain");
			String minDelay = request.getParameter("minDelay");
			String minEarly = request.getParameter("minEarly");
			String discountPrice = request.getParameter("discountPrice");
			String raisedPrice = request.getParameter("raisedPrice");
			String pNewTrain = request.getParameter("selectNewTrain");
			
			// Check if user selected a train to edit
			if (pTrain == null) {
				String m = "Must select train to edit on schedule. Please select one.";
				request.setAttribute("message", m);
				getEditScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}
			int train = Integer.parseInt(pTrain);
			
			// Check to see if user chose to both delay and make train early
			if (!minDelay.equals("") && !minEarly.equals("")) {
				String m = "Train cannot be both delayed and early. Please select one.";
				request.setAttribute("message", m);
				getEditScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// Check to see if user both discounted and raised prices
			if (!discountPrice.equals("") && !raisedPrice.equals("")) {
				String m = "Tickets cannot be both discounted and raised. Please select one.";
				request.setAttribute("message", m);
				getEditScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// Check for negative numbers
			if ((!minDelay.equals("") && Integer.parseInt(minDelay) < 0) ||
				(!minEarly.equals("") && Integer.parseInt(minEarly) < 0) ||
				(!discountPrice.equals("") && Double.parseDouble(discountPrice) < 0) ||
				(!raisedPrice.equals("") && Double.parseDouble(raisedPrice) < 0)) {
					String m = "Entries cannot be negative. Please enter positive values.";
					request.setAttribute("message", m);
					getEditScheduleOptions(request, response);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editTrainSchedulesR.jsp");
					dispatcher.forward(request, response);
					return;
			}
			
			// Let user know they didn't select anything
			if (minDelay.equals("") && minEarly.equals("") && discountPrice.equals("") && raisedPrice.equals("") && pNewTrain == null) {
				String m = "No edits were selected. Please select an edit or return to view schedule.";
				request.setAttribute("message", m);
				getEditScheduleOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editTrainSchedulesR.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// Mark train as delayed or early
			if (!minDelay.equals("") || !minEarly.equals("")) {
				// Get the minutes delayed/early
				int delT = (!minDelay.equals("")) ? Integer.parseInt(minDelay) : Integer.parseInt(minEarly)*-1;
				// Update schedule with delayed/early train
				s = "UPDATE Schedule SET departureDatetime = DATE_ADD(departureDatetime, INTERVAL ? MINUTE), arrivalDatetime = DATE_ADD(arrivalDatetime, INTERVAL ? MINUTE) WHERE train = ?";
				ps = c.prepareStatement(s);
				ps.setInt(1, delT);
				ps.setInt(2, delT);
				ps.setInt(3, train);
				ps.executeUpdate();
				// Update schedule with delayed/early reservations
				s = "UPDATE Reservations SET departureDatetime = DATE_ADD(departureDatetime, INTERVAL ? MINUTE), arrivalDatetime = DATE_ADD(arrivalDatetime, INTERVAL ? MINUTE) WHERE train = ?";
				ps = c.prepareStatement(s);
				ps.setInt(1, delT);
				ps.setInt(2, delT);
				ps.setInt(3, train);
				ps.executeUpdate();
			}
			
			// Discount or raise ticket prices for a train
			if (!discountPrice.equals("") || !raisedPrice.equals("")) {
				double delP = (!discountPrice.equals("")) ? Double.parseDouble(discountPrice)*-1 : Double.parseDouble(raisedPrice);
				s = "UPDATE Schedule SET fare = fare + ? WHERE train = ?";
				ps = c.prepareStatement(s);
				ps.setDouble(1, delP);
				ps.setInt(2, train);
				ps.executeUpdate();
			}
			
			// Update train number (new train) for route
			if (pNewTrain != null) {
				ResultSet rs = null;
				// Get number of seats on new train
				int newTrain = Integer.parseInt(pNewTrain);
				s = "SELECT numSeats FROM Train WHERE idTrain = " + newTrain;
				ps = c.prepareStatement(s);
				rs = ps.executeQuery();
				int numSeats = 0;
				while(rs.next()) { numSeats = rs.getInt(1); }
				rs.close();
				// Get number of reservations on current train
				s = "SELECT COUNT(*) FROM Reservations WHERE train = " + newTrain;
				ps = c.prepareStatement(s);
				rs = ps.executeQuery();
				int ocpSeats = 0;
				while(rs.next()) { ocpSeats = rs.getInt(1); }
				rs.close();
				// Update schedule with new train
				s = "UPDATE Schedule SET train = ?, avaliableSeats = ? WHERE train = ?";
				ps = c.prepareStatement(s);
				ps.setInt(1, newTrain);
				ps.setInt(2, numSeats - ocpSeats);
				ps.setInt(3, train);
				ps.executeUpdate();
				// Update reservations with new train
				s = "UPDATE Reservations SET train = ? WHERE train = ?";
				ps.setInt(1, newTrain);
				ps.setInt(2, train);
			}
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}

	/*
	 * VIEW RESERVATIONS
	 * Get a drop down list of all reservations
	 */
	private void getReservationOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ArrayList<ReservationR> ar = getReservations(request, response);
			request.setAttribute("allReservation", ar);
			
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs = null;
			ArrayList<String> al = new ArrayList<>();
			
			// Get list of transit lines
			s = "SELECT rid FROM Reservations";
			ps = c.prepareStatement(s);
			rs = ps.executeQuery();
			while(rs.next()) { al.add(rs.getString(1)); }
			request.setAttribute("resList", al);
			rs.close();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
	/*
	 * DELETING RESERVATION
	 * Delete selected reservation from dropdown list on View Reservation Menu
	 */
	private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
			
			// Get user input
			String prid = request.getParameter("selectRes");
			
			// Check to see if rid field is valid
			if (prid == null) {
				String m = "No reservation selected. Please select an rid.";
				request.setAttribute("message", m);
				getReservationOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/viewReservationsR.jsp");
				dispatcher.forward(request, response);
				return;				
			}
			
			// Delete selected reservation
			s = "DELETE FROM Reservations WHERE rid = ?";
			ps = c.prepareStatement(s);
			ps.setInt(1, Integer.parseInt(prid));
			ps.executeUpdate();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
	/*
	 * EDITING RESERVATION
	 * Move to edit reservation menu - can edit fee, class, and seat
	 */
	private void getEditReservationOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs = null;
			ArrayList<String> al = new ArrayList<>();
			
			// Get list of transit lines
			s = "SELECT rid FROM Reservations";
			ps = c.prepareStatement(s);
			rs = ps.executeQuery();
			while(rs.next()) { al.add(rs.getString(1)); }
			request.setAttribute("resList", al);
			rs.close();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	
	}
	
	/*
	 * EDITING RESERVATION
	 * Edits reservation if no errors
	 */
	private void confirmEditReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Get user inputs
			String prid = request.getParameter("selectRes");
			String pFee = request.getParameter("fee");
			String tclass = request.getParameter("selectClass");
			String seat = request.getParameter("seat");
			
			// Check if user selected a reservation to edit
			if (prid == null) {
				String m = "Must select reservation to edit. Please select one.";
				request.setAttribute("message", m);
				getEditReservationOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editReservation.jsp");
				dispatcher.forward(request, response);
				return;
			}
			int rid = Integer.parseInt(prid);
			
			// Check for negative numbers
			if ((!pFee.equals("") && Double.parseDouble(pFee) < 0)) {
					String m = "We like money. Fee cannot be negative. Please enter positive values.";
					request.setAttribute("message", m);
					getEditReservationOptions(request, response);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editReservation.jsp");
					dispatcher.forward(request, response);
					return;
			}
			
			// Let user know they didn't select anything
			if (pFee.equals("") && tclass == null && seat.equals("")) {
				String m = "No edits were selected. Please select an edit or return to view reservations.";
				request.setAttribute("message", m);
				getEditReservationOptions(request, response);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/editReservation.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			// Update fee
			if (!pFee.equals("")) {
				double fee = Double.parseDouble(pFee);
				// Update schedule with delayed/early train
				s = "UPDATE Reservations SET fee = ? WHERE rid = ?";
				ps = c.prepareStatement(s);
				ps.setDouble(1, fee);
				ps.setInt(2, rid);
				ps.executeUpdate();
			}
			
			// Update class
			if (tclass != null) {
				s = "UPDATE Reservations SET class = ? WHERE rid = ?";
				ps = c.prepareStatement(s);
				ps.setString(1, tclass);
				ps.setInt(2, rid);
				ps.executeUpdate();
			}
			
			// Update seat number
			if (!seat.equals("")) {
				s = "UPDATE Reservations SET seat = ? WHERE rid = ?";
				ps = c.prepareStatement(s);
				ps.setString(1, seat);
				ps.setInt(2, rid);
				ps.executeUpdate();
			}
			
			// Update representative
			s = "UPDATE Reservations SET repUsername = ? WHERE rid = ?";
			ps = c.prepareStatement(s);
			ps.setString(1, request.getSession().getAttribute("Name").toString());
			ps.setInt(2, rid);
			ps.executeUpdate();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
	
	/*
	 * ADDING RESERVATION
	 * Get all schedules and set up selection
	 */
	private void getAddReservationOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs = null;
			ArrayList<String> customerAL = new ArrayList<>();

			// Generate viable rid
			s = "SELECT MAX(rid) FROM Reservations";
			ps = c.prepareStatement(s);
			rs = ps.executeQuery();
			if (rs.next()) { request.setAttribute("assignedResNum", rs.getInt(1)+1); }
			else { request.setAttribute("assignedResNum", 1); }
			rs.close();
			
			// Get list of customers
			s = "SELECT username FROM Customer";
			ps = c.prepareStatement(s);
			rs = ps.executeQuery();
			while(rs.next()) { customerAL.add(rs.getString(1)); }
			request.setAttribute("customerList", customerAL);
			rs.close();
			
			// Get list of stops
			ArrayList<StopR> stopAL = getStops(request, response);
			request.setAttribute("scheduleList", stopAL);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
	
	private void confirmAddReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/*
	 * Make an arraylist of stops to display
	 */
	private ArrayList<StopR> getStops(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat dtf =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
		SimpleDateFormat tf =  new SimpleDateFormat ("hh:mm");
				
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs = null;
			ArrayList<StopR> al = new ArrayList<>();
			
			// Get list of transit lines
			s = "SELECT * FROM Schedule";
			ps = c.prepareStatement(s);
			rs = ps.executeQuery();
			while(rs.next()) {
				String origin = rs.getString(1);
				String destination = rs.getString(2);
				String transitLine = rs.getString(3);
				int seats = rs.getInt(4);
				int stops = rs.getInt(5);
				String deptTime = dtf.format(rs.getTimestamp(6));
				String arrvTime = dtf.format(rs.getTimestamp(7));				
				String trvlTime = tf.format(rs.getTimestamp(8));
				double fare = Math.round(rs.getDouble(9)*100)/100;
				int train = rs.getInt(10);
				al.add(new StopR(origin, destination, transitLine, seats, stops, deptTime, arrvTime, trvlTime, fare, train));
			}
			
			request.setAttribute("resList", al);
			rs.close();
			
			return al;
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/*
	 * Make an arraylist of reservations to display
	 */
	private ArrayList<ReservationR> getReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat dtf =  new SimpleDateFormat ("yyyy-MM-dd hh:mm");
				
		try {
			// Connect to SQL database
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(url,"admin","rutgerscs336");

			// Query to execute
			String s = "";
			PreparedStatement ps;
		
			// Store results of query
			ResultSet rs = null;
			ArrayList<ReservationR> al = new ArrayList<>();
			
			// Get list of transit lines
			s = "SELECT * FROM Reservations";
			ps = c.prepareStatement(s);
			rs = ps.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt(1);
				double fee = Math.round(rs.getDouble(2)*100)/100;
				String date = dtf.format(rs.getTimestamp(3));
				int train = rs.getInt(4);
				String transitLine = rs.getString(5);
				String origin = rs.getString(6);
				String destination = rs.getString(7);
				String deptTime = dtf.format(rs.getTimestamp(8));
				String arrvTime = dtf.format(rs.getTimestamp(9));
				String customer = rs.getString(10);
				String representative = rs.getString(11);
				String tclass = rs.getString(12);
				String note = rs.getString(13);
				String ticketType = rs.getString(14);
				String seat = rs.getString(15);
				
				al.add(new ReservationR(rid, fee, date, train, transitLine, origin, destination, deptTime, arrvTime, customer, representative, tclass, note, ticketType, seat));
			}
			
			request.setAttribute("resList", al);
			rs.close();
			
			return al;
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		return null;
		
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

	/*
	 * Convert minutes to to hh:mm format
	 */
	private Date convertMin(int t) throws ParseException {

		int hours = t / 60;
		int minutes = t % 60;
		return (new SimpleDateFormat("HH:mm").parse(Integer.toString(hours) + ":" + Integer.toString(minutes)));
	}

	/*
	 * Add minutes to a date
	 */
	private Date calculateTime(Date start, int min) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.add(Calendar.MINUTE, min);
		return cal.getTime();
	}

}