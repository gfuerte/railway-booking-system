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
        
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String station = request.getParameter("station");
        if(!origin.isEmpty() && !destination.isEmpty()){
	        String query = "SELECT * FROM Schedule WHERE origin = ? AND destination = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, origin); ps.setString(2, destination);
	        output = ps.executeQuery();
	        
	        %>
	        <div align="center">
            <h2>SCHEDULE LISTING</h2>
            <TABLE BORDER="1">
                <TR>
                    <th>Origin</th>
                	<th>Destination</th>
                	<th>Transit Line</th>
                	<th>Available Seats</th>
            		<th>Stops</th>
            		<th>Departure</th>
            		<th>Arrival</th>
            		<th>Travel Time</th>
            		<th>Fare</th>
            		<th>Train Number</th>
                </TR>
        <%
        while(output.next())
        {
            %>
                <TR>
                        <TD> <%= output.getString("origin") %></TD>
                        <TD> <%= output.getString("destination") %></TD>
                        <TD> <%= output.getString("transitLine") %></TD>
                        <TD> <%= output.getString("avaliableSeats") %></TD>
                        <TD> <%= output.getString("stops") %></TD>
                        <TD> <%= output.getString("departureDatetime") %></TD>
                        <TD> <%= output.getString("arrivalDatetime") %></TD>
                        <TD> <%= output.getString("travelTime") %></TD>
                        <TD> <%= output.getString("fare") %></TD>
                        <TD> <%= output.getString("train") %></TD>
                </TR>
            <%
        }
        %>
            </TABLE>
            </div>
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
		<input type="submit" name = "returnToMainR" value="Return">
	</form>
                </BODY>
            </HTML>
        <%
        /* output.close();
        statement.close();
        conn.close(); */
	        
	        
        }
        if(!station.isEmpty()){
	        String query = "SELECT * FROM Schedule WHERE origin = ? UNION SELECT * FROM Schedule WHERE destination = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, station); ps.setString(2, station);
	        output = ps.executeQuery();
	        
	        %>
	        <div align="center">
            <h2>SCHEDULE LISTING</h2>
            <TABLE BORDER="1">
                <TR>
                    <th>Origin</th>
                	<th>Destination</th>
                	<th>Transit Line</th>
                	<th>Available Seats</th>
            		<th>Stops</th>
            		<th>Departure</th>
            		<th>Arrival</th>
            		<th>Travel Time</th>
            		<th>Fare</th>
            		<th>Train Number</th>
                </TR>
        <%
        while(output.next())
        {
            %>
                <TR>
                        <TD> <%= output.getString("origin") %></TD>
                        <TD> <%= output.getString("destination") %></TD>
                        <TD> <%= output.getString("transitLine") %></TD>
                        <TD> <%= output.getString("avaliableSeats") %></TD>
                        <TD> <%= output.getString("stops") %></TD>
                        <TD> <%= output.getString("departureDatetime") %></TD>
                        <TD> <%= output.getString("arrivalDatetime") %></TD>
                        <TD> <%= output.getString("travelTime") %></TD>
                        <TD> <%= output.getString("fare") %></TD>
                        <TD> <%= output.getString("train") %></TD>
                </TR>
            <%
        }
        %>
            </TABLE>
            </div>
	<form method="get" action="${pageContext.request.contextPath}/representativeFunctions">
		<input type="submit" name = "returnToMainR" value="Return">
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