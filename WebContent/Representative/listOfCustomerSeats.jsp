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
                    <TH>Username</TH>
                    <TH>First Name</TH>
                    <TH>Last Name</TH>
                    <TH>Reservation ID</TH>
                    <TH>Seat</TH>
                    <TH>Transit Line</TH>
                    <TH>Train Number</TH>
                </TR>
        <%
        while(output.next())
        {
            %>
                <TR>
                        <TD> <%= output.getString("customerUsername") %></TD>
                        <TD> <%= output.getString("FName") %></TD>
                        <TD> <%= output.getString("LName") %></TD>
                        <TD> <%= output.getString("rid") %></TD>
                        <TD> <%= output.getString("seat") %></TD>
                        <TD> <%= output.getString("transitLine") %></TD>
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