package com.book.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetByIdServlet")
public class GetByIdServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","1234");
			Statement s = con.createStatement();
			int ID = Integer.parseInt(req.getParameter("id"));
			String query = ("select * from book where ID="+ID);
			ResultSet rs = s.executeQuery(query);
			
			pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
			pw.println("<body class='container-fluid card' style='width:40rem;'>");
			pw.println("<h2 class='bg-danger text-white text-center'>Book List By ID</h2>");
			pw.println("<table class='table table-hover'>");
			pw.println("<tr> ");
			pw.println("<th> Book Id </td>");
			pw.println("<th> Book Name </td>");
			pw.println("<th> Book Edition </td>");
			pw.println("<th> Book Price </td>");
			pw.println("</tr> ");
			while(rs.next())
			{
				pw.println("<tr> ");
				pw.println("<td> "+rs.getInt(1)+" </td>");
				pw.println("<td> "+rs.getString(2)+" </td>");
				pw.println("<td> "+rs.getString(3)+" </td>");
				pw.println("<td> "+rs.getFloat(4)+" </td>");
				pw.println("</tr> ");
			}
			pw.println("</table>");
			pw.println("</body>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<center><h4><a href='home.html'>Home</a></h4>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
}
