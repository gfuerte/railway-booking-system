<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<HTML>
    <BODY>
<%
    try
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
        Connection conn = DriverManager.getConnection(url,"admin","dbgroup20");
        Statement statement=conn.createStatement();
        
        if(request.getParameter("optionsRevenue").equals("transitLine")){
	        String query = "SELECT transitLine, SUM(fee)revenue FROM Reservations GROUP BY " + request.getParameter("optionsRevenue");
	        
	        ResultSet output = statement.executeQuery(query);
	        %>
	            <TABLE BORDER="1">
	                <TR>
	                    <TH>Transit Line</TH>
	                    <TH>Revenue</TH>
	                </TR>
	        <%
	        while(output.next())
	        {
	            %>
	                <TR>
	                        <TD> <%= output.getString("transitLine") %></TD>
	                        <TD> <%= output.getString("revenue") %></TD>
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
	        output.close();
	        statement.close();
	        conn.close();
        }
        else if(request.getParameter("optionsRevenue").equals("destination")){
	        String query = "SELECT destination, SUM(fee)revenue FROM Reservations GROUP BY " + request.getParameter("optionsRevenue");
	        
	        ResultSet output = statement.executeQuery(query);
	        %>
	            <TABLE BORDER="1">
	                <TR>
	                    <TH>Destination City</TH>
	                    <TH>Revenue</TH>
	                </TR>
	        <%
	        while(output.next())
	        {
	            %>
	                <TR>
	                        <TD> <%= output.getString("destination") %></TD>
	                        <TD> <%= output.getString("revenue") %></TD>
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
	        output.close();
	        statement.close();
	        conn.close();
        }
        if(request.getParameter("optionsRevenue").equals("customerUsername")){
	        String query = "SELECT customerUsername, SUM(fee)revenue FROM Reservations GROUP BY " + request.getParameter("optionsRevenue");
	        
	        ResultSet output = statement.executeQuery(query);
	        %>
	            <TABLE BORDER="1">
	                <TR>
	                    <TH>Customer Username</TH>
	                    <TH>Revenue</TH>
	                </TR>
	        <%
	        while(output.next())
	        {
	            %>
	                <TR>
	                        <TD> <%= output.getString("customerUsername") %></TD>
	                        <TD> <%= output.getString("revenue") %></TD>
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
	        output.close();
	        statement.close();
	        conn.close();
        }
    }
    catch(Exception e)
    {
        out.print(e);
    }
%>