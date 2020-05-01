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
import POJOs.Train; 

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
		HttpSession session = request.getSession();  
		String message = "";
	    request.setAttribute("confirmation", message);
	    
    	ArrayList<Train> trains = (ArrayList<Train>) session.getAttribute("availableTrainsSession");

	    if(request.getParameter("reserve") != null) {
	    	String trainNumber = request.getParameter("trainNumber");
	    	if(trainNumber != null && !trainNumber.isEmpty()) {
	    		int index = -1;
	    		for(int i = 0; i < trains.size(); i++) {
	    			if(trains.get(i).trainNum == Integer.parseInt(trainNumber)) {
	    				index = i;
	    				break;
	    			}
	    		}
	    		if(index == -1) System.out.println("Warning: This should not be happening!");

	    		ArrayList<Train> selectedTrain = new ArrayList<>();
	    		selectedTrain.add(trains.get(index));
	    		
	    		//Set Fares
	    		double fare = trains.get(index).fare;
	    		request.setAttribute("owa", fare);
	    		request.setAttribute("owc", fare/2);
	    		request.setAttribute("owd", fare/2);
	    		request.setAttribute("rta", fare*2);
	    		request.setAttribute("rtc", fare);
	    		request.setAttribute("rtd", fare);
	    		request.setAttribute("wt", fare*7);
	    		request.setAttribute("mt", fare*28);   	
	    		
	    		//Redirect
	    		request.setAttribute("selectedTrainRequest", selectedTrain);
	    		session.setAttribute("selectedTrainSession", selectedTrain);	
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/finalizeReservation.jsp");
				dispatcher.forward(request, response);
	    	} else {
	    		request.setAttribute("availableTrainsRequest", trains);
	    		message = "Please Select Train Number";
			    request.setAttribute("confirmation", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/createReservations.jsp");
				dispatcher.forward(request, response);
	    	}
	    }
	}
}
