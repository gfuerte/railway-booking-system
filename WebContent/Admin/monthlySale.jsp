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
        String query = "SELECT * FROM Reservations WHERE MONTH(date) = " + request.getParameter("optionsMonth");
        
        ResultSet output = statement.executeQuery(query);
        %>
            <div align="center">
            <h2>MONTHLY SALES</h2>
            <TABLE BORDER="1">
                <TR>
                    <TH>Reservation ID</TH>
                    <TH>Date and Time</TH>
                    <TH>Username</TH>
                    <TH>Fare</TH>
                </TR>
        <%
        while(output.next())
        {
            %>
                <TR>
                        <TD> <%= output.getString("rid") %></TD>
                        <TD> <%= output.getString("date") %></TD>
                        <TD> <%= output.getString("customerUsername") %></TD>
                        <TD> <%= output.getString("fee") %></TD>
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
        output.close();
        statement.close();
        conn.close();
    }
    catch(Exception e)
    {
        out.print(e);
    }
%>