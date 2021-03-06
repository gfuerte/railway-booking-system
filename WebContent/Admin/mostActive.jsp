<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<HTML>
    <BODY>
<%
    try
    {
    	int count = 0;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://cs336-g20.cary0h7flduu.us-east-1.rds.amazonaws.com:3306/RailwayBookingSystem";
        Connection conn = DriverManager.getConnection(url,"admin","rutgerscs336");
        Statement statement=conn.createStatement();
        String query = "SELECT transitLine, COUNT(*) num FROM Reservations GROUP BY transitLine ORDER BY num DESC";
        
        ResultSet output = statement.executeQuery(query);
        %>
        	<div align="center">
            <h2>TOP 5 ACTIVE TRANSIT LINES</h2>
            <TABLE BORDER="1">
                <TR>
                    <TH>Transit Line</TH>
                    <TH>Number of Reservations</TH>
                </TR>
        <%
        while(output.next() && count < 5)
        {
            %>
                <TR>
                        <TD> <%= output.getString("transitLine") %></TD>
                        <TD> <%= output.getString("num") %></TD>
                </TR>
            <%
            count++;
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