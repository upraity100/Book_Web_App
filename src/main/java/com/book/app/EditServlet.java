package com.book.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 @WebServlet("/edit")
public class EditServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		final String query = "select BOOKNAME,BOOKEDITION,BOOKPRICE from book where id=?";
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","1234");
			PreparedStatement ps = con.prepareStatement(query);
			int id = Integer.parseInt(req.getParameter("id"));
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
			pw.println("<body class='container-fluid card' style='width:40rem;'>");
			pw.println("<h2 class='bg-danger text-white text-center'>Edit Page</h2>");
			pw.println("<form action='EditServlet2?id="+id+"' method='post'>");
			pw.println("<table class='table table-hover'>");
			pw.println("<tr> ");
			pw.println("<td>Book Name</td>");
			pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr> ");
			pw.println("<tr> ");
			pw.println("<td>Book Edition</td>");
			pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr> ");
			pw.println("<tr> ");
			pw.println("<td>Book Price</td>");
			pw.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
			pw.println("</tr> ");
			pw.println("<tr> ");
			pw.println("<td><input type='submit' value='edited'></td>");
			pw.println("<td><input type='reset' value='cancel'></td>");
			pw.println("</tr> ");
			pw.println("</table");
			pw.println("</body>");
			pw.println("</form>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<h5><a href='home.html'>Home</a></h5>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
}
