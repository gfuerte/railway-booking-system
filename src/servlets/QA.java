package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import POJOs.Alert;
import POJOs.QAPair;

/**
 * Servlet implementation class QA
 */
@WebServlet("/QA")
public class QA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QA() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());


		if (request.getParameter("goBack") != null ) {    //return button
			request.setAttribute("message", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/loginCustomer.jsp");
			dispatcher.forward(request, response);
		}

		else if (request.getParameter("goBackR") != null ) {    //return button
			request.setAttribute("message", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/loginRepresentative.jsp");
			dispatcher.forward(request, response);
		}

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(request.getParameter("Alerts") != null) {
			
			//get list of Alerts that is found by searching reservations for customer username and finding route and then checking 
			//if alerts table has route in it along with the date information
			
			Alert a = new Alert("Delay Route #570", "1/2/2020");
			Alert b = new Alert("Delay Route #571", "1/3/2020");
			ArrayList<Alert> as = new ArrayList<>();
			as.add(a);
			as.add(b);
			
			request.setAttribute("list", as);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/alertCustomer.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("AlertsR") != null) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/alertRep.jsp");
			dispatcher.forward(request, response);
			
		}
		
		else if(request.getParameter("searchButton") != null) {
			try {

				//Get the database connection
				String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";

				Class.forName("com.mysql.jdbc.Driver");

				Connection con = DriverManager.getConnection(url,"admin","dbgroup20");



				//Get HTML Params
				String user = (String) request.getSession(false).getAttribute("Name");

				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Question WHERE Owner = ? AND Text COLLATE LATIN1_GENERAL_CI LIKE ?";

				//Create Prepared Statement
				String srch = request.getParameter("Search");
				PreparedStatement ps = con.prepareStatement(search);


				ps.setString(1, user);
				ps.setString(2, "%" + srch + "%");

				ResultSet rs = ps.executeQuery();


				ArrayList<QAPair> listQs = new ArrayList<>();

				while (rs.next()) 
				{  
					String Text = rs.getString("Text");  
					String UUID = rs.getString("UUID");
					String Owner = rs.getString("Owner");



					//Get HTML Params

					//Make a SELECT query from the table to see if user exists
					String search2 = "SELECT * FROM RailwayBookingSystem.Answer WHERE UUID = ?";

					//Create Prepared Statement
					PreparedStatement ps2 = con.prepareStatement(search2);
					ps2.setString(1, UUID);

					ResultSet rs2 = ps2.executeQuery();


					if(!rs2.isBeforeFirst()) {
						QAPair q = new QAPair(Text, "None", Owner, "None", UUID);
						listQs.add(q);
					}

					else {
						rs2.next();
						QAPair q = new QAPair(Text, rs2.getString("Text"), Owner, rs2.getString("Owner"), UUID);
						listQs.add(q);
					}

					System.out.println(Text);
				} 

				request.setAttribute("list", listQs);
				request.setAttribute("filter", srch);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/messageCustomer.jsp");
				dispatcher.forward(request, response);

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else if(request.getParameter("QAR") != null) {
			try {

				//Get the database connection
				String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";

				Class.forName("com.mysql.jdbc.Driver");

				Connection con = DriverManager.getConnection(url,"admin","dbgroup20");




				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Question";

				//Create Prepared Statement
				String srch = request.getParameter("Search");
				PreparedStatement ps = con.prepareStatement(search);

				ResultSet rs = ps.executeQuery();


				ArrayList<QAPair> listQs = new ArrayList<>();

				while (rs.next()) 
				{  
					String Text = rs.getString("Text");  
					String UUID = rs.getString("UUID");
					String Owner = rs.getString("Owner");


					//Get HTML Params

					//Make a SELECT query from the table to see if user exists
					String search2 = "SELECT * FROM RailwayBookingSystem.Answer WHERE UUID = ?";

					//Create Prepared Statement
					PreparedStatement ps2 = con.prepareStatement(search2);
					ps2.setString(1, UUID);

					ResultSet rs2 = ps2.executeQuery();


					if(!rs2.isBeforeFirst()) {
						QAPair q = new QAPair(Text, "None", Owner, "None", UUID);
						listQs.add(q);
					}

					System.out.println(Text);
				} 

				request.setAttribute("list", listQs);
				request.setAttribute("filter", srch);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/messageRep.jsp");
				dispatcher.forward(request, response);

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else if(request.getParameter("searchButtonR") != null) {
			try {

				//Get the database connection
				String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";

				Class.forName("com.mysql.jdbc.Driver");

				Connection con = DriverManager.getConnection(url,"admin","dbgroup20");



				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Question WHERE Text COLLATE LATIN1_GENERAL_CI LIKE ?";

				//Create Prepared Statement
				String srch = request.getParameter("SearchR");
				PreparedStatement ps = con.prepareStatement(search);

				ps.setString(1, "%" + srch + "%");


				ResultSet rs = ps.executeQuery();


				ArrayList<QAPair> listQs = new ArrayList<>();

				while (rs.next()) 
				{  
					String Text = rs.getString("Text");  
					String UUID = rs.getString("UUID");
					String Owner = rs.getString("Owner");



					//Get HTML Params

					//Make a SELECT query from the table to see if user exists
					String search2 = "SELECT * FROM RailwayBookingSystem.Answer WHERE UUID = ?";

					//Create Prepared Statement
					PreparedStatement ps2 = con.prepareStatement(search2);
					ps2.setString(1, UUID);

					ResultSet rs2 = ps2.executeQuery();


					if(!rs2.isBeforeFirst()) {
						QAPair q = new QAPair(Text, "None", Owner, "None", UUID);
						listQs.add(q);
					}

					System.out.println(Text);
				} 

				request.setAttribute("list", listQs);
				request.setAttribute("filter", srch);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/messageRep.jsp");
				dispatcher.forward(request, response);

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else if(request.getParameter("questionButtonR") != null) {
			String ans = request.getParameter("AnswerR");
			String rad = request.getParameter("Questions");

			if(rad == null || ans.isEmpty()) {
				request.setAttribute("message", "Please select a question to answer and type it in the answer box!");
			}
			else {
				try {
					//Get the database connection
					String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";

					Class.forName("com.mysql.jdbc.Driver");

					Connection con = DriverManager.getConnection(url,"admin","dbgroup20");

					//Make a SELECT query from the table to see if user exists
					String updt = "INSERT INTO RailwayBookingSystem.Answer(`UUID`,`Text`,`Owner`) VALUES (?,?,?)";

					//Create Prepared Statement
					PreparedStatement psU = con.prepareStatement(updt);
					psU.setString(1, rad);
					psU.setString(2, ans);
					psU.setString(3, (String)request.getSession(false).getAttribute("Name"));

					psU.executeUpdate();
					;
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}


			//search all again
			try {
				//Get the database connection
				String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";

				Class.forName("com.mysql.jdbc.Driver");

				Connection con = DriverManager.getConnection(url,"admin","dbgroup20");


				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Question";

				//Create Prepared Statement
				String srch = request.getParameter("Search");
				PreparedStatement ps = con.prepareStatement(search);

				ResultSet rs = ps.executeQuery();


				ArrayList<QAPair> listQs = new ArrayList<>();

				while (rs.next()) 
				{  
					String Text = rs.getString("Text");  
					String UUID = rs.getString("UUID");
					String Owner = rs.getString("Owner");

					//Get HTML Params

					//Make a SELECT query from the table to see if user exists
					String search2 = "SELECT * FROM RailwayBookingSystem.Answer WHERE UUID = ?";

					//Create Prepared Statement
					PreparedStatement ps2 = con.prepareStatement(search2);
					ps2.setString(1, UUID);

					ResultSet rs2 = ps2.executeQuery();


					if(!rs2.isBeforeFirst()) {
						QAPair q = new QAPair(Text, "None", Owner, "None", UUID);
						listQs.add(q);
					}

					System.out.println(Text);
				} 

				request.setAttribute("list", listQs);
				request.setAttribute("filter", srch);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/messageRep.jsp");
				dispatcher.forward(request, response);

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else {
			try {

				//Get the database connection
				String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";

				Class.forName("com.mysql.jdbc.Driver");

				Connection con = DriverManager.getConnection(url,"admin","dbgroup20");


				if(request.getParameter("questionButton") != null) {

					request.getSession(false).getAttribute("Name");
					String Text = request.getParameter("Question");

					//Make a SELECT query from the table to see if user exists
					String updt = "INSERT INTO RailwayBookingSystem.Question(`UUID`,`Text`,`Owner`) VALUES (?,?,?)";

					//Create Prepared Statement
					PreparedStatement psU = con.prepareStatement(updt);
					psU.setString(1, UUID.randomUUID().toString().replace("-", ""));
					psU.setString(2, Text);
					psU.setString(3, (String) request.getSession(false).getAttribute("Name"));

					psU.executeUpdate();

				}


				//Get HTML Params
				String user = (String) request.getSession(false).getAttribute("Name");

				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Question WHERE Owner = ?";

				//Create Prepared Statement
				PreparedStatement ps = con.prepareStatement(search);
				ps.setString(1, user);

				ResultSet rs = ps.executeQuery();


				ArrayList<QAPair> listQs = new ArrayList<>();

				while (rs.next()) 
				{  
					String Text = rs.getString("Text");  
					String UUID = rs.getString("UUID");
					String Owner = rs.getString("Owner");



					//Get HTML Params

					//Make a SELECT query from the table to see if user exists
					String search2 = "SELECT * FROM RailwayBookingSystem.Answer WHERE UUID = ?";

					//Create Prepared Statement
					PreparedStatement ps2 = con.prepareStatement(search2);
					ps2.setString(1, UUID);

					ResultSet rs2 = ps2.executeQuery();


					if(!rs2.isBeforeFirst()) {
						QAPair q = new QAPair(Text, "None", Owner, "None", UUID);
						listQs.add(q);
					}

					else {
						rs2.next();
						QAPair q = new QAPair(Text, rs2.getString("Text"), Owner, rs2.getString("Owner"), UUID);
						listQs.add(q);
					}

					System.out.println(Text);
				} 

				request.setAttribute("list", listQs);
				request.setAttribute("filter", "All");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/messageCustomer.jsp");
				dispatcher.forward(request, response);

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


}
