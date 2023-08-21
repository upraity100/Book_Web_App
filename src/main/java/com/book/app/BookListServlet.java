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

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		final String query = "select * from book";
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","1234");
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
			pw.println("<body class='container-fluid card' style='width:40rem;'>");
			pw.println("<h2 class='bg-danger text-white text-center'>Book List</h2>");
			pw.println("<table class='table table-hover'>");
			pw.println("<tr> ");
			pw.println("<th> Book Id </td>");
			pw.println("<th> Book Name </td>");
			pw.println("<th> Book Edition </td>");
			pw.println("<th> Book Price </td>");
			pw.println("<th> Edit </td>");
			pw.println("<th> Delete </td>");
			pw.println("<th> FindById </td>");
			pw.println("</tr> ");
			while(rs.next())
			{
				pw.println("<tr> ");
				pw.println("<td> "+rs.getInt(1)+" </td>");
				pw.println("<td> "+rs.getString(2)+" </td>");
				pw.println("<td> "+rs.getString(3)+" </td>");
				pw.println("<td> "+rs.getFloat(4)+" </td>");
				pw.println("<td><a href='edit?id="+rs.getInt(1)+"'>edit</a></td>");
				pw.println("<td><a href='delete?id="+rs.getInt(1)+"'>delete</a></td>");
				pw.println("<td><a href='GetByIdServlet?id="+rs.getInt(1)+"'>findbyid</a></td>");
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
