package servlets;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



/**
 * Servlet implementation class redirect
 */
@WebServlet("/redirect")
public class redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public redirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("logoutButton")!= null){
			HttpSession session=request.getSession();  
			session.invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        	dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if (request.getParameter("goBack") != null ) {    //return button
			HttpSession session=request.getSession();  
			session.invalidate();
			request.setAttribute("message", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        	dispatcher.forward(request, response);
		}
		
		ResultSet result = null;
		try {

			//Get the database connection
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url,"admin","dbgroup20");

			//determine redirect
			if(request.getParameter("LoginButton") != null) {
				
				//Create a SQL statement
				Statement stmt = con.createStatement();
				
				//Get HTML Params
				String user = request.getParameter("username");
				String pswd = request.getParameter("pswd");
				
				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Login WHERE username = ? AND password = ?";
				
				//Create Prepared Statement
				PreparedStatement ps = con.prepareStatement(search);
				ps.setString(1, user);
				ps.setString(2, pswd);
				
				
				if(user.isEmpty() || pswd.isEmpty()) {
					String message = user.isEmpty() ? "Please fill out a username" : "Please fill out a password";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		            dispatcher.forward(request, response);
		            return;
				}
				else {
					result = ps.executeQuery();
				}
				
				HttpSession session=request.getSession(); 
				//check if empty result set
				if (!result.isBeforeFirst() ) {    
					String message = "Invalid login";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		            dispatcher.forward(request, response);
				} 
				else {
					result.next(); 
			        session.setAttribute("Name", result.getString("username"));  
			        session.setAttribute("Level", result.getString("level"));
			        if(result.getString("level").equals("C")) {
			        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/loginCustomer.jsp");
			        	dispatcher.forward(request, response);
			        }
			        else if(result.getString("level").equals("R")) {
			        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/loginRepresentative.jsp");
			        	dispatcher.forward(request, response);
			        }
			        else if(result.getString("level").equals("A")) {
			        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/loginAdmin.jsp");
			        	dispatcher.forward(request, response);
			        }
	            	
				}
			}
			else if(request.getParameter("CustomerButton") != null) {
				request.setAttribute("message", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login/customerSignUp.jsp");
            	dispatcher.forward(request, response);
			}
			else if(request.getParameter("RepButton") != null) {
				request.setAttribute("message", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login/repSignUp.jsp");
            	dispatcher.forward(request, response);
			}
			else if(request.getParameter("repSignUp") != null) {

				//Create a SQL statement
				Statement stmt = con.createStatement();
				
				//Get HTML Params
				String user = request.getParameter("username");
				String pswd = request.getParameter("pswd");
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String ssn = request.getParameter("ssn");
				
				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Login WHERE username = ?" ;
				
				//Create Prepared Statement
				PreparedStatement ps = con.prepareStatement(search);
				ps.setString(1, user);
				
				
				if(user.isEmpty() || pswd.isEmpty() || fname.isEmpty() || lname.isEmpty() || ssn.isEmpty()) {
					String message = "Please fill out all fields";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Login/repSignUp.jsp");
		            dispatcher.forward(request, response);
		            return;
				}
				else {
					result = ps.executeQuery();
				}
				
				HttpSession session=request.getSession(); 
				//check if empty result set
				if (!result.isBeforeFirst() ) { 
					//if empty insert into login table and customer table
					
					//insert into login
					search = "INSERT INTO RailwayBookingSystem.Login(username, password, level) VALUES (?,?,?)" ;
					ps = con.prepareStatement(search);
					ps.setString(1, user);
					ps.setString(2, pswd);
					ps.setString(3, "R");
					
					ps.executeUpdate();
					
					//insert into rep
					search = "INSERT INTO RailwayBookingSystem.Representative(LName, FName, SSN, username) VALUES (?,?,?,?)" ;
					ps = con.prepareStatement(search);
					ps.setString(1, lname);
					ps.setString(2, fname);
					ps.setString(3, ssn);
					ps.setString(4, user);
					
					ps.executeUpdate();

					session.setAttribute("Name", user);  
			        session.setAttribute("Level", "R");
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Representative/loginRepresentative.jsp");
		            dispatcher.forward(request, response);
				} 
				else {
					String message = "Invalid Sign Up. User exists. Please try another unique username";
				    request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Login/repSignUp.jsp");
	            	dispatcher.forward(request, response);
				}
			}
			
			else if(request.getParameter("customerSignUp") != null) {
				//Create a SQL statement
				
				Statement stmt = con.createStatement();
				
				//Get HTML Params
				String user = request.getParameter("username");
				String pswd = request.getParameter("pswd");
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String address = request.getParameter("address");
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				String zcode = request.getParameter("zcode");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				
				//Make a SELECT query from the table to see if user exists
				String search = "SELECT * FROM RailwayBookingSystem.Login WHERE username = ?" ;
				
				//Create Prepared Statement
				PreparedStatement ps = con.prepareStatement(search);
				ps.setString(1, user);
				
				
				if(user.isEmpty() || pswd.isEmpty() || fname.isEmpty() || lname.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || zcode.isEmpty() || phone.isEmpty() || email.isEmpty()) {
					String message = "Please fill out all fields";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Login/customerSignUp.jsp");
		            dispatcher.forward(request, response);
		            return;
				}
				else {
					result = ps.executeQuery();
				}
				
				HttpSession session=request.getSession(); 
				//check if empty result set
				if (!result.isBeforeFirst() ) { 
					//if empty insert into login table and customer table
					
					//insert into login
					search = "INSERT INTO RailwayBookingSystem.Login(username, password, level) VALUES (?,?,?)" ;
					ps = con.prepareStatement(search);
					ps.setString(1, user);
					ps.setString(2, pswd);
					ps.setString(3, "C");
					
					ps.executeUpdate();
					
					//insert into rep
					search = "INSERT INTO RailwayBookingSystem.Customer(LName, FName, Address, City, State, Zcode, Phone, Email, username) VALUES (?,?,?,?,?,?,?,?,?)" ;
					ps = con.prepareStatement(search);
					ps.setString(1, lname);
					ps.setString(2, fname);
					ps.setString(3, address);
					ps.setString(4, city);
					ps.setString(5, state);
					ps.setString(6, zcode);
					ps.setString(7, phone);
					ps.setString(8, email);
					ps.setString(9, user);

					ps.executeUpdate();

					session.setAttribute("Name", user);  
			        session.setAttribute("Level", "C");
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/loginCustomer.jsp");
		            dispatcher.forward(request, response);
				} 
				else {
					String message = "Invalid Sign Up. User exists. Please try another unique username";
				    request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Login/customerSignUp.jsp");
	            	dispatcher.forward(request, response);
				}
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}			
		//doGet(request, response);
	}

}
