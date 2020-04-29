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
        String query = "SELECT * FROM Reservations WHERE MONTH(date) = ";
        if(request.getParameter("month").equals("January")){query += "1";}
        else if(request.getParameter("month").equals("February")){query += "2";}
        else if(request.getParameter("month").equals("March")){query += "3";}
        else if(request.getParameter("month").equals("April")){query += "4";}
        else if(request.getParameter("month").equals("May")){query += "5";}
        else if(request.getParameter("month").equals("June")){query += "6";}
        else if(request.getParameter("month").equals("July")){query += "7";}
        else if(request.getParameter("month").equals("August")){query += "8";}
        else if(request.getParameter("month").equals("September")){query += "9";}
        else if(request.getParameter("month").equals("October")){query += "10";}
        else if(request.getParameter("month").equals("November")){query += "11";}
        else if(request.getParameter("month").equals("December")){query += "12";}
        
        ResultSet output = statement.executeQuery(query);
        %>
            <TABLE BORDER="1">
                <TR>
                    <TH>Reservation ID</TH>
                    <TH>Date</TH>
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