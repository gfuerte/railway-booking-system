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
        String query = "SELECT c.username, c.fname, c.lname, t1.revenue FROM Customer c, (SELECT customerUsername, SUM(fee) revenue FROM Reservations GROUP BY (customerUsername)) t1 WHERE c.username = t1.customerUsername AND t1.revenue = (SELECT MAX(revenue) FROM (SELECT customerUsername, SUM(fee) revenue FROM Reservations GROUP BY (customerUsername)) t1)";
        
        ResultSet output = statement.executeQuery(query);
        %>
        	<div align="center">
        	<h2>BEST CUSTOMER</h2>
            <TABLE BORDER="1">
                <TR>
                    <TH>Username</TH>
                    <TH>First Name</TH>
                    <TH>Last Name</TH>
                    <TH>Revenue Generated</TH>
                </TR>
        <%
        while(output.next())
        {
            %>
                <TR>
                        <TD> <%= output.getString("c.username") %></TD>
                        <TD> <%= output.getString("c.FName") %></TD>
                        <TD> <%= output.getString("c.LName") %></TD>
                        <TD> <%= output.getString("t1.revenue") %></TD>
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