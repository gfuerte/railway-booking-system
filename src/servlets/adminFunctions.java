package servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		if(request.getParameter("ModifyCusInfo") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/editInfo.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("ModifyEmpInfo") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/editInfo.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getMonthlySales") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/monthlySale.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getReservationListT") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/reservationList.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getReservationListCn") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/reservationList.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getRevenueListTl") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/revenueList.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getRevenueListDc") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/revenueList.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("getRevenueListCn") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/revenueList.jsp");
			dispatcher.forward(request, response);
		}
	}

}
