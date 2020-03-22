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
			request.setAttribute("message", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        	dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet result = null;
		try {

			//Get the database connection
			String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/BarBeerDrinkerSample";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url,"admin","password");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			
			//Get HTML Params
			String user = request.getParameter("username");
			String pswd = request.getParameter("pswd");
			
			//Make a SELECT query from the table to see if user exists
			String search = "SELECT * FROM Assignment3.login WHERE username = ? AND password = ?";
			
			//Create Prepared Statement
			PreparedStatement ps = con.prepareStatement(search);
			ps.setString(1, user);
			ps.setString(2, pswd);
			
			
			if(user.isEmpty() || pswd.isEmpty()) {
				String message = "Please fill out username AND password";
			    request.setAttribute("message", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			else {
				result = ps.executeQuery();
			}
			
			//determine redirect
			if(request.getParameter("loginButton") != null) {
				//check if empty result set
				if (!result.isBeforeFirst() ) {    
					String message = "Invalid login";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		            dispatcher.forward(request, response);
				} 
				else {
					request.setAttribute("Name", user);
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	            	dispatcher.forward(request, response);
				}
			}
			else {
				if (!result.isBeforeFirst() ) {    
					String insert = "INSERT INTO Assignment3.login (username, password) VALUES (?, ?)";
					
					//Create Prepared Statement
					PreparedStatement ps2 = con.prepareStatement(insert);
					ps2.setString(1, user);
					ps2.setString(2, pswd);
					
					ps2.executeUpdate();
					
					
					String message = "Successful Signup";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		            dispatcher.forward(request, response);
				} 
				else {
					String message = "User already Exists";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		            dispatcher.forward(request, response);
				}
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}			
		//doGet(request, response);
	}

}
