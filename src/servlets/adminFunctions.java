package servlets;

import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * Servlet implementation class adminFunctions
 */
@WebServlet("/adminFunctions")
public class adminFunctions extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminFunctions() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginAdmin.jsp");
			dispatcher.forward(request, response);  
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(request.getParameter("AddCus") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/adminAddCustomer.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("AddEmp") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/adminAddEmployee.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("getMonthlySales") != null) {
			if(request.getParameter("optionsMonth").contentEquals("none")) {
				String message = "Please select a month.";
			    request.setAttribute("message1", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/loginAdmin.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/monthlySale.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getReservationsByTrain") != null) {
			if(request.getParameter("transitLine").isEmpty() || request.getParameter("trainNum").isEmpty()) {
				String message = "Please fill out both train information fields.";
			    request.setAttribute("message2", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/loginAdmin.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/reservationList.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("getReservationsByCus") != null) {
			if(request.getParameter("cname").isEmpty()) {
				String message = "Please fill out customer username field.";
			    request.setAttribute("message3", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/loginAdmin.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/reservationList.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getRevenues") != null) {
			if(request.getParameter("optionsRevenue").contentEquals("none")) {
				String message = "Please select an option.";
			    request.setAttribute("message4", message);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/loginAdmin.jsp");
	            dispatcher.forward(request, response);
	            return;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/revenueList.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("bestCus") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/bestCustomer.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("mostActive") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/mostActive.jsp");
			dispatcher.forward(request, response);
		}
		else {
			ResultSet result = null;
			try {
				//Get the database connection
				String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
				
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con = DriverManager.getConnection(url,"admin","rutgerscs336");
	
				//determine redirect
				if(request.getParameter("addCustomer") != null) {
					//Create a SQL statement
					
//					Statement stmt = con.createStatement();
					
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
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/adminAddCustomer.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					else {
						result = ps.executeQuery();
					}
					
//					HttpSession session=request.getSession(); 
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
						
						//insert into cus
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
	
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/loginAdmin.jsp");
			            dispatcher.forward(request, response);
					} 
					else {
						String message = "Invalid Sign Up. User exists. Please try another unique username";
					    request.setAttribute("message", message);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/adminAddCustomer.jsp");
		            	dispatcher.forward(request, response);
					}
				}
				
				
				if(request.getParameter("addEmployee") != null) {
	
					//Create a SQL statement
//					Statement stmt = con.createStatement();
					
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
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/adminAddEmployee.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					else {
						result = ps.executeQuery();
					}
					
//					HttpSession session=request.getSession(); 
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
	
						
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/loginAdmin.jsp");
			            dispatcher.forward(request, response);
					} 
					else {
						String message = "Invalid Sign Up. User exists. Please try another unique username";
					    request.setAttribute("message", message);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/adminAddEmployee.jsp");
		            	dispatcher.forward(request, response);
					}
				}
				if(request.getParameter("ModifyCus") != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyCus.jsp");
					dispatcher.forward(request, response);
				}
				if(request.getParameter("ModifyRep") != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyRep.jsp");
					dispatcher.forward(request, response);
				}
				
				if(request.getParameter("DeleteUser") != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/deleteUser.jsp");
					dispatcher.forward(request, response);
				}
				if(request.getParameter("saveChangesCus") != null) {
					String modification = request.getParameter("modification");
					
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
					
					if(modification.isEmpty()) {
						String message = "Please fill out Customer Username";
					    request.setAttribute("message", message);
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyCus.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					
					if(user.isEmpty() && pswd.isEmpty() && fname.isEmpty() && lname.isEmpty() && address.isEmpty() && city.isEmpty() && state.isEmpty() && zcode.isEmpty() && phone.isEmpty() && email.isEmpty()) {
						String message = "Please fill out at least one field to update.";
					    request.setAttribute("message", message);
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyCus.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					
					if(!modification.isEmpty()) {
						//Make a SELECT query from the table to see if user exists
						String search = "SELECT * FROM RailwayBookingSystem.Login WHERE (username = ? AND level = \"C\")"  ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, modification);
						
						
						result = ps.executeQuery();
	
						
//						HttpSession session=request.getSession(); 
						//check if empty result set
						if (!result.isBeforeFirst() ) { 
							String message = "Customer does not exist in the system.";
						    request.setAttribute("message", message);
						    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyCus.jsp");
				            dispatcher.forward(request, response);
				            return;
						}
					}
					if(!user.isEmpty()){
						
						//Make a SELECT query from the table to see if user exists
						String search = "SELECT * FROM RailwayBookingSystem.Login WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, user);
						
						
						result = ps.executeQuery();
	
						
//						HttpSession session=request.getSession(); 
						//check if empty result set
						if (!result.isBeforeFirst() ) { 
							search = "UPDATE RailwayBookingSystem.Login SET username = ? WHERE username = ?" ;
							
							//Create Prepared Statement
							ps = con.prepareStatement(search);
							ps.setString(1, user); ps.setString(2, modification);
							
							ps.executeUpdate();
							modification= user;
						}
						else{
							String message = "Invalid Update. Username taken. Please try another unique username";
						    request.setAttribute("message", message);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyCus.jsp");
			            	dispatcher.forward(request, response);
			            	return;
						}
					}
					if(!pswd.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Login SET password = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, pswd); ps.setString(2, modification);
						
						ps.executeUpdate();
					}
					if(!fname.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET FName = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, fname); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!lname.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET LName = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, lname); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!address.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET Address = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, address); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!city.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET City = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, city); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!state.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET State = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, state); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!zcode.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET Zcode = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, zcode); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!phone.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET Phone = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, phone); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!email.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Customer SET Email = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, email); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					
					String message = "Customer update successfully complete.";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyCus.jsp");
		            dispatcher.forward(request, response);
		            return;
				}
				
				
				
				if(request.getParameter("saveChangesRep") != null) {
					String modification = request.getParameter("modification");
					
					String user = request.getParameter("username");
					String pswd = request.getParameter("pswd");
					String fname = request.getParameter("fname");
					String lname = request.getParameter("lname");
					String ssn = request.getParameter("ssn");
					
					if(modification.isEmpty()) {
						String message = "Please fill out Customer Representative Username";
					    request.setAttribute("message", message);
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyRep.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					
					if(user.isEmpty() && pswd.isEmpty() && fname.isEmpty() && lname.isEmpty() && ssn.isEmpty()) {
						String message = "Please fill out at least one field to update.";
					    request.setAttribute("message", message);
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyRep.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					
					if(!modification.isEmpty()) {
						//Make a SELECT query from the table to see if user exists
						String search = "SELECT * FROM RailwayBookingSystem.Login WHERE (username = ? AND level = \"R\")" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, modification);
						
						
//						result = ps.executeQuery();
	
						
//						HttpSession session=request.getSession(); 
						//check if empty result set
						if (!result.isBeforeFirst() ) { 
							String message = "Customer representative does not exist in the system.";
						    request.setAttribute("message", message);
						    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyRep.jsp");
				            dispatcher.forward(request, response);
				            return;
						}
					}
					if(!user.isEmpty()){
						
						//Make a SELECT query from the table to see if user exists
						String search = "SELECT * FROM RailwayBookingSystem.Login WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, user);
						
						
						result = ps.executeQuery();
	
						
//						HttpSession session=request.getSession(); 
						//check if empty result set
						if (!result.isBeforeFirst() ) { 
							search = "UPDATE RailwayBookingSystem.Login SET username = ? WHERE username = ?" ;
							
							//Create Prepared Statement
							ps = con.prepareStatement(search);
							ps.setString(1, user); ps.setString(2, modification);
							
							ps.executeUpdate();
							modification= user;
						}
						else{
							String message = "Invalid Update. Username taken. Please try another unique username";
						    request.setAttribute("message", message);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyRep.jsp");
			            	dispatcher.forward(request, response);
			            	return;
						}
					}
					if(!pswd.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Login SET password = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, pswd); ps.setString(2, modification);
						
						ps.executeUpdate();
					}
					if(!fname.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Representative SET FName = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, fname); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!lname.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Representative SET LName = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, lname); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					if(!ssn.isEmpty()){
						String search = "UPDATE RailwayBookingSystem.Representative SET SSN = ? WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, ssn); ps.setString(2, modification);
						
						ps.executeUpdate();
						
						
					}
					
					String message = "Customer representative update successfully complete.";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modifyRep.jsp");
		            dispatcher.forward(request, response);
		            return;
				}
				if(request.getParameter("delete") != null) {
					String deleteThisUser = request.getParameter("deleteThisUser");
					
					if(deleteThisUser.isEmpty()) {
						String message = "Please enter a username.";
					    request.setAttribute("message", message);
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/deleteUser.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					else if(deleteThisUser.contentEquals("admin")){
						String message = "Permission Denied. You cannot delete admin.";
					    request.setAttribute("message", message);
					    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/deleteUser.jsp");
			            dispatcher.forward(request, response);
			            return;
					}
					
					else{
						//Make a SELECT query from the table to see if user exists
						String search = "SELECT * FROM RailwayBookingSystem.Login WHERE username = ?" ;
						
						//Create Prepared Statement
						PreparedStatement ps = con.prepareStatement(search);
						ps.setString(1, deleteThisUser);
						
						
						result = ps.executeQuery();
	
						
//						HttpSession session=request.getSession(); 
						//check if empty result set
						if (!result.isBeforeFirst() ) { 
							String message = "User does not exist in the system.";
						    request.setAttribute("message", message);
						    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/deleteUser.jsp");
				            dispatcher.forward(request, response);
				            return;
						}
						else {
							search = "DELETE FROM RailwayBookingSystem.Login WHERE username = ?" ;
							
							//Create Prepared Statement
							ps = con.prepareStatement(search);
							ps.setString(1, deleteThisUser);
							
							ps.executeUpdate();
						}
					}
					
					String message = "User successfully deleted.";
				    request.setAttribute("message", message);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/deleteUser.jsp");
		            dispatcher.forward(request, response);
		            return;
				}
				
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}

}
