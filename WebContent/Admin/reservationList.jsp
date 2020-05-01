<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<HTML>
    <BODY>
<%
    try
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
        Connection conn = DriverManager.getConnection(url,"admin","rutgerscs336");
        Statement statement=conn.createStatement();
        PreparedStatement ps;
        ResultSet output;
        
        String transitLine = request.getParameter("transitLine");
        String trainNum = request.getParameter("trainNum");
        String cname = request.getParameter("cname");
        if(!transitLine.isEmpty() && !trainNum.isEmpty()){
	        String query = "SELECT * FROM Reservations, Customer WHERE (Reservations.customerUsername = Customer.username AND transitLine = ? AND train = ?)";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, transitLine); ps.setString(2, trainNum);
	        output = ps.executeQuery();
	        
	        %>
	        <div align="center">
            <h2>RESERVATION LISTING</h2>
            <TABLE BORDER="1">
                <TR>
                    <TH>Reservation ID</TH>
                    <TH>Username</TH>
                    <TH>First Name</TH>
                    <TH>Last Name</TH>
                    <TH>Date</TH>
                    <TH>Origin</TH>
                    <TH>Destination</TH>
                </TR>
        <%
        while(output.next())
        {
            %>
                <TR>
                        <TD> <%= output.getString("rid") %></TD>
                        <TD> <%= output.getString("customerUsername") %></TD>
                        <TD> <%= output.getString("FName") %></TD>
                        <TD> <%= output.getString("LName") %></TD>
                        <TD> <%= output.getString("date") %></TD>
                        <TD> <%= output.getString("origin") %></TD>
                        <TD> <%= output.getString("destination") %></TD>
                </TR>
            <%
        }
        %>
            </TABLE>
            </div>
	<form method="get" action="${pageContext.request.contextPath}/adminFunctions">
		<input type="submit" name = "goBack" value="Return">
	</form>
                </BODY>
            </HTML>
        <%
        /* output.close();
        statement.close();
        conn.close(); */
	        
	        
        }
        if(!cname.isEmpty()){
	        String query = "SELECT * FROM Reservations, Customer WHERE (Reservations.customerUsername = Customer.username AND customerUsername = ?)";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, cname);
	        output = ps.executeQuery();
	        
	        %>
            <TABLE BORDER="1">
                <TR>
                    <TH>Reservation ID</TH>
                    <TH>Username</TH>
                    <TH>First Name</TH>
                    <TH>Last Name</TH>
                    <TH>Date</TH>
                    <TH>Origin</TH>
                    <TH>Destination</TH>
                </TR>
        <%
        while(output.next())
        {
            %>
                <TR>
                        <TD> <%= output.getString("rid") %></TD>
                        <TD> <%= output.getString("customerUsername") %></TD>
                        <TD> <%= output.getString("FName") %></TD>
                        <TD> <%= output.getString("LName") %></TD>
                        <TD> <%= output.getString("date") %></TD>
                        <TD> <%= output.getString("origin") %></TD>
                        <TD> <%= output.getString("destination") %></TD>
                </TR>
            <%
        }
        %>
            </TABLE>
	<form method="get" action="${pageContext.request.contextPath}/adminFunctions">
		<input type="submit" name = "goBack" value="Return">
	</form>
                </BODY>
            </HTML>
        <%
        /* output.close();
        statement.close();
        conn.close(); */
	        
        }
        
        
        
    }
    catch(Exception e)
    {
        out.print(e);
    }
%>